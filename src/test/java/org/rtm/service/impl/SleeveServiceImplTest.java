package org.rtm.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rtm.exception.DuplicateSleeveNumberException;
import org.rtm.mapper.SleeveMapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.WarehouseName;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;
import org.rtm.testutlis.TestDataUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SleeveServiceImplTest {

    @InjectMocks
    private SleeveServiceImpl serviceToTest;

    @Mock
    private SleeveRepository mockSleeverepository;

    @Mock
    private WarehouseRepository mockWarehouserepository;

    @Spy
    private SleeveMapper sleeveMapper = Mappers.getMapper(SleeveMapper.class);

    @BeforeEach
    void setUp() {
        serviceToTest = new SleeveServiceImpl(
                mockSleeverepository,
                sleeveMapper,
                mockWarehouserepository
        );
    }


    @Test
    void test_whenSleeveNumberAlreadyExists_thenThrowDuplicateException() {
        SaveSleeveRequest request = TestDataUtil.createSleeveRequest();

        when(mockSleeverepository.existsBySleeveNumber(request.sleeveNumber())).thenReturn(true);

        assertThrows(DuplicateSleeveNumberException.class,
                () -> serviceToTest.saveSleeve(request));

        verify(mockSleeverepository).existsBySleeveNumber(request.sleeveNumber());

        verify(mockSleeverepository, never()).save(any());

    }

    @Test
    void test_whenValidRequest_thenSaveSleeve() {
        SaveSleeveRequest req = TestDataUtil.createSleeveRequest();


        when(mockSleeverepository.existsBySleeveNumber(req.sleeveNumber()))
                .thenReturn(false);


        Warehouse warehouse = new Warehouse();
        when(mockWarehouserepository.getWarehouseByName(
                WarehouseName.valueOf(req.warehouse())))
                .thenReturn(warehouse);

        when(mockSleeverepository.save(any(Sleeve.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SleeveResponse response = serviceToTest.saveSleeve(req);

        ArgumentCaptor<Sleeve> captor = ArgumentCaptor.forClass(Sleeve.class);
        verify(mockSleeverepository).save(captor.capture());

        Sleeve saved = captor.getValue();

        assertEquals(req.sleeveNumber(), saved.getSleeveNumber());
        assertSame(warehouse, saved.getWarehouse());

    }

    @Test
    void test_whenNoSleevesFound_thenReturnsEmptyList() {
        int sequenceNumber = 42;

        when(mockSleeverepository.findAllBySequenceNumber(sequenceNumber))
                .thenReturn(Collections.emptyList());

        List<SleeveResponse> result = serviceToTest
                .getSleevesBySleeveSequenceNumber(sequenceNumber);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(mockSleeverepository).findAllBySequenceNumber(sequenceNumber);
        verifyNoInteractions(sleeveMapper);
    }

    @Test
    void test_whenSequenceNumberExists_thenReturnsSleeveResponse() {
        int sequenceNumber = 42;
        SaveSleeveRequest sleeveRequest= TestDataUtil.createSleeveRequest();

        Sleeve s1 = TestDataUtil.createSleeve(1L, sleeveRequest);
        s1.setId(1L);
        Sleeve s2 = TestDataUtil.createSleeve(2L, sleeveRequest);
        s2.setId(2L);

        when(mockSleeverepository.findAllBySequenceNumber(sequenceNumber))
                .thenReturn(List.of(s1, s2));

        List<SleeveResponse> result = serviceToTest.getSleevesBySleeveSequenceNumber(sequenceNumber);

        assertNotNull(result);
        assertEquals(2, result.size());

        SleeveResponse r1 = result.getFirst();
        assertEquals(s1.getId(), r1.id());
        assertEquals(s1.getSequenceNumber(), r1.sequenceNumber());

        SleeveResponse r2 = result.getLast();
        assertEquals(s2.getId(), r2.id());
        assertEquals(s2.getSequenceNumber(), r2.sequenceNumber());

        verify(mockSleeverepository).findAllBySequenceNumber(sequenceNumber);

        verify(sleeveMapper, times(2)).toResponse(any(Sleeve.class));
    }

    @Test
    void test_whenValidRequest_thenReturnsCorrectSleeveResponse() {
        SaveSleeveRequest request = TestDataUtil.createSleeveRequest();

        when(mockSleeverepository.existsBySleeveNumber(request.sleeveNumber()))
                .thenReturn(false);

        Warehouse warehouse = new Warehouse();
        when(mockWarehouserepository.getWarehouseByName(WarehouseName.valueOf(request.warehouse())))
                .thenReturn(warehouse);

        Sleeve unsaved = TestDataUtil.createSleeve(null, request);
        Sleeve saved = TestDataUtil.createSleeve(123L, request);
        SleeveResponse expected = TestDataUtil.createSleeveResponse(123L, request);

        when(sleeveMapper.toEntity(request)).thenReturn(unsaved);
        when(mockSleeverepository.save(unsaved)).thenReturn(saved);
        when(sleeveMapper.toResponse(saved)).thenReturn(expected);

        SleeveResponse actual = serviceToTest.saveSleeve(request);

        assertSame(expected, actual);
    }
}

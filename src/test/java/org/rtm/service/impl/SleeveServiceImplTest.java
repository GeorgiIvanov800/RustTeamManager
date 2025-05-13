package org.rtm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SleeveServiceImplTest {

    @InjectMocks
    private SleeveServiceImpl serviceToTest;

    @Mock
    private SleeveRepository mockSleeveRepository;

    @Mock
    private WarehouseRepository mockWarehouseRepository;

    @Spy
    private SleeveMapper sleeveMapper = Mappers.getMapper(SleeveMapper.class);

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void test_whenSleeveNumberAlreadyExists_thenThrowDuplicateException() {
        SaveSleeveRequest request = TestDataUtil.createSleeveRequest();

        when(mockSleeveRepository.existsBySleeveNumber(request.sleeveNumber())).thenReturn(true);

        assertThrows(DuplicateSleeveNumberException.class,
                () -> serviceToTest.saveSleeve(request));

        verify(mockSleeveRepository).existsBySleeveNumber(request.sleeveNumber());

        verify(mockSleeveRepository, never()).save(any());

    }

    @Test
    void test_whenValidRequest_thenSaveSleeve() {
        SaveSleeveRequest req = TestDataUtil.createSleeveRequest();


        when(mockSleeveRepository.existsBySleeveNumber(req.sleeveNumber()))
                .thenReturn(false);


        Warehouse warehouse = new Warehouse();
        when(mockWarehouseRepository.getWarehouseByName(
                WarehouseName.valueOf(req.warehouse())))
                .thenReturn(warehouse);

        when(mockSleeveRepository.save(any(Sleeve.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SleeveResponse response = serviceToTest.saveSleeve(req);

        ArgumentCaptor<Sleeve> captor = ArgumentCaptor.forClass(Sleeve.class);
        verify(mockSleeveRepository).save(captor.capture());

        Sleeve saved = captor.getValue();

        assertEquals(req.sleeveNumber(), saved.getSleeveNumber());
        assertSame(warehouse, saved.getWarehouse());

    }

    @Test
    void test_whenNoSleevesFound_thenReturnsEmptyList() {
        int sequenceNumber = 42;

        when(mockSleeveRepository.findAllBySequenceNumber(sequenceNumber))
                .thenReturn(Collections.emptyList());

        List<SleeveResponse> result = serviceToTest
                .getSleevesBySleeveSequenceNumber(sequenceNumber);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(mockSleeveRepository).findAllBySequenceNumber(sequenceNumber);
        verifyNoInteractions(sleeveMapper);
    }

    @Test
    void test_whenSequenceNumberExists_thenReturnsSleeveResponse() {
        int sequenceNumber = 42;
        SaveSleeveRequest sleeveRequest= TestDataUtil.createSleeveRequest();

        Sleeve s1 = TestDataUtil.createSleeveFromRequest(1L, sleeveRequest);
        s1.setId(1L);
        Sleeve s2 = TestDataUtil.createSleeveFromRequest(2L, sleeveRequest);
        s2.setId(2L);

        when(mockSleeveRepository.findAllBySequenceNumber(sequenceNumber))
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

        verify(mockSleeveRepository).findAllBySequenceNumber(sequenceNumber);

        verify(sleeveMapper, times(2)).toResponse(any(Sleeve.class));
    }

    @Test
    void test_whenValidRequest_thenReturnsCorrectSleeveResponse() {
        SaveSleeveRequest request = TestDataUtil.createSleeveRequest();

        when(mockSleeveRepository.existsBySleeveNumber(request.sleeveNumber()))
                .thenReturn(false);

        Warehouse warehouse = new Warehouse();
        when(mockWarehouseRepository.getWarehouseByName(WarehouseName.valueOf(request.warehouse())))
                .thenReturn(warehouse);

        Sleeve unsaved = TestDataUtil.createSleeveFromRequest(null, request);
        Sleeve saved = TestDataUtil.createSleeveFromRequest(123L, request);
        SleeveResponse expected = TestDataUtil.createSleeveResponse(123L, request);

        when(sleeveMapper.toEntity(request)).thenReturn(unsaved);
        when(mockSleeveRepository.save(unsaved)).thenReturn(saved);
        when(sleeveMapper.toResponse(saved)).thenReturn(expected);

        SleeveResponse actual = serviceToTest.saveSleeve(request);

        assertSame(expected, actual);
    }

    @Test
    void test_updateSleeve_whenValidUpdated_thenMergeFieldsAndSave() {
        Sleeve exisitngSleeve = TestDataUtil.createSleeve();
        Warehouse warehouse = TestDataUtil.createWarehouse("L3");

        Map<String, Object> updates = new HashMap<>();
        updates.put("color", "red");
        updates.put("kmStand", 13000L);
        updates.put("slot", 5);
        updates.put("warehouse", "L1");

        when(mockWarehouseRepository.getWarehouseByName(WarehouseName.L1))
                .thenReturn(warehouse);

        when(mockSleeveRepository.findById(1L))
                .thenReturn(Optional.of(exisitngSleeve));

        when(mockSleeveRepository.save(any(Sleeve.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SleeveResponse updatedSleeve = serviceToTest.updateSleeve(1L, updates);

        assertEquals("red",      updatedSleeve.color());
        assertEquals(13000L,     updatedSleeve.kmStand());
        assertEquals(5,          updatedSleeve.slot());
//        assertSame(warehouse, updatedSleeve.warehouse());
        verify(mockSleeveRepository, times(1)).save(any(Sleeve.class));
    }
}

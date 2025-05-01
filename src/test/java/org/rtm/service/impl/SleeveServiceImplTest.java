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
    void whenSleeveNumberAlreadyExists_thenThrowDuplicateException() {
        SaveSleeveRequest request = TestDataUtil.createSleeveRequest();

        when(mockSleeverepository.existsBySleeveNumber(request.sleeveNumber())).thenReturn(true);

        assertThrows(DuplicateSleeveNumberException.class,
                () -> serviceToTest.saveSleeve(request));

        verify(mockSleeverepository).existsBySleeveNumber(request.sleeveNumber());

        verify(mockSleeverepository, never()).save(any());

    }

    @Test
    void saveSleeve() {
        SaveSleeveRequest req = TestDataUtil.createSleeveRequest();


        when(mockSleeverepository.existsBySleeveNumber(req.sleeveNumber()))
                .thenReturn(false);


        Warehouse wh = new Warehouse();
        when(mockWarehouserepository.getWarehouseByName(
                WarehouseName.valueOf(req.warehouse())))
                .thenReturn(wh);

        when(mockSleeverepository.save(any(Sleeve.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SleeveResponse response = serviceToTest.saveSleeve(req);

        ArgumentCaptor<Sleeve> captor = ArgumentCaptor.forClass(Sleeve.class);
        verify(mockSleeverepository).save(captor.capture());

        Sleeve saved = captor.getValue();

        assertEquals(req.sleeveNumber(), saved.getSleeveNumber());
        assertSame(wh, saved.getWarehouse());

    }

}

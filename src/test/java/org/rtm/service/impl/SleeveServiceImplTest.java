package org.rtm.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rtm.exception.DuplicateSleeveNumberException;
import org.rtm.mapper.SleeveMapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;
import org.rtm.testutlis.TestDataUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SleeveServiceImplTest {

    @InjectMocks
    private SleeveServiceImpl serviceToTest;

    @Mock
    private SleeveRepository mockSleeverepository;

    @Mock
    private WarehouseRepository mockWarehouserepository;

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
}

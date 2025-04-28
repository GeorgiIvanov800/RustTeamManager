package org.rtm.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rtm.mapper.SleeveMapper;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;

@ExtendWith(MockitoExtension.class)
public class SleeveServiceImplTest {

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
    void testMock() {
        boolean b = mockSleeverepository.existsBySleeveNumber(123456);

        Assertions.assertFalse(b);

    }
}

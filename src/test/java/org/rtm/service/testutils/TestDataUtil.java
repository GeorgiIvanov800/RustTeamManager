package org.rtm.service.testutils;


import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataUtil {

    private SaveSleeveRequest createSaveSleeveRequest() {
        return new SaveSleeveRequest(
                1,                            // sequenceNumber
                100,                          // sleeveNumber
                "SpiralDesign",               // design
                "Blau",                       // color
                "Manschetten GmbH",           // manufacturer
                "Erstinspektion erforderlich",// notes
                12,                           // gear
                250,                          // circumference
                5,                            // slot
                LocalDate.of(2025, 4, 20),    // manufactureDate
                50,                           // width
                12000L,                       // kmStand
                "L3",                           // warehouseId
                "IN_STOCK",                   // status
                SleeveType.LACK,          // type
                SleeveCondition.NEW           // condition
        );
    }
}

package org.rtm.testutlis;

import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;

import java.time.LocalDate;


public class TestDataUtil {

    public static SaveSleeveRequest createSleeveRequest() {
        return new SaveSleeveRequest(
                145369,
                100,
                "SpiralDesign",        // design
                "Blau",                       // color
                "Manschetten GmbH",           // manufacturer
                "Erstinspektion erforderlich",// notes
                12,                           // gear
                250,                          // circumference
                5,                            // slot
                LocalDate.of(2025, 4, 20),    // manufactureDate
                50,                           // width
                12000L,                       // kmStand
                "L3",                         // warehouse
                "IN_STOCK",                   // status
                SleeveType.SILICON,          // type
                SleeveCondition.NEW          // condition
        );
    }
}

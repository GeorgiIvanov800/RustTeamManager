package org.rtm.testutlis;

import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;
import org.rtm.model.enums.WarehouseName;

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

    public static SleeveResponse createSleeveResponse() {
        return new SleeveResponse(
                1L,
                145369,
                100,
                "SpiralDesign",
                "Blau",
                "Manschetten GmbH",
                "",
                12,
                250,
                5,
                LocalDate.of(2025,4,20),
                50,
                12000L,
                new Warehouse(),
                "ACTIVE",
                SleeveType.SILICON,
                SleeveCondition.NEW
        );
    }

    public static Warehouse createWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(WarehouseName.L3);
        warehouse.setId(1L);

        return warehouse;
    }

    public static Sleeve createSleeve(Long id, int sequenceNumber) {
        Sleeve sleeve = new Sleeve();
        sleeve.setType(SleeveType.SILICON);
        sleeve.setCondition(SleeveCondition.NEW);
        sleeve.setWarehouse(createWarehouse());
        sleeve.setType(SleeveType.SILICON);
        sleeve.setColor("Blue");
        sleeve.setGear(53);
        sleeve.setSequenceNumber(sequenceNumber);
        sleeve.setId(id);

        return sleeve;
    }

}

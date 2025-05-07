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

    public static Sleeve createSleeve(Long id, SaveSleeveRequest request) {
        Sleeve sleeve = new Sleeve();
        sleeve.setId(id);
        sleeve.setSleeveNumber(request.sleeveNumber());
        sleeve.setSequenceNumber(request.sequenceNumber());
        sleeve.setDesign(request.design());
        sleeve.setColor(request.color());
        sleeve.setManufacturer(request.manufacturer());
        sleeve.setNotes(request.notes());
        sleeve.setGear(request.gear());
        sleeve.setCircumference(request.circumference());
        sleeve.setSlot(request.slot());
        sleeve.setManufactureDate(request.manufactureDate());
        sleeve.setWidth(request.width());
        sleeve.setKmStand(request.kmStand());
        sleeve.setStatus(request.status());
        sleeve.setType(request.type());
        sleeve.setCondition(request.condition());
        sleeve.setWarehouse(createWarehouse());

        return sleeve;
    }

    public static SleeveResponse createSleeveResponse(Long id, SaveSleeveRequest req) {
        return new SleeveResponse(
                id,
                req.sleeveNumber(),
                req.sequenceNumber(),
                req.design(),
                req.color(),
                req.manufacturer(),
                req.notes(),
                req.gear(),
                req.circumference(),
                req.slot(),
                req.manufactureDate(),
                req.width(),
                req.kmStand(),
                createWarehouse(),
                req.status(),
                req.type(),
                req.condition()
        );
    }

}

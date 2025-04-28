package org.rtm.service.impl;

import lombok.AllArgsConstructor;
import org.rtm.exception.DuplicateSleeveNumberException;
import org.rtm.mapper.SleeveMapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.model.entity.Warehouse;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;
import org.rtm.service.SleeveService;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class SleeveServiceImpl implements SleeveService {

    private final SleeveRepository sleeveRepository;
    private final SleeveMapper sleeveMapper;
    private final WarehouseRepository warehouseRepository;

    @Override
    public SleeveResponse saveSleeve(SaveSleeveRequest request) {
        System.out.println();
        if (sleeveNumberExists(request.sleeveNumber())) {
            throw new DuplicateSleeveNumberException(request.sleeveNumber());
        }
        Warehouse warehouse = warehouseRepository.getWarehouseByName(request.warehouse().getName());

        Sleeve sleeve = sleeveMapper.toEntity(request);
        sleeve.setWarehouse(warehouse);

        return sleeveMapper.toResponse(sleeveRepository.save(sleeve));
    }

    @Override
    public List<SleeveResponse> getSleevesBySleeveSequenceNumber(Integer sleeveSequenceNumber) {

        List<Sleeve> sleeves = sleeveRepository.findAllBySequenceNumber(sleeveSequenceNumber);

        return sleeves.stream()
                .map(sleeveMapper::toResponse)
                .toList();
    }


    private boolean sleeveNumberExists(Integer sleeveNumber) {
        return sleeveRepository.existsBySleeveNumber(sleeveNumber);
    }

}

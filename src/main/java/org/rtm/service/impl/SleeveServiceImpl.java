package org.rtm.service.impl;

import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SleeveServiceImpl implements SleeveService {

    private final SleeveRepository sleeveRepository;
    private final SleeveMapper sleeveMapper;
    private final WarehouseRepository warehouseRepository;

    @Override
    public SleeveResponse saveSleeve(SaveSleeveRequest request) {

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

}

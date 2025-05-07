package org.rtm.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.rtm.exception.DuplicateSleeveNumberException;
import org.rtm.mapper.SleeveMapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.WarehouseName;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;
import org.rtm.service.SleeveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class SleeveServiceImpl implements SleeveService {

    private final SleeveRepository sleeveRepository;
    private final SleeveMapper sleeveMapper;
    private final WarehouseRepository warehouseRepository;

    @Override
    @Transactional
    public SleeveResponse saveSleeve(SaveSleeveRequest request) {

        if (sleeveNumberExists(request.sleeveNumber())) {
            throw new DuplicateSleeveNumberException(request.sleeveNumber());
        }
        Warehouse warehouse = warehouseRepository.getWarehouseByName(WarehouseName.valueOf(request.warehouse()));

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

    @Override
    public Sleeve updateSleeve(Long id, Map<String, Object> updateSleeveRequest) {
        Sleeve sleeve = sleeveRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("No Sleeve with ID: " + id));

        sleeveMapper.updateSleeve(sleeve);

        return sleeveRepository.save(sleeve);
    }

    @Override
    public void deleteSleeve(Long id) {
        sleeveRepository.deleteById(id);
    }


    private boolean sleeveNumberExists(Integer sleeveNumber) {
        return sleeveRepository.existsBySleeveNumber(sleeveNumber);
    }

}

package org.rtm.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.rtm.exception.DuplicateSleeveNumberException;
import org.rtm.exception.NotFoundException;
import org.rtm.mapper.SleeveMapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.WarehouseName;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;
import org.rtm.service.SleeveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public SleeveResponse saveSleeve(SaveSleeveRequest request) {

        if (sleeveNumberExists(request.sleeveNumber())) {
            throw new DuplicateSleeveNumberException(request.sleeveNumber());
        }

        Warehouse warehouse = warehouseRepository.
                getWarehouseByName(WarehouseName.valueOf(request.warehouse()));


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
    public SleeveResponse updateSleeve(Long id, Map<String, Object> updates) {
        int sleeveNumber = (int) updates.get("sleeveNumber");

        if (sleeveNumberExists(sleeveNumber)) {
            throw new DuplicateSleeveNumberException(sleeveNumber);
        }

        Sleeve sleeve = sleeveRepository.findById(id).orElseThrow( () -> new NotFoundException(Math.toIntExact(id)));

        if (updates.containsKey("warehouse")) {
            String warehouseName = updates.remove("warehouse").toString();

            Warehouse warehouse = warehouseRepository.
                    getWarehouseByName(WarehouseName.valueOf(warehouseName));

            sleeve.setWarehouse(warehouse);
        }

        try {
            objectMapper.updateValue(sleeve, updates);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }

        sleeveRepository.save(sleeve);
        return sleeveMapper.toResponse(sleeve);
    }

    @Override
    public void deleteSleeve(Long id) {

        sleeveRepository.findById(id).orElseThrow(() -> new NotFoundException(Math.toIntExact(id)));


        sleeveRepository.deleteById(id);
    }

    @Override
    public Page<SleeveResponse> getAllSleevesInWarehouse(Pageable pageable, Long warehouseId) {
        return sleeveRepository.findAllByWarehouseId(warehouseId, pageable)
                .map(sleeveMapper::toResponse);
    }

    @Override
    public SleeveResponse getSleeveBySleeveByNumber(Integer sleeveNumber) {
        Sleeve sleeve = sleeveRepository
                .findBySleeveNumber(sleeveNumber)
                .orElseThrow(() ->
                        new NotFoundException(sleeveNumber)
                );
        return sleeveMapper.toResponse(sleeve);
    }

    private boolean sleeveNumberExists(Integer sleeveNumber) {
        return sleeveRepository.existsBySleeveNumber(sleeveNumber);
    }

}

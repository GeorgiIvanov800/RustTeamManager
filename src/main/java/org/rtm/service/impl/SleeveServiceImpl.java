package org.rtm.service.impl;

import lombok.AllArgsConstructor;
import org.rtm.mapper.SleeveMapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SaveSleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.repository.SleeveRepository;
import org.rtm.repository.WarehouseRepository;
import org.rtm.service.SleeveService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SleeveServiceImpl implements SleeveService {

    private final SleeveRepository sleeveRepository;
    private final SleeveMapper sleeveMapper;
    private final WarehouseRepository warehouseRepository;

    @Override
    public SaveSleeveResponse saveSleeve(SaveSleeveRequest request) {

        System.out.println();

        Sleeve sleeve = sleeveMapper.toEntity(request);
        System.out.println(sleeve);
        return null;
    }
}

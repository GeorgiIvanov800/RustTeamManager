package org.rtm.service;

import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;


import java.util.List;
import java.util.Map;

public interface SleeveService {
    SleeveResponse saveSleeve(SaveSleeveRequest request);

    List<SleeveResponse> getSleevesBySleeveSequenceNumber(Integer sleeveSequenceNumber);

    Sleeve updateSleeve(Long id, Map<String, Object> updateSleeveRequest);

    void deleteSleeve(Long id);
}

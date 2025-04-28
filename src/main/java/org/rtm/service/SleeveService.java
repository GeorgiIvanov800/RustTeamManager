package org.rtm.service;

import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;


import java.util.List;

public interface SleeveService {
    SleeveResponse saveSleeve(SaveSleeveRequest request);

    List<SleeveResponse> getSleevesBySleeveSequenceNumber(Integer sleeveSequenceNumber);

}

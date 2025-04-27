package org.rtm.service;

import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SaveSleeveResponse;

public interface SleeveService {
    SaveSleeveResponse saveSleeve(SaveSleeveRequest request);
}

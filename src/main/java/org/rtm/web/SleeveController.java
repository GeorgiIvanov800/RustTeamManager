package org.rtm.web;

import lombok.RequiredArgsConstructor;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SaveSleeveResponse;
import org.rtm.service.SleeveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("sleeves")
@RequiredArgsConstructor
public class SleeveController {
    private final SleeveService sleeveService;

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SaveSleeveResponse> saveSleeve(@RequestBody SaveSleeveRequest saveSleeveRequestrequest) {

        SaveSleeveResponse sleeveResponse = sleeveService.saveSleeve(saveSleeveRequestrequest);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(sleeveResponse.id())
                        .toUri()
        ).body(sleeveResponse);

    }
}

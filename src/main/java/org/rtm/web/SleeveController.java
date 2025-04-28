package org.rtm.web;

import lombok.RequiredArgsConstructor;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.service.SleeveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("sleeves")
@RequiredArgsConstructor
public class SleeveController {
    private final SleeveService sleeveService;

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SleeveResponse> saveSleeve(@RequestBody SaveSleeveRequest saveSleeveRequest) {

        SleeveResponse sleeveResponse = sleeveService.saveSleeve(saveSleeveRequest);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(sleeveResponse.id())
                        .toUri()
        ).body(sleeveResponse);

    }

    @GetMapping()
    public ResponseEntity<List<SleeveResponse>> getSleeveSequenceNumber(
            @RequestParam(value = "sequenceNumber", required = true) Integer sequenceNumber
    ) {
        return ResponseEntity.ok(sleeveService.getSleevesBySleeveSequenceNumber(sequenceNumber));
    }
}

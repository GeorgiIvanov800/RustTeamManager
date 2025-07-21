package org.rtm.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.service.SleeveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/sleeves")
@RequiredArgsConstructor
public class SleeveController {
    private final SleeveService sleeveService;

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<SleeveResponse> saveSleeve(@RequestBody SaveSleeveRequest saveSleeveRequest) {

        SleeveResponse sleeveResponse = sleeveService.saveSleeve(saveSleeveRequest);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(sleeveResponse.id())
                        .toUri()
        ).body(sleeveResponse);

    }

    @GetMapping("")
    public ResponseEntity<List<SleeveResponse>> getSleeveSequenceNumber(
            @RequestParam("sequence") Integer sequenceNumber
    ) {
        List<SleeveResponse> result = sleeveService.getSleevesBySleeveSequenceNumber(sequenceNumber);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{sleeveNumber}")
    public ResponseEntity<SleeveResponse> getSleeveNumber(
            @PathVariable Integer sleeveNumber
    ) {
        SleeveResponse response = sleeveService.getSleeveBySleeveByNumber(sleeveNumber);

        return ResponseEntity.ok(response);
    }

    @GetMapping("warehouse/{warehouseId}")
    public ResponseEntity<Page<SleeveResponse>> getAllSleevesInWarehouse(
            @PageableDefault(
                    size = 3
            ) Pageable pageable,
            @PathVariable("warehouseId") Long warehouseId) {

        return ResponseEntity.ok(sleeveService.getAllSleevesInWarehouse(pageable, warehouseId));
    }


    @PatchMapping("update/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<SleeveResponse> updateSleeve(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> updates
    ) {

        return ResponseEntity.ok(sleeveService.updateSleeve(id, updates));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteSleeve(@PathVariable("id") Long id) {

        sleeveService.deleteSleeve(id);
        return ResponseEntity.noContent().build();
    }

}

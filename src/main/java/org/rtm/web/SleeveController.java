package org.rtm.web;

import lombok.RequiredArgsConstructor;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;
import org.rtm.service.SleeveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{sleeve-sequence}")
    public ResponseEntity<List<SleeveResponse>> getSleeveSequenceNumber(
            @PathVariable("sleeve-sequence") Integer sequenceNumber
    ) {
        return ResponseEntity.ok(sleeveService.getSleevesBySleeveSequenceNumber(sequenceNumber));
    }

    @GetMapping("/{warehouseId}/sleeves")
    public ResponseEntity<Page<SleeveResponse>> getAllSleevesInWarehouse(
            @PageableDefault(
                    size = 3,
                    sort = "id",
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            @PathVariable("warehouseId") Long warehouseId) {

        return ResponseEntity.ok(sleeveService.getAllSleevesInWarehouse(pageable, warehouseId));
    }


    @PatchMapping("update/{id}")
    public ResponseEntity<Sleeve> updateSleeve(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> updates
    ) {
        return ResponseEntity.ok(sleeveService.updateSleeve(id, updates));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<SleeveResponse> deleteSleeve(@PathVariable("id") Long id) {
        sleeveService.deleteSleeve(id);
        return ResponseEntity.noContent().build();

    }

}

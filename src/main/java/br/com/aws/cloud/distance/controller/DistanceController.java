package br.com.aws.cloud.distance.controller;

import br.com.aws.cloud.distance.DistanceResponse;
import br.com.aws.cloud.distance.DistanceResponse;
import br.com.aws.cloud.distance.service.DistanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distancia")
public class DistanceController {

    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping
    public ResponseEntity<DistanceResponse> calcularDistancia(
            @RequestParam double lat1,
            @RequestParam double lon1,
            @RequestParam double lat2,
            @RequestParam double lon2
    ) {
        DistanceResponse response = distanceService.calcularDistancia(lat1, lon1, lat2, lon2);
        return ResponseEntity.ok(response);
    }
}
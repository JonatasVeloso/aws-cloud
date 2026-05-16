package br.com.aws.cloud.distance.controller;

import br.com.aws.cloud.distance.DistanceResponse;
import br.com.aws.cloud.distance.service.DistanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distancia")
public class DistanceController {

    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Operation(
            summary = "Calcula a distância entre dois pontos",
            description = "Recebe latitude e longitude de dois pontos e retorna a distância em linha reta usando a fórmula de Haversine."
    )
    @GetMapping
    public ResponseEntity<DistanceResponse> calcularDistancia(
            @Parameter(description = "Latitude do ponto de origem", example = "-23.55052")
            @RequestParam double lat1,

            @Parameter(description = "Longitude do ponto de origem", example = "-46.633308")
            @RequestParam double lon1,

            @Parameter(description = "Latitude do ponto de destino", example = "-22.906847")
            @RequestParam double lat2,

            @Parameter(description = "Longitude do ponto de destino", example = "-43.172897")
            @RequestParam double lon2
    ) {
        DistanceResponse response = distanceService.calcularDistancia(lat1, lon1, lat2, lon2);
        return ResponseEntity.ok(response);
    }
}
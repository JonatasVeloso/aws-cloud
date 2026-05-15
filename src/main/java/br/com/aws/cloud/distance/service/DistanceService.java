package br.com.aws.cloud.distance.service;

import br.com.aws.cloud.distance.DistanceResponse;
import br.com.aws.cloud.distance.DistanceResponse;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public DistanceResponse calcularDistancia(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {
        validarLatitude(lat1, "lat1");
        validarLatitude(lat2, "lat2");
        validarLongitude(lon1, "lon1");
        validarLongitude(lon2, "lon2");

        double distanciaKm = calcularDistanciaEmKm(lat1, lon1, lat2, lon2);
        double distanciaMetros = distanciaKm * 1000;

        return new DistanceResponse(
                lat1,
                lon1,
                lat2,
                lon2,
                arredondar(distanciaKm),
                arredondar(distanciaMetros)
        );
    }

    private double calcularDistanciaEmKm(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);

        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    private void validarLatitude(double latitude, String campo) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException(
                    "Latitude inválida no campo " + campo + ". Informe um valor entre -90 e 90."
            );
        }
    }

    private void validarLongitude(double longitude, String campo) {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException(
                    "Longitude inválida no campo " + campo + ". Informe um valor entre -180 e 180."
            );
        }
    }

    private double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
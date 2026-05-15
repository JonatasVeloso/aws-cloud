package br.com.aws.cloud.distance;

public record DistanceResponse(
        double latitudeOrigem,
        double longitudeOrigem,
        double latitudeDestino,
        double longitudeDestino,
        double distanciaKm,
        double distanciaMetros
) {
}
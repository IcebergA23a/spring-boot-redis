package org.example.service;

import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;

public interface GeoService {

    Long addGeo(String geoKey, double longitude, double latitude, String member);

    Long removeGeo(String geoKey, String member);

    GeoResults<RedisGeoCommands.GeoLocation<String>> searchGeo(String geoKey, double longitude, double latitude, double radius, String unit);
}

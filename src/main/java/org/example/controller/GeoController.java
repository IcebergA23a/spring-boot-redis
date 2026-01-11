package org.example.controller;

import org.example.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Geospatial 结构
 * @author bingshan
 * @since 2026/1/11 20:03
 */
@RestController
@RequestMapping("/geo")
public class GeoController {

    @Autowired
    private GeoService geoService;

    @GetMapping("/addGeo")
    public Long addGeo(@RequestParam String geoKey, @RequestParam double longitude,
                       @RequestParam double latitude, @RequestParam String member) {
        return geoService.addGeo(geoKey, longitude, latitude, member);
    }

    @GetMapping("/removeGeo")
    public Long removeGeo(@RequestParam String geoKey, @RequestParam String member) {
        return geoService.removeGeo(geoKey, member);
    }

    @GetMapping("/searchGeo")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> searchGeo(@RequestParam String geoKey,
           @RequestParam double longitude, @RequestParam double latitude,
           @RequestParam double radius, @RequestParam String unit) {
        return geoService.searchGeo(geoKey, longitude, latitude, radius, unit);
    }
}

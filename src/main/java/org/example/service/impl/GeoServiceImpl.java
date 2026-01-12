package org.example.service.impl;

import org.example.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;

/**
 * @author bingshan
 * @since 2026/1/11 20:05
 */
@Service
public class GeoServiceImpl implements GeoService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加地理信息
     * @param geoKey
     * @param longitude
     * @param latitude
     * @param member
     * @return
     */
    @Override
    public Long addGeo(String geoKey, double longitude, double latitude, String member) {
        Point point = new Point(longitude, latitude);
        RedisGeoCommands.GeoLocation<String> location = new RedisGeoCommands.GeoLocation<>(member, point);

        return redisTemplate.opsForGeo().add((Object) geoKey, location);
    }

    /**
     * 删除地理位置
     * @param geoKey
     * @param member
     * @return
     */
    @Override
    public Long removeGeo(String geoKey, String member) {
        return redisTemplate.opsForGeo().remove(geoKey, member);
    }

    /**
     * 搜索指定地理位置的数据
     * @param geoKey
     * @param longitude
     * @param latitude
     * @param radius
     * @param unit
     * @return
     */
    @Override
    public GeoResults<RedisGeoCommands.GeoLocation<String>> searchGeo(String geoKey, double longitude, double latitude,
                                                                      double radius, String unit) {
        GeoReference<String> geoReference = new GeoReference.GeoCoordinateReference<>(longitude, latitude);
        Metric metric = Metrics.valueOf(unit);
        Distance distance = new Distance(radius,  metric);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults =
                redisTemplate.opsForGeo().search(geoKey, geoReference, distance);
        return geoResults;
    }
}

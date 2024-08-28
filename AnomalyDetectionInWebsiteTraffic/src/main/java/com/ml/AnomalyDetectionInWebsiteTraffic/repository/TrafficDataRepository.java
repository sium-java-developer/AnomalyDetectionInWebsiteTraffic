package com.ml.AnomalyDetectionInWebsiteTraffic.repository;

import com.ml.AnomalyDetectionInWebsiteTraffic.model.TrafficData;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficDataRepository extends JpaRepository<TrafficData, Long>{
    
    // find traffic data within a specific time range
    @Query("SELECT t FROM TrafficData t WHERE t.timeStamp BETWEEN :startDate AND :endDate")
    List<TrafficData> findByTimestampBetween(@Param("startDate") LocalDateTime startDate, 
            @Param("endDate") LocalDateTime endDate);
    
    // find traffic data by page url
    @Query("SELECT t from TrafficData t WHERE t.pageUrl = :pageUrl")
    List<TrafficData> findByPageUrl(@Param("pageUrl") String pageUrl);
    
    // find traffic data by user agent
    @Query("SELECT t FROM TrafficData t WHERE t.userAgent = :userAgent")
    List<TrafficData> findByUserAgent(@Param("userAgent") String userAgent);
    
    // find by ip address
    @Query("SELECT t FROM TrafficData t WHERE t.ipAddress = :ipAddress")
    List<TrafficData> findByIpAddress(@Param("ipAddress") String ipAddress);
    
    // find traffic data by referrer
    @Query("SELECT t FROM TrafficData t WHERE t.referrer = :referrer")
    List<TrafficData> findByReferrer(@Param("referrer") String referrer);
    
    // custom query to find out anomalies based on specific criteria
    @Query("SELECT t FROM TrafficData t WHERE t.pageViews > :avgPageViews * 2")
    List<TrafficData> findAnomaliesByPageViews(@Param("avgPageViews") int avgPageViews);
    
}

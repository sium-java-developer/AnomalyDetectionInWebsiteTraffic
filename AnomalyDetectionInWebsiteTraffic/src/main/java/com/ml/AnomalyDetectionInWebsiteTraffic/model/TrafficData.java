package com.ml.AnomalyDetectionInWebsiteTraffic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "traffic_data")
public class TrafficData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime timeStamp;
    
    @Column(nullable = false)
    private int pageViews;
    
    @Column(nullable = false)
    private int uniqueVisitors;
    
    private double bounceRate;
    
    private int timeOnSite;
    
    // create getter and setter for id
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    // create getter and setter for timeStamp
    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }
    
    // create getter and setter for pageViews
    public int getPageViews(){
        return pageViews;
    }
    
    public void setPageViews(int pageViews){
        this.pageViews = pageViews;
    }
    
    // create getter and setter for uniqueVisitors
    public int getUniqueVisitors(){
        return uniqueVisitors;
    }
    
    public void setUniqueVisitors(int uniqueVisitors){
        this.uniqueVisitors = uniqueVisitors;
    }
    
    // create getter and setter for bounceRate
    public double getBounceRate(){
        return bounceRate;
    }
    
    public void setBounceRate(double bounceRate){
        this.bounceRate = bounceRate;
    }
    
    // create getter and setter for timeOnSite
    public int getTimeOnSite(){
        return timeOnSite;
    }
    
    public void setTimeOnSite(int timeOnSite){
        this.timeOnSite = timeOnSite;
    }
}

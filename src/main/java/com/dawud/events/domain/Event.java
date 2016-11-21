package com.dawud.events.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;
    /**
     * Event ID
     */
    private String id;

    /**
     * Event title
     */
    private String title;

    /**
     * Event URL
     */
    private String url;

    /**
     * Description
     *
     */
    private String description;

    /**
     * Event start time
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    protected Date start_time;

    /**
     * Event end time.  Not all events have end times
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date stop_time;

    /**
     * Venue details
     */

    private String venue_name;

    private int venue_display;

    private String venue_address;

    private String city_name;

    private String region_name;

    private String region_abbr;

    private String postal_code;

    private String country_name;

    private String county_abbr2;

    private String country_abbr;

    private double latitude;

    private double longitude;

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", start_time=" + start_time +
                ", stop_time=" + stop_time +
                ", venue_name='" + venue_name + '\'' +
                ", venue_display=" + venue_display +
                ", venue_address='" + venue_address + '\'' +
                ", city_name='" + city_name + '\'' +
                ", region_name='" + region_name + '\'' +
                ", region_abbr='" + region_abbr + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", country_name='" + country_name + '\'' +
                ", county_abbr2='" + county_abbr2 + '\'' +
                ", country_abbr='" + country_abbr + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getStop_time() {
        return stop_time;
    }

    public void setStop_time(Date stop_time) {
        this.stop_time = stop_time;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public int getVenue_display() {
        return venue_display;
    }

    public void setVenue_display(int venue_display) {
        this.venue_display = venue_display;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_abbr() {
        return region_abbr;
    }

    public void setRegion_abbr(String region_abbr) {
        this.region_abbr = region_abbr;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCounty_abbr2() {
        return county_abbr2;
    }

    public void setCounty_abbr2(String county_abbr2) {
        this.county_abbr2 = county_abbr2;
    }

    public String getCountry_abbr() {
        return country_abbr;
    }

    public void setCountry_abbr(String country_abbr) {
        this.country_abbr = country_abbr;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

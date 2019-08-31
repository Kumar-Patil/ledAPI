package com.ledVan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "route_map")
@ApiModel(description = "All details about the RouteMap. ")
public class RouteMap implements Serializable {

    @ApiModelProperty(notes = "The database generated routeMap ID")
    private long id;

    @ApiModelProperty(notes = "The routeMap districtName")
    private String districtName;

    @ApiModelProperty(notes = "The routeMap createdAt")
    private Date createdAt;

    @ApiModelProperty(notes = "The routeMap updatedAt")
    private Date updatedAt;

    @ApiModelProperty(notes = "The routeMap routeMapName")
    private String routeMapName;

    @ApiModelProperty(notes = "The routeMap districtId")
    private long districtId;

    public RouteMap() {

    }

    public RouteMap(String districtName, Date createdAt, Date updatedAt, long districtId) {
        this.districtName = districtName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.districtId = districtId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "district_name", nullable = false)
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Column(name = "created_at", nullable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = true)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "route_map_name", nullable = false)
    public String getRouteMapName() {
        return routeMapName;
    }

    public void setRouteMapName(String routeMapName) {
        this.routeMapName = routeMapName;
    }

    @Column(name = "district_id", nullable = false)
    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public RouteMap(String districtName, Date createdAt, Date updatedAt, String routeMapName, long districtId) {
        this.districtName = districtName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.routeMapName = routeMapName;
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "RouteMap{" + "id=" + id + ", districtName=" + districtName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", routeMapName=" + routeMapName + ", districtId=" + districtId + '}';
    }

}

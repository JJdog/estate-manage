package com.lanswon.estate.bean.pojo;

public class Property {

  private long id;
  private long fkLessorId;
  private long shareType;
  private String uid;
  private String proType;
  private String proNature;
  private String proUse;
  private double landArea;
  private double buidingArea;
  private java.sql.Timestamp endTime;
  private double apportionmentArea;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp updatedTime;
  private long createdBy;
  private long updatedBy;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFkLessorId() {
    return fkLessorId;
  }

  public void setFkLessorId(long fkLessorId) {
    this.fkLessorId = fkLessorId;
  }


  public long getShareType() {
    return shareType;
  }

  public void setShareType(long shareType) {
    this.shareType = shareType;
  }


  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }


  public String getProType() {
    return proType;
  }

  public void setProType(String proType) {
    this.proType = proType;
  }


  public String getProNature() {
    return proNature;
  }

  public void setProNature(String proNature) {
    this.proNature = proNature;
  }


  public String getProUse() {
    return proUse;
  }

  public void setProUse(String proUse) {
    this.proUse = proUse;
  }


  public double getLandArea() {
    return landArea;
  }

  public void setLandArea(double landArea) {
    this.landArea = landArea;
  }


  public double getBuidingArea() {
    return buidingArea;
  }

  public void setBuidingArea(double buidingArea) {
    this.buidingArea = buidingArea;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }


  public double getApportionmentArea() {
    return apportionmentArea;
  }

  public void setApportionmentArea(double apportionmentArea) {
    this.apportionmentArea = apportionmentArea;
  }


  public java.sql.Timestamp getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(java.sql.Timestamp createdTime) {
    this.createdTime = createdTime;
  }


  public java.sql.Timestamp getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(java.sql.Timestamp updatedTime) {
    this.updatedTime = updatedTime;
  }


  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }


  public long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(long updatedBy) {
    this.updatedBy = updatedBy;
  }

}

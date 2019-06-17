package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;



public abstract class AbstractEntity extends AbstractBaseEntity implements Serializable {

    private String createdDate;
    private String updatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /**
     * Sets createdAt before insert
     */
    @PrePersist
    public void setCreationDate() {
        this.createdAt = new Date();
    }

    /**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedBy) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdDate, updatedDate, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "} " + super.toString();
    }
}
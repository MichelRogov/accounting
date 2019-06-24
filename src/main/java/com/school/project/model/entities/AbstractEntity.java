package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "ABSTRACTENTITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity extends AbstractBaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @OneToOne
    @JoinColumn(name = "UPDATED_DATE", nullable = false)
    private Date updatedDate;


}
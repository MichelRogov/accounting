package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;



@MappedSuperclass
@Entity
@Table(name = "ABSTRACTBASEENTITY")
@Data
@AllArgsConstructor
public abstract class AbstractBaseEntity implements Serializable {


}
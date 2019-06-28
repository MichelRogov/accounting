package com.school.project.model.entities;

import com.school.project.AbstractEntity.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GROUP")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group extends AbstractBaseEntity {

    @OneToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private Module module;

    @CreationTimestamp
    @Column(name = "START_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinTable(name = "GROUP_USER", joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> users;

}

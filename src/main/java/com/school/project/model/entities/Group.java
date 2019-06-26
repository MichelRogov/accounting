package com.school.project.model.entities;

import com.school.project.AbstractEntity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUP")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private Module module;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinTable(name = "GROUP_USER", joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> users;

}

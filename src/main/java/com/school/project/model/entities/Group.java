package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GROUP")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @OneToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private Module module;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinTable(name = "GROUP_USER", joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> users;

}

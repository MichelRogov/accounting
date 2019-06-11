package com.school.project.model.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "GROUP")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @Column(name="ID", unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "START_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    private Module module;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinTable(name ="GROUP_USER",joinColumns = {@JoinColumn(name = "ID")},inverseJoinColumns = {@JoinColumn(name="USER_ID")})
    private List<User> users;

}

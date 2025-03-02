package com.morg4n.morg4n_users.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Julian David Camacho Erazo (Morg4n) {@literal <jdacamachoe@gmail.com>}
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String phoneNumber;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PossessionEntity> possessions;
}

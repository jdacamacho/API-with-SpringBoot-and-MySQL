package com.morg4n.morg4n_users.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian David Camacho Erazo (Morg4n) {@literal <jdacamachoe@gmail.com>}
 */
@Entity
@Table(name = "possessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PossessionEntity {
    @Id
    private String id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn
    private UserEntity objUser;
}

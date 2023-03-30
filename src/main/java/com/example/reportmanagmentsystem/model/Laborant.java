package com.example.reportmanagmentsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "laboratories")
public class Laborant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String ad;

    @Column(name = "lastname")
    private String soyad;

    @Column(name = "laborant_id" )
    private String laborantId;

    @Column(name = "is_enabled" )
    private boolean isEnabled;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //CascadeType: all bir data silindiginde bununla ilişkili olan tüm verilerin silinmesini saglar
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(
            name = "laborant_roles",
            joinColumns = @JoinColumn(name = "laborant_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
    }

    public String whRole(Role role){
        return role.getRoleName();
    }

}

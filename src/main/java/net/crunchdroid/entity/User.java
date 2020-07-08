package net.crunchdroid.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.*;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "m_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6337544586413989864L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Access(AccessType.PROPERTY)
    private String userName;
    private String userPassword;
    private String email;
    private String namaLengkap;
    private Integer status;
    private String apiToken;
    private Timestamp lastLogin;

    @javax.persistence.Transient
    private String passwordConfirm;
    @javax.persistence.Transient
    private String type;

    @ManyToOne
    @JoinColumn(name = "fk_id_role", referencedColumnName = "id_role")
    private Role role;

	
}

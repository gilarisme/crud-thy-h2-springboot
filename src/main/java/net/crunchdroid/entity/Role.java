package net.crunchdroid.entity;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "m_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -246760547006423214L;

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID idRole;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

}

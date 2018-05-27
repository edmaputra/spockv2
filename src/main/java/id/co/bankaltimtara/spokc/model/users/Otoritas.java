package id.co.bankaltimtara.spokc.model.users;


import id.co.bankaltimtara.spokc.model.DBConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OTORITAS", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAMA"})})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Otoritas implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMA", length = DBConstants.L_NAME)
    @NotBlank(message = "Nama Otoritas Blank")
    @NotNull(message = "Nama Otoritas Null")
    private String nama;

    @Override
    public String getAuthority() {
        return getNama();
    }
}

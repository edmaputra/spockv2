package id.co.bankaltimtara.spokc.model.masters;

import id.co.bankaltimtara.spokc.model.DBConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "USERNAME")
public class Username {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMA", length = DBConstants.L_NAME, nullable = false)
    @NotBlank(message = "Nama Username Blank")
    @NotNull(message = "Nama Username Null")
    private String nama;

    @Column(name = "Tipe", nullable = false)
    private String tipe;
}

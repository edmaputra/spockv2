package id.co.bankaltimtara.spokc.model.masters;

import id.co.bankaltimtara.spokc.model.DBConstants;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "JABATAN")
public class Jabatan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMA", length = DBConstants.L_NAME, nullable = false)
    @NotBlank(message = "Nama Jabatan Blank")
    @NotNull(message = "Nama Jabatan Null")
    private String nama;

    @Column(name = "LEVEL", nullable = false)
    private Integer level = 0;

}

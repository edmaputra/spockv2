package id.co.bankaltimtara.spokc.model.masters;

import id.co.bankaltimtara.spokc.model.DBConstants;
import id.co.bankaltimtara.spokc.model.users.Pengguna;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PEGAWAI")
public class Pegawai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMA", length = DBConstants.L_NAME, nullable = false)
    @NotBlank(message = "Nama Pegawai Blank")
    @NotNull(message = "Nama Pegawai Null")
    private String nama;

    @Column(name = "NOMOR_REGISTER", length = DBConstants.L_NOREG, nullable = false)
    @NotBlank(message = "Nomor Register Pegawai Blank")
    @NotNull(message = "Nomor Register Pegawai Null")
    private String nomorRegister;

    @OneToOne
    @JoinColumn(name = "JABATAN_ID")
    private Jabatan jabatan;

    @OneToOne
    @JoinColumn(name = "PENGGUNA_ID")
    private Pengguna pengguna;

    @OneToOne
    @JoinColumn(name = "KANTOR_ID")
    private KantorDivisi kantorDivisi;


}

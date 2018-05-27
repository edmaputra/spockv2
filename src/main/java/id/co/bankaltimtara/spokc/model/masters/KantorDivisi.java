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
@Entity(name = "KANTOR_DIVISI")
public class KantorDivisi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMA", length = DBConstants.L_NAME, nullable = false)
    @NotBlank(message = "Nama Kantor/Divisi Blank")
    @NotNull(message = "Nama Kantor/Divisi Null")
    private String nama;

    @Column(name = "KODE", nullable = false, length = 10)
    @NotBlank(message = "Kode Kantor/Divisi Blank")
    @NotNull(message = "Kode Kantor/Divisi Null")
    private String kode;

    @ManyToOne
    @JoinColumn(name = "WILAYAH_ID", nullable = false)
    private Wilayah wilayah;
}

package id.co.bankaltimtara.spokc.model.masters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.bankaltimtara.spokc.model.DBConstants;
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
@Entity(name = "WILAYAH")
public class Wilayah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMA", length = DBConstants.L_NAME, nullable = false)
    @NotBlank(message = "Nama Wilayah Blank")
    @NotNull(message = "Nama Wilayah Null")
    private String nama;

    @Column(name = "KODE", nullable = false, length = 10)
    @NotBlank(message = "Kode Wilayah Blank")
    @NotNull(message = "Kode Wilayah Null")
    private String kode;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "wilayah")
    @JsonIgnore
    private List<KantorDivisi> kantorDivisi;

}

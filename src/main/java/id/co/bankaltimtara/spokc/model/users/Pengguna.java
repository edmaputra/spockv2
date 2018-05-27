package id.co.bankaltimtara.spokc.model.users;

import id.co.bankaltimtara.spokc.model.DBConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "PENGGUNA", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "password")
public class Pengguna implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME", length = DBConstants.L_USERNAME)
    @NotBlank(message = "Username Blank")
    @NotNull(message = "Username Null")
    @Length(message = "Username Length minimal 4 karakter", min = 4)
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank(message = "Password Blank")
    @NotNull(message = "Password Null")
    private String password;

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired;

    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    @OrderBy
    private Collection<Otoritas> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired();
    }
}

package id.co.bankaltimtara.spokc.service.impl.user;

import id.co.bankaltimtara.spokc.configuration.encryptions.Encoders;
import id.co.bankaltimtara.spokc.model.users.Pengguna;
import id.co.bankaltimtara.spokc.repository.pengguna.UserRepository;
import id.co.bankaltimtara.spokc.service.PenggunaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Import(Encoders.class)
public class PenggunaServiceImpl implements PenggunaService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Pengguna> dapatkanSemua() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pengguna dapatkan(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public void tambah(Pengguna pengguna) {
        pengguna.setPassword(userPasswordEncoder.encode(pengguna.getPassword()));
        userRepository.save(pengguna);
    }

    @Override
    @Transactional
    public void update(Pengguna pengguna) {
        Pengguna p = userRepository.findOne(pengguna.getId());
        BeanUtils.copyProperties(pengguna, p);
        userRepository.save(p);
    }

    @Override
    public void updatePengguna(Pengguna pengguna) {
        Pengguna p = userRepository.findOne(pengguna.getId());
        p.setAccountExpired(pengguna.isAccountExpired());
        p.setAccountLocked(pengguna.isAccountLocked());
        p.setAuthorities(pengguna.getAuthorities());
        p.setCredentialsExpired(pengguna.isCredentialsExpired());
        p.setEnabled(pengguna.isEnabled());
        p.setUsername(pengguna.getUsername());
        userRepository.save(p);
    }

    @Override
    public void resetPassword(Pengguna pengguna) {
        Pengguna p = userRepository.findOne(pengguna.getId());
        p.setPassword(userPasswordEncoder.encode("123456"));
        userRepository.save(p);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(Pengguna pengguna) {
        userRepository.delete(pengguna);
    }
}

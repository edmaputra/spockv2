package id.co.bankaltimtara.spokc.service.impl.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.configuration.encryptions.Encoders;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.model.users.Pengguna;
import id.co.bankaltimtara.spokc.predicate.SearchCriteria;
import id.co.bankaltimtara.spokc.predicate.pengguna.PenggunaSpecification;
import id.co.bankaltimtara.spokc.query.masters.WilayahQueryBuilder;
import id.co.bankaltimtara.spokc.query.user.PenggunaQueryBuilder;
import id.co.bankaltimtara.spokc.repository.pengguna.UserRepository;
import id.co.bankaltimtara.spokc.service.PenggunaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Import(Encoders.class)
public class PenggunaServiceImpl implements PenggunaService {

    private static final int PAGE_SIZE = 30;

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
    @Transactional(readOnly = true)
    public Page<Pengguna> dapatkan(Integer halaman, String cari) {
        if (halaman == null) {
            halaman = 1;
        }
        PageRequest pageRequest = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "username");
        if (StringUtils.isBlank(cari)){
            return userRepository.findAll(pageRequest);
        } else {
            PenggunaQueryBuilder queryBuilder = new PenggunaQueryBuilder();
            queryBuilder.cari(cari);
            BooleanExpression expression = queryBuilder.getResult();
            return userRepository.findAll(expression, pageRequest);
        }
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

    @Override
    public Integer count(Long aLong) {
        return userRepository.countById(aLong);
    }
}

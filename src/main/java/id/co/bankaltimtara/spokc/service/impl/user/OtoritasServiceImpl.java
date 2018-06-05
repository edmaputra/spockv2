package id.co.bankaltimtara.spokc.service.impl.user;

import id.co.bankaltimtara.spokc.model.users.Otoritas;
import id.co.bankaltimtara.spokc.repository.pengguna.OtoritasRepository;
import id.co.bankaltimtara.spokc.service.OtoritasService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OtoritasServiceImpl implements OtoritasService {

    @Autowired
    private OtoritasRepository otoritasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Otoritas> dapatkanSemua() {
        return otoritasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Otoritas dapatkan(Long id) {
        return otoritasRepository.findOne(id);
    }

    @Override
    @Transactional
    public void tambah(Otoritas otoritas) {
        otoritasRepository.save(otoritas);
    }

    @Override
    @Transactional
    public void update(Otoritas otoritas) {
        Otoritas j = otoritasRepository.findOne(otoritas.getId());
        BeanUtils.copyProperties(otoritas, j);
        otoritasRepository.save(j);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        otoritasRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(Otoritas otoritas) {
        otoritasRepository.delete(otoritas);
    }

    @Override
    public Integer count(Long aLong) {
        return otoritasRepository.countById(aLong);
    }

    @Override
    public Page<Otoritas> dapatkan(Integer halaman, String cari) {
        return null;
    }
}

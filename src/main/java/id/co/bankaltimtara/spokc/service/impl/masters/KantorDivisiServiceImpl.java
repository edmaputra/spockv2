package id.co.bankaltimtara.spokc.service.impl.masters;

import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import id.co.bankaltimtara.spokc.repository.masters.KantorDivisiRepository;
import id.co.bankaltimtara.spokc.service.KantorDivisiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KantorDivisiServiceImpl implements KantorDivisiService {

    @Autowired
    private KantorDivisiRepository kantorDivisiRepository;

    @Override
    @Transactional(readOnly = true)
    public List<KantorDivisi> dapatkanSemua() {
        return kantorDivisiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public KantorDivisi dapatkan(Long id) {
        return kantorDivisiRepository.findOne(id);
    }

    @Override
    @Transactional
    public void tambah(KantorDivisi kantorDivisi) {
        kantorDivisiRepository.save(kantorDivisi);
    }

    @Override
    @Transactional
    public void update(KantorDivisi kantorDivisi) {
        KantorDivisi j = kantorDivisiRepository.findOne(kantorDivisi.getId());
        BeanUtils.copyProperties(kantorDivisi, j);
        kantorDivisiRepository.save(j);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        kantorDivisiRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(KantorDivisi kantorDivisi) {
        kantorDivisiRepository.delete(kantorDivisi);
    }

    @Override
    public List<KantorDivisi> findByWilayah(Wilayah wilayah) {
        return kantorDivisiRepository.findByWilayah(wilayah);
    }
}

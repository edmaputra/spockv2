package id.co.bankaltimtara.spokc.service.impl.masters;

import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import id.co.bankaltimtara.spokc.repository.masters.WilayahRepository;
import id.co.bankaltimtara.spokc.service.WilayahService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WilayahServiceImpl implements WilayahService {

    @Autowired
    private WilayahRepository wilayahRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Wilayah> dapatkanSemua() {
        return wilayahRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Wilayah dapatkan(Long id) {
        return wilayahRepository.findOne(id);
    }

    @Override
    @Transactional
    public void tambah(Wilayah wilayah) {
        wilayahRepository.save(wilayah);
    }

    @Override
    @Transactional
    public void update(Wilayah wilayah) {
        Wilayah j = wilayahRepository.findOne(wilayah.getId());
        BeanUtils.copyProperties(wilayah, j);
        wilayahRepository.save(j);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        wilayahRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(Wilayah wilayah) {
        wilayahRepository.delete(wilayah);
    }
}

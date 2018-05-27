package id.co.bankaltimtara.spokc.service.impl.masters;

import id.co.bankaltimtara.spokc.model.masters.Pegawai;
import id.co.bankaltimtara.spokc.repository.masters.PegawaiRepository;
import id.co.bankaltimtara.spokc.service.PegawaiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PegawaiServiceImpl implements PegawaiService {

    @Autowired
    private PegawaiRepository pegawaiRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pegawai> dapatkanSemua() {
        return pegawaiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pegawai dapatkan(Long id) {
        return pegawaiRepository.findOne(id);
    }

    @Override
    @Transactional
    public void tambah(Pegawai pegawai) {
        pegawaiRepository.save(pegawai);
    }

    @Override
    @Transactional
    public void update(Pegawai pegawai) {
        Pegawai j = pegawaiRepository.findOne(pegawai.getId());
        BeanUtils.copyProperties(pegawai, j);
        pegawaiRepository.save(j);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        pegawaiRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(Pegawai pegawai) {
        pegawaiRepository.delete(pegawai);
    }
}

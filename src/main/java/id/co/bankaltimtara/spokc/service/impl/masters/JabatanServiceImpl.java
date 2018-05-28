package id.co.bankaltimtara.spokc.service.impl.masters;

import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.predicate.SearchCriteria;
import id.co.bankaltimtara.spokc.predicate.masters.JabatanSpecification;
import id.co.bankaltimtara.spokc.repository.masters.JabatanRepository;
import id.co.bankaltimtara.spokc.service.JabatanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JabatanServiceImpl implements JabatanService {

    @Autowired
    private JabatanRepository jabatanRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Jabatan> dapatkanSemua() {return jabatanRepository.findAll(); }

    @Override
    public List<Jabatan> dapatkan(String q) {
        if (StringUtils.isBlank(q)) {
            return jabatanRepository.findAll();
        } else {
            JabatanSpecification spec1 = new JabatanSpecification(new SearchCriteria("nama", ":", q));
            List<Jabatan> results = jabatanRepository.findAll(Specifications.where(spec1));
            return results;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Jabatan dapatkan(Long id) {
        return jabatanRepository.findOne(id);
    }

    @Override
    @Transactional
    public void tambah(Jabatan jabatan) {
        jabatanRepository.save(jabatan);
    }

    @Override
    @Transactional
    public void update(Jabatan jabatan) {
        Jabatan j = jabatanRepository.findOne(jabatan.getId());
        BeanUtils.copyProperties(jabatan, j);
        jabatanRepository.save(j);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        jabatanRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(Jabatan jabatan) {
        jabatanRepository.delete(jabatan);
    }

    @Override
    public Jabatan findByNama(String nama) {
        return jabatanRepository.findByNama(nama);
    }
}

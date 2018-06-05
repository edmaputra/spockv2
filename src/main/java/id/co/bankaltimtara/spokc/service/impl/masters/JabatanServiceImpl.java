package id.co.bankaltimtara.spokc.service.impl.masters;

import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.query.masters.JabatanQueryBuilder;
import id.co.bankaltimtara.spokc.repository.masters.JabatanRepository;
import id.co.bankaltimtara.spokc.service.JabatanService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JabatanServiceImpl implements JabatanService {

    private static final int PAGE_SIZE = 30;

    @Autowired
    private JabatanRepository jabatanRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Jabatan> dapatkanSemua() {return jabatanRepository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Page<Jabatan> dapatkan(Integer halaman, String cari) {
        if (halaman == null) {
            halaman = 1;
        }
        PageRequest pageRequest = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
        if (StringUtils.isBlank(cari)){
            return jabatanRepository.findAll(pageRequest);
        } else {
            JabatanQueryBuilder queryBuilder = new JabatanQueryBuilder();
            queryBuilder.cari(cari);
            if (StringUtils.isNumeric(cari)) {
                queryBuilder.level(new Integer(cari));
            }
            BooleanExpression expression = queryBuilder.getResult();
            return jabatanRepository.findAll(expression, pageRequest);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count(Long id) {
        return jabatanRepository.countById(id);
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

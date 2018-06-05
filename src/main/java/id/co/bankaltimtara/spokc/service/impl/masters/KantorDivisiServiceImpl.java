package id.co.bankaltimtara.spokc.service.impl.masters;

import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.model.masters.KantorDivisi;
import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import id.co.bankaltimtara.spokc.predicate.SearchCriteria;
import id.co.bankaltimtara.spokc.predicate.masters.KantorDivisiSpecification;
import id.co.bankaltimtara.spokc.predicate.masters.UsernameSpecification;
import id.co.bankaltimtara.spokc.query.masters.JabatanQueryBuilder;
import id.co.bankaltimtara.spokc.query.masters.KantorDivisiQueryBuilder;
import id.co.bankaltimtara.spokc.repository.masters.KantorDivisiRepository;
import id.co.bankaltimtara.spokc.service.KantorDivisiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KantorDivisiServiceImpl implements KantorDivisiService {

    private static final int PAGE_SIZE = 30;

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
    public Page<KantorDivisi> dapatkan(Integer halaman, String cari) {
        if (halaman == null) {
            halaman = 1;
        }
        PageRequest pageRequest = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
        if (StringUtils.isBlank(cari)){
            return kantorDivisiRepository.findAll(pageRequest);
        } else {
            KantorDivisiQueryBuilder queryBuilder = new KantorDivisiQueryBuilder();
            queryBuilder.cari(cari);
            BooleanExpression expression = queryBuilder.getResult();
            return kantorDivisiRepository.findAll(expression, pageRequest);
        }
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

    @Override
    @Transactional(readOnly = true)
    public Integer count(Long id) {
        return kantorDivisiRepository.countById(id);
    }
}

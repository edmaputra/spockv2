package id.co.bankaltimtara.spokc.service.impl.masters;

import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.model.masters.Pegawai;
import id.co.bankaltimtara.spokc.model.masters.Username;
import id.co.bankaltimtara.spokc.predicate.SearchCriteria;
import id.co.bankaltimtara.spokc.predicate.masters.PegawaiSpecification;
import id.co.bankaltimtara.spokc.predicate.masters.WilayahSpecification;
import id.co.bankaltimtara.spokc.query.masters.KantorDivisiQueryBuilder;
import id.co.bankaltimtara.spokc.query.masters.PegawaiQueryBuilder;
import id.co.bankaltimtara.spokc.repository.masters.PegawaiRepository;
import id.co.bankaltimtara.spokc.service.PegawaiService;
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
public class PegawaiServiceImpl implements PegawaiService {

    private static final int PAGE_SIZE = 30;

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
    @Transactional(readOnly = true)
    public Integer count(Long aLong) {
        return pegawaiRepository.countById(aLong);
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

    @Override
    @Transactional(readOnly = true)
    public Page<Pegawai> dapatkan(Integer halaman, String cari) {
        if (halaman == null) {
            halaman = 1;
        }
        PageRequest pageRequest = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
        if (StringUtils.isBlank(cari)){
            return pegawaiRepository.findAll(pageRequest);
        } else {
            PegawaiQueryBuilder queryBuilder = new PegawaiQueryBuilder();
            queryBuilder.cari(cari);
            BooleanExpression expression = queryBuilder.getResult();
            return pegawaiRepository.findAll(expression, pageRequest);
        }
    }
}

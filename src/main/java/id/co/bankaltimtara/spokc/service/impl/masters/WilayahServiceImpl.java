package id.co.bankaltimtara.spokc.service.impl.masters;

import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.model.masters.Wilayah;
import id.co.bankaltimtara.spokc.predicate.SearchCriteria;
import id.co.bankaltimtara.spokc.predicate.masters.WilayahSpecification;
import id.co.bankaltimtara.spokc.query.masters.UsernameQueryBuilder;
import id.co.bankaltimtara.spokc.query.masters.WilayahQueryBuilder;
import id.co.bankaltimtara.spokc.repository.masters.WilayahRepository;
import id.co.bankaltimtara.spokc.service.WilayahService;
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
public class WilayahServiceImpl implements WilayahService {

    private static final int PAGE_SIZE = 30;

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
    public Integer count(Long aLong) {
        return wilayahRepository.countById(aLong);
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

    @Override
    public Page<Wilayah> dapatkan(Integer halaman, String cari) {
        if (halaman == null) {
            halaman = 1;
        }
        PageRequest pageRequest = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
        if (StringUtils.isBlank(cari)){
            return wilayahRepository.findAll(pageRequest);
        } else {
            WilayahQueryBuilder queryBuilder = new WilayahQueryBuilder();
            queryBuilder.cari(cari);
            BooleanExpression expression = queryBuilder.getResult();
            return wilayahRepository.findAll(expression, pageRequest);
        }
    }
}

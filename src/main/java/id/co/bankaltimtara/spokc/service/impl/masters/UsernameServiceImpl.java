package id.co.bankaltimtara.spokc.service.impl.masters;

import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.model.masters.Username;
import id.co.bankaltimtara.spokc.model.users.Pengguna;
import id.co.bankaltimtara.spokc.predicate.SearchCriteria;
import id.co.bankaltimtara.spokc.predicate.masters.UsernameSpecification;
import id.co.bankaltimtara.spokc.predicate.masters.WilayahSpecification;
import id.co.bankaltimtara.spokc.query.masters.PegawaiQueryBuilder;
import id.co.bankaltimtara.spokc.query.masters.UsernameQueryBuilder;
import id.co.bankaltimtara.spokc.repository.masters.UsernameRepository;
import id.co.bankaltimtara.spokc.service.UsernameService;
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
public class UsernameServiceImpl implements UsernameService {

    private static final int PAGE_SIZE = 30;

    @Autowired
    private UsernameRepository usernameRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Username> dapatkanSemua() {
        return usernameRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Username dapatkan(Long id) {
        return usernameRepository.findOne(id);
    }

    @Override
    public Integer count(Long aLong) {
        return usernameRepository.countById(aLong);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Username> dapatkan(Integer halaman, String cari) {
        if (halaman == null) {
            halaman = 1;
        }
        PageRequest pageRequest = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
        if (StringUtils.isBlank(cari)){
            return usernameRepository.findAll(pageRequest);
        } else {
            UsernameQueryBuilder queryBuilder = new UsernameQueryBuilder();
            queryBuilder.cari(cari);
            BooleanExpression expression = queryBuilder.getResult();
            return usernameRepository.findAll(expression, pageRequest);
        }
    }

    @Override
    @Transactional
    public void tambah(Username username) {
        usernameRepository.save(username);
    }

    @Override
    @Transactional
    public void update(Username username) {
        Username j = usernameRepository.findOne(username.getId());
        BeanUtils.copyProperties(username, j);
        usernameRepository.save(j);
    }

    @Override
    @Transactional
    public void hapus(Long id) {
        usernameRepository.delete(id);
    }

    @Override
    @Transactional
    public void hapus(Username username) {
        usernameRepository.delete(username);
    }
}

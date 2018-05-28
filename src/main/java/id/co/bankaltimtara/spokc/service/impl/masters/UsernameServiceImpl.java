package id.co.bankaltimtara.spokc.service.impl.masters;

import id.co.bankaltimtara.spokc.model.masters.Username;
import id.co.bankaltimtara.spokc.repository.masters.UsernameRepository;
import id.co.bankaltimtara.spokc.service.UsernameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsernameServiceImpl implements UsernameService {

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

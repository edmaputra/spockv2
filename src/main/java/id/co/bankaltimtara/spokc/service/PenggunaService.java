package id.co.bankaltimtara.spokc.service;

import id.co.bankaltimtara.spokc.model.users.Pengguna;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PenggunaService extends Services<Pengguna, Long> {

     void updatePengguna(Pengguna pengguna);

     void resetPassword(Pengguna pengguna);

}

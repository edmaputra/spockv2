package id.co.bankaltimtara.spokc.service;

import id.co.bankaltimtara.spokc.model.users.Pengguna;

public interface PenggunaService extends Services<Pengguna, Long> {

     void updatePengguna(Pengguna pengguna);

     void resetPassword(Pengguna pengguna);
}

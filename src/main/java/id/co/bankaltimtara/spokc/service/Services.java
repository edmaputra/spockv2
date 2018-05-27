package id.co.bankaltimtara.spokc.service;

import java.io.Serializable;

public interface Services<T, ID extends Serializable>{

    Iterable<T> dapatkanSemua();

    T dapatkan(ID id);

    void tambah(T t);

    void update(T t);

    void hapus(ID id);

    void hapus(T t);

}

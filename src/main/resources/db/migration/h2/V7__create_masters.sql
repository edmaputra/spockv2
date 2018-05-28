CREATE TABLE JABATAN (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAMA VARCHAR(140),
   LEVEL INTEGER,
   PRIMARY KEY (ID)
);

CREATE TABLE PEGAWAI (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAMA VARCHAR(140),
   NOMOR_REGISTER VARCHAR(25),
   JABATAN_ID BIGINT,
   PENGGUNA_ID BIGINT,
   KANTOR_ID BIGINT,
   PRIMARY KEY (ID)
);


CREATE TABLE WILAYAH (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAMA VARCHAR(140),
   KODE VARCHAR(20),
   PRIMARY KEY (ID)
);

CREATE TABLE KANTOR_DIVISI (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAMA VARCHAR(140),
   KODE VARCHAR(20),
   WILAYAH_ID BIGINT,
   PRIMARY KEY (ID)
);

CREATE TABLE USERNAME (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAMA VARCHAR(140),
   TIPE VARCHAR(60),
   PRIMARY KEY (ID)
);

CREATE TABLE PEGAWAI_USERNAME (
    PEGAWAI_ID BIGINT,
    USERNAME_ID BIGINT
);

ALTER TABLE PEGAWAI ADD CONSTRAINT PEGAWAI_JABATAN
   FOREIGN KEY (JABATAN_ID) REFERENCES JABATAN;

ALTER TABLE PEGAWAI ADD CONSTRAINT PEGAWAI_PENGGUNA
   FOREIGN KEY (PENGGUNA_ID) REFERENCES PENGGUNA;

ALTER TABLE PEGAWAI ADD CONSTRAINT PEGAWAI_KANTOR
   FOREIGN KEY (KANTOR_ID) REFERENCES KANTOR_DIVISI;

ALTER TABLE KANTOR_DIVISI ADD CONSTRAINT KANTOR_WILAYAH_ID
   FOREIGN KEY (WILAYAH_ID) REFERENCES WILAYAH;

ALTER TABLE PEGAWAI_USERNAME ADD CONSTRAINT P_U
   FOREIGN KEY (PEGAWAI_ID) REFERENCES PEGAWAI;

ALTER TABLE PEGAWAI_USERNAME ADD CONSTRAINT U_P
   FOREIGN KEY (USERNAME_ID) REFERENCES USERNAME;
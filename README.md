# Sistem Simulasi Transaksi Rumah Sakit (KUIS 2 PBO)

Program simulasi pengelolaan aktor (pasien), layanan medis, pemrosesan transaksi berbasis file teks, penanganan kesalahan (*custom exception*), dan persistensi data (*object serialization*) menggunakan bahasa pemrograman Java.

---

## 📌 Konfigurasi Soal (Kombinasi Digit: 007)

* **Digit Pertama (0) - Tema & Main Class:**
  * Tema: **Sistem Rumah Sakit** (`MainRumahSakit`).

* **Digit Kedua (0) - Format Delimiter Input (`input_transaksi.txt`):**
  * Format pemisah baris khusus: `ID>...KD>...JML>...` diakhiri titik.
  * Pembacaan data dilakukan secara akurat di dalam blok `try-catch` dengan penanganan data tidak valid.
* **Digit Ketiga (7) - Output & Serialization (`simpanLaporanAkhir()`):**
  * **Object Serialization:** Menyimpan *state* objek ke file biner `sistem_state.ser` (`java.io.Serializable`).
  * **File I/O Teks:** Menampilkan daftar aktor aktif menggunakan metode `.values()` pada `HashMap` dan mencetaknya ke file teks laporan (`laporan_aktor.txt`).

---

## 🏗️ Struktur Proyek Modular

Source code disusun secara modular di dalam package `rumahsakit`:

```text
RumahSakit/
├── nbproject/                 # Konfigurasi project NetBeans
├── src/
│   └── rumahsakit/
│       ├── Aktor.java              # Abstract Superclass Aktor (Encapsulation, Serializable)
│       ├── Pasien.java             # Subclass Pasien (Inheritance dari Aktor, Polymorphism)
│       ├── ItemLayanan.java        # Abstract Superclass Layanan (Encapsulation, Serializable)
│       ├── LayananMedis.java       # Subclass Layanan (Inheritance, Polymorphism)
│       ├── TransaksiException.java # Custom Exception untuk validasi transaksi
│       ├── SistemRumahSakit.java   # Controller sistem (Association, HashMap, File I/O, Serialization)
│       └── MainRumahSakit.java     # Main Class entry point

├── input_transaksi.txt        # File input transaksi
├── laporan_aktor.txt          # File output laporan teks daftar pasien aktif
├── sistem_state.ser           # File biner hasil object serialization
├── .gitignore                 # File pengabaian build artifacts
└── README.md                  # Dokumentasi proyek
```

---


---

## Format Input (`input_transaksi.txt`)

```text
ID>ACT-01.KD>OBJ-A.JML>3.
ID>ACT-02.KD>OBJ-C.JML>2.
ID>ACT-01.KD>OBJ-B.JML>5.
ID>ACT-99.KD>OBJ-A.JML>1.
ID>ACT-02.KD>OBJ-A.JML>1.
```

---

## Contoh Output 

```text
--- MEMULAI PEMBACAAN FILE TRANSAKSI ---
Transaksi Sukses -> Pasien: Cici | Layanan: Rawat Inap VIP | Qty: 3 | Total: 30.0
Transaksi Sukses -> Pasien: Doni | Layanan: UGD Ekonomis | Qty: 2 | Total: 100.0
Transaksi Sukses -> Pasien: Cici | Layanan: Rawat Jalan Reguler | Qty: 5 | Total: 10.0
[WARNING TRANSAKSI] Aktor (Pasien) dengan ID 'ACT-99' tidak terdaftar di sistem! (Data: ID>ACT-99.KD>OBJ-A.JML>1.)
Transaksi Sukses -> Pasien: Doni | Layanan: Rawat Inap VIP | Qty: 1 | Total: 10.0

--- DAFTAR AKTOR AKTIF (PASIEN) ---
ID Aktor: ACT-01 | Nama: Cici
ID Aktor: ACT-02 | Nama: Doni
------------------------------------
[INFO] Daftar aktor aktif berhasil ditulis ke 'laporan_aktor.txt'.
[INFO] Sistem state berhasil diserialisasi ke 'sistem_state.ser'.
```

---


```

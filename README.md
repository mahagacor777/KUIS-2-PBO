# Sistem Simulasi Transaksi Rumah Sakit (KUIS 2 PBO)

Program simulasi pengelolaan aktor (pasien), layanan medis, pemrosesan transaksi berbasis file teks, penanganan kesalahan (*custom exception*), dan persistensi data (*object serialization*) menggunakan bahasa pemrograman Java.

---

## 📌 Konfigurasi Soal (Kombinasi Digit: 007)

* **Digit Pertama (0) - Tema & Main Class:**
  * Tema: **Sistem Rumah Sakit** (`MainRumahSakit`).
  * Menerapkan pilar Pemrograman Berorientasi Objek (Class, Encapsulation, Inheritance, Polymorphism, Association) dan Custom Exception tanpa mengubah kode pada metode `main`.
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
├── build.xml                  # Ant build file NetBeans
├── manifest.mf                # Java manifest file
├── input_transaksi.txt        # File input transaksi
├── laporan_aktor.txt          # File output laporan teks daftar pasien aktif
├── sistem_state.ser           # File biner hasil object serialization
├── .gitignore                 # File pengabaian build artifacts
└── README.md                  # Dokumentasi proyek
```

---

## 💡 Penerapan Konsep PBO

1. **Encapsulation:** 
   Seluruh atribut pada class `Aktor`, `ItemLayanan`, dan `SistemRumahSakit` menggunakan akses `private` dengan method *getter/setter* yang aman.
2. **Inheritance (Pewarisan):** 
   * `Pasien` mewarisi properti dan method dari `Aktor`.
   * `LayananMedis` mewarisi properti dan method dari `ItemLayanan`.
3. **Polymorphism (Polimorfisme):**
   * *Method Overriding* `getDeskripsi()` pada class `Pasien`.
   * *Method Overriding* `hitungTotal(int jumlah)` pada class `LayananMedis`.
4. **Association (Asosiasi):** 
   Class `SistemRumahSakit` berasosiasi dengan banyak `Pasien` dan `LayananMedis` melalui koleksi `HashMap<String, Pasien>` dan `HashMap<String, LayananMedis>`.
5. **Custom Exception:** 
   Class `TransaksiException` dilempar (*thrown*) ketika ID Pasien atau Kode Layanan tidak ditemukan di sistem (misal: `ACT-99`), dan ditangkap (*caught*) dalam perulangan sehingga proses pembacaan file dapat terus berlanjut.

---

## 📄 Format Input (`input_transaksi.txt`)

```text
ID>ACT-01.KD>OBJ-A.JML>3.
ID>ACT-02.KD>OBJ-C.JML>2.
ID>ACT-01.KD>OBJ-B.JML>5.
ID>ACT-99.KD>OBJ-A.JML>1.
ID>ACT-02.KD>OBJ-A.JML>1.
```

---

## 🖥️ Contoh Output Eksekusi

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

## 🚀 Cara Menjalankan Proyek

### Menggunakan Apache NetBeans
1. Buka Apache NetBeans.
2. Pilih menu **File > Open Project...** lalu arahkan ke folder `RumahSakit`.
3. Klik kanan pada proyek `RumahSakit` dan pilih **Run** (atau tekan `F6`).

### Menggunakan Terminal / Command Prompt
```bash
# Kompilasi seluruh file source code
javac -d build/classes src/rumahsakit/*.java

# Jalankan program
java -cp build/classes rumahsakit.MainRumahSakit
```

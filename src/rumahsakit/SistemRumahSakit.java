package rumahsakit;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kelas SistemRumahSakit mengelola daftar aktor (Pasien) dan objek (LayananMedis).
 * Menerapkan konsep Association, File I/O Teks (input/output), dan Object Serialization.
 */
public class SistemRumahSakit implements Serializable {
    private static final long serialVersionUID = 1L;
    private String namaRs;
    private HashMap<String, Pasien> daftarPasien = new HashMap<>();
    private HashMap<String, LayananMedis> daftarLayanan = new HashMap<>();

    public SistemRumahSakit(String namaRs) {
        this.namaRs = namaRs;
    }

    public String getNamaRs() {
        return namaRs;
    }

    public void tambahPasien(String id, String nama) {
        daftarPasien.put(id, new Pasien(id, nama));
    }

    public void tambahLayananMedis(String kode, String namaLayanan, double biaya) {
        daftarLayanan.put(kode, new LayananMedis(kode, namaLayanan, biaya));
    }

    /**
     * Membaca dan memproses transaksi dari file dengan delimiter format:
     * ID>ACT-01.KD>OBJ-A.JML>3.
     */
    public void jalankanTransaksiDariFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File transaksi tidak ditemukan: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String baris;
            // Delimiter regex pattern: ID>...KD>...JML>...
            Pattern pattern = Pattern.compile("ID>([^.]+)\\.KD>([^.]+)\\.JML>([^.]+)");

            while ((baris = reader.readLine()) != null) {
                baris = baris.trim();
                if (baris.isEmpty()) continue;

                Matcher matcher = pattern.matcher(baris);
                if (matcher.find()) {
                    String idPasien = matcher.group(1).trim();
                    String kodeLayanan = matcher.group(2).trim();

                    try {
                        int jumlah = Integer.parseInt(matcher.group(3).trim());

                        // Validasi keberadaan aktor dan layanan menggunakan Custom Exception
                        if (!daftarPasien.containsKey(idPasien)) {
                            throw new TransaksiException("Aktor (Pasien) dengan ID '" + idPasien + "' tidak terdaftar di sistem!");
                        }
                        if (!daftarLayanan.containsKey(kodeLayanan)) {
                            throw new TransaksiException("Objek Layanan dengan kode '" + kodeLayanan + "' tidak terdaftar di sistem!");
                        }

                        Pasien p = daftarPasien.get(idPasien);
                        LayananMedis l = daftarLayanan.get(kodeLayanan);
                        double total = l.hitungTotal(jumlah);

                        System.out.println("Transaksi Sukses -> Pasien: " + p.getNama() + 
                                           " | Layanan: " + l.getNamaLayanan() + 
                                           " | Qty: " + jumlah + " | Total: " + total);

                    } catch (NumberFormatException e) {
                        System.out.println("[FORMAT ERROR] Jumlah bukan angka valid pada baris: " + baris);
                    } catch (TransaksiException e) {
                        // Menangani custom exception agar eksekusi baris berikutnya tidak terhenti
                        System.out.println("[WARNING TRANSAKSI] " + e.getMessage() + " (Data: " + baris + ")");
                    }
                } else {
                    System.out.println("[FORMAT ERROR] Baris tidak sesuai format: " + baris);
                }
            }
        }
    }

    /**
     * Menampilkan daftar aktor aktif dan mencetaknya ke file teks laporan_aktor.txt
     * menggunakan method .values() pada HashMap daftarPasien.
     */
    public void cetakDaftarAktorAktif() {
        System.out.println("\n--- DAFTAR AKTOR AKTIF (PASIEN) ---");
        for (Pasien pasien : daftarPasien.values()) {
            System.out.println("ID Aktor: " + pasien.getId() + " | Nama: " + pasien.getNama());
        }
        System.out.println("------------------------------------");

        // File I/O Teks: Cetak ke file laporan_aktor.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("laporan_aktor.txt"))) {
            writer.println("=== DAFTAR AKTOR AKTIF (PASIEN) ===");
            for (Pasien pasien : daftarPasien.values()) {
                writer.println("ID: " + pasien.getId() + " | Nama: " + pasien.getNama());
            }
            System.out.println("[INFO] Daftar aktor aktif berhasil ditulis ke 'laporan_aktor.txt'.");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis laporan teks: " + e.getMessage());
        }
    }

    /**
     * Menyimpan status akhir sistem:
     * 1. Output laporan teks aktor aktif
     * 2. Object serialization ke file biner sistem_state.ser
     */
    public void simpanLaporanAkhir() {
        cetakDaftarAktorAktif();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sistem_state.ser"))) {
            oos.writeObject(this);
            System.out.println("[INFO] Sistem state berhasil diserialisasi ke 'sistem_state.ser'.");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menyimpan state sistem: " + e.getMessage());
        }
    }
}

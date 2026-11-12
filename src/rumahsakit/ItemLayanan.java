package rumahsakit;

import java.io.Serializable;

/**
 * Superclass abstrak untuk mendefinisikan Objek Layanan dalam sistem.
 * Menerapkan Encapsulation dan Serializable.
 */
public abstract class ItemLayanan implements Serializable {
    private static final long serialVersionUID = 1L;
    private String kode;
    private String namaLayanan;
    private double biaya;

    public ItemLayanan(String kode, String namaLayanan, double biaya) {
        this.kode = kode;
        this.namaLayanan = namaLayanan;
        this.biaya = biaya;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public double getBiaya() {
        return biaya;
    }

    public void setBiaya(double biaya) {
        this.biaya = biaya;
    }

    // Metode polimorfik untuk kalkulasi biaya layanan
    public abstract double hitungTotal(int jumlah);
}

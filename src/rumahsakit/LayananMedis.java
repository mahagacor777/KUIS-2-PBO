package rumahsakit;

/**
 * Subclass LayananMedis merepresentasikan layanan medis riil di rumah sakit.
 * Menerapkan Inheritance (turunan dari ItemLayanan) dan Polymorphism (overriding method hitungTotal).
 */
public class LayananMedis extends ItemLayanan {
    private static final long serialVersionUID = 1L;

    public LayananMedis(String kode, String namaLayanan, double biaya) {
        super(kode, namaLayanan, biaya);
    }

    @Override
    public double hitungTotal(int jumlah) {
        return getBiaya() * jumlah;
    }
}

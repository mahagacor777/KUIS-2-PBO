package rumahsakit;

/**
 * Subclass Pasien merepresentasikan Aktor pasien di rumah sakit.
 * Menerapkan prinsip Inheritance (turunan dari Aktor) dan Polymorphism (overriding method getDeskripsi).
 */
public class Pasien extends Aktor {
    private static final long serialVersionUID = 1L;

    public Pasien(String id, String nama) {
        super(id, nama);
    }

    @Override
    public String getDeskripsi() {
        return "Pasien [ID: " + getId() + ", Nama: " + getNama() + "]";
    }
}

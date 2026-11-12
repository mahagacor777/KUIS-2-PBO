package rumahsakit;

import java.io.Serializable;

/**
 * Superclass abstrak untuk mendefinisikan entitas Aktor dalam sistem.
 * Menerapkan prinsip Encapsulation dan Serializable untuk Object Serialization.
 */
public abstract class Aktor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nama;

    public Aktor(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Metode polimorfik
    public abstract String getDeskripsi();
}

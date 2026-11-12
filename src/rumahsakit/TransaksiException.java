package rumahsakit;

/**
 * Custom Exception untuk menangani kesalahan atau validasi data pada transaksi rumah sakit.
 */
public class TransaksiException extends Exception {
    public TransaksiException(String message) {
        super(message);
    }
}

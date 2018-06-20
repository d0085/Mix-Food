package id.ac.unsyiah.android.mixfood;

/**
 * Created by FMA on 13/05/2018.
 */

public class Tempat {

    private String Nama;
    private String Alamat;
    private String Rating;
    private String Status;
    private int Foto;
    private int Favorit;


    private Tempat(){

    }

    public Tempat(String nama, String alamat, String rating, String status, int foto, int favorit) {
        Nama = nama;
        Alamat = alamat;
        Rating = rating;
        Status = status;
        Foto = foto;
        Favorit = favorit;
    }

    public String getNama() {
        return Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getRating() {
        return Rating;
    }

    public String getStatus() {
        return Status;
    }

    public int getFoto() {
        return Foto;
    }

    public int getFavorit() {
        return Favorit;
    }

    public void setNama(String nama) { Nama = nama; }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    public void setFavorit(int favorit) {
        Foto = favorit;
    }
}

package com.example.uas_mobile_revaldo.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorit")
public class Favorit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String endemikId;
    private String nama;
    private String namaLatin;
    private String tipe;
    private String foto;
    private String status;

    public Favorit(String endemikId, String nama, String namaLatin,
                   String tipe, String foto, String status) {
        this.endemikId = endemikId;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.tipe = tipe;
        this.foto = foto;
        this.status = status;
    }

    // ===== GETTER =====
    public int getId() { return id; }
    public String getEndemikId() { return endemikId; }
    public String getNama() { return nama; }
    public String getNamaLatin() { return namaLatin; }
    public String getTipe() { return tipe; }
    public String getFoto() { return foto; }
    public String getStatus() { return status; }

    // ===== SETTER =====
    public void setId(int id) { this.id = id; }
    public void setEndemikId(String endemikId) { this.endemikId = endemikId; }
    public void setNama(String nama) { this.nama = nama; }
    public void setNamaLatin(String namaLatin) { this.namaLatin = namaLatin; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public void setFoto(String foto) { this.foto = foto; }
    public void setStatus(String status) { this.status = status; }
}

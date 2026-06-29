package com.example.uas_mobile_revaldo.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "endemik")
public class Endemik {

    @PrimaryKey(autoGenerate = true)
    private int roomId;

    @SerializedName("id")
    private String id;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nama_latin")
    @ColumnInfo(name = "nama_latin")
    private String namaLatin;

    @SerializedName("famili")
    private String famili;

    @SerializedName("genus")
    private String genus;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("asal")
    private String asal;

    @SerializedName("sebaran")
    private String sebaran;

    @SerializedName("foto")
    private String foto;

    @SerializedName("vidio")
    private String vidio;

    @SerializedName("status")
    private String status;

    // ===== GETTER =====
    public int getRoomId() { return roomId; }
    public String getId() { return id; }
    public String getTipe() { return tipe; }
    public String getNama() { return nama; }
    public String getNamaLatin() { return namaLatin; }
    public String getFamili() { return famili; }
    public String getGenus() { return genus; }
    public String getDeskripsi() { return deskripsi; }
    public String getAsal() { return asal; }
    public String getSebaran() { return sebaran; }
    public String getFoto() { return foto; }
    public String getVidio() { return vidio; }
    public String getStatus() { return status; }

    // ===== SETTER =====
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public void setId(String id) { this.id = id; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public void setNama(String nama) { this.nama = nama; }
    public void setNamaLatin(String namaLatin) { this.namaLatin = namaLatin; }
    public void setFamili(String famili) { this.famili = famili; }
    public void setGenus(String genus) { this.genus = genus; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public void setAsal(String asal) { this.asal = asal; }
    public void setSebaran(String sebaran) { this.sebaran = sebaran; }
    public void setFoto(String foto) { this.foto = foto; }
    public void setVidio(String vidio) { this.vidio = vidio; }
    public void setStatus(String status) { this.status = status; }
}

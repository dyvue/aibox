/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aibox.model;

/**
 *
 * @author romad
 */
public class Transaksi {
    public String IdProduk, NamaProduk, Jumlah;
    public int TotalHarga;
    
    public Transaksi(String IdProduk, String NamaProduk, String Jumlah, int TotalHarga)
    {
        this.IdProduk = IdProduk;
        this.NamaProduk = NamaProduk;
        this.Jumlah = Jumlah;
        this.TotalHarga = TotalHarga;
    }
}

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
    public String ProdukId;
    public int Jumlah;
    
    Transaksi(String ProdukId, int Jumlah)
    {
        this.ProdukId = ProdukId;
        this.Jumlah = Jumlah;
    }
}

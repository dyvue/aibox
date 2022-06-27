/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aibox.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author  Romadhan Edy
 * @version 1.0
 */
public class Helper {
    /**
    * @params string
    * @return string
    */
    public String CurrRupiah(String number) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        return String.valueOf(kursIndonesia.format(Double.parseDouble(number)));
    }
}

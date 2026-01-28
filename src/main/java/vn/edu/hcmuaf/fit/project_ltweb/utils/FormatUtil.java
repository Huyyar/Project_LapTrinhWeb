package vn.edu.hcmuaf.fit.project_ltweb.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Utility class for formatting values
 */
public class FormatUtil {
    
    
    public static String formatPrice(double price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(price) + " đ";
    }
    
    
    public static String formatPriceCompact(double price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(price) + "đ";
    }
}

package inheritance.analyser;

import java.util.List;

public final class TaxFreeSalesAnalyser extends FatherClass {

    protected TaxFreeSalesAnalyser(List<SalesRecord> records) {
        for (SalesRecord elem : records) {
            double sell = elem.getProductPrice() * elem.getItemsSold();
            total += sell;
            switch (elem.getProductId()) {
                case "i1":
                    i1 += sell;
                    i1Count += elem.getItemsSold();
                    break;
                case "i2":
                    i2 += sell;
                    i2Count += elem.getItemsSold();
                    break;
                case "i3":
                    i3 += sell;
                    i3Count += elem.getItemsSold();
                    break;
                case "i4":
                    i4 += sell;
                    i4Count += elem.getItemsSold();
                    break;
                case "i5":
                    i5 += sell;
                    i5Count += elem.getItemsSold();
                    break;
                case "i6":
                    i6 += sell;
                    i6Count += elem.getItemsSold();
                    break;
                default:
                    i7 += sell;
                    i7Count += elem.getItemsSold();
                    break;
            }
            Integer[] idsCount = {i1Count, i2Count, i4Count, i5Count};
            Double[] idsTotal = {i1, i2, i3, i4, i5, i6, i7};
            for (Integer i : idsCount) {
                if (inCount < i){
                    inCount = i;
                    mostPopular = elem.getProductId();
                }
            }
            for (Double i : idsTotal) {
                if (inTotal < i){
                    inTotal = i;
                    largestTotalSale = elem.getProductId();
                }
            }
        }
    }

}

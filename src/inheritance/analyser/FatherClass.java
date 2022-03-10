package inheritance.analyser;

abstract class FatherClass {

    Double total = 0.0;
    Double totalById = 0.0;
    Double inTotal = 0.0;
    double i1, i2, i3, i4, i5, i6, i7;
    int i1Count, i2Count, i3Count, i4Count, i5Count, i6Count, i7Count,
            inCount = 0;
    String mostPopular, largestTotalSale;


    protected Double getTotalSalesByProductId(String id) {
        switch (id) {
            case "i1":
                totalById = i1;
                break;
            case "i2":
                totalById = i2;
                break;
            case "i3":
                totalById = i3;
                break;
            case "i4":
                totalById = i4;
                break;
            case "i5":
                totalById = i5;
                break;
            case "i6":
                totalById = i6;
                break;
            default:
                totalById = i7;
                break;

        }
        return totalById;
    }

    protected Double getTotalSales() {
        return total;
    }

}

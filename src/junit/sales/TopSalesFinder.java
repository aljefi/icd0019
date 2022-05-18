package junit.sales;

public class TopSalesFinder {

    String[] item = new String[1];
    Integer[] price = new Integer[1];
    int count = 0;
    int sizeOfArray = 1;


    public void registerSale(SalesRecord record) {
        int sum = record.getProductPrice() * record.getItemsSold();
        if (count == sizeOfArray) {
            growSize();
        }
        item[count] = record.getProductId();
        price[count] = sum;
        count++;
        // store sales record for later analyses by findItemsSoldOver()
    }

    public void growSize() {
        String[] tempItem = null;
        Integer[] tempPrice = null;
        boolean increased = false;
        if (count == sizeOfArray) {
            tempItem = new String[sizeOfArray];
            tempPrice = new Integer[sizeOfArray];
            for (int i = 0; i < sizeOfArray; i++) {
                if (notContains(tempItem, item[i])) {
                    if (!increased) {
                        tempItem = new String[sizeOfArray + 1];
                        tempPrice = new Integer[sizeOfArray + 1];
                        increased = true;
                    }
                    tempItem[i] = item[i];
                    tempPrice[i] = price[i];
                } else if (item[i] != null) {
                    extracted(tempItem, tempPrice, i);
                    break;
                }
            }
        }
        item = tempItem;
        price = tempPrice;
        sizeOfArray = sizeOfArray + 1;
    }

    private void extracted(String[] tempItem, Integer[] tempPrice, int i) {
        int c = 0;
        for (String s : tempItem) {
            if (s != null && s.equals(item[i])) {
                break;
            } else {
                c++;
            }
        }
        tempItem[c] = item[i];
        tempPrice[c] += price[i];
    }

    public static boolean notContains(String[] temp, String num) {
        for (String i : temp) {
            if (i != null && i.equals(num)) {
                return false;
            }
        }
        return true;
    }

    public String[] findItemsSoldOver(int amount) {
        String[] itemsSoldOver = new String[1];
        sizeOfArray = itemsSoldOver.length;
        count = 0;
        int temp = 0;
        // find ids of records that sold over specified amount.

        for (int i = 0; i < item.length; i++) {
            if (price[i] != null && price[i] > amount) {
                if (count == sizeOfArray) {
                    itemsSoldOver = growSizeForSoldOver(itemsSoldOver);
                }
                if (notContains(itemsSoldOver, item[i])) {
                    itemsSoldOver[temp] = item[i];
                    temp++;
                    count++;
                }
            }
        }
        if (itemsSoldOver.length == 1 && itemsSoldOver[0] == null) {
            itemsSoldOver = new String[0];
        }
        return itemsSoldOver;
    }

    private String[] growSizeForSoldOver(String[] itemsSoldOver) {
        String[] tempItemsSoldOver = new String[itemsSoldOver.length + 1];
        for (int i = 0; i < sizeOfArray; i++) {
            tempItemsSoldOver[i] = itemsSoldOver[i];
        }
        itemsSoldOver = tempItemsSoldOver;
        sizeOfArray = sizeOfArray + 1;
        return itemsSoldOver;
    }

}



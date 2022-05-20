package inheritance.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private final SimplePager dataSource;
    @SuppressWarnings("PMD.UnusedPrivateField")
    private final int pageSize;
    private int page = -1;

    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<Integer> getNextPage() {
        List<Integer> nextPage = new ArrayList<>();
        page += 1;
        try {
            getPage(nextPage);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("There is no next page");
        }

        return nextPage;
    }

    public List<Integer> getCurrentPage() {
        List<Integer> currentPage = new ArrayList<>();
        try {
            getPage(currentPage);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("There is no current page");
        }
        return currentPage;
    }

    public List<Integer> getPreviousPage() {
        List<Integer> previousPage = new ArrayList<>();
        page -= 1;
        try {
            getPage(previousPage);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("There is no previous page");
        }
        return previousPage;
    }

    private void getPage(List<Integer> previousPage) {
        for (Integer data : dataSource.getPage(page)) {
            if (data != null) {
                previousPage.add(data);
            }
        }
    }

//    private void goToNextPage() {
//        rightBound = 0;
//        endPageNumber++;
//    }
//
//    private void goToPreviousPage() {
//        startPageNumber--;
//        if (dataSource.hasPage(startPageNumber)) {
//            leftBound = dataSource.getPage(startPageNumber).size() - 1;
//        }
//    }
}

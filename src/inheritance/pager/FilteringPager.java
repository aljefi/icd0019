package inheritance.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private final SimplePager dataSource;
    @SuppressWarnings("PMD.UnusedPrivateField")
    private final int pageSize;
    public int startPageNumber;
    public int leftBorder;
    public int rightBorder;
    public int endPageNumber;


    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<Integer> getNextPage() {
        startPageNumber = endPageNumber;
        leftBorder = rightBorder - 1;
        ArrayList<Integer> nextPage = new ArrayList<>();
        while (dataSource.hasPage(endPageNumber)) {
            List<Integer> page = dataSource.getPage(endPageNumber);
            if (rightBorder >= page.size()) {
                goToNextPage();
                continue;
            }
            while (rightBorder < page.size()) {
                Integer element = page.get(rightBorder++);
                if (element != null) {
                    nextPage.add(element);
                    if (nextPage.size() == pageSize) {
                        return nextPage;
                    }
                }
            }
            goToNextPage();
        }
        if (nextPage.isEmpty()) {
            throw new IllegalStateException("There is no next page");
        }
        return nextPage;
    }

    public List<Integer> getCurrentPage() {
        int pageNumber = startPageNumber;
        int pos = leftBorder + 1;
        ArrayList<Integer> currentPage = new ArrayList<>();

        while ((pos < rightBorder || pageNumber < endPageNumber) && dataSource.hasPage(pageNumber)) {
            List<Integer> page = dataSource.getPage(pageNumber);

            if (pos >= page.size()) {
                pos = 0;
                pageNumber++;
                continue;
            }

            if (addToCurrentPage(pos, currentPage, page)) {
                return currentPage;
            }
            pos = 0;
            pageNumber++;
        }

        if (currentPage.isEmpty()) {
            throw new IllegalStateException("There is no current page");
        }

        return currentPage;
    }

    private boolean addToCurrentPage(int pos, ArrayList<Integer> currentPage, List<Integer> page) {
        while (pos < page.size()) {
            Integer element = page.get(pos++);
            if (element != null) {
                currentPage.add(element);
                if (currentPage.size() == pageSize) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> getPreviousPage() {
        endPageNumber = startPageNumber;
        rightBorder = leftBorder + 1;

        if (leftBorder == -1) {
            leftBorder = 0;
        }

        ArrayList<Integer> previousPage = new ArrayList<>();

        while (dataSource.hasPage(startPageNumber)) {

            List<Integer> page = dataSource.getPage(startPageNumber);

            if (leftBorder <= -1) {
                goToPreviousPage();
                continue;
            }

            while (leftBorder > -1) {
                Integer element = page.get(leftBorder--);
                if (element != null) {
                    previousPage.add(0, element);
                    if (previousPage.size() == pageSize) {
                        return previousPage;
                    }
                }
            }

            goToPreviousPage();
        }

        if (previousPage.isEmpty()) {
            throw new IllegalStateException("There is no previous page");
        }

        return previousPage;
    }


    private void goToNextPage() {
        rightBorder = 0;
        endPageNumber++;
    }

    private void goToPreviousPage() {
        startPageNumber--;
        if (dataSource.hasPage(startPageNumber)) {
            leftBorder = dataSource.getPage(startPageNumber).size() - 1;
        }
    }

}

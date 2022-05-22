package poly.installments;

import generics.pair.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstallmentGenerator {
    
    public List<Installment> generateRowsFor(Integer amount, LocalDate periodStart, LocalDate periodEnd) {
        if (amount <= 3) {
            return List.of(new Installment(amount, periodStart));
        }

        int installmentsCount = 0;
        LocalDate currentInstallmentDate = periodStart;
        while (currentInstallmentDate.compareTo(periodEnd) < 0) {
            installmentsCount++;
            currentInstallmentDate = currentInstallmentDate.plusMonths(1);
        }

        int installmentAmount = amount / installmentsCount;
        while (installmentAmount < 3) {
            installmentAmount = amount / --installmentsCount;
        }

        ArrayList<Pair<Integer, LocalDate>> installments = new ArrayList<>();
        while (installmentsCount > 0) {
            installments.add(new Pair<>(installmentAmount, periodStart));
            periodStart = periodStart.plusMonths(1);
            while (periodStart.getDayOfMonth() != 1) {
                periodStart = periodStart.minusDays(1);
            }
            installmentsCount--;
            amount -= installmentAmount;
        }

        ArrayList<Installment> reversedInstallments = new ArrayList<>();
        for (int index = installments.size() - 1; index >= 0; index--) {
            Pair<Integer, LocalDate> pair = installments.get(index);
            int instAmount = pair.getFirst();
            if (amount > 0) {
                instAmount++;
            }
            Installment installment = new Installment(instAmount, pair.getSecond());
            reversedInstallments.add(installment);
            amount--;
        }

        Collections.reverse(reversedInstallments);
        return reversedInstallments;
    }
}

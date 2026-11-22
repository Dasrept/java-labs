package edu.course.lab02;

import java.util.Arrays;

public final class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);
        account.deposit(50);
        account.withdraw(20);
        System.out.println("Bank account balance: " + account.getBalance());

        DataSample sample = new DataSample(
                "sample-1", "cat", SampleStatus.NEW, new double[]{2.0, 4.0, 6.0});
        System.out.println("Sample id: " + sample.getId());
        System.out.println("Ready before status change: " + sample.isReady());
        sample.changeStatus(SampleStatus.READY);
        System.out.println("Ready after status change: " + sample.isReady());
        System.out.println("Average feature: " + sample.averageFeature());
        System.out.println("Normalized features: "
                + Arrays.toString(sample.normalizedFeatures()));
    }
}

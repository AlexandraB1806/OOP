package lab12.reports;

import java.util.*;
import java.util.stream.Collectors;
import lab12.entities.*;

public class BankReport {
    // Customer = Employee of the Business
    // Business = a client of the Bank
    // Customers of the Bank = all the Employees that work for the Businesses that are clients of the Bank

    public static int getNumberOfCustomers(Bank bank) {
        // All the customers that have accounts at the bank
        return bank.getClients().stream().map(Business::getEmployees).mapToInt(Set::size).sum();
    }

    public static int getNumberOfAccounts(Bank bank) {
        // Accounts of all the customers of the bank
        return bank.getClients().stream()
                .map(Business::getEmployees).flatMap(Set::stream)
                .map(Employee::getAccounts).mapToInt(Set::size).sum();
    }

    public static SortedSet<Employee> getCustomersSorted(Bank bank) {
        // Display the set of customers in alphabetical order
        Comparator<Employee> comp = Comparator.comparing(Employee::getName);
        SortedSet<Employee> customers = new TreeSet<>(comp);
        bank.getClients().forEach(client -> customers.addAll(client.getEmployees()));

        return Collections.unmodifiableSortedSet(customers);
    }

    public static double getTotalSumInAccounts(Bank bank) {
        // Sum of all customers' accounts balances
        return bank.getClients().stream().map(Business::getEmployees)
                .flatMap(Set::stream).map(Employee::getAccounts).flatMap(Set::stream)
                .mapToDouble(Account::getBalance).sum();
    }

    public static SortedSet<Account> getAccountsSortedBySum(Bank bank) {
        // The set of all accounts, ordered by current account balance
        Comparator<Account> comp = (o1, o2) -> {
            if(o1.equals(o2))
                return 0;
            else return 1;
        };
        SortedSet<Account> accounts = new TreeSet<Account>(comp);
        bank.getClients().stream().map(Business::getEmployees).flatMap(Set::stream)
                .map(Employee::getAccounts).flatMap(Set::stream).forEach(accounts::add);

        return accounts;
    }

    public static Map<Employee, Collection<Account>> getCustomerAccounts(Bank bank) {
        // Return a map of all the customers with their respective accounts
        return bank.getClients().stream().map(Business::getEmployees).flatMap(Set::stream)
                .collect(Collectors.toMap(employee -> employee, Employee::getAccounts));
    }

    public static Map<String, List<Employee>> getCustomersByCity(Bank bank) {
        // Map all the customers to their respective cities
        return bank.getClients().stream()
                .map(Business::getEmployees).flatMap(Set::stream)
                .collect(Collectors.groupingBy(Employee::getCity));
    }
}

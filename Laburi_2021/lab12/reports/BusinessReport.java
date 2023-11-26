package lab12.reports;

import java.util.*;
import java.util.stream.Collectors;

import lab12.entities.*;

public class BusinessReport {

    public static Map<Disability, List<Employee>> getEmployeesOnSameDisability(Business business) {
        // Get employees and map them on the type of disability they possess
        return business.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDisability));
    }

    public static long getNumberOfDifferentProjectsWorkedByCurrentFemaleEmployees(Business business) {
        // Get employees, filter by gender, get their projects without duplicates, count
        return business.getEmployees().stream().filter(employee -> employee.getGender().equals(Gender.FEMALE))
                .map(Employee::getProjects).flatMap(List::stream).collect(Collectors.toSet()).size();
    }

    public static Map<Religion, Map<Gender, List<Employee>>> getEmployeesOnSameReligionAndGender(Business business) {
        // Map the employees by religion then map each of the lists by city
        Map<Religion, Map<Gender, List<Employee>>> result = new HashMap<>();
        Map<Religion, List<Employee>> religion = business.getEmployees().
                stream().collect(Collectors.groupingBy(Employee::getReligion));

        // Iterate through each religion
        for (Religion religionIterator : religion.keySet()) {
            Map<Gender, List<Employee>> genders;
            genders = religion.get(religionIterator).stream().collect(Collectors.groupingBy(Employee::getGender));
            result.put(religionIterator, genders);
        }
        return result;
    }
}

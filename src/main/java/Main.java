import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Person> people = getPeople();

        // Imperative approach ❌

        System.out.println("\nImperative approach\n");

        List<Person> females = new ArrayList<>();

        for (Person person : people) {

            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }

        females.forEach(System.out::println);

        // Declarative approach ✅

        System.out.println("\nDeclarative approach");

        // FILTER
        System.out.println("\nFiltering by Male Gender");
        List<Person> femalesStream = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .collect(Collectors.toList()); //it saves the result of the search into a new list = females;

        femalesStream.forEach(System.out::println);


        // SORT
        System.out.println("\nSorting by Age Descending");
        List<Person> sortedByAgeDesc = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());

        sortedByAgeDesc.forEach(System.out::println);

        System.out.println("\nSorting by Age Ascending");
        List<Person> sortedByAgeAsc = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
                .collect(Collectors.toList());

        sortedByAgeAsc.forEach(System.out::println);

        System.out.println("\nSorting by Name Ascending");
        List<Person> sortedByNameDesc = people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());

        sortedByNameDesc.forEach(System.out::println);

        System.out.println("\nSorting by Name Descending");
        List<Person> sortedByNameAsc = people.stream()
                .sorted(Comparator.comparing(Person::getName).reversed())
                .collect(Collectors.toList());

        sortedByNameAsc.forEach(System.out::println);

        // ALL MATCH
        System.out.println("\nAll Match person age should be true with greater than 6");
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 6); //Everybody in this list has an age bigger than 6 years, so the result will be true
        System.out.println(allMatch);

        System.out.println("\nAll Match person name should be true with starts with 'A' letter");
        boolean allMatchNameStartsWithLetterA = people.stream()
                .allMatch(person -> person.getName().startsWith("A")); //Not everybody in this list has a name that starts with "A" letter, so the result with be false
        System.out.println(allMatchNameStartsWithLetterA);

        // ANY MATCH
        System.out.println("\nAny Match person at person data collection should have at least one person with an age greater than 119 years to this returns true");
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 119); //At least one person should have an age greater than 119 years to this returns true
        System.out.println(anyMatch);


        // NONE MATCH
        System.out.println("\nNone Match will ensure that there's no one in this data collection with the name equal to 'Vítor' and the return will be true");
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Vítor"));
        System.out.println(noneMatch);

        // Max
        System.out.println("\nMax in this case will return the person with the greatest age");
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Min
        System.out.println("\nMin in this case will return the person with the lowest age");
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println("\n" + gender);
            people1.forEach(System.out::println);
        });

        System.out.println("\nGrouping Female and looking for the oldest one");
        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }

}
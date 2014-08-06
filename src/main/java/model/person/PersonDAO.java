package model.person;

import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public interface PersonDAO {
    public void correctPerson(Person person);
    public Person getPerson(int id);
    public List getPersons(int page);
    public List searchPersons(String surname, String name, String patronymic, String age, String[] ageRange,
                              String sex, String nationality, String maritalStatus, String country, String city,
                              String street, String home, String flat, String index);

    public void deletePerson(String [] id);
    public int getPreviousPage(int currentPage);
    public int getNextPage(int currentPage);
}

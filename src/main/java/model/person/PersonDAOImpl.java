package model.person;

import logger.LoggerApplication;
import model.ConnectToDB;
import param.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class PersonDAOImpl implements PersonDAO {
    private static PersonDAOImpl ourInstance = new PersonDAOImpl();

    public static PersonDAOImpl getInstance() {
        return ourInstance;
    }

    private PersonDAOImpl() {
    }

    @Override
    public void correctPerson(Person person) {
        if (person.getId() == 0) {
            ConnectToDB.getInstance().correctPerson(person,RequestParams.CREATE);
        } else {
            ConnectToDB.getInstance().correctPerson(person,RequestParams.CORRECT);
        }
    }

    @Override
    public Person getPerson(int id) {
        Person person = ConnectToDB.getInstance().getPerson(id);
        return person;
    }

    @Override
    public List getPersons(int page) {
        List persons = ConnectToDB.getInstance().getPersons();
        List personForPage = new ArrayList<Person>();

        for (int i = (page - 1) * 20; i < page * 20 && i < persons.size(); i++) {
            personForPage.add(persons.get(i));
        }
        return personForPage;
    }

    @Override
    public List searchPersons(String surname, String name, String patronymic, String age, String[] ageRange,
                              String sex, String nationality, String maritalStatus, String country, String city,
                              String street, String home, String flat, String index) {
        List persons;
        int homeInt;
        int flatInt;
        int ageInt;
        try {
            homeInt = Integer.parseInt(home);
        } catch (NumberFormatException e) {
            homeInt = 0;
            LoggerApplication.getInstance().setError("incorrect home number");
        }
        try {
            flatInt = Integer.parseInt(flat);
        } catch (NumberFormatException e) {
            flatInt = 0;
            LoggerApplication.getInstance().setError("incorrect flat number");
        }
        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            ageInt = 0;
            LoggerApplication.getInstance().setError("incorrect age");
        }
        int ageRangeType = -1;
        if (ageRange != null) {
            switch (ageRange.length) {
                case 1:
                    if (ageRange[0].equals(RequestParams.MORE_THAN)) {
                        ageRangeType = 1;
                    } else {
                        ageRangeType = 0;
                    }
                    break;
                case 2:
                    ageRangeType = 2;
                    break;
            }
        }
        if(ageInt == 0){
            ageRangeType = 2;
        }
        persons = ConnectToDB.getInstance().searchPerson(surname, name, patronymic, ageInt, ageRangeType, sex, nationality, maritalStatus,
                country, city, street, homeInt, flatInt, index);
        return persons;
    }

    public void deletePerson(String [] id){
        List idInt = new ArrayList();
        for (String temp: id){
            idInt.add(Integer.parseInt(temp));
        }
        ConnectToDB.getInstance().deletePerson(idInt);

    }

    @Override
    public int getPreviousPage(int currentPage) {
        if (currentPage != 1) {
            return currentPage - 1;
        }
        return 0;
    }

    @Override
    public int getNextPage(int currentPage) {
        if (currentPage != ConnectToDB.getInstance().getPersons().size() / 20 + 1) {
            return currentPage + 1;
        }
        return 0;
    }


}

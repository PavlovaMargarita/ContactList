package model;

import model.company.Company;
import model.country.Country;
import model.maritalStatus.MaritalStatus;
import model.person.Person;
import model.phone.Phone;
import param.RequestParams;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Margarita on 21.07.2014.
 */
public class ConnectToDB {
    private static ConnectToDB ourInstance = new ConnectToDB();

    public static ConnectToDB getInstance() {
        return ourInstance;
    }

    private ConnectToDB() {
    }

    public Person getPerson(int id) {
        Person person = new Person();
        String selectPerson = "select persons.id, persons.surname,persons.name, persons.patronymic, persons.dateOfBirth, " +
                "persons.sex, persons.nationality, maritalstatus.maritalStatus,  persons.webSite, persons.email," +
                "company.company, country.country, persons.city, persons.street, persons.home, persons.flat, persons.cityIndex " +
                "from persons join maritalstatus on persons.maritalStatus = maritalstatus.id " +
                "join company on persons.company = company.id join country on persons.country = country.id where persons.id = ?";
        List phones = new ArrayList<Phone>();
        String selectPhone = "select * from phone where idPerson = ?";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            PreparedStatement statement = connect.prepareStatement(selectPerson);
            statement.setInt(1, id);
            ResultSet resultPerson = statement.executeQuery();
            List persons = new ArrayList<Person>();
            setPersonData(persons, resultPerson);
            person = (Person) persons.get(0);
            statement = connect.prepareStatement(selectPhone);
            statement.setInt(1, id);
            ResultSet resultPhone = statement.executeQuery();
            while (resultPhone.next()) {
                Phone phone = new Phone();
                phone.setId(resultPhone.getInt(RequestParams.ID));
                phone.setCountryCode(resultPhone.getInt(RequestParams.COUNTY_CODE));
                phone.setOperatorCode(resultPhone.getInt(RequestParams.OPERATOR_CODE));
                phone.setPhoneNumber(resultPhone.getInt(RequestParams.PHONE_NUMBER));
                phone.setPhoneType(resultPhone.getString(RequestParams.PHONE_TYPE));
                phone.setComment(resultPhone.getString(RequestParams.COMMENT));
                phone.setIdPerson(resultPhone.getShort(RequestParams.ID_PERSON));
                phones.add(phone);
            }
            person.setPhone(phones);
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    public List getPersons() {
        List persons = new ArrayList<Person>();
//        String selectPerson = "select * from persons";
        String selectPerson = "select persons.id, persons.surname,persons.name, persons.patronymic, persons.dateOfBirth, " +
                "persons.sex, persons.nationality, maritalstatus.maritalStatus,  persons.webSite, persons.email," +
                "company.company, country.country, persons.city, persons.street, persons.home, persons.flat, persons.cityIndex " +
                "from persons join maritalstatus on persons.maritalStatus = maritalstatus.id " +
                "join company on persons.company = company.id join country on persons.country = country.id";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            Statement statement = connect.createStatement();
            ResultSet resultPerson = statement.executeQuery(new String(selectPerson));
            setPersonData(persons, resultPerson);
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void correctPerson(Person person, String task) {
        String insertPerson = "insert into persons(surname, name, patronymic, dateOfBirth, sex, nationality," +
                "maritalStatus, webSite, email, company, country, city, street, home, flat, cityIndex) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String correctPerson = "update persons set surname = ?, name = ?, patronymic = ?," +
                "dateOfBirth = ?, sex = ?, nationality = ?, maritalStatus = ?," +
                "webSite = ?, email = ?, company = ?, country = ?, city = ?, street = ?," +
                "home = ?, flat = ?, cityindex = ? where id = ?";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            PreparedStatement statement;
            if (task.equals(RequestParams.CREATE)) {
                statement = connect.prepareStatement(insertPerson);
            } else {
                statement = connect.prepareStatement(correctPerson);
                statement.setInt(17, person.getId());
            }
            statement.setString(1, person.getSurname());
            statement.setString(2, person.getName());
            statement.setString(3, person.getPatronymic());
            statement.setString(4, person.getDateOfBirth());
            statement.setString(5, person.getSex());
            statement.setString(6, person.getNationality());
            statement.setString(7, person.getMaritalStatus());
            statement.setString(8, person.getWebSite());
            statement.setString(9, person.getEmail());
            statement.setString(10, person.getCompany());
            statement.setString(11, person.getCountry());
            statement.setString(12, person.getCity());
            statement.setString(13, person.getStreet());
            statement.setInt(14, person.getHome());
            statement.setInt(15, person.getFlat());
            statement.setString(16, person.getIndex());
            statement.executeUpdate();
            int id = 0;
            if (task.equals(RequestParams.CREATE)) {
                ResultSet a = statement.getGeneratedKeys();
                a.next();
                id = a.getInt(1);
            } else {
                id = person.getId();
            }
            List <Phone> phones = person.getPhone();
            for (Phone phone : phones) {
                phone.setIdPerson(id);
            }
            String deletePhone = "delete from phone where idPerson = ?";
            statement = connect.prepareStatement(deletePhone);
            statement.setInt(1, id);
            statement.executeUpdate();

            String insertPhone = "insert into phone(countryCode, operatorCode, phoneNumber, phoneType," +
                    "comment, idPerson) VALUES (?,?,?,?,?,?)";
            statement = connect.prepareStatement(insertPhone);
            for (Phone phone : phones) {


                statement.setInt(1, phone.getCountryCode());
                statement.setInt(2, phone.getOperatorCode());
                statement.setInt(3, phone.getPhoneNumber());
                statement.setString(4, phone.getPhoneType());
                statement.setString(5, phone.getComment());
                statement.setInt(6, id);
                statement.executeUpdate();
            }


            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List searchPerson(String surname, String name, String patronymic, int age, int ageRange,
                             String sex, String nationality, String maritalStatus, String country, String city,
                             String street, int home, int flat, String index) {
        List persons = new ArrayList<Person>();
//        String searchPerson = "select * from persons where surname like ? and name like ?" +
//                "and patronymic like ? and sex like ? and nationality like ? and maritalStatus like ?" +
//                "and country like ? and city like ? and street like ? and CASE WHEN ? = 0 THEN home >= 0 ELSE home like ? END " +
//                "and CASE WHEN ? = 0 THEN flat >= 0 ELSE flat like ? END and cityindex like ?" +
//                "and case when ? = 1 then TIMESTAMPDIFF(YEAR,dateOfBirth,CURDATE()) >= ?  " +
//                "when ? = 0 then TIMESTAMPDIFF(YEAR,dateOfBirth,CURDATE()) <= ?   " +
//                "when ? = -1 then TIMESTAMPDIFF(YEAR,dateOfBirth,CURDATE()) = ?  " +
//                "else dateOfBirth end";
        String searchPerson = "select persons.id, persons.surname,persons.name, persons.patronymic, persons.dateOfBirth, " +
                "persons.sex, persons.nationality, maritalstatus.maritalStatus,  persons.webSite, persons.email," +
                "company.company, country.country, persons.city, persons.street, persons.home, persons.flat, persons.cityIndex " +
                "from persons join maritalstatus on persons.maritalStatus = maritalstatus.id " +
                "join company on persons.company = company.id join country on persons.country = country.id " +
                "where  persons.surname like ? and  persons.name like ? and  persons.patronymic like ? and  persons.sex like ? " +
                "and persons.nationality like ? and persons.maritalStatus like ? and persons.country like ?" +
                "and persons.city like ? and persons.street like ? and CASE WHEN ? = 0 THEN persons.home >= 0 ELSE persons.home like ? END " +
                "and CASE WHEN ? = 0 THEN persons.flat >= 0 ELSE persons.flat like ? END and persons.cityindex like ? " +
                "and case when ? = 1 then TIMESTAMPDIFF(YEAR,persons.dateOfBirth,CURDATE()) >= ?  " +
                "when ? = 0 then TIMESTAMPDIFF(YEAR,persons.dateOfBirth,CURDATE()) <= ?   " +
                "when ? = -1 then TIMESTAMPDIFF(YEAR,persons.dateOfBirth,CURDATE()) = ?  " +
                "else persons.dateOfBirth end";

        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            PreparedStatement statement = connect.prepareStatement(searchPerson);
            statement.setString(1, "%" + surname + "%");
            statement.setString(2, "%" + name + "%");
            statement.setString(3, "%" + patronymic + "%");
            statement.setString(4, "%" + sex + "%");
            statement.setString(5, "%" + nationality + "%");
            statement.setString(6, "%" + maritalStatus + "%");
            statement.setString(7, "%" + country + "%");
            statement.setString(8, "%" + city + "%");
            statement.setString(9, "%" + street + "%");
            statement.setInt(10, home);
            statement.setInt(11, home);
            statement.setInt(12, flat);
            statement.setInt(13, flat);
            statement.setString(14, "%" + index + "%");
            statement.setInt(15, ageRange);
            statement.setInt(16, age);
            statement.setInt(17, ageRange);
            statement.setInt(18, age);
            statement.setInt(19, ageRange);
            statement.setInt(20, age);

            ResultSet resultPerson = statement.executeQuery();
            setPersonData(persons, resultPerson);
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void deletePerson(List<Integer> id) {
        String deletePerson = "delete from persons where persons.id = ?";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            PreparedStatement statement = connect.prepareStatement(deletePerson);
            for (int temp : id) {
                statement.setInt(1, temp);
                statement.executeUpdate();
            }

            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setPersonData(List persons, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt(RequestParams.ID));
                person.setSurname(resultSet.getString(RequestParams.SURNAME));
                person.setName(resultSet.getString(RequestParams.NAME));
                person.setPatronymic(resultSet.getString(RequestParams.PATRONYMIC));
                StringTokenizer st = new StringTokenizer(resultSet.getString(RequestParams.DATE_OF_BIRTH), "-");
                StringBuilder dateOfBirth = new StringBuilder();
                while(st.hasMoreTokens()){
                    dateOfBirth.insert(0,st.nextToken());
                    if(st.hasMoreTokens()){
                        dateOfBirth.insert(0, "-");
                    }
                }
                person.setDateOfBirth(dateOfBirth.toString());
                person.setSex(resultSet.getString(RequestParams.SEX));
                person.setNationality(resultSet.getString(RequestParams.NATIONALITY));
                person.setMaritalStatus(resultSet.getString(RequestParams.MARITAL_STATUS));
                person.setWebSite(resultSet.getString(RequestParams.WEB_SITE));
                person.setEmail(resultSet.getString(RequestParams.EMAIL));
                person.setCompany(resultSet.getString(RequestParams.COMPANY));
                person.setCountry(resultSet.getString(RequestParams.COUNTRY));
                person.setCity(resultSet.getString(RequestParams.CITY));
                person.setStreet(resultSet.getString(RequestParams.STREET));
                person.setHome(resultSet.getInt(RequestParams.HOME));
                person.setFlat(resultSet.getInt(RequestParams.FLAT));
                person.setIndex(resultSet.getString(RequestParams.CITY_INDEX));
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List getCountries() {
        List countries = new ArrayList<Country>();
        String selectCounty = "select * from country";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            Statement statement = connect.createStatement();
            ResultSet resultCountry = statement.executeQuery(new String(selectCounty));
            while (resultCountry.next()) {
                Country country = new Country();
                country.setId(resultCountry.getInt(RequestParams.ID));
                country.setCountry(resultCountry.getString(RequestParams.COUNTRY));
                countries.add(country);
            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public List getMaritalStatus() {
        List maritalStatuses = new ArrayList<MaritalStatus>();
        String selectMaritalStatus = "select * from maritalstatus";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            Statement statement = connect.createStatement();
            ResultSet resultMaritalStatus = statement.executeQuery(new String(selectMaritalStatus));
            while (resultMaritalStatus.next()) {
                MaritalStatus maritalStatus = new MaritalStatus();
                maritalStatus.setId(resultMaritalStatus.getInt(RequestParams.ID));
                maritalStatus.setMaritalStatus(resultMaritalStatus.getString(RequestParams.MARITAL_STATUS));
                maritalStatuses.add(maritalStatus);
            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return maritalStatuses;
    }

    public List getCompanies() {
        List companies = new ArrayList<Company>();
        String selectCompany = "select * from company";
        try {
            Class.forName(RequestParams.bundle.getString("urlDriver"));
            Connection connect = DriverManager.getConnection(RequestParams.bundle.getString("urlDB"),
                    RequestParams.bundle.getString("userDB"), RequestParams.bundle.getString("passwordDB"));
            Statement statement = connect.createStatement();
            ResultSet resultCompany = statement.executeQuery(new String(selectCompany));
            while (resultCompany.next()) {
                Company company = new Company();
                company.setId(resultCompany.getInt(RequestParams.ID));
                company.setCompany(resultCompany.getString(RequestParams.COMPANY));
                companies.add(company);
            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return companies;
    }
}

package com.mycompany.xerp.web;

import com.mycompany.xerp.domain.*;
import eu.paasword.annotations.PaaSwordPEP;
import eu.paasword.jpa.PaaSwordEntityHandler;
import eu.paasword.jpa.exceptions.ProxyInitializationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by smantzouratos on 22/09/16.
 */
@Controller
public class WebController {

    private final Logger logger = Logger.getLogger(WebController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        if (isUserLoggedIn()) {
            return "redirect:/" + dashboard(model);
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        if (isUserLoggedIn()) {

            SecurityContextHolder.getContext().setAuthentication(null);

            return "redirect:/" + login(model);
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", "addUserObject");
        if (isUserLoggedIn()) {
            return "redirect:/" + dashboard(model);
        }
        return "login";
    }

    // Error
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String reset() {
        return "reset";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    //    @PaaSwordPEP("notMobile")
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());
//        model.addAttribute("allClients", clientService.findAll());

        List<City> listOfCities = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> cities = entityHandler.findAll(City.class);
            for (Iterator<Object> it = cities.iterator(); it.hasNext(); ) {
                City city = (City) it.next();
                Country country = (Country) entityHandler.findOne(city.getCountry().getId(), Country.class);
                city.setCountry(country);
                listOfCities.add(city);
//                logger.info("ID: " + city.getId() + ", Name: " + city.getName() + ", Country: " + city.getCountry().getName());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("allCities", listOfCities);

        return "cities";
    }

//    @RequestMapping(value = "/client", method = RequestMethod.GET)
//    public String client(Model model) {
//        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
//        model.addAttribute("currentUser", getCurrentUser());
////        model.addAttribute("allClients", clientService.findAll());
//
//        return "clients";
//    }
//
//    @RequestMapping(value = "/client/add", method = RequestMethod.GET)
//    public String clientAdd(Model model) {
//        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
//        model.addAttribute("currentUser", getCurrentUser());
//        return "addClient";
//    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String cities(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());
        List<City> listOfCities = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> cities = entityHandler.findAll(City.class);
            for (Iterator<Object> it = cities.iterator(); it.hasNext(); ) {
                City city = (City) it.next();
                Country country = (Country) entityHandler.findOne(city.getCountry().getId(), Country.class);
                city.setCountry(country);
                listOfCities.add(city);
//                logger.info("ID: " + city.getId() + ", Name: " + city.getName() + ", Country: " + city.getCountry().getName());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("allCities", listOfCities);

        return "cities";
    }

    @RequestMapping(value = "/city/add", method = RequestMethod.GET)
    public String cityAdd(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        List<Country> listOfCountries = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> countries = entityHandler.findAll(Country.class);
            for (Iterator<Object> it = countries.iterator(); it.hasNext(); ) {
                Country country = (Country) it.next();
                listOfCountries.add(country);
//                logger.info("ID: " + country.getId() + ", Name: " + country.getName() + ", Inhabitants: " + country.getInhabitants());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("countries", listOfCountries);

        return "addCity";
    }

    @PaaSwordPEP(value = "webAccessPolicy")
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public String countries(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());
        List<Country> listOfCountries = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List countries = entityHandler.findAll(Country.class);
            for (Iterator it = countries.iterator(); it.hasNext(); ) {
                Country country = (Country) it.next();
                listOfCountries.add(country);
//                logger.info("ID: " + country.getId() + ", Name: " + country.getName() + ", Inhabitants: " + country.getInhabitants());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("allCountries", listOfCountries);

        return "countries";
    }

    @RequestMapping(value = "/country/add", method = RequestMethod.GET)
    public String countryAdd(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        return "addCountry";
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String faculties(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        List<Faculty> listOfFaculties = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> faculties = entityHandler.findAll(Faculty.class);
            for (Iterator<Object> it = faculties.iterator(); it.hasNext(); ) {
                Faculty faculty = (Faculty) it.next();
                listOfFaculties.add(faculty);
//                logger.info("ID: " + faculty.getId() + ", Name: " + faculty.getName());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("allFaculties", listOfFaculties);

        return "faculties";
    }

    @RequestMapping(value = "/faculty/add", method = RequestMethod.GET)
    public String facultyAdd(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());
        return "addFaculty";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String students(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        List<Student> listOfStudents = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> students = entityHandler.findAll(Student.class);
            for (Iterator<Object> it = students.iterator(); it.hasNext(); ) {
                Student student = (Student) it.next();
                University university = (University) entityHandler.findOne(student.getUniversity().getId(), University.class);
                Faculty faculty = (Faculty) entityHandler.findOne(student.getFaculty().getId(), Faculty.class);
                student.setUniversity(university);
                student.setFaculty(faculty);
                listOfStudents.add(student);
//                logger.info("ID: " + student.getId() + ", Name: " + student.getName() + ", Surname: " + student.getSurname() + ", Birth date: " + student.getBirth_date() + ", Gender: " + student.isGender() + ", Semester: " + student.getSemester() + ", Grade: " + student.getGrade() + ", University: " +  student.getUniversity().getName() + ", Faculty: " + student.getFaculty().getName());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("allStudents", listOfStudents);

        return "students";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public String studentAdd(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        List<University> listOfUniversities = new ArrayList<>();
        List<Faculty> listOfFaculties = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> universities = entityHandler.findAll(University.class);
            for (Iterator<Object> it = universities.iterator(); it.hasNext(); ) {
                University university = (University) it.next();
                City city = (City) entityHandler.findOne(university.getCity().getId(), City.class);
                university.setCity(city);
                listOfUniversities.add(university);
//                logger.info("ID: " + university.getId() + ", Name: " + university.getName() + ", Lecture Halls: " + university.getNumber_of_lecutre_halls() + ", City: " + university.getCity().getName());
            }


            List<Object> faculties = entityHandler.findAll(Faculty.class);
            for (Iterator<Object> it = faculties.iterator(); it.hasNext(); ) {
                Faculty faculty = (Faculty) it.next();
                listOfFaculties.add(faculty);
//                logger.info("ID: " + faculty.getId() + ", Name: " + faculty.getName());
            }


        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("universities", listOfUniversities);
        model.addAttribute("faculties", listOfFaculties);

        return "addStudent";
    }

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public String universities(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        List<University> listOfUniversities = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> universities = entityHandler.findAll(University.class);
            for (Iterator<Object> it = universities.iterator(); it.hasNext(); ) {
                University university = (University) it.next();
                City city = (City) entityHandler.findOne(university.getCity().getId(), City.class);
                university.setCity(city);
                listOfUniversities.add(university);
//                logger.info("ID: " + university.getId() + ", Name: " + university.getName() + ", Lecture Halls: " + university.getNumber_of_lecutre_halls() + ", City: " + university.getCity().getName());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("allUniversities", listOfUniversities);

        return "universities";
    }

    @RequestMapping(value = "/university/add", method = RequestMethod.GET)
    public String universityAdd(Model model) {
        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
        model.addAttribute("currentUser", getCurrentUser());

        List<City> listOfCities = new ArrayList<>();

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            List<Object> cities = entityHandler.findAll(City.class);
            for (Iterator<Object> it = cities.iterator(); it.hasNext(); ) {
                City city = (City) it.next();
                Country country = (Country) entityHandler.findOne(city.getCountry().getId(), Country.class);
                city.setCountry(country);
                listOfCities.add(city);
//                logger.info("ID: " + city.getId() + ", Name: " + city.getName() + ", Country: " + city.getCountry().getName());
            }

        } catch (ProxyInitializationException e) {
            e.printStackTrace();
        }

        model.addAttribute("cities", listOfCities);

        return "addUniversity";
    }

//    @PaaSwordPEP("specificLocationPolicy")
//    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
//    public String invoices(Model model) {
//        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
//        model.addAttribute("currentUser", getCurrentUser());
////        model.addAttribute("allInvoices", invoiceService.findAll());
//        return "invoices";
//    }
//
//    @RequestMapping(value = "/invoice/add", method = RequestMethod.GET)
//    public String invoiceAdd(Model model) {
//        logger.log(Level.INFO, "Success login for user: {0} , with userID: {1} and role: {2}", new Object[]{getCurrentUser().getUsername()});//, getCurrentUser().getId(), getCurrentUser().getRole()});
//        model.addAttribute("currentUser", getCurrentUser());
//        return "addInvoice";
//    }

    /*
     *  Help Methods
     */

    /**
     * Retrieve the current logged-in user
     *
     * @return An instance of CurrentUser object
     */
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Get current logged-in role
     *
     * @return Role name
     */
    public static String getCurrentRole() {
//        return getCurrentUser().getAuthorities()..getRole();
        return "";
    }

    /**
     * Check if a user is logged-in
     *
     * @return True if user is logged-in, otherwise returns false
     */
    private boolean isUserLoggedIn() {
        try {
            getCurrentUser();
        } catch (ClassCastException ex) {
            return false;
        }
        return true;
    }

}

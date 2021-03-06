package com.mycompany.xerp.web;

import com.mycompany.xerp.domain.*;
import com.mycompany.xerp.response.BasicResponseCode;
import com.mycompany.xerp.response.XERPRestResponse;
import com.mycompany.xerp.trasferObjects.*;
import eu.paasword.jpa.PaaSwordEntityHandler;
import eu.paasword.jpa.exceptions.NotAValidPaaSwordEntityException;
import eu.paasword.jpa.exceptions.ProxyInitializationException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by smantzouratos on 22/09/16.
 */
@RestController
@RequestMapping("/api/v1")
public class RestAPIController {

    private final Logger logger = Logger.getLogger(RestAPIController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
        return "Hello World!!!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/city")
    public XERPRestResponse addCity(@RequestBody TCity tCity) {

       try {

           PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

           // Adding City
           Country country = new Country();
           country.setId(tCity.getCountryID());

           City city = new City();
           city.setName(tCity.getName());
           city.setCountry(country);

           entityHandler.save(city);

           return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.CITY_ADDED, Optional.empty());

       } catch (NotAValidPaaSwordEntityException | ProxyInitializationException e) {
           logger.severe(e.getMessage());
           return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.CITY_ERROR, Optional.empty());

       }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/city/{id}")
    public XERPRestResponse deleteCity(@PathVariable long id) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            entityHandler.delete(id, City.class);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.CITY_DELETED, Optional.empty());

        } catch (ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.CITY_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/country")
    public XERPRestResponse addCountry(@RequestBody TCountry tCountry) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            // Adding Country
            Country country = new Country();
            country.setName(tCountry.getName());
            country.setInhabitants(Integer.valueOf(tCountry.getInhabitants()));

            entityHandler.save(country);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.COUNTRY_ADDED, Optional.empty());

        } catch (NotAValidPaaSwordEntityException | ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.COUNTRY_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/country/{id}")
    public XERPRestResponse deleteCountry(@PathVariable long id) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            entityHandler.delete(id, Country.class);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.COUNTRY_DELETED, Optional.empty());

        } catch (ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.COUNTRY_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/faculty")
    public XERPRestResponse addFaculty(@RequestBody TFaculty tFaculty) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            // Adding Faculty
            Faculty faculty = new Faculty();
            faculty.setName(tFaculty.getName());

            entityHandler.save(faculty);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.FACULTY_ADDED, Optional.empty());

        } catch (NotAValidPaaSwordEntityException | ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.FACULTY_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/faculty/{id}")
    public XERPRestResponse deleteFaculty(@PathVariable long id) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            entityHandler.delete(id, Faculty.class);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.FACULTY_DELETED, Optional.empty());

        } catch (ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.FACULTY_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/student")
    public XERPRestResponse addStudent(@RequestBody TStudent tStudent) {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            // Adding Student
            University university = new University();
            university.setId(tStudent.getUniversityID());

            Faculty faculty = new Faculty();
            faculty.setId(tStudent.getFacultyID());

            Student student = new Student();
            student.setName(tStudent.getName());
            student.setSurname(tStudent.getSurname());
            student.setBirth_date(formatter.parse(tStudent.getDateOfBirth()));
            student.setGender(tStudent.isGender());
            student.setGrade(tStudent.getGrade());
            student.setSemester(tStudent.getSemester());
            student.setFaculty(faculty);
            student.setUniversity(university);

            entityHandler.save(student);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.STUDENT_ADDED, Optional.empty());

        } catch (ProxyInitializationException | NotAValidPaaSwordEntityException | ParseException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.STUDENT_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/student/{id}")
    public XERPRestResponse deleteStudent(@PathVariable long id) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            entityHandler.delete(id, Student.class);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.STUDENT_DELETED, Optional.empty());

        } catch (ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.STUDENT_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/university")
    public XERPRestResponse addUniversity(@RequestBody TUniversity tUniversity) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            // Adding University
            City city = new City();
            city.setId(tUniversity.getCityID());

            University university = new University();
            university.setName(tUniversity.getUniversityName());
            university.setNumber_of_lecutre_halls(tUniversity.getNumberOfLectureHalls());
            university.setCity(city);

            entityHandler.save(university);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.UNIVERSITY_ADDED, Optional.empty());

        } catch (ProxyInitializationException | NotAValidPaaSwordEntityException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.UNIVERSITY_ERROR, Optional.empty());

        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/university/{id}")
    public XERPRestResponse deleteUniversity(@PathVariable long id) {

        try {

            PaaSwordEntityHandler entityHandler = PaaSwordEntityHandler.getInstance();

            entityHandler.delete(id, University.class);

            return new XERPRestResponse(BasicResponseCode.SUCCESS, Message.UNIVERSITY_DELETED, Optional.empty());

        } catch (ProxyInitializationException e) {
            logger.severe(e.getMessage());
            return new XERPRestResponse(BasicResponseCode.EXCEPTION, Message.UNIVERSITY_ERROR, Optional.empty());

        }

    }

    private final static class Message {

        final static String CITY_ADDED = "City has been added successfully";
        final static String CITY_DELETED = "City has been deleted successfully";
        final static String CITY_ERROR = "Error during handling City. Please try again!";
        final static String COUNTRY_ADDED = "Country has been added successfully";
        final static String COUNTRY_DELETED = "Country has been deleted successfully";
        final static String COUNTRY_ERROR = "Error during handling Country. Please try again!";
        final static String FACULTY_ADDED = "Faculty has been added successfully";
        final static String FACULTY_DELETED = "Faculty has been deleted successfully";
        final static String FACULTY_ERROR = "Error during handling Faculty. Please try again!";
        final static String STUDENT_ADDED = "Student has been added successfully";
        final static String STUDENT_DELETED = "Student has been deleted successfully";
        final static String STUDENT_ERROR = "Error during handling Student. Please try again!";
        final static String UNIVERSITY_ADDED = "University has been added successfully";
        final static String UNIVERSITY_DELETED = "University has been deleted successfully";
        final static String UNIVERSITY_ERROR = "Error during handling University. Please try again!";
    }

}

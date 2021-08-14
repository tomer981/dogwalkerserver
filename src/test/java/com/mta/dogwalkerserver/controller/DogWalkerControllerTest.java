package com.mta.dogwalkerserver.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.models.Gender;
import com.mta.dogwalkerserver.models.Image;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DogWalkerController.class})
@ExtendWith(SpringExtension.class)
public class DogWalkerControllerTest {
    @Autowired
    private DogWalkerController dogWalkerController;

    @MockBean
    private DogWalkerRepo dogWalkerRepo;

    @Test
    public void testDeleteDogWalkerV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        doNothing().when(this.dogWalkerRepo).delete((DogWalker) any());
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/DogWalker/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted 1"));
    }

    @Test
    public void testDeleteDogWalkerV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        doNothing().when(this.dogWalkerRepo).delete((DogWalker) any());
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/DogWalker/");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted 1"));
    }

    @Test
    public void testFindDogWalkersAroundMe() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/DogWalker/DogWalkersAroundMe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testGetAddressV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogWalker/address/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"city\":\"Oxford\",\"streetName\":\"Street Name\",\"houseNumber\":10,\"zipCode\":1,\"geoHashLocation\":\"Geo Hash"
                                        + " Location\"}"));
    }

    @Test
    public void testGetAddressV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogWalker/address/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"city\":\"Oxford\",\"streetName\":\"Street Name\",\"houseNumber\":10,\"zipCode\":1,\"geoHashLocation\":\"Geo Hash"
                                        + " Location\"}"));
    }

    @Test
    public void testGetContactV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogWalker/contact/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetContactV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogWalker/contact/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetDogWalkerByIdV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogWalker/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe"
                                        + "@example.org\",\"aboutMyself\":\"About Myself\",\"birthDay\":0,\"phone\":\"4105551212\",\"gender\":\"MALE\","
                                        + "\"hourSalary\":1,\"image_Id\":{\"image_Id\":123,\"content\":\"QUFBQUFBQUE=\",\"name\":\"Name\",\"location\":\"Location"
                                        + "\"},\"id\":1}"));
    }

    @Test
    public void testGetDogWalkerByIdV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Image image = new Image();
        image.setLocation("Location");
        image.setContent("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setImage_Id(123L);

        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName("Doe");
        dogWalker.setPassword("iloveyou");
        dogWalker.setEmail("jane.doe@example.org");
        dogWalker.setContact(new HashSet<DogOwner>());
        dogWalker.setId(1);
        dogWalker.setBirthDay(mock(Date.class));
        dogWalker.setFirstName("Jane");
        dogWalker.setAboutMyself("About Myself");
        dogWalker.setUserName("janedoe");
        dogWalker.setAddress_Id(address);
        dogWalker.setPhone("4105551212");
        dogWalker.setHourSalary(1);
        dogWalker.setGender(Gender.MALE);
        dogWalker.setImage_Id(image);
        Optional<DogWalker> ofResult = Optional.<DogWalker>of(dogWalker);
        when(this.dogWalkerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogWalker/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogWalkerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe"
                                        + "@example.org\",\"aboutMyself\":\"About Myself\",\"birthDay\":0,\"phone\":\"4105551212\",\"gender\":\"MALE\","
                                        + "\"hourSalary\":1,\"image_Id\":{\"image_Id\":123,\"content\":\"QUFBQUFBQUE=\",\"name\":\"Name\",\"location\":\"Location"
                                        + "\"},\"id\":1}"));
    }
}


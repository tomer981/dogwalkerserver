package com.mta.dogwalkerserver.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mta.dogwalkerserver.models.Address;
import com.mta.dogwalkerserver.models.Dog;
import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.models.Gender;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;

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

@ContextConfiguration(classes = {DogOwnerController.class})
@ExtendWith(SpringExtension.class)
public class DogOwnerControllerTest {
    @Autowired
    private DogOwnerController dogOwnerController;

    @MockBean
    private DogOwnerRepo dogOwnerRepo;

    @Test
    public void testDeleteDogOwnerV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        doNothing().when(this.dogOwnerRepo).delete((DogOwner) any());
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/DogOwner/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted 1"));
    }

    @Test
    public void testDeleteDogOwnerV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        doNothing().when(this.dogOwnerRepo).delete((DogOwner) any());
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/DogOwner/");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted 1"));
    }

    @Test
    public void testFindDogWalkerAroundMe() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/DogOwner/DogOwnersAroundMe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.dogOwnerController)
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

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogOwner/address/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
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

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogOwner/address/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
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

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogOwner/contact/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
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

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogOwner/contact/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetDogOwnerByIdV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogOwner/id/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe"
                                        + "@example.org\",\"aboutMyself\":\"About Myself\",\"birthDay\":0,\"phone\":\"4105551212\",\"gender\":\"MALE\",\"dog_Id"
                                        + "\":{\"dog_Id\":123,\"name\":\"Name\",\"breed\":\"Breed\",\"vaccinated\":true,\"castrated\":true,\"socialPeople\":true"
                                        + ",\"socialDog\":true,\"gender\":\"MALE\"},\"id\":1}"));
    }

    @Test
    public void testGetDogOwnerByIdV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogOwner/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane.doe"
                                        + "@example.org\",\"aboutMyself\":\"About Myself\",\"birthDay\":0,\"phone\":\"4105551212\",\"gender\":\"MALE\",\"dog_Id"
                                        + "\":{\"dog_Id\":123,\"name\":\"Name\",\"breed\":\"Breed\",\"vaccinated\":true,\"castrated\":true,\"socialPeople\":true"
                                        + ",\"socialDog\":true,\"gender\":\"MALE\"},\"id\":1}"));
    }

    @Test
    public void testGetDogV1() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DogOwner/getDogByDogOwner/id/{id}",
                1);
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"dog_Id\":123,\"name\":\"Name\",\"breed\":\"Breed\",\"vaccinated\":true,\"castrated\":true,\"socialPeople\":true,"
                                        + "\"socialDog\":true,\"gender\":\"MALE\"}"));
    }

    @Test
    public void testGetDogV2() throws Exception {
        Address address = new Address();
        address.setStreetName("Street Name");
        address.setZipCode(1);
        address.setCity("Oxford");
        address.setGeoHashLocation("Geo Hash Location");
        address.setHouseNumber(10);

        Dog dog = new Dog();
        dog.setCastrated(true);
        dog.setSocialPeople(true);
        dog.setVaccinated(true);
        dog.setName("Name");
        dog.setGender(Gender.MALE);
        dog.setDog_Id(123);
        dog.setSocialDog(true);
        dog.setBreed("Breed");

        DogOwner dogOwner = new DogOwner();
        dogOwner.setLastName("Doe");
        dogOwner.setUserName("janedoe");
        dogOwner.setAddress_Id(address);
        dogOwner.setPassword("iloveyou");
        dogOwner.setEmail("jane.doe@example.org");
        dogOwner.setContact(new HashSet<DogWalker>());
        dogOwner.setId(1);
        dogOwner.setPhone("4105551212");
        dogOwner.setGender(Gender.MALE);
        dogOwner.setBirthDay(mock(Date.class));
        dogOwner.setFirstName("Jane");
        dogOwner.setAboutMyself("About Myself");
        dogOwner.setDog_Id(dog);
        Optional<DogOwner> ofResult = Optional.<DogOwner>of(dogOwner);
        when(this.dogOwnerRepo.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/DogOwner/getDogByDogOwner/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.dogOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"dog_Id\":123,\"name\":\"Name\",\"breed\":\"Breed\",\"vaccinated\":true,\"castrated\":true,\"socialPeople\":true,"
                                        + "\"socialDog\":true,\"gender\":\"MALE\"}"));
    }
}


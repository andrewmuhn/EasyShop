package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Profile;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySqlProfileDaoTest extends BaseDaoTestClass {
    private MySqlProfileDao dao;

    @BeforeEach
    public void setup()
    {
        dao = new MySqlProfileDao(dataSource);
    }

    @Test
    public void getById_shouldReturn_theCorrectUser()
    {
        // arrange
        int userId = 1;
        Profile expected = new Profile()
        {{
            setUserId(1);
            setFirstName("Joe");
            setLastName("Joesephus");
            setPhone("800-555-1234");
            setEmail("joejoesephus@email.com");
            setAddress("789 Oak Avenue");
            setCity("Dallas");
            setState("TX");
            setZip("75051");
        }};

        // act
        var actual = dao.getByUserId(userId);

        // assert
        assertEquals(expected.getAddress(), actual.getAddress(), "Because I tried to get the profile object with userId 1 from the database.");
    }

    @Test
    public void updateUserById_shouldUpdate_theCorrectUser() {
        Profile updatedProfile = new Profile() {{
            setUserId(1);
            setFirstName("Joseph");
            setLastName("Johnson");
            setPhone("800-555-1234");
            setEmail("joejoesephus@email.com");
            setAddress("789 Oak Avenue");
            setCity("Dallas");
            setState("TX");
            setZip("75051");
        }};

        // act
        dao.update(updatedProfile, 1);
        var actual = dao.getByUserId(1);

        // assert
        assertEquals(updatedProfile.getLastName(), actual.getLastName(), "Last name should be updated for userId 1.");
    }

    @Test
    public void createProfile_shouldCreate_newProfile() {
        Profile newProfile = new Profile() {{
            setUserId(3);
            setFirstName("Mack");
            setLastName("Mcdonald");
            setPhone("800-555-1234");
            setEmail("macm@email.com");
            setAddress("789 Mcdonald Avenue");
            setCity("Richmond");
            setState("VA");
            setZip("23219");
        }};

        // act
        dao.create(newProfile);
        var actual = dao.getByUserId(1);

        // assert
        assertEquals(updatedProfile.getLastName(), actual.getLastName(), "Last name should be updated for userId 1.");
    }

}

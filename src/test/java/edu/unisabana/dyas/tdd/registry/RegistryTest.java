package edu.unisabana.dyas.tdd.registry;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class RegistryTest {
     private Registry registry;

    @Before
    public void setUp() {
        registry = new Registry();
    }

    @Test
    public void validateRegistryResult_ValidPerson() {
        Person person = new Person("John Doe", 12345, 30, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void validateRegistryResult_DeadPerson() {
        Person person = new Person("Jane Doe", 54321, 25, Gender.FEMALE, false);
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void validateRegistryResult_UnderagePerson() {
        Person person = new Person("Jack Doe", 54322, 16, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void validateRegistryResult_InvalidId_Negative() {
        Person person = new Person("Jim Doe", -1, 25, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateRegistryResult_InvalidId_Zero() {
        Person person = new Person("Jill Doe", 0, 25, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateRegistryResult_DuplicateId() {
        Person person1 = new Person("Jake Doe", 67890, 25, Gender.MALE, true);
        Person person2 = new Person("Julia Doe", 67890, 28, Gender.FEMALE, true);

        registry.registerVoter(person1);
        RegisterResult result = registry.registerVoter(person2);

        assertEquals(RegisterResult.DUPLICATED, result);

    }
}

package hu.tmx.model;

import hu.tmx.customException.BigamyException;
import hu.tmx.customException.InvalidAgeException;
import hu.tmx.customException.SameSexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private static Person testPersonJohn17;
    private static Person testPersonGeorge18;
    private static Person testPersonBill19;
    private static Person testPersonClara17;
    private static Person testPersonKate18;
    private static Person testPersonBeatrix19;

    @BeforeEach
    void setUp() {
        testPersonJohn17 = new Person("John", true, 17);
        testPersonGeorge18 = new Person("George", true, 18);
        testPersonBill19 = new Person("Bill", true, 19);
        testPersonClara17 = new Person("Clara", false, 17);
        testPersonKate18 = new Person("Kate", false, 18);
        testPersonBeatrix19 = new Person("Beatrix", false, 19);
    }

    @Test
    void GetName_TestPersonNameIsJohn_ReturnJohn() {
        assertEquals("John", testPersonJohn17.getName());
    }

    @Test
    void IsMan_TestPersonIsAMan_ReturnTrue() {
        assertTrue(testPersonJohn17.isMan());
    }

    @Test
    void IsMan_TestPersonIsNotAMan_ReturnFalse() {
        assertFalse(testPersonClara17.isMan());
    }

    @Test
    void GetAge_TestPersonAge17_Return17() {
        assertEquals(17, testPersonJohn17.getAge());
    }

    @Test
    void Marry_BothPersonAreMen_ThrowSameSexException() {
        Throwable exception = assertThrows(SameSexException.class,
                () -> testPersonJohn17.marry(testPersonGeorge18));
        assertEquals("Same sex marriage is not allowed", exception.getMessage());
    }

    @Test
    void Marry_BothPersonAreWomen_ThrowSameSexException() {
        Throwable exception = assertThrows(SameSexException.class,
                () -> testPersonClara17.marry(testPersonKate18));
        assertEquals("Same sex marriage is not allowed", exception.getMessage());
    }

    @Test
    void Marry_ManLessThan18AndWomanLessThan18_ThrowInvalidAgeException() {
        Throwable exception = assertThrows(InvalidAgeException.class,
                () -> testPersonJohn17.marry(testPersonClara17));
        assertEquals("Marriage is not allowed under age 18", exception.getMessage());
    }

    @Test
    void Marry_ManLessThan18AndWomanExactly18_ThrowInvalidAgeException() {
        Throwable exception = assertThrows(InvalidAgeException.class,
                () -> testPersonJohn17.marry(testPersonKate18));
        assertEquals("Marriage is not allowed under age 18", exception.getMessage());
    }

    @Test
    void Marry_ManLessThan18AndWomanMoreThan18_ThrowInvalidAgeException() {
        Throwable exception = assertThrows(InvalidAgeException.class,
                () -> testPersonJohn17.marry(testPersonBeatrix19));
        assertEquals("Marriage is not allowed under age 18", exception.getMessage());
    }

    @Test
    void Marry_ManExactly18AndWomanLessThan18_ThrowInvalidAgeException() {
        Throwable exception = assertThrows(InvalidAgeException.class,
                () -> testPersonGeorge18.marry(testPersonClara17));
        assertEquals("Marriage is not allowed under age 18", exception.getMessage());
    }

    @Test
    void Marry_ManMoreThan18AndWomanLessThan18_ThrowInvalidAgeException() {
        Throwable exception = assertThrows(InvalidAgeException.class,
                () -> testPersonBill19.marry(testPersonClara17));
        assertEquals("Marriage is not allowed under age 18", exception.getMessage());
    }

    @Test
    void Marry_NotSameSexBothValidAgeBothAreMarried_ThrowBigamyException() {
        testPersonBill19.marry(testPersonKate18);
        testPersonBeatrix19.marry(testPersonGeorge18);

        Throwable exception = assertThrows(BigamyException.class,
                () -> testPersonBill19.marry(testPersonBeatrix19));
        assertEquals("Bigamy is not allowed", exception.getMessage());
    }

    @Test
    void Marry_NotSameSexBothValidAgeManIsMarried_ThrowBigamyException() {
        testPersonBill19.marry(testPersonKate18);

        Throwable exception = assertThrows(BigamyException.class,
                () -> testPersonBill19.marry(testPersonBeatrix19));
        assertEquals("Bigamy is not allowed", exception.getMessage());
    }

    @Test
    void Marry_NotSameSexBothValidAgeWomanIsMarried_ThrowBigamyException() {
        testPersonBeatrix19.marry(testPersonGeorge18);

        Throwable exception = assertThrows(BigamyException.class,
                () -> testPersonBill19.marry(testPersonBeatrix19));
        assertEquals("Bigamy is not allowed", exception.getMessage());
    }

    @Test
    void Marry_ManExactly18AndWomanExactly18BothAreNotMarried_ReturnTrue() {
        assertAll(
                () -> assertTrue(testPersonGeorge18.marry(testPersonKate18)),
                () -> assertTrue(testPersonGeorge18.isMarried()),
                () -> assertTrue(testPersonKate18.isMarried())
        );
    }

    @Test
    void Marry_ManExactly18AndWomanMoreThan18BothAreNotMarried_ReturnTrue() {
        assertAll(
                () -> assertTrue(testPersonGeorge18.marry(testPersonBeatrix19)),
                () -> assertTrue(testPersonGeorge18.isMarried()),
                () -> assertTrue(testPersonBeatrix19.isMarried())
        );
    }

    @Test
    void Marry_ManMoreThan18AndWomanExactly18BothAreNotMarried_ReturnTrue() {
        assertAll(
                () -> assertTrue(testPersonBill19.marry(testPersonKate18)),
                () -> assertTrue(testPersonBill19.isMarried()),
                () -> assertTrue(testPersonKate18.isMarried())
        );
    }

    @Test
    void Marry_ManMoreThan18AndWomanMoreThan18BothAreNotMarried_ReturnTrue() {
        assertAll(
                () -> assertTrue(testPersonBill19.marry(testPersonBeatrix19)),
                () -> assertTrue(testPersonBill19.isMarried()),
                () -> assertTrue(testPersonBeatrix19.isMarried())
        );
    }
}
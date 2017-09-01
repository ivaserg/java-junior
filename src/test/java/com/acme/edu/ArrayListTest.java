package com.acme.edu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;


public class ArrayListTest {
    @Before
    public void setUp() {

    }

    @Test(timeout = 1_000)
    public void shouldContainElementAndSizeIncrementedWhenElementAdd() { //BDD, Cohesion, Contract
        //region Fixture | Arrange | Given
        ArrayList sut = new ArrayList();
        //endregion

        //region Act | When
        sut.add(new Object());
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size());
        assertThat(sut.size()) //Fluent API interface
                .isEqualTo(1)
                .isPositive();
        //endregion
    }

    @Test
    public void shouldRelyOnElementsToStringWhenToString() {
        ArrayList sut = new ArrayList();
        Object stub = mock(Object.class);
        Object stub2 = mock(Object.class);
        sut.add(stub);
        sut.add(stub2);
        // When region
        when(stub.toString()).thenReturn("test string 1");
        when(stub2.toString()).thenReturn("test string 2");


        assertThat(sut.toString())
                .isEqualTo("[test string 1, test string 2]");
    }

    @Test
    public void shouldReturnTheSameElementWhenAdded() {
        ArrayList sut = new ArrayList();
        String s = "Hello";
        String s2 = s;
        sut.add(s);

        assertThat(sut.get(0)).isEqualTo(s2);

    }

    @Test
    public void shouldRelyOnElementsToStringWhenToString2() {
        ArrayList sut = new ArrayList();
        Object mock = mock(Object.class);
        sut.add(mock);

//        when(mock.toString()).thenReturn("object");

//        verify(mock, times(1)).toString();
    }
}

package com.acme.edu.iteration03;

import com.acme.edu.LoggerFacade;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import static java.lang.System.lineSeparator;

@Ignore
public class LoggerFacadeTest implements SysoutCaptureAndAssertionAbility {
//    //region given
//    @Before
//    public void setUpSystemOut() throws IOException {
//        resetOut();
//        captureSysout();
//    }
//
//    @After
//    public void tearDown() {
//        resetOut();
//    }
//    //endregion
//
//
////    TODO: implement Logger solution to match specification as tests
//
//    @Test
//    public void shouldLogIntegersArray() throws IOException {
//        //region when
//        LoggerFacade.log(new int[] {-1, 0, 1});
//        LoggerFacade.endLogSession();
//        //endregion
//
//        //region then
//        assertSysoutContains(
//            "primitives array: {-1, 0, 1}"
//        );
//        //endregion
//    }
//
//    @Test
//    public void shouldLogIntegersMatrix() throws IOException {
//        //region when
//        LoggerFacade.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
//        LoggerFacade.endLogSession();
//        //endregion
//
//        //region then
//        assertSysoutContains(
//            "primitives matrix: {" + lineSeparator() +
//                "{-1, 0, 1}" + lineSeparator() +
//                "{1, 2, 3}" + lineSeparator() +
//                "{-1, -2, -3}" + lineSeparator() +
//            "}" + lineSeparator()
//        );
//        //endregion
//    }
//
//    @Test
//    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
//        //region when
//        LoggerFacade.log(new int[][][][] {{{{0}}}});
//        LoggerFacade.endLogSession();
//        //endregion
//
//        //region then
//        assertSysoutContains(
//            "primitives multimatrix: {" + lineSeparator() +
//                "{" + lineSeparator() + "{" +  lineSeparator() + "{" +
//                    "0" +
//                "}" +  lineSeparator() +"}" +  lineSeparator() +"}" +  lineSeparator() +
//            "}" +  lineSeparator()
//        );
//        //endregion
//    }
//
//    @Test
//    public void shouldLogStringsWithOneMethodCall() throws IOException {
//        //region when
//        LoggerFacade.log("str1", "string 2", "str 3");
//        LoggerFacade.endLogSession();
//        //endregion
//
//        //region then
//        assertSysoutContains("str1" +  lineSeparator() +"string 2" +  lineSeparator() +"str 3");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogIntegersWithOneMethodCall() throws IOException {
//        //region when
//        LoggerFacade.log(-1, 0, 1, 3);
//        LoggerFacade.endLogSession();
//        //endregion
//
//        //region then
//        assertSysoutContains("3");
//        //endregion
//    }
//
//    @Test
//    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
//        //region when
//        LoggerFacade.log(1);
//        LoggerFacade.log("str");
//        LoggerFacade.log(Integer.MAX_VALUE - 10);
//        LoggerFacade.log(11);
//        LoggerFacade.endLogSession();
//        //endregion
//
//        //region then
//        assertSysoutContains("1");
//        assertSysoutContains("str");
//        assertSysoutContains(String.valueOf(Integer.MAX_VALUE - 10));
//        assertSysoutContains("11");
//        //endregion
//    }

}
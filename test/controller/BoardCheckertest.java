package controller;

import java.io.File;

import model.IModel;
import model.ModelImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import errors.InvalidMapException;


public class BoardCheckertest {
    
    private IModel model;
    
    private BoardChecker checker;

    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass()
        throws Exception {

    }

    @Before
    public void setUp()
        throws Exception {

    }

    @After
    public void tearDown()
        throws Exception {

    }

    @Test
    public void testIsConnected() throws InvalidMapException {
        model = new ModelImpl("cartes"+File.separatorChar+"normal.map");
        checker = new BoardChecker(model.getBoard());
        Assert.assertTrue(checker.isConnected());
    }
    
    @Test(expected=InvalidMapException.class)
    public void testIsConnectedProblem() throws InvalidMapException {
        model = new ModelImpl("cartes"+File.separatorChar+"problem.map");
        checker = new BoardChecker(model.getBoard());
        Assert.assertFalse(checker.isConnected());
    }
    
    @Test
    public void testRemainingGumCount() throws InvalidMapException {
        model = new ModelImpl("cartes"+File.separatorChar+"small.map");
        checker = new BoardChecker(model.getBoard());
        System.out.println(checker.remainingGumsCount());
    }

}

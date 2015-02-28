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
    public void testIsConnected() {
        model = new ModelImpl("cartes"+File.separatorChar+"normal.map");
        checker = new BoardChecker(model.getBoard());
        Assert.assertTrue(checker.isConnected());
    }
    
    @Test
    public void testIsConnectedProblem() {
        model = new ModelImpl("cartes"+File.separatorChar+"problem.map");
        checker = new BoardChecker(model.getBoard());
        Assert.assertFalse(checker.isConnected());
    }

}

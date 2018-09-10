package com.thor.tech.test;

import com.thor.tech.file.paser.FileParser;
import com.thor.tech.file.paser.model.DataModel;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestFileParser {

    private final String testFile="src/main/resources/test.txt";


    @Test
    public void testSetDataModel() {
        final String  currentFileLine="Raymond,Pellicano";
        final DataModel expectedData = new DataModel("Raymond","Pellicano");
        final FileParser fileParser = new FileParser(testFile);
        final DataModel resultData = fileParser.populateModel(currentFileLine);
        assertEquals(expectedData.getName(), resultData.getName());
        assertEquals(expectedData.getSurname(), resultData.getSurname());
    }


    @Test
    public void testIsCurrentLineAHeader() {

        final String  currentFileLine="#name,surname";
        final Boolean expectedData = true;
        final FileParser fileParser = new FileParser(testFile);
        final Boolean resultData = fileParser.isCurrentLineAHeader(currentFileLine);
        assertEquals(expectedData, resultData);
    }

    @Test
    public void integrationTest() {

        final ArrayList<DataModel> expectedData = new ArrayList<DataModel>();
        expectedData.add(new DataModel("Tobias","Eduardo"));
        expectedData.add(new DataModel("Raymond","Pellicano"));

        final FileParser fileParser = new FileParser(testFile);

        final ArrayList<DataModel> resultData = fileParser.populateDataModel();
        assertEquals(expectedData.size(), resultData.size());
        assertEquals(expectedData, resultData);


    }

}
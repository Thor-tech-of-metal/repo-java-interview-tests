package com.thor.tech.test.parser;

import com.thor.tech.parser.FileParser;
import com.thor.tech.parser.model.DataModel;
import com.thor.tech.test.utils.ServiceLayerTest;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestFileParser {

    private final URL url = ServiceLayerTest.class.getClassLoader().getResource("inputExamples/testOneEntry.csv");
    private final String testFile = url.getFile();


    @Test
    public void testSetDataModel() {
        final String  currentFileLine="SIG 4536,RED,EASY RIDER";
        final DataModel expectedData = new DataModel("SIG 4536","RED","EASY RIDER");
        final FileParser fileParser = new FileParser(testFile);
        final DataModel resultData = fileParser.populateModel(currentFileLine);
        assertEquals(expectedData.getPlateNumber(), resultData.getPlateNumber());
        assertEquals(expectedData.getColour(), resultData.getColour());
    }


    @Test
    public void testIsCurrentLineAHeader() {

        final String  currentFileLine="Plate number,Colour,Make";
        final Boolean expectedData = true;
        final FileParser fileParser = new FileParser(testFile);
        final Boolean resultData = fileParser.isCurrentLineAHeader(currentFileLine);
        assertEquals(expectedData, resultData);
    }

    @Test
    public void integrationTest() {

        final ArrayList<DataModel> expectedData = new ArrayList<DataModel>();
        expectedData.add(new DataModel("SIG 4536","RED","EASY RIDER"));
        expectedData.add(new DataModel("FDV 542T",DataModel.notFound,DataModel.notFound));

        final FileParser fileParser = new FileParser(testFile);

        final ArrayList<DataModel> resultData = fileParser.populateDataModel();
        assertEquals(expectedData.size(), resultData.size());
        assertEquals(expectedData, resultData);

    }

}
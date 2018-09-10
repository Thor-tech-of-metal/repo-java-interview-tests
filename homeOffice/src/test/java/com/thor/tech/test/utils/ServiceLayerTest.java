package com.thor.tech.test.utils;

import com.thor.tech.model.FileMimeTypes;
import com.thor.tech.model.FileModel;
import com.thor.tech.utils.ServiceLayer;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceLayerTest {

    private final URL url = ServiceLayerTest.class.getClassLoader().getResource("inputExamples");
    private final String inputDirectory = url.getFile();

    @Test
    public void testGetCSVFiles() {

        final int expectedFiles = 4;
        final List<FileModel> results = ServiceLayer.getAllFilesPathsInADirectory(inputDirectory, FileMimeTypes.CSV);
        final String expectedFileExtension = "csv";
        assertEquals(expectedFiles, results.size());
        assertFileModel(expectedFileExtension,FileMimeTypes.CSV,results);
    }

    @Test
    public void testGetXMLFiles() {

        final int expectedFiles = 5;
        final String expectedFileExtension = "xml";
        final List<FileModel> results = ServiceLayer.getAllFilesPathsInADirectory(inputDirectory, FileMimeTypes.XML);
        assertEquals(expectedFiles, results.size());
        assertFileModel(expectedFileExtension,FileMimeTypes.XML,results);
    }

    private void assertFileModel(String expectedFileExtension,FileMimeTypes expectedMimeType ,List<FileModel> results ){

        for (FileModel fileModel: results){

            assertEquals(fileModel.getExtension(), expectedFileExtension);
            assertEquals(fileModel.getMimeType(), expectedMimeType);
        }
    }

}

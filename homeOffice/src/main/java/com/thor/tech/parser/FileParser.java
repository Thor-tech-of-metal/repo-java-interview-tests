package com.thor.tech.parser;


import com.thor.tech.parser.model.DataModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

    private final String filePath;

    public FileParser(String filePath) {
        this.filePath= filePath;
    }

    public DataModel populateModel(String currentFileLine) {
        final String[] line = currentFileLine.split(",");
        return new DataModel(line[0], line[1],line[2]);
    }

    public Boolean isCurrentLineAHeader(String currentFileLine) {
        return currentFileLine.contains("Plate number") && currentFileLine.contains("Colour") && currentFileLine.contains("Make") ;
    }

    /**
     * This is method is going to parse all files from the input directory and produce a list od data models.
     * @return ArrayList<DataModel> is the result that contains all populated model.
     */
    public ArrayList<DataModel> populateDataModel(){

        final ArrayList<DataModel> result = new ArrayList<DataModel> ();

        try {

            final File file = new File(filePath);
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String readLine = "";

            while ((readLine = bufferedReader.readLine()) != null) {
                if (!isCurrentLineAHeader(readLine)) {
                    result.add(populateModel(readLine));
                }

            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

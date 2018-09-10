package com.thor.tech.file.paser;

import com.thor.tech.file.paser.model.DataModel;

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
        return new DataModel(line[0], line[1]);
    }

    public Boolean isCurrentLineAHeader(String currentFileLine) {
        return currentFileLine.contains("#");
    }

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

package com.thor.tech.model;

import java.util.HashMap;
import java.util.Map;

public class FileModelTypes {


    private static final  Map<FileMimeTypes, String> fileExtensionToMimeTypes = new HashMap<FileMimeTypes, String>()
    {
        {
            put(FileMimeTypes.CSV, "csv");
            put(FileMimeTypes.JSON, "json");
            put(FileMimeTypes.TEXT, "txt");
            put(FileMimeTypes.XML, "xml");
        }
    };

    private static final  Map<String,FileMimeTypes> fileExtensionToMimeType = new HashMap<String, FileMimeTypes>()
    {
        {
            put("csv",FileMimeTypes.CSV);
            put("json",FileMimeTypes.JSON);
            put("txt",FileMimeTypes.TEXT);
            put("xml",FileMimeTypes.XML);
        }
    };

    public static  FileMimeTypes getMimeTypeBaseOnFileExtension(String fileExtension){

        return fileExtensionToMimeType.getOrDefault(fileExtension,FileMimeTypes.NOT_SUPPORTED);
    }

    public static  String getFileExtensionBaseOnMimeType(FileMimeTypes fileMimeType){

        return fileExtensionToMimeTypes.getOrDefault(fileMimeType,FileMimeTypes.NOT_SUPPORTED.toString());
    }
}

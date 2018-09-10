package com.thor.tech.utils;

import com.thor.tech.model.FileMimeTypes;
import com.thor.tech.model.FileModel;
import com.thor.tech.model.FileModelTypes;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceLayer {

    /**
     * This method is going to retrieve all files which match with the filter passed as parameter from a directory.
     * @param path this is the input directory path.
     * @param fileMimeType this a mime type file filter.
     * @return <code>List<FileModel></code> which contains all files that match with the filter passed as parameter.
     */
    public static List<FileModel> getAllFilesPathsInADirectory(String path, FileMimeTypes fileMimeType) {

        final String inputFileExtension = FileModelTypes.getFileExtensionBaseOnMimeType(fileMimeType);

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {

            return paths.map(

                    element -> {
                        final File file = new File(element.toString());
                        if (!file.isDirectory()) {
                            if(file.getName().contains(inputFileExtension)) { return buildFileModel(file); } else { return null; }
                        }
                        return null;

                    }
            ).filter(element -> element != null).collect(Collectors.toList());

        } catch (IOException iOException) {

            iOException.printStackTrace();
            return null;
        }
    }

    public static FileModel buildFileModel (File file){

        return  new FileModel(
                file.getAbsolutePath(),
                file.length(),
                FilenameUtils.getExtension(file.getName()),
                FileModelTypes.getMimeTypeBaseOnFileExtension(FilenameUtils.getExtension(file.getName()))
        );
    }
}

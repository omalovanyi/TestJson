package com.luxoft.omalovanyi.reader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maoleks on 6/10/2016.
 */

@Service
public class FolderReader {

    @Value("${FolderName}")
    private String folderName;

    @Value("${DefaultFolderName}")
    private String defaultFolderName;

    @Value("${Extension}")
    private String extension;

    public List<File> getListFiles() {
        File folder = new File("".equals(folderName) ? defaultFolderName : folderName);
        return Arrays.asList(folder.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return (name.toUpperCase().endsWith("." + extension.toUpperCase()) && new File(dir, name).isFile());
                    }
                })
        );
    }

}






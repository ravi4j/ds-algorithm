package com.rs.ds.file;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ravisharma on 9/28/2016.
 */
public class SortContentOfFile {

    private Path path;

    public SortContentOfFile(Path path){
        this.path = path;
    }


    public SortedMap<Integer , List<String>> sortFileContent() {
        SortedMap<Integer, List<String>> map = Collections.emptySortedMap();
        try {
            map = Files.lines(path).collect(
                    Collectors.groupingBy(l -> Integer.valueOf(l.split(",")[1]), TreeMap::new, Collectors.toList()));
        } catch(IOException ioEx){
            ioEx.printStackTrace();
        }
        return map;
    }

    public Path getPath(){
        return path;
    }

}

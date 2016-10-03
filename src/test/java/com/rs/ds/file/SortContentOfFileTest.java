package com.rs.ds.file;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by ravisharma on 9/28/2016.
 */
public class SortContentOfFileTest {

    private SortContentOfFile sortContentOfFile;

    @Before
    public void setUp(){
        Path path = Paths.get("C:/RaviSharma/Learning-Notes/ds-alogrithm/src/main/resources/file" , "SortContentOfFile-Input.txt");
        sortContentOfFile =  new SortContentOfFile(path);
    }

    @Test
    public void testSortContent(){
        SortedMap<Integer , List<String>> map = sortContentOfFile.sortFileContent();
        assertEquals(map.firstKey() , Integer.valueOf(11));
        assertEquals(map.lastKey() , Integer.valueOf(23));
    }


}

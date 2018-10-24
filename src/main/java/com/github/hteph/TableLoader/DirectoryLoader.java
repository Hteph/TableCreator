package com.github.hteph.TableLoader;

import com.github.hteph.domain.Table;
import com.github.hteph.domain.TableArchive;
import com.github.hteph.domain.TableLine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Mikael Hansson {@literal <mailto:mikael.hansson@so4it.com/>}
 */
public class DirectoryLoader {

    // Uses Files.walk method
    public static void collectAllFiles(String path){
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        readContent(filePath);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void readContent(Path filePath) throws IOException{

        List<String> fileLinesList = Files.readAllLines(filePath, StandardCharsets.ISO_8859_1);
        Table table = null;

        for(String line:fileLinesList){
            if(line.startsWith("#")){
                if(table != null) TableArchive.addToArchive(table);
                table = new Table(line.substring(line.indexOf("#")+1,line.indexOf(":")).trim(),line.substring(line.indexOf(":")+1).trim() );
            }else if(table != null && line.indexOf(" ")>0 &&line.substring(0,line.indexOf(" ")).chars().allMatch( Character::isDigit )){
                table.addTableLine(TableLine.builder()
                                            .withNumber(Integer.valueOf(line.substring(0, line.indexOf(" "))))
                                            .withDescription(line.substring(line.indexOf(" ")))
                                            .build());
            }

        }
        TableArchive.addToArchive(table);
        //TODO parse the lines, create objects containing the tables, put in tableArchive HashMap
        //#-> Table name #TableTitel:TableDescription
        // number -> table instance
    }

    private void readNewTable(List<String> fileLinesList) {
    }

}

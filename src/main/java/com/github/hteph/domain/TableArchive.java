package com.github.hteph.domain;

import java.util.*;

/**
 * @author Mikael Hansson {@literal <mailto:mikael.hansson@so4it.com/>}
 */
public class TableArchive {

    private static Map<String, Table> archive;

    static {
      archive = new HashMap<>();
    }

    public static void addToArchive(Table table){

        if(table != null) archive.put(table.getName().toUpperCase(),table);

    }

    public static Table getTable(String name){

        if(name == null || name.isEmpty()) return getRandomTable();

        Table table = archive.get(name.toUpperCase());

        if(table == null) System.out.println("Error in finding table: "+name);
        return table;
    }

    private static Table getRandomTable(){

        List<String> keySet = new ArrayList<>(archive.keySet());

        int number = (int) (Math.random()*keySet.size());

        return getTable(keySet.get(number));
    }

    public static Set<String> listAllTables() {

        Set<String> test = archive.keySet();

        return new TreeSet<>(archive.keySet());
    }
}

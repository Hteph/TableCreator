package com.github.hteph.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Mikael Hansson {@literal <mailto:mikael.hansson@so4it.com/>}
 */
public class Table {

    private Map<Integer,TableLine> tableLines;
    private String name;
    private String description;

    public Table(String name, String description) {

        this.name = name;
        this.description = description;

        tableLines = new HashMap<Integer, TableLine>();
    }

    public String getTableLine(Integer number) {
        TableLine tableLine;
        boolean found = false;

        do {
            tableLine = tableLines.get(number);
            if(tableLine !=null) found = true;
            if(number>0) number--;
            else return getFirst().getDescription();
        }while (found);

        return description+" "+tableLine.getDescription();
    }

    public String getRandomLine() {

        return getDescription()+" "+getLine().getDescription();
    }

    private TableLine getFirst() {

        TreeSet<Integer> keyList =new TreeSet<>(tableLines.keySet());

        return tableLines.get(keyList.first());

    }

    public void addTableLine(TableLine tableLine) {

        tableLines.put(tableLine.getNumber(),tableLine);
    }

    public String getName() {

        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRandomLineWithoutHeader() {

        return getLine().getDescription();
    }

    private TableLine getLine() {
        TableLine tableLine;
        boolean found = false;
        TreeSet<Integer> keyList = new TreeSet<>(tableLines.keySet());
        Integer number = (int) (keyList.size() * Math.random());

        do {
            tableLine = tableLines.get(number);
            if (tableLine != null) found = true;
            if (number > 0) number--;
            else return getFirst();
        } while (!found);

        return tableLine;
    }
}
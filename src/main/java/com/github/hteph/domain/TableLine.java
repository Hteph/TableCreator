package com.github.hteph.domain;

/**
 * @author Mikael Hansson {@literal <mailto:mikael.hansson@so4it.com/>}
 */
public class TableLine {

    private int number;
    private String description;

    public TableLine(int number, String description) {

        this.number = number;
        this.description = description;
    }

    public TableLine(Builder builder) {

        this.number = builder.number;
        this.description = builder.description;
    }

    public int getNumber() {

        return number;
    }

    public String getDescription() {

        if(description == null) return "";

        return checkForOtherTableInvokations();
    }

    private String checkForOtherTableInvokations() {

        while(description.contains("<")) {

            int start = description.indexOf("<");
            int stop = description.indexOf(">");
            String tableName = description.substring(start + 1, stop).trim();
            description = (description.substring(0,start)+" "
                            + TableArchive.getTable(tableName).getRandomLineWithoutHeader()+" "
                            +description.substring(stop+1))
                            .trim().replaceAll(" +", " ");
        }

        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int number;
        private String description;

        private Builder(){

        }

        public Builder withNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TableLine build() {
            return new TableLine(number, description);
        }
    }
}

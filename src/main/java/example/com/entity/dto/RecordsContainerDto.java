package example.com.entity.dto;

import example.com.entity.Record;

import java.util.List;

public class RecordsContainerDto {
    private final List<Record> records;
    private final int numberDoneRecords;
    private final int numberActiveRecords;

    public RecordsContainerDto (List<Record> records, int numberDoneRecords, int numberActiveRecords) {
        this.records = records;
        this.numberDoneRecords = numberDoneRecords;
        this.numberActiveRecords = numberActiveRecords;
    }

    public List<Record> getRecords () {
        return records;
    }

    public int getNumberDoneRecords () {
        return numberDoneRecords;
    }

    public int getNumberActiveRecords () {
        return numberActiveRecords;
    }
}

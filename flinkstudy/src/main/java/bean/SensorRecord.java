package bean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 温度Bean
 *
 * @Writer ArtisanLS
 * @Date 2023/4/12
 */
public class SensorRecord {
    private String id;
    private Double lastRecord;
    private Double record;
    private LocalDateTime time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(Double lastRecord) {
        this.lastRecord = lastRecord;
    }

    public Double getRecord() {
        return record;
    }

    public void setRecord(Double record) {
        this.record = record;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getTimeEpochMilli() {
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}

package net.xanarey.securitylearn.model.weather;

import lombok.Data;

@Data
public class Coord {
    private double lon;
    private double lat;

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}' + "\n";
    }
}

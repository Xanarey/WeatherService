package net.xanarey.securitylearn.model.weather;

import lombok.Data;

@Data
public class Wind {
    private double speed;
    private int deg;
    private double gust;

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                ", gust=" + gust +
                '}'  + "\n";
    }
}

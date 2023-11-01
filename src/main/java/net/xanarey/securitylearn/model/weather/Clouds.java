package net.xanarey.securitylearn.model.weather;

import lombok.Data;

@Data
public class Clouds {
    private int all;

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}' + "\n";
    }
}

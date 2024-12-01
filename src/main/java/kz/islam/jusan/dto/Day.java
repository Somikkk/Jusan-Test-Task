package kz.islam.jusan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Day {
    @JsonProperty("maxtemp_c")
    private double maxTempC;

    @JsonProperty("mintemp_c")
    private double minTempC;

    private Condition condition;
}

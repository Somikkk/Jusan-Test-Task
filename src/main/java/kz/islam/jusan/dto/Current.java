package kz.islam.jusan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Current {
    @JsonProperty("temp_c")
    private double temp_c;

    @JsonProperty("temp_f")
    private double temp_f;
    private Condition condition;
}

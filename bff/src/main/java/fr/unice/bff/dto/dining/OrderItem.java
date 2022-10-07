package fr.unice.bff.dto.dining;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("howMany")
    private int howMany;
}

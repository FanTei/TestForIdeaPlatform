package TestForIdeaPlatform.TestForIdeaPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class Ticket {
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("origin_name")
    private String originName;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("destination_name")
    private String destinationName;
    @JsonProperty("departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd.MM.yy")
    private Date departureDate;
    @JsonProperty("departure_time")
    private String departureTime;
    @JsonProperty("arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd.MM.yy")
    private Date arrivalDate;
    @JsonProperty("arrival_time")
    private String arrivalTime;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("stops")
    private int stops;
    @JsonProperty("price")
    private int price;
}

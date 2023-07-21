package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    @NotBlank(message = "Country must be present.")
    private String country;

    @NotNull(message = "Number of people must be between 1 and 10 inclusive.")
    @Min(value = 1, message = "Number of people must be between 1 and 10 inclusive.")
    @Max(value = 10, message = "Number of people must be between 1 and 10 inclusive.")
    private Integer personCount;

    @NotNull(message = "Minimum price must be between 1 and 10000 inclusive.")
    @Min(value = 1, message = "Minimum price must be between 1 and 10000 inclusive.")
    @Max(value = 10000, message = "Minimum price must be between 1 and 10000 inclusive.")
    private Double priceMin;

    @NotNull(message = "Maximum price must be between 1 and 10000 inclusive.")
    @Min(value = 1, message = "Maximum price must be between 1 and 10000 inclusive.")
    @Max(value = 10000, message = "Maximum price must be between 1 and 10000 inclusive.")
    private Double priceMax;
    
}

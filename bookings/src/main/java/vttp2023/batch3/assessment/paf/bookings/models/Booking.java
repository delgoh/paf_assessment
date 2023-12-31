package vttp2023.batch3.assessment.paf.bookings.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private String name;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    private Integer stayDuration;

}

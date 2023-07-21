package vttp2023.batch3.assessment.paf.bookings.models;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccomSummary {

    @Field("_id")
    private String accommodationId;

    @Field("address.street")
    private String address;

    private Double price;

    @Field("images.picture_url")
    private String imageUrl;
    
}

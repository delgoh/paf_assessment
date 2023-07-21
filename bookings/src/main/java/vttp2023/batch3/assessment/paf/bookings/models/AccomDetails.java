package vttp2023.batch3.assessment.paf.bookings.models;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccomDetails {

    @Field("_id")
    private String accomId;

    private String description;

    @Field("address.street")
    private String addressStreet;
    
    @Field("address.suburb")
    private String addressSuburb;
    
    @Field("address.country")
    private String addressCountry;

    @Field("images.picture_url")
    private String imageUrl;

    private Double price;

    private String[] amenities;
    
}

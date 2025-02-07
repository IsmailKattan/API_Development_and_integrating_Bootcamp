package com.istanbul_tech.homework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address {
    @Id
    private String id;
    private String city;
    private String district;
    private String street;
    private String no;
    private String zipCode;
    private String userId;


}

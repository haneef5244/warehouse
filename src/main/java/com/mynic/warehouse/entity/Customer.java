package com.mynic.warehouse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customer", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "nric", nullable = false)
    private String nric;

    @Column(name = "residency", nullable = false)
    private String residency;

    @OneToMany(mappedBy = "customerRentalInfoEntities", cascade = CascadeType.ALL)
    private List<RentalInfo> rentalInfoEntities = new ArrayList<>();

}

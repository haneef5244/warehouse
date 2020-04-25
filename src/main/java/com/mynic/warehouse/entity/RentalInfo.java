package com.mynic.warehouse.entity;

import com.mynic.warehouse.obj.req.rentalInfo.RentalInfoDetailsReq;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="RentalInfo", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class RentalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "status")
    private String rentalStatus;

    @Column(name = "charge")
    private Double charge;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carEntity_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "customerRentalInfoEntities", nullable = false)
    private Customer customerRentalInfoEntities;

}

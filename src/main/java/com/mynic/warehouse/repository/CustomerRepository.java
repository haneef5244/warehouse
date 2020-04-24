package com.mynic.warehouse.repository;

import com.mynic.warehouse.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNric(String nric);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Customer c SET c.name=:name, c.phoneNumber=:phoneNumber WHERE c.nric=:nric")
    void updateCustomer(@Param("name") String name,
                        @Param("phoneNumber") String phoneNumber,
                        @Param("nric") String nric);
}

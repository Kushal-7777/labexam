package com.klef.jfsd.exam.CustomerMvcCrud;



import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}


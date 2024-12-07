package com.klef.jfsd.exam.CustomerMvcCrud;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String listCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
        model.addAttribute("customer", customer);
        return "edit-customer";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute("customer") Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
      
        customerRepository.save(existingCustomer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}

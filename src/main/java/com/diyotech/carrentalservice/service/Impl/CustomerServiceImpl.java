package com.diyotech.carrentalservice.service.Impl;

import com.diyotech.carrentalservice.entity.Address;
import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;
import com.diyotech.carrentalservice.repository.CustomerRepository;
import com.diyotech.carrentalservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    // 1. Save customer details
    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // 2. Find customer by Id
    @Override
    public Customer findById(Long id) throws CustomerNotFoundException {
       Optional<Customer> customerOptional = customerRepository.findById(id);
       if(customerOptional.isEmpty()) {
           throw new CustomerNotFoundException("Customer not found having id: "+ id);
       }
       return customerOptional.get();

    }

    // 3. Update customer details by Id
    @Override
    public void updateCustomer(Long id, Customer customer) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with id: "+ id));

        if(customer.getFullName() != null){
            existingCustomer.setFullName(customer.getFullName());
        }
        if(customer.getEmail() != null){
            existingCustomer.setEmail(customer.getEmail());
        }
        if(customer.getUserName() != null){
            existingCustomer.setUserName(customer.getUserName());
        }
        if(customer.getPassword() != null){
            existingCustomer.setPassword(customer.getPassword());
        }
        // Updating the address too
        if(customer.getAddress() != null){
            Address existingAddress = existingCustomer.getAddress();
            Address updatedAddress = customer.getAddress();

            if(updatedAddress.getStreet() != null){
                existingAddress.setStreet(updatedAddress.getStreet());
            }
            if(updatedAddress.getCity() != null){
                existingAddress.setCity(updatedAddress.getCity());
            }
            if(updatedAddress.getState() != null){
                existingAddress.setState(updatedAddress.getState());
            }
            if(updatedAddress.getZip() != null){
                existingAddress.setZip(updatedAddress.getZip());
            }
            if(updatedAddress.getCountry() != null){
                existingAddress.setCountry(updatedAddress.getCountry());
            }
        }
        customerRepository.save(existingCustomer);
    }


    // 4. Delete customer by Id
    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with id: "+ id));
        customerRepository.delete(existingCustomer);
    }


    @Override
    public void addMultiple(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }


}

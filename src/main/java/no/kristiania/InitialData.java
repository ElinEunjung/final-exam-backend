package no.kristiania;

import com.github.javafaker.Faker;
import no.kristiania.services.CustomerAddressService;
import no.kristiania.services.CustomerService;
import no.kristiania.services.OrderService;
import no.kristiania.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class InitialData implements CommandLineRunner {


    private final CustomerService customerService;
    private final CustomerAddressService customerAddressService;
    private final OrderService orderService;
    private final ProductService productService;
    private final Faker faker = Faker.instance();
    Random random = new Random();

    @Autowired
    public InitialData(
            CustomerService customerService,
            CustomerAddressService customerAddressService,
            OrderService orderService,
            ProductService productService
    ) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
        this.orderService = orderService;
        this.productService = productService;
    }

    // TODO: Create fake customers;
    // TODO: Create fake customerAddresses;
    // TODO: Create fake Orders;
    // TODO: Create fake Products;
    // TODO: Create fake OrderProducts;


    // Create Fake customers
//    @Override
//    public void run(String... args) {
//        List<CustomerAddress> customerAddresses = new ArrayList<>();
//        for (int i = 0; i <10; i++) {
//            String website = faker.internet().domainName();
//            String phoneNum = faker.phoneNumber().cellPhone();
//            String regexPattern = "[^0-9]";
//            String numericPhoneNum = phoneNum.replaceAll(regexPattern, "");
//
//            long phoneNumber = Long.parseLong(numericPhoneNum);
//            String name = faker.company().name();
//            CustomerAddress customerAddress = new CustomerAddress(
//                    website,
//                    phoneNumber,
//                    name
//            );
//
//            manufacturerService.createManufacturer(customerAddress);
//            customerAddresses.add(customerAddress);
//        }
//
//        for (int i = 0; i < 50; i++) {
//            String name = faker.name().firstName();
//            String color = faker.color().name();
//            String model = "BIKE-" + String.format("%02d", random.nextInt(100) + 1);
//            int nrInStock = random.nextInt(11);
//            CustomerAddress customerAddress = getRandomManufacturer(customerAddresses);
//
//            bicycleService.createBicycle(new Customer(
//                    name,
//                    color,
//                    model,
//                    nrInStock,
//                    customerAddress
//            ));
//        }
//    }
//
//    private CustomerAddress getRandomManufacturer(List<CustomerAddress> customerAddresses) {
//        int totalNrOfManufacturers = customerAddresses.size();
//        Random random = new Random();
//
//        int randomIndex = random.nextInt(totalNrOfManufacturers);
//        return customerAddresses.get(randomIndex);
//    }
}




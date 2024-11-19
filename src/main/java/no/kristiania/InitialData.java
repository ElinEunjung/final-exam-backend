package no.kristiania;

import com.github.javafaker.Faker;
import no.kristiania.domain.Customer;
import no.kristiania.services.BicycleService;
import no.kristiania.domain.CustomerAddress;
import no.kristiania.services.ManufacturerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class InitialData implements CommandLineRunner {


    private final BicycleService bicycleService;
    private final ManufacturerService manufacturerService;
    private final Faker faker;
    Random random = new Random();

    public InitialData(BicycleService bicycleService, ManufacturerService manufacturerService) {
        this.bicycleService = bicycleService;
        this.manufacturerService = manufacturerService;
        this.faker = Faker.instance();
    }

    // Create Fake dummy bicycles
    @Override
    public void run(String... args) {
        List<CustomerAddress> customerAddresses = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            String website = faker.internet().domainName();
            String phoneNum = faker.phoneNumber().cellPhone();
            String regexPattern = "[^0-9]";
            String numericPhoneNum = phoneNum.replaceAll(regexPattern, "");

            long phoneNumber = Long.parseLong(numericPhoneNum);
            String name = faker.company().name();
            CustomerAddress customerAddress = new CustomerAddress(
                    website,
                    phoneNumber,
                    name
            );

            manufacturerService.createManufacturer(customerAddress);
            customerAddresses.add(customerAddress);
        }

        for (int i = 0; i < 50; i++) {
            String name = faker.name().firstName();
            String color = faker.color().name();
            String model = "BIKE-" + String.format("%02d", random.nextInt(100) + 1);
            int nrInStock = random.nextInt(11);
            CustomerAddress customerAddress = getRandomManufacturer(customerAddresses);

            bicycleService.createBicycle(new Customer(
                    name,
                    color,
                    model,
                    nrInStock,
                    customerAddress
            ));
        }
    }

    private CustomerAddress getRandomManufacturer(List<CustomerAddress> customerAddresses) {
        int totalNrOfManufacturers = customerAddresses.size();
        Random random = new Random();

        int randomIndex = random.nextInt(totalNrOfManufacturers);
        return customerAddresses.get(randomIndex);
    }
}




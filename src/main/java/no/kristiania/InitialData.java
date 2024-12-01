package no.kristiania;

import com.github.javafaker.Faker;
import no.kristiania.models.ProductStatus;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.customers.CustomerAddress;
import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.orders.OrderProduct;
import no.kristiania.repositories.products.Product;
import no.kristiania.services.*;
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

    private final OrderProductService orderProductService;
    private final Faker faker = Faker.instance();
    Random random = new Random();

    @Autowired
    public InitialData(
            CustomerService customerService,
            CustomerAddressService customerAddressService,
            OrderService orderService,
            ProductService productService,
            OrderProductService orderProductService
    ) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
        this.orderService = orderService;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }

    //ToDo: Fix problem with id reset after db migration

    @Override
    public void run(String... args) {
//        customerService.deleteAllCustomers();
//        customerAddressService.deleteAllCustomerAddresses();
//        orderService.deleteAllOrders();
//        orderProductService.deleteAllOrderProducts();
//        productService.deleteAllProducts();

        List<Customer> customers = createFakeCustomers(5);
        List<CustomerAddress> customerAddresses = createFakeCustomerAddresses(customers);
        List<Product> products = createFakeProducts(10);
        List<Order> orders = createFakeOrders(customers, products, 10);
        List<OrderProduct> orderProducts = createFakeOrderProducts(new ArrayList<>(), products, 10);

    }

    public List<Customer> createFakeCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            String name = faker.name().fullName();
            String phoneNum = faker.phoneNumber().cellPhone();
            String regexPattern = "[^0-9]";
            String numericPhoneNum = phoneNum.replaceAll(regexPattern, "");
            String phoneNumber = numericPhoneNum;
            String email = faker.internet().emailAddress();

            Customer customer = new Customer(
                    name,
                    phoneNumber,
                    email
            );

            customerService.createCustomer(customer);
            customers.add(customer);
        }

        return customers;
    }

    public List<CustomerAddress> createFakeCustomerAddresses(List<Customer> customers) {
        List<CustomerAddress> customerAddresses = new ArrayList<>();
        for (long i = 0; i < customers.size(); i++) {
            String address = faker.address().fullAddress();
            Customer customer = customers.get((int) i);

            CustomerAddress customerAddress = new CustomerAddress(
                    address,
                    customer
            );

            customerAddressService.createCustomerAddress(customerAddress);
            customerAddresses.add(customerAddress);
        }

        return customerAddresses;
    }

    public List<Product> createFakeProducts(int count) {

        List<Product> products = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            List<String> candyNames = List.of(
                    "Blue Alligator",
                    "Red Lip",
                    "Mini Hamburger",
                    "Sky Coke",
                    "Bubble Crush",
                    "Minty Love",
                    "Gummy bear Rainbow"
            );
            String candyName = candyNames.get(random.nextInt(candyNames.size()));
            String description = faker.lorem().sentence(10);
            float price = Float.parseFloat(faker.commerce().price(50, 100));
            ProductStatus status = ProductStatus.values()[random.nextInt(ProductStatus.values().length)];
            int quantityInStock = random.nextInt(50) + 1;

            if (status == ProductStatus.OUT_OF_STOCK || status == ProductStatus.DISCONTINUED){
                quantityInStock = 0;
            }

            Product product = new Product(
                    candyName,
                    description,
                    price,
                    status,
                    quantityInStock
            );

            productService.createProduct(product);
            products.add(product);


        }
        return products;
    }

    public List<Order> createFakeOrders(
            List<Customer> customers,
            List<Product> products,
            int count
    ) {
        List<Order> orders = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            String shippingAddress = faker.address().fullAddress();
            float shippingCharge = Float.parseFloat(faker.commerce().price(50, 100));
            boolean isShipped = faker.bool().bool();
            Customer customer = customers.get(random.nextInt(customers.size()));

            // Create new orderProduct
            List<OrderProduct> newOrderProducts = new ArrayList<>();
            float totalPrice = shippingCharge;

            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                Product product = products.get(random.nextInt(products.size()));
                if (product.getQuantityInStock() == 0) continue; // Don't add to order when out of stock

                int productQuantity = random.nextInt(20) + 1;
                if (productQuantity > product.getQuantityInStock()) {
                    productQuantity = product.getQuantityInStock(); // Limit available product quantity
                }

                OrderProduct newOrderProduct = new OrderProduct(
                        null, // Set order later
                        product,
                        productQuantity
                );
                newOrderProducts.add(newOrderProduct);
                totalPrice += product.getPrice() * productQuantity;

                // Update quantityInStock
                product.setQuantityInStock(product.getQuantityInStock() - productQuantity);
            }

            // Create order
            Order order = new Order (
                shippingAddress,
                shippingCharge,
                totalPrice,
                isShipped,
                customer
            );
            orderService.createOrder(order);

            // Connect OrderProduct to Order
            for (OrderProduct newOrderProduct : newOrderProducts) {
                newOrderProduct.setOrder(order);
                orderProductService.createOrderProduct(newOrderProduct);
            }
            orders.add(order);
        }
        orders.forEach(order -> {
            System.out.println("ShippingAddress: " + order.getShippingAddress());
            System.out.println("ShippingCharge: " + order.getShippingCharge());
            System.out.println("TotalPrice: " + order.getTotalPrice());
            System.out.println("isShipped: " + order.isShipped());
            System.out.println("CustomerId: " + order.getCustomer());
            System.out.println("---");
        });
        return orders;
    }

    public List<OrderProduct> createFakeOrderProducts(
            List<Order> orders,
            List<Product> products,
            int count
    ) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            Order order = orders.get(random.nextInt(orders.size()));
            Product product = products.get(random.nextInt(products.size()));
            int productQuantity = product.getQuantityInStock();


            OrderProduct orderProduct = new OrderProduct(
                    order,
                    product,
                    productQuantity
            );

            orderProductService.createOrderProduct(orderProduct);
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }
}
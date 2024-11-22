//package no.kristiania.manufacturer;
//
//import no.kristiania.repository.customer.CustomerAddress;
//import no.kristiania.repository.ManufacturerRepo;
//import no.kristiania.services.ManufacturerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class ManufacturerServiceTest {
//    private final String Website1 = "http://www.acc.no";
//    private final long PhoneNumber1 = 8888888888L;
//    private final String Name1 = "AccManufac";
//
//    private final String Website2 = "http://www.mochicrew.jp";
//    private final long PhoneNumber2 = 777777777L;
//    private final String Name2 = "MochiCrew Global Ltd.";
//
//    private final String Website3 = "http://www.kimchi.co.kr";
//    private final long PhoneNumber3 = 999999999L;
//    private final String Name3 = "Kimchi Korea Ltd";
//
//    private List<CustomerAddress> mockManList = new ArrayList<>();
//
//    @Mock
//    private ManufacturerRepo manufacturerRepo;
//
//    private ManufacturerService manufacturerService;
//
//
//    @BeforeEach
//    public void setUpTest() {
//        MockitoAnnotations.openMocks(this);
//        mockManList.add(new CustomerAddress(
//                Website1, PhoneNumber1, Name1
//        ));
//        mockManList.add(new CustomerAddress(
//                Website2, PhoneNumber2, Name2
//        ));
//        mockManList.add(new CustomerAddress(
//                Website3, PhoneNumber3, Name3
//        ));
//
//        Mockito.when(manufacturerRepo.findAll()).thenReturn(mockManList);
//        manufacturerService = new ManufacturerService(manufacturerRepo);
//    }
//
//    @Test
//    public void testGetAllManufacturers() {
//        List<CustomerAddress> allCustomerAddresses = manufacturerService.getAllManufacturers();
//        assert allCustomerAddresses.equals(mockManList);
//    }
//
//
//}

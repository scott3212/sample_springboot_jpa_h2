package com.bitbuy.test.scott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BitbuytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitbuytestApplication.class, args);
	}

}
//
//@Component
//class BookingCommandLinerunnder implements CommandLineRunner {
//	private static final Logger log = LoggerFactory.getLogger(BookingCommandLinerunnder.class);
//
//	@Override
//	public void run(String... args) throws Exception {
//		Collection<Booking> bookings = this.bookingRepository.findAll();
//		for (Booking b : bookings) {
//			log.info(b.toString());
//		}
//	}
//
//	@Autowired
//	BookingRepository bookingRepository;
//}
//
//interface BookingRepository extends JpaRepository<Booking, Long> {
//	Collection<Booking> findByBookingName(String bookingName);
//}
//
//@RestController
//@RequestMapping("/api")
//class BookingRestController {
//	@GetMapping("/bookings")
//	Collection<Booking> bookings() {
//		return this.bookingRepository.findAll();
//	}
//
//	@Autowired
//	BookingRepository bookingRepository;
//}
//
//@Entity
//@Table(name = "bookings")
//class Booking {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private String bookingName;
//
//	public Booking(String bookingName) {
//		super();
//		this.bookingName = bookingName;
//	}
//
//	public Booking() {
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public void setBookingName(String bookingName) {
//		this.bookingName = bookingName;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public String getBookingName() {
//		return bookingName;
//	}
//
//	@Override
//	public String toString() {
//		return "Booking [id=" + id + ", bookingName=" + bookingName + "]";
//	}
//}
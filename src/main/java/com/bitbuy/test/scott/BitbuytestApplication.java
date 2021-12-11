package com.bitbuy.test.scott;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BitbuytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitbuytestApplication.class, args);
	}

}

@Component
class BookingCommandLinerunnder implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(BookingCommandLinerunnder.class);

	@Override
	public void run(String... args) throws Exception {
		Collection<Booking> bookings = this.bookingRepository.findAll();
		for (Booking b : bookings) {
			log.info(b.toString());
		}
	}

	@Autowired
	BookingRepository bookingRepository;
}

interface BookingRepository extends JpaRepository<Booking, Long> {
	Collection<Booking> findByBookingName(String bookingName);
}

@RestController
@RequestMapping("/api")
class BookingRestController {
	@GetMapping("/bookings")
	Collection<Booking> bookings() {
		return this.bookingRepository.findAll();
	}

	@Autowired
	BookingRepository bookingRepository;
}

@Entity
@Table(name = "bookings")
class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String bookingName;

	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}

	public Booking() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}

	public Long getId() {
		return id;
	}

	public String getBookingName() {
		return bookingName;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingName=" + bookingName + "]";
	}
}
package com.rahul.bookingservice.service;

import com.rahul.bookingservice.client.InventoryServiceClient;
import com.rahul.bookingservice.entity.Customer;
import com.rahul.bookingservice.event.BookingEvent;
import com.rahul.bookingservice.repository.CustomerRepository;
import com.rahul.bookingservice.request.BookingRequest;
import com.rahul.bookingservice.response.BookingResponse;
import com.rahul.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {
    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    @Autowired
    public BookingService(final CustomerRepository customerRepository,
                          final InventoryServiceClient inventoryServiceClient,
                          final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse createBooking(final BookingRequest request){
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if(customer == null){
            throw new RuntimeException("User not found");
        }

        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        log.info("Inventory Response: {}", inventoryResponse);
        if(inventoryResponse.getCapacity() < request.getTicketCount()){
            throw new RuntimeException("Not enough inventory");
        }

        final BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);
        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking event sent to Kafka: {}", bookingEvent);
        return BookingResponse.builder()
            .userId(bookingEvent.getUserId())
            .eventId(bookingEvent.getEventId())
            .ticketCount(bookingEvent.getTicketCount())
            .totalPrice(bookingEvent.getTotalPrice())
            .build();
    }

    private BookingEvent createBookingEvent(final BookingRequest request,
                                             final Customer customer,
                                             final InventoryResponse inventoryResponse) {
        return BookingEvent.builder()
            .userId(customer.getId())
            .eventId(request.getEventId())
            .ticketCount(request.getTicketCount())
            .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
            .build();
    }
}

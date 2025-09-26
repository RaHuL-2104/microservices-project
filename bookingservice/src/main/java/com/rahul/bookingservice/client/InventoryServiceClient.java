package com.rahul.bookingservice.client;

import com.rahul.bookingservice.response.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long eventId){
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }
}

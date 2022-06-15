package com.mycompany.orderservice.controller;

import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
    public class OrderControllerTest {
        @InjectMocks
        private OrderController orderController; //Mockito is going to create a proxy object of BookController and inject it to BookController test file

        @Mock
        private OrderService orderService;


        @Test
        @DisplayName("Test Success Scenario for placing Order")
        void testPlaceOrder(){

            OrderDTO dto =new OrderDTO();
            dto.setUserId(1l);
            OrderDTO placedOrder = new OrderDTO();
            placedOrder.setId(1L);


            Mockito.when(orderService.placeOrder(dto)).thenReturn(placedOrder);
            ResponseEntity<OrderDTO> responseEntity = orderController.placeOrder(dto);
            Assertions.assertNotNull(responseEntity.getBody().getId());
            Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());

        }
    @Test

    void testGetAllOrders()
    {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        OrderDTO orderDTO=new OrderDTO();
        orderDTOList.add(orderDTO);
        Long userId=1L;
        Mockito.when(orderService.getAllOrders(userId)).thenReturn(orderDTOList);
        ResponseEntity<List<OrderDTO>> responseEntity=orderController.getAllOrders(userId);
        Assertions.assertEquals(1,responseEntity.getBody().size());
        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());

    }


}

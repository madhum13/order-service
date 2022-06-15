package com.mycompany.orderservice.service;

import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.entity.OrderEntity;
import com.mycompany.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
    public class OrderServiceImpl {
        @ExtendWith(MockitoExtension .class)

        @InjectMocks
        private OrderServiceImpl orderServiceImpl;

        @Mock
        private OrderRepository orderRepository;

      /*  @Test
        void testPlaceOrder_Success() {

            OrderDTO dto = new OrderDTO();
            dto.setId(1L);

            OrderEntity bookEntity = new OrderEntity();
            bookEntity.setBookIds("Dummy");

            OrderEntity savedEntity = new OrderEntity();
            savedEntity.setUserId(1L);
            savedEntity.setId(1L);

            OrderDTO savedDTO = new OrderDTO();
            savedDTO.setUserId(1L);
            savedDTO.setId(1L);


            Mockito.when(orderRepository.save(Mockito.any())).thenReturn(savedEntity);

            OrderDTO result = orderServiceImpl.placeOrder(dto);
            Assertions.assertEquals(savedDTO.getUserId(), result.getUserId());
        }

    @Test
    void testGetAllOrders_Success(){

        List<OrderEntity> orderEntities = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setUserId(1L);
        orderEntities.add(orderEntity);

        OrderDTO savedDTO = new OrderDTO();
        savedDTO.setId(1L);
        savedDTO.setUserId(1L);

        Mockito.when(orderRepository.findAll()).thenReturn(orderEntities);

        List<OrderDTO> listOrderDTO = OrderServiceImpl.getAllOrders();

        Assertions.assertEquals(1, listOrderDTO.size());
    }*/
}

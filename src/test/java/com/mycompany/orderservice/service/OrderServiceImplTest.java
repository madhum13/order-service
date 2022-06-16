package com.mycompany.orderservice.service;

import com.mycompany.orderservice.dto.BookDTO;
import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.entity.OrderEntity;
import com.mycompany.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import com.mycompany.orderservice.exception.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceImplTest {
    String bookServiceUrl = "http://localhost:8080";
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup()
    {
        ReflectionTestUtils.setField(orderService, "bookServiceUrl", bookServiceUrl);
    }
    @Test
    void placeOrderTest_Success() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(1L);
        bookDTO.setAuthorName("aaa");
        bookDTO.setAvailableQty(30.0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<BookDTO> bookDTOList = new ArrayList<>();
        bookDTOList.add(bookDTO);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(2L);
        orderDTO.setUserId(1L);
        orderDTO.setBooks(bookDTOList);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(3L);
        orderEntity.setBookIds("1,2");

        OrderEntity savedorderEntity = new OrderEntity();
        savedorderEntity.setId(3L);
        savedorderEntity.setBookIds("1,2");


        // String bookServiceUrl = "localhost://8080";
        ResponseEntity<BookDTO> respEntity = new ResponseEntity<>(bookDTO, HttpStatus.OK);
        ResponseEntity<String> respEntity1 = new ResponseEntity<>("success", HttpStatus.OK);
        Mockito.when(orderRepository.save(orderEntity)).thenReturn(savedorderEntity);
        Mockito.when(this.restTemplate.exchange(bookServiceUrl + "/books/{bookId}", HttpMethod.GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<BookDTO>() {}, bookDTO.getBookId())).thenReturn(new ResponseEntity<>(bookDTO, HttpStatus.OK));

        orderDTO = orderService.placeOrder(orderDTO);
        Assertions.assertEquals(2L, orderDTO.getId());


    }

    @Test
    void placeOrderTest_Failure() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(1L);
        bookDTO.setAuthorName("aaa");
        bookDTO.setAvailableQty(0.0);
        List<BookDTO> bookDTOList = new ArrayList<>();
        bookDTOList.add(bookDTO);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(2L);
        orderDTO.setBooks(bookDTOList);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(3L);

        String bookServiceUrl = "localhost://8080";
        ResponseEntity<BookDTO> respEntity = new ResponseEntity<>(bookDTO, HttpStatus.OK);
        ResponseEntity<String> respEntity1 = new ResponseEntity<>("success", HttpStatus.OK);
        Mockito.when(this.restTemplate.exchange(bookServiceUrl + "/books/{bookId}", HttpMethod.GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<BookDTO>() {}, bookDTO.getBookId())).thenReturn(new ResponseEntity<>(bookDTO, HttpStatus.OK));

        OrderDTO finalOrderDTO = orderDTO;
        BusinessException thrown=Assertions.assertThrows(BusinessException.class,() -> {
            OrderDTO orderDTO1=orderService.placeOrder(orderDTO);
        });

        Assertions.assertEquals("book with id 1L does not have sufficient quantity",thrown.getMessage());

    }
}

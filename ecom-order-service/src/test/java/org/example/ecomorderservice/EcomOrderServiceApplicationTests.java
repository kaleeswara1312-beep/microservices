        package org.example.ecomorderservice;

import org.example.ecomorderservice.config.FeignClientConfig;
import org.example.ecomorderservice.service.OrderService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

        @ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EcomOrderServiceApplicationTests {

    @Mock
    FeignClientConfig feignClientConfig;

    @Mock
    RestClient restClient;

    @Spy
    @InjectMocks
    OrderService orderService;

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll: Executes once before all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach: Executes before each test");
    }

    @Test
    @Order(1)
    void placeOrderTest() {
        System.out.println("Test 1: placeOrderTest");

        String productId = "1";

        // Mock the dependency, not the service being tested
        Mockito.when(feignClientConfig.placeOrder(productId))
                .thenReturn("1");

        // Execute the real OrderService method
        String actualProductId = orderService.placeOrder(productId);

        // Verify the result
        Assertions.assertEquals(productId, actualProductId);

        // Verify the dependency was called
        Mockito.verify(feignClientConfig).placeOrder(productId);
    }

    @Test
    @Order(2)
    void test1() {
        System.out.println("Test 2: test1");

        Assertions.assertTrue(true);
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach: Executes after each test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll: Executes once after all tests");
    }

            @Test
            void testDoNothing() {

                // Suppose feignClientConfig has a void method named
                // cancelOrder(String productId)

                System.out.println("Do nothing invoked");

                Mockito.doNothing()
                        .when(orderService)
                        .cancelOrder("123");

                orderService.cancelOrder("123");

                Mockito.verify(orderService)
                        .cancelOrder("123");
            }

    @Test
    void testPrivateValidateProductId() throws Exception {

        // Get the private method by name and parameter type
        Method method = OrderService.class
                .getDeclaredMethod("validateProductId", String.class);

        // Allow reflective access to the private method
        method.setAccessible(true);

        // Invoke the method on the OrderService instance
        String result = (String) method.invoke(orderService, " 123 ");

        System.out.println("Reflection API");
        // Verify the result
        Assertions.assertEquals("123", result);
    }

            @Test
            void testPrivateMethodException() throws Exception {

                System.out.println("Exception testing scenario");
                Method method = OrderService.class
                        .getDeclaredMethod("validateProductId", String.class);

                method.setAccessible(true);

                // Verify that the private method throws an exception
                InvocationTargetException exception =
                        Assertions.assertThrows(
                                InvocationTargetException.class,
                                () -> method.invoke(orderService, (Object) null)
                        );

                // Verify the actual exception thrown by the private method
//                Assertions.assertInstanceOf(
//                        IllegalArgumentException.class,
//                        exception.getCause()
//                );

                // Verify the exception message
                Assertions.assertEquals(
                        "Product ID cannot be null or blank",
                        exception.getCause().getMessage()
                );
            }
}

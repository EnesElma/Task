package com.enes.product.service;

import com.enes.product.entity.Product;
import com.enes.product.exception.CustomException;
import com.enes.product.repo.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product,product2;

    @BeforeEach
    public void setup(){
        product = Product.builder()
                .id(1L).name("Test product").username("user_1").price(130.00)
                .build();

        product2 = Product.builder()
                .id(2L).name("Test product 2").username("user_2").price(1302.00)
                .build();
    }


    @DisplayName("JUnit test for createProduct")
    @Test
    void createProduct() {
        //given - precondition or setup
        given(productRepository.findByName(product.getName())).willReturn(Optional.empty());
        given(productRepository.save(product)).willReturn(product);

        //when - action or the behavior that we are going test
        Product savedProduct = productService.createProduct(product);

        //then - verify the output
        assertNotNull(savedProduct);
    }

    @DisplayName("JUnit test for createProduct fail when product name exist")
    @Test
    void createProductFail() {
        //given - precondition or setup
        given(productRepository.findByName(product.getName())).willReturn(Optional.of(product));

        //when - action or the behavior that we are going test
        assertThrows(CustomException.class,()->{
            productService.createProduct(product);
        });

        //then - verify the output
        verify(productRepository,never()).save(any(Product.class));
    }

    @Test
    void updateProduct() {
        //given
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        given(productRepository.save(product)).willReturn(product);
        product.setName("Update name test");

        //when
        Product updatedProduct = productService.updateProduct(product);


        //then
        assertEquals(updatedProduct.getName(),product.getName());

    }

    @Test
    void updateProductFail(){
        //given
        given(productRepository.findById(product.getId())).willReturn(Optional.empty());

        //when
        assertThrows(CustomException.class,()->{
            productService.updateProduct(product);
        });

        //then
        verify(productRepository,never()).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        long productId = 1L;
        String username = "user_1";

        //given
        willDoNothing().given(productRepository).deleteByIdAndUsername(productId,username);

        //when
        productService.deleteProduct(productId,username);

        //then
        verify(productRepository,times(1)).deleteByIdAndUsername(productId,username);
    }

    @Test
    void listAllProduct() {
        //given
        given(productRepository.findAll()).willReturn(List.of(product,product2));

        //when
        List<Product> products = productService.listAllProduct();

        //then
        assertNotNull(products);
        assertEquals(products.size(),2);
    }
}
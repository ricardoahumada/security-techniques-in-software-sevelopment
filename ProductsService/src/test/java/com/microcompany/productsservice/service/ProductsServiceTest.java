package com.microcompany.productsservice.service;



import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@DataJpaTest()
public class ProductsServiceTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
        @Bean
        public ProductsService productsService() {
            return new ProductsService();
        }

    }

    @BeforeEach
    public void setUp() {
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);  // when repo bean needs EM

        List<Product> products = Arrays.asList(
                new Product(1L, "Fake product", "")
        );

        Mockito.when(productsRepository.findByNameContaining("a"))
                .thenReturn(products);

        Mockito.when(productsRepository.save(Mockito.any(Product.class)))
                .thenAnswer(elem -> {
                    Product ap = (Product) elem.getArguments()[0];
                    ap.setId(100L);
                    return ap;
                });
    }


    @MockBean
    private EntityManager entityManager;  // when repo bean needs EM

    @MockBean
    private EntityManagerFactory entityManagerFactory;  // when repo bean needs EM

    @MockBean
    private ProductsRepository productsRepository;

    @Autowired
    ProductsService productsService;


    @Test
    void givenProductsWhenSearchByTextThenIsNotNull() {
        List<Product> products = productsService.getProductsByText("a");
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    void givenValidProduct_WhenCreate_ThenThenIsNotNull() {
        Product newProduct = new Product(null, "Nuevo Producto", "111-222-3333");

        productsService.create(newProduct);
        assertThat(newProduct)
                .isNotNull()
                .extracting(Product::getId).isEqualTo(100L)
                /*.extracting(Product::getName).isEqualTo("Nuevo Producto")*/;
    }
}
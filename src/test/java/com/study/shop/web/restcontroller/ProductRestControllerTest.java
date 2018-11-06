package com.study.shop.web.restcontroller;

import com.study.shop.entity.Product;
import com.study.shop.service.ProductService;
import com.study.shop.web.config.TestContext;
import com.study.shop.web.config.WebAppContext;
import com.study.shop.web.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ProductRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    private ProductService productServiceMock;

    @Before
    public void setUp() {
        Mockito.reset(productServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testGetAll() throws Exception {
        Product first = new Product(1, "name1", 100.99, null, "the link 1");
        Product second = new Product(2, "name2", 200.99, null, "the link 2");

        when(productServiceMock.getAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("name1")))
                .andExpect(jsonPath("$[0].price", equalTo(100.99)))
                .andExpect(jsonPath("$[0].picturePath", equalTo("the link 1")))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].name", equalTo("name2")))
                .andExpect(jsonPath("$[1].price", equalTo(200.99)))
                .andExpect(jsonPath("$[1].picturePath", equalTo("the link 2")));

        verify(productServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void testGetById() throws Exception {
        Product second = new Product(2, "name2", 200.99, null, "the link 2");

        when(productServiceMock.getById(2)).thenReturn(Arrays.asList(second));

        mockMvc.perform(get("/api/v1/product/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo("name2")))
                .andExpect(jsonPath("$.price", equalTo(200.99)))
                .andExpect(jsonPath("$.picturePath", equalTo("the link 2")));

        verify(productServiceMock, times(1)).getById(2);
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/product/1"))
                .andExpect(status().isOk());

        verify(productServiceMock, times(1)).delete(1);
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void testEdit() throws Exception {
        Product updated = new Product(1, "new_name1", 99.99, null, "the new link 1");

        mockMvc.perform(put("/api/v1/product/1", updated)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updated))
        )
                .andExpect(status().isOk());

        verify(productServiceMock, times(1)).update(1, "new_name1", 99.99, null, "the new link 1");
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void testAdd() throws Exception {
        LocalDateTime this_moment = LocalDateTime.now();
        Product new_product = new Product(1, "new_name", 9.99, null, "the new link");

        mockMvc.perform(post("/api/v1/product")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(new_product))
        )
                .andExpect(status().isOk());

        verify(productServiceMock, times(1)).add("new_name", 9.99, "the new link");
        verifyNoMoreInteractions(productServiceMock);
    }

}

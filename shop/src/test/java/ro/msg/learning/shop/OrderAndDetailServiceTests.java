package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exception.StringObjectMapperException;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopApplication.class , webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Profile("test")
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class OrderAndDetailServiceTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void createOrdersSuccess() throws Exception
    {
        ProductQuantityDTO detail1=new ProductQuantityDTO(1,5);
        ProductQuantityDTO detail2=new ProductQuantityDTO(2,5);
        OrderAndDetailsDTO order1=new OrderAndDetailsDTO(Date.valueOf("2018-09-09"),"Romania","Oradea,","Bihor","Str nuf nr 13", Collections.singletonList(detail1),1);
        OrderAndDetailsDTO order2=new OrderAndDetailsDTO(Date.valueOf("2018-09-09"),"Romania","Oradea,","Bihor","Str nuf nr 13", Arrays.asList(detail1,detail2),1);
        assertThat(detail1.getQuantity()).isEqualTo(5);

        mvc.perform( MockMvcRequestBuilders
                .post("/orders/")
                .content(asJsonString(order1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdAt").value("2018-09-08T21:00:00.000+0000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].country").value("Romania"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Oradea,"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].county").value("Bihor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].streetAddress").value("Str nuf nr 13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].shippedFrom.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerID.id").value(1));

        mvc.perform( MockMvcRequestBuilders
                .post("/orders/")
                .content(asJsonString(order2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdAt").value("2018-09-08T21:00:00.000+0000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].country").value("Romania"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Oradea,"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].county").value("Bihor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].streetAddress").value("Str nuf nr 13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].shippedFrom.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerID.id").value(1));

    }

    @Test
    public void createOrdersFailNoStock() throws Exception
    {
        //fail to create orders because of insufficient stock
        ProductQuantityDTO detail1=new ProductQuantityDTO(1,1000);
        ProductQuantityDTO detail2=new ProductQuantityDTO(2,1000);
        OrderAndDetailsDTO order1=new OrderAndDetailsDTO(Date.valueOf("2018-09-09"),"Romania","Oradea,","Bihor","Str nuf nr 13", Collections.singletonList(detail1),1);
        OrderAndDetailsDTO order2=new OrderAndDetailsDTO(Date.valueOf("2018-09-09"),"Romania","Oradea,","Bihor","Str nuf nr 13", Arrays.asList(detail1,detail2),1);
        assertThat(detail1.getQuantity()).isEqualTo(1000);

        mvc.perform(MockMvcRequestBuilders
                .post("/orders/")
                .content(asJsonString(order1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mvc.perform( MockMvcRequestBuilders
                .post("/orders/")
                .content(asJsonString(order2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new StringObjectMapperException(e.getMessage());
        }
    }
}

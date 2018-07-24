package com.demo.pricingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith ( SpringRunner.class )
@SpringBootTest ( classes = PricingServiceApplication.class )
public class ApiDocumentationIntegrationTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void getPrices() throws Exception {
        this.mockMvc.perform(get("/price").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getPrice() throws Exception {
        this.mockMvc.perform(get("/price/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}", preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("id").description("Price Id")
                                , fieldWithPath("productId").description("Product Id")
                                , fieldWithPath("purchasePrice").description("Purchase Price")
                                , fieldWithPath("sellingPrice").description("Selling Price")
                                , fieldWithPath("taxAmount").description("Tax amount")
                                , fieldWithPath("currency").description("Currency code")
                        )));
    }

    @Test
    public void getPriceByProductId() throws Exception {
        this.mockMvc.perform(get("/price/product/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}", preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("id").description("Price Id")
                                , fieldWithPath("productId").description("Product Id")
                                , fieldWithPath("purchasePrice").description("Purchase Price")
                                , fieldWithPath("sellingPrice").description("Selling Price")
                                , fieldWithPath("taxAmount").description("Tax amount")
                                , fieldWithPath("currency").description("Currency code")
                        )));
    }

}

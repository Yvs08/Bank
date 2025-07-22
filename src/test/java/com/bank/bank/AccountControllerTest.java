package com.bank;

import com.bank.model.*;
import com.bank.repository.*;
import com.bank.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
   // @Autowired
    private AccountRepository accountRepository;

   // @Test
    void createAccount_and_deposit_and_withdraw_and_statement() throws Exception {
        // Create account
        String response = mockMvc.perform(post("/accounts?initialBalance=100.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(100.0)))
                .andReturn().getResponse().getContentAsString();
        // Extract account id
        Long accountId = 2L;
        //JsonPath.read(response, "$.id");

        // Deposit
        mockMvc.perform(post("/accounts/" + accountId + "/deposit?amount=50.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(150.0)));

        // Withdraw
        mockMvc.perform(post("/accounts/" + accountId + "/withdraw?amount=20.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(130.0)));

        // Statement
        mockMvc.perform(get("/accounts/" + accountId + "/statement"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].amount", is(100.0)))
                .andExpect(jsonPath("$[1].amount", is(50.0)))
                .andExpect(jsonPath("$[2].amount", is(-20.0)));
    }
} 
package com.shinn.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class TxControllerTest extends AbstractContrllerTest{
    
    @Test
    public void testCreateTx() throws Exception {
        getMockMvc().perform(post("/tx/savetx")
        .param("roomId", "1")
        .param("aptId","1")
        .param("renterId","1")
        .param("dueDate", "07/05/2016")
        .param("txDate", "07/05/2016")
        .param("startDate", "07/05/2016")
        .param("endDate", "07/05/2016")
        .param("deposit", "1000.00")
        .param("balance", "600.00")
        .param("txType", "apartment")
        .param("provider", "meco")
        .param("amount", "1000.00")
        .param("status", "New")
        .param("userId", "1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
    }

}

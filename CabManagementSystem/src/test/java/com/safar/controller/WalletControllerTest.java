package com.safar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safar.entity.Users;
import com.safar.entity.Wallet;
import com.safar.entity.WalletStatus;
import com.safar.exceptions.WalletException;
import com.safar.service.UserService;
import com.safar.service.WalletServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WalletController.class)
@AutoConfigureMockMvc(addFilters=false)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletServices walletServices;

    @MockBean
    private UserService userService;

    private Wallet requestWallet;

    private  Wallet responseWallet;

    private Users user;

    private Authentication auth;

    @BeforeEach
   public void init(){
          requestWallet = new Wallet();
          requestWallet.setBalance(0.0f);
          requestWallet.setStatus(WalletStatus.Active);

          responseWallet= new Wallet();
          responseWallet.setWalletId(1);
          responseWallet.setBalance(0.0f);
          responseWallet.setStatus(WalletStatus.Active);
          user= new Users();
          user.setWallet(responseWallet);
         auth = new UsernamePasswordAuthenticationToken("testusername","testpassword");

   }

   @Test
   @DisplayName("Wallet can be created")
   public void testCreateWallet_WhenValidEmailPassed_shouldReturnWalletEnity() throws Exception {
        Mockito.when(walletServices.createWallet(anyString())).thenReturn(responseWallet);
       RequestBuilder builder = MockMvcRequestBuilders.post("/WALLET/createWallet").param("email","ankit@gmail.com").contentType(MediaType.APPLICATION_JSON_VALUE);

       MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isCreated()).andReturn();

       String responseBodyAsString = mvcResult.getResponse().getContentAsString();

       Wallet resWallet= new ObjectMapper().readValue(responseBodyAsString,Wallet.class);

       Assertions.assertNotNull(resWallet.getWalletId(),"Created wallet should have a id");
       Assertions.assertEquals(responseWallet.getWalletId(),resWallet.getWalletId(),"Walled id should  equal to 1");
       Mockito.verify(walletServices, Mockito.times(1)).createWallet(anyString());


   }



    @Test
    @DisplayName("Money can be added to wallet")
    public void testAddMoneyToWallet_WhenAmountPassed_ThenMoneyShouldBeAddedIntoWallet() throws Exception {



        Mockito.when(userService.getUserDetailsByEmail(auth.getName())).thenReturn(user);
        responseWallet.setBalance(10.0f);
        Mockito.when(walletServices.addMoney(anyInt(), anyFloat())).thenReturn(responseWallet);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/WALLET/addMoney")
                .param("amount", "10") // remove double quotes
                .principal(auth)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        Wallet resWallet = new ObjectMapper().readValue(responseBodyAsString, Wallet.class);

        Assertions.assertNotNull(resWallet.getWalletId(), "wallet should have a id");
        Assertions.assertEquals(10.0f, resWallet.getBalance(), "Walled  should have added balance");
        Mockito.verify(walletServices, Mockito.times(1)).addMoney(anyInt(), anyFloat());
    }

    @Test
    @DisplayName("wallet status can be change")
    public void testChangeStatus_WhenValidUserLoggedIn_thenWalletStatusCanBeChanged() throws Exception {
        Mockito.when(userService.getUserDetailsByEmail(auth.getName())).thenReturn(user);
        responseWallet.setStatus(WalletStatus.Inactive);
        Mockito.when(walletServices.changeStatus(anyInt())).thenReturn(responseWallet);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.patch("/WALLET/changeStatus").principal(auth).contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString= mvcResult.getResponse().getContentAsString();
        Wallet resWallet= new ObjectMapper().readValue(responseBodyAsString,Wallet.class);

        Assertions.assertNotNull(resWallet.getWalletId(),"Wallet should have a id");
        Assertions.assertEquals(WalletStatus.Inactive,resWallet.getStatus(),"status should be Inactive");

    }

    @Test
    @DisplayName("Wallet object can be find")
    public void testGetWallet_WhenValidWalletIdProvided_ThenReturnsWalletObject() throws  Exception{
       Mockito.when(walletServices.getWallet(anyInt())).thenReturn(responseWallet);

       RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/WALLET/getWallet/{wid}",1).contentType(MediaType.APPLICATION_JSON_VALUE);
       MvcResult mvcResult= mockMvc.perform(requestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString= mvcResult.getResponse().getContentAsString();
        Wallet resWallet= new ObjectMapper().readValue(responseBodyAsString,Wallet.class);
        Assertions.assertNotNull(resWallet.getWalletId(),"Wallet should have a id");
        Assertions.assertEquals(responseWallet.getWalletId(),resWallet.getWalletId(),"Wallet id should be equal to 1");
        Mockito.verify(walletServices,Mockito.times(1)).getWallet(anyInt());

    }


    @Test
    @DisplayName("Wallet object can be find for logged user")
  public void testGetLoggedUserWallet()throws  Exception{
        Mockito.when(walletServices.getLoggedUserWallet(anyString())).thenReturn(responseWallet);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/WALLET/WalletDetails").principal(auth).contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString= mvcResult.getResponse().getContentAsString();
        Wallet resWallet= new ObjectMapper().readValue(responseBodyAsString,Wallet.class);
        Assertions.assertNotNull(resWallet.getWalletId(),"Wallet should have a id");
        Assertions.assertEquals(responseWallet.getWalletId(),resWallet.getWalletId(),"Wallet id should be equal to 1");
        Mockito.verify(walletServices,Mockito.times(1)).getLoggedUserWallet(anyString());

  }
  @Test
  @DisplayName("Wallet Exception can be thrown")
  public void testGetLoggedUserWallet_WhenNullAuthenticationPassed_shouldThrowWalletException() throws Exception {
     auth= new UsernamePasswordAuthenticationToken(null,null);
      Mockito.when(walletServices.getLoggedUserWallet(anyString())).thenReturn(responseWallet);

      RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/WALLET/WalletDetails").principal(auth).contentType(MediaType.APPLICATION_JSON_VALUE);
      MvcResult mvcResult= mockMvc.perform(requestBuilder).andExpect(result->Assertions.assertTrue(result.getResolvedException() instanceof WalletException)).andReturn();
      }
  }


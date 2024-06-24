package ar.edu.utn.frc.tup.lciii.videos.controllers;

import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getByIdTest() throws Exception {

        Player player = new Player();
        player.setId(1L);
        player.setEmail("email@email.com");
        player.setUserName("agusnieto");
        player.setPassword("Password07$");

        //cuando yo ejecute player service con un 1 , entonces me devuelva el player que creamos
        when(playerService.getPlayerById(1L)).thenReturn(player);

        //test con jsonPath
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("agusnieto"))
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.password").value("Password07$"))
        ;

        //test validando un result
        MvcResult mvcResult = this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        Player result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Player.class);

        Assertions.assertEquals("agusnieto",result.getUserName());
    }
}
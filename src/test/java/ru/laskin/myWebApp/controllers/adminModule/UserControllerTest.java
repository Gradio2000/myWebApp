package ru.laskin.myWebApp.controllers.adminModule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.laskin.myWebApp.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void deleteUserTest() throws Exception {

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get("http://localhost:8081/users/delete");

//        userController.deleteUser((HttpServletRequest) req, 2);

        ResultActions resultActions = mockMvc.perform(req);
        resultActions.andDo(print())
                .andExpect(status().isOk());

//        this.mockMvc.perform(get("/homePage")).andDo(print())
//                .andExpect(view().name("index"));
    }
}
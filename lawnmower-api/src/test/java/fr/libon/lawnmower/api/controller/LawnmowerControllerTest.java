package fr.libon.lawnmower.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import fr.libon.lawnmower.api.LawnMowerApplication;
import fr.libon.lawnmower.core.dto.PositionsResultDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = LawnMowerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public class LawnmowerControllerTest {

    private static final String LAWNMOWER_API_URI = "/api/mower/process";

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<PositionsResultDto> positionsResultDtoJacksonTester;

    @BeforeEach
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        objectMapper.registerModule(javaTimeModule);
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    void givenWrongPayload_whenMowersProcess_assert200() throws Exception{
        String goodPayload = Files.readAllLines(new ClassPathResource("good_payload.json").getFile().toPath()).stream().collect(Collectors.joining());
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(LAWNMOWER_API_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(goodPayload)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PositionsResultDto positionsResultDto = positionsResultDtoJacksonTester.parse(mvcResult.getResponse().getContentAsString()).getObject();
        Assertions.assertNotNull(positionsResultDto);
        Assertions.assertFalse(positionsResultDto.getPositions().isEmpty());
        Assertions.assertEquals(2, positionsResultDto.getPositions().size());

    }

    @Test
    void givenWrongPayload_whenMowersProcess_assert404() throws Exception{
        String wrongPayload = Files.readAllLines(new ClassPathResource("wrong_payload.json").getFile().toPath()).stream().collect(Collectors.joining());
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(LAWNMOWER_API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wrongPayload)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}

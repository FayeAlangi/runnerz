package com.fayea.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RunController.class)
class RunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RunRepository runRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Run run1;
    private Run run2;

    @BeforeEach
    void setUp() {
        run1 = new Run(0, "Noon Run", LocalDateTime.parse("2024-02-20T06:05:00.000000"),LocalDateTime.parse("2024-02-20T10:27:00.000000"), 24,Location.OUTDOOR,1);
        run2 = new Run(1, "Evening Run", LocalDateTime.parse("2024-03-20T06:05:00.000000"),LocalDateTime.parse("2024-03-20T10:27:00.000000"), 5,Location.INDOOR,1);
    }

    @Test
    void findAll() throws Exception {
        when(runRepository.findAll()).thenReturn(Arrays.asList(run1, run2));

        mockMvc.perform(get("/api/runs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(run1.id()))
                .andExpect(jsonPath("$[1].id").value(run2.id()));
    }

    @Test
    void findById() throws Exception {
        when(runRepository.findById(anyInt())).thenReturn(Optional.of(run1));

        mockMvc.perform(get("/api/runs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(run1.id()));
    }

    @Test
    void findById_NotFound() throws Exception {
        when(runRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/runs/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/api/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(run1)))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        when(runRepository.findById(anyInt())).thenReturn(Optional.of(run1));

        mockMvc.perform(put("/api/runs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(run1)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(runRepository).deleteById(anyInt());

        mockMvc.perform(delete("/api/runs/1"))
                .andExpect(status().isNoContent());

        verify(runRepository, times(1)).deleteById(1);
    }

    @Test
    void findByLocation() throws Exception {
        when(runRepository.findAllByLocation(anyString())).thenReturn(Arrays.asList(run1, run2));

        mockMvc.perform(get("/api/runs/location/OUTDOOR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location").value("OUTDOOR"));
    }
}

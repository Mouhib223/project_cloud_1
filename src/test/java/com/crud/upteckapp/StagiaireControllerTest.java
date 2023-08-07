package com.crud.upteckapp;
import com.crud.upteckapp.controller.StagiaireController;
import com.crud.upteckapp.Services.StagiaireService;
import com.crud.upteckapp.model.Stagiaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StagiaireControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StagiaireService stagiaireService;

    @InjectMocks
    private StagiaireController stagiaireController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stagiaireController).build();
    }

    @Test
    public void testGetAllStagiaires() throws Exception {
        Stagiaire stagiaire1 = new Stagiaire();
        stagiaire1.setId(1L);
        Stagiaire stagiaire2 = new Stagiaire();
        stagiaire2.setId(2L);
        List<Stagiaire> stagiaires = Arrays.asList(stagiaire1, stagiaire2);

        when(stagiaireService.listAll()).thenReturn(stagiaires);

        mockMvc.perform(MockMvcRequestBuilders.get("/Stagiaires/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(stagiaireService, times(1)).listAll();
    }

    @Test
    public void testGetStagiaireById() throws Exception {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(1L);

        when(stagiaireService.get(1L)).thenReturn(stagiaire);

        mockMvc.perform(MockMvcRequestBuilders.get("/Stagiaires/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(stagiaireService, times(1)).get(1L);
    }

    @Test
    public void testAddStagiaire() throws Exception {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/Stagiaires/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk());

        verify(stagiaireService, times(1)).save(any(Stagiaire.class));
    }

    @Test
    public void testUpdateStagiaire() throws Exception {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(1L);

        when(stagiaireService.get(1L)).thenReturn(stagiaire);

        mockMvc.perform(MockMvcRequestBuilders.put("/Stagiaires/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk());

        verify(stagiaireService, times(1)).save(any(Stagiaire.class));
    }

    @Test
    public void testDeleteStagiaire() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Stagiaires/1"))
                .andExpect(status().isOk());

        verify(stagiaireService, times(1)).delete(1L);
    }
}

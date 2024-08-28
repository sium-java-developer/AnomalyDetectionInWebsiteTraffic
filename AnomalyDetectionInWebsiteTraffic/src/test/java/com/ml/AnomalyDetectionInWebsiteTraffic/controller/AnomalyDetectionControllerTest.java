package com.ml.AnomalyDetectionInWebsiteTraffic.controller;

import com.ml.AnomalyDetectionInWebsiteTraffic.service.AnomalyDetectionService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AnomalyDetectionController.class)
public class AnomalyDetectionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AnomalyDetectionService anomalyDetectionService;
    
    @Test
    public void testUploadFile() throws Exception {
        // Mock the service methods
        when(anomalyDetectionService.trainModel(anyString())).thenReturn(true);
        List<Integer> expectedAnomalies = Arrays.asList(1, 3, 5);
        when(anomalyDetectionService.detectAnomalies(anyString())).thenReturn(expectedAnomalies);

        // Create a mock multipart file with test data
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", MediaType.TEXT_PLAIN_VALUE, "test data".getBytes());

        // Perform the POST request to the /upload endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("anomalies"))
                .andReturn();

        // Retrieve the model attributes
        Map<String, Object> modelMap = result.getModelAndView().getModelMap();
        List<Integer> actualAnomalies = (List<Integer>) modelMap.get("anomalies");

        // Assert that the actual anomalies match the expected anomalies
        assertEquals(expectedAnomalies, actualAnomalies);
    }
}

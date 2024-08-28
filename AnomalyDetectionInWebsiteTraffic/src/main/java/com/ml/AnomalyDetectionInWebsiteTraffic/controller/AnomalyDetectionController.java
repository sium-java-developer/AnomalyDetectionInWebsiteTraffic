package com.ml.AnomalyDetectionInWebsiteTraffic.controller;

import com.ml.AnomalyDetectionInWebsiteTraffic.service.AnomalyDetectionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;



@Controller
public  
 class AnomalyDetectionController {

    @Autowired
    private AnomalyDetectionService anomalyDetectionService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, Model model) throws Exception {
        if (!file.isEmpty()) {
            try {
                anomalyDetectionService.trainModel(file.getOriginalFilename());
                List<Integer> anomalies = anomalyDetectionService.detectAnomalies(file.getOriginalFilename());
                model.addAttribute("anomalies", anomalies);
                return "results";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "An error occurred while processing the file.");
                return "upload";
            }
        } else {
            model.addAttribute("error", "Please select a file to upload.");
            return "upload";
        }
    }
}

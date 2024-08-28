package com.ml.AnomalyDetectionInWebsiteTraffic.service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;

import static java.lang.Integer.parseInt;

@Service
public class AnomalyDetectionService {

    private Instances trainingData;
    private Filter filter1;

    public void trainModel(String filePath) throws Exception {
        // Load data from CSV file
        CSVLoader loader = new CSVLoader();
        loader.setFile(new File(filePath));
        trainingData = loader.getDataSet();

        // Define attributes if not present in the CSV
        if (trainingData.numAttributes() == 0) {
            List<Attribute> attributes = new ArrayList<>();
            // Add attributes based on your data (e.g., page views, unique visitors)
            attributes.add(new Attribute("page_views"));
            attributes.add(new Attribute("unique_visitors"));
            //trainingData.setHeader(attributes);
        }

        // Local Outlier Factor (LOF) for anomaly detection
        filter1.setInputFormat(trainingData);
        filter1.setOptions(new String[]{}); // Adjust parameters if needed (e.g., number of neighbors)
        filter1.batchFinished();
    }

    public List<Integer> detectAnomalies(String filePath) throws Exception {
        List<Integer> anomalies = new ArrayList<>();

        // Load test data from CSV file
        CSVLoader loader = new CSVLoader();
        loader.setFile(new File(filePath));
        Instances testData = loader.getDataSet();

        // Identify anomalies based on LOF scores (lower scores indicate anomalies)
        for (int i = 0; i < testData.numInstances(); i++) {
            Instance instance = testData.instance(i);
            double lofScore = instance.value(instance.attribute(parseInt("filter1")));
            if (lofScore < 0.5) { // Adjust threshold as needed
                anomalies.add(i);
            }
        }

        return anomalies;
    }
}

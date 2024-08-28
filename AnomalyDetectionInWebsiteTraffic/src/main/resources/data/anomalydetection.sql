

-- Create the traffic_data table
CREATE TABLE traffic_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    page_views INT,
    unique_visitors INT,
);

-- Insert sample data (replace with your actual data)
INSERT INTO traffic_data (page_views, unique_visitors) VALUES
(1000, 500),
(2000, 800),
(500, 300),
(1500, 700),
(3000, 1200);

-- Calculate average page views per day
SELECT DATE(timestamp) AS day, AVG(page_views) AS avg_page_views
FROM traffic_data
GROUP BY DATE(timestamp);

-- Identify anomalies based on a threshold (e.g., page views exceeding a certain value)
SELECT * FROM traffic_data WHERE page_views > 10000;

-- Use Z-score for outlier detection
SELECT * FROM traffic_data WHERE ZSCORE(page_views) > 3;

-- Calculate correlation between page views and unique visitors
SELECT CORR(page_views, unique_visitors) FROM traffic_data;

-- Find the top 5 most visited pages
SELECT page_url, COUNT(*) AS page_views
FROM traffic_data
GROUP BY page_url
ORDER BY page_views DESC
LIMIT 5;

-- Find the most frequent user agents
SELECT user_agent, COUNT(*) AS occurrences
FROM traffic_data
GROUP BY user_agent
ORDER BY occurrences DESC
LIMIT 5;

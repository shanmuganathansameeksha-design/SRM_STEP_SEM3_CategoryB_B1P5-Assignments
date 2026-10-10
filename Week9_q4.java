public class WeatherAlert {
    public static int countAlerts(int[] readings, int k, int threshold) {
        int alertCount = 0;
        long currentSum = 0;
        
        for (int i = 0; i < k; i++) {
            currentSum += readings[i];
        }
        
        long targetSum = (long) k * threshold;
        if (currentSum >= targetSum) {
            alertCount++;
        }
        
        for (int i = k; i < readings.length; i++) {
            currentSum += readings[i] - readings[i - k]; 
            if (currentSum >= targetSum) {
                alertCount++;
            }
        }
        
        return alertCount;
    }
}
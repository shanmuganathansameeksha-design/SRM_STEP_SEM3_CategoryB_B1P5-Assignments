import java.util.HashMap;
import java.util.Map;

public class CanteenOrder {
    public static class Result {
        public String item;
        public int count;

        public Result(String item, int count) {
            this.item = item;
            this.count = count;
        }
    }

    public static Result mostPopular(String[] orders) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        
        for (String item : orders) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }
        
        String popularItem = "";
        int maxCount = 0;
        
        
        for (String item : orders) {
            int count = frequencyMap.get(item);
            if (count > maxCount) {
                maxCount = count;
                popularItem = item;
            }
        }
        
        return new Result(popularItem, maxCount);
    }
}
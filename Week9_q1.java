public class TopperFinder {
    public static int[] findTopper(int[][] marks) {
        int m = marks.length;   
        int n = marks[0].length;    
        
        int bestRowIndex = 0;
        int maxTotal = -1;
        
        for (int i = 0; i < m; i++) {
            int currentTotal = 0;
            for (int j = 0; j < n; j++) {
                currentTotal += marks[i][j];
       }
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestRowIndex = i;
            }
        }
        
        return new int[]{bestRowIndex, maxTotal};
    }
}
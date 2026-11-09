
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    // Question 5
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[HOURS_PER_DAY];
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    // Question 4
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    // Question 6 & 7
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total += hourCounts[hour];
        }
        return total;
    }
    
    // Question 8
    public int busiestHour()
    {
        int maxHour = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > hourCounts[maxHour]) {
                maxHour = hour;
            }
        }
        return maxHour;
    }
    
    // Question 9
    public int quietestHour()
    {
        int minHour = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] < hourCounts[minHour]) {
                minHour = hour;
            }
        }
        return minHour;
    }
    
    // Question 11
    public int busiestTwoHour()
    {
        int maxHour = 0;
        int maxCount = hourCounts[0] + hourCounts[1];
        
        for(int hour = 1; hour < hourCounts.length - 1; hour++) {
            int twoHourCount = hourCounts[hour] + hourCounts[hour + 1];
            if(twoHourCount > maxCount) {
                maxCount = twoHourCount;
                maxHour = hour;
            }
        }
        return maxHour;
    }
}

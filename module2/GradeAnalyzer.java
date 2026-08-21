import java.io.*; 
import java.util.ArrayList;
 
public class GradeAnalyzer {
  static int invalidLines = 0; // Counter for invalid lines
  public static void main(String[] args) {
    // Step 1: read scores from file
    ArrayList<Integer> scores = readScores("scores.txt");
    // Step 2: calculate statistics
    double average = calculateAverage(scores);
    int[] highLow = findHighLow(scores);
    int[] gradeBands = calcGradeBands(scores);
    // Step 3: write and print report
    String reportFile = "report.txt";
    writeReport(scores, average, highLow[0], highLow[1], gradeBands, reportFile);
    System.out.println();
    printReport(reportFile);
  } 
 
  // Returns a list of valid scores read from the file
  public static ArrayList<Integer> readScores(String filename) {
    ArrayList<Integer> scores = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim(); // Remove leading and trailing whitespace
        if (line.isEmpty()) {
          continue; // Skip empty lines
        }
        try {
          int score = Integer.parseInt(line);
          scores.add(score);
        } catch (NumberFormatException e) {
          System.out.println("Invalid score: " + line);
          invalidLines++; // Increment invalid lines counter
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
    return scores;
  }
 
  // Returns the average of a list of scores, or 0.0 if the list is empty
  public static double calculateAverage(ArrayList<Integer> scores) {
    if (scores.isEmpty()) {
      return 0.0;
    }
    int sum = 0;
    for (int score : scores) {
      sum += score;
    }
    return (double) sum / scores.size();
  } 
 
  public static int[] findHighLow(ArrayList<Integer> scores) {
    if (scores.isEmpty()) {
      return new int[]{0, 0};
    }
    int high = scores.get(0);
    int low = scores.get(0);
    for (int score : scores) {
      if (score > high) {
        high = score;
      }
      if (score < low) {
        low = score;
      }
    }
    return new int[]{high, low};
  }

  public static int[] calcGradeBands(ArrayList<Integer> scores) {
    int[] bands = new int[5]; // Assuming 5 grade bands
    for (int score : scores) {
      if (score >= 90) {
        bands[0]++;
      } else if (score >= 80) {
        bands[1]++;
      } else if (score >= 70) {
        bands[2]++;
      } else if (score >= 60) {
        bands[3]++;
      } else {
        bands[4]++;
      }
    }
    return bands;
  }

  // Writes and prints the report
  public static void writeReport(
    ArrayList<Integer> scores,
    double avg, int high, int low,
    int[] gradeBands,
    String outputFile) {
    // your code here
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(outputFile));
      writer.write("=== Grade Analysis Report ===");
      writer.write("\nTotal scores processed: " + scores.size());
      writer.write("\nInvalid lines skipped: " + invalidLines);
      writer.newLine();
      writer.write("\nAverage score: " + avg);
      writer.write("\nHighest score: " + high);
      writer.write("\nLowest score: " + low);
      writer.newLine();
      writer.write("\nGrade Distribution:");
      writer.write("\nA (90-100): " + gradeBands[0]);
      writer.write("\nB (80-89): " + gradeBands[1]);
      writer.write("\nC (70-79): " + gradeBands[2]);
      writer.write("\nD (60-69): " + gradeBands[3]);
      writer.write("\nF (<60): " + gradeBands[4]);
    } catch (IOException e) {
      System.out.println("Could not write file: " + e.getMessage());
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          System.out.println("Could not close file: " + e.getMessage());
        }
      }
    }
  }

  public static void printReport(String reportFile) {
    try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("Could not read report file: " + e.getMessage());
    }
  }
} 
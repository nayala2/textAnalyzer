import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 */

/**
 * Assignment Description
 * Write a text analyzer that reads a file and outputs statistics 
 * about that file. It should output the word frequencies of all 
 * words in the file, sorted by the most frequently used word. 
 * The output should be a set of pairs, each pair containing a 
 * word and how many times it occurred in the file.
 * 
 * @author nayala2
 *
 */
public class WordCount {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
    static HashMap<Integer, String> repeatCheck 
    = new HashMap<>(); 
    
	public static void main(String[] args) throws FileNotFoundException {

	    // read whole file as String in JDK 7
	    String data = "";
	    try {
	      data = new String(Files.readAllBytes(Paths.get("full.html")));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    String[] textArray = count(data);
	    
	    int count = 0;
	    for (int i = 0; i < textArray.length; i++) {
    		if(!(repeatCheck.containsValue(textArray[i]))) {
		    	for (int j = 1; j < textArray.length; j++) {
		    		if(textArray[i].equals(textArray[j])) {
		    			count++;
		    		}

		    	}
    		}
    		String newText = textArray[i].toString();
			repeatCheck.put(count, newText);
    	count = 0;
	    }
	  
	    sortbykey();
	 }
	
    /**Return an array of the words in a text file 
     * removes all punctuation and split by spaces
     * @param textFile, file of text separated by whitespace
     * @return array of words in textFile
     */
    public static String[] count(String textFile){
        if(textFile == null || textFile.isEmpty()){
            return null;
        }
        
        String[] words = textFile.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
        return words;
    }

    // Function to sort map by Key 
    public static void sortbykey() 
    { 
        // TreeMap to store values of HashMap 
        TreeMap<Integer, String> sorted = new TreeMap<>(Collections.reverseOrder()); 
  
        // Copy all data from hashMap into TreeMap 
        sorted.putAll(repeatCheck); 
  
        // Display the TreeMap which is naturally sorted 
        for (Entry<Integer, String> entry : sorted.entrySet())  
            System.out.println(entry.getValue() + " " + entry.getKey());         
    } 
}

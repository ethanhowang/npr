package emailSorter;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   
import java.io.IOException;

/**
 * This Main class serves as the main organizer of file reads, processing, and file outputs.
 */
public class Main {

    public static void main(String... args) {   

        // takes email list from input.txt file
        ArrayList<String> dataset = new ArrayList<String>();
        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dataset.add(data);   
            }
        myReader.close();


        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

        // process of sorting the emails using a custom-made Sorter class
        Sorter test = new Sorter();
        ArrayList<String> result = test.sort(dataset);
        
        // writes to output.txt file 
        try {
            FileWriter myWriter = new FileWriter(args[1]);
            for (String i : result) {
                myWriter.write(i + "\n");
            }
            myWriter.close();

            // confirmation message upon successful completion
            System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
    }

}

/**
 * This Sorter class provides all the functionality for sorting the email list given the user input from
 * the input.txt file.
 */

public class Sorter {

    /**
     * This sort function contains the main functionality of the entire sorting process, calling other functions for filtering 
     * as needed, and does the sorting through a HashMap data structure before finally returning a list of valid emails
     * sorted in domain order.  The emails are further sorted within each domain as well.
     * @param emails
     * @return ArrayList<String> of filtered emails in domain sorted order.
     */
    public ArrayList<String> sort(ArrayList<String> emails) {
        ArrayList<String> modified = filter(emails);
        // hashmap to order the emails within a domain
        HashMap<String, ArrayList<String>> contacts = new HashMap<String, ArrayList<String>>();
        // list to keep track of distinct keys
        ArrayList<String> keys = new ArrayList<String>();
        // sorts the emails into directories corresponding to domain
        for (String i : modified) {
            String[] parts = i.split("@");
            String name = parts[0];
            String key = parts[1];

            if (!contacts.containsKey(key)) {
                keys.add(key);
                ArrayList<String> domainList = new ArrayList<String>();
                domainList.add(name);
                contacts.put(key, domainList);

            } else {
                contacts.get(key).add(name);
            }
        }
        // sort keys
        Collections.sort(keys);

        ArrayList<String> finalized = new ArrayList<String>();

        for (String j : keys) {
            
            Collections.sort(contacts.get(j));
            for (String k : contacts.get(j)) {
                finalized.add(k + "@" + j);

            }
        }

        return finalized;

    }

    /**
     * This function takes in the user's email list and checks the validity of each email
     * before returning a finalized list of just the valid ones. 
     * @param emails
     * @return list of valid emails
     */
    public ArrayList filter(ArrayList<String> emails) {
        ArrayList<String> modified = new ArrayList<String>();
        for (String i : emails) {
            if (checkIsValid(i)) {
                modified.add(i);
            }
        }

        return modified;

    }

    /**
     * Function that simply checks if an email address is valid using regex.
     * @param email
     * @return boolean -> true if email is in valid format, false otherwise
     */

    public boolean checkIsValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);

    }


}

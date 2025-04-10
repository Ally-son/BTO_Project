package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class StorageController {
    int nextEnquiryID;

    /*
    Initialise USERS data
     */
    public Map<String, ArrayList<String>> readUserFile(){
        Map<String, ArrayList<String>> USERS = new HashMap<>();
        try{
            Scanner data = new Scanner(new File("src/main/user_data/UserList.csv"));
            data.nextLine(); //for skipping the title
            while(data.hasNextLine()) {// Going through all user data
                String line = data.nextLine();
                String[] row = line.split(","); //delimiting
                USERS.put(row[1], new ArrayList<>(Arrays.asList(row))); //appending into hashmap
            }
            data.close();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
        }
        return USERS;
    }

    /*
    Write USERS into csv
     */
    public void writeUserFile(Map<String, ArrayList<String>> USERS){ //Replace userList.csv
        try{
            BufferedWriter obj = new BufferedWriter(new FileWriter("src/main/user_data/UserList.csv"));
            obj.write("Name,NRIC,Age,Marital Status,Password,Type\n");
            for (ArrayList<String> user : USERS.values()) { //using info in USERS to write file
                String line = String.join(",", user) + "\n";
                obj.write(line);
            }
            obj.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
    Initialise PROJECTS data
     */
    public Map<String, Project> readProjectFile(){
        Map<String, Project> PROJECTS = new HashMap<>();
        try{
            Scanner data = new Scanner(new File("src/main/user_data/ProjectList.csv"));
            data.nextLine(); //for skipping the title
            while(data.hasNextLine()) {// Going through all user data
                String line = data.nextLine();
                String[] row = line.split(","); //delimiting
                PROJECTS.put(row[0], new Project(row)); //appending into hashmap
            }
            data.close();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
        }
        return PROJECTS;
    }

    /*
    Used to write the data in PROJECT into csv
     */
    public void writeProjectFile(Map<String, ArrayList<String>> PROJECT){ //Replace ProjectList.csv
        try{
            BufferedWriter obj = new BufferedWriter(new FileWriter("src/main/user_data/ProjectList.csv"));
            obj.write("Project Name,Neighborhood,Type 1,Number of units for Type 1,Selling price for Type 1,Type 2,Number of units for Type 2,Selling price for Type 2,Application opening date,Application closing date,Manager,Officer Slot,Officer\n");
            for (ArrayList<String> user : PROJECT.values()) { //using info in USERS to write file
                String line = String.join(",", user) + "\n";
                obj.write(line);
            }
            obj.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
    Initialise the ENQUIRIES data
     */
    public Map<String, Enquiry> readEnquiryFile(){
        Map<String, Enquiry> ENQUIRIES = new HashMap<>();
        try{
            Scanner data = new Scanner(new File("src/main/user_data/Enquiry.csv"));
            data.nextLine(); //for skipping the title
            while(data.hasNextLine()) {// Going through all user data
                String line = data.nextLine();
                String[] row = line.split(","); //delimiting
                ENQUIRIES.put(row[0], new Enquiry(row[0], row[1], row[2], row[3], row[4]));
                nextEnquiryID =Integer.parseInt(row[0])+1;
            }
            data.close();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
        }
        return ENQUIRIES;
    }

    /*
    Adding Enquiry while in operation
     */
    public Map<String, Enquiry> addEnquiry(String askerID, String projectName, String question, Map<String, Enquiry> ENQUIRIES) {
        String ID = Integer.toString(nextEnquiryID);
        ENQUIRIES.put(ID, new Enquiry(ID, askerID, projectName, question, "NULL"));
        nextEnquiryID++;
        writeEnquiryFile(ENQUIRIES);
        return ENQUIRIES;
    }

    /*
    Write ENQUIRIES into csv
     */
    public void writeEnquiryFile(Map<String, Enquiry> ENQUIRY){ //WRITES using ENQUIRY in Storage
        try{
            BufferedWriter obj = new BufferedWriter(new FileWriter("src/main/user_data/Enquiry.csv"));
            obj.write("ID,Asker ID,Project Name,Question,Reply\n");
            for (Enquiry e : ENQUIRY.values()) { //using info in USERS to write file
                obj.write(e.getID()+","+e.getAskerID()+","+e.getProjectName()+","+e.getQuestion()+","+e.getReply()+"\n");
            }
            obj.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

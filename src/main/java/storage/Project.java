package storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.List;

import users.Applicant;
import users.HDBManager;
import users.HDBOfficer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project {
    private final String projectName;
    private final String neighbourhood;
    private long openingDate; //TODO maybe can use long instead
    private long closingDate;
    private final String createdBy;
    private HashMap<String, Integer> units = new HashMap<>(); // key: flatType, value: number of units
    private HashMap<String, Integer> prices = new HashMap<>();
    private ProjectTeam projectTeam;
    private List<Enquiry> enquiries;
    private boolean projectVisibility;

    public Project(String[] data) {
        /*
        (0)Project Name,(1)Neighborhood,(2)Type 1,(3)Number of units for Type 1,(4)Selling price for Type 1,
        (5)Type 2,(6)Number of units for Type 2,(7)Selling price for Type 2,
        (8)Application opening date,(9)Application closing date,(10)Manager,(11)Officer Slot,(12)Officer
         */
        this.projectName = data[0];
        this.neighbourhood = data[1];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.openingDate = formatter.parse(data[8]).getTime();
            this.closingDate = formatter.parse(data[9]).getTime();
            System.out.println(this.openingDate + " " + this.closingDate + " " + formatter.format(new Date(this.openingDate)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.createdBy = data[11];
        this.units.put(data[2], Integer.parseInt(data[3])); this.prices.put(data[2],Integer.parseInt(data[4]));
        this.units.put(data[5], Integer.parseInt(data[6])); this.prices.put(data[5], Integer.parseInt(data[7]));
        String[] officers = data[12].replaceAll("\"", "").split(",");
        this.projectTeam = new ProjectTeam(officers, data[11], data[10], null);
        //TODO add enquries
//        this.units = new HashMap<>();
//        this.applicants = new ArrayList<>();
//        this.HDBOfficers = new ArrayList<>();
//        this.enquiries = new ArrayList<>();
    }

    public String getProjectName() { return projectName; }
    public String getNeighbourhood() { return neighbourhood; }
    public long getOpeningDate() { return openingDate; }
    public long getClosingDate() { return closingDate; }
    public String getCreatedBy() { return createdBy; }
    public HashMap<String, Integer> getUnits() { return units; }
    public HashMap<String, Integer> getPrices() { return prices; }
    public ProjectTeam getProjectTeam() { return projectTeam; }
    public void setUnits(HashMap<String, Integer> units) {
        this.units = units;
    }

    // method to check flat availability
    public void updateFlatAvailability(String flatType, int numberOfUnits) {
        this.units.put(flatType, numberOfUnits);
    }

    public void toggleProjectVisibility() {
        projectVisibility = !projectVisibility;
    }
}

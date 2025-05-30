package storage;

import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectTeam {
    private final String manager;
    private int maxSlots;
    private List<String> officers;
    private List<String> officersApplying;
    private List<String> officersRejected; //Stores the userID of all UNSUCCESSFUL Officer

    public ProjectTeam(String hdbManager, String slots, String[] officers, String[] officersApplying, String[] officersRejected) {
        this.manager = hdbManager;
        this.maxSlots = Integer.parseInt(slots);

        this.officers = Arrays.stream(officers)
                .map(String::trim)
                .filter(id -> !id.isEmpty())
                .collect(Collectors.toList());

        this.officersApplying = Arrays.stream(officersApplying)
                .map(String::trim)
                .filter(id -> !id.isEmpty())
                .collect(Collectors.toList());

        this.officersRejected = Arrays.stream(officersRejected)
                .map(String::trim)
                .filter(id -> !id.isEmpty())
                .collect(Collectors.toList());
    }

    public void decideOfficerApplication() {
        Ui ui = new Ui();
        System.out.println("Which officer application would you like to decide?");
        for (int i=0; i < officersApplying.size(); i++){
            System.out.println(i+1+") " + officersApplying.get(i));
        }
        try {
            String decidedOfficerID = officersApplying.get(ui.inputInt() - 1);
            if (!decidedOfficerID.isEmpty()) {
                System.out.println("ENTER 1 to ACCEPT, 0 to REJECT " + decidedOfficerID + " officer application?");
                int decision = ui.inputInt();
                if (decision == 0) { //Add the officer into the TEAM(SUCCESSFUL)
                    officersRejected.add(decidedOfficerID);
                    officersApplying.remove(decidedOfficerID);
                } else if (decision == 1) { //Add the officer into the REJECTED LIST(UNSUCCESSFUL)
                    if(officers.size()<maxSlots) { //checking max team size
                        officers.add(decidedOfficerID);
                        officersApplying.remove(decidedOfficerID);
                    }
                    else{
                        System.out.println("Cannot Accept more officer application.");
                    }
                }
            } else {
                System.out.println("DIDN'T Select an officer. Exiting.");
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Officer application does not exist. Exiting.");
        }
    }

    public List<String> getListOfStrings() {
        List<String> list = new ArrayList<>();
        list.add(manager);
        list.add(String.valueOf(maxSlots));
        list.add("\""+String.join(".", officers)+"\"");
        list.add("\""+String.join(".", officersApplying)+"\"");
        list.add("\""+String.join(".", officersRejected)+"\"");
        return list;
    }
    /**
     * @return NRIC of Officers
     */
    public List<String> getOfficers() {
        return officers;
    }

    public List<String> getOfficersApplying() {
        return officersApplying;
    }
    public List<String> getOfficersRejected() {
        return officersRejected;
    }

    public void addOfficerApplying(String officerID) {
        officersApplying.add(officerID);
        System.out.println(officersApplying);
    }
}

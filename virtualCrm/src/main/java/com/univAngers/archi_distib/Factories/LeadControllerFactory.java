package com.univAngers.archi_distib.Factories;

import com.univAngers.archi_distib.Controller.LeadController;

public class LeadControllerFactory {

    private static LeadController leadController;

    public static LeadController getInstance() {
        if(leadController == null) {
            leadController = new LeadController();
        }
        return leadController;
    }

}

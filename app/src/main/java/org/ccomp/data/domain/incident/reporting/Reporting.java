package org.ccomp.data.domain.incident.reporting;

import org.ccomp.data.domain.incident.IncidentCategory;

import java.util.List;

public class Reporting {

    private List<IncidentCategory> incidentCategories;


    public List<IncidentCategory> getIncidentCategories() {
        return incidentCategories;
    }

    public void setIncidentCategories(List<IncidentCategory> incidentCategories) {
        this.incidentCategories = incidentCategories;
    }
}

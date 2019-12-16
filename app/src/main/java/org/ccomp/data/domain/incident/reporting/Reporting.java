package org.ccomp.data.domain.incident.reporting;

import androidx.room.Ignore;

import org.ccomp.data.domain.incident.IncidentCategory;

import java.util.List;

public class Reporting {

    @Ignore
    private List<IncidentCategory> incidentCategories;

    public List<IncidentCategory> getIncidentCategories() {
        return incidentCategories;
    }


}

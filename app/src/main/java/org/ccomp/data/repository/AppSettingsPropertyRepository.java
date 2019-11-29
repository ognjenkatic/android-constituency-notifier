package org.ccomp.data.repository;

import org.ccomp.data.database.dao.AppSettingsPropertyDAO;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class AppSettingsPropertyRepository extends GenericRepository <AppSettingsProperty, AppSettingsOption> {



    @Inject
    public  AppSettingsPropertyRepository(AppSettingsPropertyDAO appSettingsPropertyDAO, ExecutorService executorService){
        this.mainDAO=appSettingsPropertyDAO;
        this.executorService=executorService;
    }

    @Override
    public AppSettingsProperty build(AppSettingsProperty in) {
        return in;
    }

    @Override
    public void dismantle(AppSettingsProperty obj) {

    }

    @Override
    public void saveCallResults(@NotNull List<AppSettingsProperty> items) {
        save(new AppSettingsProperty[items.size()]);
    }
}

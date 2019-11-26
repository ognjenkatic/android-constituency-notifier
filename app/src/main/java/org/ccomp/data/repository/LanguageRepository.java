package org.ccomp.data.repository;

import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.database.dao.WordDAO;
import org.ccomp.service.NetworkAvailabilityService;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

@Singleton
public class LanguageRepository {

    LangDAO langDAO;
    WordDAO wordDAO;
    ExecutorService executorService;
    NetworkAvailabilityService networkAvailabilityService;
}

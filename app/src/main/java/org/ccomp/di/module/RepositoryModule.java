package org.ccomp.di.module;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.database.dao.AppSettingsPropertyDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.database.dao.WordDAO;
import org.ccomp.data.database.dao.mapping.TranslationDAO;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.lang.Translation;
import org.ccomp.data.domain.settings.TLP;
import org.ccomp.data.repository.AppSettingsPropertyRepository;
import org.ccomp.data.repository.EmailReportingRepo;
import org.ccomp.data.repository.EmailReportingRepository;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.data.repository.LanguageRepository;
import org.ccomp.service.NetworkAvailabilityService;
import org.ccomp.service.feed.FeedParserService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public FeedRepository provideFeedRepository(FeedItemDAO feedItemDAO, FeedParserService feedParserService, ExecutorService executorService){
        return new FeedRepository(feedItemDAO,feedParserService,executorService);
    }

    @Provides
    @Singleton
    public EmailReportingRepository provideEmailReportingRepository(@NotNull AppDatabase appDatabase, @NotNull ExecutorService executorService, @NotNull NetworkAvailabilityService networkAvailabilityService) {
        EmailReportingRepository emailReportingRepository = new EmailReportingRepository(appDatabase.emailReportingDAO(), appDatabase.incidentCategoryDAO(), appDatabase.emailReportingIncidentCategoryMappingDAO(), executorService, networkAvailabilityService);

        return emailReportingRepository;
    }

    @Provides
    @Singleton
    public EmailReportingRepo provideEmailReportingRepo(@NotNull AppDatabase appDatabase, @NotNull ExecutorService executorService, @NotNull NetworkAvailabilityService networkAvailabilityService) {

        EmailReportingRepo repo = new EmailReportingRepo(appDatabase.emailReportingDAO(), appDatabase.incidentCategoryDAO(), appDatabase.emailReportingIncidentCategoryMappingDAO(), executorService);
        EmailReporting reporting = new EmailReporting();
        reporting.setAddress("cert@certrs.org");
        reporting.setDefaultTLP(TLP.AMBER);
        reporting.setPgpId("0xEE03B7F3");
        reporting.setPgpFingerprint("6F1F 1A61 EBA9 50A7 E3FC 5C03 1B5A 3A3B EE03 B7F3");
        reporting.setPgpKey("Potpis");
        List<IncidentCategory> categories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            IncidentCategory category = new IncidentCategory();
            category.setId("ID" + i);
            category.setClassDescription("ClassDescription");
            category.setClassName("Class");
            category.setDescription("Description");
            category.setTypeDescription("TypeDescription");
            category.setTypeName("TypeName");
            categories.add(category);

        }
        reporting.setIncidentCategories(categories);

       repo.save(true,reporting);


        return repo;
    }

    @Provides
    @Singleton
    public LanguageRepository provideLanguageRepository(LangDAO langDAO, WordDAO wordDAO, TranslationDAO translationDAO, ExecutorService executorService) {
        LanguageRepository languageRepository = new LanguageRepository(langDAO, wordDAO, translationDAO, executorService);

        String langId = "sr-lat";
        Language lang = new Language();
        lang.setName("Srpski");
        lang.setLangId(langId);
        lang.setDescription("Srpski jezika");
        List<Translation> translations = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Translation translation = new Translation();
            translation.setLangId(langId);
            translation.setWord("Hello" + i);
            translation.setValue("Pozdrav " + i);
            translations.add(translation);
        }
        lang.setTranslations(translations);

        languageRepository.save(true, lang);

        return languageRepository;
    }

    @Provides
    @Singleton
    public AppSettingsPropertyRepository provideAppSettingsPropertyRepository(AppSettingsPropertyDAO appSettingsPropertyDAO, ExecutorService executorService){
        return new AppSettingsPropertyRepository(appSettingsPropertyDAO ,executorService );
    }
}

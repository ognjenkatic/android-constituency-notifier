package org.ccomp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.EmailReportingRepo;
import org.ccomp.data.repository.LanguageRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private LiveData<Resource<List<EmailReporting>>> allEmails;
    private LiveData<Resource<List<Language>>> allLang;

   EmailReportingRepo emailsRepository;
   LanguageRepository languageRepository;




   @Inject
    public HomeViewModel(EmailReportingRepo repository, LanguageRepository languageRepository) {
       // super(application);
        setmText(new MutableLiveData<>());
        getmText().setValue("This is home fragment");
        this.emailsRepository = repository;
        this.setAllEmails(repository.load(false,repository.getDefaultPredicate()));
        this.languageRepository=languageRepository;
        this.setAllLang(languageRepository.load(false,languageRepository.getDefaultPredicate()));

    }







    public LiveData<String> getText() {
        return getmText();
    }



    public void save(EmailReporting emailReporting){
        getEmailsRepository().save(true,emailReporting);
    }
    public void save(Language lang){
       getLanguageRepository().save(true,lang);
    }


    public MutableLiveData<String> getmText() {
        return mText;
    }

    public void setmText(MutableLiveData<String> mText) {
        this.mText = mText;
    }

    public LiveData<Resource<List<EmailReporting>>> getAllEmails() {
        return allEmails;
    }

    public void setAllEmails(LiveData<Resource<List<EmailReporting>>> allEmails) {
        this.allEmails = allEmails;
    }

    public LiveData<Resource<List<Language>>> getAllLang() {
        return allLang;
    }

    public void setAllLang(LiveData<Resource<List<Language>>> allLang) {
        this.allLang = allLang;
    }


    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }

    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public EmailReportingRepo getEmailsRepository() {
        return emailsRepository;
    }

    public void setEmailsRepository(EmailReportingRepo emailsRepository) {
        this.emailsRepository = emailsRepository;
    }
}
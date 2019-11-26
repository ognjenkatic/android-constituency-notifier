package org.ccomp.data.repository;

import androidx.core.util.Pair;

import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.database.dao.WordDAO;
import org.ccomp.data.domain.settings.lang.Language;
import org.ccomp.data.domain.settings.lang.Word;
import org.ccomp.service.NetworkAvailabilityService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

@Singleton
public class LanguageRepository extends GenericRepository<Language,String> {


    WordDAO wordDAO;

    public LanguageRepository(LangDAO langDAO, WordDAO wordDAO, ExecutorService executorService){

        this.mainDAO=langDAO;
        this.wordDAO=wordDAO;
        this.executorService=executorService;

    }

    @Override
    public Language build(Language in) {
        List<Pair<String ,String >> keys=wordDAO.getWordKeysByLangIdSync(in.getLangId());
        List<Word> words=wordDAO.getAllSync(keys);
        in.setWords(words);
        return in;
    }

    @Override
    public void dismantle(Language obj) {
        mainDAO.save(obj);
        if(obj.getWords()!=null){
            wordDAO.save(obj.getWords().toArray(new Word[obj.getWords().size()]));
        }
    }

    @Override
    public void saveCallResults(@NotNull List<Language> items) {
        save(true,items.toArray(new Language[items.size()]));
    }
}

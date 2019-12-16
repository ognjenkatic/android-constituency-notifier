package org.ccomp.data.repository;

import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.database.dao.WordDAO;
import org.ccomp.data.database.dao.mapping.TranslationDAO;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.lang.Translation;
import org.ccomp.data.domain.lang.Word;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LanguageRepository extends GenericRepository<Language,String> {

    WordDAO wordDAO;
   TranslationDAO translationDAO;

   @Inject
    public LanguageRepository(LangDAO langDAO, WordDAO wordDAO, TranslationDAO translationDAO, ExecutorService executorService){

        this.mainDAO=langDAO;
        this.wordDAO=wordDAO;
       this.translationDAO = translationDAO;
        this.executorService=executorService;

    }

    @Override
    public Language build(Language in) {
       if(in!=null) {
           List<Translation> translations = translationDAO.getTranslationByLangIdSync(in.getLangId());
           in.setTranslations(translations);
       }
        return in;
    }

    @Override
    public void dismantle(Language obj) {
       executorService.execute(()->{
           mainDAO.save(obj);
           if(obj.getTranslations()!=null){
               for(Translation translation : obj.getTranslations()){
                   wordDAO.save(new Word(translation.getWord()));
                   translationDAO.insert(translation);
               }
           }
       });

    }

    @Override
    public void saveCallResults(@NotNull List<Language> items) {
        save(true,items.toArray(new Language[items.size()]));
    }
}

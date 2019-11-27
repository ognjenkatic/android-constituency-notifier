package org.ccomp.data.database.dao.mapping;

import androidx.lifecycle.LiveData;

import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.domain.lang.Language;

import java.util.List;

public class MockLangDAO extends LangDAO {

    @Override
    public void insert(Language... objects) {

    }

    @Override
    public void update(Language... objects) {

    }

    @Override
    public void delete(Language... objects) {

    }

    @Override
    public void save(Language... objects) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LiveData<List<Language>> getAll() {
        return null;
    }

    @Override
    public LiveData<List<Language>> getAll(List<String> keys) {
        return null;
    }

    @Override
    public LiveData<Language> get(String key) {
        return null;
    }

    @Override
    public LiveData<List<String>> getKeys() {
        return null;
    }

    @Override
    public List<Language> getAllSync() {
        return null;
    }

    @Override
    public List<Language> getAllSync(List<String> keys) {
        return null;
    }

    @Override
    public Language getSync(String key) {
        return null;
    }

    @Override
    public List<String> getKeysSync() {
        return null;
    }
}

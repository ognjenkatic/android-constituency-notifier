package org.ccomp.data.repository;

import org.ccomp.data.database.dao.IDAO;
import org.ccomp.service.IService;

import java.util.List;

public abstract class GenericRepository<T,K> {

    IDAO dao;
    IService service;


    public GenericRepository(){

    }

    public <T,K>GenericRepository( IDAO<T,K> dao,IService<T> service){
        this.dao=dao;
        this.service=service;
    }

    public List<T> doWork(boolean online){

        if(online){
            List<T> tmpList=fetch();
            save(tmpList);
        }
        return load();
    }

    public  List<T> fetch(){
        if(service!=null) {
            return service.fetch();
        }else{
            return null;
        }
    }

    public void save( List<T> objects){
        if(objects!=null && dao!=null) {
            dao.save(objects.toArray());
        }
    }

    public List<T> load(){
        if(dao!=null) {
            return dao.getAll();
        }else{
            return null;
        }
    }




}

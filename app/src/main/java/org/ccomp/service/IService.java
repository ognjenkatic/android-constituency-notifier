package org.ccomp.service;

import java.util.List;
import java.util.Map;

public interface IService<T> {

    List<T> fetch();
    List<T> fetch(Map<String,Object> params);
}

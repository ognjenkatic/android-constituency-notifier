package org.ccomp.data.repository;



public interface Predicate<T> {

    boolean test(T obj);
}

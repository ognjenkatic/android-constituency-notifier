package org.ccomp.data.repository;

public class DefaultPredicate<T>  implements Predicate<T>{
    @Override
    public boolean test(T obj) {
        return true;
    }
}

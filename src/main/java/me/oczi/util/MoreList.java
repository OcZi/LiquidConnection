package me.oczi.util;

import java.util.ArrayList;
import java.util.List;

public interface MoreList {

    @SafeVarargs
    static <E> List<E> joinList(List<E>... lists) {
        List<E> list = new ArrayList<>();
        for (List<E> eList : lists) {
            list.addAll(eList);
        }
        return list;
    }
}

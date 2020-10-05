package me.oczi.api.collections;

import java.util.Set;

public class CheckedSetImpl<T>
    implements CheckedSet<T> {
    private final Set<T> whiteList;
    private final Set<T> blackList;

    public CheckedSetImpl(Set<T> whiteList,
                          Set<T> blackList) {
        this.whiteList = whiteList;
        this.blackList = blackList;
    }

    @Override
    public void addToBlackSet(T object) {
        whiteList.remove(object);
        blackList.add(object);
    }

    @Override
    public void addToWhiteSet(T object) {
        blackList.remove(object);
        whiteList.add(object);
    }

    @Override
    public boolean isBlackSetted(T object) {
        return blackList.contains(object);
    }

    @Override
    public boolean isWhiteSetted(T object) {
        return whiteList.contains(object);
    }

    @Override
    public boolean isUnchecked(T object) {
        return !isBlackSetted(object) && !isWhiteSetted(object);
    }

    @Override
    public boolean isChecked(T object) {
        return isBlackSetted(object) || isWhiteSetted(object);
    }

    @Override
    public Set<T> getBlackSet() {
        return blackList;
    }

    @Override
    public Set<T> getWhiteSet() {
        return whiteList;
    }
}

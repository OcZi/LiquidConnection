package me.oczi.api.collections;

import java.util.Set;

public class CheckedSetImpl<T>
    implements CheckedSet<T> {
    private final Set<T> whiteSet;
    private final Set<T> blackSet;

    public CheckedSetImpl(Set<T> whiteSet,
                          Set<T> blackSet) {
        this.whiteSet = whiteSet;
        this.blackSet = blackSet;
    }

    @Override
    public void addToBlackSet(T object) {
        whiteSet.remove(object);
        blackSet.add(object);
    }

    @Override
    public void addToWhiteSet(T object) {
        blackSet.remove(object);
        whiteSet.add(object);
    }

    @Override
    public boolean isBlackSetted(T object) {
        return blackSet.contains(object);
    }

    @Override
    public boolean isWhiteSetted(T object) {
        return whiteSet.contains(object);
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
        return blackSet;
    }

    @Override
    public Set<T> getWhiteSet() {
        return whiteSet;
    }
}

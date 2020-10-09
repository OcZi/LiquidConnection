package me.oczi.api.collections;

import java.util.HashSet;
import java.util.Set;

/**
 * A Set master that controls two
 * Sets called WhiteSet and BlackSet.
 *
 * <p>Used to maintain order between
 * the objects available and unavailable.</p>
 * @param <T> Type.
 */
public interface CheckedSet<T> {

    static <T> CheckedSet<T> newCheckedSet() {
        return new CheckedSetImpl<>(
            new HashSet<>(), new HashSet<>());
    }

    static <T> CheckedSet<T> newCheckedSet(Set<T> whiteSet,
                                           Set<T> blackSet) {
        return new CheckedSetImpl<>(whiteSet, blackSet);
    }

    void addToBlackSet(T object);

    void addToWhiteSet(T object);

    boolean isBlackSetted(T object);

    boolean isWhiteSetted(T object);

    boolean isUnchecked(T object);

    boolean isChecked(T object);

    Set<T> getBlackSet();

    Set<T> getWhiteSet();
}

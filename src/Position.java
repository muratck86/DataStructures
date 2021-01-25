/**
 * The Position interface which will be a wrapper for E
 * @param <E> Generic type
 */
interface Position<E>{
    /**
     * returns the data being kept in this position.
     * @return the stored data
     * @throws IllegalStateException if position is no longer valid
     */
    E getElement() throws IllegalStateException;
}

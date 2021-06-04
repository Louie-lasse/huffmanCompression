public interface Tree extends Comparable<Tree>{
    int getOccurrences();
    //TODO add bits to read()
    char read(BitField bits);
    boolean isChar(BitField bits);
    int size();

    @Override
    default int compareTo(Tree o) {
        int t1 = getOccurrences();
        int t2 = o.getOccurrences();
        return Integer.compare(t1, t2);
    }
}

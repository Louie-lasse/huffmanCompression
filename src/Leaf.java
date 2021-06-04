public class Leaf implements Tree{

    private int occurrences = 1;

    private final char c;

    Leaf(char c){
        this.c=c;
    }

    boolean is(char c){
        return this.c == c;
    }

    void add(){ occurrences++; }

    @Override
    public int getOccurrences() {
        return occurrences;
    }

    @Override
    public char read(BitField bits) {
        return c;
    }

    @Override
    public boolean isChar(BitField bits) {
        return true;
    }

    @Override
    public int size(){
        return 32+8;
    }
}

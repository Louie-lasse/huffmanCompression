public class Branch implements Tree{
    Tree left;
    Tree right;

    Branch(Tree lower, Tree upper){
        left = lower;
        right = upper;
    }

    public int getOccurrences(){
        return left.getOccurrences() + right.getOccurrences();
    }

    @Override
    public char read(BitField bits) {
        if (!bits.removeFirst()) return left.read(bits);
        return right.read(bits);
    }

    public boolean isChar(BitField bits){
        try {
            if (!bits.removeFirst()) return left.isChar(bits);
            return right.isChar(bits);
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public int size() {
        return left.size() + right.size() + 64 + 64;
    }
}

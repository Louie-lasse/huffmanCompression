public class TreeIterator {

    private BitField path;

    private final Tree tree;

    TreeIterator(Tree tree){
        this.tree = tree;
        path = new BitField();
    }

    public boolean hasNext(){
        if (path.size()==0) return true;
        return (path.indexOfLastZero()!=-1);
    }

    public BitField getNext(){
        int indexOfLastZero = path.indexOfLastZero();
        if (indexOfLastZero!=-1) {
            path.cutAt(indexOfLastZero);
            path.add(1);
        }
        findChar();
        return new BitField(path);
    }

    private void findChar() {
        while (!tree.isChar(new BitField(path))){
            path.add(0);
        }
    }
}

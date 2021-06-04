import java.util.*;

public class Compressor {

    private int stringSize;

    private Tree tree;

    private BitField compressedString;

    Compressor(){}

    Compressor(String string){
        compress(string);
    }

    void compress(String string){
        stringSize = string.toCharArray().length*8;
        HashMap<Character, BitField> map = createAndMapTree(string);
        createBitField(map, string);
    }

    int getCompressedSize(){
        return compressedString.size();
    }

    int getFullCompressedSize(){
        return tree.size() + getCompressedSize();
    }

    int getUncompressedSize(){
        return stringSize;
    }

    String uncompressed(){
        BitField readable = new BitField(compressedString);
        StringBuilder sb = new StringBuilder();
        while (readable.size()!=0){
            sb.append(tree.read(readable));
        }
        return sb.toString();
    }

    BitField getCompressedString(){
        return compressedString;
    }

    private HashMap<Character, BitField> createAndMapTree(String string){
        PriorityQueue<Tree> structure = getStructure(string);
        tree = createTree(structure);
        return mapTree();
    }

    private PriorityQueue<Tree> getStructure(String string){
        PriorityQueue<Tree> leaves = new PriorityQueue<>();
        Map<Character, Leaf> map = new HashMap<>();
        for (char c: string.toCharArray()){
            add(map, leaves, c);
        }
        return leaves;
    }

    private void add(Map<Character, Leaf> map, PriorityQueue<Tree> leaves, char c){
        if (map.get(c)==null){
            Leaf leaf = new Leaf(c);
            map.put(c, leaf);
            leaves.add(leaf);
        } else {
            map.get(c).add();
        }
    }


    private Tree createTree(PriorityQueue<Tree> structure){
        if (structure.size() == 1) return structure.poll();
        Branch b = new Branch(structure.poll(), structure.poll());
        structure.add(b);
        return createTree(structure);
    }

    private static class TreeComparator implements Comparator<Tree>{
        @Override
        public int compare(Tree t1, Tree t2){
            int size1 = t1.getOccurrences();
            int size2 = t2.getOccurrences();
            return Integer.compare(size1, size2);
        }
    }

    private HashMap<Character, BitField> mapTree(){
        HashMap<Character, BitField> map = new HashMap<>();
        TreeIterator iterator = new TreeIterator(tree);
        BitField path;
        while (iterator.hasNext()){
            path = iterator.getNext();
            map.put(tree.read(new BitField(path)), new BitField(path));
        }
        return map;
    }

    private void createBitField(HashMap<Character, BitField> map, String string){
        compressedString = new BitField();
        BitField path;
        for(Character c: string.toCharArray()){
            path = map.get(c);
            compressedString.add(path);
        }
    }

}

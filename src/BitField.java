import java.util.ArrayList;
import java.util.List;

public class BitField {
    private List<Boolean> values = new ArrayList<>();

    BitField(){}

    BitField(int i){
        values.add(i>0);
    }

    BitField(BitField b){ values = new ArrayList<>(b.values); }

    boolean removeFirst(){
        return values.remove(0);
    }

    void add(int i){
        values.add(i>0);
    }

    void add(BitField bits){
        values.addAll(bits.values);
    }

    int indexOfLastZero(){
        int index = values.size()-1;
        while (index >= 0){
            if (!values.get(index)){
                return index;
            }
            index--;
        }
        return -1;
    }

    void cutAt(int index){
        List<Boolean> cutList = new ArrayList<>();
        for (int i = 0; i < index; i++){
            cutList.add(values.get(i));
        }
        values = cutList;
    }

    void set(int index){
        values.set(index, true);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Boolean bool: values){
            sb.append(bool? '1': '0');
        }
        return sb.toString();
    }

    public int size(){
        return values.size();
    }

}

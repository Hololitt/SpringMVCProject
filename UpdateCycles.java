package Training;

import java.util.ArrayList;
import java.util.List;

public class UpdateCycles {
    public static void main(String[] args) {
        List<Machine> list = new ArrayList<>();
        list.add(new Machine());
        list.add(new Machine2());
        list.add(new Machine3());
        for(Machine machines : list){
            System.out.println(machines.getId());
        }

    }
}
class Machine{
    public int getId(){
        return 1;
    }
}
class Machine2 extends Machine{
    public int getId(){
        return 2;
    }
}
class Machine3 extends Machine{
    public int getId(){
        return 3;
    }
}

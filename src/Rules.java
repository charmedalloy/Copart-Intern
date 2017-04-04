/**
 * Created by avaljot on 04/04/17.
 */
public class Rules {
    int cusId;
    int outOfState;
    int[] yard;

    public Rules(){

    }
    public Rules(int cusId,int outOfState,int[] yard){
        this.cusId = cusId;
        this.outOfState = outOfState;
        this.yard=yard;
    }
}

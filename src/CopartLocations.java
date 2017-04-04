/**
 * Created by avaljot on 04/04/17.
 */
public class CopartLocations {
    String stateCode;
    String city;
    String address;
    String zipCode;
    String yardNo;
    String phoneNumber;


    public CopartLocations(String stateCode, String city, String address, String zipCode, String yardNo, String phoneNumber) {
        this.stateCode = stateCode;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.yardNo = yardNo;
        this.phoneNumber = phoneNumber;

    }

//    public String toString() {
//        return this.stateCode + " " + this.state + " " + this.phoneNumber;
//    }
}

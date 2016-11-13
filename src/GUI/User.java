package GUI;
/**
 * Created by AddisonSasko on 2016-11-11.
 */
public class User {

    public String name;
    public int privilage;


    /*

    DIFFERENT LEVELS OF PRIVILAGE:
        1 - Patient
        2 - Nurse
        3 - Doctor
        4 - Receptionist

     */

    public User(String name, int privilage){
        this.name = name;
        this.privilage = privilage;
    }

    public int getPrivilage(){
        return this.privilage;
    }


}

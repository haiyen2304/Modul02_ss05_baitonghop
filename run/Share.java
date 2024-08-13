package BTVN_SS05_TH.run;

import java.util.Scanner;

public class Share {
    public static int inputChoice(Scanner sc) {
        System.out.println("nhập vào lựa chọn :");
        ;
        while (true) {
            try{
                return Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                System.out.println("lựa chọn phải là số");
            }
        }
    }
}

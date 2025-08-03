import Sorting.*;
public class App {

    public static void main(String[] args) {
        String [] S = {"XCVCX","BEEN","CAW","POM"};
        Integer [] D = {15,20,100,80,40,60,32,42,75};
        Float [] F = {4.2F,5.9F,1.2F,7.9F};
        Character [] C = {'A','B','D','X','Q','N'};
        InsertionSort.Sort(S);
        InsertionSort.Sort(D);
        InsertionSort.Sort(F);
        InsertionSort.Sort(C);
        System.out.println("InsertionSort arrayes : ");
        for (String ss : S)
        {
            System.out.print(ss+" : ");
        }
        System.out.println();
        for (Integer dd  : D)
        {
            System.out.print(dd+" : ");
        }
        System.out.println();
        for (Float ff : F)
        {
            System.out.print(ff+" : ");
        }
        System.out.println();
        for (Character cc : C)
        {
            System.out.print(cc+" : ");
        }
//        System.out.println("\n before using insertion sort :");
//        String [] SA = {"XCVCX","BEEN","CAW","POM"};
//        Integer [] DA = {15,20,100,80,40,60,32,42,75};
//        Float [] FA = {4.2F,5.9F,1.2F,7.9F};
//        Character [] CA = {'A','B','D','X','Q','N'};
//        for (String ss : SA)
//        {
//            System.out.print(ss+" : ");
//        }
//        System.out.println();
//        for (Integer dd  : DA)
//        {
//            System.out.print(dd+" : ");
//        }
//        System.out.println();
//        for (Float ff : FA)
//        {
//            System.out.print(ff+" : ");
//        }
//        System.out.println();
//        for (Character cc : CA)
//        {
//            System.out.print(cc+" : ");
//        }
//        System.out.println("\n using selction sort :");
//        SelctionSort.Sort(SA);
//        SelctionSort.Sort(DA);
//        SelctionSort.Sort(FA);
//        SelctionSort.Sort(CA);
//        for (String ss : SA)
//        {
//            System.out.print(ss+" : ");
//        }
//        System.out.println();
//        for (Integer dd  : DA)
//        {
//            System.out.print(dd+" : ");
//        }
//        System.out.println();
//        for (Float ff : FA)
//        {
//            System.out.print(ff+" : ");
//        }
//        System.out.println();
//        for (Character cc : CA)
//        {
//            System.out.print(cc+" : ");
//        }
    }
}

package BTVN_SS05_TH.run;

import java.util.Scanner;
import BTVN_SS05_TH.entity.Categories;
import BTVN_SS05_TH.entity.Product;
import BTVN_SS05_TH.service.CategorySevice;
import BTVN_SS05_TH.service.ProductService;
public class ShopManagement {

    public  static Categories[] arrCategories=new Categories[100];
    public  static int currentIndexCate=-1;
    //arrCategories là tên mảng, có 100 phần tử, mang kiểu dữ liệu là Categories.

    //Tóm lại, dòng code này tạo ra một mảng arrCategories với 100 phần tử, mỗi phần tử sẽ là một đối tượng thuộc lớp Categories.
    public  static Product[] arrProducts=new Product[100];
    public  static int currentProIndex=-1;
    public static String proId="";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CategorySevice categorySevice=new CategorySevice();
        ProductService productService=new ProductService();
        int choice;
        while(true){
            System.out.println("""
                  ---------------------------SHOP MENU---------------------------\s
                  1. Quản lý danh mục sản phẩm.
                  2. Quản lý sản phẩm.
                  3. Thoát.""");
            choice = inputChoice(sc);
            switch (choice){
                case 1:
                    categorySevice.categoryManagement(sc,arrCategories,arrProducts,currentIndexCate);
                    break;
                case 2:
                    productService.productManagement(sc,arrProducts,currentProIndex,arrCategories,proId);
                    break;
                case 3:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static int inputChoice(Scanner sc) {

        System.out.print("Nhập vào lựa chọn (int): ");
        while (true) {
            try{
                return sc.nextInt();
            }catch (Exception e){
                System.out.print("Nhập lại lựa chọn (int): ");
                sc.nextLine();
            }
        }
    }
}

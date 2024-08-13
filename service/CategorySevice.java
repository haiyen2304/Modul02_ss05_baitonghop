package BTVN_SS05_TH.service;
import BTVN_SS05_TH.entity.Product;
import BTVN_SS05_TH.run.Share;
import java.util.Scanner;
import BTVN_SS05_TH.run.ShopManagement;
import BTVN_SS05_TH.entity.Categories;


public class CategorySevice {

    public void categoryManagement(Scanner scanner, Categories[] arrCategories, Product[] arrProducts,int currentIndex) {
        int choice;
        while(true) {

            System.out.println("""
                    ---------------------------CATEGORIES MENU---------------------------\s
                    1. Nhập thông tin các danh mục.
                    2. Hiển thị thông tin các danh mục.
                    3. Cập nhật thông tin danh mục.
                    4. Xóa danh mục.
                    5. Cập nhật trạng thái danh mục.
                    6. Thoát.""");

            choice= ShopManagement.inputChoice(scanner);

            switch(choice) {
                case 1:
                    try {
                        System.out.print("\n(1).Hãy nhập số danh mục mà bạn muốn nhập: ");
                        int n = scanner.nextInt();

                        for (int index = 0; index < n; index++) {
                            currentIndex++;
                            Categories cate = new Categories();
                            System.out.println("============" + index + "============");
                            cate.inputDataCategory(scanner, arrCategories, currentIndex);
                            System.out.println("============" + index + "============ \n");

                            ShopManagement.arrCategories[currentIndex] = cate;

                        }
                    } catch (Exception e) {
                        System.out.println("Lựa chọn phải là số ...." + e.getMessage());
                    }

                    break;
                case 2:

                    System.out.println("\n(2). Hiển thị thông tin các danh mục");
                    for(Categories c: ShopManagement.arrCategories){
                        if(c!=null)
                            c.displayData();
                    }
                    break;
                case 3:
                    System.out.println("\n(3). Cập nhật thông tin danh mục.");
                    // validate khi input
                    int idUpdate = inputIdUpdate(scanner);

                    boolean checkIndexUpdate = false;
                    int indexUpdate = -1;

                    for(Categories c: ShopManagement.arrCategories){
                        indexUpdate++;// để lấy vị trí, sau đó truyền thông tin nhập vào vị trí đó
                        if(c != null && idUpdate == c.getCategoryId()) {
                            checkIndexUpdate = true;
                            break;
                        }
                    }

                    if(!checkIndexUpdate){
                        System.out.println(ShopManagement.ANSI_RED +  "ID danh mục mà bạn vừa nhập không tồn tại ... " + ShopManagement.ANSI_RESET);
                    }else {
                        System.out.println("Nhập lại thông tin cho danh mục có id = " + idUpdate);

                        Categories c = new Categories();
                        c.setCategoryId(idUpdate);
                        c.updateDataCategory(scanner, arrCategories, idUpdate);

                        arrCategories[indexUpdate] = c;
                    }
                    break;
                case 4:
//   tư tưởng code:
//   B1: biến kiểm tra=false nếu thấy thì chuyển thành true
//   B2:// for mảng sản phẩm, nếu product nào có CategoryId== id nhập xóa, thì hiển thị ra không thể xóa, nếu k có thì thực hiện xóa
//   B3: tạo ra mảng mới, để chứa các phần tử mới không bao gồm những thằng null ở mảng cũ,
//   đồng thời nếu có ID trùng với ID muốn xóa thì bỏ qua thằng ,
//   nếu có ID không trùng với ID muốn xóa thì nhét vào mảng mới
                    System.out.println("\n(4). Xóa danh mục");
                    System.out.println("hãy nhập Id danh mục mà bạn muốn xóa");
                    int idDelete = inputIdUpdate(scanner);
                    boolean checkIndexDelete = false;
                    for(Categories c: ShopManagement.arrCategories){
                        if(c != null &&  idDelete== c.getCategoryId()){
                            checkIndexDelete = true;
                            break;
                        }
                    }


                    if(!checkIndexDelete){
                        System.out.println(ShopManagement.ANSI_RED +  "ID danh mục mà bạn vừa nhập không tồn tại ... " + ShopManagement.ANSI_RESET);
                    }else {
     //   B2:// for mảng sản phẩm, nếu product nào có CategoryId== id nhập xóa, thì hiển thị ra không thể xóa, nếu k có thì thực hiện xóa
                        boolean check=false;
                        for (Product p : ShopManagement.arrProducts){
                            if(p != null && idDelete == p.getCategoryId()){
                                check=true;
                                break;
                               }
                            }
                        if(check){
                            System.out.println(ShopManagement.ANSI_RED + "có sản phẩm trong mã danh mục này, không thể xóa"+ ShopManagement.ANSI_RESET);
                            break;
                        }else {
                            Categories[] newArrCategories = new Categories[100];
                            int j=-1;
                            for(Categories c: ShopManagement.arrCategories){
                                if(c!=null){
                                    if(c.getCategoryId()==idDelete){
                                        continue;
                                    }else {
                                        j++;
                                        newArrCategories[j] = c;
                                    }
                                }
                            }
                            ShopManagement.arrCategories=newArrCategories;
                            System.out.println("Đã xóa thông tin cho danh mục có id = " + idDelete);
                        }
                    }
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("\n(5). Cập nhật trạng thái danh mục");
                    int inputIdStatus= inputIdUpdate(scanner);
                    boolean checkStatus = false;
                    int index=-1;
                    for(Categories c: ShopManagement.arrCategories){
                        index++;
                        if(c != null && c.getCategoryId()==inputIdStatus){
                            checkStatus = true;
                            break;
                        }
                    }
                    if(!checkStatus){
                        System.out.println(ShopManagement.ANSI_RED +  "ID danh mục mà bạn vừa nhập không tồn tại ... " + ShopManagement.ANSI_RESET);
                    }else {
                        ShopManagement.arrCategories[index].setCategoryStatus(!arrCategories[index].getCategoryStatus());
                        System.out.println("Cập nhật thành công thông tin Status cho danh mục có id = " + inputIdStatus);
                        System.out.println("trạng thái hiện tại sau khi cập nhật của danh mục có id = "+inputIdStatus+" là "+ShopManagement.arrCategories[index].getCategoryStatus());
                    }



                    break;
                case 6:
                    System.out.println("(6). Thoát");
                    return;
                default:
                    System.out.println("Hãy nhập lựa chọn (1 - 6)");
                    break;
            }
        }
    }

    public int inputIdUpdate(Scanner scanner) {
        System.out.print("Nhập ID danh mục mà bạn muốn cập nhật: ");
        while (true) {
            try{
                return scanner.nextInt();
            }catch (Exception e){
                System.out.print("Nhập lại idUpdate (int): ");
                scanner.nextLine();
            }
        }
    }
}

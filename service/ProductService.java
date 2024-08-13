package BTVN_SS05_TH.service;

import BTVN_SS05_TH.entity.Categories;
import BTVN_SS05_TH.entity.Product;
import BTVN_SS05_TH.run.ShopManagement;
import java.util.Scanner;

public class ProductService {
    public void productManagement(Scanner sc, Product[] arrProducts, int currentProIndex, Categories[] arrCategories,String proId) {
        while (true){
            System.out.println("---------------------------PRODUCT MANAGEMENT---------------------------\n" +
                    "\n" +
                    "1.Nhập thông tin các sản phẩm\n" +
                    "2.Hiển thị thông tin các sản phẩm\n" +
                    "3.Sắp xếp các sản phẩm theo giá\n" +
                    "4.Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5.Xóa sản phẩm theo mã sản phẩm\n" +
                    "6.Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7.Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8.Thoát\n" +
                    "Lưu ý: Khi chọn 8, quay lại menu SHOP MANAGEMENT");
            int choice = ShopManagement.inputChoice(sc);
            switch (choice) {
                case 1:
                    try{System.out.println("(1). Nhập thông tin các sản phẩm\n" +
                            "Hãy nhập số sản phẩm mà bạn muốn nhập");
                        int n=sc.nextInt();
                        for ( int index = 0 ; index < n;index ++) {
                            currentProIndex++;
                            Product pro=new Product();
                            System.out.println("============" + index + "============");
                            pro.inputDataProduct(sc,arrProducts,currentProIndex,arrCategories);
                            System.out.println("============" + index + "============\n");
                            ShopManagement.arrProducts[currentProIndex]=pro;

                        }
                    }catch (Exception e){
                        System.out.println("Lựa chọn phải là số...." + e.getMessage());
                    }
                    break;


                case 2:
                    System.out.println("(2). Hiển thị thông tin các sản phẩm");
                    for(Product d : ShopManagement.arrProducts){
                        if(d!=null){
                            d.displayProduct();
                        }
                    }
                    break;


                case 3:
                    System.out.println("(3). Sắp xếp các sản phẩm theo giá");
                    // sau khi sắp xếp
                    bubbleSortPrice(arrProducts);
                    for(Product d : ShopManagement.arrProducts){
                        if(d!=null){
                            d.displayProduct();
                        }
                    }
                    break;


                case 4:
                    if (sc.hasNextLine()){
                        sc.nextLine();
                    }
                    System.out.println("(4). Cập nhật thông tin sản phẩm theo mã sản phẩm");
                    String proIdUpdate;
                    while (true){
                        proIdUpdate=sc.nextLine();
                        boolean checkIndexUpdate = false;
                        int indexUpdate = -1;
                        int index=0;
                        for(Product d : ShopManagement.arrProducts){
                            indexUpdate++;
                            if(d!=null && d.getProductId().equalsIgnoreCase(proIdUpdate)){
                                checkIndexUpdate = true;
                                index=indexUpdate;
                                break;
                            }
                        }
                        if(!checkIndexUpdate){
                            System.out.println(ShopManagement.ANSI_RED +  "ID sản phẩm mà bạn vừa nhập không tồn tại ... " + ShopManagement.ANSI_RESET);
                            System.out.println("Nhập lại ID sản phẩm : ");
                        }else {
                            System.out.println("Cập nhập lại thông tin cho sản phẩm có id = "+proIdUpdate);
                            Product d=new Product();
                            d.setProductId(proIdUpdate);
                            d.updateProduct(sc,arrProducts,currentProIndex,arrCategories,proIdUpdate);
                            arrProducts[index]=d;
                            break;
                        }
                    }
                case 5:
                    if (sc.hasNextLine()){
                        sc.nextLine();
                    }
                    System.out.println("(5). Xóa sản phẩm theo mã sản phẩm");
                    System.out.println("hãy nhập Id sản phẩm mà bạn muốn xóa");
                    String idDelete ;
                    boolean checkIndexUpdate2;
                    while (true){

                        idDelete=sc.nextLine();
                        checkIndexUpdate2 = false;
                        for(Product d : ShopManagement.arrProducts){
                            if(d!=null && idDelete.trim().equalsIgnoreCase(d.getProductId())){
                                checkIndexUpdate2=true;
                                break;
                            }
                        }
                        if(!checkIndexUpdate2){
                            System.out.println(ShopManagement.ANSI_RED +  "ID sản phẩm mà bạn vừa nhập không tồn tại ... " + ShopManagement.ANSI_RESET);
                            System.out.println("Hãy nhập lại : ");
                        }else {
                            Product[] newArrProducts=new Product[100];
                            int j=-1;
                            for (Product d : ShopManagement.arrProducts) {
                                if(d!=null){
                                    if(idDelete.trim().equalsIgnoreCase(d.getProductId())){
                                        continue;
                                    }else {
                                        j++;
                                        newArrProducts[j]=d;
                                    }
                                }
                            }
                            ShopManagement.arrProducts=newArrProducts;
                            System.out.println("Đã xóa thông tin cho sản phẩm có id = " + idDelete);
                            break;
                        }
                    }break;
                case 6:
                    if (sc.hasNextLine()){
                        sc.nextLine();
                    }
                    System.out.println("(6). Tìm kiếm các sản phẩm theo tên sản phẩm");
                    System.out.println("Hãy nhập tên sản phẩm mà bạn muốn tìm");
                    String search=sc.nextLine();
                    boolean checkFindName=false;
                    for(Product d : ShopManagement.arrProducts){
                        if(d!=null &&(search.equalsIgnoreCase(d.getProductName()))){
                                checkFindName=true;
                                d.displayProduct();
                                break;
                        }
                    }
                    if(!checkFindName){
                        System.out.println(ShopManagement.ANSI_RED +"Tên sản phẩm mà bạn muốn tìm không tồn tại"+ ShopManagement.ANSI_RESET);
                        System.out.println("hãy nhập lại");
                    }
                    break;
                case 7:
                    System.out.println("(7). Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím");
                    int a,b;
                    while (true){
                        System.out.println("Nhập khoảng giá đầu : ");
                        a=ShopManagement.inputChoice(sc);
                        System.out.println("Nhập khoảng giá cuối : ");
                        b=ShopManagement.inputChoice(sc);
                        if(a<b && a>=0){
                            for(Product d : ShopManagement.arrProducts){
                                if(d!=null && d.getPrice()<=b && d.getPrice()>=a ){
                                    d.displayProduct();
                                }
                            }
                            break;
                        }else {
                            System.out.println(ShopManagement.ANSI_RED + "Khoảng giá bạn nhập không hợp lệ, vui lòng nhập lại a<b và a>0"+ ShopManagement.ANSI_RESET);
                            System.out.println("hãy nhập lại");
                        }
                    }
                    break;



                case 8:
                    System.out.println("(8). Thoát");
                    return;

                default:
                    System.out.println("Hãy nhập lựa chọn (1 - 8)");
                    break;


            }

        }

    }

    public void  bubbleSortPrice(Product[] arrProducts){
        int n = arrProducts.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrProducts[j]!=null  && arrProducts[j+1]!=null && arrProducts[j].getPrice() > arrProducts[j + 1].getPrice()) {
                    // hoán đổi
                    // do j chạy đến 99 nến cần xét j+1 khác null, nếu không j so sánh với j+1 đang null sẽ bị lỗi
                    Product temp = arrProducts[j];
                    arrProducts[j] = arrProducts[j + 1];
                    arrProducts[j + 1] = temp;
                }
            }
        }
    }
}

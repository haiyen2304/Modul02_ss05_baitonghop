package BTVN_SS05_TH.entity;

import BTVN_SS05_TH.run.ShopManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId,productName,description;
    private float price;
    private int categoryId,productStatus;
    private Date created;

    public Product() {

    }
    public Product(String productId, String productName, String description, float price, int categoryId, Date created,int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.created = created;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public  void  inputDataProduct(Scanner sc, Product[] arrProducts, int currentProIndex, Categories[] arrCategories){
        this.productId=inputProductId(sc,arrProducts,currentProIndex);
        this.productName = inputProductName(sc,arrProducts,currentProIndex);
        this.description = inputDescription(sc);
        this.price = inputPrice(sc);
        this.categoryId = inputCategoryId(sc,arrCategories);
        this.created = inputCreated(sc);
        this.productStatus = inputProductStatus(sc);

    }
    public void updateProduct(Scanner sc, Product[] arrProducts, int currentProIndex, Categories[] arrCategories,String proId) {
        this.productName = inputProductName(sc,arrProducts,currentProIndex);
        this.description = inputDescription(sc);
        this.price = inputPrice(sc);
        this.categoryId = inputCategoryId(sc,arrCategories);
        this.created = inputCreated(sc);
        this.productStatus = inputProductStatus(sc);
    }

    public String inputProductId(Scanner sc,Product[] arrProducts,int currentProIndex) {
        if (sc.hasNextLine()){
            sc.nextLine();
        }
        String productId;
        while (true){
            System.out.println("Nhập vào mã sản phẩm (Cxxx | Sxxx | Axxx): ");
            productId = sc.nextLine();
            if(productId.matches("^[CSA]\\w{3}$")){
                //-> [CSA] -> C hoặc S hoặc A là 1 ký tự \\w bất kỳ ký tự nào {3} 3 ký tự
                boolean isExist = false;
                for (Product arrProduct : arrProducts) {
                    if (arrProduct != null) {
                        if (arrProduct.getProductId().equals(productId)) {
                            isExist = true;
                            break;
                        }
                    }
                }
                if(!isExist){
                    return productId;
                }else {
                    System.err.println("Mã sản phẩm đã bị trùng");
                }
            }
        }

    }

    public String inputProductName(Scanner sc,Product[] arrProducts,int currentProIndex) {
        String inputProductName ;
        System.out.print("Nhập tên Sản phẩm có từ 10-50 ký tự, không được trùng lặp (String): ");
        while (true){
            inputProductName=sc.nextLine().trim();
            if(inputProductName.length()>50||inputProductName.length()<10){
                System.out.println("Tên Sản phẩm có độ dài từ 10-50 ký tự");
            }else {
                 // check kiểm tra độ trùng lặp của tên
                boolean check=true;
                for(int i=0;i<currentProIndex;i++){
                   // Cả đoạn code inputCategoryName.trim().equalsIgnoreCase(arrCategories[i].getCategoryName()) sẽ kiểm tra
                    // xem tên danh mục mà người dùng nhập vào (sau khi loại bỏ khoảng trắng) có trùng khớp (không phân biệt chữ hoa hay chữ thường)
                    // với tên danh mục của phần tử thứ i trong mảng arrCategories hay không.
                    if(inputProductName.trim().equalsIgnoreCase(arrProducts[i].getProductName())){
                        check=false;
                        break;
                    }
                }
                if(check){
                    return inputProductName;
                }else {
                    System.out.print("Tên sản phẩm bị trùng, vui lòng nhập lại: ");
                    if (sc.hasNextLine()){
                        sc.nextLine();
                    }
                }
            }
        }
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Nhập mô tả sản phẩm (String): ");
        return sc.nextLine();
    }

    public float inputPrice(Scanner sc) {
        System.out.println("nhập giá sản phẩm");
        // cách 1 :tự làm
        float price;
        while (true){
            try{
                price = sc.nextFloat();
                if (price < 0) {
                    System.out.println("Giá  phải là số lớn hơn 0 : ");
                }else {
                    return price;
                }
            }catch (Exception ex){
                System.out.println(ShopManagement.ANSI_RED +" Lựa chọn phải là số : " + ShopManagement.ANSI_RESET);
                System.out.println("Hãy nhập lại giá : ");
                sc.nextLine();
            }
        }
    }



        //cách 2:cách thầy BÌnh
//        String priceStr ;
//        while (true){
//            priceStr = sc.nextLine();
//            if(priceStr.trim().isEmpty()){
//                System.out.println("không được để trống");
//            }else {
//                // sẽ chuyển đổi giá trị chuỗi priceStr thành một số thực (kiểu float)
//                // và gán giá trị đó cho biến newPrice.
//                float newPrice = Float.parseFloat(priceStr);
//                if(newPrice>0){
//                    return newPrice;
//                }else {
//                    System.err.println("Vui lòng nhập giá tiền lớn hơn 0");
//                }
//            }
//        }
  //  }

    public int inputCategoryId(Scanner sc,Categories[] arrCategories) {
        int choice;
        while (true){
            System.out.print("Các Id bạn có thể lựa chn là : ");
            for(Categories c:arrCategories){
                if(c!=null){
                    System.out.print(c.getCategoryId()+"\t");
                }
            }
            choice= ShopManagement.inputChoice(sc);
            boolean check=false;
            for(Categories c:arrCategories){
                if(c!=null && c.getCategoryId()==choice ){
                    check=true;
                    break;
                }
            }if(!check){
                System.err.println("Không tồn tại danh mục đó");
            }else {
                return choice;
            }

        }



    }

    public Date inputCreated(Scanner sc) {
        if (sc.hasNextLine()){
            sc.nextLine();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date;
        while (true){
            System.out.println("Hãy nhập ngày tạo dd/mm/yyyy");
            date = sc.nextLine();
            try{
                return sdf.parse(date);
            }catch (Exception ex){
                System.err.println("Nhập ngày không đúng định dạng");
            }
        }


    }

    public int inputProductStatus(Scanner sc) {
        //trạng thái sản phẩm,
        // chỉ nhận 1 trong các trạng thái sau
        // (0: Đang bán – 1: Hết hàng – 2: Không bán)
        System.out.println("Nhập trạng thái của sản phẩm (0: Đang bán – 1: Hết hàng – 2: Không bán)");
        int productStatus ;
        while (true){
            try{
                productStatus = sc.nextInt();
                switch (productStatus){
                    case 0:
                        System.out.println("trạng thái của sản phẩm 0: Đang bán");
                        break;
                    case 1:
                        System.out.println("trạng thái của sản phẩm 1: Hết hàng");
                        break;
                    case 2:
                        System.out.println("trạng thái của sản phẩm 2: Không bán");
                        break;
                }
                if(productStatus==1||productStatus==2||productStatus==0){
                    return productStatus;
                }else {
                    System.out.println("Nhập lại trạng thái của sản phẩm (0: Đang bán – 1: Hết hàng – 2: Không bán)");
                }
            }catch (Exception e){
                System.out.println("Nhập lại trạng thái của sản phẩm phải là số 0||1||2");

            }
        }

    }

    public void displayProduct() {
        System.out.println("[id = " + this.productId + "; \t"
                + "name = " + this.productName + "; \t"
                + "description = " + this.description + "; \t"
                + "price = " + this.price + "]"
                + "categoryId = " + this.categoryId + "; \t"
                + "created = " + this.created + "; \t"
                + "productStatus = " + this.productStatus + "; \t");
    }





}

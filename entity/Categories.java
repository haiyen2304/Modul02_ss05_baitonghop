package BTVN_SS05_TH.entity;

import java.util.Scanner;

public class Categories {
    private int  catelogyId;
    private String categoryName,descriptions;
    private boolean catelogyStatus;

    public Categories() {
    }

    public Categories(int catalogyId, String categoryName, String descriptions, boolean catalogStatus) {
        this.catelogyId = catalogyId;
        this.categoryName = categoryName;
        this.descriptions = descriptions;
        this.catelogyStatus = catalogStatus;
    }

    public int getCategoryId() {
        return catelogyId;
    }

    public void setCategoryId(int catalogId) {
        this.catelogyId = catalogId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean getCategoryStatus() {
        return catelogyStatus;
    }

    public void setCategoryStatus(boolean catalogStatus) {
        this.catelogyStatus = catalogStatus;
    }
    public void inputDataCategory(Scanner scanner, Categories[] arrCategories,int currentIndex) {
        // Cho phép nhập thông tin các danh mục theo đúng các yêu cầu dữ liệu đầu vào
        catelogyId = inputCategoryId(scanner,arrCategories,currentIndex);
        categoryName = inputCategoryName(scanner,arrCategories, currentIndex);
        descriptions = inputDescriptions(scanner);
        catelogyStatus = inputCatelogStatus(scanner);

    }

    public void updateDataCategory(Scanner scanner, Categories[] arrCategories,int index) {
        categoryName = inputCategoryName(scanner,arrCategories, index);
        descriptions = inputDescriptions(scanner);
        catelogyStatus = inputCatelogStatus(scanner);
    }

    // index là vị trí mình sẽ làm việc trong mảng
    public int inputCategoryId(Scanner scanner, Categories[] arrCategories,int currentIndex) {
        // nếu  vị trí hiện tại ta làm việc  =0 thì gán
        if(currentIndex == 0){
            System.out.println("Id (int) = 1");
            return 1;
        }else{
            int id = arrCategories[currentIndex].getCategoryId() + 1;
            System.out.println("Id (int) = " + id);
            return id;
        }
    }

    public String inputCategoryName(Scanner scanner, Categories[] arrCategories, int index) {

        if (scanner.hasNextLine()){
            scanner.nextLine();
        }

        String inputCategoryName;
        while (true){
            System.out.print("Nhập tên danh mục (String): ");
            inputCategoryName = scanner.nextLine().trim();
            if(inputCategoryName.length() > 50){
                System.out.println("Độ dài của tên danh mục chỉ được tối đa 50 kí tự");
            }else {
                // check kiểm tra độ trùng lặp của tên
                boolean isDuplicate = true;
                for(int i = 0; i < index; i++){
                    // .equalsIgnoreCase bỏ qua viết hoa và viết thường, so sánh tên ở dưới;
                    if (inputCategoryName.trim().equalsIgnoreCase(arrCategories[i].getCategoryName())) {
                        isDuplicate = false;
                        break;
                    }
                }
                if(!isDuplicate){
                    System.out.println("Tên đã bị trùng lặp vui lòng nhập lại");
                }else {
                    return inputCategoryName;
                }
            }
        }
    }

    public String inputDescriptions(Scanner scanner) {
        System.out.print("Nhap description (String): ");
        return scanner.nextLine();
    }

    public boolean inputCatelogStatus(Scanner scanner) {
        //return Boolean.parseBoolean(scanner.nextLine());  // scanner.nextBoolean();
        System.out.print("Nhập status (true/false): ");
        while (true) {
            try {
                return scanner.nextBoolean();
            }catch (Exception e) {
                System.out.print("Nhập lại status (true/false) ");
                scanner.nextLine();
            }
        }
    }

    public void displayData(){
        System.out.println( "[id = " + this.catelogyId + "; \t"
                + "name = " + this.categoryName + "; \t"
                + "description = " + this.descriptions + "; \t"
                + "status = " + this.catelogyStatus + "]"
        );
    }
}


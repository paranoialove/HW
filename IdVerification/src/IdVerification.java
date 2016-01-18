
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Anastasia wu
 */
public class IdVerification {

    public static void main(String[] args) {

        //輸入ID
        System.out.print("請輸入ID：");
        Scanner scan = new Scanner(System.in); //參考型別Scanner  建立物件
        String id = scan.next();//輸入字串
        System.out.println("id = " + id);

        //抓出身分證字號第一碼英文
        char c0 = id.charAt(0);

        //將第一碼英文  字元轉字串  以供搜尋
        String c00 = String.valueOf(c0);

        //判斷英文字對應
        String[] city;
        city = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "W", "Z", "I",};
        //搜尋英文字對應之索引數字(int)
        int s2 = 0;

        //檢查格式(重要！！)
        if (Character.isDigit(Arrays.binarySearch(city, c00))) {
            s2 = Arrays.binarySearch(city, c00);
            System.out.println("帳號格式正確");
        } else {
            System.out.println("身分證字首格式錯誤！");
            return;
        }

        //數字轉字串
        String cityVaule = String.valueOf(s2);
        //查詢縣市加權數字並分割十位數與個位數
        //分割十位數與個位數(字元)直轉數字
        int cvn1 = Character.getNumericValue(cityVaule.charAt(0)); //十位數
        int cvn2 = Character.getNumericValue(cityVaule.charAt(1)); //個位數

        //int yi = Character.getNumericValue(id.charAt(i));
        int y99 = Character.getNumericValue(id.charAt(9));

        //加權總數(不含英文代碼加權)
        int L = 0;
        for (int i = 1; i <= 8; i++) {
            L = L + Character.getNumericValue(id.charAt(i)) * (9 - i);
        }
        System.out.println("L = " + L);
        //int y = 0;
        //y = y11 * 8 + y22 * 7 + y33 * 6 + y44 * 5 + y55 * 4 + y66 * 3 + y77 * 2 + y88 * 1;
        int Y = cvn1 + cvn2 * 9 + L + y99;

        System.out.println("驗證數字為" + Y);
        int z = Y % 10;
        System.out.println("餘數為：" + z);
        if (z % 10 == 0) {
            System.out.println("此身分證字號有效。");
        }

    }

}


import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author swanky
 */
public class DEMOIdCreater extends JFrame {

    private String[] location = {
        "台北市", "台中市", "基隆市", "台南市", "高雄市", "台北縣", "宜蘭縣", "桃園縣",
        "嘉義市", "新竹縣", "苗栗縣", "台中縣", "南投縣", "彰化縣", "新竹市", "雲林縣",
        "嘉義縣", "台南縣", "高雄縣", "屏東縣", "花蓮縣", "台東縣", "金門縣", "澎湖縣",
        "陽明山", "連江縣"
    };
    private int[] locationNumber = {
        10, 11, 12, 13, 14, 15, 16, 17,
        34, 18, 19, 20, 21, 22, 35, 23,
        24, 25, 26, 27, 28, 29, 32, 30,
        31, 33
    };
    private char[] locationChar = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'
    };
    private JComboBox c = new JComboBox(location);
    private JComboBox s = new JComboBox(new String[]{"男", "女"});
    private JButton make = new JButton("製造");
    private JButton checkID = new JButton("確認");
    private JTextField tf = new JTextField(10);

    private ActionListener makeAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            StringBuffer output = new StringBuffer();
            int[] number = new int[9];
            int locationIndex = c.getSelectedIndex();
            output.append(locationChar[locationIndex]);
            number[0] = (locationNumber[locationIndex] % 10) * 9
                    + (int) (locationNumber[locationIndex] / 10);

            output.append(number[1] = s.getSelectedIndex() + 1);

            Random r = new Random();
            for (int i = 2; i < number.length; i++) {
                output.append(number[i] = r.nextInt(10));
            }

            int sum = number[0];
            for (int i = number.length - 1, j = 1; i > 0; i--, j++) {
                sum += number[i] * j;
            }
            int check = (sum % 10) == 0 ? 0 : 10 - (sum % 10);
            output.append(check);
            tf.setText(output.toString());
        }
    };

    private ActionListener checkAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String s = tf.getText().trim();
            if (s.equals("")) {
                return;
            }

            if (checkID(s)) {
                JOptionPane.showMessageDialog(null, "正確", "身分證字號確認",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "錯誤", "身分證字號確認",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    public DEMOIdCreater() {
        JPanel p1 = new JPanel();
        p1.add(c);
        p1.add(s);
        getContentPane().add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        make.addActionListener(makeAction);
        p2.add(make);
        checkID.addActionListener(checkAction);
        p2.add(checkID);
        getContentPane().add(p2, BorderLayout.CENTER);
        getContentPane().add(tf, BorderLayout.SOUTH);
    }

    @SuppressWarnings("empty-statement")
    public boolean checkID(String Chk2) {
        String v[][] = {{"A", "台北市"}, {"B", "台中市"}, {"C", "基隆市"},
        {"D", "台南市"}, {"E", "高雄市"}, {"F", "台北縣"}, {"G", "宜蘭縣"},
        {"H", "桃園縣"}, {"J", "新竹縣"}, {"K", "苗栗縣"}, {"L", "台中縣"},
        {"M", "南投縣"}, {"N", "彰化縣"}, {"P", "雲林縣"}, {"Q", "嘉義縣"},
        {"R", "台南縣"}, {"S", "高雄縣"}, {"T", "屏東縣"}, {"U", "花蓮縣"},
        {"V", "台東縣"}, {"X", "澎湖縣"}, {"Y", "陽明山"}, {"W", "金門縣"},
        {"Z", "連江縣"}, {"I", "嘉義市"}, {"O", "新竹市"}
        };

        int inte = -1;
        String s1 = String.valueOf(Character.toUpperCase(Chk2.charAt(0)));
        for (int i = 0; i < 26; i++) {
            if (s1.compareTo(v[i][0]) == 0) {
                inte = i;
            }
        }
        int total = 0;
        int all[] = new int[11];
        String E = String.valueOf(inte + 10);
        int E1 = Integer.parseInt(String.valueOf(E.charAt(0)));
        int E2 = Integer.parseInt(String.valueOf(E.charAt(1)));
        all[0] = E1;
        all[1] = E2;
        try {
            for (int j = 2; j <= 10; j++) {
                all[j] = Integer.parseInt(String.valueOf(Chk2.charAt(j - 1)));
            }
            for (int k = 1; k <= 9; k++) {
                total += all[k] * (10 - k);
            }
            total += all[0] + all[10];
            if (total % 10 == 0) {
                return true;
            }
        } catch (Exception ee) {;
        }
        return false;
    }

    public static void main(String[] args) {
        DEMOIdCreater app = new DEMOIdCreater();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.pack();
        app.setVisible(true);
    }
}

package project;

import java.awt.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class DateCalc extends JFrame implements CaretListener {
    private static final long serialVersionUID = 1L;
    private JTextField text_datenow, text_yearent, text_monthent, text_dayent, text_dateout, text_yearout,
            text_monthout, text_dayout, text_dateout3;

    public DateCalc() {
        super("DateCalc.");
        this.setBounds(300, 220, 598, 270);
        this.setVisible(true);
        this.setBackground(java.awt.Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(5, 3, 0, 3));

        SimpleDateFormat dfdateshow = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat dftimeshow = new SimpleDateFormat("HH:mm:ss");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datenow = df.format(new Date());

        String dateshow = dfdateshow.format(new Date());
        String timeshow = dftimeshow.format(new Date());
        this.add(new JLabel("系统当前时钟：", Label.LEFT));

        JMenuBar menuuse = new JMenuBar();
        JMenu menu1 = new JMenu("查看");
        JMenu menu2 = new JMenu("帮助");
        JMenuItem datesh = new JMenuItem("当前日期：" + dateshow);
        JMenuItem timesh = new JMenuItem("当前时间：" + timeshow);
        JMenuItem guanyu = new JMenuItem("DateCalc.");
        JMenuItem by = new JMenuItem("Software by slvayf.");

        this.setJMenuBar(menuuse);
        menuuse.add(menu1);
        menu1.add(datesh);
        menu1.add(timesh);
        menuuse.add(menu2);
        menu2.add(guanyu);
        menu2.add(by);

        text_datenow = new JTextField(datenow, 22);
        this.add(text_datenow);
        text_datenow.setEditable(false);
        text_datenow.addCaretListener(this);

        this.add(new JLabel("请输入年：", Label.LEFT));
        text_yearent = new JTextField("2016", 22);
        this.add(text_yearent);
        text_yearent.addCaretListener(this);

        this.getContentPane().add(new JLabel("请输入月：", Label.LEFT));
        text_monthent = new JTextField("5", 22);
        this.add(text_monthent);
        text_monthent.addCaretListener(this);

        this.getContentPane().add(new JLabel("请输入日：", Label.LEFT));
        text_dayent = new JTextField("27", 22);
        this.add(text_dayent);
        text_dayent.addCaretListener(this);

        JPanel j = new JPanel(new GridLayout(1, 5));
        text_dateout = new JTextField(datenow, 22);
        text_yearout = new JTextField("", 22);
        text_monthout = new JTextField("", 22);
        text_dayout = new JTextField("", 22);

        j.add(text_dateout);
        j.add(text_yearout);
        j.add(text_monthout);
        j.add(text_dayout);

        this.getContentPane().add(j);
        text_dateout.setEditable(false);
        text_yearout.setEditable(false);
        text_monthout.setEditable(false);
        text_dayout.setEditable(false);

        JPanel k = new JPanel(new GridLayout(1, 2));
        text_dateout3 = new JTextField("", 22);
        k.add(text_dateout3);
        this.getContentPane().add(k);
        text_dateout3.setEditable(false);
        caretUpdate(null);

        this.setVisible(true);
    }

    public void caretUpdate(CaretEvent ev) {
        String yearget = this.text_yearent.getText();
        String monthget = this.text_monthent.getText();
        String dayget = this.text_dayent.getText();
        if(yearget.length()>0 && monthget.length()>0 && dayget.length()>0) {
            text_yearout.setText("与  " + yearget + "年");
            text_monthout.setText("       " + monthget + "月");
            text_dayout.setText("        " + dayget + "日");

            String datenowget = this.text_datenow.getText();
            String dateget = yearget + "-" + monthget + "-" + dayget + " " + "00" + ":" + "00" + ":" + "00";

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date datenowgetc = null;
            try {
                datenowgetc = format.parse(datenowget);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date dategetc = null;
            try {
                dategetc = format.parse(dateget);
            } catch (ParseException e) {
                System.out.println("日期获取失败");
            }

            text_dateout3.setText("两个日期的差距： " + differentDaysByMillisecond(datenowgetc, dategetc) + " 天");
        }
    }

    public static int differentDaysByMillisecond(Date datenowgetc, Date dategetc) {
        int days = (int) ((datenowgetc.getTime() - dategetc.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static void main(String arg[]) {
        new DateCalc();
    }
}
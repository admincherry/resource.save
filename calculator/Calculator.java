import javax.swing.*;

public class Calculator {

    public static void main(String[] args){
        CalculatorFrame frame = new CalculatorFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("小计算器");
        frame.setSize(800,1000);
        frame.setVisible(true);
    }
}

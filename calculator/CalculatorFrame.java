import javax.swing.*;

/**
 * 计算器的Frame
 */
public class CalculatorFrame extends JFrame {
    /**
     * Frame 设置名称，并添加计算器panel
     */
    public CalculatorFrame(){
        setTitle("小计算器");
        CalculatorPanel panel = new CalculatorPanel();
        add(panel);
        //pack();
    }
}

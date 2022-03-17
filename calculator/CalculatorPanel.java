import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * @data 2022-03-09
 * 简单的计算器
 */
public class CalculatorPanel extends JPanel {
    private JButton showtext; //结果显示Button
    private JPanel panel;
    private double result;//计算结果值
    private String resultCommand;
    private boolean start;//控制标识
    private String lastStr;//最后一个字符的标识，用于一个字符一个字符删除
    DecimalFormat df=new DecimalFormat("#.00");
    Font f=new Font("宋体",Font.BOLD,50);
    //构造方法
    //画出一个计算器的面板，在面板上增加按钮
    public CalculatorPanel(){
        setLayout(new BorderLayout());
        resultCommand = "=";
        result = 0; //结果值设置为0
        start = true;
        //把showtext加到面板最上端
        showtext = new JButton("0");//初始显示0
        showtext.setEnabled(false);
        showtext.setFont(f);
        add(showtext,BorderLayout.NORTH);
        //new 一个数字按钮的面板
        panel =  new JPanel();
        //在此面板上添加数字按钮 5 x 5 grid
        panel.setLayout(new GridLayout(5, 5));

        addButton("7", insertAction());
        addButton("8", insertAction());
        addButton("9", insertAction());
        addButton("*", commandAction());
        addButton("4", insertAction());
        addButton("5", insertAction());
        addButton("6", insertAction());
        addButton("-", commandAction());
        addButton("1", insertAction());
        addButton("2", insertAction());
        addButton("3", insertAction());
        addButton("+", commandAction());
        addButton("0", insertAction());
        addButton(".", insertAction());
        addButton("/", commandAction());
        addButton("%", commandAction());
        addButton("spac",commandAction());
        addButton("CE",commandAction());
        addButton("C",commandAction());
        addButton("=",commandAction());
        add(panel,BorderLayout.CENTER);

    }

    /**
     * 添加按钮
     *
     */
    private void addButton(String label, ActionListener listener){
            JButton jButton = new JButton(label);
            jButton.addActionListener(listener);
            jButton.setFont(f);
            panel.add(jButton);
    }

    /**
     * 插入数字事件
     * @return
     */
    public ActionListener insertAction(){
        ActionListener insertListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String input = e.getActionCommand();//获取输入值
                System.out.println("result=insertAction===:"+result + "====input:==="+input);
                    if (start){
                        showtext.setText("");
                        start = false;
                    }
                    showtext.setText(showtext.getText() + input); //显示框显示原有的数字+新输入的数字
            }
        };
        return insertListener;
    }

    /**
     * 运算逻辑
     */
    public ActionListener commandAction(){

        ActionListener commandAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();//获取按钮命令值
                //判断是否是清除按钮
                if(command.equals("CE") || command.equals("C")){ //清除显示框内容
                    showtext.setText("0");
                    result = 0;
                }else if(command.equals("spac") && showtext.getText().length() > 0){//判断是否是spac按钮
                    lastStr = showtext.getText().substring(0,showtext.getText().length() - 1);
                    showtext.setText(lastStr);
                   //判断最后一个数字不能是-号，字符串
                    if(!lastStr.equals("-") && lastStr.length() > 0 ) {
                        result = Double.parseDouble(lastStr);
                    }else if(lastStr.equals("-")){
                        showtext.setText(lastStr);
                    }else{
                        showtext.setText("0");
                    }
                }
                if(start){
                   if(command.equals("-")){  //如果为负数
                        showtext.setText("");
                        showtext.setText(command + showtext.getText());
                        start = false;
                    }
                }else{
                    //进行计算结果
                    calculate(Double.parseDouble(showtext.getText()));
                    start = true;
                   // resultCommand = command;
                }
                resultCommand = command;
            }
        };
        return commandAction;
    }

    /**
     * 计算结果
     */
    private void calculate(Double d){
        System.out. println("calculate====:"+result );

        if (resultCommand.equals("+")) {
            result += d;
        } else if (resultCommand.equals("-") && d >= 0.0) {//区分是否负数
            result -= d;
            // result = result - d;
        } else if (d < 0 && !resultCommand.equals("spac")) {//如果是删除字符操作则不进行运算
            result += d;
        } else if (resultCommand.equals("*")) {
            result *= d;
        } else if (resultCommand.equals("/")) {
            result /= d;
        } else if (resultCommand.equals("%")) {
            result = result % d;
        } else {
            result = d;
        }
        System.out.println("计算result:"+ result  + "=======");
       String resultStr = dataCheck(result);
        showtext.setText("" + resultStr);
    }
    /**
    * @Description:  Double类型数据后面为0则不显示
    * @Param:  num
    * @data 2022-03-10
    */
    private String dataCheck(double num){
        if(num % 1.0 == 0){
            return String.valueOf((long)num);
        }
        return String.valueOf(num);
    }
}

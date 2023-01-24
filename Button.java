import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Button {
    public static void main(String[] args) {
        JFrame window1 = new JFrame();
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window1.setVisible(true);
        window1.setSize(400,400);
        window1.getContentPane().setLayout(null);

        JButton button = new JButton();
        button.setText("Start");
        button.setSize(100,100);
        button.setLocation(100,100);
        ActionListener al = e -> System.out.println("ijjfuhSDUFHfuIJUFHPUIWHEP");
        button.addActionListener(al);
        window1.add(button);
    }
}


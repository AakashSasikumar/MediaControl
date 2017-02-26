import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aakash on 2/26/2017.
 */
public class MainFrame {
    JFrame mainFrame;
    JPanel bottom;
    JButton start, stop;

    public static VideoCapture videoCapture;
    static JLabel toShow=new JLabel();
    MainFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException,InstantiationException, IllegalAccessException{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        mainFrame = new JFrame("Control");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(300, 100);
        bottom = new JPanel(new FlowLayout());
        start=new JButton("Start");
        stop= new JButton("Stop");
        mainFrame.add(toShow, BorderLayout.CENTER);
        bottom.add(start);
        bottom.add(stop);
        mainFrame.add(bottom, BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toShow.setVisible(false);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //toShow.setVisible(true);
                VideoCapturer a = new VideoCapturer();
                a.execute();


            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoCapture.release();
                //toShow.setVisible(false);

            }
        });
    }
}

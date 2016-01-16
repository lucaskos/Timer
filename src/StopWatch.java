import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StopWatch extends JLabel implements ActionListener {
    private static final String Start = "Start";
    private static final String Pause = "Pause";
    private static final String Reset = "Reset";
    private boolean isRunning;
    private Timer timer = new Timer(1000, this);
    private long initTime = System.currentTimeMillis();
    private long startTime;
    private long pauseTime;
    
    public StopWatch() {
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setText(getCurrentTime(System.currentTimeMillis() - initTime));
    }

    public void actionPerformed(ActionEvent ae) {
        setText(getCurrentTime(System.currentTimeMillis() - startTime));
    }

    public void start() {
        if (isRunning == false) {
            startTime = System.currentTimeMillis();
        } else {
            startTime = System.currentTimeMillis() - (pauseTime - startTime);
        }
        isRunning = true;
        timer.start();
    }
    

    public void pause() {           
        pauseTime = System.currentTimeMillis();
        timer.stop();
    }

    public void reset() {       
        startTime = 0;
        isRunning = false;
        this.setText(getCurrentTime(System.currentTimeMillis() - System.currentTimeMillis()));
    }

    private String getCurrentTime(long time) {
        return myFormat(time);
    }

    private String myFormat(final long time) {
        final long hr = TimeUnit.MILLISECONDS.toHours(time);
        final long min = TimeUnit.MILLISECONDS.toMinutes(time
                - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(time
                - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        
//ms is not used in this program
        final long ms = TimeUnit.MILLISECONDS.toMillis(time
                - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min)
                - TimeUnit.SECONDS.toMillis(sec));
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final StopWatch textLabel = new StopWatch();
        textLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        f.add(textLabel, BorderLayout.CENTER);

        final JButton button = new JButton(Start);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (Pause.equals(cmd)) {
                    textLabel.pause();
                    button.setText(Start);
                } else {
                    textLabel.start();
                    button.setText(Pause);
                }
            }
        });

        final JButton button2 = new JButton(Reset);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textLabel.reset();
            }
        });

        f.add(button, BorderLayout.SOUTH);
        f.add(button2, BorderLayout.NORTH);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                create();
            }
        });
    }
}
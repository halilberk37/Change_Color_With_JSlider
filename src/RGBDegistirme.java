import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RGBDegistirme extends JFrame {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("RGB Deðiþtirme");

		JPanel GUI = new JPanel();
		GUI.setLayout(null);

		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.blue);
		jp2.setSize(295, 120);
		jp2.setLocation(0, 0);
		jp2.setLayout(null);

		GUI.add(jp2);
		
		
		JLabel kirmizi = new JLabel("Kirmizi:");
		kirmizi.setSize(70, 20);
		kirmizi.setLocation(200, 125);
		GUI.add(kirmizi);

		JSlider sliderKirmizi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		sliderKirmizi.setSize(185, 10);
		sliderKirmizi.setLocation(10, 130);
		sliderKirmizi.setMaximum(255);
		sliderKirmizi.setBackground(Color.RED);

		GUI.add(sliderKirmizi);

		JLabel yesil = new JLabel("Yeþil:");
		yesil.setSize(60, 20);
		yesil.setLocation(200, 175);
		GUI.add(yesil);
		
		
		JSlider sliderYesil = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		sliderYesil.setSize(185, 10);
		sliderYesil.setLocation(10, 180);
		sliderYesil.setMaximum(255);
		sliderYesil.setBackground(Color.green);
		GUI.add(sliderYesil);

		JLabel mavi = new JLabel("Mavi:");
		mavi.setSize(50, 20);
		mavi.setLocation(200, 225);
		GUI.add(mavi);

		JSlider sliderMavi = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		sliderMavi.setSize(185, 10);
		sliderMavi.setLocation(10, 230);
		sliderMavi.setMaximum(255);
		sliderMavi.setBackground(Color.blue);
		GUI.add(sliderMavi);

		
		// RGB Deðiþim Olayý
				sliderKirmizi.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent a) {

						jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
						kirmizi.setText("Kýrmýzý:" + sliderKirmizi.getValue());
					}

				});
				sliderYesil.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent a) {

						jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
						yesil.setText("Yeþil:" + sliderYesil.getValue());
					}

				});

				sliderMavi.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent a ) {
						
						jp2.setBackground(new Color(sliderKirmizi.getValue(), sliderYesil.getValue(), sliderMavi.getValue()));
						mavi.setText("Mavi:"+sliderMavi.getValue());
					}
					
				});

		
		
		
		frame.setVisible(true);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(GUI);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

}

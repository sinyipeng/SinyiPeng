package tw.rose.tutor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class MyMemu extends JFrame implements ActionListener{
	public JLabel label1,label2,label3,label4,label5,CaloriesM,BEEFemale;
	public JTextField txt1,txt2,txt3,txt4,txt5;
	public JComboBox gender, activity;
	public JButton calories;
	public String[] genderChoose, stressChoose;
	
    public MyMemu() {
    	setLayout(new GridBagLayout());
		JFrame frame = new JFrame("飲食設計");
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		frame.setContentPane(panel);
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0; 
		c.weighty = 0;
		//c.fill = GridBagConstraints.HORIZONTAL; //使元件佔用儘可能多餘的水平空間
		
		//身高
		JLabel label1 = new JLabel("身高:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridheight = 1;
		panel.add(label1,c);
		
		JTextField txt1 = new JTextField(10);
		txt1.setEditable(true);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridheight = 1;
		panel.add(txt1,c);
		
		//體重
		JLabel label2 = new JLabel("體重:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridheight = 1;
		panel.add(label2,c);
		
		JTextField txt2 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridheight = 1;
		panel.add(txt2,c);
		
		//age
		JLabel label3 = new JLabel("年齡:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridheight = 1;
		panel.add(label3,c);
		
		JTextField txt3 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridheight = 1;
		panel.add(txt3,c);
		
		//理想體重
		JLabel label4 = new JLabel("理想體重:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridheight = 1;
		panel.add(label4,c);
				
		JTextField txt4 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridheight = 1;
		txt4.setEditable(true);
		panel.add(txt4,c);
		
		//BMI
		JLabel label5 = new JLabel("BMI:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridheight = 1;
		panel.add(label5,c);
				
		JTextField txt5 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridheight = 1;
		txt4.setEditable(true);
		panel.add(txt5,c);
		
		//性別
		String[] genderChoose = {"男","女"};
		JComboBox gender = new JComboBox(genderChoose);
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 5;
		c.gridheight =1;
		panel.add(gender,c);
		
		//活動因子
		String[] activityChoose = {"輕度活動","中度活動","臥床"};
		JComboBox activity = new JComboBox(activityChoose);
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 6;
		c.gridheight =1;
		panel.add(activity,c);
		
		//計算熱量
		JButton calories = new JButton("計算熱量");
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 8;
		c.gridheight =1;
		panel.add(calories,c);
		
		
		JLabel CaloriesM = new JLabel();
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 9;
		c.gridheight =1;
		panel.add(CaloriesM,c);
		
		
		calories.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gender.getSelectedItem().equals("男")) {
					double BEEMale;
					BEEMale = 
						(66 + 13.7*(Double.parseDouble(txt2.getText())) + 
						5*(Double.parseDouble(txt1.getText())) - 
						6.8*(Double.parseDouble(txt3.getText())));
					if (activity.getSelectedItem().equals("輕度活動")) {
						CaloriesM.setText("一日所需熱量為：" + Double.toString(BEEMale*1.3));
					}
					if (activity.getSelectedItem().equals("中度活動")) {
						CaloriesM.setText("一日所需熱量為：" + Double.toString(BEEMale*1.4));
					}
					if (activity.getSelectedItem().equals("臥床")) {
						CaloriesM.setText("一日所需熱量為：" + Double.toString(BEEMale*1.2));
					}
				}
					
				if (gender.getSelectedItem().equals("女")) {
					double BEEFemale;
					BEEFemale = 
						(655 + 9.6*(Double.parseDouble(txt2.getText())) + 
						1.7*(Double.parseDouble(txt1.getText())) - 
						4.7*(Double.parseDouble(txt3.getText())));
					
					if (activity.getSelectedItem().equals("輕度活動")) {
						CaloriesM.setText("一日所需熱量為：" + Double.toString(BEEFemale*1.3));
					}
					if (activity.getSelectedItem().equals("中度活動")) {
						CaloriesM.setText("一日所需熱量為：" + Double.toString(BEEFemale*1.4));
					}
					if (activity.getSelectedItem().equals("臥床")) {
						CaloriesM.setText("一日所需熱量為：" + Double.toString(BEEFemale*1.2));
					}

						}
					}
				}
					
				);
		
        DocumentFilter df = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {

                if (isDigit(string)) {
                    super.insertString(fb, i, string, as);
                    calcIdealBw();
                }
            }

            @Override
            public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
                super.remove(fb, i, i1);
                calcIdealBw();
            }

            @Override
            public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.replace(fb, i, i1, string, as);
                    calcIdealBw();

                }
            }

            private boolean isDigit(String string) {
                for (int n = 0; n < string.length(); n++) {
                    char c = string.charAt(n);//get a single character of the string
                    //System.out.println(c);
                    if (!Character.isDigit(c)) {//if its an alphabetic character or white space
                        return false;
                    }
                }
                return true;
            }

            void calcIdealBw() {
 
            	float sum = 22;
                float BMI = 1;
                //float heightSquare= (txt1.getVerifyInputWhenFocusTarget()) * (txt1.getVerifyInputWhenFocusTarget());
               
                if (!txt1.getText().isEmpty()) {
                    sum *= (Double.parseDouble(txt1.getText()) * Double.parseDouble(txt1.getText()))/ 10000;//we must add this
                }
                if (!txt1.getText().isEmpty()) {
                	if (!txt2.getText().isEmpty()) {
                    BMI *= Double.parseDouble(txt2.getText())/
                    		(Double.parseDouble(txt1.getText())/100 * Double.parseDouble(txt1.getText())/100);
                	}
                }
              
                txt4.setText(String.valueOf(sum));
                txt5.setText(String.valueOf(BMI));
            }
            };

        ((AbstractDocument) (txt1.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (txt2.getDocument())).setDocumentFilter(df);
        
        frame.setSize(800,480);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        }        


@Override
public void actionPerformed(ActionEvent e) {
	
        }

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new MyMemu();
			        }
			    });
			}
}

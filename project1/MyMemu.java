package midtermProject;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class MyMemu extends JFrame implements ActionListener, DocumentListener{
	public JFrame frame;
	public JLabel label1,label2,label3,label4,label5,CaloriesM,label6,label7;
	public JPanel panel;
	public JTextField txt1,txt2,txt3,txt4,txt5;
	public JComboBox gender, activity;
	public JButton calories;
	public String[] genderChoose, stressChoose;
	public JTextArea area, area2, areanull;
	public String sample_name, Calories;
	
	
    public MyMemu() {
    	//設定介面
    	setLayout(new GridBagLayout());
		frame = new JFrame("飲食設計");
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		frame.setContentPane(panel);
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0; 
		c.weighty = 0;
		//c.fill = GridBagConstraints.HORIZONTAL; //使元件佔用儘可能多餘的水平空間
		
		//身高
		label1 = new JLabel("身高：");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridheight = 1;
		panel.add(label1,c);
		
		txt1 = new JTextField(10);
		txt1.setEditable(true);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridheight = 1;
		panel.add(txt1,c);
		
		//體重
		label2 = new JLabel("體重：");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridheight = 1;
		panel.add(label2,c);
		
		txt2 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridheight = 1;
		panel.add(txt2,c);
		
		//年齡
		label3 = new JLabel("年齡：");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridheight = 1;
		panel.add(label3,c);
		
		txt3 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridheight = 1;
		panel.add(txt3,c);
		
		//理想體重
		label4 = new JLabel("理想體重：");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridheight = 1;
		panel.add(label4,c);
				
		txt4 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridheight = 1;
		txt4.setEditable(true);
		panel.add(txt4,c);
		
		//BMI
		label5 = new JLabel("BMI：");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridheight = 1;
		panel.add(label5,c);
				
		txt5 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridheight = 1;
		txt4.setEditable(true);
		panel.add(txt5,c);
		
		//性別
		String[] genderChoose = {"男","女"};
		gender = new JComboBox(genderChoose);
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 5;
		c.gridheight =1;
		panel.add(gender,c);
		
		//活動因子
		String[] activityChoose = {"輕度活動","中度活動","臥床"};
		activity = new JComboBox(activityChoose);
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 6;
		c.gridheight =1;
		panel.add(activity,c);
		
		//計算熱量
		calories = new JButton("計算熱量");
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 8;
		c.gridheight =1;
		panel.add(calories,c);
		
		//一日所需熱量標籤
		CaloriesM = new JLabel();
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 9;
		c.gridheight =1;
		panel.add(CaloriesM,c);
		
		//計算熱量觸發actionLisner事件
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
		
		//飲食輸入
		label6 = new JLabel("飲食紀錄：");
		c.gridx= 0;
		c.gridwidth = 1;
		c.gridy = 10;
		c.gridheight =1;
		panel.add(label6,c);
		
		area = new JTextArea(3, 15);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 10;
		c.gridheight = 1;
		//area.setEditable(true);
		panel.add(area,c);
		LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY,1);
		area.setBorder(lineBorder);
		
		areanull = new JTextArea(1,15);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 15;
		c.gridheight = 1;
		areanull.setEditable(false);
		panel.add(areanull,c);
		
		//飲食熱量
		label7 = new JLabel("飲食熱量：");
		c.gridx= 0;
		c.gridwidth = 1;
		c.gridy = 20;
		c.gridheight =1;
		panel.add(label7,c);
		
		area2 = new JTextArea(3, 15);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 20;
		c.gridheight = 1;
		area2.setEditable(false);
		panel.add(area2,c);
		LineBorder lineBorder2 = new LineBorder(Color.LIGHT_GRAY,1);
		area2.setBorder(lineBorder2);
		
		
		//數值計算同步更新
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
            //計算熱量方法
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
        
        //撈出到的對應資料數值同步呈現於另一個
        area.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					//showData();
					changedUpdate(e);
				}catch(Exception a) {
					System.out.println(a.toString());
				}
				
			}
			
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					//showData();
					changedUpdate(e);
					}catch(Exception a) {
						System.out.println(a.toString());
					}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					showData();
					
					}catch(Exception a) {
						System.out.println(a.toString());
					}
				
			}
		});
        	
        frame.setSize(800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        }        


@Override
public void actionPerformed(ActionEvent e) {
	
        }
//從資料庫撈出JTextArea對應的特定資料的方法
public void showData() throws NullPointerException{
	try {	
	String keyin = area.getText();
	System.out.println(keyin);
	Class.forName("com.mysql.cj.jdbc.Driver");
	Properties prop = new Properties();
	prop.put("user", "root");
	prop.put("password", "root");
	Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:8889/iii", prop);
	
	// 3. SQL statement
	String sql = "SELECT * FROM nutrition WHERE Sample_Name like '" + '%' + area.getText() + '%' + "' " ;
	System.out.println(sql);
	PreparedStatement pstmt = conn.prepareStatement(sql);

	ResultSet rs = pstmt.executeQuery();
	//System.out.println(pstmt);
	while (rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("Food_ID");
			String category = rs.getString("Category");
			String sample_name = rs.getString("Sample_Name");
			String calories = rs.getString("Calories");
			String modified_calories = rs.getString("Modified_Calories");
			System.out.println(id + ":" +  category + ":" + sample_name + ":" + calories + ":" + modified_calories);
			
			area2.setText(calories + "大卡");
			
		}
			
			conn.close();
			//System.out.println(rs.getString(" + area.getText().trim() + "));
			
}catch(Exception e) {
	System.out.println(e.toString());
}
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new MyMemu();
			        }
			    });
  
			}


@Override
public void insertUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void removeUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void changedUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
}
}

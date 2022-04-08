package tw.rose.tutor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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

public class MyMemu extends JFrame{

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
		JLabel label1 = new JLabel("Height:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridheight = 1;
		panel.add(label1,c);
		
		JTextField txt1 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridheight = 1;
		panel.add(txt1,c);
		
		//體重
		JLabel label2 = new JLabel("Weight:");
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
		JLabel label3 = new JLabel("Age:");
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
		
		//性別
		String[] genderChoose = {"Male","Female"};
		JComboBox gender = new JComboBox(genderChoose);
		c.gridx= 1;
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridheight =1;
		panel.add(gender,c);
		
		//理想體重
		JLabel label4 = new JLabel(" Ideal BW:");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridheight = 1;
		panel.add(label4,c);
				
		JTextField txt4 = new JTextField(10);
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridheight = 1;
		txt4.setEditable(true);
		panel.add(txt4,c);
		
		frame.setSize(800,480);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
       

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
                float IDBW;

                if (!txt1.getText().isEmpty()) {
                    sum *= (Double.parseDouble(txt1.getText()) * Double.parseDouble(txt1.getText()))/ 10000;//we must add this
                    
                }
//                if (!jtf2.getText().isEmpty()) {
//                    sum += Integer.parseInt(jtf2.getText());//we must add this
//                }
//                if (!jtf3.getText().isEmpty()) {
//                    sum -= Integer.parseInt(jtf3.getText());//we must subtract this
//                }

                txt4.setText(String.valueOf(sum));
            }
        };

        ((AbstractDocument) (txt1.getDocument())).setDocumentFilter(df);
        
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

package tw.rose.tutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader1 {

	public static PreparedStatement pstmt;
	public static void main(String[] args) {
		try{ 
			Properties prop = new Properties();
			prop.put("user","root");
			prop.put("password","root");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iii", prop);
		
		// 3.敘述句SQL statement
		//preparedstatement可以防止引碼攻擊
		pstmt = conn.prepareStatement(
				"INSERT INTO nutrition (Food_ID,Category,Sample_Name,Content, Calories,Modified_Calories, Water, Protein, Fat, CHO, Fiber)" 
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
				
				//"INSERT INTO nutritions (ID, Category,Sample_Name,Content)" + "VALUES (?,?,?,?)");
				
				//"INSERT INTO nutritions (ID,Category,Sample_Name,Content,Calories,Modified_Calories,Water,Protein,Fat,CHO,Fiber)" 
		//+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		ReadExcel();
	}
		public static void ReadExcel() {
		try {
			
		Workbook workbook = Workbook.getWorkbook(new File("dir1/food_nutrition.xls"));
		Sheet sheet = workbook.getSheet("sheet1");
		//String col = sheet.getColumns();
			
			
		for(int i = 1; i < sheet.getRows() ; i++) {
			float f = Float.valueOf(sheet.getCell(10,i).getContents()).floatValue();
			System.out.println(f);
			//System.out.println(sheet.getCell(4,0).getContents()); //取得每欄資料
////			for (int j = 0 ; j < sheet.getColumns() ; j++) { //取得每行資料
////			System.out.println(sheet.getCell(j,1).getContents());
//				
			String Food_ID = sheet.getCell(0,i).getContents();
			String Category = sheet.getCell(1,i).getContents();
			String Sample_Name = sheet.getCell(2,i).getContents();
			String Content = sheet.getCell(3,i).getContents();
			float Calories = Float.valueOf(sheet.getCell(4,i).getContents()).floatValue();
			float Modified_Calories = Float.valueOf(sheet.getCell(5,i).getContents()).floatValue();
			float Water = Float.valueOf(sheet.getCell(6,i).getContents()).floatValue();
			float Protein = Float.valueOf(sheet.getCell(7,i).getContents()).floatValue();
			float Fat = Float.valueOf(sheet.getCell(8,i).getContents()).floatValue();
			float CHO = Float.valueOf(sheet.getCell(9,i).getContents()).floatValue();
			float Fiber = Float.valueOf(sheet.getCell(10,i).getContents()).floatValue();
//		String Abandonment_Rate = sheet.getCell(4,i).getContents();
			
			
//			 insertData(Food_ID, Category, Sample_Name, Content, Calories, Modified_Calories, Water, Protein, Fat, CHO, Fiber);
			//}
		}
				workbook.close();
			
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		public static void insertData(String Food_ID, String Category,String Sample_Name,String Content,float Calories,float Modified_Calories
				,float Water,float Protein, float Fat, float CHO, float Fiber) {
			try {
			pstmt.setString(1, Food_ID);
			pstmt.setString(2, Category);
			pstmt.setString(3, Sample_Name);
			pstmt.setString(4, Content);
////			pstmt.setString(5, Abandonment_Rate);
			pstmt.setFloat(5, Calories);
			pstmt.setFloat(6, Modified_Calories);
			pstmt.setFloat(7, Water);
			pstmt.setFloat(8, Protein);
			pstmt.setFloat(9, Fat);
			pstmt.setFloat(10, CHO);
			pstmt.setFloat(11, Fiber);
//			pstmt.setString(3, Water);
//			pstmt.setString(4, Protein);
//			pstmt.setString(5, Fat);
//			pstmt.setString(6, CHO);
//			pstmt.setString(7, Fiber);
			
			pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		}



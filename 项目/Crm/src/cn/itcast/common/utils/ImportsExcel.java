package cn.itcast.common.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ImportsExcel {    
    /**  
     * 对外提供读取excel 的方法  
     * 
     */    
public static List<List<Object>> readExcel(MultipartFile file) throws IOException{    
	String extension= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
   
   if("xls".equals(extension)){    
    return read2003Excel(file.getInputStream());    
   }else if("xlsx".equals(extension)){    
    return read2007Excel(file.getInputStream());    
   }else{    
    throw new IOException("不支持的文件类型");    
   }    
}    
   /**  
	* 读取 office 2003 excel  
	* @throws IOException   
	* @throws FileNotFoundException 
	*/    
private static List<List<Object>> read2003Excel(InputStream stream) throws IOException,FileNotFoundException{    
   List<List<Object>> list = new LinkedList<List<Object>>();    
   HSSFWorkbook hwb = new HSSFWorkbook(stream);    
   HSSFSheet sheet = hwb.getSheetAt(0);    
   Object value = null;    
   HSSFRow row = null;    
   HSSFCell cell = null;     
   for(int i = sheet.getFirstRowNum()+1;i<= sheet.getPhysicalNumberOfRows();i++){    
    row = sheet.getRow(i);    
    if (row == null) {    
     continue;    
    }    
    List<Object> linked = new LinkedList<Object>();    
    for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {    
     cell = row.getCell(j);    
     if (cell == null) {    
      continue;    
     }  
     value = cell.getStringCellValue();
     linked.add(value);      
   }    
    list.add(linked);    
   }    
   return list;    
}    
   /**  
	* 读取Office 2007 excel  
	*/    
private static List<List<Object>> read2007Excel(InputStream stream) throws IOException,FileNotFoundException{    
   List<List<Object>> list = new LinkedList<List<Object>>();    
   // 构造 XSSFWorkbook 对象，strPath 传入文件路径     
   XSSFWorkbook xwb = new XSSFWorkbook(stream);    
   // 读取第一章表格内容     
   XSSFSheet sheet = xwb.getSheetAt(0);    
   Object value = null;    
   XSSFRow row = null;    
   XSSFCell cell = null;    
   for(int i = sheet.getFirstRowNum()+1; i <= sheet.getPhysicalNumberOfRows(); i++) {    
    row = sheet.getRow(i);    
    if (row == null) {    
     continue;    
    }    
    List<Object> linked = new LinkedList<Object>();    
    for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {    
     cell = row.getCell(j);    
     if (cell == null) {    
      continue;    
     }    
     value = cell.getStringCellValue();
     linked.add(value);    
    }    
    list.add(linked);    
   }    
   return list;    
}  

//解决excel类型问题，获得数值  
public  String getValue(Cell cell) {  
    String value = "";  
    if(null==cell){  
        return value;  
    }  
    switch (cell.getCellType()) {  
    //数值型  
    case Cell.CELL_TYPE_NUMERIC:  
        if (HSSFDateUtil.isCellDateFormatted(cell)) {  
            //如果是date类型则 ，获取该cell的date值  
            Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            value = format.format(date);;  
        }else {// 纯数字  
            BigDecimal big=new BigDecimal(cell.getNumericCellValue());  
            value = big.toString();  
            //解决1234.0  去掉后面的.0  
            if(null!=value&&!"".equals(value.trim())){  
                 String[] item = value.split("[.]");  
                 if(1<item.length&&"0".equals(item[1])){  
                     value=item[0];  
                 }  
            }  
        }  
        break;  
        //字符串类型   
    case Cell.CELL_TYPE_STRING:  
        value = cell.getStringCellValue().toString();  
        break;  
    // 公式类型  
    case Cell.CELL_TYPE_FORMULA:  
        //读公式计算值  
        value = String.valueOf(cell.getNumericCellValue());  
        if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串  
            value = cell.getStringCellValue().toString();  
        }  
        break;  
    // 布尔类型  
    case Cell.CELL_TYPE_BOOLEAN:  
        value = " "+ cell.getBooleanCellValue();  
        break;   
    default:  
        value = cell.getStringCellValue().toString();  
}  
if("null".endsWith(value.trim())){  
    value="";  
}  
return value;  
}  
    
}    

package xgxt.xtwh.zpgl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

import org.apache.struts.upload.FormFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zfsoft.utils.FileUploadUtil;

/**
 * ϵͳ��ʹ�õĹ��÷���
 * @author hufei
 *
 */
public class FileOper {

	private static final int BUFFER_SIZE = 16 * 1024 ;

	/**
	 * ������·�����ļ����Ƿ���ڣ����������򴴽���
	 * @param fileDirPath �ļ���·��
	 */
	public static void mkdir(String fileDirPath){
		File tempFilePath  = new File(fileDirPath);
		if(!tempFilePath.exists()){
			tempFilePath.mkdir();
		}
	}
	
	/**
	 * ���ļ�����д���µ��ļ���
	 * @param file ����Դ�ļ�
	 * @param newFile Ҫд�˵��ļ�
	 */
	public static void writeFile(File file,File newFile){
	
		try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
                in = new BufferedInputStream( new FileInputStream(file), BUFFER_SIZE);
                out = new BufferedOutputStream( new FileOutputStream(newFile), BUFFER_SIZE);
                 byte [] buffer = new byte [BUFFER_SIZE];
                 while (in.read(buffer) > 0 )  {
                    out.write(buffer);
                } 
             } finally  {
                 if ( null != in)  {
                    in.close();
                } 
                  if ( null != out)  {
                    out.close();
                } 
            } 
         } catch (Exception e)  {
            e.printStackTrace();
        } 
	}
	
	/**
	 * ���ļ�����д���µ��ļ���
	 * @param file ����Դ�ļ�
	 * @param newFile Ҫд�˵��ļ�
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void writeFile(FormFile file,String tempDir,File newFile) throws FileNotFoundException, IOException{
	
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = "" + newFile;
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead1 = 0;
		byte[] buffer1 = new byte[size];
		while ((bytesRead1 = in.read(buffer1, 0, size)) != -1) {
			out.write(buffer1, 0, bytesRead1);
		}
		out.close();
		in.close();
	}
	
	/**
	 * ��ȡ�����ļ��ĺ�׺�����㣬��.xml
	 * @param filePath �ļ�·��
	 * @return
	 */
	public static String getFileSuffix(String filePath){
		int begin = filePath.lastIndexOf(".");
		return filePath.substring(begin,filePath.length());
	}
	
	/**
	 * ��ȡ������excel�ļ����б��⣬�������list��Map�У���Ϊ��column��
	 * @param filePath �ļ�·��
	 * @return
	 */
	public static List<Map<String, String>> getExcelColumns(String filePath){
		List<Map<String, String>> cols = new ArrayList<Map<String, String>>();
		try {
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			
			Sheet sheet = wb.getSheet(0);
			Cell[] colRow = sheet.getRow(0);
			for(int cellnum=0;cellnum<colRow.length;cellnum++){
				HashMap<String, String> cellCol = new HashMap<String, String>();
				cellCol.put("column", colRow[cellnum].getContents());
				cols.add(cellCol);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cols;
	}
	
	/**
	 * ��ȡ�����ַ������ڸ���excel�ļ��е����±꣬����±����鷵��
	 * @param titName ����������
	 * @param filePath �ļ�·��
	 * @return
	 */
	public static int[] getExcelTitSuffix(String[] titName,String filePath){
		List<Map<String,String>> list = getExcelColumns(filePath); 
		int[] suffix = new int[titName.length];
		int suffixNum = 0;
		for(int i = 0; i < list.size();i++){
			Map<String,String> map = list.get(i);
			
			for(int y = 0; y < titName.length;y++){
				if(map.get("column").equalsIgnoreCase(titName[suffixNum])){
					suffix[suffixNum] = i;
					suffixNum++;
				}
			}
		}
		return suffix;
	}
	
	/**
	 * ���ݸ������зִ��з��ַ����������зֺ��������ݸ�������������
	 * @param str ���з��ַ���
	 * @param splitNam �зִ�
	 * @param indexNmu ����
	 * @return
	 */
	public static String getSplitStr(String str,String splitNam,int indexNum){
		String[] splitStr = str.split(splitNam); 
		String newStr = null;
		if(splitStr.length > 0){
			newStr = splitStr[indexNum];
		}
		return newStr;
	}
	
	/**
	 * ��ȡ����excel�ļ��ĸ���sheet
	 * @param filePath �ļ�·��
	 * @param sheetNum sheet���
	 * @return
	 */
	public static Sheet getExcelSheet(String filePath,int sheetNum){
		Sheet sheet = null;
		try {
			sheet = Workbook.getWorkbook(new File(filePath)).getSheet(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}
	
	/**
	 * ���ָ����excel����ĳ������
	 * @param sheet excel��sheet
	 * @return
	 */
	public static String[] getOneRow(Sheet sheet,int rownum,int excelColNum){
		/*Cell[] row = new Cell[excelColNum];
		Cell[] cell = sheet.getRow(rownum); 
		for(int i = 0;i < cell.length;i++){
			row[i] = cell[i];
		}
		if(excelColNum > cell.length){
			for(int i = cell.length;i < excelColNum - cell.length;i++){
				row[i] = "";
			}
		}
		
		return row;*/
		
		Cell[] row = sheet.getRow(rownum);		
		String[] result = new String[Math.max(excelColNum, row.length)];
		int length = Math.min(excelColNum, row.length);
		for(int rnum=0;rnum<length;rnum++){
			result[rnum] = row[rnum].getContents();
		}
		for(int rowNum=0; rowNum<result.length;rowNum++){
			if(result[rowNum]==null){
				result[rowNum] = "";
			}
		}
		return result;
	}
	
	/**
	 * ��Cell[]תΪString[],�ַ�������ȵ�Ԫ��1
	 * @param cell excel��cell����
	 * @return
	 */
	public static String[] cell2String(Cell[] cell){
		String[] str = new String[cell.length+1];
		for(int i = 0;i < cell.length;i++){
			str[i] = cell[i].getContents();
		}
		return str;
	}
	
	/**
	 * ��ȡһ��EXCEL������
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static WritableWorkbook getWritableWorkbook(
			HttpServletResponse response) {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook ww = null;
		try {
			ww = Workbook.createWorkbook(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ww;
	}
	
	/**
	 * ɾ��ָ��·���µ��ļ�,���ļ�ɾ��
	 * @param filePath �ļ�·��
	 */
	public static void delFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * ɾ��ĳ�ļ����µ������ļ�
	 * @param parentPath �ļ���·��
	 * @return
	 */
	public static boolean delFiles(String parentPath){
		File fileOne = new File (parentPath);
		String[] fileNames = fileOne.list();
		for(String fn:fileNames){
			File filetmp=new File(parentPath+"/"+fn);
			if(filetmp.isDirectory()){
				delFile(parentPath+"/"+fn);
			}
			try {
				filetmp.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	/**
	 * ��request�е��ļ����ݻ�ȡ����д��ʵ���ļ�������������ˡ�������ļ����ɳ���
	 * Ĭ�ϸ�����ʹ�õ��̣߳�ȷ�����ɵ��ļ��������ظ���
	 * @param tempDir ��ʱ�ļ���ŵ��ļ���·��
	 * @param src Դ�ļ�
	 * @param fileName Դ�ļ��ļ���
	 * @param useSrcName �Ƿ�ʹ���ϴ��ļ�����Ϊ�����ļ���
	 * @return �����ļ���ȫ·��
	 */
	public synchronized static String fileUp(String tempDir,FormFile src,String fileName,boolean useSrcName){
		FileOper.mkdir(tempDir);
		String newFileName = "";
		if(!useSrcName){
			//ʹ�õ�ǰʱ����Ϊ�ϴ��ļ���
			newFileName = System.currentTimeMillis() 
								+ FileOper.getFileSuffix(fileName);
		}else
			newFileName = fileName;
		
		try {
			FileOper.writeFile(src,tempDir, new File(tempDir + "/" + newFileName));
		} catch (FileNotFoundException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return tempDir + "/" + newFileName;
	}
	
	/**
	 * ��ʾͼƬ
	 * @param response
	 * @param in
	 * @throws IOException 
	 */
	public static void showPic(HttpServletResponse response,InputStream in) throws IOException{
		OutputStream outs = response.getOutputStream();   
		response.setContentType("image/gif;charset=UTF-8");
		BufferedInputStream bis=new BufferedInputStream(in);    //���뻺����
		BufferedOutputStream bos=new BufferedOutputStream(outs);   //���������
		byte data[]=new byte[4096];    //�����ֽ���    
		int size=0;    
		size=bis.read(data);
		while (size!=-1){
			bos.write(data,0,size); size=bis.read(data);
		}   
		bis.close();   
		bos.flush();//������������         
		bos.close();          
		outs.close();
	}
	
	/**
	 * ��ȡ��Ŀ����·������web-infͬ����·����󲻰�����/��
	 * @return
	 */
	public static String getFileRealBasePath(){
		String path = "";
		if(System.getProperty("os.name").equals("Linux")){
			path = FileOper.class.getResource("/").toString();
		}else{
			path = FileOper.class.getResource("/").toString().substring(5);
		}
		path = path.substring(1, path.indexOf("WEB-INF")-1); 
		path = path.replaceAll("%20", " ");
		return path;
	}
	
	/**
	 * ʹ��dom4j��ȡ����·����xml�ļ���document
	 * @param xmlPath �ļ�·��
	 * @return
	 * @throws MalformedURLException 
	 */
	public static Document getDocument(String xmlPath) throws MalformedURLException{
		SAXReader reader = new SAXReader();
        Document d = null;
		try {
			d = reader.read(new File(xmlPath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        return d;
		
	}
	
	/**
	 * ʹ��xPath��ȡ�ڵ��б�
	 * @param d document����
	 * @param xPath 
	 * @return
	 */
	public static List<Element> getNodeList(Document d, String xPath) {
		List<Element> list =  d.selectNodes(xPath);
        return list;
    }
	
	/**
	  * getJspUrl ����
	  * <p>����˵��: �����Ի�ҳ���Ƿ���ڣ������ڻ�ȡ���Ի�ҳ�棬�����������ȡͨ����תҳ��</p>
	  * @param request
	  * @param gxhJsp ���Ի�ҳ��·��������/jsp/print/bdd/12317.jsp
	  * @param tyJsp ͨ��ҳ��·��������/jsp/print/bdd/default.jsp
	  * @return String
	  * @author hhy
	  * @date 2011-6-23
	 */
	public static String getJspUrl(HttpServletRequest request,String gxhJsp,String tyJsp){
		File f = new File(request.getRealPath(gxhJsp));
		if(f.exists()){
			return gxhJsp;
		}else{
			return tyJsp;
		}
	}

	public static void main(String[] args) throws MalformedURLException{
		String path = FileOper.class.getResource("/").toString().substring(5);
		path = path.substring(1, path.indexOf("WEB-INF")-1); 
		String xmlpath = path + "/dxfs.xml";
		Document d = FileOper.getDocument(xmlpath);
		Element root = d.getRootElement();
		List<Element> list = FileOper.getNodeList(d, "//dxname");
		System.out.println(list.get(0).getText());
	}
}

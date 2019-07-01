package xgxt.xtwh.zpgl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.upload.FormFile;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

public class XtwhZpglService extends CommService{
	
	XtwhZpglDAO dao=new XtwhZpglDAO();
	/**
	 * 学生照片信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZpxxList(XtwhZpglForm model,String[]outPut,User user) throws Exception {

		return dao.getZpxxList(model,outPut,user);
	}
	
	/**
	  * savePhotoXssc 方法
	  * <p>方法说明: 学生上传照片保存</p>
	  * @param photoPath
	  * @param request
	  * @param photoNameType
	  * @return void
	  * @author hhy
	  * @date 2011-8-17
	 */
	public void savePhotoXssc(String photoPath,HttpServletRequest request,
			String photoNameType){
		File file = new File(photoPath); 
		//System.out.println("----照片路径："+photoPath);
		String[] str = file.list();
		String photoName = "";
		FileInputStream inStream = null;
		String fileType = ".jpg.gif.png.bmp.JPG.GJF.PNG.BMP";
		int falseNum = 0;
		String xh = "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length; i++){
			if(str[i].length()<4||fileType.indexOf(str[i].substring(str[i].length()-4,str[i].length()))==-1){
				sb.append(" 不是图片或者图片格式不匹配\n");
				falseNum++;
				continue;
			}
			File fileOne = new File(photoPath+"/"+str[i]);
			if(fileOne.length()>=100*1024){
				sb.append(" 导入失败：文件过大(大于100K)\n");
				falseNum++;
				continue;
			}
			try {
				inStream = new FileInputStream(photoPath+"/"+str[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			photoName = str[i].substring(0,str[i].indexOf(".")).replaceAll(" ", "");
			HashMap<String,String> xhMap = dao.getXhByType(photoNameType, photoName);
			if(xhMap.isEmpty()){
				sb.append(" 导入失败：因为数据库中没有该学生的相关字段信息\n");
				falseNum++;
			}else
				xh = xhMap.get("xh");
			//统计照片库中该录取号的记录数
			String num = dao.countDataRowNum(xh);
			//根据数据库中统计的结果数进行不同操作
			if("0".equals(num) && !xhMap.isEmpty()){
				dao.insertPhoto(xh,inStream);
			}else if("1".equals(num) && !xhMap.isEmpty()){
				dao.updatePhoto(xh, inStream);
			}else if(Integer.parseInt(num) > 1){
				falseNum++;
				sb.append(" 导入失败：因为数据库中有多个重复数据\n");
			}
			//保存完毕后关闭流
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("errorString", "\n"+sb.toString());
		request.setAttribute("tolNum", str.length);
		request.setAttribute("rightNum", str.length-falseNum);
	}
	
	/**
	 * 将request中的文件内容获取到并写成实体文件，存入服务器端。存入的文件夹由程序
	 * 默认给定。使用单线程，确保生成的文件名不会重复。
	 * @param tempDir 临时文件存放的文件夹路径
	 * @param src 源文件
	 * @param fileName 源文件文件名
	 * @param useSrcName 是否使用上传文件名作为生成文件名
	 * @return 生成文件的全路径
	 */
	public synchronized String fileUp(String tempDir,FormFile src,String fileName,boolean useSrcName){
		FileOper.mkdir(tempDir);
		String newFileName = "";
		if(!useSrcName){
			//使用当前时间作为上传文件名
			newFileName = System.currentTimeMillis() 
								+ FileOper.getFileSuffix(fileName);
		}else
			newFileName = fileName;
		
		try {
			FileOper.writeFile(src,tempDir, new File(tempDir + "/" + newFileName));
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return tempDir + "/" + newFileName;
	}
	
	/**
	 * 将上传的zip文件解压到同目录下的photo文件夹里
	 * @param tempFilePath zip上传路径
	 * @param folderPath 上传文件夹
	 */
	public String unZip(String tempFilePath,String folderPath){
		//文件进行解压
		ZipUtils zipUtils = new ZipUtils();
		String savePath = folderPath + "/photo";
		try {
			zipUtils.unzip(tempFilePath,savePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePath;
	}
	
	/**
	 * 上传照片，并将未导入的照片名抛出
	 * @param photoPath 照片存放路径
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> savePhoto(String photoPath,HttpServletRequest request,
			String photoNameType){
		String zpType=Base.chgNull(request.getParameter("zpType"),"zszp",0);//导入照片类型
		File file = new File(photoPath); 
		//System.out.println("----照片路径："+photoPath);
		String[] str = file.list();
		String photoName = "";
		FileInputStream inStream = null;
		String fileType = ".jpg.gif.png.bmp.JPG.GJF.PNG.BMP";
		int falseNum = 0;
		String lqh = "";
		
		List<HashMap<String,String>>zpscjgList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>errorList=new ArrayList<HashMap<String,String>>();
		
		String xszdmc="";
		if("xh".equalsIgnoreCase(photoNameType)){
			xszdmc="学号";
		}else if("sfzh".equalsIgnoreCase(photoNameType)){
			xszdmc="身份证号";
		}else if("ksh".equalsIgnoreCase(photoNameType)){
			xszdmc="考生号";
		}
		for(int i=0; i<str.length; i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			if(str[i].length()<4||fileType.indexOf(str[i].substring(str[i].length()-4,str[i].length()))==-1){
				
//				sb.append(str[i]+" 不是图片或者图片格式不匹配\n");
				falseNum++;
				continue;
			}
			File fileOne = new File(photoPath+"/"+str[i]);
			if(fileOne.length()>=100*1024){
				
				hashMap.put("xh", str[i].substring(0,str[i].indexOf(".")));
				hashMap.put("czjg", " 导入失败：文件过大(大于100K)");
				errorList.add(hashMap);
				falseNum++;
				continue;
			}
			try {
				inStream = new FileInputStream(photoPath+"/"+str[i]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			photoName = str[i].substring(0,str[i].indexOf(".")).replaceAll(" ", "");
			HashMap<String,String> lqhMap = dao.getXhByType(photoNameType, photoName);
			if(lqhMap.isEmpty()){
				
				
				hashMap.put("xh", str[i].substring(0,str[i].indexOf(".")));
				hashMap.put("czjg", " 导入失败：因为数据库中没有该学生的"+xszdmc+"信息");
				falseNum++;
			}else{
				lqh = lqhMap.get("xh");
			}
			
			//统计照片库中该录取号的记录数
			
			String num = dao.countDataRowNum(lqh);
			//根据数据库中统计的结果数进行不同操作
			if("xszp".equals(zpType)){//新生照片
				if("0".equals(num) && !lqhMap.isEmpty()){
					dao.insertPhoto_xs(lqh,inStream);
				}else if("1".equals(num) && !lqhMap.isEmpty()){
					dao.updatePhoto_xs(lqh, inStream);
				}else if(Integer.parseInt(num) > 1){
					falseNum++;
					
					hashMap.put("czjg", str[i]+" 导入失败：因为数据库中有多个重复数据");
					
				}
			}else{//招生照片
				if("0".equals(num) && !lqhMap.isEmpty()){
					dao.insertPhoto(lqh,inStream);
				}else if("1".equals(num) && !lqhMap.isEmpty()){
					dao.updatePhoto(lqh, inStream);
				}else if(Integer.parseInt(num) > 1){
					falseNum++;
					
					hashMap.put("czjg", str[i]+" 导入失败：因为数据库中有多个重复数据");
					
				}
			}
			if(!hashMap.isEmpty()){
				errorList.add(hashMap);
			}
			//保存完毕后关闭流
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		HashMap<String,String>hashMap=new HashMap<String,String>();
		hashMap.put("czjg", "成功导入"+(str.length-falseNum)+"张照片,失败"+errorList.size()+"张");
		zpscjgList.add(hashMap);
		if(errorList.size()==0){
			//无失败信息
			HashMap<String,String> temp=new HashMap<String, String>();
			temp.put("xh","");
			temp.put("czjg", "无失败信息");
			errorList.add(temp);
		}
		zpscjgList.addAll(errorList);
		return zpscjgList;
	}
	
	public void printZpdr(XtwhZpglForm myForm,String photoNameType,
			HttpServletRequest request,List<HashMap<String,String>>zpscjgList, WritableWorkbook wwb)
			throws Exception {

		
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,4, 0);
			ws.addCell(new Label(0,0,"照片导入("+zpscjgList.get(0).get("czjg")+")",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			String xszdmc="";
			if("xh".equalsIgnoreCase(photoNameType)){
				xszdmc="学号";
			}else if("sfzh".equalsIgnoreCase(photoNameType)){
				xszdmc="身份证号";
			}else if("ksh".equalsIgnoreCase(photoNameType)){
				xszdmc="考生号";
			}
			ws.addCell(new Label(0,1,xszdmc,wcfTytle));
			ws.mergeCells(1, 1,4, 1);
			ws.addCell(new Label(1,1,"操作结果",wcfTytle));
			for(int i=1;i<zpscjgList.size();i++){
				HashMap<String,String>zpscjgMap=zpscjgList.get(i);
				ws.mergeCells(1, i+1,4, i+1);
				ws.addCell(new Label(0,i+1,zpscjgMap.get("xh"),wcfTytle));
				ws.addCell(new Label(1,i+1,zpscjgMap.get("czjg"),wcfTytle));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	  * zpdc 方法
	  * <p>方法说明: 照片导出</p>
	  * @param model
	  * @param response
	  * @return void
	  * @author hhy
	  * @date 2011-8-30
	 */
	public void zpdc(XtwhZpglForm model,HttpServletResponse response){
		List<HashMap<String, String>> list = dao.getXszpList(model);
		ZipOutputStream zos = null;
		DAO dao=DAO.getInstance();
		try {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=image.zip");
			zos = new ZipOutputStream(response.getOutputStream());

			for (int i = 0; i < list.size(); i++) {
				if (!Base.isNull(list.get(i).get("xh"))) {
					//查询出学生相片BLOB
//					Blob blob = dao.getOneBlob("select xh,zp from xszpb where xh=?", new String[]{list.get(i).get("xh")}, "zp");
					Blob blob = getXszp(list.get(i).get("xh"), model.getZpType(), dao);
					if(null!=blob){
						InputStream in2 = blob.getBinaryStream();
						//照片命名（若所选择的命名账号类型相应字段为空则以姓名+学号命名）
						String zpmc = Base.isNull(list.get(i).get(model.getPhotoNameType())) ? list.get(i).get("xm") + list.get(i).get("xh") : list.get(i).get("xm")+list.get(i).get(model.getPhotoNameType());
						//输出相片流
						ZipEntry ze = new ZipEntry(zpmc + ".jpg");
						zos.putNextEntry(ze);
						zos.setEncoding("gbk"); //以中文命名时必须转码，否则乱码
						int blobsize = (int) blob.length();
						byte bu[] = new byte[blobsize];
						int bytesRead = 0;
						
						while ((bytesRead = in2.read(bu)) != -1) {
							zos.write(bu, 0, bytesRead);
						}
						in2.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Blob getXszp(String xh,String type,DAO dao){
		if("xs_zs".equals(type)){//若有新生照片则导出新生，否则导出招生照片
			Blob blob=dao.getOneBlob("select xszp from xszpb where xh=? and xszp is not null and nvl(length(xszp),'0')>0", new String[]{xh}, "xszp");
			if(blob==null){
				blob=dao.getOneBlob("select zp from xszpb where xh=? and zp is not null and nvl(length(zp),'0')>0", new String[]{xh}, "zp");
			}
			return blob;
		}else if("xszp".equals(type)){//新生照片
			return dao.getOneBlob("select xszp from xszpb where xh=? and xszp is not null and nvl(length(xszp),'0')>0", new String[]{xh}, "xszp");
		}else{//导出招生照片
			return dao.getOneBlob("select zp from xszpb where xh=? and zp is not null and nvl(length(zp),'0')>0", new String[]{xh}, "zp");
		}
	}
	
	
	
	/**
	 * 查询学生照片实体文件(png格式，用于excel输出)
	 * @param xh
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public File getPhotoFile(String xh) throws IOException, SQLException {
		InputStream is = dao.getPhotoStream(xh);
		File file =null;
		if(is!=null){
		byte[] buffer = new byte[is.toString().length() / 7]; // 定义
		// buffer为缓冲区每次读取内容的长度
		file = new File(new StringBuilder(System.getProperty("user.dir"))
				.append("\\").append(xh).append(".png").toString());// 将数据写入文件
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream newFile = new FileOutputStream(file, true);

		for (int ch = 0; ch != -1; newFile.write(buffer)) {
			ch = is.read(buffer);
		}
		}
		return file;
	}
	
}

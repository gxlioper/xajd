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
	 * ѧ����Ƭ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZpxxList(XtwhZpglForm model,String[]outPut,User user) throws Exception {

		return dao.getZpxxList(model,outPut,user);
	}
	
	/**
	  * savePhotoXssc ����
	  * <p>����˵��: ѧ���ϴ���Ƭ����</p>
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
		//System.out.println("----��Ƭ·����"+photoPath);
		String[] str = file.list();
		String photoName = "";
		FileInputStream inStream = null;
		String fileType = ".jpg.gif.png.bmp.JPG.GJF.PNG.BMP";
		int falseNum = 0;
		String xh = "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length; i++){
			if(str[i].length()<4||fileType.indexOf(str[i].substring(str[i].length()-4,str[i].length()))==-1){
				sb.append(" ����ͼƬ����ͼƬ��ʽ��ƥ��\n");
				falseNum++;
				continue;
			}
			File fileOne = new File(photoPath+"/"+str[i]);
			if(fileOne.length()>=100*1024){
				sb.append(" ����ʧ�ܣ��ļ�����(����100K)\n");
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
				sb.append(" ����ʧ�ܣ���Ϊ���ݿ���û�и�ѧ��������ֶ���Ϣ\n");
				falseNum++;
			}else
				xh = xhMap.get("xh");
			//ͳ����Ƭ���и�¼ȡ�ŵļ�¼��
			String num = dao.countDataRowNum(xh);
			//�������ݿ���ͳ�ƵĽ�������в�ͬ����
			if("0".equals(num) && !xhMap.isEmpty()){
				dao.insertPhoto(xh,inStream);
			}else if("1".equals(num) && !xhMap.isEmpty()){
				dao.updatePhoto(xh, inStream);
			}else if(Integer.parseInt(num) > 1){
				falseNum++;
				sb.append(" ����ʧ�ܣ���Ϊ���ݿ����ж���ظ�����\n");
			}
			//������Ϻ�ر���
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
	 * ��request�е��ļ����ݻ�ȡ����д��ʵ���ļ�������������ˡ�������ļ����ɳ���
	 * Ĭ�ϸ�����ʹ�õ��̣߳�ȷ�����ɵ��ļ��������ظ���
	 * @param tempDir ��ʱ�ļ���ŵ��ļ���·��
	 * @param src Դ�ļ�
	 * @param fileName Դ�ļ��ļ���
	 * @param useSrcName �Ƿ�ʹ���ϴ��ļ�����Ϊ�����ļ���
	 * @return �����ļ���ȫ·��
	 */
	public synchronized String fileUp(String tempDir,FormFile src,String fileName,boolean useSrcName){
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
	 * ���ϴ���zip�ļ���ѹ��ͬĿ¼�µ�photo�ļ�����
	 * @param tempFilePath zip�ϴ�·��
	 * @param folderPath �ϴ��ļ���
	 */
	public String unZip(String tempFilePath,String folderPath){
		//�ļ����н�ѹ
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
	 * �ϴ���Ƭ������δ�������Ƭ���׳�
	 * @param photoPath ��Ƭ���·��
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> savePhoto(String photoPath,HttpServletRequest request,
			String photoNameType){
		String zpType=Base.chgNull(request.getParameter("zpType"),"zszp",0);//������Ƭ����
		File file = new File(photoPath); 
		//System.out.println("----��Ƭ·����"+photoPath);
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
			xszdmc="ѧ��";
		}else if("sfzh".equalsIgnoreCase(photoNameType)){
			xszdmc="���֤��";
		}else if("ksh".equalsIgnoreCase(photoNameType)){
			xszdmc="������";
		}
		for(int i=0; i<str.length; i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			if(str[i].length()<4||fileType.indexOf(str[i].substring(str[i].length()-4,str[i].length()))==-1){
				
//				sb.append(str[i]+" ����ͼƬ����ͼƬ��ʽ��ƥ��\n");
				falseNum++;
				continue;
			}
			File fileOne = new File(photoPath+"/"+str[i]);
			if(fileOne.length()>=100*1024){
				
				hashMap.put("xh", str[i].substring(0,str[i].indexOf(".")));
				hashMap.put("czjg", " ����ʧ�ܣ��ļ�����(����100K)");
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
				hashMap.put("czjg", " ����ʧ�ܣ���Ϊ���ݿ���û�и�ѧ����"+xszdmc+"��Ϣ");
				falseNum++;
			}else{
				lqh = lqhMap.get("xh");
			}
			
			//ͳ����Ƭ���и�¼ȡ�ŵļ�¼��
			
			String num = dao.countDataRowNum(lqh);
			//�������ݿ���ͳ�ƵĽ�������в�ͬ����
			if("xszp".equals(zpType)){//������Ƭ
				if("0".equals(num) && !lqhMap.isEmpty()){
					dao.insertPhoto_xs(lqh,inStream);
				}else if("1".equals(num) && !lqhMap.isEmpty()){
					dao.updatePhoto_xs(lqh, inStream);
				}else if(Integer.parseInt(num) > 1){
					falseNum++;
					
					hashMap.put("czjg", str[i]+" ����ʧ�ܣ���Ϊ���ݿ����ж���ظ�����");
					
				}
			}else{//������Ƭ
				if("0".equals(num) && !lqhMap.isEmpty()){
					dao.insertPhoto(lqh,inStream);
				}else if("1".equals(num) && !lqhMap.isEmpty()){
					dao.updatePhoto(lqh, inStream);
				}else if(Integer.parseInt(num) > 1){
					falseNum++;
					
					hashMap.put("czjg", str[i]+" ����ʧ�ܣ���Ϊ���ݿ����ж���ظ�����");
					
				}
			}
			if(!hashMap.isEmpty()){
				errorList.add(hashMap);
			}
			//������Ϻ�ر���
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		HashMap<String,String>hashMap=new HashMap<String,String>();
		hashMap.put("czjg", "�ɹ�����"+(str.length-falseNum)+"����Ƭ,ʧ��"+errorList.size()+"��");
		zpscjgList.add(hashMap);
		if(errorList.size()==0){
			//��ʧ����Ϣ
			HashMap<String,String> temp=new HashMap<String, String>();
			temp.put("xh","");
			temp.put("czjg", "��ʧ����Ϣ");
			errorList.add(temp);
		}
		zpscjgList.addAll(errorList);
		return zpscjgList;
	}
	
	public void printZpdr(XtwhZpglForm myForm,String photoNameType,
			HttpServletRequest request,List<HashMap<String,String>>zpscjgList, WritableWorkbook wwb)
			throws Exception {

		
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,4, 0);
			ws.addCell(new Label(0,0,"��Ƭ����("+zpscjgList.get(0).get("czjg")+")",wcfTytle));
			
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
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			String xszdmc="";
			if("xh".equalsIgnoreCase(photoNameType)){
				xszdmc="ѧ��";
			}else if("sfzh".equalsIgnoreCase(photoNameType)){
				xszdmc="���֤��";
			}else if("ksh".equalsIgnoreCase(photoNameType)){
				xszdmc="������";
			}
			ws.addCell(new Label(0,1,xszdmc,wcfTytle));
			ws.mergeCells(1, 1,4, 1);
			ws.addCell(new Label(1,1,"�������",wcfTytle));
			for(int i=1;i<zpscjgList.size();i++){
				HashMap<String,String>zpscjgMap=zpscjgList.get(i);
				ws.mergeCells(1, i+1,4, i+1);
				ws.addCell(new Label(0,i+1,zpscjgMap.get("xh"),wcfTytle));
				ws.addCell(new Label(1,i+1,zpscjgMap.get("czjg"),wcfTytle));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	  * zpdc ����
	  * <p>����˵��: ��Ƭ����</p>
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
					//��ѯ��ѧ����ƬBLOB
//					Blob blob = dao.getOneBlob("select xh,zp from xszpb where xh=?", new String[]{list.get(i).get("xh")}, "zp");
					Blob blob = getXszp(list.get(i).get("xh"), model.getZpType(), dao);
					if(null!=blob){
						InputStream in2 = blob.getBinaryStream();
						//��Ƭ����������ѡ��������˺�������Ӧ�ֶ�Ϊ����������+ѧ��������
						String zpmc = Base.isNull(list.get(i).get(model.getPhotoNameType())) ? list.get(i).get("xm") + list.get(i).get("xh") : list.get(i).get("xm")+list.get(i).get(model.getPhotoNameType());
						//�����Ƭ��
						ZipEntry ze = new ZipEntry(zpmc + ".jpg");
						zos.putNextEntry(ze);
						zos.setEncoding("gbk"); //����������ʱ����ת�룬��������
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
		if("xs_zs".equals(type)){//����������Ƭ�򵼳����������򵼳�������Ƭ
			Blob blob=dao.getOneBlob("select xszp from xszpb where xh=? and xszp is not null and nvl(length(xszp),'0')>0", new String[]{xh}, "xszp");
			if(blob==null){
				blob=dao.getOneBlob("select zp from xszpb where xh=? and zp is not null and nvl(length(zp),'0')>0", new String[]{xh}, "zp");
			}
			return blob;
		}else if("xszp".equals(type)){//������Ƭ
			return dao.getOneBlob("select xszp from xszpb where xh=? and xszp is not null and nvl(length(xszp),'0')>0", new String[]{xh}, "xszp");
		}else{//����������Ƭ
			return dao.getOneBlob("select zp from xszpb where xh=? and zp is not null and nvl(length(zp),'0')>0", new String[]{xh}, "zp");
		}
	}
	
	
	
	/**
	 * ��ѯѧ����Ƭʵ���ļ�(png��ʽ������excel���)
	 * @param xh
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public File getPhotoFile(String xh) throws IOException, SQLException {
		InputStream is = dao.getPhotoStream(xh);
		File file =null;
		if(is!=null){
		byte[] buffer = new byte[is.toString().length() / 7]; // ����
		// bufferΪ������ÿ�ζ�ȡ���ݵĳ���
		file = new File(new StringBuilder(System.getProperty("user.dir"))
				.append("\\").append(xh).append(".png").toString());// ������д���ļ�
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

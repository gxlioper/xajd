/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-22 ����05:06:46 
 */  
package com.zfsoft.xgxt.zjly.zhf.sh;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.bddc.ExportService;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfForm;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfService;
import com.zfsoft.xgxt.zjly.zhf.zhfhz.ZhfhzDao;
import com.zfsoft.xgxt.zjly.zhf.zhfhz.ZhfhzService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷ����(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-22 ����05:06:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfshService extends SuperServiceImpl<ZhfForm, ZhfshDao>{
	private ZhfshDao dao = new ZhfshDao();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	
	private ZhfService zhfService = new ZhfService();
	ExportService exportService = new ExportService();
	/** 
	 * @����:��ȡģ����Ŀ�б� (������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����10:06:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmmkList(){
		return dao.getXmmkList();
	}
	
	/** 
	 * @����:��ȡ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����10:07:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmYsdByXh(String xh){
		return dao.getJfxmYsdByXh(xh);
	}
	
	/** 
	 * @����:��ȡδ���б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����10:07:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmWsdByXh(String xh){
		return dao.getJfxmWsdByXh(xh);
	}
	
	/** 
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����02:11:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean savePlSd(ZhfForm form) throws Exception{		
		return dao.saveSdByXh(form.getXhs(),form.getShr(),form.getShsj());		
	}
	
	/** 
	 * @����:������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����02:19:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSd(ZhfForm model)throws Exception{
		List<ZhfForm> list = new ArrayList<ZhfForm>();
		//����
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		String[] jfxms = model.getJfxmdms();
		String[] xmdms = model.getXmmkdms();
		String[] sxsms = model.getSxsms();
		String[] cysjs = model.getCysjs();
		model.setShzt("1");
		for(int i = 0;i<jfxms.length;i++){
			ZhfForm form = new ZhfForm();
			form.setXh(model.getXh());
			form.setXmmkdm(xmdms[i]);
			form.setJfxmdm(jfxms[i]);
			form.setSxsm(sxsms[i]);
			form.setCysj(cysjs[i]);
			form.setShr(model.getShr());
			form.setShsj(model.getShsj());
			form.setShzt(model.getShzt());
			FormFile file = (FormFile) files.get("fj"+i);
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					form.setFj(destFile.getAbsolutePath());
					form.setFjmc(file.getFileName());
				}
			}
			else{
				form.setFj("");
				form.setFjmc("");
			}
			list.add(form);
		}
		if(zhfService.isExist(list)){
			return false;
		}else{
			dao.saveSdByXh(new String[]{model.getXh()}, model.getShr(), model.getShsj());
			return dao.saveSd(list);			
		}		
	} 
	
	/** 
	 * @����:����id�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����03:31:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param shr
	 * @param shsj
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSdById(String[] ids,String shr,String shsj) throws Exception{
		return dao.saveSdById(ids, shr, shsj);
	}
	
	
	/** 
	 * @����:�õ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-24 ����10:59:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zcfsForm
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws 
	 */
	public File getZhfFile(ZhfForm form, User user) throws Exception{	
	 List<HashMap<String, String>> xmmkList = getXmmkList();
		//����������ͷ
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("xh", "ѧ��");
		map.put("xm", "����");
		map.put("nj", "�꼶");
		map.put("xymc", "ϵ��");
		map.put("zymc", "רҵ");
		map.put("bjmc", "�༶");
		
		for (int i = 0 , j = xmmkList.size() ; i < j ; i++){
			map.put("fs"+i, xmmkList.get(i).get("xmmkmc"));
		}
		map.put("zf","�ܷ�");
		map.put("shztmc", "���״̬");
		//��������
		form.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getAllList(form, user);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}

	/**
	 * @param thyj 
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-10 ����01:48:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param idsss
	 * @param shr
	 * @param shsj
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveBack(String[] idsss, String shr, String shsj, String thyj) throws Exception {
		return dao.saveBack(idsss, shr, shsj,thyj);
	}

	/** 
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-10 ����04:59:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmThByXh(String xh) {
		return dao.getJfxmThByXh(xh);
	}

	/**
	 * @throws Exception  
	 * @����:��д����word
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-21 ����06:01:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File createWord(ZhfForm model) throws Exception {
		String xh = model.getXh();
		String filepath = model.getFilepath();
		File filefolder = new File(filepath);
		if(!filefolder.exists()){
			filefolder.mkdir();
		}
		String path = model.getFilepath()+xh+"_ѧ���ۺϷֻ��ܱ�.doc";
		File file = new File(path);
		file.createNewFile();
		Document document = new Document(PageSize.A4);  
		RtfWriter2.getInstance(document,new FileOutputStream(file));
	     BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
		   Font yourFont = new Font(bfChinese,12,Font.BOLD);  
		HeaderFooter footer = new HeaderFooter(new Phrase("ҳ�룺",yourFont), true);  
		footer.setBorder(Rectangle.NO_BORDER);  
		footer.setAlignment(Element.ALIGN_CENTER);//����   
		document.setFooter(footer);  
		document.open();
		 //������������  
        //����������  
        Font titleFont = new Font(bfChinese,14,Font.BOLD);  
        //����������  
        Paragraph title = new Paragraph("�㽭����ְҵѧԺѧ���ۺ�����ѧ�ֻ��ܱ�",titleFont); 
    	title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
	    //������Ϣtable
	    	Table table = this.writeJbxxTable(xh,model);
	    	if(null != table){
	    		document.add(table);
	    	}
	    //�ۺϷ���Ϣtable
	        ZhfhzDao zhfhzDao = new ZhfhzDao();
	    	 List<HashMap<String, String>> mkzflist = zhfhzDao.getmkzf(xh);
	          for (int i = 0; i < mkzflist.size(); i++) {
	        	    	Table mktable = this.writeSxszTable(xh,model,mkzflist.get(i).get("xmmkmc"),mkzflist.get(i).get("mkzf"),mkzflist.get(i).get("sfhg"));
	        	    	if(null != mktable){
	        	    		document.add(mktable);
	        	    	}
	          }
		document.close();
		return file;
	}

	/**
	 * @throws Exception  
	 * @����:��ģ�黭word
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-9 ����10:43:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param model
	 * @return
	 * Table �������� 
	 * @throws 
	 */
	private Table writeSxszTable(String xh, ZhfForm model, String mkmc, String mkzf, String sfhg) throws Exception {
	     ZhfhzDao zhfhzDao = new ZhfhzDao();	
		Font bold_fontChinese = exportService.getFontChinese();
		Table table = new Table(6);//����һ��6�еı��
        table.setAlignment(Element.ALIGN_CENTER);//����   
        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
        table.setAutoFillEmptyCells(true);//�Զ�����
        table.setBorder(0);//���ñ߿�
        table.setPadding(1f);//���ü��
        table.setWidth(101f);//���ñ����
        table.setWidths(new int[]{20,6,15,20,8,12});
        //����/��ע��
        Cell tableName =new Cell();
        if ("��������".equals(mkmc)) {
        	 tableName = new Cell(new Paragraph(mkmc+"   "+mkzf+"��   ",bold_fontChinese));
		}else {
			 tableName = new Cell(new Paragraph(mkmc+"   "+mkzf+"��   "+sfhg,bold_fontChinese));
		}
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        tableName.setColspan(6);//�ϲ���Ԫ��
        table.addCell(tableName);
        //��ͷ
    	String[] zdmcArr ={"�Ʒ���Ŀ","�÷�","���","��������","�����","����/���ʱ��"};
        for (int i = 0; i < zdmcArr.length; i++) {
        	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
              columnName.setUseAscender(true);
              columnName.setUseDescender(true);
              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
              table.addCell(columnName);
		}
        //ȡ�±�������
        List<HashMap<String, String>> Xmlist = zhfhzDao.getsingleMkXmlist(xh,mkmc);
        String[] zdArr1 ={"lb","sxsm","sxf","cysj"};
        String[] zdArr2 = {"jfxmmc","xmzf"};//ǰ����׼���ϲ�
        String jfxmmc ="";//��ǼƷ���Ŀ
        int sum=0;
         for (int i = 0; i < Xmlist.size(); i++) {
        	 String fsstr = Xmlist.get(i).get("fs");
				String[] ts =fsstr.split(";");
        	if (!Xmlist.get(i).get("jfxmmc").equals(jfxmmc)) {
 				for (int j = 0; j < zdArr2.length; j++) {
 					Cell cell = null;
 					cell = new Cell(new Paragraph(Xmlist.get(i).get(zdArr2[j]),bold_fontChinese));
 					cell.setUseAscender(true);
 					cell.setUseDescender(true);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 					if("jfxmmc".equals(zdArr2[j])){
 						if (ts.length>1) {
 							sum=ts.length;
						}else if (ts.length==1) {
							sum=1;
						}
 						jfxmmc=Xmlist.get(i).get(zdArr2[j]);
 					}
 					cell.setRowspan(sum);
 					table.addCell(cell);
 				}
    		}
			for (int j = 0; j < zdArr1.length; j++) {
  				  Cell columnName = new Cell(new Paragraph(Xmlist.get(i).get(zdArr1[j]),bold_fontChinese));
  	              columnName.setUseAscender(true);
  	              columnName.setUseDescender(true);
  	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
  	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
  	              table.addCell(columnName);
        		}
         }
        return table;
	}

	/**
	 * @throws Exception  
	 * @����:����ѧ���ۺϷֻ���table  (20170309����������Ϊ��ģ����ʾ)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-22 ����02:20:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param model
	 * @return
	 * Table �������� 
	 * @throws 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private Table writeZhfxxTable(String xh, ZhfForm model) throws Exception {
		Font bold_fontChinese = exportService.getFontChinese();
		Table table = new Table(8);//����һ��5�еı��
        table.setAlignment(Element.ALIGN_CENTER);//����   
        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
        table.setAutoFillEmptyCells(true);//�Զ�����
        table.setBorder(0);//���ñ߿�
        table.setPadding(1f);//���ü��
        table.setWidth(101f);//���ñ����
       
        //����/��ע��
        Cell tableName = new Cell(new Paragraph("�ۺϷ���Ϣ",bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        tableName.setColspan(8);//�ϲ���Ԫ��
        table.addCell(tableName);
        //��ͷ
    	String[] zdmcArr ={"ģ��","�÷�","״̬","�Ʒ���Ŀ","�÷�","���","��������","����/���ʱ��"};
        for (int i = 0; i < zdmcArr.length; i++) {
        	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
              columnName.setUseAscender(true);
              columnName.setUseDescender(true);
              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
              table.addCell(columnName);
		}
        //ȡ�±�������
        ZhfhzDao zhfhzDao = new ZhfhzDao();	
        List<HashMap<String, String>> mkxmlist = zhfhzDao.getMkxmListforword(xh);
        String[] zdArr1 ={"jfxmmc","jfxmzf","lb","sxsm","cysj"};
        String[] zdArr2 ={"xmmkmc","mkzf","hgf"};
        HashMap<String, String> map = new HashMap<String, String> ();
       int sx=0;
       int rw=0;
       int sx2=0;
       int zy=0;
       int gr=0;
        for (int i = 0; i < mkxmlist.size(); i++) {
        		if (mkxmlist.get(i).get("xmmkmc").equals("˼������")) {
        			sx++;
        		}else if (mkxmlist.get(i).get("xmmkmc").equals("��������")) {
        			rw++;
				}else if (mkxmlist.get(i).get("xmmkmc").equals("��������")) {
        			sx2++;
				}else if (mkxmlist.get(i).get("xmmkmc").equals("ְҵ����")) {
        			zy++;
				}else if (mkxmlist.get(i).get("xmmkmc").equals("��������")) {
					gr++;
				}
        }
        	map.put("˼������", String.valueOf(sx));
			map.put("��������", String.valueOf(rw));
			map.put("��������", String.valueOf(sx2));
			map.put("ְҵ����", String.valueOf(zy));
			map.put("��������", String.valueOf(gr));
        int sum =0;
        String xmmkmc ="";
        for (int i = 0; i < mkxmlist.size(); i++) {
        	if (mkxmlist.get(i).get("xmmkmc").equals(xmmkmc)) {
			}else {
				for (int j = 0; j < zdArr2.length; j++) {
					Cell cell = null;
					cell = new Cell(new Paragraph(mkxmlist.get(i).get(zdArr2[j]),bold_fontChinese));
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
					if("xmmkmc".equals(zdArr2[j])){
						Iterator it = map.keySet().iterator();  
						while (it.hasNext()) {  
							String key = (String) it.next();//��ǰkeyֵ
							if(key.equals(mkxmlist.get(i).get(zdArr2[j]))){//��ȡvalue �� ��֪����value�Ƚ�
								sum = Integer.valueOf(map.get(key));  
								xmmkmc =mkxmlist.get(i).get(zdArr2[j]);//���ģ��
							}
						}  
					}
					cell.setRowspan(sum);
					table.addCell(cell);
				}
			}
					for (int j = 0; j < zdArr1.length; j++) {
		  				  Cell columnName = new Cell(new Paragraph(mkxmlist.get(i).get(zdArr1[j]),bold_fontChinese));
		  	              columnName.setUseAscender(true);
		  	              columnName.setUseDescender(true);
		  	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		  	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		  	              table.addCell(columnName);
		        		}
				}
        return table;
	}

	/**
	 * @throws Exception  
	 * @����:ѧ��������Ϣ
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-21 ����06:06:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param model
	 * @return
	 * Table �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	private Table writeJbxxTable(String xh, ZhfForm model) throws Exception {
		ZhfhzService zhfhzservice = new ZhfhzService();	
		HashMap<String,String> xsjbxx = zhfhzservice.getXsxx(xh);
		 List listKey = new ArrayList();  
	       List listValue = new ArrayList();  
		 Iterator it = xsjbxx.keySet().iterator();  
	       while (it.hasNext()) {  
	           String key = it.next().toString();
	           if("xh".equals(key)){
	        	   listKey.add("ѧ��");  
	           }else if ("xm".equals(key)) {
	        	   listKey.add("����"); 
	           }else if ("xymc".equals(key)) {
	        	   listKey.add("ϵ��"); 
	           }else if ("bjmc".equals(key)) {
	        	   listKey.add("�༶"); 
	           }else if ("sjhm".equals(key)) {
	        	   listKey.add("��ϵ��ʽ"); 
	           }else if ("xb".equals(key)) {
	        	   listKey.add("�Ա�"); 
	           }else if ("zymc".equals(key)) {
	        	   listKey.add("רҵ����"); 
	           }else{
	        	   listKey.add("ѧ��"); 
	           }
	           listValue.add(xsjbxx.get(key));  
	       }  
		Font bold_fontChinese = exportService.getFontChinese();
		Table table = new Table(5);//����һ��5�еı��
        table.setAlignment(Element.ALIGN_CENTER);//����   
        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
        table.setAutoFillEmptyCells(true);//�Զ�����
        table.setBorder(0);//���ñ߿�
        table.setPadding(1f);//���ü��
        table.setWidth(101f);//���ñ����
        //�����п�
        table.setWidths(new int[]{15,20,15,22,12});
        //����/��ע��
        Cell tableName = new Cell(new Paragraph("ѧ��������Ϣ",bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        tableName.setColspan(5);//�ϲ���Ԫ��
        table.addCell(tableName);
        //����Զ����������Ϣ�����ֶ�Ϊ�ջ����С��10��ֱ�ӷ��ؿ�
        for (int i = 0; i < listKey.size(); i++) {
        	Cell columnName = new Cell(new Paragraph((String) listKey.get(i),bold_fontChinese));
            columnName.setUseAscender(true);
            columnName.setUseDescender(true);
            columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
            columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
            table.addCell(columnName);
            Cell columnZdContent = new Cell(new Paragraph((String) listValue.get(i) ,bold_fontChinese));
            columnZdContent.setUseAscender(true);
            columnZdContent.setUseDescender(true);
            columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
            columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
            table.addCell(columnZdContent);
            Cell cell = null;
			if((i+1)== 2){
				cell = new Cell(new Paragraph("",bold_fontChinese));
				cell.setUseAscender(true);
				cell.setUseDescender(true);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
				cell.setRowspan(4);
			    byte[] byteArr = exportService.showPhoto(xh, model.getDefaultimagepath()); 
			    //���ֽ�����ȡͼƬAPI�ɲ������
			    Image image = Image.getInstance(byteArr);
			    image.scaleAbsoluteWidth(70f);
			    image.scaleAbsoluteHeight(85f);
			    //�����������ָ��λ��
			    cell.add(image);
				table.addCell(cell);
			}
		}
        ZhfhzService service = new ZhfhzService();	
		HashMap<String,String> zfMap = service.getZfs(xh);
		String zfs = zfMap.get("zfs");
		if(zfs != null&& zfs !="") {
			if(".".equalsIgnoreCase(zfs.substring(0, 1))) {
				zfs = "0"+zfs;
			}
		}
		if(zfs == null || zfs =="" || "0".equalsIgnoreCase(zfs)){		
			zfs = "";
		}	
        Cell columnName = new Cell(new Paragraph("�ܷ�",bold_fontChinese));
        columnName.setUseAscender(true);
        columnName.setUseDescender(true);
        columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
        columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        columnName.setColspan(1);
        columnName.setRowspan(5);
        Cell columnName1 = new Cell(new Paragraph(zfs,bold_fontChinese));
        columnName1.setUseAscender(true);
        columnName1.setUseDescender(true);
        columnName1.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
        columnName1.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        columnName1.setColspan(1);
        columnName1.setRowspan(5);
        Cell columnName2 = new Cell(new Paragraph("ѧ�������",bold_fontChinese));
        columnName2.setUseAscender(true);
        columnName2.setUseDescender(true);
        columnName2.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
        columnName2.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        columnName2.setColspan(1);
        columnName2.setRowspan(5);
        Cell columnName3 = new Cell(new Paragraph("",bold_fontChinese));
        columnName3.setUseAscender(true);
        columnName3.setUseDescender(true);
        columnName3.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
        columnName3.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
        columnName3.setColspan(2);
        columnName3.setRowspan(5);
        table.addCell(columnName);
        table.addCell(columnName1);
        table.addCell(columnName2);
        table.addCell(columnName3);
		return table;
	}
	

	/**
	 * @throws Exception  
	 * @����:��������word
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-22 ����03:23:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File createZipWord(ZhfForm model) throws Exception {
		String[] xhArr = model.getXhArr();
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < xhArr.length; i++) {
			model.setXh(xhArr[i]);
			File file = this.createWord(model);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		return zipFile;
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-3-13 ����07:51:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param time
	 * @param user
	 * @param values
	 * void �������� 
	 * @throws 
	 */
	public void addlog(String time, User user, String values) throws Exception {
		 dao.addlog(time,user,values);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-3-13 ����08:06:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * void �������� 
	 * @throws 
	 */
	public void dellog(String values) throws Exception {
		// TODO �Զ����ɷ������
		dao.dellog(values);
	}

	
}

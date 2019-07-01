/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-18 ����10:35:18 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.misc.BASE64Decoder;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.kycxgl.kyxmgl.KyxmglService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Row;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.zdybd.dao.ZdybdBaseDao;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhService;
import com.zfsoft.xgxt.rcsw.cwsjcx.CwsjService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgService;
import com.zfsoft.xgxt.zxdk.syddk.SyddkService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-8-18 ����10:35:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExportService extends SuperServiceImpl<ExportModel, ExportDao> {
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����08:44:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkList(ExportModel exportmodel,User user,String type){
		String zgh = user.getUserName();
		String[] selectzd   = exportmodel.getSelectCol();
		List<HashMap<String, String>> gndmlist = null;
		if(null != selectzd && selectzd.length > 0){
			
		}else{
			gndmlist = dao.getGnmkInSzb(zgh,type);
			if(null != gndmlist && gndmlist.size() > 0){
				return gndmlist;
			}else{
				gndmlist = dao.getGnmkInSzb("public",type);
				if(null != gndmlist && gndmlist.size() > 0){
					return gndmlist;
				}else{
					gndmlist = dao.getGnmkMrList();
					
				}
			}
		}
		return gndmlist;
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ò�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����10:57:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBcszDc(ExportModel model) throws Exception{
		//selectCol 
		String[] selectCol = model.getSelectCol();
		//ѡ���еĳ���
		int sellen = selectCol.length;
		if(sellen == 1 && StringUtils.isNull(selectCol[0]) ){
			sellen = 0;
		}
		//unselectCol
		//����֮ǰ��ɾ�����ñ���ԭ�е�����
		dao.delGndm(model.getZgh());
		//δѡ���еĳ���
		String[] unselectCol = model.getUnselectCol();
		int unsellen = unselectCol.length;
		if(unsellen == 1 && StringUtils.isNull(unselectCol[0]) ){
			unsellen = 0;
		}
		if(0 == sellen && 0 == unsellen){
			return false;
		}
		return dao.saveBcszDc(selectCol, unselectCol, sellen, unsellen, model.getZgh());
	}
	
	/**
	 * @throws DocumentException 
	 * @throws IOException 
	 * 
	 * @����: ���ɵ���word�ļ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����05:42:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File createWord(ExportModel model) throws Exception{
		String xh = model.getXh();
		String filepath = model.getFilepath();
		//��ȥ������û��ļ��д治����
		File filefolder = new File(filepath);
		if(!filefolder.exists()){
			filefolder.mkdirs();
		}
		String path = model.getFilepath()+xh+"ѧ����Ϣ����.doc";
		//File file = new File();
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		
		Document document = new Document(PageSize.A4);  
		RtfWriter2.getInstance(document,new FileOutputStream(file));
		document.open();
		 //������������  
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
        //����������  
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        //����������  
        Font contextFont = new Font(bfChinese,13,Font.NORMAL);  
        Paragraph title = new Paragraph("ѧ����Ϣ����",titleFont); 
    	title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		//��ȡ��ѡ�е���
	    String[] selectCol = model.getSelectCol();
	    List<String> selectColArr = new ArrayList<String>();
	    List<String> selectColNameArr = new ArrayList<String>();
        HashMap<String, String> zdyMap = new XsxxglService().getXsxxByXh(xh);
	    for (int i = 0; i < selectCol.length; i++) {
			String[] lsArr = selectCol[i].split("@");
			selectColArr.add(lsArr[0]);
			selectColNameArr.add(lsArr[1]);
		} 
	    List<HashMap<String, String>> selectColListInFlszb = dao.getSelectMkinFlszb(selectColArr.toArray(new String[]{}));
	    //������������ģ���
	    for (int i = 0; i < selectCol.length; i++) {
	    	HashMap<String, String>lsMap = null;
	    	for (int j = 0; j < selectColListInFlszb.size(); j++) {
				if(selectColArr.get(i).equals(selectColListInFlszb.get(j).get("flszid")) ){
					lsMap = selectColListInFlszb.get(j);
					break;
				}
			}
	    	if(null == lsMap){
	    		continue;
	    	}
	    	Table table = this.writeTable(lsMap, selectColNameArr.get(i),xh,zdyMap,model);
	    	if(null != table){
	    		document.add(table);
	    	}
	    	
		}
		document.close();
		return file;
	}
	//�ο�ѧ����Ϣ�ĵģ�һЩ����û�����֣���xhӦ����zgh
	public File createWordForTea(ExportModel model) throws Exception{
		String xh = model.getXh();
		String filepath = model.getFilepath();
		//��ȥ������û��ļ��д治����
		File filefolder = new File(filepath);
		if(!filefolder.exists()){
			filefolder.mkdirs();
		}
		String path = model.getFilepath()+xh+"��ʦ��Ϣ����.doc";
		//File file = new File();
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();

		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document,new FileOutputStream(file));
		document.open();
		//������������
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		//����������
		Font titleFont = new Font(bfChinese,16,Font.BOLD);
		//����������
		Font contextFont = new Font(bfChinese,13,Font.NORMAL);
		Paragraph title = new Paragraph("��ʦ��Ϣ����",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		//��ȡ��ѡ�е���
		String[] selectCol = model.getSelectCol();
		List<String> selectColArr = new ArrayList<String>();
		List<String> selectColNameArr = new ArrayList<String>();
		HashMap<String, String> zdyMap = new FdyxxService().getFdyInfo(xh);
		for (int i = 0; i < selectCol.length; i++) {
			String[] lsArr = selectCol[i].split("@");
			selectColArr.add(lsArr[0]);
			selectColNameArr.add(lsArr[1]);
		}
		List<HashMap<String, String>> selectColListInFlszb = dao.getSelectMkinFlszb(selectColArr.toArray(new String[]{}));
		//������������ģ���
		for (int i = 0; i < selectCol.length; i++) {
			HashMap<String, String>lsMap = null;
			for (int j = 0; j < selectColListInFlszb.size(); j++) {
				if(selectColArr.get(i).equals(selectColListInFlszb.get(j).get("flszid")) ){
					lsMap = selectColListInFlszb.get(j);
					break;
				}
			}
			if(null == lsMap){
				continue;
			}
			Table table = this.writeTableForTea(lsMap, selectColNameArr.get(i),xh,zdyMap,model);
			if(null != table){
				document.add(table);
			}

		}
		document.close();
		return file;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����: ����wordѹ�����ļ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����05:42:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File createZipWord(ExportModel model) throws Exception{
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
	public File createZipWordForTea(ExportModel model) throws Exception{
		String[] xhArr = model.getXhArr();
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < xhArr.length; i++) {
			model.setXh(xhArr[i]);
			File file = this.createWordForTea(model);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		return zipFile;
	}

	
	/**
	 * 
	 * @����: ��ȡ���ĸ�ʽ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-26 ����09:58:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Font �������� 
	 * @throws
	 */
	public Font getFontChinese(){
		try {
		    //����PDF֧������
		    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", false);  
	        Font bold_fontChinese = new Font(bfChinese, 12, Font.BOLD,  Color.BLACK);
	        return bold_fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @����: ��ȡ���ĸ�ʽ���ĸ�ʽ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-26 ����09:58:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Font �������� 
	 * @throws
	 */
	public Font getFontChineseNormal(){
		try {
		    //����PDF֧������
		    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", false);  
	        Font bold_fontChinese = new Font(bfChinese, 11, Font.NORMAL,  Color.BLACK);
	        return bold_fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����ҵ��ģ�黭table
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����10:12:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsMap
	 * @return
	 * Table �������� 
	 * @throws
	 */
	public Table writeTable(HashMap<String, String> lsMap,String name,String xh, HashMap<String, String> zdyMap,ExportModel model) throws Exception{
		String bdszz = lsMap.get("bdszz");
		Table table = null;
		if(StringUtils.isNull(bdszz)){
			if("xsxx_query_xsxx_jbxx".equalsIgnoreCase(lsMap.get("flszid"))){
				table = this.writeJbxx(name, xh, lsMap, zdyMap,model.getDefaultimagepath());
			}else{
				table = this.writeZddyzdTable(name, xh, lsMap.get("flszid"), zdyMap);
			}
		}else{
			if("thtdkd:'110px'".equals(bdszz)){
				return null;
			}else if("rcxwHidDiv".equals(bdszz)){
				table = this.writeTableRcxwjl(xh, name);
			}else{//�Զ�����б�ģ��
				JSONObject jsonParaSzz = JSONObject.fromString("{"+bdszz+"}");
			
				//�б��ֶ�
				String[] zdArr = jsonParaSzz.getString("zd").split(",");
				//�б��ֶ�����
				String[] zdmcArr = jsonParaSzz.getString("zdmc").split(",");
				String dataSrc =  jsonParaSzz.getString("src");
				 table = this.writeListTable(zdArr, zdmcArr, dataSrc, name,xh);
			}
		}
//		Font bold_fontChinese = this.getFontChinese();
//		 //���ñ�ͷ
//	    Table table = this.getTableHeader(bold_fontChinese,5);
//       
//       //���ñ�����
//	    table = this.getTableBody(table, bold_fontChinese);
//       
//       //���ñ�β
//         table = this.getTableBottom(table, bold_fontChinese);
         return table;
	}
	public Table writeTableForTea(HashMap<String, String> lsMap,String name,String xh, HashMap<String, String> zdyMap,ExportModel model) throws Exception{
		String bdszz = lsMap.get("bdszz");
		Table table = null;
		if(StringUtils.isNull(bdszz)){
			if("szdw_fdyxx_query_jbxx".equalsIgnoreCase(lsMap.get("flszid"))){
				table = this.writeJbxx(name, xh, lsMap, zdyMap,model.getDefaultimagepath());
			}else{
				table = this.writeZddyzdTable(name, xh, lsMap.get("flszid"), zdyMap);
			}
		}else{
			if("thtdkd:'110px'".equals(bdszz)){
				return null;
			}else if("rcxwHidDiv".equals(bdszz)){
				table = this.writeTableRcxwjl(xh, name);
			}else {//�Զ�����б�ģ��
				JSONObject jsonParaSzz = JSONObject.fromString("{"+bdszz+"}");

				//�б��ֶ�
				String[] zdArr = jsonParaSzz.getString("zd").split(",");
				//�б��ֶ�����
				String[] zdmcArr = jsonParaSzz.getString("zdmc").split(",");
				String dataSrc =  jsonParaSzz.getString("src");
				table = this.writeListTableForTea(zdArr, zdmcArr, dataSrc, name,xh);
			}
		}
//		Font bold_fontChinese = this.getFontChinese();
//		 //���ñ�ͷ
//	    Table table = this.getTableHeader(bold_fontChinese,5);
//
//       //���ñ�����
//	    table = this.getTableBody(table, bold_fontChinese);
//
//       //���ñ�β
//         table = this.getTableBottom(table, bold_fontChinese);
		return table;
	}
	/**
	 * @throws Exception 
	 * @����: ���б�չʾģ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����11:04:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdArr
	 * @param zdmcArr
	 * @param dataSrc
	 * @param name
	 * @return
	 * Table �������� 
	 * @throws
	 */
	public Table writeListTable(String[] zdArr,String[] zdmcArr,String dataSrc,String name,String xh) throws Exception{
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(zdArr.length);//����һ��5�еı��
        table.setAlignment(Element.ALIGN_CENTER);//����   
        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
        table.setAutoFillEmptyCells(true);//�Զ�����
        table.setBorder(0);//���ñ߿�
        table.setPadding(1f);//���ü��
        table.setWidth(101f);//���ñ����

        //����/��ע��
        Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
      //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
        tableName.setColspan(zdArr.length);//�ϲ���Ԫ��
        table.addCell(tableName);
        //��ͷ
        for (int i = 0; i < zdmcArr.length; i++) {
        	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
              columnName.setUseAscender(true);
              columnName.setUseDescender(true);
              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
              table.addCell(columnName);
		}
        //����
        //ȡ�±�������
        List<HashMap<String, String>> listData = this.getXsxxCklist(xh, dataSrc);
        for (int i = 0; i < listData.size(); i++) {
			for (int j = 0; j < zdArr.length; j++) {
				  Cell columnName = new Cell(new Paragraph(listData.get(i).get(zdArr[j]),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              table.addCell(columnName);
			}
		}
        return table;
	}
	public Table writeListTableForTea(String[] zdArr,String[] zdmcArr,String dataSrc,String name,String xh) throws Exception{
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(zdArr.length);//����һ��5�еı��
		table.setAlignment(Element.ALIGN_CENTER);//����
		table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		table.setAutoFillEmptyCells(true);//�Զ�����
		table.setBorder(0);//���ñ߿�
		table.setPadding(1f);//���ü��
		table.setWidth(101f);//���ñ����

		//����/��ע��
		Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
		tableName.setUseAscender(true);
		tableName.setUseDescender(true);
		tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
		tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		//  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		tableName.setColspan(zdArr.length);//�ϲ���Ԫ��
		table.addCell(tableName);
		//��ͷ
		for (int i = 0; i < zdmcArr.length; i++) {
			Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
			columnName.setUseAscender(true);
			columnName.setUseDescender(true);
			columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
			columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
			//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
			table.addCell(columnName);
		}
		//����
		//ȡ�±�������
		List<HashMap<String, String>> listData = this.getJsxxList(xh, dataSrc);
		for (int i = 0; i < listData.size(); i++) {
			for (int j = 0; j < zdArr.length; j++) {
				Cell columnName = new Cell(new Paragraph(listData.get(i).get(zdArr[j]),bold_fontChinese));
				columnName.setUseAscender(true);
				columnName.setUseDescender(true);
				columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
				columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
				//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
				table.addCell(columnName);
			}
		}
		return table;
	}
	public List<HashMap<String, String>> getJsxxList(String zgh,String dataSrc) throws Exception {
		FdyxxService service = new FdyxxService();
		List<HashMap<String, String>> data ;
		if("jtcyList".equals(dataSrc)){
			return service.getJtcyxx(zgh);
		}
		if("gwydList".equals(dataSrc)){
			return service.getGwydxx(zgh);
		}
		if("jtjlList".equals(dataSrc)){
			return service.getJtjlxx(zgh);
		}
		if("zyjszwList".equals(dataSrc)){
			return service.getZyjsgwxx(zgh);
		}
		if("xxjlList".equals(dataSrc)){
			return service.getXxjlxx(zgh);
		}
		if("gzjlList".equals(dataSrc)){
			return service.getGzjlxx(zgh);
		}
		return new ArrayList<HashMap<String, String>>();
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�˷���������ѧ����Ϣ�鿴���ܣ�XsxxglAction-xsxxglCk��
	 * ��Ϊ�������淶��ȡֵ�������ڲ�ͬ�����У��޷���ȡ���䷽ʽȡֵ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����11:46:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxCklist(String xh,String dataSrc) throws Exception{
		XsxxglService service = new XsxxglService();
		if (("stuCjList").equals(dataSrc)) {
			List<HashMap<String, String>> stuCjList = service
					.getStuCjList(xh);// �γ̿��Գɼ�
			return stuCjList;
		}
		if (("stuDjcjList").equals(dataSrc)) {
			List<HashMap<String, String>> stuDjcjList = service
					.getStuDjcjList(xh);// �ȼ����Գɼ�
			return stuDjcjList;
		}
		if (("stuQgzxXsgwxxList").equals(dataSrc)) {
			List<HashMap<String, String>> stuQgzxXsgwxxList = service
					.getStuQgzxXsgwxxList(xh);// ��λ¼�����
			return stuQgzxXsgwxxList;
		}
		if (("stuQgzxCjffList").equals(dataSrc)) {
			List<HashMap<String, String>> stuQgzxCjffList = service
					.getStuQgzxCjffList(xh);// ��𷢷����
			return stuQgzxCjffList;
		}
		if (("stuGyxxList").equals(dataSrc)) {
			List<HashMap<String, String>> stuGyxxList = service
					.getStuGyxxList(xh);// ��Ԣ��Ϣ
			return stuGyxxList;
		}
		if (("qswpList").equals(dataSrc)) {
			List<HashMap<String, String>> qswpList = service
					.getQswpList(xh);// ��ȡ������Ʒ�б�
			return qswpList;
		}

		// ���ҵ�ʦ��Ϣ�б�1036
		if (("qsdsList").equals(dataSrc)) {
			QsdswhService qsdsService = new QsdswhService();
	        return qsdsService.getQsdsxxListByXh(xh);
		}
		// ��Ԣ����Ա��Ϣ���㽭����ְҵ����ѧԺ��
		if (("stuGyglyxxList").equals(dataSrc)) {
			 return service.getGyglyxx(xh);
		}
		// ��Ԣ����Ա��Ϣ���㽭����ְҵ����ѧԺ��
		if (("stuGyfdyxxList").equals(dataSrc)) { 
			return service.getGyfdyxx(xh);
		}

		if (("knsInfoList").equals(dataSrc)) {
			List<HashMap<String, String>> knsInfoList = new KnsjgService()
					.getKnsInfoList(xh);// ��ѧ�Ų�ѯȫ����������Ϣ
			return knsInfoList;
		}
		if (("zzxmjgInfoList").equals(dataSrc)) {
			List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService()
					.getZzxmjgInfoList(xh);// ͨ��ѧ�Ų�ѯ������Ŀ���
			return zzxmjgInfoList;
		}
		if (("hjqkList").equals(dataSrc)) {
			List<HashMap<String, String>> hjqkList = new PjjgService()
					.getHjqkList(xh);// ����ѧ�Ų�ѯ�����
			return hjqkList;
		}
		if (("zcfsList").equals(dataSrc)) {
			List<HashMap<String, String>> zcfsList = new ZcfsService()
					.getZcfsList(xh);// ͨ��ѧ�Ų�ѯ�ղ����
			return zcfsList;
		}
		if (("zcfsListold").equals(dataSrc)) {
			List<HashMap<String, String>> zcfsListold = new ZcfsService()
					.getZcfsListOld(xh);// ͨ��ѧ�Ų�ѯ���ϰ汾�Ĳ����
		   return zcfsListold;
		}
		if (("cwsjList").equals(dataSrc)) {
			List<HashMap<String, String>> cwsjList = new CwsjService()
					.getCwsjList(xh);// ����ѧ�Ų�ѯѧ����������
			return cwsjList;
		}
		if (("wjcfList").equals(dataSrc)) {
			List<HashMap<String, String>> wjcfList = service
					.getWjcfList(xh);// ����ѧ�Ų�ѯΥ�ʹ����б�
			return wjcfList;
		}
		if (("xjydList").equals(dataSrc)) {
			// List<HashMap<String, String>> xjydList = service
			// .getXjydList(xh);// ����ѧ�Ų�ѯѧ���춯�б�
			List<HashMap<String, String>> xjydList = new XjydjgService()
					.getXsydList(xh);// ����ѧ�Ų�ѯѧ���춯�б�
            return xjydList;
		}
		if (("jtcyxxList").equals(dataSrc)) {
			// ��ͥ��Ա��Ϣ
			List<HashMap<String, String>> jtcyxxList = service
					.getJtcyxxXsList(xh);
			return jtcyxxList;
		}
		// ѧ����ᾭ����Ϣ
		// 1036 �޸� 2014-01-23
		if (("xlshjlxxList").equals(dataSrc)) {
			List<HashMap<String, String>> xlshjlxxList = service
					.getXlshjlList(xh);
			return xlshjlxxList;
		}

		// ��ѵ��Ϣ
		if (("pxxxList").equals(dataSrc)) {
			List<HashMap<String, String>> pxxxList = service
					.getPxxxList(xh);
			return pxxxList;
		}

		// �����
		if (("rxqhjqkList").equals(dataSrc)) {
			List<HashMap<String, String>> rxqhjqkList = service
					.getHjqkList(xh);
			return rxqhjqkList;
		}
		
		// ���£������
		if (("hjqkxxNewList").equals(dataSrc)) {
			List<HashMap<String, String>> hjqkxxNewList = service
			.getHjqkNewList(xh);
			return hjqkxxNewList;
		}

		/* ˼����ʦ�б�*/
		// ����Ա��ʦ��Ϣ�б�
		if (("fdyList").equals(dataSrc)) {
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService
					.getFdyBzrListByBjdm(dao.getBjdm(xh));
			 return xsxxtyglService.getSzxxList("fdy",szxxList);
		}
		// ��������ʦ��Ϣ�б�
		if (("bzrList").equals(dataSrc)) {
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService
					.getFdyBzrListByBjdm(dao.getBjdm(xh));
			return xsxxtyglService.getSzxxList("bzr",szxxList);
		}

		// ��Ԣ���ɴ�����Ϣ�б�
		if (("gyjlclxxList").equals(dataSrc)) {
		    return service.getGyjlclxxList(xh);
		}
		// ��Ԣ�춯��Ϣ�б�
		if (("gyygxxList").equals(dataSrc)) {
			return service.getGyydxxList(xh);
		}

		// ��Ԣ������Ϣ�б�
		if (("gypyxxList").equals(dataSrc)) {
			return service.getGypyxxList(xh);
		}

		// ѧ���ɲ���Ϣ

		if (("xsgbxxList").equals(dataSrc)) {
			return service.getXsgbxxList(xh);
		}

		// �����Ϣ

		if (("qjxxList").equals(dataSrc)) {
			return  service.getQjjgxxList(xh);
		}

		// ������У��Ϣ

		if (("jqlxxxList").equals(dataSrc)) {
		    return service.getJqlxxxList(xh);
		}

		// ֤��������Ϣ

		if (("zjbbxxList").equals(dataSrc)) {
			 return service.getZjbbxxList(xh);
		}
		

		// ��Ԣ������

		if (("gywsflist").equals(dataSrc)) {
			 return service.getGywsfList(xh);
		}
		
		// ��Ԣ�����֣����м�¼��
		if (("gywsfAllList").equals(dataSrc)) {
			 return service.getGywsfAllList(xh);
		}

		// ���Żݿ�

		if (("hcyhkxxList").equals(dataSrc)) {
			return service.getHcyhkxxList(xh);
		}

		// ����ע��

		if (("xqzcbdxxList").equals(dataSrc)) {
			return service.getXqbdzcxxList(xh);
		}

		// ��ԢΥ��

		if (("gywjxxList").equals(dataSrc)) {
		    return service.getGywjxxList(xh);
		}

		// ���÷���

		if (("fyffxxList").equals(dataSrc)) {
			return service.getFyffxxList(xh);
		}
		
		//��Ԣ������ 
		
		if(("gywsflist").equals(dataSrc)){
			return service.getGywsfList(xh);
		}
		

		// ��ȡ���һ�ܹ�Ԣ���Ų������ύ����ճ̵����һ�ܣ��㽭�����Ի���

		if (("lgGypyxxList").equals(dataSrc)) {
			return service.getLgGypyxxList(xh);
		}
		
		//������Ϣ
		if(("kqxxList").equals(dataSrc)){
			return service.getKqxxList(xh);
		}
		
		//������ϰ������Ϣ
		if(("zwzxkqxxList").equals(dataSrc)){
			return service.getZwzxKqxxList(xh);
		}
		
		//���ĳ�����Ʒ������Ϣ
		if(("wpsqjgList").equals(dataSrc)){
			return service.getWpsqjgList(xh);
		}
		//��ɫͨ��������Ϣ
		if(("lstdList").equals(dataSrc)){
		    return service.getLstdList(xh);
		}
		
		
		if(("grjdxxList").equals(dataSrc)){
			//������Ϣ
			DtxxjgService dtxxjgService = new DtxxjgService();
			return dtxxjgService.getGrJdxx(xh);
		}
		
		//��ѧ����(��Դ�ش���)
		if (("syddkList").equals(dataSrc)){
			SyddkService sydService = new SyddkService();
			return sydService.getSydkList(xh);
		}
		
		//��ѧ����(������ѧ����)
		if (("gjdkList").equals(dataSrc)){
			DkjgService dkjgService = new DkjgService();
			return dkjgService.getGjdkList(xh);
		}
		
		//����ʦ�������ҵ�������
		if (("JcjyBcjglist").equals(dataSrc)){
			BcjgService bcjg = new BcjgService();
			return bcjg.getJcjyBcjglist(xh);
		}
		
		//����ʦ��������۴������
		if (("Rwdcjglist").equals(dataSrc)){
			RwfbydcjgService rwdc = new RwfbydcjgService();
		    return rwdc.getRwdcjglist(xh);
		}
		
		//����ʦ��־Ը�߽��
		if (("Sthdlist").equals(dataSrc)){
			SthdjgService stjg = new SthdjgService();
			return stjg.getSthdlist(xh);
		}
		//���д���
		if (("Kycxlist").equals(dataSrc)){
			KyxmglService kycx = new KyxmglService();
			return kycx.getKycxList(xh);
		}
		//�����OLD
		if (("hjqkListOld").equals(dataSrc)) {
			List<HashMap<String, String>> hjqkListOld = new PjjgService()
					.getHjqkListOld(xh);// ����ѧ�Ų�ѯ�����
			return hjqkListOld;
		}
		
		//��ѧ��ƽ�����㣨���
		if("10335".equals(Base.xxdm)) {
			if(("xfjdList").equals(dataSrc)) {		
		     return  service.getXfjdList(xh,service.getXsnj(xh));
			}
		}
		
		if(Base.xxdm.equalsIgnoreCase("12871")) {
			//�����ȵ�
			if(("dyddList").equals(dataSrc)) {
				List<HashMap<String, String>> dyddList = new PjjgService()
				.getDyddList(xh);// ����ѧ�Ų�ѯ�����
				return dyddList;		
			}
		}
		// ���ֹ�ҵְҵ����ѧԺ
		if(Base.xxdm.equalsIgnoreCase("12903")) {
			// ����ʵϰ��Ϣ
			if (("ggsxjlList").equals(dataSrc)) {
				List<HashMap<String, String>> ggsxjlList = service
				.getGgsxjlList(xh);
				 return ggsxjlList;
			}
			// ����ʵϰ��Ϣ
			if (("dgsxjlList").equals(dataSrc)) {
				List<HashMap<String, String>> dgsxjlList = service
				.getDgsxjlList(xh);
				return dgsxjlList;
			}
		}
		
		//ɽ���ƾ�
		if("10125".equalsIgnoreCase(Base.xxdm)) {
			//��ѧ�о�
			if(("kxyjList").equals(dataSrc)) {
				return service.getKxyjList(xh);
			}
			//�����о�
			if(("ktyjList").equals(dataSrc)) {
				return service.getKtyjList(xh);
			}
			//���´�ҵ��Ŀ
			if(("cxcyxmList").equals(dataSrc)) {
				return service.getCxcyxmList(xh);
			}
			//ѧ�ƾ���
			if(("xkjsList").equals(dataSrc)) {
				return service.getXkjsList(xh);
			}
			//����֤��
			if(("jnzsList").equals(dataSrc)) {
				return service.getJnzsList(xh);
			}
		}
		
		//����ְҵ
		if("13265".equalsIgnoreCase(Base.xxdm)) {
			//ѧ���ɷ�
			if(("xsjfList").equals(dataSrc)) {
				return service.getJnzsList(xh);
			}
		}
		
		// ����ʦ����ѧ
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			// ���˼���
			if (("grjlList").equals(dataSrc)) {
				List<HashMap<String, String>> grjlList = service
				.getXsGrjlList(xh);
				return grjlList;
			}
			// ��������
			if (("gzjlList").equals(dataSrc)) {
				List<HashMap<String, String>> gzjlList = service
				.getXsGzjlList(xh);
				return gzjlList;
			}
			
		}
		
		// ����ѧԺ
		if("11354".equalsIgnoreCase(Base.xxdm)) {
			// ѧ��������Ϣ
			if(("xsyhxxList").equals(dataSrc)) {
				List<HashMap<String, String>> xsyhxxList = service
				.getXsyhxxList(xh);
				return xsyhxxList;
			}
		}
		
		// ������ҽҩ��ѧ
		if("10026".equalsIgnoreCase(Base.xxdm)) {
			// ���˽�����Ϣ
			if(("grhjxxList").equals(dataSrc)) {
				List<HashMap<String, String>> grhjxxList = service
				.getGrhjxxList(xh);
				return grhjxxList;
			}				
		}
		
		if("10335".equalsIgnoreCase(Base.xxdm)) {
			// ���ѧ���춯
			if(("zdxjydList").equals(dataSrc)) {
				List<HashMap<String, String>> zdxjydList = service
				.getZdxjydList(xh);
				return zdxjydList;
			}		
		}
		return new ArrayList<HashMap<String,String>>();
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ϵ��ʽ��������Ϣ��ģ�黭table
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����04:01:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Table �������� 
	 * @throws
	 */
	public Table writeZddyzdTable(String name,String xh,String flszid,HashMap<String, String> zdyMap) throws Exception{
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(4);//����һ��4�еı��
        table.setAlignment(Element.ALIGN_CENTER);//����   
        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
        table.setAutoFillEmptyCells(true);//�Զ�����
        table.setBorder(0);//���ñ߿�
        table.setPadding(1f);//���ü��
        table.setWidth(101f);//���ñ����

        //����/��ע��
        Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
      //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
        tableName.setColspan(4);//�ϲ���Ԫ��
        table.addCell(tableName);
        //ȡ�Զ�����ֶ���Ϣ
        List<HashMap<String, String>> zdyxx = dao.getZdyxxList(flszid);
        int index = 0;
        for (int i = 0; i < zdyxx.size(); i++) {
			String szls = zdyxx.get(i).get("szls");
			if(!"2".equals(szls)){
				index = index + 1;
				  Cell columnName = new Cell(new Paragraph(zdyxx.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(zdyMap.get(zdyxx.get(i).get("zd")) ,bold_fontChinese));
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              table.addCell(columnZdContent);
			}else{
				if(index%2 != 0){
					index = index + 3 ;
					Cell columnName = new Cell(new Paragraph("",bold_fontChinese));
					columnName.setUseAscender(true);
					columnName.setUseDescender(true);
					columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
					columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
					//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
					table.addCell(columnName);
					Cell columnZdContent = new Cell(new Paragraph("",bold_fontChinese));
					columnZdContent.setUseAscender(true);
					columnZdContent.setUseDescender(true);
					columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
					columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
					//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
					table.addCell(columnZdContent);
				}else{
					index = index + 2 ;
				}
				  /*if(i-1 >= 0 && (Integer.parseInt(zdyxx.get(i-1).get("rn")))%2 != 0 && !"2".equals(zdyxx.get(i-1).get("szls")) ){
					  Cell columnName = new Cell(new Paragraph("",bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnName);
		              Cell columnZdContent = new Cell(new Paragraph("",bold_fontChinese));
		              columnZdContent.setUseAscender(true);
		              columnZdContent.setUseDescender(true);
		              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnZdContent);
				  }*/
				  Cell columnName = new Cell(new Paragraph(zdyxx.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(zdyMap.get(zdyxx.get(i).get("zd")),bold_fontChinese));
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              columnZdContent.setColspan(3);
	              table.addCell(columnZdContent);
			}
		}
        return table;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �ճ�����-�ճ���Ϊ��¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����05:52:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param name
	 * @return
	 * Table �������� 
	 * @throws
	 */
	public Table writeTableRcxwjl(String xh,String name) throws Exception{
		//�ճ���Ϊ��������
		HashMap<String,String> cspzMap = new RcxwjgService().getCspz();
		//1���ճ���ΪNEW 2���ճ���Ϊ
		List<HashMap<String, String>> rcswList = new ArrayList<HashMap<String, String>>();
		Table table = null;
		if("1".equals(cspzMap.get("mklb"))){
			rcswList=new com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgService().getrcxwFzxxList(xh);//����ѧ�Ų�ѯ�ճ�����
			Font bold_fontChinese = this.getFontChinese();
		
			if("0".equals(cspzMap.get("zq"))){
				table = new Table(5);
			}else{
				table = new Table(4);
			}
	        table.setAlignment(Element.ALIGN_CENTER);//����   
	        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
	        table.setAutoFillEmptyCells(true);//�Զ�����
	        table.setBorder(0);//���ñ߿�
	        table.setPadding(1f);//���ü��
	        table.setWidth(101f);//���ñ����
	        if("0".equals(cspzMap.get("zq"))){
	        	 //����/��ע��
	            Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	            tableName.setUseAscender(true);
	            tableName.setUseDescender(true);
	            tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
	            tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	            tableName.setColspan(5);//�ϲ���Ԫ��
	            table.addCell(tableName);
	            String[] zdmcArr = new String[]{"��Ϊ��¼ѧ��","��Ϊ��¼ѧ��","��Ϊ��¼���","��Ϊ��¼�ܷ�","ѧ��"};
	            for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnName);
				}
	            String[] zdArr = new String[]{"rdnd","xqmc","rcxwlbmc","fz","xf"};
	            for (int i = 0; i < rcswList.size(); i++) {
	            	for (int j = 0; j < zdArr.length; j++) {
	            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
			              columnName.setUseAscender(true);
			              columnName.setUseDescender(true);
			              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
			              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
			              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
			              table.addCell(columnName);
					}
	            	
				}
	              
			}else{
				 Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	             tableName.setUseAscender(true);
	             tableName.setUseDescender(true);
	             tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
	             tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	             tableName.setColspan(4);//�ϲ���Ԫ��
	              table.addCell(tableName);
	              String[] zdmcArr = new String[]{"��Ϊ��¼ѧ��","��Ϊ��¼���","��Ϊ��¼�ܷ�","ѧ��"};
	              for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnName);
				}
	              String[] zdArr = new String[]{"rdnd","rcxwlbmc","fz","xf"};
		            for (int i = 0; i < rcswList.size(); i++) {
		            	for (int j = 0; j < zdArr.length; j++) {
		            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
				              columnName.setUseAscender(true);
				              columnName.setUseDescender(true);
				              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
				              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
				              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
				              table.addCell(columnName);
						}
		            	
					}
			}
			
		}
		else{
			rcswList = new RcxwjgService().getRcswList(xh);// ����ѧ�Ų�ѯ�ճ�����
			Font bold_fontChinese = this.getFontChinese();
			
			if("0".equals(cspzMap.get("zq"))){
				table = new Table(6);
			}else{
				table = new Table(5);
			}
	        table.setAlignment(Element.ALIGN_CENTER);//����   
	        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
	        table.setAutoFillEmptyCells(true);//�Զ�����
	        table.setBorder(0);//���ñ߿�
	        table.setPadding(1f);//���ü��
	        table.setWidth(101f);//���ñ����
	        if("0".equals(cspzMap.get("zq"))){
	        	 //����/��ע��
	            Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	            tableName.setUseAscender(true);
	            tableName.setUseDescender(true);
	            tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
	            tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	            tableName.setColspan(6);//�ϲ���Ԫ��
	            table.addCell(tableName);
	            String[] zdmcArr = new String[]{"��Ϊ��¼ѧ��","��Ϊ��¼ѧ��","��Ϊ��¼����","��Ϊ��¼���","��Ϊ��¼ʱ��","��Ϊ��¼��ֵ"};
	            for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnName);
				}
	            String[] zdArr = new String[]{"xn","xqmc","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","fz"};
	            for (int i = 0; i < rcswList.size(); i++) {
	            	for (int j = 0; j < zdArr.length; j++) {
	            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
			              columnName.setUseAscender(true);
			              columnName.setUseDescender(true);
			              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
			              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
			              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
			              table.addCell(columnName);
					}
	            	
				}
	              
			}else{
				 Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	             tableName.setUseAscender(true);
	             tableName.setUseDescender(true);
	             tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
	             tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	             tableName.setColspan(5);//�ϲ���Ԫ��
	              table.addCell(tableName);
	              String[] zdmcArr = new String[]{"��Ϊ��¼ѧ��","��Ϊ��¼����","��Ϊ��¼���","��Ϊ��¼ʱ��","��Ϊ��¼��ֵ"};;
	              for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnName);
				}
	              String[] zdArr = new String[]{"xn","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","fz"};
		            for (int i = 0; i < rcswList.size(); i++) {
		            	for (int j = 0; j < zdArr.length; j++) {
		            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
				              columnName.setUseAscender(true);
				              columnName.setUseDescender(true);
				              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
				              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
				              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
				              table.addCell(columnName);
						}
		            	
					}
			}
		}
		return table;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ѧ����Ϣ-������Ϣ��table
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-30 ����11:10:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param name
	 * @param xh
	 * @param lsmap
	 * @return
	 * Table �������� 
	 * @throws
	 */
	public Table writeJbxx(String name,String xh,HashMap<String, String> lsmap,HashMap<String, String> xsxx,String defaultimagepath) throws Exception{
		List<HashMap<String, String>> zdyzdList = dao.getZdyxxList(lsmap.get("flszid"));
		ZdybdBaseDao zdydao = new ZdybdBaseDao();
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(5);//����һ��5�еı��
        table.setAlignment(Element.ALIGN_CENTER);//����   
        table.setAlignment(Element.ALIGN_MIDDLE);//��ֱ����   
        table.setAutoFillEmptyCells(true);//�Զ�����
        table.setBorder(0);//���ñ߿�
        table.setPadding(1f);//���ü��
        table.setWidth(100f);//���ñ����
        //�����п�
        table.setWidths(new int[]{15,35,15,35,20});
        List<HashMap<String, String>> shList = zdydao.getShList();
		List<HashMap<String, String>> qxList = zdydao.getQxList();

        //����/��ע��
        Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//ˮƽ����
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
      //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
        tableName.setColspan(5);//�ϲ���Ԫ��
        table.addCell(tableName);
        //����Զ����������Ϣ�����ֶ�Ϊ�ջ����С��10��ֱ�ӷ��ؿ�
        if(null == zdyzdList || zdyzdList.size() < 10){
        	return null;
        }
        for (int i = 0; i < 10; i++) {
        	Cell columnName = new Cell(new Paragraph(zdyzdList.get(i).get("bdmc"),bold_fontChinese));
            columnName.setUseAscender(true);
            columnName.setUseDescender(true);
            columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
            columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
            //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
            table.addCell(columnName);
            Cell columnZdContent = new Cell(new Paragraph(xsxx.get(zdyzdList.get(i).get("zd")) ,bold_fontChinese));
            columnZdContent.setUseAscender(true);
            columnZdContent.setUseDescender(true);
            columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
            columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
            //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
            table.addCell(columnZdContent);
            Cell cell = null;
			if( (i+1)  == 2){
				 
				 cell = new Cell(new Paragraph("",bold_fontChinese));
				cell.setUseAscender(true);
				cell.setUseDescender(true);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	            //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
				cell.setRowspan(5);
			    byte[] byteArr = this.showPhoto(xh, defaultimagepath); 
			    //���ֽ�����ȡͼƬAPI�ɲ������
			    Image image = Image.getInstance(byteArr);
//			    image.setWidthPercentage(cell.width());
			    image.scaleAbsoluteWidth(85f);
			    image.scaleAbsoluteHeight(85f);
			    //�����������ָ��λ��
			    cell.add(image);
				table.addCell(cell);
				
			}
		}
        for (int i = 10; i < zdyzdList.size(); i++) {
        	String szls = zdyzdList.get(i).get("szls");
			if(!"2".equals(szls)){
				  Cell columnName = new Cell(new Paragraph(zdyzdList.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(xsxx.get(zdyzdList.get(i).get("zd")) ,bold_fontChinese));
	              if("22".equals(zdyzdList.get(i).get("zdlx"))){
	            	  String zdvlaue = xsxx.get(zdyzdList.get(i).get("zd"));
	            	  String ssxmc = "";
                      if(StringUtils.isNull(zdvlaue)){
                    	  ssxmc = "";
                    	  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }else{
	            		  ssxmc = this.getSsxMc(shList, qxList,zdvlaue);
	            		  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }
	            	   
	              }
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              if((i+1)%2 == 0){
	            	  columnZdContent.setColspan(2);
	              }
	              table.addCell(columnZdContent);
			}else{
				  if(i-1 >= 0 && Integer.parseInt(zdyzdList.get(i-1).get("rn"))%2 != 0  &&  !"2".equals(zdyzdList.get(i-1).get("szls"))){
					  Cell columnName = new Cell(new Paragraph("",bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              table.addCell(columnName);
		              Cell columnZdContent = new Cell(new Paragraph("",bold_fontChinese));
		              columnZdContent.setUseAscender(true);
		              columnZdContent.setUseDescender(true);
		              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
		              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
		              columnZdContent.setColspan(2);
		              table.addCell(columnZdContent);
				  }
				  Cell columnName = new Cell(new Paragraph(zdyzdList.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(xsxx.get(zdyzdList.get(i).get("zd")),bold_fontChinese));
	              if("22".equals(zdyzdList.get(i).get("zdlx"))){
	            	  String zdvlaue = xsxx.get(zdyzdList.get(i).get("zd"));
	            	  String ssxmc = "";
                      if(StringUtils.isNull(zdvlaue)){
                    	  ssxmc = "";
                    	  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }else{
	            		  ssxmc = this.getSsxMc(shList, qxList,zdvlaue);
	            		  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }
	            	   
	              }
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//ˮƽ����
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//��ֱ����
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//������ɫ
	              columnZdContent.setColspan(4);
	              table.addCell(columnZdContent);
			}
		}
		return table;
	}
	
 
    /**
     * 	
     * @����:������Ƭ��ʾ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-8-31 ����11:25:53
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @param defaultpath
     * @return
     * @throws Exception
     * byte[] �������� 
     * @throws
     */
	public byte[] showPhoto(String xh,String defaultpath)
			throws Exception {

		XsxxService service = new XsxxService();

		if (null == xh || "".equals(xh)) {
			InputStream is = new FileInputStream(defaultpath);
			byte[] photoByteArr = FileUtil.inputTobyte(is);
			return photoByteArr;
		} else {
			if("10335".equals(Base.xxdm)){
				String zjdxZpurl = "http://api.zju.edu.cn:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + xh;
				
				URL localURL = new URL(zjdxZpurl);
		        URLConnection connection = localURL.openConnection();
		        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
		        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		        InputStream inputStream = null;
		        InputStreamReader inputStreamReader = null;
		        BufferedReader reader = null;
		        StringBuffer resultBuffer = new StringBuffer();
		        String tempLine = null;
		        try {
					if (httpURLConnection.getResponseCode() >= 300) {
					    throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
					}
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
					InputStream is = new FileInputStream(defaultpath);
					byte[] photoByteArr = FileUtil.inputTobyte(is);
					return photoByteArr;
				}
		        try {
		            inputStream = httpURLConnection.getInputStream();
		            inputStreamReader = new InputStreamReader(inputStream);
		            reader = new BufferedReader(inputStreamReader);
		            while ((tempLine = reader.readLine()) != null) {
		                resultBuffer.append(tempLine);
		            }
		        } finally {
		            if (reader != null) {
		                reader.close();
		            }
		            if (inputStreamReader != null) {
		                inputStreamReader.close();
		            }
		            if (inputStream != null) {
		                inputStream.close();
		            }
		        }	
				
		        String[] zps = org.apache.commons.lang.StringUtils.substringsBetween(resultBuffer.toString(), "<ZP>", "</ZP>");
		        
		        byte[] bs = new BASE64Decoder().decodeBuffer(zps[0]);
		        return bs;
			}else{
				InputStream is = service.getPhotoStream(xh);

				if (is == null) {
						is = new FileInputStream(new File(defaultpath));
				}
				byte[] photoByteArr = FileUtil.inputTobyte(is);
				return photoByteArr;
				
			}
		}
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-28 ����03:25:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSsxMc(List<HashMap<String, String>> shlist,List<HashMap<String, String>> qxlist,String zdvalue){
		String result = "";
		if("0000".equals(zdvalue.substring(2, 6))){
			for (int i = 0; i < shlist.size(); i++) {
				if(shlist.get(i).get("dm").equals(zdvalue)){
					result = shlist.get(i).get("mc");
				    break;
				}
			}
		}else if("00".equals(zdvalue.substring(4, 6))){
			String shen = "";
			String shi = "";
			for (int i = 0; i < qxlist.size(); i++) {
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 2)+"0000")){
					shen = qxlist.get(i).get("mc");
				    
				}
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 4)+"00")){
					shi = qxlist.get(i).get("mc");
				    
				}
				if(StringUtils.isNotNull(shen)&&StringUtils.isNotNull(shi)){
					result = shen + shi;
					break;
				}
			}
		}else{
			String shen = "";
			String shi = "";
			String xian = "";
			for (int i = 0; i < qxlist.size(); i++) {
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 2)+"0000")){
					shen = qxlist.get(i).get("mc");
				    
				}
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 4)+"00")){
					shi = qxlist.get(i).get("mc");
				    
				}
				if(qxlist.get(i).get("dm").equals(zdvalue)){
					xian = qxlist.get(i).get("mc");
				    
				}
				if(StringUtils.isNotNull(shen)&&StringUtils.isNotNull(shi)&&StringUtils.isNotNull(xian)){
					result = shen + shi + xian;
					break;
				}
			}
		}
		return result;
	}

}

package xgxt.xszz.xcxy;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.pjpy.shcbys.jxj.JxjDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class XcxyXszzService {
	/*
	 * �����ʾ�ɼ���xn��nd
	 */
	public String getViewCjXnOrNd(){
		if(Base.currXn.indexOf(Base.currNd) > 4){
			return String.valueOf((Integer.parseInt(Base.currNd)-1));
		}else{
			return String.valueOf((Integer.parseInt(Base.currNd)-1))+"-"+Base.currNd;
		}
	}
	/*
	 * ͨ�����ݿ��е��ֶα�ע��ñ�ͷ������ʾ
	 */
	public String[] getColumnNameCN_ser(String[] colList,String tableName){
		DAO dao = DAO.getInstance();
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/*
	 * ��ñ�ͷ��list
	 */
	public List<HashMap<String,String>> arrayToList_ser(String[] colList,String[] colListCN){
		DAO dao = DAO.getInstance();
		return dao.arrayToList(colList, colListCN);
	}
	
	/*
	 * ���������Ŀlist
	 */
	public List<String[]> getZzxm_ser(String[] colList,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getZzxm_db(colList,form);
	}
	
	/*
	 * ���ָ��xmdm����Ŀ
	 */
	public HashMap<String, String> getOneZzxm_ser(String pk,String[] colList){
		XcxyXszzDao dao = new XcxyXszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] array = dao.getOneZzxm_db(pk, colList);
		if(array != null){
			for(int i = 0; i < array.length; i++){
				map.put(colList[i], array[i]);
			}
		}
		return map;
	}
	
	/*
	 * ���Ψһ��
	 */
	public String checkOnlyOne_ser(String pk,String pkValue,String tableName){	
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.checkOnlyOne_db(pk, pkValue, tableName);
	}
	
	/*
	 * ������Ŀ����
	 */
	public String add_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.add_db(form);
	}
	
	/*
	 * ������Ŀ�޸�
	 */
	public String modify_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.modify_db(form);
	}
	
	/*
	 * ������Ŀɾ��
	 */
	public String delete_ser(String pkStr){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.delete_db(pkStr);
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * �������������Ŀlist
	 */
	public List<HashMap<String,String>> getAllZzxm_ser(){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getAllZzxm_db();
	}
	/*
	 * ��ѯ������Ŀ���
	 */
	public List<String[]> queryZzxmJe_ser(String[] colList,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.queryZzxmJe_db(colList,form);
	}
	
	/*
	 * ������Ŀ�������
	 */
	public String addZzje_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.addZzje_db(form);
	}
	
	/*
	 * ������Ŀ����޸�
	 */
	public String modifyZzje_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.modifyZzje_db(form);
	}
	
	/*
	 * ������Ŀ���ɾ��
	 */
	public String deleteZzje_ser(String pkStr){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.deleteZzje_db(pkStr);
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * ���ָ��xmdm||zzje����Ŀ���
	 */
	public HashMap<String, String> getOneZzje_ser(String pk,String[] colList){
		XcxyXszzDao dao = new XcxyXszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] array = dao.getOneZzje_db(pk, colList);
		if(array != null){
			for(int i = 0; i < array.length; i++){
				map.put(colList[i], array[i]);
			}
		}
		return map;
	}
	
	/*
	 * ���ָ��������Ŀ�Ľ��
	 */
	public List<HashMap<String,String>> getZzxmJe_ser(String xmdm){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getZzxmJe_db(xmdm);
	}
	
	/*
	 * ��ѯ������Ŀʱ��
	 */
	public List<String[]> queryZzxmSj_ser(String[] colList,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.queryZzxmSj_db(colList,form);
	}
	
	/*
	 * ���ָ��xmdm����Ŀʱ��
	 */
	public HashMap<String, String> getOneZzsj_ser(String pk,String[] colList){
		XcxyXszzDao dao = new XcxyXszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] array = dao.getOneZzsj_db(pk, colList);
		if(array != null){
			for(int i = 0; i < array.length; i++){
				map.put(colList[i], array[i]);
			}
		}
		return map;
	}
	
	/*
	 * ��������ʱ���趨
	 */
	public String setZzsj_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.setZzsj_db(form);
	}
	
	/*
	 * ��ʼ������ʱ��
	 */
	public String clearZzsj_ser(){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.clearZzsj_db();
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * ������������ʱ��
	 */
	public String saveManyZzsj_ser(String str,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.saveManyZzsj_db(str,form);
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * У���Ƿ�����Ŀ����ʱ�䷶Χ��
	 */
	public String checkSfksq_ser(HashMap<String,String> map){
		XcxyXszzDao dao = new XcxyXszzDao();
		if(map != null && !Base.isNull(map.get("zzkssj"))){
			return dao.checkSfksq_db(map);
		}else{
			return "no";
		}
	}
	
	/*
	 * ��ù��ҽ�ѧ��ѧ���Ļ�����Ϣ
	 */
	public HashMap<String,String> getJxjXsxx_ser(String xxmc,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getJxjXsxx_db(xxmc,form);
	}
	
	/*
	 * �����ѧ��ѧ���Ļ�����Ϣ
	 */
	public HashMap<String,String> getZxjXsxx_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();	
		return dao.getZxjXsxx_db(form);
	}
	
	/*
	 * ��ù�����־��ѧ��ѧ���Ļ�����Ϣ
	 */
	public HashMap<String,String> getLzjxjXsxx_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();	
		return dao.getLzjxjXsxx_db(form);
	}
	
	/*
	 * ѧ���ɼ�
	 */
	public List<String[]> getCjList_ser(String xh,String xn,String xq) {
		JxjDAO jxjdao = new JxjDAO();
		XcxyXszzDao dao = new XcxyXszzDao();
		String jwflag = jxjdao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1ѧ��0����
		try{
			if ("1".equalsIgnoreCase(jwflag)) {
				return dao.getCjListByxf_db(xh,xn,xq);
			} else {
				return dao.getCjList_db(xh,xn,xq);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/*
	 * ���ѧ���ɼ��Ļ���
	 */
	public HashMap<String,String> getXscjHz_ser(String xh,String xn,String xq){
		XcxyXszzDao dao = new XcxyXszzDao();	
		return dao.getXscjHz_db(xh,xn,xq);
	}
	
	/*
	 * ����ѧ����������
	 */
	public String saveJzxjsq_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		try {
			return dao.saveJzxjsq_db(form);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		} 
	}
	
	/*
	 * ��˲�ѯ
	 */
	public List<String[]> queryShInfo_ser(String[] cols,XcxyXszzForm form,String tableName,String userType){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.queryShInfo_db(cols,form,tableName,userType);
	}
	
	/*
	 * ��������ѧ�������������
	 */
	public String excutePlsh_ser(XcxyXszzForm form,String userType){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.excutePlsh_db(form,userType);
	}
	
	/*
	 * ���ѧ��������������Ϣ
	 */
	public List<HashMap<String,String>> getXsAllPrise_ser(String xh,String xn){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXsAllPrise_db(xh,xn);
	}
	
	/*
	 * ���ѧ��Υ�ʹ������
	 */
	public List<HashMap<String,String>> getXsAllWjcf_ser(String xh,String xn,String xq){	
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXsAllWjcf_db(xh, xn,xq);
	}
	
	/*
	 * ���ѧ���������뵥�����չ�ֵ���Ϣ
	 */
	public HashMap<String,String> getXssqInfo_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXssqInfo_db(form);
	}
	
	/*
	 * ��������ѧ�������������
	 */
	public String excuteDgsh_ser(XcxyXszzForm form,String userType){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.excuteDgsh_db(form,userType);
	}
	
	/*
	 * ���ݵ���sql
	 */
	public String getExpSql_ser(XcxyXszzForm form,String tableName,String condsql){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getExpSql_db(form,tableName,condsql);
	}
	
	/*
	 * form�е�ֵ��װ��HashMap��
	 */
	public HashMap<String,String> formToMap_ser(ActionForm form){
		Class cla = form.getClass();
		Method[] methods = cla.getMethods();
		HashMap<String,String> map = new HashMap<String,String>();
		if(methods != null && methods.length > 0){
			try{
				for(int i= 0;i<methods.length;i++){
					String mName = methods[i].getName();
					if(mName != null && mName.indexOf("get") ==0){
						if(methods[i].getReturnType()==String.class){
							map.put(mName.substring(3).toLowerCase(), (String)methods[i].invoke(form,(Object[]) null));
						}			
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				return map;
			}			
		}
		return map;
	}
	
	/*
	 * ���ѧ���Ļ�����Ϣ
	 */
	public HashMap<String,String> getXsxx_ser(String xxmc,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXsxx_db(xxmc, form);
	}
	
	/*
	 * ���ѧ���ɼ���Ϣ
	 */
	public HashMap<String,String> getXscj_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXscj_db(form);
	}
	/*
	 * ���ѧ���Ļ�����Ϣ
	 */
	public List<HashMap<String,String>> getPrintList_ser(XcxyXszzForm form,String userType){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		String bbys = form.getBbys();
		String xmmc = form.getXmmc();
		if ("���ҽ�ѧ��".equals(bbys) || "������־��ѧ��".equals(bbys)) {
			map.put("dm", "jxjffb");
			map.put("mc", xmmc + "���ű�");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "jxjcsb");
			map.put("mc", xmmc + "�����");
			list.add(map);
		} else if ("������ѧ��".equals(bbys)) {		
			if(!"xy".equals(userType)){
				map.put("dm", "zxjtjzb");
				map.put("mc", xmmc + "ͳ���ܱ�");
				list.add(map);			
			}
			map = new HashMap<String, String>();
			map.put("dm", "zxjffb");
			map.put("mc", xmmc + "���ű�");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "zxjsbb");
			map.put("mc", xmmc + "�ϱ���");
			list.add(map);
		}else if ("������ѧ��".equals(bbys)) {		
			map.put("dm", "gzzxjffb");
			map.put("mc", xmmc + "���ű�");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "gzzxjmdb");
			map.put("mc", xmmc + "������");
			list.add(map);
		}else if ("���ô󸣻ۻ������־��ѧ��".equals(bbys)) {		
			map.put("dm", "jndlzjxjffb");
			map.put("mc", xmmc + "���ű�");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "jndlzjxjmdb");
			map.put("mc", xmmc + "������");
			list.add(map);
		}
		return list;
	}
	
	/*
	 * ִ�б����ӡ
	 */
	public void excutePrint_ser(XcxyXszzForm form,String userType,OutputStream os){
		String bbys = form.getBbys();
		String xmlc = form.getXmlc();
		String xmmc = form.getXmmc();
		String tableName="";
		if("���ҽ�ѧ��".equalsIgnoreCase(xmlc)){
			tableName = "view_xszz_jxjsqb";
		}else if("������ѧ��".equalsIgnoreCase(xmlc)){
			tableName = "view_xszz_zxjsqb";
		}else{
			tableName = "view_xszz_lzjxjsqb";
		}
		form.setTableName(tableName);
		try {
			if ("jxjffb".equals(bbys)) {
				if ("xy".equalsIgnoreCase(userType)) {
					printXxFfList(userType, tableName, form, os, xmmc + "����");
				} else {
					printXxFfList(tableName, form, os, xmmc + "����");
				}

			} else if ("jxjcsb".equals(bbys)) {

				if ("xy".equalsIgnoreCase(userType)) {
					printXxCsList(userType, tableName, "", form, os, xmmc
							+ "����");
				} else {
					printXxCsList(userType, tableName, xmmc, form, os, xmmc
							+ "����");
				}

				if ("xy".equalsIgnoreCase(userType)) {
					printXxCsList(userType, tableName, "", form, os, xmmc
							+ "����");
				} else {
					printXxCsList(userType, tableName, xmmc, form, os, xmmc
							+ "����");
				}				
			} else if ("zxjtjzb".equals(bbys)) {
				zxjtjzb_ser(form, userType, os);
			} else if ("zxjffb".equals(bbys)) {
				if ("xy".equalsIgnoreCase(userType)) {
					printXsZxjList_yx(form, userType, os);
				}else{
					printXscZxjList(userType, xmlc, form, os, xmmc+"���ű�");
				}
			} else if ("zxjsbb".equals(bbys)) {
				zxjsbb_ser(form, userType, os);
			} else if ("gzzxjmdb".equals(bbys)) {
				gzzxjmdb_ser(form, userType, os);
			} else if ("gzzxjffb".equals(bbys)) {
				gzzxjffb_ser(form, userType, os);
			} else if ("jndlzjxjffb".equals(bbys)) {
				jndlzjxjffb_ser(form, userType, os);
			} else if ("jndlzjxjmdb".equals(bbys)) {
				jndlzjxjmdb_ser(form, userType, os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ��ѧ��ͳ���ܱ��ӡ
	 */
	public void zxjtjzb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		String xmmc = form.getXmmc();
		XcxyXszzDao dao = new XcxyXszzDao();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "ͳ���ܱ�", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		ws.mergeCells(0, 0, 10, 1);
		mb.printToOneCell_multy(ws, form.getNd() + "����Ĵ�ʡ��У" + form.getXmmc()
				+ "����ѧ�����ܱ�", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 2, 5, 3);
		ws.mergeCells(6, 2, 10, 3);
		mb.printToOneCell_multy(ws, "���λ��", 0, 2, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "�ʱ�䣺" + GetTime.getNowTime(), 6, 2, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		ws.mergeCells(0, 4, 0, 5);
		mb.printToOneCell_multy(ws, "ѧУ����", 0, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(1, 4, 1, 5);
		mb.printToOneCell_multy(ws, "��У������", 1, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(2, 4, 5, 4);
		mb.printToOneCell_multy(ws, "����ѧ����", 2, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(2, 5, "�ϼ�",wcf));
		ws.addCell(new Label(3, 5, "һ��",wcf));
		ws.addCell(new Label(4, 5, "����",wcf));
		ws.addCell(new Label(5, 5, "����",wcf));
		ws.mergeCells(6, 4, 6, 5);
		mb.printToOneCell_multy(ws, "�������", 6, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(7, 4, 7, 5);
		mb.printToOneCell_multy(ws, "����ѧ���б�������", 7, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(8, 4, 8, 5);
		mb.printToOneCell_multy(ws, "����ѧ��������������", 8, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(9, 4, 9, 5);
		mb.printToOneCell_multy(ws, "����ѧ����ũ��ѧ����", 9, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(10, 4, 10, 5);
		mb.printToOneCell_multy(ws, "��ע", 10, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(0, 6, "����ѧԺ",wcf));
		String[] xsrs = dao.getXsrs_db(form.getNd(),form.getXmdm(),form.getTableName());
		ws.addCell(new Label(1, 6, xsrs[0],wcf));
		ws.addCell(new Label(7, 6, xsrs[1],wcf));
		ws.addCell(new Label(8, 6, xsrs[2],wcf));
		ws.addCell(new Label(9, 6, xsrs[3],wcf));
		List<String[]> rsje = dao.getZxjrsAndJe_db(form.getNd(),form.getXmdm(),form.getTableName());
		int zrs = 0;
		float zje = 0;
		for(int i=0;i<rsje.size();i++){
			String[] array = rsje.get(i);
			ws.addCell(new Label(3+i, 6, array[0],wcf));
			zrs += Integer.parseInt(array[0]);
			zje += Float.parseFloat(array[1]);
		}
		if(rsje.size() < 3){
			for(int j= rsje.size();j<3;j++){
				ws.addCell(new Label(3+j, 6, "0",wcf));
			}		
		}
		ws.addCell(new Label(2, 6, String.valueOf(zrs),wcf));
		ws.addCell(new Label(6, 6, String.valueOf(zje),wcf));
		ws.addCell(new Label(10, 6, "",wcf));
		wwb.write();
		wwb.close();
	}
	
	/*
	 * ��ѧ���ϱ����ӡ
	 */
	public void zxjsbb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		String xmmc = form.getXmmc();
		XcxyXszzDao dao = new XcxyXszzDao();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "�ϱ���", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		ws.mergeCells(0, 0, 13, 1);
		String content = "";
		ws.mergeCells(0, 2, 5, 3);
		ws.mergeCells(6, 2, 13, 3);
		if("xy".equals(userType)){
			mb.printToOneCell_multy(ws, "ϵ�����ƣ�"+form.getXymc(), 0, 2, 12, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
			content = form.getNd() + "�������ѧԺ"+xmmc+"����ѧ������";
		}else{
			mb.printToOneCell_multy(ws, "ѧУ���ƣ�����ѧԺ", 0, 2, 12, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
			content = form.getNd() + "����Ĵ�ʡ��У"+xmmc+"����ѧ������";
		}
		mb.printToOneCell_multy(ws, content, 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		mb.printToOneCell_multy(ws, "�ʱ�䣺" + GetTime.getNowTime(), 6, 2, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		ws.mergeCells(0, 4, 0, 5);
		mb.printToOneCell_multy(ws, "���", 0, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(1, 4, 1, 5);
		mb.printToOneCell_multy(ws, "����", 1, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(2, 4, 2, 5);
		mb.printToOneCell_multy(ws, "�Ա�", 2, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(3, 4, 3, 5);
		mb.printToOneCell_multy(ws, "���֤��", 3, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(4, 4, 4, 5);
		mb.printToOneCell_multy(ws, "Ժϵ", 4, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(5, 4, 5, 5);
		mb.printToOneCell_multy(ws, "רҵ", 5, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(6, 4, 6, 5);
		mb.printToOneCell_multy(ws, "ѧ��", 6, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(7, 4, 7, 5);
		mb.printToOneCell_multy(ws, "��������", 7, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(8, 4, 8, 5);
		mb.printToOneCell_multy(ws, "ũ�廧��",8, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(9, 4, 9, 5);
		mb.printToOneCell_multy(ws, "���Ʋ��", 9, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);	
		ws.mergeCells(10, 4, 12, 4);
		mb.printToOneCell_multy(ws, "��������", 10, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(10, 5, "һ��",wcf));
		ws.addCell(new Label(11, 5, "����",wcf));
		ws.addCell(new Label(12, 5, "����",wcf));
		ws.mergeCells(13, 4, 13, 5);
		mb.printToOneCell_multy(ws, "�������(��λ:Ԫ)", 13, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		
		List<HashMap<String,String>> list = dao.getZxjSb_db(form, userType);
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			map = list.get(i);
			ws.addCell(new Label(0, 6+i, map.get("rownum"),wcf));
			ws.addCell(new Label(1, 6+i, map.get("xm"),wcf));
			ws.addCell(new Label(2, 6+i, map.get("xb"),wcf));
			ws.addCell(new Label(3, 6+i, map.get("sfzh"),wcf));
			ws.addCell(new Label(4, 6+i, map.get("xymc"),wcf));
			ws.addCell(new Label(5, 6+i, map.get("zymc"),wcf));
			ws.addCell(new Label(6, 6+i, map.get("xh"),wcf));
			ws.addCell(new Label(7, 6+i, map.get("mz"),wcf));
			ws.addCell(new Label(8, 6+i, map.get("hk"),wcf));
			ws.addCell(new Label(9, 6+i, map.get("xl"),wcf));
			String dc1 = "";
			String dc2 = "";
			String dc3 = "";
			if("1".equals(map.get("dc"))){
				dc1 = "1";
			}else if("2".equals(map.get("dc"))){
				dc2 = "1";
			}else{
				dc3 = "1";
			}
			ws.addCell(new Label(10, 6+i, dc1,wcf));
			ws.addCell(new Label(11, 6+i, dc2,wcf));
			ws.addCell(new Label(12, 6+i, dc3,wcf));
			ws.addCell(new Label(13, 6+i, map.get("zzje"),wcf));
		}
		wwb.write();
		wwb.close();
	}
	
	/*
	 * ִ��Ժϵ���ҽ�ѧ��/��־��ѧ��ѧԺ�����б��ӡ
	 */
	public void printXxFfList(String userType, String tableName,
			XcxyXszzForm form, OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		String bmmc = form.getXymc();
		String userDep = form.getXydm();

		String content = nd + "�������ѧԺ" + bmmc + "ϵ���ű�";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// �������
		List<HashMap<String, String>> list = dao.getXxFfList(nd, tableName,
				userDep,form.getXmdm());

		WritableSheet ws = wwb.createSheet(title + "ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// ����ͷ);
		ws.mergeCells(0, 1, 8, 2);
		String topInfo = "ϵ������:                                ��д���ڣ�"
				+ GetTime.getNowTime();
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 650, Border.NONE);

		ws.addCell(new Label(0, 3, "���", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 3, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 3, "�Ա�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 3, "�꼶רҵ", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 3, "���֤����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 3, "�����˺�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 3, "��Ԫ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(7, 3, "ѧ��ǩ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(8, 3, "��ע", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 4 + i, list.get(i).get("xm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2, 4 + i, list.get(i).get("xb"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3, 4 + i, list.get(i).get("zymc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(4, 4 + i, list.get(i).get("sfzh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(5, 4 + i, list.get(i).get("yhkh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(6, 4 + i, list.get(i).get("zzje"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(7, 4 + i, "", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(8, 4 + i, "", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/*
	 * ִ��Ժϵ���ҽ�ѧ��/��־��ѧ��ѧУ�����б��ӡ
	 */
	public void printXxFfList(String tableName, XcxyXszzForm form,
			OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		String content = "����ѧԺ"+nd + "�����ͨ�ߵ�ѧУ" + form.getXmmc() + "���ű�";
		int zje = 0;

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// �������
		List<HashMap<String, String>> list = dao.getXxFfList(nd, tableName,form.getXmdm());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String je = list.get(i).get("zje");
				if (!Base.isNull(je)) {
					zje += Integer.parseInt(je);
				}
			}
		}
		
		WritableSheet ws = wwb.createSheet(title + "ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 4, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// ����ͷ);
		ws.mergeCells(0, 1, 4, 2);
		String topInfo = "����:ѧ����                                ��λ����,Ԫ";
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 650, Border.NONE);

		ws.addCell(new Label(0, 3, "ϵ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 3, "���", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 3, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 3, "���С��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 3, "�����ǩ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 4 + i, list.get(i).get("xymc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 4 + i, list.get(i).get("zzje"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2, 4 + i, list.get(i).get("num"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3, 4 + i, list.get(i).get("zje"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(4, 4 + i, "", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
			ws.addCell(new Label(0, 4 + list.size(), "�ϼ�", wcf2));
			ws.addCell(new Label(1, 4 + list.size(), String.valueOf(zje),
							wcf2));
			ws.mergeCells(1, 4 + list.size(), 4, 4 + list.size());
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/*
	 * ִ��Ժϵ���ҽ�ѧ��/��־��ѧ������б��ӡ
	 */
	public void printXxCsList(String userType, String tableName,String xmmc,
			XcxyXszzForm form, OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		String bmmc = form.getXymc();
		String userDep=form.getXydm();
		
		String content = "";
		String topInfo="";
		if ("xy".equalsIgnoreCase(userType)) {
			content = nd + "�������ѧԺ" + bmmc + "ϵ����������";
			topInfo = "ϵ������:                                     ��д���ڣ�"
					+ GetTime.getNowTime();
		}else{
			content = nd + "�����ͨ�ߵ�ѧУ" + xmmc + "ѧ������������";
			topInfo = "ѧУ����:                                     ��д���ڣ�"
				+ GetTime.getNowTime();
			userDep = "%";
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// �������
		List<HashMap<String, String>> list = dao.getXxCsList(nd, tableName,
				userDep,form.getXmdm());

		WritableSheet ws = wwb.createSheet(title + "ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// ����ͷ);
		ws.mergeCells(0, 1, 8, 2);
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		ws.addCell(new Label(0, 3, "���", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 3, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 3, "���֤��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 3, "Ժϵ", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 3, "רҵ", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 3, "ѧ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 3, "�Ա�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(7, 3, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(8, 3, "��ѧ����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 4 + i, list.get(i).get("xm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(2, 4 + i, list.get(i).get("sfzh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(3, 4 + i, list.get(i).get("xymc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(4, 4 + i, list.get(i).get("zymc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(5, 4 + i, list.get(i).get("xh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(6, 4 + i, list.get(i).get("xb"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(7, 4 + i, list.get(i).get("mzmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(8, 4 + i, list.get(i).get("rxrq"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/*
	 * ִ��ѧ������ѧ���б��ӡ
	 */
	public void printXscZxjList(String userType,String xmlc,
			XcxyXszzForm form, OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		
		String	content = "����ѧԺ"+nd + "���"+form.getXmmc()+"���ű�";
		String	topInfo = "��д����:ѧ����                                     ��λ���ˣ���Ԫ";

		
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// �������
		List<HashMap<String, String>> list = dao.getXscZxjList(nd,form.getXmdm(),form.getTableName());

		WritableSheet ws = wwb.createSheet(title + "ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 6, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// ����ͷ);
		ws.mergeCells(0, 1, 6, 2);
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 650, Border.NONE);

		ws.mergeCells(0, 3, 0, 4);
		ws.addCell(new Label(0, 3, "ϵ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(1, 3, 4, 3);
		ws.addCell(new Label(1, 3, "����ѧ����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 4, "�ϼ�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 4, "һ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 4, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 4, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(5, 3, 5, 4);
		ws.addCell(new Label(5, 3, "�������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.mergeCells(6, 3, 6, 4);
		ws.addCell(new Label(6, 3, "��ע", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ

		int zrs=0;
		int zone=0;
		int ztwo=0;
		int zthree=0;
		float zje=0;
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 5 + i, list.get(i).get("xymc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				ws.addCell(new Label(1, 5 + i, list.get(i).get("num"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				if(!Base.isNull(list.get(i).get("num"))){
					zrs+=Integer.parseInt(list.get(i).get("num"));
				}
				ws.addCell(new Label(2, 5 + i, list.get(i).get("one"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				if(!Base.isNull(list.get(i).get("one"))){
					zone+=Integer.parseInt(list.get(i).get("one"));
				}
				ws.addCell(new Label(3, 5 + i, list.get(i).get("two"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				if(!Base.isNull(list.get(i).get("two"))){
					ztwo+=Integer.parseInt(list.get(i).get("two"));
				}
				ws.addCell(new Label(4, 5 + i, list.get(i).get("three"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				if(!Base.isNull(list.get(i).get("three"))){
					zthree+=Integer.parseInt(list.get(i).get("three"));
				}
				ws.addCell(new Label(5, 5 + i, list.get(i).get("zje"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
				if(!Base.isNull(list.get(i).get("zje"))){
					zje+=Float.parseFloat((list.get(i).get("zje")));
				}
				ws.addCell(new Label(6, 5 + i, "", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ	
			}
			ws.addCell(new Label(0, 5 + list.size(),"�ϼ�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(1, 5 + list.size(), String.valueOf(zrs), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(2, 5 + list.size(), String.valueOf(zone), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(3, 5 + list.size(), String.valueOf(ztwo), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(4, 5 + list.size(), String.valueOf(zthree), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(5, 5 + list.size(), String.valueOf(zje), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(6, 5 + list.size(), "", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/*
	 * ������ѧ��������
	 */
	public void gzzxjmdb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "������", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 9, 3);
		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "����ƶ��ѧ������", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 3, 4);		
		mb.printToOneCell_multy(ws, "���ţ�", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.mergeCells(4, 4, 9, 4);
		mb.printToOneCell_multy(ws, "�Ʊ�ʱ�䣺" + GetTime.getNowTime(), 4, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.addCell(new Label(0, 5, "���",wcf));
		ws.addCell(new Label(1, 5, "����",wcf));
		ws.addCell(new Label(2, 5, "�Ա�",wcf));
		ws.addCell(new Label(3, 5, "����",wcf));
		ws.addCell(new Label(4, 5, "��ͥסַ",wcf));
		ws.mergeCells(5, 5, 8, 5);
		ws.addCell(new Label(5, 5, "���ھͶ�"+Base.YXPZXY_KEY+"��רҵ���༶",wcf));
		ws.addCell(new Label(9, 5, "��ע",wcf));
		List<HashMap<String,String>> list = dao.getGzzxjmdbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xb"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("mzmc"),3,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("jtdz"),4,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(5, i+6, 8, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("jdqk"),5,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",9,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	/*
	 * ������ѧ�𷢷ű�
	 */
	public void gzzxjffb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "���ű�", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 8, 3);
		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "���ű�", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 3, 4);		
		mb.printToOneCell_multy(ws, "���ţ�", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.mergeCells(4, 4, 8, 4);
		mb.printToOneCell_multy(ws, "�Ʊ�ʱ�䣺" + GetTime.getNowTime(), 4, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.addCell(new Label(0, 5, "���",wcf));
		ws.addCell(new Label(1, 5, "����",wcf));
		ws.addCell(new Label(2, 5, "ϵ��",wcf));
		ws.addCell(new Label(3, 5, "�꼶רҵ",wcf));
		ws.mergeCells(3, 5, 5, 5);
		ws.addCell(new Label(6, 5, "���",wcf));		
		ws.addCell(new Label(7, 5, "�����ǩ��",wcf));
		ws.addCell(new Label(8, 5, "��ע",wcf));
		List<HashMap<String,String>> list = dao.getJxjxyffbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xymc"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(3, i+6, 5, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("njzy"),3,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			
			mb.printToOneCell_multy(ws, list.get(i).get("zzje"),6,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			;
			mb.printToOneCell_multy(ws, " ",7,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",8,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	/*
	 * ���ô󸣻ۻ������־��ѧ�𷢷ű�
	 */
	public void jndlzjxjffb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "���ű�", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 8, 3);		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "��־��ѧ�𷢷ű�", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 2, 4);		
		mb.printToOneCell_multy(ws, "���ţ�", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.NONE);
		ws.mergeCells(3, 4, 5, 4);
		mb.printToOneCell_multy(ws, "�Ʊ�ʱ�䣺" + GetTime.getNowTime(), 3, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.NONE);
		ws.mergeCells(6, 4, 8, 4);
		mb.printToOneCell_multy(ws, "��λ��  ��,Ԫ" , 6, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.NONE);
		ws.addCell(new Label(0, 5, "���",wcf));
		ws.addCell(new Label(1, 5, "����",wcf));
		ws.addCell(new Label(2, 5, "ϵ��",wcf));
		ws.addCell(new Label(3, 5, "�꼶רҵ",wcf));
		ws.mergeCells(3, 5, 5, 5);
		ws.addCell(new Label(6, 5, "���",wcf));		
		ws.addCell(new Label(7, 5, "�����ǩ��",wcf));
		ws.addCell(new Label(8, 5, "��ע",wcf));
		List<HashMap<String,String>> list = dao.getJndlzjxjffbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xymc"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(3, i+6, 5, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("njzy"),3,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			
			mb.printToOneCell_multy(ws, list.get(i).get("zzje"),6,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			;
			mb.printToOneCell_multy(ws, " ",7,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",8,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	/*
	 * ���ô󸣻ۻ������־��ѧ��������
	 */
	public void jndlzjxjmdb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "������", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 8, 3);
		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "��־��ѧ������", 0, 0, 13, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 4, 4);		
		mb.printToOneCell_multy(ws, "���ţ�", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.mergeCells(5, 4, 8, 4);
		mb.printToOneCell_multy(ws, "�Ʊ�ʱ�䣺" + GetTime.getNowTime(), 5, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);		
		ws.addCell(new Label(0, 5, "���",wcf));
		ws.addCell(new Label(1, 5, "����",wcf));
		ws.mergeCells(2, 5, 3, 5);
		ws.addCell(new Label(2, 5, "ϵ��",wcf));
		ws.mergeCells(4, 5, 7, 5);
		ws.addCell(new Label(4, 5, "�꼶רҵ",wcf));
		ws.addCell(new Label(8, 5, "��ע",wcf));
		List<HashMap<String,String>> list = dao.getJndlzjxjmdbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(2, i+6, 3, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("xymc"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(4, i+6, 7, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("njzy"),4,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",8,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	
	/*
	 * ������ѧ�𷢷ű��ӡ(Ժϵͳ��)
	 */
	public void printXsZxjList_yx(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String xymc = form.getXymc();
		String xmdm = form.getXmdm();
		String xydm = form.getXydm();
		String nd   = form.getNd();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "���ű�", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		ws.mergeCells(0, 0, 10, 1);
		mb.printToOneCell_multy(ws, Base.xxmc+nd + xmmc+"���ű�" , 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 1000, Border.NONE);
		ws.mergeCells(0, 2, 5, 3);
		ws.mergeCells(6, 2, 10, 3);
		mb.printToOneCell_multy(ws, "ϵ������"+xymc, 0, 2, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "�ʱ��" + GetTime.getNowTime(), 6, 2, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);		
		ws.mergeCells(0, 4, 0, 5);
		mb.printToOneCell_multy(ws, "���", 0, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(1, 4, 1, 5);
		mb.printToOneCell_multy(ws, "����", 1, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(2, 4, 2, 5);
		mb.printToOneCell_multy(ws, "רҵ", 2, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(3, 4, 3, 5);
		mb.printToOneCell_multy(ws, "���֤��",3, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(4, 4, 4, 5);
		mb.printToOneCell_multy(ws, "�����˺�",4, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		
		ws.mergeCells(5, 4, 7, 4);
		mb.printToOneCell_multy(ws, "��������", 5, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(5, 5, "һ��",wcf));
		ws.addCell(new Label(6, 5, "����",wcf));
		ws.addCell(new Label(7, 5, "����",wcf));
		ws.mergeCells(8, 4, 8, 5);
		mb.printToOneCell_multy(ws, "�������", 8, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(9, 4, 9, 5);
		mb.printToOneCell_multy(ws, "ѧ��ǩ��", 9, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(10, 4, 10, 5);
		mb.printToOneCell_multy(ws, "��ע", 10, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);	
		List<HashMap<String,String>> list = dao.getXsZxjList_yx(nd,xmdm,xydm,form.getTableName());
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			map = list.get(i);
			ws.addCell(new Label(0, 6+i, map.get("rownum"),wcf));
			ws.addCell(new Label(1, 6+i, map.get("xm"),wcf));
			ws.addCell(new Label(2, 6+i, map.get("zymc"),wcf));
			ws.addCell(new Label(3, 6+i, map.get("sfzh"),wcf));
			ws.addCell(new Label(4, 6+i, map.get("yhkh"),wcf));		
			ws.addCell(new Label(8, 6+i, map.get("zzje"),wcf));
			
			ws.addCell(new Label(9, 6+i, " ",wcf));
			ws.addCell(new Label(10, 6+i, " ",wcf));
			String dc1 = "";
			String dc2 = "";
			String dc3 = "";
			if("1".equals(map.get("dc"))){
				dc1 = "1";
			}else if("2".equals(map.get("dc"))){
				dc2 = "1";
			}else{
				dc3 = "1";
			}
			ws.addCell(new Label(5, 6+i, dc1,wcf));
			ws.addCell(new Label(6, 6+i, dc2,wcf));
			ws.addCell(new Label(7, 6+i, dc3,wcf));			
		}

		wwb.write();
		wwb.close();
	}

}

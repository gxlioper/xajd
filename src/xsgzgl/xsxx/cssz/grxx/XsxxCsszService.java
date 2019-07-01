package xsgzgl.xsxx.cssz.grxx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.cssz.XsxxCsszInterface;
import xsgzgl.xsxx.model.CsszModel;
import xsgzgl.xsxx.model.ZdqxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_������Ϣ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxCsszService extends CommService implements XsxxCsszInterface {

	XsxxCsszDAO dao = new XsxxCsszDAO();

	/**
	 * ��ø�����Ϣ��������
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getCsszInfo(XsxxCsszForm model) {

		String[] colList = new String[] { "sfsh", "lcid", "sqkssj", "sqjssj",
				"shkssj", "shjssj", };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"xg_xsxx_grxx_szb", colList, "1", "1");

		if (Base.isNull(map.get("sfsh"))) {
			map.put("lcid", "��");
			map.put("sfsh", model.getSfsh());
		}

		return map;
	}

	/**
	 * �����������
	 * 
	 * @author luojw
	 */
	public boolean saveCssz(XsxxCsszForm model, User user,
			HttpServletRequest request) {

		// ������Ϣ�������ñ�
		String tableName = "xg_xsxx_grxx_szb";
		// ����
		String pk = "1";
		// ����ֵ
		String[] pkValue = new String[] { "1" };
		// ��һ�ֶ�
		String[] onezd = new String[] { "sfsh", "lcid", "sqkssj", "sqjssj",
				"shkssj", "shjssj", "over" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		model.setSfsh(unicode2Gbk(model.getSfsh()));
		
		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ����ֶ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZdszList(String zd,User user) {
		return dao.getZdszList(zd);
	}
	
	/**
	 * ����ֶ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,Object>> getZdszList() {

		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		// ��������
		List<HashMap<String, String>> sslxList = dao.getSslxList();
		// �ֶ�����
		List<HashMap<String, String>> zdList = dao.getZdszList("");

		for (int i = 0; i < sslxList.size(); i++) {
			HashMap<String, String> map = sslxList.get(i);
			String sslx = map.get("sslx");
			
			HashMap<String,Object> rs = new HashMap<String, Object>();
			rs.put("sslx", sslx);
			
			List<HashMap<String,String>> zdInfoList = new ArrayList<HashMap<String,String>>();
			
			for (int j = 0; j < zdList.size(); j++) {
				
				HashMap<String, String> zdInfo = zdList.get(j);
				if(sslx.equalsIgnoreCase(zdInfo.get("sslx"))){
					zdInfoList.add(zdInfo);
				}	
			}
			
			list.add(rs);
		}
		
		
		
		return list;

	}

	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void showZdxzDiv(String yhlx, HttpServletResponse response)
			throws IOException {
		
		response.setContentType("text/html;charset=gbk");

		// ��������
		List<HashMap<String, String>> sslxList = dao.getSslxList();
		// �ֶ�����
		List<HashMap<String, String>> zdList = dao.getZdszList("");
		
		StringBuilder html = new StringBuilder();
		
		for (int i = 0; i < sslxList.size(); i++) {
			
			HashMap<String, String> map = sslxList.get(i);
			String sslx = map.get("sslx");
			
			List<HashMap<String,String>> zdInfoList = new ArrayList<HashMap<String,String>>();
			HashMap<String,String> zdInfo = new HashMap<String, String>();
			zdInfo.put("zd", "all_" + i);
			zdInfo.put("zdm", "ȫѡ");
			zdInfo.put("zdlx", "all");
			
			zdInfoList.add(zdInfo);
			
			for (int j = 0; j < zdList.size(); j++) {
				
				zdInfo = zdList.get(j);
				
				if(sslx.equalsIgnoreCase(zdInfo.get("sslx"))){
					zdInfoList.add(zdInfo);
				}
			}
			
			int space = 5-zdInfoList.size()%5;
			if (space != 0) {
				for (int j = 0; j < space; j++) {
					zdInfo = new HashMap<String, String>();
					zdInfo.put("zd", "");
					zdInfo.put("zdm", "");
					
					zdInfoList.add(zdInfo);
				}
			}
			
			//������
			int num = 0;
			boolean br = true;
			
			html.append("<table class=\"formlist\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<th colspan=\"5\">");
			html.append("<span>" + sslx + "</span>");
			html.append("</th>");
			html.append("</tr>");
			
			for (int j = 0; j < zdInfoList.size(); j++) {

				zdInfo = zdInfoList.get(j);

				// �ֶ�
				String zd = zdInfo.get("zd");
				// �ֶ���
				String zdm = zdInfo.get("zdm");
				// �ֶ�����
				String zdlx = zdInfo.get("zdlx");
				// ѧ��Ȩ��
				String xsqx = zdInfo.get("xsqx");
				// ��ʦȨ��
				String lsqx = zdInfo.get("lsqx");
				
				//�Ƿ�ѡ��
				String checked = "";
				
				if ("xsqx".equalsIgnoreCase(yhlx) && "1".equalsIgnoreCase(xsqx)) {
					checked = "checked=\"true\" ";
				} else if ("lsqx".equalsIgnoreCase(yhlx) && "1".equalsIgnoreCase(lsqx)) {
					checked = "checked=\"true\" ";
				}
				
				if (br) {
					html.append("<tr>");
					br = false;
				}
				
				html.append("<td style=\"width:15%\">");
				
				if("all".equalsIgnoreCase(zdlx)){
					html.append("<input type=\"checkbox\" id=\"cb_all_"+i+"\" value=\"all\" onclick=\"checkAll('"+i+"')\"/>");
				}else if(Base.isNull(zdlx)){
					
				}else if("disabled".equalsIgnoreCase(zdlx)){
					html.append("<input type=\"checkbox\" value=\""+zd+"\" disabled=\"disabled\"/>");
				}else{
					html.append("<input type=\"checkbox\" name=\"cb_" + i + "\" value=\"" + zd + "\" " + checked + "/>");
				}
				html.append(zdm);
				html.append("</td>");
				
				num++;

				if (num >= 5) {
					br = true;
					num=0;
				}
				
				if (br) {
					html.append("</tr>");
				}
				
				
			}
			
			html.append("</thead>");
		
			html.append("</table>");
		}
		response.getWriter().print(html.toString());
	}

	/**
	 * �����ֶ�Ȩ��
	 * 
	 * @author luojw
	 */
	public boolean saveZdqx(XsxxCsszForm model, User user) {

		// �ֶ�Ȩ��Model
		ZdqxModel zdqxModel = model.getZdqxModel();
		// �ֶ�
		String[] zd = zdqxModel.getZd();
		// �Ƿ����ý���
		String over = model.getOver();
			
		//��ʼ���ֶ�Ȩ��
		boolean flag = dao.initZdqx(model, user);

		if (flag && zd != null && zd.length > 0) {
			//�����ֶ�Ȩ��
			flag = dao.saveZdqx(model, user);
		}

		if ("yes".equalsIgnoreCase(over)) {
			//�����������
			flag = dao.saveOver(model, user);
		}
		
		return flag;
	}
	
	/**
	 * ��ø�����Ϣ��������
	 * 
	 * @author ΰ�����
	 * 
	 */
	public CsszModel getGrxxCssz() {

		CsszModel csszModel = new CsszModel();

		HashMap<String, String> map = dao.getGrxxCssz();

		List<HashMap<String, String>> gwList =  XtwhShlcService.getSpgwList(map.get("lcid"),"");
		
		csszModel.setSfsh(map.get("sfsh"));
		csszModel.setLcid(map.get("lcid"));
		csszModel.setSqkssj(map.get("sqkssj"));
		csszModel.setSqjssj(map.get("sqjssj"));
		csszModel.setShkssj(map.get("shkssj"));
		csszModel.setShjssj(map.get("shjssj"));
		csszModel.setGwList(gwList);
		
		return csszModel;
	}
}

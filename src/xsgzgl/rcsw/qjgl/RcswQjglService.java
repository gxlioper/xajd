package xsgzgl.rcsw.qjgl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import com.zfsoft.utils.StringUtil;


import xgxt.DAO.DAO;
import xgxt.DAO.PicDAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;
import xsgzgl.comm.BasicService;
import xsgzgl.rcsw.qjgl.model.SqModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ճ�����_��ٹ���_Service��
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

public class RcswQjglService extends CommService {
	
	RcswQjglDAO dao = new RcswQjglDAO();
	
	/**
	 * ������������Ϣ
	 * 
	 * @author luojw
	 */
	public  HashMap<String, String> getQjlcInfo(RcswQjglForm model) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (!Base.isNull(model.getId())) {
			String[] colList = new String[] { "id", "lxmc", "lcid", "mints",
					"maxts", "xgr", "xgsj","qjlx" };
			map = CommonQueryDAO.commonQueryOne("xg_rcsw_qjgl_qjlxb", colList,
					"id", model.getId());
		}else{
			map.put("bz", "");
		}

		return map;
	}
	
	public HashMap<String, String> getQjStudentInfo(String xh) {
		return dao.getQjStuInfo(xh);
	}
	
	/**
	 * �����������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQjlcList() {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getWhList("xg_xtwh_splc",
				"id", "mc", "", "djlx", "rcsw", false);

		return list;
	}
	
	
	
	
	public String minDaysIsLegality(RcswQjglForm model){
		String qjs = dao.minDaysIsLegality(model);
		return qjs;
	}
	
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveQjlc(RcswQjglForm model, User user,
			HttpServletRequest request) {

		// 5S��
		String tableName = "xg_rcsw_qjgl_qjlxb";
		// ����
		String pk = "id";
		// ����ֵ
		String[] pkValue = new String[] { model.getId() };
		// ��һ�ֶ�
		String[] onezd = new String[] { "lxmc", "lcid", "mints", "maxts", "xgr", "qjlx" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		model.setXgr(user.getUserName());

		boolean flag = false;

		try {
			
			if(pkValue!=null&&!pkValue[0].equals("")){
				flag=dao.updateQjlc(model);
			}else{
				flag = saveInfoToDb(saveForm, model, request);
			}
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * �����������б��
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getQjlcList(RcswQjglForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		return dao.getQjlcList(model, user);
	}
	
	/**
	 * ����������Html
	 * 
	 * @author ΰ�����
	 */
	public String getQjlcHtml(SearchRsModel rsModel, RcswQjglForm model,
			ArrayList<String[]> rsArrList,User user) {
		BasicService bService=new BasicService();
		// IE�汾
		String ie = rsModel.getIe();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				// ���ID
				String id = rs[0];
				// ��С����
				String mints = rs[rs.length - 3];
				// �������
				String maxts = rs[rs.length - 2];
				// ʹ������
				String num = rs[rs.length - 1];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				
				if(!"0".equalsIgnoreCase(num)){
					spHtml.append(" disabled='true' ");
				}
				spHtml.append("value=\"" + id + "\"/>");
				spHtml.append("</td>");
				
				for (int j = 1; j < rs.length-3; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(bService.replaceHtml(rs[j]));
					spHtml.append("</td>");
				}
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				spHtml.append(">");
				
				spHtml.append("����");
				spHtml.append(mints);
				spHtml.append("�죬С�ڵ���");
				spHtml.append(maxts);
				spHtml.append("��");
				
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\"> ");
				spHtml.append(num);
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ɾ���������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delQjlc(RcswQjglForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.delQjlc(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	//	===================���������������======================================
	
	/**
	 * ����ҵ�����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMyqjList(RcswQjglForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getMyqjList(model, user);
	}
	
	/**
	 * ����ҵ����Html
	 * 
	 * @author ΰ�����
	 */
	public String getMyqjHtml(SearchRsModel rsModel, RcswQjglForm model,
			ArrayList<String[]> rsArrList,User user) {
		
		// IE�汾
		String ie = rsModel.getIe();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				// ����ID
				String id = rs[0];
				// ������
				String sqjg = rs[rs.length - 1];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"   \">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				
				if(!"δ���".equalsIgnoreCase(sqjg)){
					spHtml.append(" disabled='true' ");
				}
				spHtml.append("value=\"" + id + "\"/>");
				spHtml.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\">");	
				spHtml.append("<a href=\"#\" onclick=\"print('"+id+"');return false;\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("��ӡ�����");
				spHtml.append("</font>");
				spHtml.append("</a>");
				
				spHtml.append("&nbsp;<a href=\"#\" onclick=\"viewdetail('"+id+"');return false;\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("��������");
				spHtml.append("</font>");
				spHtml.append("</a>");
				
				
				spHtml.append("</td>");
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ��������������������Ŀ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getQjxm(RcswQjglForm model, User user) {

		// �����Ŀ
		String qjxm = "";
		// ��������
		String sqts = model.getSqts();
		// �������
		String qjlx = model.getQjlx();
		// ������������б�
		List<HashMap<String, String>> list = dao.getSqlxList(model, user);

//		if (list != null && list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//
//				HashMap<String, String> map = list.get(i);
//				String id = map.get("id");
//				String lxmc = map.get("lxmc");
//				String mints = map.get("mints");
//				String maxts = map.get("maxts");
//				String qjlb = map.get("qjlx");
//				
//				if (Float.parseFloat(sqts) > Float.parseFloat(mints)
//						&& Float.parseFloat(sqts) <= Float.parseFloat(maxts)
//						&& qjlx.equalsIgnoreCase(qjlb)) {
//					qjxm = lxmc + "!!@@!!" + id;
//					break;
//				}
//			}
//		}

		return list;
	}
	
	public List<HashMap<String, String>> getLctList(RcswQjglForm model) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> list = dao.getLctList(model);
		
		int j =0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String shzt = map.get("shzt");
				if((shzt.equals("δ���")||shzt.equals("��ͨ��"))&&j!=-999){
					j=-999;
					newList.add(map);
				}else if(shzt.equals("δ���")&&j==-999){
					map.put("xh", map.get("xh"));
					map.put("mc", map.get("mc"));
					map.put("shzt", "");
					newList.add(map);
				}else{
					newList.add(map);
				}
			}
		}
		

		return newList;
	}
	
	/**
	 * ɾ���ҵ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delMyqjSq(RcswQjglForm model, User user) {

		boolean flag = false;

		// ɾ���������Ϣ
		try {
			flag = dao.delMyqjSq(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		// ɾ����˱���Ϣ
		try {
			flag = dao.delMyqjSh(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		if (flag){
			MyJobsManager manager = new MyJobsImpl();
			try {
				manager.removeJob(model.getCheckVal(), "��ٹ���");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	/**
	 * �����ҵ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveMysq(RcswQjglForm model, User user,
			HttpServletRequest request) {

		String id = New_Random_ID.getUUId();
		
		model.setId(id);
		// �����������
		boolean flag = saveQjsq(model, user, request);

		// ������˸�λID
		String[] gwid = null;
		
		List<HashMap<String, String>> list = dao.getGwidList(model);

		if (list != null && list.size() > 0) {

			gwid = new String[list.size()];

			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				gwid[i] = map.get("spgw");
			}

			model.setGwid(gwid);
		}

		// ���ID
		String sqid = dao.getQjgwId(model, user);
		
		// ����������
		if (flag && model.getGwid() != null && model.getGwid().length > 0) {
			
			SqModel sqModel = new SqModel();
			sqModel.setSqid(sqid);
			sqModel.setGwid(gwid);
			
			flag = saveQjsh(sqModel, user);
			
			if(flag){
				RcswQjglForm qjglForm = new RcswQjglForm();
				qjglForm.setId(model.getQjid());
				
				HashMap<String,String> qjlcInfo = getQjlcInfo(qjglForm);
				
				/*
				* ���칤����ҵ��ID��������,������λ���������תURL����������תURL��ҵ���ܣ���Ŀ���ƣ�
				* 2013-1-10 qph
				*/
				Job job = Job.getJobInstance(id, model.getXh(), model.getGwid()[0], 
						"rcsw_qjgl.do?method=myshManage&czxm="+model.getQjid()+"&shgw="+model.getGwid()[0], 
						"rcsw_qjgl_mygz_stu.do","��ٹ���", qjlcInfo.get("lxmc"));
				
				MyJobsManager manager = new MyJobsImpl();
				try {
					manager.pushJob(job);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;

	}
	
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveQjsq(RcswQjglForm model, User user,
			HttpServletRequest request) {

		// ��������
		String tableName = "xg_rcsw_qjgl_qjsqb";
		// ����
		String pk = "id";
		// ����ֵ
		String[] pkValue = new String[] { model.getId() };
		// ��һ�ֶ�
		String[] onezd = new String[] { "id","qjid", "xn", "xq", "xh", "sqts",
				"kssj", "jssj", "lxdh", "jtdh", "jtdz", "sqly", "bz","sqjg","qjcl","kzzd1" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setSqjg("δ���");
		
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
	 * ����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveQjsh(SqModel model, User user) {

		// �����˱�
		String tableName = "xg_rcsw_qjgl_qjshb";
		// ����
		String pk = "sqid";
		// ����ֵ
		String[] pkValue = new String[] { model.getSqid() };
		// �����ֶ�
		String[] arrzd = new String[] { "gwid" };
		// ��һ�ֶ�
		String[] onezd = new String[] { "sqid" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	//	===================�����ҵ����======================================
	
	/**
	 * ����û���λ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getShgwInfoList(RcswQjglForm model,
			User user) {
		return dao.getShgwInfoList(model, user);
	}
	
	/**
	 * ����ҵ�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getMygzList(RcswQjglForm model,
			User user) {

		List<HashMap<String, String>> shgwList = getShgwInfoList(model, user);

		List<HashMap<String, String>> list = dao.getMygzList(shgwList, model,
				user);

		return list;
	}
	
	/**
	 * ����ҵĹ���Html
	 * 
	 * @author ΰ�����
	 */
	public String getMygzHtml(SearchRsModel rsModel, RcswQjglForm model,User user) {
		
		// IE�汾
		String ie = rsModel.getIe();
		
		List<HashMap<String,String>> rsList = getMygzList(model, user);
		
		StringBuilder spHtml = new StringBuilder();

		if (rsList != null && rsList.size() > 0) {
			
			for (int i = 0; i < rsList.size(); i++) {

				HashMap<String,String> rs = rsList.get(i);
				
				String lxid = rs.get("lxid");
				String lxmc = rs.get("lxmc");
				String num = rs.get("num");
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"40%\">");
				spHtml.append(lxmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"40%\">");
				spHtml.append(num);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append("<a href=\"#\" onclick=\"goMysh('"+lxid+"');return false;\">");
				spHtml.append("<font color=\"blue\">");
				spHtml.append("���");
				spHtml.append("</font>");
				spHtml.append("</a>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ��ó�ʼ����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getCshXmList(RcswQjglForm model,User user) {
		
		List<HashMap<String, String>> list = dao.getCshXmList(model, user);
		
		BasicService bService = new BasicService(); 
		
		for(int i=0;i<list.size();i++){
			
			HashMap<String,String>map=list.get(i);
			map.put("xmmc",bService.replaceHtml(map.get("xmmc")));
		}

		return list;
	}
	
	public List<HashMap<String, String>> getShList(RcswQjglForm model,User user) {
		List<HashMap<String, String>> list = dao.getShList(model, user);
		return list;
	}
	
	
	
	/**
	 * ����ҵ�����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getMyshList(RcswQjglForm model,
			User user) throws IllegalArgumentException, 
			SecurityException, IllegalAccessException, 
			InvocationTargetException, NoSuchMethodException {
		return dao.getMyshList(model, user);
	}
	
	/**
	 * ����ҵĹ���Html
	 * 
	 * @author ΰ�����
	 */
	public String getMyshHtml(SearchRsModel rsModel, RcswQjglForm model, ArrayList<String[]> rsArrList,User user) {
		
		// IE�汾
		String ie = rsModel.getIe();
		// ��ʽ·��
		String stylePath=rsModel.getStylePath();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String bjmc = rs[3];
				String sqts = rs[4];
				String shzt = rs[5];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqts);
				spHtml.append("</td>");
				
				String pic_name = "";
				
				if ("δ���".equalsIgnoreCase(shzt)) {
					pic_name = "dsh";
				} else if ("ͨ��".equalsIgnoreCase(shzt)) {
					pic_name = "shtg";
				} else if ("��ͨ��".equalsIgnoreCase(shzt)) {
					pic_name = "shbtg";
				}
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append("<p><img src=\""+stylePath+"images/ico_"+pic_name+".gif\" width=\"52\" height=\"18\" /></p>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ����ҵ������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getMyqjInfo(RcswQjglForm model, User user) {
		return dao.getMyqjInfo(model, user);
	}	
	
	/**
	 * ���������ϢHtml
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void createShInfoHtml(RcswQjglForm model,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		List<HashMap<String, String>> list = dao.getShInfoList(model);
		
		StringBuilder html = new StringBuilder();
		html.append("<table class=\"formlist\">");
		html.append("<thead onclick=\"\"><tr><th colspan=\"4\">");
		html.append("<span>�����Ϣ</span>");
		html.append("</th></tr></thead>");

		String shgw = model.getShgw();
		
		if (list != null && list.size() > 0) {
			html.append("<tbody>");
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				// ��λID
				String gwid = map.get("gwid");
				// �����
				String shrxm = map.get("shrxm");
				// ���ʱ��
				String shsj = map.get("shsj");
				
				if(shgw.equalsIgnoreCase(gwid)){
					html.append("<tr><th width=\"20%\">������");
					html.append("<br /><font color=\"red\">(����¼��500��)</font></th>");
					html.append("<td colspan=\"3\">");
					html.append("<textarea name=\"shyj\" ");
					html.append("id=\"shyj\" rows=\"5\" ");
					html.append("style=\"word-break:break-all;width:545px\" ");
					html.append("onblur=\"chLeng(this,'500')\">");
					html.append("</textarea>");
					html.append("</td>");
					break;
				}else{
//					html.append("<tr><th width=\"20%\">�����</th>");
//					html.append("<td width=\"30%\">"+shrxm+"</td>");
//					html.append("<tr><th width=\"20%\">���ʱ��</th>");
//					html.append("<td width=\"30%\">"+shsj+"</td></tr>");	
				}
			}
			html.append("</tbody>");
		}
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ����ҵ������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveShzt(RcswQjglForm model, User user) {
		
		boolean flag = false;

		try {
			flag = dao.saveShzt(model, user);
			if (flag) {
				flag = dao.saveSqjg(model, user);
				
				/*
				 * ���ʹ���
				 * 2013-1-10 qph
				 */
				String[] spgw = dao.getShgwBySqid(model.getSqid());
				String nextShgw = null;
				Job job = null;
				if (null != spgw && spgw.length > 0){
					String curShgw = model.getShgw();
					int index = StringUtils.getStrIndexInArray(curShgw, spgw);
					nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
				}
				
				if (StringUtil.isNull(nextShgw) || "��ͨ��".equals(model.getShzt())) {
					job = Job.getJobInstance(model.getSqid(),"��ٹ���");
				} else {
					job = Job.getJobInstance(model.getSqid(),nextShgw,
							"rcsw_qjgl.do?method=myshManage&czxm="+model.getQjid()+"&shgw="+nextShgw,"��ٹ���");
				}
				 
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	// ===================�����ҵĹ���======================================
	
	/**
	 * ����ҵ������Ϣ(�����Ϣ)
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getQjshInfo(RcswQjglForm model, User user) {

		return dao.getQjshInfo(model, user);

	}
	
	/**
	 *  ����ҵĹ���Html�������ѯ��
	 * 
	 * @author qlj
	 */
	public String getMycxHtml(SearchRsModel rsModel, RcswQjglForm model,
			ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String nj = rs[3];
				String xymc = rs[4];
				String zymc = rs[5];
				String bjmc = rs[6];
				String lxmc = rs[7];
				String sqsj = rs[8];
				String sqts = rs[9];
				String sqjg = rs[10];

				spHtml.append("<tr onclick=\"rowOnClick(this);\" title=\"˫������ʾ��ϸ��Ϣ\" ondblclick=\"showJgcxDetail(this)\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
//				spHtml.append(nj);
//				spHtml.append("</td>");
//				
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
//				spHtml.append(xymc);
//				spHtml.append("</td>");
//				
//				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
//				spHtml.append(zymc);
//				spHtml.append("</td>");
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(lxmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqsj);
				spHtml.append("</td>");

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqts);
				spHtml.append("</td>");

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqjg);
				spHtml.append("</td>");

				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}

	/**
	 * ��ý���б�
	 * 
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMycxList(RcswQjglForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getMycxList(model, user);
	}
	
	/**
	 * ��ȡ�������б�
	 * @ auther qlj
	 */
	public List<HashMap<String,String>>getQjlbList(){
		
		return dao.getQjlbList();
	}
	
	/**
	 * �����ʦ��Ƭ
	 * @param model
	 * @return
	 */
	public String saveQjcl(RcswQjglForm model){
		
		String guid=GetSysData.getGuid();
		PicDAO picDao = new PicDAO();
		
		FormFile file = model.getQjclPath();
		
		String message="";
		try {
			picDao.saveQjclPic(file.getInputStream(), guid);
			message=guid;
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return message;
	}
	
	/**
	 * �ճ���������ѯ�Զ��嵼��
	 * @param model
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getMycxExportList(RcswQjglForm model, User user)
	throws IllegalArgumentException, SecurityException,
	IllegalAccessException, InvocationTargetException,
	NoSuchMethodException {
		return dao.getMycxExportList(model, user);
	}
}

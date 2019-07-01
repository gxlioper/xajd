package xsgzgl.pjpy.szgyyq.mypj.tea;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.pjlc.xmsh.PjpyXmshForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;
import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.DshdService;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(��ʦ)_Service��
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

public class PjpyTeaService extends PjpyMypjService {
	
	PjpyTeaDAO dao = new PjpyTeaDAO();
	
	/**
	 * ���5S����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFivesInfoList(PjpyTeaForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �༶ѧ��
		ArrayList<String[]> xsList = dao.getBjxsList(model, user);

		// 5S�����
		List<HashMap<String, String>> fivesList = dao.getFivesList(model,
				xsList, user);
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if (xsList != null && xsList.size() > 0) {
			for (int i = 0; i < xsList.size(); i++) {
				String[] xs = xsList.get(i);
				String xh = xs[0];//ѧ��
				String xm = xs[1];//����
				float sqfs = 0;//�����
				float xyf = 0;//ѧԺ��˷�
				float xxf = 0;//ѧУ��˷�
				
				if (fivesList != null && fivesList.size() > 0) {
					for (int j = 0; j < fivesList.size(); j++) {
						HashMap<String, String> map = fivesList.get(j);
						
						// �Ѵ���
						String ycl = map.get("ycl");
						// 5Sѧ��
						String fives_xh = map.get("xh");
						
						if(!"yes".equalsIgnoreCase(ycl) && fives_xh.equalsIgnoreCase(xh)){
							
							String jjf = map.get("jjf");//�Ӽ���
							String sqf = map.get("sqf");//�����
							String xyshf = map.get("xyshf");//ѧԺ��˷�
							String xysh = map.get("xysh");//ѧԺ���
							String xxshf = map.get("xxshf");//ѧУ��˷�
							String xxsh = map.get("xxsh");//ѧУ���
							
							//����ִ���
							if("�ӷ�".equalsIgnoreCase(jjf)){
								sqfs+=Float.parseFloat(sqf);
							}else{
								sqfs-=Float.parseFloat(sqf);
							}
							
							//ѧԺ�ִ���
							if("ͨ��".equalsIgnoreCase(xysh)){
								if("�ӷ�".equalsIgnoreCase(jjf)){
									xyf+=Float.parseFloat(xyshf);
								}else{
									xyf-=Float.parseFloat(xyshf);
								}
							}
							
							// ѧУ�ִ���
							if("ͨ��".equalsIgnoreCase(xxsh)){
								if("�ӷ�".equalsIgnoreCase(jjf)){
									xxf+=Float.parseFloat(xxshf);
								}else{
									xxf-=Float.parseFloat(xxshf);
								}
							}
							
						}else{
							continue;
						}
					}
				}
				
				String[] rs = new String[5];
				rs[0] = xh;
				rs[1] = xm;
				rs[2] = String.valueOf(sqfs);
				rs[3] = String.valueOf(xyf);
				rs[4] = String.valueOf(xxf);
				
				list.add(rs);
			}
		}
		
		return list;
	}
	
	/**
	 * ���5S��ά��Html
	 * 
	 * @author ΰ�����
	 */
	public String getFivesHtml(SearchRsModel rsModel, PjpyTeaForm model,
			ArrayList<String[]> rsArrList,User user) {

		// ������Ŀ
		String czxm = model.getCzxm();
		// IE�汾
		String ie = rsModel.getIe();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ��Ŀ
		String xmdm = model.getXmdm();
		//�û�����
		String yhlx = model.getYhlx();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String xh = rs[0];
				
				String userName = user.getUserName();
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"center\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + rs[0] + "\"/>");
				spHtml.append("</td>");
				
				for (int j = 0; j < rs.length; j++) {
					
					spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"width:20%;\" ");
					
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
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * ����������5S���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getFivesList(PjpyTeaForm model) {
		return dao.getFivesList(model);
	}

	/**
	 * ��üӼ���ԭ���б�
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getYyList() {
		return dao.getYyList();
	}
	
	/**
	 * ����5S��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveFives(PjpyTeaForm model, User user,HttpServletRequest request){

		FivesModel fivesModel = model.getFivesModel();
		
		String[] id = fivesModel.getId();
		String[] fzxm = fivesModel.getFzxm();
		String[] jjf = fivesModel.getJjf();
		String[] sqf = fivesModel.getSqf();
		String[] jfrq = fivesModel.getJfrq();
		String[] yy = fivesModel.getYy();
		String[] jfyy = fivesModel.getJfyy();

		ArrayList<String> add_fzxmList = new ArrayList<String>();
		ArrayList<String> add_jjfList = new ArrayList<String>();
		ArrayList<String> add_sqfList = new ArrayList<String>();
		ArrayList<String> add_jfrqList = new ArrayList<String>();
		ArrayList<String> add_yyList = new ArrayList<String>();
		ArrayList<String> add_jfyyList = new ArrayList<String>();
		
		
		ArrayList<String> edit_idList = new ArrayList<String>();
		ArrayList<String> edit_sqfList = new ArrayList<String>();
		
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				if("no".equalsIgnoreCase(id[i])){
					add_fzxmList.add(fzxm[i]);
					add_jjfList.add(unicode2Gbk(jjf[i]));
					add_sqfList.add(sqf[i]);
					add_jfrqList.add(jfrq[i]);
					add_yyList.add(yy[i]);
					add_jfyyList.add(unicode2Gbk(jfyy[i]));
				}else{
					edit_idList.add(id[i]);
					edit_sqfList.add(sqf[i]);
				}
			}
		}

		boolean flag = false;
		
		try {
			
			// ��������
			fivesModel.setFzxm(add_fzxmList.toArray(new String[] {}));
			fivesModel.setJjf(add_jjfList.toArray(new String[] {}));
			fivesModel.setSqf(add_sqfList.toArray(new String[] {}));
			fivesModel.setJfrq(add_jfrqList.toArray(new String[] {}));
			fivesModel.setYy(add_yyList.toArray(new String[] {}));
			fivesModel.setJfyy(add_jfyyList.toArray(new String[]{}));
			flag = addFives(fivesModel, user);
			
			// �޸Ĳ���
			fivesModel.setId(edit_idList.toArray(new String[] {}));
			fivesModel.setSqf(edit_sqfList.toArray(new String[] {}));
			flag = dao.editFives(fivesModel, user);
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ����5S��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean addFives(FivesModel fivesModel, User user){

		
		// 5S��
		String tableName = "szyc_5sb";
		// ����
		String pk = "1";
		// ����ֵ
		String[] pkValue = new String[] { "2" };
		// ��һ�ֶ�
		String[] onezd = new String[] { "xn", "xq", "xh" };
		// �����ֶ�
		String[] arrzd = new String[] { "fzxm", "jjf", "yy", "jfrq", "sqf","jfyy" };
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);

		boolean flag = false;
		
		try {
			flag = saveInfoToDb(saveForm, fivesModel, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ɾ��5S��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delFives(PjpyTeaForm model, User user) {

		FivesModel fivesModel = model.getFivesModel();

		// 5S��
		String tableName = "szyc_5sb";
		// ����
		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(fivesModel.getId());

		boolean flag = false;

		try {
			flag = delInfoInDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����Ŀ�б�(�������)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getFsshXmList(PjpyTeaForm model) {

		//�û�����
		String yhlx = model.getYhlx();
		
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		// ����
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_dshdjzb");
		map.put("xmmc", "����");
		xmList.add(map);

		// ���Ա��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_yybdjzb");
		map.put("xmmc", "���Ա��");
		xmList.add(map);

		// IVT��̳
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_ivtltb");
		map.put("xmmc", "IVT��̳");
		xmList.add(map);

		// ����
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_xthddjb");
		map.put("xmmc", "����");
		xmList.add(map);

		// ��֯�
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_zznlfzb");
		map.put("xmmc", "��֯����");
		xmList.add(map);

		// ���ʵ��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_shsjfzb");
		map.put("xmmc", "���ʵ��");
		xmList.add(map);

		if("xy".equalsIgnoreCase(yhlx) || "xx".equalsIgnoreCase(yhlx)){
			// 5S��
			map = new HashMap<String, String>();
			map.put("xmdm", "szyc_5sb");
			map.put("xmmc", "5S");
			xmList.add(map);
		}
		
		return xmList;
	}
	
	/**
	 * ��ø�����Ŀ�Ľ����ѯ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFsshList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//��Ŀ����
		String xmdm = model.getXmdm();
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			DshdService service = new DshdService();
			//list = service.getDshdShList(model, user);
			list=getCommShList(model, user, xmdm);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��  ����ļ������Ժϲ���һ��
			list=getCommShList(model, user, xmdm);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			list=getCommShList(model, user, xmdm);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			list=getCommShList(model, user, xmdm);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
			list=getCommShList(model, user, xmdm);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			list=getCommShList(model, user, xmdm);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S
			list=getFiveSShList(model, user, xmdm);
		}


		return list;
	}
	
	/**
	 * �������Html
	 * 
	 * @author ΰ�����
	 */
	public String getFsshHtml(SearchRsModel rsModel, PjpyTeaForm model,
			ArrayList<String[]> rsArrList,User user) {
		
		// IE�汾
		String ie = rsModel.getIe();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ��Ŀ
		String xmdm = model.getXmdm();
		//�û�����
		String yhlx = model.getYhlx();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			int show_num = 4;

			if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
				if ("xy".equalsIgnoreCase(yhlx)) {
					show_num = 4;
				}
			} else {
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {
					show_num = 0;
				}
			}
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String xh = rs[0];
				
				// -------------------2011.11.22 qlj�ж��������ʱ ���ø�ѡ�� begin -------------------------
				String shzt="";
				if ("xx".equalsIgnoreCase(yhlx)) {

					shzt = rs[rs.length-show_num - 1];
						
				}else if ("szyc_5sb".equalsIgnoreCase(xmdm) && "xy".equalsIgnoreCase(yhlx)) {

					shzt = rs[rs.length -show_num- 1];
						
				}else if("xy".equalsIgnoreCase(yhlx)){
					
					shzt = rs[rs.length -show_num- 1];
				}else if(!"szyc_5sb".equalsIgnoreCase(xmdm) && "bzr".equalsIgnoreCase(yhlx)){
					
					shzt = rs[rs.length -show_num- 2];
				}
				// -------------------2011.11.22 qlj�ж��������ʱ ���ø�ѡ�� end -------------------------
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				
				if("�������".equalsIgnoreCase(shzt)){
					spHtml.append(" disabled='true' ");
				}
				spHtml.append("value=\"" + xh + "\"/>");
				spHtml.append("</td>");
				
				for (int j = 0; j < rs.length - show_num; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");

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
				
				
				if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {

					if ("xy".equalsIgnoreCase(yhlx)
							|| "xx".equalsIgnoreCase(yhlx)) {

						// ��Ͷ����
						String btsr = rs[rs.length - 4];
						// Ͷ����
						String tsr = rs[rs.length - 3];
						// Ͷ������
						String tsnr = rs[rs.length - 2];
						// Ͷ��ʱ��
						String tssj = rs[rs.length - 1];
						
						

						spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\"> ");
						
						//��Ͷ��
						if(Base.isNull(tsr)){
							spHtml.append("��");
						}else{
							
							spHtml.append("<a href=\"#\" onclick=\"showXstsDiv('"+tsr+"','"+btsr+"','"+tsnr+"','"+tssj+"');return false;\">");
							spHtml.append("<font color=\"blue\">");
							spHtml.append("��");
							spHtml.append("</font>");
							spHtml.append("</a>");
						}					
						spHtml.append("</td >");
					}
				} else {
					if ("xx".equalsIgnoreCase(yhlx)) {
						// ��Ͷ����
						String btsr = rs[rs.length - 4];
						// Ͷ����
						String tsr = rs[rs.length - 3];
						// Ͷ������
						String tsnr = rs[rs.length - 2];
						// Ͷ��ʱ��
						String tssj = rs[rs.length - 1];

						spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\"> ");
						
						//��Ͷ��
						if(Base.isNull(tsr)){
							spHtml.append("��");
						}else{
							
							spHtml.append("<a href=\"#\" onclick=\"showXstsDiv('"+tsr+"','"+btsr+"','"+tsnr+"','"+tssj+"');return false;\">");
							spHtml.append("<font color=\"blue\">");
							spHtml.append("��");
							spHtml.append("</font>");
							spHtml.append("</a>");
						}					
						spHtml.append("</td >");
					}
				}
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * ������˷�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveShf(PjpyTeaForm model, User user){
		return dao.saveShf(model, user);
	}
	
	/**
	 * �������״̬
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveShzt(PjpyTeaForm model, User user){
		return dao.saveShzt(model, user);
	}
	
	/**
	 * �����������״̬
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean savePlShzt(PjpyTeaForm model, User user){
		return dao.savePlShzt(model, user);
	}
	
	/**
	 * ��÷�������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getFsshInfoList(PjpyTeaForm model,User user) {
		
		PjpyStuService stuService = new PjpyStuService();
		
		PjpyStuForm stuModel = new PjpyStuForm();
		
		// ѧ��
		String xh = model.getXh();
		stuModel.setXh(xh);

		// ѧ��
		String xn = model.getXn();
		stuModel.setXn(xn);

		// ѧ��
		String xq = model.getXq();
		stuModel.setXq(xq);

		// ��Ŀ����
		String xmdm = model.getXmdm();
		stuModel.setXmdm(xmdm);
		
		//������Ϣ
		List<HashMap<String, String>> infoList = stuService.getSqxxList(stuModel);
		//ѧ�������б�
		List<HashMap<String, String>> xsssList = getXsssList(model, user);
		
		//�û�����
		String yhlx = model.getYhlx();
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		if (infoList != null && infoList.size() > 0) {
			for (int i = 0; i < infoList.size(); i++) {
				
				HashMap<String, String> map = infoList.get(i);
				String bzrsh = map.get("bzrsh");
				String xysh = map.get("xysh");
				String xxsh = map.get("xxsh");
				String id = map.get("id");
				
				// ��������
				String ssnr = "";
				// ����ʱ��
				String sssj = "";
				// ������
				String clr = "";
				// �������
				String clyj = "";
				
				if (!Base.isNull(id)) {
					if (xsssList != null && xsssList.size() > 0) {
						for (int j = 0; j < xsssList.size(); j++) {
							HashMap<String, String> xsssInfo = xsssList.get(j);
							if (id.equalsIgnoreCase(xsssInfo.get("xmid"))) {
								ssnr = xsssInfo.get("ssnr");
								sssj = xsssInfo.get("sssj");
								clr = xsssInfo.get("clr");
								clyj = xsssInfo.get("clyj");
								break;
							}
						}
					}
				}
				
				map.put("ssnr", ssnr);
				map.put("sssj", sssj);
				map.put("clr", clr);
				map.put("clr", clyj);
				
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// �೤�������
					if ("ͨ��".equalsIgnoreCase(xysh)
							|| "��ͨ��".equalsIgnoreCase(xysh)
							|| "ͨ��".equalsIgnoreCase(xxsh)
							|| "��ͨ��".equalsIgnoreCase(xxsh)) {
						map.put("kfsh", "no");
					} else {
						map.put("kfsh", "yes");
					}
					
					list.add(map);
					
				} else if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ
					if ("ͨ��".equalsIgnoreCase(xxsh)
							|| "��ͨ��".equalsIgnoreCase(xxsh)) {
						map.put("kfsh", "no");
					} else {
						map.put("kfsh", "yes");
					}
					
					if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
						list.add(map);
					} else {
						if ("ͨ��".equalsIgnoreCase(bzrsh)) {
							list.add(map);
						}
					}
				}else if ("xx".equalsIgnoreCase(yhlx)) {// ѧԺ
					if("ͨ��".equalsIgnoreCase(xysh)){
						map.put("kfsh", "yes");
						list.add(map);
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * ���ѧ��Ͷ����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXstsInfo(ArrayList<String[]> xhList,
			PjpyTeaForm model, User user) {
		return dao.getXstsInfo(xhList, model, user);
	}
	
	/**
	 * ���ѧ�������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsssList(PjpyTeaForm model,
			User user) {
		return dao.getXsssList(model, user);
	}
	
	/**
	 * �ж����״̬
	 * 
	 * @author ΰ�����
	 */
	public String getShztInfo(List<HashMap<String, String>> shztList,
			String xh, String yhlx,String xmdm) {

		int bzrsh_tg = 0;
		int bzrsh_btg = 0;
		int bzrsh_wsh = 0;
		int bzrsh_xcs = 0;
		
		int xysh_tg = 0;
		int xysh_btg = 0;
		int xysh_wsh = 0;
		int xysh_xcs = 0;
		int xysh_th = 0;
		
		int xxsh_tg = 0;
		int xxsh_btg = 0;
		int xxsh_wsh = 0;
		int xxsh_th = 0;
		
		String bzrshqk = "";
		String xyshqk = "";
		String xxshqk = "";

		int sh_num = 0;
		
		for (int j = 0; j < shztList.size(); j++) {

			HashMap<String, String> map = shztList.get(j);

			String shxh = map.get("xh");
			String bzrsh = map.get("bzrsh");
			String xysh = map.get("xysh");
			String xxsh = map.get("xxsh");

			if (shxh.equalsIgnoreCase(xh)) {
				
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {//������ �� �೤
					
					sh_num++;

					if ("ͨ��".equalsIgnoreCase(bzrsh)) {
						bzrsh_tg++;
					} else if ("��ͨ��".equalsIgnoreCase(bzrsh)) {
						bzrsh_btg++;
					} else if ("δ���".equalsIgnoreCase(bzrsh)) {
						bzrsh_wsh++;
					} else if ("������".equalsIgnoreCase(bzrsh)) {
						bzrsh_xcs++;
					}
				}
				

				if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
					if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ�û�

						sh_num++;

						if ("ͨ��".equalsIgnoreCase(xysh)) {
							xysh_tg++;
						} else if ("��ͨ��".equalsIgnoreCase(xysh)) {
							xysh_btg++;
						} else if ("δ���".equalsIgnoreCase(xysh)) {
							xysh_wsh++;
						} else if ("������".equalsIgnoreCase(xysh)) {
							xysh_xcs++;
						} else if ("�˻�".equalsIgnoreCase(xysh)) {
							xysh_th++;
						}
					}
				} else {
					if ("xy".equalsIgnoreCase(yhlx)
							&& "ͨ��".equalsIgnoreCase(bzrsh)) {// ѧԺ�û�

						sh_num++;

						if ("ͨ��".equalsIgnoreCase(xysh)) {
							xysh_tg++;
						} else if ("��ͨ��".equalsIgnoreCase(xysh)) {
							xysh_btg++;
						} else if ("δ���".equalsIgnoreCase(xysh)) {
							xysh_wsh++;
						} else if ("������".equalsIgnoreCase(xysh)) {
							xysh_xcs++;
						} else if ("�˻�".equalsIgnoreCase(xysh)) {
							xysh_th++;
						}
					}
				}

				if ("xx".equalsIgnoreCase(yhlx) && "ͨ��".equalsIgnoreCase(xysh)) {// ѧԺ�û�

					sh_num++;
					
					if ("ͨ��".equalsIgnoreCase(xxsh)) {
						xxsh_tg++;
					} else if ("��ͨ��".equalsIgnoreCase(xxsh)) {
						xxsh_btg++;
					} else if ("δ���".equalsIgnoreCase(xxsh)) {
						xxsh_wsh++;
					} else if ("�˻�".equalsIgnoreCase(xxsh)) {
						xxsh_th++;
					}
				}
			}
		}

		if (bzrsh_wsh != 0) {
			bzrshqk = "����δ���";
		} else if (bzrsh_xcs != 0) {
			bzrshqk = "����������";
		} else if (bzrsh_tg == sh_num) {
			bzrshqk = "ȫ�����ͨ��";
		} else if (bzrsh_btg == sh_num) {
			bzrshqk = "ȫ����˲�ͨ��";
		} else if (bzrsh_tg != 0) {
			bzrshqk = "�������ͨ��";
		} 

		if (sh_num == 0) {
			xyshqk = "�������";
		} else if (xysh_wsh != 0) {
			xyshqk = "����δ���";
		} else if (xysh_xcs != 0) {
			xyshqk = "����������";
		} else if (xysh_tg == sh_num) {
			xyshqk = "ȫ�����ͨ��";
		} else if (xysh_btg == sh_num) {
			xyshqk = "ȫ����˲�ͨ��";
		} else if (xysh_tg != 0) {
			xyshqk = "�������ͨ��";
		} else if (xysh_th != 0) {
			xyshqk = "�����˻�";
		} 

		if (sh_num == 0) {
			xxshqk = "�������";
		} else if (xxsh_wsh != 0) {
			xxshqk = "����δ���";
		} else if (xxsh_tg == sh_num) {
			xxshqk = "ȫ�����ͨ��";
		} else if (xxsh_btg == sh_num) {
			xxshqk = "ȫ����˲�ͨ��";
		} else if (xxsh_tg != 0) {
			xxshqk = "�������ͨ��";
		} else if (xxsh_th != 0) {
			xxshqk = "�����˻�";
		}
		
		String shzt = "";

		// �����λ�೤
		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {
			shzt = bzrshqk;
		} else if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ
			shzt = xyshqk;
		} else if ("xx".equalsIgnoreCase(yhlx)) {// ѧУ
			shzt = xxshqk;
		}

		return shzt;
	}
	
	/**
	 * �������ߴ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveSscl(PjpyTeaForm model, User user){

		XsssModel xsssModel = model.getXsssModel();
		
		// ѧ��
		String xn = xsssModel.getXn();
		// ѧ��
		String xq = xsssModel.getXq();
		// ѧ��
		String xh = xsssModel.getXh();
		//��ĿID
		String xmid = xsssModel.getXmid();
		// ��Ŀ����
		String xmlx = xsssModel.getXmlx();

		// ������Ŀ��˱�
		String tableName = "xg_pjpy_szgyyq_xsssb";
		// ����
		String pk = "xn||xq||xh||xmid";
		// ����ֵ
		String[] pkValue = new String[] { xn + xq + xh + xmid };
		// �޸��ֶ�
		String[] onezd = new String[] { "clr", "clyj", "clsj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// ������
		String clr = user.getUserName();
		xsssModel.setClr(clr);
		// ����ʱ��
		String clsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		xsssModel.setClsj(clsj);
		
		boolean flag = false;
		
		try {
			flag = updateInfoInDb(saveForm, xsssModel, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ����Ͷ�ߴ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveTscl(PjpyTeaForm model, User user){

		XstsModel xstsModel = model.getXstsModel();
		
		// ѧ��
		String xn = xstsModel.getXn();
		// ѧ��
		String xq = xstsModel.getXq();
		// ѧ��
		String xh = xstsModel.getXh();
		// ��Ͷ����
		String btsr = xstsModel.getBtsr();
		// ��Ŀ����
		String xmlx = xstsModel.getXmlx();

		// ������Ŀ��˱�
		String tableName = "xg_pjpy_szgyyq_xstsb";
		// ����
		String pk = "xn||xq||xh||btsr||xmlx";
		// ����ֵ
		String[] pkValue = new String[] { xn + xq + xh + btsr + xmlx };
		// �޸��ֶ�
		String[] onezd = new String[] { "clr", "clyj", "clsj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// ������
		String clr = user.getUserName();
		xstsModel.setClr(clr);
		// ����ʱ��
		String clsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		xstsModel.setClsj(clsj);
		
		boolean flag = false;
		
		try {
			flag = updateInfoInDb(saveForm, xstsModel, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	// ==================����Made By ΰ�����======================
	
	/**
	 * ��ȡ�ۺϲ����б�
	 * @param model
	 * @return
	 * @author gaobb
	 * @throws Exception 
	 */
	public List<String[]> getZhcpList(PjpyTeaForm model,User user) throws Exception{
		return dao.getZhcpList(model, user);
	}
	
	/**
	 * ��ȡ��ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] top=new String[]{"ѧ��","����","�༶","5Sģ���","���Ա���","������","IVT��̳��",
				"������","��֯������","���ʵ����","�ۺ����ʷ�","�ۺ����ʷ�����"};
		return top;
	}
	
	/**
	 * �����������
	 * @param model
	 * @return
	 * @author gaobb
	 * @throws Exception 
	 */
	public boolean jsfspm(PjpyTeaForm model,HttpServletRequest request) throws Exception{
		return dao.jsfspm(model, request);
	}
	
	/**
	 * �����ۺϲ����༶���ܱ�����
	 * @param model
	 * @param response
	 * @return
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public boolean exportZhcpBjhzb(PjpyTeaForm model,HttpServletResponse response) throws Exception{
		String bjdm=model.getBjdm();
		if(Base.isNull(bjdm)){//���༶����Ϊ��ֱ��
			return false;
		}
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		
		HashMap<String,HashMap<String,String>> szb=dao.getSzgy_zhszycfsszb();
		String bjmc=dao.getBjmc(bjdm);
		String title=bjmc+"��ѧ���ۺ��������ɿγɼ�����";
		String xthd="ѧ�Ż("+(Integer.parseInt(szb.get("yybd").get("zxmzgf"))
							+Integer.parseInt(szb.get("dshd").get("zxmzgf"))
							+Integer.parseInt(szb.get("ivtlt").get("zxmzgf"))
							+Integer.parseInt(szb.get("wthd").get("zxmzgf")))+"��)";
		String[] colNameCN=new String[]{"ѧ��","����","5S("+szb.get("wsmk").get("zxmzgf")+"��)",
				"���Ա��("+szb.get("yybd").get("zxmzgf")+"��)","����("+szb.get("dshd").get("zxmzgf")+"��)",
				"IVT��̳("+szb.get("ivtlt").get("zxmzgf")+"��)","����("+szb.get("wthd").get("zxmzgf")+"��)",
				"��֯����("+szb.get("zznl").get("zxmzgf")+"��)","���ʵ��("+szb.get("shsj").get("zxmzgf")+"��)",
				"�ܼ�","����"};
		
		DAO dao = DAO.getInstance();
		List<String[]> list = new ArrayList<String[]>();
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet(title, 0);
		
		ws.setColumnView(0, 12);
		ws.setColumnView(1, 15);
		ws.setColumnView(2, 10);
		ws.setColumnView(3, 13);
		ws.setColumnView(4, 13);
		ws.setColumnView(5, 13);
		ws.setColumnView(6, 15);
		ws.setColumnView(7, 15);
		ws.setColumnView(8, 15);
//		ws.setColumnView(9, 15);
//		ws.setColumnView(10, 15);
		
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat titleFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 11, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);; // ���쵥Ԫ���ʽ
		//��ͷ
		ws.mergeCells(0, 0, colNameCN.length-1, 0);
		ws.addCell(new Label(0, 0, title, titleFormat));
		//�μ���ͷ
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0,1,colNameCN[0],wcf2));
		ws.mergeCells(1, 1, 1, 2);
		ws.addCell(new Label(1,1,colNameCN[1],wcf2));
		ws.mergeCells(2, 1, 2, 2);
		ws.addCell(new Label(2,1,colNameCN[2],wcf2));
		ws.mergeCells(3, 1, 6, 1);
		ws.addCell(new Label(3,1,xthd,wcf3));
		ws.mergeCells(7, 1, 7, 2);
		ws.addCell(new Label(7,1,colNameCN[7],wcf2));
		ws.mergeCells(8, 1, 8, 2);
		ws.addCell(new Label(8,1,colNameCN[8],wcf2));
		ws.mergeCells(9, 1, 9, 2);
		ws.addCell(new Label(9,1,colNameCN[9],wcf2));
		ws.mergeCells(10, 1, 10, 2);
		ws.addCell(new Label(10,1,colNameCN[10],wcf2));
		//����
		ws.addCell(new Label(3,2,colNameCN[3],wcf2));
		ws.addCell(new Label(4,2,colNameCN[4],wcf2));
		ws.addCell(new Label(5,2,colNameCN[5],wcf2));
		ws.addCell(new Label(6,2,colNameCN[6],wcf2));
		try {
//			for (int m = 0; m < colNameCN.length; m++) {
//				ws.addCell(new Label(m, 1, colNameCN[m]));
//			}
			String[] outputs=new String[]{"xh","xm","wsmkf","yybdf","dshdf","ivtlt","wthd","zznl","shsj","zhszf","zhszfpm"};
			String sql="select a.*,b.xm from szgy_zhszcphzlsb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and a.xq=? and b.bjdm=?";
			list = dao.rsToVator(sql, new String[]{Base.currXn,Base.currXq,bjdm},outputs );
			
			int k = outputs.length;
			for (int i = 0; i < list.size(); i++) {
				String[] tmp = list.get(i);
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 3, tmp[j],wcf2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return true;
	}
	/**
	 * ��ȡ����ivtlt��sql
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public String importIvtlt(String filePath,User user) throws Exception{
		File file = new File(filePath);
		
		String message = "����ʧ�ܣ�";
		
		boolean flag = false;
		
		// ��������
		if (!Base.isNull(filePath)) {
			String sql="insert into szyq_ivtltb(xh,xn,xq,jztm,xthdrq,jcdj,ccdj,pf,bzrsh,xysh,xxsh,xxshr) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?)";
			List<String[]> params = new ArrayList<String[]>();

			Sheet sourceSheet = Workbook.getWorkbook(new File(filePath)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			String[] row;
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				//���Ҫ�����¼   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,12);
				String[] param=new String[]{row[2],row[0],row[1],row[7],row[8],row[9],row[10],row[11],
						"ͨ��","ͨ��","ͨ��",user.getUserName()+"����"};
				String[] topTr=new String[]{"ѧ��","ѧ��","ѧ��","����","ѧԺ","רҵ","�༶","������Ŀ","����","�����Ǽ�","�����Ǽ�","����"};
				params.add(param);
			    //���Ҫ�����¼   end
			}
			
			
			flag = saveArrDate(sql, params, "szyq_ivtltb", user);
			if(flag){
				message="����ɹ���";
			}
			file.delete();
		}

		return message;
	}
	
	public ArrayList<String[]> getCommShList(PjpyTeaForm model, User user,
			String tableName) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyTeaService service = new PjpyTeaService();

		// ����б�
		ArrayList<String[]> dshdList = dao
				.getCommShList(model, user, tableName);
		// ���״̬�б�
		List<HashMap<String, String>> shztList = dao.getCommShList(dshdList,
				model, user, tableName);
		// ѧ��Ͷ����Ϣ
		List<HashMap<String, String>> xstsList = service.getXstsInfo(dshdList,
				model, user);

		ArrayList<String[]> list = new ArrayList<String[]>();

		String yhlx = model.getYhlx();
		String xmdm = model.getXmdm();

		if (dshdList != null && dshdList.size() > 0) {
			for (int i = 0; i < dshdList.size(); i++) {
				String[] rs = dshdList.get(i);
				String xh = rs[0];
				String xm = rs[1];
				String bjmc = rs[2];
				String sqf = fillZero(rs[3]);
				String bzrshf = fillZero(rs[4]);
				String xyshf = fillZero(rs[5]);
				String xxshf = fillZero(rs[6]);

				// ��Ͷ����
				String btsr = "";
				// Ͷ����
				String tsr = "";
				// Ͷ������
				String tsnr = "";
				// Ͷ��ʱ��
				String tssj = "";

				// ����Ͷ����Ϣ
				if (xstsList != null && xstsList.size() > 0) {
					for (int j = 0; j < xstsList.size(); j++) {
						HashMap<String, String> xstsInfo = xstsList.get(j);
						if (xh.equalsIgnoreCase(xstsInfo.get("btsr"))) {
							btsr = xstsInfo.get("btsr");
							tsr = xstsInfo.get("tsr");
							tsnr = xstsInfo.get("tsnr");
							tssj = xstsInfo.get("tssj");
						}
					}
				}

				// �����λ�೤
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {

					// ���״̬
					String shzt = service.getShztInfo(shztList, xh, yhlx, xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							shzt };
					list.add(value);
				} else if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ
					// ���״̬
					String shzt = service.getShztInfo(shztList, xh, yhlx, xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, shzt, btsr, tsr, tsnr, tssj };

					list.add(value);
				} else if ("xx".equalsIgnoreCase(yhlx)) {// ѧУ
					// ���״̬
					String shzt = service.getShztInfo(shztList, xh, yhlx, xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, xxshf, shzt, btsr, tsr, tsnr, tssj };

					list.add(value);
				}
			}
		}

		return list;
	}
	
	/**
	 * ��ȡ5S�����Ϣ�б�
	 * @param model
	 * @param user
	 * @param tableName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getFiveSShList(PjpyTeaForm model, User user,String tableName)
		throws IllegalArgumentException, SecurityException,
				IllegalAccessException, InvocationTargetException,
				NoSuchMethodException {

	PjpyTeaService service = new PjpyTeaService();
	
	// ����б�
	ArrayList<String[]> fiveSList = dao.getFiveSList(model, user,tableName);
	// ���״̬�б�
	List<HashMap<String, String>> shztList = dao.getFiveSList(fiveSList,model, user,tableName);
	// ѧ��Ͷ����Ϣ
	List<HashMap<String, String>> xstsList = service.getXstsInfo(fiveSList, model,
			user);
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String yhlx = model.getYhlx();
	String xmdm = model.getXmdm();
	
	if (fiveSList != null && fiveSList.size() > 0) {
		for (int i = 0; i < fiveSList.size(); i++) {
			String[] rs = fiveSList.get(i);
			String xh = rs[0];
			String xm = rs[1];
			String bjmc = rs[2];
			String sqf = rs[3];
			String xyshf = rs[4];
			String xxshf = rs[5];
	
			// ��Ͷ����
			String btsr = "";
			// Ͷ����
			String tsr = "";
			// Ͷ������
			String tsnr = "";
			// Ͷ��ʱ��
			String tssj = "";
	
			//����Ͷ����Ϣ
			if (xstsList != null && xstsList.size() > 0) {
				for (int j = 0; j < xstsList.size(); j++) {
					HashMap<String, String> xstsInfo = xstsList.get(j);			
					if(xh.equalsIgnoreCase(xstsInfo.get("btsr"))){
						btsr = xstsInfo.get("btsr");
						tsr = xstsInfo.get("tsr");
						tsnr = xstsInfo.get("tsnr");
						tssj = xstsInfo.get("tssj");
					}
				}
			}
			
			// �����λ�೤
			if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {
	
				// ���״̬
				String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);
	
				String[] value = new String[] { xh, xm, bjmc, sqf, shzt };
				list.add(value);
			} else if ("xy".equalsIgnoreCase(yhlx)) {// ѧԺ
				// ���״̬
				String shzt =service.getShztInfo(shztList, xh, yhlx,xmdm);
	
				
				String[] value = new String[] { xh, xm, bjmc, sqf, xyshf, 
						shzt, btsr, tsr, tsnr, tssj };
				
				list.add(value);
			} else if ("xx".equalsIgnoreCase(yhlx)) {// ѧУ
				// ���״̬
				String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);
	
				String[] value = new String[] { xh, xm, bjmc, sqf, xyshf, 
						xxshf, shzt, btsr, tsr, tsnr, tssj };
	
				list.add(value);
			}
		}
	}
	
	return list;
	}
	

	/**
	 * ��ȡ�ۺϲ����趨
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getZhcpsdInfo(PjpyTeaForm model){
		
		return dao.getZhcpsdInfo(model);
	}
	
}
package xsgzgl.pjpy.szgyyq.mypj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.Pages;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_Service��
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

public class PjpyMypjService extends CommService {

	PjpyMypjDAO dao = new PjpyMypjDAO();
	
	/**
	 * �����ҵ������û�����
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public String getMypjYhlx(User user) {

		// �û���
		String userName = user.getUserName();

		// �û�����
		String userType = user.getUserType();
		
		// ������Ȩ��
		boolean bzrQx = Fdypd.isBzr(userName, "");
		
		String lx = "";
		
		if ("stu".equalsIgnoreCase(userType)) {// �ж��Ƿ�ѧ��
			// �ж��Ƿ�೤
			if (isBz(userName)) {
				lx = "bz";
			} else {
				lx = "stu";
			}
		} else if (bzrQx) {// �ж��Ƿ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// �ж��Ƿ�Ժϵ��ʦ
			lx = "xy";
		} else {
			lx = "xx";
		}

		return lx;
	}
	
	/**
	 * ����ҵ�����Html(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public String getMypjStuHtml(SearchRsModel rsModel, PjpyMypjForm model,
			User user) {

		// ��������Ŀͳ���б�
		List<HashMap<String, String>> tjList = getStuTjList(model, user);

		// ��ҳ
		Pages pages = model.getPages();
		tjList = getResultList(tjList, pages);

		StringBuilder spHtml = new StringBuilder();

		if (tjList != null && tjList.size() > 0) {

			// ����
			model.setRows(String.valueOf(tjList.size()));

			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> rs = tjList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");

				String color = "";
				spHtml.append("/>");

				// ��Ŀ����
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("xmmc"));
				spHtml.append("</td>");

				// �������
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("sqfs"));
				spHtml.append("</td>");

				// ������
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("shqk"));
				spHtml.append("</td>");

				// ���շ���
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("zzfs"));
				spHtml.append("</td>");

				// ����
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\"> ");
				if("��".equalsIgnoreCase(rs.get("xmdm"))){
					spHtml.append("��");
				}else{
					
					String xn = Base.currXn;
					String xq = Base.currXq;
					String xh = user.getUserName();
					String xmdm = rs.get("xmdm");
					
					spHtml.append("<a href=\"#\" onclick=\"showSqxxDetail('"+xn+"','"+xq+"','"+xh+"','"+xmdm+"','edit');return false;\">");
					spHtml.append("<font color=\"blue\">");
					spHtml.append("<font color=\"blue\">����鿴��ϸ</font>");
					spHtml.append("</font>");
					spHtml.append("</a>");
				}
				spHtml.append("</td>");

				spHtml.append("</tr>");

			}
		}

		return spHtml.toString();
	}

	/**
	 * ���ͳ���б�(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getStuTjList(PjpyMypjForm model,
			User user) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// ����ͳ���б�
		List<HashMap<String, String>> dshdList = dao.getStuDshdTjList(model,
				user);

		HashMap<String, String> map = new HashMap<String, String>();

		if (dshdList != null && dshdList.size() > 0) {

			setStuTjMap(dshdList, map, "szyq_dshdjzb", "����");

		} else {
			map.put("xmmc", "����");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		// ���Ա��ͳ���б�
		List<HashMap<String, String>> yybdList = dao.getStuYybdTjList(model,
				user);

		map = new HashMap<String, String>();

		if (yybdList != null && yybdList.size() > 0) {

			setStuTjMap(yybdList, map, "szyq_yybdjzb", "���Ա��");

		} else {
			map.put("xmmc", "���Ա��");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		// Ivt��̳ͳ���б�
		List<HashMap<String, String>> ivtltList = dao.getStuIvtltTjList(model,
				user);

		map = new HashMap<String, String>();

		if (ivtltList != null && ivtltList.size() > 0) {

			setStuTjMap(ivtltList, map, "szyq_ivtltb", "IVT��̳");

		} else {
			map.put("xmmc", "IVT��̳");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		// ����ͳ���б�
		List<HashMap<String, String>> wthdList = dao.getStuWthdTjList(model,
				user);

		map = new HashMap<String, String>();

		if (wthdList != null && wthdList.size() > 0) {

			setStuTjMap(wthdList, map, "szyq_xthddjb", "����");

		} else {
			map.put("xmmc", "����");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		// ��֯����ͳ���б�
		List<HashMap<String, String>> zznlList = dao.getStuZznlTjList(model,
				user);

		map = new HashMap<String, String>();

		if (zznlList != null && zznlList.size() > 0) {

			setStuTjMap(zznlList, map, "szyc_zznlfzb", "��֯����");

		} else {
			map.put("xmmc", "��֯����");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		// ���ʵ��ͳ���б�
		List<HashMap<String, String>> shsjList = dao.getStuShsjTjList(model,
				user);

		map = new HashMap<String, String>();

		if (shsjList != null && shsjList.size() > 0) {

			setStuTjMap(shsjList, map, "szyc_shsjfzb", "���ʵ��");

		} else {
			map.put("xmmc", "���ʵ��");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		// 5Sͳ���б�
		List<HashMap<String, String>> fiveSList = dao.getStuFiveSTjList(model,
				user);

		map = new HashMap<String, String>();

		if (fiveSList != null && fiveSList.size() > 0) {

			setStuTjMap(fiveSList, map, "szyc_5sb", "5S");

		} else {
			map.put("xmmc", "5S");
			map.put("sqfs", "0");
			map.put("shqk", "��");
			map.put("zzfs", "0");
			map.put("xmdm", "��");
		}

		list.add(map);

		return list;
	}

	/**
	 * ����ͳ��Map(ѧ��)
	 * 
	 * @author ΰ�����
	 */
	private void setStuTjMap(List<HashMap<String, String>> list,
			HashMap<String, String> map, String xmdm, String xmmc) {
		
		float sqfs = 0;
		float zzfs = 0;
		String jcf = "";
		String zgf = "";

		int bzrsh_tg = 0;
		int bzrsh_btg = 0;
		int bzrsh_wsh = 0;

		int xysh_tg = 0;
		int xysh_btg = 0;
		int xysh_wsh = 0;

		int xxsh_tg = 0;
		int xxsh_btg = 0;
		int xxsh_wsh = 0;

		String shqk = "";
		String bzrshqk = "";
		String xyshqk = "";
		String xxshqk = "";
		String zzfqk = "";

		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> rs = list.get(i);
			String sqf = rs.get("sqf");
			String zzf = rs.get("zzf");
			String bzrsh = rs.get("bzrsh");
			String xysh = rs.get("xysh");
			String xxsh = rs.get("xxsh");
			String jjf = rs.get("jjf");
			jcf = rs.get("jcf");
			zgf = rs.get("zgf");

			if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
				sqfs += Float.parseFloat(sqf);
				zzfs += Float.parseFloat(zzf);
			}else{
				if("�ӷ�".equalsIgnoreCase(jjf)){
					sqfs += Float.parseFloat(sqf);
					zzfs += Float.parseFloat(zzf);
				}else{
					sqfs -= Float.parseFloat(sqf);
					zzfs -= Float.parseFloat(zzf);
				}
			}

			if ("ͨ��".equalsIgnoreCase(bzrsh)) {
				bzrsh_tg++;
			} else if ("��ͨ��".equalsIgnoreCase(bzrsh)) {
				bzrsh_btg++;
			} else if ("δ���".equalsIgnoreCase(bzrsh)) {
				bzrsh_wsh++;
			}

			if ("ͨ��".equalsIgnoreCase(xysh)) {
				xysh_tg++;
			} else if ("��ͨ��".equalsIgnoreCase(xysh)) {
				xysh_btg++;
			} else if ("δ���".equalsIgnoreCase(xysh)) {
				xysh_wsh++;
			}

			if ("ͨ��".equalsIgnoreCase(xxsh)) {
				xxsh_tg++;
			} else if ("��ͨ��".equalsIgnoreCase(xxsh)) {
				xxsh_btg++;
			} else if ("δ���".equalsIgnoreCase(xxsh)) {
				xxsh_wsh++;
			}
		}

		if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
			if (bzrsh_tg == list.size()) {
				bzrshqk = "������ȫ�����ͨ��";
			} else if (bzrsh_btg == list.size()) {
				bzrshqk = "������ȫ����˲�ͨ��";
			} else if (bzrsh_wsh == list.size()) {
				bzrshqk = "��������δ���";
			} else if (bzrsh_tg != 0) {
				bzrshqk = "�����β������ͨ��";
			} else if (bzrsh_btg != 0) {
				bzrshqk = "�����β�����˲�ͨ��������δ���";
			}
		}

		if (xysh_tg == list.size()) {
			xyshqk = Base.YXPZXY_KEY+"ȫ�����ͨ��";
		} else if (xysh_btg == list.size()) {
			xyshqk = Base.YXPZXY_KEY+"ȫ����˲�ͨ��";
		} else if (xysh_wsh == list.size()) {
			xyshqk = Base.YXPZXY_KEY+"��δ���";
		} else if (xysh_tg != 0) {
			xyshqk = Base.YXPZXY_KEY+"�������ͨ��";
		} else if (xysh_btg != 0) {
			xyshqk = Base.YXPZXY_KEY+"������˲�ͨ��������δ���";
		}

		if (xxsh_tg == list.size()) {
			xxshqk = "ѧУȫ�����ͨ��";
		} else if (xxsh_btg == list.size()) {
			xxshqk = "ѧУȫ����˲�ͨ��";
		} else if (xxsh_wsh == list.size()) {
			xxshqk = "ѧУ��δ���";
		} else if (xxsh_tg != 0) {
			xxshqk = "ѧУ�������ͨ��";
		} else if (xxsh_btg != 0) {
			xxshqk = "ѧУ������˲�ͨ��������δ���";
		}

		shqk += bzrshqk + "<br/>";
		shqk += xyshqk + "<br/>";
		shqk += xxshqk;

		zzfqk += "���շ���:" + zzfs + "</br>";
		zzfqk += "������:" + jcf + "</br>";
		zzfqk += "��߷�:" + zgf;

		map.put("xmmc", xmmc);
		map.put("sqfs", String.valueOf(sqfs));
		map.put("shqk", shqk);
		map.put("zzfs", zzfqk);
		map.put("xmdm", xmdm);
	}
	
	/**
	 * ��ȡѧУͳ���б�
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getXxMyPjTjList(User user){
		return dao.xxbzrMyPjCheckEmpty(dao.getXxMyPjTjList(user),"xx");
	}
	
	/**
	 * ��ȡ������ͳ���б�
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getBzrMyPjTjList(User user){
		return dao.xxbzrMyPjCheckEmpty(dao.getBzrMyPjTjList(user),"bzr");
	}
	
}
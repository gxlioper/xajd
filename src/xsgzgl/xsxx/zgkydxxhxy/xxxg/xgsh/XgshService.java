package xsgzgl.xsxx.zgkydxxhxy.xxxg.xgsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.jcsz.XsxxJcszService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸Č���_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XgshService extends XsxxGeneralService implements
		XxxgXgshInterface {

	XgshDAO dao = new XgshDAO();

	/**
	 * ��ñ�ͷ�ļ����޸Č��ˡ�
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXgshTop(XgshModel model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjdm",
				"sqsj", "shzt" };
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�꼶", "�༶",
				"�����޸�ʱ��", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý�������޸Č��ˡ�
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXgshList(XsxxGeneralForm myForm,
			XgshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXgshList(myForm, model, user);
	}

	/**
	 * ������������޸Č��ˡ�
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public String createXgshHTML(SearchRsModel rsModel, XgshModel model,
			ArrayList<String[]> rsArrList, User user) {
		
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
				String xb = rs[3];
				String nj = rs[4];
				String bjmc = rs[5];
				String xgsj = rs[6];
				String shzt = rs[7];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xb);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(nj);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xgsj);
				spHtml.append("</td>");
				
				String pic_name = "";
				
				if ("wsh".equalsIgnoreCase(shzt)) {
					pic_name = "dsh";
				} else if ("tg".equalsIgnoreCase(shzt)) {
					pic_name = "shtg";
				} else if ("btg".equalsIgnoreCase(shzt)) {
					pic_name = "shbtg";
				} else if ("th".equalsIgnoreCase(shzt)) {
					pic_name = "shth";
				} else if ("xcs".equalsIgnoreCase(shzt)) {
					pic_name = "shxcs";
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
	 * ��ÌW���޸���Ϣ
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public HashMap<String, String> getXgshInfo(XgshModel model, User user) {

		// �W����Ϣ���ֶ�
		String[] xsxxb = { "xh", "xm", "xb", "csrq", "nj", "xz", "xymc",
				"zzmmmc", "zymc", "mzmc", "bjmc", "xjztm", "rxrq", "sfzh",
				"jgmc", "hkszdmc", "sydmc", "sjhm", "dzyx", "qqhm", "jtdh",
				"jtyb", "jtszd", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz",
				"jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx",
				"jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1", "jtcy3_xm",
				"jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2", "jtcy3_lxdh1", "xmpy",
				"cym", "sg", "tz", "tc", "jkzk", "pyccmc", "sfzd", "kslbmc",
				"rxfsmc", "pyfsmc", "bz","yhdm","yhkh","zw","zd1","zd2","zd3","zd4","zd5","grjl"};

		// ��ՈID
		String sqid = model.getSqid();

		List<HashMap<String, String>> list = dao.getXgxxList(sqid, xsxxb);

		HashMap<String, String> map = new HashMap<String, String>();
		// �W����Ϣ
		HashMap<String, String> xsxx = list.get(0);
		// �޸���Ϣ
		HashMap<String, String> xgxx = list.get(1);

		for (int i = 0; i < xsxxb.length; i++) {

			String zd = xsxxb[i];
			String xsxx_zd = xsxx.get(zd);
			String xgxx_zd = xgxx.get(zd);
			
			if(zd.equalsIgnoreCase("jtyb")){
				int a=0;
				a++;
			}
			if (Base.isNull(xgxx_zd) || Base.isNull(xgxx_zd.trim())) {
				map.put(zd, xsxx_zd);
			} else if (!Base.isNull(xgxx_zd)) {
				//���иĳ���  ��ʽΪ "strLiTT"����LiTT��β
				
				if(xgxx_zd.endsWith("LiTT")){
					xgxx_zd = xgxx_zd+"��";
				}
				String[] value = xgxx_zd.split("LiTT");
				if(value.length==1){
					map.put(zd, xgxx_zd);
				} else {		
					if("null".equalsIgnoreCase(value[0]) || StringUtils.isNull(value[0])){
						value[0]="��";
					}
					String msg = "�޸�ǰ��ϢΪ��" + value[0];
					StringBuilder html = new StringBuilder();
					html.append("<a ");
					html.append("title=\""+msg+"\"");
					html.append(">");
					html.append("<font color=\"red\">");
					if("null".equalsIgnoreCase(value[1])){
						html.append("��");
					}else{
						html.append(value[1]);
					}
					html.append("</font>");
					html.append("</a>");
					
					map.put(zd, html.toString());
				}
			}
			/*
			 * else if (!Base.isNull(xgxx_zd)) {
						
				String[] value = xgxx_zd.split("LiTT");
				
				if(0 !=(xgxx_zd.indexOf("LiTT", 0))){
					if(value.length == 1){
						value = new String []{value[0],"��"};
					}
				}
					
				if(value.length==1){
					map.put(zd, xgxx_zd);
				} else {				
					String msg = "�޸�ǰ��ϢΪ��" + value[0];
					StringBuilder html = new StringBuilder();
					html.append("<a ");
					html.append("title=\""+msg+"\"");
					html.append(">");
					html.append("<font color=\"red\">");
					html.append(value[1]);			
					html.append("</font>");
					html.append("</a>");
					
					map.put(zd, html.toString());
				}
			}
			 */
		}

		return map;
	}

	/**
	 * ���挏�ˠ�B
	 * 
	 * @date 2013-01-25
	 * @author ΰ�����
	 */
	public boolean saveShzt(XgshModel model, User user) {

		XsxxJcszService jcszService = new XsxxJcszService();
//		// �������P��Ϣ
//		HashMap<String, Object> shxgInfo = jcszService.getShlcByYh(user
//				.getUserName());
//		// ������λ�б�
//		List<HashMap<String, String>> spgwList = (List<HashMap<String, String>>) shxgInfo
//				.get("spgwList");
		XsxxtyglService service = new XsxxtyglService();
		HashMap<String, String> csszMap = service.getCsszjg();
		List<HashMap<String, String>> spgwList = jcszService.getSpgwBySplc(csszMap.get("shlid"));
		// ��ՈID
		String sqid = model.getSqid();
		// ������Ҋ
		String shyj = model.getShyj();
		// ���ˠ�B
		String shzt = model.getShzt();
		// ��λID
		String gwid = model.getGwid();
		// ���I
		String[] pkValue = model.getPkValue();

		// ��󼉄e
		boolean isMax = gwid.equalsIgnoreCase(spgwList.get(spgwList.size() - 1)
				.get("spgw")) ? true : false;

		// ��С���e
		boolean isMin = gwid.equalsIgnoreCase(spgwList.get(0).get("spgw")) ? true
				: false;

		// ��λ���e
		int gwjb = 0;
		// ������λ
		List<String> spgw = new ArrayList<String>();
		
		for (int i = 0; i < spgwList.size(); i++) {
			
			spgw.add(spgwList.get(i).get("spgw"));
			
			if (gwid.equalsIgnoreCase(spgwList.get(i).get("spgw"))) {
				gwjb = i;
			}
		}
		
		boolean flag = false;

		try {

			// ���汾���ˍ�λ�Č��ˠ�B
			flag = dao.updateXgshb(pkValue, gwid, shzt, shyj, user);

			// ������칤��
			saveDbgz(pkValue, gwid, spgw.toArray(new String[] {}), shzt);
			
			if ("th".equalsIgnoreCase(shzt) && !isMin) {// �����e�˻أ���ǰһ���e�O�Þ����،�
				shzt = "xcs";
				gwid = spgwList.get(gwjb - 1).get("spgw");
				dao.updateXgshb(pkValue, gwid, shzt, user);
			} else if ("tg".equalsIgnoreCase(shzt) && !isMax) {// �����eͨ�^������һ���e�O�Þ����،�
				shzt = "wsh";
				gwid = spgwList.get(gwjb + 1).get("spgw");
				dao.updateXgshb(pkValue, gwid, shzt, user);
			}

			String shjg = "shz";

			if ("tg".equalsIgnoreCase(shzt) && isMax) {// ��߼�ͨ�^
				shjg = "tg";
			} else if ("th".equalsIgnoreCase(shzt) && isMin) {// ��С���e�˻�
				shjg = "th";
			}

			// ������Ո��Č��˽Y��
			if (flag) {
				flag = dao.updateXgsqb(pkValue, shjg, user);
			}
			
			// ��߼��Ñ􌏺�ͨ�^�t���W����Ϣ��
			if ("tg".equalsIgnoreCase(shzt) && isMax) {
				submitXxxg(pkValue, user);
			}
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ������칤��
	 * 
	 * @date 2013-02-01
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void saveDbgz(String[] sqid, String gwid, String[] spgw, String shzt)
			throws Exception {

		// ����ID
		String[] id = sqid;
		// ��ǰ��λID
		String curShgw = gwid;
		// ��ǰ����
		int index = StringUtils.getStrIndexInArray(curShgw, spgw);

		for (String str : id) {
			Job job = null;
			if ("tg".equals(shzt)) {
				String nextShgw = index != spgw.length - 1
						&& spgw[index + 1] != null ? spgw[index + 1] : null;
				job = Job.getJobInstance(str, nextShgw,
						"general_xsxx.do?method=xgshSearch&spgw="+nextShgw, "ѧ����Ϣ�޸�");
			} else if ("th".equals(shzt)) {
				if (index != 0) {
					String nextShgw = index != 0 ? spgw[index - 1] : null;
					job = Job.getJobInstance(str, nextShgw,
							"general_xsxx.do?method=xgshSearch&spgw="+nextShgw, "ѧ����Ϣ�޸�");
				} else {
					job = Job.getJobInstance(str, "ѧ����Ϣ�޸�");
				}
			}

			MyJobsManager manager = new MyJobsImpl();
			manager.updateJob(job);
		}
	}
	
	/**
	 * ��Ì�����Ϣ�б�
	 * 
	 * @date 2013-01-25
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXgshList(XgshModel model, User user) {
		XsxxtyglService service = new XsxxtyglService();
		XsxxJcszService jcszService = new XsxxJcszService();
//		// �������P��Ϣ
//		HashMap<String, Object> shxgInfo = jcszService.getShlcByYh(user
//				.getUserName());
//		// ������λ�б�
//		List<HashMap<String, String>> spgwList = (List<HashMap<String, String>>) shxgInfo
//				.get("spgwList");
		
		HashMap<String, String> csszMap = service.getCsszjg();
		List<HashMap<String, String>> spgwList = jcszService.getSpgwBySplc(csszMap.get("shlid"));
		// ��ՈID
		String sqid = model.getSqid();
		// ��λID
		String gwid = model.getGwid();

		List<HashMap<String, String>> shList = dao.getXgshList(sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < spgwList.size(); i++) {

			// ������λ
			String spgw = spgwList.get(i).get("spgw");
			// ��λ���Q
			String gwmc = spgwList.get(i).get("gwmc");
			// ���ˠ�B
			String shzt = "";
			// ������
			String shr = "";
			// ���˕r�g
			String shsj = "";
			// ������Ҋ
			String shyj = "";
			// �Ƿ��@ʾ
			String sfxs = "yes";
			// ������Ҋ
			String bjyj = "no";
			
			for (int j = 0; j < shList.size(); j++) {
				if (spgw.equalsIgnoreCase(shList.get(j).get("gwid"))) { 
					shzt = shList.get(j).get("shzt");
					shr = shList.get(j).get("shrxm");
					shsj = shList.get(j).get("shsj");
					shyj = shList.get(j).get("shyj");

					if ("wsh".equalsIgnoreCase(shzt)
						|| "xcs".equalsIgnoreCase(shzt)) {
						sfxs = "no";
					}

					if (shList.get(j).get("gwid").equalsIgnoreCase(gwid)) {
						bjyj = "yes";
					}
					
					break;
				}
			}

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("gwmc", gwmc);
			map.put("shzt", shzt);
			map.put("shr", shr);
			map.put("shsj", shsj);
			map.put("shyj", shyj);
			map.put("sfxs", sfxs);
			map.put("bjyj", bjyj);
			
			list.add(map);
		}

		return list;
	}

	/**
	 * �ύ�W���޸���Ϣ
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public void submitXxxg(String[] sqid, User user) {

		// �W����Ϣ���ֶ�
		String[] xsxxb = { "xm", "xb", "csrq", "xz", "zzmm", "yhdm","yhkh",
				 "mz", "xjztm", "rxrq", "sfzh", "jg", "hkszd",
				"syd", "sjhm", "dzyx", "qqhm", "jtyb", "xmpy", "cym", "sg",
				"tz", "tc", "jkzk", "pycc", "sfzd", "kslb", "rxfs", "bz","jtdh","pyfs",
				"zw","zd1","zd2","zd3","zd4","zd5","grjl"};

		// �W���o����Ϣ���ֶ�
		String[] xsfzxxb = { "lxdh1", "jtszd", "jtcy1_xm", "jtcy1_gx",
				"jtcy1_gzdz", "jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm",
				"jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1",
				"jtcy3_xm", "jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2",
				"jtcy3_lxdh1" };
			
		try {
			//�ύ���W����Ϣ��
			dao.updateXsxxb(sqid, xsxxb);
			//�ύ���W���o����Ϣ��
			dao.updateXsfzxxb(sqid, xsfzxxb);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ�����ܲ���
	 * 
	 * @date 2013-01-25
	 * @author ΰ�����
	 */
	public void initParameter() {

		String path = "xsxx_general_xxxg_xgsh.do";

		// �߼���ѯ�Ƿ�����
		boolean isSearch = isExists("xg_search_szb", "path", path);

		if (!isSearch) {
			initSearch();
		}
	}
	
	/**
	 * ��ʼ����ѯ����
	 * 
	 * @date 2013-01-25
	 * @author ΰ�����
	 */
	private void initSearch() {

		List<String[]> params = new ArrayList<String[]>();

		String path = "xsxx_general_xxxg_xgsh.do";
		String[] tj = new String[] { "xh", "xm", "nj", "xy", "zy", "bj",
				"shztTwo" };
		String[] mc = new String[] { "ѧ��", "����", "�꼶", "Ժϵ", "רҵ", "�༶", "���״̬" };
		String[] lx = new String[] { "mhcx", "mhcx", "djcx", "djcx", "djcx",
				"djcx", "djcx" };
		String[] zd = new String[] { "xh", "xm", "nj", "xydm", "zydm", "bjdm",
				"shztmc" };
		String[] ssmk = new String[] { "xsxx", "xsxx", "xsxx", "xsxx", "xsxx",
				"xsxx", "xsxx" };
		String[] xssx = new String[] { "1", "2", "1", "2", "3", "4", "5" };

		for (int i = 0; i < tj.length; i++) {
			String[] value = new String[] { path, tj[i], mc[i], lx[i], zd[i],
					ssmk[i], xssx[i] };
			params.add(value);
		}

		try {
			dao.initSearch(params);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
}
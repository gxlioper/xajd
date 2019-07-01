package xsgzgl.pjpy.general.xmsz.pjtj;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.tjsz.PjpyTjszMethod;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszPjtjService extends CommService implements XmszPjtjInterface {

	XmszPjtjDAO dao = new XmszPjtjDAO();

	//-----------------------������������ begin-------------------------
	
	/**
	 * ��ʼ��������������
	 * 
	 * @author ΰ�����
	 */
	public void defaultPjtjSetting(XmszPjtjModel model, User user,
			HttpServletResponse response) throws IOException {
		
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �����б�
		List<HashMap<String, String>> list = dao.getPjtjList(xmdm);
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		// ���������б�
		List<HashMap<String, String>> pjtjList = model.getPjtjList();

		// �༶�����б�
		List<HashMap<String, String>> bjdlList = model.getBjdlList();
			
		if (list != null && list.size() > 0) {
			
			html.append("<input type=\"hidden\" id=\"hidden_num\" value=\""+list.size()+"\"/>");
			
			for (int i = 0; i < list.size(); i++) {
				
				String tjdm = list.get(i).get("tjdm");// ��������
				String tjz = list.get(i).get("tjz");// ����ֵ
				String tjfw = list.get(i).get("tjfw");// ������Χ
				String pjtjz = list.get(i).get("pjtjz");// ��������ֵ
				String gx = list.get(i).get("gx");// ��ϵ
				String tsgs = list.get(i).get("tsgs");// �����ʽ
				
				html.append("<div id=\"div_tjsz_" + i + "\">");
				html.append("<table width=\"100%\" border=\"0\">");
				html.append("<tr>");
				
				html.append("<td width=\"5%\">");//checkBox
				html.append("<input type=\"checkbox\" name=\"checkVal\" value=\""+i+"\"/>");
				html.append("</td>");

				html.append("<td width=\"40%\">");//����
				html.append("");
				html.append("<select name=\"array_tjdm_sz\" ");
				html.append("onchange=\"defaultPjtjInfo(this.value,'"+i+"')\" ");
				html.append("id=\"select_tjdm_"+i+"\">");
				if (pjtjList != null && pjtjList.size() > 0) {
					for (int j = 0; j < pjtjList.size(); j++) {
						html.append("<option ");
						html.append("value=\"" + pjtjList.get(j).get("tjdm")+ "\" ");
						if(tjdm.equalsIgnoreCase(pjtjList.get(j).get("tjdm"))){
							html.append("selected=\"selected\"");
						}
						html.append(">");
						html.append(pjtjList.get(j).get("tjmc"));
						html.append("</option>");
					}
				}
				html.append("</select>");
				html.append("</td>");

				html.append("<td width=\"10%\">");//��ϵ
				html.append("<select name=\"array_gx_sz\" id=\"select_gx_"+i+"\">");
				
				if(!Base.isNull(pjtjz)){
					html.append("<option value=\"=\">=<option>");
				}else{
					html.append("<option value=\">\" ");
					if(">".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append(">></option>");
					
					html.append("<option value=\">=\" ");
					if(">=".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append(">>=</option>");
					
					html.append("<option value=\"=\" ");
					if("=".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append(">=</option>");
					
					html.append("<option value=\"<=\" ");
					if("<=".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append("><=</option>");
					
					html.append("<option value=\"<\" ");
					if("<".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append("><</option>");
				}
				html.append("</select>");
				html.append("</td>");

				html.append("<td width=\"10%\">");//����ֵ
				html.append("<input type=\"text\" name=\"array_tjz_sz\" ");	
				html.append("onkeydown=\"return onlyNum(this,5)\" ");
				html.append("onmousedown=\"return onlyNum(this,5)\" ");
				html.append("maxlength=\"5\"");
				html.append("style=\"width:40px;ime-mode:disabled\" ");
				html.append("value=\""+tjz+"\" ");
				html.append("id=\"input_tjz_"+i+"\" maxLength=\"10\"/>");
				if(Base.isNull(tsgs)){
					html.append("<span id=\"span_tsgs_"+i+"\"></span>");
				}else{
					html.append("<span id=\"span_tsgs_"+i+"\">"+tsgs+"</span>");
				}
				html.append("</td>");

				html.append("<td width=\"\">");//���÷�Χ����
				html.append("<select name=\"array_tjfw_sz\" id=\"select_tjfw_"+i+"\">");
				html.append("<option value=\"all\" ");
				if("all".equalsIgnoreCase(tjfw)){
					html.append("selected=\"selected\" ");
				}
				html.append(">ȫ��</option>");
				if (bjdlList != null && bjdlList.size() > 0) {
					for (int j = 0; j < bjdlList.size(); j++) {
						html.append("<option ");
						html.append("value=\"" + bjdlList.get(j).get("dm")+ "\" ");
						if(tjfw.equalsIgnoreCase(bjdlList.get(j).get("dm"))){
							html.append("selected=\"selected\"");
						}
						html.append(">");
						html.append(bjdlList.get(j).get("mc"));
						html.append("</option>");
					}
				}
				html.append("</select>");
				html.append("</td>");
				
				html.append("</tr>");
				html.append("</table>");
				html.append("</div>");
			}
		}
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * �������������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getPjtjInfo(XmszPjtjModel model, User user) {

		//��������
		String tjdm = model.getTjdm();
		
		HashMap<String, String> map = dao.getPjtjInfo(tjdm);

		return map;
	}
	
	/**
	 * �����������
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjtjList() {

		List<HashMap<String, String>> list = dao.getPjtjList();

		return list;
	}
	
	/**
	 * ɾ����������
	 * 
	 * @author ΰ�����
	 */
	public Boolean deletePjtj(XmszPjtjModel model, User user) {

		boolean flag = false;

		// ��Ŀ����
		String xmdm = model.getXmdm();

		try {
			flag = dao.deleteTjszb(xmdm, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjtj(XmszPjtjModel model, User user) {

		boolean flag = false;
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String[] tjdm = model.getTjdm_sz();
		// ��Ŀ����
		String[] tjfw =  model.getTjfw_sz();
		// ��Ŀ����
		String[] gx = model.getGx_sz();
		// ��Ŀ����
		String[] tjz = model.getTjz_sz();

		flag = deletePjtj(model, user);

		if (flag) {
			try {
				flag = dao.insertTjszb(xmdm, tjdm, tjfw, gx, tjz, user);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	//-----------------------������������ end-------------------------
	
	/**
	 * ���������������
	 * 
	 * @author ΰ�����
	 */
	public String getPjtjMessage(XmszPjtjModel model, User user) {

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ѧ��
		String[] xh = model.getXh();

		// ���������б�
		List<HashMap<String, String>> pjtjList = dao.getPjtjList(xmdm);

		String message = "";

		if (pjtjList != null && pjtjList.size() > 0) {
			for (int i = 0; i < pjtjList.size(); i++) {
				HashMap<String, String> map = pjtjList.get(i);
				message = getStuPjzg(xh, map);

				if (!Base.isNull(message)) {
					break;
				}
			}
		}

		return message;
	}

	/**
	 * ���ѧ�������ʸ�
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String getStuPjzg(String[] xh, Map<String, String> map) {

		// ��������
		String tjlx = map.get("tjlx");
		// ����˵��
		String tjms = map.get("tjms");
		
		// ���ʸ�����ѧ��
		String noPjzgXh = "";

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// �߼���ϵ
			noPjzgXh = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// �����߼���ϵ
			noPjzgXh = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// ��С�ֹ�ϵ
			noPjzgXh = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// ƽ���ֹ�ϵ
			noPjzgXh = judgeAvgRelation(xh, map);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// ĳ�����ϵ
			noPjzgXh = judgeInstanceReverse(xh, map);
		} else {// ���÷�����������
			try {
				PjpyTjszMethod pjpyTjszMethod = new PjpyTjszMethod();
				Class myClass = pjpyTjszMethod.getClass();
				// map.put("xh", xh);
				noPjzgXh = (String) myClass.getMethod(tjlx,
						(Class[]) new Class[] { HashMap.class }).invoke(
						pjpyTjszMethod, map);
			} catch (Exception e) {
				System.out.println("������������,method:" + tjlx + "��������;");
				e.printStackTrace();
			}
		}

		// ��ʾ��Ϣ
		String message = "";
		
		// �����������Ļ�
		if (!Base.isNull(noPjzgXh)) {
			DAO dao = DAO.getInstance();
			String xm = dao.getOneValue("view_xsjbxx", "xm", "xh", noPjzgXh);
			noPjzgXh = "�������Ŀ��Ҫ��" + tjms + "," + xm + "(" + noPjzgXh
					+ ")����������������";
		}
		
		return message;
	}

	/**
	 * �ж��߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicRelation(String[] xh, Map<String, String> map) {

		String message = "";

		// ����˵��
		String tjms = map.get("tjms");
		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// ������Դ��
		String tablename = map.get("tablename");
		// �ֶ�
		String zd = map.get("zd");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "");
		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);

		return message;
	}

	/**
	 * �жϷ����߼���ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicReverse(String[] xh, Map<String, String> map) {

		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, false);
		
		return noPjzgXh;

	}
	
	/**
	 * �ж�Сֵ��ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeMinRelation(String[] xh, Map<String, String> map) {	

		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "Min");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * �ж�ƽ��ֵ��ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeAvgRelation(String[] xh, Map<String, String> map) {

		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "AVG");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);

		return noPjzgXh;
	}
	
	/**
	 * �ж����ϵ
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String[] xh, Map<String, String> map) {

		// ��ϵ
		String gx = map.get("gx");
		// ����ֵ
		String tjz = map.get("tjz");
		// �����ʽ
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// �Ƚ�ֵ
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "count");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * Ŀ��ֵ��ȷ��ֵ�Ƚ�
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private String compareTo(String[] xh,
			List<HashMap<String, String>> bjzList, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// ���ʸ�ѧ��
		String noPjzgXh = "";

		for (int i = 0; i < bjzList.size(); i++) {

			HashMap<String, String> bjzMap = bjzList.get(i);
			String bjz = bjzMap.get("bjz");

			// �Ƚ�ֵ������ֵ�ǿ�
			if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
				// ��ϵΪ"="
				if ("=".equalsIgnoreCase(gx)) {
					if("��".equalsIgnoreCase(tjz) && !"0".equalsIgnoreCase(bjz)){
						bjz = "��";
					}
					flag = bjz.equalsIgnoreCase(tjz) ? true : false;
				}
				// ��ϵΪ">"
				else if (">".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0 ? true : false;
					}
				}
				// ��ϵΪ">="
				else if (">=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}
				}
				// ��ϵΪ"<"
				else if ("<".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0 ? true : false;
					}

				}
				// ��ϵΪ"<="
				else if ("<=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}

				}
			}

			if (!flag) {
				noPjzgXh = bjzMap.get("xh");
				break;
			}
		}

		return noPjzgXh;
	}

}

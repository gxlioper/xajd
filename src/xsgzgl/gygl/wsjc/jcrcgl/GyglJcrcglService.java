package xsgzgl.gygl.wsjc.jcrcgl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-14 ����03:45:48
 * </p>
 */
public class GyglJcrcglService extends BasicService {

	/**
	 * ���ý����ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = null;
		String[] cn = null;
		
		if (GyglNewInit.WSJC_XJQS){
			if("18180".equals(Base.xxdm)){
				en = new String[] { "", "xn", "xq","jcyf", "mc", "lxmc","kssj", "jssj", "bz", "tjztInfo"};
				cn = new String[] { "", "ѧ��", "ѧ��","����·�", "����", "����","��ʼʱ��", "����ʱ��", "��ע","�ύ״̬" };
			}else{
				en = new String[] { "", "xn", "xq", "mc", "lxmc","kssj", "jssj", "bz", "tjztInfo"};
				cn = new String[] { "", "ѧ��", "ѧ��", "����", "����","��ʼʱ��", "����ʱ��", "��ע","�ύ״̬" };
			}
		} else {
			en = new String[] { "", "xn", "xq", "mc", "kssj", "jssj", "bz", "tjztInfo"};
			cn = new String[] { "", "ѧ��", "ѧ��", "����", "��ʼʱ��", "����ʱ��", "��ע","�ύ״̬" };
		}
		if("12688".equals(Base.xxdm)){
			en = new String[] { "", "xn", "xq", "mc", "lxmc","kssj", "jssj", "bz", "tjztInfo","pfjbmc"};
			cn = new String[] { "", "ѧ��", "ѧ��", "����", "����","��ʼʱ��", "����ʱ��", "��ע","�ύ״̬" ,"����"};
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ʼ�������ѯ��Ϣ
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initJcrcglManage(RequestForm rForm, HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		// ����ģ��
		String gnmk = "gygl";
		// ����·��
		String path = "gzdx_gygl_wsjc_jcrcgl.do";
		// ========================����ֶ� begin=========================
		// �ֶ���
		String[] en = new String[] { "xn", "xq", "mc", "kssj", "jssj", "bz", "tjztInfo" };
		// ������
		String[] cn = new String[] { "xn", "xq", "mc", "kssj", "jssj", "bz", "tjztInfo" };
		// ��ͷ
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================����ֶ� end=========================
		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();
		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);
		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);
		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		// ===============ͨ������ end ============
		rForm.setQtzdz(new String[] {});// ������������
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);
	}

	/**
	 * ��ȡ��������ճ̹�����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJcrcglList(GyglJcrcglForm model) throws Exception {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.getJcrcglList(model);
	}

	/**
	 * �ճ����Ʋ����ظ��ļ��
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException
	 *             , SecurityException, IllegalAccessException,
	 *             InvocationTargetException, NoSuchMethodException
	 */
	public boolean findInfo(BasicModel model, String Mc)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.findInfo(model, Mc);
	}

	/**
	 * ��ֹʱ�䲻���ص��ļ��
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException, SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException
	 */
	public String findQzsj(BasicModel model, String Kssj, String Jssj,String Xn, String Xq, String jclx) 
			throws IllegalArgumentException,SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.findQzsj(model, Kssj, Jssj, Xn, Xq, jclx);
	}

	/**
	 * ���������
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				int len = rs.length;
				if("12688".equals(Base.xxdm))
					len= rs.length-1;
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue' sfwh='"+replaceHtml(rs[len-2])+"' ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
//				if (rs[8] != "") {
//					html.append(" disabled='disabled'");
//				}
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 3) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					if (j > len-4) {
						html.append(replaceHtml(rs[j+2]));
					}else{
						html.append(replaceHtml(rs[j]));
					}
					
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * �������ͳ�ʼ��
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initSave(BasicModel basicModel, HttpServletRequest request) {
		// �����ֶ�
		String[] save = { "xn", "xq","jcyf","mc", "kssj", "jssj", "bz","jclx" };
		// --------------����------------
		basicModel.setTableName("xg_gygl_new_wsjc_jcrcb");
		// --------------��Ҫ�����ֵ--------------------
		HashMap<String, String> valueMap = getValueMap(request, save);
		basicModel.setValueMap(valueMap);
	}

	/**
	 * �������ͳ�ʼ��
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initModi(BasicModel basicModel, HttpServletRequest request) {
		String[] save = { "xn", "xq","jcyf", "mc", "kssj", "jssj", "bz","jclx" };
		String[] pk = { "guid" };
		basicModel.setPk(pk);
		basicModel.setTableName("xg_gygl_new_wsjc_jcrcb");
		HashMap<String, String> valueMap = getValueMap(request, save);
		valueMap.put("guid", request.getParameter("pkValue"));
		basicModel.setValueMap(valueMap);
	}

	/**
	 * ��ѯ������������ճ���Ϣ
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJcrcglMap(GyglJcrcglForm model) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.getJcrcglMap(model);
	}
	
	/**
	 *  ����ճ̹��� �Զ�������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcrcglExportList(GyglJcrcglForm model,User user) throws Exception {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.getJcrcglExportList(model,user);
	}

	/**
	 * @����:�޸ļ���ճ̹���Ϊ�ύ״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-29 ����10:09:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean tjJcrcgl(String pkStr) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		if("10351".equals(Base.xxdm)||"10344".equals(Base.xxdm)||"12424".equals(Base.xxdm)){
			boolean flag = dao.tjJcrcgl(pkStr);
			if(flag){
				List<HashMap<String,String>> xswsf = dao.getXsfs(pkStr);
				flag=dao.bcXsWsf(xswsf);
			}
		   return flag;
		}
		else{
		return dao.tjJcrcgl(pkStr);
		}
	}
	
	/**
	 * 
	 * @����: �ύͬ�����µ�XG_QSFMX���Ϻ�Ϸ����Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-5 ����04:11:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSubmit(String pkStr) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.saveSubmit(pkStr);
		
	}
	
	/**
	 * 
	 * @����: ȡ���ύͬ��ɾ����Ӧ����XG_QSFMX���Ϻ�Ϸ����Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-5 ����04:11:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delCancel(String pkStr) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.delCancel(pkStr);
		
	}
	
	/**
	 * 
	 * @����:ȡ���ύ����ճ̹���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-29 ����02:38:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxtjJcrcgl(String pkStr){
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		if("10351".equals(Base.xxdm)||"10344".equals(Base.xxdm)){
			boolean flag = dao.qxtjJcrcgl(pkStr);
			if(flag){
				flag=dao.scXsWsf(pkStr);
			}
		   return flag;
		}
		else{
		return dao.qxtjJcrcgl(pkStr);
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-10 ����10:17:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateByqsForZjcm(String pkValues) throws Exception{
		String[] pkValue = pkValues.split("!!array!!");
		return new GyglJcrcglDAO().updateByqsForZjcm(pkValue);
	}
	
}
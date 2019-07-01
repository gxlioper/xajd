package xsgzgl.gygl.gypy.gypywh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
/**
 * ��Ԣ����-��Ԣ����-��Ԣ���Ź���
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class GypywhService  extends BasicService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GypywhDao gypywhDao = new GypywhDao();
	
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type,String pydx) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "xqmc", "pylbmc", "ldmc", "chmc", "qsh", "qsxb","xh","xm","pysj" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "�������", "¥������", "���", "���Һ�","�����Ա�","ѧ��","����","����ʱ��" };
		if("pyqs".equalsIgnoreCase(type) && "1".equalsIgnoreCase(pydx)){
			en = new String[] { "", "ldmc", "chmc", "qsh", "ssnj", "ssxy", "cws","rzrs" };
			cn = new String[] { "", "¥��", "���", "���Һ�", "�����꼶", "����"+Base.YXPZXY_KEY,"��λ��","��ס����" };
		}else if("pyqs".equalsIgnoreCase(type) && "0".equalsIgnoreCase(pydx)) {
			en = new String[] { "", "ldmc", "ldxb", "ldcs" };
			cn = new String[] { "", "¥��", "¥���Ա�", "¥������" };
		}else if("pyqs".equalsIgnoreCase(type) && ("2".equalsIgnoreCase(pydx) || "3".equalsIgnoreCase(pydx) || "4".equalsIgnoreCase(pydx))) {
			en = new String[] { "", "ldmc", "chmc", "qsh", "gllx", "xb", "xh","xm","sjhm","qsdh","bz" };
			cn = new String[] { "", "¥��", "���", "���Һ�", "����", "�Ա�","ѧ��","����","��ϵ�绰","����绰","��ע" };
		}
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * ��Ԣ���Ų�ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> gypyCx(GypywhForm model,HttpServletRequest request) throws Exception {
		return gypywhDao.gypyCx(model,request);
	}
	
	/**
	 * ��Ԣ���Ų�ѯ for ����
	 * @param model
	 * @return
	 */
	public List<HashMap<String , String>> gypyCxForDc(GypywhForm model,HttpServletRequest request) throws Exception{
		return gypywhDao.gypyCxForDc(model, request);
	}
	
	/**
	 * �������Ҳ�ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> pyqsCx(GypywhForm model,HttpServletRequest request) throws Exception {
		return gypywhDao.pyqsCx(model,request);
	}
	
	/**
	 * 
	 * @����:����¥����ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-24 ����03:23:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]> pyldCx(GypywhForm model,HttpServletRequest request) throws Exception {
		return gypywhDao.pyldCx(model,request);
	}
	
	public ArrayList<String[]> pyzzCx(GypywhForm model,HttpServletRequest request) throws Exception {
		return gypywhDao.pyzzCx(model,request);
	}
	
	/**
	 * ����ҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	/**
	 * ��ù�Ԣ�����б�
	 * @return
	 */
	public List<HashMap<String,String>> getPylbList() {
		return gypywhDao.getPylbList();
	}


	/**
	 * ���ѧ������
	 * @param xqdm
	 * @return
	 */
	public String getXqmc(String xqdm) {
		return gypywhDao.getXqmc(xqdm);
	}


	/**
	 * ��������������
	 * @param pylbdm
	 * @return
	 */
	public String getPylbmc(String pylbdm) {
		return gypywhDao.getPylbmc(pylbdm);
	}

	
	/**
	 * ��Ԣ��������
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String gypyZj(GypywhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		String xn = model.getXn();
		String xq = model.getXqdm();
		String pylbdm = model.getPylbdm();
		String pysj = model.getPysj();
		String pydx = model.getPydx();
		List<String[]> params = new ArrayList<String[]>();
		if("0".equalsIgnoreCase(pydx)){
			for(int i = 0; i < pkValue.length; i++){
				String[] pkV = pkValue[i].split("!!@@!!");
				String[] input = {xn,xq,pylbdm,pysj,pkV[0],"","",""};
				params.add(input);
			}
		}else if("1".equalsIgnoreCase(pydx)) {
			for(int i = 0; i < pkValue.length; i++){
				String[] pkV = pkValue[i].split("!!@@!!");
				String[] input = {xn,xq,pylbdm,pysj,pkV[0],pkV[1],"",""};
				params.add(input);
			}
		}else if("2".equalsIgnoreCase(pydx) || "3".equalsIgnoreCase(pydx) || "4".equalsIgnoreCase(pydx)) {
			for(int i = 0; i < pkValue.length; i++){
				String[] pkV = pkValue[i].split("!!@@!!");
				String[] input = {xn,xq,pylbdm,pysj,pkV[0],pkV[2],pkV[3],pkV[1]};
				params.add(input);
			}
			
		}
		return gypywhDao.gypyZj(params)? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}

	/**
	 * ��Ԣ��������
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String gypySc(GypywhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] input = {pkValue[i]};
			params.add(input);
		}
		return gypywhDao.gypySc(params)? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}







}

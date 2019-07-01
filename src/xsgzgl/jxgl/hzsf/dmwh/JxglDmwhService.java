package xsgzgl.jxgl.hzsf.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * ��ѵ����-��������-����ά��
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhService extends BasicService{
	JxglDmwhDAO dao = new JxglDmwhDAO();
	/**
	 * ��ñ�ͷ
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "grrydm","grrymc" };
		String[] cn = new String[] { "", "�к�", "������������", "������������" };
		if("tdry".equalsIgnoreCase(type)){
			en = new String[] { "", "r", "tdrydm","tdrymc" };
			cn = new String[] { "", "�к�", "�Ŷ���������", "�Ŷ���������" };
		}
		return dao.arrayToList(en, cn);
	}
	/**
	 * �ṩ��������ʹ�õĽӿڷ�����ø�������List
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getGrrydmList() throws Exception{
		return dao.getGrrydmList();
	}
	/**
	 * �ṩ��������ʹ�õĽӿڷ�������Ŷ�����List
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getTdrydmList() throws Exception{
		return dao.getTdrydmList();
	}
	/**
	 * ��ø�������List
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getGrryList(JxglDmwhForm model) throws Exception {
		return dao.getGrryList(model);
	}
	/**
	 * ����Ŷ�����List
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getTdryList(JxglDmwhForm model) throws Exception {
		return dao.getTdryList(model);
	}
	/**
	 * ������������
	 * @param model
	 * @param doType
	 * @return
	 * @throws Exception 
	 */
	public String grryBc(JxglDmwhForm model, String doType) throws Exception {
		if("add".equalsIgnoreCase(doType)){
			model.setGrrydm(changeGrrydm());
		}
		if(isGrryExist(model,doType)){
			return "�ø������������Ѵ���";
		}
		return dao.grryBc(model,doType)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * ��֤�������������Ƿ��ѱ�ʹ��
	 * @param grrymc
	 * @return
	 */
	private boolean isGrryExist(JxglDmwhForm model,String doType) {
		return dao.isGrryExist(model,doType);
	}
	/**
	 * ��ø�����������
	 * @return
	 */
	private String changeGrrydm() {
		String max = dao.getMaxGrrydm();
		if(max==null){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	/**
	 * �Ŷ���������
	 * @param model
	 * @param doType
	 * @return
	 * @throws Exception 
	 */
	public String tdryBc(JxglDmwhForm model, String doType) throws Exception {
		if("add".equalsIgnoreCase(doType)){
			model.setTdrydm(changeTdrydm());
		}
		if(isTdryExist(model,doType)){
			return "���Ŷ����������Ѵ���";
		}
		return dao.tdryBc(model,doType)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * ��֤�Ŷ����������Ƿ��ѱ�ʹ��
	 * @param grrymc
	 * @return
	 */
	private boolean isTdryExist(JxglDmwhForm model,String doType) {
		return dao.isTdryExist(model,doType);
	}
	/**
	 * ����Ŷ���������
	 * @return
	 */
	private String changeTdrydm() {
		String max = dao.getMaxTdrydm();
		if(max==null){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	/**
	 * ��������ɾ��
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public String grrySc(JxglDmwhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		return dao.grrySc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	/**
	 * �Ŷ���Աɾ��
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public String tdrySc(JxglDmwhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		return dao.tdrySc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	/**
	 * ��֤����������Ϣ
	 * @param model
	 * @return
	 */
	public String checkGrry(JxglDmwhForm model) {
		String message = "true";
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0;i < pkValue.length;i++){
			//�ж��Ƿ��ѱ�ʹ��
			if(isGrryUsed(pkValue[i])){
				return "���������ѱ�ʹ��,���ܱ��޸Ļ�ɾ��";
			}
		}
		return message;
	}
	/**
	 * �жϸ��������Ƿ��ѱ�ʹ��
	 * @param pkValue
	 * @return
	 */
	private boolean isGrryUsed(String pkValue) {
		return dao.isGrryUsed(pkValue);
	}
	/**
	 * ��֤�Ŷ�������Ϣ
	 * @param model
	 * @return
	 */
	public String checkTdry(JxglDmwhForm model) {
		String message = "true";
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0;i < pkValue.length;i++){
			//�ж��Ƿ��ѱ�ʹ��
			if(isTdryUsed(pkValue[i])){
				return "�Ŷ������ѱ�ʹ��,���ܱ��޸Ļ�ɾ��";
			}
		}
		return message;
	}
	/**
	 * �ж��Ŷ������Ƿ��ѱ�ʹ��
	 * @param pkValue
	 * @return
	 */
	private boolean isTdryUsed(String pkValue) {
		return dao.isTdryUsed(pkValue);
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
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
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


}

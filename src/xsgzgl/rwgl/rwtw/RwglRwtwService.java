package xsgzgl.rwgl.rwtw;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

public class RwglRwtwService extends BasicService{
	RwglRwtwDao rwglRwtwDao = new RwglRwtwDao(); 

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	
	/**
	 * ��ñ�ͷ
	 * @param string
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "", "xh", "xm", "nj", "xymc", "zymc","bjmc","rwsj","rwd","rwfsmc"};
		String[] cn = new String[] { "", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY,"רҵ","�༶","����ʱ��","�����","���鷽ʽ"};
		if("zxxs".equalsIgnoreCase(type)){
			en = new String[] { "","xh", "xb", "xm", "nj", "xymc", "zymc", "bjmc" };
			cn = new String[] { "","ѧ��", "�Ա�", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶"};
		}else if("twdj".equalsIgnoreCase(type)){
			en = new String[] { "", "xh", "xm", "nj", "xymc","bjmc","rwsj","rwfsmc","twsj" };
			cn = new String[] { "", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY,"�༶","����ʱ��","���鷽ʽ","����ʱ��"};
		}else if("rwxs".equalsIgnoreCase(type)){
			en = new String[] { "","xh", "xb", "xm", "nj", "xymc", "zymc", "bjmc", "rwsj"};
			cn = new String[] { "","ѧ��", "�Ա�", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "����ʱ��"};
		}
		return rwglRwtwDao.arrayToList(en, cn);
	}

	/**
	 * 
	 * @����:��ѯ���鷽ʽ�б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-3 ����11:44:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> rwfsList() throws Exception{
		return rwglRwtwDao.rwfsList();
	}
	
	/**
	 * ��ѯ����;���б�
	 */
	public List<HashMap<String , String>> rwtjList() throws Exception{
		return rwglRwtwDao.rwtjList();
	}
	
	/**
	 * ����Ǽǲ�ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> rwdjCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		return rwglRwtwDao.rwdjCx(model,request);
	}
	
	
	/**
	 * ��Уѧ����ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> zxxsCx(RwglRwtwForm model,HttpServletRequest request) throws Exception {
		return rwglRwtwDao.zxxsCx(model,request);
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
				for (int j = 1; j <= rs.length-1; j++) {
					if(j==rs.length-2){
						html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" title=\""+replaceHtml(rs[j])+"\"");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(replaceHtml(rs[j]));
						html.append("</td>");
					}else{
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
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * ����ҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByTw(SearchRsModel rsModel,
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
	 * ����ҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByXsxx(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"sendXx();\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='hidden' name='div_pkValue'  ");
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
	 * �����Ϣ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String rwdjBc(RwglRwtwForm model) throws Exception {
		String doType = model.getDoType();
		boolean flag = false;
		if("add".equalsIgnoreCase(doType)){
			String count = rwglRwtwDao.rwdjYz(model);
			if(!"0".equalsIgnoreCase(count)){
				return "�Ѵ��ڸ�ѧ����������Ϣ";
			}
		}
		flag = rwglRwtwDao.rwdjBc(model);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}


	/**
	 * ѧ����ϸ��Ϣ
	 * @param modelt
	 * @return
	 */
	public HashMap<String, String> xsxxCk(RwglRwtwForm model) {
		return rwglRwtwDao.xsxxCk(model);
	}


	/**
	 * ����Ǽ�ɾ��
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String rwdjSc(RwglRwtwForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return rwglRwtwDao.rwdjSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}


	/**
	 * ���������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getRwxx(RwglRwtwForm model) {
		return rwglRwtwDao.getRwxx(model);
	}


	/**
	 * ����Ǽǲ�ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> twdjCx(RwglRwtwForm model,HttpServletRequest request)throws Exception{
		return rwglRwtwDao.twdjCx(model,request);
	}


	/**
	 * ����ѧ����ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> rwxsCx(RwglRwtwForm model,HttpServletRequest request) throws Exception {
		return rwglRwtwDao.rwxsCx(model,request);
	}


	/**
	 * ����ǼǱ���
	 * @param model
	 * @return
	 */
	public String twdjBc(RwglRwtwForm model) throws Exception {
		String doType = model.getDoType();
		boolean flag = false;
		if("add".equalsIgnoreCase(doType)){
			String count = rwglRwtwDao.twdjYz(model);
			if(!"0".equalsIgnoreCase(count)){
				return "�Ѵ��ڸ�ѧ����������Ϣ";
			}
		}
		flag = rwglRwtwDao.twdjBc(model);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}


	/**
	 * ����Ǽ�ɾ��
	 * @param model
	 * @return
	 */
	public String twdjSc(RwglRwtwForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return rwglRwtwDao.twdjSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}

	/**
	 * ����Ǽǹ����Զ��嵼��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> rwdjExportCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		return rwglRwtwDao.rwdjExportCx(model,request);
	}
	
	/**
	 * ����Ǽǹ����Զ��嵼��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> twdjExportCx(RwglRwtwForm model,HttpServletRequest request)throws Exception{
		return rwglRwtwDao.twdjExportCx(model,request);
	}

}

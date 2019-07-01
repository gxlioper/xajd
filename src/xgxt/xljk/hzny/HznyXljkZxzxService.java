package xgxt.xljk.hzny;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-4 ����04:35:02</p>
 */
public class HznyXljkZxzxService extends BasicService{

	HznyXljkZxzxDAO dao=new HznyXljkZxzxDAO();
	
	/**
	 * ��ȡ��ѯʦ�����ѯ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxsglList(BasicModel model) throws Exception{
		
		return dao.getZxsglList(model);
	}
	
	/**
	 * ���������
	 * 
	 * @author qlj
	 */
	public String createZxsglHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < 6; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					
					if(j==5){
						html.append(" title=\""+rs[6]+"\"");
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
	 * ��ȡ��ѯʦ�����ѯ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTsxsglList(BasicModel model) throws Exception{
		
		return dao.getTsxsglList(model);
	}
	
	/**
	 * ���������
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					
//					if(j==rs.length-1 && rs[j].equalsIgnoreCase("��ϸ")){
//					
//						html.append("<a href=\"#\" onclick=\"showDetailDiv('"+rs[0]+"');return false;\" ><font color=\"blue\">��ϸ</font></a>");
//					
//					}else{
						
						html.append(replaceHtml(rs[j]));
//					}
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getTeaInfo(String zgh){
		
		
		return dao.getTeaInfo(zgh);
	}
	
	/**
	 * ��ȡ��Ҫ�ر����ѧ�������Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getTbgxxslb(){
		
		return dao.getTbgxxslb();
	}
	
	
	/**
	 * ��ȡ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getTsxsInfo(String xh){
		
		return dao.getTsxsInfo(xh);
	}

	/**
	 * ��ȡ������ѯ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxzxglList(BasicModel model) throws Exception{
		
		return dao.getZxzxglList(model);
	}
	

	/**
	 * ���������(������ѯ)
	 * 
	 * @author qlj
	 */
	public String createZxzxHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();
		
		String userType=user.getUserType();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				if("stu".equalsIgnoreCase(userType)){
					for (int j = 1; j < 6; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						
						if(j==4){
							html.append(" title=\""+rs[6]+"\"");
						}
						
						html.append(">");
						
						html.append(replaceHtml(rs[j]));
	
						html.append("</td>");
					}
				}else{
					
					for (int j = 1; j < 9; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						
						if(j==6){
							html.append(" title=\""+rs[9]+"\"");
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
	 * ��ȡѧ��������ѯ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getZxzxInfo(String guid){
		
		return dao.getZxzxInfo(guid);
	}
	
	/**
	 * ��ȡѧ��������ѯ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean checkZxzx(String guid){
		
		Map<String,String>zxzxInfo=dao.getZxzxInfo(guid);

		if(zxzxInfo==null || zxzxInfo.size()==0){
			
			return false;
		
		}else if(!Base.isNull(zxzxInfo.get("zgh"))){
				
			return false;	
		}
		
		return true;
	}

	/**
	 * ��ȡѧ��������ѯ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean checkZxzx(String[] guid){
		
		List<HashMap<String,String>>zxzxInfo=dao.getZxzxInfo(guid);

		if(zxzxInfo==null || zxzxInfo.size()==0){
			
			return true;
		
		}
		return false;
	}
	
	/**
	 * �ж��û��Ƿ���������ѯʦ
	 * @param model
	 * @return
	 */
	public boolean checkZxs(String zgh){
		
		HashMap<String,String>teaInfo=dao.getTeaInfo(zgh);

		if(teaInfo==null || teaInfo.size()==0){
			
			return false;
		
		}
		return true;
	}
	
	/**
	 * ��ȡ������ѯ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszxList(BasicModel model) throws Exception{
		
		return dao.getXszxList(model);
	}
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getXlzxsInfo(String zgh){
		
	
		return dao.getXlzxsInfo(zgh);
	}
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getXszxInfo(String guid){
		
		return dao.getXszxInfo(guid);
	}
	
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean checkXszx(String guid){
		
		Map<String,String>xszxInfo= dao.getXszxInfo(guid);
		
		if(xszxInfo==null || xszxInfo.size()==0){
			
			return false;
			
		}else if(!Base.isNull(xszxInfo.get("fksj"))){
			
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * ��ȡѧ��������ѯ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean checkXszx(String[] guid){
		
		List<HashMap<String,String>>xszxInfo=dao.getXszxInfo(guid);

		if(xszxInfo==null || xszxInfo.size()==0){
			
			return true;
		
		}
		return false;
	}

	/**
	 * ��ȡ��ѯʦ�����ѯ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxjlList(BasicModel model) throws Exception{
		
		return dao.getZxjlList(model);
	}
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getZxjlInfo(String guid){
		
		return dao.getZxjlInfo(guid);
	}
}

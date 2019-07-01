package xsgzgl.xsxx.qtxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XsxxQtxxService extends BasicService{
	
	XsxxQtxxDAO dao=new XsxxQtxxDAO();
	
	/**
	 * �߼���ѯ��ʽ��ȡ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsQtxxList(BasicModel model) throws Exception{
		
		return dao.getXsQtxxList(model);
	}
	
	/**
	 * ������ѯ��ʽ��ȡ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>basicQueryList(BasicModel model,Object obj) throws Exception{
		
		return dao.getMakeQueryList(model,obj);
	}
	
	/**
	 * ��ȡ��ͷ
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(BasicModel model,User user){
		
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"","xh","xm","nj","xymc","zymc","bjmc"};
		
		String[]cn=new String[]{"","ѧ��","����","�꼶","ѧԺ","רҵ","�༶"};
		
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ���������(�ۺϲ���_�۲���Ϣ)
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
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
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
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ�����޸�
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
			
		return dao.updateInfo(model);
	} 
	
	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model){
		
		return dao.saveInfo(model);
	} 
	
	public HashMap<String,String>getQtxxDetail(XsxxQtxxForm myForm){
		
		return dao.getQtxxDetail(myForm);
		
	}
	
	/**
	 * ����ɾ������
	 * @param model
	 * @return
	 */
	public boolean batchDelete(BasicModel model){
		
		return dao.batchDelete(model);
	}
}

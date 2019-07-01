package xgxt.xszz.whtl.ybbx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XszzYbbxService extends BasicService {
	
	XszzYbbxDAO dao= new XszzYbbxDAO();
	/**
	 * ͨ�ò�ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getYbbxcxList(BasicModel model) throws Exception{

		return dao.getYbbxcxList(model);
	}
	
	/**
	 * ͨ�ò�ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getYbbxshList(BasicModel model) throws Exception{

		return dao.getYbbxshList(model);
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
	
	public boolean plsh(BasicModel model) throws Exception{

		boolean flag=dao.batchUpdate(model);
		
		String userType=model.getUser().getUserType();
		
		if(flag){
			
			if("xy".equalsIgnoreCase(userType)){
				flag=dao.updateYbbx(model);
			}
			
		}
		
		return flag;
	
	}
	
	public boolean checkInfo(BasicModel model){

		HashMap<String,String>map= dao.checkInfo(model);
		
		String num=map.get("num");
		
		if("0".equalsIgnoreCase(num)){
			return true;
		}
		
		return false;
	
	}
	
	
	public HashMap<String,String>getYbbxSqbInfo(XszzYbbxForm myForm){
		
		return dao.getYbbxSqbInfo(myForm);
		
	}
	
	public boolean batchDelete(BasicModel model){
		
		return dao.batchDelete(model);
	}
	
	public boolean deleteYbbxInfo() throws Exception{
		
		return dao.deleteYbbxInfo();
	}
	
	public boolean countXh(String xh){
		
		String num=dao.countXh(xh);
		
		if("0".equalsIgnoreCase(num)){
			return false;
		}
		return true;
	}
}

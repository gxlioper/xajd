package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.xszz.whtl.ybbx.XszzYbbxService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-14 ����03:45:48</p>
 */
public class PjpyQtxxService extends BasicService{
	
	PjpyQtxxDAO dao=new PjpyQtxxDAO();
	
	/**
	 * ���ý����ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(){
		
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","hjjls"};
		
		String[]cn=new String[]{"","ѧ��","ѧ��","����","�꼶","�༶","����"};
		
		return  dao.arrayToList(en, cn);
	}
	
	
	/**
	 * ��ȡ����������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getQtxxList(PjpyQtxxForm model) throws Exception{
		
		return dao.getQtxxList(model);
	}
	
	/**
	 * ���������
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			List<String[]> rsArrList, User user) {
	
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
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-2)+"%\" ");
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
	 * �޸���Ϣ��ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initModi(BasicModel basicModel,
			HttpServletRequest request) {

		String []save={"xn","xh","zzmc","zzjb","gbmc","bz"};
		String []pk={"xn","xh","zzmc","zzjb","gbmc"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_szdw_txgbb");
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
//	
//	public HashMap<String,String>getTxgbMap(PjpyQtxxForm model){
//		
//		return dao.getTxgbMap(model);
//		
//	}
	
}

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
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-14 下午03:45:48</p>
 */
public class PjpyQtxxService extends BasicService{
	
	PjpyQtxxDAO dao=new PjpyQtxxDAO();
	
	/**
	 * 设置结果查询表头
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(){
		
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","hjjls"};
		
		String[]cn=new String[]{"","学年","学号","姓名","年级","班级","获奖数"};
		
		return  dao.arrayToList(en, cn);
	}
	
	
	/**
	 * 获取评奖其他信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getQtxxList(PjpyQtxxForm model) throws Exception{
		
		return dao.getQtxxList(model);
	}
	
	/**
	 * 构建结果集
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
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
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
	 * 修改信息初始化
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

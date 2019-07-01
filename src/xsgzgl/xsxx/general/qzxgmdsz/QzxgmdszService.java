package xsgzgl.xsxx.general.qzxgmdsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;

public class QzxgmdszService extends CommService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	private QzxgmdszDao dao = new QzxgmdszDao();
	
	/**
	 *构建表头
	 * @param model
	 * @return
	 *
	 */
	public List<HashMap<String, String>> getQzxgmdTop(QzxgmdszForm model, User user) {
		String[] en = new String[] {"pk" ,"xh", "xm", "nj","xymc","zymc","bjmc","xgztmc", "shzt"};
		Base.YXPZXY_KEY = message.getMessage("lable.xy");
		String[] cn = new String[] { "","学号", "姓名", "年级",Base.YXPZXY_KEY,"专业","班级","修改状态","审核状态" };
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}
	
	
	/**
	 *查询列表
	 * @param model
	 * @return
	 *
	 */
	public ArrayList<String[]> getQzxgmdList(QzxgmdszForm myForm,
			QzxgmdszForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getQzxgmdList(myForm, model,user);
	}
	
	/**
	 *构建html
	 * @param model
	 * @return
	 *
	 */
	public String createQzxgmdHTML(SearchRsModel rsModel,
			QzxgmdszForm model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:5%\">");
				
				html.append("<input type='checkbox' name='primarykey_checkVal' ");
				
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if(j==1){
						html.append(" style=\"width:15%\"");
					}
					if(j==2){
						html.append(" style=\"width:10%\"");
					}
					if(j==3){
						html.append(" style=\"width:10%\"");
					}
					if(j==4){
						html.append(" style=\"width:15%\"");
					}
					if(j==5){
						html.append(" style=\"width:15%\"");
					}if(j==6){
						html.append(" style=\"width:15%\"");
					}if(j==7){
						html.append(" style=\"width:15%\"");
					} 
					html.append(">");
					
					html.append(rs[j]);
					
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	public String checkQzxg(QzxgmdszForm model,User user) throws Exception{
		return dao.checkQzxg(model,user);
	}
	
	/**
	 *设置强制名单
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean szQzxg(QzxgmdszForm model,User user) throws Exception {
		boolean flag = true;
		flag= dao.qxQzxg(model,user);
		flag =  dao.szQzxg(model,user);
		return flag;
	}
	
	/**
	 *设置强制名单
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean qxQzxg(QzxgmdszForm model,User user) throws Exception {
		boolean flag = true;
		flag= dao.qxQzxg(model,user);
		return flag;
	}
	
	/**
	 * 判断学生是否强制修改
	 * @param xh
	 * @return
	 */
	public boolean Sfqzxg(String xh){
		return dao.Sfqzxg(xh);
	}
	
	/**
	 * 修改学生是否强制修改状态
	 * @param xh
	 * @return
	 */
	public boolean xgQzxgzt(String xh) throws Exception{
		return dao.xgQzxgzt(xh);
	}
	/**
	 * 
	 * @描述:强制修改名单导出查询列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-25 下午02:22:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQzxgmdListForDc(QzxgmdszForm t,User user) 
	throws IllegalArgumentException, SecurityException, IllegalAccessException, 
	InvocationTargetException, NoSuchMethodException{
		return dao.getQzxgmdListForDc(t, user);
	}
	
}

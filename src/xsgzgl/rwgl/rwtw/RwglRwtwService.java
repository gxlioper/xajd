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
	 * 获得表头
	 * @param string
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "", "xh", "xm", "nj", "xymc", "zymc","bjmc","rwsj","rwd","rwfsmc"};
		String[] cn = new String[] { "", "学号", "姓名", "年级", Base.YXPZXY_KEY,"专业","班级","入伍时间","入伍地","入伍方式"};
		if("zxxs".equalsIgnoreCase(type)){
			en = new String[] { "","xh", "xb", "xm", "nj", "xymc", "zymc", "bjmc" };
			cn = new String[] { "","学号", "性别", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级"};
		}else if("twdj".equalsIgnoreCase(type)){
			en = new String[] { "", "xh", "xm", "nj", "xymc","bjmc","rwsj","rwfsmc","twsj" };
			cn = new String[] { "", "学号", "姓名", "年级", Base.YXPZXY_KEY,"班级","入伍时间","入伍方式","退伍时间"};
		}else if("rwxs".equalsIgnoreCase(type)){
			en = new String[] { "","xh", "xb", "xm", "nj", "xymc", "zymc", "bjmc", "rwsj"};
			cn = new String[] { "","学号", "性别", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级", "入伍时间"};
		}
		return rwglRwtwDao.arrayToList(en, cn);
	}

	/**
	 * 
	 * @描述:查询入伍方式列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-3 上午11:44:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> rwfsList() throws Exception{
		return rwglRwtwDao.rwfsList();
	}
	
	/**
	 * 查询入伍途径列表
	 */
	public List<HashMap<String , String>> rwtjList() throws Exception{
		return rwglRwtwDao.rwtjList();
	}
	
	/**
	 * 入伍登记查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> rwdjCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		return rwglRwtwDao.rwdjCx(model,request);
	}
	
	
	/**
	 * 在校学生查询
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> zxxsCx(RwglRwtwForm model,HttpServletRequest request) throws Exception {
		return rwglRwtwDao.zxxsCx(model,request);
	}

	
	/**
	 * 创建页面
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
				// --------------------构建HTML扩展字段与分数除外------------------------
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
	 * 创建页面
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
				// --------------------构建HTML扩展字段与分数除外------------------------
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
	 * 创建页面
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
				// --------------------构建HTML扩展字段与分数除外------------------------
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
	 * 民兵信息保存
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
				return "已存在该学生的入伍信息";
			}
		}
		flag = rwglRwtwDao.rwdjBc(model);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}


	/**
	 * 学生详细信息
	 * @param modelt
	 * @return
	 */
	public HashMap<String, String> xsxxCk(RwglRwtwForm model) {
		return rwglRwtwDao.xsxxCk(model);
	}


	/**
	 * 入伍登记删除
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
	 * 获得入伍信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getRwxx(RwglRwtwForm model) {
		return rwglRwtwDao.getRwxx(model);
	}


	/**
	 * 退伍登记查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> twdjCx(RwglRwtwForm model,HttpServletRequest request)throws Exception{
		return rwglRwtwDao.twdjCx(model,request);
	}


	/**
	 * 入伍学生查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> rwxsCx(RwglRwtwForm model,HttpServletRequest request) throws Exception {
		return rwglRwtwDao.rwxsCx(model,request);
	}


	/**
	 * 退伍登记保存
	 * @param model
	 * @return
	 */
	public String twdjBc(RwglRwtwForm model) throws Exception {
		String doType = model.getDoType();
		boolean flag = false;
		if("add".equalsIgnoreCase(doType)){
			String count = rwglRwtwDao.twdjYz(model);
			if(!"0".equalsIgnoreCase(count)){
				return "已存在该学生的退伍信息";
			}
		}
		flag = rwglRwtwDao.twdjBc(model);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}


	/**
	 * 退伍登记删除
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
	 * 退伍登记管理自定义导出
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> rwdjExportCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		return rwglRwtwDao.rwdjExportCx(model,request);
	}
	
	/**
	 * 退伍登记管理自定义导出
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> twdjExportCx(RwglRwtwForm model,HttpServletRequest request)throws Exception{
		return rwglRwtwDao.twdjExportCx(model,request);
	}

}

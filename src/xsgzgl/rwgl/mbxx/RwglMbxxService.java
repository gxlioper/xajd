package xsgzgl.rwgl.mbxx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

public class RwglMbxxService  extends BasicService{
	RwglMbxxDao rwglMbxxDao = new RwglMbxxDao(); 

	
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		String[] en = new String[] { "", "xm", "xb", "zw", "csrq", "rdsj","zzmmmc","sftwjr"};
		String[] cn = new String[] { "", "姓名", "性别", "职务", "出生日期","入队时间","政治面貌","是否转业退伍军人"};
		return rwglMbxxDao.arrayToList(en, cn);
	}

	
	/**
	 * 民兵信息查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> mbxxCx(RwglMbxxForm model) throws Exception{
		return rwglMbxxDao.mbxxCx(model);
	}

	
	/**
	 * 创建页面HTML
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
	 * 获得政治面貌LIST
	 * @return
	 */
	public List<HashMap<String, String>> getZzmmList() {
		return rwglMbxxDao.getZzmmList();
	}
	
	
	/**
	 * 民兵信息保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String mbxxBc(RwglMbxxForm model) throws Exception {
		String doType = model.getDoType();
		boolean flag = false;
		if("add".equalsIgnoreCase(doType)){
			String count = rwglMbxxDao.mbxxYz(model);
			if(!"0".equalsIgnoreCase(count)){
				return "存在相同信息的身份证号";
			}
		}
		flag = rwglMbxxDao.mbxxBc(model);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}


	/**
	 * 民兵信息删除
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String mbxxSc(RwglMbxxForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return rwglMbxxDao.mbxxSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}


	/**
	 * 获得一条民兵信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getMbxx(RwglMbxxForm model) {
		return rwglMbxxDao.getMbxx(model);
	}




}

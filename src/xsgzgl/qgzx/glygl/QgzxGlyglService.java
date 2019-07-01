package xsgzgl.qgzx.glygl;

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
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxGlyglService extends BasicService {
	
	
	public QgzxGlyglDAO qgzxGlyglDAO = new QgzxGlyglDAO();
	
	
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "yhm","xm","ssz","szbm" };
		String[] cn = new String[] { "", "行号", "用户名", "姓名","所属组","所在部门" };
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * 是否勤工管理员
	 * @param yhm
	 * @return
	 */
	public boolean sfQggly(String yhm){
		return qgzxGlyglDAO.sfQggly(yhm);
	}
	
	
	/**
	 * 获得管理员list
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public ArrayList<String[]> getGlyList(QgzxGlyglForm model) throws Exception {
		return qgzxGlyglDAO.getGlyList(model);
	}
	
	
	/**
	 * 获得用户list
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getYhList(QgzxGlyglForm model) throws Exception {
		return qgzxGlyglDAO.getYhList(model);
	}
	
	
	/**
	 * 管理员增加保存
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String glyZjbc(QgzxGlyglForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		boolean flag = qgzxGlyglDAO.glyZjbc(params);
		return flag?"true" : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 管理员批量删除
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String glySc(QgzxGlyglForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		boolean flag = qgzxGlyglDAO.glySc(params);
		return flag?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
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


}

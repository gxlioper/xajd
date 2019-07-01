package xsgzgl.qgzx.jcdmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 勤工助学-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJcdmwhService extends SuperServiceImpl<QgzxJcdmwhForm,QgzxJcdmwhDAO>{
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "gwxzdm","gwxzmc" };
		String[] cn = new String[] { "", "行号", "岗位性质代码", "岗位性质名称" };
		if("yrdw".equalsIgnoreCase(type)){
			en = new String[]{"","r","yrdwdm","yrdwmc"};
			cn = new String[]{"","行号","用人单位代码","用人单位名称"};
		}
		return dao.arrayToList(en, cn);
	}
	/**
	 * 提供其他程序使用的接口方法获得岗位性质信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGwxzdmList() throws Exception{
		return dao.getGwxzdmList();
	}
	/**
	 * 获得岗位性质list
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxzList(QgzxJcdmwhForm model) throws Exception{
		return dao.getGwxzList(model);
	}
	/**
	 * 保存或者修改岗位性质
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String gwxzBc(QgzxJcdmwhForm model, String type) throws Exception {
		if("add".equalsIgnoreCase(type)){
			model.setGwxzdm(changeGwxz());
		}
		if(isExist(model,type)){
			return "该岗位类别名称已存在!";
		}
		boolean b=dao.gwxzBc(model,type);
		return b?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 验证修改删除信息
	 * @param model
	 * @return
	 */
	public String checkScInfo(QgzxJcdmwhForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0;i < pkValue.length;i++){
			//判断是否已被使用
			if(isUsed(pkValue[i])){
				return "该岗位性质已被使用,不能被修改或删除!";
			}
		}
		return "true";
	}
	/**
	 * 岗位性质删除
	 * @param myForm
	 * @return
	 * @throws SQLException
	 */
	public String gwxzSc(QgzxJcdmwhForm model) throws SQLException {
		String[] pkValue = model.getGwxzdm().split(",");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		return dao.gwxzSc(params)?"删除成功!":"删除失败!";
	}
	/**
	 * 验证是否被使用
	 * @param model
	 * @param type
	 * @return
	 */
	public boolean isUsed(String pkValue) {
		return dao.isUsed(pkValue);
	}
	/**
	 * 验证是否已存在
	 * @param model
	 * @param type
	 * @return
	 */
	private boolean isExist(QgzxJcdmwhForm model, String type) {
		return dao.isExist(model,type);
	}
	/**
	 * 获得最大的代码序号再加1
	 * @return
	 */
	private String changeGwxz() {
		String max = dao.getMaxGwxzdm();
		if(Base.isNull(max)){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}


	/**
	 * 获得用人单位列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getYrdwList(QgzxJcdmwhForm model) throws Exception {
		return dao.getYrdwList(model);
	}


	/**
	 * 保存或者修改用人单位
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String yrdwBc(QgzxJcdmwhForm model, String type) throws Exception {
		if("add".equalsIgnoreCase(type)){
			model.setYrdwdm(changeYrdw());
		}
		if(isYrdwExist(model,type)){
			return "该用人单位名称已存在!";
		}
		boolean b=dao.yrdwBc(model,type);
		return b?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}




	/**
	 * 获得最大的代码序号再加1
	 * @return
	 */
	private String changeYrdw() {
		String max = dao.getMaxYrdwdm();
		if(Base.isNull(max)){
			return "xw001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "xw";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}

	/**
	 * 验证是否已存在
	 * @param model
	 * @param type
	 * @return
	 */
	private boolean isYrdwExist(QgzxJcdmwhForm model, String type) {
		return dao.isYrdwExist(model,type);
	}


	/**
	 * 用人单位删除
	 * @param myForm
	 * @return
	 * @throws SQLException
	 */
	public String yrdwSc(QgzxJcdmwhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		return dao.yrdwSc(params)?"删除成功!":"删除失败!";
	}

	/**
	 * 验证修改删除信息
	 * @param model
	 * @return
	 */
	public String checkyrdwScInfo(QgzxJcdmwhForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0;i < pkValue.length;i++){
			//判断是否已被使用
			if(isyrdwUsed(pkValue[i])){
				return "该岗位性质已被使用,不能被修改或删除!";
			}
		}
		return "true";
	}


	/**
	 * 验证是否被使用
	 * @param model
	 * @param type
	 * @return
	 */
	public boolean isyrdwUsed(String pkValue) {
		return dao.isyrdwUsed(pkValue);
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
		/*String ie = rsModel.getIe();
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
		}*/
		return html.toString();
	}

	/**
	 * 岗位性质维护自定义导出
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxzExportDataList(QgzxJcdmwhForm model) throws Exception{
		return dao.getGwxzExportDataList(model);
	}

	/**
	 * 用人单位维护自定义导出
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwExportDataList(QgzxJcdmwhForm model) throws Exception {
		return dao.getYrdwExportDataList(model);
	}


    public List<HashMap<String, String>> getAllTeacher(QgzxJcdmwhForm t) throws Exception{
        return dao.getAllTeacher(t);
    }

    public HashMap<String,String> getData(String pk){
        return dao.getData(pk);
    }

    public boolean mmcsh(QgzxJcdmwhForm t) throws Exception {
        return dao.mmcsh(t);
    }
    public boolean blsc(QgzxJcdmwhForm t)throws Exception {
    	return dao.blsc(t);
	}
	public boolean checkDwIsUsed(String[] yrdwid){
		return dao.checkDwIsUsed(yrdwid);
	}
    public boolean yhIsExist(QgzxJcdmwhForm t) throws Exception {
        String num = dao.yhIsExist(t);
        return Integer.valueOf(num) > 0;
    }
    public boolean insertYhb(QgzxJcdmwhForm t) throws Exception {
        return dao.insertYhb(t);
    }
    public HashMap<String,String> getGwlbData(String pk){
		return dao.getGwlbData(pk);
	}

    public List<HashMap<String,String>> getBmList() {
    	return dao.getBmList();
    }
}

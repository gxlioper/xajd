package xsgzgl.szdw.txgb;

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
public class SzdwTxgbService extends BasicService{
	
	SzdwTxgbDAO dao=new SzdwTxgbDAO();
	
	/**
	 * 设置结果查询表头
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(){
		
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","zzmc","gbmc","zzjb"};
		
		String[]cn=new String[]{"","学年","学号","姓名","年级","班级","组织名称","干部名称","组织级别"};
		
		return  dao.arrayToList(en, cn);
	}
	
	/**
	 * 初始化结果查询信息
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTxgbManage(RequestForm rForm,
			HttpServletRequest request) {
		
		DAO dao=DAO.getInstance();
		// 功能模块
		String gnmk = "szdw";
		// 访问路径
		String path ="szdw_xsgb_txgb.do";
		
		// ========================输出字段 begin=========================
		
		// 字段名
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","zzmc","gbmc","zzjb"};
		// 中文名
		String[]cn=new String[]{"","学年","学号","姓名","年级","班级","组织名称","干部名称","组织级别"};
		// 表头
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================输出字段 end=========================

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ===============通用设置 end ============
		
		rForm.setQtzdz(new String[]{});//加载其他数据

		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 获取卫生检查日程管理信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTxgbList(SzdwTxgbForm model) throws Exception{
		
		return dao.getTxgbList(model);
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
	 * 保存信息初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initSave(BasicModel basicModel,
			HttpServletRequest request) {

		// 主键字段
		String []save={"xn","xh","zzmc","zzjb","gbmc","bz"};
		
		// --------------表名------------
		basicModel.setTableName("xg_szdw_txgbb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
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
	
	public HashMap<String,String>getTxgbMap(SzdwTxgbForm model){
		
		return dao.getTxgbMap(model);
		
	}
	
}

package xsgzgl.wjcf.cfsjwh;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午03:45:48
 * </p>
 */
public class WjcfCfsjwhService extends BasicService {
	
	/**
	 * 设置结果查询表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "xqmc", "xh", "xm", "bjmc","cflbmc", "cfyymc", "fwsj", "fwjg","sjly" };
		String[] cn = new String[] { "", "学年", "学期", "学号", "姓名", "班级","处分类别", "处分原因", "发文时间", "发文结果" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 初始化结果查询信息
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initCfcxManage(RequestForm rForm, HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		// 功能模块
		String gnmk = "wjcf";
		// 访问路径
		String path = "wjcfsjCx.do";
		// ========================输出字段 begin=========================
		// 字段名
		String[] en = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "cwsj", "cwwh", "fwjg" };
		// 中文名
		String[] cn = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "cwsj", "cwwh", "fwjg" };
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
		rForm.setQtzdz(new String[] {});// 加载其他数据
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);
	}

	/**
	 * 获取卫生检查日程管理信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCfjgList(WjcfCfsjwhActionForm model) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.getCfjgList(model);
	}

	

	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 3) + "%\" ");
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
	 * 新增保存
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean saveCfsb(WjcfCfsjwhActionForm form) throws Exception{
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		InputStream instream = form.getFj().getInputStream();
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
		form.setFjmc(fjmc);
		form.setCflbmc(dao.cflbmc(form.getCflbdm()));
		form.setCfyymc(dao.cfyymc(form.getCfyydm()));
		return dao.saveCfsj(form, instream);
	}
	
	/**
	 * 查看处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception{
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfsjwhCk(cfid);
	}
	/**
	 * 
	 * @描述:查询是否党团员和职务
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-1 上午11:09:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZwAndZzmm(String xh) throws Exception{
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.getZwAndZzmm(xh);
	}
	
	/**
	 * 删除处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhSc(String[] cfid) throws Exception {
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : cfid){
			if (StringUtils.isNull(str)) continue;
			params.add(new String[]{str});
		}
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		boolean f =dao.cfsjwhSc(params); 
		f = dao.cfSbSsJcSc(cfid);
		return f;
	}
	
	/**
	 * 修改处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfsjwhXg(form);
	}
	
	/**
	 * 保存处分申诉信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfssjgBc(form);
	}
	
	/**
	 * 保存处分解除信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfjcjgBc(form);
	}
	
	/**
	 * 保存处分终止信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.cfzzjgBc(form);
	}

	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfCfsjwhActionForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * 通过学号查询学生处分列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuWjcfList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "违纪处分");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuWjcfAllList(xh));
		rs.add(map);
		return rs;
	}
	
	/**
	 * 通过学号查询违纪处分列表
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfAllList(String xh) {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		String[] title = {"学年", "学期", "处分类别", "处分原因", "处分时间", "处分文号", "最终结果"};
		List<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.getStuWjcfList(xh));
		rs.add(0, title);
		return rs;
	}
	/**
	 * 数据维护自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCfjgExportList(WjcfCfsjwhActionForm model,User user) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		return dao.getCfjgExportList(model,user);
	}
	
	/**
	 * 
	 * @描述:TODO(构建数据来源html)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-9 上午09:20:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String createSearchSylyHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 3) + "%\" ");
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
				
				html.append("<td style=\"display:none\">");
				html.append("<input type='hidden' name='sjly'  ");
				html.append(" value='" + replaceHtml(rs[10]) + "'/> ");
				html.append("</td>");
				
				html.append("<td style=\"display:none\">");
				html.append("<input type='hidden' name='jcwh'  ");
				html.append(" value='" + replaceHtml(rs[11]) + "'/> ");
				html.append("</td>");
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 
	 * @描述:TODO(查询处分决定书信息)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 下午01:58:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> cfjdsxx(String cfid) throws Exception{
		
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		
		return dao.cfjdsxx(cfid);
	}
	
	

}

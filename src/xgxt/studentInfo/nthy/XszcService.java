package xgxt.studentInfo.nthy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;


public class XszcService {
	
	XszcDAO dao = new XszcDAO();
	DAO basedao = DAO.getInstance();
	
	/**
	 * 下拉列表
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("zczt".equalsIgnoreCase(flg)) {
			//补办原因
			List zcztList = dao.getSelectList("zczt");
			request.setAttribute("zcztList", zcztList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		}
		request.setAttribute("sfqfList", dao.getSelectList("sfqf"));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xnList", dao.getXnList());
	}
	
	/**
	 * 查询
	 * @param form
	 * @param session
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws NullStringException
	 */
	public List<String[]> getList(XszcActionForm form,HttpSession session) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NullStringException{
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String isBzr = session.getAttribute("isBzr").toString();
		
		StringBuilder sb = new StringBuilder();
		MakeQuery makequery = new MakeQuery();
		makequery.makeQuery(new String[]{"nj","xydm","zydm","bjdm","sfqf"}, new String[]{"xh","xm"}, form);
		sb.append(" select a.*,rownum r from (select a.xh,a.xydm,a.xymc,")
			.append("a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq,")
			.append("b.zczt,b.wzcyy,b.czr,b.czsj,")
			.append("case when nvl(c.sfqf,'0')='0' then '可注册' ")
			.append("when c.sfqf='1' and d.fdysh='通过' and d.xysh='通过' and d.xxsh='通过' then '可注册' ")
			.append("else '不可注册' end sfkzc,")
			.append("nvl(c.sfqf,'0') sfqf,case when nvl(c.sfqf,'0')='1' then '未交清' else '已交清' end sfqfmc ")
			.append("from view_xsjbxx a left join (")
			.append("select * from xg_xsxx_nthy_xszcb where xn='")
			.append(form.getXn()).append("' and xq='").append(form.getXq()).append("' ")
			.append(") b on a.xh=b.xh ")
			.append("left join (select * from xg_rcsw_nthy_xsqfxxb where xn='" + form.getXn() + "') c on a.xh=c.xh ")
			.append("left join (select * from xg_rcsw_nthy_xfhjsqb where xn='" + form.getXn() + "') d on a.xh=d.xh ")
			.append(" ) a ")
			.append(makequery.getQueryString());
		StringBuilder whereSql = new StringBuilder();
		if(StringUtils.isEqual(isBzr, "true")){
			whereSql.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(isFdy, "true")){
			whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(userType, "xy")){
			whereSql.append(" and xydm = '" + userDep + "' ");
		}
		String zczt = form.getZczt();
		if(StringUtils.isNotNull(zczt)){
			if("已注册".equals(zczt) || "未注册".equals(zczt)){
				whereSql.append(" and zczt='").append(zczt).append("' ");
			}else{
				whereSql.append(" and zczt is null ");
			}
		}
		return CommonQueryDAO.commonQuery(sb.toString(), whereSql.toString(), makequery.getInputList(), new String[]{"sfkzc","xh","xm","nj","xymc","zymc","bjmc","zczt","sfqfmc"}, form);
	}
	
	/**
	 * 获取表头
	 * @return
	 */
	public List<HashMap<String, String>> getTitle(){
		String[] outputValue = new String[]{"xh","xm","nj","xymc","zymc","bjmc","zczt","sfqfmc"};
		String[] colCN = new String[]{"学号","姓名","年级","学院","专业","班级","注册状态","交费情况"};
		return basedao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * 批量更新注册状态
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws NullStringException
	 */
	public boolean updateSave(XszcActionForm form) throws SQLException, NullStringException{
		String[] pks = form.getCbv();
		if(StringUtils.isEqual("未注册", form.getZt())){
			pks = form.getXhstr().split(",");
		}
		String xn = Base.currXn;
		String xq = Base.currXq;
		StringBuilder sb = new StringBuilder();
		for(String pk : pks){
			sb.append("delete from xg_xsxx_nthy_xszcb where xn='").append(xn).append("' and xq='").append(xq).append("' and xh='").append(pk).append("'").append("!@");
			sb.append("insert into xg_xsxx_nthy_xszcb(xh,xn,xq,zczt,wzcyy,czr) values('").append(pk).append("','").append(xn).append("','").append(xq).append("','").append(form.getZt()).append("','").append(StringUtils.isNull(form.getWzcyy()) ? "" : form.getWzcyy()).append("','").append(form.getUserName()).append("')").append("!@");
		}
		return basedao.checkBatch(basedao.runBatch(sb.toString().split("!@")));
	}
	
	/**
	 * 取消注册
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteZcxx(XszcActionForm form) throws SQLException{
		String[] pks = form.getCbv();
		String xn = form.getXn();
		String xq = form.getXq();
		StringBuilder sb = new StringBuilder();
		for(String pk : pks){
			sb.append("delete from xg_xsxx_nthy_xszcb where xn='").append(xn).append("' and xq='").append(xq).append("' and xh='").append(pk).append("'").append("!@");
		}
		return basedao.checkBatch(basedao.runBatch(sb.toString().split("!@")));
	}
	
	/**
	 * 查看学生注册相关信息
	 * @param form
	 * @return
	 */
	public HashMap<String, String> viewZcxx(XszcActionForm form){
		return dao.viewZcxx(form);
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @param form
	 * @param session
	 * @throws Exception
	 */
	public void expPageData(HttpServletRequest request, 
            HttpServletResponse response,XszcActionForm form,HttpSession session) throws Exception{
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String isBzr = session.getAttribute("isBzr").toString();
		
		StringBuilder sb = new StringBuilder();
		MakeQuery makequery = new MakeQuery();
		makequery.makeQuery(new String[]{"nj","xydm","zydm","bjdm","sfqf"}, new String[]{"xh","xm"}, form);
		sb.append(" select a.*,rownum r from (select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq,b.zczt,b.wzcyy,b.czr,b.czsj,nvl(c.sfqf,'0') sfqf,case when nvl(c.sfqf,'0')='1' then '未交清' else '已交清' end sfqfmc from view_xsjbxx a left join (")
			.append("select * from xg_xsxx_nthy_xszcb where xn='")
			.append(form.getXn()).append("' and xq='").append(form.getXq()).append("' ")
			.append(") b on a.xh=b.xh ")
			.append("left join (select * from xg_rcsw_nthy_xsqfxxb where xn='" + form.getXn() + "') c on a.xh=c.xh ")
			.append(") a ")
			.append(makequery.getQueryString());
		StringBuilder whereSql = new StringBuilder();
		if(StringUtils.isEqual(isBzr, "true")){
			whereSql.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(isFdy, "true")){
			whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(userType, "xy")){
			whereSql.append(" and xydm = '" + userDep + "' ");
		}
		String zczt = form.getZczt();
		if(StringUtils.isNotNull(zczt)){
			if("已注册".equals(zczt) || "未注册".equals(zczt)){
				whereSql.append(" and zczt='").append(zczt).append("' ");
			}else{
				whereSql.append(" and zczt is null ");
			}
		}
		String[] outputColumn = new String[]{"xh","xm","nj","xymc","zymc","bjmc","zczt","wzcyy","czr","czsj","sfqfmc"};
		String[] colListCN = new String[]{"学号","姓名","年级","学院","专业","班级","注册状态","未注册原因","操作人","操作时间","交费情况"};
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(sb.toString(), whereSql.toString(), makequery.getInputList(), outputColumn, form);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
	}
	
	/**
	 * 获取统计表头信息
	 * @return
	 */
	public List<HashMap<String, String>> getTjTitle(){
		String[] outputValue = new String[]{"xymc","zrs","yzcrs","zcbl","wzcrs","wclrs"};
		String[] colCN = new String[]{"学院","总人数","已注册人数","注册比率","未注册人数","未处理人数"};
		return basedao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * 获取统计结果
	 * @param form
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getTjList(XszcActionForm form) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder jcsql = new StringBuilder();
		jcsql.append("select a.xydm,a.xymc,nvl(a.zrs,0) zrs,nvl(b.yzcrs,0) yzcrs,nvl(c.wzcrs,0) wzcrs,nvl(a.zrs,0)-nvl(b.yzcrs,0)-nvl(c.wzcrs,0) wclrs from (select xydm,xymc,count(1) zrs from view_xsjbxx where xydm is not null and xymc is not null group by xydm,xymc) a ");
		jcsql.append("left join (select b.xydm,count(1) yzcrs from xg_xsxx_nthy_xszcb a,view_xsjbxx b where a.xn='" + form.getXn() + "' and a.xq='" + form.getXq() + "' and a.zczt='已注册' and a.xh=b.xh group by b.xydm) b on a.xydm=b.xydm ");
		jcsql.append("left join (select b.xydm,count(1) wzcrs from xg_xsxx_nthy_xszcb a,view_xsjbxx b where a.xn='" + form.getXn() + "' and a.xq='" + form.getXq() + "' and a.zczt='未注册' and a.xh=b.xh group by b.xydm) c on a.xydm=c.xydm ");
		String sql = "select xymc,zrs,yzcrs,wzcrs,wclrs," +
				"case zrs when 0 then '0%' else nvl(case when instr(to_char(round(yzcrs / zrs * 100, 2)), '.', 1, 1) = 1 then '0' || to_char(round(yzcrs / zrs * 100, 2)) else to_char(round(yzcrs /zrs * 100, 2)) end, '0') || '%' end zcbl " +
				"from (" + jcsql.toString() + " order by xymc) " +
				"union all " +
				"select '合计' xymc,sum(zrs),sum(yzcrs),sum(wzcrs),sum(wclrs)," +
				"case sum(zrs) when 0 then '0%' else nvl(case when instr(to_char(round(sum(yzcrs) / sum(zrs) * 100, 2)), '.', 1, 1) = 1 then '0' || to_char(round(sum(yzcrs) / sum(zrs) * 100, 2)) else to_char(round(sum(yzcrs) /sum(zrs) * 100, 2)) end, '0') || '%' end zcbl " +
				"from (" + jcsql.toString() + ")";
		return CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{}, new String[]{"xymc","zrs","yzcrs","zcbl","wzcrs","wclrs"}, form);
	}
	
}

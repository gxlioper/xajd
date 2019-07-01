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


public class XsbdService {
	
	XsbdDAO dao = new XsbdDAO();
	DAO basedao = DAO.getInstance();
	
	/**
	 * 下拉列表
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("bdzt".equalsIgnoreCase(flg)) {
			//补办原因
			List bdztList = dao.getSelectList("bdzt");
			request.setAttribute("bdztList", bdztList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		}
		
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
	public List<String[]> getList(XsbdActionForm form,HttpSession session) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NullStringException{
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String isBzr = session.getAttribute("isBzr").toString();
		
		StringBuilder sb = new StringBuilder();
		MakeQuery makequery = new MakeQuery();
		makequery.makeQuery(new String[]{"nj","xydm","zydm","bjdm"}, new String[]{"xh","xm"}, form);
		sb.append(" select a.*,rownum r from (select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq,b.bdzt,b.wbdyy,b.czr,b.czsj from view_xsjbxx a left join (")
			.append("select * from xg_xsxx_nthy_xsbdb where xn='")
			.append(form.getXn()).append("' and xq='").append(form.getXq()).append("' ")
			.append(") b on a.xh=b.xh ) a ")
			.append(makequery.getQueryString());
		StringBuilder whereSql = new StringBuilder();
		if(StringUtils.isEqual(isBzr, "true")){
			whereSql.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(isFdy, "true")){
			whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(userType, "xy")){
			whereSql.append(" and xydm = '" + userDep + "' ");
		}
		String bdzt = form.getBdzt();
		if(StringUtils.isNotNull(bdzt)){
			if("已报到".equals(bdzt) || "未报到".equals(bdzt)){
				whereSql.append(" and bdzt='").append(bdzt).append("' ");
			}else{
				whereSql.append(" and bdzt is null ");
			}
		}
		return CommonQueryDAO.commonQuery(sb.toString(), whereSql.toString(), makequery.getInputList(), new String[]{"xh","xm","nj","xymc","zymc","bjmc","bdzt"}, form);
	}
	
	/**
	 * 获取表头
	 * @return
	 */
	public List<HashMap<String, String>> getTitle(){
		String[] outputValue = new String[]{"xh","xm","nj","xymc","zymc","bjmc","bdzt"};
		String[] colCN = new String[]{"学号","姓名","年级","学院","专业","班级","报到状态"};
		return basedao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * 批量更新报到状态
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws NullStringException
	 */
	public boolean updateSave(XsbdActionForm form) throws SQLException, NullStringException{
		String[] pks = form.getCbv();
		if(StringUtils.isEqual("未报到", form.getZt())){
			pks = form.getXhstr().split(",");
		}
		String xn = Base.currXn;
		String xq = Base.currXq;
		StringBuilder sb = new StringBuilder();
		for(String pk : pks){
			sb.append("delete from xg_xsxx_nthy_xsbdb where xn='").append(xn).append("' and xq='").append(xq).append("' and xh='").append(pk).append("'").append("!@");
			sb.append("insert into xg_xsxx_nthy_xsbdb(xh,xn,xq,bdzt,wbdyy,czr) values('").append(pk).append("','").append(xn).append("','").append(xq).append("','").append(form.getZt()).append("','").append(StringUtils.isNull(form.getWbdyy()) ? "" : form.getWbdyy()).append("','").append(form.getUserName()).append("')").append("!@");
		}
		return basedao.checkBatch(basedao.runBatch(sb.toString().split("!@")));
	}
	
	/**
	 * 取消报到
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBdxx(XsbdActionForm form) throws SQLException{
		String[] pks = form.getCbv();
		String xn = form.getXn();
		String xq = form.getXq();
		StringBuilder sb = new StringBuilder();
		for(String pk : pks){
			sb.append("delete from xg_xsxx_nthy_xsbdb where xn='").append(xn).append("' and xq='").append(xq).append("' and xh='").append(pk).append("'").append("!@");
		}
		return basedao.checkBatch(basedao.runBatch(sb.toString().split("!@")));
	}
	
	/**
	 * 查看学生报到相关信息
	 * @param form
	 * @return
	 */
	public HashMap<String, String> viewBdxx(XsbdActionForm form){
		return dao.viewBdxx(form);
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
            HttpServletResponse response,XsbdActionForm form,HttpSession session) throws Exception{
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String isBzr = session.getAttribute("isBzr").toString();
		
		StringBuilder sb = new StringBuilder();
		MakeQuery makequery = new MakeQuery();
		makequery.makeQuery(new String[]{"nj","xydm","zydm","bjdm"}, new String[]{"xh","xm"}, form);
		sb.append(" select a.*,rownum r from (select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq,b.bdzt,b.wbdyy,b.czr,b.czsj from view_xsjbxx a left join (")
			.append("select * from xg_xsxx_nthy_xsbdb where xn='")
			.append(form.getXn()).append("' and xq='").append(form.getXq()).append("' ")
			.append(") b on a.xh=b.xh ) a ")
			.append(makequery.getQueryString());
		StringBuilder whereSql = new StringBuilder();
		if(StringUtils.isEqual(isBzr, "true")){
			whereSql.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(isFdy, "true")){
			whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		}else if(StringUtils.isEqual(userType, "xy")){
			whereSql.append(" and xydm = '" + userDep + "' ");
		}
		String bdzt = form.getBdzt();
		if(StringUtils.isNotNull(bdzt)){
			if("已报到".equals(bdzt) || "未报到".equals(bdzt)){
				whereSql.append(" and bdzt='").append(bdzt).append("' ");
			}else{
				whereSql.append(" and bdzt is null ");
			}
		}
		String[] outputColumn = new String[]{"xh","xm","nj","xymc","zymc","bjmc","bdzt","wbdyy","czr","czsj"};
		String[] colListCN = new String[]{"学号","姓名","年级","学院","专业","班级","报到状态","未报到原因","操作人","操作时间"};
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
		String[] outputValue = new String[]{"xymc","zrs","ybdrs","bdbl","wbdrs","wclrs"};
		String[] colCN = new String[]{"学院","总人数","已报到人数","报到比率","未报到人数","未处理人数"};
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
	public List<String[]> getTjList(XsbdActionForm form) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder jcsql = new StringBuilder();
		jcsql.append("select a.xydm,a.xymc,nvl(a.zrs,0) zrs,nvl(b.ybdrs,0) ybdrs,nvl(c.wbdrs,0) wbdrs,nvl(a.zrs,0)-nvl(b.ybdrs,0)-nvl(c.wbdrs,0) wclrs from (select xydm,xymc,count(1) zrs from view_xsjbxx where xydm is not null and xymc is not null group by xydm,xymc) a ");
		jcsql.append("left join (select b.xydm,count(1) ybdrs from xg_xsxx_nthy_xsbdb a,view_xsjbxx b where a.xn='" + form.getXn() + "' and a.xq='" + form.getXq() + "' and a.bdzt='已报到' and a.xh=b.xh group by b.xydm) b on a.xydm=b.xydm ");
		jcsql.append("left join (select b.xydm,count(1) wbdrs from xg_xsxx_nthy_xsbdb a,view_xsjbxx b where a.xn='" + form.getXn() + "' and a.xq='" + form.getXq() + "' and a.bdzt='未报到' and a.xh=b.xh group by b.xydm) c on a.xydm=c.xydm ");
		String sql = "select xymc,zrs,ybdrs,wbdrs,wclrs," +
				"case zrs when 0 then '0%' else nvl(case when instr(to_char(round(ybdrs / zrs * 100, 2)), '.', 1, 1) = 1 then '0' || to_char(round(ybdrs / zrs * 100, 2)) else to_char(round(ybdrs /zrs * 100, 2)) end, '0') || '%' end bdbl " +
				"from (" + jcsql.toString() + " order by xymc) " +
				"union all " +
				"select '合计' xymc,sum(zrs),sum(ybdrs),sum(wbdrs),sum(wclrs)," +
				"case sum(zrs) when 0 then '0%' else nvl(case when instr(to_char(round(sum(ybdrs) / sum(zrs) * 100, 2)), '.', 1, 1) = 1 then '0' || to_char(round(sum(ybdrs) / sum(zrs) * 100, 2)) else to_char(round(sum(ybdrs) /sum(zrs) * 100, 2)) end, '0') || '%' end bdbl " +
				"from (" + jcsql.toString() + ")";
		return CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{}, new String[]{"xymc","zrs","ybdrs","bdbl","wbdrs","wclrs"}, form);
	}
	
}

package xgxt.studentInfo.xszc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;



public class XszcService {
	
	XszcDAO dao = new XszcDAO();
	
	/**
	 * 拼装获取学生所有学年学期的注册情况信息
	 * @param xh 学号
	 * @return
	 */
	public List<String[]> getRs(String xh){
		//获取学生信息
		HashMap<String, String> stuinfo = CommonQueryDAO.getStuInfo(xh);
		String nj = StringUtils.isNull(stuinfo.get("nj")) ? "" : stuinfo.get("nj");
		int cont = (Integer.parseInt(Base.currNd) - Integer.parseInt(nj));
		StringBuilder zbsql = new StringBuilder();
		int nianji = Integer.parseInt(nj);
		//获取除当前年度外的所有学年学期
		for(int i = 0;i < cont; i++){
			zbsql.append(" select ")
				.append(nianji)
				.append("||'-'||")
				.append(nianji+1)
				.append(" xn,'上学期' xq from dual ")
				.append(" union all ");
			zbsql.append(" select ")
				.append(nianji)
				.append("||'-'||")
				.append(nianji+1)
				.append(" xn,'下学期' xq from dual ")
				.append(" union all ");
			nianji++;
		}
		//获取当前年度相关学年学期，主要是学期有区别
		if(Base.currNd.equals(Base.currXn.substring(0,4))){
			zbsql.append(" select ")
				.append(Base.currNd)
				.append("||'-'||")
				.append(Integer.parseInt(Base.currNd)+1)
				.append(" xn,'上学期' xq from dual ");
		}else{
			zbsql.append(" select ")
				.append(Base.currNd)
				.append("||'-'||")
				.append(Integer.parseInt(Base.currNd)+1)
				.append(" xn,'上学期' xq from dual ")
				.append(" union all ");
			zbsql.append(" select ")
				.append(Base.currNd)
				.append("||'-'||")
				.append(Integer.parseInt(Base.currNd)+1)
				.append(" xn,'下学期' xq from dual ");
		}
		String sql = "select a.xn||a.xq||'" + xh + "' pk,a.xn,a.xq," + xh + " xh,nvl(b.zczt,'未注册') zczt,nvl(b.fdysh,'未审核') fdysh,case when b.fdysh is not null then '#99CCFF' else '#FFFFFF' end bgcolor,case when b.yy is not null then '已填写' else '未填写' end yytxzt from (" + zbsql.toString() + ") a left join xg_xsxx_zcqkb b on a.xn=b.xn and a.xq=b.xq and b.xh='" + xh + "' where a.xn <= '"+Base.currXn+"' order by a.xn,a.xq "; 
		return dao.getRs(sql, new String[]{}, new String[]{"pk","bgcolor","xn","xq","zczt","fdysh"});
	}
	
	/**
	 * 获取表头信息
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(){
		return dao.getTopTr();
	}
	
	/**
	 * 注册
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public boolean zc(XszcActionForm form) throws SQLException{
		StringBuilder sql = new StringBuilder();
		String[] pkArr = form.getPrimarykey_cbv();
		String[] xnArr = form.getXn().split(",");
		String[] xqArr = form.getXq().split(",");
		for(int i=0;i<pkArr.length;i++){
			sql.append("delete from xg_xsxx_zcqkb where xn||xq||xh = '").append(pkArr[i]).append("'").append("!@");
			sql.append("insert into xg_xsxx_zcqkb(xn,xq,xh,zczt) values('").append(xnArr[i]).append("','").append(xqArr[i]).append("','").append(form.getXh()).append("','已注册')").append("!@");
		}
		return dao.zc(sql.toString().substring(0,sql.toString().length()-2).split("!@"));
	}
	
	/**
	 * 获取学生注册信息
	 * @param form
	 * @return
	 */
	public HashMap<String, String> getXszcInfo(XszcActionForm form){
		HashMap<String, String> map = new HashMap<String, String>();
		if(!"0".equals(dao.getCont(form.getPk()))){
			map = dao.getXszcInfo(form.getPk());
		}else{
			map.put("xn", form.getXn());
			map.put("xq", form.getXq());
			map.put("xh", form.getXh());
		}
		map.put("type", form.getType()); //欲操作的注册状态
		return map;
	}
	
	/**
	 * 删除学生注册信息
	 * @param pk 学年+学期+学号
	 * @return
	 * @throws Exception
	 */
	public boolean del(String pk) throws Exception{
		return dao.del(pk);
	}
	
	/**
	 * 下拉列表
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		if ("sh".equalsIgnoreCase(flg)) {
			List shztList = dao.getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		} else if ("zc".equals(flg)){
			List zcztList = dao.getSelectList("zczt");
			request.setAttribute("zcztList", zcztList);
		}
	}
	
}

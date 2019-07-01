package xgxt.pjpy.jhzy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;

public class JhzyPjpyDAO extends CommonQueryDAO {
	
	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 取得个各分数班级排名
	 * @return HashMap
	 */
	public HashMap<String, String> getFzPm(String xh, String xn, String xq){
		DAO dao = new DAO();		
		String[] outputValue = new String[] { "xh", "xm", "xn", "xq", "bjdm",
				"dyf", "dypm", "dyb", "zyf", "zypm", "zyb", "tyf", "typm",
				"tyb", "jnf", "jnpm", "jnb", "jcf", "jcpm", "jcb", "zhf",
				"zhpm", "zhb","num" };
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.xh,a.xm,a.xn,a.xq,a.bjdm, ");
		sql.append("a.dyf,a.dypm,trim(to_char(a.dypm / b.num * 100, 99.99)) dyb, ");
		sql.append("a.zyf,a.zypm,trim(to_char(a.zypm / b.num * 100, 99.99)) zyb, ");
		sql.append("a.tyf,a.typm,trim(to_char(a.typm / b.num * 100, 99.99)) tyb, ");
		sql.append("a.jnf,a.jnpm,trim(to_char(a.jnpm / b.num * 100, 99.99)) jnb, ");
		sql.append("a.jcf,a.jcpm,trim(to_char(a.jcpm / b.num * 100, 99.99)) jcb, ");
		sql.append("a.zhf,a.zhpm,trim(to_char(a.zhpm / b.num * 100, 99.99)) zhb, ");
		sql.append("b.num from (select xh,xm,xn,xq,bjdm,dyf,zyf,tyf,jnf,jcf,zhf, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(dyf) desc nulls last)) dypm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(zyf) desc nulls last)) zypm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(tyf) desc nulls last)) typm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(jnf) desc nulls last)) jnpm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(jcf) desc nulls last)) jcpm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(zhf) desc nulls last)) zhpm ");
		sql.append("from view_jhzy_zhf where bjdm = (select bjdm from view_xsjbxx where xh = ?) and xn = ? and xq = ?) a ");
		sql.append("left join (select bjdm, count(*) num from view_xsjbxx group by bjdm) b on a.bjdm = b.bjdm) where xh = ? ");
		//System.out.println(sql);
		return dao.getMap(sql.toString(), new String[]{xh,xn,xq,xh}, outputValue);
	}
	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 取得个各分数班级排名
	 * @return List
	 */
	public  List<HashMap<String, String>> getFzPmList(String xh,String xn,String xq){
		DAO dao = new DAO();
		xn=Base.isNull(xn)?"%":xn;
		xq=Base.isNull(xq)?"%":xq;
		String[] outputValue = new String[] { "xh", "xm", "xn", "xq","xqmc", "bjdm",
				"dyf", "dypm", "dyb", "zyf", "zypm", "zyb", "tyf", "typm",
				"tyb", "jnf", "jnpm", "jnb", "jcf", "jcpm", "jcb", "zhf",
				"zhpm", "zhb","num" };
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc from (select a.xh,a.xm,a.xn,a.xq,a.bjdm, ");
		sql.append("a.dyf,a.dypm,trim(to_char(a.dypm / b.num * 100, 99.99)) dyb, ");
		sql.append("a.zyf,a.zypm,trim(to_char(a.zypm / b.num * 100, 99.99)) zyb, ");
		sql.append("a.tyf,a.typm,trim(to_char(a.typm / b.num * 100, 99.99)) tyb, ");
		sql.append("a.jnf,a.jnpm,trim(to_char(a.jnpm / b.num * 100, 99.99)) jnb, ");
		sql.append("a.jcf,a.jcpm,trim(to_char(a.jcpm / b.num * 100, 99.99)) jcb, ");
		sql.append("a.zhf,a.zhpm,trim(to_char(a.zhpm / b.num * 100, 99.99)) zhb, ");
		sql.append("b.num from (select xh,xm,xn,xq,bjdm,dyf,zyf,tyf,jnf,jcf,zhf, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(dyf) desc nulls last)) dypm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(zyf) desc nulls last)) zypm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(tyf) desc nulls last)) typm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(jnf) desc nulls last)) jnpm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(jcf) desc nulls last)) jcpm, ");
		sql.append("(rank() over(partition by xn,xq,bjdm order by to_number(zhf) desc nulls last)) zhpm ");
		sql.append("from view_jhzy_zhf where bjdm = (select bjdm from view_xsjbxx where xh = ? )and xn like ? and xq like ? ) a ");
		sql.append("left join (select bjdm, count(*) num from view_xsjbxx group by bjdm) b on a.bjdm = b.bjdm)a where xh = ? order by xn,xq");
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[]{xh,xn,xq,xh}, outputValue);
	}
	
	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 取得个学生成绩列表
	 */
	public List<HashMap<String, String>> getCjList(String xh, String xn){
		DAO dao = new DAO();
		String[] outputValue = new String[] { "kcmc", "xq", "cj" };
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select kcmc, (select xqmc from xqdzb where xqdm = xq) xq, cj ");
		sql.append("from cjb where xh = ? and xn = ? union select '平均分' kcmc, ");
		sql.append("'学年 ' || (select xqmc from xqdzb where xqdm = xq) || '学期' xq,to_Char(round(avg(cj))) cj ");
		sql.append("from cjb where xh = ? and xn = ? group by xq) order by xq ");
		System.out.println(sql);
		return dao.getList(sql.toString(), new String[]{xh,xn,xh,xn}, outputValue);
	}
}

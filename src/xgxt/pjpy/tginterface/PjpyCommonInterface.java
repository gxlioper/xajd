/*
 * 评奖评优和违纪处分模块需要提供给其它模块的相关接口类 
 * 
 * PjpyCommonInterface.java
 * 
 * lt 2010.6.11
 */
package xgxt.pjpy.tginterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;


public class PjpyCommonInterface {

	DAO dao = DAO.getInstance();
	
	List<String> values = new ArrayList<String>();
	
	/**
	 * 查询班级学生处分信息
	 * @param map 里面key必须要有 bjdm,xn
	 * @return {学号,姓名,学年,学期,处分类别名称,处分原因名称,处分时间,处分文号}
	 */
	public List<String[]> queryBjStuCfxx(HashMap<String, String> map) {
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("bjdm"))
				&& StringUtils.isNotNull(map.get("xn"))) {
			return dao
					.rsToVator(
							"select xh,xm,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,cflbmc,cfyymc,cfsj,cfwh from view_wjcf a "
									+ "where xn=? and bjdm=? and xxsh='通过' and cfsj is not null and cfwh is not null order by bjdm,xh",
							new String[] { map.get("xn"), map.get("bjdm") },
							new String[] { "xh", "xm", "xn", "xq", "cflbmc",
									"cfyymc", "cfsj", "cfwh" });
		} else {
			System.out.println("传入参数中,班级代码,学年不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 查询班级学生评奖评优信息
	 * @param map 里面key必须要有 bjdm,xn
	 * @return {"学年", "班级", "获奖奖项", "获奖人数"}
	 */
	public List<String[]> queryBjStuPjpyxx(HashMap<String, String> map) {
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("bjdm")) && StringUtils.isNotNull(map.get("xn"))) {
			return dao.rsToVator("select xn,bjmc,jxjmc mc,(count(xh)) rs from view_xsjxjb where xn=? and bjdm=? and xxsh='通过' group by xn,bjmc,jxjmc" +
					" union all " +
					"select xn,bjmc,rychmc mc,(count(xh)) rs from view_xsrychb where xn=? and bjdm=? and xxsh='通过' group by xn,bjmc,rychmc",
							new String[] { map.get("xn"), map.get("bjdm"),map.get("xn"), map.get("bjdm") },
							new String[] {"xn", "bjmc", "mc", "rs"});
		} else {
			System.out.println("传入参数中,班级代码,学年不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 查询班级学生补考人数及补考率
	 * @param map  里面key必须要有 bjdm,bjrs,xn  (班级代码,班级人数,学年)
	 * @return HashMap {"学年", "学期", "班级人数", "补考人数", "补考率"}
	 */
	public List<HashMap<String, String>> queryBjStuBjgl(HashMap<String, String> map) {
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("bjdm")) && StringUtils.isNotNull(map.get("xn"))) {
			return dao.getList("select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,bjrs,rs,round((rs/bjrs*100),2)||'%' bkl from (" +
					"select xn,xq,count(xh) rs,"+map.get("bjrs")+" bjrs from (" +
					"select distinct xh,xn,xq from (" +
					"select xn,xq,a.xh from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and b.bjdm=? and a.bkcj is not null group by a.xn,a.xq,a.xh" +
					")) group by xn,xq) a", new String[]{map.get("xn"), map.get("bjdm")}, new String[]{"xn", "xq", "bjrs", "rs", "bkl"});
		} else {
			System.out.println("传入参数中,班级代码,学年不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 通过学号和学年 查询学生成绩补考科目数 
	 * @param map  里面key必须要有 xh,xn  (学号,学年)
	 * @return
	 */
	public String queryStuCjbkms(HashMap<String, String> map) {
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("xn"))
				&& StringUtils.isNotNull(map.get("xh"))) {
			return dao.getOneRs("select count(xh) ms from cjb where xh=? and xn=? and bkcj is not null",
								new String[] { map.get("xh"), map.get("xn") },
								"ms");
		} else {
			System.out.println("传入参数中,学号和学年不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 查询单个学生处分信息
	 * @param map 里面key必须要有 xh.  
	 *            如果传入xn,xq 则根据所传入的xn,xq进行查询,否则查询学生所有的处分信息
	 * @return {学号,姓名,学年,学期,处分类别名称,处分原因名称,处分时间,处分文号}
	 */
	public List<String[]> queryStuCfxx(HashMap<String, String> map) {
		if ( map != null && !map.isEmpty() && StringUtils.isNotNull(map.get("xh"))) {
			StringBuilder whereSql = new StringBuilder("");
			if (StringUtils.isNotNull(map.get("xn"))) {
				whereSql.append(" and xn='");
				whereSql.append(map.get("xn"));
				whereSql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				whereSql.append(" and xq='");
				whereSql.append(map.get("xq"));
				whereSql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xh"))) {
				whereSql.append(" and xh='");
				whereSql.append(map.get("xh"));
				whereSql.append("'");
			}
		//	whereSql.append(" order by bjdm,xh");
			return dao.rsToVator("select xh,xm,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,cflbmc,cfyymc,cfsj,cfwh from view_wjcf a " +
					"where xxsh='通过' and cfsj is not null and cfwh is not null" + whereSql.toString(),
							new String[] {},
							new String[] {"xh","xm","xn","xq","cflbmc","cfyymc","cfsj","cfwh"});
		} else {
			System.out.println("传入参数中,学号不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 查询单个学生成绩信息
	 * @param map 里面key必须要有 xh.  
	 *            如果传入xn,xq 则根据所传入的xn,xq进行查询,否则查询学生所有的处分信息
	 * @return {学号,姓名,学年,课程名称,课程性质,成绩,补考成绩}
	 */
	public List<String[]> queryStuCjxx(HashMap<String, String> map) {
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("xh"))) {
			StringBuilder whereSql = new StringBuilder("");
			if (StringUtils.isNotNull(map.get("xn"))) {
				whereSql.append(" and xn='");
				whereSql.append(map.get("xn"));
				whereSql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				whereSql.append(" and xq='");
				whereSql.append(map.get("xq"));
				whereSql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xh"))) {
				whereSql.append(" and xh='");
				whereSql.append(map.get("xh"));
				whereSql.append("'");
			}
			//whereSql.append(" order by bjdm,xh");
			return dao.rsToVator("select xh,xm,xn,xq,kcmc,kcxz,cj,bkcj from view_cjb a " +
					"where 1=1 " + whereSql.toString(),
							new String[] {},
							new String[] {"xh","xm","xn","xq","kcmc","kcxz","cj","bkcj"});
		} else {
			System.out.println("传入参数中,学号不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 查询综合素质测评成绩和排名
	 * 
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> queryStuZhszcpCjAndPm(
			HashMap<String, String> map) {
		if (StringUtils.isNotNull(map.get("xh"))) {
			StringBuilder sql = new StringBuilder(
					"select nvl(a.fs,0) fs,a.pm,(select mc from pjpy_zctjszb b where a.dm=b.dm) mc,xn,nd,xq,(case when xq='无' then xq else (select xqmc from xqdzb b where a.xq=b.xqdm) end) xqmc from view_pjpy_zhszcpb a where jb='2' and xh='");
			sql.append(map.get("xh"));
			sql.append("'");
			if (StringUtils.isNotNull(map.get("xn"))) {
				sql.append(" and xn='"); 
				sql.append(map.get("xn"));
				sql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				sql.append(" and xq='"); 
				sql.append(map.get("xq"));
				sql.append("'");
			}
			
			String pjzq = queryPjzq();
			if ("nd".equalsIgnoreCase(pjzq)) {
				sql.append(" and nd !='无'");
			} else if ("xq".equalsIgnoreCase(pjzq)) {
				sql.append(" and xq != '无' and xn != '无'");
			} else if ("xn".equalsIgnoreCase(pjzq)) {
				sql.append(" and xn != '无'");
			}
			
			
			return dao.getList(sql.toString(), new String[] {}, new String[] {
					"mc", "fs", "pm", "xn", "xq", "nd", "xqmc" });
		} else {
			System.out.println("传入参数中,学号不能为空!!!!!!");
		}
		return new ArrayList<HashMap<String, String>>();
	}
	
	public String queryPjzq() {
		String result = "";
		HashMap<String, String> zqMap = dao.getMapNotOut("select xn,nd,xq from pjpy_pjzqb", new String[]{});
		if ("checked".equalsIgnoreCase(zqMap.get("nd"))) {
			result = "nd";
		} else {
			if ("checked".equalsIgnoreCase(zqMap.get("xq"))) {
				result = "xq";
			} else if ("checked".equalsIgnoreCase(zqMap.get("xn"))) {
				result = "xn";
			}
		}
		return result;
	}
	
	/**
	 * 综测总分及排名
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> queryStuCjAndPm(
			HashMap<String, String> map) {
		if (StringUtils.isNotNull(map.get("xh"))) {
			StringBuilder sql = new StringBuilder(
					"select nvl(fs,0) fs,pm,'总分' mc,xn,nd,xq,(case when xq='无' then xq else (select xqmc from xqdzb b where a.xq=b.xqdm) end) xqmc from view_pjpy_zhszcpb a where jb='1' and xh='");
			sql.append(map.get("xh"));
			sql.append("'");
			if (StringUtils.isNotNull(map.get("xn"))) {
				sql.append(" and xn='");
				sql.append(map.get("xn"));
				sql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				sql.append(" and xq='");
				sql.append(map.get("xq"));
				sql.append("'");
			}
			String[] colList = new String[]{"mc", "fs", "pm", "nd", "xn", "xq", "xqmc"};
			String pjzq = queryPjzq();
			if ("nd".equalsIgnoreCase(pjzq)) {
				sql.append(" and nd !='无' and xn='无' and xq='无'");
			} else if ("xq".equalsIgnoreCase(pjzq)) {
				sql.append(" and xq != '无' and xn != '无' and nd='无'");

			} else if ("xn".equalsIgnoreCase(pjzq)) {
				sql.append(" and xn != '无' and xq='无'");
				
			}
			
			
			return dao.getList(sql.toString(), new String[] {}, colList);
		} else {
			System.out.println("传入参数中,学号不能为空!!!!!!");
		}
		return new ArrayList<HashMap<String, String>>();
	}
	
	
	/**
	 * 闽江学院综合综测接口
	 * 
	 * 参数：zydm,nj,xh,jb
	 *	返回参数：综测分排名比例。返回的是所有分数的排名
	 *  测试学号：3049913161
	 */
	public List<HashMap<String, String>> queryStuZcPmxx(Map<String, String> map) {
		StringBuilder whereSql = new StringBuilder("select xh,xm,xn,mc,jb,fs,pm from view_pjpy_zhszcpb where 1=1 ");
		values = new ArrayList<String>();
		if (StringUtils.isNotNull(map.get("xh"))) {
			whereSql.append(" and xh=?");
			values.add(map.get("xh"));
		}
		if (StringUtils.isNotNull(map.get("zydm"))) {
			whereSql.append(" and zydm=?");
			values.add(map.get("zydm"));
		}
		if (StringUtils.isNotNull(map.get("xn"))) {
			whereSql.append(" and xn=?");
			values.add(map.get("xn"));
		}
		if (StringUtils.isNotNull(map.get("jb"))) {
			whereSql.append(" and jb=?");
			values.add(map.get("jb"));
		}
		return dao.getList(
				whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, new String[] { "xh", "xm", "xn",
						"mc", "jb", "fs", "pm" });
	}
	
}

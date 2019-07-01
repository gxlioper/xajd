package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CnToEnUtil;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class SearchTj {

	/**
	 * 设置通用所需条件
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setCommListList(SearchForm searchForm, RequestForm rForm,
			User user, String[] PY_BIG, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		// 政治面貌
		String sql = "select distinct zzmmdm dm,zzmmmc mc from zzmmdmb where zzmmmc is not null order by zzmmdm";
		List<HashMap<String, String>> zzmmList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("zzmmList", zzmmList);

		// 部门条件
		List<HashMap<String, Object>> commBmList = setNewBmListBy(PY_BIG);

		request.setAttribute("commBmList", commBmList);

		// 审核状态
		List<HashMap<String, String>> shztList = getCommSelectList("shzt");
		
		request.setAttribute("shztList", shztList);
		
		// 审核结果
		List<HashMap<String, String>> shjgList = getCommSelectList("shjg");
		
		request.setAttribute("shjgList", shjgList);
		
		
		// 是否列表
		List<HashMap<String, String>> sfList = getCommSelectList("sf");

		request.setAttribute("sfList", sfList);
	}

	/**
	 * 设置学生信息所需条件
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setXsxxListList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		DAO dao = DAO.getInstance();
		// 学制条件
		String sql = "select distinct t.xz dm,t.xz mc from view_xsbfxx t where t.xz is not null order by t.xz";
		List<HashMap<String, String>> xzList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("xsxxXzList", xzList);

		// 学籍状态
		//sql = "select distinct zxdmmc dm,zxdmmc mc from dm_zju_xjzt order by zxdmmc";
		sql = "select distinct xjztm dm,xjztm mc from view_xsbfxx order by xjztm";
		List<HashMap<String, String>> xjList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("xsxxXjList", xjList);

		// 是否在校
		List<HashMap<String, String>> sfzxList = getXsxxSelectList("sfzx");
		request.setAttribute("xsxxSfzxList", sfzxList);

		// 民族
		sql = "select distinct mzdm dm,mzmc mc from mzdmb where mzdm <> '-8' order by mzdm";
		List<HashMap<String, String>> mzList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxMzList", mzList);

		// 是否缴费
		List<HashMap<String, String>> sfjfList = getXsxxSelectList("sfjf");
		request.setAttribute("xsxxSfjfList", sfjfList);

		// 户口状态
		sql = "select distinct hkztdm dm,hkztmc mc from hkztdmb order by hkztdm";
		List<HashMap<String, String>> hkztList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxHkztList", hkztList);

		// 转档类别
		List<HashMap<String, String>> zdlbList = getXsxxSelectList("zdlb");
		request.setAttribute("xsxxZdlbList", zdlbList);

		// 异动类别
		sql = "select distinct ydlbm dm,ydlbmc mc from dm_ydlb order by ydlbm";
		List<HashMap<String, String>> ydlbList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxYdlbList", ydlbList);

		// 来源省
		sql = "select distinct sydqdm dm,sydq mc from dmk_sydq order by sydqdm";
		List<HashMap<String, String>> provList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxProvList", provList);

		// 培养层次
		sql = "select distinct pyccdm dm,pyccmc mc from xg_xsxx_pyccdmb order by pyccdm";
		List<HashMap<String, String>> pyccList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxPyccList", pyccList);
		
		// 学籍类别
		sql = "select distinct xjlbdm dm,xjlbmc mc from dm_xjlb order by xjlbdm";
		List<HashMap<String, String>> xjlbList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xjlbList", xjlbList);
		

		// 学籍类别
		sql = "select distinct xjlb dm,xjlb mc from view_xsxxb  where xjlb is not null order by xjlb";
		List<HashMap<String, String>> xjlbmcList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xjlbmcList", xjlbmcList);
		
	}

	/**
	 * 设置日常事务所需条件
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setRcswListList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		// 是否已通知
		List<HashMap<String, String>> sftzList = getRcswSelectList("sftz");

		request.setAttribute("rcswSftzList", sftzList);
	}

	/**
	 * 设置公寓管理所需条件
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setGyglListList(SearchForm searchForm, RequestForm rForm,
			User user, String[] PY_BIG, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		// 公寓管理员权限
		String gyglyQx = user.getGyglyQx();
		// 用户名
		String userName = user.getUserName();
		
		// 校区条件
		String sql = "select distinct t.dm dm,t.xqmc mc from dm_zju_xq t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.dm = a.xqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.dm";
		List<HashMap<String, String>> xqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglXqList", xqList);

		// 园区条件
		sql = "select distinct t.yqdm dm,t.yqmc mc from yqdmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.yqdm";
		List<HashMap<String, String>> yqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglYqList", yqList);

		// 楼栋条件
		sql = "select distinct t.lddm dm,t.ldmc mc from sslddmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by t.lddm";
		List<HashMap<String, String>> ldList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglLdList", ldList);

		// 层数条件
		sql = "select distinct t.cs dm,t.cs mc from sslddmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by to_number(cs)";
		
		List<HashMap<String, String>> csList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglCsList", csList);
		
		// 寝室条件
		List<HashMap<String, Object>> gyglQshList = setNewQsListBy(user,PY_BIG);
		
		request.setAttribute("gyglQshList", gyglQshList);
		
		// 可否混住
		List<HashMap<String, String>> gyglKfhzList = getGyglSelectList("kfhz");

		request.setAttribute("gyglKfhzList", gyglKfhzList);
		
		// 入住状态
		List<HashMap<String, String>> gyglRzztList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzztList", gyglRzztList);
		
		// 床位分配状态
		List<HashMap<String, String>> gyglCwfpList = getGyglSelectList("cwfp");
		
		request.setAttribute("gyglCwfpList", gyglCwfpList);
		
		// 性别限定
		List<HashMap<String, String>> gyglXbxdList = getGyglSelectList("xbxd");

		request.setAttribute("gyglXbxdList", gyglXbxdList);

		// 入住情况
		List<HashMap<String, String>> gyglRzqkList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzqkList", gyglRzqkList);
	}

	/**
	 * 设置评奖评优所需条件
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setPjpyListList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {
		// 性别限定
		List<HashMap<String, String>> pjpySfqrList = getPjpySelectList("sfqr");

		request.setAttribute("pjpySfqrList", pjpySfqrList);

		// 入住情况
		List<HashMap<String, String>> pjpySfpfList = getPjpySelectList("sfpf");
		
		request.setAttribute("pjpySfpfList", pjpySfpfList);
	}
	
	/**
	 * 设置寝室列表（带拼音）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, Object>> setNewQsListBy(User user,
			String[] PY_BIG) {
		List<HashMap<String, Object>> gyglQshList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();

		// 公寓管理员权限
		String gyglyQx = user.getGyglyQx();
		// 用户名
		String userName = user.getUserName();

		// 寝室条件
		String sql = "select distinct t.qsh from ssxxb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='" + userName + "') ";
		}
		sql += "order by qsh";

		List<HashMap<String, String>> qshList = dao.getList(sql,
				new String[] {}, new String[] { "qsh" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < qshList.size(); j++) {

				HashMap<String, String> map = qshList.get(j);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				if (py.equalsIgnoreCase(qshpy)) {

					HashMap<String, String> newQsh = new HashMap<String, String>();

					if (qsList.size() < 3) {

						newQsh.put("qshdm", qsh);
						newQsh.put("qshmc", qsh);

						qsList.add(newQsh);

					} else {
						newQsh.put("qshdm", "qsh_" + qshpy);
						newQsh.put("qshmc", "其他");

						qsList.add(newQsh);

						break;
					}

				}
			}

			HashMap<String, Object> qsMap = new HashMap<String, Object>();
			qsMap.put("py", py);
			qsMap.put("qsList", qsList);

			gyglQshList.add(qsMap);
		}
		return gyglQshList;
	}
	
	/**
	 * 设置部门列表（带拼音）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> setNewBmListBy(String[] PY_BIG) {
		
		List<HashMap<String, Object>> commBmList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();
		// 寝室条件
		String sql = "select distinct bmdm,bmmc from zxbz_xxbmdm where bmmc is not null order by bmdm";
		
		List<HashMap<String, String>> bmList = dao.getList(sql,
				new String[] {}, new String[] { "bmdm","bmmc" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bmPyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < bmList.size(); j++) {

				HashMap<String, String> map = bmList.get(j);

				String bmdm = map.get("bmdm");
				String bmmc = map.get("bmmc");
				String bmpy = CnToEnUtil.getFirstLetter(bmmc);

				if (py.equalsIgnoreCase(bmpy)) {

					HashMap<String, String> newBm = new HashMap<String, String>();

					newBm.put("bmdm", bmdm);
					newBm.put("bmmc", bmmc);

					bmPyList.add(newBm);

				}
			}

			HashMap<String, Object> bmMap = new HashMap<String, Object>();
			bmMap.put("py", py);
			bmMap.put("bmList", bmPyList);

			commBmList.add(bmMap);
		}
		return commBmList;
	}
	
	/**
	 * 获得无需维护下拉框值（通用）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCommSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("shzt".equalsIgnoreCase(lx)) {// 审核状态
			dm = new String[] { "未审核", "通过", "不通过", "退回", "需重审" };
			mc = new String[] { "未审核", "通过", "不通过", "退回", "需重审" };
		} else if ("sf".equalsIgnoreCase(lx)) {// 是否
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if("shjg".equalsIgnoreCase(lx)){//审核结果
			dm = new String[] { "未审核", "审核中", "通过", "不通过", "退回" };
			mc = new String[] { "未审核", "审核中", "通过", "不通过", "退回" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得无需维护下拉框值（学生信息）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sfzx".equalsIgnoreCase(lx)) {// 是否在校
			dm = new String[] { "在校", "不在校" };
			mc = new String[] { "在校", "不在校" };
		} else if ("sfjf".equalsIgnoreCase(lx)) {// 是否缴费
			dm = new String[] { "已缴", "未缴" };
			mc = new String[] { "已缴", "未缴" };
		} else if ("zdlb".equalsIgnoreCase(lx)) {// 转档类别
			dm = new String[] { "毕业", "升学", "转学", "退学" };
			mc = new String[] { "毕业", "升学", "转学", "退学" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得无需维护下拉框值（日常事务）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRcswSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sftz".equalsIgnoreCase(lx)) {// 是否通知
			dm = new String[] { "已通知", "未通知" };
			mc = new String[] { "已通知", "未通知" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得无需维护下拉框值（公寓管理）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGyglSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("kfhz".equalsIgnoreCase(lx)) {// 可否混住
			dm = new String[] { "可以", "不可" };
			mc = new String[] { "可以", "不可" };
		} else if ("rzzt".equalsIgnoreCase(lx)) {// 入住状态
			dm = new String[] { "满员", "缺额", "全空" };
			mc = new String[] { "满员", "缺额", "全空" };
		} else if ("cwfp".equalsIgnoreCase(lx)) {// 床位分配状态
			dm = new String[] { "已入住", "可分配" };
			mc = new String[] { "已入住", "可分配" };
		} else if ("xbxd".equalsIgnoreCase(lx)) {// 性别限定
			dm = new String[] { "男", "女","混合" };
			mc = new String[] { "男", "女","混合" };
		} 

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得无需维护下拉框值（评奖评优）
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getPjpySelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sfpf".equalsIgnoreCase(lx)) {// 是否评分
			dm = new String[] { "是", "否", "重新评分" };
			mc = new String[] { "是", "否", "重新评分"  };
		} else if ("sfqr".equalsIgnoreCase(lx)) {// 是否确认
			dm = new String[] { "是", "否", "退回" };
			mc = new String[] { "是", "否", "退回" };
		} 
		
		return dao.arrayToList(dm, mc);
	}
	
	
	/********************************************************************************************************************************/
	/**************************************************公寓管理（第三版）高级查询条件**************************************************/
	/********************************************************************************************************************************/
	/**
	 * 设置公寓管理所需条件
	 * @author zhangh	  
	 */
	public void setGyglListList_Third(SearchForm searchForm, RequestForm rForm,
			User user, String[] PY_BIG, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		// 公寓管理员权限
		String gyglyQx = user.getGyglyQx();
		// 用户名
		String userName = user.getUserName();
		
		String version = XMLReader.getFlowControl("gygl", "gyversion");
		request.setAttribute("gyversion", version);
		
		// 校区条件_未修改
		String sql = "select distinct t.dm dm,t.xqmc mc from dm_zju_xq t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.dm = a.xqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.dm";
		List<HashMap<String, String>> xqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglXqList", xqList);

		// 园区条件_未修改
		sql = "select distinct t.yqdm dm,t.yqmc mc from yqdmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.yqdm";
		List<HashMap<String, String>> yqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglYqList", yqList);

		// 楼栋条件_已修改
		sql = "select distinct t.lddm dm,t.ldmc mc from xg_gygl_new_ldxxb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by t.lddm";
		List<HashMap<String, String>> ldList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglLdList", ldList);

		//层号条件_已修改
		sql = "select distinct t.ch dm,t.chmc mc from view_xg_gygl_new_cwxx t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by to_number(ch)";
		
		List<HashMap<String, String>> chList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglChList", chList);
		
		// 层数条件_已修改
		sql = "select distinct t.ldcs dm,t.ldcs mc from xg_gygl_new_ldxxb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by to_number(ldcs)";
		
		List<HashMap<String, String>> csList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglCsList", csList);
		
		// 寝室条件_已修改
		List<HashMap<String, Object>> gyglQshList = setNewQsList_Third(user,PY_BIG);
		
		request.setAttribute("gyglQshList", gyglQshList);
		
		// 可否混住
		List<HashMap<String, String>> gyglKfhzList = getGyglSelectList("kfhz");

		request.setAttribute("gyglKfhzList", gyglKfhzList);
		
		// 入住状态
		List<HashMap<String, String>> gyglRzztList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzztList", gyglRzztList);
		
		// 床位分配状态
		List<HashMap<String, String>> gyglCwfpList = getGyglSelectList("cwfp");
		
		request.setAttribute("gyglCwfpList", gyglCwfpList);
		
		// 性别限定
		List<HashMap<String, String>> gyglXbxdList = getGyglSelectList("xbxd");

		request.setAttribute("gyglXbxdList", gyglXbxdList);

		// 入住情况
		List<HashMap<String, String>> gyglRzqkList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzqkList", gyglRzqkList);
		
		// 退宿原因
		sql = "select distinct tsyydm,tsyymc dm,tsyymc mc from xg_gygl_new_tsyydmb order by to_number(tsyydm)";
		List<HashMap<String, String>> tsyyList = dao.getList(sql,new String[] {}, new String[] { "dm", "mc" });
		
		request.setAttribute("gyglTsyyList", tsyyList);
		
		//是否入住
		List<HashMap<String, String>> gyglSfrzList = getGyglSelectList("sfrz");
		
		request.setAttribute("gyglSfrzList", gyglSfrzList);
		
		//是否分配
		List<HashMap<String, String>> gyglSffpList = getGyglSelectList("sffp");
		
		request.setAttribute("gyglSffpList", gyglSffpList);
		
	}
	
	/**
	 * 设置寝室列表（带拼音）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, Object>> setNewQsList_Third(User user,String[] PY_BIG) {
		List<HashMap<String, Object>> gyglQshList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();
		
//		 公寓管理员权限
		String gyglyQx = user.getGyglyQx();
		// 用户名
		String userName = user.getUserName();
		
		// 寝室条件
		String sql = "select qsh from view_xg_gygl_new_qsxx t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql += "order by qsh";
		
		List<HashMap<String, String>> qshList = dao.getList(sql,
				new String[] {}, new String[] { "qsh" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < qshList.size(); j++) {

				HashMap<String, String> map = qshList.get(j);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				if (py.equalsIgnoreCase(qshpy)) {

					HashMap<String, String> newQsh = new HashMap<String, String>();

					if (qsList.size() < 3) {

						newQsh.put("qshdm", qsh);
						newQsh.put("qshmc", qsh);

						qsList.add(newQsh);

					} else {
						newQsh.put("qshdm", "qsh_" + qshpy);
						newQsh.put("qshmc", "其他");

						qsList.add(newQsh);

						break;
					}

				}
			}

			HashMap<String, Object> qsMap = new HashMap<String, Object>();
			qsMap.put("py", py);
			qsMap.put("qsList", qsList);

			gyglQshList.add(qsMap);
		}
		return gyglQshList;
	}

}

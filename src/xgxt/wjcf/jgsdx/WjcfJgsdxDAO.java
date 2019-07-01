
package xgxt.wjcf.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.jgsdx.CxcfSqSaveModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学违纪处分DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxDAO {

	DAO dao = DAO.getInstance(); 
	ArrayList<String> values = new ArrayList<String>();// 查询条件值
	
	/**
	 * 通过学号获取学生相关信息
	 * getStuInfo ---- 获取学生信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		HashMap<String, String> userMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc from view_xsjbxx where xh=? and rownum=1";
		userMap = dao.getMapNotOut(sql, new String[]{xh});
		return userMap;
	}
	
	/**
	 * 保存前检查学生处分时间是否满一年
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		boolean bFlag = false;
		String sql = "select round(months_between(to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_date('" + cfsj + "','yyyy-mm-dd'))) hg from dual";
		String[] flag = dao.getOneRs(sql, new String[]{}, new String[]{"hg"});
		if ((flag.length > 0 && (!StringUtils.isNull(flag[0])))) {
			int iTj = Integer.parseInt(flag[0]);//违纪处分时间长度
			if (iTj >= 12) {
				bFlag = true;
			}
		}
		return bFlag;
	}
	
	/**
	 * 保存撤消处分信息
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = cxcfSaveModel.getXh();
		String xn = cxcfSaveModel.getXn();
		String xq = cxcfSaveModel.getXq();
		String cfwh = DealString.toGBK(cxcfSaveModel.getCfwh());
		String cfsj = cxcfSaveModel.getCfsj();
		String cflbmc = DealString.toGBK(cxcfSaveModel.getCflbmc());
		String cfyymc = DealString.toGBK(cxcfSaveModel.getCfyymc());
		String bz = DealString.toGBK(cxcfSaveModel.getBz());
		String cxsj = cxcfSaveModel.getCxsj();
		bFlag = StandardOperation
				.insert("wjcf_cxcfb", new String[] { "xh", "xn", "xq", "cfwh",
						"cfsj", "cflbmc", "cfyymc", "bz", "cxsqsj" },
						new String[] { xh, xn, xq, cfwh, cfsj, cflbmc, cfyymc,
								bz, cxsj }, request);
		return bFlag;
	}
	
	/**
	 * 撤消处分查询表头
	 * getCxcfSearchTitle ---- 撤消处分查询表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSearchTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","cflbmc","cfyymc","cfwh","cfsj","cxwh","cxsj"};
		String[] cn = new String[]{"主键","学号","姓名","学年","学期","处分类别","处分原因","处分文号","处分时间","解除文号","解除时间"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * 撤消处分查询结果
	 * getCxcfSearchResult ---- 撤消处分查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSearchResult(CxcfQryModel cxcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(cxcfModel);
		String[] opList = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","cflbmc","cfyymc","cfwh","cfsj","cxwh","cxsj"};
		String sql = "select xh||cfwh||cfsj,xh,xm,xn,xq,cflbmc,cfyymc,cfwh,cfsj,cxwh,cxsj from view_wjcf_cxcf where 1=1";
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 公用方法：获取查询条件
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(CxcfQryModel cxcfModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = cxcfModel.getXn();
		String xh = DealString.toGBK(cxcfModel.getXh());
		String xm = DealString.toGBK(cxcfModel.getXm());
		String xydm = cxcfModel.getXydm();
		String zydm = cxcfModel.getZydm();
		String bjdm = cxcfModel.getBjdm();
		String nj = cxcfModel.getNj();
		String xq = cxcfModel.getXq();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}//end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm like ?");
			values.add(xm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		return whereSql;
	}
	
	/**
	 * 通过主键获取学生相关信息
	 * getStuInfo1 ---- 通过主键获取学生相关信息 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo1(String pkValue) throws Exception {
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sql = "select xh,xm,xymc,bjmc,cfyymc,cfsj from view_wjcf where xh||cfwh||cfsj=?";
		stuMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return stuMap;
	}
	
	/**
	 * 撤消处分批量删除
	 * wjcfCxcfPlDel ---- 违纪处分撤消处分批量删除 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String wjcfCxcfPlDel(String[] keys, HttpServletRequest request) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from wjcf_cxcfb where xh||cfwh||cfsj = '" + whichxh
					+ "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 撤消处分审批查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSpTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","bjmc","cflbmc","cfyymc","cfwh","cfsj","cxsqsj"};
		String[] cn = new String[]{"主键","学号","姓名","学年","学期","班级名称","处分类别","处分原因","处分文号","处分时间","撤消申请时间"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * 撤消处分审批查询结果
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSpResult(CxcfQryModel cxcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(cxcfModel);
		String[] opList = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","bjmc","cflbmc","cfyymc","cfwh","cfsj","cxsqsj"};
		String sql = "select xh||cfwh||cfsj,xh,xm,xn,xq,bjmc,cflbmc,cfyymc,cfwh,cfsj,cxsqsj from view_wjcf_cxcf where 1=1";
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 通过主键获取撤消处分相关信息
	 * getCxcfInfoByPk ---- 通过主键获取撤消处分相关信息 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfInfoByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh||a.cfwh||a.cfsj,a.xh,a.nj,a.xymc,a.zymc,a.xm,a.xn,a.xq,a.bjmc,a.cflbmc,a.cfyymc,a.cxsqsj,a.cfwh,a.cfsj,a.cxsj,a.bz,a.cxwh,a.cxsj,a.spzt,b.nd,b.xn,b.xb from view_wjcf_cxcf a left join view_wjcf b on a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj where a.xh||a.cfwh||a.cfsj=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 返回审核列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChList(int type) throws Exception {
		return dao.getChkList(type);
	}
	
	/**
	 * 撤消处分审批
	 * cxcfSp ---- 撤消处分审批 
	 * @param cxcfsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean cxcfSp(CxcfSpSaveModel cxcfsqModel, HttpServletRequest request) throws Exception {
		boolean bFlag =false;
		String pkValue = cxcfsqModel.getPkValue();
		String spzt = DealString.toGBK(cxcfsqModel.getSpzt());
		spzt = !StringUtils.isNull(spzt) ? spzt.trim() : "";
		String cxwh = DealString.toGBK(cxcfsqModel.getCxwh());
		String cxsj = cxcfsqModel.getCxsj();
		bFlag = StandardOperation.update("wjcf_cxcfb", new String[]{"spzt","cxwh","cxsj"}, new String[]{spzt,cxwh,cxsj}, "xh||cfwh||cfsj", pkValue, request);
		if (bFlag) {//更新成功后更新WJCF表里面的解除文号和时间
			StandardOperation.update("wjcfb", new String[]{"cxwh", "cxsj"}, new String[]{cxwh,cxsj}, "xh||cfwh||cfsj", pkValue, request);
		}
		return bFlag;
	}
	
	/**
	 * 学生违纪处分相关信息
	 * getStuWjcfinfo ---- 学生违纪处分相关信息 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfinfo(String xh) throws Exception {
		String sql = "select xh||cfwh||cfsj,xh,xm,xb,bjmc,cflbmc,cfyymc,cfsj,cfwh,spzt,cxwh,cxsj from view_wjcf_cxcf where xh=?";
		List<String[]> stuList = new ArrayList<String[]>();
		stuList = dao.rsToVator(sql, new String[]{xh}, new String[]{"xh||cfwh||cfsj","xh","xm","xb","bjmc","cflbmc","cfyymc","cfsj","cfwh","spzt","cxwh","cxsj"});
		return stuList;
	}
	
	/**
	 * 学生违纪处分相关信息表头
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStuWjcfTit(String xh) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xb","bjmc","cflbmc","cfyymc","cfsj","cfwh","spzt","cxwh","cxsj"};
		String[] cn = new String[]{"主键","学号","姓名","性别","班级名称","处分类别","处分原因","处分时间","处分文号","学校审批","解除文号","解除时间"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
}

package xgxt.pjpy.scjz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyScjzDAO {

	DAO dao = DAO.getInstance();
	
	public static PjpyScjzDAO mydao = new PjpyScjzDAO();
	
	public static PjpyScjzDAO getInstance() {
		return mydao;
	}
	
	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * 获取学生基本信息加学习成绩信息班级排名，专业排名
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuDetailsInfo(String xh) throws Exception {
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,bjdm,zydm,(select b.wysp from xsjxjb b where b.xh=a.xh and b.xn=? and b.xq=? and rownum=1) wysp,(select b.jsjsp from xsjxjb b where b.xh=a.xh and b.xn=? and b.xq=? and rownum=1) jsjsp,(select b.drzw from xsjxjb b where b.xh=a.xh and b.xn=? and b.xq=? and rownum=1) drzw,(select b.sjhm from xsjxjb b where b.xh=a.xh and b.xn=? and b.xq=? and rownum=1) sjhm,(select b.khss from xsjxjb b where b.xh=a.xh and b.xn=? and b.xq=? and rownum=1) khss,(select b.kh from xsjxjb b where b.xh=a.xh and b.xn=? and b.xq=? and rownum=1) kh from view_xsjbxx a where xh=?";
		HashMap<String, String> rs = dao.getMapNotOut(sql, new String[]{xn,xq,xn,xq,xn,xq,xn,xq,xn,xq,xn,xq,xh});
		xq = !StringUtils.isNull(xq) && xq.length()==2 ? xq.substring(1, 2) : xq;
		String pjcj = dao
				.getOneRs(
						"select trunc(avg(cj),2) cj from view_zhhcjb where kcxz not like '%选修%' and xn=? and xq=? and xh=?",
						new String[] { xn, xq, xh },"cj");
		String zdcj = dao.getOneRs("select min(cj) cj from view_zhhcjb where kcxz not like '%选修%' and xn=? and xq=? and xh=?", new String[]{xn,xq,xh}, "cj");
		String bjpm = dao.getOneRs("select bjpm from (select xh,(dense_rank() over " +
				"(partition by bjdm order by to_number(cj) desc nulls last)) bjpm " +
				"from (select xh,bjdm,avg(cj) cj from view_cjb where bjdm=? " +
				"and xn=? and xq=? and kcxz not like '%选修%' group by xh,bjdm)) " +
				"where xh=?", new String[]{rs.get("bjdm"), xn, xq, xh}, "bjpm");
		String zypm = dao.getOneRs("select zypm from (select xh,(dense_rank() over " +
				"(partition by zydm order by to_number(cj) desc nulls last)) zypm " +
				"from (select xh,zydm,avg(cj) cj from view_cjb where zydm=? " +
				"and xn=? and xq=? and kcxz not like '%选修%' group by xh,zydm)) " +
				"where xh=?", new String[]{rs.get("zydm"), xn, xq, xh}, "zypm");
		rs.put("pjcj", pjcj);
		rs.put("zdcj", zdcj);
		rs.put("bjpm", bjpm);
		rs.put("zypm", zypm);
		return rs;
	}
	
	/**
	 * 获取学生评奖学年的处分信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfInfo(String xh) throws Exception {
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		//xq = !StringUtils.isNull(xq) && xq.length()==2 ? xq.substring(1, 2) : xq;
		List<String[]> cfList = dao
		.rsToVator(
				"select xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,(select b.cflbmc from cflbdmb b where a.cflb=b.cflbdm) cflb,(select b.cfyymc from cfyydmb b where a.cfyy=b.cfyydm) cfyy from wjcfb a where xh=? and xn=? and xq=? and xxsh='通过'",
				new String[] { xh.trim(), xn, xq },
				new String[] {"xn", "xq", "cfyy", "cflb"});
		return cfList;
	}
	
	/**
	 * 获取奖学金类别和金额
	 * @param jxjdm
	 * @return
	 */
	public String getJxjlbandje(String jxjdm) {
		String[] info = dao.getOneRs(
				"select jxjlb,jlje from jxjdmb where jxjdm=?",
				new String[] { jxjdm }, new String[] { "jxjlb", "jlje" });
		if (info != null && info.length==2) {
			String tmp = info[0];
			tmp += "-";
			tmp += info[1];
			return tmp;
		}
		return "";
	} 
	
	/**
	 * 保存奖学金申请信息
	 * @param model
	 * @param reuqest
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjsqInfo(ScjzModel model, HttpServletRequest reuqest)
			throws Exception {
		return StandardOperation.insert("xsjxjb", new String[] { "xh", "xn",
				"xq", "nd","jxjdm", "drzw", "sjhm", "wysp", "jsjsp", "khss", "kh",
				"sqly" }, new String[] { model.getXh(), Base.getJxjsqxn(),
				Base.getJxjsqxq(),Base.getJxjsqnd(), model.getJxjdm(),
				DealString.toGBK(model.getDrzw()),
				DealString.toGBK(model.getSjhm()),
				DealString.toGBK(model.getWysp()),
				DealString.toGBK(model.getJsjsp()),
				DealString.toGBK(model.getKhss()), model.getKh(),
				DealString.toGBK(model.getSqly()) }, reuqest);
	}
	
	/**
	 * 修改奖学金信息
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjsqInfo(ScjzModel model, String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[]{ "jxjdm", "drzw", "sjhm", "wysp", "jsjsp", "khss", "kh",
				"sqly"}, new String[]{ model.getJxjdm(),
				DealString.toGBK(model.getDrzw()),
				DealString.toGBK(model.getSjhm()),
				DealString.toGBK(model.getWysp()),
				DealString.toGBK(model.getJsjsp()),
				DealString.toGBK(model.getKhss()), model.getKh(),
				DealString.toGBK(model.getSqly())}, "xn||nd||jxjdm||xh", pkValue, request);
	}
	
	public HashMap<String, String> viewJxjSqInfo(String pkValue) throws Exception {
		return dao.getMapNotOut("select * from view_xsjxjb where xn||nd||jxjdm||xh=?", new String[]{pkValue});
	}
	
	/**
	 * 删除奖学金信息
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteJxjSqInfo(String[] keys) throws Exception {
		String rs = "";
		StringBuffer sql = new StringBuffer();
		String[] sqls = new String[keys.length];
		for (int i=0;i<keys.length;i++) {
			sql = new StringBuffer();
			sql.append("delete from xjsxjb where xh||xn||xq||jxjdm = '");
			sql.append(keys[i]);
			sql.append("'");
			sqls[i] = sql.toString();
		}
		int[] flag = dao.runBatch(sqls);
		for (int i=0;i<flag.length;i++) {
			if (flag[i] < 1) {
				rs += (i+1)+ ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length()-1);
	}
	
	/**
	 * 查询奖学金审核信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjshInfo(String pkValue) throws Exception {
		return dao.getMapNotOut("select * from view_xsjxjb where xn||nd||xh||jxjdm=?", new String[]{pkValue});
	}
	
	/**
	 * 辅导员审核
	 * @param pkValue
	 * @param cxcj
	 * @param yesNo
	 * @param shyj
	 * @return
	 * @throws Exception
	 */
	public String fdysh(String pkValue, String cxcj, String yesNo, String shyj, String jxjdm, String jlje, String xydm)
			throws Exception {
		String xytgje = dao.getOneRs("select sum(jlje) je from view_xsjxjb where xn=? and xq=? and xydm=? and jxjdm=? and fdysh='通过'", new String[]{Base.getJxjsqxn(), Base.getJxjsqxq(),xydm, jxjdm}, "je");
		String jsje = dao
		.getOneRs(
				"select sum(to_number(jsje)) jsje from xyjxjrs where bmdm=? and bmlb='xydm' and jxjdm=? and xn=? and nd=?",
				new String[] {xydm,jxjdm,Base.getJxjsqxn(),Base.getJxjsqnd()}, "jsje");
		double xyje = StringUtils.isNull(xytgje) ? 0 : Double.parseDouble(xytgje);
		double xxje = StringUtils.isNull(jsje) ? 0 : Double.parseDouble(jsje);
		double djlje = StringUtils.isNull(jlje) ? 0 : Double.parseDouble(jlje);
		if ((xyje+djlje) > xxje && "通过".equalsIgnoreCase(DealString.toGBK(yesNo))) {
			return (xyje+djlje) + "-" + xxje;
		} else {
			dao
				.runUpdate(
						"update xsjxjb set cxcj=?,fdysh=?,fdyyj=? where xn||nd||xh||jxjdm=?",
						new String[] { DealString.toGBK(cxcj),
								DealString.toGBK(yesNo),
								DealString.toGBK(shyj), pkValue });
		}
		return "";
	}

	public String xysh(String pkValue, String cxcj, String yesNo, String shyj, String jxjdm, String jlje, String xydm)
			throws Exception {
		String xytgje = dao.getOneRs("select sum(jlje) je from view_xsjxjb where xn=? and xq=? and xydm=? and jxjdm=? and xysh='通过'", new String[]{Base.getJxjsqxn(), Base.getJxjsqxq(),xydm, jxjdm}, "je");
		String jsje = dao
		.getOneRs(
				"select sum(to_number(jsje)) jsje from xyjxjrs where bmdm=? and bmlb='xydm' and jxjdm=? and xn=? and nd=?",
				new String[] {xydm,jxjdm,Base.getJxjsqxn(),Base.getJxjsqnd()}, "jsje");
		double xyje = StringUtils.isNull(xytgje) ? 0 : Double.parseDouble(xytgje);
		double xxje = StringUtils.isNull(jsje) ? 0 : Double.parseDouble(jsje);
		double djlje = StringUtils.isNull(jlje) ? 0 : Double.parseDouble(jlje);
		if ((xyje+djlje) > xxje && "通过".equalsIgnoreCase(DealString.toGBK(yesNo))) {
			return (xyje+djlje) + "-" + xxje;
		} else {
		dao.runUpdate(
				"update xsjxjb set xysh=?,xyshyj=? where xn||nd||xh||jxjdm=?",
				new String[] { DealString.toGBK(yesNo), DealString.toGBK(shyj),
						pkValue });
		}
		return "";
	}

	public boolean xxsh(String pkValue, String yesNo, String shyj)
			throws Exception {
		return dao.runUpdate(
				"update xsjxjb set xxsh=?,xxshyj=? where xn||nd||xh||jxjdm=?",
				new String[] { DealString.toGBK(yesNo), DealString.toGBK(shyj),
						pkValue });
	}
	
	/**
	 * 通用计算综合素质总分
	 * 通过德成绩,智成绩,体成绩,自动计算总分
	 * @param dcj
	 * @param zcj
	 * @param tcj
	 * @return
	 */
	public String countZf(String dcj, String zcj, String tcj) {
		String zf = "";
		String[] zfblList = dao.getOneRs("select distinct (case when dybl is null" +
				" then '0' else dybl end) dybl,(case when zybl is null then '0'" +
				" else zybl end) zybl,(case when tybl is null then '0' else tybl end) " +
				"tybl from zjcm_zhszcpblszb", new String[]{}, new String[]{"dybl", "zybl", "tybl"});
		double[] blList = stringToDouble(zfblList);
		double dyf = StringUtils.isNull(dcj) ? 0 : Double.parseDouble(dcj.trim());
		double tyf = StringUtils.isNull(tcj) ? 0 : Double.parseDouble(tcj.trim());
		double zyf = StringUtils.isNull(zcj) ? 0 : Double.parseDouble(zcj.trim());
		if (blList != null && blList.length ==3) {
			dyf = dyf*blList[0]/100;
			zyf = zyf*blList[1]/100;
			tyf = tyf*blList[2]/100;
		}
		double df = dyf + zyf + tyf;
		zf = doubleFormat(df) + "";
		return zf;
	}
	
	public String doubleFormat(double d) {
		if (d > 0) {
			DecimalFormat   df   =   new   DecimalFormat("0.##");   
	        return   df.format(d);   
		}
		return "";
	}
	
	public double[] stringToDouble(String[] zfblList) {
		if (zfblList != null && zfblList.length > 0) {
			double[] blList = new double[zfblList.length];
			for (int i = 0; i < zfblList.length; i++) {
				blList[i] = StringUtils.isNull(zfblList[i]) ? 1 : Double
						.parseDouble(zfblList[i].trim());
			}
			return blList;
		}
		return new double[]{1,1,1};
	}
}

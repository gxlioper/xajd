package xgxt.wjcf.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class KswjCfbxDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = new ArrayList<String>(); //条件及值
	
	/**
	 * 处分表现表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfbxTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "cxrs", "zc", "jlr", "rq" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "年级", "班级名称",
				"出席人数", "主持", "记录人", "日期" };
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql1(KswjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(model.getNd());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getCfyy())) {
			whereSql.append(" and cfyy = ?");
			values.add(model.getCfyy());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		return whereSql;
	}
	
	public List<String[]> cfbxResult(KswjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xh||rq pk,rownum,xh,xm,nj,bjmc,cxrs,zc,jlr,rq from view_kswjcfhbxb where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "cxrs", "zc", "jlr", "rq" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? 
				values.toArray(new String[0]) : new String[]{}, opList);
	}
	
	public HashMap<String, String> stuDetails(String xh) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?", new String[]{xh});
	} 
	
	public boolean kswjcfbxSave(KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("kswjcfhbxb", new String[] { "xh",
				"zc", "cxryqd", "cxrs", "hyjl", "jlr", "rq" }, new String[] {model.getXh()
				, DealString.toGBK(model.getZc()),
				DealString.toGBK(model.getCxryqd()), model.getCxrs(),
				DealString.toGBK(model.getHyjl()),
				DealString.toGBK(model.getJlr()), model.getRq() },
				request);
	}
	
	public HashMap<String, String> cfbxInfo(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,rq,jlr,hyjl,cxryqd,cxrs,zc from view_kswjcfhbxb where xh||rq = ?",
						new String[] { pkValue });
	}
	
	public boolean cfbxModiSave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		if (StringUtils.isEqual(pkValue, model.getXh() + model.getRq())) {
			bFlag = StandardOperation.update("kswjcfhbxb", new String[] { "zc",
					"cxryqd", "cxrs", "hyjl", "jlr", "rq" }, new String[] {
					DealString.toGBK(model.getZc()),
					DealString.toGBK(model.getCxryqd()),
					DealString.toGBK(model.getCxrs()),
					DealString.toGBK(model.getHyjl()),
					DealString.toGBK(model.getJlr()), model.getRq() },
					"xh||rq", pkValue, request);
		} else {
			StandardOperation.delete("kswjcfhbxb", "xh||rq", pkValue, request);
			/*StandardOperation.delete("kswjcfhbxb", "xh||rq", model.getXh()
					+ model.getRq(), request);*/
			bFlag = StandardOperation.insert("kswjcfhbxb", new String[] { "xh",
					"zc", "cxryqd", "cxrs", "hyjl", "jlr", "rq" }, new String[] {model.getXh()
					, DealString.toGBK(model.getZc()),
					DealString.toGBK(model.getCxryqd()), model.getCxrs(),
					DealString.toGBK(model.getHyjl()),
					DealString.toGBK(model.getJlr()), model.getRq() },
					request);
		}
		return bFlag;
	}
	
	public String cfbcDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("kswjcfhbxb", "xh||rq", keys[i], request);
			if (!bFlag) {//记录删除失败的数据行
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	/**
	 * 处分表现表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> bgdTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "cflbmc", "cfyymc" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "年级", "班级名称",
				"处分类别","处分原因" };
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> bgdResult(KswjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xh||sbsj pk,rownum,xh,xm,nj,bjmc,cflbmc,cfyymc from view_kswjbgdb where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "cflbmc", "cfyymc" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public HashMap<String, String> stuCfxx(String xh) throws Exception {
		return dao.getMapNotOut("select csrq,mzmc,jg,zzmmmc,xh,xm,xb,nj,xymc,zymc,bjmc,(select distinct b.cflbmc from view_kswjcf b where view_xsxxb.xh=b.xh) cflbmc,(select distinct b.cfyymc from view_kswjcf b where view_xsxxb.xh=b.xh) cfyymc,(select distinct b.cflb from view_kswjcf b where view_xsxxb.xh=b.xh) cflb, (select distinct b.cfyy from view_kswjcf b where view_xsxxb.xh=b.xh) cfyy,(select distinct b.cfjb from view_kswjcf b where view_xsxxb.xh=b.xh) cfjb from view_xsxxb where xh=?", new String[]{xh});
	} 
	
	public HashMap<String, String> stuKswjxx(String pkValue) throws Exception {
		return dao.getMapNotOut("select a.*,b.csrq,b.mzmc,b.jg,b.zzmmmc from view_kswjcf a left join view_xsxxb b on a.xh=b.xh where a.xh||a.xn||a.xq||a.sbsj=?", new String[]{pkValue});
	} 
	
	public boolean bgdSave(KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("kswjbgdb", new String[] { "xh",
				"cflb", "cfyy", "cfhbx", "jcjl", "bz", "bzryj", "xyyj",
				"kbbmyj", "xxyj", "xscyj","cfjb" }, new String[] { model.getXh(),
				model.getCflb(), model.getCfyy(),
				DealString.toGBK(model.getCfhbx()),
				DealString.toGBK(model.getJcjl()),
				DealString.toGBK(model.getBz()),
				DealString.toGBK(model.getBzryj()),
				DealString.toGBK(model.getXyyj()),
				DealString.toGBK(model.getKbbmyj()),
				DealString.toGBK(model.getXxyj()),
				DealString.toGBK(model.getXscyj()),model.getCfjb() },
				request);
	}
	
	public String bgdDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("kswjbgdb", "xh||sbsj", keys[i], request);
			if (!bFlag) {//记录删除失败的数据行
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	public HashMap<String, String> bgdinfo(String pkValue) throws Exception {
		return dao.getMapNotOut("select a.*,b.csrq,b.mzmc,b.jg,b.zzmmmc from view_kswjbgdb a left join view_xsxxb b on a.xh=b.xh where a.xh||a.sbsj=?", new String[]{pkValue});
	}
	
	public boolean bgdmodisave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		bFlag = StandardOperation.update("kswjbgdb", new String[] { 
					 "cfhbx", "jcjl", "bz", "bzryj", "xyyj",
					"kbbmyj", "xxyj", "xscyj" }, new String[] {
							DealString.toGBK(model.getCfhbx()),
							DealString.toGBK(model.getJcjl()),
							DealString.toGBK(model.getBz()),
							DealString.toGBK(model.getBzryj()),
							DealString.toGBK(model.getXyyj()),
							DealString.toGBK(model.getKbbmyj()),
							DealString.toGBK(model.getXxyj()),
							DealString.toGBK(model.getXscyj()) }, "xh||sbsj", pkValue, request);
			
		return bFlag;
	}
	
	public HashMap<String, String> kswjprint(String pkVal) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,xymc,zymc,bjmc,nj,xyclyj,jtwjsy,zacfqk,qtcfqk,xxclyj,(select a.rxrq from view_xsxxb a where a.xh=view_kswjcf.xh) rxrq,(select a.sfzh from view_xsxxb a where a.xh=view_kswjcf.xh) sfzh from view_kswjcf where xh||xn||xq||sbsj = ?", new String[]{pkVal});
	}
	
	/**
	 * 考试违纪处分表现报表
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kswjcfbxPrint(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,rq,zc,jlr,cxryqd,cxrs,hyjl from view_kswjcfhbxb where xh||rq = ?",
						new String[] { pkValue });
	}
	
	public HashMap<String, String> kswjbgdprint(String pkValue) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,cfjb,cfyymc,cflbmc," +
				"jcjl,bz,bzryj,xyyj,xxyj,xscyj,kbbmyj,(select b.jg from view_xsxxb b " +
				"where b.xh=view_kswjbgdb.xh) jg,(select b.sfzh from view_xsxxb b" +
				" where b.xh=view_kswjbgdb.xh) sfzh,(select b.zzmmmc from view_xsxxb b " +
				"where b.xh=view_kswjbgdb.xh) zzmm,(select b.mzmc from view_xsxxb b " +
				"where b.xh=view_kswjbgdb.xh) mz from view_kswjbgdb where xh||sbsj=?", new String[]{pkValue});
	}
	
	public HashMap<String, String> kswjjybzprint(String pkValue) throws Exception {
		return dao.getMapNotOut("select a.*,b.csrq,b.sfzh,(select to_char(sysdate,'yyyymmdd') from dual) currdate from view_kswjcf a left join view_xsxxb b on a.xh=b.xh where a.xh||a.xn||a.xq||a.sbsj=?", new String[]{pkValue});
	}
}

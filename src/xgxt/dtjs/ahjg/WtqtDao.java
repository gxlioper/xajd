package xgxt.dtjs.ahjg;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;

public class WtqtDao {

	DAO dao = DAO.getInstance();

	public HashMap<String, String> getXsxxInfo(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
//		String sql = "select a.*,b.xn,b.xq,b.xsczqk from (select a.xh, a.xm,a.xb, a.csrq, a.mzmc, a.zzmmmc, a.sfzh, a.syd lydq, a.syd csdm,"
//				+ "a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd, "
//				+ "(select ksh from bks_xsjbxx b where a.xh=b.xh) ksh,b.JTCY1_XM,b.JTCY2_XM,b.jtszd ,a.zw,a.jg"
//				+ " from view_xsxxb a left join xsfzxxb b on a.xh=b.xh) a,ahjg_wtqt b where a.xh=b.xh and a.xh||xn||xq=? ";
//		
		String sql = "select * from (select a.xh, a.xm,a.xb, a.csrq, a.mzmc, a.zzmmmc, a.sfzh, a.syd lydq, a.syd csdm,"
			+ "a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd, "
			+ "(select ksh from bks_xsjbxx b where a.xh=b.xh) ksh,b.JTCY1_XM,b.JTCY2_XM,b.jtszd ,a.zw,a.jg"
			+ " from view_xsxxb a left join xsfzxxb b on a.xh=b.xh) where xh=? ";
//		System.out.println(sql);
		map = dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm", "ssbh",
				"nj", "xymc", "zymc", "bjmc", "xz", "xjztm", "lxdzxx", "lxdh",
				"sjhm", "qsdh", "jtszd", "jg" });
		return map;
	}

	public boolean saveStuxx(Model model,HttpServletRequest request) {
//		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "insert into ahjg_wtqt(xh,xn,xq,xsczqk) values(?,?,?,?)";
		String[] input = { model.getXh(), model.getXn(), model.getXq(),
				model.getXsczqk() };
		boolean bool = false;
		try {
			bool = dao.insert(sql, input);
			if(bool){
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()).append(model.getXn()).append(model.getXq());
			
			try {
//				boolean bo = StandardOperation.delete("ahjg_wtqtlsb", "xh||xn||xq", buff.toString(), request);
				StandardOperation.delete("ahjg_wtqtlsb", "xh||xn||xq", buff.toString(), request);
//				System.out.println(bo);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
			String sqlls = "insert into ahjg_wtqtlsb(xh,xn,xq,dm) select a.xh,(select dqxn from xtszb) xn," 
				+"(select dqxq from xtszb) xq,a.dm from tsxsxxb a,stu_lxdmb b,ahjg_wtqt c where a.dm=b.dm " 
				+"and a.xh=c.xh and c.xh||xn||xq=?";
			bool = dao.insert(sqlls, new String[]{buff.toString()});
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return bool;
	}

	public boolean save_modify(Model model,HttpServletRequest request) {
		@SuppressWarnings("unused")
		HashMap<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unused")
		String sql = "update ahjg_wtqt set xh=?,xn=?,xq=?,xsczqk=?";
		String[] values = { model.getXh(), model.getXn(), model.getXq(),
				model.getXsczqk() };
		String[] columns = {"xh","xn","xq","xsczqk"};
		StringBuffer buff = new StringBuffer();
		buff.append(model.getXh()).append(model.getXn()).append(model.getXq());
		boolean bool = false;
		try {
			bool = StandardOperation.update("ahjg_wtqt", columns, values, "xh||xn||xq", buff.toString(), request);
			if(bool){
				StringBuffer buffsql = new StringBuffer();
				buffsql.append(model.getXh()).append(model.getXn()).append(model.getXq());
				
				try {
					boolean bo = StandardOperation.delete("ahjg_wtqtlsb", "xh||xn||xq", buffsql.toString(), request);
					System.out.println(bo);
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				
				String sqlls = "insert into ahjg_wtqtlsb(xh,xn,xq,dm) select a.xh,(select dqxn from xtszb) xn," 
					+"(select dqxq from xtszb) xq,a.dm from tsxsxxb a,stu_lxdmb b,ahjg_wtqt c where a.dm=b.dm " 
					+"and a.xh=c.xh and c.xh||xn||xq=?";
				bool = dao.insert(sqlls, new String[]{buffsql.toString()});
				}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return bool;
	}
	
	public boolean issave(Model model) {
		boolean bool = false;
//		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from ahjg_wtqt where xh||xn||xq=?";
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		StringBuffer buff = new StringBuffer();
		buff.append(xh).append(xn).append(xq);
		String[] input = { buff.toString() };
		String[] rs = null;
		try {
			rs = dao.getRs(sql, input, "xh");
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if (rs != null && rs.length > 0) {
			bool = true;
		}
		return bool;
	}

	public ArrayList<String[]> getQueryList(Model model,CommanForm myForm) {
//		boolean bool = false;
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = DealString.toGBK(model.getXm());
		String xsxh = model.getXsxh();
//		String xh = DealString.toGBK(model.getXh());
		String wtqt = model.getWtqt();
		String xn = model.getXn();
		String xq = model.getXq();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xsxh)?"":" and xh='"+xsxh.trim()+"' ");
		querry.append(Base.isNull(xm)?"":" and xm='"+xm+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(wtqt)?"":" and exists(select dm from ahjg_wtqtlsb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='"+wtqt+"')");
		
//		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.* from (select a.xh || a.xn || a.xq pk,rownum r, a.xh, a.xn, a.xq, xm,b.xydm,b.xymc," 
			+"b.zydm,b.zymc,b.bjdm,b.bjmc, xz,b.nj from (select c.xh,(select dqxn from xtszb) xn," 
			+"(select dqxq from xtszb) xq from ahjg_wtqt c " 
			+") a, view_xsxxb b where a.xh = b.xh) a"+querry+"and r<="
		+ (myForm.getPages().getStart() + myForm.getPages().getPageSize())
		+ " and r> "
		+ myForm.getPages().getStart();
		System.out.println(sql);
		String[] colLost = { "pk", "xh", "xn", "xq", "xm", "xymc", "bjmc", "xz" };
		ArrayList<String[]> rs = dao.rsToVator(sql, new String[] {}, colLost);
		return rs;
	}

	public HashMap<String, String> getViewList(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.*,b.xn,(case b.xq when '01' then '春' else '秋' end) dqxq,b.xq,b.xsczqk from (select a.xh, a.xm,a.xb, a.csrq, a.mzmc, a.zzmmmc, a.sfzh, a.syd lydq, a.syd csdm,"
				+ "a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd, "
				+ "(select ksh from bks_xsjbxx b where a.xh=b.xh) ksh,b.JTCY1_XM,b.JTCY2_XM,b.jtszd ,a.zw,a.jg"
				+ " from view_xsxxb a left join xsfzxxb b on a.xh=b.xh) a left join ahjg_wtqt b on a.xh=b.xh where a.xh||xn||xq=?";
		System.out.println(sql);
		map = dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm","xn", "xq","dqxq",
				"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm", "ssbh",
				"nj", "xymc", "zymc", "bjmc", "xz", "xjztm", "lxdzxx", "lxdh",
				"sjhm", "qsdh", "jtszd", "jg","xsczqk"});
		return map;
	}

	
	public String[] getwtqtList(String xh,String xn,String xq) {
		String sql = "select xh||xn||xq pk,xh,xn,xq,a.dm,b.mc mc from ahjg_wtqtlsb a,stu_lxdmb b " +
						"where a.dm=b.dm and xh=? and xn=? and xq=?";
		String mc[] = null;
		try {
			mc = dao.getRs(sql, new String[]{xh,xn,xq}, "mc");
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return mc;
	}
	public String getAllDelList(String pks, HttpServletRequest request)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from ahjg_wtqt where xh||xn||xq = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public int queryListNum(Model model) {
		int count = 0;
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select count(xh||xn||xq) count from (select a.xh || a.xn || a.xq pk,rownum r, a.xh, a.xn, a.xq, xm,dm,mc,b.xydm,b.xymc," 
			+"b.zydm,b.zymc,b.bjdm,b.bjmc, xz,b.nj from (select a.xh,(select dqxn from xtszb) xn," 
			+"(select dqxq from xtszb) xq,a.dm,b.mc from tsxsxxb a,stu_lxdmb b,ahjg_wtqt c where a.dm=b.dm " 
			+"and a.xh=c.xh) a, view_xsxxb b where a.xh = b.xh)";
		map = dao.getMap(sql, new String[]{}, new String[]{"count"});
		if(map!=null){
			count = Integer.parseInt(map.get("count"));
		}
		return count;
	}

	public List<HashMap<String, String>> getTitle(String[] enList,
			String[] cnList) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		if (enList != null && cnList != null) {
			for (int i = 0; i < enList.length; i++) {
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("en", enList[i]);// 英文名称
				tmpMap.put("cn", cnList[i]);// 中文名称
				topList.add(tmpMap);
			}
		}
		return topList;
	}
	public List<HashMap<String, String>>  getWtqt() {
		
		String sql = "select * from stu_lxdmb";
		List<HashMap<String, String>> rs = dao.getArrayList(sql, new String[]{}, new String[]{"dm","mc"});
		return rs;
	}
	public void getExpList(Model model, OutputStream os) 
			throws IOException, WriteException {
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = DealString.toGBK(model.getXm());
		String xsxh = model.getXsxh();
		String wtqt = model.getWtqt();
		String xn = model.getXn();
		String xq = model.getXq();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		StringBuffer querry1 = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xsxh)?"":" and xh='"+xsxh.trim()+"' ");
		querry.append(Base.isNull(xm)?"":" and xm='"+xm+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(wtqt)?"":" and exists(select dm from ahjg_wtqtlsb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='"+wtqt+"')");
		
		querry1.append(Base.isNull(xsxh)?"":" and xh='"+xsxh.trim()+"' ");
		querry1.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry1.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		
//		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from (select a.xn,a.xq,a.mc,b.* from ((select xh,xn,xq,substr(" +
				"lower(max(sys_connect_by_path(mc,','))),2) mc from (select row_number() " +
				"over(partition by pk order by pk) pno,row_number() over(partition by pk " +
				"order by pk)-1 sno,xh,xn,xq,mc,pk from " +
				"(select * from (select c.xh,xn,xq,c.xh||xn||xq pk,nvl(b.mc,'暂无五特信息') mc " +
				"from ahjg_wtqt c left join tsxsxxb a on a.xh = c.xh left " +
				"join stu_lxdmb b on a.dm = b.dm))"+querry1+") start with pno = 1 connect by " +
				"prior pno = sno and pk=pk group by pk,xh,xn,xq)) a left outer join " +
				"(select a.xh,a.xm,a.xb,a.csrq,a.mzmc,a.nj,a.xydm,a.xymc,a.zydm,a.zymc," +
				"a.bjdm,a.bjmc,a.xz from view_xsxxb a) b on a.xh = b.xh order by a.xh) a " 
				+querry;
//		System.out.println(sql);
		DAO dao = DAO.getInstance();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
//			String ColumnName[] = dao.getColumnName(sql);
//			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, "view_xsxxb");
			String ColumnName[] = {"xh","xn","xq","mc","xm","xb","nj","csrq","mzmc","xymc","zymc","bjmc","xz"};
			String ColumnNameCN[] = {"学号","学年","学期","五特群体名称","姓名","性别","年级","出生日期","民族",
					Base.YXPZXY_KEY+"名称","专业名称","班级名称","学制"
					};
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}

}

package xgxt.xsgygl.hhgxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.CommonUpdata;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.action.XsgyglForm;
import xgxt.xsgygl.dao.gyglDao;

public class GyglService {
	
	/**
	 * 获取系统中下拉列表中的固定值
	 * @param int type
	 * @return List<HashMap<String, String>>
	 * @author lr
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		DAO dao = DAO.getInstance();
		return dao.getChkList(type);
	}
	
	/**
	 * 获取表的中文列注释
	 * @param int type
	 * @return String[]
	 * @author lr
	 * */
	public String[] getColCNList(String tableName, String[] inputList){
		DAO dao = DAO.getInstance();
		return dao.getColumnNameCN(inputList, tableName);
	}
	
	/**
	 * @describe 获得表头
	 * @author luo
	 */
	public List getZrsglTopTr() {
		DAO dao = DAO.getInstance();
		String tableName = "view_hh_gygl_zrsgl";
		String[] colList = new String[] { "xn", "xq", "ssbh", "xh", "xm",
				"bjmc", "zs" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		return topTr;
	}

	/**
	 * @describe 获得表头
	 * @author luo
	 */
	public List getGydykpTopTr() {
		DAO dao = DAO.getInstance();
		String tableName = "view_hhgxy_gydykp";
		String[] colList = new String[] { "xn", "xq", "xh", "xm", "bjmc",
				"ssbh", "wsjccj", "yjfs", "ryjf", "rykf", "zf" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		return topTr;
	}

	/**
	 * @describe 获得表头
	 * @author luo
	 */
	public List<HashMap<String, String>> getGywmqsTopTr() {
		DAO dao = DAO.getInstance();
		String tableName = "view_hhgxy_wmqs";
		String[] colList = new String[] { "xn", "xq", "ssbh", "avgfs" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * @describe 取得值日生管理列表
	 * @author luo
	 */
	public ArrayList<String[]> getZrsglList(XsgyglForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(dataSearchForm.getXydm());
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String zydm = DealString.toGBK(dataSearchForm.getZydm());
		String bjdm = DealString.toGBK(dataSearchForm.getBjdm());
		String xh = DealString.toGBK(dataSearchForm.getXh());
		String xn = DealString.toGBK(dataSearchForm.getXn());
		String xq = DealString.toGBK(dataSearchForm.getXq());
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?",
				new String[] { xq }, "xqmc");
		String qsh = DealString.toGBK(dataSearchForm.getQsh());

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xh) ? "" : " and xh ='" + xh + "' ");
		query.append(Base.isNull(xydm) ? "" : " and xydm ='" + xydm + "' ");
		query.append(Base.isNull(xm) ? "" : " and xm like '%" + xm + "%' ");
		query.append(Base.isNull(zydm) ? "" : " and zydm ='" + zydm + "' ");
		query.append(Base.isNull(bjdm) ? "" : " and bjdm ='" + bjdm + "' ");
		query.append(Base.isNull(xn) ? "" : " and xn ='" + xn + "' ");
		query.append(Base.isNull(xh) ? "" : " and xh ='" + xh + "' ");
		query.append(Base.isNull(xqmc) ? "" : " and xq ='" + xqmc + "' ");
		query.append(Base.isNull(qsh) ? "" : " and qsh ='" + qsh + "' ");

		String[] colList = new String[] { "pk", "xn", "xq", "ssbh", "xh", "xm",
				"bjmc", "zs" };

		String sql = "select * from (select rownum r,f.* from (select d.*,e.xydm,e.zydm,e.bjdm from (select distinct (xh), xn,"
				+ " xn||xq||ssbh pk,(select t.xqmc from xqdzb t where a.xq = t.xqdm) xq,(select t.xm"
				+ " from view_xsxxb t where t.xh = a.xh) xm, (select t.bjmc from view_xsxxb t where t.xh = a.xh) bjmc,"
				+ "  ssbh qsh,replace(NVL(ssbh, '0'), '-', '号楼') || '寝室' ssbh, max(ltrim(sys_connect_by_path('第' || (zs)"
				+ " || '周', '、'),  '、')) zs from (select t.xn,"
				+ " t.xq, t.xh, t.SSBH,t.ZS, row_number() over(partition by b.xh order by t.xh) pno,"
				+ " row_number() over(partition by b.xh order by t.xh) - 1 sno from hhgxy_zrsgl t, hhgxy_zrsgl b"
				+ " where t.zs = b.zs and t.xh = b.xh group by t.xn, t.xq, t.XH, t.SSBH, t.ZS, b.xh) a"
				+ " start with pno = 1 connect by prior pno = sno and prior a.xh = a.xh group by xh, xn, xq, ssbh) d"
				+ " ,view_xsjbxx e where d.xh=e.xh ) f "
				+ query
				+ " ) where r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and r > "
				+ dataSearchForm.getPages().getStart() + " order by ssbh,zs";

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from (select d.*, e.xydm, e.zydm, e.bjdm from(select distinct (xh), xn, xq, (select t.xm"
				+ " from view_xsxxb t where t.xh = a.xh) xm, (select t.bjmc from view_xsxxb t where t.xh = a.xh) bjmc,"
				+ " ssbh qsh, max(ltrim(sys_connect_by_path('第' || (zs) || '周', '、'),  '、')) zs from (select t.xn,"
				+ " t.xq, t.xh, t.SSBH,t.ZS, row_number() over(partition by b.xh order by t.xh) pno,"
				+ " row_number() over(partition by b.xh order by t.xh) - 1 sno from hhgxy_zrsgl t, hhgxy_zrsgl b"
				+ " where t.zs = b.zs and t.xh = b.xh and 1 = 1 group by t.xn, t.xq, t.XH, t.SSBH, t.ZS, b.xh) a"
				+ " start with pno = 1 connect by prior pno = sno and prior a.xh = a.xh group by xh, xn, xq, ssbh) d,"
				+ " view_xsjbxx e where d.xh = e.xh)" + query;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	/**
	 * @describe 取得公寓德育考评列表
	 * @author luo
	 */
	public ArrayList<String[]> getGydykpList(XsgyglForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(dataSearchForm.getXydm());
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String zydm = DealString.toGBK(dataSearchForm.getZydm());
		String bjdm = DealString.toGBK(dataSearchForm.getBjdm());
		String xh = DealString.toGBK(dataSearchForm.getXh());
		String xn = DealString.toGBK(dataSearchForm.getXn());
		String xq = DealString.toGBK(dataSearchForm.getXq());
		String lddm = dataSearchForm.getLddm();
		String lc = dataSearchForm.getLc();
		String qsh = dataSearchForm.getQsh();
		String ssbh = "";
		if ((lddm != null && !"".equals(lddm))
				&& (!"null".equalsIgnoreCase(qsh) && qsh != null && !""
						.equals(qsh))) {
			ssbh = lddm + '-' + qsh;
		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xh) ? "" : " and xh ='" + xh + "' ");
		query.append(Base.isNull(xydm) ? "" : " and xydm ='" + xydm + "' ");
		query.append(Base.isNull(xm) ? "" : " and xm like '%" + xm + "%' ");
		query.append(Base.isNull(zydm) ? "" : " and zydm ='" + zydm + "' ");
		query.append(Base.isNull(bjdm) ? "" : " and bjdm ='" + bjdm + "' ");
		query.append(Base.isNull(xn) ? "" : " and xn ='" + xn + "' ");
		query.append(Base.isNull(xq) ? "" : " and xq ='" + xq + "' ");
		if (ssbh != null && !"".equalsIgnoreCase(ssbh)) {
			query.append(Base.isNull(ssbh) ? "" : " and ssbh ='" + ssbh + "' ");
		} else if ((lddm != null && !"".equals(lddm))) {
			query.append(Base.isNull(lc) ? " and ssbh like'" + lddm + "-%' "
					: " and ssbh like'" + lddm + "-" + lc + "%' ");
		} else if ((lddm == null || "".equals(lddm))) {
			query.append(Base.isNull(lc) ? "" : " and ssbh like'%-" + lc
					+ "%' ");
		}
		query.append(" and xn is not null ");
		query.append(" and xq is not null ");
		query.append(" order by xn,xq,ssbh ");

		String[] colList = new String[] { "xn", "xqmc", "xh", "xm", "bjmc",
				"ssbh", "wsjccj", "yjfs", "ryjf", "rykf", "zf" };

		String sql = "select * from view_hhgxy_gydykp" + query;

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		return rs;
	}

	/**
	 * 德育考评打印
	 * 
	 * @param wwb
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void printPayReport(WritableWorkbook wwb, String pk) {

		DAO dao = new DAO();

		pk += " and xn is not null  and xq is not null ";
		String sql = "select b.xnav, c.xqav, a.* from view_hhgxy_gydykp a"
				+ " left join (select xn, avg(zf) xnav from view_hhgxy_gydykp group by xn) b on a.xn = b.xn"
				+ " left join (select xn, xq, avg(zf) xqav from view_hhgxy_gydykp group by xn, xq) c on a.xn = c.xn"
				+ " and a.xq = c.xq " + pk + "order by a.xn, a.xq, a.ssbh";

		String[] colList = new String[] { "xn", "xq", "xh", "xm", "xb", "bjmc",
				"ssbh", "wsjccj", "yjfs", "ryjf", "rykf", "zf", "xnav", "xqav" };

		List list = dao.getList(sql, new String[] {}, colList);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, "公寓德育考评", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			String xnZf = "";
			String xqZf = "";
			String xqmc = "";
			String ssbh = "";
			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();

				map = (HashMap<String, String>) list.get(i);

				xnZf = map.get("xnav");
				xqZf = map.get("xqav");
				xqmc = map.get("xqmc");
				ssbh = map.get("ssbh");

				if (xnZf != null && !"".equals(xnZf)) {
					if (xnZf.length() > 5) {
						xnZf = xnZf.substring(0, 4);
					}
				}
				if (xqZf != null && !"".equals(xqZf)) {
					if (xqZf.length() > 5) {
						xqZf = xqZf.substring(0, 4);
					}
				}

				if (ssbh != null && !"".equals(ssbh)) {
					String[] ss = ssbh.split("-");
					ssbh = ss[0] + "号楼" + ss[1] + "寝室";
				}
				ws.addCell(new Label(0, 3 + i, map.get("xn"), wcfTytle));
				ws.addCell(new Label(1, 3 + i, xnZf, wcfTytle));
				ws.addCell(new Label(2, 3 + i, xqmc, wcfTytle));
				ws.addCell(new Label(3, 3 + i, xqZf, wcfTytle));
				ws.addCell(new Label(4, 3 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(5, 3 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(6, 3 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(7, 3 + i, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(8, 3 + i, ssbh, wcfTytle));
				ws.addCell(new Label(9, 3 + i, map.get("wsjccj"), wcfTytle));
				ws.addCell(new Label(10, 3 + i, map.get("yjfs"), wcfTytle));
				ws.addCell(new Label(11, 3 + i, map.get("ryjf"), wcfTytle));
				ws.addCell(new Label(12, 3 + i, map.get("rykf"), wcfTytle));
				ws.addCell(new Label(13, 3 + i, map.get("zf"), wcfTytle));

			}
			int m = 1;
			int k = 1;
			boolean n = false;
			boolean l = false;

			for (int i = 0; i <= list.size(); i++) {
				String a3 = "";
				String a4 = "";
				String d3 = "";
				String d4 = "";

				WritableCell a1 = ws.getWritableCell(0, 3 + i);
				WritableCell d1 = ws.getWritableCell(3, 3 + i);

				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 3 + i - 1);
					WritableCell d2 = ws.getWritableCell(3, 3 + i - 1);

					a4 = a2.getContents();
					d4 = d2.getContents();
				}
				a3 = a1.getContents();
				d3 = d1.getContents();

				if (a3.equals(a4)) {
					m++;
					n = true;
					if (d3.equals(d4)) {
						l = true;
						k++;
					}
				}
				if ((!a3.equals(a4)) && n) {
					ws.mergeCells(0, 3 + i - m, 0, 3 + i - 1);
					ws.mergeCells(1, 3 + i - m, 1, 3 + i - 1);
					n = false;
					m = 1;
				}
				if (!d3.equals(d4) && l) {
					ws.mergeCells(2, 3 + i - k, 2, 3 + i - 1);
					ws.mergeCells(3, 3 + i - k, 3, 3 + i - 1);
					l = false;
					k = 1;
				}
			}
			xqZf = xnZf;

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * @describe 取得符合条件可评选为文明寝室的寝室列表
	 * @author luo
	 */
	public ArrayList<String[]> getWmqsZgList(XsgyglForm dataSearchForm) {

		DAO dao = DAO.getInstance();
		String xn = Base.currXn;
		String xq = Base.currXq;
		String xnV = DealString.toGBK(dataSearchForm.getXn());
		String xqV = DealString.toGBK(dataSearchForm.getXq());
		xn = Base.isNull(xnV) ? xn : xnV;
		xq = Base.isNull(xqV) ? xq : xqV;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sqlws = "select wsjcdj, wsjcdjbfb from gygl_csszb where xn=? and xq=? and cfdj is null order by wsjcdj";
		String sqljv = "select b.cfdjdm,a.cfdj, a.cfdjcs from gygl_csszb a, hhgxy_cfdj b where a.cfdj = b.cfdjmc"
				+ " and a.xn = ? and a.xq = ? and a.wsjcdj is null order by b.cfdjdm";
		String sqlxq = "select xqdm, xqmc from xqdzb";
		List<HashMap<String, String>> wsList = dao.getList(sqlws, new String[] {
				xn, xq }, new String[] { "wsjcdj", "wsjcdjbfb" });
		List<HashMap<String, String>> jvList = dao.getList(sqljv, new String[] {
				xn, xq }, new String[] { "cfdjdm", "cfdj", "cfdjcs" });
		List<HashMap<String, String>> xqList = dao.getList(sqlxq,
				new String[] {}, new String[] { "xqdm", "xqmc" });
		String zs = dao.getOneRs("  select xqzs from xtszb", new String[] {},
				"xqzs");

		String wsjcdj = "";
		String wsjcdjbfb = "";
		String cfdj = "";
		String cfdjcs = "";

		StringBuffer queryxqws = new StringBuffer();
		if (xqList != null && xqList.size() > 0) {
			queryxqws.append(" case ");
			for (int i = 0; i < xqList.size(); i++) {
				queryxqws.append(" when a.xq = '" + xqList.get(i).get("xqdm")
						+ "' then '" + xqList.get(i).get("xqmc") + "'");
			}
			queryxqws.append("end xq,");
		}

		StringBuffer queryxqjl = new StringBuffer();
		if (xqList != null && xqList.size() > 0) {
			queryxqjl.append(" case ");
			for (int i = 0; i < xqList.size(); i++) {
				queryxqjl.append(" when xq = '" + xqList.get(i).get("xqdm")
						+ "' then '" + xqList.get(i).get("xqmc") + "'");
			}
			queryxqjl.append("end xq,");
		}

		StringBuffer queryws = new StringBuffer();
		queryws.append(" where 1=1");

		for (int i = 0; i < wsList.size(); i++) {
			HashMap<String, String> map = wsList.get(i);
			wsjcdj = map.get("wsjcdj");
			wsjcdjbfb = map.get("wsjcdjbfb");
			if (wsjcdjbfb != null && !"".equalsIgnoreCase(wsjcdjbfb)) {
				float bfb = Float.parseFloat(wsjcdjbfb) / 100;
				if (i == 0) {
					queryws.append(" and ( ");
					queryws.append(" (");
					queryws.append(" t.dj = '" + wsjcdj + "'");
					queryws.append(" and t.djnum /" + zs + " >'" + bfb + "'");
					queryws.append(" )");
				} else {
					queryws.append(" or ( ");
					queryws.append(" t.dj = '" + wsjcdj + "'");
					queryws.append(" and t.djnum /" + zs + " >'" + bfb + "'");
					queryws.append(" )");
				}
				if (i == (wsList.size() - 1)) {
					queryws.append(" )");
				}
			} else {
				if (i > 0) {
					queryws.append(" )");
					break;
				}
			}
		}

		StringBuffer queryjv = new StringBuffer();
		queryjv.append(" where 1=1");

		for (int i = 0; i < jvList.size(); i++) {
			HashMap<String, String> map = jvList.get(i);
			cfdj = map.get("cfdj");
			cfdjcs = map.get("cfdjcs");

			if (cfdjcs != null && !"".equalsIgnoreCase(cfdjcs)) {
				if (i == 0) {
					queryjv.append(" and ( ");
					queryjv.append(" (");
					queryjv.append(" v.cfdjmc = '" + cfdj + "'");
					queryjv.append(" and v.cfdjs >= '" + cfdjcs + "'");
					queryjv.append(" )");
				} else {
					queryjv.append(" or (");
					queryjv.append(" v.cfdjmc = '" + cfdj + "'");
					queryjv.append(" and v.cfdjs >= '" + cfdjcs + "'");
					queryjv.append(" )");
				}
				if (i == (jvList.size() - 1)) {
					queryjv.append(" ) ");
				}
			} else {
				if (i > 0) {
					queryjv.append(" ) ");
					break;
				}
			}
		}

		String qsh = DealString.toGBK(dataSearchForm.getQsh());
		String lddm = DealString.toGBK(dataSearchForm.getLddm());
		String cs = DealString.toGBK(dataSearchForm.getLc());
		String sfwmqs = dataSearchForm.getSfwmqs();
		String wmqsTable = "";
		String ssbh = "";

		if (lddm != null && !"".equalsIgnoreCase(lddm)) {
			if (qsh != null && !"".equalsIgnoreCase(qsh)) {
				ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
			}
		}
		xn = Base.isNull(xnV) ? xn : xnV;
		xq = Base.isNull(xqV) ? xq : xqV;

		String pbzq = dao.getOneRs(
				" select distinct(pbzq) from gygl_csszb where xn=? and xq=?",
				new String[] { xn, xq }, "pbzq");
		StringBuffer query = new StringBuffer();
		query.append(" and 1=1");
		if (Base.isNull(ssbh)) {
			if (Base.isNull(cs) && !Base.isNull(lddm)) {
				query.append(" and a.ssbh like '" + lddm + "-%' ");
			} else if (!Base.isNull(cs) && Base.isNull(lddm)) {
				query.append(" and a.ssbh like '%-" + cs + "%' ");
			} else if (!Base.isNull(cs) && !Base.isNull(lddm)) {
				query.append(" and a.ssbh like '" + lddm + "-" + cs + "%' ");
			}
		}else{
			query.append(" and a.ssbh ='" + ssbh + "' ");
		}
		query.append(" and a.xq ='" + xq + "' ");
		query.append(" and a.xn ='" + xn + "' ");

		if ("1".equalsIgnoreCase(sfwmqs)) {
			wmqsTable = ", hhgxy_wmqs s";
			query.append(" and a.ssbh = s.ssbh");
			query.append(" and a.xn = s.xn");
			query.append(" and a.xq = s.xq");
		} else if ("0".equalsIgnoreCase(sfwmqs)) {
			query
					.append(" and a.ssbh not in (select s.ssbh from hhgxy_wmqs s where s.xn = '");
			query.append(Base.isNull(xnV) ? xn : xnV);
			query.append("' and s.xq = '");
			query.append(Base.isNull(xqV) ? xq : xqV);
			query.append("' and s.pbzq='");
			query.append(pbzq);
			query.append("')");
		}

		String[] colList = new String[] { "xn", "xq", "ssbh", "avgfs", "iswmqs" };

		String sql = "select v.r, v.xn, v.xq, v.ssbh, v.avgfs, decode(z.ssbh, '', '否','是') iswmqs"
				+ " from (select rownum r, d.* from (select a.xn, "
				+ queryxqws
				+ " replace(NVL(a.ssbh, '0'), '-', '号楼') || '寝室' ssbh,"
				+ " substr(a.avgfs, 1, 5) avgfs from (select distinct (a.ssbh),a.xn,a.xq,a.avgfs from(select distinct (t.ssbh), t.djnum,"
				+ " t.xn, t.xq, t.avgfs from view_hhgxy_wmqs t"
				+ queryws
				+ " )a)a "
				+ wmqsTable
				+ " where a.ssbh not in (select distinct (b.ssbh) from (select distinct (v.ssbh), v.xh, v.cfdjmc, v.cfdjs"
				+ " from view_hhgxy_wmqs v "
				+ queryjv
				+ " ) b ) "
				+ query
				+ " order by a.avgfs desc,a.ssbh) d)  v left join (select xn,"
				+ queryxqjl
				+ " replace(NVL(t.ssbh, '0'), '-', '号楼') || '寝室' ssbh"
				+ " from hhgxy_wmqs t) z on v.xn = z.xn and v.xq = z.xq and v.ssbh = z.ssbh"
				+ " where v.r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and v.r > "
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		// 分页
		sql = "select count(*) count from (select a.xn, case when a.xq = '01' then"
				+ " '春' when a.xq = '02' then '秋' end xq, replace(NVL(a.ssbh, '0'), '-', '号楼') || '寝室' ssbh,"
				+ " substr(a.avgfs, 1, 5) avgfs from (select distinct (a.ssbh),a.xn,a.xq,a.avgfs from(select distinct (t.ssbh), t.djnum,"
				+ " t.xn, t.xq, t.avgfs from view_hhgxy_wmqs t"
				+ queryws
				+ " )a)a "
				+ wmqsTable
				+ " where a.ssbh not in (select distinct (b.ssbh) from (select distinct (v.ssbh), v.xh, v.cfdjmc, v.cfdjs"
				+ " from view_hhgxy_wmqs v "
				+ queryjv
				+ " ) b ) "
				+ query
				+ " order by a.ssbh, a.avgfs)";

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	public List<HashMap<String, String>> getWsjcList(String pk) {
		DAO dao = new DAO();

		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		String ssbh = tempPk[2].replace("号楼", "-").replace("寝室", "").trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");

		String sql = "select distinct (t.dj), t.djnum from view_hhgxy_wmqs t"
				+ " where t.xn = ? and t.xq = ? and t.ssbh = ?";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {
				xn, xq, ssbh }, new String[] { "dj", "djnum" });
		return list;
	}

	public List<HashMap<String, String>> getWjList(String pk) {
		DAO dao = new DAO();

		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		String ssbh = tempPk[2].replace("号楼", "-").replace("寝室", "").trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");

		String sql = "select a.xm, max(ltrim(sys_connect_by_path((a.cfdjmc || ' ' || a.cfdjs || '次') || '', '、'), '、')) wj"
				+ " from (select distinct (t.xm), t.cfdjmc, t.cfdjs, row_number() over(partition by b.xm order by t.xm) pno,"
				+ " row_number() over(partition by b.xm order by t.xm) - 1 sno from view_hhgxy_wmqs t, view_hhgxy_wmqs b"
				+ " where t.xn = ? and t.xq = ? and t.ssbh = ? and t.xm = b.xm and t.xn = b.xn"
				+ " and t.xq = b.xq and t.ssbh = b.ssbh group by t.cfdjmc, t.cfdjs, t.xm, b.xm order by t.xm) a"
				+ " start with pno = 1 connect by prior pno = sno and prior a.xm = a.xm group by a.xm";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {
				xn, xq, ssbh }, new String[] { "xm", "wj" });
		return list;
	}

	/**
	 * @describe 保存文明寝室
	 * @author luo
	 */
	public boolean saveWmqs(String tableName, String pk, String pbdj,
			HttpServletRequest request) throws Exception {
		DAO dao = new DAO();

		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");
		String ssbh = tempPk[2].replace("号楼", "-").replace("寝室", "").trim();
		String pbzq = dao.getOneRs(
				" select distinct(pbzq) from gygl_csszb where xn=? and xq=?",
				new String[] { xn, xq }, "pbzq");

		boolean inserted = StandardOperation.insert(tableName, new String[] {
				"xn", "xq", "ssbh", "pbzq", "pbdj" }, new String[] { xn, xq,
				ssbh, pbzq, pbdj }, request);

		return inserted;
	}

	/**
	 * @describe 判断文明寝室是否达到上限
	 * @author luo
	 */
	public String maxWmqs(String pk) throws Exception {
		DAO dao = new DAO();

		String msg = "";
		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");
		String ssnum = dao.getOneRs(
				"select count(distinct(ssbh)) ssnum from view_xszsxx",
				new String[] {}, "ssnum");
		String wmqsbz = dao.getOneRs(
				"select distinct(wmqsbz) from gygl_csszb where xn=? and xq=?",
				new String[] { xn, xq }, "wmqsbz");
		String str_wmqsnum = dao.getOneRs(
				"select count(*) wmqsnum from hhgxy_wmqs where xn=? and xq=?",
				new String[] { xn, xq }, "wmqsnum");

		float bfb = Float.parseFloat(wmqsbz) / 100;
		String str_maxWmqs = String.valueOf(Integer.parseInt(ssnum) * bfb);
		str_maxWmqs.indexOf(".");
		str_maxWmqs = str_maxWmqs.substring(0, str_maxWmqs.indexOf("."));
		int int_wmqsnum = Integer.parseInt(str_wmqsnum);
		if (int_wmqsnum == Integer.parseInt(str_maxWmqs)) {
			msg = "本学期文明寝室上限为" + str_maxWmqs + ",已达到上限,\n该寝室不能设置为文明寝室,请确认 ";
		}

		return msg;
	}

	/**
	 * @describe 判断文明寝室是否达到上限
	 * @author luo
	 */
	public String maxWmqsAll(String xn, String xq, int num) throws Exception {
		DAO dao = new DAO();

		String msg = "";

		String ssnum = dao.getOneRs(
				"select count(distinct(ssbh)) ssnum from view_xszsxx",
				new String[] {}, "ssnum");
		String wmqsbz = dao.getOneRs(
				"select distinct(wmqsbz) from gygl_csszb where xn=? and xq=?",
				new String[] { xn, xq }, "wmqsbz");
		String str_wmqsnum = dao.getOneRs(
				"select count(*) wmqsnum from hhgxy_wmqs where xn=? and xq=?",
				new String[] { xn, xq }, "wmqsnum");

		float bfb = Float.parseFloat(wmqsbz) / 100;
		String str_maxWmqs = String.valueOf(Integer.parseInt(ssnum) * bfb);
		str_maxWmqs.indexOf(".");
		str_maxWmqs = str_maxWmqs.substring(0, str_maxWmqs.indexOf("."));
		int int_wmqsnum = Integer.parseInt(str_wmqsnum);
		if (int_wmqsnum + num > Integer.parseInt(str_maxWmqs)) {
			msg = "本学期文明寝室上限为" + str_maxWmqs + ",已超过上限,\n该批寝室不能设置为文明寝室,请确认 ";
		}

		return msg;
	}

	/**
	 * @describe 取消文明寝室
	 * @author luo
	 */
	public boolean delWmqs(String tableName, String pk,
			HttpServletRequest request) throws Exception {
		DAO dao = new DAO();
		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");
		String ssbh = tempPk[2].replace("号楼", "-").replace("寝室", "").trim();
		String pbzq = dao.getOneRs(
				" select distinct(pbzq) from gygl_csszb where xn=? and xq=?",
				new String[] { xn, xq }, "pbzq");

		boolean inserted = StandardOperation.delete(tableName, new String[] {
				"xn", "xq", "ssbh", "pbzq" },
				new String[] { xn, xq, ssbh, pbzq }, request);

		return inserted;
	}

	/**
	 * @describe 判断是否文明寝室
	 * @author luo
	 */
	public boolean isWmqs(String tableName, String pk,
			HttpServletRequest request) throws Exception {
		DAO dao = new DAO();
		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");
		String ssbh = tempPk[2].replace("号楼", "-").replace("寝室", "").trim();
		String pbzq = dao.getOneRs(
				" select distinct(pbzq) from gygl_csszb where xn=? and xq=?",
				new String[] { xn, xq }, "pbzq");

		boolean flg = false;
		String count = dao
				.getOneRs(
						"select count(ssbh) count from hhgxy_wmqs where xn = ? and xq = ? and ssbh = ? and pbzq = ?",
						new String[] { xn, xq, ssbh, pbzq }, "count");

		if (Integer.parseInt(count) > 0) {
			flg = true;
		}

		return flg;
	}

	public String getPbzq(String pk) throws Exception {
		DAO dao = new DAO();

		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");
		String sql = " select case when pbzq='1' then '学年' when pbzq='2' then '学期' end pbzq"
				+ " from (select distinct (pbzq) from gygl_csszb where xn = ? and xq = ?)";
		String pbzq = dao.getOneRs(sql, new String[] { xn, xq }, "pbzq");

		return pbzq;
	}

	public String getPbdj(String pk) throws Exception {
		DAO dao = new DAO();

		String[] tempPk = pk.split("!!SplitSignOne!!");
		String xn = tempPk[0].trim();
		String xq = tempPk[1].trim();
		// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
		xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
				new String[] { xq }, "xqdm");
		String ssbh = tempPk[2].replace("号楼", "-").replace("寝室", "").trim();

		String sql = "select pbdj from hhgxy_wmqs where xn=? and xq=? and ssbh=?";
		String pbzq = dao.getOneRs(sql, new String[] { xn, xq, ssbh }, "pbdj");

		return pbzq;
	}

	public String addAllWmqs(String pk) throws Exception {

		DAO dao = new DAO();

		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String[] tempPk = pk.split("!!SplitSignOne!!");
		String sqldel = "";
		String sqladd = "";
		String xnV = "";
		String xqV = "";
		String msg = "";
		int num = 0;

		for (int i = 0; i < tempPk.length; i++) {
			String str_addpk = tempPk[i];// 得到主键
			if (str_addpk != null && !"".equalsIgnoreCase(str_addpk)) {
				String[] addpk = str_addpk.split(" ");
				String xn = addpk[0].trim();
				String xq = addpk[1].trim();
				// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
				xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
						new String[] { xq }, "xqdm");
				String ssbh = addpk[2].replace("号楼", "-").replace("寝室", "")
						.trim();
				xnV = xn;
				xqV = xq;
				msg = maxWmqsAll(xn, xq, 0);
				if ("".equalsIgnoreCase(msg)) {
					String pbzq = dao
							.getOneRs(
									" select distinct(pbzq) from gygl_csszb where xn=? and xq=?",
									new String[] { xn, xq }, "pbzq");
					pk = xn + xq + ssbh;

					sqldel = "delete from hhgxy_wmqs where xn||xq||ssbh = '"
							+ pk + "'";
					sb.append(sqldel);
					sb.append("!!#!!");

					sqladd = "insert into hhgxy_wmqs (xn,xq,ssbh,pbzq) values('"
							+ xn
							+ "','"
							+ xq
							+ "','"
							+ ssbh
							+ "','"
							+ pbzq
							+ "')";
					sb.append(sqladd);
					sb.append("!!#!!");

					num++;
				} else {
					break;
				}
			}

		}
		msg = maxWmqsAll(xnV, xqV, num);
		if ("".equalsIgnoreCase(msg)) {
			pksql = sb.toString().split("!!#!!");
			dao.runBatch(pksql);
		}
		return msg;
	}

	public void delAllWmqs(String pk) throws Exception {

		DAO dao = new DAO();

		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String[] tempPk = pk.split("!!SplitSignOne!!");
		String sqldel = "";

		for (int i = 0; i < tempPk.length; i++) {
			String str_addpk = tempPk[i];// 得到主键
			if (str_addpk != null && !"".equalsIgnoreCase(str_addpk)) {
				String[] addpk = str_addpk.split(" ");
				String xn = addpk[0].trim();
				String xq = addpk[1].trim();
				// xq = "春".equalsIgnoreCase(xq) ? "01" : "02";
				xq = dao.getOneRs("select xqdm from xqdzb where xqmc = ?",
						new String[] { xq }, "xqdm");
				String ssbh = addpk[2].replace("号楼", "-").replace("寝室", "")
						.trim();

				pk = xn + xq + ssbh;

				sqldel = "delete from hhgxy_wmqs where xn||xq||ssbh = '" + pk
						+ "'";
				sb.append(sqldel);
				sb.append("!!#!!");
			}

		}
		pksql = sb.toString().split("!!#!!");
		dao.runBatch(pksql);
	}

	/**
	 * 德育考评打印
	 * 
	 * @param wwb
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void printWmqsReport(WritableWorkbook wwb, String pk) {

		DAO dao = new DAO();

		String ssbh = "";

		StringBuffer query = new StringBuffer();
		query.append(" and 1=1");
		query.append(pk);

		String sql = "select distinct(a.ssbh),a.xn, a.xq,substr(a.avgfs, 1, 5) avgfs, b.pbdj, c.xh,c.xm,c.xb,c.zymc,c.bjmc"
				+ " from view_hhgxy_wmqs a, hhgxy_wmqs b, view_xszsxx c where a.xn = b.xn and a.xq = b.xq"
				+ " and a.ssbh = b.ssbh and a.ssbh = c.ssbh "
				+ query.toString() + "order by a.ssbh,a.xn,a.xq";

		String[] colList = new String[] { "ssbh", "xn", "xq", "avgfs", "pbdj",
				"xh", "xm", "xb", "zymc", "bjmc", };

		List list = dao.getList(sql, new String[] {}, colList);

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, "文明寝室考评", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			String xqmc = "";

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();

				map = (HashMap<String, String>) list.get(i);

				xqmc = map.get("xq");
				ssbh = map.get("ssbh");

				if (xqmc != null && !"".equals(xqmc)) {
					// if ("01".equals(xqmc)) {
					// xqmc = "春";
					// }
					// if ("02".equals(xqmc)) {
					// xqmc = "秋";
					// }
					xqmc = dao.getOneRs(
							"select xqmc from xqdzb where xqdm = ?",
							new String[] { xqmc }, "xqmc");
				}
				if (ssbh != null && !"".equals(ssbh)) {
					String[] ss = ssbh.split("-");
					ssbh = ss[0] + "号楼" + ss[1] + "寝室";
				}
				ws.addCell(new Label(0, 4 + i, ssbh, wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xn"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, xqmc, wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("avgfs"), wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("pbdj"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(7, 4 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(8, 4 + i, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(9, 4 + i, map.get("bjmc"), wcfTytle));
			}
			int m = 1;
			int k = 1;
			int o = 1;
			boolean n = false;
			boolean l = false;
			boolean p = false;

			for (int i = 0; i <= list.size(); i++) {
				String a3 = "";
				String a4 = "";
				String b3 = "";
				String b4 = "";
				String c3 = "";
				String c4 = "";

				WritableCell a1 = ws.getWritableCell(0, 4 + i);
				WritableCell b1 = ws.getWritableCell(1, 4 + i);
				WritableCell c1 = ws.getWritableCell(2, 4 + i);

				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 4 + i - 1);
					WritableCell b2 = ws.getWritableCell(1, 4 + i - 1);
					WritableCell c2 = ws.getWritableCell(2, 4 + i - 1);

					a4 = a2.getContents();
					b4 = b2.getContents();
					c4 = c2.getContents();
				}
				a3 = a1.getContents();
				b3 = b1.getContents();
				c3 = c1.getContents();

				if (a3.equals(a4)) {
					m++;
					n = true;
					if (b3.equals(b4)) {
						k++;
						l = true;
						if (c3.equals(c4)) {
							o++;
							p = true;
						}
					}
				}
				if ((!a3.equals(a4)) && n) {
					ws.mergeCells(0, 4 + i - m, 0, 4 + i - 1);
					ws.mergeCells(1, 4 + i - k, 1, 4 + i - 1);
					ws.mergeCells(2, 4 + i - o, 2, 4 + i - 1);
					ws.mergeCells(3, 4 + i - o, 3, 4 + i - 1);
					ws.mergeCells(4, 4 + i - o, 4, 4 + i - 1);
					n = false;
					l = false;
					p = false;
					k = 1;
					m = 1;
					o = 1;
				} else {
					if ((!b3.equals(b4)) && l) {
						ws.mergeCells(1, 4 + i - k, 1, 4 + i - 1);
						ws.mergeCells(2, 4 + i - o, 2, 4 + i - 1);
						ws.mergeCells(3, 4 + i - o, 3, 4 + i - 1);
						ws.mergeCells(4, 4 + i - o, 4, 4 + i - 1);
						l = false;
						p = false;
						o = 1;
						k = 1;
					} else {
						if ((!c3.equals(c4)) && p) {
							ws.mergeCells(2, 4 + i - o, 2, 4 + i - 1);
							ws.mergeCells(3, 4 + i - o, 3, 4 + i - 1);
							ws.mergeCells(4, 4 + i - o, 4, 4 + i - 1);
							p = false;
							o = 1;
						}
					}
				}
			}

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获取公寓管理中的列表数据
	 * @param String xm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getList(String xm){
		gyglDao gyDao = new gyglDao();
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if("ld".equalsIgnoreCase(xm)){//公寓楼栋
			rs = gyDao.getGyLdList();
		}else if("qsh".equalsIgnoreCase(xm)){//公寓寝室号
			rs = gyDao.getQshList();
		}
		return rs;
	}
	
	/**
	 * 月度文明寝室申请月份列表数据
	 * @param List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYdwmqssqYfList(){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String dqyf = GetTime.getNowMonth();
		//当前月份
		dqyf = dqyf.length() <2 ? ("0" + dqyf) : dqyf;
		map.put("yf", dqyf);
		rs.add(map);
		
		//当前月份上一月份
		String bYf = (Integer.parseInt(dqyf)-1)+"";
		bYf = bYf.length() <2 ? ("0" + bYf) : bYf;
		map = new HashMap<String, String>();
		map.put("yf", bYf);	
		rs.add(map);
		
		return rs;
	}
	
	/**
	 * 保存月度文明寝室申请信息
	 * @param XsgyglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveYdwmqssqb(XsgyglForm model,HttpServletRequest request) 
		throws Exception{
		gyglDao dao = new gyglDao();
		String tableName = "gygl_ydwmqssqb";
		String[] columns = null;
		String[] values = null;
		boolean result = false;
		String pk = "xn||xq||yf||ssbh";
		String pkValue = StringUtils.joinStr(model.getXn(),model.getXq(),
											 model.getYf(),model.getSsbh());
		if(dao.checkIsExists(tableName, pk, pkValue)){
			//记录已经存在，进行修改操作
			columns = new String[]{"xn", "xq", "yf", "ssbh", "sqr", "bz"};
			result = CommonUpdata.commonUpdata(columns, model, tableName, 
		                           pk, pkValue, request);
		}else{
			//记录不存在，执行增加操作

			columns = new String[]{"xn", "xq", "yf", "ssbh", "sqr", "bz"};
			values = new String[]{model.getXn(), model.getXq(), model.getYf(), 
							      model.getSsbh(), model.getSqr(), model.getBz()};
			result = StandardOperation.insert(tableName, columns, values, request);
		}
		return result;
	}
	
	/**
	 * 删除月度文明寝室申请表信息
	 * @param XsgyglForm model
	 * @return boolean
	 * */
	public boolean delYdwmqssqb(XsgyglForm model){
		gyglDao dao = new gyglDao();
		return dao.deleteYdwmqssqb(model);
	}
	
	/**
	 * 根据主键查询月度文明寝室申请表信息
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> queryYdwmqssqb(XsgyglForm model,
			String userType,String userName){
		gyglDao dao = new gyglDao();
		String yhType = getYhlx(userType, userName, model.getShjb());
		return dao.selectYdwmqssqbByPk(model,yhType);
	}
	
	/**
	 * 查询月度文明寝室申请表信息（含分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryYdwmqssqxx(XsgyglForm model) throws Exception{
		gyglDao dao = new gyglDao();
		return dao.selectYdwmqssqxx(model);
	}
	
	/**
	 * 查询月度文明寝室申请审核信息（含分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryYdwmqssqshxx(XsgyglForm model,
			String userType,String userName) throws Exception{
		gyglDao dao = new gyglDao();
		String yhlx = getYhlx(userType, userName, model.getShjb());
		return dao.selectYdwmqssqshxx(model,yhlx, userName);
	}
	
	/**
	 * 月度文明寝室申请审核
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean audiYdwmqssqb(XsgyglForm model, HttpServletRequest request,
			String userType, String userName) throws Exception{
		boolean result = false;
		String[] updateCol = null;
		String time = GetTime.getSystemTime();
		String yhType = getYhlx(userType, userName, model.getShjb());
		
		if("fdy".equalsIgnoreCase(yhType)){//公寓辅导员
			model.setFdyshsj(time);
			updateCol = new String[]{"fdysh", "fdyshyj", "fdyshsj"};
		}else if("xy".equalsIgnoreCase(yhType)){//学院
			model.setXyshsj(time);
			updateCol = new String[]{"xysh", "xyshyj", "xyshsj"};
		}else{//学校
			model.setXxshsj(time);
			updateCol = new String[]{"xxsh", "xxshyj", "xxshsj"};
		}
		
		String pk = StringUtils.joinStr(model.getXn(), model.getXq(), 
				                        model.getYf(), model.getSsbh());
		//保存审核信息
		result = CommonUpdata.commonUpdata(updateCol, model, "gygl_ydwmqssqb", 
				                           "xn||xq||yf||ssbh", pk, request);
		return result;
	}	
	
	/**
	 * 月度文明寝室申请审核
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean audiYdwmqssqbBatch(XsgyglForm model, String shjg,String userType, 
			String userName) throws Exception{
		boolean result = false;
		gyglDao dao = new gyglDao();
		String yhType = getYhlx(userType, userName,model.getShjb());
		
		result = dao.audiYdwmqssqbBatch(model,shjg,yhType); 
		return result;
	}
	
	/**
	 * 查询月度文明寝室申请表信息（不分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryYdwmqssqxxAll(XsgyglForm model,String[] outputList) throws Exception{
		gyglDao dao = new gyglDao();
		return dao.selectYdwmqssqxxAll(model,outputList);
	}
	
	
	/**
	 * 获取查询月度文明寝室申请信息标题
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYdwmqssqToptr(){
		DAO dao = DAO.getInstance();
		String[] colList = {"pk", "xn", "xqmc", "yf", "ldmc", "qsh", "sqsj", 
				            "sqr", "xxsh"};
		String[] colCNList = dao.getColumnNameCN(colList, "view_gygl_ydwmqssqb"); 
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 获取查询月度文明寝室申请审核信息标题
	 * @param String shjb
	 * @param String userType
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYdwmqssqshToptr(String shjb,String userType,String userName){
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colCNList = null;
		String yhlx = getYhlx(userType, userName, shjb);
		
		if("fdy".equalsIgnoreCase(yhlx)){//公寓辅导员
			colList = new String[]{"pk", "xn", "xqmc", "yf", "ldmc", "qsh", "sqsj", 
	            "sqr", "fdysh", "xysh", "xxsh"};
			
		}else if("xy".equalsIgnoreCase(yhlx)){//学院
			colList = new String[]{"pk", "xn", "xqmc", "yf", "ldmc", "qsh", "sqsj", 
		            "sqr", "xysh", "xxsh"};
		}else{//学校
			colList = new String[]{"pk", "xn", "xqmc", "yf", "ldmc", "qsh", "sqsj", 
		            "sqr", "xxsh"};
		}
		
		colCNList = dao.getColumnNameCN(colList, "view_gygl_ydwmqssqb");
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 获取查询学期文明寝室申请审核信息标题
	 * @param String userType
	 * @param String userName
	 * @param String shjb
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXqwmqssqshToptr(String shjb,String userType,String userName){
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colCNList = null;
		String yhType = getYhlx(userType, userName, shjb);
		
		if("fdy".equalsIgnoreCase(yhType)){//公寓辅导员
			colList = new String[]{"pk", "xn", "xqmc", "ldmc", "qsh", "sqsj", 
	                               "sqr", "fdysh", "xxsh"};
			
		}else if("xy".equalsIgnoreCase(yhType)){//学院
			colList = new String[]{"pk", "xn", "xqmc", "ldmc", "qsh", "sqsj", 
		                           "sqr", "xysh", "xxsh"};
		}else{//学校
			colList = new String[]{"pk", "xn", "xqmc", "ldmc", "qsh", "sqsj", 
		                           "sqr", "xxsh"};
		}
		
		colCNList = dao.getColumnNameCN(colList, "view_gygl_xqwmqssqb");
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 获取查询学年文明寝室申请审核信息标题
	 * @param String shjb
	 * @param String userType
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXnwmqssqshToptr(String shjb,String userType,String userName){
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colCNList = null;
		String yhType = getYhlx(userType, userName, shjb);
		
		if("fdy".equalsIgnoreCase(yhType)){//公寓辅导员
			colList = new String[]{"pk", "xn", "ldmc", "qsh", "sqsj", 
	                               "sqr", "fdysh", "xxsh"};
			
		}else if("xy".equalsIgnoreCase(yhType)){//学院
			colList = new String[]{"pk", "xn", "ldmc", "qsh", "sqsj", 
		                           "sqr", "xysh", "xxsh"};
		}else{//学校
			colList = new String[]{"pk", "xn", "ldmc", "qsh", "sqsj", 
		                           "sqr", "xxsh"};
		}
		
		colCNList = dao.getColumnNameCN(colList, "view_gygl_xnwmqssqb");
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 保存学期文明寝室申请信息
	 * @param XsgyglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXqwmqssqb(XsgyglForm model,HttpServletRequest request) 
		throws Exception{
		gyglDao dao = new gyglDao();
		String tableName = "gygl_xqwmqssqb";
		String[] columns = null;
		String[] values = null;
		boolean result = false;
		String pk = "xn||xq||ssbh";
		String pkValue = StringUtils.joinStr(model.getXn(),model.getXq(),
											 model.getSsbh());
		if(dao.checkIsExists(tableName, pk, pkValue)){
			//记录已经存在，进行修改操作
			columns = new String[]{"xn", "xq", "ssbh", "sqr", "bz"};
			result = CommonUpdata.commonUpdata(columns, model, tableName, 
					                           pk, pkValue, request);
		}else{
			//记录不存在，执行增加操作
			columns = new String[]{"xn", "xq", "ssbh", "sqr", "bz"};
			values = new String[]{model.getXn(), model.getXq(), model.getSsbh(), 
					              model.getSqr(), model.getBz()};
			result = StandardOperation.insert(tableName, columns, values, request);			
		}
		return result;
	}
	
	/**
	 * 根据主键查询学期文明寝室申请表信息
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> queryXqwmqssqb(XsgyglForm model,
			String userType,String userName){
		gyglDao dao = new gyglDao();
		String yhType = getYhlx(userType, userName, model.getShjb());
		return dao.selectXqwmqssqbByPk(model,yhType);
	}
	
	/**
	 * 删除学期文明寝室申请表信息
	 * @param XsgyglForm model
	 * @return boolean
	 * */
	public boolean delXqwmqssqb(XsgyglForm model){
		gyglDao dao = new gyglDao();
		return dao.deleteXqwmqssqb(model);
	}
	
	/**
	 * 查询学期文明寝室申请表信息（含分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryXqwmqssqxx(XsgyglForm model) throws Exception{
		gyglDao dao = new gyglDao();
		return dao.selectXqwmqssqxx(model);
	}
	
	/**
	 * 获取查询学期文明寝室申请信息标题
	 * @param List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXqwmqssqToptr(){
		DAO dao = DAO.getInstance();
		String[] colList = {"pk", "xn", "xqmc", "ldmc", "qsh", "sqsj", 
				            "sqr", "fdysh", "xxsh"};
		String[] colCNList = dao.getColumnNameCN(colList, "view_gygl_xqwmqssqb"); 
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 查询学期文明寝室申请表信息（不分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryXqwmqssqxxAll(XsgyglForm model,String[] outputList) throws Exception{
		gyglDao dao = new gyglDao();
		return dao.selectXqwmqssqxxAll(model,outputList);
	}
	
	/**
	 * 查询学期文明寝室申请审核信息（含分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryXqwmqssqshxx(XsgyglForm model,
			String userType,String userName) throws Exception{
		gyglDao dao = new gyglDao();
		String yhlx = getYhlx(userType, userName, model.getShjb());
		return dao.selectXqwmqssqshxx(model,yhlx,userName);
	}
	
	/**
	 * 学期文明寝室申请审核
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean audiXqwmqssqb(XsgyglForm model, HttpServletRequest request,
			String userType, String userName) throws Exception{
		boolean result = false;
		String[] updateCol = null;
		String time = GetTime.getSystemTime();
		String yhType = getYhlx(userType, userName, model.getShjb());
		
		if("fdy".equalsIgnoreCase(yhType)){//公寓辅导员
			model.setFdyshsj(time);
			updateCol = new String[]{"fdysh", "fdyshyj", "fdyshsj"};
		}else if("xy".equalsIgnoreCase(yhType)){//学院
			model.setXyshsj(time);
			updateCol = new String[]{"xysh", "xyshyj", "xyshsj"};
		}else{//学校
			model.setXxshsj(time);
			updateCol = new String[]{"xxsh", "xxshyj", "xxshsj"};
		}
		
		String pk = StringUtils.joinStr(model.getXn(), model.getXq(), 
				                        model.getSsbh());
		//保存审核信息
		result = CommonUpdata.commonUpdata(updateCol, model, "gygl_xqwmqssqb", 
				                           "xn||xq||ssbh", pk, request);
		return result;
	}	
	
	/**
	 * 学期文明寝室申请审核
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean audiXqwmqssqbBatch(XsgyglForm model, String shjg,String userType, 
			String userName) throws Exception{
		boolean result = false;
		gyglDao dao = new gyglDao();
		String yhType = getYhlx(userType, userName,model.getShjb());
		
		result = dao.audiXqwmqssqbBatch(model,shjg,yhType); 
		return result;
	}
	
	/**
	 * 根据主键查询学年文明寝室申请表信息
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> queryXnwmqssqb(XsgyglForm model,
			String userType,String userName){
		gyglDao dao = new gyglDao();
		String yhlx = getYhlx(userType, userName, model.getShjb());
		return dao.selectXnwmqssqbByPk(model,yhlx);
	}
	
	/**
	 * 删除学年文明寝室申请表信息
	 * @param XsgyglForm model
	 * @return boolean
	 * */
	public boolean delXnwmqssqb(XsgyglForm model){
		gyglDao dao = new gyglDao();
		return dao.deleteXnwmqssqb(model);
	}
	
	/**
	 * 查询学年文明寝室申请表信息（含分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryXnwmqssqxx(XsgyglForm model) throws Exception{
		gyglDao dao = new gyglDao();
		return dao.selectXnwmqssqxx(model);
	}
	
	/**
	 * 获取查询学年文明寝室申请信息标题
	 * @param List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXnwmqssqToptr(){
		DAO dao = DAO.getInstance();
		String[] colList = {"pk", "xn", "ldmc", "qsh", "sqsj", 
				            "sqr", "fdysh", "xysh", "xxsh"};
		String[] colCNList = dao.getColumnNameCN(colList, "view_gygl_xnwmqssqb"); 
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * 查询学年文明寝室申请表信息（不分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryXnwmqssqxxAll(XsgyglForm model,String[] outputList) throws Exception{
		gyglDao dao = new gyglDao();
		return dao.selectXnwmqssqxxAll(model,outputList);
	}
	
	/**
	 * 保存学年文明寝室申请信息
	 * @param XsgyglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXnwmqssqb(XsgyglForm model,HttpServletRequest request) 
		throws Exception{
		gyglDao dao = new gyglDao();
		String tableName = "gygl_xnwmqssqb";
		String[] columns = null;
		String[] values = null;
		boolean result = false;
		String pk = "xn||ssbh";
		String pkValue = StringUtils.joinStr(model.getXn(), model.getSsbh());
		if(dao.checkIsExists(tableName, pk, pkValue)){
			//记录已经存在，进行修改操作
			columns = new String[]{"xn", "ssbh", "sqr", "bz"};
			result = CommonUpdata.commonUpdata(columns, model, tableName, 
					                           pk, pkValue, request);
		}else{
			//记录不存在，执行增加操作
			columns = new String[]{"xn","ssbh", "sqr", "bz"};
			values = new String[]{model.getXn(),model.getSsbh(), model.getSqr(), 
					              model.getBz()};
			result = StandardOperation.insert(tableName, columns, values, request);			
		}
		return result;
	}
	
	/**
	 * 查询学年文明寝室申请审核信息（含分页）
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryXnwmqssqshxx(XsgyglForm model,
			String userType,String userName) throws Exception{
		gyglDao dao = new gyglDao();
		String yhType = getYhlx(userType, userName, model.getShjb());
		return dao.selectXnwmqssqshxx(model,yhType,userName);
	}
	
	/**
	 * 学年文明寝室申请审核
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean audiXnwmqssqb(XsgyglForm model, HttpServletRequest request,
			String userType, String userName) throws Exception{
		boolean result = false;
		String[] updateCol = null;
		String time = GetTime.getSystemTime();
		String yhType = getYhlx(userType, userName, model.getShjb());
		
		if("fdy".equalsIgnoreCase(yhType)){//公寓辅导员
			model.setFdyshsj(time);
			updateCol = new String[]{"fdysh", "fdyshyj", "fdyshsj"};
		}else if("xy".equalsIgnoreCase(yhType)){//学院
			model.setXyshsj(time);
			updateCol = new String[]{"xysh", "xyshyj", "xyshsj"};
		}else{//学校
			model.setXxshsj(time);
			updateCol = new String[]{"xxsh", "xxshyj", "xxshsj"};
		}
		
		String pk = StringUtils.joinStr(model.getXn(), model.getXq(), 
				                        model.getSsbh());
		//保存审核信息
		result = CommonUpdata.commonUpdata(updateCol, model, "gygl_xnwmqssqb", 
				                           "xn||ssbh", pk, request);
		return result;
	}	
	
	/**
	 * 学年文明寝室申请审核
	 * @param XsgyglForm model
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean audiXnwmqssqbBatch(XsgyglForm model, String shjg,String userType, 
			String userName) throws Exception{
		boolean result = false;
		gyglDao dao = new gyglDao();
		String yhType = getYhlx(userType, userName,model.getShjb());
		
		result = dao.audiXnwmqssqbBatch(model,shjg,yhType); 
		return result;
	}
	
	/**
	 * 判断审核通过寝室数是否超过设置的比例
	 * @param XsgyglForm model
	 * @return String
	 * */
	public String checkWmqsbl(XsgyglForm model){
		gyglDao dao = new gyglDao();		
		return dao.checkWmqsbl(model);
	}
	
	/**
	 * 获取用户类型
	 * @param String userType
	 * @param String userName
	 * @param String shjb
	 * @return String
	 * */
	public String getYhlx(String userType, String userName,String shjb){
		String yhType = "xx";//学校
		if("2".equalsIgnoreCase(shjb)){//2级审核
			if(gyglDao.isGyFdy(userName)){//公寓辅导员
				yhType = "fdy";
			}
//			if("xy".equalsIgnoreCase(userType)){//学院
//				yhType = userType;
//			}
		}else if("3".equalsIgnoreCase(shjb)){//三级审核
			if(gyglDao.isGyFdy(userName)){//公寓辅导员
				yhType = "fdy";
			}else if("xy".equalsIgnoreCase(userType)){//学院
				yhType = userType;
			}
		}
		
		return yhType;
	}
	
	/**
	 * 月度文明寝室申请级别
	 * @return String
	 * */
	public String getYdwmqsshjb(){
		return "1";
	}
	
	/**
	 * 学期文明寝室申请级别
	 * @return String
	 * */
	public String getXqwmqsshjb(){
		return "2";
	}
	
	/**
	 * 学年文明寝室申请级别
	 * @return String
	 * */
	public String getXnwmqsshjb(){
		return "2";
	}
}

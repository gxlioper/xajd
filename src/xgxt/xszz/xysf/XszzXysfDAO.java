package xgxt.xszz.xysf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xszz.XszzTyForm;

public class XszzXysfDAO {

	/**
	 * 获得导出数据表头
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		if ("kns".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "序号", "姓名", "证件号码", "性别", "民族", "院系名称",
					"专业名称", "班级名称", "入学年份", "学号", "考生号", "入学年月", "户口类型",
					"政治面貌", "品德表现", "成绩等级", "生源地", "家庭人口", "家庭年收入", "原毕业高校",
					"个人特长", "家庭通讯地址", "邮编", "家庭电话", "宿舍号", "个人电话", "个人电子邮箱",
					"即时通信号码", "学制", "家庭经济困难等级", "申请理由"};
			colListEN = new String[] { "xuh", "xm", "sfzh", "xb", "mz", "xymc",
					"zymc", "bjmc", "rxny", "xh", "ksh", "rxny", "hklx",
					"zzmm", "pdbx", "cjdj", "syd", "jtrk", "jtnsr", "byxx",
					"grtc", "jttxdd", "yb", "jtdh", "ssh", "lxdh", "yx",
					"txhm", "xz", "kndj", "sqly" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}
		
	/**
	 * 获取绿色通道统计表数据
	 * 
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 */
	public List<String[]> getLstdData(String xmdm, XszzTyForm model) {

		DAO dao = DAO.getInstance();
		// 学年
		String xn = model.getXn();
		// 学院
		String xydm = model.getXydm();
		// 审核级别
		String shjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*, b.jtdz,rownum num from (select a.xh,b.xm,");
		sql.append("a.yjje,a.hjje,b.zkzh,b.zymc,b.bjmc from　xszz_lstdb a,");
		sql.append("view_xsxxb b where a.xh = b.xh ");
		sql.append(" and b.xydm = ? ");
		sql.append(" and a.xn = ? ");
		sql.append("一级审核".equalsIgnoreCase(shjb) ? " and a.shzt1 = '通过' " : "");
		sql.append("两级审核".equalsIgnoreCase(shjb) ? " and a.shzt2 = '通过' " : "");
		sql.append("三级审核".equalsIgnoreCase(shjb) ? " and a.shzt3 = '通过' " : "");
		sql.append(") a left join ");
		sql.append("(select a.* from jtqkdcb a where exists (select 1 ");
		sql.append("from (select xh, max(sqsj) sqsj from jtqkdcb ");
		sql.append("group by xh) b where a.xh = b.xh and ");
		sql.append("a.sqsj = b.sqsj)) b on a.xh = b.xh ");

		String[] inputValue = new String[] { xydm, xn };
		String[] outputValue = new String[] { "num", "xm", "zkzh", "jtdz",
				"zymc", "bjmc", "yjje", "hjje" };

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * 获取困难生统计表数据
	 * 
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 */
	public ArrayList<String[]>  getKnsData(String xmdm,XszzTyForm model) {
		
		DAO dao = DAO.getInstance();
		// 年度
		String nd = model.getNd();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 审核级别
		String shjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*,b.jthk,b.jtrs,b.jtnzsr,b.jtdz,b.jtyb,b.jtdh, ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = b.szsheng) syd, ");
		sql.append("rownum num from (select b.xm, b.sfzh, b.xb, b.mzmc, b.xymc, ");
		sql.append("b.zymc, b.bjmc, substr(b.rxrq, 0, 4) rxrq, a.xh, ");
		sql.append("b.ksh, b.rxrq rxny, b.zzmmmc, a.pdbx, a.cjdj, b.byxx, ");
		sql.append("b.tc, b.ssbh, b.lxdh, b.dzyx, b.qqhm, b.xz, ");
		sql.append("a.sqly, a.xmzzjb   from xszz_knsb a, view_xsxxb b ");
		sql.append("where a.xh = b.xh and a.nd = ? ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		sql.append("一级审核".equalsIgnoreCase(shjb) ? " and a.shzt1 = '通过' " : "");
		sql.append("两级审核".equalsIgnoreCase(shjb) ? " and a.shzt2 = '通过' " : "");
		sql.append("三级审核".equalsIgnoreCase(shjb) ? " and a.shzt3 = '通过' " : "");
		sql.append(") a ");
		sql.append("left join (select a.* from jtqkdcb a ");
		sql.append("where exists (select 1 from (select xh, max(sqsj) sqsj ");
		sql.append("from jtqkdcb group by xh) b where a.xh = b.xh ");
		sql.append("and a.sqsj = b.sqsj)) b on a.xh = b.xh ");

		String[] inputValue = new String[] { nd };
		String[] outputValue = new String[] { "NUM", "XM", "SFZH", "XB",
				"MZMC", "XYMC", "ZYMC", "BJMC", "RXRQ", "XH", "KSH", "RXNY",
				"JTHK", "ZZMMMC", "PDBX", "CJDJ", "SYD", "JTRS", "JTNZSR",
				"BYXX", "TC", "JTDZ", "JTYB", "JTDH", "SSBH", "LXDH", "DZYX",
				"QQHM", "XZ", "XMZZJB", "SQLY" };

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
}

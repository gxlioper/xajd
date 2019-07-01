package xgxt.xszz.hndx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.jxgl.JxglTyDAO;
import xgxt.jxgl.JxglTyForm;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.sztz.bjlhdx.QuerryModel;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzDAO;
import xgxt.xszz.XszzTyForm;

public class XszzHndxDAO extends XszzDAO {

	/**
	 * 获得学院人数列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXyRsList(XszzTyForm model) {
		
		DAO dao = DAO.getInstance();
		
		//学年
		String xn = model.getXn();
		
		//经济困难生级别列表
		List<HashMap<String, String>> djList = dao.getWhList(
				"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
		
		//SQL
		StringBuilder xySb = new StringBuilder();
		xySb.append("select a.xydm, a.xymc, b.num,? xn ");
		xySb.append("from (select distinct xydm, xymc from view_njxyzybj) a ");
		xySb.append("left join (select xydm, count(xh) num from  ");
		xySb.append("view_xsjbxx group by xydm) b on a.xydm = b.xydm ");
		
		String[] inputValue = new String[]{xn};
		String[] outputValue = new String[] { "xydm", "xn", "xymc", "num", };
		
		//SQL
		StringBuilder jbSb = new StringBuilder();
		if (djList != null && djList.size() > 0) {
			
			outputValue = new String[3 + djList.size()];
			outputValue[0] = "xydm";
			outputValue[1] = "xn";
			outputValue[2] = "xymc";
			outputValue[3] = "num";
			
			jbSb.append("select a.* ");
			for (int i = 1; i < djList.size(); i++) {
				
				HashMap<String, String> map = djList.get(i);
				String jb = "jb" + map.get("dm");
				jbSb.append(" ,");
				jbSb.append("nvl((select b.xmrs from hndx_xszz_xyrsb b where a.xydm = b.szxy and b.xn = '"+xn+"' and b.xmmc = '经济困难生认定' and b.xmjb = '"+map.get("dm")+"'),0) ");
				jbSb.append(jb);
				
				outputValue[3 + i] = jb;
			}
			jbSb.append(" from (");
			jbSb.append(xySb);
			jbSb.append(" ) a");
		}
		
		String sql = Base.isNull(jbSb.toString())?xySb.toString():jbSb.toString();
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	

	/**
	 * 获得学院人数比例(批量)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyRsBlList(XszzTyForm model) {

		DAO dao = DAO.getInstance();
		
		// 学年
		String xn = model.getXn();
		// 项目名称
		String xmmc = model.getXmmc();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select a.dm, a.mc, b.xmbl from hndx_xszz_jjknsjb a ");
		sql.append("left join view_hddx_xyrsbl b on a.dm = b.xmjb  ");
		sql.append("and b.xn =? and b.xmmc = ? order by a.dm ");

		String[] inputValue = new String[] { xn, xmmc };
		String[] outputValue = new String[] { "dm", "mc", "xmbl" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得学院人数(单个操作)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXySzRsList(XszzTyForm model) {

		DAO dao = DAO.getInstance();

		// 学年
		String xn = model.getXn();
		// 项目名称
		String xmmc = model.getXmmc();
		// 学院代码
		String xydm = model.getXydm();
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select a.dm, a.mc,b.xmbl, b.xmjb,nvl(b.xmrs,0) xmrs from hndx_xszz_jjknsjb a ");
		sql.append("left join hndx_xszz_xyrsb b on a.dm = b.xmjb  ");
		sql.append("and b.xn =? and b.xmmc = ? and b.szxy = ? order by a.dm ");

		String[] inputValue = new String[] { xn, xmmc, xydm };
		String[] outputValue = new String[] { "dm", "mc","xmbl","xmjb", "xmrs" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}

	/**
	 * 获得批量审核学生所在学院当前学年的项目设置人数
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPlshXyZrsList(XszzTyForm model) {

		DAO dao = DAO.getInstance();

		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();
		// 项目级别
		String xmjb = model.getKnsjb();
		// 项目名称
		String xmmc = model.getXmmc();
		// 审核字段
		String shzd = model.getShzd();
		// 学年
		String xn = model.getXn();

		if (pkValue == null || pkValue.length == 0) {
			return null;
		}

		// SQL
		StringBuilder sql = new StringBuilder();

		// 申请人数
		sql.append("select a.xydm, a.xymc, a.sqrs, nvl(b.qttgr, 0) qttgr ");
		sql.append(", nvl(c.xmrs, 0) xmrs from (select xydm, xymc ");
		sql.append(", count(xh) sqrs from view_hddx_jjknssq ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i == 0) {
				sql.append("where pk = '" + pkValue[i] + "' ");
			} else {
				sql.append("or pk = '" + pkValue[i] + "' ");
			}
		}
		sql.append(" group by xydm, xymc) a ");

		// 其他申请人数
		sql.append("left join (select a.xydm, count(a.xh) qttgr from view_hddx_jjknssq a ");
		sql.append("where a.knsjb = '" + xmjb + "'");
		sql.append("and a." + shzd + " = '通过' ");
		sql.append("and xn = '" + xn + "' ");
		sql.append("and not exists (select 1 from (select pk from view_hddx_jjknssq ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i == 0) {
				sql.append("where pk = '" + pkValue[i] + "' ");
			} else {
				sql.append("or pk = '" + pkValue[i] + "' ");
			}
		}
		sql.append(") b where a.pk = b.pk) group by a.xydm) b on a.xydm = b.xydm ");
		
		// 学院设置人数
		sql.append("left join (select a.szxy xydm, xmrs from hndx_xszz_xyrsb a ");
		sql.append("where a.xmmc = '"+xmmc+"' ");
		sql.append("and a.xmjb = '"+xmjb+"' ");
		sql.append("and exists (select 1 from (select distinct xn, xydm from view_hddx_jjknssq ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i == 0) {
				sql.append("where pk = '" + pkValue[i] + "' ");
			} else {
				sql.append("or pk = '" + pkValue[i] + "' ");
			}
		}	                             
		sql.append(") b where a.xn = b.xn and a.szxy = b.xydm)) c on a.xydm = c.xydm ");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "xydm", "xymc","sqrs","qttgr","xmrs" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 根据学院代码获取学院名称
	 * @param xydm
	 * @return String
	 * */
	public String getXymc(String xydm){
		DAO dao = DAO.getInstance();
		String sql = "select distinct xymc from view_njxyzybj where xydm=?";
		String xymc = dao.getOneRs(sql, new String[]{xydm}, "xymc");
		
		return StringUtils.isNull(xymc) ? "" : xymc;
	}
	
	/**
	 * 获取困难生统计表数据
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getKnsData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {model.getXydm()};
		String[] outputValue = {"r", "xm", "xh", "xymc", "zybj", "jg", "csrq",
				                "sfzh", "xb", "mzmc", "xmzzjb", "jzqdqk", "ssbh",
				                "lxdh", "jtdz", "jtyb", "jtdh", "xiaoqumc", "bz"};
		
		
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc||bjmc from view_xsjbxx b where a.xh=b.xh)zybj,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,",
				                         "(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh,",
				                         "(select jg from view_xsxxb b where a.xh=b.xh)jg,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select jtdz from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtdz,",
				                         "(select jtyb from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtyb,",
				                         "(select jtdh from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtdh,",
				                         "a.xmzzjb,a.jzqdqk,a.bz,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,",
				                         "(select xqmc from dm_zju_xq b where ",
				                         "b.dm=(select xiaoqudm from jtqkdcb c where c.xh=a.xh and sqsj=(select max(sqsj) from jtqkdcb d where d.xh=a.xh)))xiaoqumc from xszz_knsb a ) ",
				                         "a where xydm=?");
		
		StringBuilder sb = new StringBuilder();//条件
		if(StringUtils.isNotNull(model.getXn())){//学年
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//年度
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//学期
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//增加审核通过条件
		sb.append(getShtgtj(xmdm));
		
		//查询
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//参数设置信息
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//学生地质信息取代码
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[5] = xsxxglDao.dzxxdmToMc(list.get(i)[5]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * 获取国家奖学金统计表数据
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "xh", "xm", "xb", "csrq",
				                "hkszd", "mzmc", "zymc", "nj", "rxrq", "xiaoqumc",
				                "yhkh", "bz"};
		
		
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'海南大学'xxmc from xszz_gjjxjb a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//条件
		if(StringUtils.isNotNull(model.getXn())){//学年
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//年度
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//学期
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//增加审核通过条件
		sb.append(getShtgtj(xmdm));
		
		//查询
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//参数设置信息
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//学生地质信息取代码
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[7] = xsxxglDao.dzxxdmToMc(list.get(i)[7]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * 获取国家励志奖学金统计表数据
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjlzjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "xh", "xm", "xb", "csrq",
				                "hkszd", "mzmc", "zymc", "nj", "rxrq", "yhkh", "xmzzjb",
				                "xiaoqumc", "bz"};
		//困难生申请周期
		String knzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", XszzXmdm.XSZZ_KNS);
		//联结条件
		StringBuilder knsb = new StringBuilder();
		if("学年".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xn=b.xn ");
		}else if("年度".equalsIgnoreCase(knzq)){
			knsb.append(" and a.nd=b.nd ");
		}else if("学期".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xq=b.xq ");
		}else if("无周期".equalsIgnoreCase(knzq)){
			knsb.append(" and sqsj=(select max(sqsj) from xszz_knsb c where c.xh=a.xh) ");
		}
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "(select xmzzjb from xszz_knsb b where a.xh=b.xh",
				                         knsb.toString(),
				                         ")xmzzjb,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'海南大学'xxmc from gjlzjxj a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//条件
		if(StringUtils.isNotNull(model.getXn())){//学年
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//年度
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//学期
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//增加审核通过条件
		sb.append(getShtgtj(xmdm));
		
		//查询
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//参数设置信息
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//学生地质信息取代码
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[7] = xsxxglDao.dzxxdmToMc(list.get(i)[7]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * 获取国家助学金统计表数据
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjzxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "xh", "xm", "xb", "csrq",
				                "hkszd", "mzmc", "zymc", "nj", "rxrq", "xmzzjb",
				                "xmzzje", "yhkh", "xiaoqumc", "bz"};
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'海南大学'xxmc from gjzxj a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//条件
		if(StringUtils.isNotNull(model.getXn())){//学年
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//年度
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//学期
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//增加审核通过条件
		sb.append(getShtgtj(xmdm));
		
		//查询
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//参数设置信息
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//学生地质信息取代码
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[7] = xsxxglDao.dzxxdmToMc(list.get(i)[7]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * 获取优秀困难生奖学金统计表数据
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getYxpksjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "zymc", "xm", "xb", "csrq",
								"mzmc", "xh", "rxrq", "jtdz", "yhkh", "bz"};
						
		StringBuilder sb = new StringBuilder();//条件
		if(StringUtils.isNotNull(model.getXn())){//学年
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//年度
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//学期
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//增加审核通过条件
		sb.append(getShtgtj(xmdm));
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "(select jtdz from jtqkdcb b where a.xh=b.xh ",
				                         " and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtdz,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'海南大学'xxmc from xszz_hnsgxyxpksjxj a ) ",
				                         "a where 1=1 ");
		
		//查询
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);		
		return list;	
	}
	
	/**
	 * 中海油大学生助学基金汇总统计表数据
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getZhydxszxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"xm", "xb", "xmzzjb", "mzmc", "jtsfqz", "myshf", 
								"xyzymc", "lxdh", "sfzh"};
		
		StringBuilder sb = new StringBuilder();//条件
		if(StringUtils.isNotNull(model.getXn())){//学年
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//年度
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//学期
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//增加审核通过条件
		sb.append(getShtgtj(xmdm));
		
		//困难生申请周期
		String knzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", XszzXmdm.XSZZ_KNS);
		//联结条件
		StringBuilder knsb = new StringBuilder();
		if("学年".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xn=b.xn ");
		}else if("年度".equalsIgnoreCase(knzq)){
			knsb.append(" and a.nd=b.nd ");
		}else if("学期".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xq=b.xq ");
		}else if("无周期".equalsIgnoreCase(knzq)){
			knsb.append(" and sqsj=(select max(sqsj) from xszz_knsb c where c.xh=a.xh) ");
		}
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select xymc||zymc from view_xsjbxx b where a.xh=b.xh)xyzymc,",
				                         "(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh,",
				                         "(select xb from view_xsxxb b where a.xh=b.xh)xb,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
				                         "(select xmzzjb from xszz_knsb b where a.xh=b.xh ",
				                         knsb.toString(),
				                         ")xmzzjb,",
				                         "(select sfqz from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtsfqz,",
				                         " a.shf myshf,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.xmdm",
				                         " from xszz_comm_zzwsb a ) ",
				                         "a where xmdm='3003'");
		
		//查询
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);		
		return list;	
	}
	
	/**
	 * 获取学生汇总情况
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuCollect(XszzTyForm model){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = model.getXn();
		String shtj = getShtgtj(XszzXmdm.XSZZ_KNS).toString();
		String sql = StringUtils.joinStr("select (select count(*) from view_xsxxb where sfzx='在校') zxrs,",
					 "(select count(*) from xszz_knsb where xn=? and xmzzjb like '%特%困%' ",shtj,")tksrs,",
					 "(select count(*) from xszz_knsb where xn=? and xmzzjb like '%贫%困%' ",shtj,")pksrs,",
					 "(select count(*) from xszz_knsb a where xn=? and xmzzjb like '%特%困%'",shtj,"  and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.nj=(select max(nj) from view_xsjbxx))) xstkrs,",
					 "(select count(*) from xszz_knsb a where xn=? and xmzzjb like '%贫%困%' ",shtj," and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.nj=(select max(nj) from view_xsjbxx))) xspkrs,",
					 "(select count(*) from jtqkdcb a where xn=? and jthk='农村' ","and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh and a.xn=b.xn))ncxss",
			         " from dual");
		//在校总人数
		//特困生人数
		//贫困生人数
		//农村籍学生数
		//特困新生人数
		//贫困新生人数
		map = dao.getMap(sql, new String[]{xn,xn,xn,xn,xn}, new String[]{"zxrs", "tksrs", "pksrs", "xstkrs", "xspkrs", "ncxss"});
		return map;		
	}
	
	/**
	 * 获取审核通过条件
	 * @param xmdm
	 * @return StringBuilder
	 * */
	private StringBuilder getShtgtj(String xmdm){
		DAO dao = DAO.getInstance();		
		StringBuilder sb = new StringBuilder();
		String shjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);
		
		if("一级审核".equalsIgnoreCase(shjb)){
			sb.append(" and shzt1='通过'");
		}else if("二级审核".equalsIgnoreCase(shjb)){
			sb.append(" and shzt2='通过'");
		}
		else if("三级审核".equalsIgnoreCase(shjb)){
			sb.append(" and shzt3='通过'");
		}
		return sb;
	}
}

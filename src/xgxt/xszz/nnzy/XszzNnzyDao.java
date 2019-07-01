package xgxt.xszz.nnzy;

import java.util.HashMap;
import java.util.List;

import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

public class XszzNnzyDao {
	
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
		String[] outputValue = {"r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
				                "mzmc", "rxrq"};
		
		
		
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
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from xszz_gjjxjb a ) ",
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
		String[] outputValue = {"r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
                "mzmc", "rxrq"};
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
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "(select xmzzjb from xszz_knsb b where a.xh=b.xh",
				                         knsb.toString(),
				                         ")xmzzjb,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from gjlzjxj a ) ",
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
		String[] outputValue = {"r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
                "mzmc", "rxrq"};
		
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
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from gjzxj a ) ",
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
	 * 自治区人民政府奖学金
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getZzqrmzfjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm","xb","mzmc","csrq","xybj","xh","rxrq","jtdz","lxdh", "sfzh"};
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select zymc||'、'||bjmc from view_xsjbxx b where a.xh=b.xh)xybj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select jtdz from view_xsxxb b where a.xh=b.xh)jtdz,",
				                         "(select lxdh from view_xsxxb b where a.xh=b.xh)lxdh,",
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from xszz_comm_zzwsb a where xmdm='4001' ) ",
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
	 * 校长奖学金
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getXzjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm","xb","mzmc","csrq","xybj","xh","rxrq","jtdz","lxdh", "sfzh"};
		
		//查询所有要显示的信息语句
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select zymc||'、'||bjmc from view_xsjbxx b where a.xh=b.xh)xybj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select jtdz from view_xsxxb b where a.xh=b.xh)jtdz,",
				                         "(select lxdh from view_xsxxb b where a.xh=b.xh)lxdh,",
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from xszz_comm_zzwsb a where xmdm='4002' ) ",
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

package xgxt.pjpy.lsxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.utils.String.StringUtils;

public class LsxyPjpyDAO extends DAO {
	
	/**
	 * 查询学生综合素质测评汇总数据
	 * @param model
	 * */
	public List<HashMap<String, String>> selectXszhszcphzxx(PjpyLsxyForm model){
		String[] outputValue = {"xh", "xm", "zcj", "dcj", "tcj", "nlf", "tbjf", 
								"zf", "zfpm", "zyzfpm", "cj", "cjpm", "zycjpm"};
		String sql = StringUtils.joinStr("select a.*, ",
				"dense_rank() over (partition by xydm,nj,zydm order by to_number(nvl(zf,0)) desc nulls last)zyzfpm,  ",
				"dense_rank() over (partition by xydm,nj,zydm order by to_number(nvl(cj,0)) desc nulls last)zycjpm, ",
				"dense_rank() over (partition by xydm,nj,bjdm order by to_number(nvl(cj,0)) desc nulls last)cjpm ",
				"from (select xh,xn,xqmc,xm,bjdm,xydm,nj,zydm,",
				"(select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='001' and b.jb='2') zcj,",
				"(select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='002' and b.jb='2') dcj,",
				"(select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='003' and b.jb='2') tcj,",
				"(select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='004' and b.jb='2') nlf,",
				"(select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='005' and b.jb='2') tbjf,",
				"(select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='999') zf,",
				"(select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xq and a.xq=b.xq and b.dm='999') zfpm," +
				"(select cj from view_lsxy_cjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)cj ",
				" from view_pjpy_zhszcpb a where xn=? and xq=? and bjdm=? group by xh,xn,xq,xqmc,xm,nj,xydm,zydm,bjdm) a");
		return getList(sql, new String[]{model.getXn(), model.getXq(), model.getBjdm()}, outputValue);
		
	}
	
	/**
	 * 获取同年级同专业人数
	 * @param bjdm
	 * @return String
	 * */
	public String getBnjzyrs(String bjdm){
		String sql = StringUtils.joinStr("select count(*)num from view_xsjbxx where " ,
				"nj=(select distinct nj from view_njxyzybj b where bjdm=?) ",
				"and zydm=(select distinct zydm from view_njxyzybj b where bjdm=?)");
		return getOneRs(sql, new String[]{bjdm,bjdm}, "num");
	}
	
	/**
	 * 获取个人奖学金评定信息
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel, HashMap<String, String> resMap){
		String sqlP = "";
		String zfblmc = ",'0' zfblmc";
		String zyzfblmc = ", '0' zyzfblmc";
		String cjblmc = ",'0' cjblmc";
		String zycjblmc = ", '0' zycjblmc";
		if(StringUtils.isNotNull(jxjpdModel.getZfpmbl())){
			zfblmc = StringUtils.joinStr(",(select count(*)*",jxjpdModel.getZfpmbl(),"/100 from view_xsjbxx b where a.bjdm=b.bjdm) zfblmc");
		}
		if(StringUtils.isNotNull(jxjpdModel.getZyzfpmbl())){
			zyzfblmc = StringUtils.joinStr(",(select count(*)*",jxjpdModel.getZyzfpmbl(),"/100 from view_xsjbxx b where a.bjdm=b.bjdm) zyzfblmc");
		}
		if(StringUtils.isNotNull(jxjpdModel.getCjpmbl())){
			cjblmc = StringUtils.joinStr(",(select count(*)*",jxjpdModel.getCjpmbl(),"/100 from view_xsjbxx b where a.bjdm=b.bjdm) cjblmc");
		}
		if(StringUtils.isNotNull(jxjpdModel.getZycjpmbl())){
			zycjblmc = StringUtils.joinStr(",(select count(*)*",jxjpdModel.getZycjpmbl(),"/100 from view_xsjbxx b where a.bjdm=b.bjdm) zycjblmc");
		}
		sqlP += StringUtils.joinStr(zfblmc,zyzfblmc,cjblmc,zycjblmc,",");
		//tzjkcj=体质健康成绩   bjrs =班级人数
		String[] opList = new String[] { "zcj", "dcj", "tcj", "nlf", "tbjf", 
										 "zf", "zfpm", "zyzfpm", "cj", "cjpm", 
										 "zycjpm", "tzjkcj", "bjrs", "xn", "zfblmc", 
										 "zyzfblmc", "cjblmc", "zycjblmc"};
		String sql = StringUtils.joinStr("select * from(select xh,xn",sqlP,
					"(select count(*) from view_xsjbxx b where a.bjdm=b.bjdm) bjrs,zcj,dcj,tcj,nlf,tbjf,zf,tzjkcj,",
					"dense_rank() over (partition by xydm,nj,zydm order by to_number(nvl(zf,0)) desc nulls last)zyzfpm,",
					"dense_rank() over (partition by xydm,nj,bjdm order by to_number(nvl(zf,0)) desc nulls last)zfpm,",
					"cj,dense_rank() over (partition by xydm,nj,zydm order by to_number(nvl(cj,0)) desc nulls last)zycjpm,",
					"dense_rank() over (partition by xydm,nj,bjdm order by to_number(nvl(cj,0)) desc nulls last)cjpm",
					" from (",
					"	select xh,xn,xydm,zydm,bjdm,nj,",
					"          (select round(sum(fs)/2,3) from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and b.dm='001' and  b.jb='2')zcj,",
					"		   (select round(sum(fs)/2,3) from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and b.dm='002' and  b.jb='2')dcj,",
					"		   (select round(sum(fs)/2,3) from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and b.dm='003' and  b.jb='2')tcj,",
					"          (select round(sum(fs)/2,3) from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and b.dm='004' and  b.jb='2')nlf,",
					"          (select round(sum(fs)/2,3) from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and b.dm='005' and b.jb='2')tbjf,",
					"          (select round(sum(fs)/2,3) from pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and b.dm='999')zf,",
					"          (select round(sum(cj)/2,3) from view_lsxy_cjb b where a.xh=b.xh and a.xn=b.xn)cj," +
					"		   (select round(sum(cj)/2,3) from view_zhhcjb b where a.xh=b.xh and a.xn=b.xn and (b.kcmc='体育课' or b.kcmc='体能测试'))tzjkcj from view_pjpy_zhszcpb a",
					"          group by xh,xydm,zydm,bjdm,nj,xn",
					")a) where xh=? and xn=? ");
		resMap.putAll(getMap(sql, new String[]{jxjpdModel.getXh(),jxjpdModel.getXn()}, opList));
		//是否有违纪受处分
		PjpyCommonInterface pjInterface = new PjpyCommonInterface();
		resMap = resMap == null ? new HashMap<String, String>() : resMap;
		List<String[]> list = pjInterface.queryStuCfxx(resMap);
		if(list != null){
			resMap.put("sfwj", list.size()>0 ? "是" : "否");
		}
		
		return resMap;
	}
	
	/**
	 * 条件列表
	 * @param map
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTjList(JxjpdxxModel model, String xmlx) {		
		String sql = "select * from zjcm_pjpy_tjsz where xn=? and lx=? and jxjdm=?";
		if(StringUtils.isNotNull(model.getXq())){
			sql += StringUtils.joinStr(" and xq='", model.getXq(), "'");
			
		}
		return getList(sql, new String[] { model.getXn(), xmlx,
				model.getJxjdm() }, new String[] { "tjzd", "tjlx", "tjz" });
	}
	
	/**
	 * 判断单科成绩是否符合条件
	 * @param argMap
	 * @param tjMap
	 * @return boolean
	 * */
	public boolean getDkcj(HashMap<String, String> argMap, HashMap<String, String> tjMap, String kclx){
		int zms = 0; //总门数
		int fhtjms = 0;//符合条件门数
		String whereSql = "";
		//课程类型
		if(StringUtils.isNotNull(kclx)){
			whereSql += " and kclx='" + kclx + "'";
		}
		//符合条件的课程门数
		String sql = StringUtils.joinStr("select count(*)num from cjb where xh=? and xn=? and cj",
										 tjMap.get("tjlx"),
										 tjMap.get("tjz"),
										 whereSql);
		String num = getOneRs(sql, new String[]{argMap.get("xh"), argMap.get("xn")}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		fhtjms = Integer.parseInt(num);
		//查询总数
		sql = "select count(*) num from cjb where xh=? and xn=?" + whereSql;
		num = getOneRs(sql, new String[]{argMap.get("xh"), argMap.get("xn")}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		zms = Integer.parseInt(num);
		return zms == fhtjms;// 总门数和符合条件的门数是否一致
	}
	
	/**
	 * 查询学生奖学金信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsjxjb(JxjpdxxModel model){
		String sql = "select xh,xn,jxjmc,jfqk,fdyyj,xyshyj xyyj,xxshyj xxyj from view_xsjxjb a where xh=? and xn=?";
		String[] output = {"xh","xn","jxjmc","jfqk","fdyyj","xyyj","xxyj"};
		return getMap(sql, new String[]{model.getXh(), model.getXn()}, output);
	}
	
	/**
	 * 查询学生荣誉称号信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsrychbByPk(String pkVlaue){
		String sql = "select xh,xn,rychdm,xydm,zydm,bjdm,nj from view_xsrychb a where xh||xn||rychdm||xq=?";
		String[] output = {"xh","xn","rychdm","xydm","zydm","bjdm","nj"};
		return getMap(sql, new String[]{pkVlaue}, output);
	}
	
	
	
	/**
	 * 查询学生奖学金获得总数信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public int selectXsjxjbCount(JxjpdxxModel model){
		String sql = "select count(*) num from view_xsjxjb a where xh=? and xn=? and xxsh='通过'";
		String num = getOneRs(sql, new String[]{model.getXh(), model.getXn()}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num);
	}
	
	/**
	 * 查询学生荣誉称号信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsrychb(JxjpdxxModel model){
		String sql = "select xh,xn,rychmc,zysj,fdyyj,xyyj,xxyj from view_xsrychb a where xh=? and xn=?";
		String[] output = {"xh","xn","rychmc","zysj","fdyyj","xyyj","xxyj"};
		return getMap(sql, new String[]{model.getXh(), model.getXn()}, output);
	}
	
	
	/**
	 * 查询学生成绩信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> selectXscjb(JxjpdxxModel model,String kclx){
		String sqlP = "";
		String cjP = "sum(cj)/2 cj";
		String groupP = "group by xh,xn,kcmc";
		if(StringUtils.isNotNull(kclx)){
			sqlP += " and kclx='" + kclx+"'";
		}
		if(StringUtils.isNotNull(model.getXq())){
			sqlP += " and xq='" + model.getXq()+"'";
			cjP = " cj";
			groupP = "";
		}
		if(StringUtils.isNotNull(model.getXn())){
			sqlP += " and xn='" + model.getXn()+"'";
		}
		
		String sql = StringUtils.joinStr("select xh,xn,kcmc,",
										 cjP,
										 " from view_cjb a where xh=? ",
										 sqlP,
										 groupP);
		String[] output = {"xh","xn","kcmc","cj"};
		return getList(sql, new String[]{model.getXh()}, output);
	}
	
	/**
	 * 获取奖学金人数分配方式
	 * @param model
	 * @return String
	 * */
	public String getJxjrsfpfs(JxjpdxxModel model){
		String sql = "select distinct bmlb fpfs from xyjxjrs where xn=? and jxjdm=? and jxjrs is not null";
		return getOneRs(sql, new String[]{model.getXn(), model.getJxjdm()}, "fpfs");
	}
	
	/**
	 * 获取奖学金分配人数
	 * @param model
	 * @param fpfs
	 * @return int
	 * */
	public int getJxjFprs(JxjpdxxModel model,String fpfs){	
		String fpbm = "zydm".equalsIgnoreCase(fpfs) ? model.getZydm() : model.getBjdm();
		fpbm = "xydm".equalsIgnoreCase(fpfs) ? model.getXydm() : fpbm;
		
		String[] input = new String[]{model.getXn(), model.getJxjdm(), fpbm};
		String sql = "select jxjrs from xyjxjrs where xn=? and jxjdm=? and bmdm=?";
		if("xydm".equalsIgnoreCase(fpfs) || "zydm".equalsIgnoreCase(fpfs)){
			sql += " and nj=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getNj()});	
		}
		
		String num = getOneRs(sql, input, "jxjrs");
		
		num = StringUtils.isNull(num) ? "-1" : num;
		return Integer.parseInt(num);
	}
	
	/**
	 * 获取奖学金审核通过人数
	 * @param model
	 * @param fpfs
	 * @return int
	 * */
	public int getJxjShtgrs(JxjpdxxModel model,String fpfs){	
		String[] input = new String[]{model.getXh(), model.getXn(), model.getJxjdm(), model.getNj()};
		String sql = "select count(*) num from view_xsjxjb a where not exists(select 1 from view_xsjxjb b where a.xh=b.xh and a.jxjdm=b.jxjdm and a.xn=b.xn and b.xh=?) and xysh='通过' and xn=? and jxjdm=? and nj=?";
		if("xydm".equalsIgnoreCase(fpfs)){
			sql += " and xydm=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getXydm()});
		} else if ("zydm".equalsIgnoreCase(fpfs)){
			sql += " and zydm=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getZydm()});
		} else {
			sql += " and bjdm=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getBjdm()});
		}
		
		String num = getOneRs(sql, input, "num");		
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num);
	}
	
	/**
	 * 获取荣誉称号审核通过人数
	 * @param model
	 * @param fpfs
	 * @return int
	 * */
	public int getRychShtgrs(JxjpdxxModel model,String fpfs){	
		String[] input = new String[]{model.getXh(), model.getXn(), model.getJxjdm(), model.getNj()};
		String sql = "select count(*) num from view_xsrychb a where not exists(select 1 from view_xsrychb b where a.xh=b.xh and a.rychdm=b.rychdm and a.xn=b.xn and b.xh=?) and xysh='通过' and xn=? and rychdm=? and nj=?";
		if("xydm".equalsIgnoreCase(fpfs)){
			sql += " and xydm=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getXydm()});
		} else if ("zydm".equalsIgnoreCase(fpfs)){
			sql += " and zydm=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getZydm()});
		} else {
			sql += " and bjdm=?";
			input = StringUtils.joinStrArr(input, new String[]{model.getBjdm()});
		}
		
		String num = getOneRs(sql, input, "num");		
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num);
	}

	/**
	 * 成绩详细信息查询
	 * @param viewName
	 * @param outputColumn
	 * @param pkValue
	 * */
	public List<String[]> selectXscjbOne(String viewName, 
			String[] outputColumn,
			String pkValue){
		String sql = "select * from " + viewName + " where xh||xn||xq=?";
		
		return rsToVator(sql, new String[]{pkValue}, outputColumn);
	}
	
}

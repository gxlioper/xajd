package xgxt.studentInfo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.pjpy.zgkd.PjpyZgkdZhszcpDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class XsxxglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * 获取查询条件
	 * @return StringBuffer
	 * */
	public StringBuffer getWhereSql(CommanForm model){		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		
		if(!StringUtils.isNull(xn)){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		return sb;		
	}
	
	/**
	 * 检测记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + " =?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num) >0 ? true : false;
	}
	
	/**
	 * 获取导出列的列表
	 * @param sql
	 * @param tableName
	 * @return List
	 * */
	public List<HashMap<String, String>> getColumn2ExpList(String sql,String tableName){
		GetDropDownList dao = new GetDropDownList();
		return dao.getTableColForExp(tableName);		
	}
	
	/**
	 * 查询学生详细信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuDetail(String xh){
		String xxdm = StandardOperation.getXxdm();//学校代码
		HashMap<String, String> map = new HashMap<String, String>();
		//String sql = "select a.*,(select qsh from view_xszsxx b where a.xh=b.xh)qsh from view_xsxxb a where xh=?";
		// ==============2010.9.29 edit by luojw===========
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select a.*,b.ldmc,b.qsh,b.xqmc,to_char(sysdate,'yyyy')-substr(a.csrq,1,4) nl from view_xsxxb a ");
		sql.append("left join view_xszsxx b on a.xh = b.xh ");
		sql.append(") t where t.xh=? ");
		// ==============end================================
		
		String[] outputValue = {"xh","xm","xb","nj","xydm","xymc","zydm","zymc",
				                "bjdm","bjmc","lxdh","sjhm","dzyx","sfzh","csrq",
				                "xz","zzmmmc","mzmc","syd","jg","ssbh","qsdh",
				                "xjztm", "rxrq","sg","tc","cym","yhkh","hkszd",
				                "ykth", "kh", "qsh","hkxz","rxqdw","bysj","ldmc",
				                "xqmc","yhmc", "yhkh","jtdz","jtyb","jtdh","sfzx","byny",
				                "xmpy","pycc","sydsmc","xlmc","qqhm","sfzd","jgmc","nl","sydqmc"};
		map = getMap(sql.toString(), new String[]{xh}, outputValue);
		HashMap<String, String> configer = selectXsxxglXsxx();//参数设置信息
		//-------2011.7.11 qph 代码转名称的方法改过 ------------------
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm")) && !Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//学生地质信息取代码且非地质大学
			//map.put("syd",new CommDAO().getSydmc(map.get("syd"), "/", "/"));
			map.put("syd",dzxxdmToMc(map.get("syd")));
			map.put("jg", dzxxdmToMc(map.get("jg")));
			map.put("hkszd", dzxxdmToMc(map.get("hkszd")));
		}
		
		return map;
	}
	
	
	/**
	 * 查询学生公寓详细信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuDetailGy(String xh){
		String xxdm = StandardOperation.getXxdm();//学校代码
		HashMap<String, String> map = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select a.*,b.lddm,b.qsh,b.ldmc,b.cwh,b.qsdh ssdh from view_xsxxb a ");
		sql.append("left join xg_view_gygl_new_xszsgl b on a.xh = b.xh ");
		sql.append(") t where t.xh=? ");
		// ==============end================================
		
		String[] outputValue = {"xh","xm","xb","nj","xydm","xymc","zydm","zymc",
				                "bjdm","bjmc","lxdh","sjhm","dzyx","sfzh","csrq",
				                "xz","zzmmmc","mzmc","syd","jg","ssbh","qsdh",
				                "xjztm", "rxrq","sg","tc","cym","yhkh","hkszd",
				                "ykth", "kh", "qsh","hkxz","rxqdw","bysj","ldmc",
				                "yhmc", "yhkh","jtdz","jtyb","jtdh","sfzx","byny",
				                "xmpy","pycc","sydsmc","xlmc","qqhm","sfzd","ssdh","cwh"};
		map = getMap(sql.toString(), new String[]{xh}, outputValue);
		HashMap<String, String> configer = selectXsxxglXsxx();//参数设置信息
		//-------2011.7.11 qph 代码转名称的方法改过 ------------------
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm")) && !Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//学生地质信息取代码且非地质大学
			map.put("syd",new CommDAO().getSydmc(map.get("syd"), "/", "/"));
			map.put("jg", dzxxdmToMc(map.get("jg")));
			map.put("hkszd", dzxxdmToMc(map.get("hkszd")));
		}
		
		return map;
	}
	
	/**
	 * 根据地区代码获取名称
	 * @param dzdm
	 * @return String 
	 * */
	public String dzxxdmToMc(String dzdm){
		String dzmc = "";
		String sql = "";
		if(StringUtils.isNotNull(dzdm)){
			String[] dmArr = dzdm.split("/");
			for(String dm : dmArr){
				if(StringUtils.isNotNull(dm)){
					sql = "select qxmc mc from dmk_qx where qxdm=?";
					dzmc += getOneRs(sql, new String[]{dm}, "mc");
				}
			}
		}
		
		return dzmc;
	}
	
	/**
	 * 由代码查询名称
	 * */
	public String dmToMc(String table, String dmColumnName,String mcColumnName,String value){
		String sql = "select " + mcColumnName + " from " + table + " where " + dmColumnName + " = ?";
		return getOneRs(sql, new String[]{value}, mcColumnName);
	}
	
	/**
	 * 中国矿业大学获取综合素质测评信息
	 * @param xh
	 * @param colList
	 * @return List
	 * */
	public List<String[]> getZhszcpxx(String xh,String[] colList) throws Exception{
			PjpyZgkdZhszcpDAO pzDao = PjpyZgkdZhszcpDAO.getInstance();
			String sql = pzDao.queryZhszfPmfs();//获取查询语句
			return rsToVator(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 中国矿业大学获取党员信息
	 * @param xh
	 * @return List
	 * */
	public List<String[]> getDyxx(String xh){
		StuInfoDAO dao = new StuInfoDAO();
		
		String sql = "";
		String[] colList = {"xh","xm","rdsqsj","qdwjjfzsj","fzdxsj","rdsj","zzsj","xzzt"};
		
		if(dao.checkExists("view_zgkd_dyxx", "xh", xh)){
			sql = "select * from view_zgkd_dyxx where xh=?";
		}else{
			sql = "select DJSQSJ from view_zgkd_rdsq where xh=? order by djsqsj desc";
			String sqsj = getOneRs(sql, new String[]{xh}, "DJSQSJ");
			sql = "select xh,xm,DJSQSJ rdsqsj, '' qdwjjfzsj,'' fzdxsj,'' rdsj,'' zzsj, '' xzzt from view_zgkd_rdsq where DJSQSJ='" + sqsj+ "' and xh=?";
		}
		
		return rsToVator(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 查询班级的父级信息
	 * */
	public HashMap<String, String> queryBjParentInfo(String bjdm){
		String sql = "select nj,xydm,zydm from view_njxyzybj where bjdm=?";
		return getMap(sql, new String[]{bjdm}, new String[]{"nj", "xydm", "zydm"});
	}
	
	/**
	 * 获取学制列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXzList(){
		String sql = "select distinct xz from view_xsjbxx order by xz asc";
		return getList(sql, new String[]{}, new String[]{"xz"});
	}
	
	/**
	 * 查询学生成绩报告单信息
	 * @param CommanForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXscjbgdxx(CommanForm model) throws Exception{
		String sql = "select * from (select rownum r,xn||xq||xh 主键,a.* from view_hzzy_cjbgdxxb a " + getWhereSql(model).toString() + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize()) ;
		String[] outputValue = new String[]{"主键","xn","xqmc","xh","xm","xb","nj","bjmc","yskts","sskts","bingjia","shijia","chidao"};
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return CommonQueryDAO.commonQuery("view_hzzy_cjbgdxxb", getWhereSql(model).toString(), input , outputValue, sql, model);
	}
	
	/**
	 * 查询单条学生成绩报告单信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXscjbgdxxOne(String pkValue){
		String sql = "select * from view_hzzy_cjbgdxxb where xn||xq||xh=?";
		String[] outputValue = new String[]{"xn","xq","xqmc","xh","xm","xb","xz","nj","xymc","xydm","zymc","zydm","bjmc","bjdm",
				"yskts","sskts","bingjia","shijia","kuangke","chidao","qita","zhszpd","bzr","bzrlxdh"};
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 查询学生成绩报告单显示的时间信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectCjbgddysjxx(){
		String[] outputValue = new String[]{"ffY","ffM","ffD","kxY","kxM","kxD","bdY","bdM","bdD","bkksY","bkksM","bkksD","bkjsY","bkjsM","bkjsD","xzf"};
		String sql = "select substr(cjbgdffsj,0,4) ffY ,substr(cjbgdffsj,6,2) ffM,substr(cjbgdffsj,9,2) ffD," +
				"substr(kxsj,0,4) kxY ,substr(kxsj,6,2) kxM,substr(kxsj,9,2) kxD," +
				"substr(bdsj,0,4) bdY ,substr(bdsj,6,2) bdM,substr(bdsj,9,2) bdD," +
				"substr(bkkssj,0,4) bkksY ,substr(bkkssj,6,2) bkksM,substr(bkkssj,9,2) bkksD," +
				"substr(bkjssj,0,4) bkjsY ,substr(bkjssj,6,2) bkjsM,substr(bkjssj,9,2) bkjsD,xzf from xsxxxgsjb";
		
		return getMap(sql, new String[]{}, outputValue);
	}
	
	/**
	 * 查询银行信息
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> selectYhxx(){
		String sql = "select yhdm, yhmc from dmk_yh order by yhdm";
		String[] colList = {"yhdm", "yhmc"};
		return getList(sql, new String[]{}, colList);
	}
	
	/**
	 * 判断当前时间是否在设定的修改学生信息时间范围内
	 * @return boolean
	 * */
	public boolean isXsxxxgsj(){
		String sql = "select count(*) num from xsxxxgsjb where kssj<to_char(sysdate,'yyyy-mm-ddhh24miss') and jssj>to_char(sysdate,'yyyy-mm-ddhh24miss')";
		String result = getOneRs(sql, new String[]{}, "num");
		return Integer.parseInt(StringUtils.isNull(result) ? "0" : result) >0 ? true : false;		
	}
	
	/**
	 * 查询学生信息模块参数设置信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsxxglXsxx(){
		String sql = "select * from xsxxxgsjb";
		String[] colList = {"bdsj","bkjssj","bkkssj","bzrjssj","bzrkssj","cjbgdffsj","havexsqx","issz","jssj","kssj",
							"kxsj","nd","xn","xq","xzf","xsxgxxsh","dzxxqdm","xsxgxxsfsh"};//
		return getMap(sql, new String[]{}, colList);
	}
	
	/**
	 * 查询除学号外的修改学生信息字段
	 * */
	public String selectXgzdNoXh(String yhjs){
		String result = "";
		String sql = "select distinct zdm from xsxx_qxfpb where yhjs=? and zdm<>'xh'";
		String[] zdList = null;
		try {
			zdList = getArray(sql, new String[]{yhjs}, "zdm");
		    for(int i=0; i<zdList.length; i++){
		    	result += zdList[i] + "!!";
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询学生基本信息和家庭信息
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuAndFamily(String xh,String yhjs,String xxdm){
		HashMap<String, String> rs = null;
		String[] outputValue = null;
		String sql = "";
		//查询学生信息和家庭信息
		try {
			outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsfzxxb'", new String[]{xxdm}, "zdmc");
			if(outputValue == null || outputValue.length<1){//非特殊学校
				outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsfzxxb'", new String[]{"public_xsfzxxb"}, "zdmc");
			}
			sql = "select * from xsfzxxb where xh=?";
			rs = getMap(sql, new String[]{xh}, outputValue);
			
			outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsxxb'", new String[]{xxdm}, "zdmc");
			if(outputValue == null || outputValue.length<1){//非特殊学校
				outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsxxb'", new String[]{"public_xsxxb"}, "zdmc");
			}
			sql = "select * from view_xsxxb where xh=?";
			rs.putAll(getMap(sql, new String[]{xh}, outputValue));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * 查询学费设置信息
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXfszxx(CommanForm model){
		String sql = "select a.*,rownum r from view_xsxxgl_xfszb a ";
		String[] outputValue = {"bjdm","nj","xymc","zymc","bjmc","xfje"};
		String whereSql = getWhereSql(model).toString();
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from ("+sql + whereSql + ")", value != null ? value.toArray(new String[0]) : new String[]{} , "num")));
		
		sql = "select * from (" + sql + whereSql +") a where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize());		
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 设置学费信息
	 * */
	public boolean updateXfszxx(CommanForm model) throws Exception{
		String whereSql = getWhereSql(model).toString();
		boolean result = false;
		String sql = "insert into xsxxgl_xfszb(bjdm,xfje)(select bjdm," + model.getXfje() + " from view_xsxxgl_xfszb " + whereSql + ")";
		result = runUpdate("delete from xsxxgl_xfszb a where exists(select 1 from view_xsxxgl_xfszb b " + whereSql + " and a.bjdm=b.bjdm)", value != null ? value.toArray(new String[0]) : new String[]{});
		if(result){
			result = runUpdate(sql, value != null ? value.toArray(new String[0]) : new String[]{});
		}		
		return result;
	}
	
	/**
	 * 查询成绩表信息
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<List<HashMap<String, String>>> selectCjb(CommanForm model){
		String sql = "";
		String[] colList = {"xn","xq","xh","kcmc","xf","cj","bjmc","xm"};
		String[] pkV = model.getPkV();
		List<List<HashMap<String, String>>> rs = new ArrayList<List<HashMap<String,String>>>();
		if(pkV != null && pkV.length>0){
			for(int i=0; i<pkV.length;i++){
				if(StringUtils.isNotNull(pkV[i])){
					sql = "select a.*,(select bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,(select xm from view_xsjbxx b where a.xh=b.xh)xm from cjb a";
					model.setXh(pkV[i]);
					sql += getWhereSql(model);
					List<HashMap<String, String>> list = getList(sql, value != null ? value.toArray(new String[0]) : new String[]{}, colList);

					if(list != null && list.size()>0){
						rs.add(list);
					}
					value = new ArrayList<String>();
				}				
			}
		}
		return rs;
	}
	
	/**
	 * 获得发布信息
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public String getInfo_db(String title){
		String sql = "select nr from xsxx_xxfbb where title=?";
		return getOneRs(sql, new String[]{title},"nr");
	}
	
	/**
	 * 查询学生开学应缴学费
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String,String> getXsxf(String xh){
		String sql = "select xm,bjmc,(select xfje from view_xsxxgl_xfszb b where a.bjdm=b.bjdm)xfje from view_xsjbxx a  where xh=?";
		return getMap(sql, new String[]{xh}, new String[]{"xm","bjmc","xfje"});
	}
	
	/**
	 * 根据学期代码查询学期名称
	 * @param String sq
	 * @return String
	 * */
	public String selectXqmc(String xq){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return getOneRs(sql, new String[]{xq}, "xqmc");
	}
	
	/**
	 * 保存学生信息模块参数设置信息
	 * @param StudentInfoForm model
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveStuConfig(StudentInfoForm model) throws Exception{
		boolean flag = false;
		flag = runUpdate("delete from xsxxxgsjb", new String[]{});//删除数据
		if(flag){//插入数据
			String sql = "insert into xsxxxgsjb(nd,xn,xq,kssj,jssj,bzrkssj," +
					     "bzrjssj,issz,cjbgdffsj,kxsj,bdsj,xzf,bkkssj,bkjssj," +
					     "havexsqx,xsxgxxsh,xsxgxxsfsh) " +
					     "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String kssj = StringUtils.joinStr(model.getKssj(), model.getKssjH(),
					                          model.getKssjM(), model.getKssjS());
			String jssj = StringUtils.joinStr(model.getJssj(), model.getJssjH(),
					                          model.getJssjM(), model.getJssjS());
			String bzrkssj = StringUtils.joinStr(model.getBzrkssj(), model.getBzrkssjH(),
					                             model.getBzrkssjM(), model.getBzrkssjS());
			String bzrjssj = StringUtils.joinStr(model.getBzrjssj(), model.getBzrjssjH(),
					                             model.getBzrjssjM(), model.getBzrjssjS());
			String[] input = {model.getNd(), model.getXn(), model.getXq(),
					         kssj, jssj, bzrkssj, bzrjssj, model.getIsSz(),
					         model.getCjbgdffsj(), model.getKxsj(), model.getBdsj(),
					         model.getXzf(), model.getBkkssj(), model.getBkjssj(),
					         model.getHavexsqx(), model.getXsxgxxsh(), model.getXsxgxxsfsh()};
			flag = insert(sql, input);
		}
		return flag;
	
	}
	
	/**
	 * 保存学生学习经历信息
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean saveXsxxjlb(String xh, HashMap<String, String[]> valueMap){
		boolean result = false;
		int length = valueMap != null && valueMap.get("kssj") != null ? valueMap.get("kssj").length : 0;
		String[] sqlArr = new String[length+1];
		//将数据删除
		sqlArr[0] = "delete from xsxxjlb where xh='" + xh + "'";	
		//将数据插入到数据库
		if(length > 0){
			for(int i=0; i<length; i++){
				String kssj = valueMap.get("kssj") != null && StringUtils.isNotNull(valueMap.get("kssj")[i]) ? valueMap.get("kssj")[i] : "";
				String jssj = valueMap.get("jssj") != null && StringUtils.isNotNull(valueMap.get("jssj")[i]) ? valueMap.get("jssj")[i] : "";
				String szdw = valueMap.get("szdw") != null && StringUtils.isNotNull(valueMap.get("szdw")[i]) ? valueMap.get("szdw")[i] : "";
				String drzw = valueMap.get("drzw") != null && StringUtils.isNotNull(valueMap.get("drzw")[i]) ? valueMap.get("drzw")[i] : "";
				String zmr = valueMap.get("zmr") != null && StringUtils.isNotNull(valueMap.get("zmr")[i]) ? valueMap.get("zmr")[i] : "";
				String szdq = valueMap.get("szdq") != null && StringUtils.isNotNull(valueMap.get("szdq")[i]) ? valueMap.get("szdq")[i] : "";
				String szbm = valueMap.get("szbm") != null && StringUtils.isNotNull(valueMap.get("szbm")[i]) ? valueMap.get("szbm")[i] : "";
				String zmrdw = valueMap.get("zmrdw") != null && StringUtils.isNotNull(valueMap.get("zmrdw")[i]) ? valueMap.get("zmrdw")[i] : "";
				String zmrzw = valueMap.get("zmrzw") != null && StringUtils.isNotNull(valueMap.get("zmrzw")[i]) ? valueMap.get("zmrzw")[i] : "";
				
				if(StringUtils.isNotNull(kssj)){
					sqlArr[i+1] = StringUtils.joinStr("insert into xsxxjlb(xh,kssj,jssj,szdw,drzw,zmr,szdq,szbm,zmrdw,zmrzw) ",
							      "values('",DealString.replaceImmitStr(xh), "','" , DealString.replaceImmitStr(kssj), "','", DealString.replaceImmitStr(jssj) ,"','", DealString.replaceImmitStr(szdw), "','", DealString.replaceImmitStr(drzw), 
							      "','", DealString.replaceImmitStr(zmr),"','", DealString.replaceImmitStr(szdq),"','", DealString.replaceImmitStr(szbm),"','", DealString.replaceImmitStr(zmrdw), "','", DealString.replaceImmitStr(zmrzw),"')");
				}
			}
			try{
				result = checkBatch(runBatch(sqlArr));
			}catch(SQLException e){
				result = false;
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 保存学生社会关系信息
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean saveXsshgx(String xh, HashMap<String, String[]> valueMap){
		boolean result = false;
		int length = valueMap != null && valueMap.get("cyxm") != null ? valueMap.get("cyxm").length : 0;
		String[] sqlArr = new String[length+1];
		//将数据删除
		sqlArr[0] = "delete from xsxx_xsshgxb where xh='" + xh + "'";	
		//将数据插入到数据库
		if(length > 0){
			for(int i=0; i<length; i++){
				String cyxm = valueMap.get("cyxm")[i];
				String cynl = valueMap.get("cynl")[i];
				String cyzzmmdm = valueMap.get("cyzzmmdm")[i];
				String cygzdw = valueMap.get("cygzdw")[i];
				String cydrzw = valueMap.get("cydrzw")[i];
				String cylxdh = valueMap.get("cylxdh")[i];
				
				if(StringUtils.isNotNull(cyxm)){
					sqlArr[i+1] = StringUtils.joinStr("insert into xsxx_xsshgxb(xh,cyxm,cynl,cyzzmmdm,cygzdw,cydrzw,cylxdh) ",
							      "values('",DealString.replaceImmitStr(xh), "','" , DealString.replaceImmitStr(cyxm), "','",
							      DealString.replaceImmitStr(cynl) ,"','", DealString.replaceImmitStr(cyzzmmdm), "','", 
							      DealString.replaceImmitStr(cygzdw), "','", DealString.replaceImmitStr(cydrzw),"','", 
							      DealString.replaceImmitStr(cylxdh),"')");
				}
			}
			try{
				result = checkBatch(runBatch(sqlArr));
			}catch(SQLException e){
				result = false;
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 获取学生身体信息
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * author qlj
	 */
	public HashMap<String, String> getXsstList(String xh){
		
		DAO dao=DAO.getInstance();
		
		String sql ="select * from xg_xsxx_xsstxxb where xh=? ";
		//鞋子尺码、胸围
		String[]outPut={"xzcm","xsxw"};
		
		return dao.getMap(sql, new String[]{xh}, outPut);
		
	}
    /**
     * 获取学生姓名
     * 
     */
	public String getStuInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		String xm = dao.getOneRs("select xm from view_xsjbxx where xh=?",new String[] { xh }, "xm");
		
		return xm!=null?xm:"";
		
	}
	
	/**
	 * 获取系统当前时间
	 */
	public String nowTime(){
		
		DAO dao=DAO.getInstance();
		
		String nowTime = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') str from dual",new String[] {}, "str");
		
		return nowTime!=null?nowTime:"";
	}
	
	/**
	 * 获取学生住宿基本信息
	 * @param xh
	 * @return
	 * @author gaobb 2011-11-22 新版公寓
	 */
	public HashMap<String,String> getXszsjbxx(String xh){
		String sql="select * from view_xg_gygl_new_cwxx where xh=?";
		return getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获取苏州工业园区综合测评列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getSzgyyqZhcpList(String xh){
		String sql="select a.*,(select xqmc from xqdzb x where x.xqdm=a.xq) xqmc from szgy_zhszcphzlsb a where xh=? order by xn,xq";
		return getListNotOut(sql, new String[]{xh});
	}
}

package xgxt.studentInfo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.base.MD5;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.pjpy.zgkd.PjpyZgkdZhszcpDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.xscj.XscjActionForm;
import xgxt.utils.String.StringUtils;

import common.Globals;

public class StuInfoDAO {	
	/**
	 * 获取学生详细信息中需要查询某项目的所有信息
	 * @param String xh
	 * @param String tableName
	 * @param String[] colList1
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public  List getAllOfInfo(String xh,String tableName,String[] colList1){
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		List<HashMap<String, String>> rs1= new ArrayList<HashMap<String,String>>();
		String sql1 = "";
		String tableName1 = tableName;
		sql1 = "select * from " + tableName1 + " where xh=?";
		if(tableName!=null && (tableName.equalsIgnoreCase("view_wjcf") 
				|| tableName.equalsIgnoreCase("view_xsjxjb")
				|| tableName.equalsIgnoreCase("view_xsrychb"))){
			sql1 = "select * from " + tableName1 + " where xh=? and xxsh='通过'";
			if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm) && "view_wjcf".equalsIgnoreCase(tableName)) {
				sql1 = "select xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,nd,cfsj,cfwh,cflbmc,cfyymc,xm,ssjg,cxsj from " + tableName1 + " a where xh=? and xxsh='通过'";
			}
		}else if(tableName != null && "xg_view_pjpy_xmsh".equalsIgnoreCase(tableName)){//2012.3.19日更改评奖评优
			sql1 = "select a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje,a.sqsj from " + tableName + " a where a.shjb=(select max(b.shjb) from xg_view_pjpy_xmsh b where a.xh=b.xh) and a.shzt='通过' and a.xh=? ";
		}else if(tableName != null && "view_jy_jyxy".equalsIgnoreCase(tableName)){
			sql1 = "select * from " + tableName + " where xh=? and xxshyj='通过'";
		}else if(tableName != null && "view_jygl_jyxyblsq".equalsIgnoreCase(tableName)){
			sql1 = "select * from " + tableName + " where xh=? and xxshyj='通过'";
		}else if(tableName != null && "view_jy_bysxxb".equalsIgnoreCase(tableName)){
			sql1 = "select * from " + tableName + " where xh=? and shzt='通过'";
		}else if(tableName != null && "xg_pjpy_zhcpb".equalsIgnoreCase(tableName)){//综合，获取该学生有多少个学年
			sql1 = "select xn from xg_pjpy_zhcpb where xh=? group by xn";
		}else if(tableName != null && "xg_pjpy_zcxmb".equalsIgnoreCase(tableName)){//综合，获取该学年有多少个综合项目
			sql1 = "select xmdm,xmmc from xg_pjpy_zcxmb where xn=? order by xn";
		}else if(tableName != null && "xg_pjpy_zhcpb2".equalsIgnoreCase(tableName)){
			StudentInfoForm model = new StudentInfoForm();
			StuInfoDAO dao2 = new StuInfoDAO();
			String zd ="";
			String[] xszdArr = null;
			//System.out.println(xh);
			xszdArr = xh.split(",");
			String xsxh =xszdArr[1].toString();
			List<HashMap<String, String>> kind = dao2.getKind(model,xsxh);//获取zd字段
			if(kind!=null && kind.size()>0){
				for (int i = 0; i < kind.size(); i++) {
					HashMap<String, String> kindMap = kind.get(i);
					zd+=" nvl("+kindMap.get("xmdm").toString()+",0)"+kindMap.get("xmdm").toString()+",";
					//System.out.println(kindMap.get("xmdm").toString());
				}
				sql1 = "select xn,nvl((select xqmc from xqdzb b where a.xq=b.xqdm),'无')xq ,"+zd+"zcfbjpm from xg_pjpy_zhcpb a where xh=? and xn=?";
			}else{
				sql1 = "select xn,nvl((select xqmc from xqdzb b where a.xq=b.xqdm),'无')xq ,zd1,zd2,zd3,zd4,zd5,zcfbjpm from xg_pjpy_zhcpb where xh=? and xn=?";
			}
		}
		if(tableName.equalsIgnoreCase("view_xsgwxx")){//学生岗位信息
			sql1 = "select * from " + tableName1 + " where xh=? and xxyj='通过'";
		}
		if(tableName!=null && (tableName.equalsIgnoreCase("view_cjb"))){
			//学生成绩
			sql1 = "select a.xh,a.xm,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xq,kcmc,kcxz,cj,xf,bkcj,jd,cxcj from " + tableName + " a where xh=? order by a.xn,a.xq,kcxz";
		}
		if(xxdm.equalsIgnoreCase("10344") &&tableName!=null && (tableName.equalsIgnoreCase("XG_XLJK_ZJZYY_TSXSXXB"))){
			//浙江中医药大学心理健康
			sql1 = "select a.*,(select xqmc from xqdzb where xqdm = a.xq) xqmc from " + tableName + " a where xh=? order by a.xn,a.xq,a.xh";
		}
		if(tableName!=null && tableName.equalsIgnoreCase("view_xsxx_xjyd")){
			//新学籍异动
			sql1 = "select * from view_xsxx_xjyd where xh = ? order by xn,xq desc ";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX) || xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			if(tableName != null && "view_xscjff".equalsIgnoreCase(tableName)){
				sql1 = "select * from " + tableName + " where xh=? and xxsh='通过'" ; 
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			if (tableName != null
					&& ("view_xszz_com_gjzxj2".equalsIgnoreCase(tableName) || "view_xszz_com_xfjm1"
							.equalsIgnoreCase(tableName))) {
				sql1 = "select * from " + tableName
						+ " where xh=? and xxsh='通过'";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)){
			if(tableName != null && "view_ybdyxx".equalsIgnoreCase(tableName)){
				String sql = "select ybdykssj,ybdyjssj from dyxxb where xh=?";
				List<HashMap<String,String>> list = dao.getList(sql, new String[]{xh}, new String[]{"ybdykssj","ybdyjssj"});
				if (list != null && list.size() > 0) {
					HashMap<String, String> map = list.get(0);
					if(!Base.isNull(map.get("ybdykssj"))&&!Base.isNull(map.get("ybdyjssj"))){
						sql1="select nd, xn, xq, xh, xm, nj, bjmc, ybdykssj kssj,ybdyjssj jssj from view_dyxx where xh=?";
					}
				}
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY) && "xcxyxszz".equals(tableName)){
			StringBuffer sb = new StringBuffer();
			sb.append("select * from (select a.xn,a.xh,b.xm,b.xymc,b.zymc,b.bjmc,(select xmmc from xszz_xmdmb where xmdm=a.xmdm) xmmc,");
			sb.append("a.zzje from xszz_jxjsqb a,view_xsjbxx b where a.xh=b.xh and a.xxsh='通过' union select a.xn,a.xh,b.xm,b.xymc,b.zymc,");
			sb.append("b.bjmc,(select xmmc from xszz_xmdmb where xmdm=a.xmdm) xmmc,a.zzje from xszz_zxjsqb a,view_xsjbxx b ");
			sb.append("where a.xh=b.xh and a.xxsh='通过' union select a.xn,a.xh,b.xm,b.xymc,b.zymc,b.bjmc,(select xmmc from xszz_xmdmb where ");
			sb.append("xmdm=a.xmdm) xmmc,a.zzje from xszz_lzjxjsqb a,view_xsjbxx b where a.xh=b.xh and a.xxsh='通过') where xh=? order by xn,xmmc");
			sb.append("");
			sb.append("");
			sql1 = sb.toString();
		}
		try{		
			if(tableName != null && "xg_pjpy_zhcpb2".equalsIgnoreCase(tableName)){
				StudentInfoForm model = new StudentInfoForm();
				StuInfoDAO dao2 = new StuInfoDAO();
				String[] xszdArr = null;
				xszdArr = xh.split(",");
				String xsxn =xszdArr[1].toString();
				List<HashMap<String, String>> kind = dao2.getKind(model,xsxn);//获取zd字段
				if(kind!=null && kind.size()>0){
					colList1 = new String[kind.size()+3];
					colList1[0]="xn";
					colList1[1]="xq";
					for (int i = 0; i < kind.size(); i++) {
						HashMap<String, String> kindMap = kind.get(i);
						int a = i+2;
						colList1[a]=kindMap.get("xmdm").toString();
						//System.out.println(kindMap.get("xmdm").toString());
					}
					colList1[kind.size()+2]="zcfbjpm";
				}else{
					colList1 = new String[7];
					colList1[0]="xn";
					colList1[1]="xq";
					colList1[2]="zd1";
					colList1[3]="zd2";
					colList1[4]="zd3";
					colList1[5]="zd4";
					colList1[6]="zd5";
					colList1[7]="zcfbjpm";
					
				}
				rs1.addAll(dao.getList(sql1, new String[]{xszdArr[0],xszdArr[1]}, colList1));
			}else{
				rs1.addAll(dao.getList(sql1, new String[]{xh}, colList1));
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs1;
	}
	
	public  List<String[]> getAllOfInfoByPrint(String xh,String tableName,String[] colList1){
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		List<String[]> rs1= new ArrayList<String[]>();
		String sql1 = "";
		String tableName1 = tableName;
		sql1 = "select * from " + tableName1 + " where xh=?";
		if(tableName!=null && (tableName.equalsIgnoreCase("view_wjcf") 
				|| tableName.equalsIgnoreCase("view_xsjxjb")
				|| tableName.equalsIgnoreCase("view_xsrychb"))){
			sql1 = "select * from " + tableName1 + " where xh=? and xxsh='通过'";
		}
		if(tableName!=null && tableName.equalsIgnoreCase("view_cjb")){
			//学生成绩
			sql1 = "select a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xq,kcmc,kcxz,cj,xf,jd from " + tableName + " a where xh=?";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX) || xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			if(tableName != null && "view_xscjff".equalsIgnoreCase(tableName)){
				sql1 = "select * from " + tableName + " where xh=? and xxsh='通过'" ; 
			}
		}
		try{			
			rs1.addAll(dao.rsToVator(sql1, new String[]{xh}, colList1));			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs1;
	}
	
	/**
	 * 中国矿业大学综合素质测评信息查询
	 * @param xh
	 * @param tableName
	 * @param colList
	 * @return List
	 * @throws Exception 
	 * */
	public List getZhszcpOfZgkydx(String xh,String[] colList) throws Exception{
		DAO dao = DAO.getInstance();
		PjpyZgkdZhszcpDAO pzDao = PjpyZgkdZhszcpDAO.getInstance();
		String sql = pzDao.queryZhszfPmfs();//获取查询语句
		return dao.getList(sql, new String[]{xh}, colList);
	}
	
	public List getDyxx(String xh) throws SQLException{
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = {"xh","xm","rdsqsj","qdwjjfzsj","fzdxsj","rdsj","zzsj","xzzt"};
		
		if(checkExists("view_zgkd_dyxx", "xh", xh)){
			sql = "select * from view_zgkd_dyxx where xh=?";
		}else{
			sql = "select DJSQSJ from view_zgkd_rdsq where xh=? order by djsqsj desc";
			String sqsj = dao.getOneRs(sql, new String[]{xh}, "DJSQSJ");
			sql = "select xh,xm,DJSQSJ rdsqsj, '' qdwjjfzsj,'' fzdxsj,'' rdsj,'' zzsj, '' xzzt from view_zgkd_rdsq where DJSQSJ='" + sqsj+ "' and xh=?";
		}
		
		return dao.getList(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 判断模块下是否存在学生记录
	 * @param tableNames
	 * @param xh
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public boolean checkDisplay(String[] tableNames, String xh){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		List list = new ArrayList();
		String sql = "";
		for(int i = 0; i<tableNames.length; i++){
			sql = "select xh from " + tableNames[i] + " where xh=?";
			if("view_xsjxjb".equalsIgnoreCase(tableNames[i]) || "view_xsrychb".equalsIgnoreCase(tableNames[i])){
				sql = "select xh from " + tableNames[i] + " where xh=? and xxsh='通过'";
			}
			list.addAll(dao.getList(sql, new String[]{xh}, new String[]{"xh"}));
		}
		
		flag = list.size()>0 ? true : false;
		return flag;
	}
	
	/**
	 * 判断学生资助模块下是否存在学生记录
	 * @param tableNames
	 * @param xh
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public boolean checkDisOfXszz(String xh){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String xxdm = dao.getXxdm();
		List list = new ArrayList();
		List tables = getTabCNofXszz(xxdm);
		
		for(int i = 0; i<tables.size(); i++){
			HashMap<String, String > map = (HashMap<String, String >)tables.get(i);
			list.addAll(getXszzInfo(xh, xxdm, map.get("tabEN")));
		}
		
		flag = list.size()>tables.size() ? true : false;
		return flag;
	}
	
	/**
	 * 文明寝室评比
	 * @param String xh
	 * @return List
	 * */
	public List getWmqspbInfo(String xh){
		DAO dao = DAO.getInstance();
		List rs = new ArrayList();
		String ssbh = dao.getOneRs("select ssbh from view_xsjbxx where xh=?",new String[] { xh }, "ssbh");
		String sql = "select xn,xq,pysj,ssbh,lbmc pbdj,bz from view_wmqspbxx where ssbh=?";
		rs = dao.getList(sql, new String[] { ssbh }, new String[] { "xn", "xq", "pysj", "ssbh", "pbdj", "bz" });
		return rs; 
	}
	
	/**
	 * 文明寝室评比
	 * @param String xh
	 * @return List
	 * */
	public List getNbzyZhszcp(String xh,String[] outputValue){
		DAO dao = DAO.getInstance();
		List rs = new ArrayList();		
		String sql = "select * from (select a.* ,(dense_rank() over (partition by bjdm order by to_number(zysyf) desc nulls last)) zypm,(dense_rank() over (partition by bjdm order by to_number(zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp a) where xh=? ";
		
		
		rs = dao.getList(sql, new String[] { xh }, outputValue);
		return rs; 
	}
	
	/**
	 * 商业职业学生不及格成绩
	 * @param String pk
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List getBjgcjInfo(String pk){
		HashMap< String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		List rs = new ArrayList();
		//zpcj1 折算成百分制后的成绩
		String[] colList={"xh","kcmc","zpcj1","cxcj","xf"};
		String[] titList={"学号","课程名称","成绩","重修成绩","学分"};
		String sql = "select xh,kcmc,zpcj1,cxcj,xf from cjb where xn||xq||xh=? and (zpcj1='不及格' or zpcj1<'60')";
		
		for(int i=0;i<colList.length;i++){
			map.put(colList[i], titList[i]);
		}
		rs.add(map);
		rs.addAll(dao.getList(sql, new String[]{pk},colList));
		return rs;		
	}
	
	/**
	 * 学生资助获取要显示的表
	 * @param String xxdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTabCNofXszz(String xxdm){		
		XszzDao xszzDao= new XszzDao();
		DAO dao = DAO.getInstance();
		List<String> tabList = new ArrayList<String>();	
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		tabList.addAll(xszzDao.getStuDataByXxdm(xxdm));	
		String[] tabCN = new String[tabList.size()];
		String[] tabEN = new String[tabList.size()];
		for(int i=0 ;i<tabList.size();i++){
			try {
				tabCN[i] = dao.getCNtableName(tabList.get(i).toString());
				tabEN[i] = tabList.get(i).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<tabList.size();i++){
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("tabCN", tabCN[i]);
			map.put("tabEN", tabEN[i]);
			rs.add(map);
		}
		return rs;
	}
	
	/**
	 * 学生资助获取表中要显示的列
	 * @param String xxdm
	 * @param String table
	 * @return String[]
	 * */
	public String[] getColOfXszz(String xxdm,String table){
		List colList = new ArrayList();
//		List tabList = new ArrayList();
		int len = 0;	
		XszzDao xszzDao= new XszzDao();
		colList=xszzDao.getStuDataByXxdm(xxdm);
//		colList=xszzDao.getStuDataPartByXxdm(table);
		
		len = colList.size();
		String[] col =new String[len];
		//将字段转换成数组
		for(int i=0;i<colList.size();i++){
			col[i] = colList.get(i).toString();
		}
		return col;
	}
	
	/**
	 * 学生详细信息中显示的学生资助信息
	 * @param String xh
	 * @param String xxdm
	 * @param String table
	 * @return List
	 * */
	public List<String[]> getXszzInfo(String xh,String xxdm,String table){
		List<String> colList = new ArrayList<String>();
		DAO dao = DAO.getInstance();
		List<String[]> rs = new ArrayList<String[]>();	
		int colLen=0;
		XszzDao xszzDao= new XszzDao();
		String sql = "";
		
		colList=xszzDao.getStuDataPartByXxdm(table);
		colLen = colList.size();
		String[] col =new String[colLen];
 
		//将字段转换成数组
		for(int i=0;i<colList.size();i++){
			col[i] = colList.get(i).toString();
		}		
		
		//获取中文添加到集合		 
		String[] tit=dao.getColumnNameCN(col, table);
		rs.add(tit);
		if(table != null && (table.toLowerCase().equalsIgnoreCase("view_nbzy_syjj_jkrxxb") || table.toLowerCase().equalsIgnoreCase("view_nbzy_syjj_jkjl"))){
			sql = "select * from "+ table + " where xhyhm=?";
		}else{
			sql = "select * from "+ table + " where xh=?";
		}
		rs.addAll(dao.rsToVator(sql, new String[]{xh}, col));
		return rs;
	}
	
	/**
	 * 获取就业管理的表
	 * @param String xxdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTabCNofJygl(String xxdm){		
		DAO dao = DAO.getInstance();
		String[]en={"view_jy_bysxxb","view_jy_jyxy","view_jygl_jyxyslqdjb","view_jygl_jyxysbb"};
		String[]cn={"毕业生信息","就业协议","就业协议书领取登记","毕业协议申报"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 中国矿业大学获取学生家庭详细信息
	 * */
	public HashMap<String, String> getXsjtxx(String xh){
		DAO dao = DAO.getInstance();
		String[] cols = {"jtdz","jtyb","jtjjqk","lxdh1","jtcy1_xm","jtcy1_gx","jtcy1_csrq","jtcy1_sfzh","jtcy1_mzmc",
				  "jtcy1_zzmmmc","jtcy1_zy","jtcy1_zw","jtcy1_lxdh1","jtcy1_lxdh2","jtcy1_gzdz","jtcy2_xm",
				  "jtcy2_gx","jtcy2_csrq","jtcy2_sfzh","jtcy2_mzmc","jtcy2_zzmmmc","jtcy2_zy","jtcy2_zw",
				  "jtcy2_lxdh1","jtcy2_lxdh2","jtcy2_gzdz","jtcy3_xm","jtcy3_gx","jtcy3_csrq","jtcy3_sfzh",
				  "jtcy3_mzmc","jtcy3_zzmmmc","jtcy3_zy","jtcy3_zw","jtcy3_lxdh1","jtcy3_lxdh2","jtcy3_gzdz"};
		String sql = "select jtszd jtdz,yb jtyb,jjzk jtjjqk,lxdh1,jtcy1_xm,jtcy1_gx,jtcy1_nl jtcy1_csrq,jtcy1_sfzh,(select mzmc from mzdmb b where a.jtcy1_mz=b.mzdm)jtcy1_mzmc," + 
                  "(select zzmmmc from zzmmdmb b where a.jtcy1_zzmm=b.zzmmdm)jtcy1_zzmmmc,jtcy1_zy,jtcy1_zw,jtcy1_lxdh1,jtcy1_lxdh2,jtcy1_gzdz,jtcy2_xm,"+
                  "jtcy2_gx,jtcy2_nl jtcy2_csrq,jtcy2_sfzh,(select mzmc from mzdmb b where a.jtcy2_mz=b.mzdm)jtcy2_mzmc,(select zzmmmc from zzmmdmb b where a.jtcy2_zzmm=b.zzmmdm)jtcy2_zzmmmc,jtcy2_zy,jtcy2_zw,"+
                  "jtcy2_lxdh1,jtcy2_lxdh2,jtcy2_gzdz,jtcy3_xm,jtcy3_gx,jtcy3_nl jtcy3_csrq,jtcy3_sfzh," +
                  "(select mzmc from mzdmb b where a.jtcy3_mz=b.mzdm)jtcy3_mzmc,(select zzmmmc from zzmmdmb b where a.jtcy3_zzmm=b.zzmmdm)jtcy3_zzmmmc,jtcy3_zy,jtcy3_zw,jtcy3_lxdh1,jtcy3_lxdh2,jtcy3_gzdz from xsfzxxb a where xh=?";
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getMap(sql, new String[]{xh}, cols);
		for(int i=0; i<cols.length;i++){
			map.put(cols[i], map.get(cols[i])== null ? "" : map.get(cols[i]));
		}		
		return map;
	}
	
	/**
	 * 获取学生班主任辅导员信息
	 * @param String xh
	 * @param String[] colList
	 * @return List
	 * */
	public List getBzrInfo(String xh,String[] colList){
		List rs = new ArrayList();
		DAO dao = DAO.getInstance();
		String bjdm ="";
		String sql = "";
		bjdm = dao.getOneRs("select bjdm from view_xsjbxx where xh=?", new String[]{xh}, "bjdm");
		sql = "select a.* from view_fdybjdz a where bjdm=?";
		rs= dao.getList(sql, new String[]{bjdm}, colList);
		return rs;
	}
	
	/**
	 * 杭职院收费信息
	 * @param String xh
	 * @param String[] colList
	 * @return List
	 * */
	public List getZsf(String xh,String[] colList){
		List rs = new ArrayList();
		DAO dao = DAO.getInstance();
		String sql = "select xh,rzrq,jzrq,zsf,dsjssf from xszsxxb where xh=?" ;
		rs = dao.getList(sql, new String[]{xh},colList);
		return rs;
	}
  
	/**
	 * 检测输入学号是否存在
	 * @param String xh
	 * @return boolean 
	 * */
	public boolean dctStuXh(String xh){
		DAO dao=DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql="select count(xh) num from view_xsjbxx where xh=? ";
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{xh}, "num"));
		flag=(result>0)?true:false;
		return flag;
	}
	
	/**
	 * 上海工程毕业生转档申请检测是否已经存在申请记录
	 * @param String xh
	 * @return boolean 
	 * */	
	public boolean checkGradArchiveApply(String xh){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from bysdasqb where xh=?";
		int count = Integer.parseInt(dao.getOneRs(sql, new String[]{xh}, "num"));
		if(count>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}

	/**
	 * 西南民族大学素质拓展班级结业信息
	 * @param String xh
	 * @param String[] colList
	 * @return List
	 * */	
	public List getTzBjJy(String xh,String[] colList){
		List rs = new ArrayList();
		DAO dao = DAO.getInstance();
		String sql = "select xn,xqmc,mc,jysj,jyfs from view_sztz_bjjyxx where xh=?" ;
		rs = dao.getList(sql, new String[]{xh},colList);
		return rs;
	}
	
	/**
	 * 判断宿舍编号跟寝室电话是否正确
	 * @param String ssbh
	 * @param String qsdh
	 * @return String
	 * */
	public String checkQsdhAndQsbh(String ssbh,String qsdh){
		DAO dao = DAO.getInstance();
		String str = "";
		String sql = "";
		sql = "select ssbh from ssxxb where ssbh=?";
		ssbh = dao.getOneRs(sql, new String[]{ssbh}, "ssbh");
		if(ssbh==null || "".equalsIgnoreCase(ssbh)){
			str="宿舍编号不存在";
		}
		if(qsdh!=null && !"".equalsIgnoreCase(qsdh) && ssbh!=null && !"".equalsIgnoreCase(ssbh)){
			sql = "select qsdh from ssxxb where ssbh=?";
			qsdh = dao.getOneRs(sql, new String[]{ssbh}, "qsdh");
			if(qsdh==null || "".equalsIgnoreCase(qsdh)){
				str= "寝室电话错误";
			}
		}		
		return str;
	}
	
	/**
	 * 上海工程技术大学获取学生已毕业年限
	 * @param String xh
	 * @return int
	 * */
	public int getGraduateNum(String xh){
		DAO dao = DAO.getInstance();
		int num = 0;
		int byn = 0;
		int dqn = 0;
		int rxy = 0;
		int dqy = 0;
		String rxrq = "";
		String[] value = null;
		String sql = "";
		xh = xh.trim();
		
		sql = "select (to_number(a.nj)+to_number(a.xz)) byn,(select b.rxrq from view_xsxxb b where a.xh=b.xh) rxrq from view_xsjbxx a where a.xh=?";
		value = dao.getOneRs(sql, new String[]{xh}, new String[]{"byn","rxrq"});
		if(value!=null){
			byn = Integer.parseInt((value[0]==null || value[0].equalsIgnoreCase("")) ? "0" : value[0]);
			rxrq = value[1]==null || "".equalsIgnoreCase(value[1]) ? "" : value[1];
		}
		
		if(rxrq != null && !"".equalsIgnoreCase(rxrq) && rxrq.indexOf("-")>0 && rxrq.length()>6){
			rxy = Integer.parseInt(rxrq.substring(5, 7));
		}else if(rxrq != null && !"".equalsIgnoreCase(rxrq) && rxrq.length()>5){
			rxy = Integer.parseInt(rxrq.substring(4, 6));
		}
		
		sql = "select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn, substr(to_char(sysdate,'yyyy-mm-dd'),6,2) dqy from dual";
		value = dao.getOneRs(sql,new String[]{}, new String[]{"dqn", "dqy"});
		dqn = Integer.parseInt(value[0]);
		dqy = Integer.parseInt(value[1]);
		
		num = dqn-byn;
		if(dqy-rxy<0 & num>1){
			num -= 1;
		}
		
		return num;
	}
	
	/**
	 * 判断用户是否为辅导员
	 * @param String userName
	 * @return boolean 
	 * */
	public boolean isFdy(String userName){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "select count(*) num from  view_fdybbj where zgh=?";
		int num = Integer.parseInt(dao.getOneRs(sql, new String[]{userName},"num"));
		flag = num>0 ? true : false;
		return flag;
	}
	
	/**
	 * 判断学生是否是辅导员所带学生
	 * @param String xh
	 * @param String userName
	 * @return boolean 
	 * */
	public boolean stuIsOwnFdy(String xh,String userName){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String sql = "";
		sql = "select count(*)num  from view_fdybbj where zgh=? and bjdm=(select bjdm from view_xsjbxx where xh=?)";
		int num = Integer.parseInt(dao.getOneRs(sql, new String[]{userName,xh}, "num"));
		flag = num>0 ? true : false;
		return flag ;
	}
	
	/**
	 * 获取省列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getSsList(){
		List<HashMap<String, String>> ssList = null;
		DAO dao = DAO.getInstance();
		String sql = "select distinct sydqdm ssdm,sydq ssmc from dmk_sydq order by sydqdm";
		ssList = dao.getList(sql, new String[]{}, new String[]{"ssdm","ssmc"});
		return ssList;
	}
	
	/**
	 * 获取市县列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getShList(){
		List<HashMap<String, String>> ssList = null;
		DAO dao = DAO.getInstance();
		String sql = "select distinct qxdm shidm,ltrim(rtrim(qxmc,'　'),'　') shimc from dmk_qx";
		ssList = dao.getList(sql, new String[]{}, new String[]{"shidm","shimc"});
		return ssList;
	}
	
	/**
	 * 获取县列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getXiList(){
		List<HashMap<String, String>> ssList = null;
		DAO dao = DAO.getInstance();
		String sql = "select distinct qxdm xiandm,ltrim(rtrim(qxmc,'　'),'　') xianmc from dmk_qx";
		ssList = dao.getList(sql, new String[]{}, new String[]{"xiandm","xianmc"});
		return ssList;
	}
	
	/**
	 * 根据省代码获取市列表
	 * @param String shenId
	 * @return HashMap<String, List>
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiList(String shenId){
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> shiList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> xianList = new ArrayList<HashMap<String, String>>();

		HashMap<String, List<HashMap<String, String>>> tMap = new HashMap<String, List<HashMap<String, String>>>();
		String sTemp = !Base.isNull(shenId) && shenId.length()>1 ? shenId.substring(0, 2) : "%";
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("shidm", "");
		map.put("shimc", "----请选择----");
		map.put("xiandm", "");
		map.put("xianmc", "----请选择----");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qxdm shidm,ltrim(rtrim(qxmc,'　'),'　') shimc ");
		sql.append("from dmk_qx where qxdm like'");
		sql.append(sTemp);
		sql.append("__00' and qxdm not like '");
		sql.append(sTemp);
		sql.append("0000'");
		sql.append(" order by qxdm ");
		
		shiList.add(map);
		shiList.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"shidm","shimc"}));

		sql = new StringBuilder();
		sql.append("select distinct qxdm xiandm,ltrim(rtrim(qxmc,'　'),'　') xianmc ");
		sql.append("from dmk_qx where qxdm like'");
		sql.append(sTemp);
		sql.append("____' and qxdm not like'");
		sql.append(sTemp);
		sql.append("__00'   order by qxdm");
		
		xianList.add(map);
		xianList.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"xiandm","xianmc"}));

		tMap.put("shiList", shiList);
		tMap.put("xianList", xianList);
		return tMap;
	}
	
	/**
	 * 根据省代码获取市列表
	 * @param String shenId
	 * @return HashMap<String, List>
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiListNew(String shenId){
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> shiList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> xianList = new ArrayList<HashMap<String, String>>();

		HashMap<String, List<HashMap<String, String>>> tMap = new HashMap<String, List<HashMap<String, String>>>();
		String sTemp = !Base.isNull(shenId) && shenId.length()>1 ? shenId.substring(0, 2) : "%";
		String shiTemp = !Base.isNull(shenId) && shenId.length()>1 ? (shenId.length()==2 ? shenId.substring(0, 2)+"__" : shenId.substring(0, 4)) : "%";
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("shidm", "");
		map.put("shimc", "----请选择----");
		map.put("xiandm", "");
		map.put("xianmc", "----请选择----");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qxdm shidm,ltrim(rtrim(qxmc,'　'),'　') shimc ");
		sql.append("from dmk_qx where qxdm like'");
		sql.append(sTemp);
		sql.append("__00' and qxdm not like '");
		sql.append(sTemp);
		sql.append("0000'");
		sql.append(" order by qxdm ");
		
		shiList.add(map);
		shiList.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"shidm","shimc"}));

		sql = new StringBuilder();
		sql.append("select distinct qxdm xiandm,ltrim(rtrim(qxmc,'　'),'　') xianmc ");
		sql.append("from dmk_qx where qxdm like'");
		sql.append(shiTemp);
		sql.append("__' and qxdm not like'");
		sql.append(sTemp);
		sql.append("__00'   order by qxdm");
		
		xianList.add(map);
		xianList.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"xiandm","xianmc"}));

		tMap.put("shiList", shiList);
		tMap.put("xianList", xianList);
		return tMap;
	}
	
	/**
	 * 根据省名称获取市列表
	 * @param String shenId
	 * @return HashMap<String, List>
	 * */
	public HashMap<String, List<HashMap<String, String>>> shiList(String shenName){
		List shiList = new ArrayList();
		List xianList = new ArrayList();
		DAO dao = DAO.getInstance();
		String getId ="select qxdm from dmk_qx where qxmc=?";
		List<HashMap<String,String>> idlist=dao.getList(getId, new String[]{shenName}, new String[]{"qxdm"});
		String sTemp="";
		if(idlist.size()>0){
			HashMap<String,String>hashMap=idlist.get(0);
			sTemp=hashMap.get("qxdm");
		}
		
		List rs = new ArrayList();
		List rsT = new ArrayList();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, List<HashMap<String, String>>> tMap = new HashMap<String, List<HashMap<String, String>>>();
		sTemp = sTemp!=null && !"".equalsIgnoreCase(sTemp)?sTemp.substring(0, 2):"%";
		String sql = "select distinct qxdm shidm,ltrim(rtrim(qxmc,'　'),'　') shimc from dmk_qx where qxdm like'"+sTemp+"__00' and qxdm not like '"+sTemp+"0000' order by qxdm ";
		map.put("shidm", "");
		
		shiList.add(map);		
		rs = dao.getList(sql, new String[]{}, new String[]{"shidm","shimc"});
		shiList.addAll(rs);	
		
		map = new HashMap<String, String>();
		map.put("xiandm", "");
		xianList.add(map);
		sql = "select distinct qxdm xiandm,ltrim(rtrim(qxmc,'　'),'　') xianmc from dmk_qx where qxdm like'"+sTemp+"____' and qxdm not like'"+sTemp+"__00'   order by qxdm";
		rsT = dao.getList(sql, new String[]{}, new String[]{"xiandm","xianmc"});
		xianList.addAll(rsT);
		
		tMap.put("shiList", shiList);
		tMap.put("xianList", xianList);
		return tMap;
	}
	
	/**
	 * 根据市代码查询区县列表
	 * @param String shiId
	 * @return List
	 * */
	public List getXianList(String shiId){
		List<HashMap<String, String>> xianList = new ArrayList<HashMap<String, String>>();
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sTemp = shiId!=null && !"".equalsIgnoreCase(shiId) ? shiId.substring(0, 4) : " ";
		String sql = "select distinct qxdm xiandm,ltrim(rtrim(qxmc,'　'),'　') xianmc from dmk_qx where qxdm like'"+sTemp+"%' and qxdm<>'"+sTemp+"00' order by qxdm ";
		map.put("xiandm", "");
		map.put("xianmc", "--请选择--");	
		xianList.add(map);
		xianList.addAll(dao.getList(sql, new String[]{}, new String[]{"xiandm","xianmc"})) ;		
		return xianList;
	}
	
	/**
	 * 获取婚姻状况列表
	 * @param String 
	 * @return List
	 * */
	public List<HashMap<String, String>> getHyzkList(){
		DAO dao = DAO.getInstance();
		return dao.getWhList("dmk_hyzk", "hyzkdm", "hyzkmc", "", "", "");
	}
	
	/**
	 * 获取国别列表
	 * @param String 
	 * @return List
	 * */
	public List<HashMap<String, String>> getGbList(){
		DAO dao = DAO.getInstance();
		return dao.getWhList("dmk_gb", "gbdm", "gbmc", "", "", "");
	}
	
	/**
	 * 获取无符号的取到秒的时间
	 * @return String
	 * */
	public static String getCurrDate(){
		DAO dao = DAO.getInstance();
		String time = "";
		time = dao.getOneRs("select to_char(sysdate,'yyyymmddHH24MISS') time from dual", new String[]{}, "time");
		return time;
	}
	
	/**
	 * 根据班级名称获取班级下的学生信息
	 * @param String bjmc
	 * @return List
	 * */
	public List selectStuinfoByBjmc(String bjmc){
		DAO dao = DAO.getInstance();
		String sql = "select xh,xm,xb,sfzh,xz,xymc,zymc,bjmc,xjlb,jg,zzmmmc from view_xsxxb where bjmc=?";
		String[] inputValue = {bjmc};
		String[] outputValue = {"xm","xh","xb","sfzh","xz","xymc","zymc","bjmc","xjlb","jg","zzmmmc"};
		
		return dao.rsToVator(sql, inputValue, outputValue);
	}
	
	/**
	 * 云南艺术点击班级名称查询班级下的学生信息表头
	 * **/
	public String[] getTopTr(){
		DAO dao = DAO.getInstance();
		String[] outputValue = {"xm","xh","xb","sfzh","xz","xymc","zymc","bjmc","xjlb","jg","zzmmmc"};
		return dao.getColumnNameCN(outputValue, "view_xsxxb");
	}
	
	/**
	 * 根据辅导员查询所带班级
	 * @param userName
	 * @return List
	 * */
	public List<HashMap<String, String>> getBjByFdy(String userName,String xydm,String zydm){
		DAO dao = DAO.getInstance();
		String[] inputValue = {userName};
		String[] outputValue = {"bjdm","bjmc"};
		String query = "";
		if(xydm!=null && !"".equalsIgnoreCase(xydm)){
			query += " and xydm='" + xydm + "'";
		}
		if(zydm!=null && !"".equalsIgnoreCase(zydm)){
			query += " and zydm='" + zydm + "'";
		}
		String sql = "select distinct a.bjdm, a.bjmc from view_njxyzybj a where exists(select bjdm from view_fdybbj b where a.bjdm=b.bjdm and b.zgh=? " + query + ")";
		
		return dao.getList(sql, inputValue, outputValue);
	}
	
	/**
	 * 判断学生是否可以注册
	 * @param xh
	 * @return boolean
	 * */
	public boolean zgdzdxCheckZc(String xh){
		boolean flag = true;
		boolean jfFlag = true;		
		double sum = 0;
		double zzje = 0;
		
		//判断学费是否缴清
		DAO dao = DAO.getInstance();
		String xn = Base.currXn;
		String sql = "select count(*) num,sum(fybz) money from cw_bks_xsfybz a where not exists(select 1 from cw_bks_xssfb b where a.xh=b.xh and a.nd=b.sfqjdm and a.xh=? and a.nd=?) and a.xh=? and a.nd=?";
		String[] values = dao.getOneRs(sql, new String[]{xh,xn,xh,xn}, new String[]{ "num","money"});
		String num = values[0];
		String je = values[1];
		num = num==null ||"".equalsIgnoreCase(num) ? "0" : num;
		if(Integer.parseInt(num)>0){
			//有未缴清的费用
			jfFlag = false;
			sum += Double.parseDouble(je);
			sql = "select sum(ysje-ssje) je from cw_bks_xssfb a where ssje<ysje and xh=? and sfqjdm=?";
			je = dao.getOneRs(sql, new String[]{xh,xn}, "je");
			je = je==null || "".equalsIgnoreCase(je) ? "0" : je;
			sum += Double.parseDouble(je);
		}else if(Integer.parseInt(num)>=0){
			//每项费用都有缴费记录
			sql = "select count(*) num,sum(ysje-ssje)je from cw_bks_xssfb a where ssje<ysje and xh=? and sfqjdm=?";
			values = dao.getOneRs(sql, new String[]{xh,xn}, new String[]{"num","je"});
			num = values[0];
			num = num==null ||"".equalsIgnoreCase(num) ? "0": num;
			if(Integer.parseInt(num)>0){
				jfFlag = false;
				je = values[1]==null || "".equalsIgnoreCase(values[1]) ? "0" : values[1];
				sum += Double.parseDouble(je);
			}
		}
		sql = "select sum(xgpzje) zzje from  jzxj_xssqb where xgsh='通过' and xh = ? and xn=? ";
		je = dao.getOneRs(sql, new String[] { xh, xn }, "zzje");
		zzje=Base.isNull(je)?0:Double.parseDouble(je);
		if(jfFlag==false){
			//有未缴清的费用判断资助金额是否抵消应缴费用
			if(sum>zzje){
				flag = false;
			}
		}	
		//没有缴费记录
		sql = "select count(*) num from cw_bks_xssfb where xh=? and sfqjdm=?";
		String result = dao.getOneRs(sql, new String[]{xh,xn}, "num");
		result = result==null || "".equalsIgnoreCase(result) ? "0" : result;
		if(Integer.parseInt(result)<1){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 判断学生是否可以毕业（根据年级＋学制＝当前年来判断）
	 * @param xh
	 * @return boolean
	 * */
	public boolean zgdzdxCheckBy(String xh){//年级＋学制＝当前年
		boolean flag = true;
		DAO dao = DAO.getInstance();
		String sql = "select to_number(nj+case when xz is not null then xz else '0' end)year,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)dqn from view_xsjbxx where xh=?";
		String[] values = dao.getOneRs(sql, new String[]{xh}, new String[]{"year","dqn"});
		if(values == null || Integer.parseInt(values[0]) !=Integer.parseInt(values[1])){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 根据名称判断类型是表还是视图
	 * @param name
	 * @return String
	 * */
	public String tabOrView(String name){
		DAO dao = DAO.getInstance();
		String result = "";
		String sql = "select table_name from user_tables where table_name=?";
		result = dao.getOneRs(sql, new String[]{name.toUpperCase()}, "table_name");
		if(result != null && ! "".equalsIgnoreCase(result)){
			result = "table";
		}else{
			sql = "select view_name from user_views where view_name=?";
			result = dao.getOneRs(sql, new String[]{name.toUpperCase()}, "view_name");
			result = result == null || "".equalsIgnoreCase(result) ? "" : "view";
		}
		return result;
	}
	
	/**
	 * 查询学生信息修改条款
	 * @return String
	 * */
	public String getStuInfoPub(){
		DAO dao = DAO.getInstance();
		String sql = "select content from xsxx_xxfbb where title='信息修改协议'";
		return dao.getOneRs(sql, new String[]{}, "content");
	}
	
	/**
	 * 获取握手密码
	 * @return String
	 * */
	public String getZfSoftKey(){
		DAO dao = DAO.getInstance();
		String sql = "select zfssokey from view_zf_key where rownum=1";
		String result = dao.getOneRs(sql, new String[]{}, "zfssokey");
		result = result == null ? "" : result;
		return result;
	}	
	
	/**
	 * 获取加密字符
	 * @param userName
	 * @param time
	 * @return time
	 * */
	public String getTurnJwUrl(String userName, String time){
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(userName + getZfSoftKey() + time);		
	}
	
	/**
	 * 判断记录是否存在
	 * @param table
	 * @param pk
	 * @param value
	 * @return boolean
	 * */
	public boolean checkExists(String table, String pk, String value){
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from " + table + " where " + pk + "=?";
		String result = dao.getOneRs(sql, new String[]{value}, "num");
		result = result != null && !"".equalsIgnoreCase(result) ? result : "0";
		return Integer.parseInt(result) >0 ? true : false;
	}
	
	/**
	 * 获得学生状态
	 **/
	public List<HashMap<String, String>> getXsztList(String xjzt) {
		DAO dao = DAO.getInstance();
		String sql = "select ydlbm dm,ydlbmc mc from dm_ydlb where xjzt = ?";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {xjzt},
				new String[] { "dm", "mc" });
		return list;
	}
	
	
	/**
	 * 查询学生学籍异动信息
	 * */
	public HashMap<String, String> queryXjydxx(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select ydxh,ydsm,clwh,ydyy,ydrq,(select ydlbmc from dm_ydlb b where a.ydlbm=b.ydlbm) ydlbmc,ydhxjztm xjztm,(case xszt when '休学' then '休学' else '未休学' end) sfxx from bks_xjydxx a where ydxh=(select max(ydxh) from bks_xjydxx c where xh=?)";
		return dao.getMap(sql, new String[]{xh}, new String[]{"ydxh","ydsm","clwh","ydyy","ydrq","ydlbmc","xjztm","sfxx"});
	}
	
	/**
	 * 查询学籍状态列表（代码维护中的所有学籍状态）
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getXjztList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct zxdmmc from dm_zju_xjzt";
		return dao.getList(sql, new String[]{}, new String[]{"zxdmmc"});
	}
	
	/**
	 * 查询校区信息列表（代码维护中的所有校区信息）
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getYxdmList(){
		DAO dao = DAO.getInstance();
		String sql = "select dm,xqmc from dm_zju_xq";
		return dao.getList(sql, new String[]{}, new String[]{"dm","xqmc"});
	}
	/**
	 * 获取有学籍状态(学籍状态名称中含有'有'的记录)
	 * @return HashMap<String, String>
	 * */
	public static HashMap<String, String> getYxj(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct zxdmmc from dm_zju_xjzt where zxdmmc like '有%'";
		return dao.getMap(sql, new String[]{}, new String[]{"zxdmmc"});
	}
	
	/**
	 * 查询学生学习经历信息（纵向存储）
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxxjl(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select xh, kssj, jssj,szdw,drzw,zmr,szdq,szbm,zmrdw,zmrzw from xsxxjlb where xh=?";
		String[] outputValue = {"kssj", "jssj", "szdw", "drzw", "zmr", "xh" , "szdq", "szbm", "zmrdw", "zmrzw"};
		
		return dao.getList(sql, new String[]{xh}, outputValue); 
	}
	
	/**
	 * 查询学生学习经历信息(横向存储)
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> getXsxxjlb(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select * from xsxx_xsxxjlb where xh=?";
		String[] outputValue = {"jl1_qzny","jl1_xxjgzdw","jl1_xxhrzw","jl2_qzny",
								"jl2_xxjgzdw","jl2_xxhrzw","jl3_qzny","jl3_xxjgzdw",
								"jl3_xxhrzw","jl4_qzny","jl4_xxjgzdw","jl4_xxhrzw",
								"jl5_qzny","jl5_xxjgzdw","jl5_xxhrzw","jl6_qzny",
								"jl6_xxjgzdw","jl6_xxhrzw"};		
		return dao.getMap(sql, new String[]{xh}, outputValue); 
	}
	
	/**
	 * 查询学生社会关系信息
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsshgx(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select xh, cyxm, cynl, cyzzmmdm, (select zzmmmc from zzmmdmb b where a.cyzzmmdm=b.zzmmdm)cyzzmmmc,cygzdw,cydrzw,cylxdh from xsxx_xsshgxb a where xh=?";
		String[] outputValue = {"xh" , "cyxm", "cynl", "cyzzmmdm", "cyzzmmmc", "cygzdw", "cydrzw", "cylxdh"};
		
		return dao.getList(sql, new String[]{xh}, outputValue); 
	}
	
	/**
	 * 获取学生关联信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsglxx(String xh){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql=new StringBuilder();
		
//		sql.append("  select a.xh,d.xm fdyxm,d.lxdh fdylxdh, f.xm zlbzrxm, ");
//		sql.append("  f.lxdh zlbzrlxdh,e.xm bzrxm,e.yddh bzrlxdh from view_xsjbxx a left join fdybjb b on a.bjdm=b.bjdm ");
//		sql.append("  left join bzrbbb c on a.bjdm=c.bjdm ");
//		sql.append("  left join fdyxxb d on b.zgh=d.zgh ");
//		sql.append("  left join fdyxxb e on c.zgh=e.zgh ");
//		sql.append("  left join zlbzrxx f on a.bjdm = f.bjdm  where a.xh=? ");
		
//		String sql =StringUtils.joinStr("select a.xh,b.xm fdyxm,b.lxdh fdylxdh,",
//					"c.xm bzrxm,c.yddh bzrlxdh,d.xm zlbzrxm, d.lxdh zlbzrlxdh ",
//					"from view_xsjbxx a left join view_fdybjdz b on a.bjdm=b.bjdm",
//					" left join view_bzrbbj c on a.bjdm=c.bjdm left join ",
//					"zlbzrxx d  on d.bjdm=a.bjdm where a.xh=?");
		
		// ---------------------------偷懒~~~~~  2012.11.30 qlj 辅导员、班主任信息 begin-------------------------
		sql.append(" select b.zgh,d.xm,d.lxdh from view_xsjbxx a left join fdybjb b ");
		sql.append(" on a.bjdm=b.bjdm   left join fdyxxb d on b.zgh=d.zgh  ");
		sql.append(" where a.xh=? and b.zgh is not null ");
		
		List<HashMap<String,String>>fdyxxList=dao.getList(sql.toString(), new String[]{xh},new String[]{"zgh","xm","lxdh"} );
		
		StringBuilder fdyxxInfo = new StringBuilder("<table border='0'>");
		if(fdyxxList!=null && fdyxxList.size()>0){
			for(int i=0;i<fdyxxList.size();i++){
				HashMap<String,String>fdyxxMap=fdyxxList.get(i);
				fdyxxInfo.append("<tr>");
				fdyxxInfo.append("<th style='width:50px'>职工号</th>");
				fdyxxInfo.append("<td style='width:100px'>"+(Base.isNull(fdyxxMap.get("zgh"))?"":fdyxxMap.get("zgh"))+"</td>");
				fdyxxInfo.append("<th style='width:100px'>姓名</th>");
				fdyxxInfo.append("<td style='width:100px'>"+(Base.isNull(fdyxxMap.get("xm"))?"":fdyxxMap.get("xm"))+"</td>");
				fdyxxInfo.append("<th style='width:100px'>联系电话</th>");
				fdyxxInfo.append("<td style='width:100px'>"+(Base.isNull(fdyxxMap.get("lxdh"))?"":fdyxxMap.get("lxdh"))+"</td>");
				fdyxxInfo.append("</tr>");
			}
		}
		fdyxxInfo.append("</table>");
		map.put("fdyxx", fdyxxInfo.toString());
		
		sql=new StringBuilder();
		sql.append(" select b.zgh,d.xm,d.lxdh from view_xsjbxx a left join bzrbbb b ");
		sql.append(" on a.bjdm=b.bjdm   left join fdyxxb d on b.zgh=d.zgh  ");
		sql.append(" where a.xh=? and b.zgh is not null ");
		
		List<HashMap<String,String>>bzrxxList=dao.getList(sql.toString(), new String[]{xh},new String[]{"zgh","xm","lxdh"} );
		
		StringBuilder bzrxxInfo = new StringBuilder("<table border='0'>");
		if(bzrxxList!=null && bzrxxList.size()>0){
			for(int i=0;i<bzrxxList.size();i++){
				HashMap<String,String>bzrxxMap=bzrxxList.get(i);
				bzrxxInfo.append("<tr>");
				bzrxxInfo.append("<th style='width:50px'>职工号</th>");
				bzrxxInfo.append("<td style='width:100px'>"+(Base.isNull(bzrxxMap.get("zgh"))?"":bzrxxMap.get("zgh"))+"</td>");
				bzrxxInfo.append("<th style='width:100px'>姓名</th>");
				bzrxxInfo.append("<td style='width:100px'>"+(Base.isNull(bzrxxMap.get("xm"))?"":bzrxxMap.get("xm"))+"</td>");
				bzrxxInfo.append("<th style='width:100px'>联系电话</th>");
				bzrxxInfo.append("<td style='width:100px'>"+(Base.isNull(bzrxxMap.get("lxdh"))?"":bzrxxMap.get("lxdh"))+"</td>");
				bzrxxInfo.append("</tr>");
			}
		}
		bzrxxInfo.append("</table>");
		map.put("bzrxx", bzrxxInfo.toString());
		// ---------------------------偷懒~~~~~  2012.11.30 qlj end-------------------------
		
		return map;
	}
	
	
	/**
	 * 获得学生扩展字段
	 * @author yyp
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXskzzd(String xh){
		DAO dao=DAO.getInstance();
		String sql=" select zd1,zd2,zd3,zd4,zd5 from xsxxb where xh = ? ";
		return dao.getMap(sql, new String[]{xh}, new String[]{"zd1","zd2","zd3","zd4","zd5"});
	}
	
	/**
	 * 获得特殊学生记录
	 * @author yyp
	 * @param xh
	 * @return
	 */
	public String getTsxs(String xh){
		DAO dao=DAO.getInstance();
		String sql=" select count(1) num from xg_xljk_tsxsxxb where xh = ? ";
		return dao.getOneRs(sql, new String[]{xh}, "num");
	}
	
	/**
	 * 自定义字段学生信息判断非空字段是否维护
	 * author qlj
	 */
	public boolean checkFkzd(){
		DAO dao=DAO.getInstance();
		String sql=" select count(1) num from xg_view_xsxx_xsqzd where (zd='xydm' or zd='zydm' or zd='bjdm' or zd='xh' or zd='nj') and sfqy='是' ";
		String []num=dao.getOneRs(sql, new String[]{}, new String[]{"num"});
		if(num[0].equalsIgnoreCase("5")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 查询银行卡类型列表（代码维护中的所有银行卡类型）
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getYhklxList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct yhklxmc from ZJLG_YHKLX";
		return dao.getList(sql, new String[]{}, new String[]{"yhklxmc"});
	}
	
	
	public List<HashMap<String, String>> getKnlxList(){
		DAO dao = DAO.getInstance();
		String sql = "select dm,mc from xg_xsxx_jtknlxb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取综合项目字段
	 * @param model
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getKind(StudentInfoForm model,String xn) {
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("select xmdm from xg_pjpy_zcxmb where xn=? order by xn");
		return dao.getList(sql.toString(), new String[]{xn}, new String[]{"xmdm"});
	}
}

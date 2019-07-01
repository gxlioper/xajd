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
	 * ��ȡѧ����ϸ��Ϣ����Ҫ��ѯĳ��Ŀ��������Ϣ
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
			sql1 = "select * from " + tableName1 + " where xh=? and xxsh='ͨ��'";
			if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm) && "view_wjcf".equalsIgnoreCase(tableName)) {
				sql1 = "select xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,nd,cfsj,cfwh,cflbmc,cfyymc,xm,ssjg,cxsj from " + tableName1 + " a where xh=? and xxsh='ͨ��'";
			}
		}else if(tableName != null && "xg_view_pjpy_xmsh".equalsIgnoreCase(tableName)){//2012.3.19�ո�����������
			sql1 = "select a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje,a.sqsj from " + tableName + " a where a.shjb=(select max(b.shjb) from xg_view_pjpy_xmsh b where a.xh=b.xh) and a.shzt='ͨ��' and a.xh=? ";
		}else if(tableName != null && "view_jy_jyxy".equalsIgnoreCase(tableName)){
			sql1 = "select * from " + tableName + " where xh=? and xxshyj='ͨ��'";
		}else if(tableName != null && "view_jygl_jyxyblsq".equalsIgnoreCase(tableName)){
			sql1 = "select * from " + tableName + " where xh=? and xxshyj='ͨ��'";
		}else if(tableName != null && "view_jy_bysxxb".equalsIgnoreCase(tableName)){
			sql1 = "select * from " + tableName + " where xh=? and shzt='ͨ��'";
		}else if(tableName != null && "xg_pjpy_zhcpb".equalsIgnoreCase(tableName)){//�ۺϣ���ȡ��ѧ���ж��ٸ�ѧ��
			sql1 = "select xn from xg_pjpy_zhcpb where xh=? group by xn";
		}else if(tableName != null && "xg_pjpy_zcxmb".equalsIgnoreCase(tableName)){//�ۺϣ���ȡ��ѧ���ж��ٸ��ۺ���Ŀ
			sql1 = "select xmdm,xmmc from xg_pjpy_zcxmb where xn=? order by xn";
		}else if(tableName != null && "xg_pjpy_zhcpb2".equalsIgnoreCase(tableName)){
			StudentInfoForm model = new StudentInfoForm();
			StuInfoDAO dao2 = new StuInfoDAO();
			String zd ="";
			String[] xszdArr = null;
			//System.out.println(xh);
			xszdArr = xh.split(",");
			String xsxh =xszdArr[1].toString();
			List<HashMap<String, String>> kind = dao2.getKind(model,xsxh);//��ȡzd�ֶ�
			if(kind!=null && kind.size()>0){
				for (int i = 0; i < kind.size(); i++) {
					HashMap<String, String> kindMap = kind.get(i);
					zd+=" nvl("+kindMap.get("xmdm").toString()+",0)"+kindMap.get("xmdm").toString()+",";
					//System.out.println(kindMap.get("xmdm").toString());
				}
				sql1 = "select xn,nvl((select xqmc from xqdzb b where a.xq=b.xqdm),'��')xq ,"+zd+"zcfbjpm from xg_pjpy_zhcpb a where xh=? and xn=?";
			}else{
				sql1 = "select xn,nvl((select xqmc from xqdzb b where a.xq=b.xqdm),'��')xq ,zd1,zd2,zd3,zd4,zd5,zcfbjpm from xg_pjpy_zhcpb where xh=? and xn=?";
			}
		}
		if(tableName.equalsIgnoreCase("view_xsgwxx")){//ѧ����λ��Ϣ
			sql1 = "select * from " + tableName1 + " where xh=? and xxyj='ͨ��'";
		}
		if(tableName!=null && (tableName.equalsIgnoreCase("view_cjb"))){
			//ѧ���ɼ�
			sql1 = "select a.xh,a.xm,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xq,kcmc,kcxz,cj,xf,bkcj,jd,cxcj from " + tableName + " a where xh=? order by a.xn,a.xq,kcxz";
		}
		if(xxdm.equalsIgnoreCase("10344") &&tableName!=null && (tableName.equalsIgnoreCase("XG_XLJK_ZJZYY_TSXSXXB"))){
			//�㽭��ҽҩ��ѧ������
			sql1 = "select a.*,(select xqmc from xqdzb where xqdm = a.xq) xqmc from " + tableName + " a where xh=? order by a.xn,a.xq,a.xh";
		}
		if(tableName!=null && tableName.equalsIgnoreCase("view_xsxx_xjyd")){
			//��ѧ���춯
			sql1 = "select * from view_xsxx_xjyd where xh = ? order by xn,xq desc ";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX) || xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			if(tableName != null && "view_xscjff".equalsIgnoreCase(tableName)){
				sql1 = "select * from " + tableName + " where xh=? and xxsh='ͨ��'" ; 
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			if (tableName != null
					&& ("view_xszz_com_gjzxj2".equalsIgnoreCase(tableName) || "view_xszz_com_xfjm1"
							.equalsIgnoreCase(tableName))) {
				sql1 = "select * from " + tableName
						+ " where xh=? and xxsh='ͨ��'";
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
			sb.append("a.zzje from xszz_jxjsqb a,view_xsjbxx b where a.xh=b.xh and a.xxsh='ͨ��' union select a.xn,a.xh,b.xm,b.xymc,b.zymc,");
			sb.append("b.bjmc,(select xmmc from xszz_xmdmb where xmdm=a.xmdm) xmmc,a.zzje from xszz_zxjsqb a,view_xsjbxx b ");
			sb.append("where a.xh=b.xh and a.xxsh='ͨ��' union select a.xn,a.xh,b.xm,b.xymc,b.zymc,b.bjmc,(select xmmc from xszz_xmdmb where ");
			sb.append("xmdm=a.xmdm) xmmc,a.zzje from xszz_lzjxjsqb a,view_xsjbxx b where a.xh=b.xh and a.xxsh='ͨ��') where xh=? order by xn,xmmc");
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
				List<HashMap<String, String>> kind = dao2.getKind(model,xsxn);//��ȡzd�ֶ�
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
			sql1 = "select * from " + tableName1 + " where xh=? and xxsh='ͨ��'";
		}
		if(tableName!=null && tableName.equalsIgnoreCase("view_cjb")){
			//ѧ���ɼ�
			sql1 = "select a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xq,kcmc,kcxz,cj,xf,jd from " + tableName + " a where xh=?";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX) || xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			if(tableName != null && "view_xscjff".equalsIgnoreCase(tableName)){
				sql1 = "select * from " + tableName + " where xh=? and xxsh='ͨ��'" ; 
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
	 * �й���ҵ��ѧ�ۺ����ʲ�����Ϣ��ѯ
	 * @param xh
	 * @param tableName
	 * @param colList
	 * @return List
	 * @throws Exception 
	 * */
	public List getZhszcpOfZgkydx(String xh,String[] colList) throws Exception{
		DAO dao = DAO.getInstance();
		PjpyZgkdZhszcpDAO pzDao = PjpyZgkdZhszcpDAO.getInstance();
		String sql = pzDao.queryZhszfPmfs();//��ȡ��ѯ���
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
	 * �ж�ģ�����Ƿ����ѧ����¼
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
				sql = "select xh from " + tableNames[i] + " where xh=? and xxsh='ͨ��'";
			}
			list.addAll(dao.getList(sql, new String[]{xh}, new String[]{"xh"}));
		}
		
		flag = list.size()>0 ? true : false;
		return flag;
	}
	
	/**
	 * �ж�ѧ������ģ�����Ƿ����ѧ����¼
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
	 * ������������
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
	 * ������������
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
	 * ��ҵְҵѧ��������ɼ�
	 * @param String pk
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List getBjgcjInfo(String pk){
		HashMap< String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		List rs = new ArrayList();
		//zpcj1 ����ɰٷ��ƺ�ĳɼ�
		String[] colList={"xh","kcmc","zpcj1","cxcj","xf"};
		String[] titList={"ѧ��","�γ�����","�ɼ�","���޳ɼ�","ѧ��"};
		String sql = "select xh,kcmc,zpcj1,cxcj,xf from cjb where xn||xq||xh=? and (zpcj1='������' or zpcj1<'60')";
		
		for(int i=0;i<colList.length;i++){
			map.put(colList[i], titList[i]);
		}
		rs.add(map);
		rs.addAll(dao.getList(sql, new String[]{pk},colList));
		return rs;		
	}
	
	/**
	 * ѧ��������ȡҪ��ʾ�ı�
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
	 * ѧ��������ȡ����Ҫ��ʾ����
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
		//���ֶ�ת��������
		for(int i=0;i<colList.size();i++){
			col[i] = colList.get(i).toString();
		}
		return col;
	}
	
	/**
	 * ѧ����ϸ��Ϣ����ʾ��ѧ��������Ϣ
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
 
		//���ֶ�ת��������
		for(int i=0;i<colList.size();i++){
			col[i] = colList.get(i).toString();
		}		
		
		//��ȡ������ӵ�����		 
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
	 * ��ȡ��ҵ����ı�
	 * @param String xxdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTabCNofJygl(String xxdm){		
		DAO dao = DAO.getInstance();
		String[]en={"view_jy_bysxxb","view_jy_jyxy","view_jygl_jyxyslqdjb","view_jygl_jyxysbb"};
		String[]cn={"��ҵ����Ϣ","��ҵЭ��","��ҵЭ������ȡ�Ǽ�","��ҵЭ���걨"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * �й���ҵ��ѧ��ȡѧ����ͥ��ϸ��Ϣ
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
	 * ��ȡѧ�������θ���Ա��Ϣ
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
	 * ��ְԺ�շ���Ϣ
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
	 * �������ѧ���Ƿ����
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
	 * �Ϻ����̱�ҵ��ת���������Ƿ��Ѿ����������¼
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
	 * ���������ѧ������չ�༶��ҵ��Ϣ
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
	 * �ж������Ÿ����ҵ绰�Ƿ���ȷ
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
			str="�����Ų�����";
		}
		if(qsdh!=null && !"".equalsIgnoreCase(qsdh) && ssbh!=null && !"".equalsIgnoreCase(ssbh)){
			sql = "select qsdh from ssxxb where ssbh=?";
			qsdh = dao.getOneRs(sql, new String[]{ssbh}, "qsdh");
			if(qsdh==null || "".equalsIgnoreCase(qsdh)){
				str= "���ҵ绰����";
			}
		}		
		return str;
	}
	
	/**
	 * �Ϻ����̼�����ѧ��ȡѧ���ѱ�ҵ����
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
	 * �ж��û��Ƿ�Ϊ����Ա
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
	 * �ж�ѧ���Ƿ��Ǹ���Ա����ѧ��
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
	 * ��ȡʡ�б�
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
	 * ��ȡ�����б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getShList(){
		List<HashMap<String, String>> ssList = null;
		DAO dao = DAO.getInstance();
		String sql = "select distinct qxdm shidm,ltrim(rtrim(qxmc,'��'),'��') shimc from dmk_qx";
		ssList = dao.getList(sql, new String[]{}, new String[]{"shidm","shimc"});
		return ssList;
	}
	
	/**
	 * ��ȡ���б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getXiList(){
		List<HashMap<String, String>> ssList = null;
		DAO dao = DAO.getInstance();
		String sql = "select distinct qxdm xiandm,ltrim(rtrim(qxmc,'��'),'��') xianmc from dmk_qx";
		ssList = dao.getList(sql, new String[]{}, new String[]{"xiandm","xianmc"});
		return ssList;
	}
	
	/**
	 * ����ʡ�����ȡ���б�
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
		map.put("shimc", "----��ѡ��----");
		map.put("xiandm", "");
		map.put("xianmc", "----��ѡ��----");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qxdm shidm,ltrim(rtrim(qxmc,'��'),'��') shimc ");
		sql.append("from dmk_qx where qxdm like'");
		sql.append(sTemp);
		sql.append("__00' and qxdm not like '");
		sql.append(sTemp);
		sql.append("0000'");
		sql.append(" order by qxdm ");
		
		shiList.add(map);
		shiList.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"shidm","shimc"}));

		sql = new StringBuilder();
		sql.append("select distinct qxdm xiandm,ltrim(rtrim(qxmc,'��'),'��') xianmc ");
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
	 * ����ʡ�����ȡ���б�
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
		map.put("shimc", "----��ѡ��----");
		map.put("xiandm", "");
		map.put("xianmc", "----��ѡ��----");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qxdm shidm,ltrim(rtrim(qxmc,'��'),'��') shimc ");
		sql.append("from dmk_qx where qxdm like'");
		sql.append(sTemp);
		sql.append("__00' and qxdm not like '");
		sql.append(sTemp);
		sql.append("0000'");
		sql.append(" order by qxdm ");
		
		shiList.add(map);
		shiList.addAll(dao.getList(sql.toString(), new String[]{}, new String[]{"shidm","shimc"}));

		sql = new StringBuilder();
		sql.append("select distinct qxdm xiandm,ltrim(rtrim(qxmc,'��'),'��') xianmc ");
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
	 * ����ʡ���ƻ�ȡ���б�
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
		String sql = "select distinct qxdm shidm,ltrim(rtrim(qxmc,'��'),'��') shimc from dmk_qx where qxdm like'"+sTemp+"__00' and qxdm not like '"+sTemp+"0000' order by qxdm ";
		map.put("shidm", "");
		
		shiList.add(map);		
		rs = dao.getList(sql, new String[]{}, new String[]{"shidm","shimc"});
		shiList.addAll(rs);	
		
		map = new HashMap<String, String>();
		map.put("xiandm", "");
		xianList.add(map);
		sql = "select distinct qxdm xiandm,ltrim(rtrim(qxmc,'��'),'��') xianmc from dmk_qx where qxdm like'"+sTemp+"____' and qxdm not like'"+sTemp+"__00'   order by qxdm";
		rsT = dao.getList(sql, new String[]{}, new String[]{"xiandm","xianmc"});
		xianList.addAll(rsT);
		
		tMap.put("shiList", shiList);
		tMap.put("xianList", xianList);
		return tMap;
	}
	
	/**
	 * �����д����ѯ�����б�
	 * @param String shiId
	 * @return List
	 * */
	public List getXianList(String shiId){
		List<HashMap<String, String>> xianList = new ArrayList<HashMap<String, String>>();
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sTemp = shiId!=null && !"".equalsIgnoreCase(shiId) ? shiId.substring(0, 4) : " ";
		String sql = "select distinct qxdm xiandm,ltrim(rtrim(qxmc,'��'),'��') xianmc from dmk_qx where qxdm like'"+sTemp+"%' and qxdm<>'"+sTemp+"00' order by qxdm ";
		map.put("xiandm", "");
		map.put("xianmc", "--��ѡ��--");	
		xianList.add(map);
		xianList.addAll(dao.getList(sql, new String[]{}, new String[]{"xiandm","xianmc"})) ;		
		return xianList;
	}
	
	/**
	 * ��ȡ����״���б�
	 * @param String 
	 * @return List
	 * */
	public List<HashMap<String, String>> getHyzkList(){
		DAO dao = DAO.getInstance();
		return dao.getWhList("dmk_hyzk", "hyzkdm", "hyzkmc", "", "", "");
	}
	
	/**
	 * ��ȡ�����б�
	 * @param String 
	 * @return List
	 * */
	public List<HashMap<String, String>> getGbList(){
		DAO dao = DAO.getInstance();
		return dao.getWhList("dmk_gb", "gbdm", "gbmc", "", "", "");
	}
	
	/**
	 * ��ȡ�޷��ŵ�ȡ�����ʱ��
	 * @return String
	 * */
	public static String getCurrDate(){
		DAO dao = DAO.getInstance();
		String time = "";
		time = dao.getOneRs("select to_char(sysdate,'yyyymmddHH24MISS') time from dual", new String[]{}, "time");
		return time;
	}
	
	/**
	 * ���ݰ༶���ƻ�ȡ�༶�µ�ѧ����Ϣ
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
	 * ������������༶���Ʋ�ѯ�༶�µ�ѧ����Ϣ��ͷ
	 * **/
	public String[] getTopTr(){
		DAO dao = DAO.getInstance();
		String[] outputValue = {"xm","xh","xb","sfzh","xz","xymc","zymc","bjmc","xjlb","jg","zzmmmc"};
		return dao.getColumnNameCN(outputValue, "view_xsxxb");
	}
	
	/**
	 * ���ݸ���Ա��ѯ�����༶
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
	 * �ж�ѧ���Ƿ����ע��
	 * @param xh
	 * @return boolean
	 * */
	public boolean zgdzdxCheckZc(String xh){
		boolean flag = true;
		boolean jfFlag = true;		
		double sum = 0;
		double zzje = 0;
		
		//�ж�ѧ���Ƿ����
		DAO dao = DAO.getInstance();
		String xn = Base.currXn;
		String sql = "select count(*) num,sum(fybz) money from cw_bks_xsfybz a where not exists(select 1 from cw_bks_xssfb b where a.xh=b.xh and a.nd=b.sfqjdm and a.xh=? and a.nd=?) and a.xh=? and a.nd=?";
		String[] values = dao.getOneRs(sql, new String[]{xh,xn,xh,xn}, new String[]{ "num","money"});
		String num = values[0];
		String je = values[1];
		num = num==null ||"".equalsIgnoreCase(num) ? "0" : num;
		if(Integer.parseInt(num)>0){
			//��δ����ķ���
			jfFlag = false;
			sum += Double.parseDouble(je);
			sql = "select sum(ysje-ssje) je from cw_bks_xssfb a where ssje<ysje and xh=? and sfqjdm=?";
			je = dao.getOneRs(sql, new String[]{xh,xn}, "je");
			je = je==null || "".equalsIgnoreCase(je) ? "0" : je;
			sum += Double.parseDouble(je);
		}else if(Integer.parseInt(num)>=0){
			//ÿ����ö��нɷѼ�¼
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
		sql = "select sum(xgpzje) zzje from  jzxj_xssqb where xgsh='ͨ��' and xh = ? and xn=? ";
		je = dao.getOneRs(sql, new String[] { xh, xn }, "zzje");
		zzje=Base.isNull(je)?0:Double.parseDouble(je);
		if(jfFlag==false){
			//��δ����ķ����ж���������Ƿ����Ӧ�ɷ���
			if(sum>zzje){
				flag = false;
			}
		}	
		//û�нɷѼ�¼
		sql = "select count(*) num from cw_bks_xssfb where xh=? and sfqjdm=?";
		String result = dao.getOneRs(sql, new String[]{xh,xn}, "num");
		result = result==null || "".equalsIgnoreCase(result) ? "0" : result;
		if(Integer.parseInt(result)<1){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * �ж�ѧ���Ƿ���Ա�ҵ�������꼶��ѧ�ƣ���ǰ�����жϣ�
	 * @param xh
	 * @return boolean
	 * */
	public boolean zgdzdxCheckBy(String xh){//�꼶��ѧ�ƣ���ǰ��
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
	 * ���������ж������Ǳ�����ͼ
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
	 * ��ѯѧ����Ϣ�޸�����
	 * @return String
	 * */
	public String getStuInfoPub(){
		DAO dao = DAO.getInstance();
		String sql = "select content from xsxx_xxfbb where title='��Ϣ�޸�Э��'";
		return dao.getOneRs(sql, new String[]{}, "content");
	}
	
	/**
	 * ��ȡ��������
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
	 * ��ȡ�����ַ�
	 * @param userName
	 * @param time
	 * @return time
	 * */
	public String getTurnJwUrl(String userName, String time){
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(userName + getZfSoftKey() + time);		
	}
	
	/**
	 * �жϼ�¼�Ƿ����
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
	 * ���ѧ��״̬
	 **/
	public List<HashMap<String, String>> getXsztList(String xjzt) {
		DAO dao = DAO.getInstance();
		String sql = "select ydlbm dm,ydlbmc mc from dm_ydlb where xjzt = ?";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {xjzt},
				new String[] { "dm", "mc" });
		return list;
	}
	
	
	/**
	 * ��ѯѧ��ѧ���춯��Ϣ
	 * */
	public HashMap<String, String> queryXjydxx(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select ydxh,ydsm,clwh,ydyy,ydrq,(select ydlbmc from dm_ydlb b where a.ydlbm=b.ydlbm) ydlbmc,ydhxjztm xjztm,(case xszt when '��ѧ' then '��ѧ' else 'δ��ѧ' end) sfxx from bks_xjydxx a where ydxh=(select max(ydxh) from bks_xjydxx c where xh=?)";
		return dao.getMap(sql, new String[]{xh}, new String[]{"ydxh","ydsm","clwh","ydyy","ydrq","ydlbmc","xjztm","sfxx"});
	}
	
	/**
	 * ��ѯѧ��״̬�б�����ά���е�����ѧ��״̬��
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getXjztList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct zxdmmc from dm_zju_xjzt";
		return dao.getList(sql, new String[]{}, new String[]{"zxdmmc"});
	}
	
	/**
	 * ��ѯУ����Ϣ�б�����ά���е�����У����Ϣ��
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getYxdmList(){
		DAO dao = DAO.getInstance();
		String sql = "select dm,xqmc from dm_zju_xq";
		return dao.getList(sql, new String[]{}, new String[]{"dm","xqmc"});
	}
	/**
	 * ��ȡ��ѧ��״̬(ѧ��״̬�����к���'��'�ļ�¼)
	 * @return HashMap<String, String>
	 * */
	public static HashMap<String, String> getYxj(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct zxdmmc from dm_zju_xjzt where zxdmmc like '��%'";
		return dao.getMap(sql, new String[]{}, new String[]{"zxdmmc"});
	}
	
	/**
	 * ��ѯѧ��ѧϰ������Ϣ������洢��
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
	 * ��ѯѧ��ѧϰ������Ϣ(����洢)
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
	 * ��ѯѧ������ϵ��Ϣ
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
	 * ��ȡѧ��������Ϣ
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
		
		// ---------------------------͵��~~~~~  2012.11.30 qlj ����Ա����������Ϣ begin-------------------------
		sql.append(" select b.zgh,d.xm,d.lxdh from view_xsjbxx a left join fdybjb b ");
		sql.append(" on a.bjdm=b.bjdm   left join fdyxxb d on b.zgh=d.zgh  ");
		sql.append(" where a.xh=? and b.zgh is not null ");
		
		List<HashMap<String,String>>fdyxxList=dao.getList(sql.toString(), new String[]{xh},new String[]{"zgh","xm","lxdh"} );
		
		StringBuilder fdyxxInfo = new StringBuilder("<table border='0'>");
		if(fdyxxList!=null && fdyxxList.size()>0){
			for(int i=0;i<fdyxxList.size();i++){
				HashMap<String,String>fdyxxMap=fdyxxList.get(i);
				fdyxxInfo.append("<tr>");
				fdyxxInfo.append("<th style='width:50px'>ְ����</th>");
				fdyxxInfo.append("<td style='width:100px'>"+(Base.isNull(fdyxxMap.get("zgh"))?"":fdyxxMap.get("zgh"))+"</td>");
				fdyxxInfo.append("<th style='width:100px'>����</th>");
				fdyxxInfo.append("<td style='width:100px'>"+(Base.isNull(fdyxxMap.get("xm"))?"":fdyxxMap.get("xm"))+"</td>");
				fdyxxInfo.append("<th style='width:100px'>��ϵ�绰</th>");
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
				bzrxxInfo.append("<th style='width:50px'>ְ����</th>");
				bzrxxInfo.append("<td style='width:100px'>"+(Base.isNull(bzrxxMap.get("zgh"))?"":bzrxxMap.get("zgh"))+"</td>");
				bzrxxInfo.append("<th style='width:100px'>����</th>");
				bzrxxInfo.append("<td style='width:100px'>"+(Base.isNull(bzrxxMap.get("xm"))?"":bzrxxMap.get("xm"))+"</td>");
				bzrxxInfo.append("<th style='width:100px'>��ϵ�绰</th>");
				bzrxxInfo.append("<td style='width:100px'>"+(Base.isNull(bzrxxMap.get("lxdh"))?"":bzrxxMap.get("lxdh"))+"</td>");
				bzrxxInfo.append("</tr>");
			}
		}
		bzrxxInfo.append("</table>");
		map.put("bzrxx", bzrxxInfo.toString());
		// ---------------------------͵��~~~~~  2012.11.30 qlj end-------------------------
		
		return map;
	}
	
	
	/**
	 * ���ѧ����չ�ֶ�
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
	 * �������ѧ����¼
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
	 * �Զ����ֶ�ѧ����Ϣ�жϷǿ��ֶ��Ƿ�ά��
	 * author qlj
	 */
	public boolean checkFkzd(){
		DAO dao=DAO.getInstance();
		String sql=" select count(1) num from xg_view_xsxx_xsqzd where (zd='xydm' or zd='zydm' or zd='bjdm' or zd='xh' or zd='nj') and sfqy='��' ";
		String []num=dao.getOneRs(sql, new String[]{}, new String[]{"num"});
		if(num[0].equalsIgnoreCase("5")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ��ѯ���п������б�����ά���е��������п����ͣ�
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
	 * ��ȡ�ۺ���Ŀ�ֶ�
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

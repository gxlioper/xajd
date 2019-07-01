package xgxt.studentInfo.zgkd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й���ҵ��ѧѧ����ϢDAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-03</p>
 */
public class XsxxZgkdDAO extends DAO{
	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * ��ȡѧ����Ϣ���ֶ��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getXsxxzdList(String xxdm){
		//�й����ѧ����Ϣ�������ֶ�
		String[] colCN = null;	
		String[] columns = null;
		try {
			columns = getArray("select zdmc from drb where xxdm=? and zdssb='xsxxb' order by to_number(xsxh)", new String[]{xxdm}, "zdmc");
			if(columns == null || columns.length<1){
				//ͨ��
				columns = getArray("select zdmc from drb where xxdm=? and zdssb='xsxxb' order by to_number(xsxh)", new String[]{"public_xsxxb"}, "zdmc");
			}
			colCN = getColumnNameCN(columns, "xsxxb");
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return arrayToList(columns, colCN);
	}
		
	/**
	 * ��ȡ�û����޸ĵ��ֶ�
	 * @param yhjs
	 * @return String[]
	 * */
	public String[] getZdinfo(String yhjs){
		String[] col = null;
		try {
			col =  getArray("select distinct zdm from xsxx_qxfpb where yhjs=? and zdm<>'xh'", new String[]{yhjs}, "zdm");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return col;
	}
	
	/**
	 * ���ݱ����ƻ�ȡ�û����޸ĵ�ѧ����Ϣ�ֶ�
	 * @param yhjs
	 * @return String[]
	 * */
	public String[] getZdInfoByTab(String yhjs,String tableName){
		String[] col = null;		
		try {
			//�����ѯ���ֶ���û��ѧ�ŵ�
			col =  getArray("select distinct zdm from xsxx_qxfpb where yhjs=? and zdssb=? and zdm<>'xh'", new String[]{yhjs,tableName}, "zdm");
			if("student".equalsIgnoreCase(yhjs)){
				col =  getArray("select distinct zdm from xsxx_qxfpb where yhjs=? and zdssb=? and zdm<>'xydm' and zdm<>'zydm' and zdm<>'bjdm' and zdm<>'xh'", new String[]{yhjs,tableName}, "zdm");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return col;
	}
	
	/**
	 * �ж�ѧ������Ϣ�Ƿ����
	 * @param xh
	 * @param tableName
	 * @return boolean
	 * */
	public boolean isExists(String xh, String tableName){
		String sql = "select count(*)count from " + tableName + " where xh=?";
		int count = Integer.parseInt(getOneRs(sql, new String[]{xh}, "count"));
		
		return count>0 ? true : false;
	}
	
	/**
	 * ��ȡѧ����ͥ��Ϣ���ֶ��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getJtxxzdList(String xxdm){
		String sql = "select distinct zdmc,xsxh from drb where xxdm=? and zdssb='xsfzxxb' order by to_number(xsxh)";
		String[] jtxx = null;
		String[] colCN = null;
		try {
			jtxx = getArray(sql, new String[]{xxdm}, "zdmc");
			if(jtxx == null || jtxx.length<1){
				jtxx = getArray(sql,new String[]{"public_xsfzxxb"}, "zdmc");
			}
			colCN = getColumnNameCN(jtxx, "xsfzxxb");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return arrayToList(jtxx, colCN);
	}
	                            
	
	/**
	 * ��ȡ�û��б�
	 * @param String xxdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getYhList(String xxdm){
		//�û���ɫ
		String[] yhm = {"xx","xy","student"};
		String[] zwm = {"ѧУ",Base.YXPZXY_KEY,"ѧ��"};
		if(!(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) 
				|| Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm))){
			yhm = new String[]{"student"};
			zwm = new String[]{"ѧ��"};
		}
		return arrayToList(yhm, zwm);
	}
	
	/**
	 * �����û���ɫ��ȡ�û���ά����ѧ����Ϣ�ֶ��б�
	 * @param yh
	 * @param zdssb
	 * @return List
	 * */
	public List<HashMap<String, String>> getWhzdByYh(String yh,String zdssb){
		//zdssb���������Ǽ�ͥ��Ϣ����ѧ��������Ϣ
		String sql = "select distinct zdm en,zdzwmc cn from xsxx_qxfpb where yhjs=? and zdssb=?";
		return getList(sql, new String[]{yh,zdssb}, new String[]{"en","cn"});
	}
	
	/**
	 * �����û���ɫ��ȡ�û������ѧ����Ϣ�ֶ��б�
	 * @param yh
	 * @param zdssb
	 * @return List
	 * */
	public List<HashMap<String, String>> getBtzdByYh(String yh,String zdssb){
		//zdssb���������Ǽ�ͥ��Ϣ����ѧ��������Ϣ
		String sql = "select distinct zdm en,zdzwmc cn from xsxx_btzdfpb where yhjs=? and zdssb=?";
		return getList(sql, new String[]{yh,zdssb}, new String[]{"en","cn"});
	}
	
	
	/**
	 * ����ѧ����Ϣ�޸�Ȩ����Ϣ
	 * @param model
	 * @return boolean
	 * */
	public boolean savePower(XsxxZgkdForm model,HttpServletRequest request){
		boolean flag = false;
		DAO dao = DAO.getInstance();
//		String tableName = "xsxx_qxfpb";
		String yhjs = model.getYhjs();
		String xsxxzd = model.getXsxxzd();
		String jtxxzd = model.getJtxxzd();
		
		try {
			if(xsxxzd != null && !"".equalsIgnoreCase(xsxxzd)){
				//����ѧ��������Ϣ�����Ȩ����Ϣ
				String[] xsxx = xsxxzd.split("!!");
				String[] colCN = getColumnNameCN(xsxx, "xsxxb");	
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
					String [] sqlMap = new String [xsxx.length]; 
					if(flag){
						for(int i=0; i<xsxx.length; i++){
							String sqlTmp = "insert into xsxx_qxfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+xsxx[i]+"','"+colCN[i]+"','xsxxb')";
							sqlMap[i]=sqlTmp;
						}
						dao.runBatch(sqlMap);
					}				
			}else{
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
			}
			if(jtxxzd != null && !"".equalsIgnoreCase(jtxxzd)){
				//����ѧ����ͥ��Ϣ�����Ȩ����Ϣ
				String[] jtxx = jtxxzd.split("!!");
				String[] colCN = getColumnNameCN(jtxx, "xsfzxxb");
//				flag = StandardOperation.delete(tableName, "yhjs||zdssb", yhjs+"xsfzxxb", request);
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
				String [] sqlMap = new String [jtxx.length]; 
				if(flag){
					for(int i=0; i<jtxx.length; i++){
//						flag = StandardOperation.insert(tableName, new String[]{"yhjs","zdm","zdzwmc","zdssb"}, 
//								new String[]{yhjs,jtxx[i],colCN[i],"xsfzxxb"}, request);
						String sqlTmp = "insert into xsxx_qxfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+jtxx[i]+"','"+colCN[i]+"','xsfzxxb')";
						sqlMap[i]=sqlTmp;
					}
					dao.runBatch(sqlMap);
				}
			}else{
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ��ѯ��ѧ����ѧ����ά�����ֶ�
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs){
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
	 * ��ѯ��ѧ����ѧ����ά�����ֶ�
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs,String tableName){
		String result = "";
		String sql = "select distinct zdm from xsxx_qxfpb where yhjs=? and zdm<>'xh' and zdssb=?";
		String[] zdList = null;
		try {
			zdList = getArray(sql, new String[]{yhjs,tableName}, "zdm");
		    for(int i=0; i<zdList.length; i++){
		    	result += zdList[i] + "!!";
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * ��ѯѧ��������Ϣ�ͼ�ͥ��Ϣ
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuAndFamily(String xh,String yhjs,String xxdm){
		HashMap<String, String> rs = null;
		int count = 0;
		String[] outputValue = null;
		String sql = "select count(*) count from xsxx_xsxgxxb where xh=?";
		count = Integer.parseInt(getOneRs(sql, new String[]{xh}, "count"));
		//��ѯѧ����Ϣ�ͼ�ͥ��Ϣ
		try {
			outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsfzxxb'", new String[]{xxdm}, "zdmc");
			sql = "select * from xsfzxxb where xh=?";
			rs = getMap(sql, new String[]{xh}, outputValue);
			
			outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsxxb'", new String[]{xxdm}, "zdmc");			
			sql = "select * from view_xsxxb where xh=?";
			rs.putAll(getMap(sql, new String[]{xh}, outputValue));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(yhjs !=null && yhjs.equalsIgnoreCase("student")){
			if(count>0){
				//��Ϣ�޸ĺ�δ���ͨ��	
				try {
					outputValue = getArray("select distinct zdm from xsxx_qxfpb where yhjs=?", new String[]{yhjs}, "zdm");
					sql = "select * from xsxx_xsxgxxb where xh=?";
					rs.putAll(getMap(sql, new String[]{xh}, outputValue));
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		}
		return rs;
	}
	
	/**
	 * �ж��Ƿ����޸�ѧ����Ϣ��ʱ�����
	 * @return String
	 * */
	public String isSqqjFlag(){
		String sql = "select issz from xsxxxgsjb";
		String isSz = getOneRs(sql, new String[]{}, "issz");
		if("no".equals(isSz)){
			return "0";
		}
		
		sql = "select count(*)count from xsxxxgsjb where kssj<to_char(sysdate,'yyyy-mm-ddhh24miss') and jssj>to_char(sysdate,'yyyy-mm-ddhh24miss') and issz='yes'";
		int count = Integer.parseInt(getOneRs(sql, new String[]{}, "count"));
		if(count>0){
			return "0";
		}
		
		return "1";
	}
	
	/**
	 * ��ѯѧ���޸ĺ�ĸ�����Ϣ
	 * 
	 * @throws Exception
	 */
	public List<String[]> selectModiStuinfo(XsxxZgkdForm model,
			HttpServletRequest request) throws Exception {
		
		// ------------�޸�ʱ�䣺2012.4.9 ---------------
		// ------------�޸��ˣ� ������ ---------------
		// ------------�޸����ݣ��Ż���ѯ�ٶ�,BUG�޸�-----------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select rownum r,a.* from(select  ");
		sql.append("  xh,xm,xb,a.nj,a.xydm,a.zydm,a.bjdm,");
		sql.append("  xz,shjg,fdysh,b.zymc,b.bjmc");
		sql.append("  from (select distinct a.xh,");
		sql.append("  nvl(a.xm,b.xm) xm,");
		sql.append("  nvl(a.xb,b.xb) xb,");
		sql.append("  nvl(a.nj,b.nj) nj,");
		sql.append("  nvl(a.xydm,b.xydm) xydm,");
		sql.append("  nvl(a.zydm,b.zydm) zydm,");
		sql.append("  nvl(a.bjdm,b.bjdm) bjdm,");
		sql.append("  a.xz,a.fdysh,a.shjg");
		sql.append("  from xsxx_xsxgxxb a left join view_xsjbxx b on a.xh = b.xh)a left join view_njxyzybj b");
		sql.append("  on a.bjdm=b.bjdm)a 	");
		
		String[] outputValue = {"xh","xm","xb","nj","zymc","bjmc","xz","shjg"};		
		if(Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			outputValue = new String[]{"xh","xm","xb","nj","zymc","bjmc","fdysh","shjg"};
		}
		
		// �߼���ѯ
		String superSearch = Base.getSuperSearch();
		String whereSql = "";
		String[] inputValue = null;
		
		if ("yes".equalsIgnoreCase(superSearch)) {
			
			SearchService searchService = new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request,
					"a", "xydm", "bjdm");

			// �߼���ѯ
			whereSql = " where 1 = 1 ";
			whereSql += SearchService.getSearchTj(model.getSearchModel());
			whereSql += searchTjByUser;
			inputValue = SearchService.getTjInput(model.getSearchModel());
		} else {
			whereSql = getWhereSql(model).toString();
			inputValue = values != null ? values.toArray(new String[0])
					: new String[] {};
		}
		
		//��ѯ�ܼ�¼
		String count = getOneRs("select count(*) num from ("+ sql + whereSql+")", inputValue, "num");
		model.getPages().setMaxRecord(Integer.parseInt(count));
		//��ҳ
		String sqlStr="";
		sqlStr="select a.* from(" + sql + whereSql + " order by bjdm)a where r>" + model.getPages().getStart() + "and r<=" 
			  + (model.getPages().getStart()+model.getPages().getPageSize());
		System.out.println(sqlStr);
		return rsToVator(sqlStr, inputValue, outputValue);
	}
	
	/**
	 * ѧ����Ϣ�޸Ĳ�ѯ������ֵ
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(XsxxZgkdForm model) throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xh = model.getXh();
		String xm = DealString.toGBK(model.getXm());
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj= model.getNj();
		String fdysh = model.getFdysh();
		
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and a.xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and a.zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and a.bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and a.xh like '%'||?||'%'");
			values.add(xh);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and a.nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and a.xm like '%'||?||'%'");
			values.add(xm);
		}
		if (!StringUtils.isNull(fdysh)) {
			whereSql.append(" and a.fdysh = ?");
			values.add(fdysh);
		}
		return whereSql;
	}
	
	/**
	 * ��ȡ��ѯ�����ͷ 
	 * @return List
	 * */
	public List<HashMap<String, String>> getTopTr(){
		String tabName = "xsxx_xsxgxxb";
		String[] outputValue = {"xh","xm","xb","nj","רҵ����","�༶����","xz","shjg"};
		if(Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			outputValue = new String[]{"xh","xm","xb","nj","רҵ����","�༶����","fdysh","shjg"};
		}
		String[] outputCN = getColumnNameCN(outputValue, tabName);
		return arrayToList(outputValue,outputCN);
	}
	
	/**
	 * ��ȡ�޸ĺ��ֵ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNewValue(String xh){
		HashMap<String, String> rs = new HashMap<String, String>();
		String yhjs = "student";
		String[] outputValue = getZdinfo(yhjs);		
		//�������Ӧ�����Ʋ�ѯ����
		String[] dmArr = {"mz", "zzmm", "jtcy1_mz", "jtcy1_zzmm", "jtcy2_mz", "jtcy2_zzmm", "jtcy3_mz", "jtcy3_zzmm", 
						  "pycc", "kslb", "rxfs", "pyfs", "jtcy1_gxmc", "jtcy2_gxmc", "jtcy3_gxmc"};
		String[] mcArr = {"yhmc"};
		for(String column : dmArr){
			if(StringUtils.contains(outputValue, new String[]{column})){
				mcArr = StringUtils.joinStrArr(mcArr, new String[]{column+"mc"});
			}
		}
		outputValue = StringUtils.joinStrArr(outputValue,mcArr);
		
		String sql = "select * from view_xsxx_xsxgxxb where xh=?";
		
		//��ȡѧ����Ϣ��ԭʼֵ	
		rs.putAll(getOldValue(xh));
		//�޸ĺ����ֵ
		rs = getMap(sql, new String[]{xh}, outputValue);
		
		return rs;
	}
	
	
	/**
	 * ��ȡ�޸ĺ��ֵ
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getOldValue(String xh){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		HashMap<String, String> rs = new HashMap<String, String>();
		String[] outputValue = getColumnName("select * from view_xsxxb where 1=2");		
		String sql = "select a.*,(select distinct pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm)pyccmc," 
					+"(select distinct kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm)kslbmc," 
					+"(select distinct rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm)rxfsmc," 
					+"(select distinct pyfsmc from xg_xsxx_pyfsdmb c where a.pyfs=c.pyfsdm)pyfsmc from view_xsxxb a where xh=?";
		
		//ѧ��ԭʼ������Ϣ		
		for(int i=0; i<outputValue.length; i++){
			outputValue[i] = outputValue[i].toLowerCase();
		}
		rs = getMapNotOut(sql, new String[]{xh});
		
		//ѧ��ԭʼ��ͥ��Ϣ
		outputValue = getColumnName("select * from view_xsjtxx where 1=2");
		sql = "select * from view_xsjtxx where xh=?";		
		for(int i=0; i<outputValue.length; i++){
			outputValue[i] = outputValue[i].toLowerCase();
		}
		rs.putAll(getMap(sql, new String[]{xh}, outputValue));
		
		//δ�޸ĵ��ֶ�ȡԭʼֵ
		String yhjs = "student";
		String[] fpzd = getZdinfo(yhjs);
		//�����ֶ�
		String[] syzd = getColumnName(Excel2Oracle.getSqlColumn("","xsxxb"));//ѧ����Ϣ�ֶ�
		syzd = StringUtils.joinStrArr(syzd,getColumnName(Excel2Oracle.getSqlColumn("","xsfzxxb")));//��ͥ��Ϣ�ֶ�
		for(int i=0; i<syzd.length; i++){
			syzd[i] = syzd[i].toLowerCase();
		}
		
		//��Ҫ��ѯԭʼֵ���ǳ��˷�����ֶ���Ϊ�������ֶ�
		List<String> syzdList = new ArrayList<String>(Arrays.asList(syzd));
		//syzdList.removeAll(Arrays.asList(fpzd));
		syzdList.addAll(Arrays.asList(fpzd));
	    syzd = syzdList.toArray(new String[]{});
		//syzd = fpzd;
	    
	    //ѧԺ��רҵ���༶��ʱ�����˿��޸ģ�ѧ��Ҳ�����޸�
	    syzd = StringUtils.joinStrArr(syzd, new String[]{"xydm", "zydm", "bjdm"});
	    
	    syzd = StringUtils.joinStrArr(syzd, new String[]{"xymc", "zymc", "bjmc","yhmc"});
	    //������д�����ֶΣ�������Ҳ��ѯ����
		String[] dmArr = {"mz", "zzmm", "jtcy1_mz", "jtcy1_zzmm", "jtcy2_mz", "jtcy2_zzmm", "jtcy3_mz", "jtcy3_zzmm", 
				"pycc", "kslb", "rxfs", "pyfs","jtcy1_gx", "jtcy2_gx", "jtcy3gx"};
		String[] mcArr = new String[dmArr.length];
		
		for(String column : dmArr){
			if(StringUtils.contains(syzd, new String[]{column})){
				mcArr = StringUtils.joinStrArr(mcArr, new String[]{column+"mc"});
			}
		}
		syzd = StringUtils.joinStrArr(syzd,mcArr);
		//ֵ����
	    for(String column : syzd){
	    	resultMap.put(column, rs.get(column));
	    }
		return resultMap;
	}
	
	
	/**
	 * �й���ҵ��ѧ��ȡѧ�������ѵȼ�
	 * @param xh
	 * @return String
	 * */
	public String getPksdj(String xh){
		String nd = Base.currNd;
		String tempSql = "select XXSHJG from ZGKYDX_KNSXX where xh=? and nd=?";
		return getOneRs(tempSql, new String[]{xh,nd}, "xxshjg");
	}
	
	/**
	 * �ж�ѧУ�Ƿ����ͨ��
	 * @param xh
	 * @return String
	 * */
	public String getXxshjg(String xh){
		return getOneRs("select shjg from xsxx_xsxgxxb where xh=?", new String[]{xh}, "shjg");
	}
	
	/**
	 * �����޸ĵ��ֶ�
	 * */
	public boolean saveXgzdxx(String xh,String xgzd,User user){
		String[] sqlArr = new String[2];
		sqlArr[0] = "delete from xg_xsxx_xsxgzdb where xh='" +DealString.replaceImmitStr(xh)+"'";
		sqlArr[1] = "insert into xg_xsxx_xsxgzdb(xh,xgzd) values('" + DealString.replaceImmitStr(xh) 
					+ "','" + DealString.replaceImmitStr(xgzd) + "')";
		boolean flag = false;
		try{
			int[] result = runBatch(sqlArr);
			flag = checkBatch(result);
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * ��ѯ�޸��ֶ���Ϣ
	 * */
	public HashMap<String, String> selectXgzdxx(String xh){
		String sql = "select * from xg_xsxx_xsxgzdb where xh=?";
		return getMap(sql, new String[]{xh}, new String[]{"xh", "xgzd"});		
	}
}


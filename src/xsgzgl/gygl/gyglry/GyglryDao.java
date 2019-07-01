package xsgzgl.gygl.gyglry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

public class GyglryDao extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	/**
	 * ��ȡ��Ԣ������Ա��Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGyglryList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		String searchTjQx;
		
		StringBuilder sql = new StringBuilder();
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
			sql.append("select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a  where 1=1 ");
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
			if(Base.xxdm.equals("14008")){//������Ͽ���Ի�
				sql.append(" select d.* from (select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a ");
				sql.append(" where exists (select 1 from view_xg_gygl_new_cwxx b where a.lddm = b.lddm and a.qsh = b.qsh and b.xh is not null");
				sql.append(searchTjQx);
				sql.append(")) d");
			}else{		
				sql.append("select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a  where 1=1 ");
			}
		}
		String[] outPutString=new String[]{};
		if("10466".equals(Base.xxdm)){//����ũҵ��ѧ
			outPutString=new String[]{"pkValue","ldmc","chmc","qsh","gllx","xb","xh","xm","sjhm","bz"};				
		}else {
			outPutString=new String[]{"pkValue","ldmc","chmc","qsh","gllx","xb","xh","xm","sjhm","qsdh","bz"};				
		}
		return CommonQueryDAO.commonQuery(sql.toString(),searchTj,inputV,outPutString,model);
	}
	
	/**
	 * ��ȡ��סѧ����Ϣ
	 * @param model
	 * @return
	 */
	public List<String[]> getRzxsxxList(GyglryForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		
//		sql.append("select rownum r,a.* from (select a.xh pkValue,a.*,b.xm," +
//				"(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
//				"from view_xg_gygl_new_cwxx a inner join view_xsjbxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where " +
//				"lddm=? ");
		
		sql.append("select rownum r,a.* from (select a.xh pkValue,a.*,b.xm," +
				"(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
				"from view_xg_gygl_new_cwxx a inner join "+Base.xsxxb+" b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where " +
				"lddm=? ");
		
		ArrayList<String> inputV=new ArrayList<String>();
		inputV.add(model.getLddm());
		
		if(!Base.isNull(model.getCh())){
			sql.append(" and ch=? ");
			inputV.add(model.getCh());
		}
		if(!Base.isNull(model.getQsh())){
			sql.append(" and qsh=? ");
			inputV.add(model.getQsh());
		}
		if(!Base.isNull(model.getXm())){
			sql.append(" and ( xh like '%'||?||'%' or xm like '%'||?||'%')");
			inputV.add(model.getXm());
			inputV.add(model.getXm());
		}
		String[] output = new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "bjmc","xh", "xm","sfbl"};
		if("xydm".equals(GyglNewInit.CWFPDX)){
			output = new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "xh", "xm","sfbl"};
		}
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), "" , inputV.toArray(new String[]{}), output, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��ȡ����б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getChList(GyglryForm model){
		String sql="select distinct ch,(case when ch='#' then '#' when to_number(ch)>-1 then ch else 'B'||abs(ch) end) chmc " +
				"from xg_gygl_new_qsxxb where lddm=? order by to_number(ch)";
		return dao.getListNotOut(sql, new String[]{model.getLddm()});
	}
	
	/**
	 * ��ȡ���Һ��б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getQshList(GyglryForm model){
		String[] input=null;
		String sql="select qsh from xg_gygl_new_qsxxb where lddm=?";
		if(!Base.isNull(model.getCh())){
			sql+=" and ch=?";
			input=new String[]{model.getLddm(),model.getCh()};
		}else{
			input=new String[]{model.getLddm()};
		}
		sql+=" order by qsh";
		return dao.getListNotOut(sql, input);
	}
	/**
	 * ��Ԣ������Ա����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryFp(GyglryForm model,Boolean flag) throws Exception{
		String lddm=model.getLddm();
		String ch=Base.isNull(model.getFp_ch())?"#":model.getFp_ch();
		String qsh=Base.isNull(model.getFp_qsh())?"#":model.getFp_qsh();
		String old_xh=model.getOld_xh();
		
		List<String> sqls=new ArrayList<String>();
		sqls.add(getGYglryTsyqUpdateSql(model));
		String sql=null;
		if(flag||"gx".equals(model.getCzfs())){//��¥�����㳤����Ϊһ�˻�������ҳ��������ʽΪ����ʱ��ֱ���滻
			sql="select xh from xg_gygl_new_gyglryb where nvl(rzzt,' ')<>'����' and lddm=? and ch=? and qsh=?";
			String[] xhs=dao.getArray(sql, new String[]{lddm,ch,qsh}, "xh");
			if(xhs==null||xhs.length==0){
				sqls.add(getGyglryInsertSql(model));
			}else {
				if(xhs.length==1){
					old_xh=xhs[0];
				}
				sqls.add("update xg_gygl_new_gyglryb set rzzt='����',rzjsrq='"+model.getRzksrq()+"' where " +
						"nvl(rzzt,' ')<>'����' and lddm='"+lddm+"' and ch='"+ch+"' and qsh='"+qsh+"' and xh='"+old_xh+"'");
				sqls.add(getGyglryInsertSql(model));
			}
			
		}else{
			sqls.add(getGyglryInsertSql(model));
		}
		CommDAO dao=new CommDAO();
		boolean b=dao.saveArrDate(sqls.toArray(new String[]{}));
		if(b){
			return "�����ɹ���";
		}else{
			return "����ʧ�ܣ�";
		}
	}
	
	/**
	 * ��ȡ��Ԣ������Ա����sql
	 * @param model
	 * @return
	 */
	private String getGyglryInsertSql(GyglryForm model){
		String lddm=model.getLddm();
		String ch=Base.isNull(model.getFp_ch())?"#":model.getFp_ch();
		String qsh=Base.isNull(model.getFp_qsh())?"#":model.getFp_qsh();
		String lxdh=Base.chgNull(model.getLxdh(), "", 0);
		String bz=Base.chgNull(model.getBz(), "", 0);
		String sql="insert into xg_gygl_new_gyglryb(xh,lddm,ch,qsh,rzzt,rzksrq,lxdh,bz) " +
				"values('"+model.getXh()+"','"+lddm+"','"+ch+"','"+qsh+"','����','"+model.getRzksrq()+"'," +
						"'"+lxdh+"','"+bz+"')";
		return sql;
	}
	
	/**
	 * ��ȥ��Ԣ������Ա���޻��춯���޸����
	 * @param model
	 * @return
	 */
	private String getGYglryTsyqUpdateSql(GyglryForm model){
		String sql="";
		if(Base.isNull(model.getFp_ch())){//¥��
			sql="update xg_gygl_new_gyglryb a set rzzt='����' where rzzt='����' and " +
					"not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.xh=b.xh)";
		}else if(Base.isNull(model.getFp_qsh())){//�㳤
			sql="update xg_gygl_new_gyglryb a set rzzt='����' where rzzt='����' and ch<>'#' and " +
					"not exists (select 1 from xg_gygl_new_cwxxb b left join xg_gygl_new_qsxxb c on b.lddm=c.lddm and b.qsh=c.qsh " +
					"where a.lddm=c.lddm and a.xh=b.xh)";
		}else{//���ҳ�
			sql="update xg_gygl_new_gyglryb a set rzzt='����' where rzzt='����' and ch<>'#' and qsh<>'#' and " +
					"not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.xh=b.xh)";
		}
		return sql;
	}
	
	/**
	 * ��Ԣ������Աȡ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryQxfp(GyglryForm model) throws Exception{
		String lddm=model.getLddm();
		String ch=Base.isNull(model.getFp_ch())?"#":model.getFp_ch();
		String qsh=Base.isNull(model.getFp_qsh())?"#":model.getFp_qsh();
		String old_xh=model.getOld_xh();
		
		String sql="update xg_gygl_new_gyglryb set rzzt='����',rzjsrq=? where " +
		"nvl(rzzt,' ')<>'����' and lddm=? and ch=? and qsh=? and xh=?";
		boolean b=dao.runUpdate(sql,new String[]{model.getRzjsrq(),lddm,ch,qsh,old_xh});
		dao.runUpdate(getGYglryTsyqUpdateSql(model), new String[]{});
		if(b){
			return "�����ɹ���";
		}else{
			return "����ʧ�ܣ�";
		}
	}

	/**
	 * ���ҳ�ά���Զ��嵼������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGyglryExportList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String[] outPutString=new String[]{"pkValue","ldmc","chmc","qsh","gllx","xb","xh","xm","lxdh","qsdh","bz"};
		String sql="select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a where 1=1 ";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		String searchTjQx;
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
			searchTj+=searchTjQx;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
			if(Base.xxdm.equals("14008")){//������Ͽ���Ի�
				searchTjQx = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
				StringBuilder sqll = new StringBuilder();
				sqll.append(" select d.* from (select rownum r,a.lddm||'!$$!'||a.ch||'!$$!'||a.qsh pkValue,a.* from xg_view_gygl_new_gyglryb a ");
				sqll.append(" where exists (select 1 from view_xg_gygl_new_cwxx b where a.lddm = b.lddm and a.qsh = b.qsh and b.xh is not null");
				sqll.append(searchTjQx);
				sqll.append(")) d");
				sql = sqll.toString();
			}else{
				searchTj+=searchTjQx;
			}
		}
		
		return CommonQueryDAO.commonQueryforExportList(sql,searchTj,inputV,outPutString,model);
	}
	
	/**
	 * ��ȡ��Ԣ������Ա¥����Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLzInfoList(String lddm) throws Exception{
		String sql="select * from xg_gygl_new_gyglryb a  where rzzt='����' and lddm=? and ch='#' and qsh='#'";
		return dao.getListNotOut(sql, new String[]{lddm});
	}
	
	/**
	 * ��ȡ��Ԣ������Ա�㳤��Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCzInfoList(String lddm,String ch) throws Exception{
		String sql="select * from xg_gygl_new_gyglryb a  where rzzt='����' and lddm=? and ch=? and qsh='#'";
		return dao.getListNotOut(sql, new String[]{lddm,ch});
	}
	
	/** 
	 * @����:��ȡ��Ԣ������Ա���ҳ���Ϣ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-31 ����10:48:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getQszInfo(String lddm,String qsh) {
		String sql="select b.sjhm qszlxdh,b.xm qszxm from xg_gygl_new_gyglryb a left join view_xsjbxx b on a.xh=b.xh  where a.rzzt='����' and a.lddm=? and a.qsh=? ";
		return dao.getMapNotOut(sql, new String[]{lddm,qsh});
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����04:26:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDrData(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_gyglryb(xh,lddm,ch,qsh,rzzt,rzksrq,bz,lxdh) values(?,?,?,?,'����',?,?,?)");
		return dao.runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @����: ��������(��ԭ�����ڵ����ݸ��ǵ�)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-24 ����01:44:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDrDataForUpdate(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_gygl_new_gyglryb set rzzt='����',rzjsrq= to_char(sysdate,'yyyy-mm-dd') where  lddm = ? and ch = ? and qsh = ?");
		return dao.runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����05:26:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfExist(String xh,String lddm,String qsh,String ch){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_gygl_new_gyglryb where xh = ? and lddm = ? and qsh = ? and ch = ?");
		String rs = dao.getOneRs(sql.toString(), new String[]{xh,lddm,qsh,ch}, "cnt");
		return "0".equals(rs) ? false :true;
	}
	
	/**
	 * 
	 * @����: ѧ���Ƿ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����02:23:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhIsExist(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xsxxb t where t.xh = ? and t.sfzx = '��'");
		String rs = dao.getOneRs(sql.toString(),new String[]{xh}, "cnt");
		return "0".equals(rs) ? false : true;
	}
	
	/**
	 * 
	 * @����: ��֤¥�������Ƿ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����03:27:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String  checkLdmcIfExist(String ldmc,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct lddm from view_xg_gygl_new_cwxx where ldmc = ? and xh = ?");
		return  dao.getOneRs(sql.toString(), new String[]{ldmc,xh}, "lddm");
	}
	
	/**
	 * 
	 * @����: ��֤����Ƿ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����03:42:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkChIfExist(String lddm,String ch,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from view_xg_gygl_new_cwxx where lddm = ? and ch = ? and xh =?");
		String rs = dao.getOneRs(sql.toString(), new String[]{lddm,ch,xh}, "cnt");
		return "0".equals(rs) ? false : true;
	}
	
	/**
	 * 
	 * @����: ���Һ��Ƿ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����05:01:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkQshIfExist(String lddm,String ch,String qsh,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from view_xg_gygl_new_cwxx where lddm = ? and ch = ? and qsh = ? and xh =?");
		String rs = dao.getOneRs(sql.toString(), new String[]{lddm,ch,qsh,xh}, "cnt");
		return "0".equals(rs)? false:true;
	}
	
}

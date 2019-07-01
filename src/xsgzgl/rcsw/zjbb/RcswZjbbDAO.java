package xsgzgl.rcsw.zjbb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-19 ����10:02:11</p>
 */
public class RcswZjbbDAO extends BasicDAO{
	
	
	// ---------------------֤���������� begin -------------------	
	/**
	 * ֤���������ý����
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbszList(RcswZjbbForm myForm,BasicModel model) throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		BasicService basicService=new BasicService();
		
		String []colList=new String[] {"id","zjmc","lcmc","num","sfksc"};
		
		String[]queryList={"sfksc"};
		
		String[]queryLikeList={"zjmc"};
		
		mQuery.makeQuery(queryList, queryLikeList, myForm);
		
		//====================��������===================================

		
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select a.id,nvl(mc,'��') lcmc,zjmc,nvl(b.num,0)num,  ");
		sql.append(" case when b.num>'0' then '��' else '��' end sfksc  ");
		sql.append(" from (select a.id,b.mc,a.zjmc from xg_rcsw_zjbbszb a left join ");
		sql.append(" xg_xtwh_splc b on a.lcid=b.id)a left join  ");
		sql.append(" (select xmid,count(1)num from  ");
		sql.append(" xg_rcsw_zjbbsqb where sqjg <>'btg'  ");
		sql.append(" and sqjg<>'tg' group by xmid)b on a.id=b.xmid ");
		sql.append(" )a ");

		sql.append(mQuery.getQueryString());
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		
		return list;
	}
	
	// ---------------------֤���������� end -------------------	
	public HashMap<String,String>getBbszInfo(RcswZjbbForm myForm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_rcsw_zjbbszb where id=? ");
		
		return  dao.getMapNotOut(sql.toString(), new String[]{myForm.getXmid()});
		
	}
	
	// ---------------------֤���������� begin -------------------	
	/**
	 * ��ȡ������Ĳ�����Ŀ�б�
	 * @author qlj
	 */
	public List<HashMap<String,String>>getZjbbList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select id,zjmc from xg_rcsw_zjbbszb where sqkg='yes' ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});		
	}
	
	/**
	 * ֤��������˽����
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbsqList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String []colList={"id","zjmc","shzt", "shyj" , "sqsj","sqjg"};
		
		// �û�����
		String userType=user.getUserType();
		
		//====================��������===================================
	
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
	//TODO ��������ʾ
		sql.append(" select a.*,rownum r from (  ");
		sql.append(" select xh,id,sqsj,max(shzt)shzt,zjmc ,sqjg , max(shyj) shyj from (  ");
		sql.append(" select xh,id,sqsj,zjmc,sqjg,to_char(  ");
		sql.append(" WM_CONCAT(mc||'('||nvl(shzt,'�������')||')')over( ");
		sql.append(" partition by id order by xsxh asc ))shzt,to_char(WM_CONCAT(mc || '(' || nvl(shyj, '��') || ')') over(partition by id order by xsxh asc)) shyj from (  ");
		sql.append(" select id,xh, sqsj,sqjg, zjmc, shzt,  xsxh,mc , shyj  from (  ");
		sql.append(" select a.id,a.xh,a.sqsj,a.sqjg,a.zjmc,b.shzt,b.xh xsxh,b.mc,b.shyj from(  ");
		sql.append(" select a.id,a.xh,a.sqsj,a.sqjg,e.lcid,e.zjmc from xg_rcsw_zjbbsqb a,xg_rcsw_zjbbszb e   ");
		sql.append(" where a.xmid=e.id)a left join   ");
		sql.append(" (select b.sqid, b.xtgwid, c.mc, case when b.shzt = 'wsh' then 'δ���' ");
		sql.append(" when b.shzt = 'tg' then 'ͨ��'   ");
		sql.append(" when b.shzt = 'btg' then '��ͨ��'   ");
		sql.append(" when b.shzt = 'xcs' then  '������'  ");
		sql.append(" when b.shzt = 'th' then '�˻�'  end shzt, ");
		sql.append(" d.splc,d.xh,b.shyj  from xg_rcsw_zjbbshb b,  ");
		sql.append(" xg_xtwh_spgw c, xg_xtwh_spbz d  where  ");
		sql.append(" b.xtgwid = c.id  and b.xtgwid = d.spgw)b  ");
		sql.append(" on a.id=b.sqid and a.lcid=b.splc  )) ) ");
		sql.append("  group by xh,id,sqsj,zjmc,sqjg)a ");
				        
		
		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	// ---------------------֤���������� end -------------------			
	
	
	// ---------------------֤��������� begin -------------------			
	/**
	 * ͨ�ò�ѯ����
	 * �������ܣ��÷����Ĳ�ѯ������Դ�ڸ߼���ѯ����֧�ַǸ߼���ѯ
	 * model�еĲ������÷�����Ҫ����user����colList��viewName����������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBbshList(BasicModel model) throws Exception{
		
		DAO dao=DAO.getInstance();
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		HashMap<String,String> nextSpgw =getNextSpMap(model, user);
		
		String []pk=model.getPk();
		
		String []colList={ "id","xh","xm","nj","bjmc","sqsj","shzt","sfsh"};
		
		//====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		// ��Ŀ����
		String xmdm=valueMap.get("xmdm");
		
		String spgw=valueMap.get("spgw");

		tableSql.append(" select a.*,rownum r from( "); 
		tableSql.append(" select a.*,case when b.sqid is not null then "); 
		tableSql.append(" 'disabled' else '' end sfsh from(  "); 
		tableSql.append(" select a.xh pkValue, a.xmid,a.id, "); 
		tableSql.append(" a.xh,a.sqsj,c.xydm, "); 
		tableSql.append(" case when b.shzt='wsh' then 'δ���' ");
		tableSql.append(" when b.shzt='tg' then 'ͨ��' ");
		tableSql.append(" when b.shzt='btg' then '��ͨ��' ");
		tableSql.append(" when b.shzt='xcs' then '������' ");
		tableSql.append(" when b.shzt='th' then '�˻�' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm "); 
		tableSql.append(" from xg_rcsw_zjbbsqb a, xg_rcsw_zjbbshb b, "); 
		tableSql.append(" (select a.*,b.xymc,b.zymc,b.bjmc from "+Base.xsxxb+" ");
		tableSql.append(" a left join view_njxyzybj_all b on a.bjdm=b.bjdm) c ");
		tableSql.append(" where b.xtgwid =? "); 
		tableSql.append(" and a.xmid = ? "); 
		tableSql.append(" and a.id = b.sqid ");
		tableSql.append(" and a.xh = c.xh "); 
		
		HashMap<String,String>higherUpSpgw=getHigherUpSpMap(model, user);
		
		String higherUpgw=higherUpSpgw.get("spgw");
		if(!Base.isNull(higherUpgw)){
			tableSql.append(" and exists( select 1 from xg_rcsw_zjbbshb x where 1=1");
			tableSql.append(" and exists(select 1 from xg_rcsw_zjbbsqb y where x.sqid=y.id and y.xmid='"+xmdm+"' )");
			tableSql.append(" and x.xtgwid='"+higherUpgw+"' and x.sqid=b.sqid and shzt='tg' )");
		}
		
		// --------------------------�¼�����Ƿ������-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select sqid from xg_rcsw_zjbbshb x ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and exists(select 1 from xg_rcsw_zjbbsqb y where x.sqid=y.id and y.xmid=? ) ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.id=b.sqid) a ");
		// --------------------------�¼�����Ƿ������-------------------------------
		
		tableSql.append(query);
		
		// ====================SQLƴװ end================================

		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(tableSql.toString(),"",dao.unionArray(new String[]{spgw,xmdm,nextSpgw.get("spgw"),xmdm},inputV), colList, model);
		
		return list;
	}
	
	/**
	 * ������Ŀ���롢
	 * ������λ��ȡ�ϼ���˸�λ
	 * @author qlj
	 */
	public HashMap<String,String> getHigherUpSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		// ��Ŀ����
		String xmdm = valueMap.get("xmdm");
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh+1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,spgw});
	}
	
	
	/**
	 * ������Ŀ���롢
	 * ������λ��ȡ�¼���˸�λ
	 * @author qlj
	 */
	public HashMap<String,String> getNextSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		// ��Ŀ����
		String xmdm = valueMap.get("xmdm");
		// ������λ
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh-1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,spgw});
	}
	
	/**
	 * ��ȡ��Ŀ�����˸�λ����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getMaxSpxh(RcswZjbbForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		// ��Ŀ����
		String xmid=model.getXmid();
		
		StringBuilder sql=new StringBuilder();
	
		sql.append(" select splc,max(xh)xh ");
		sql.append(" from xg_xtwh_spbz ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_rcsw_zjbbszb where id=? ");
		sql.append(" ) ");
		sql.append(" group by splc");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xmid});
		
	}
	
	/**
	 * ��ʼ�������Ŀ�б�
	 * @author qlj 
	 */
	public List<HashMap<String,String>>getCshXmList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select id xmdm ,zjmc xmmc from xg_rcsw_zjbbszb where lcid<>'no' ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});		
	}
	
	/**
	 * ��ʼ�������Ŀ�б�
	 * @author qlj 
	 */
	public List<HashMap<String,String>>getCshXmList(User user){
		
		String userName=user.getUserName();
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select id xmdm ,zjmc xmmc from xg_rcsw_zjbbszb a where lcid<>'no' ");
		sql.append(" and exists(select 1 from xg_xtwh_spbz b,xg_xtwh_spgwyh c where b.spgw=c.spgw ");
		sql.append(" and a.lcid=b.splc and c.spyh=? )");
		
		return dao.getListNotOut(sql.toString(), new String[]{userName});		
	}
	
	
	/**
	 * �������״̬
	 * @param model
	 * @param user
	 * @author qlj 
	 * @throws Exception
	 */
	// ----------------------������˱����� begin ------------------------------
	
	public boolean resultShzt(RcswZjbbForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
	
		String[] sqid=model.getSqid();
		
		sql.append(" update xg_rcsw_zjbbshb set shzt='wsh'  ");
		sql.append(" where  shzt='th' ");
		sql.append(" and sqid in ( ");
		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append("  ) ");
		
		return dao.runUpdate(sql.toString(),sqid);
	}
	
	/**
	 * �޸����״̬
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateShzt(RcswZjbbForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		String shzt=model.getShzt();
		
		String shyj=model.getShyj();
		
		String userName=user.getUserName();

		String spgw=model.getSpgw();
		
		String[]sqid=model.getSqid();
		
		sql.append(" update xg_rcsw_zjbbshb set shzt=?,shr=?,shsj=?, ");
		sql.append(" shyj=? where  xtgwid=?  ");
		sql.append(" and sqid in ( ");
		
		inputV.add(shzt);
		inputV.add(userName);
		inputV.add(GetTime.getNowTime2());
		inputV.add(shyj);;
		inputV.add(spgw);
	
		
		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(sqid[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), inputV.toArray(new String[]{}),
				"xg_rcsw_zjbbshb",user);
		
	}
	
	/**
	 * ���ʱΪ�˻�
	 * �ϼ�������Ϊ������
	 * @param model
	 * @param user
	 * @author qlj
	 * @throws Exception
	 */
	public boolean updateThzt(RcswZjbbForm model,BasicModel basicModel,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		ArrayList<String>inputV=new ArrayList<String>();
	
		String spgw=getHigherUpSpMap(basicModel,user).get("spgw");
		
		String[]sqid=model.getSqid();
		
		sql.append(" update xg_rcsw_zjbbshb set shzt='xcs',shr='',shsj='', ");
		sql.append(" shyj='' where xtgwid=?  ");
		sql.append(" and sqid in ( ");

		inputV.add(spgw);

		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(sqid[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), inputV);
		
	}
	

	/**
	 * �޸��������������
	 * @param model
	 * @param user
	 * @author qlj
	 * @throws Exception
	 */
	public boolean updateSqjg(RcswZjbbForm model,BasicModel basicModel,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
	
		HashMap<String,String>maxSp=getMaxSpxh(model, user);
		
		String maxxh=maxSp.get("xh");
		
		StringBuilder sql=new StringBuilder();
		// ����ѧ��
		sql.append(" update xg_rcsw_zjbbsqb b set sqjg= ");
		sql.append(" (select case when tg = ? then 'tg' when btg >= 1 then 'btg'  ");
		sql.append(" when th >= 1 then 'shz'  when wsh = ? then 'wsh' else 'shz' end shzt ");
		sql.append(" from (select sqid,sum(tg)tg,sum(btg)btg,sum(wsh)wsh,sum(th)th from( ");
		sql.append(" select sqid,case when shzt='tg' then 1 else 0 end tg, ");
		sql.append(" case when shzt='btg' then 1 else 0  end btg, ");
		sql.append(" case when shzt='wsh' then 1 else 0  end wsh, ");
		sql.append(" case when shzt='th' then 1 else 0  end  th ");
		sql.append(" from xg_rcsw_zjbbshb  ");
		sql.append(" ) group by sqid)a where ");
		sql.append(" a.sqid=b.id) ");
		sql.append(" where exists(select 1 from xg_rcsw_zjbbshb  a where b.id=a.sqid) ");

		return dao.runUpdate(sql.toString(), new String[]{maxxh,maxxh});
		
	}
	// ---------------------֤��������� end -------------------
	
	/**
	 * ��ȡ������У�����б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBbjgList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String []colList=model.getColList();
	
		//====================��������===================================
	
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select a.id,a.xh, ");
		sql.append(" substr(a.sqsj, 0, 4) || '��' || substr(a.sqsj, 5, 2) || '��' || ");
		sql.append(" substr(a.sqsj, 7, 2) || '��' sqsj, ");
		sql.append(" zjmc, ");
		sql.append(" case when sqjg='wsh' then 'δ���'  ");
		sql.append(" when sqjg='shz' then '�����' ");
		sql.append(" when sqjg='btg' then '��ͨ��' ");
		sql.append(" when sqjg='tg' then 'ͨ��' ");
		sql.append(" when sqjg='wxsh' then '�������' ");
		sql.append(" else 'δ���' ");
		sql.append(" end sqjg,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc, b.bjdm,b.bjmc from  ");
		sql.append(" (select a.*,b.zjmc from  xg_rcsw_zjbbsqb a, xg_rcsw_zjbbszb b where a.xmid=b.id)a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	
	
	/**
	 * ���������У��˱���Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean saveZjbbShb(String id,String xmid) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_rcsw_zjbbshb(sqid,xtgwid) ");
		sql.append(" select id,spgw from ( ");
		sql.append(" select * from xg_rcsw_zjbbsqb  where id=? ) ");
		sql.append(" a left join (select spgw from xg_xtwh_spbz b ");
		sql.append(" where splc=(select lcid from xg_rcsw_zjbbszb where id=? ))b ");
		sql.append(" on 1=1 ");
		
		return dao.runUpdate(sql.toString(), new String[]{id,xmid});
	}
	
	/**
	 * ��ȡ���û���ָ������
	 * ��Ŀ�е���˸�λ��Ϣ
	 * (����һ�˶��)
	 * @author qlj
	 */
	public List<HashMap<String,String>> getSpgwList(String xmdm,User user) {

		String userName=user.getUserName();
	
		StringBuilder sql = new StringBuilder();

		sql.append("  select c.id,c.mc ");
		sql.append("  from xg_rcsw_zjbbszb a,xg_xtwh_spbz b ,xg_xtwh_spgw c,xg_xtwh_spgwyh d ");
		sql.append("  where a.lcid=b.splc and b.spgw=c.id ");
		sql.append("  and b.spgw=d.spgw and d.spyh =?  ");
		sql.append("  and  a.id = ?  ");
		sql.append("  order by b.xh");
		
		DAO dao = DAO.getInstance();

		return dao.getList(sql.toString(), new String[]{userName,xmdm}, new String[]{"id","mc"});
	}
	

	public HashMap<String,String>getZjbbSqInfo(String sqid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select id,a.xh, substr(a.sqsj, 0, 4) || '��' || substr(a.sqsj, 5, 2) || '��' ||");
		sql.append(" substr(a.sqsj, 7, 2) || '��' sqsj,sqly,");
		sql.append(" xm,nj,xymc,zymc,bjmc,zjmc from   ");
		sql.append(" (select a.*,b.zjmc from xg_rcsw_zjbbsqb a,xg_rcsw_zjbbszb b where a.xmid=b.id )a");
		sql.append(" left join view_xsjbxx b on a.xh=b.xh )where id=? ");
	
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	public List<HashMap<String,String>>getZjbbShInfo(String xmid,String sqid,String spgw){
		
			StringBuilder sql = new StringBuilder();

			List<String>inputV=new ArrayList<String>();
			
			sql.append(" select spgw,mc gwmc,shzt,shsj,shyj,shr, ");
			sql.append(" (select xm from yhb t where b.shr=t.yhm)shrxm ");       
			sql.append(" from ( select spgw,mc from xg_xtwh_spbz a,xg_xtwh_spgw c where splc= ");             
			sql.append(" (select a.lcid from xg_rcsw_zjbbszb a,xg_rcsw_zjbbsqb b where b.id=?  ");   
			sql.append(" and a.id=b.xmid ) ");
			sql.append(" and (exists( select 1 from xg_xtwh_spbz b where splc= ");   
			sql.append(" (select a.lcid from xg_rcsw_zjbbszb a,xg_rcsw_zjbbsqb b where b.id=?    "); 
			sql.append(" and a.id=b.xmid ) ");
			
			inputV.add(sqid);
			inputV.add(sqid);
		
			if(!Base.isNull(spgw)){
				
				sql.append(" and spgw=? ");
				inputV.add(spgw);
			}
			
			sql.append(" and a.xh<=b.xh) ");  
			sql.append(" or exists ");  
			sql.append(" (select 1  from xg_rcsw_zjbbshb b "); 
			sql.append(" where c.id = b.xtgwid "); 
			sql.append(" and b.sqid = ? "); 
			sql.append(" and shzt='th')) "); 
			sql.append(" and a.spgw=c.id order by xh)a left join xg_rcsw_zjbbshb b ");   
			sql.append(" on a.spgw=b.xtgwid  where b.sqid=? ");   
	 
			DAO dao = DAO.getInstance();
			inputV.add(sqid);
			inputV.add(sqid);
		
			return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
		
	}
	
	public String countSpgw(String xmid,User user){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		String userName=user.getUserName();
		
		sql.append(" select count(1)num from xg_xtwh_spbz a where  ");
		sql.append(" a.splc=(select lcid from xg_rcsw_zjbbszb where id=? ) ");
		sql.append(" and exists(select 1 from xg_xtwh_spgwyh b  ");
		sql.append(" where spyh=? and a.spgw=b.spgw) ");
		
		return dao.getOneRs(sql.toString(), new String[]{xmid,userName}, "num");
	}
	
	// ----------------------������˱����� end ------------------------------	
	
	
	public String[] getSpgwByXmid(String xmid) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select spgw from xg_xtwh_spbz where splc=");
		sql.append("(select lcid from xg_rcsw_zjbbszb where id=?) ");
		sql.append("order by xh");
		
		return DAO.getInstance().getArray(sql.toString(), new String[]{xmid}, "spgw");
	}
	
	
	/**
	 * ֤���������Զ��嵼��
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public  List<HashMap<String,String>> getBbjgExportList(BasicModel model,User user) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		//User user=model.getUser();
		
		String []colList=model.getColList();
	
		//====================��������===================================
	
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select a.id,a.xh, ");
		sql.append(" substr(a.sqsj, 0, 4) || '��' || substr(a.sqsj, 5, 2) || '��' || ");
		sql.append(" substr(a.sqsj, 7, 2) || '��' sqsj, ");
		sql.append(" zjmc, ");
		sql.append(" case when sqjg='wsh' then 'δ���'  ");
		sql.append(" when sqjg='shz' then '�����' ");
		sql.append(" when sqjg='btg' then '��ͨ��' ");
		sql.append(" when sqjg='tg' then 'ͨ��' ");
		sql.append(" when sqjg='wxsh' then '�������' ");
		sql.append(" else 'δ���' ");
		sql.append(" end sqjg,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc, b.bjdm,b.bjmc from  ");
		sql.append(" (select a.*,b.zjmc from  xg_rcsw_zjbbsqb a, xg_rcsw_zjbbszb b where a.xmid=b.id)a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQLƴװ end================================
		List<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>) CommonQueryDAO.
		commonQueryforExportList(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
}

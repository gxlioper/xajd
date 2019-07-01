package xsgzgl.gygl.jqlx;

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
 * <p>Time:2012-7-18 ����11:56:53</p>
 */
public class GyglJqlxDAO extends BasicDAO{
	
	/**
	 * ��ȡ������У�����б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getLxjgList(BasicModel model) throws Exception{
		
		
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
		sql.append(" substr(a.kssj, 0, 4) || '��' || substr(a.kssj, 5, 2) || '��' || ");
		sql.append(" substr(a.kssj, 7, 2) || '�� �� '|| ");
		sql.append(" substr(a.jssj, 0, 4) || '��' || substr(a.jssj, 5, 2) || '��' || ");
		sql.append(" substr(a.jssj, 7, 2) || '��' yjzxsj, ");
		sql.append(" case when sqjg='wsh' then 'δ���'  ");
		sql.append(" when sqjg='shz' then '�����' ");
		sql.append(" when sqjg='btg' then '��ͨ��' ");
		sql.append(" when sqjg='tg' then 'ͨ��' ");
		sql.append(" when sqjg='wxsh' then '�������' ");
		sql.append(" else 'δ���' ");
		sql.append(" end sqjg,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc, b.bjdm,b.bjmc from xg_gygl_jqlxsqb a ");
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
	 * ������У��˲�ѯ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getLxshList(BasicModel model) throws Exception{
		
		DAO dao=DAO.getInstance();
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		HashMap<String,String> nextSpgw =getNextSpMap(model, user);
		
		String []sqbPk=model.getSqbPk();
		
		String []shbPk=model.getShbPk();
		
		String []colList={"id","xh","xm","nj","bjmc","sqsj","shzt","sfsh"};
		
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
		// �����
		String sqb="xg_gygl_jqlxsqb";
		// ��˱�
		String shb="xg_gygl_jqlxshb";
		// ��Ա��
		String ryb=" (select a.*,b.xymc,b.zymc,b.bjmc from "+Base.xsxxb+"  " +
				"a left join view_njxyzybj_all b on a.bjdm=b.bjdm) ";
		
		tableSql.append(" select a.*,rownum r from( "); 
		tableSql.append(" select a.*,case when b.sqid is not null then "); 
		tableSql.append(" 'disabled' else '' end sfsh from(  "); 
		tableSql.append(" select a.xh pkValue, a.id, "); 
		tableSql.append(" a.xh,a.sqsj,c.xydm, "); 
		tableSql.append(" case when b.shzt='wsh' then 'δ���' ");
		tableSql.append(" when b.shzt='tg' then 'ͨ��' ");
		tableSql.append(" when b.shzt='btg' then '��ͨ��' ");
		tableSql.append(" when b.shzt='xcs' then '������' ");
		tableSql.append(" when b.shzt='th' then '�˻�' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm "); 
		tableSql.append(" from "+sqb+" a, "+shb+" b, "+ryb+" c "); 
		tableSql.append(" where b.xtgwid =? "); 
		tableSql.append(getPkQuery(sqbPk, shbPk, "a", "b"));
		tableSql.append(" and a.xh = c.xh "); 
		tableSql.append(" and a.lcid=(select lcid from xg_gygl_jqlxszb) "); 
		
		HashMap<String,String>higherUpSpgw=getHigherUpMap(model, user);
		
		String higherUpgw=higherUpSpgw.get("spgw");
		if(!Base.isNull(higherUpgw)){
			tableSql.append(" and exists( select 1 from "+shb+" x where 1=1 ");
			tableSql.append(getPkQuery(sqbPk, shbPk, "a", "x"));
			tableSql.append(" and x.xtgwid='"+higherUpgw+"' and shzt='tg' )");
		}
		
		// --------------------------�¼�����Ƿ������-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select sqid from "+shb+" ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.id=b.sqid) a ");
		// --------------------------�¼�����Ƿ������-------------------------------
		
		tableSql.append(query);
		
		// ====================SQLƴװ end================================

		String spgw=valueMap.get("spgw");
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(tableSql.toString(),"", dao.unionArray(new String[]{spgw,nextSpgw.get("spgw")},inputV), colList, model);
		
		return list;
	}
	
	// ----------------------------������У���� begin ------------------------------
	/**
	 * ��ȡ������У�����б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getLxsqList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String []colList={"id","sqsj","shzt","kssj","jssj","sqjg"};
		
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
		sql.append(" select xh,id,sqsj,kssj,jssj,max(shzt)shzt,");
		sql.append(" case when sqjg='wsh' then '' else 'disabled' end sqjg from ( ");
		sql.append(" select xh,id,sqsj,kssj,jssj,sqjg,to_char( ");
		sql.append(" WM_CONCAT(mc||'('||nvl(shzt,'�������')||')')over(partition by id");
		sql.append(" order by xsxh asc ))shzt from ( ");
	
		sql.append(" select id,xh,kssj,jssj, sqsj,sqjg, shzt,  xsxh,mc  from (  ");
		sql.append(" select a.id,a.xh,a.kssj,a.jssj,a.sqsj,a.sqjg,b.shzt,b.xh xsxh,b.mc from(  ");
		sql.append(" select a.id,a.xh,a.kssj,a.jssj,a.sqsj,a.sqjg,e.lcid from xg_gygl_jqlxsqb a,xg_gygl_jqlxszb e   ");
		sql.append(" where 1=1)a left join   ");
		sql.append(" (select b.sqid, b.xtgwid, c.mc, case when b.shzt = 'wsh' then 'δ���' ");
		sql.append(" when b.shzt = 'tg' then 'ͨ��'   ");
		sql.append(" when b.shzt = 'btg' then '��ͨ��'   ");
		sql.append(" when b.shzt = 'xcs' then  '������'  ");
		sql.append(" when b.shzt = 'th' then '�˻�'  end shzt, ");
		sql.append(" d.splc,d.xh  from xg_gygl_jqlxshb b,  ");
		sql.append(" xg_xtwh_spgw c, xg_xtwh_spbz d  where  ");
		sql.append(" b.xtgwid = c.id  and b.xtgwid = d.spgw)b  ");
		sql.append(" on a.id=b.sqid and a.lcid=b.splc  )) ) ");
		sql.append("  group by xh,id,sqsj,kssj,jssj,sqjg)a ");
		
		
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
	// ----------------------------������У���� begin ------------------------------	
	
	
	// ----------------------������˱����� begin ------------------------------
	/**
	 * ���������У��˱���Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean saveJqlxShb(String id,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_gygl_jqlxshb(sqid,xtgwid) ");
		sql.append(" select id,spgw from ( ");
		sql.append(" select * from xg_gygl_jqlxsqb  where id=? ) ");
		sql.append(" a left join (select spgw from xg_xtwh_spbz b ");
		sql.append(" where splc=(select lcid from xg_gygl_jqlxszb))b ");
		sql.append(" on 1=1 ");
		
		return dao.runUpdate(sql.toString(),new String[]{id},"xg_gygl_jqlxshb",user);
	}
	
	/**
	 * ��ȡ����������Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getJbszInfo() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select lcid,sqkg from xg_gygl_jqlxszb ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ��ȡ��˸�λ�б�
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getSpgwList(GyglJqlxForm model,User user){
		
		String lcid=model.getLcid();
		
		String userName=user.getUserName();
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select a.id,a.mc,b.xh from xg_xtwh_spgw a,xg_xtwh_spbz b,xg_xtwh_spgwyh c");
		sql.append(" where a.id=b.spgw and a.id=c.spgw and c.spyh=? ");
		sql.append(" and b.splc=? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{userName,lcid});
	}
	
	/**
	 * �������״̬
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean resultShzt(GyglJqlxForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
	
		String[] sqid=model.getSqid();
		
		sql.append(" update xg_gygl_jqlxshb set shzt='wsh'  ");
		sql.append(" where  shzt='th' ");
		sql.append(" and sqid in ( ");
		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append("  ) ");
		
		return dao.runUpdate2(sql.toString(),sqid,"xg_gygl_jqlxshb",user);
	}
	
	/**
	 * �޸����״̬
	 * (������������˽Կ�ʹ��)
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateShzt(GyglJqlxForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		ArrayList<String>inputV=new ArrayList<String>();
		
		String shzt=model.getShzt();
		
		String shyj=model.getShyj();
		
		String userName=user.getUserName();

		String spgw=model.getSpgw();
		
		String[]sqid=model.getSqid();
		
		sql.append(" update xg_gygl_jqlxshb set shzt=?,shr=?,shsj=?, ");
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
		
		return dao.runUpdate(sql.toString(),inputV,"xg_gygl_jqlxshb",user);
		
	}
	
	/**
	 * ���ʱ���Ϊ�˻�״̬
	 * �ϼ������Ҫ�޸�Ϊ������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateThzt(GyglJqlxForm model,BasicModel basicModel,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		ArrayList<String>inputV=new ArrayList<String>();
	
		String spgw=getHigherUpMap(basicModel,user).get("spgw");
		
		String[]xh=model.getXh();
		
		sql.append(" update xg_gygl_jqlxshb set shzt='xcs',shr='',shsj='', ");
		sql.append(" shyj='' where xtgwid=?  ");
		sql.append(" and xh in ( ");

		inputV.add(spgw);

		for(int i=0;i<xh.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(xh[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(),inputV,"xg_gygl_jqlxshb",user);
		
	}
	

	/**
	 * ��ȡ��Ŀ�����˸�λ����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateSqjg(GyglJqlxForm model,BasicModel basicModel,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
	
		HashMap<String,String>maxSp=getMaxSpxh(model, user);
		
		String maxxh=maxSp.get("xh");
		
		StringBuilder sql=new StringBuilder();
		// ����ѧ��
		sql.append(" update xg_gygl_jqlxsqb b set sqjg= ");
		sql.append(" (select case when tg = ? then 'tg' when btg >= 1 then 'btg'  ");
		sql.append(" when th >= 1 then 'shz'  when wsh = ? then 'wsh' else 'shz' end shzt ");
		sql.append(" from (select sqid,sum(tg)tg,sum(btg)btg,sum(wsh)wsh,sum(th)th from( ");
		sql.append(" select sqid,case when shzt='tg' then 1 else 0 end tg, ");
		sql.append(" case when shzt='btg' then 1 else 0  end btg, ");
		sql.append(" case when shzt='wsh' then 1 else 0  end wsh, ");
		sql.append(" case when shzt='th' then 1 else 0  end  th ");
		sql.append(" from xg_gygl_jqlxshb  ");
		sql.append(" ) group by sqid)a where ");
		sql.append(" a.sqid=b.id) ");
		sql.append(" where exists(select 1 from xg_gygl_jqlxshb  a where b.id=a.sqid) ");

		return dao.runUpdate(sql.toString(),new String[]{maxxh,maxxh},"xg_gygl_jqlxshb",user);
	}
	
	/**
	 * ��ȡ��Ŀ�����˸�λ����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getMaxSpxh(GyglJqlxForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		// ����ѧ��
		sql.append(" select splc,max(xh)xh ");
		sql.append(" from xg_xtwh_spbz ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_gygl_jqlxszb ");
		sql.append(" ) ");
		sql.append(" group by splc");
		
		return dao.getMapNotOut(sql.toString(), new String[]{});
		
	}
	
	/**
	 * ������У������Ϣ��ȡ
	 * @param sqid
	 * @return
	 */
	public HashMap<String,String>getJqlxSqInfo(String sqid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select id,a.xh, substr(a.sqsj, 0, 4) || '��' || substr(a.sqsj, 5, 2) || '��' ||");
		sql.append(" substr(a.sqsj, 7, 2) || '��' sqsj,");
		sql.append(" substr(a.kssj, 0, 4) || '��' || substr(a.kssj, 5, 2) || '��' ||");
		sql.append(" substr(a.kssj, 7, 2) || '�� �� '||");
		sql.append(" substr(a.jssj, 0, 4) || '��' || substr(a.jssj, 5, 2) || '��' ||");
		sql.append(" substr(a.jssj, 7, 2) || '��' yjzxsj,xm,nj,xymc,zymc,bjmc from xg_gygl_jqlxsqb a left join view_xsjbxx b ");
		sql.append(" on a.xh=b.xh )where id=? ");
	
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	public List<HashMap<String,String>>getJqlxShInfo(String sqid,String spgw){
		
			StringBuilder sql = new StringBuilder();

			List<String>inputV=new ArrayList<String>();
			
			sql.append(" select spgw,mc gwmc,shzt,shsj,shyj,shr, ");
			sql.append(" (select xm from yhb t where b.shr=t.yhm)shrxm ");       
			sql.append(" from ( select spgw,mc from xg_xtwh_spbz a,xg_xtwh_spgw c where splc= ");             
			sql.append(" (select lcid from xg_gygl_jqlxszb) ");   
			sql.append(" and (exists( select 1 from xg_xtwh_spbz b where splc= ");   
			sql.append(" (select lcid from xg_gygl_jqlxszb)  ");  
			
			if(!Base.isNull(spgw)){
				
				sql.append(" and spgw=? ");
				inputV.add(spgw);
			}
			
			sql.append(" and a.xh<=b.xh) ");  
			sql.append(" or exists ");  
			sql.append(" (select 1  from xg_gygl_jqlxshb b "); 
			sql.append(" where c.id = b.xtgwid "); 
			sql.append(" and b.sqid = ? "); 
			sql.append(" and shzt='th')) "); 
			sql.append(" and a.spgw=c.id order by xh)a left join xg_gygl_jqlxshb b ");   
			sql.append(" on a.spgw=b.xtgwid  where b.sqid=? ");   
	 
			DAO dao = DAO.getInstance();
			inputV.add(sqid);
			inputV.add(sqid);
		
			return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
		
	}
	
	/**
	 * ͳ��������λ��
	 * @param user
	 * @return
	 */
	public String countSpgw(User user){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		String userName=user.getUserName();
		
		sql.append(" select count(1)num from xg_xtwh_spbz a where  ");
		sql.append(" a.splc=(select lcid from xg_gygl_jqlxszb) ");
		sql.append(" and exists(select 1 from xg_xtwh_spgwyh b  ");
		sql.append(" where spyh=? and a.spgw=b.spgw) ");
		
		return dao.getOneRs(sql.toString(), new String[]{userName}, "num");
	}
	
	/**
	 * ͳ����˽�����¼��
	 * @param lcid
	 * @return
	 */
	public String countShjsjl(String lcid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append("  select count(1)num from xg_gygl_jqlxsqb ");
		sql.append("  where lcid=(select lcid from xg_gygl_jqlxszb) ");
		sql.append("  and (sqjg='shz' or sqjg='wsh') ");
		sql.append("  and lcid<>? ");
		
		return dao.getOneRs(sql.toString(), new String[]{lcid}, "num");
	}
	
	/**
	 * ����������Ŀ����ǰ���
	 * ��λ��ȡ�¼�������λ
	 * @author qlj
	 */
	public HashMap<String,String> getHigherUpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		String xmb=valueMap.get("xmb");
		
		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(xmb);
		sql.append("  ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(xmb);
		sql.append("  )  ");
		sql.append(" and spgw=? ");
		sql.append(" and a.xh+1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{spgw});
	}
	
	/**
	 * ����������Ŀ����ǰ���
	 * ��λ��ȡ�¼�������λ
	 * @author qlj
	 */
	public HashMap<String,String> getNextSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_gygl_jqlxszb ");
		sql.append("  ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(" xg_gygl_jqlxszb ");
		sql.append("  )  ");
		sql.append(" and spgw=? ");
		sql.append(" and a.xh-1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{spgw});
	}
	
	// ----------------------������˱����� end ------------------------------	
}

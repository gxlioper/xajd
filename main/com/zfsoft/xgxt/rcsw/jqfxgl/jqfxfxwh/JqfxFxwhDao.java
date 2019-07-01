/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����04:02:10 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxfxwh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����04:02:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JqfxFxwhDao  extends  SuperDAOImpl<JqfxFxwhForm> {
	/*
    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
*/

@Override
public List<HashMap<String, String>> getPageList(JqfxFxwhForm t)
		throws Exception {
	// TODO �Զ����ɷ������
	return null;
}

/*
    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
*/	
@Override
public List<HashMap<String, String>> getPageList(JqfxFxwhForm t, User user)
		throws Exception {
	//���ɸ߼���ѯ�������������ֵ 
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xsxydm", "xsbjdm");
	String[] inputV = SearchService.getTjInput(t.getSearchModel());		
	StringBuffer sql = new StringBuffer();	
	sql.append(" select rownum r,a.* from (select "
			+ " b.xm,b.xh,b.xb,b.nj,e.yymc wfxyymc,c.sfqdlx,c.yjfxsj "
			+ " ,b.zymc xszymc,b.zydm xszydm,b.xymc xsxymc,b.xydm xsxydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm," 
			+ " c.id,c.xn,c.xq," + "  decode(c.fxzt,NULL , '-1' , '0' , '0' , '1' , '1')fxzt, "
			+ " decode(c.fxzt,'','δ����','0','δ��У','1','�ѷ�У',c.fxzt)fxztmc,c.fxsj,c.wfxyy,d.fxmc,c.bz ");
	sql.append(" from view_xsjbxx b   left join xg_rcsw_jqfxgl_jgb c  on b.xh = c.xh " 
			+ " left join XG_RCSW_JQFXGL_WBDYYB e on c.wfxyy=e.yydm " 
			+ " left join XG_RCSW_JQFXGL_FXLBB d on c.fxdm = d.fxdm "  
			+ " order by xh  ) a where  1=1 ");
	
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return getPageList(t, sql.toString(), inputV); 
	
	
}


/**
* 
* @����:TODO(��ȡ��У����)
* @�๦������: TODO(������һ�仰��������������) 
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10 
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @return
* String �������� 
* @throws
*/
public String getFxmc()throws Exception {
	String sql = " select fxmc "
		+"from XG_RCSW_JQFXGL_JBSZB a left join  XG_RCSW_JQFXGL_FXLBB b on a.fxdm = b.fxdm ";
	return dao.getOneRs(sql, new String[]{}, "fxmc");
}

/**
* 
* @����:TODO(������һ�仰�����������������)
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @param xwjgId
* @return
* Map<String,String> �������� 
* @throws
*/
public Map<String, String> getOneFxgljgList(String  xh) {		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from (select c.sfqdlx,c.yjfxsj,c.wfxyy,"
				+ " b.xh,b.xm, b.zymc xszymc, b.zydm xszydm,b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,"
				+ " b.pycc,b.sjhm,b.sfzh,b.lxdh,c.id,c.xn, c.xq,c.fxdm ,c.bz "
                + " from view_xsbfxx b left join xg_rcsw_jqfxgl_jgb c on b.xh = c.xh ) a"
                + " where 1 = 1 and  a.xh = ? ");	
		return dao.getMapNotOut(sql.toString(),new String[]{xh});		
}

/**
* 
* @����:TODO(��ȡδ��У��Ϣ��¼)
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @param xh
* @return
* Map<String,String> �������� 
* @throws
*/
public Map<String, String> getOneWfxxwhjgList(String  xh) {
	StringBuffer sql = new StringBuffer();	
  sql.append("select xh,xn,xq,sfqdlx,yjfxsj,decode(fxzt,'','δ����','0','δ��У','1','�ѷ�У',fxzt) fxztmc," 
  		  //+"decode(sfqdlx,'','��','��','��','��','��',sfqdlx)" 
  		  +"fxsj,fxdm,wfxyy,b.yymc wfxyymc,a.bz from xg_rcsw_jqfxgl_jgb a " 
  		  +" left join XG_RCSW_JQFXGL_WBDYYB b on a.wfxyy=b.yydm  where xh = ?");
	return dao.getMapNotOut(sql.toString(),new String[]{xh});
}
		
/**
* 
* @����:TODO(������ȡ���ݿ������δ��У��¼)
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @return
* @throws Exception
* String �������� 
* @throws
*/
public String getCountNum(JqfxFxwhForm t,User user)throws Exception {
	//wewew
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
	String[] inputV = SearchService.getTjInput(t.getSearchModel());	
	StringBuffer sql = new StringBuffer();			
/*	sql.append( " select count(*) countNum from (select a.xh,a.sfrz,a.lddm," 
	+ " a.bjdm,a.nj,a.qsh,a.xydm,a.zydm,a.cwh,c.fxsj,b.xm,c.fxzt "         
	+  " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh = b.xh "
	+  " left join xg_rcsw_jqfxgl_jgb c on a.xh = c.xh "
	+  " order by a.lddm, to_number(a.ch), a.qsh ) a "
	+  " where a.sfrz = '��'  and a.xh not in (select xh  from xg_rcsw_jqfxgl_jgb where fxzt = '1') " );*/
	
	sql.append( " select count(*) countNum from (select b.xh,b.bjdm,b.nj, b.xydm, "
	             + " b.zydm, c.fxsj, b.xm,c.fxzt from view_xsjbxx b "
	             + " left join xg_rcsw_jqfxgl_jgb c on b.xh = c.xh ) a "
	             + " where  a.xh not in (select xh from xg_rcsw_jqfxgl_jgb where fxzt = '1') ");
	
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return dao.getOneRs(sql.toString(), inputV, "countNum");		
}


/**
*
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @���ڣ�2016-3-14 ����12:26:58
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @param model
* @return
* boolean �������� 
* @throws
*/
public boolean plSaveJqwh(JqfxFxwhForm t,User user) throws Exception {
					
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xsxydm", "xsbjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuffer sql = new StringBuffer();	
		
		sql.append("select a.xh from (select b.xh,b.xm, "
               + "b.zymc xszymc, b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xydm xszydm,b.xz,b.pycc,b.sjhm, "
               + "c.id,c.xn,c.xq,"
               + "decode(c.fxzt, NULL, '-1', '0', '0', '1', '1') fxzt, "
               + "decode(c.fxzt,'','δ����','0','δ��У','1','�ѷ�У',c.fxzt) fxztmc, " 
               + "c.fxsj, c.wfxyy, d.fxmc from view_xsjbxx b "
               + "left join xg_rcsw_jqfxgl_jgb c on b.xh = c.xh "
               + "left join XG_RCSW_JQFXGL_FXLBB d  on c.fxdm = d.fxdm "
			   + ")  a where a.fxzt <> '1'  ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
					
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		List<HashMap<String, String>> xsjqfxxhmap =  dao.getListNotOut(sql.toString(),inputV);				
					
		StringBuffer insertJgbSql = new StringBuffer();
		insertJgbSql.append("insert into xg_rcsw_jqfxgl_jgb ");
		insertJgbSql.append("(xh,xn,xq,fxzt,fxsj,fxdm,wfxyy,bz,tbsj)");
		insertJgbSql.append(" values(?,?,?,?,?,?,?,?,?)");
		
		
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (Map<String, String> map : xsjqfxxhmap) {
				deleteFxgljgb(map.get("xh"));
				param = new String[9];
				param[0] = map.get("xh");
				param[1] = t.getXn();
				param[2] = Base.currXq;
				param[3] = t.getFxzt();
				param[4] = t.getFxsj();
				param[5] = t.getFxdm();	
				param[6] = "";//δ��Уԭ�����ÿյ�ҵ����ȷ��
				param[7] = t.getBz();
				param[8] = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
				params.add(param);
		}
		try {
			dao.runBatch(insertJgbSql.toString(), params);
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
}

/**
* 
* @����:TODO(����������ڷ�У)
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @param model
* @param user
* @return
* @throws Exception
* boolean �������� 
* @throws
*/
public boolean pldgSaveJqwh(JqfxFxwhForm model,User user) throws Exception{

	String[] xhs = model.getXhs();
	for (int i = 0, n = xhs.length; i < n; i++) {				
		deleteFxgljgb(xhs[i]);
	}

	StringBuffer insertJgbSql = new StringBuffer();
	insertJgbSql.append(" insert into xg_rcsw_jqfxgl_jgb ");
	insertJgbSql.append(" (xh,xn,xq,fxzt,fxsj,fxdm,wfxyy,bz,tbsj) ");
	insertJgbSql.append(" values(?,?,?,?,?,?,?,?,?) ");
	List<String[]> params = new ArrayList<String[]>();
	//String[] xhs = model.getXhs();
	String[] param = null;
	for (int i = 0, n = xhs.length; i < n; i++) {
		
		param = new String[9];
		param[0] = xhs[i];
		param[1] = model.getXn();
		param[2] = Base.currXq;
		param[3] = model.getFxzt();
		param[4] = model.getFxsj();
		param[5] = model.getFxdm();	
		param[6] = "";//δ��Уԭ�����ÿյ�ҵ����ȷ��	
		param[7] = model.getBz();
		param[8] = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
		params.add(param);
		
	}
	try {
		dao.runBatch(insertJgbSql.toString(), params);
		return true;		
	} catch (SQLException e){
		e.printStackTrace();
		return false;		
	}
}





/**
* 
* @����:TODO(ɾ�����ڷ�У������е�����)
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @param xhs
* @return
* @throws Exception
* int �������� 
* @throws
*/
public int deleteFxgljgb(String xhs) throws Exception {
	String sql = " delete from xg_rcsw_jqfxgl_jgb where xh = ? ";
	return dao.runDelete(sql, new String[] {xhs});

}

/**
* @throws Exception 
* 
* @����:TODO(����������ѧ������δ��Уά��)
* @���ߣ� lgx[����:1553]
* @ʱ�䣺 2018-4-20 ����04:02:10
* @�޸ļ�¼: �޸�������-�޸�����-�޸�����
* @param model
* @return
* boolean �������� 
* @throws
*/
public boolean pldgSaveJqwfx(JqfxFxwhForm model) throws Exception{ 			
		String[] xhs = model.getXhs();
		for (int i = 0, n = xhs.length; i < n; i++) {				
			deleteFxgljgb(xhs[i]);
		}		
		StringBuffer insertJgbSql = new StringBuffer();
		insertJgbSql.append(" insert into xg_rcsw_jqfxgl_jgb ");
		insertJgbSql.append(" (xh,xn,xq,fxzt,fxsj,fxdm,wfxyy,sfqdlx,yjfxsj,bz,tbsj) ");
		insertJgbSql.append(" values(?,?,?,?,?,?,?,?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		//String[] xhs = model.getXhs();
		String[] param = null;
		for (int i = 0, n = xhs.length; i < n; i++) {					
			param = new String[11];
			param[0] = xhs[i];
			param[1] = model.getXn();
			param[2] = Base.currXq;
			param[3] = model.getFxzt();
			param[4] = model.getFxsj();
			param[5] = model.getFxdm();	
			param[6] = model.getWfxyy();	
			param[7] = model.getSfqdlx();
			param[8] = model.getYjfxsj();
			param[9] = model.getBz();
			param[10] = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
			params.add(param);				
		}
		try {
			dao.runBatch(insertJgbSql.toString(), params);
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
}

/** 
 * @����:��ȡ����δ��Уԭ��(������һ�仰�����������������)
 * @���ߣ�lgx[���ţ�1553]
 * @���ڣ�2018-4-24 ����05:32:58
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @return
 * HashMap<String,String> �������� 
 * @throws 
 */
public List<HashMap<String, String>> getAllWfxyy() {
	String sql = "select * from XG_RCSW_JQFXGL_WBDYYB order by yydm ";
	return dao.getListNotOut (sql, new String[]{});
}
/** 
 * @����:TODO(������һ�仰�����������������)
 * @���ߣ��Ų�·[���ţ�982]
 * @���ڣ�2018-4-25 ����11:52:49
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param dm
 * @return
 * String �������� 
 * @throws 
 */
public String getWfxyyMc(String dm) {
	String sql = "select yymc from XG_RCSW_JQFXGL_WBDYYB where yydm=? ";
	return dao.getOneRs(sql, new String[]{dm}, "yymc");
}
/*
    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
*/
@Override
protected void setTableInfo() {		
	// TODO �Զ����ɷ������
	super.setClass(JqfxFxwhForm.class);
	super.setTableName("xg_rcsw_jqfxgl_jgb");
	super.setKey("id");
	
	
}

/**
 * @throws Exception 
 * @param model
 * @����:TODO(������һ�仰�����������������)
 * @���ߣ�lgx[���ţ�1553]
 * @���ڣ�2018-4-27 ����03:45:22
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @return
 * List<HashMap<String,String>> �������� 
 * @throws 
 */
public List<HashMap<String, String>> getBdqktjList(JqfxFxwhForm t,User user) throws  Exception {
	SearchModel searchModel = new SearchModel();
	searchModel.setSearch_tj_xy(t.getSearchModel().getSearch_tj_xy());
	searchModel.setPath(t.getSearchModel().getPath());
	String searchTj = SearchService.getSearchTj(searchModel);
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "");
	String[] inputV = SearchService.getTjInput(searchModel);
	StringBuffer sql = new StringBuffer();
	sql.append( " select rownum r,a.* from (  select xymc, wbdzrs,f.xyzrs,g.yxjrs,e.xydm xsxydm ,WM_CONCAT(yymc|| '_' || rs) wbdqk from ("
			  + " select count(wfxyy) rs ,b.yymc,b.yydm ,"
			  + " d.wbdzrs ,c.xydm,c.xymc "
			  + " from xg_rcsw_jqfxgl_jgb a "
			  + " left join XG_RCSW_JQFXGL_wbdyyb b  on a.wfxyy=b.yydm " 
			  + " left join view_xsjbxx c on a.xh=c.xh "
			  + " left join ( "
			  + " select count(0) wbdzrs,t1.xymc,t1.xydm from xg_rcsw_jqfxgl_jgb t "
			  + " left join view_xsjbxx t1 on t.xh=t1.xh where t.fxzt='0' group by t1.xydm,t1.xymc,t1.xydm) d "
			  + " on c.xydm=d.xydm "
			 /* + " where a.fxzt='0' "*/
			  + " group by a.wfxyy,b.yymc,b.yydm,c.xydm,c.xymc,d.wbdzrs ) e "
			  + " left join (select count(0) xyzrs,xydm from view_xsjbxx group by xydm) f on e.xydm=f.xydm " 
			  +	" left join (select count(0) yxjrs,xydm from view_xsjbxx where xjztm='��ѧ��' group by xydm) g on e.xydm=g.xydm "
			  + " group by xymc, wbdzrs,e.xydm,f.xyzrs,g.yxjrs ) a where xsxydm is not null ");
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return dao.getListNotOut(sql.toString(), inputV);
}
	/**
	 * @����:��ȡѧԺʵ����������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/6/19 14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xsxydm]
	 * @return: int
	 */
		public int getSdrs(String xydm) throws SQLException {
			if(StringUtils.isNull(xydm)) return 0;
			String sql = "select count(0) z from xg_rcsw_jqfxgl_jgb a " +
					" left join view_xsjbxx b on b.xh=a.xh where a.fxzt='1' and b.xydm='"+xydm+"'";
			return dao.getOneRsint(sql);
	}
}

package com.zfsoft.xgxt.dekt.xfjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class DektxfjgDao extends SuperDAOImpl<DektxfjgForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_DEKT_DEKTXFJGB");
		super.setKey("jgid");
		super.setClass(DektxfjgForm.class);
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(DektxfjgForm model) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DektxfjgForm model, User user) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.jgid,t1.sqid,t1.xh,t1.xmid,t1.cyfs,decode(t1.cyfs,'gr','����','tt','����') cyfsmc,t1.hjsj,t1.sqsm,t1.filepath,t1.xf, ");
		sql.append("t2.ssxydm,t2.xmdl,t2.lx,t2.rdxm,t2.rdnrbz,t2.dj,t2.yjsm, ");
		sql.append("t3.xm,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc, ");
		//����ʦ����ѧ
		if(Base.xxdm.equals("10346")){
			sql.append("t1.pjjg,t1.pjbz,");
		}
		sql.append("replace( t2.lx||'->'||t2.rdxm||'->'||t2.rdnrbz||'->'||t2.dj,'->��',' ') rdnr ");
		sql.append("from XG_DEKT_DEKTXFJGB t1 left join XG_DEKT_XMDMB t2 on t1.xmid=t2.xmid ");
		sql.append("left join view_xsjbxx t3 on t1.xh=t3.xh ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), inputV);
	}

	public boolean checkExist(DektxfjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_DEKT_DEKTXFJGB where xh = ? and xmid=? and hjsj=?");
		if(StringUtils.isNotNull(form.getJgid())){
			sql.append("and jgid<> '");
			sql.append(form.getJgid());
			sql.append("'");
		}
		String num=dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getXmid(),form.getHjsj()}, "num");
		return Integer.valueOf(num) > 0;
	}


	/** 
	 * @������
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��27��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public boolean deleteExist(DektxfjgForm form) throws Exception {
		String sql="delete from XG_DEKT_DEKTXFJGB where xh=? and xmid=? and hjsj=? ";
		return dao.runUpdate(sql, new String[]{form.getXh(),form.getXmid(),form.getHjsj()});
	}
	
	public boolean deleteBysqid(String sqid) throws Exception {
		String sql="delete from XG_DEKT_DEKTXFJGB where sqid=? ";
		return dao.runUpdate(sql, new String[]{sqid});
	}
	
	public Map<String, String> getView(DektxfjgForm form) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("select * from (select t1.sqid,t1.xh,t1.xmid,t1.cyfs,decode(t1.cyfs,'gr','����','tt','����') cyfsmc,t1.hjsj,t1.sqsm,t1.jgid,t1.xf, ");
		//����ʦ����ѧ���Ի�
		if(Base.xxdm.equals("10346")){
			sql.append("t1.pjjg,t1.pjbz,");
		}
		sql.append("t1.filepath,t2.ssxydm,t2.xmdl,t2.lx,t2.rdxm,t2.rdnrbz,t2.dj,t2.yjsm,replace( t2.lx||'->'||t2.rdxm||'->'||t2.rdnrbz||'->'||t2.dj,'->��',' ') rdnr ");
		
		sql.append("from XG_DEKT_DEKTXFJGB t1 left join XG_DEKT_XMDMB t2 on t1.xmid=t2.xmid) ");
		sql.append("where  jgid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getJgid()});
	}
	
	/** 
	 * @����:��ȡ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-10 ����04:02:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getTotalXf(String xh) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("select xh,sum(xf) xf from xg_dekt_dektxfjgb where xh = ? group by xh ");
		return dao.getOneRs(sb.toString(), new String[]{xh}, "xf");
	}
	
	
}

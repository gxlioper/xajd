/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-14 ����02:34:43 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-14 ����02:34:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjlwhDao extends SuperDAOImpl<BjxwjlwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxwjlwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxwjlwhForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* ")
		.append("  from (select a.* , (select xqmc from xqdzb where xqdm = a.xqdm) xqmc, " +
				"d.xm jlrmc ," +
				"case when length(a.jlnr)>=15 then substr(a.jlnr,0,15)||'...' else a.jlnr end jlnrxs, " +
				"b.xndsmc, c.nj, c.xydm, c.xymc, c.zydm, c.zymc, c.bjmc")
		.append("          from xg_szdw_bjxwjlb a")
		.append("          left join fdyxxb d")
		.append("            on d.zgh  = a.jlr")
		.append("          left join xg_szdw_xndsdmb b")
		.append("            on a.xndsdm = b.xndsdm")
		.append("          left join view_njxyzybj_all c")
		.append("            on a.bjdm = c.bjdm) t1")
		.append(" where 1 = 1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 ��ȡ��¼��Ϣ guid
	 */
	public HashMap<String , String> getModelMap(String guid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* ")
		.append("  from (select a.*,")
		.append("               (select xqmc from xqdzb where xqdm = a.xqdm) xqmc,")
		.append("               (select xm from fdyxxb where zgh = a.jlr) jlrmc,")
		.append("               (select bmmc from zxbz_xxbmdm where d.bmdm = bmdm) jlrbmmc,")
		.append("               b.xndsmc,")
		.append("               c.nj,")
		.append("               c.xydm,")
		.append("               c.xymc,")
		.append("               c.zydm,")
		.append("               c.zymc,")
		.append("               c.bjmc")
		.append("          from xg_szdw_bjxwjlb a")
		.append("          left join xg_szdw_xndsdmb b")
		.append("            on a.xndsdm = b.xndsdm")
		.append("          left join view_njxyzybj_all c")
		.append("            on a.bjdm = c.bjdm")
		.append("          left join fdyxxb d ")
		.append("            on a.jlr = d.zgh) t1")
		.append(" where t1.guid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա����༶��Ϣ
	 */
	public List<HashMap<String , String>> getFdyBjxxList(String zgh){
		StringBuffer sql = new StringBuffer();
		sql.append("select b.bjdm, b.bjmc")
		.append("  from (select *")
		.append("          from fdybjb")
		.append("        union")
		.append("        select * from bzrbbb) a")
		.append("  left join VIEW_NJXYZYBJ_ALL b")
		.append("    on a.bjdm = b.bjdm")
		.append(" where a.zgh = ? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 
	 * @����:��ȡ�����Ϣ
	 */
	public List<HashMap<String , String>> getLbList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_szdw_xndsdmb");
		return dao.getListNotOut(sql.toString() , new String[]{});
	}
	
	//����༶��¼
	public boolean saveBjjl(String xn , String xqdm , String zgh , List records) {
		
		String sql = "insert into xg_szdw_bjxwjlb (xn , xqdm , xndsdm , jlr , jlsj , jlnr , bjdm) values (?,?,?,?,?,?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		boolean ret = false;
		for (Object obj : records) {
			BjxwjlwhForm.Record rd = (BjxwjlwhForm.Record)obj;
			String[] param = new String[7];
			param[0] = xn;
			param[1] = xqdm;
			param[2] = rd.ixndsdm;
			param[3] = zgh;
			param[4] = rd.ijlsj;
			param[5] = rd.ijlnr;
			param[6] = rd.ibjdm;
			paramList.add(param);
		}
		try {
			dao.runBatch(sql, paramList);
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(BjxwjlwhForm.class);
		super.setKey("guid");
		super.setTableName("xg_szdw_bjxwjlb");
	}

}

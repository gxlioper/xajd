/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-28 ����09:25:32 
 */  
package xgxt.gygl.sspy.pyjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ�������ģ��
 * @�๦������: �������Ž��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-28 ����09:24:38 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SspyjgDao extends SuperDAOImpl<SspyjgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SspyjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SspyjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,a.xh,a.sqsj,a.xn,a.xq,c.xqmc,a.lddm,b.ldmc,a.qsh,a.pyxmdm,e.pyxmmc, ");
		sql.append("a.sjly,a.filepath,a.sqly,a.bz,a.ywid,a.sjlrsj,a.sjlrr,f.xm sjlrrxm ");
		sql.append("from xg_gygl_sspyjgb a ");
		sql.append("left join view_xg_gygl_new_qsxx b on a.qsh = b.qsh and b.lddm = a.lddm ");
		sql.append("left join xqdzb c on a.xq = c.xqdm ");
		sql.append("left join view_xsxxb d on a.xh = d.xh ");
		sql.append("left join xg_gygl_new_sspyxmdmb e on a.pyxmdm = e.pyxmdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(SspyjgForm.class);
		this.setKey("guid");
		this.setTableName("xg_gygl_sspyjgb");
	}
	//���ʱ��������ظ����ݣ�ɾ��
	public boolean deleteExist(SspyjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_gygl_sspyjgb where  xn = ? and xq=? and lddm=? and qsh = ? and pyxmdm=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getLddm(),model.getQsh(),model.getPyxmdm()});
	}
	
	/**
	 * @����: ��ȡ¥����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����01:40:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdxxList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_gygl_new_ldxxb order by lddm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����05:53:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsxxForParam(String lddm,String ch,String qsh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_xg_gygl_new_qsxx where lddm = ? and ch = ? and qsh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{lddm,ch,qsh});
	}
	
	/**
	 * @����: ��֤Ψһ��:ѧ�ꡢѧ�ڡ�¥�������ҡ�������Ŀ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����03:57:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(SspyjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_gygl_sspyjgb where xn = ? and xq = ? and lddm = ? and qsh = ? and pyxmdm = ?");
		paraList.add(model.getXn());
		paraList.add(model.getXq());
		paraList.add(model.getLddm());
		paraList.add(model.getQsh());
		paraList.add(model.getPyxmdm());
		if(StringUtils.isNotNull(model.getGuid())){
			sql.append(" and guid <> ? ");
			paraList.add(model.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*��������ʱ����false��������ʱ����true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @����: ����ID��ȡ�����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-3 ����09:07:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,a.xh,a.sqsj,a.xn,a.xq,c.xqmc,a.lddm,b.ldmc,a.qsh,a.pyxmdm,e.pyxmmc, ");
		sql.append("a.sjly,a.filepath,a.sqly,a.bz,a.ywid,a.sjlrsj,a.sjlrr,f.xm sjlrrxm ");
		sql.append("from xg_gygl_sspyjgb a ");
		sql.append("left join view_xg_gygl_new_qsxx b on a.qsh = b.qsh and b.lddm = a.lddm ");
		sql.append("left join xqdzb c on a.xq = c.xqdm ");
		sql.append("left join view_xsxxb d on a.xh = d.xh ");
		sql.append("left join xg_gygl_new_sspyxmdmb e on a.pyxmdm = e.pyxmdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where guid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
}

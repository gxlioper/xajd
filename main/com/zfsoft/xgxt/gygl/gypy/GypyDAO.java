/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:21:09 
 */
package com.zfsoft.xgxt.gygl.gypy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-��Ԣ����
 * @�๦������: (��Ԣ����)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-8-20 ����04:17:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GypyDAO extends SuperDAOImpl<GypyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GypyForm t)
			throws Exception {
		return getData(t, true);
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-22 ����09:14:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param isOpenPage
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getData(GypyForm t,boolean isOpenPage) throws Exception {
		List<HashMap<String, String>> list = null;

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer base=new StringBuffer();
		base.append(" select gypyid,xn,xqdm,pylx,pyly,gldm,lddm,case when ch<0 then 'B'||replace(ch,'-','') else ch end ch,xydm,qsh from xg_gygl_new_gypy ");
		//��ȡ����¥��Ϣ
		if (GypyService._WMSS.equals(t.getPylx())
				|| StringUtils.isNull(t.getPylx())) {
			inputV=Arrays2.addObject(inputV, GypyService._WMSS);
			base.append(" where 1=1");
		}
		//��ȡ��������Ա��Ϣ
		if (GypyService._YXFDY.equals(t.getPylx())) {
			inputV=Arrays2.addObject(inputV, GypyService._YXFDY);
			base.append("a,(select zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where b.bmdm=e.bmdm)bmmc,(select xb from dmk_xb e where b.xb=e.xbdm) xbmc");
			base.append(" from fdyxxb b) c");
			base.append(" where c.zgh=a.gldm");
			
		}
		//��ȡ����ѧ����Ϣ
		if (GypyService._YXXS.equals(t.getPylx())) {
			inputV=Arrays2.addObject(inputV, GypyService._YXXS);
			base.delete(0,base.length());
			base.append("select * from(select a.gypyid,a.xn,a.xqdm,a.pylx,a.pyly,a.gldm,a.ch,b.xh,b.xm,b.xb,b.ldmc,b.lddm,b.qsh,b.xydm,b.xymc from xg_gygl_new_gypy ");
			base.append("a,xg_view_gygl_new_xszsgl b where a.gldm=b.xh) where 1=1 ");
			base.append(t.getOtherFilter());
		}
		base.append(searchTj);
		base.append(" and pylx=?");
		//�Ƿ�����ҳ
		if(isOpenPage){
			list = getPageList(t, base.toString(), inputV);
		}else{
			list = dao.getListNotOut(base.toString(), inputV);
		}
		for (HashMap<String, String> hm : list) {
			this.setXyXqLdForDm(hm);
			this.setFdyxx(hm);
		}
		return list;
	}
	/**
	 * 
	 * @����:����id��ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����04:45:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>  getModelMapForId(String id){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_gygl_new_gypy where GYPYID=?");
		return dao.getMapNotOut(sb.toString(), new String[]{id});
	}
	/**
	 * 
	 * @����:���ø���Ա��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����11:02:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> setFdyxx(HashMap<String, String> dm) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(select xb from dmk_xb e where a.xb=e.xbdm) xbmc");
		sql.append(" from fdyxxb a) a where 1 = 1");
		sql.append(" and zgh=?");
		//����ְ���Ż�ȡ����Ա��ϸ��Ϣ
		HashMap<String, String> hm=dao.getMapNotOut(sql.toString(),new String[]{dm.get("gldm")});
		dm.putAll(hm);
		return hm;
	}
	/**
	 * 
	 * @����:����ѧԺѧ��¥������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����10:05:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> setXyXqLdForDm(HashMap<String, String> dm) {
		String xydm = dm.get("xydm");
		String lddm = dm.get("lddm");
		String xqdm = dm.get("xqdm");
		HashMap<String, String> addMap = new HashMap<String, String>();
		// ��ȡѧԺ����
		for (HashMap<String, String> hm : Base.getXyList()) {
			if (hm.get("xydm").equals(xydm)) {
				addMap.put("xymc", hm.get("xymc"));
				break;
			}
		}
		// ��ȡѧ������
		for (HashMap<String, String> hm : Base.getXqList()) {
			if (hm.get("xqdm").equals(xqdm)) {
				addMap.put("xqmc", hm.get("xqmc"));
				break;
			}
		}
		// ��ȡ¥������
		for (HashMap<String, String> hm : getLdList()) {
			if (hm.get("lddm").equals(lddm)) {
				addMap.put("ldmc", hm.get("ldmc"));
				break;
			}
		}
		dm.putAll(addMap);
		return dm;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GypyForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @����:��ȡ����Ա��ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-20 ����05:23:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getFdyxx(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql
				.append(" from (select zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(select xb from dmk_xb e where a.xb=e.xbdm) xbmc");
		sql.append(" from fdyxxb a) a where 1 = 1 and zgh=?");
		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}

	/**
	 * 
	 * @����:��ȡ¥��list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����10:20:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getLdList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select lddm,ldmc from xg_gygl_new_ldxxb");
		return dao.getListNotOut(sql.toString(), null);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_gypy");
		super.setKey("gypyid");
		super.setClass(GypyForm.class);
	}

	/**
	 * ���ָ��¥��ָ���������������Һ�
	 * 
	 * @param lddm
	 * @param ch
	 * @return
	 */
	public Map<String, String> getMaxQsh(String lddm, String ch) {
		String sql = "select max(qsh) maxQsh from xg_gygl_new_qsxxb where lddm=? and ch=?";
		return dao.getMapNotOut(sql, new String[] { lddm, ch });
	}
	public Map<String, String> getQsxx(String lddm, String ch,String qsh) {
		StringBuffer sql = new StringBuffer("select * from xg_gygl_new_qsxxb where 1=1");
		List<String> param=new ArrayList<String>();
		if(StringUtils.isNotNull(lddm)){
			sql.append(" and lddm=?");
			param.add(lddm);
		}
		if(StringUtils.isNotNull(ch)){
			sql.append(" and ch=?");
			param.add(ch);
		}
		if(StringUtils.isNotNull(qsh)){
			sql.append(" and qsh=?");
			param.add(qsh);
		}
		return dao.getMapNotOut(sql.toString(), param.toArray(new String[param.size()]));
	} 
	/**
	 * 
	 * @����:���ѧ����ϸ��Ϣ-¥�����ҵ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-22 ����05:15:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getXsxx(String xh){
		String[] inputValue = new String[]{xh};
		StringBuilder sql = new StringBuilder();
		String[] outputValue = new String[] { "xh","xh","xm","xb","nj","xymc","bjmc","zymc","ldmc","qsh","cwh","qsdh" };
		sql.append("select a.* from xg_view_gygl_new_xszsgl a where sfzs='��' and xh = ? order by xh asc");
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	/**
	 * 
	 * @����:����Form�������� ��ȡ���ݿ��Ӧ�������ԣ����У�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����10:57:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf	����
	 * @param bindKey Ҫ�����Ķ�������(���Ϊ����֤ȫ��)
	 * @return		
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDataForBeFindEntity(GypyForm gf,String[] bindKey) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_gygl_new_gypy");
		sb.append(" where 1=1");
		List<String> param=new LinkedList<String>();
		if(null==bindKey){
			bindKey=dao.getColumnName(sb.toString());
		}
		for(String str:bindKey){
			String keyValue = (String) gf.getClass().getMethod("get"+str.substring(0, 1).toUpperCase()+str.substring(1).toLowerCase()).invoke(gf);
			if(StringUtils.isNotNull(keyValue)){
				sb.append(" and ");
				sb.append(str);
				sb.append("=?");
				param.add(keyValue);
			}
		}
		return dao.getListNotOut(sb.toString(),param.toArray(new String[param.size()]));
	}
	/**
	 * @����:����Form�������� ��ȡ���ݿ��Ӧ�������ԣ����У�
	 * 		 <br>�ݲ�֧��Form�С���д���ԣ�xxxId,Ҫ������ȫ��ΪСд��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����10:59:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gf
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDataForBeFindEntity(GypyForm gf) throws Exception{
		return this.getDataForBeFindEntity(gf,null);
	}
}

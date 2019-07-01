/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-3-13 ����01:59:43 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_�ҵ�����_�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-13 ����01:59:43 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjjgDao extends SuperDAOImpl<PjjgForm> {
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_pjjgb");
		super.setKey("id");
		super.setClass(PjjgForm.class);
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(PjjgForm t)
			throws Exception {
	
		
		return null;
		// TODO �Զ����ɷ������
	}
	
	/**
	 *  ���������ѯ
	 *  ���ߣ� Meng.Wei[����:1186]
	 *  ���ڣ�2017-3-14 ����07:17:27
	 */
	public List<HashMap<String, String>> getPageList(PjjgForm t, User user)
		throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//���ݷ�Χ
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.id,a.xh,d.xm,d.xb,");
		sql.append("decode(a.cpnj, null, d.nj, a.cpnj) nj,");
		sql.append("decode(a.cpxydm, null, d.xydm, a.cpxydm) xydm,");
		sql.append("decode(a.cpxymc, null, d.xymc, a.cpxymc) xymc,");
		sql.append("decode(a.cpzydm, null, d.zydm, a.cpzydm) zydm,");
		sql.append("decode(a.cpzymc, null, d.zymc, a.cpzymc) zymc,");
		sql.append("decode(a.cpbjdm, null, d.bjdm, a.cpbjdm) bjdm,");
		sql.append("decode(a.cpbjmc, null, d.bjmc, a.cpbjmc) bjmc,");
		sql.append("a.xn,a.xmdm,a.xmmc,a.xmje,a.sqsj,a.sqly,a.ylzd1,a.ylzd2,a.ylzd3,a.ylzd4,a.ylzd5 fjid,");
//		sql.append("decode(a.sjly, '1', '��������', '2', '�����������', '0', '����') sjly,");
		sql.append("a.sjly,");
		sql.append("a.lxdm,c.lxmc,a.xzdm,b.xzmc,a.lylcywid,a.bjdw,d.xz,d.sfzh,d.zd5,d.zzmm,d.zzmmmc,d.mz,");
		/*��ӵ�����ֶ�*/
		sql.append("a.wysp,a.ssdh,a.gzzw,a.cjkyqk,a.dwrs,");
		sql.append("d.mzmc,d.yhmc,d.yhkh,d.sjhm,d.rxrq,d.jtdzxx,d.jtyb ");
		sql.append("from xg_zjdx_pjpy_pjjgb a ");
		sql.append("left join xg_zjdx_pjpy_xmxz b on b.xzdm = a.xzdm ");
		sql.append("left join xg_zjdx_pjpy_xmlx c on c.lxdm = a.lxdm ");
		sql.append("left join view_xsxxb d on d.xh = a.xh ");
		sql.append(" )t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append("order by xn,sqsj desc ");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @����: ��Ŀ���ͱ����ж�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����07:17:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdm,lxmc from  xg_zjdx_pjpy_xmlx order by lxmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ��Ŀ����list
	 * @���ߣ�  Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����07:17:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xzdm,xzmc from xg_zjdx_pjpy_xmxz order by xzmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ���ӱ����ж�
	 * @���ߣ�  Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����07:16:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForAddSave(PjjgForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjdx_pjpy_pjjgb where xh = ? and xn = ? and xmmc = ? ");
		String[] inputV = new String[] { model.getXh(),model.getXn(), model.getXmmc().trim()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	
	/**
	 * @����: �޸ı����ж�
	 * @���ߣ�  Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����07:16:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdateSave(PjjgForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjdx_pjpy_pjjgb where xh = ? and xn = ? and xmmc = ? and id <> ? ");
		String[] inputV = new String[] { model.getXh(),model.getXn(), model.getXmmc().trim(), model.getId()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	
	/**
	 * @����: ѧ��������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����07:14:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh,String xn){
		List<String> params = new ArrayList<String>();
		params.add(xh);
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.XH,a.XM,a.XB,a.XZ,a.XJZTM,a.SFZH,a.rxrq,a.ZZMM,a.ZZMMMC,a.MZ,a.MZMC,a.DZYX,a.SJHM,a.jtdzxx,a.jtyb, ");
		sql.append(" nvl(c.CPXYDM, a.XYDM) XYDM,nvl(c.CPXYMC, a.XYMC) XYMC,nvl(c.CPZYDM, a.ZYDM) ZYDM,nvl(c.CPZYMC, a.ZYMC) ZYMC, ");
		sql.append(" nvl(c.CPBJDM, a.BJDM) BJDM,nvl(c.CPBJMC, a.BJMC) BJMC,nvl(c.CPNJ, a.NJ) NJ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join (select * ");
		sql.append(" from (select a.*, ");
		sql.append(" row_number() over(partition by xh, xn order by sqsj desc) rn ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a ");
		sql.append(" where xn = (select xn from xg_zjdx_pjpy_csszb)) ");
		sql.append(" where rn = 1) c ");
		sql.append(" on a.xh = c.xh ");
		sql.append(" where rownum = 1 ");
		sql.append(" and a.xh = ? ");
		if(!StringUtil.isNull(xn)){
			params.add(xn);
			sql.append(" and c.xn = ? ");
		}
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * @����: ���������Ϣ�����鿴
	 * @���ߣ�  Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����07:15:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOnePjjgList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xn,a.xmmc,a.xmje,a.sqsj,a.sqly,a.cpnj,a.cpxymc,a.cpzymc,a.cpbjmc,a.cpxydm,a.cpzydm,a.cpbjdm,a.ylzd1,a.ylzd5, ");
		sql.append(" a.wysp,a.ssdh,a.gzzw, a.grxxjl,a.cjkyqk,a.dwrs, ");
		sql.append(" (select xzmc from xg_zjdx_pjpy_xmxz b where a.xzdm = b.xzdm) xzmc, ");
		sql.append(" (select lxmc from xg_zjdx_pjpy_xmlx c where a.lxdm = c.lxdm) lxmc ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a ");
		sql.append(" where id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}
	
	/**
	 * @����: ����ѧ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-22 ����11:19:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjXs(PjjgForm model, User user)
		throws Exception {
		/*���ɸ߼���ѯ�������������ֵ*/
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from ( ");
		sql.append("select t1.XH,t1.XM,t1.XB, ");
		sql.append("nvl(t5.XYDM, t1.XYDM) XYDM,nvl(t5.XYMC, t1.XYMC) XYMC,nvl(t5.ZYDM, t1.ZYDM) ZYDM,nvl(t5.ZYMC, t1.ZYMC) ZYMC,nvl(t5.bjdm, t1.bjdm) bjdm,nvl(t5.BJMC, t1.BJMC) BJMC,nvl(t5.NJ, t1.NJ) NJ,");
		sql.append("t1.SFZH,t1.LXDH,t1.XZ,t1.SJHM,t1.RXRQ,t1.YHKH,t1.YHDM,t2.YHMC,t1.CSRQ,t1.MZ,t1.MZMC,t1.ZZMM,t1.ZZMMMC  ");
		sql.append("from view_xsbfxx t1 ");
		sql.append("left join dmk_yh t2 on t1.yhdm = t2.yhdm ");
		sql.append("join (select t3.xh, t4.* from xg_zjdx_pjpy_cpmdb t3 ");
		sql.append("left join view_njxyzybj_all t4 on t3.bjdm = t4.bjdm ");
		sql.append("where xn in (select xn from xg_zjdx_pjpy_csszb)) t5 ");
		sql.append("on t1.xh = t5.xh where t5.bjdm is not null ");
		sql.append(") a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����: �ع������������¼
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-26 ����03:44:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delJgb(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zjdx_pjpy_pjjgb where sjly='1' and lylcywid = ?");
		return dao.runDelete(sql.toString(), new String[]{ywid});
	}
	
	/**
	 * @����: ��ѧԺ��ѯָ��������Ŀ�Ļ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-6-5 ����02:00:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzmeByXy(String xmdm, String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) tgrs,t3.xydm from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh=t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("where t1.xn = ? and t1.xmdm = ?");
		sql.append("group by t3.xydm");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}
	
	/**
	 * @����: ��ȫУ��ѯָ��������Ŀ�Ļ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����02:01:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzmeByQx(String xmdm, String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) tgrs from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("where t1.xn = ? and t1.xmdm = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}

	/**
	 *  ������������ѧ���Ĵ洢����.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-01-04 15:26
	 * @param proName
	 * @return boolean
	 * @throw Exception
	 */
	public boolean scyxxs(String proName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("call ");
		sql.append(proName);
		sql.append("()");
		return dao.runProcuder(sql.toString(), new String[]{});
	}

	/**
	 * @����: ��ȡƽ���ɼ�
	 * @���ڣ�2018-1-3 ����05:23:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * Map<String,String> ��������
	 * @throws
	 */
	public Map<String, String> getBjgcjNum(String xh,String xn,String xq){
		StringBuilder sql =  new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		sql.append("select count(*) num from view_zhhcjb  where cj <60 and xh=?");

		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ?");
			params.add(xn);
		}

		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			params.add(xq);
		}
		return dao.getMap(sql.toString(), params.toArray(new String[params.size()]), new String[]{"num"});
	}

	/**
	 * @����: ȡ�����������4������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-3 ����05:36:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHznydxPjpyMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append(" select sqsjs,xmmc,bjdw from ( select t1.bjdw,(select xzmc from xg_zjdx_pjpy_xmxz b where t1.xzdm = b.xzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ) where ROWNUM <= 4 ");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"sqsjs","xmmc","bjdw"});
	}

	/**
	 * @����: �����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-4 ����11:12:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.bjdw,(select xzmc from xg_zjdx_pjpy_xmxz b where t1.xzdm = b.xzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq = t2.xqdm where t1.xh = ? order by t1.xn desc,t1.xq desc");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj","sqsjs"});
	}
	
	/**
	 * @����: ȡѧ�����һ��������Ϣ����Ҫ��ȡ
	 *������ˮƽ������绰��������Ṥ��ְ�񡢸���ѧϰ�������μӿ�����������轱��λ����ʶ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-31 ����05:04:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.* from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t2 on t1.xmdm = t2.xmdm ");
		sql.append("left join xg_zjdx_pjpy_xmlx t3 on t2.lxdm = t3.lxdm ");
		sql.append("where t1.xh = ? ");
		sql.append("order by t1.sqsj desc ");
		sql.append(") where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
}

package xsgzgl.gyjc.jcrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.newp.ArrayUtil;

public class JcrcDao extends SuperDAOImpl<JcrcForm> {

	@Override
	public List<HashMap<String, String>> getPageList(JcrcForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JcrcForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select nvl(sum(case");
		sql.append(" when t1.tjzt = '1' then");
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end),");
		sql.append(" 0) ytjs,");
		sql.append(" nvl(sum(case");
		sql.append(" when t1.tjzt = '0' then");
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end),");
		sql.append(" 0) wtjs,");
		sql.append(" t.guid,");
		sql.append(" t.ccrq,");
		sql.append(" t.jzrq,");
		sql.append(" t.wsjc,");
		sql.append(" t.aqjc,");
		sql.append(" t.jljc,");
		sql.append(" decode(t.js,'xx','У��','xy','Ժ��','����Ա') js,");
		sql.append(" t.xq,");
		sql.append(" t.xn,");
		sql.append(" t.ls");
		sql.append(" from xg_jhzy_gygl_jcrc t");
		sql.append(" left join xg_jhzy_gygl_jcmx t1");
		sql.append(" on t.guid = t1.rcid");
		sql.append(" where  t.js = ?");
		paraList.add(t.getJs());
		if("xy".equals(user.getUserStatus())){
			sql.append(" and t.xydm = ? ");
			paraList.add(t.getXydm());
		}
		sql.append(" group by t.guid,");
		sql.append(" t.ccrq,");
		sql.append(" t.jzrq,");
		sql.append(" t.wsjc,");
		sql.append(" t.aqjc,");
		sql.append(" t.jljc,");
		sql.append(" t.js,");
		sql.append(" t.xq,");
		sql.append(" t.xn,");
		sql.append(" t.ls");
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);
		ArrayUtil arrayutil = new ArrayUtil();
		//��������
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(JcrcForm.class);
		this.setKey("guid");
		this.setTableName("xg_jhzy_gygl_jcrc");
	}
	
	/**
	 * 
	 * @����: ����ʱ�����ϸ�б�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-14 ����11:58:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcrcAddList(JcrcForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(t.getGuid());
		paraList.add(t.getJs());
		paraList.add(t.getXn());
		paraList.add(t.getXq());
		sql.append(" select *");
		sql.append(" from (select t.xydm,");
		sql.append(" t.xymc,");
		sql.append(" decode(ceil(nvl(t1.jcrs, 0) / t2.zs),'0','1',ceil(nvl(t1.jcrs, 0) / t2.zs)) ls,");
		sql.append(" case when nvl(t1.jcrs, 0) / t2.zs > 0 and (nvl(t1.jcrs, 0) / t2.zs)-floor(nvl(t1.jcrs, 0) / t2.zs) = 0");
		sql.append(" then 100");
		sql.append(" else ");
		sql.append(" trunc((nvl(t1.jcrs, 0) / t2.zs)-floor(nvl(t1.jcrs, 0) / t2.zs), 4) * 100 end jcbl,");
		sql.append(" t2.zs,(trunc(nvl(t1.bcrs, 0) / t2.zs, 4)) * 100 bcjcbl, nvl(t1.bcrs,0) bcrs");
		sql.append(" from (select distinct xydm, xymc from view_njxyzybj) t");
		sql.append(" left join ");
		sql.append(" (select distinct ");
		sql.append(" t1.xydm,");
		sql.append(" count(1) jcrs,");
		sql.append("  nvl(sum(case when t.guid = ? then 1 else 0 end),0) bcrs");
		sql.append(" ");
		sql.append(" from xg_jhzy_gygl_jcrc t");
		sql.append(" left join (select distinct   rcid,xydm,lddm,qsh from xg_jhzy_gygl_jcmx) t1");
		sql.append(" on t.guid = t1.rcid");
		sql.append(" where t.js = ?");
		sql.append(" and t.xn = ?");
		sql.append(" and t.xq = ?");
		sql.append(" group by t1.xydm) t1");
		sql.append(" on t.xydm = t1.xydm");
		sql.append(" left join (select count(1) zs, xydm");
		sql.append(" from view_xg_gygl_new_qsxx where rzqk != 'ȫ��'");
		sql.append(" group by xydm) t2");
		sql.append(" on t.xydm = t2.xydm");
		sql.append(" where nvl(t2.zs, 0) > 0");
		if(StringUtils.isNotNull(t.getXydm())){
			sql.append(" and t.xydm = ?");
			paraList.add(t.getXydm());
		}
		sql.append("  )");
		sql.append("  order by ls, jcbl, xydm");
		logger.debug(sql.toString());
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: �����ȡ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����08:53:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> produceSjqsList(JcrcForm t){
		StringBuilder sql = new StringBuilder();
		String[] xydms =  t.getXydms();
		String[] jcbls = t.getJcbls();
		String[] lddms = t.getLddms();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" (row_number() over(partition by t.xydm order by f.xh nulls last, t.rancnt) / zs) * 100 bfb");
		sql.append(" from (select t.xydm,");
		sql.append(" t.xymc,");
		sql.append(" t2.zs,");
		sql.append(" t1.LDDM,");
		sql.append(" t1.QSH,");
		sql.append(" dbms_random.value(1, 9999999) rancnt");
		sql.append(" from (select distinct xydm, xymc from view_njxyzybj) t");
		sql.append(" left join  view_xg_gygl_new_qsxx t1");
		sql.append(" on t.xydm = t1.XYDM");
		sql.append(" left join (select count(1) zs, xydm");
		sql.append(" from view_xg_gygl_new_qsxx  where rzqk != 'ȫ��'");
		sql.append(" group by xydm) t2");
		sql.append(" on t.xydm = t2.xydm");
		sql.append(" where t1.rzqk != 'ȫ��' and t1.lddm || t1.qsh not in (");
        sql.append(this.getGlData());
		paraList.add(t.getJs());
		paraList.add(t.getXn());
		paraList.add(t.getXq());
		paraList.add(t.getJs());
		paraList.add(t.getXn());
		paraList.add(t.getXq());
		sql.append(" )");
		sql.append(" and nvl(t2.zs, 0) > 0) t");
		sql.append(" left join  (");
		sql.append(" select '999999999999' lddm,'0' xh,'9999999999999999' xydm from dual ");
		if(lddms != null && lddms.length >0){
			sql.append(" union all ");
			for (int i = 0; i < lddms.length; i++) {
				sql.append(" select ? lddm, ? xh,? xydm from dual");
				paraList.add(lddms[i].split("-")[0]);
				int x = i+1;
				paraList.add(x+"");
				paraList.add(lddms[i].split("-")[1]);
				if( i != lddms.length-1){
					sql.append(" union all ");
				}
			}
		}

		sql.append(" )f on t.lddm = f.lddm and t.xydm = f.xydm");
		
		sql.append(" )where 1=1 and (");
		//ע�⣺�����޸ĺ��ü���ճ̳�ȡ����ʱ�ǿ���ѡȡ�ض���¥����,��������������쳣����Ҫ��������д���
		ArrayList<ArrayList<String>> xydmList = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < xydms.length; i++) {
			ArrayList<String> tempList = new ArrayList<String>();
			if(lddms != null && lddms.length > 0){
				for (int j = 0; j < lddms.length; j++) {
					String[] tempArray = lddms[j].split("-");
					if(tempArray[1].equals(xydms[i])){
						tempList.add(tempArray[0]);
					}
				}
			}
			
			xydmList.add(tempList);
		}
		for (int i = 0; i < xydms.length; i++) {
				sql.append(" (xydm = ? and bfb <= ?");
				paraList.add(xydms[i]);
				paraList.add(jcbls[i]);
				List<String> tempList = xydmList.get(i);
				if(tempList != null && tempList.size() >0){
					sql.append("and lddm in (");
					for (int j = 0; j < tempList.size(); j++) {
						sql.append("?");
						paraList.add(tempList.get(j));
						if(j != tempList.size()-1){
							sql.append(",");
						}
					}
					sql.append(")");
				}
					
				sql.append(")");
				
				if(i != xydms.length-1){
					sql.append(" or ");
				}
		}
		sql.append(" )");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ������ճ̱��е�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����10:29:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJcrcB(String[] guids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_jhzy_gygl_jcrc where guid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����10:29:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJcblB(String[] rcids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from  xg_jhzy_gygl_jcbl where rcid in(");
		for (int i = 0; i < rcids.length; i++) {
			sql.append(" ?");
			if(i != rcids.length-1){
				sql.append(" ,");
			}
			paraList.add(rcids[i]);
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�������ϸ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����10:29:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJcmxB(String[] rcids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_jhzy_gygl_jcmx where rcid in(");
		for (int i = 0; i < rcids.length; i++) {
			sql.append(" ?");
			if(i != rcids.length-1){
				sql.append(" ,");
			}
			paraList.add(rcids[i]);
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));

	}
	
	/**
	 * 
	 * @����: ɾ�������ϸ��׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����05:50:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJcmxBz(String rcid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_jhzy_gygl_jcmxbz where mxid in(");
		sql.append(" select guid from xg_jhzy_gygl_jcmx where rcid = ?");
		sql.append(" )");
		return dao.runUpdate(sql.toString(),new String[]{rcid});

	}
	
	/**
	 * 
	 * @����: �������ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����02:28:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJcrc(JcrcForm t) throws Exception{
		if("update".equals(t.getType())){
			return runUpdateNotCommit(t);
		}else{
			return runInsertNotCommit(t);
		}
	}
	
	/**
	 * 
	 * @����: ���������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����02:42:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJcbl(List<String[]> jcblList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jhzy_gygl_jcbl(rcid,xydm,jcbl)values(?,?,?)");
		return dao.runBatchNotCommit(sql.toString(), jcblList);
	} 
	
	/**
	 * 
	 * @����: ��������ϸ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����02:54:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sjsList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJcmx(List<String[]> jcmxList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jhzy_gygl_jcmx(rcid,xydm,lddm,qsh,jjlx,tjzt)values(?,?,?,?,?,'0')");
		return dao.runBatchNotCommit(sql.toString(), jcmxList);
	}
	
	/**
	 * 
	 * @����: �޸�ʱ��ȡjcbl��ϸ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����03:41:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getUpdatejcrcmx(String rcid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jhzy_gygl_jcbl where rcid = ?");
		return dao.getListNotOut(sql.toString(), new String[]{rcid});
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ����ύ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����03:53:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfExistTj(String rcid ){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_jhzy_gygl_jcmx where rcid = ? and tjzt = '1'");
		String cnt = dao.getOneRs(sql.toString(),new String[]{rcid}, "cnt");
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * 
	 * @����: ��֤��ǰ�Ƿ�Ϊ���¼���ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����04:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfLastNew(String rcid,String js,String xydm){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select guid from (select  guid from xg_jhzy_gygl_jcrc where js = ? ");
		paraList.add(js);
		if(StringUtils.isNotNull(xydm)){
			sql.append(" and xydm = ? ");
			paraList.add(xydm);
		}
		sql.append(" order by jzrq desc) where rownum = 1");
		String lastrcid = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "guid");
		return rcid.equals(lastrcid) ? true : false;
	}
	
	/**
	 * 
	 * @����: ��֤�����Ƿ��н���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����04:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccrq
	 * @param jzrq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfRqIntersection(String ccrq,String jzrq,String rcid,String js,String xydm){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_jhzy_gygl_jcrc where 1= 1 ");
		if(StringUtils.isNotNull(rcid)){
			sql.append(" and guid != ? ");
			paraList.add(rcid);
		}
		sql.append(" and js = ?");
		paraList.add(js);
		if(StringUtils.isNotNull(xydm)){
			sql.append(" and guid in (select rcid from  xg_jhzy_gygl_jcbl where xydm  = ? )");
			paraList.add(xydm);
		}
		sql.append(" and ( jzrq >= ? and ccrq <= ?)");
		paraList.add(ccrq);
		paraList.add(jzrq);
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-11 ����07:20:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getGlData(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select qsh from (");
		sql.append(" select t.qsh,t.xydm,nvl(t2.rs,0)/t1.zs bfb,t1.zs,t2.rs,decode(instr(nvl(t2.rs,0)/t1.zs, '.') + sign(nvl(t2.rs,0)/t1.zs), 1, 1, 0)zt from ");
		sql.append(" (select a.lddm || a.qsh qsh,a.xydm");
		sql.append(" from (select t.qsh,");
		sql.append(" t.lddm,");
		sql.append(" t.xydm,");
		sql.append(" rank() over(partition by t.xydm order by count(distinct t.lddm || t.qsh || t.rcid) desc) ro");
		sql.append(" from xg_jhzy_gygl_jcmx t");
		sql.append(" left join xg_jhzy_gygl_jcrc t1");
		sql.append(" on t.rcid = t1.guid");
		sql.append("  where t1.js = ?");
		sql.append("  and t1.xn = ?");
		sql.append(" and t1.xq = ?");
		sql.append(" group by t.qsh, t.lddm, t.xydm) a");
		sql.append(" where ro = 1) t");
		sql.append(" left join ");
		sql.append(" (select count(1) zs, xydm");
		sql.append("  from view_xg_gygl_new_qsxx  where rzqk != 'ȫ��' group by xydm)t1");
		sql.append("  on t.xydm = t1.xydm");
		sql.append("  left join");
		sql.append("  (select count(1) rs, t.xydm");
		sql.append(" from (select distinct   rcid,xydm,lddm,qsh from xg_jhzy_gygl_jcmx) t");
		sql.append("  left join xg_jhzy_gygl_jcrc t1");
		sql.append("  on t.rcid = t1.guid");
		sql.append("  where t1.js = ?");
		sql.append(" and t1.xn = ?");
		sql.append(" and t1.xq = ? group by t.xydm)t2");
		sql.append(" on t.xydm = t2.xydm) where zt != '1'");
		return sql.toString();
		
	}
	
	/**
	 * 
	 * @����: ��ȡѡ��¥����ϢList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-23 ����05:37:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSelLdList(JcrcForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select t.lddm, count(1) cnt,t1.ldmc,t.xydm from");
		sql.append(" (select distinct lddm,qsh,xydm from view_xg_gygl_new_qsxx where xydm = ? and rzqk != 'ȫ��'");
		paraList.add(t.getXydm());
		sql.append(" and lddm || qsh not in (");
		sql.append(this.getGlData());
		sql.append(" )");
		paraList.add(t.getJs());
		paraList.add(t.getXn());
		paraList.add(t.getXq());
		paraList.add(t.getJs());
		paraList.add(t.getXn());
		paraList.add(t.getXq());
		sql.append(" union");
		sql.append(" select distinct lddm,qsh,xydm  from xg_jhzy_gygl_jcmx where rcid = ? and xydm = ?");
		paraList.add(t.getGuid());
		paraList.add(t.getXydm());
		sql.append(" )t");
		sql.append(" left join xg_gygl_new_ldxxb t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append(" group by t.lddm, t1.ldmc,t.xydm");
		sql.append(" order by t.lddm");
		
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: �޸�ʱ��ȡ�����ճ̳��˶��ٸ�¥�������ٸ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-24 ����11:22:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdxxList(String rcid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xydm, t.lddm, t1.ldmc, count(distinct t.xydm || t.lddm || t.qsh) cnt");
		sql.append(" from xg_jhzy_gygl_jcmx t");
		sql.append(" left join xg_gygl_new_ldxxb t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append(" where rcid = ?");
		sql.append(" group by t.lddm, t1.ldmc, t.xydm");
		sql.append(" order by t.xydm,t.lddm");
		return dao.getListNotOut(sql.toString(), new String[]{rcid});
	}
	
}

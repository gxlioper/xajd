package xsgzgl.gygl.gyccgl.ccdj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.newp.ArrayUtil;

public class CcdjDao extends SuperDAOImpl<CcdjForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CcdjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/**
	 * 
	 * @����: ��ѯ���������õ��÷���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����02:15:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	@Override
	public List<HashMap<String, String>> getPageList(CcdjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
	    List<HashMap<String, String>> wpList = getWpList();
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjbyGygly = SearchService.getGyglyQx(t.getGyglyQx(),user.getUserName(), t.getPath());
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.ldmc,");
		sql.append(" t1.ch,");
		sql.append(" t2.cnt,");
		sql.append(" t3.bjmc,");
		sql.append(" t4.xqmc,");
		for (int i = 0; i < wpList.size(); i++) {
			sql.append(" (select x.shcdmc ");
			sql.append(" from view_qswpshb x");
			sql.append(" where t.lddm = x.lddm");
			sql.append(" and t.xn = x.xn");
			sql.append(" and t.xq = x.xq");
			sql.append(" and t.qsh = x.qsh");
			sql.append(" and x.wpdm = ?) shcd"+i);
			paraList.add(wpList.get(i).get("dm"));
			if(i != wpList.size()-1){
				sql.append(",");
			}
		}
		sql.append(" from xg_gygl_new_ssccgl_ccdjb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm and t.qsh = t1.qsh");
		sql.append(" left join (select count(1) cnt, xn, xq, qsh, lddm");
		sql.append(" from xg_gygl_new_ssccgl_qswpshb");
		sql.append(" group by xn, xq, qsh, lddm) t2");
		sql.append(" on t.lddm = t2.lddm");
		sql.append(" and t.xn = t2.xn");
		sql.append(" and t.xq = t2.xq");
		sql.append(" and t.qsh = t2.qsh");
		sql.append(" left join (");
		sql.append(" select replace(wm_concat(bjmc), ';', ',') bjmc, lddm, qsh");
		sql.append(" from (select distinct t1.bjmc, t.lddm, t.qsh");
		sql.append(" from xg_gygl_new_cwxxb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH)");
//		sql.append(" where t.xh is not null");
//		sql.append(" and t.sfbl = '��')");
		sql.append(" group by lddm, qsh");
		sql.append(" ) t3");
		sql.append(" on t.lddm = t3.lddm");
		sql.append(" and t.qsh = t3.qsh");
		sql.append(" left join xqdzb t4 on t.xq=t4.xqdm");
		sql.append(" ) a where 1=1");
		ArrayUtil arrayutil = new ArrayUtil();
		//��������
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		sql.append(searchTjbyGygly);
		sql.append(searchTj);
		sql.append(" order by xn desc,xq desc, lddm,ch,qsh ");
		return getPageList(t, sql.toString(), inputVnew);
	}
	
	/** 
	 * @����:�ൺ�Ƶ����(�����༶���г���������ƽ�����ý��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-26 ����10:05:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForDc(CcdjForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		List<HashMap<String, String>> wpList = getWpList();
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjbyGygly = SearchService.getGyglyQx(t.getGyglyQx(),user.getUserName(), t.getPath());
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.ldmc,");
		sql.append(" t1.ch,");
		sql.append(" t2.cnt,");
		sql.append(" t3.bjmc,");
		sql.append(" t4.xqmc,");
		sql.append(" round(case t5.bjs when 0 then to_number(t.zje) else to_number(t.zje)/to_number(t5.bjs) end) avgje,");
		for (int i = 0; i < wpList.size(); i++) {
			sql.append(" (select x.shcdmc ");
			sql.append(" from view_qswpshb x");
			sql.append(" where t.lddm = x.lddm");
			sql.append(" and t.xn = x.xn");
			sql.append(" and t.xq = x.xq");
			sql.append(" and t.qsh = x.qsh");
			sql.append(" and x.wpdm = ?) shcd"+i);
			paraList.add(wpList.get(i).get("dm"));
			if(i != wpList.size()-1){
				sql.append(",");
			}
		}
		sql.append(" from xg_gygl_new_ssccgl_ccdjb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm and t.qsh = t1.qsh");
		sql.append(" left join (select count(1) cnt, xn, xq, qsh, lddm");
		sql.append(" from xg_gygl_new_ssccgl_qswpshb");
		sql.append(" group by xn, xq, qsh, lddm) t2");
		sql.append(" on t.lddm = t2.lddm");
		sql.append(" and t.xn = t2.xn");
		sql.append(" and t.xq = t2.xq");
		sql.append(" and t.qsh = t2.qsh");
		sql.append(" left join (");
		sql.append(" select distinct t1.bjmc, t.lddm, t.qsh");
		sql.append(" from xg_gygl_new_cwxxb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" ) t3");
		sql.append(" on t.lddm = t3.lddm");
		sql.append(" and t.qsh = t3.qsh");
		sql.append(" left join xqdzb t4 on t.xq=t4.xqdm");
		sql.append(" left join ");
		sql.append(" (select count(bjmc) bjs,lddm,qsh from (select distinct t1.bjmc,t.lddm,t.qsh from xg_gygl_new_cwxxb t left join view_xsbfxx t1 on t.xh = t1.xh) group by lddm,qsh) t5 on t1.lddm = t5.lddm and t1.qsh = t5.qsh");
		sql.append(" ) a where 1=1");
		ArrayUtil arrayutil = new ArrayUtil();
		//��������
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		sql.append(searchTjbyGygly);
		sql.append(searchTj);
		sql.append(" order by xn desc,xq desc, lddm,ch,qsh ");
		return getPageList(t, sql.toString(), inputVnew);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(CcdjForm.class);
		this.setKey("id");
		this.setTableName("xg_gygl_new_ssccgl_ccdjb");
	}
	
	/**
	 * 
	 * @����: ��ȡ��ƷList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����02:15:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t.mc wpmc,t.dm wpdm from xg_gygl_new_ssccgl_wpwhb t order by to_number(dm) asc ");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���𻵳̶Ƚ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����05:03:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delQswpshbJg(String[] ids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_new_ssccgl_qswpshb ");
		sql.append(" where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append(" ?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdateNotCommit(sql.toString(), ids);
	}
	
	/**
	 * 
	 * @����: ɾ���Ʋ��ǼǱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����05:28:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delCcdjbJg(String[] ids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_new_ssccgl_ccdjb ");
		sql.append(" where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append(" ?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdateNotCommit(sql.toString(), ids);
	}
	
	/**
	 * 
	 * @����: ��ȡ¥������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:02:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(CcdjForm ccdj){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm, ldmc from XG_GYGL_NEW_LDXXB where 1=1 ");
		String searchTjBygygly = SearchService.getGyglyQx(ccdj.getGyglyQx(), ccdj.getUsername(), ccdj.getPath());
		sql.append(searchTjBygygly);
		sql.append(" order by lddm asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:04:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm,qsh from XG_GYGL_NEW_QSXXB where lddm = ? and ch = ? order by qsh");
		return dao.getListNotOut(sql.toString(), new String[]{lddm,ch});
	}
	
	/**
	 * 
	 * @����: ��ȡ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm,ch,ch || '��' chmc from XG_GYGL_NEW_QSXXB where lddm = ? order by ch");
		return dao.getListNotOut(sql.toString(),new String[]{lddm});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����10:20:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShcdList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_shcdwhb order by shcddm asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ�Ʋ��Ǽ���Ϣmap
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����10:40:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCcdjMap(String id){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t.*, t1.ldmc, t1.ch, t1.qsh,t2.xqmc");
		sql.append(" from xg_gygl_new_ssccgl_ccdjb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append(" and t.qsh = t1.qsh");
		sql.append(" left join xqdzb t2");
		sql.append(" on t.xq = t2.xqdm");
		sql.append(" where t.id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ʒά��List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����11:23:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQswpshbList(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_qswpshb where id = ?");
		return dao.getListNotOut(sql.toString(), new String[]{id});
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����������ҲƲ��𻵱�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����01:50:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int[] �������� 
	 * @throws
	 */
	public boolean runBatchQswpshb(CcdjForm ccdjform) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_ssccgl_qswpshb(id,xn,xq,lddm,qsh,wpdm,shcd)");
		sql.append(" values(?,?,?,?,?,?,?)");
		List<String[]> params = new ArrayList<String[]>();
		String[] wpdms = ccdjform.getWpdms();
		String[] shcds = ccdjform.getShcds();
		for (int i = 0; i < wpdms.length; i++) {
			if(StringUtils.isNotNull(wpdms[i]) && StringUtils.isNotNull(shcds[i])){
				params.add(new String[]{ccdjform.getId(),ccdjform.getXn(),ccdjform.getXq(),ccdjform.getLddm(),ccdjform.getQsh(),wpdms[i],shcds[i]});
			}
		}
		return dao.runBatchNotCommit(sql.toString(), params);
	}
	
	/**
	 * 
	 * @����: ��ȡ�鿴ʱ��Ҫ��ѯ���𻵳̶�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����03:16:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getViewWpShcdList(String id){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_qswpshb where id = ? order by wpdm");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ڲƲ��ǼǱ��д����ظ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����05:59:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccdjform
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsExistNotInCcdjb(CcdjForm ccdjform){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_ccdjb where xn = ?");
		sql.append(" and xq=? and lddm = ? and qsh = ? ");
		String cnt =  dao.getOneRs(sql.toString(), new String[]{ccdjform.getXn(),ccdjform.getXq(),ccdjform.getLddm(),ccdjform.getQsh()}, "cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * 
	 * @����: ���Һţ�ѧ�꣬ѧ������ʱ��ȡ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-9 ����10:10:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccdjform
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getViewWpList(CcdjForm ccdjform){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select nvl(t1.wpmc,t.mc) wpmc,nvl(t1.WPDM,t.dm) wpdm,t1.ID,t1.XN");
		sql.append(" ,t1.XQ,t1.LDDM,t1.QSH,t1.SHCD,t1.shcdmc,t1.je");
		sql.append(" from xg_gygl_new_ssccgl_wpwhb t");
		sql.append(" left join  (select * from view_qswpshb x where  1 =1");
		if(StringUtils.isNotNull(ccdjform.getId())){
			sql.append(" and (x.id = ?)");
			paraList.add(ccdjform.getId());
		}else{
			sql.append(" and (x.xn = ?) and (x.xq = ?)  and (x.lddm = ?)  and (x.qsh = ?)  ");
			paraList.add(ccdjform.getXn());
			paraList.add(ccdjform.getXq());
			paraList.add(ccdjform.getLddm());
			paraList.add(ccdjform.getQsh());
		}
		
		sql.append(")t1 on t.dm = t1.WPDM");
		sql.append(" where (1=1)");
		if(StringUtils.isNotNull(ccdjform.getId())){
			sql.append(" and (t1.id = ? or t1.id is null)");
			paraList.add(ccdjform.getId());
		}else{
			sql.append(" and (t1.xn = ? or t1.xn is null) and (t1.xq = ? or t1.xq is null)  and (t1.lddm = ? or t1.lddm is null)  and (t1.qsh = ? or t1.qsh is null)  ");
			paraList.add(ccdjform.getXn());
			paraList.add(ccdjform.getXq());
			paraList.add(ccdjform.getLddm());
			paraList.add(ccdjform.getQsh());
		}
		sql.append(" order by t1.xn,t1.wpdm");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ¥��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-13 ����03:30:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param searchTjBygygly
	 * @param ldmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkLdmcDryz(CcdjForm ccdj,String ldmc,String qsh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select lddm from view_xg_gygl_new_qsxx where 1=1 ");
		String searchTjBygygly = SearchService.getGyglyQx(ccdj.getGyglyQx(), ccdj.getUsername(), ccdj.getPath());
		sql.append(searchTjBygygly);
		sql.append(" and ldmc = ?");
		sql.append(" and qsh = ?");
		String lddm = dao.getOneRs(sql.toString(), new String[]{ldmc,qsh}, "lddm");
		return lddm;
	}
	
	/**
	 * 
	 * @����:�����������ݵ��Ʋ��ǼǱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-14 ����09:54:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public boolean saveDrDataIntoZb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_ssccgl_ccdjb( ");
		sql.append(" id, ");
		sql.append(" xn,");
		sql.append(" xq,");
		sql.append(" lddm,");
		sql.append(" qsh,");
		sql.append(" zje,");
		sql.append(" bz");
		sql.append(" )values(?,?,?,?,?,?,?)");
 		return dao.runBatchNotCommit(sql.toString(), paralist);
 		
	}
	
	/**
	 * 
	 * @����: ��������������Ʒ�𻵱�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-14 ����10:05:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public boolean saveDrDataIntoFb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_ssccgl_qswpshb( ");
		sql.append(" id, ");
		sql.append(" xn,");
		sql.append(" xq,");
		sql.append(" lddm,");
		sql.append(" qsh,");
		sql.append(" wpdm,");
		sql.append(" shcd");
		sql.append(" )values(?,?,?,?,?,?,?)");
 		return dao.runBatchNotCommit(sql.toString(), paralist);
 		
	}
	
	/**
	 * 
	 * @����:ɾ���Ʋ��ǼǱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-14 ����10:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public boolean delCcdjb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_new_ssccgl_ccdjb where xn = ? and xq = ? and lddm = ? and qsh = ?");
		return dao.runBatchNotCommit(sql.toString(),paralist);
	}
	
	/**
	 * 
	 * @����:ɾ���𺦳̶�ά����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-14 ����10:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public boolean delQswpshb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_new_ssccgl_qswpshb where xn = ? and xq = ? and lddm = ? and qsh = ?");
		return dao.runBatchNotCommit(sql.toString(),paralist);
	}
	
    /**
     * 
     * @����: ��ȡ������Ҫ�ķ�������
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2017-3-14 ����01:56:17
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param t
     * @param user
     * @return
     * @throws Exception
     * List<HashMap<String,String>> �������� 
     * @throws
     */
	public List<HashMap<String, String>> getGroupLddmList(CcdjForm t)
	throws Exception {
// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjBygygly = SearchService.getGyglyQx(t.getGyglyQx(), t.getUsername(), t.getPath());
		sql.append(" select distinct lddm, ldmc from (");
		sql.append(" select t.*,");
		sql.append(" t1.ldmc,");
		sql.append(" t1.ch ");
		sql.append(" from xg_gygl_new_ssccgl_ccdjb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm and t.qsh = t1.qsh");
		sql.append(" left join xqdzb t4 on t.xq=t4.xqdm");
		sql.append(" ) a where 1=1 ");
		sql.append(searchTjBygygly);
		sql.append(searchTj);
		sql.append(" order by lddm ");
		return dao.getListNotOut(sql.toString(), inputV);
    }
}

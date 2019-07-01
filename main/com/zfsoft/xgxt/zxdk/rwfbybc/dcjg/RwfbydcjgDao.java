/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-8 ����02:58:17 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-9-8 ����02:58:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwfbydcjgDao extends SuperDAOImpl<RwfbydcjgModel> {

	@Override
	public List<HashMap<String, String>> getPageList(RwfbydcjgModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RwfbydcjgModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t2.yhkh, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,x1.sydm,x1.symc,t3.mc dclbmc,t10.mc yjxfmc ");
		sql.append("from xg_zxdk_rwfbydcjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf=t10.dm ");
		sql.append(" left join dmk_yh t11 on t1.yhdm=t11.yhdm ");
		sql.append("left join xg_zxdk_dclbdmb t3 on t1.dclb=t3.dm ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public RwfbydcjgModel getModel(String id) throws Exception {
		 	StringBuilder sql = new StringBuilder();
			sql.append("select * from (");
			sql.append("select t1.*,t3.mc yjxfmc,t4.mc dclbmc,t10.yhmc ");
			sql.append("from xg_zxdk_rwfbydcjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
			sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
			sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ");
			sql.append("left join xg_zxdk_dclbdmb t4 on t1.dclb=t4.dm ) t where id = ? ");
			return getModel(sql.toString(),new String[]{id});
	}

	/**
	 * 
	 * @����:��֤�Ƿ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExists(RwfbydcjgModel model) {	
		String sql = "select count(1) count from xg_zxdk_rwfbydcjgb where xh = ?";
		String num = dao.getOneRs(sql, new String[]{model.getXh()}, "count");
		return Integer.parseInt(num) > 0?true:false;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��˵�ʱ����֤�����Ƿ����������ڸ���ѧ��ɾ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-10 ����11:31:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteByKey(String key) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zxdk_rwfbydcjgb where xh = ? ");
		return dao.runUpdate(sql.toString(), new String[]{key});
		
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_rwfbydcjgb");
		super.setKey("id");
		super.setClass(RwfbydcjgModel.class);
	}
	
	/**
	 *
	 * @����:����ʦ����ѧ���۴������listѧ����Ϣ�鿴�Զ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-7 ����09:14:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRwdcjglist(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,");
		sql.append(" t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc dclbmc,t10.mc yjxfmc ");
		sql.append(" from xg_zxdk_rwfbydcjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf=t10.dm ");
		sql.append(" left join dmk_yh t11 on t1.yhdm=t11.yhdm ");
		sql.append(" left join xg_zxdk_dclbdmb t3 on t1.dclb=t3.dm  where t1.xh = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 * @throws SQLException  
	 * @����:�����������������η��ŵ���Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��13�� ����3:57:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffcsList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveDcjgffsc(String xh,String xn,List<DcjgffcsModel> ffcsList) throws SQLException {
		String sql = "insert into xg_zxdk_rwfbydcjg_ffcsb (xh,xn,ffsj,ffje,ffnr) values(?,?,?,?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for(DcjgffcsModel model:ffcsList){
			String [] param = {xh,xn,model.getFfsj(),model.getFfje(), model.getFfnr()};
			paramList.add(param);
		}
		int [] results = dao.runBatch(sql, paramList);
		
		return true;
	}

	/** 
	 * @����:����ѧ�š�ѧ���ѯ���������Ӧ�ķ��Ŵ�����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����8:55:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDcjgffcsList(String xh, String xn) {
		String sql = "select ffsj,ffje,ffnr from xg_zxdk_rwfbydcjg_ffcsb where xh = ? and xn = ? order by to_date(ffsj,'yyyy-mm-dd')";
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn});
	}

	/**
	 * @throws Exception  
	 * @����:����ѧ�š�ѧ��ɾ������������Ŵ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����10:06:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteDcjgffsc(List<HashMap<String,String>> xhxnList) throws Exception {
		String sql = "delete from xg_zxdk_rwfbydcjg_ffcsb where xh = ? and xn = ?";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for(HashMap<String,String> map:xhxnList){
			String [] param = {map.get("xh"),map.get("xn")};
			paramList.add(param);
		}
		int [] results = dao.runBatch(sql, paramList);
		
		return true;
	}

	/** 
	 * @����:���ݴ������id���ֲ�ѯ���ѧ�ţ�ѧ�꼯��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����12:38:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param idArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXhxnList(String[] idArr) {
		StringBuilder sql = new StringBuilder("select xh,xn from xg_zxdk_rwfbydcjgb where ");
		for (int i = 0 , n = idArr.length ; i < n ; i++){
			sql.append("id");
			sql.append("=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), idArr);
	}

	
	//=================���Ի����뵼�����Զ�����=====================
	/** 
	 * @����:��ѯ����ģ����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����10:05:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDrmbxx(String drmkdm) {
		String sql = "select drmkdm,drmkmc,drbmc from zfxg_drsj_drpz where drmkdm = ?";
		HashMap<String,String> drmbxx = dao.getMapNotOut(sql, new String[]{drmkdm});
		return drmbxx;
	}

	/** 
	 * @����:��ѯ��������б���Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����10:05:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDrgzxxList(String drmkdm) {
		String sql = "select drl,drlmc,lsjgsh,gshxx,sfzj,sfbt,zdcd,sfwy from ZFXG_DRSJ_DRLPZ where drmkdm = ? order by to_number(xsxx)";
		List<HashMap<String,String>> drgzxxList = dao.getListNotOut(sql, new String[]{drmkdm});
		return drgzxxList;
	}

	/** 
	 * @����: ��ѯ���븨������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����5:05:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDrfzxxList(String drmkdm) {
		String sql = "select pz,fzmc from ZFXG_DRSJ_FZB where drmkdm = ?";
		return dao.getListNotOut(sql, new String[]{drmkdm});
	}

	/** 
	 * @����:��ȡ���븨������������Ϣ�б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����6:09:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tableName
	 * @param outputValues
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFzdmxxList(String tableName, String[] outputValue,String sortCol) {
		String sql = "select * from " + tableName;
		if(!StringUtil.isNull(sortCol)){
			sql += " order by "+sortCol;
		}
		return dao.getList(sql, new String[]{}, outputValue);
	}

	/** 
	 * @����:����ʱ��֤����sql���ò�ѯĳ��Ԫ�����ݲ����أ�����������滻����������������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��20�� ����4:34:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sql
	 * @param cellContents
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String changeCellData(String sql, String cellContents,String drl) {
		return dao.getOneRs(sql.toString(), new String[]{cellContents}, drl);
	}

	/**
	 * @throws SQLException  
	 * @����:����������������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��22�� ����5:32:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dcjgParaList
	 * @return
	 * int [] �������� 
	 * @throws 
	 */
	public int[] insertDcjgDataIntoDB(List<String[]> dcjgParaList) throws SQLException {
		StringBuilder sql = new StringBuilder("insert into xg_zxdk_rwfbydcjgb(xh,xn,rwqsfsqdc,xfje,dkbj,");
		sql.append("yhdm,dkhth,dkkssj,dkjssj,dclb,rwnf,twnf) values (?,?,?,?,?,?,?,?,?,?,?,?)");
		return dao.runBatch(sql.toString(), dcjgParaList);
	}

	/**
	 * @throws SQLException  
	 * @����:�����������������Ŵ�����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��22�� ����5:33:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dcjg_ffcsParaList
	 * @return
	 * int [] �������� 
	 * @throws 
	 */
	public int[] insertDcjgFfcsDataIntoDB(List<String[]> dcjg_ffcsParaList) throws SQLException {
		String sql = "insert into xg_zxdk_rwfbydcjg_ffcsb (xh,xn,ffsj,ffje,ffnr) values(?,?,?,?,?)";
		return dao.runBatch(sql, dcjg_ffcsParaList);
	}

	/** 
	 * @����:��֤�����ظ����Դ��������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��23�� ����12:43:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drl
	 * @param cellContents
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isRepeatForDr(String drl, String cellContents) {
		StringBuilder sql = new StringBuilder("select ");
		sql.append(drl);
		sql.append(" from xg_zxdk_rwfbydcjgb where ");
		sql.append(drl);
		sql.append("=?");
		List<HashMap<String,String>> resultList = dao.getListNotOut(sql.toString(), new String[]{cellContents});
		return resultList.size()>0;
	}

	/**
	 * @throws Exception 
	 * @����:����ʱ��ѯ���м�¼���������Ŵ�����Ϣ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��24�� ����10:52:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDcListForZD(RwfbydcjgModel t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder("select * from (");
		sql.append("select t1.*, t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj  sjdh, ");
		sql.append("t2.dzyx,t2.yhkh,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc dclbmc,t10.mc  yjxfmc,t4.ffsj,t4.ffje,t4.ffnr");
		sql.append(" from xg_zxdk_rwfbydcjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf = t10.dm");
		sql.append(" left join dmk_yh t11 on t1.yhdm = t11.yhdm");
		sql.append(" left join xg_zxdk_dclbdmb t3 on t1.dclb = t3.dm");
		sql.append(" left join (select xh,xn, replace(wm_concat(ffsj),',','|') ffsj,replace(wm_concat(ffje),',','|') ffje,");
		sql.append("replace(wm_concat(replace(ffnr,',','��')),',','|') ffnr from xg_zxdk_rwfbydcjg_ffcsb group by xh,xn) t4");
		sql.append(" on t1.xh = t4.xh ) t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}

}

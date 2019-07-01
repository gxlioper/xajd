package xsgzgl.gygl.cssz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class CsszDAO {
	/**
	 * ��ѯʱ������
	 * @param model
	 * @return
	 */
	public List<String[]> querySjsz(CsszModel model) throws Exception{
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		List<String[]> list = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.* from (select a.nj||a.xydm pkValue,a.nj,a.xydm,b.bmmc xymc,c.kssj,c.jssj,nvl(c.sfqy, '��') sfqy from ")
			.append("(select distinct nj,xydm from view_xsjbxx a ")
			//.append("where exists (select 1 from view_njxyzybj b where b.nj = a.nj and b.xydm = a.xydm) ")
			.append("order by nj,xydm) a left join zxbz_xxbmdm b on a.xydm=b.bmdm ")
			.append("left join xg_gygl_new_qxrzkgkzb c on a.xydm=c.xydm and a.nj=c.nj order by nj desc,xydm) a where 1=1 ");
		
		String[] outputs = new String[]{"pkValue", "nj", "xymc", "kssj", "jssj", "sfqy"};			
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, outputs, model);
		
		return list;
	}
	
	/**
	 * ���濪��״̬
	 * @param model
	 * @return
	 */
	public boolean saveKqzt(CsszModel model) {
		DAO dao = DAO.getInstance();
		
		boolean bz = false;
		
		// ����
		String[] pks = model.getPrimarykey_cbv();
		String sfqy = model.getSfqy();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		
		String sqlArr[] = new String[pks.length];
		
		boolean[] rs = dao.checkExists("xg_gygl_new_qxrzkgkzb", "nj||xydm", pks);
		int count = 0;
		
		for(int i=0; i<rs.length; i++){
			boolean flag = rs[i];
			String nj = pks[i].substring(0,4);
			String xydm = pks[i].substring(4);
			
			if(flag){
				if("yes".equalsIgnoreCase(sfqy)){
					sqlArr[count++] = "update xg_gygl_new_qxrzkgkzb set kssj='"+kssj+"',jssj='"+jssj+"',sfqy='��' where nj||xydm='"+pks[i]+"'";
				}else{
					sqlArr[count++] = "update xg_gygl_new_qxrzkgkzb set sfqy='��' where nj||xydm='"+pks[i]+"'";
				}
			}else{
				if("yes".equalsIgnoreCase(sfqy)){
					sqlArr[count++] = "insert into xg_gygl_new_qxrzkgkzb(nj,xydm,kssj,jssj,sfqy) " +
							"values('"+nj+"','"+xydm+"','"+kssj+"','"+jssj+"','��')";
				}else{
					sqlArr[count++] = "insert into xg_gygl_new_qxrzkgkzb(nj,xydm,sfqy) values('"+nj+"','"+xydm+"','��')";
				}
			}
		}
		try {
			int[] result = dao.runBatch(sqlArr);
			bz = dao.checkBatchResult(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bz;
	}

	/** 
	 * @����:TODO(��ȡ����Ա������λʱ�䷶Χ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-20 ����02:19:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getSjfw() {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		String sql="select csz from xg_gygl_new_jbszb where csdm='fdyczsjsz'";
		HashMap<String, String> map=new HashMap<String, String>();
		map=dao.getMapNotOut(sql, new String[]{});
		String sj=map.get("csz");
		if(sj!=null&&!sj.equalsIgnoreCase("")){
			String[] ksjssj=sj.split(",");
			if(ksjssj.length==2){
				map.put("kssj", ksjssj[0]);
				map.put("jssj", ksjssj[1]);
			}else if(ksjssj.length==1){
				map.put("kssj", ksjssj[0]);
			}
		}
		return map;
	}

	/**
	 * @throws Exception  
	 * @����:TODO(���渨��Ա������λʱ�䷶Χ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-20 ����02:43:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSjfw(CsszForm myForm) throws Exception {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		String sql="update xg_gygl_new_jbszb set csz=? where csdm='fdyczsjsz'";
		String kssj=myForm.getKssj();
		String jssj=myForm.getJssj();
		kssj=Base.isNull(kssj)?"":kssj;
		jssj=Base.isNull(jssj)?"":jssj;
		String sj=kssj+","+jssj;
		return dao.runUpdate(sql, new String[]{sj});
	}
}

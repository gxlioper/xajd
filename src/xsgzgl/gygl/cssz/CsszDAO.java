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
	 * 查询时间设置
	 * @param model
	 * @return
	 */
	public List<String[]> querySjsz(CsszModel model) throws Exception{
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		List<String[]> list = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.* from (select a.nj||a.xydm pkValue,a.nj,a.xydm,b.bmmc xymc,c.kssj,c.jssj,nvl(c.sfqy, '否') sfqy from ")
			.append("(select distinct nj,xydm from view_xsjbxx a ")
			//.append("where exists (select 1 from view_njxyzybj b where b.nj = a.nj and b.xydm = a.xydm) ")
			.append("order by nj,xydm) a left join zxbz_xxbmdm b on a.xydm=b.bmdm ")
			.append("left join xg_gygl_new_qxrzkgkzb c on a.xydm=c.xydm and a.nj=c.nj order by nj desc,xydm) a where 1=1 ");
		
		String[] outputs = new String[]{"pkValue", "nj", "xymc", "kssj", "jssj", "sfqy"};			
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, outputs, model);
		
		return list;
	}
	
	/**
	 * 保存开启状态
	 * @param model
	 * @return
	 */
	public boolean saveKqzt(CsszModel model) {
		DAO dao = DAO.getInstance();
		
		boolean bz = false;
		
		// 主键
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
					sqlArr[count++] = "update xg_gygl_new_qxrzkgkzb set kssj='"+kssj+"',jssj='"+jssj+"',sfqy='是' where nj||xydm='"+pks[i]+"'";
				}else{
					sqlArr[count++] = "update xg_gygl_new_qxrzkgkzb set sfqy='否' where nj||xydm='"+pks[i]+"'";
				}
			}else{
				if("yes".equalsIgnoreCase(sfqy)){
					sqlArr[count++] = "insert into xg_gygl_new_qxrzkgkzb(nj,xydm,kssj,jssj,sfqy) " +
							"values('"+nj+"','"+xydm+"','"+kssj+"','"+jssj+"','是')";
				}else{
					sqlArr[count++] = "insert into xg_gygl_new_qxrzkgkzb(nj,xydm,sfqy) values('"+nj+"','"+xydm+"','否')";
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
	 * @描述:TODO(获取辅导员操作床位时间范围)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-20 下午02:19:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getSjfw() {
		// TODO 自动生成方法存根
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
	 * @描述:TODO(保存辅导员操作床位时间范围)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-20 下午02:43:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSjfw(CsszForm myForm) throws Exception {
		// TODO 自动生成方法存根
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

package xsgzgl.xsxx.general.xgzdpz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

public class XgzdpzDao extends CommDAO{

	/**
	 * 获取字段分类
	 * @return
	 */
	public List<HashMap<String, String>> getXgzdfl() {
		DAO dao = DAO.getInstance();
		return dao.getList("select * from (select a.zdfl,case when a.zdfl='jbxx' then '基本信息' when a.zdfl='lxfs' then '联系方式' " +
				"when a.zdfl='jtcy' then '家庭成员' else  '其他信息' end flmc,case when a.zdfl='jbxx' then '1' when a.zdfl='lxfs' then '2' " +
				"when a.zdfl='jtcy' then '3' else  '4' end xssx " +
				" from  (select a.dyzd zd,a.zdmc,b.lb,b.zdfl,b.sfzd,b.sfbt,b.xssx from xg_xsxx_tbzdpzb a ,xg_xsxx_xxxgzdszb b where a.sfyxgwz='是' and a.dyzd=b.zd) a  group by a.zdfl ) a order by a.xssx", new String[]{}, new String[]{"zdfl","flmc"});
	}
	
	/**
	 * 获取字段
	 * @return
	 */
	public List<HashMap<String, String>> getXgzd(XgzdpzForm model) {
		DAO dao = DAO.getInstance();
		return dao.getList("select * from (select a.dyzd zd,a.zdmc,b.gnmk,b.lb,b.zdfl,b.sfzd,b.sfbt,b.xssx from xg_xsxx_tbzdpzb a ,xg_xsxx_xxxgzdszb b where a.sfyxgwz='是' and a.dyzd=b.zd)  where lb='"+model.getLb()+"' order by zdfl ,to_number(xssx)", 
				new String[]{}, new String[]{"lb","gnmk","zd","zdmc","zdfl","sfzd","sfbt","xssx"});
	}
	
	/**
	 * 获取只读字段
	 * @return
	 */
	public List<HashMap<String, String>> getZdzd(XgzdpzForm model) {
		DAO dao = DAO.getInstance();
		return dao.getList("select * from (select a.dyzd zd,a.zdmc,b.gnmk,b.lb,b.zdfl,b.sfzd,b.sfbt,b.xssx from xg_xsxx_tbzdpzb a ,xg_xsxx_xxxgzdszb b where a.sfyxgwz='是' and a.dyzd=b.zd)  where lb='"+model.getLb()+"' and sfzd='y' order by zdfl ,to_number(xssx)", 
				new String[]{}, new String[]{"lb","gnmk","zd","zdmc","zdfl","sfzd","sfbt","xssx"});
	}
	
	/**
	 * 获取只读字段
	 * @return
	 */
	public List<HashMap<String, String>> getBtzd(XgzdpzForm model) {
		DAO dao = DAO.getInstance();
		return dao.getList("select * from (select a.dyzd zd,a.zdmc,b.gnmk,b.lb,b.zdfl,b.sfzd,b.sfbt,b.xssx from xg_xsxx_tbzdpzb a ,xg_xsxx_xxxgzdszb b where a.sfyxgwz='是' and a.dyzd=b.zd)  where lb='"+model.getLb()+"' and sfbt='y' order by zdfl ,to_number(xssx)", 
				new String[]{}, new String[]{"lb","gnmk","zd","zdmc","zdfl","sfzd","sfbt","xssx"});
	}
	
	/**
	 * 修改只读必填状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgSfzdSfbt(XgzdpzForm model) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "update xg_xsxx_xxxgzdszb set sfzd = '' ,sfbt = '' where lb = '"+model.getLb()+"'";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * 修改只读状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgSfzd(XgzdpzForm model,String[] zdzd) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "update xg_xsxx_xxxgzdszb set sfzd = 'y' ,sfbt = '' where lb = '"+model.getLb()+"' and zd in (" ;
			for(int i =0;i<zdzd.length;i++){
				if(i!=zdzd.length-1){
					sql+="'"+zdzd[i]+"',";
				}else{
					sql+="'"+zdzd[i]+"'";
				}
			}
			sql+=")";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * 修改必填状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgSfbt(XgzdpzForm model,String[] btzd) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "update xg_xsxx_xxxgzdszb set sfzd = '' ,sfbt = 'y' where lb = '"+model.getLb()+"' and zd in (" ;
			for(int i =0;i<btzd.length;i++){
				if(i!=btzd.length-1){
					sql+="'"+btzd[i]+"',";
				}else{
					sql+="'"+btzd[i]+"'";
				}
			}
			sql+=")";
		return dao.runUpdate(sql, new String[]{});
	}
	
}

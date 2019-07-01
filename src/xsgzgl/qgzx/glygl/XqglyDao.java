package xsgzgl.qgzx.glygl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XqglyDao extends SuperDAOImpl<XqglyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XqglyForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XqglyForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.zgh,t.xq,t1.xm,t1.bmmc,t2.xqmc,t1.xb from xg_zjzyy_xqdzb t");
        sql.append(" left join view_fdyxx t1");
        sql.append(" on t.zgh = t1.zgh");
        sql.append(" left join dm_zju_xq t2");
        sql.append(" on t.xq = t2.dm  where 1=1 order by t.zgh,t.xq");
		return getPageList(t, sql.toString(), new String[]{});
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setTableName("xg_zjzyy_xqdzb");
		this.setKey("zgh");
		this.setClass(XqglyForm.class);
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午02:27:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsRepeat(String zgh,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_zjzyy_xqdzb where xq = ? and zgh = ?");
		String rs = dao.getOneRs(sql.toString(), new String[]{xq,zgh},"cnt");
		return Integer.valueOf(rs) > 0 ? false :true;
	}

	/**
	 * 
	 * @描述: 获取校区
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午02:28:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXqList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from dm_zju_xq order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 *删除 
	 * @throws Exception 
	 */
	public boolean runDeletes(String[] ids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjzyy_xqdzb where zgh||xq in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" ");
		sql.append(" )");
		return dao.runUpdate(sql.toString(), ids);
	}

}

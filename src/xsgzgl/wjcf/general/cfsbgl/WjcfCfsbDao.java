package xsgzgl.wjcf.general.cfsbgl;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.WjcfGeneralForm;

import common.GlobalsVariable;

/**
 * 
* 
* 类名称：WjcfCfsbAction 
* 类描述：违纪处分处分上报Dao
* 创建人：xucy 
* 创建时间：2012-6-20 下午01:18:00 
* 修改备注：  
* @version 
*
 */
public class WjcfCfsbDao extends DAO{

	DAO dao = DAO.getInstance();	
	/**
	 * 违纪处分 查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWjcfList(WjcfGeneralForm myForm,WjcfCfsbModel model,User user) 
	throws IllegalArgumentException, SecurityException, 
	IllegalAccessException, InvocationTargetException, 
	NoSuchMethodException{
	List<String> colList = new ArrayList<String>();
	String[] colArr = new String[] {"cfid","xn","xqmc","xh","xm","cflbmc","cfyymc", "cfwh","cfsj", "sbrxm","shjg"};
	
	// 权限过滤
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
			"xydm", "bjdm");
	
	// 高级查询条件
	String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
	String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	String query = " where 1 = 1 ";
	query += searchTj;
	
	StringBuilder sql = new StringBuilder();
	sql.append("select rownum r,a.* from (");
	sql.append("view_wjcf_cfsb");
	sql.append(") a ");
	sql.append(query);
	
	sql.append(searchTjByUser);
	ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
	.commonPageQuery(myForm.getPages(), sql.toString(), inputV, dao
		.unionArray(colArr, colList.toArray(new String[] {})));
	return list;
	}
	
	/**
	 * 新增保存
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String saveCfsb(WjcfGeneralForm form,InputStream instream) throws Exception{
		boolean flag = false;
		HashMap<String, String> map= dao.getMapNotOut("select spl,sys_guid() guid from xg_wjcf_cflbdmb where cflbdm = ?", new String[]{form.getCflbdm()});
		String spl = map.get("spl");
		String sql = "insert into xg_wjcf_wjcfsbb(fj,cfid,xh,xn,xq,cflbdm,cfyydm,wjsj,sbb,sbsj,wjssjg,bz,sbjg,fjmc) " +
				     "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		String zt = GlobalsVariable.GNDM_WJCF_WXSH.equalsIgnoreCase(spl) ? spl : "wsh";
		flag = dao.updateBlob(sql, new String[]{map.get("guid"),form.getXh(),form.getXn(),form.getXq(),form.getCflbdm(),
				form.getCfyydm(),form.getWjsj(),form.getSbb(),form.getSbsj(),form.getWjssjg(),form.getBz(),
				zt,form.getFjmc()},instream.available(), instream);
		
		//如果是无需审核的则直接进正式表
		if (GlobalsVariable.GNDM_WJCF_WXSH.equalsIgnoreCase(spl)) {
			/*sql = "insert into xg_wjcf_wjcfb(fj,cfid,xh,xn,xq,cflbmc,cfyymc,wjsj,sbb,sbsj,wjssjg,bz,cfsj,fjmc) " +
		     "values(?,?,?,?,?,(select cflbmc from xg_wjcf_cflbdmb where cflbdm=?),(select cfyymc from xg_wjcf_cfyydmb where cfyydm=?),?,?,?,?,?,?,?)" ;
			flag = dao.updateBlob(sql, new String[]{map.get("guid"),form.getXh(),form.getXn(),form.getXq(),form.getCflbdm(),
					form.getCfyydm(),form.getWjsj(),form.getSbb(),form.getSbsj(),form.getWjssjg(),form.getBz(),form.getSbsj(),
					form.getFjmc()},instream.available(), instream);*/
			sql="insert into xg_wjcf_wjcfb(fj,cfid,xh,xn,xq,cflbmc,cfyymc,wjsj,sbb,sbsj,wjssjg,bz,cfsj,fjmc) select " +
					"fj,cfid,xh,xn,xq,(select cflbmc from xg_wjcf_cflbdmb where cflbdm=?) cflbmc,(select cfyymc from xg_wjcf_cfyydmb where cfyydm=?) cfyymc,wjsj,sbb,sbsj,wjssjg,bz,sbsj,fjmc from xg_wjcf_wjcfsbb where cfid='"+map.get("guid")+"'";
			flag = dao.runUpdate(sql,new String[]{form.getCflbdm(),form.getCfyydm()});
		} else {
			//其他按审核流来走
			if(flag){
				String cflbdm = form.getCflbdm();
				if(cflbdm!=null){
					sql="select t.cfid from xg_wjcf_wjcfsbb t where t.xh=? and t.xn=? and t.xq=? and t.wjsj=? and t.cflbdm=?";
					String[] inputValue = new String[] {form.getXh(),form.getXn(),form.getXq(),form.getWjsj(),cflbdm};
					 List<String> list= dao.getList(sql, inputValue, "cfid");
					if(null!=list&&list.size()>0){
						sql = "select t.spl from xg_wjcf_cflbdmb t where t.cflbdm=?";
						 inputValue = new String[] {cflbdm};
						 List<String> list1= dao.getList(sql, inputValue, "spl");
						if(list1!=null&&list1.size()>0){
							String splc = list1.get(0);
							sql = "select spgw from xg_xtwh_spbz t where t.splc=?";
							 inputValue = new String[] {splc};
							 List<String> list2 = dao.getList(sql, inputValue, "spgw");
							 if(null!=list2&&list2.size()>0){
								 sql="insert into xg_wjcf_wjcfshb(cfid,xtgwid,shzt,sftj) values(?,?,?,?)";
								 List<String[]> params=new ArrayList<String[]>();
								 for(int i=0;i<list2.size();i++){
									 String[] param=null;
									 param=new String[]{list.get(0),list2.get(i),"wsh","0"};
									 params.add(param);
								 }
								 int[] rows = dao.runBatch(sql, params);
								 flag = dao.checkBatchResult(rows); 
							 }
						}
					}
				}
			}
		}
		
		
		
		return map.get("guid");
	}
	
	/**
	 * 修改保存
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean updateCfsb(WjcfGeneralForm form,String cflbdmXt) throws Exception{
		
		boolean	flag = false;
		String sql ="";
		String fjmc = form.getFjmc();
		if (StringUtils.isNotNull(fjmc)) {
					 sql = "update xg_wjcf_wjcfsbb set fj=?, cfyydm =?,cflbdm=?,wjsj=?,wjssjg=?,bz=? ,fjmc=? where cfid=?" ;
					flag =  dao.updateBlob(sql, new String[]{ form.getCfyydm(),form.getCflbdm(),
							form.getWjsj(),form.getWjssjg(), form.getBz(),form.getFjmc(),form.getCfId()},form.getFj().getInputStream().available(), 
							form.getFj().getInputStream());
		}else{
			sql = "update xg_wjcf_wjcfsbb set cfyydm =?,cflbdm=?,wjsj=?,wjssjg=?,bz=? where cfid=?" ;
			flag =  dao.runUpdate(sql, new String[]{ form.getCfyydm(),form.getCflbdm(),
					form.getWjsj(),form.getWjssjg(), form.getBz(),form.getCfId()});
		}
		
		//无需审核的处理  直接修改处分正式表
		String spl = dao.getOneRs("select spl from xg_wjcf_cflbdmb where cflbdm = ?", new String[]{form.getCflbdm()}, "spl");
		if (GlobalsVariable.GNDM_WJCF_WXSH.equalsIgnoreCase(spl)) {
			if (StringUtils.isNotNull(fjmc)) {
				 sql = "update xg_wjcf_wjcfb set fj=?, cfyymc =(select cfyymc from xg_wjcf_cfyydmb where cfyydm=?),cflbmc=(select cflbmc from xg_wjcf_cflbdmb where cflbdm=?),wjsj=?,wjssjg=?,bz=? ,fjmc=? where cfid=?" ;
				flag =  dao.updateBlob(sql, new String[]{ form.getCfyydm(),form.getCflbdm(),
						form.getWjsj(),form.getWjssjg(), form.getBz(),form.getFjmc(),form.getCfId()},form.getFj().getInputStream().available(), 
						form.getFj().getInputStream());
			}else{
				sql = "update xg_wjcf_wjcfb set cfyymc =(select cfyymc from xg_wjcf_cfyydmb where cfyydm=?),cflbmc=(select cflbmc from xg_wjcf_cflbdmb where cflbdm=?),wjsj=?,wjssjg=?,bz=? where cfid=?" ;
				flag =  dao.runUpdate(sql, new String[]{ form.getCfyydm(),form.getCflbdm(),
						form.getWjsj(),form.getWjssjg(), form.getBz(),form.getCfId()});
			}
		} else {
			//当处分类别不一致时，先删除审核表，后增加
			if("false".equals(cflbdmXt)){
				if(flag){
					String cflbdm = form.getCflbdm();
					if(cflbdm!=null){
						sql="delete from xg_wjcf_wjcfshb t where t.cfid=?";
						flag = runUpdate(sql, new String[]{form.getCfId()});
						sql = "select t.spl from xg_wjcf_cflbdmb t where t.cflbdm=?";
						List<String> list1= dao.getList(sql, new String[]{cflbdm}, "spl");
							if(list1!=null&&list1.size()>0){
								String splc = list1.get(0);
								sql = "select spgw from xg_xtwh_spbz t where t.splc=?";
								 List<String> list2 = dao.getList(sql, new String[]{splc}, "spgw");
								 if(null!=list2&&list2.size()>0){
									 sql="insert into xg_wjcf_wjcfshb(cfid,xtgwid,shzt,sftj) values(?,?,?,?)";
									 List<String[]> params=new ArrayList<String[]>();
									 for(int i=0;i<list2.size();i++){
										 String[] param=null;
										 param=new String[]{form.getCfId(),list2.get(i),"wsh","0" };
										 params.add(param);
									 }
									 int[] rows = dao.runBatch(sql, params);
									 flag = dao.checkBatchResult(rows); 
								 }
						}
					}
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 查询一条数据
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public HashMap<String, String> getCfsb(String cfid) throws Exception{
		String sql = "select a.cfid,a.xh,a.xn,a.xq,a.fjmc,(select b.xqmc from xqdzb b where a.xq = b.xqdm) xqmc," +
				"a.cflbdm,(select c.cflbmc from XG_WJCF_CFLBDMB c where a.cflbdm = c.cflbdm ) cflbmc, " +
				"a.cfyydm,(select d.cfyymc from XG_WJCF_CFYYDMB d where a.cfyydm = d.cfyydm ) cfyymc," +
				"a.wjsj,a.sbb,a.sbsj,a.wjssjg,a.bz,a.sbjg from xg_wjcf_wjcfsbb a where a.cfid = ?" ;
		String[] inputValue = new String[] { cfid };
		return getMapNotOut(sql, inputValue);
	}

	/**
	 * 查询学生已受违纪处分列表（全部记录）
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getYscfqk(String xh){
		String sql = "select t.xn,t.xq,(select xqmc from xqdzb m where m.xqdm = t.xq) xqmc,(case when t.cfggw is not null then t.cfggw else t.cflbmc end) cflbmc,t.cfyymc,t.cfsj,t.cfwh,t.jcsj,t.wjssjg from xg_wjcf_wjcfb t where nvl(t.ssjg,0) <> '撤消处分' and nvl(t.ssjg,0) <> '撤销处分' and t.xh=? order by t.cfsj desc";
		return dao.getListNotOut(sql.toString(), new String[] {xh});
	}
	/**
	 * 查询学生已受违纪处分列表（不包含本记录）
	 */
	public List<HashMap<String,String>> getYscfqkNotId(String xh, String cfid){
		String sql = "select t.xn,t.xq,(select xqmc from xqdzb m where m.xqdm = t.xq) xqmc,(case when t.cfggw is not null then t.cfggw else t.cflbmc end) cflbmc,t.cfyymc,t.cfsj,t.cfwh,t.jcsj,t.wjssjg from xg_wjcf_wjcfb t where nvl(t.ssjg,0) <> '撤消处分' and nvl(t.ssjg,0) <> '撤销处分' and t.xh=? and cfid<>? order by t.cfsj desc";
		return dao.getListNotOut(sql.toString(), new String[] {xh,cfid});
	}
	
	/**
	 * 查询处分审核信息
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getCfsh(String cfid){
		String sql = "select a.shzt, (case when a.shzt='wsh' then'未审核' when a.shzt='shz' then '审核中' when  a.shzt='tg' then '审核通过' " +
		"else '审核不通过' end) shztmc,a.shsj,a.shyj,a.shr,a.sftj,a.cfsj,a.cfwh,(select WM_CONCAT(b.spyh) from xg_xtwh_spgwyh b where b.spgw=a.xtgwid) spyh " +
		"from xg_wjcf_wjcfshb a where a.cfid=?";
		return dao.getListNotOut(sql.toString(), new String[] {cfid});
	}
	
	/**
	 * 批量删除
	 * */
	public boolean delWjsb(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		//StringBuilder zsksql = new StringBuilder();
		sql1.append("delete xg_wjcf_wjcfsbb where cfid=?");
		sql2.append("delete xg_wjcf_wjcfshb where cfid=?");
	//	zsksql.append("delete xg_wjcf_wjcfb where cfwh is null and cfsj is null and cfid = ?");
		try {
			int[] rs = dao.runBatch(sql1.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag){
			try {
				int[] rs = dao.runBatch(sql2.toString(), params);
				flag = dao.checkBatchResult(rs);
				
				/*if (flag) {
					dao.runBatch(zsksql.toString(), params);
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag; 
	}
	
	/**
	 * 查询其他级别处分审核信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfshxx(String cfid) {
		String sql = "select t.cfid,t.xtgwid,t.shzt,t.shsj,t.shyj,t.shr,(select m.xm from yhb m where t.shr = m.yhm) shrxm,t.cfsj,t.cfwh,"
				+ "(select a.mc from xg_xtwh_spgw a where a.id = t.xtgwid) gwmc from xg_wjcf_wjcfshb t where t.cfid=? and t.shzt<>?";
		return dao.getListNotOut(sql.toString(), new String[] { cfid ,"wsh"});
	}
	
	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		return dao.getOneLong(sql, inputList, column);
	}
	
	/**
	 * 
	 * 检测是否可修改或者删除
	 * 
	 * @author xucy
	 */
	public String checkCfsb(WjcfGeneralForm model) {
		String pk[] = model.getPrimarykey_checkVal();
		String message = "";
		if (null != pk && pk.length > 0) {
			if(pk.length==1){
				StringBuilder sql = new StringBuilder();
				String cfId = pk[0];
				sql.append("select a.xh,a.sbjg from xg_wjcf_wjcfsbb a where a.cfid=?");
				HashMap<String, String> sbjg = dao.getMapNotOut(sql.toString(),
						new String[] {cfId});
				if ( !"wsh".equals(sbjg.get("sbjg")) ) {
					message += sbjg.get("xh") + ",";
				}else {
					message += "";
				}
			}else{
					StringBuilder sql = new StringBuilder();
					sql.append("select a.xh,a.sbjg from xg_wjcf_wjcfsbb a where a.sbjg !='wsh' and a.cfid in( ");
					for (int i = 0; i < pk.length; i++) {
						if (i != 0) {
							sql.append(",");
						}
						sql.append("'"+pk[i]+"'");
					}
					sql.append(" ) ");
					List<HashMap<String, String>>  list = dao.getListNotOut(sql.toString(), new String[] {});
					if(null!=list&&list.size()>0){
						for(int i = 0;i<list.size();i++){
							message += list.get(i).get("xh") + ",";
						}
					}
			}
		}
		return message;
	}

	
	/**
	 * 根据处分类别询审批岗位
	 * @param cflbdm
	 * @return
	 * @throws SQLException
	 */
	public String[] getSpgwByCflb(String cflbdm) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select spgw from xg_xtwh_spbz where splc=(");
		sql.append("select spl from xg_wjcf_cflbdmb where cflbdm=?");
		sql.append(") order by xh");
		
		return dao.getArray(sql.toString(), new String[]{cflbdm}, "spgw");
	}
	
	
	/**
	 * 自定义导出设置违纪处分 上报
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfCfsbfExportList(WjcfGeneralForm myForm,User user) 
	throws IllegalArgumentException, SecurityException, 
	IllegalAccessException, InvocationTargetException, 
	NoSuchMethodException{
	
	// 权限过滤
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
			"xydm", "bjdm");
	
	// 高级查询条件
	String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
	String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	String query = " where 1 = 1 ";
	query += searchTj;
	
	StringBuilder sql = new StringBuilder();
	sql.append("select rownum r,a.* from (");
	sql.append("view_wjcf_cfsb");
	sql.append(") a ");
	sql.append(query);
	
	sql.append(searchTjByUser);
	List<HashMap<String, String>> list = (List<HashMap<String, String>>) CommonQueryDAO
	.commonPageQueryForExportMap(myForm.getPages(), sql.toString(), inputV);
	return list;
	}
}

package xsgzgl.wjcf.jcsz;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.cfssgl.WjcfCfssglForm;

/**
 * 
* 
* 类名称：WjcfJcszServices 
* 类描述：违纪处分基础设置Services
* 创建人：yijd 
* 创建时间：2012-6-19 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class WjcfJcszServices extends CommService {

	private WjcfJcszDao dao = new WjcfJcszDao();
	
	/**
	 * 违纪处分  类别代码查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cflbdmCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cflbdmCx(model);
	}
	
	/**
	 * 违纪处分  类别代码接口
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbdmCx() throws Exception {
		return dao.cflbdmCx();
	}
	
	/**
	 * 违纪处分  类别名称接口
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbmcCx() throws Exception {
		return dao.cflbmcCx();
	}
	
	/**
	 * 违纪处分   类别代码增加
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmZj(WjcfJcszForm model) throws Exception {
		return dao.cflbdmZj(model);
	}
	
	/**
	 * 违纪处分   类别代码修改
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmXg(WjcfJcszForm model) throws Exception {
		return dao.cflbdmXg(model);
	}
	
	/**
	 * 违纪处分  类别代码删除
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmSc(String cflbdm) throws Exception {
		if(StringUtils.isNotNull(cflbdm)){
			String[] cflbdms=cflbdm.split("@@");
			List<String[]> list=new ArrayList<String[]>();
			for(String str : cflbdms){
				list.add(new String[]{str});
			}
			return dao.cflbdmSc(list);
		}
		return false;
	}
	
	/**
	 * 违纪处分  类别代码查看
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cflbdmCk(String cflbdm) throws Exception {
		return dao.cflbdmCk(cflbdm);
	}
	
	
	/**
	 * 违纪处分  原因代码查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfyydmCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cfyydmCx(model);
	}
	
	/**
	 * 违纪处分  原因代码接口
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyydmCx() throws Exception {
		return dao.cfyydmCx();
	}
	
	/**
	 * 违纪处分  原因名称接口
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyymcCx() throws Exception {
		return dao.cfyymcCx();
	}
	
	/**
	 * 违纪处分   原因代码增加
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmZj(WjcfJcszForm model) throws Exception {
		return dao.cfyydmZj(model);
	}
	
	/**
	 * 违纪处分   原因代码修改
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmXg(WjcfJcszForm model) throws Exception {
		return dao.cfyydmXg(model);
	}
	
	/**
	 * 违纪处分  原因代码删除
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmSc(String cfyydm) throws Exception {
		if(StringUtils.isNotNull(cfyydm)){
			String[] cfyydms=cfyydm.split("@@");
			List<String[]> list=new ArrayList<String[]>();
			for(String str : cfyydms){
				list.add(new String[]{str});
			}
			return dao.cfyydmSc(list);
		}
		return false;
	}
	
	/**
	 * 违纪处分  原因代码查看
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfyydmCk(String cfyydm) throws Exception {
		return dao.cfyydmCk(cfyydm);
	}

	/**
	 * 工具类：设置列表头名
	 * 
	 * @param model
	 *            业务模型
	 * @return
	 */
	public String[] getTopTr(WjcfJcszForm model,String type) {
		String[] outTr = null;
		if("wjcflb".equals(type)){
			outTr = new String[] { "名称", "审核流", "是否可申诉", "是否可申请解除","申诉受理工作日" };
		}else if("wjcfyy".equals(type)){
			outTr = new String[] { "名称" };
		}
		
		return outTr;

	}

	/**
	 * 工具类：显示的页面列表内容列名，也是数据查询出来的列值
	 * 
	 * @param model
	 *            业务模型
	 * @return
	 */
	public String[] getColnumName(WjcfJcszForm model,String type) {
		String[] colnumName = null;
		if("wjcflb".equals(type)){
			colnumName = new String[] { "cflbdm", "cflbmc", "spl", "sfkss",
					"sfksqjc", "ssslgzr" };
		}else if("wjcfyy".equals(type)){
			colnumName = new String[] { "cfyymc" };
		}
		return colnumName;
	}
	
	/**
	 * 保存处分申诉信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssZj(WjcfCfssglForm model,InputStream is) throws Exception{
		if(model==null){
			return false;
		}
		return false;
	}
	
	
	/**
	 * 修改处分申诉信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssXg(WjcfCfssglForm model,InputStream is) throws Exception{
		
		return false;
	}
	
	/**
	 * 撤销是审批流查询
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ssjcsplCx(WjcfJcszForm model) throws Exception{
		return dao.ssjcsplCx(model);
	}
	
	/**
	 * 撤销是审批流  保存
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean ssjcsplBc(WjcfJcszForm model) throws Exception{
		boolean sc=dao.ssjcsplScSy();
		if(sc){
			if(model==null){
				return false;
			}
			return dao.ssjcsplBc(model);
		}
		return false;
	}
	
	/**
	 * 查询处理类别上报是否存在
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public boolean cxCfsbBycflbdm(String cflbdm) throws Exception{
		String cnt = dao.cxCfsbBycflbdm(cflbdm);
		return "0".equalsIgnoreCase(cnt) ? false : true;
	}
	
	/**
	 * 查询处分类别名称是否可删除
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public String cxCflbdmsfksc(String pkValue) throws Exception{
		StringBuffer sql = new StringBuffer("select distinct b.cflbmc from xg_wjcf_wjcfsbb a," +
				"xg_wjcf_cflbdmb b where a.cflbdm=b.cflbdm and a.cflbdm in (");
		for (String s : pkValue.split("@@")) {
			sql.append("'");
			sql.append(s);
			sql.append("',");
		}
		sql = new StringBuffer(sql.toString().substring(0, sql.length()-1));
		sql.append(")");
		sql.append("union select distinct cflbmc from (select (select cflbdm from xg_wjcf_cflbdmb b where a.cflbmc=b.cflbmc) cflbdm,cflbmc from xg_wjcf_wjcfb a) where cflbdm in (");
		for (String s : pkValue.split("@@")) {
			sql.append("'");
			sql.append(s);
			sql.append("',");
		}
		sql = new StringBuffer(sql.toString().substring(0, sql.length()-1));
		sql.append(")");
		List<String> cflbmcList = dao.getList(sql.toString(), new String[]{}, "cflbmc");
		String rs = "";
		if (cflbmcList != null && cflbmcList.size() > 0) {
			for (String s : cflbmcList) {
				rs += s;
				rs += ",";
			}
		}
		return StringUtils.isNotNull(rs) ? rs.substring(0, rs.length()-1) : "";
	}
	
	/**
	 * 违纪处分类别 自定义设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbdmExportCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cflbdmExportData(model);
	}
	
	/**
	 * 违纪处分  原因代码自定义设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyydmExportCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cfyydmExportCx(model);
	}
	
}

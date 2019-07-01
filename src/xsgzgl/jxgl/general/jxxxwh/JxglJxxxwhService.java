package xsgzgl.jxgl.general.jxxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * 军训管理-基础信息维护-军训信息维护
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhService extends BasicService{
	private final String JXZT_START="start";
	JxglJxxxwhDao jxglJxxxwhDao = new JxglJxxxwhDao();
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		String[] en = new String[] { "", "jxmc", "cjnj", "kssj", "jssj", "cjrs", "hxrs","zt" };
		String[] cn = new String[] { "", "军训名称", "参加年级", "开始时间", "结束时间", "参训人数", "缓训人数","状态"};
		if("jxmd".equalsIgnoreCase(type)){
			en = new String[]{"", "xh", "xm", "nj","xymc", "zymc","bjmc","cxqk"};
			cn = new String[] { "", "学号", "姓名", "年级","院系", "专业","班级","参训情况"};
		}
		return jxglJxxxwhDao.arrayToList(en, cn);
	}
	
	/**
	 * 军训信息查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jxxxCx(JxglJxxxwhForm model) throws Exception {
		return jxglJxxxwhDao.jxxxCx(model);
	}
	
	/**
	 * 创建页面查询结果
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					if(j==rs.length-1){
						String color = "运行".equalsIgnoreCase(replaceHtml(rs[j]))?"blue":"red";
						html.append("<font color='"+color+"'>");
						html.append(replaceHtml(rs[j]));
						html.append("</font>");
					}else{
						html.append(replaceHtml(rs[j]));
					}
					html.append("</td>");
				}
				
				
				/*html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append("><a href='jxgl_jxxxwh.do?method=jxmdCx&pkValue="+replaceHtml(rs[0])+"&cxqk=cx'><font class='name'>");
				html.append(replaceHtml(rs[rs.length-3]));
				html.append("</font></a></td>");
				
				
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append("><a href='jxgl_jxxxwh.do?method=jxmdCx&pkValue="+replaceHtml(rs[0])+"&cxqk=hx'><font class='name'>");
				html.append(replaceHtml(rs[rs.length-2]));
				html.append("</font></a></td>");*/
				
				
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				String color = "运行".equalsIgnoreCase(replaceHtml(rs[rs.length-1]))?"have":"non";
				html.append("<font class='"+color+"'>");
				html.append(replaceHtml(rs[rs.length-1]));
				html.append("</font></td>");
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	
	
	/**
	 * 获得参加年级列表
	 * @return
	 */
	public List<HashMap<String, String>> getCjnj() {
		return jxglJxxxwhDao.getCjnj();
	}
	
	/**
	 * 获得建制等级列表
	 * @return
	 */
	public List<HashMap<String, String>> getJzdj() {
		return jxglJxxxwhDao.getJzdj();
	}
	
	/**
	 * 更新建制等级 
	 * @param sql
	 * @return
	 */
	public boolean serv_updateJzdj(String sql) {
		boolean result = false; 
		try {
			result = jxglJxxxwhDao.dao_updateJzdj(sql);
		} catch (Exception e) {
			
		}
		return result;
	}
	
	public int[] serv_batchUpdateJzdj(String[] sql) {
		try{
			return jxglJxxxwhDao.dao_batchUpdateJzdj(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 验证军训信息 true：通过 false: 不通过
	 * @param model
	 * @return
	 */
	public boolean checkJxxx(JxglJxxxwhForm model) {
		boolean flag = false;
		String num = jxglJxxxwhDao.getCount(model);
		if("0".equalsIgnoreCase(num)){
			flag = true;
		}
		return flag;
	}

	
	/**
	 * 军训信息保存(增加，更新)
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxxxSave(JxglJxxxwhForm model) throws Exception {
		boolean flag = false;
		//增加
		if(model.getJxid()==null || "".equalsIgnoreCase(model.getJxid())){
			//获得一个GUID
			model.setJxid(jxglJxxxwhDao.getGuid());
			flag = jxglJxxxwhDao.insertJxxx(model);
		//更新
		}else{
			flag = jxglJxxxwhDao.updateJxxx(model);
		}
		//如果保存成功并且此军训状态为“运行”，则其他军训状态改为“停止”
		if(flag && "start".equalsIgnoreCase(model.getJxzt())){
			flag = jxglJxxxwhDao.stopJxxx(model);
		}
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 获得军训信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxx(JxglJxxxwhForm model) {
		return jxglJxxxwhDao.getJxxx(model);
	}
	
	/**
	 * 获得军训信息（理由附件）
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxView(JxglJxxxwhForm model) {
		return jxglJxxxwhDao.getJxxxView(model);
	}
	
	/**
	 * 军训信息删除验证
	 * @param model
	 * @return
	 */
	public boolean checkScJxxx(JxglJxxxwhForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			model.setJxid(pkValue[i]);
			String num = jxglJxxxwhDao.getCountJz(model);
			if(!"0".equalsIgnoreCase(num)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 批量删除军训信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxxxSc(JxglJxxxwhForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return jxglJxxxwhDao.jxxxSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}

	
	/**
	 * 军训信息操作
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxxxCz(JxglJxxxwhForm model) throws Exception {
		boolean flag = jxglJxxxwhDao.jxxxCz(model);
		//如果操作成功并且此军训状态为“运行”，则其他军训状态改为“停止”
		if(flag && "start".equalsIgnoreCase(model.getJxzt())){
			flag = jxglJxxxwhDao.stopJxxx(model);
		}
		return flag?MessageInfo.MESSAGE_WORK_SUCCESS:MessageInfo.MESSAGE_WORK_FALSE;
	}

	
	/**
	 * 军训名单查询
	 * @param model
	 * @param request 
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> jxmdCx(JxglJxxxwhForm model, HttpServletRequest request) throws Exception {
		return jxglJxxxwhDao.jxmdCx(model,request);
	}
	
	/**
	 * 创建页面查询结果
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML2(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	
	
	/**
	 * 获得军训人数
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxrs(JxglJxxxwhForm model) {
		return jxglJxxxwhDao.getJxrs(model);
	}

	
	/**
	 * 生成军训名单
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String scJxmd(JxglJxxxwhForm model) throws Exception {
		boolean flag = false;
		flag = jxglJxxxwhDao.insertJxmd(model);
		if(flag&&"yes".equalsIgnoreCase(model.getSfhx())){
			model.setCxqk("hx");
			flag = jxglJxxxwhDao.copyJxmd(model);
		}
		if(flag&&"yes".equalsIgnoreCase(model.getSfmx())){
			model.setCxqk("mx");
			flag = jxglJxxxwhDao.copyJxmd(model);
		}
		
		return flag?MessageInfo.MESSAGE_DO_SUCCESS:MessageInfo.MESSAGE_DO_FALSE;
	}
	
	
	
	/**
	 * 军训名单操作验证
	 * @param model
	 * @return
	 */
	public boolean checkScJxmd(JxglJxxxwhForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			model.setPkValue(pkValue[i]);
			String num = jxglJxxxwhDao.getCountMd(model);
			if(!"0".equalsIgnoreCase(num)){
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * 军训名单删除
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxmdSc(JxglJxxxwhForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return jxglJxxxwhDao.jxmdSc(params)?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}

	
	/**
	 * 参训情况操作(缓训，免训，参训)
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String cxqkCz(JxglJxxxwhForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String cxqk = model.getCxqk();
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{cxqk,pkValue[i]});
		}
		return jxglJxxxwhDao.cxqkCz(params)?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}

	/**
	 * 获取当前军训信息维护model   yjd
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxwhModel(){
		JxglJxxxwhForm model=new JxglJxxxwhForm();
		model.setJxzt(JXZT_START);
		return jxglJxxxwhDao.getJxxxwhModel(model);
	}
	
	/**
	 * 获取军训信息   yjd
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxxxList(){
		return jxglJxxxwhDao.getJxxxList();
	}
	
	/**
	 * 获取军训信息   yjd
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxwhByJxidModel(JxglJxxxwhForm model){
		if(model == null){
			return null;
		}
		return jxglJxxxwhDao.getJxxxwhByJxidModel(model);
	}
    
	/**
	 * 军训名单查询
	 * @param model
	 * @param request 
	 * @return
	 */
	public List<HashMap<String,String>> getAlljxmdCx(JxglJxxxwhForm model, HttpServletRequest request) throws Exception{
		return jxglJxxxwhDao.getAlljxmdCx(model, request);
	}
	/**
	 * 
	 * @描述:获取启用的军训信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-8 上午11:17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQyJxxx() {
		return jxglJxxxwhDao.getQyJxxx();
	}

}

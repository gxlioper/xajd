package xsgzgl.qgzx.jfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
/**
 * 勤工助学-勤工经费管理-经费信息管理
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglService extends BasicService{
	QgzxJfglDAO myDao = new QgzxJfglDAO();
	/**
	 * 提供某部门某年度剩余可用总经费接口供其他模块使用
	 * @param nd
	 * @param yrdwdm
	 * @return
	 */
	public String getBmNdSyje(String nd, String yrdwdm){
		return myDao.getBmNdSyje(nd,yrdwdm);
	}
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[] {};
		if ("12309".equals(Base.xxdm)) {
			 en = new String[] { "", "r", "nd","yrdwmc","hbzje","jfhbje","wcsysyje" };
			 cn = new String[] { "", "行号", "年度", "用人部门", "划拨总金额<元>", "已发放金额<元>", "剩余金额<元>" };
		}else {
			en = new String[] { "", "r", "nd","yrdwmc","hbzje","yffje","syje" };
			cn = new String[] { "", "行号", "年度", "用人部门", "划拨总金额<元>", "已发放金额<元>", "剩余金额<元>" };
		}
		//浙江交通职业技术学院个性化
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			cn = new String[] { "", "行号", "年月", "用人部门", "划拨总金额<元>", "已发放金额<元>", "剩余金额<元>" };
		}
		return dao.arrayToList(en, cn);
	}
	/**
	 * 获得经费信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJfxx(QgzxJfglForm myForm) throws Exception {
		return myDao.getJfxx(myForm);
	}
	/**
	 * 
	 * @描述: 是否控制划拨金额
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-24 下午03:18:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 */
	public boolean isKzHbjs(){
		QgzxCsszService qcs=new QgzxCsszService();
		HashMap<String, String> hm=qcs.getCssz();
		String sfjfhb=hm.get("sfjfhb");
		//酬金发放不受经费划拨约束
		if("no".equals(sfjfhb)){
			return false;
		}
		return true;
	}
	public Map<String, String> getJfxx(String yrdwdm, String nd) {
		return myDao.getJfxx(yrdwdm, nd);
	}
	/**
	 * 创建HTML查询页面
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
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
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
	 * 经费信息保存
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String jfxxBc(QgzxJfglForm myForm) throws Exception {
		boolean flag = false;
		String nd = myForm.getNd();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String[] bm= myForm.getBm().split("!!@@!!");
		String[] hbsj = myForm.getHbsj().split("!!@@!!");
		String[] hbje = myForm.getHbje().split("!!@@!!");
		String[] bz = myForm.getBz().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < bm.length;i++){
			String[] el = {xn,xq,nd,bm[i],hbsj[i],hbje[i],bz[i]};
			params.add(el);
		}
		flag = myDao.jfxxBc(params);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	public String jfxxInit(QgzxJfglForm myForm) throws Exception {
		boolean flag = false;
		String nd = myForm.getNd();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String[] bm= myForm.getBm().split("!!@@!!");
		String[] hbsj = myForm.getHbsj().split("!!@@!!");
		String[] hbje = myForm.getHbje().split("!!@@!!");
		String[] bz = myForm.getBz().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < bm.length;i++){
			for(int j = 1; j <= 12; j++){
				String ny =nd+"-"+String.format("%02d", j);
				String[] el = {xn,xq,ny,bm[i],hbsj[i],hbje[i],bz[i]};
				params.add(el);
			}
		}
		flag = myDao.jfxxBc(params);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 修改保存
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String jfxxXg(QgzxJfglForm myForm) throws Exception {
		boolean flag = false;
		String nd = myForm.getNd();
		String bm = myForm.getBm();
		String[] hbsj = myForm.getHbsj().split("!!@@!!");
		String[] hbje = myForm.getHbje().split("!!@@!!");
		String[] bz = myForm.getBz().split("!!@@!!");
		String[] xn = myForm.getXns();
		String[] xq = myForm.getXqs();
		List<String[]> params = new ArrayList<String[]>();
		if(!Base.isNull(myForm.getHbsj())&&!"".equals(myForm.getHbsj())){
			for(int i = 0; i < hbsj.length;i++){
				String forxn = "";
				String forxq = "";
				if(xn != null && xn.length == hbsj.length){
					forxn = xn[i];
				}
				if(xq != null && xq.length == hbsj.length){
					forxq = xq[i];
				}
				String[] el = {forxn,forxq,nd,bm,hbsj[i],hbje[i],bz[i]};
				params.add(el);
			}
			flag = myDao.jfxxDel(myForm);
			if(flag){
				flag = myDao.jfxxSave(params);
			}
		}else{
			flag = myDao.jfxxDel(myForm);
		}
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 获得部门List
	 * @return
	 */
	public List<HashMap<String, String>> getBm() {
		return myDao.getBm();
	}
	public List<HashMap<String, String>> getBms(String xn,String xq) {
		return myDao.getBms(xn,xq);
	}
	public List<HashMap<String, String>> getBm(String xn,String nd) {
		return myDao.getBm(xn,nd);
	}
	public HashMap<String, String> getGwxx(String xn,String yrdwdm,String xq) {
		return myDao.getGwxx(xn,yrdwdm,xq);
	}
	/**
	 * 获得经费信息Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getJfxxMap(QgzxJfglForm myForm) {
		HashMap<String,String> rs = new HashMap<String,String>();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		myForm.setNd(pkValue[0]);
		myForm.setBm(pkValue[1]);
		//年度
		rs.put("nd", pkValue[0]);
		rs.put("bm", pkValue[1]);
		rs.put("bmmc",getBmmc(pkValue[1]));
//		List<HashMap<String,String>> list = myDao.getJfxxList(myForm);
//		String rsList = getHtml(myForm,list);
//		rs.put("rsList", rsList);
		return rs;
	}
	/**
	 * 获得部门名称
	 * @param bm
	 * @return
	 */
	private String getBmmc(String bm) {
		return myDao.getBmmc(bm);
	}
	/**
	 * 构建经费信息页面
	 * @param myForm
	 * @param list
	 * @return
	 */
	private String getHtml(QgzxJfglForm myForm,
			List<HashMap<String, String>> list) {
		String doType = myForm.getDoType();
		StringBuilder html = new StringBuilder();
		for(int i = 0;i < list.size();i++){
			if("update".equalsIgnoreCase(doType)){
				html.append("<tr><td width='5%'><input type='checkbox' /></td>");
				html.append("<td width='15%'><input type='hidden' id='hbsj' name='hbsj' value='");
				html.append(list.get(i).get("hbsj"));
				html.append("'/>");
				html.append(list.get(i).get("hbsj"));
				html.append("</td><td width='15%'><input type='text' id='hbje' name='hbje' size='10' maxlength='10' onblur='checkInputNum(this)' value='");
				html.append(list.get(i).get("hbje"));
				html.append("'/></td><td width='65%'><input type='text' id='bz' name='bz' style='width:280px' maxlength='1000' value='");
				if(list.get(i).get("bz")!=null){
					html.append(list.get(i).get("bz"));
				}
				html.append("'/></td></tr>");
			}else if("view".equalsIgnoreCase(doType)){
				html.append("<tr><td width='5%'>");
				html.append(i+1);
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbsj"));
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbje"));
				html.append("</td><td width='65%'>");
				if(list.get(i).get("bz")!=null){
					html.append(list.get(i).get("bz"));
				}
				html.append("</td></tr>");
			}
		}
		
		return html.toString();
	}
	/**
	 * 验证保存信息是否正确
	 * @param model
	 * @return
	 */
	public String checkBcInfo(QgzxJfglForm model) {
		String nd = model.getNd();
		String[] bm= model.getBm().split("!!@@!!");
		String[] hbsj = model.getHbsj().split("!!@@!!");
		for(int i = 0; i < bm.length;i++){
			String[] elSel = {nd,bm[i],hbsj[i]};
			if(myDao.isExist(elSel)){
				return "同一部门不能存在两条相同划拨时间的经费项目,请确认";
			}
		}
		return "true";
	}
	/**
	 * 验证修改保存信息是否正确
	 * @param model
	 * @return
	 */
	public String checkXgBcInfo(QgzxJfglForm myForm) {
		String[] hbje = myForm.getHbje().split("!!@@!!");
		if(Base.isNull(myForm.getHbje())||"".equals(myForm.getHbje())){
			hbje = new String[]{"0"};
		}
		//划拨总金额
		Double hbzje = 0.0;
		for(int i = 0; i < hbje.length;i++){
			hbzje+=Double.valueOf(hbje[i]);
		}
		if(hbzje<myDao.getHbzje(myForm)){
			return "修改后的划拨总金额小于当前已划拨的金额,请确认";
		}
		return "true";
	}
	
	/**
	 * 获得默认参数
	 * @param user 
	 * @return
	 */
	public HashMap<String, String> getMrcs(User user) {
		HashMap<String, String> rs = new HashMap<String,String>();
		rs.put("nd", Base.currNd);
		rs.put("bm", user.getUserDep());
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			rs.put("dis", "true");
		}
		return rs;
	}
	
	/**============================浙大新勤工Begin============================*/
	/**
	 * 查询列表
	 */
	public List<HashMap<String,String>> getPageList(QgzxJfglForm t, User user) throws Exception {
		return myDao.getPageList(t, user);
	}
	/**
	 * 作用：得到用人部门
	 * 时间：2016-12-20
	 */
	public List<HashMap<String, String>>getYrbm(){
		return myDao.getYrbm();
	}
	/**
	 * 作用：传出年度、部门代码
	 * 时间：2016-12-20
	 */
	public HashMap<String, String> getXycs(User user) {
		HashMap<String, String> rs = new HashMap<String,String>();
		rs.put("nd", Base.currNd);
		rs.put("bm", user.getUserDep());
		return rs;
	}
	/**
	 * 作用：取经费划拨信息
	 * 时间：2016-12-20
	 */
	public HashMap<String, String> getJfhbMap(QgzxJfglForm myForm) {
		HashMap<String,String> rs = new HashMap<String,String>();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		myForm.setNd(pkValue[0]);
		myForm.setBm(pkValue[1]);
		//年度
		rs.put("nd", pkValue[0]);
		rs.put("bm", pkValue[1]);
		rs.put("bmmc",getBmmc(pkValue[1]));
		List<HashMap<String,String>> list = myDao.getJfxxList(myForm);
		String rsList = getJfhbym(myForm,list);
		rs.put("rsList", rsList);
		return rs;
	}
	/**
	 * 作用：画修改页面的经费划拨信息
	 * 时间：2016-12-20
	 */
	private String getJfhbym(QgzxJfglForm myForm,List<HashMap<String, String>> list) {
		StringBuilder html = new StringBuilder();
		for(int i = 0;i < list.size();i++){
				html.append("<tr><td width='5%'>");
				html.append(i+1);
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbsj"));
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbje"));
				html.append("</td><td width='65%'>");
				if(list.get(i).get("bz")!=null){
					html.append(list.get(i).get("bz"));
				}
				html.append("</td></tr>");
		}
		return html.toString();
	}
	/**
	 * @描述: 查看页面，发放明细列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-20 上午11:14:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFfmxList(QgzxJfglForm myForm) {
		return myDao.getFfmxList(myForm);
	}
	/**
	 * @描述: 是否是勤工管理员
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-20 下午05:24:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean sfQggly(String yhm){
		return myDao.sfQggly(yhm);
	}
	
	/**
	 * 此方法不共用了，以免以后有问题
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getZdJfxxMap(QgzxJfglForm myForm) {
		HashMap<String,String> rs = new HashMap<String,String>();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		myForm.setNd(pkValue[0]);
		myForm.setBm(pkValue[1]);
		//年度
		rs.put("nd", pkValue[0]);
		rs.put("bm", pkValue[1]);
		rs.put("bmmc",getBmmc(pkValue[1]));
		List<HashMap<String,String>> list = myDao.getJfxxList(myForm);
		String rsList = getHtml(myForm,list);
		rs.put("rsList", rsList);
		return rs;
	}
	/**============================浙大新勤工End============================*/
	
	/**
	 * 获取数据源月份
	 */
	public List<HashMap<String,String>> getSourceMonth(){
		return new QgzxJfglDAO().getSourceMonth();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:复制经费划拨数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-24 上午11:37:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean copyJfhbData(String targetmonth,String sourcemonth) throws Exception{
		String hbsj = GetTime.getTimeByFormat("yyyymmdd");
		return new QgzxJfglDAO().copyJfhbData(targetmonth, hbsj, sourcemonth);
	}
	
	/**
	 * 
	 * @描述: 当前月份是否存在经费划拨数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-24 下午03:24:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotJfhbDataExist(){
		return  new QgzxJfglDAO().checkIsNotJfhbDataExist();
	}
}

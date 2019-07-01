package xsgzgl.qgzx.gwglnew;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.conditions.KnsrdCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.lxsh.LxshForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlxxModel;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import common.Globals;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 */
public class QgzxGwglService extends BasicService {
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	QgzxGwglDAO qgzxGwglDAO = new QgzxGwglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public List<HashMap<String, String>> getGwxxPageList(QgzxGwglForm t, User user)
		throws Exception {
		return qgzxGwglDAO.getGwxxPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwxxAllList(QgzxGwglForm t, User user)
		throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return qgzxGwglDAO.getGwxxPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwsqPageList(QgzxGwglForm t, User user)
		throws Exception {
		return qgzxGwglDAO.getGwsqPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwsqAllList(QgzxGwglForm t, User user)
		throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return qgzxGwglDAO.getGwsqPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwshPageList(QgzxGwglForm t, User user)
		throws Exception {
		return qgzxGwglDAO.getGwshPageList(t, user);
	}
	
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = null;
		String[] cn = null;
		if("10052".equals(Base.xxdm)){
			 en = new String[] { "", "r", "xn", "bmmc","gwxh", "gwmc", "gwxzmc", "xqrs", "zgrs", "tgrs" };
			 cn = new String[] { "", "行号", "学年", "用人部门", "岗位序号","岗位名称", "岗位性质", "需求人数","在岗人数","退岗人数" };
		}
		else{
		 en = new String[] { "", "r", "xn", "bmmc", "gwmc", "gwxzmc", "xqrs", "zgrs", "tgrs" };
		 cn = new String[] { "", "行号", "学年", "用人部门", "岗位名称", "岗位性质", "需求人数","在岗人数","退岗人数" };
		}
		if("stu".equalsIgnoreCase(type)){
			en = new String[]{"", "r", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sfkns" };
			cn = new String[]{"", "行号", "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY, "专业", "班级","是否困难生" };
		}else if("gwsq".equalsIgnoreCase(type)){
			//浙江交通职业
			if("12036".equals(Base.xxdm)){
				en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
				cn = new String[]{"", "行号", "学年", "用人部门", "岗位名称","岗位性质", "需求人数", "需求困难生数", "申请时间", "审核状态" };
			}
			else{
			en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
			cn = new String[]{"", "行号", "学年", "用人部门", "岗位名称","岗位性质", "需求人数", "需求困难生数", "申请时间", "审核状态" };
			}
		}else if("gwsh".equalsIgnoreCase(type)){
			en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
			cn = new String[]{"", "行号", "学年", "用人部门", "岗位名称","岗位性质", "需求人数", "需求困难生数", "申请时间", "审核状态" };
		}
		
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwsqList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwsqList(model);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwshList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwshList(model);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxxList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwxxList(model);
	}
	
	
	/**
	 * 获得学生信息List
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getStuList(QgzxGwglForm model,HttpServletRequest request) throws Exception {
		
		return qgzxGwglDAO.getStuList(model,request);
	}
	
	public List<HashMap<String, String>> getStuPageList(QgzxGwglForm t, User user, String sfxyzgsc)
		throws Exception {
		return qgzxGwglDAO.getStuPageList(t, user, sfxyzgsc);
	}

	
	/**
	 * 获得岗位信息Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxxMap(QgzxGwglForm myForm) {
		return qgzxGwglDAO.getGwxxMap(myForm);
	}
	
	
	/**
	 * 获得岗位及人员信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getGwryxx(QgzxGwglForm model,HttpServletRequest request)throws Exception{
		//岗位信息
		HashMap<String,String> rs = qgzxGwglDAO.getGwxxMap(model);
		//获得在岗人员并生成html
		model.setZgzt("zg");
		//在岗人员学号
		String[] zgryxh = qgzxGwglDAO.getRyxhList(model);
		//发送在岗学生学号值到前台
		setRequest(request,zgryxh,rs);
		List<HashMap<String, String>> zgryxxList = getGwxsxxList(zgryxh,model);
		// ========= 在岗困难生数 < =========
		String zgknsrs = "0";
		if(zgryxxList.size() > 0){
			zgknsrs = zgryxxList.get(0).get("zgknsrs");
		}
		rs.put("zgknsrs", zgknsrs);
		// ========= 在岗困难生数 > =========
		rs.put("zgryHtml", createRyxxHtml(zgryxxList,model));
		//获得退岗人员并生成html
		model.setZgzt("tg");
		String[] tgryxh = qgzxGwglDAO.getRyxhList(model);
		List<HashMap<String, String>> tgryxxList = getGwxsxxList(tgryxh,model);
		rs.put("tgryHtml", createRyxxHtml(tgryxxList,model));
		//当前时间 yyyyMMdd
		DAO dao = DAO.getInstance();
		String currTime = dao.getNowTime("yyyyMMdd");
		rs.put("tgsj", currTime);
		return rs;
	}
	
	
	/**
	 * 获得岗位申请MAP
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getGwsqMap(QgzxGwglForm model){
		return qgzxGwglDAO.getGwsqMap(model);
	}
	/** 
	 * @描述:(是否是最后一级岗位)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午06:59:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isZhgw(QgzxGwglForm model) {
		ArrayList<HashMap<String, String>> spgws=qgzxGwglDAO.getSplcgw(model);
		String spgw=spgws.get(spgws.size()-1).get("spgw");
		return model.getGwid().equalsIgnoreCase(spgw);
	}
	
	/**
	 * 发送在岗学生学号+已经达到最大勤工数 的学号值到前台
	 * @param request
	 * @param zgryxh
	 * @throws SQLException 
	 */
	private void setRequest(HttpServletRequest request, String[] zgrypk, HashMap<String,String> gwxx) throws SQLException {
		String xn = gwxx.get("xn");
		int count = 0;
		//获得学年已经到达上限的学生学号
		String[] sxxhs = getGwxsxh(xn); 
		StringBuilder xhs = new StringBuilder();
		for(int i = 0;i<zgrypk.length;i++){
			String zgryxh = zgrypk[i].split("!!@@!!")[0];
			//判断是否是困难生
			KnsrdCondition knsrd = new KnsrdCondition();
			HashMap<String,String> knsTj = new HashMap<String,String>();
			if ("xq".equals(SQZQ)) {
				knsTj.put("xn", Base.currXn + ";" + Base.currXq);
			}else{
				knsTj.put("xn", Base.currXn);
			}
			String knsCount = knsrd.getKnsrdInfo(zgryxh, knsTj);
			if(knsCount==null && "".equals(knsCount)){
				knsCount="0";
			}	
			if(Integer.parseInt(knsCount)>0){
				count++;
			}
			xhs.append(zgryxh+"!!@@!!");
		}
		for(int i = 0;i<sxxhs.length;i++){
			String sxxh = sxxhs[i].split("!!@@!!")[0];
			xhs.append(sxxh+"!!@@!!");
		}
		request.setAttribute("yykns", count);
		request.setAttribute("xhs", xhs.toString());
	}


	/**
	 * 获得该学年已经到达上限的学生学号
	 * @param xn
	 * @return
	 * @throws SQLException 
	 */
	private String[] getGwxsxh(String xn) throws SQLException {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		String xsgws = qgzxCsszService.getCssz().get("xsgws");
		if("0".equalsIgnoreCase(xsgws)){
			return new String[]{};
		}
		return qgzxGwglDAO.getGwxsxh(xn,xsgws);
	}


	/**
	 * 获得岗位学生信息
	 * @param myForm
	 * @return
	 */
	private List<HashMap<String, String>> getGwxsxxList(String[] pks,QgzxGwglForm model) throws Exception {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String,String>>();
		int zgknsrs = 0; // 在岗困难生数
		for(int i = 0; i < pks.length;i++){
			String[] pkV = pks[i].split("!!@@!!");
			model.setXh(pkV[0]);
			model.setSgsj(pkV[1]);
			//model.setSqbhs(pkV[3]);
			HashMap<String, String> rs = qgzxGwglDAO.getGwxsxx(model);
			KnsrdCondition knsrd = new KnsrdCondition();
			//设置获取是否困难生增加 学年条件
			HashMap<String,String> param= new HashMap<String,String>();
			HashMap<String, String> gwxx=qgzxGwglDAO.getGwxxMap(model);
			if ("xq".equals(SQZQ)) {
				param.put("xn", Base.currXn + ";" + Base.currXq);
			}else{
				param.put("xn", Base.currXn);
			}
			String knsCount = knsrd.getKnsrdInfo(pkV[0],param);
			if(knsCount==null && "".equals(knsCount)){
				knsCount="0";
			}	
			if(rs!=null&&rs.size()!=0){
				rs.put("r", String.valueOf(i+1));
				//判断是否困难生
				if(Integer.parseInt(knsCount)>0){
					rs.put("sfkns", "是");
					zgknsrs++;
				}else{
					rs.put("sfkns", "否");
				}
				ryxxList.add(rs);
			}
		}
		if(ryxxList.size() > 0){
			ryxxList.get(0).put("zgknsrs", String.valueOf(zgknsrs));
		}
		return ryxxList;
	}
	
	
	/**
	 * 获得学生信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getXsxxList(QgzxGwglForm model) {
		DAO dao = DAO.getInstance();
		String[] xhs = model.getPkValue().split("!!@@!!");
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < xhs.length;i++){
			model.setXh(xhs[i]);
			HashMap<String, String> rs = qgzxGwglDAO.getXsxx(model);
			if(rs!=null&&rs.size()!=0){
				rs.put("r", String.valueOf(i+1));
				//判断是否困难生
				rs.put("sfkns", dao.isKns(xhs[i])?"是":"否");
				rs.put("sgsj", dao.getNowTime("yyyyMMdd"));
				ryxxList.add(rs);
			}
			
		}
		return ryxxList;
	}
	
	
	/**
	 * 创建人员信息html
	 * @param tgryxxList
	 * @return
	 */
	private String createRyxxHtml(List<HashMap<String, String>> rsArrList,QgzxGwglForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String,String> rs = rsArrList.get(i);
				if("ryxxZj".equalsIgnoreCase(model.getDoType()) || "ryxxTg".equalsIgnoreCase(model.getDoType())){
					html.append("<tr><td width='7%;'><input type='checkbox' value='"+rs.get("xh")+"!!@@!!"+rs.get("sgsj")+"!!@@!!"+rs.get("sjly")+"!!@@!!"+rs.get("sqbh")+"'/></td>");
				}else{
					html.append("<tr><td width='7%;'>"+rs.get("r")+"</td>");
				}
				html.append("<td width='13%'><a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
						+ rs.get("xh")
						+ "&quot;);return false;'>"
						+ rs.get("xh") + "</a></td>");
//				html.append("<td>"+rs.get("xh")+"</td>");
				html.append("<td width='13%'>"+rs.get("xm")+"</td>");
				html.append("<td width='31%'>"+rs.get("bjmc")+"</td>");
				html.append("<td width='11%'>"+rs.get("sfkns")+"</td>");
				html.append("<td width='10%'>"+rs.get("sfzx")+"</td>");
				if("ryxxZj".equalsIgnoreCase(model.getDoType())||"ryxxTg".equalsIgnoreCase(model.getDoType())){
					html.append("<td width='15%'>");
					html.append("<a href=\"#\" class=\"name\" onclick=\"showXsxx('"+replaceHtml(rs.get("xh"))+"');return false;\" value=\"" +replaceHtml(rs.get("xh"))+"\">");
					html.append(replaceHtml(rs.get("qggws")));
					html.append("</a></td>");
					if(Base.xxdm.equals(Globals.XXDM_ZJJTZYJSXY)&&"ryxxZj".equalsIgnoreCase(model.getDoType())){
					html.append("<td width='10%'><input type='text' width='90px' value='"+rs.get("sgsj")+"' name='sgsj' onfocus=\"showCalendar(this.id,'yyyyMM');\" id='sgsj"+i+"'>");
					}
				}
				if("gwxxCk".equalsIgnoreCase(model.getDoType())){
					html.append("<td width='15%'>"+rs.get("sgsj")+"</td>");
					if("tg".equalsIgnoreCase(model.getZgzt())){
						html.append("<td>"+rs.get("tgsj")+"</td>");
					}
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 保存新增加的岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean gwxxBc(QgzxGwglForm model) throws Exception {
		String gwxh="";
		if("10052".equals(Base.xxdm)){
			gwxh=qgzxGwglDAO.GetGwxh(model.getYrbm(), model.getXn());
		}
		model.setGwxh(gwxh);
		String gwdm = UniqID.getInstance().getUniqIDHash();
		model.setGwdm(gwdm);
		boolean result = new QgzxGwglJgDao().runInsert(model);
		if(Base.xxdm.equalsIgnoreCase("10351") && result) {//温州大学个性化
			if(result){				
				model.setDoType("zj");
				return bcSqglXy(model);
			}
		}
		return result;
	}
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	private boolean isExist(QgzxGwglForm model) {
		return qgzxGwglDAO.isExist(model);
	}
	
	/**
	 * 同一部门有效岗位名称重复
	 * @param model
	 * @return
	 */
	private boolean isExistSj(QgzxGwglForm model) {
		return qgzxGwglDAO.isExistSj(model);
	}
	
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	private boolean checkZjGwsqInfoExist(QgzxGwglForm model) {
		return qgzxGwglDAO.checkZjGwsqInfoExist(model);
	}
	/**
	 * 同一部门有效岗位名称重复
	 * @param model
	 * @return
	 */
	private boolean checkZjGwsqInfoExistSj(QgzxGwglForm model) {
		return qgzxGwglDAO.checkZjGwsqInfoExistSj(model);
	}
	
	
	
	
	/**
	 * 保存修改岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean update(QgzxGwglForm myForm) throws Exception {
		boolean flag =  new QgzxGwglJgDao().runUpdate(myForm);
		if(flag){
			flag = qgzxGwglDAO.runUpdate(myForm);
		}
		if(Base.xxdm.equalsIgnoreCase("10351")) {//温州大学个性化
			if(flag){		
				myForm.setDoType("xg");
				return bcSqglXy(myForm);
			}
		}		
		return flag;
	}
	
	
	/**
	 * 保存修改岗位申请
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean bcXgGwsq(QgzxGwglForm myForm) throws Exception {
		if("submit".equals(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
		myForm.setSplcid(splc);
		boolean result = qgzxGwglDAO.runUpdate(myForm);
		if("submit".equals(myForm.getType())){
			result = shlc.runSubmit( myForm.getPkValue(),myForm.getSplcid(),myForm.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
			if(!result){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if(Base.xxdm.equalsIgnoreCase("10351")) {//温州大学个性化
			myForm.setDoType("xg");
			if(result){				
				return bcSqglXy(myForm);
			}
		}
		return result;
	}
	
	/**
	 * @描述：审核通过时岗位修改 温州大学个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String bcXgGwsh(QgzxGwglForm myForm) throws Exception {
		return qgzxGwglDAO.bcXgGwsh(myForm)? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	/**
	 * 岗位信息复制
	 * @param xn
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwxxFz(String xn, QgzxGwglForm myForm) throws SQLException {
		String yxssz = myForm.getYxssz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] input = {xn,yxssz,gwkssj,gwjssj,myForm.getXq(),pkValue[i]};
			params.add(input);
		}
		return qgzxGwglDAO.gwxxSave(params)? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	
	/**
	 * 岗位信息删除
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String gwxxSc(QgzxGwglForm myForm) throws Exception {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < pkValue.length; i++){
			if(isUsed(pkValue[i])){
				return "岗位已被使用,不能被删除！";
			}
			params.add(new String[]{pkValue[i]});
			list.add(pkValue[i]);
		}
		if("10351".equals(Base.xxdm)){//温州大学个性化
			delXygl(list);//删除申请人岗位所属学院
		}
		return qgzxGwglDAO.gwxxSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	
	
	
	/**
	 * 岗位信息删除
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String gwsqSc(QgzxGwglForm myForm) throws Exception {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
			list.add(pkValue[i]);
		}
		if("10351".equals(Base.xxdm)){//温州大学个性化
			delXygl(list);
		}
		return qgzxGwglDAO.gwsqSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	
	
	
	/**
	 * 判断岗位是否已被使用
	 * @param pkValue
	 * @return
	 */
	private boolean isUsed(String pkValue) {
		return qgzxGwglDAO.isUsed(pkValue);
	}
	
	
	/**
	 * 获得用人单位List
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxGwglForm myForm) {
		return qgzxGwglDAO.getYrbm(myForm);
	}
	
	
	/**
	 * 获得用人单位名称
	 * @param yrdwdm
	 * @return
	 */
	public String getYrdwmc(String yrdwdm){
		return qgzxGwglDAO.getYrdwmc(yrdwdm);
	}
	
	
	/**
	 * 生成岗位代码（学年+4位流水号）
	 * @param xn
	 * @return
	 */
	public String getGwdm(String xn,int start) {
		String max = qgzxGwglDAO.getMaxGwdm();
		if(max==null){
			return xn+"0001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+start+1));
			String pre = "";
			for(int i = 0; i < 4-max.length();i ++){
				pre+="0";
			}
			return xn+pre+max;
		}
	}
	/**
	 * 增加页面的默认参数
	 * @param request
	 * @return
	 */
	public HashMap<String, String> setZjmrCs(HttpServletRequest request) {
		HashMap<String,String> rs = new HashMap<String,String>();
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		//如果不是勤工管理员
		User user = getUser(request);
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			rs.put("dis", "true");
		}
		DAO dao = DAO.getInstance();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		String sfkfgwsq = cs.get("sfkfgwsq");
		String gwsqkssj = cs.get("gwsqkssj");
		String gwsqjssj = cs.get("gwsqjssj");
		String sfkfxsgwsq = cs.get("sfkfxsgwsq");
		String xsgwsqkssj = cs.get("xsgwsqkssj");
		String xsgwsqjssj = cs.get("xsgwsqjssj");
		String dsfxy = cs.get("dsfxy");
		String kcxs = cs.get("kcxs");
		String dqsj = dao.getNowTime("yyyyMMdd");
		if("on".equalsIgnoreCase(sfkfgwsq) 
				&& (null==gwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(gwsqkssj))
				&& (null==gwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(gwsqjssj))){
			rs.put("kycz", "true");
		}
		if("on".equalsIgnoreCase(sfkfxsgwsq) 
				&& (null==xsgwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(xsgwsqkssj))
				&& (null==xsgwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(xsgwsqjssj))){
			rs.put("xssqkg", "true");
		}else{
			rs.put("xssqkg", "false");
		}
		String yrbm = user.getUserDep();
		rs.put("yrbm", yrbm);
		//所属部门赋予默认值
		rs.put("ssbm", yrbm);
		rs.put("ssbmmc", getYrdwmc(yrbm));
		rs.put("yrdwmc", getYrdwmc(yrbm));
		rs.put("qgzq", cs.get("qgzq"));
		rs.put("xn", StringUtils.isNull(cs.get("yrdwgwsqxn")) ? Base.currXn : cs.get("yrdwgwsqxn"));
		rs.put("xq", Base.currXq);
		rs.put("xqmc", Base.getXqmcForXqdm(Base.currXq));
		rs.put("dsfxy", dsfxy);
		rs.put("kcxs", kcxs);
		return rs;
	}
	
	
	/**
	 * 创建页面
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
				html.append("<td style=\"width:2.5%\">");
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
	 * 创建页面
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByGwsh(SearchRsModel rsModel,
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
				html.append(" value='" + replaceHtml(rs[0]) + "' "+replaceHtml(rs[1])+"/> ");
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

					if(j == 5) {
						html.append("<a href='javascript:void(0);' class='name' onclick='showViewLink(&quot;"
								+ replaceHtml(rs[0])
								+ "&quot;);return false;'>"
								+ replaceHtml(rs[j]) + "</a>");
					} else {
						html.append(replaceHtml(rs[j]));
					}
					
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 创建页面查询结果
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML2(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
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
	 * 验证保存信息
	 * @param model
	 * @return
	 */
	public String checkBcInfo(QgzxGwglForm model) {
		if(isExist(model)){
			return StringUtils.isNull(model.getXq()) ? "同一学年同一部门岗位名称重复，请确认！" :"同一学年同一学期同一部门岗位名称重复，请确认！";
		}
		if(isExistSj(model)){
			return "同一部门有效岗位名称重复，请确认！";
		}
		return "true";
	}
	
	/**
	 * 验证增加岗位申请信息
	 * @param model
	 * @return
	 */
	public String checkZjGwsqInfo(QgzxGwglForm model) {
		if(checkZjGwsqInfoExist(model)){
			return StringUtils.isNull(model.getXq()) ? "本学年本部门岗位名称重复，请确认！" : "本学年本学期本部门岗位名称重复，请确认！";
		}
		if(checkZjGwsqInfoExistSj(model)){
			return "本部门有效岗位名称重复，请确认！";
		}
		return "true";
	}
	
	
	/**
	 * 验证删除信息
	 * @param model
	 * @return
	 */
	public String checkScInfo(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			if(isUsed(pkValue[i])){
				return "岗位已被使用,不能删除！";
			}
		}
		return "true";
	}
	
	
	/**
	 * 验证复制信息
	 * @param model
	 * @return
	 */
	public String checkFzInfo(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwmcs="";
		for(int i = 0; i < pkValue.length; i++){
			// 通过pkValue获取岗位信息
			model.setPkValue(pkValue[i]);
			HashMap<String,String> map = getGwxxMap(model);
			model.setYrbm(map.get("yrdwdm"));
			String gwmc = map.get("gwmc");
			String yrdwdm = map.get("yrdwdm");
			if(gwmcs.indexOf(","+gwmc+yrdwdm+",")!=-1){
				return "勾选岗位中包含相同名称的岗位信息！";
			}
			gwmcs+=","+gwmc+yrdwdm+",";
			model.setGwmc(gwmc);
			// 验证前，清空pkValue
			model.setPkValue(null);
			if(isExist(model)){
				return "同一学年同一部门岗位名称重复，请确认！";
			}
			if(isExistSj(model)){
				return "同一部门有效岗位名称重复，请确认！";
			}
		}
		return "true";
	}

	
	/**
	 * 批量保存岗位人员信息
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String bcZjryxx(QgzxGwglForm model) throws SQLException {
		DAO dao = DAO.getInstance(); 
		String gwdm = model.getGwdm();
		String sgsj = model.getSgsj();
		String sfsgwsqsxz = model.getSfsgwsqsxz();
		String[] xh = model.getXh().split("!!@@!!");
		//允许的非困难生人数
		int fknsrs = Integer.parseInt(model.getFknsrs());
		int count = 0;
		List<String[]> params = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				if("1".equals(sfsgwsqsxz) && isOver(gwdm,xh[i])){
					return "学号为"+xh[i]+"的学生已到达当前学年最大岗位上限！";
				}
				//验证困难生人数
				KnsrdCondition knsrd = new KnsrdCondition();
				
				HashMap<String, String> knsTj=new HashMap<String, String>();
				if ("xq".equals(SQZQ)) {
					knsTj.put("xn", Base.currXn + ";" + Base.currXq);
				}else{
					knsTj.put("xn", Base.currXn);
				}
				String kns= knsrd.getKnsrdInfo(xh[i],knsTj);
				
				KnsjgService knsjgService=new KnsjgService();
				List<HashMap<String, String>> knsList = knsjgService.getKnsInfoList(xh[i]);
				//验证是否当前勤工岗位的困难生
				if(StringUtil.isNull(kns) && knsList != null){
					//是否市困难生
					boolean isKns=false;
					for (int j = 0; j < knsList.size(); j++) {
						if(model.getXn().equals(knsList.get(i).get("xn"))){
							isKns= true;
						}
					}
					if(!isKns){
						if ("xq".equals(SQZQ)) {
							return "学号为"+xh[i]+"的学生非当前学年学期下的困难生";
						}else{
							return "学号为"+xh[i]+"的学生非当前学年下的困难生";
						}
					}
				}
				
				if(kns==null || "".equals(kns)){
					kns = "0";
				}
				if(Integer.parseInt(kns)<=0){
					count++;
				}
				String[] el=null;
				if(Globals.XXDM_ZJJTZYJSXY.equals(Base.xxdm)){
				 el = new String[]{gwdm,xh[i],sgsj};
				}else{
					el = new String[]{gwdm,xh[i]};
				}
				params.add(el);
			}
		}
		if(count>fknsrs){
			return "该岗位的困难生数未能到达岗位要求！";
		}
		return qgzxGwglDAO.bcZjryxx(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	public boolean saveRyzj(QgzxGwglForm model, List<QgzxGwglForm> gwryList) throws Exception {
		boolean result = true;
		List<String[]> ryList = new ArrayList<String[]>();
		String[] ryxx = null;
		String[] xh = model.getXhs().split("!!@@!!");
		
		for (int i = 0; i < gwryList.size(); i++) {
			
			ryxx = new String[3];
			ryxx[0] = gwryList.get(i).getSgsj();
			ryxx[1] = gwryList.get(i).getGwdm();
			ryxx[2] = xh[i];
			ryList.add(ryxx);
		}
		if (result) {
			//批量保存
			qgzxGwglDAO.gwryUpdate(ryList);
		}
		return result;

	}
	
	
	/**
	 * 是否达到岗位上限
	 * @param string
	 * @return
	 */
	private boolean isOver(String gwdm,String xh) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		String zdgws = qgzxCsszService.getCssz().get("xsgws");
		if("0".equalsIgnoreCase(zdgws)||null==zdgws){
			return false;
		}
		String gws = qgzxGwglDAO.getGws(gwdm,xh);
		return Integer.parseInt(zdgws)<=Integer.parseInt(gws);
		
	}


	/**
	 * 批量保存岗位人员信息
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String scRyxx(QgzxGwglForm model) throws SQLException {
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				String[] el = new String[]{gwdm,xh[i]};
				params.add(el);
			}
		}
		return qgzxGwglDAO.scRyxx(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	


	/**
	 * 批量保存退岗人员信息
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String bcTgryxx(QgzxGwglForm model) throws SQLException {
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		String[] sgsj = model.getSgsj().split("!!@@!!");
		String[] sqids = model.getSqbhs().split("!!@@!!");
		String tgyy = model.getTgyy();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramssq = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				String[] el = new String[]{tgyy,gwdm,xh[i],sgsj[i]};
				params.add(el);
				//对应申请数据还原
				String[] sqid = new String[]{sqids[i]};
				paramssq.add(sqid);
			}
		}
		return qgzxGwglDAO.bcTgryxx(params)&&qgzxGwglDAO.hySqsj(paramssq)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 保存岗位批量退岗
	 */
	public String bcTggwxx(String[] gwdms, String tgyy) throws SQLException {
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsSq = new ArrayList<String[]>();
		for(int i = 0; i < gwdms.length; i++){
			String[] el = new String[]{tgyy,gwdms[i]};
			params.add(el);
			//对应申请数据还原
			String[] elSq = new String[]{gwdms[i]};
			paramsSq.add(elSq);
		}
		return qgzxGwglDAO.bcTggwxx(paramsSq, params)?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	
	/**
	 * 人员信息查看
	 * @param myForm
	 * @param request
	 * @return
	 */
	public HashMap<String, String> ryxxCk(QgzxGwglForm model,
			HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		//岗位信息
		HashMap<String,String> rs = qgzxGwglDAO.getXsxx(model);
		rs.put("sfkns", dao.isKns(model.getXh())?"是":"否");
		//获得在岗人员并生成html
		model.setZgzt("zg");
		List<HashMap<String, String>> zggwList = getGwxx(model);
		rs.put("zggwHtml", createGwxxHtml(zggwList,model));
		return rs;
	}


	/**
	 * 获得岗位信息根据学号
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> getGwxx(QgzxGwglForm model) {
		return qgzxGwglDAO.getGwxx(model);
	}

	
	/**
	 * 创建岗位信息页面
	 * @param gwxxList
	 * @param model
	 * @return
	 */
	private String createGwxxHtml(List<HashMap<String, String>> rsArrList,
			QgzxGwglForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String,String> rs = rsArrList.get(i);
				html.append("<tr><td>"+rs.get("r")+"</td>");
				html.append("<td>"+rs.get("xn")+"</td>");
				html.append("<td>"+rs.get("bmmc")+"</td>");
				html.append("<td>"+rs.get("gwmc")+"</td>");
				html.append("<td>"+rs.get("gwxzmc")+"</td>");
				html.append("<td>"+rs.get("sgsj")+"</td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	/**
	 * 验证删除人员信息
	 * @param myForm
	 * @return
	 */
	public String checkScRyxx(QgzxGwglForm model) {
		String message = "true";
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		for(int i = 0; i < xh.length; i++){
			if(isUsed(gwdm,xh[i])){
				return "学号为"+xh[i]+"的学生已发放过酬金不能删除！";
			}
		}
		return message;
	}


	/**
	 * 在此岗位上是否发放过酬金
	 * @param gwdm
	 * @param string
	 * @return
	 */
	private boolean isUsed(String gwdm, String xh) {
		return qgzxGwglDAO.isUsed(gwdm,xh);
	}


	/**
	 * 保存增加岗位申请
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean bcZjGwsq(QgzxGwglForm model) throws Exception {
		String gwdm = UniqID.getInstance().getUniqIDHash();
		model.setGwdm(gwdm);
		String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
		model.setSplcid(splc);
		if("submit".equals(model.getType())){
			model.setShzt(Constants.YW_SHZ);
		}else{
			model.setShzt(Constants.YW_WTJ);
		}
		boolean result = qgzxGwglDAO.runInsert(model);
		if("submit".equals(model.getType())){
			result = shlc.runSubmit(model.getGwdm(),model.getSplcid(),model.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
			if(!result){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if(Base.xxdm.equalsIgnoreCase("10351") && result) {//温州大学个性化
			model.setDoType("zj");
			return bcSqglXy(model);
		}
		return result;
	}

	
	/**
	 * 
	 */
	public boolean insertGwxxForZjlyzy(String[] zjs,String type,User user) throws Exception {
		List<QgzxGwglForm> gwsqrList = new ArrayList<QgzxGwglForm>();
		List<QgzxGwglForm> gwsqList = new ArrayList<QgzxGwglForm>();
		if(null != zjs && zjs.length>0){//如果有增加数据	
			for(int i = 0;i<zjs.length;i++){
				QgzxGwglForm model = new QgzxGwglForm();
				model.setUser(user);
				model.setType(type);
				//获取系统当前时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String sqsj = df.format(System.currentTimeMillis());
				model.setSqsj(sqsj);
				String[] zj = zjs[i].split(",");
				if(zj.length <3){
					for(int j = 0;j<zj.length;j++){
						if(j==0){						
							model.setLxr(zj[j]);
						}else if(j==1){
							model.setLxphone(zj[j]);
						}
						gwsqrList.add(model);
					}
				} else{
					for(int j = 0;j<zj.length;j++){
						//生成岗位编码，唯一标识
						String gwdm = UniqID.getInstance().getUniqIDHash();
						model.setGwdm(gwdm);
						String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
						model.setSplcid(splc);
						if("submit".equals(model.getType())){
							model.setShzt(Constants.YW_SHZ);
						}else{
							model.setShzt(Constants.YW_WTJ);
						}
						if(j==0){						
							model.setGwmc(zj[j]);
						}else if(j==1){
							model.setXqrs(zj[j]);
						}else if(j==2){
							model.setGwlx(zj[j]);
						}else if(j==3){
							model.setKnsrs(zj[j]);
						}else if(j==4){
							model.setGwshr(zj[j]);
						}else if(j==5){
							model.setGwshrxm(zj[j]);
						}else if(j==6) {
							model.setGwryyq(zj[j]);
						}else if(j==7) {
							model.setGznr(zj[j]);
						}
					}
					gwsqList.add(model);
				}
			}
		}
		boolean result = true;
		if((null != zjs && zjs.length>0)){
			result = qgzxGwglDAO.insertGwsqForZjlyzy(gwsqrList,gwsqList,user);
			//将岗位信息添加到审核状态表
			for(QgzxGwglForm model :gwsqList){
				if("submit".equals(model.getType())){
					result = shlc.runSubmit(model.getGwdm(),model.getSplcid(),model.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
					if(!result){
						throw new SystemException(MessageKey.SYS_SAVE_FAIL);
					}
				}
			}
			gwsqList.clear();
			gwsqrList.clear();
		}
		return result;
	}
	
	/**
	 * @throws Exception  
	 * @描述:保存申请学院数据范围(温州大学专用)(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-14 下午02:14:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public boolean bcSqglXy(QgzxGwglForm model) throws Exception {
		boolean result = true;
			
		if("xg".equalsIgnoreCase(model.getDoType())){			
			result = qgzxGwglDAO.delSqglXy(model.getGwdm());//删除
		}
		if(result){
			//if(null != model.getSqxy() && !model.getSqxy()[0].equalsIgnoreCase("")){//如果有申请人学院范围				
				result = qgzxGwglDAO.plInsertGlXy(model);//批量插入关联学院范围
			//}
		}		
		return result;
	}
	
	
	/**
	 * 岗位信息审核保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String gwxxshBc(QgzxGwglForm model) throws Exception {
		boolean flag = false;
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwxh ="";
		if("10052".equals(Base.xxdm)){
			gwxh = qgzxGwglDAO.GetGwxh(model.getYrbm(),model.getXn());
		}
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getUser().getUserName();
		String gwcjbz = model.getGwcjbz();
		String jfhb= model.getJfhb();
		String zc = model.getZc();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsByZs = new ArrayList<String[]>();
		if(pkValue.length == 0){
			return MessageInfo.MESSAGE_SH_FALSE;
		}
		if("1".equalsIgnoreCase(shzt)){ // 审核通过
			int okNum = 0;
			StringBuilder notOk = new StringBuilder(); // 重复岗位
			for(int i = 0; i < pkValue.length; i++){
				QgzxGwglForm formTemp = new QgzxGwglForm();
				formTemp.setPkValue(pkValue[i]);
				HashMap<String, String> formMap = getGwsqMap(formTemp);
				formTemp.setXn(formMap.get("xn"));
				formTemp.setYrbm(formMap.get("yrdwdm"));
				formTemp.setGwmc(formMap.get("gwmc"));
				formTemp.setGwjssj(formMap.get("gwjssj"));
				formTemp.setGwkssj(formMap.get("gwkssj"));
				formTemp.setXq(formMap.get("xq"));
				if(isExist(formTemp) || isExistSj(formTemp)){ // 重复岗位
					notOk.append(formMap.get("gwmc") + "；");
				}else{
					String[] el ={};
					if("12867".equalsIgnoreCase(Base.xxdm)) {
						el = new String[]{shyj,shzt,shr,pkValue[i]};
					}else {
						el = new String[]{shyj,shzt,shr,gwcjbz,jfhb,zc,pkValue[i]};
					}
					String[] el2 = new String[]{gwxh,pkValue[i]};
					params.add(el);
					paramsByZs.add(el2);
					okNum++;
				}
			}
			flag = qgzxGwglDAO.gwxxshBc(params);
			if(flag){
				flag = qgzxGwglDAO.insertByZs(paramsByZs);
			}
			String resultMsg = "审核成功"+okNum+"条！";
			if(pkValue.length - okNum > 0){
				resultMsg += "重复岗位：" + notOk.toString().substring(0, notOk.toString().length() - 1);
			}
			return flag?resultMsg : MessageInfo.MESSAGE_SH_FALSE;
		}else{
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{shyj,shzt,shr,gwcjbz,jfhb,zc,pkValue[i]};
				params.add(el);
			}
			flag = qgzxGwglDAO.gwxxshBc(params);
			if(flag){ // 删除岗位
				List<String[]> paramsSc = new ArrayList<String[]>();
				paramsSc.add(pkValue);
				flag = qgzxGwglDAO.gwxxSc(paramsSc);
			}
			return flag?MessageInfo.MESSAGE_SH_SUCCESS : MessageInfo.MESSAGE_SH_FALSE;
		}
	}
	//用人单位类别
	public List<HashMap<String, String>> getYddwLbList() throws Exception{
		return qgzxGwglDAO.getYddwLbList();
	}
	
	//资金来源
	public List<HashMap<String, String>> getZjlyList() throws Exception{
		return qgzxGwglDAO.getZjlyList();
	}
	
	/**
	 * 岗位信息复制
	 * @param xn
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwsqFz(String xn, QgzxGwglForm myForm,String xm,String xq) throws SQLException {
		String yxssz = myForm.getYxssz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] input = {xn,yxssz,gwkssj,gwjssj,xm,xq,pkValue[i]};
			params.add(input);
		}
		return qgzxGwglDAO.gwsqSave(params)? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	/**
	 * 验证复制信息
	 * @param model
	 * @return
	 */
	public String checkFzInfoSq(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwmcs="";
		for(int i = 0; i < pkValue.length; i++){
			// 通过pkValue获取岗位信息
			model.setPkValue(pkValue[i]);
			HashMap<String,String> map = getGwsqMap(model);
			model.setYrbm(map.get("yrdwdm"));
			String gwmc = map.get("gwmc");
			String yrdwdm = map.get("yrdwdm");
			if(gwmcs.indexOf(","+gwmc+yrdwdm+",")!=-1){
				return "勾选岗位中包含相同名称的岗位信息！";
			}
			gwmcs+=","+gwmc+yrdwdm+",";
			model.setGwmc(gwmc);
			// 验证前，清空pkValue
			model.setPkValue(null);
			if(isExistSq(model)){
				return "同一学年同一部门岗位名称重复，请确认！";
			}
			if(isExistSjSq(model)){
				return "同一部门有效岗位名称重复，请确认！";
			}
		}
		return "true";
	}
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	private boolean isExistSq(QgzxGwglForm model) {
		return qgzxGwglDAO.isExistSq(model);
	}
	
	/**
	 * 同一部门有效岗位名称重复
	 * @param model
	 * @return
	 */
	private boolean isExistSjSq(QgzxGwglForm model) {
		return qgzxGwglDAO.isExistSjSq(model);
	}
	
	/** 
	 * @描述:获取关联学院list（温州大学专用）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-14 下午04:01:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * void 返回类型 
	 * @throws 
	 */
	public void setGlxyList(QgzxGwglForm model,String gwdm) {
		List<HashMap<String,String>> list = qgzxGwglDAO.getGlxydm(gwdm);
		if(null != list && list.size()>0){
			String[] strs = new String[list.size()];
			for(int i = 0;i<list.size();i++){
				strs[i] = list.get(i).get("xydm");
			}
			model.setSqxy(strs);
		}
	}
	
	/** 
	 * @描述:是否为院系部门(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-17 下午05:17:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isYxBm(String bmdm){
		return qgzxGwglDAO.isYxbm(bmdm);
	}
	
	/**
	 * @throws Exception  
	 * @描述:删除申请人学院范围关联（温州大学）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-17 下午05:18:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delXygl(List<String> list) throws Exception{
		return qgzxGwglDAO.delXygl(list);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-6-2 上午10:10:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(QgzxGwglForm model,
			User user) throws Exception {
		List<HashMap<String, String>> fdylist=qgzxGwglDAO.getFdyPageList(model);
			return fdylist;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-7 下午03:24:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusi(QgzxGwglForm model, User user)  throws Exception {
		String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplcid();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splc);
		boolean flag = new QgzxGwglDAO().runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getGwdm(), splc, model.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	
	/**
	 * 
	 *单个审核保存
	 */
	@TransactionControl
	public boolean gwxxshBcNew(QgzxGwglForm form, User user) throws Exception{
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getGwdm());
		model.setSqrid(form.getSqr());
		model.setTzlj("qgzx_gwglnew_gwsh.do");
		model.setTzljsq("qgzx_gwglnew_gwsq.do");
		boolean reuslt = false;
		//防止存在审核时可以修改表单字段的情况，必须立即更新申请表字段以免造成数据不一致
		new QgzxGwglDAO().runUpdate(form);
		QgzxGwglForm allSqxxForm = new QgzxGwglDAO().getModel(form.getGwdm());
		if(isExistSj(allSqxxForm)){ // 重复岗位
			throw new SystemException();
		}
		String zhzt = shlc.runAuditingNotCommit(model);
		form.setShzt(zhzt);
		reuslt = new QgzxGwglDAO().runUpdateNotCommit(form, form.getGwdm());
		// 保存到结果表
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			new QgzxGwglDAO().deleteJgbData(allSqxxForm);
			QgzxGwglForm jgForm = new QgzxGwglForm();
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(allSqxxForm));
			jgForm.setSjly("1");
			String gwxh = "";
			if("10052".equals(Base.xxdm)){
				gwxh = qgzxGwglDAO.GetGwxh(jgForm.getYrdwdm(),jgForm.getXn());
			}
			jgForm.setGwxh(gwxh);
			reuslt = new QgzxGwglJgDao().runInsertNotCommit(jgForm);
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-8 下午05:34:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(QgzxGwglForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		QgzxGwglForm model = new QgzxGwglForm();
		String[] sqids = t.getId();
		String[] gwid = t.getGwids();
		String[] sqrs = t.getSqrs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setGwdm(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSqr(sqrs[i]);
			boolean isSuccess = gwxxshBcNew(model, user);
			if (!isSuccess) {
				failZghs.add(sqrs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failZghs);
		if (failZghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午11:03:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(QgzxGwglForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = new QgzxGwglDAO().runUpdate(myForm, myForm.getGwdm());
		if (result) {
			QgzxGwglJgDao jgService = new QgzxGwglJgDao();
		
			// 删除结果表中的申请结果
			jgService.runDelete(new String[]{myForm.getGwdm()});
		
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午11:03:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, QgzxGwglForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		new QgzxGwglDAO().runUpdate(model, ywid);
		return cancelFlag;
	}
	
	public List<HashMap<String,String>> getGwPageList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getPageList(model,user);
	}
	public List<HashMap<String,String>> getExportList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getExportList(model,user);
	}

	public List<HashMap<String,String>> getGwfbshList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getGwfbshList(model,user);
	}

	public List<HashMap<String,String>> getJgPageList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getJgPageList(model,user);
	}
	public List<HashMap<String,String>> getYrdwList(QgzxGwglForm t,User user) throws Exception {
		return qgzxGwglDAO.getYrdwList(t,user);
	}

	public List<HashMap<String,String>> getGwList(){
		return qgzxGwglDAO.getGwlbList();
	}
	public HashMap<String,String> getGwlbById(String id){
		return qgzxGwglDAO.getGwlbById(id);
	}
	public HashMap<String,String> getGwxxByGwdm(String gwdm){
		return qgzxGwglDAO.getGwxxByGwdm(gwdm);
	}

	public boolean saveGw(QgzxGwglForm t,User user) throws Exception {
		boolean flag = false;
		if("submit".equals(t.getType())){
			t.setShzt(Constants.YW_SHZ);
		} else {
			t.setShzt(Constants.YW_WTJ);
		}
		String dwlb = qgzxGwglDAO.getDwlb(user.getUserName());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//校内单位
			t.setSplc(splcMap.get("yrdwsplc"));
		} else {//校外单位
			t.setSplc(splcMap.get("xwgwsplc"));
		}
		t.setFbsj(DateUtils.getCurrTime());
		t.setGwdm(UniqID.getInstance().getUniqIDHash());
		t.setXn(Base.currXn);
		t.setXq(Base.currXq);
		flag = qgzxGwglDAO.insertGwxx(t);
		if(flag && "submit".equals(t.getType())){
			ShlcInterface shlc = new CommShlcImpl();
			flag = shlc.runSubmit(t.getGwdm(), t.getSplc(),user.getUserName(),"qgzx_gwglnew_gwfbsh.do","qgzx_gwglnew_gwfb.do");
		}
		return flag;
	}

	public boolean updateGw(QgzxGwglForm t,User user) throws Exception {
		boolean flag = false;
		if("submit".equals(t.getType())){
			t.setShzt(Constants.YW_SHZ);
		} else {
			t.setShzt(Constants.YW_WTJ);
		}
		String dwlb = qgzxGwglDAO.getDwlb(user.getUserName());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//校内单位
			t.setSplc(splcMap.get("yrdwsplc"));
		} else {//校外单位
			t.setSplc(splcMap.get("xwgwsplc"));
		}
		t.setFbsj(DateUtils.getCurrTime());
		flag = qgzxGwglDAO.updateGwxx(t);
		if(flag && "submit".equals(t.getType())){
			flag = shlc.runSubmit(t.getGwdm(), t.getSplc(),user.getUserName(),"qgzx_gwglnew_gwfbsh.do","qgzx_gwglnew_gwfb.do");
		}
		return flag;
	}

	public boolean submitGwfb(QgzxGwglForm model,User user) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			String dwlb = qgzxGwglDAO.getDwlb(user.getUserName());
			HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
			if("01".equals(dwlb)){//校内单位
				model.setSplc(splcMap.get("yrdwsplc"));
			} else {//校外单位
				model.setSplc(splcMap.get("xwgwsplc"));
			}
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = qgzxGwglDAO.updateGwfbSq(model);
		boolean result = false;
		if(flag){
			result = shlc.runSubmit(model.getGwdm(), model.getSplc(),user.getUserName(),"qgzx_gwglnew_gwfbsh.do","qgzx_gwglnew_gwfb.do");
		}
		return result;
	}

	public boolean cancelGwfb(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid,lcid);
	}
	public boolean updateGwfb(QgzxGwglForm model) throws Exception {
		return qgzxGwglDAO.updateGwfbSq(model);
	}

	public HashMap<String, String> getYrdwByUser(String yhm){
		return qgzxGwglDAO.getYrdwByUser(yhm);
	}

	public boolean saveSh(QgzxGwglForm model,User user) throws Exception{

		ShxxModel shxxModel = new ShxxModel();
		// 审核流程ID
		shxxModel.setShlc(model.getSplc());
		// 审核人
		shxxModel.setShr(user.getUserName());
		// 审核意见
		shxxModel.setShyj(model.getShyj());
		// 审核状态
		shxxModel.setShzt(model.getShjg());
//        shxxModel.setThgw(model.getThgw());
		// 审核岗位ID
		shxxModel.setGwid(model.getGwid());
		// 业务ID(多为申请ID)
		shxxModel.setYwid(model.getGwdm());
		shxxModel.setSqrid(model.getYrdwid());
		shxxModel.setTzlj("qgzx_gwglnew_gwfbsh.do");
		shxxModel.setTzljsq("qgzx_gwglnew_gwfb.do");

		boolean result = false;
		String zhzt = shlc.runAuditing(shxxModel);
		QgzxGwglForm form = new QgzxGwglForm();
		form.setGwdm(model.getGwdm());
		form.setShzt(zhzt);
		form.setSfzd(model.getSfzd());
		form.setSfxsgz(model.getSfxsgz());
		result = qgzxGwglDAO.updateGwfbSqxx(form);
		return result;
	}
	public String plshBc(QgzxGwglForm t, User user) throws Exception {

		QgzxGwglForm model = new QgzxGwglForm();
		String[] sqids = t.getId();
		String[] gwid = t.getGwids();
		String[] sqrs = t.getSqrs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//要不要做验证有待研究

		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplc(splcids[i]);
			model.setGwid(gwid[i]);
			model.setGwdm(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setYrdwid(sqrs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(sqrs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failZghs);
		if (failZghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	public boolean cancelSh(QgzxGwglForm model) throws Exception {
		model.setShzt(Constants.YW_SHZ);
		return qgzxGwglDAO.updateGwfbSqxx(model);
	}

	public String yz(User user){

		if(qgzxGwglDAO.isHmdYh(user)){
			return "您已被管理员拉入黑名单，不能发布岗位！";
		}

		if(!qgzxGwglDAO.haveDw(user)){
			return "未查询到您的单位信息，请确认！";
		}
		return "success";
	}

	public boolean gwSc(String gwdms, User user) throws Exception {

		return qgzxGwglDAO.deleteGw(gwdms.split(","));
	}

	public boolean checkIsUsed(String[] gwdms){
		return qgzxGwglDAO.checkIsUsed(gwdms);
	}

	public boolean insertJg(QgzxGwglForm t, User user) throws Exception {
		t.setFbsj(DateUtils.getCurrTime());
		t.setGwdm(UniqID.getInstance().getUniqIDHash());
		t.setXn(Base.currXn);
		t.setXq(Base.currXq);
		t.setShzt("1");
		t.setSjly("1");
		return qgzxGwglDAO.insertJg(t);
	}

	public boolean updateJg(QgzxGwglForm t, User user) throws Exception {
		t.setFbsj(DateUtils.getCurrTime());
		return qgzxGwglDAO.updatejg(t);
	}
}

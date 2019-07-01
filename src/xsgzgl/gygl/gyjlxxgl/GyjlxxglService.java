package xsgzgl.gygl.gyjlxxgl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.base.util.DateTranCnDate;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

public class GyjlxxglService extends CommService{
	
	private GyjlxxglDao dao=new GyjlxxglDao();
	
	public List<HashMap<String, String>> getToplist(String[] colListCN ) throws Exception {
		String[] en = new String[colListCN.length];
		for(int i=0;i<en.length;i++){
			en[i]="tr_"+i;
		}
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.arrayToList(en,colListCN);
		return list;
	}
	
	/**
	 * 获得表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] str = null;
		if(type.equals("jlxx")){
			str = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"班级","楼栋名称","寝室号","床位号"};
		}else if(type.equals("wjxx")){
			str = new String[]{"学号","姓名","性别","班级","住宿寝室","违纪时间","违纪类别", "处理结果"};
		}else if(type.equals("wjcl")){
			str = new String[]{"学号","姓名","性别","住宿寝室","违纪时间","违纪类别","处理结果","审核状态"};
		}else if(type.equals("shxx")){
			str = new String[]{"","学号","姓名","性别","住宿寝室","违纪时间","违纪类别","处理结果","审核状态"};
		}
		return str;
	}
	
	/**
	 * 获得住宿学生纪律信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJlxxList(GyjlxxglForm model, HttpServletRequest request) throws Exception{
		return dao.getJlxxList(model,request);
	}
	/**
	 * 保存纪律信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String saveJlxx(GyjlxxglForm myForm,HttpServletRequest request) throws Exception{
		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//单个学生
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//多个学生
			pk_xh = xhstr.split("!!splitOne!!");
		}else {//数据集
			pk_xh = dao.getXhs(searchTjstr);
		}
		myForm.setPk_xh(pk_xh);
		return dao.saveJlxx(myForm, user);
	}
	
	/**
	 * 获得纪律大类list
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request){
		return dao.getJldlList(request);
	}
	/**
	 * 获取纪律类别list
	 * @param jldldm
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getJllbList(String jldldm,HttpServletRequest request){
		return dao.getJllbList(jldldm,request);
	}
	
	/**
	 * 公寓纪律处分类别表
	 * @param jldldm
	 * @param request
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>> getCflbList(){
		return dao.getCflbList();
	}
	
	/**
	 * 获得学生信息
	 * @param xh
	 * @param request
	 * @return
	 */
	public Map<String, String> getXsxx(String xh, HttpServletRequest request) {
		return dao.getXsxx(xh,request);
	}
	/**
	 * 获得学生的违纪信息
	 * @param xh
	 * @param request
	 * @return
	 */
	public List<String[]> getOneWjxxList(String xh,String wjsj, HttpServletRequest request) {
		return dao.getOneWjxxList(xh,wjsj,request);
	}
	/**
	 * 获得历史违纪信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getWjxxList(GyjlxxglForm myForm, HttpServletRequest request,String type) throws Exception {
		return dao.getWjxxList(myForm,request,type);
	}
	/**
	 * 删除违纪信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public String delWjxx(GyjlxxglForm myForm, HttpServletRequest request) throws SQLException {
		return dao.delWjxx(myForm,request);
	}
	/**
	 * 获得学生违纪信息
	 * @param pkValue
	 * @param request
	 * @return
	 */
	public  Map<String, String> getXswjxx(String[] pkValue, HttpServletRequest request) {
		return dao.getXswjxx(pkValue,request);
	}

	/**
	 * 获得同寝室
	 * @param xh
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getTqs(String xh,
			HttpServletRequest request) {
		return dao.getTqsxsxx(xh,request);
	}
	
	
	/**
	 * 
	 * @描述:浙江传媒个性化，根据违纪时间获取学生对应请假时间
	 * @作者：cq [工号：785]
	 * @日期：2017-6-13 下午05:47:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param wjsj
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjInfo(String xh,String wjsj){
		return dao.getQjInfo(xh,wjsj);
	}
	
	

	/**
	 * 保存公寓纪律信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public String saveGyjlxx(GyjlxxglForm myForm, HttpServletRequest request) throws SQLException, ParseException {
		String[] xh = myForm.getXh().split("!!@@!!");
		String gyjllbdldm = myForm.getJldldm();
		String gyjllbdm = myForm.getJllbdm();
		String wjsj = myForm.getWjsj();
		String bz = myForm.getBz();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String czr = getUser(request).getUserName();
		String fileid = myForm.getFileid();
		String ycxh = "";
		String gyjllbdlmc = dao.getJldlListByDm(gyjllbdldm);
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = sdf.parse(wjsj);
		int n = DateTranCnDate.getDay(d);
		if("13573".equalsIgnoreCase(Base.xxdm)) {
			if("夜不归宿".equals(gyjllbdlmc)) {
				for(int i =0; i < xh.length;i++){				
					String num = dao.isExists2(xh[i],wjsj);
					if(!num.equals("0")) {
						return "该时间段内学生已申请假条，不需要登记！";
					}
					if(n == 7 || n == 1) {
						String num2 = dao.isExists3(xh[i],wjsj);
						if(!num2.equals("0")) {
							return "该学生已申请了长期假条，周末不需要登记！";
						}
					}
				}
				
			}
		}

		List<String[]>param = new ArrayList<String[]>();
		String[] jllbdms=gyjllbdm.split(",");
		for(String jllbdm:jllbdms){
			if(StringUtils.isNull(jllbdm)){
				continue;
			}
			for(int i =0; i < xh.length;i++){
				String[] pkValue = new String[]{xh[i],wjsj,jllbdm};
				if(isExist(pkValue)){
					if(!ycxh.contains(pkValue[0])){
						ycxh = ycxh + pkValue[0]+",";
					}
					continue;
				}
				String[] el = new String[]{gyjllbdldm,jllbdm,wjsj,bz,czr,xn,xq,xh[i]};
				if("70002".equals(Base.xxdm)){
					 el = new String[]{gyjllbdldm,jllbdm,wjsj,bz,czr,xn,xq,fileid,xh[i]};
				}
				param.add(el);
			}
		}
		if(param.size() ==0){
			return "保存失败！<br>选中学生的违纪记录已录入,无需重复录入！";
		}else{
			boolean result = false;
			
			try {
				result = dao.saveGyjlxx(param);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			return result&&(ycxh.equals("")) ? "保存成功！" : "保存成功！<br>其中,学号 "+ycxh.substring(0,ycxh.length()-1)+" 的违纪记录已录入,无需重复录入！";
		}
	}
	
	private boolean isExist(String[] pkValue) {
		return dao.isExists(pkValue);
	}

	/**
	 * 修改公寓纪律信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public String updateGyjlxx(GyjlxxglForm myForm, HttpServletRequest request) throws Exception {
		String czr = getUser(request).getUserName();
		myForm.setCzr(czr);
		String xh = myForm.getXh();
		String gyjllbdldm = myForm.getJldldm();
		String wjsj = myForm.getWjsj();
		String gyjllbdlmc = dao.getJldlListByDm(gyjllbdldm);
		String gyjllbdm = myForm.getJllbdm();
		String yjllbdm = request.getParameter("yjllbdm");
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = sdf.parse(wjsj);
		int n = DateTranCnDate.getDay(d);
		if("13573".equalsIgnoreCase(Base.xxdm)) {
			if("夜不归宿".equals(gyjllbdlmc)) {
				String num = dao.isExists2(xh,wjsj);
				if(!num.equals("0")) {
					return "该时间段内学生已申请假条，不需要登记！";
				}
				if(n == 7 || n == 1) {
					String num2 = dao.isExists3(xh,wjsj);
					if(!num2.equals("0")) {
						return "该学生已申请了长期假条，周末不需要登记！";
					}
				}			
			}
		}
		// 纪律类别修改
		if(!gyjllbdm.equals(yjllbdm)){
			String[] pkValue = new String[]{xh,wjsj,gyjllbdm};
			if(isExist(pkValue)){
				return "该学生的违纪记录已录入,无需重复录入！";
			}
		}
		return dao.updateGyjlxx(myForm,request);
	}
	
	/**
	 * 获取审核信息
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<String[]>getGyjlShList(GyjlxxglForm myForm,User user, HttpServletRequest request) throws Exception{
		
		return dao.getGyjlShList(myForm, user, request);
		
	}
	
	/**
	 * 修改审核状态
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveShzt(GyjlxxglForm myForm,User user) throws Exception{
		
		return dao.saveShzt(myForm, user);
	}
	
	/**
	 * 批量保存处理情况
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean plclGyjlxx(GyjlxxglForm myForm) throws Exception{
		return dao.plclGyjlxx(myForm);
	}
	
	/**
	 * 修改处理结果
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCljg(GyjlxxglForm myForm,User user) throws Exception{
		
		return dao.saveCljg(myForm, user);
	}
}

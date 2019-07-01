package xgxt.szdw.bjlh.fdykh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

import common.Globals;

public class BjlhFdykhService extends BasicService{

	BjlhFdykhDAO dao = new BjlhFdykhDAO();
	
	/**
	 * 获取表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] topTr=new String[]{};
		if("khdxxz".equals(type)){
			topTr=new String[]{"职工号","姓名","学院","考核小组成员"};
		}else if("khdxxzyh".equals(type)){
			topTr=new String[]{"用户名","姓名","学院"};
		}else if("fdykh".equals(type)){
			topTr=new String[]{"学年","考核表ID","考核表名称","评分对象","是否启用"};
		}else if("khbxm".equals(type)){//考核表项目
			topTr=new String[]{"一级指标","二级指标","考核内容","分值","评分类型","显示顺序"};
		}else if("khbtj".equals(type)){//考核统计 
			// 学校代码
			String xxdm = Base.xxdm;
			if(xxdm.equals(Globals.XXDM_CZZYJSXY)){
				topTr=new String[]{"学年", "职工号","姓名", "所在部门","学生评分情况","学生平均分","考核小组平均分","总分","考核等级","津贴等级"};
			}else{
				if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核
					topTr=new String[]{"学年", "职工号","姓名", "所在部门","学生评分情况","学生平均分","总分"};
				} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核
					topTr=new String[]{"学年", "职工号","姓名", "所在部门","考核小组平均分","总分"};
				}else{
					topTr=new String[]{"学年", "职工号","姓名", "所在部门","学生评分情况","学生平均分","考核小组平均分","总分"};
				}
				
			}
		}else if("jskhcp".equals(type)){
			topTr=new String[]{"学年","职工号","姓名","所在部门","是否考评","总分"};
		}
		
		return topTr;
	}
	/**
	 * 获取辅导员考核列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getTableList(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		return dao.getTableList(myForm,request);
	}
	
	/**
	 * 查询用户
	 * @param form
	 * @return
	 */
	public List<String[]> queryYh(BjlhFdykhForm myForm){
		return dao.queryYh(myForm);
	}

	public boolean saveFdyKh(String[] yhs, String[] khzgh) {
		List<String[]> params = new ArrayList<String[]>(); 
		List<String[]> delParams = new ArrayList<String[]>();
		
		for(int i=0; i<khzgh.length; i++){
			delParams.add(new String[]{khzgh[i]});
			for(int j=0; j<yhs.length; j++){
				params.add(new String[]{yhs[j],khzgh[i]});
			}
		}
		
		return dao.saveFdyKh(params, delParams);
	}
	
	/**
	 * 获取辅导员考核列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getFdykhList(BjlhFdykhForm model) throws Exception{
		return dao.getFdykhList(model);
	}
	
	/**
	 * 获取评分对象列表
	 * @return
	 */
	public List<HashMap<String,String>> getPfdxList(){
		List<HashMap<String,String>> pfdx=new ArrayList<HashMap<String,String>>();
		if(SzkhCssz.KHRY.equalsIgnoreCase("xs")){
			HashMap<String,String> xs=new HashMap<String, String>();
			xs.put("dm", "xs");
			xs.put("mc", "学生");
			pfdx.add(xs);
		}else if(SzkhCssz.KHRY.equalsIgnoreCase("cpz")){
			HashMap<String,String> pfxz=new HashMap<String, String>();
			pfxz.put("dm", "pfxz");
			pfxz.put("mc", "考核小组");
			pfdx.add(pfxz);
		}else{
			HashMap<String,String> xs=new HashMap<String, String>();
			xs.put("dm", "xs");
			xs.put("mc", "学生");
			pfdx.add(xs);
			HashMap<String,String> pfxz=new HashMap<String, String>();
			pfxz.put("dm", "pfxz");
			pfxz.put("mc", "考核小组");
			pfdx.add(pfxz);
		}
		
		return pfdx;
	}
	
	/**
	 * 保存考核表信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveKhbInfo(BjlhFdykhForm model,String type) throws Exception{
		return dao.saveKhbInfo(model, type);
	}
	
	/**
	 * 删除考核表信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteKhbInfo(BjlhFdykhForm model) throws Exception{
		String msg=dao.checkKhbQx(model);
		if("".equals(msg)){
			msg=dao.deleteKhbInfo(model)?"删除成功！":"删除失败！";
		}else{
			msg+="不可以删除！";
		}
		return msg;
	}
	
	/**
	 * 验证考核表权限
	 * @param model
	 * @return
	 */
	public String checkKhbQx(BjlhFdykhForm model){
		return dao.checkKhbQx(model);
	}
	
	/**
	 * 复制考核表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean copyKhbInfo(BjlhFdykhForm model) throws Exception{
		return dao.copyKhbInfo(model);
	}

	/**
	 * 考核表启用状态维护
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String sfqyKhb(BjlhFdykhForm model) throws Exception{
		return dao.sfqyKhb(model);
	}
	
	/**
	 * 获取辅导员考核项目列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getFdykhXmList(BjlhFdykhForm model){
		return dao.getFdykhXmList(model);
	}
	
	/**
	 * 保存考核表项目信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveKhbXmxx(BjlhFdykhForm model,String type) throws Exception{
		return dao.saveKhbXmxx(model,type);
	}
	
	/**
	 * 删除考核表项目信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteKhbXmxx(BjlhFdykhForm model) throws Exception{
		return dao.deleteKhbXmxx(model);
	}
	
	/**
	 * 获取考核表项目信息单条记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getKhbXmxxOne(BjlhFdykhForm model){
		return dao.getKhbXmxxOne(model);
	}
		
	/**
	 * 获取考核表信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getKhbxx(BjlhFdykhForm model){
		return dao.getKhbxx(model);
	}
	
	/**
	 * 获取经过处理一级指标的考核表项目列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getKhbXmYjzbColNum(BjlhFdykhForm model){
		List<HashMap<String,String>> xmList=getFdykhXmList(model);//考核项目列表
		if(xmList==null||xmList.size()==0){
			return null;
		}
		HashMap<String,String> currXm=xmList.get(0);
		HashMap<String,String> yjzbStartXm=currXm;//一级指标起始项目
		String yjzb=currXm.get("yjzb");//一级指标
		int yjzbRowNum=0;//一级指标合并单元格个数
		for(int i=0;i<xmList.size();i++){
			currXm=xmList.get(i);
			if(yjzb.equals(currXm.get("yjzb"))){
				yjzbRowNum++;
			}else{
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
				yjzbStartXm=currXm;
				yjzbRowNum=1;
				yjzb=currXm.get("yjzb");
			}
			//如果是循环到了最后，则直接放入
			if(i==xmList.size()-1){
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
			}
		}
		return xmList;
	}
	
	/**
	 * 获取辅导员考核测评
	 * @param userName
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public  List<String[]> getFdykhcpList(String userName, BjlhFdykhForm myForm,
			HttpServletRequest request) throws Exception {
		return dao.getFdykhcpList(userName,myForm,request);
	}
		
	/**
	 * 获取统计
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfo(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		return dao.getFdyKhTjInfo(myForm,request);
	}
	
	/**
	 * 获取统计（池州学院）
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfoforCzxy(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		return dao.getFdyKhTjInfoforCzxy(myForm,request);
	}
	
	/**
	 * 获得辅导员信息
	 * @param yhm
	 * @return
	 */
	public List<HashMap<String,String>> getYhmxx(String yhm,String lx) {
		return dao.getYhmxx(yhm,lx);
	}
	
	public HashMap<String, String> getMrsz(String pfdx) {
		return dao.getMrsz(pfdx);
	}
	
	public String getDate(){
		return dao.getNowTime("YYYYMMDD");
	}
	public boolean saveFdykhpfb(String[] xmid, String[] df,BjlhFdykhForm myForm) throws Exception {
		return dao.saveFdykhpfb(xmid,df,myForm);
	}
	
	public boolean checkFdykhpf(String xmid[],String[] df) throws Exception{
		boolean flag;
		flag = true;
		for(int i=0;i<df.length;i++){
			if(!dao.checkFdykhpf(xmid[i], df[i])){
				flag =false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 获取考核表是否已作答问卷
	 * @param model
	 * @return
	 */
	public boolean getKhbSfzd(BjlhFdykhForm myForm){
		return dao.getKhbSfzd(myForm);
	}
	
	/**
	 * 判断是否超过考核时间
	 * @param
	 * @return
	 */
	public boolean getSfcgkhsj(){
		String dqsj = dao.getNowTime("YYYYMMDD");
		return dao.getSfcgkhsj(dqsj);
	}
	
	/**
	 * 辅导员成绩统计维护
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> fdycjTjWh(BjlhFdykhForm myForm) {
		return dao.fdycjTjWh(myForm);
	}
	
	/**
	 * 获取考核等级下拉值
	 * @param request
	 * @return
	 */
	public Object getKhDjList(HttpServletRequest request) {
		return dao.getKhDjList(request);
	}
	
	/**
	 * 获取津贴等级下拉值
	 * @param request
	 * @return
	 */
	public Object getJtDjList(HttpServletRequest request) {
		return dao.getJtDjList(request);
	}
	
	/**
	 * 辅导员成绩统计维护保存
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean fdycjTjWhBc(BjlhFdykhForm myForm) throws Exception {
		return dao.fdycjTjWhBc(myForm);
	}
	
	/**
	 * 辅导员成绩统计导出
	 * @param myForm
	 * @param outputStream
	 * @param xn
	 * @param yf
	 * @param xymc
	 * @throws Exception 
	 */
	public void expFdycjTj(BjlhFdykhForm myForm,ServletOutputStream os,HttpServletRequest request) throws Exception {
		String title = "辅导员成绩统计";
		// 固定列
		String[] gdName = new String[] { "学年", "职工号", "姓名", "所在部门", "学生评分情况","学生平均分","考核小组平均分","总分","考核等级","津贴等级" };
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// 导出报表的固定数据
		List<String[]> gdlist = dao.getFdyKhTjInfoforCzxyNotFy(myForm,request);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充表头
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 10, 800);// 标题
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// 填充内容
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 1; j < info.length; j++) {
						ws.addCell(new Label(j-1, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/** 
	 * @描述:(项目的平均分列表一级标题行处理)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-9 下午04:58:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdykhmx(BjlhFdykhForm model) {
		List<HashMap<String,String>> xmList=getKhXmPjfList(model);//考核项目列表平均分
		if(xmList==null||xmList.size()==0){
			return null;
		}
		HashMap<String,String> currXm=xmList.get(0);
		HashMap<String,String> yjzbStartXm=currXm;//一级指标起始项目
		String yjzb=currXm.get("yjzb");//一级指标
		int yjzbRowNum=0;//一级指标合并单元格个数
		for(int i=0;i<xmList.size();i++){
			currXm=xmList.get(i);
			if(yjzb.equals(currXm.get("yjzb"))){
				yjzbRowNum++;
			}else{
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
				yjzbStartXm=currXm;
				yjzbRowNum=1;
				yjzb=currXm.get("yjzb");
			}
			//如果是循环到了最后，则直接放入
			if(i==xmList.size()-1){
				yjzbStartXm.put("yjzbRowNum", yjzbRowNum+"");
			}
		}
		return xmList;
	}
	/** 
	 * @描述:(获取辅导员考核表个项目的平均分列表)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-9 下午05:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	private List<HashMap<String, String>> getKhXmPjfList(BjlhFdykhForm model) {
		// TODO 自动生成方法存根
		return dao.getKhXmPjfList(model);
	}
	/** 
	 * @描述:获取学生需要考核的辅导员list
	 * @作者：cmj
	 * @日期：2013-12-13 上午10:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhFdyList(BjlhFdykhForm myForm) {
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象辅导员
			lx="辅导员";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象班主任
			lx="班主任";
		}
		return dao.getKhFdyList(myForm,lx);
	}
	/** 
	 * @描述:导出条件
	 * @作者：陈敏杰
	 * @日期：2013-12-16 下午05:28:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(BjlhFdykhForm model,
			HttpServletRequest request) throws Exception{
		return dao.getAllList(model,request);
	}
}

package xgxt.dtjs.ntzy.gqtgl;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.xsgygl.GyglTyService;

import com.zfsoft.basic.BasicService;

public class NtzyWpjyService {
	/**
	 * 获取登陆人姓名
	 */
	public String getDlrXm(String userName){
		NtzyWpjyDao dao=new NtzyWpjyDao();
		String xm=dao.getDlrXm(userName).get("xm");
		xm=Base.isNull(xm)? "" : xm;
		return xm;
	}
	
	/**
	 * 获取学生姓名
	 */
	public String getXsxm(String userName){
		NtzyWpjyDao dao=new NtzyWpjyDao();
		String xm=dao.getXsXm(userName).get("xm");	
		xm=Base.isNull(xm)?"" :xm;
		return xm;
	}
	
	/**
	 * 批量保存
	 * 所借设备信息
	 */
	public void plSave(HttpServletRequest request,NtzyWpjyForm form) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//进行数据操作 的表名
		String realTable ="ntzy_wpb";
		
		//保存查询出的所有信息的XH主键
		String sqsj =form.getSave_sqsj();
		String jyrq= form.getSave_jyrq();
		String sqr =form.getSave_sqr(); 
		String xn=Base.currXn;
		String xq=Base.currXq;
		String []plsbdm= form.getSbdm();
		String []plsbdw = form.getSbdw();
		String []plsbsl = form.getSbsl();
		String []plghbz = form.getGhqk();
		String []pkValue =new String[plsbdm.length];
		//判断操作是否成功
		boolean reslut = false;
		
		for(int i=0;i<plsbdm.length;i++){
			pkValue[i]=sqr+sqsj+jyrq;
		}
		
		String []sqsji=new String[pkValue.length];
		String []jyrqi=new String[pkValue.length];
		String []sqri=new String[pkValue.length];
		String []xni=new String[pkValue.length];
		String []xqi=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			sqsji[i]=sqsj;
			jyrqi[i]=jyrq;
			sqri[i]=sqr;
			xni[i]=xn;
			xqi[i]=xq;	
		}
		
		String pk = "sqr || sqsj || jyrq  ";
		String[] arrzd = new String[] {"sqr","sqsj","jyrq","xn","xq","sbdm","sbdw","sbsl","ghbz"};
	
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
				
		NtzyWpjyModel ntzyWpjyModel=new NtzyWpjyModel();
		ntzyWpjyModel.setSqr(sqri);//申请人
		ntzyWpjyModel.setSqsj(sqsji);//申请时间
		ntzyWpjyModel.setJyrq(jyrqi);//借用日期
		ntzyWpjyModel.setSbdm(plsbdm);//申报代码
		ntzyWpjyModel.setSbdw(plsbdw);//申报单位
		ntzyWpjyModel.setSbsl(plsbsl);//申报数量
		ntzyWpjyModel.setGhbz(plghbz);//归还标注
		ntzyWpjyModel.setXn(xni);
		ntzyWpjyModel.setXq(xqi);
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, ntzyWpjyModel);
		request.setAttribute("result", reslut);
			
	}
	
	
	/**
	 * 删除副表多余信息(物品表)
	 * @throws Exception 
	 * 
	 */
	public void delWpbxx() throws Exception{
		NtzyWpjyDao dao=new NtzyWpjyDao();
		dao.delWpbXx();
	}
	
	
	
	/**
	 * 获取审核值
	 */
	public HashMap<String,String> getValueMap(HttpServletRequest request,
			String userType,String shjg){
		HashMap<String,String> hashMap=new HashMap<String,String>();
		String shjb="";//审核级别
		String shsj="";//审核时间
		if("xy".equalsIgnoreCase(userType)){
			shjb="xysh";
			shsj="xyshsj";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shjb="xxsh";
			shsj="xxshsj";
			hashMap.put("xysh", "通过");
		}
		hashMap.put(shjb, shjg);
		hashMap.put(shsj,GetTime.getNowTime2());
		return hashMap;
	}
	
	/**
	 * 归还物品
	 * 
	 */
	public HashMap<String,String> getGhwpMap(String userName,String act){
		NtzyWpjyService service =new NtzyWpjyService();
		HashMap<String,String> hashMap=new HashMap<String,String>();
		if("gh".equals(act)){
			hashMap.put("ghrq", GetTime.getNowTime2());
			hashMap.put("ysr",service.getDlrXm(userName));
		}else{
			hashMap.put("ghrq","未归还");
			hashMap.put("ysr","未归还");
		}
		
		return hashMap;
	}
	
	/**
	 * 判断审核时的权限
	 * 
	 */
	public void setDisabled(HttpServletRequest request,String userType,String userDep){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end ) disabled, ");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("annexTerm", " and (xysh='通过' or bmdm='"+userDep+"') ");
			request.setAttribute("clientColumns", "'' disabled, ");
		}else {
			request.setAttribute("clientColumns", "'disabled' disabled, ");
		}
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public List<String[]> getWpbXx(String pkValue) throws Exception{
		NtzyWpjyModel model=new NtzyWpjyModel();
		String sql="select rownum r,a.* from( select * from ntzy_wpb  where sqr||sqsj||jyrq=?)a";
		String []colList={"r","sbdm","sbdw","sbsl","ghbz"};
		List<String[]> li= CommonQueryDAO.commonQueryNotFy(sql, "", new String[]{pkValue},colList , model);
		if(li.size()<10){
			int len=li.size();
			for(int i=len;i<10;i++){
				String[] str=new String[5];
				str[0]=""+(i+1);
				li.add(str);
			}
		}
		return li;
	}
	
	/**
	 * 物品归还
	 * @param request
	 * @param hashMap
	 * @param userType
	 */
	public void setWpgh(HttpServletRequest request,String userType){
		request.setAttribute("clientColumns", "'' disabled, ");
		//只有学校审核通过借出物品的数据，才能进行归还操作
		request.setAttribute("annexTerm", " and xxsh='通过' ");
	}
	
	public void getShql(HttpServletRequest request,HashMap<String,String>hashMap,
			String userType){
		String write="yes";
		if(!"xx".equalsIgnoreCase(userType)
				|| !"admin".equalsIgnoreCase(userType)){
			if("通过".equalsIgnoreCase(hashMap.get("xxsh"))){
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	/**
	 * 获取物品借用表List
	 */
	public ArrayList<String[]> getWpjyList(NtzyWpjyForm wpjyForm) throws Exception{
		String sql="select * from ntzy_wpjyb ";
		MakeQuery mQuery=new MakeQuery();
		String[] colList={"xn","xq","bmdm","xysh","xxsh"};
		String[] colLikeLis={"sqr","jbr"};
		String[] output={"sqr","xn","xq","sqsj","sqbm","jyrq","xysh","xxsh",
				"jbr","lxdh","sqsy","sqbyj","sybyj","ghrq","ysr","bz","bmdm","xyshsj","xxshsj","xyyj","xxyj"};
		mQuery.makeQuery(colList, colLikeLis, wpjyForm);
		return CommonQueryDAO.commonQueryNotFy(sql, mQuery.getQueryString(),mQuery.getInputList() , output, wpjyForm);
	}
	
	/**
	 * 获取物品表List
	 */
	public ArrayList<String[]> getWpbList(NtzyWpjyForm wpjyForm) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("select a.* from ntzy_wpb a,ntzy_wpjyb b where a.sqr=b.sqr and a.sqsj=b.sqsj and a.jyrq=b.jyrq  ");
		if(!"".equals(wpjyForm.getXn())){
			sql.append(" and b.xn='"+wpjyForm.getXn()+"'");
		}
		if(!"".equals(wpjyForm.getXq())){
			sql.append(" and b.xq='"+wpjyForm.getXq()+"'");
		}
		if(!"".equals(wpjyForm.getBmdm())){
			sql.append(" and b.bmdm='"+wpjyForm.getBmdm()+"'");
		}
		if(!"".equals(wpjyForm.getXysh())){
			sql.append(" and b.xysh='"+wpjyForm.getXysh()+"'");
		}
		if(!"".equals(wpjyForm.getXxsh())){
			sql.append(" and b.xxsh='"+wpjyForm.getXxsh()+"'");
		}
		if(!"".equals(wpjyForm.getSqr())){
			sql.append(" and b.sqr='"+wpjyForm.getSqr()+"'");
		}
		if(!"".equals(wpjyForm.getJbr())){
			sql.append(" and b.jbr='"+wpjyForm.getJbr()+"'");
		}
		String[] outPut={"sqr","xn","xq","sqsj","jyrq","sbdm","sbdw","sbsl","ghbz"};
		return CommonQueryDAO.commonQueryNotFy(sql.toString(),"" ,new String[]{} ,outPut , wpjyForm);
	}
	
	public void expDate(OutputStream os,NtzyWpjyForm form,HttpServletRequest request,String viewName) throws Exception {
		//从页面获得要导出的表名
		GyglTyService gyglTyService = new GyglTyService();
		BasicService basicService = new BasicService();
		NtzyWpjyService service =new NtzyWpjyService();
		String []topTr=null;
		
		form.setBmdm(form.getQueryequals_bmdm());
		form.setXn(form.getQueryequals_xn());
		form.setXq(form.getQueryequals_xq());
		form.setXysh(form.getQueryequals_xysh());
		form.setXxsh(form.getQueryequals_xxsh());
		form.setSqr(form.getQuerylike_sqr());
		form.setJbr(form.getQuerylike_jbr());
		
		if("ntzy_wpb".equals(viewName)){
			topTr=new String[]{"申请人","学年","学期","申请时间","借用日期","申报代码",
					"申报单位","申报数量","归还标注"};
			gyglTyService.expToExcel("sheet1", basicService.getTopTr("",topTr), service.getWpbList(form),os);
		}else{
			topTr=new String[]{"申请人","学年","学期","申请时间","申请部门","借用日期",
					Base.YXPZXY_KEY+"审核","学校审核","经办人","联系电话","申请事由","申请部门意见",
					"所有部门意见","归还日期","验收人","备注","部门代码",Base.YXPZXY_KEY+"审核时间",
					"学校审核时间",Base.YXPZXY_KEY+"意见","学校意见"};
			
			
			gyglTyService.expToExcel("sheet1", basicService.getTopTr("",topTr), service.getWpjyList(form),os);
			
		}
		
		
	}
}

package xgxt.rcsw.kqgl.xskq;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.xszz.nbty.jtjjknsbz.XszzDao;

import com.zfsoft.basic.BasicService;

public class RcswKqglXskqService {
	public HashMap<String, String> getStuInfo(String xh)
	{
		CommonQueryDAO commonQueryDao =new CommonQueryDAO();
		return commonQueryDao.getStuInfo(xh);
	}
	
	
	public List<HashMap<String,String>>getQjlxList(){
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		return dao.getQjlxList();
	}
	

	/**
	 * 获取学期名
	 */
	public String getXqMc(String xq)
	{
		XszzDao xszzDao=new XszzDao();
		//根据学院代码判断申请是否在申请日期内
		return xszzDao.getXqMc(xq);
	
	}
	
	//公寓管理员判断
	public boolean isGyGly(String userName){
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		return dao.isGyGly(userName);
	}
	
	public List<HashMap<String,String>> getQsglStu(HttpServletRequest request,String xqdm,String lddm){
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		return dao.getQsglStu(xqdm, lddm);
	}
	
	//根据公寓FDY的userName获取管辖范围内的学区号和楼栋号
	public List<HashMap<String,String>>getLdByGygly(String userName){
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		return dao.getLdByGygly(userName);
	}
	
	//根据学生XH获取公寓管理员
	public void getGygly(HttpServletRequest request,String xh){
		RcswKqglXskqService service=new RcswKqglXskqService();
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		HashMap<String,String> map=service.getXszsxx(xh);
		List <HashMap<String,String>>list=dao.getGyGly(map.get("xqdm"), map.get("lddm"));
		if(list.size()>0){
			service.getGyglyXm(request, list.get(0).get("gygly"));
		}
	}
	
	public void getGyglyXm(HttpServletRequest request,String gygly){
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		List<HashMap<String,String>>list=dao.getGyglyXm(gygly);
		if(list.size()>0){
			request.setAttribute("gygly",list.get(0).get("gygly"));
		}
	}
	
	//根据学生XH获取住宿信息
	public HashMap<String,String>getXszsxx(String xh){
		RcswKqglXskqDao dao=new RcswKqglXskqDao();
		HashMap<String,String>map=new HashMap<String,String>();
		if(dao.getXszsxx(xh).size()>0){
			map=dao.getXszsxx(xh).get(0);
		}
		return map;
	}
	
	
	public void getStuByGygly(HttpServletRequest request,RcswKqglXskqForm form,String userName) throws Exception{
		RcswKqglXskqService service=new RcswKqglXskqService();
		BasicService basicService = new BasicService();
		RcswKqglXskqModel model=new RcswKqglXskqModel();
		BeanUtils.copyProperties(model,form);
		CommonQueryDAO dao=new CommonQueryDAO();
		String query="";
		String lddm="";
		String xqdm="";
		List<HashMap<String,String>>list=service.getLdByGygly(userName);
		String sql="select a.*,Rownum r from view_rcsw_kqgl_xskq a where exists (select 1 from view_xszsxx b where b.xqdm=? and b.lddm=? and a.xh=b.xh) and qjlxmc<>'夜不归宿'";
		List<String[]>allList=new ArrayList<String[]>();
		String[]outputValue=new String[]{"pk","xn","xqmc","xh","xm","xb","xymc","zymc","bjmc","fdysh"};
		for(int i=0;i<list.size();i++){
			HashMap<String,String>map=list.get(i);
			xqdm=map.get("xqdm");
			lddm=map.get("lddm");
			allList.addAll(dao.commonQuery(sql,query ,new String[]{xqdm,lddm},outputValue,model));
		}
		request.setAttribute("topTr", basicService.getTopTr("view_rcsw_kqgl_xskq",new String[]{"编号","学年","学期","学号","姓名","性别",Base.YXPZXY_KEY+"名称","专业名称","班级名称","辅导员审核"}));
		request.setAttribute("rs",allList);
		request.setAttribute("rsNum",allList.size());
		
	}
	
//	and sqsj>? and to_date(sqsj,'yyyyMMdd')+to_number(qjsj) < ?
	//获取统计结果
	public void getStuTj(HttpServletRequest request,RcswKqglXskqForm form) throws Exception{
		BasicService basicService = new BasicService();
		RcswKqglXskqModel model=new RcswKqglXskqModel();
		BeanUtils.copyProperties(model,form);
		String []colList={"xn","xq","xydm","zydm","bjdm","shzt" };
		String []colLikeList={"xh","xm"};
		MakeQuery makeQuery=new MakeQuery();
		makeQuery.makeQuery(colList, colLikeList, model);
		String qjqssj=form.getQjqssj();
		String qjjssj=form.getQjjssj();
		CommonQueryDAO dao=new CommonQueryDAO();
		String query="";
		int length=makeQuery.getInputList().length;
		List<String> al=Arrays.asList(makeQuery.getInputList());
		ArrayList<String> temp =  new ArrayList<String>(); 
		temp.addAll(al); 	
		String checkDay="";
		if(!"".equals(qjqssj) && !"".equals(qjjssj)){
			checkDay= "and qjqssj>=? and qjjssj<= ?";
			temp.add(qjqssj);
			temp.add(qjjssj);
			length+=2;
		}else if(!"".equals(qjqssj)){
			checkDay= "and qjqssj>=?";
			temp.add(qjqssj);
			length+=1;
		}else if(!"".equals(qjjssj)){
			checkDay=" and qjjssj<= ?";
			temp.add(qjjssj);
			length+=1;
		}
		  
		String []inputValue=new String[length];
		inputValue=temp.toArray(inputValue);
		String sql="select xm,xqmc,xymc,zymc,bjmc,xn,xh,(nvl(case when instr(to_char(qjsj),'.',1,1)=1 then '0'||to_char(qjsj) else to_char(qjsj) end, '0')) qjsj,shzt from (select xm,xqmc,xymc,zymc,bjmc,xn,xh,sum(to_number(qjsj)) qjsj,shzt from view_rcsw_kqgl_xskq";
		sql+=makeQuery.getQueryString();
		sql+=checkDay+"and shzt='通过' "+" group by xh,xm,xn,xq,xymc,zymc,bjmc,shzt,bjdm,xydm,zydm)";
		String[]outputValue=new String[]{"xh","xm","xn","xqmc","xymc","zymc","bjmc","qjsj"};
		List<String[]>allList=dao.commonQueryNotFy(sql,query ,inputValue,outputValue,model);
		request.setAttribute("topTr", basicService.getTopTr("view_rcsw_kqgl_xskq",new String[]{"学号","姓名","学年","学期名称",Base.YXPZXY_KEY+"名称","专业名称","班级名称","请假时间"}));
		request.setAttribute("rs",allList);
		request.setAttribute("rsNum",allList.size());
		
	}
	
	
	public void getAllStuInfo(HttpServletRequest request,RcswKqglXskqForm form,String xh) throws Exception{
		BasicService basicService = new BasicService();
		RcswKqglXskqModel model=new RcswKqglXskqModel();
		String query="";
		CommonQueryDAO dao=new CommonQueryDAO();
		BeanUtils.copyProperties(model,form);
		String sql="select * from view_rcsw_kqgl_xskq";
		sql+="	where shzt='通过' and xh=?";
		String[]outputValue=new String[]{"xn","xqmc","xh","xm","xb","xymc","zymc","bjmc","qjsjd","qjsj"};
		List<String[]>allList=dao.commonQueryNotFy(sql,query ,new String[]{xh},outputValue,model);
		request.setAttribute("topTr", basicService.getTopTr("view_rcsw_kqgl_xskq",new String[]{"学年","学期","学号","姓名","性别",Base.YXPZXY_KEY+"名称","专业名称","班级名称","请假时间段","请假时间"}));
		request.setAttribute("rs",allList);
		request.setAttribute("rsNum",allList.size());
		
	}
	
    //返回要修改信息的Map
	public Map<String,String> getValueMap(HttpServletRequest request,RcswKqglXskqForm form,
			String userName,String shjg,String userType){
		
		RcswKqglXskqService service=new RcswKqglXskqService();
		Map<String,String>valueMap=new HashMap<String,String>();
		String shzd="";
		String shsj="";
		//返回最终审核结果
		String shzt="shzt";
		//判断是否是公寓管理员 return true(是)
		if(service.isGyGly(userName)){
				shzd="fdysh";
				shsj="fdyshsj";
				valueMap.put(shzt,shjg);
				request.setAttribute("isGygly", "true");
		}else if("xy".equalsIgnoreCase(userType)){
			shzd="xysh";
			shsj="xyshsj";
			if("不通过".equalsIgnoreCase(shjg)){
				valueMap.put("xxsh",shjg);
				valueMap.put(shzt,shjg);
			}else if("通过".equalsIgnoreCase(shjg)){
				valueMap.put("xxsh", "未审核");
				valueMap.put(shzt, "未审核");
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shzd="xxsh";
			shsj="xxshsj";
			valueMap.put(shzt,shjg);
		}
		valueMap.put(shzd,shjg );
		valueMap.put(shsj, GetTime.getNowTime2());
		return valueMap;
	}
	
	
	public void setShjg(HttpServletRequest request,RcswKqglXskqForm form,
			String userName,String userType){
		HashMap<String,String>hashMap=(HashMap)request.getAttribute("rs");
		RcswKqglXskqService service=new RcswKqglXskqService();
		//公寓管理员
		if(service.isGyGly(userName)){
			request.setAttribute("isGyfdy", "true");
			form.setSave_fdysh(hashMap.get("fdysh"));
		}else if("xy".equalsIgnoreCase(userType)){
			form.setSave_xysh(hashMap.get("xysh"));
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			form.setSave_xxsh(hashMap.get("xxsh"));
		}
	}
	
	//判断请假3天内 学校用户是否审核
	public void getWrite(HttpServletRequest request,String userType,String userName){
		RcswKqglXskqService service=new RcswKqglXskqService();
		HashMap<String,String> hashMap=(HashMap<String,String>)request.getAttribute("rs");
		String write="yes";
		//是学院用户并且不是公寓管理员
		if("xy".equalsIgnoreCase(userType) && !service.isGyGly(userName)){
			//如果请假天数大于3天并且学校审核通过
			if(Float.parseFloat(hashMap.get("qjsj"))>3 && "通过".equals(hashMap.get("xxsh"))){
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	//判断学生的读写权利
	public void getWriteByStu(HttpServletRequest request,String userType,String userName){
		HashMap<String,String> hashMap=(HashMap<String,String>)request.getAttribute("rs");
		String write="yes";
		//是学院用户并且不是公寓管理员
		if("通过".equals(hashMap.get("fdysh"))
				||"通过".equals(hashMap.get("xysh")) 
					|| "通过".equals(hashMap.get("xxsh"))){
				write="no";
		}
		request.setAttribute("write", write);
	}
	
	//寝室管理员、学院、学校获取学生请假信息
	public String[]  getStuQjxx(HttpServletRequest request,RcswKqglXskqForm form,String userName,String userType)throws Exception{
		
		RcswKqglXskqService service=new RcswKqglXskqService();
		String[]outputColumn=null;
		if(service.isGyGly(userName)){
			//根据公寓辅导员的userName获取学生信息
			service.getStuByGygly(request,form, userName);
		}else if("xy".equalsIgnoreCase(userType)){
			//学院查看范围
			request.setAttribute("annexTerm", "  and qjlxmc<>'夜不归宿' ");
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
			outputColumn=new String[] {"pk","disabled","xn","xqmc","xh","xm","xymc","zymc","bjmc","qjlxmc","qjsj","xysh"};
			
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			//学校和管理员 查看范围是（qjsj>3 &&  xysh='通过'）
			request.setAttribute("annexTerm", " and qjsj>=3 and xysh ='通过' and qjlxmc<>'夜不归宿' ");
			request.setAttribute("clientColumns", "('')disabled,");
			outputColumn=new String[] {"pk","disabled","xn","xqmc","xh","xm","xymc","zymc","bjmc","qjlxmc","qjsj","shzt"};
			
		}
		return  outputColumn;
	}
	
	//设置学生用户的查询条件
	public void setStuQuery(RcswKqglXskqForm rcswKqglXskqForm,
			String userOnLine,String userName){ 
		
		XsxxglService xsxxglService=new XsxxglService();
		
		if("student".equalsIgnoreCase(userOnLine)){
			HashMap<String,String> map=xsxxglService.selectStuinfo(userName);
			rcswKqglXskqForm.setQueryequals_bjdm(map.get("bjdm"));
			rcswKqglXskqForm.setQueryequals_xydm(map.get("xydm"));
			rcswKqglXskqForm.setQueryequals_zydm(map.get("zydm"));
			rcswKqglXskqForm.setQuerylike_xm(map.get("xm"));
			rcswKqglXskqForm.setQuerylike_xh(map.get("xh"));
		}
	}
	
//	设置学生用户的查询条件
	public void setStQuery(RcswKqglXskqForm rcswKqglXskqForm,
			String userOnLine,String userName){ 
		
		XsxxglService xsxxglService=new XsxxglService();
		
		if("student".equalsIgnoreCase(userOnLine)){
			HashMap<String,String> map=xsxxglService.selectStuinfo(userName);
			rcswKqglXskqForm.setBjdm(map.get("bjdm"));
			rcswKqglXskqForm.setXydm(map.get("xydm"));
			rcswKqglXskqForm.setZydm(map.get("zydm"));
			rcswKqglXskqForm.setXm(map.get("xm"));
			rcswKqglXskqForm.setXh(map.get("xh"));
		}
	}
	
	public void disabled(HttpServletRequest request,RcswKqglXskqService service,
			RcswKqglXskqForm rcswKqglXskqForm,
			String userOnLine,String userName,String userType) throws Exception{
		if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("clientColumns", 
					"(case xxsh when '通过' then 'disabled' else " +
					"(case xysh when '通过' then 'disabled' else " +
					"(case fdysh when '通过' then 'disabled' else '' end) end) end)disabled,");
		}
		//判断是否是公寓管理员 return true(是)
			if(service.isGyGly(userName)){
			//根据公寓辅导员的userName获取学生信息（查看夜不归宿）
			service.getStuByGygly(request,rcswKqglXskqForm, userName);
		}else if("xy".equalsIgnoreCase(userType)){
			//学院用户可以看见状态为普通请假的信息
			request.setAttribute("clientColumns", "(case  when (xxsh='通过'and qjsj>3) then 'disabled' else  ''  end)disabled,");
			
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			//管理员和学校用户可查看所有请假信息
			request.setAttribute("clientColumns", "('')disabled,");
			
		}
	}
	
	public void updateShzt(String shjg) throws Exception{
		DAO dao=new DAO();
		String sql="update rcsw_kqgl_xskq set shzt=? ,xysh=? where qjsj<3";
		dao.runUpdate(sql, new String[]{shjg,shjg});
	}
	
	public void writeAbled(HttpServletRequest request,String path){
		request.setAttribute("path",path);
		String[]str=FormModleCommon.getWriteAbleAndTitle(request);
		request.setAttribute("writeAbled", str[0]);
	}
	
	
	//打印请假申请报表
	public void printQjsjbb(RcswKqglXskqForm form,RcswKqglXskqModel rcswKqglXskqModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		RcswKqglXskqDao dao= new RcswKqglXskqDao();
		RcswKqglXskqModel model=new RcswKqglXskqModel();
		BeanUtils.copyProperties(model,form);
		String []colList={"xn","xq","xydm","zydm","bjdm","shzt" };
		String []colLikeList={"xh","xm"};
		MakeQuery makeQuery=new MakeQuery();
		makeQuery.makeQuery(colList,colLikeList, rcswKqglXskqModel);
		String qjqssj=form.getQjqssj();
		String qjjssj=form.getQjjssj();
		int length=makeQuery.getInputList().length;
		List<String> al=Arrays.asList(makeQuery.getInputList());
		ArrayList<String> temp =  new ArrayList<String>(); 
		temp.addAll(al); 	
		String checkDay="";
		if(!"".equals(qjqssj) && !"".equals(qjjssj)){
			checkDay= "and qjqssj>=? and qjjssj<= ?";
			temp.add(qjqssj);
			temp.add(qjjssj);
			length+=2;
		}else if(!"".equals(qjqssj)){
			checkDay= "and qjqssj>=?";
			temp.add(qjqssj);
			length+=1;
		}else if(!"".equals(qjjssj)){
			checkDay=" and qjjssj<= ?";
			temp.add(qjjssj);
			length+=1;
		}
		String []inputValue=new String[length];
		inputValue=temp.toArray(inputValue);
		String sql="select xm,xqmc,xymc,zymc,bjmc,xn,xh,(nvl(case when instr(to_char(qjsj),'.',1,1)=1 then '0'||to_char(qjsj) else to_char(qjsj) end, '0')) qjsj,shzt from (select xm,xqmc,xymc,zymc,bjmc,xn,xh,sum(to_number(qjsj)) qjsj,shzt from view_rcsw_kqgl_xskq";
		sql+=makeQuery.getQueryString();
		sql+=checkDay+"and shzt='通过' "+" group by xh,xm,xn,xq,xymc,zymc,bjmc,shzt,bjdm,xydm,zydm)";
	
		List<HashMap<String, String>> list = dao.getTjList(sql,inputValue);
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(24);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			// wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			// wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			// 设置表格边框
			// wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(0, 0, "学号", wcfTytle));
			ws.addCell(new Label(1, 0, "姓名", wcfTytle));
			ws.addCell(new Label(2, 0, "学年", wcfTytle));
			ws.addCell(new Label(3, 0, "学期名称", wcfTytle));
			ws.addCell(new Label(4, 0, Base.YXPZXY_KEY+"名称", wcfTytle));
			ws.addCell(new Label(5, 0, "专业名称", wcfTytle));
			ws.addCell(new Label(6, 0, "班级名称", wcfTytle));
			ws.addCell(new Label(7, 0, "请假时间", wcfTytle));
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				
				ws.addCell(new Label(0,i+1,  map.get("xh"), wcfTytle));
				ws.addCell(new Label(1,i+1, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2,i+1, map.get("xn"), wcfTytle));
				ws.addCell(new Label(3,i+1,  map.get("xqmc"), wcfTytle));
				ws.addCell(new Label(4,i+1, map.get("xymc"), wcfTytle));
				ws.addCell(new Label(5,i+1, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(6,i+1, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(7,i+1, map.get("qjsj"), wcfTytle));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	//判断请假天数与请假时间段是否正确
	public String checkTimes(String qjqssj,String qjjssj,String qjts) throws ParseException{
		//获取请假天数
		float qjtsf=Float.parseFloat(qjts);
		DateFormat format=new SimpleDateFormat("yyyyMMdd"); 
		
		Date qjqssjd=format.parse(qjqssj);
		Date qjjssjd=format.parse(qjjssj);
		float diff=qjjssjd.getTime()-qjqssjd.getTime();
		float dates=diff/(1000*24*60*60);
		if(dates+1.0>=qjtsf && dates<=qjtsf){
			return "true";
		}else{
			return "false";
		}
	}

	
}

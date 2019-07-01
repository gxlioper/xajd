package xgxt.pjpy.ghxy.bjryf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.ghxy.qsryf.GhxyQsryfDao;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhDao;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.GetTime;
import xgxt.xsgygl.ghxy.qyfdywh.GhxyQyfdywhService;

import com.zfsoft.basic.BasicService;

public class GhxyBjryfService {
	
	public void getBjList(HttpServletRequest request,GhxyBjryfForm form,String pc,
			String xn,String xq,String nj,String userName) throws Exception{
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		GhxyBjryfService ghxyBjryfService=new GhxyBjryfService();

		List<String[]>map=ghxyBjryfDao.getBjList(form,userName,nj);
		
		//返回结果集内容
		
		//返回结果集记录数
		request.setAttribute("rsNum", map.size());
		List<HashMap<String,String>> list = ghxyBjryfDao.getBjzb();
		
		String[] colList = new String[1];
		String[] colListCN = new String[1];
		if (list != null && list.size() > 0) {
			colList = new String[list.size()+1];
			colListCN = new String[list.size()+1];
			colList[0]="bjmc";
			colListCN[0]="班级名称";
			for (int i = 1; i <= list.size(); i++) {
				colList[i]=list.get(i-1).get("zbdm");
				colListCN[i]=list.get(i-1).get("zbnr");
			}
		}
		
		DAO dao = DAO.getInstance();
		//返回结果集的表头
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", ghxyBjryfService.getZblb(map,list,nj,pc,xn,xq));
	}
	
	
	public void ryfPcList(HttpServletRequest request){
		GhxyBjryfDao dao=new GhxyBjryfDao();
		request.setAttribute("ryfPcList", dao.getRyfpc());
	}
	/**
	 * 班级荣誉分批量保存
	 * @throws Exception 
	 *
	 */
	public void plSave(HttpServletRequest request,GhxyBjryfForm form,String dgpc) throws Exception{
		GhxyBjryfModel ghxyBjryfModel=new GhxyBjryfModel();
		GhxyBjryfService ghxyBjryfService=new GhxyBjryfService();
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//进行数据操作 的表名
		String realTable ="ghxy_bjryfb";
		
		//保存查询出的所有信息的XH主键
		String[] zgh = form.getPlbjdm();
		//保存已选择的信息的主键
		String[] checkVal = form.getCheckVal();
		
		String[]xmfz=form.getXmfz();
		
		String[]zbdms=ghxyBjryfService.getZbdm();
		int length=zbdms.length;
		//判断操作是否成功
		boolean reslut = false;
		
		//批量增加及取消
		
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xn || xq || pc  || plbjdm  ";
			String[] arrzd = new String[] {"xn","xq","pc","plbjdm","xmdm","xmfz"};
			String[] sbjdm = new String[xmfz.length];
			String dgxq=Base.currXq;
			String dgxn=Base.currXn;
			int m = 0;
			for (int i = 0; i < checkVal.length; i++) {
				for(int j=i*length;j<=(i+1)*length-1;j++){
					if(!Base.isNull(checkVal[i])
							&& !Base.isNull(xmfz[j])){
						sbjdm[j] = checkVal[i];
						
						m++;
						
					}else{
						sbjdm[j]="";
					}
					
				}
			}
			
				
			String[] xhArr = new String[m];
				String[] xmfzArr=new String[m];
				String[] xq = new String[m];
				String[] xn = new String[m];
				String[] pc = new String[m];
				String[]zbdm = new String[m];
				String[] pkVal=new String[xmfz.length];
				int f = 0;
				for(int i=0; i<sbjdm.length;i++){
					pkVal[i]=dgxn+dgxq+dgpc+sbjdm[i];
						if(!Base.isNull(sbjdm[i])){
							xhArr[f] = sbjdm[i];
							xmfzArr[f] = xmfz[i];
							xq[f]=dgxq;
							xn[f]=dgxn;
							pc[f]=dgpc;
							zbdm[f]=zbdms[i%length];
							f++;
						}
				}
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkVal);
				saveForm.setArrzd(arrzd);
				
			
				ghxyBjryfModel.setPlbjdm(xhArr);
				ghxyBjryfModel.setXn(xn);
				ghxyBjryfModel.setXq(xq);
				ghxyBjryfModel.setPc(pc);
				ghxyBjryfModel.setXmdm(zbdm);
				ghxyBjryfModel.setXmfz(xmfzArr);
				
				reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyBjryfModel);
				request.setAttribute("result", reslut);
			
			reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyBjryfModel);
			request.setAttribute("result", reslut);
			
		}
	}
	
	/**
	 * 班级荣誉分审核表批量保存
	 * @throws Exception 
	 * 
	 */
	public void shbSave(HttpServletRequest request,GhxyBjryfForm form,String dgpc) throws Exception{

		GhxyBjryfModel ghxyBjryfModel=new GhxyBjryfModel();
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//进行数据操作 的表名
		String realTable ="ghxy_bjryfshb";
		
		//保存查询出的所有信息的XH主键
		String[] plbjdm = form.getPlbjdm();
		//保存已选择的信息的主键
		String[]pkValue=form.getPrimarykey_cbv();
		
		String[]xn=form.getXnArr();
		
		String[]xq=form.getXqArr();
		
		String[]xqin=new String[xq.length];
		
		String[]xnin=new String[xn.length];
		String[] checkVal = form.getCheckVal();

		//判断操作是否成功
		boolean reslut = false;
		
		//批量增加及取消
		
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xn || xq ||plbjdm ||pc   ";
			String[] arrzd = new String[] {"xn","xq","pc","plbjdm"};
			String[] sxh = new String[checkVal.length];
			int m = 0;
			for (int i = 0; i < checkVal.length; i++) {
				if (pkValue != null && pkValue.length > 0) {
					for (int j = 0; j < pkValue.length; j++) {
						if (pkValue[j].equalsIgnoreCase(checkVal[i])) {
							sxh[i] = plbjdm[i];
							xnin[i]=xn[i];
							xqin[i]=xq[i];
							m++;
							break;
						}else{
							sxh[i]="";
						}
					}
				}
			}
			
				
				String[] xhArr = new String[m];
				String[] xqArr= new String[m];
				String[] xnArr =new String[m];
				String[] pc = new String[m];
				int f = 0;
				for(int i=0; i<sxh.length;i++){
					if(!Base.isNull(sxh[i])){
						xhArr[f] = sxh[i];
						xqArr[f]=xqin[i];
						xnArr[f]=xnin[i];
						pc[f]=dgpc;
						f++;
					}
				}
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);
				saveForm.setArrzd(arrzd);
				
			
				ghxyBjryfModel.setPlbjdm(xhArr);
				ghxyBjryfModel.setXn(xnArr);
				ghxyBjryfModel.setXq(xqArr);
				ghxyBjryfModel.setPc(pc);
				reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyBjryfModel);
				request.setAttribute("result", reslut);
			
			reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyBjryfModel);
			request.setAttribute("result", reslut);
			
		}
	}
	
	
	/**
	 * 获取指标List
	 */
	public List<HashMap<String,String>>zbList(){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		return ghxyBjryfDao.getBjzb();
	}
	
	/**
	 * 获取指标代码
	 * getZbdm
	 */
	public String[] getZbdm(){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		List<HashMap<String,String>>list=ghxyBjryfDao.getBjzb();
		String[] zbdm=new String[list.size()];
		for(int i=0;i<list.size();i++){
			zbdm[i]=list.get(i).get("zbdm");
		}
		return zbdm;
	}
	
	/**
	 * list 结果集
	 * listMap 表头信息
	 * @param list
	 * @param listMap
	 */
	public List getZblb(List<String[]>list,List<HashMap<String,String>>listMap,String nj,
			String pc,String xn,String xq){
		
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		StringBuilder sql =new StringBuilder();
		List textArray=new ArrayList();
		
		//拼接查询ghxy_bjryfb数据的语句（根据班级查询）
		sql.append("select * from(select * from (select b.plbjdm,b.xmdm,b.xmfz,b.pc,b.xn,b.xq,a.nj from view_njxyzybj a,");
		sql.append("(select plbjdm,xmdm,xmfz,pc,xn,xq from ghxy_bjryfb)b where a.bjdm=b.plbjdm)where 1=1");
		for(int i=0;i<list.size();i++){
			String[]bjStr=list.get(i);
			sql.append(" or plbjdm = '");
			sql.append(bjStr[0]);
			sql.append("'");
		}
		sql.append(") where 1=1 ");
		if(!"".equalsIgnoreCase(pc)){
			sql.append(" and pc='");
			sql.append(pc);
			sql.append("'");
		}
		if(!"".equalsIgnoreCase(xn)){
			sql.append(" and xn='");
			sql.append(xn);
			sql.append("'");
		}
		if(!"".equalsIgnoreCase(xq)){
			sql.append(" and xq='");
			sql.append(xq);
			sql.append("'");
		}
		System.out.println(sql.toString());
		
		//获取班级荣誉代码表列表
		List<HashMap<String,String>> zblb=ghxyBjryfDao.getZblb(sql.toString());
		//length为JSP上的数据所需要存放的TEXT个数
		String[]str=new String[list.size()*listMap.size()];
		boolean blog=false;
		int f=0;
		for(int i=1;i<=list.size();i++){
			String[]strs=list.get(i-1);
			for(int j=1;j<=listMap.size();j++){
				HashMap hashMap=listMap.get(j-1);
				for(int m=0;m<zblb.size();m++){
					if(strs[0].equalsIgnoreCase(zblb.get(m).get("plbjdm"))
							&& hashMap.get("zbdm").equals(zblb.get(m).get("xmdm"))){
						str[f]=zblb.get(m).get("xmfz");
						blog=true;
						break;
					}
				}
				if(!blog){
					str[f]="";
				}
				f++;
				blog=false;
			}
			
		}
		//返回结果集
		int s=0;
		for(int i=0;i<list.size();i++){
			String []array=new String[listMap.size()+2];
			array[0]=list.get(i)[0];
			array[1]=list.get(i)[1];
			for(int j=0; j<listMap.size();j++){
						array[j+2]=str[s];
						s++;
			}
			textArray.add(array);
		}
		return textArray;
	}
	
	
	/**
	 * 获取班级的加分信息
	 */
	public void getBjjfxx(HttpServletRequest request,String bjdm){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		BasicService basicService=new BasicService();
		List<String[]> bjjfxx=ghxyBjryfDao.getBjjfxx(bjdm);
		request.setAttribute("map", bjjfxx);
		if(bjjfxx.size()>0){
			request.setAttribute("rsNum", bjjfxx.size());
		}
		request.setAttribute("topTr", basicService.getTopTr("view_rcsw_kqgl_xskq",new String[]{"学年","学期","批次名称","班级名称","项目名称","项目分值"}));
	}
	
	/**
	 * 设置审核信息
	 * 
	 */
	public HashMap<String,String> getShxx(String userType,String userName,String shjg,String djdm){
		HashMap<String,String> hashMap=new HashMap<String,String>();
		GhxyNjzrwhService ghxyNjzrwhService=new GhxyNjzrwhService();
		String times=GetTime.getNowTime2();
		//是否是年级辅导员
		if(ghxyNjzrwhService.isNjfdy(userName)){
			hashMap.put("njzrsh", shjg);
			hashMap.put("njzrshsj",times);
			hashMap.put("djdm",djdm);
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			hashMap.put("xxsh", shjg);
			hashMap.put("xxshsj",times);
		}
		
		return hashMap;
	}
	
	/**
	 * 访问信息
	 * 
	 */
	public void getReader(HttpServletRequest request,
			String userType,String userName){
		GhxyNjzrwhService ghxyNjzrwhService=new GhxyNjzrwhService();
		if(ghxyNjzrwhService.isNjfdy(userName)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
			request.setAttribute("annexTerm", " and njzrsh='通过' ");
		}
		
	}
	/**
	 * 
	 * @param request
	 * @param userType
	 * @param userName
	 */
	public void getReaders(HttpServletRequest request,String userName,String isFdy,String userType){
		GhxyNjzrwhService ghxyNjzrwhService=new GhxyNjzrwhService();
		if("true".equalsIgnoreCase(isFdy) || "xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns","(select (case njzrsh when '通过' then 'disabled' else '' end)disabled from ghxy_bjryfshb b where b.plbjdm=a.plbjdm and b.xn=a.xn and b.xq=a.xq and b.pc=a.pc)disabled,");
		}else if(ghxyNjzrwhService.isNjfdy(userName)){//年级主任
			request.setAttribute("clientColumns","(select (case xxsh when '通过' then 'disabled' else '' end)disabled from ghxy_bjryfshb b where b.plbjdm=a.plbjdm and b.xn=a.xn and b.xq=a.xq and b.pc=a.pc)disabled,");
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))
			request.setAttribute("clientColumns", " '' disabled, ");
		else{
			request.setAttribute("clientColumns", " 'disabled' disabled, ");
		}
	}
	
	/**
	 * 班级荣誉分等级List
	 */
	public void ryfdjList(HttpServletRequest request){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		request.setAttribute("ryfdjList",ghxyBjryfDao.ryfdjList());
	}
	
	/**
	 * 荣誉班级
	 * @param request
	 */
	public void getBlszxx(HttpServletRequest request){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		BasicService basicService=new BasicService();
		List<String[]> bjjfxx=ghxyBjryfDao.getBlszxx();
		request.setAttribute("map", bjjfxx);
		request.setAttribute("rsNum", bjjfxx.size());
		request.setAttribute("topTr", basicService.getTopTr("view_ghxy_bjryfbl",new String[]{"等级代码","等级名称","加分","班级比例"}));
	}
	
	/**
	 * 如果是年级主任登陆判断各等级班级申请数量(用户名，等级代码，数量)
	 * 
	 */
	public boolean checkBjs(String userName,String djdm,int sl,String pc){
		GhxyNjzrwhService ghxyNjzrwhService=new GhxyNjzrwhService();
		GhxyBjryfService ghxyBjryfService=new GhxyBjryfService();
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		if(ghxyNjzrwhService.isNjfdy(userName)){
			//该年级主任所管辖的年级
			List<HashMap<String,String>>list= ghxyNjzrwhService.getFdyNj(userName);
			String nj=list.get(0).get("nj");
			//年级总班级数
			int zbjs=ghxyBjryfService.getZbjByNj(nj);
			int rydj=ghxyBjryfService.getBjRyfSl(nj, djdm,pc)+sl;
			float djbl=(float)rydj/(float)zbjs*100;
			return ghxyBjryfDao.checkDjSl(djbl);
		}else{
			return true;
		}
	}	
	
	/**
	 * 获取年级总班级数
	 */
	public int getZbjByNj(String nj){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		List <HashMap<String,String>>list=ghxyBjryfDao.getZbjByNj(nj);
		HashMap<String,String> hashMap=list.get(0);
		String zbjs=hashMap.get("zbjs");
		return Integer.parseInt(zbjs);
	}
	
	/**
	 * 根据荣誉等级获取班级荣誉等级数量
	 */
	public int getBjRyfSl(String nj,String djdm,String pc){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		List <HashMap<String,String>>list=ghxyBjryfDao.getBjRyfSl(nj, djdm,pc);
		HashMap<String,String> hashMap=list.get(0);
		String rydj=hashMap.get("rydj");
		return Integer.parseInt(rydj);
	}
	
	/**
	 *判断寝室的荣誉分是否被审核
	 */
	public boolean checkQsryf(String bjdm){
		GhxyBjryfDao ghxyBjryfDao=new GhxyBjryfDao();
		return ghxyBjryfDao.checkBjryf(bjdm);
	}
	

	/**
	 *判断班级荣誉分是否被审核
	 */
	public boolean checkQsryf(String userName,String userType,
			String bjdm,String pc,String xn,String xq){
		
		boolean blog=true;
		GhxyNjzrwhService njzrService=new GhxyNjzrwhService();
		GhxyBjryfDao ghxybjryfDao=new GhxyBjryfDao();
		HashMap<String,String>hashMap= ghxybjryfDao.checkBjryf(bjdm,pc,xn,xq);
		String xxsh="";
		if(hashMap!=null){
			 xxsh=hashMap.get("xxsh");
		}
		//寝室辅导员
		if(njzrService.isNjfdy(userName)){
			if("通过".equalsIgnoreCase(xxsh)){
				blog=false;
			}else{
				blog=true;
			}
		}else if("xx".equalsIgnoreCase(userType)
				||"admin".equalsIgnoreCase(userType)){
			blog=true;
		}else{
			blog=false;
		}
		return blog;
	}
	
	/**
	 * 根据学号获取学生班级信息
	 * 
	 */
	public HashMap<String,String>getXsBjxx(String userName){
		DAO dao=DAO.getInstance();
		String sql="select xydm,zydm,bjdm from xsxxb where xh=?";
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{userName},new String[]{"xydm","zydm","bjdm"});
		HashMap<String,String>map=new HashMap<String,String>();
		if(list.size()>0){
			map=list.get(0);
		}else{
			map.put("lddm", "");
			map.put("zydm", "");
			map.put("bjd,", "");
		}
		return map;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * 
	 */
	public void deleteBjxx() throws Exception{
		StringBuilder sb=new StringBuilder();
		sb.append("delete from ghxy_bjryfshb a where not exists");
		sb.append("(select * from ghxy_bjryfb b where (a.xn||a.xq||a.pc||a.plbjdm) = (b.xn||b.xq||b.pc||b.plbjdm) )");
		DAO dao=DAO.getInstance();
		dao.runUpdate(sb.toString(),new String[]{});
	}
}

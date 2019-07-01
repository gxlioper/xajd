package xgxt.pjpy.zjsyzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江商业职业技术学院评奖评优Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-16</p>
 */
public class PjpyZjsyzyService {
	PjpyZjsyzyDAO dao = new PjpyZjsyzyDAO();
	
	/**
	 * 获取学生的综合素质测评基本信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhszcpStuinfo(String xh){
		return dao.getZhszcpStuinfo(xh);
	}
	
	/**
	 * 获取学年列表
	 * @param List
	 * */
	public List getXnndList(){
		return dao.getXnndList();
	}
	
	/**
	 * 获取学期列表
	 * @return List
	 * */
	public List getXqList(){
		return dao.getXqList();
	}
	
	/**
	 * 获取单个学生申请奖学金信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 **/
	public HashMap<String, String> getJxjshInfo(String pk, String pkValue,String userType){
		return dao.getJxjshInfo(pk,pkValue,userType);
	}
	
	/**
	 * 获取审核结果列表
	 * @param i
	 * @return List
	 * */
	public List getChkList(int i){
		return dao.getChkList(i);
	}
	
	/**
	 * 获取学生奖学金信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuJxjInfo(String pk, String pkValue){
		return dao.getStuJxjInfo(pk, pkValue);
	}
	
	/**
	 * 奖学金审核
	 * @param pk
	 * @param pkValue
	 * @param yesNo
	 * @return boolean
	 * @throws Exception 
	 * */
	public String saveCheck(PjpyZjsyzyForm model,HttpServletRequest request, String jmc) throws Exception{
//		boolean flag = false;
		String yesNo = model.getYesNo();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "xsjxjb";
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String xyshyj = model.getXyshyj();
		String xxshyj = model.getXxshyj();
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
//		String xq = model.getXq();
		String jxjdm = model.getJxjdm();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String mes = "";
		
		if("xy".equalsIgnoreCase(userType) && yesNo != null && yesNo.equalsIgnoreCase("通过") && !yesNo.equalsIgnoreCase(model.getSh())){
			//黑名单检测
			if(!dao.checkIsHmd(xh, "奖学金")){
				mes +=  xh + ":" + "该生已经被列入奖学金申请黑名单，不能审核通过！";
				return mes;
			}
//			不可兼得判断
			String hdjxjmc = dao.getHdjxjmc(xh,xn,nd);
			hdjxjmc = StringUtils.isNull(hdjxjmc) ? "" : hdjxjmc;
			
			//人数限制判断
			int shtgrs = dao.getCountOfShtg(xydm,jxjdm,nj);//审核通过人数
			int xzrs = dao.getCountOfXz(jxjdm,xydm,nj);//限制人数
			
			if(xzrs!=0 && (shtgrs>=xzrs)){
				String dw = dao.getShdw(pk,pkValue,userType);//学院或专业
				mes += dw + "审核通过人数已经满额！限制人数：" + xzrs + ";通过人数：" + shtgrs;				
				return mes;
			}	
			
			
			if(hdjxjmc != null && !hdjxjmc.equalsIgnoreCase("") && !hdjxjmc.equalsIgnoreCase(jmc)){
				mes += xh + ":" + xn + "学年" + nd + "年度该生已经获得了" + hdjxjmc + ",不能再兼得该项奖学金！";
				return mes;
			}
			//单项奖条件判断
			//String jxjmc = dao.getJxjmcByJxjdm(jxjdm);
			/**参评条件**/
			//学习成绩必修课有一门不及格		
//			if(dao.checkBxk(xh,xn,xq)){
//				mes +=  xh + ":" + "至少有一门必修课成绩不及格,不能通过！";
//				return mes;
//			}
			//体育未达标或体育成绩不及格（体育免修者不能参评特等奖）
//			if(dao.checkTycj(xh,xn,xq)){
//				mes += xh + ":" + "体育未达标或体育成绩不及格,暂时不能通过！";				
//				return mes;
//			}
			//评比期内受党、团、行政处分
			
			//综合奖学金
//			if(jxjmc != null && (jxjmc.equalsIgnoreCase("一等奖") || jxjmc.equalsIgnoreCase("二等奖") || jxjmc.equalsIgnoreCase("三等奖"))){
//				String zhszcpcj = dao.getZhszcpcj(xh,xn,nd);
//				String pjcj = dao.getPjcj(xh,xn,xq);
//				zhszcpcj = zhszcpcj == null || "".equalsIgnoreCase(zhszcpcj) ? "0" : zhszcpcj;
//				//综合素质测评成绩大于80
//				if(Double.parseDouble(zhszcpcj) <80){
//					mes += xh + ":" + "综合素质测评成绩为：" + zhszcpcj + "分; 小于80分";
//					return mes;
//				}
//				//TODO 成绩平均70分以上
//				if(Double.parseDouble(pjcj)<70){
//					request.setAttribute("mes", xh + ":" + xn + "学年" +nd + "年度成绩平均分：" + pjcj + ";小于70分");
//				}
//			}
			
			//特等奖学金
//			if(jxjmc != null && jxjmc.equalsIgnoreCase("特等奖")){
//				//各科考试成绩均在85分以上
//				if(!dao.Zcjdb(xh,xn,xq)){
//					mes += xh + ":" + "各科考试均要在85分以上，未达到要求！";
//					return mes;
//				}
//				//体育成绩70分以上
//				String tycj = dao.getTycj(xh,xn,xq);
//				tycj = tycj == null || tycj.equalsIgnoreCase("") ? "0" : tycj;
//				if(Double.parseDouble(tycj) <70){
//					mes += xh + ":" + "体育成绩小于70分!";
//					
//					return mes;
//				}
//				
//				//德成绩90分以上
//				String dcj = dao.getDycj(xh,xn,nd);
//				dcj = dcj == null || dcj.equalsIgnoreCase("") ? "0" : dcj;
//				if(Double.parseDouble(dcj)<90){
//					mes += xh + ":" + "思想品德操行测评小于90分！";
//					return mes;
//				}
//			}
		}
		if(userType != null && userType.equalsIgnoreCase("xy")){
			StandardOperation.update(tableName, new String[]{"xysh","xyshyj","fdyyj"}, new String[]{yesNo,xyshyj,model.getFdyyj()}, pk, pkValue, request);
		}else if(userType != null && (userType.equalsIgnoreCase("xx") || userType.equalsIgnoreCase("admin"))){
			StandardOperation.update(tableName, new String[]{"xxsh","xxshyj"}, new String[]{yesNo,xxshyj}, pk, pkValue, request);
		}
		return mes;
	}
	
	/**
	 * 获取荣誉称号审核信息
	 * @param PjpyZjsyzyForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRychshInfo(PjpyZjsyzyForm model){
		return dao.getRychshInfo(model);
	}
	
	/**
	 * 保存荣誉称号的审核结果
	 * @param PjpyZjsyzyForm model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveCheckCredit(PjpyZjsyzyForm model, HttpServletRequest request) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		boolean flag = false;
		String userType = model.getUserType();
		String yesNo = model.getYesNo();
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String rychdm = model.getRychdm();
		String rychmc = model.getRychmc();
		String tableName = "xsrychb";
		String primaryKey = "xh||xn||nd||rychdm";
		String pkValue = xh+xn+nd+rychdm;
		String[] columns = {"xysh"};
		String[] values = {yesNo};
		
		String sql = "select xydm,zydm,bjdm from view_xsjbxx where xh=?";
		map = dao.getMap(sql, new String[]{xh}, new String[]{"xydm","zydm","bjdm"});
		
		//审核限制条件判断 
		if(yesNo != null && yesNo.equalsIgnoreCase("通过")){
			//黑名单检测
			if(!dao.checkIsHmd(xh, "荣誉称号")){
				request.setAttribute("mes", "该生已经被列入荣誉称号申请黑名单，不能审核通过！");
				return false;
			}
			if(rychmc != null && rychmc.equalsIgnoreCase("优秀学生干部")){//优秀学生干部
				//人数限制
				int xzrs = dao.getRychXzrs(map.get("xydm"));
				int shtgrs = dao.getRychShtgrs(map.get("xydm"),model);
				if(shtgrs>=xzrs){
					request.setAttribute("mes", "审核通过人数已满,请核查！");
					return false;
				}				
				//条件判断
				//学习成绩无不及格 学习无旷课
				if(dao.isFail(xh,xn)){
					request.setAttribute("mes", xn + "学年" + "有不及格成绩记录,请核查！");
					return false;
				}
			}
			if(rychmc != null && rychmc.equalsIgnoreCase("三好学生")){  //三好学生
				String pjcj = dao.getPjcj(xh,xn,xq);
				//一学年中无成绩不及格、考试作弊和受处分等不良记录				
				//已经经过通用判断
				//每学期均为奖学金获得者	
				if(dao.priseEveryTerm(xh)){
					request.setAttribute("mes", "必需每学期均为奖学金获得者,请核查！");
					return false;
				}
				//各门课程平均成绩在80分以上
				if(Double.parseDouble(pjcj)<80){
					request.setAttribute("mes", xn + "学年" + xq + "学期课程平均成绩低于80分,请核查！");
					return false;
				}
				//综合素质测评成绩在全班前15%
				if(dao.checkPm(xh,xn,xq)){
					request.setAttribute("mes", xn + "学年" + xq + "学期综合素质测评成绩排名在15%以下,请核查！");
					return false;
				}
				//学生无旷课现象
			}
			if(rychmc != null 
					&& (rychmc.equalsIgnoreCase("省级优秀毕业生") 
					|| rychmc.equalsIgnoreCase("厅级优秀毕业生") 
					|| rychmc.equalsIgnoreCase("院级优秀毕业生")) ){
				if(rychmc.equalsIgnoreCase("省级优秀毕业生")){
//					int xzrs = 0;
				}				
			}
		}
		if(userType != null && !userType.equalsIgnoreCase("xy")){//学校用户
			columns = new String[]{"xxsh"};	
		}
		flag = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		return flag;
	}
	
	//////////////////////////////////////////////////
	
	/**
	 * 获取所属加分项目列表
	 * @return list
	 * */
	public List getSsjfxmList(){
		return dao.getSsjfxmList();
	}
	
	/**
	 * 获取操作类型列表
	 * @return List
	 * */
	public List getCzlxList(){
		return dao.getCzlxList();
	}
	
	/**
	 * 比赛项目模糊查询
	 * @param model
	 * @return List
	 * */
	public List querryBsxm(PjpyZjsyzyForm model){
		String[] columns = {"xmdm","xmmc","ssjfxm","czlx"};
		String userType = model.getUserType();
		String tableName = model.getTableName();
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			columns = new String[]{"xmdm","xmmc","ssjfxm","xmjf","czlx"};
		}
		return dao.querryBsxm(model,tableName,columns);
	}
	
	/**
	 * 比赛项目模糊查询表头
	 * @return List
	 * */
	public List getBsxmToptr(String userType){
		String[] colEN = {"xmdm", "xmmc" ,"ssjfxm","czlx"};
		String[] colCN = dao.getColumnNameCN(colEN, "bsxmb");
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			colEN = new String[]{"xmdm", "xmmc" ,"ssjfxm","xmjf","czlx"};
			colCN = dao.getColumnNameCN(colEN, "view_xybsxmjfxxb");
		}
		return dao.arrayToList(colEN, colCN);
	}
	
	/**
	 * 保存比赛项目信息
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveBsxm(PjpyZjsyzyForm model,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String xmdm = DealString.toGBK(model.getXmdm());
		String xmmc = DealString.toGBK(model.getXmmc());
		String ssjfxm = DealString.toGBK(model.getSsjfxm());
		String tableName = "bsxmb";
		String userType = model.getUserType();
		String userDep = model.getUserDep();
		String xmjf = DealString.toGBK(model.getXmjf());
		String czlx = DealString.toGBK(model.getCzlx());
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			tableName = "xybsxmjfxxb";
			if(dao.checkExists("xmdm||xydm", xmdm+userDep, tableName)){
				//update
				flag = StandardOperation.update(tableName, new String[]{"xmjf"},
						new String[]{xmjf}, "xmdm||xydm", xmdm+userDep, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xmdm","xmjf","xydm"}, 
						new String[]{xmdm,xmjf,userDep}, request);
			}
		}else{
			if(dao.checkExists("xmdm", xmdm, tableName)){
				//update
				flag = StandardOperation.update(tableName, new String[]{"xmmc","ssjfxm","czlx"},
						new String[]{xmmc,ssjfxm,czlx}, "xmdm", xmdm, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xmdm","xmmc","ssjfxm","czlx"}, 
						new String[]{xmdm,xmmc,ssjfxm,czlx}, request);
			}
		}
		return flag;
	}
	
	/**
	 * 删除比赛项目信息
	 * @param String[] pk
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean delBsxm(String[] pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "bsxmb";
		for(int i=0; i<pk.length; i++){
			if(pk[i] != null && !pk[i].equalsIgnoreCase("0001") && !pk[i].equalsIgnoreCase("0002")){// 0001 0002系统默认的项目不能删除
				flag = StandardOperation.delete(tableName, "xmdm", pk[i], request);
			}else{
				request.setAttribute("mes", "0001和0002为系统默认的项目，不能删除!");
			}
			
		}
		return flag;
	}
	
	/**
	 * 根据主键查询比赛项目信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> showBsxmInfo(String pk, String pkValue){
		return dao.querryBxsmInfo(pk,pkValue);
	}
	
	/**
	 * 根据主键查询比赛项目信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> showXyBsxmInfo(String pk, String pkValue){
		return dao.querryXyBxsmInfo(pk,pkValue);
	}
	
	/**
	 * 获取代码信息列表
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getBsxmList(String tableName, String[]columns,String tj1){
		if(tj1 != null && !"".equalsIgnoreCase(tj1)){
			tj1 = " where ssjfxm='" + tj1 + "'";
		}
		return dao.getDmList(tableName,columns,tj1);
	}
	
	
	public List getBxsmListByXy(String xydm){
		return dao.getBxsmListByXy(xydm);
	}
	/**
	 * 初始化比赛项目加分分数
	 * @param userDep
	 * @param reqeust
	 * @return boolean
	 * */
	public boolean initMark(String userDep, HttpServletRequest request) throws Exception{
		return StandardOperation.update("xybsxmjfxxb", new String[]{"xmjf"}, new String[]{""}, "xydm", userDep, request);
	}
	
	/**
	 * 查询素质积分信息
	 * @param model
	 * @return List
	 * @throws Exception 
	 * */
	public List querrySzjf(PjpyZjsyzyForm model) throws Exception{
		return dao.querrySzjf(model);
	}
	
	/**
	 * 查询素质积分信息表头
	 * @return List 
	 * */
	public List getSzjfToptr(){
		String tabName =  "view_xsszjf";
		String[] topColumn = {"主键","xn","xq","xh","xm","bjmc","xmlbmc","ssjfxm","czlx","xmjf"};
		String[] topCN = dao.getColumnNameCN(topColumn, tabName);
		return dao.arrayToList(topColumn, topCN); 
	}
	
	/**
	 * 根据学号查询学生信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuDetails(String xh){
		return dao.getStuDetails(xh);
	}
	
	/**
	 * 查询单个学生素质积分信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSzjfInfo(String pkValue){
		return dao.getSzjfInfo(pkValue);
	}
	
	/**
	 * 素质积分信息保存
	 * @param model
	 * @param doType
	 * @param request
	 * @return boolean 
	 * @throws Exception
	 * */
	public boolean saveSzjf(PjpyZjsyzyForm model, String doType, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "xsszjfb";
		String pk = "xn||xq||xh||xmlbdm||sj";
		String xh = model.getXh();
		String xmlbdm = model.getXmlbdm();
		String sj = model.getSj();
		String bz = DealString.toGBK(model.getBz());
		String jfxm = DealString.toGBK(model.getJfxm());
		String xmmc = DealString.toGBK(model.getXmmc());
		String zgdw = DealString.toGBK(model.getZgdw());
		String xn = model.getXn();
		String xq = model.getXq();
		String xhO = model.getXhO();
		String xmlbdmO = model.getXmlbdmO();
		String sjO = model.getSjO();
		
		String pkValue = xn+xq+xh+xmlbdm+sj;
		if(doType != null && "modi".equalsIgnoreCase(doType)){//更新
			pkValue = xn+xq+xhO+xmlbdmO+sjO;
			flag = StandardOperation.update(tableName, new String[]{"xh","xmlbdm","sj","xmmc","zgdw","jfxm","bz"},
					new String[]{xh,xmlbdm,sj,xmmc,zgdw,jfxm,bz}, pk,xn+xq+xhO+xmlbdmO+sjO, request);
		}else{//增加
			if(dao.isExists(tableName, pk, pkValue)){//记录已经存在
				//update
				flag = StandardOperation.update(tableName, new String[]{"jfxm","xmmc","zgdw","bz"}, new String[]{jfxm,xmmc,zgdw,bz}, pk, pkValue, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xh","xmlbdm","xn","xq","sj","bz","jfxm","xmmc","zgdw"}, new String[]{xh,xmlbdm,xn,xq,sj,bz,jfxm,xmmc,zgdw}, request);
			}
		}
		
		return flag;
	}
	
	/**
	 * 学生素质积分信息删除
	 * 
	 * */
	public boolean delSzjf(String pk, HttpServletRequest request) throws Exception{
		boolean flag = false;
		pk = DealString.toGBK(pk);
		String[] pkValues = pk.split("!!");
		String tableName = "xsszjfb";//操作数据表
		String pkStr = "xn||xq||xh||xmlbdm||sj";
		String mes = "";
		
		for(int i=0; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, pkStr, pkValues[i], request);
			if(flag==false){
				mes += "第" + i  + "条数据删除失败!\n";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * 查询评奖评优黑名单信息
	 * @param model
	 * @return List
	 * @throws Exception 
	 * */
	public List querryPjpyhmd(PjpyZjsyzyForm model) throws Exception{		
		return dao.querryPjpyhmd(model); 
	}
	
	/**
	 * 获取评奖评优黑名单信息表头
	 * @return List
	 * */
	public List getPjpyhmdToptr(){
		String tabName = "view_pjpyhmd";
		String[] topColumn = {"主键","xh","xm","bjmc","xmmc","kssj","jssj"};
		String[] topCN = dao.getColumnNameCN(topColumn, tabName);
		return dao.arrayToList(topColumn, topCN);
	}
	
	/**
	 * 获取黑名单项目列表
	 * @return List
	 * */
	public List getXmList(){
		String[] tmpValue = {"奖学金","荣誉称号"};		
		return dao.arrayToList(tmpValue, tmpValue);
	}
	
	/**
	 * 查询单个学生的评奖评优黑名单记录信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPjpyhmdInfo(String pkValue){
		return dao.getPjpyhmdInfo(pkValue);
	}
	
	/**
	 * 保存评奖评优黑名单信息
	 * @param model
	 * @param doType
	 * @param request
	 * @return booleans
	 * */
	public boolean savePjpyhmd(PjpyZjsyzyForm model,String doType,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "pjpyhmd";
		String pk = "xh||xmmc";
		String xh = model.getXh();
		String xmmc = DealString.toGBK(model.getXmmc());
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String yy = DealString.toGBK(model.getYy());
		String bz = DealString.toGBK(model.getBz());
		String pkValue = xh+xmmc;
		String xmmcO = DealString.toGBK(model.getXmmcO());
		
		if(doType != null && "modi".equalsIgnoreCase(doType)){//修改操作
			if(!xmmc.equalsIgnoreCase(xmmcO)){
				if(dao.isExists(tableName, pk, pkValue)){
					flag = StandardOperation.delete(tableName, pk, pkValue, request);
					if(flag){
						flag = StandardOperation.update(tableName, new String[]{"xh","xmmc","kssj","jssj","yy","bz"}, 
								new String[]{xh,xmmc,kssj,jssj,yy,bz}, pk, xh+xmmcO, request);
					}
				}else{
					flag = StandardOperation.update(tableName, new String[]{"xh","xmmc","kssj","jssj","yy","bz"}, 
							new String[]{xh,xmmc,kssj,jssj,yy,bz}, pk, xh+xmmcO, request);
				}
			}
		}else{//增加操作
			if(dao.isExists(tableName, pk, pkValue)){
				//update
				flag = StandardOperation.update(tableName, new String[]{"kssj","jssj","yy","bz"}, 
						new String[]{kssj,jssj,yy,bz}, pk, pkValue, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xh","xmmc","kssj","jssj","yy","bz"}, 
						new String[]{xh,xmmc,kssj,jssj,yy,bz}, request);
			}
		}
		return flag;
	}
	
	/**
	 * 删除评奖评优黑名单信息
	 * @param pk
	 * @param request
	 * @return boolean
	 * */
	public boolean delPjpyhmd(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		pk = DealString.toGBK(pk);
		String[] pkValues = pk.split("!!");
		String tableName = "pjpyhmd";//操作数据表
		String pkStr = "xh||xmmc";
		String mes = "";
		
		for(int i=0; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, pkStr, pkValues[i], request);
			if(flag==false){
				mes += "第" + (i+1)  + "条数据删除失败!\n";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * 自动计算综合素质测评分数
	 * @param model
	 * @return boolean
	 * */
	public boolean autoAccount(PjpyZjsyzyForm model) throws Exception{
		String xn = model.getXn();
		String xq = model.getXq();
		String bjdm = model.getBjdm();
		
		return dao.runProcuder("{call autoAccountOfZjsyzy(?,?,?)}", new String[]{xn,xq,bjdm});
	}
	
	/**
	 * 查询奖学金申请详细信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @throws Exception
	 * **/
	public HashMap<String, String> selectJxjsqOne(String pk, String pkValue) throws Exception{
		return dao.selectJxjsqOne(pk,pkValue);		
	}
	
	/**
	 * 查询学生基本信息
	 * @param xh
	 * @return HashMap<String, String> 
	 * @throws Exception
	 * */
	public HashMap<String, String> selectStuBase(String xh) throws Exception{
		return dao.selectStuBase(xh);
	}
	
	/**
	 * 查询学生在评奖学年的违纪处分信息 返回学年,学期,处分类别名称,处分原因名称
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfxx(String xh, String xn, String xq)
			throws Exception {
		return dao.getStuWjcfxx(xh, xn, xq);
	}
	
	/**
	 * 查询学生在评奖学年的违纪处分信息 返回违纪记录数
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public int getStuWjcfxxCount(String xh, String xn, String xq)
			throws Exception {
		return dao.getStuWjcfxxCount(xh, xn, xq);
	}
	
	/**
	 * 查询学生在评奖学年的学生成绩 返回:不及格显示背景色,课程名称,课程类型,成绩
	 * 
	 * @param jwbb
	 *            is true 则查询 过渡版成绩表反之学分版成绩表
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuKccjxx(boolean jwbb, String xh, String xn,
			String xq) throws Exception {
		return dao.getStuKccjxx(jwbb, xh, xn, xq);
	}
	
	/**
	 * 	查询学生所在班级综合素质成绩及排名信息
	 * @param xh
	 * @return
	 */
	public List<String[]> getBjZhszcpinfo(String xh,String xn,String xq) {
		return dao.getBjZhszcpinfo(xh,xn,xq);
	}
	
	/**
	 * 获取学生所在的班级名称
	 * @param xh
	 * @return String
	 * **/
	public String getStubjmc(String xh) {
		return dao.getStubjmc(xh);
	}
	
	/**
	 * 判断能否获得特等奖学金
	 * @param xh
	 * @param xn
	 * @param nd
	 * @param xq
	 * @throws Exception 
	 * */
	public boolean nfhdtdjxj(String xh,String xn, String nd, String xq) throws Exception{
		return dao.nfhdtdjxj(xh, xn, nd, xq);
	}
	
	/**
	 * 获取测评参评排名
	 * @param xh
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return String
	 * @throws Exception 
	 * */
	public String getCpcppm(String xh,String xn,String nd,String xq) throws Exception{
		return dao.getCpcppm(xh, xn, nd, xq);
	}
}

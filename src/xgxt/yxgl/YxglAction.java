package xgxt.yxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.exceptions.NotEnoughPowerException;
import xgxt.utils.CheckPower;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.yxgl.util.PictureUtil;

import common.Globals;
public class YxglAction extends Action{
	private int flag=0;
	private String sessionId;
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward af = new ActionForward();
		try{
			String parameter = mapping.getParameter();
			HttpSession session = request.getSession();
			String userOnLine = session.getAttribute("userOnLine") == null ? "" :
				session.getAttribute("userOnLine").toString();
			String usrName = session.getAttribute("userName") == null ? "" :
				session.getAttribute("userName").toString();
			sessionId = session.getId();
			if("yxgl_xybd".equalsIgnoreCase(parameter) //ѧԺ����
					&& CheckPower.checkUsrPageAccessPower(userOnLine, usrName, "yxgl_xybd.do")){
				af = yxglXybd(mapping,form,request,response);
			} else if("yxgl_xybd_one".equalsIgnoreCase(parameter)){//����ѧԺ����
				af = yxglXybdOne(mapping,form,request,response);
			} else if("yxgl_bjly_xsbdd".equalsIgnoreCase(parameter)){ //��ӡ       ��Ա�����ҵ
				af = yxglBjlyXsbdd(mapping,form,request,response);
			}else if("yxgl_hngy_xsbdd".equalsIgnoreCase(parameter)){ //��ӡ        ��Ժ��Ϲ�ҵ��ѧ
				af = yxglHngyXsbdd(mapping,form,request,response);
			}else if("yxgl_xyybd".equalsIgnoreCase(parameter)){   //ҽԺ�����ܽ���
				af = yxglXyybd(mapping,form,request,response);
			} else if("yxgl_xyybd_sdjyqy".equalsIgnoreCase(parameter)){
				af = yxglXyybdSdjyqy(mapping,form,request,response);
			}  else if("yxgl_xyybd_one".equalsIgnoreCase(parameter)){ //ҽԺ��������
				af = yxglXyybdOne(mapping,form,request,response);
			} else if("yxgl_stbd".equalsIgnoreCase(parameter)){  //ʳ�ñ����ܽ���
				af = yxglStbd(mapping,form,request,response);
			}  else if("yxgl_stbd_one".equalsIgnoreCase(parameter)){ //����ʳ�ñ���
				af = yxglStbdOne(mapping,form,request,response);
			} else if("yxgl_ssbd".equalsIgnoreCase(parameter)){ //���ᱨ���ܽ���
				af = yxglSsbd(mapping,form,request,response);
			} else if("yxgl_ssbd_one".equalsIgnoreCase(parameter)){//���ᵥ������
				af = yxglSsbdOne(mapping,form,request,response);
			} else if("yxgl_xsjf".equalsIgnoreCase(parameter)){  //ѧ���ɷ��ܽ���
				af = yxglXsjf(mapping,form,request,response);
			} else if("yxgl_lstd".equalsIgnoreCase(parameter)){  //��ɫͨ��
				af = yxglLstd(mapping,form,request,response);
			} else if("xsbd_one".equalsIgnoreCase(parameter)){ //��������
				af = xsbdOne(mapping,form,request,response);
			} else if("yxgl_xsgl".equalsIgnoreCase(parameter)){ //ѧ�������ܽ���
				af = yxglxsgl(mapping,form,request,response);
			} else if("yxgl_xsgl_one".equalsIgnoreCase(parameter)){ //ѧ����������
				af = yxgl_xsgl_one(mapping,form,request,response);
			} else if("yxgl_xsjf_one".equalsIgnoreCase(parameter)){//ѧ�������ɷ�
				af = yxgl_xsjf_one(mapping,form,request,response);
			} else if("yxgl_lstd_one".equalsIgnoreCase(parameter)){ //����ѧ����ɫͨ��
				af = yxgl_lstd_one(mapping,form,request,response);
			} else if("yxgl_xstj".equalsIgnoreCase(parameter)){  //ѧ��ͳ��
				af = yxgl_xstj(mapping,form,request,response);
			} else if("yxgl_lstd_report".equalsIgnoreCase(parameter)){ //��ӡ��ɫͨ��֪ͨ��
				af = yxgl_lstd_report(mapping,form,request,response);
			} else if("yxgl_jcsjwh".equalsIgnoreCase(parameter)){
				af = yxgl_jcsjwh(mapping,form,request,response);			
			} else if("yxgl_init_time".equalsIgnoreCase(parameter)){ //��ʼ��ʱ��
				af = yxgl_init_time(mapping,form,request,response);
			} else if("percentOfBdSearch".equalsIgnoreCase(parameter)){   //�����ʲ�ѯ  ��Ժ��Ϲ�ҵ��ѧ
				af = percentOfBdSearch(mapping,form,request,response);
			} else if("getStuDataByXy".equalsIgnoreCase(parameter)){   //�����ʲ�ѯ  ��Ժ��Ϲ�ҵ��ѧ
				af = getStuDataByXy(mapping,form,request,response);
			} else if("yxgl_bdzc".equalsIgnoreCase(parameter)){   //  ��ɽʦ��
				af = getBdzcInfo(mapping,form,request,response);
			}else if("yxgl_bdzcInfo".equalsIgnoreCase(parameter)){   //  ��ɽʦ��
				af = getOneBdzcInfo(mapping,form,request,response);  //  ��ɽʦ������ѧ������ע����Ϣ
			}else if("yxglExpData".equalsIgnoreCase(parameter)){   //  ��ɽʦ��
				af = yxglExpData(mapping,form,request,response);  //  ���ݵ���
			}else {  
 				throw new NotEnoughPowerException();
			}
		}catch(NotEnoughPowerException e){
			e.printStackTrace();
			return new ActionForward("/login.jsp",false);
		}
		return af;
	}
    
	private ActionForward yxgl_jcsjwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
				HttpServletResponse response) {
		DAO dao = new DAO();
		String sql = "";
		sql = "select table_name tablename,nvl(comments,table_name) tablecomment from user_tab_comments where table_name in (select upper(tablename) from yxjbsjb)";
		List<HashMap<String, String>> jbsjTableList = dao.getList(sql,
				new String[] {}, new String[] { "tablename", "tablecomment" });

		request.setAttribute("jbsjTableList", jbsjTableList);// ���ڵ������ݵĻ������ݱ�

		String writeAble = request.getParameter("writeAble");
		String title = request.getParameter("title");
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("title", title);
		return mapping.findForward("success");
	}	
		
	private ActionForward yxgl_lstd_report(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		YxglDAO yxglDao = new YxglDAO();
		String ksh = request.getParameter("ksh");
		HashMap<String, String> map = new HashMap<String, String>();
		map = yxglDao.getNewStuLstdReport(ksh);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward yxgl_xstj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ѧ��ͳ��
		Vector<Object> rs = new Vector<Object>(); 
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		String type = "";
		String yesCount = "";
		String noCount = "";
		String moduleName = "";
		String sql = "";
		String dh = ",";
		ArrayList vector = null;
		HashMap<String, String> map = new HashMap<String, String>();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String bgzldm = yxform.getBgzldm();
		String sfdm= yxform.getSfdm();
		String lddm = yxform.getLddm();
		String qsh = yxform.getQsh();
		//String ssbh = yxform.getSsbh(); //������
		String xxdm = StandardOperation.getXxdm();
		
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_xybd.do"))?"yes" : "no";
		}
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		if("sfbd".equalsIgnoreCase(bgzldm)){
			bgzldm = "";
			dh = "";
		}
		String xb = DealString.toGBK(yxform.getXb());
		String typeTmp = "";
//		String typeTmp2 []= null;
		StringBuffer sql_querry_condition = new StringBuffer("select count(*) sum");	
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX) && !StringUtils.isNull(yxform.getLddm())) {
//			sql_querry_condition.append(",ssbd");
//			bgzldm = "ssbd";  //���Ϲ�ҵ��ѧ
//		}else{	
			sql_querry_condition.append(dh);
			sql_querry_condition.append(bgzldm);
//		}	
		String go = request.getParameter("go"); 
		sql_querry_condition.append(" from view_newstureportinfo where 1=1 ");
//		String condi1 = null;
//		String condi2 = null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)) {
			//���Ϲ�ҵ��ѧ
//			if(StringUtils.isNull(yxform.getQsh())){
//				rs =  YxglDAO.getInstance().dealHENAN_Lddm(yxform);
//				map = YxglDAO.getInstance().getSsbdNumByLddm(rs);
//				request.setAttribute("rs2",rs);
//				request.setAttribute("henanSs", "");  //Ϊ�˸ı�therd�ı����ֵ������һ����־λ
//			}else{
//				map = YxglDAO.getInstance().dealHENAN_Qsh(yxform);
//			}
			//String lddm = yxform.getLddm();
			//String qsh = yxform.getQsh();
			//String [] condiArray = new String[]{xydm, zydm,bjdm,xb,sfdm,lddm,qsh};
			//String[] condiArrayCol = new String[]{"xydm", "zydm","bjdm","xb","sfdm","lddm","qsh"};
			//condi2 = makeQueryCondition(condiArray,bgzldm,condiArrayCol); //�õ�����ֵ
			//condi1 = makeQueryCondition(condiArray,null,condiArrayCol);
			request.setAttribute("onload", "yes");
		}
		sql_querry_condition.append(makeQueryCondition(xydm, zydm, bjdm,bgzldm,xb,sfdm,lddm,qsh));	
		if("go".equalsIgnoreCase(go)){
			if(!bjdm.equalsIgnoreCase("")){
				sql = "select bjmc from newbjdmb where bjdm = ? order by bjmc";
				String[] tmp = dao.getOneRs(sql, new String[] {bjdm},new String[]{"bjmc"});
				moduleName = tmp[0];
				typeTmp = "";
			}else if(!zydm.equalsIgnoreCase("")){
				sql = "select zymc from newzydmb where zydm = ? order by zymc";
				String[] tmp = dao.getOneRs(sql, new String[] {zydm},new String[]{"zymc"});
				moduleName = tmp[0];
				typeTmp = "bjmc";
			}else if(!xydm.equalsIgnoreCase("")){
				sql = "select xymc from newxydmb where xydm = ? order by xymc";
				String[] tmp = dao.getOneRs(sql, new String[] {xydm},new String[]{"xymc"});
				moduleName = tmp[0];
				typeTmp = "zymc";
			}else{
				moduleName = "ȫУ��Χ";
				typeTmp = "xymc";
			}	
			if(bgzldm.equalsIgnoreCase("")){  //��ʾ��У����				
				String[] valArr  = dao.getOneRs(sql_querry_condition.toString(), new String[] {},new String[]{"sum"});
				if(valArr!=null){
					yesCount = valArr[0];
				}else{
					yesCount = "0";
				}
				if(!typeTmp.equalsIgnoreCase("")){
					sql_querry_condition = new StringBuffer("select a.");
					sql_querry_condition.append(typeTmp);
					sql_querry_condition.append(",'��У����' bdzl,a.sum,nvl(b.yescount,'0') yescount, to_char(to_number(a.sum) - to_number(nvl(b.yescount,'0'))) nocount from (select count(*) sum,");
					sql_querry_condition.append(typeTmp);
					sql_querry_condition.append(" from view_newstureportinfo where 1=1 "); 
					sql_querry_condition.append(makeQueryCondition(xydm, zydm, bjdm,null,xb,sfdm,lddm,qsh));
					sql_querry_condition.append(" group by ");
					sql_querry_condition.append(typeTmp);
					sql_querry_condition.append(") a left join (select count(*) yescount,");
					sql_querry_condition.append(typeTmp);
					sql_querry_condition.append(" from view_newstureportinfo where 1=1");
					sql_querry_condition.append(makeQueryCondition(xydm, zydm, bjdm,bgzldm,xb,sfdm,lddm,qsh));
					sql_querry_condition.append(" group by ");
					sql_querry_condition.append(typeTmp);
					sql_querry_condition.append(") b on a.");
					sql_querry_condition.append(typeTmp);
					sql_querry_condition.append("= b.");
					sql_querry_condition.append(typeTmp);
					//System.out.println(sql_querry_condition.toString());
//					
//					String tempSql = "select a.zymc,'��У����' bdzl,a.sum,nvl(b.yescount,'0') yescount, to_char(to_number(a.sum) - to_number(nvl(b.yescount,'0'))) nocount from (select count(*) sum,zymc from view_newstureportinfo where 1=1  and xydm='101'  group by zymc) a left join (select count(*) yescount,zymc from view_newstureportinfo where 1=1 and xydm='101'  and (xybd='��' or yybd='��' or stbd='��' or ssbd='��' )  group by zymc) b on a.zymc= b.zymc";
//					ArrayList<String[]> list = dao.rsToVator(tempSql, new String[] {},new String[]{typeTmp, "bdzl","yescount","nocount","sum"});
//					if(list == null){
//						System.out.println("null");
//					}
//					rs.addAll(list);
					
					rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[] {},new String[]{typeTmp, "bdzl","yescount","nocount","sum"}));
					
					request.setAttribute("rs2", rs);
				}
				bgzldm = null;
				sql_querry_condition = new StringBuffer("select count(*) sum from view_newstureportinfo where 1=1 ");
				sql_querry_condition.append(makeQueryCondition(xydm, zydm, bjdm,null,xb,sfdm,lddm,qsh));
				String tmp = (dao.getOneRs(sql_querry_condition.toString(), new String[] {},new String[]{"sum"}))[0];
				noCount = ((Integer)(Integer.parseInt(tmp)-Integer.parseInt(yesCount))).toString();
				map.put("chickModule", "��У����"); 
			}else{
				String[] tmp = dao.getColumnNameCN(new String[]{bgzldm}, "view_newstureportinfo");
				map.put("chickModule", tmp[0]); 

				vector = dao.rsToVator(sql_querry_condition.toString(), new String[] {},new String[]{"sum",bgzldm});
				if(vector!=null){
					for(int i = 0 ;i < vector.size();i++){
						type =  ((String [])vector.get(i))[1]; 
						if (type.equalsIgnoreCase("��")){
							yesCount = ((String [])vector.get(i))[0]; 
						}else if (type.equalsIgnoreCase("��")){
							noCount = ((String [])vector.get(i))[0]; 
						}
					}
					if(!typeTmp.equalsIgnoreCase("")){
						sql_querry_condition = new StringBuffer("select a.");
						sql_querry_condition.append(typeTmp);
						sql_querry_condition.append(",'");
						sql_querry_condition.append(tmp[0]);
						sql_querry_condition.append("' bdzl,a.sum,nvl(b.yescount,'0') yescount, to_char(to_number(a.sum) - to_number(nvl(b.yescount,'0'))) nocount from (select count(*) sum,");
						sql_querry_condition.append(typeTmp);
						sql_querry_condition.append(" from view_newstureportinfo where 1=1 "); 
						sql_querry_condition.append(makeQueryCondition(xydm, zydm, bjdm,typeTmp,xb,sfdm,lddm,qsh));
						sql_querry_condition.append(") a left join (select count(*) yescount,");
						sql_querry_condition.append(typeTmp);
						sql_querry_condition.append(" from view_newstureportinfo where 1=1 and ");
						sql_querry_condition.append(bgzldm);
						sql_querry_condition.append(" = '��'");
						sql_querry_condition.append(makeQueryCondition(xydm, zydm, bjdm,typeTmp,xb,sfdm,lddm,qsh));
						sql_querry_condition.append(") b on a.");
						sql_querry_condition.append(typeTmp);
						sql_querry_condition.append("= b.");
						sql_querry_condition.append(typeTmp);
						rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[] {},new String[]{typeTmp, "bdzl","yescount","nocount","sum"}));
						request.setAttribute("rs2", rs);
					}
				}
				}
				if(null==yesCount||yesCount.equalsIgnoreCase("")){
					yesCount = "0";
				}
				if(null==noCount||noCount.equalsIgnoreCase("")){
					noCount = "0";
				}
				String maxSum = ((Integer)(Integer.parseInt(yesCount)+Integer.parseInt(noCount))).toString();
				map.put("moduleName", moduleName);
				map.put("yesCount", yesCount);
				map.put("noCount", noCount); 
				map.put("maxSum", maxSum); 			
			}	
			if(flag != 0 ){
				request.setAttribute("onload", "no");
				flag = 0;
			} else{
				request.setAttribute("onload", "yes");
			}
		//}
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("bgList", subDao.getBgList());
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)){   
			request.setAttribute("bgzlList", subDao.getBgzlList_HENAN());//���Ϲ�ҵ��ѧ�ı�������
			//request.setAttribute("ssList", subDao.getNewSsList()); //���Ἧ��
			request.setAttribute("ldList", subDao.getLdmcList()); //¥������
		}else{
			request.setAttribute("bgzlList", subDao.getBgzlList());
		}
		HashMap<String,String> dqnd = new HashMap<String,String>();
		dqnd.put("nj",subDao.getDqnd());
		request.setAttribute("dqnd",dqnd);   //��õ�ǰ���
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("sfList", subDao.getSfList());
		return mapping.findForward("success");
	}

	private ActionForward yxgl_lstd_one(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//ѧ����ɫͨ��
		YxglActionForm yxglForm = (YxglActionForm) form;
		YxglDAO yxgldao = new YxglDAO();
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
		String ksh  = yxglForm.getKsh();
		String xh   = yxglForm.getXh();
		String xydm = yxglForm.getXydm();
		String zydm = yxglForm.getZydm();
		String dors = "";
		HashMap<String, String> map = new HashMap<String, String>();
		
		String xxdm = StandardOperation.getXxdm();
		if(active == null){
			String cjrq = DealString.toGBK(yxglForm.getCjrq());
			String hyjqsf = DealString.toGBK(yxglForm.getHyjqsf());
			String hyjxf = DealString.toGBK(yxglForm.getHyjxf());
			String jtrs = DealString.toGBK(yxglForm.getJtrs());
			String pjsr = DealString.toGBK(yxglForm.getPjsr());
			String kndj = DealString.toGBK(yxglForm.getKndj());
			String bz = DealString.toGBK(yxglForm.getBz());
			String[] tablecol = {"lsh","ksh","xh","cjrq","jtrs","pjsr","kndj","bz","hyjqsf","hyjxf"};//������ɫͨ�����ֶ�
			String[] values = {ksh,xh,cjrq,jtrs,pjsr,kndj,bz,hyjqsf,hyjxf};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ����������ɫͨ����Ϣ    			
					if(StandardOperation.insert("stbdzcb",tablecol,values, request)){
						dors = "ok";
					} else{
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
					boolean del = StandardOperation.delete("lstdxxb", "xh", xh, request);
					if(del){
						yxgldao.insertlstd(values);
					}
					request.setAttribute("dors", dors);
				} else if("modify2".equalsIgnoreCase(doType)){
					boolean del = StandardOperation.delete("lstdxxb", "xh", xh, request);
					if(del){
						yxgldao.insertlstd(values);
					}
					request.setAttribute("dors", dors);
					ksh = request.getParameter("ksh");
					String[] cols = new String[]{"ksh","xh","cjrq","jtrs","pjsr","kndj","bz","hyjqsf","hyjxf"};
					String[] vals = yxgldao.getNewStulstdInfo(ksh, cols);
					for(int i=0;i<cols.length;i++){
						map.put(cols[i], (vals!=null)? vals[i]:"");
					}
					cols = new String[]{"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","sftj","sfzsym"};
					vals = yxgldao.getNewStuInfo(ksh, cols);
					for(int i=0;i<cols.length;i++){
						map.put(cols[i], (vals!=null)? vals[i]:"");
					}
					if(map.get("hyjqsf")==null||map.get("hyjqsf").equalsIgnoreCase("")){ //����ס�޷�
						map.put("hyjqsf", map.get("ysqsf"));
					}
					if(map.get("hyjxf")==null||map.get("hyjxf").equalsIgnoreCase("")){//����ѧ��
						map.put("hyjxf", map.get("ysxf"));
					}
					if(map.get("cjrq")==null||map.get("cjrq").equalsIgnoreCase("")){//��������
						map.put("cjrq", yxgldao.getNextYear()+"0101");
					}
					request.setAttribute("modify", "modify");
				} 
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			
		} else if("modify".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			ksh = request.getParameter("ksh");
			String[] cols = new String[]{"ksh","xh","cjrq","jtrs","pjsr","kndj","bz","hyjqsf","hyjxf"};
			String[] vals = yxgldao.getNewStulstdInfo(ksh, cols);
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
			cols = new String[]{"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","sftj","sfzsym","sfzh"};
			vals = yxgldao.getNewStuInfo(ksh, cols);
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
			if(map.get("hyjqsf")==null||map.get("hyjqsf").equalsIgnoreCase("")){ //����ס�޷�
				map.put("hyjqsf", map.get("ysqsf"));
			}
			if(map.get("hyjxf")==null||map.get("hyjxf").equalsIgnoreCase("")){ //����ѧ��
				map.put("hyjxf", map.get("ysxf"));
			}
			if(map.get("cjrq")==null||map.get("cjrq").equalsIgnoreCase("")){  //��������
				map.put("cjrq", yxgldao.getNextYear()+"0101");
			}
			request.setAttribute("modify", "modify");
		} 
		
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("kndjList", yxgldao.getKndjList());
		request.setAttribute("sffsList", yxgldao.getSffsList());
		request.setAttribute("rs", map);
		
		
		
		return mapping.findForward("success");
	}

	private ActionForward yxgl_xsjf_one(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//��������������Ӻ��޸�ѧ��������Ϣ
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
//		DAO dao = DAO.getInstance();
		YxglDAO yxgldao = new YxglDAO();
		YxglActionForm yaform = (YxglActionForm) form;
		HttpSession session = request.getSession();
		String xydm = yaform.getXydm();
		String zydm = yaform.getZydm();
//		String bjdm = yaform.getBjdm();
		if(xydm==null && "xy".equalsIgnoreCase(session.getAttribute("userType").toString())){
			xydm = session.getAttribute("userDep").toString();
		}
		String dors = "";
		String ksh = yaform.getKsh();
		String xh = DealString.toGBK(yaform.getXh());
		HashMap<String, String> map = new HashMap<String, String>();
		if(active == null){
			String ysqsf = yaform.getYsqsf();
			String ysxf = yaform.getYsxf();
			String sjqsf = yaform.getSjqsf();
			String sjxf = yaform.getSjxf();
			String sffs = yaform.getSffs();
			String[] tablecol = {"ysqsf","ysxf","sjqsf","sjxf","sffs","ksh","xh"};//ѧԺ�������ֶ�
			String[] values = {ysqsf,ysxf,sjqsf,sjxf,sffs,ksh,xh};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ��������ѧԺ������Ϣ    			
					if(StandardOperation.insert("newstuskb",tablecol,values, request)){
						dors = "ok";
					} else{
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
					boolean del = StandardOperation.delete("newstuskb", "xh", xh, request);
					if(del){
						StandardOperation.insert("newstuskb",tablecol,values, request);
					}
					request.setAttribute("dors", dors);
				} 
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			
		} else if("modify".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			ksh = request.getParameter("ksh");
			String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","ysqsf","ysxf","sjqsf","sjxf","sffs","sfzh"};
			String[] vals = yxgldao.getNewStuInfo(ksh, cols);
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
				request.setAttribute("modify", "modify");
		} 
		if(map!=null&&map.size()!=0){
			if(("1").equalsIgnoreCase(map.get("sffs"))){
				request.setAttribute("sjdis", "yes");
			}
		}
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("rs", map);
		//��ȡ��Ƭ
		String[] id = new String[]{ksh,map.get("xh"),map.get("sfzh")};
		String dir = request.getSession().getServletContext().getRealPath("");
		map.put("picture",PictureUtil.getPicture(id, dir,"yxgl"));
		
		request.setAttribute("sffsList", yxgldao.getSffsList());
		return mapping.findForward("success");
	}

	private ActionForward yxgl_xsgl_one(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		//��������������Ӻ��޸�ѧ����Ϣ
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		YxglDAO yxgldao = new YxglDAO();
		YxglService service = YxglService.getInstance();
		YxglActionForm yaform = (YxglActionForm) form;
		HttpSession session = request.getSession();
		String xydm = yaform.getXydm();                  //ѧԺ���� 
		String zydm = yaform.getZydm();                  //רҵ����
		String bjdm = yaform.getBjdm();                  //�༶����
		String sfdm = yaform.getSfdm();                  //ʡ�ݴ���
		String xb = DealString.toGBK(yaform.getXb());    //�Ա�
		String pageType = "";
		if(xydm==null && "xy".equalsIgnoreCase(session.getAttribute("userType").toString())){
			xydm = session.getAttribute("userDep").toString();
		}
		String dors = "";
		String ksh = DealString.toGBK(yaform.getKsh());  //������
		String xh = DealString.toGBK(yaform.getXh());    //ѧ��
		String xm = DealString.toGBK(yaform.getXm());    //����
		String sfzh = DealString.toGBK(yaform.getSfzh());//���֤��
		String csrq = yaform.getCsrq();                  //��������
		String jg = DealString.toGBK(yaform.getJg());    //����
		String nj = GetTime.getNowYear();                //��ǰ�꼶
		HashMap<String, String> map = new HashMap<String, String>();
		try{
		if(active == null){
			String[] tablecol = {"ksh","xh","xm","xb","nj","csrq","jg","sfzh","xydm","zydm","bjdm","sfdm"};//������Ϣ�ֶ�
			String[] values = {ksh,xh,xm,xb,nj,csrq,jg,sfzh,xydm,zydm,bjdm,sfdm};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ����������Ϣ    			
//					if(StandardOperation.insert("newstusinfo",tablecol,values, request)){
//						dors = "ok";
//					} else{
//						dors = "no";
//					}
					//�ж��ű�����Ҫ����
					String [] valuesM = new String[]{ksh,xh,sfzh,xm,xb,sfdm,csrq,jg,xydm,zydm,bjdm,nj};
					if(service.saveNewStuInfo(valuesM)){
						dors = "ok";
					}else{
						dors = "no";
					}
					map.put("ksh",ksh);
					map.put("xh",xh);
					map.put("xm",xm);
					map.put("xb",xb);
					map.put("csrq",csrq);
					map.put("jg",jg);
					map.put("sfzh",sfzh);
					map.put("xydm",xydm);
					map.put("zydm",zydm);
					map.put("bjdm",bjdm);
					map.put("sfdm",sfdm);
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
					// TODO �޸�������Ϣ
					if(StandardOperation.update("newstusinfo",tablecol,values, "ksh", ksh, request)){
						dors = "ok";
					} else {
						dors = "no";
					}
					request.setAttribute("dors", dors);		
				} else {
					pageType = request.getParameter("pageType");
					map.put("ksh",ksh);
					map.put("xh",xh);
					map.put("xm",xm);
					map.put("xb",xb);
					map.put("csrq",csrq);
					map.put("jg",jg);
					map.put("sfzh",sfzh);
					map.put("xydm",xydm);
					map.put("zydm",zydm);
					map.put("bjdm",bjdm);
					map.put("sfdm",sfdm);
					if(pageType.equalsIgnoreCase("modify")){
						request.setAttribute("modify", "modify");						
					}
				}
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			pageType = "add";	
		} else if("modify".equalsIgnoreCase(active) || "view".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			pageType = "modify";
			ksh = request.getParameter("ksh");
			String[] cols = {"ksh","xh","xm","xydm","xb","sfzh","zydm","bjdm","sfdm","csrq","jg","nj"};
			String[] vals = yxgldao.getNewStuGlinfo(ksh, cols);
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
				request.setAttribute("modify", "modify");
		} 
		if(map!=null&&map.size()!=0){
			if(("1").equalsIgnoreCase(map.get("sffs"))){
				request.setAttribute("sjdis", "yes");
			}
		}
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("pageType", pageType);
		request.setAttribute("sfList", yxgldao.getNewSfList());
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("doType", active);
		request.setAttribute("rs", map);
		request.setAttribute("xxdm", xxdm);
		
		//��ȡ��Ƭ
		map = this.getPicture(map, ksh, request);
		} catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward yxglxsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��������
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String xm = DealString.toGBK(yxform.getXm());
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		String xxdm = StandardOperation.getXxdm();
		
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"};
		String[] title_cn = dao.getColumnNameCN(title_en, "view_newstureportinfo");
		Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,xybd,yybd,stbd,ssbd from view_newstureportinfo where 1=1 ");
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		sql_querry_condition.append("order by ksh");
		String go = request.getParameter("go");
		//��ģ��ִ��ɾ������
		String active = request.getParameter("active");
		if("delete".equalsIgnoreCase(active)){
			ksh = request.getParameter("pk");
			String dors = "";
			boolean del = StandardOperation.delete("newstusinfo","ksh",ksh, request);
			if(del){
				dors = "yes"; 
			} else { 
				dors= "no";
			}
			request.setAttribute("dors", dors);
			go="go";
		}
		Vector<String[]> rs = new Vector<String[]>();    	
		if("go".equalsIgnoreCase(go)){
			rs.addAll(dao.rsToVator(
					sql_querry_condition.toString(), 
					new String[]{}, 
					new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"}));
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			//have bug
			for(String[] array : rs){
				if(array[7].equalsIgnoreCase("off")){
					array[7] = "��";
				}else if(array[7].equalsIgnoreCase("on")){
					array[7] = "��";
				}
			}
			request.setAttribute("rs", rs);
			flag++;
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_xsgl.do"))?"yes" : "no";
		}
		
		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}		
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	private ActionForward xsbdOne(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		try{
			//ӭ�¹�����������������
			String doType = request.getParameter("doType");
	//		String tableName = "";
			YxglDAO yxgldao = new YxglDAO();
			YxglActionForm yxform = (YxglActionForm) form;
	//		HttpSession session = request.getSession();
			String ksh = yxform.getKsh();
			String xh = yxform.getXh();
			String bd = "��";
			boolean del = false;
			String xxdm = StandardOperation.getXxdm(); //�õ�ѧУ����
			request.setAttribute("xxdm", xxdm);
			
			//List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
			//request.setAttribute("rs", rs);
			
			if(doType.equalsIgnoreCase("xy")){
				String sflsj = yxform.getSflsj();
				del = yxgldao.doDelete(ksh);
				if(del){
					String[] tablecol = {"ksh","xh","sflsj","xybd"};//ѧԺ�������ֶ�
					String[] values = {ksh,xh,sflsj,bd};
					StandardOperation.insert("xybdzcb",tablecol,values, request);
				}
				//return mapping.findForward("xy");
			}else if(doType.equalsIgnoreCase("yy")){
				String Sfzsym = yxform.getSfzsym();
				String sftj = yxform.getSftj();
				del = yxgldao.doXyyBdDelete(ksh);
				if(del){
					String[] tablecol = {"ksh","xh","Sfzsym","sftj","sfbd"};//ҽԺ�������ֶ�
					String[] values = {ksh,xh,Sfzsym,sftj,bd};
					StandardOperation.insert("xyybdzcb",tablecol,values, request);
				}
				//return mapping.findForward("yy");
			}else if(doType.equalsIgnoreCase("st")){
				del = yxgldao.doStBdDelete(ksh);
				if(del){
					String[] tablecol = {"ksh","xh","stbd"};//ʳ�ñ������ֶ�
					String[] values = {ksh,xh,bd};
					StandardOperation.insert("stbdzcb",tablecol,values, request);
				}
				//return mapping.findForward("st");
			}else if(doType.equalsIgnoreCase("ss")){
					del = yxgldao.doSsBdDelete(ksh); 
					if(del){
						String[] tablecol = {"ksh","xh","ssbd"};//���ᱨ�����ֶ�
						String[] values = {ksh,xh,bd};
						StandardOperation.insert("ssbdzcb",tablecol,values, request);
					}
					
				//return mapping.findForward("ss");
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("do", "����ʧ��!");
			//return mapping.findForward("");
		}
		return null;
	}

	/**
	 * ӭ�¹���������ѧ��ѧԺ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	private ActionForward yxglXybd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//TODO Ĭ����ʾ��ѯҳ�棬����ֻ�ܲ鵽�Ѿ�������ѧ��
		//TODO ��������ѯʱҪ������Ӧ�Ĳ�ѯ���� 
		try{
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String xm = DealString.toGBK(yxform.getXm());
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"};
		String[] title_cn = dao.getColumnNameCN(title_en, "view_newstureportinfo");
		Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,xybd,yybd,stbd,ssbd from view_newstureportinfo where 1=1 ");
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		String go = request.getParameter("go");
		{//��ģ��ִ��ɾ������
			String active = request.getParameter("active");
			if("delete".equalsIgnoreCase(active)){
				ksh = request.getParameter("ksh");
				String dors = "";
				if(subDao.doDelete(ksh)){
					dors = "yes"; 
				} else { 
					dors= "no";
				}
				request.setAttribute("dors", dors);
				go="go";
			}
		}
		Vector<String[]> rs = new Vector<String[]>();    	
		if("go".equalsIgnoreCase(go)){
			rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[]{}, new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"}));
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			request.setAttribute("rs", rs);
			flag++;
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_xybd.do"))?"yes" : "no";
		}
		
		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}
		yxform.setKsh("");
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		request.setAttribute("xxdm", xxdm);
		if(new YxglDAO().isOkTime(StandardOperation.getXxdm()).equalsIgnoreCase("no")){
			request.setAttribute("tag", "no");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward yxglXybdOne(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		// TODO ��������������Ӻ��޸�ѧ����Ϣ
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
//		DAO dao = DAO.getInstance();
		YxglDAO yxgldao = new YxglDAO();
		YxglActionForm yaform = (YxglActionForm) form;
		
		String xxdm = StandardOperation.getXxdm();
		HttpSession session = request.getSession();
		String xydm = yaform.getXydm();
		String zydm = yaform.getZydm();
		String bjdm = yaform.getBjdm();
		if(xydm==null && "xy".equalsIgnoreCase(session.getAttribute("userType").toString())){
			xydm = session.getAttribute("userDep").toString();
		}
		String dors = "";
		String ksh = yaform.getKsh();
		String xh = yaform.getXh();
		String sflsj = yaform.getSflsj();
		HashMap<String, String> map = new HashMap<String, String>();
		if(active == null){
			String[] tablecol = {"ksh","xh","sflsj"};//ѧԺ�������ֶ�
			String[] values = {ksh,xh,sflsj};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ��������ѧԺ������Ϣ    			
					if(StandardOperation.insert("xybdzcb",tablecol,values, request)){
						dors = "ok";
					} else{
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
					// TODO �޸�����ѧԺ������Ϣ
					if(StandardOperation.update("xybdzcb",tablecol,values, "ksh", ksh, request)){
						dors = "ok";
					} else {
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else {
					// TODO �ڶ�ȡ��������ѧ���Ŀ�����֮�󣬻�ȡ����������Ϣ
					String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","cwh"};
					String[] vals = yxgldao.getNewStuInfo(ksh, cols);
					for(int i=0;i<cols.length;i++){
						if(cols[i].equalsIgnoreCase("xydm")){
							map.put(cols[i], (xydm != null && !(xydm.trim().equals("")))? xydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("zydm")){
							map.put(cols[i], (zydm != null && !(zydm.trim().equals("")))? zydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("bjdm")){
							map.put(cols[i], (bjdm != null && !(bjdm.trim().equals("")))? bjdm :(vals != null ? vals[i] :""));
						} else {
							map.put(cols[i], vals != null ? vals[i] :"");
						}						
					}				
				}
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			
		} else if("modify".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			
			ksh = request.getParameter("ksh");
			HashMap<String, String> map2 = new HashMap<String, String>();
			String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","sfzh","cwh"};
			String[] vals=null;
			if(xydm!=null){
			 vals = yxgldao.getNewStuInfoByXydm(ksh,xydm, cols);
			}else{
			 vals = yxgldao.getNewStuInfo(ksh, cols);
			}
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
			request.setAttribute("modify", "modify");
			cols = new String[] {"jtrs","pjsr","kndj","bz","cjrq","hyjqsf","hyjxf"};
			vals = yxgldao.getNewStulstdInfo(ksh,cols);	
			for(int i=0;i<cols.length;i++){
				map2.put(cols[i], (vals!=null)? vals[i]:"");
			}
			if(map2.get("cjrq")!=null){
				request.setAttribute("rs2", map2);
			}
		} 
		if(map!=null&&map.size()!=0){
			if(("1").equalsIgnoreCase(map.get("sffs"))){
				request.setAttribute("sjdis", "yes");
			}
		}
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("kndjList", yxgldao.getKndjList());
		request.setAttribute("sffsList", yxgldao.getSffsList());
		request.setAttribute("rs", map);
		
		map = this.getPicture(map, ksh, request);		
		if(!map.get("ksh").equalsIgnoreCase("")){
			return mapping.findForward("success");
		}else{
			return  new ActionForward("/yxgl/yxgl_err.jsp");
		}
	}
    //��ӡ������ҵ������������
	private ActionForward yxglBjlyXsbdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		YxglDAO yxglDao = new YxglDAO();
		String ksh = request.getParameter("ksh");
		String bd = "��";
		boolean del = false;
		String xh = request.getParameter("xh");
		String sflsj = request.getParameter("sflsj");//�Ƿ����վ�
		if(sflsj.equalsIgnoreCase("undefined")){
			sflsj = null;
		}
		del = yxglDao.doDelete(ksh);
			if(del){
				String[] tablecol = {"ksh","xh","sflsj","xybd"};//ѧԺ�������ֶ�
				String[] values = {ksh,xh,sflsj,bd};
				StandardOperation.insert("xybdzcb",tablecol,values, request);
			}
		HashMap<String, String> map = new HashMap<String, String>();
		map = yxglDao.getNewStuDetails(ksh);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	/**
	 * УҽԺ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward yxglXyybd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		// TODO �޸�ѧ��УҽԺ������ͼ
		try{
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String sftj = DealString.toGBK(yxform.getSftj());
		String sfzsym = DealString.toGBK(yxform.getSfzsym());
		String xm = DealString.toGBK(yxform.getXm());
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"};
		String[] title_cn = dao.getColumnNameCN(title_en, "view_newstureportinfo");
		Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,xybd,yybd,stbd,ssbd from view_newstuyyreportinfo where 1=1 ");
		if(sftj!=null&&!sftj.equalsIgnoreCase("")){
			sql_querry_condition.append(" and sftj = '");
			sql_querry_condition.append(sftj);
			sql_querry_condition.append("'");
		}
		if(sfzsym!=null&&!sfzsym.equalsIgnoreCase("")) {
			sql_querry_condition.append(" and sfzsym = '");
			sql_querry_condition.append(sfzsym);
			sql_querry_condition.append("'");
		}
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		String go = request.getParameter("go");
		{//��ģ��ִ��ɾ������
			String active = request.getParameter("active");
			if("delete".equalsIgnoreCase(active)){
				String dors =" ";
				ksh = request.getParameter("ksh");
				if(subDao.doXyyBdDelete(ksh)){
					dors = "yes"; 
				} else { 
					dors= "no";
				}
				request.setAttribute("dors", dors);
				go = "go";
			}
		}
		Vector<String[]> rs = new Vector<String[]>();
		
		if("go".equalsIgnoreCase(go)){
			rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[]{}, new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"}));
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			request.setAttribute("rs", rs);
			flag++;
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_xyybd.do"))?"yes" : "no";
		}

		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}
		yxform.setKsh("");
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		request.setAttribute("sfzsymList", subDao.getSfzsymList());
		request.setAttribute("sftjList", subDao.getSftjList());
		if(new YxglDAO().isOkTime(StandardOperation.getXxdm()).equalsIgnoreCase("no")){
			request.setAttribute("tag", "no");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * ��УҽԺ�趨���������ѧ��(�޸������Ļ�����Ϣ) 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	private ActionForward yxglXyybdSdjyqy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		YxglActionForm yxform = (YxglActionForm) form;
		YxglDAO yxglDao = new YxglDAO();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
//		DealString deal = new DealString();
		String sfmcTmp = DealString.toGBK(yxform.getSxsfdm());
		String [] sfmc = sfmcTmp.split(","); 
//		String dors = "no";
		if("modify".equalsIgnoreCase(doType)){
			if(yxglDao.setMedicineArea("yes", doType, sfmc)){
//				dors = "yes";
			}
		} else if("cancel".equalsIgnoreCase(doType)){
			String sql = "update newstusinfo set sfjy='no'";
			if(dao.runUpdate(sql, new String[]{})){
//				dors = "yes";
			}
		}
		String[] rs = yxglDao.getMedicineArea();
		if(rs != null){
			request.setAttribute("rs", rs);
		}
		yxform.setSfmc("");
		yxform.setSxsfdm("");
		request.setAttribute("sfList", yxglDao.getProvinceList());
		return mapping.findForward("success");
	}
	
	private ActionForward yxglXyybdOne(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		// TODO ���ѧ����УҽԺ���������ӡ��޸ĺ�ɾ��
		YxglActionForm yxglForm = (YxglActionForm) form;
		YxglDAO yxgldao = new YxglDAO();
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
		String ksh  = yxglForm.getKsh();
		String xh   = yxglForm.getXh();
		String sfbd = yxglForm.getSfbd();
		String sftj = yxglForm.getSftj();
		String sfzsym = yxglForm.getSfzsym();
		String xydm = yxglForm.getXydm();
		String zydm = yxglForm.getZydm();
		String bjdm = yxglForm.getBjdm();
		HttpSession session = request.getSession();
		if(xydm==null && "xy".equalsIgnoreCase(session.getAttribute("userType").toString())){
			xydm = session.getAttribute("userDep").toString();
		}
		String dors = "";
		HashMap<String, String> map = new HashMap<String, String>();
		if(active == null){
			String[] tablecol = {"ksh","xh","sfbd","sftj","sfzsym"};//ѧԺ�������ֶ�
			String[] values = {ksh,xh,sfbd,sftj,sfzsym};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ��������ѧԺ������Ϣ    			
					if(StandardOperation.insert("xyybdzcb",tablecol,values, request)){
						dors = "ok";
					} else{
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
					// TODO �޸�����ѧԺ������Ϣ
					if(StandardOperation.update("xyybdzcb",tablecol,values, "ksh", ksh, request)){
						dors = "ok";
					} else {
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else {
					// TODO �ڶ�ȡ��������ѧ���Ŀ�����֮�󣬻�ȡ����������Ϣ
					String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sfbd","sftj","sfzsym"};
					String[] vals = yxgldao.getNewStuHospitalInfo(ksh, cols);
					for(int i=0;i<cols.length;i++){
						if(cols[i].equalsIgnoreCase("xydm")){
							map.put(cols[i], (xydm != null && !(xydm.trim().equals("")))? xydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("zydm")){
							map.put(cols[i], (zydm != null && !(zydm.trim().equals("")))? zydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("bjdm")){
							map.put(cols[i], (bjdm != null && !(bjdm.trim().equals("")))? bjdm :(vals != null ? vals[i] :""));
						} else {
							map.put(cols[i], vals != null ? vals[i] :"");
						}						
					}				
				}
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			
		} else if("modify".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			ksh = request.getParameter("ksh");
			String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sfjy","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","sftj","sfzsym","sfzh","cwh"};
			String[] vals=null;
			if(xydm!=null){
			 vals = yxgldao.getNewStuInfoByXydm(ksh,xydm, cols);
			}else{
			 vals = yxgldao.getNewStuInfo(ksh, cols);
			}
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
			request.setAttribute("modify", "modify");
		} 
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("sffsList", yxgldao.getSffsList());
		request.setAttribute("rs", map);
		
		map = this.getPicture(map, ksh, request);	
		if(!map.get("ksh").equalsIgnoreCase("")){
			return mapping.findForward("success");
		}else{
			return  new ActionForward("/yxgl/yxgl_err.jsp");
		}
	}
	
	/**
	 * ʳ�ñ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward yxglStbd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
//		 TODO �޸�ѧ��ʳ�ñ�����ͼ
		try{
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String xm = DealString.toGBK(yxform.getXm());
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"};
		String[] title_cn = dao.getColumnNameCN(title_en, "view_newstureportinfo");
		Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,xybd,yybd,stbd,ssbd from view_newstureportinfo where 1=1 ");
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		String go = request.getParameter("go");
		{//��ģ��ִ��ɾ������
			String active = request.getParameter("active");
			if("delete".equalsIgnoreCase(active)){
				ksh = request.getParameter("ksh");
				String dors = "";
				if(subDao.doStBdDelete(ksh)){
					dors = "yes"; 
				} else { 
					dors= "no";
				}
				request.setAttribute("dors", dors);
				go="go";
			}
		}
		Vector<String[]> rs = new Vector<String[]>();    	
		if("go".equalsIgnoreCase(go)){
			rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[]{}, new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"}));
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			request.setAttribute("rs", rs);
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_stbd.do"))?"yes" : "no";
		}
		
		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}
		yxform.setKsh("");
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		if(new YxglDAO().isOkTime(StandardOperation.getXxdm()).equalsIgnoreCase("no")){
			request.setAttribute("tag", "no");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		return mapping.findForward("success");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward yxglStbdOne(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		// TODO ���ѧ����ʳ�ñ��������ӡ��޸ĺ�ɾ��
		YxglActionForm yxglForm = (YxglActionForm) form;
		YxglDAO yxgldao = new YxglDAO();
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
		String ksh  = yxglForm.getKsh();
		String xh   = yxglForm.getXh();
		String xydm = yxglForm.getXydm();
		String zydm = yxglForm.getZydm();
		String bjdm = yxglForm.getBjdm();
		String dors = "";
		HttpSession session = request.getSession();
		if(xydm==null && "xy".equalsIgnoreCase(session.getAttribute("userType").toString())){
			xydm = session.getAttribute("userDep").toString();
		}
		HashMap<String, String> map = new HashMap<String, String>();
		if(active == null){
			String[] tablecol = {"ksh","xh",};//ʳ�ñ������ֶ�
			String[] values = {ksh,xh};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ��������ʳ�ñ�����Ϣ    			
					if(StandardOperation.insert("stbdzcb",tablecol,values, request)){
						dors = "ok";
					} else{
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
//					 TODO �޸�����ʳ�ñ�����Ϣ
					if(StandardOperation.update("stbdzcb",tablecol,values, "ksh", ksh, request)){
						dors = "ok";
					} else {
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else {
					// TODO �ڶ�ȡ��������ѧ���Ŀ�����֮�󣬻�ȡ����������Ϣ
					String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm"};
					String[] vals = yxgldao.getNewStuEateryInfo(ksh, cols);
					for(int i=0;i<cols.length;i++){
						if(cols[i].equalsIgnoreCase("xydm")){
							map.put(cols[i], (xydm != null && !(xydm.trim().equals("")))? xydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("zydm")){
							map.put(cols[i], (zydm != null && !(zydm.trim().equals("")))? zydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("bjdm")){
							map.put(cols[i], (bjdm != null && !(bjdm.trim().equals("")))? bjdm :(vals != null ? vals[i] :""));
						} else {
							map.put(cols[i], vals != null ? vals[i] :"");
						}						
					}
				}
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			
		} else if("modify".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			ksh = request.getParameter("ksh");
			String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","sftj","sfzsym","cwh"};
			String[] vals=null;
			if(xydm!=null){
			 vals = yxgldao.getNewStuInfoByXydm(ksh,xydm, cols);
			}else{
			 vals = yxgldao.getNewStuInfo(ksh, cols);
			}
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
			request.setAttribute("modify", "modify");
		} 
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("rs", map);
		
		map = this.getPicture(map, ksh, request);		
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		request.setAttribute("sffsList", yxgldao.getSffsList());
		if(!map.get("ksh").equalsIgnoreCase("")){
			return mapping.findForward("success");
		}else{
			return  new ActionForward("/yxgl/yxgl_err.jsp");
		}
	}
	
	/**
	 * ���ᱨ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward yxglSsbd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
     //TODO �޸�ѧ�����ᱨ����ͼ
		try{
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String xm = DealString.toGBK(yxform.getXm());
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"};
		String[] title_cn = dao.getColumnNameCN(title_en, "view_newstureportinfo");
		Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,xybd,yybd,stbd,ssbd from view_newstureportinfo where 1=1 ");
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		String go = request.getParameter("go");
		{//��ģ��ִ��ɾ������
			String active = request.getParameter("active");
			if("delete".equalsIgnoreCase(active)){
				ksh = request.getParameter("ksh");
				String dors = "";
				if(subDao.doStBdDelete(ksh)){
					dors = "yes"; 
				} else { 
					dors= "no";
				}
				request.setAttribute("dors", dors);
				go="go";
			}
		}
		Vector<String[]> rs = new Vector<String[]>();    	
		if("go".equalsIgnoreCase(go)){
			rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[]{}, new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"}));
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			request.setAttribute("rs", rs);
			flag++;
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_ssbd.do"))?"yes" : "no";
		}
		
		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}
		yxform.setKsh("");
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		if(new YxglDAO().isOkTime(StandardOperation.getXxdm()).equalsIgnoreCase("no")){
			request.setAttribute("tag", "no");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward yxglSsbdOne(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		// TODO ���ѧ�������ᱨ�������ӡ��޸ĺ�ɾ��
		YxglActionForm yxglForm = (YxglActionForm) form;
		YxglDAO yxgldao = new YxglDAO();
		String doType = request.getParameter("doType");
		String active = request.getParameter("active");
		String ksh  = yxglForm.getKsh();
		String xh   = yxglForm.getXh();
		String xydm = yxglForm.getXydm();
		String zydm = yxglForm.getZydm();
		String bjdm = yxglForm.getBjdm();
		String dors = "";
		String xxdm = StandardOperation.getXxdm();
		HttpSession session = request.getSession();
		if(xydm==null && "xy".equalsIgnoreCase(session.getAttribute("userType").toString())){
			xydm = session.getAttribute("userDep").toString();
		}
		HashMap<String, String> map = new HashMap<String, String>();
		if(active == null){
			String[] tablecol = {"ksh","xh",};//ʳ�ñ������ֶ�
			String[] values = {ksh,xh};
			try{
				if("add".equalsIgnoreCase(doType)){
					// TODO ��������ѧԺ������Ϣ    			
					if(StandardOperation.insert("ssbdzcb",tablecol,values, request)){
						dors = "ok";
					} else{
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else if("modify".equalsIgnoreCase(doType)){
					// TODO �޸�����ѧԺ������Ϣ
					if(StandardOperation.update("ssbdzcb",tablecol,values, "ksh", ksh, request)){
						dors = "ok";
					} else {
						dors = "no";
					}
					request.setAttribute("dors", dors);
				} else {
					// TODO �ڶ�ȡ��������ѧ���Ŀ�����֮�󣬻�ȡ����������Ϣ
					String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm"};
					String[] vals = yxgldao.getNewStuDomInfo(ksh, cols);
					for(int i=0;i<cols.length;i++){
						if(cols[i].equalsIgnoreCase("xydm")){
							map.put(cols[i], (xydm != null && !(xydm.trim().equals("")))? xydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("zydm")){
							map.put(cols[i], (zydm != null && !(zydm.trim().equals("")))? zydm :(vals != null ? vals[i] :""));
						} else if(cols[i].equalsIgnoreCase("bjdm")){
							map.put(cols[i], (bjdm != null && !(bjdm.trim().equals("")))? bjdm :(vals != null ? vals[i] :""));
						} else {
							map.put(cols[i], vals != null ? vals[i] :"");
						}						
					}	
				}
			} catch(Exception e){
				dors = "no";
				request.setAttribute("dors", dors);
			}

		} else if("add".equalsIgnoreCase(active)){
			// TODO ��ʾ��ҳ�棬��������ѧ��������Ϣ
			
		} else if("modify".equalsIgnoreCase(active)){
			// TODO  ��ʾѧ���޸�ҳ��
			ksh = request.getParameter("ksh");
			String[] cols = {"ksh","xh","xm","xydm","zydm","bjdm","sflsj","sfmc","ldh","qsh","xybd","stbd","yybd","ssbd","ysqsf","ysxf","sjqsf","sjxf","sffs","sftj","sfzsym","sfzh","cwh"};
			String[] vals=null;
			if(xydm!=null){
			 vals = yxgldao.getNewStuInfoByXydm(ksh,xydm, cols);
			}else{
			 vals = yxgldao.getNewStuInfo(ksh, cols);
			}
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], (vals!=null)? vals[i]:"");
			}
			request.setAttribute("modify", "modify");
		} 
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xyList", yxgldao.getNewXyList());
		request.setAttribute("zyList", yxgldao.getNewZyList(xydm));
		request.setAttribute("bjList", yxgldao.getNewBjList(xydm, zydm));
		request.setAttribute("sffsList", yxgldao.getSffsList());
		request.setAttribute("rs", map);
		
		map = this.getPicture(map, ksh, request);	
		if(!map.get("ksh").equalsIgnoreCase("")){
			return mapping.findForward("success");
		}else{
			return  new ActionForward("/yxgl/yxgl_err.jsp");
		}
	}
	
	/**
	 * ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward yxglXsjf(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
//		 TODO �޸�ѧ��������ͼ
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String xm = DealString.toGBK(yxform.getXm());
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","ysqsf","sjqsf","ysxf","sjxf","sffs"};
		String[] title_cn = dao.getColumnNameCN(title_en, "view_newstureportinfo");
		Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,ysqsf,sjqsf,ysxf,sjxf,(case(sffs) when '1' then '���' when '2' then '�ֽ�' when '3' then 'ˢ��' end) sffs from view_newstureportinfo where 1=1 ");
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		String go = request.getParameter("go");

		Vector<String[]> rs = new Vector<String[]>();    	
		if("go".equalsIgnoreCase(go)){
			rs.addAll(dao.rsToVator(sql_querry_condition.toString(), new String[]{}, new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","ysqsf","sjqsf","ysxf","sjxf","sffs"}));
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			request.setAttribute("rs", rs);
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_xsjf.do"))?"yes" : "no";
		}
		
		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}		
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		return mapping.findForward("success");
	}
	/**
	 * ��ɫͨ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	private ActionForward yxglLstd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		 TODO �޸�ѧ����ɫͨ����ͼ
		try{
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		YxglActionForm yxform = (YxglActionForm) form;
		DAO dao = DAO.getInstance();
		YxglDAO subDao = new YxglDAO();
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		String bjdm = yxform.getBjdm();
		String xh = yxform.getXh();
		String sfblLstd = yxform.getSfblLstd();
		String xm = DealString.toGBK(yxform.getXm());
		
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			if( request.getParameter("xydm")!=null){
				userDep =  request.getParameter("xydm");
			}else{
				userDep	= " ";
			}
		}
		if(userType.equalsIgnoreCase("xy") && (xydm == null || xydm.equalsIgnoreCase(""))){
			xydm = userDep;
			yxform.setXydm(xydm);
		}
		
		
		String xxdm = StandardOperation.getXxdm();
		yxform.setXm(xm);
		String ksh = yxform.getKsh();
		String[] title_en = {"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","cjrq","hyjqsf","hyjxf"};
		StringBuffer sql_querry_condition = new StringBuffer("select primaryKey,ksh,xh,xm,xymc,zymc,bjmc,cjrq,hyjqsf,hyjxf from view_newstulstdinfo where 1=1 ");
		sql_querry_condition.append(makeCondition(xydm, zydm, bjdm, xh, xm,ksh));
		String go = request.getParameter("go");
		{//��ģ��ִ��ɾ������
			String active = request.getParameter("active");
			if("delete".equalsIgnoreCase(active)){
				ksh = request.getParameter("pk");
				String dors = "";
				boolean del = StandardOperation.delete("lstdxxb","ksh",ksh, request);
				if(del){
					dors = "yes"; 
				} else { 
					dors= "no";
				}
				request.setAttribute("dors", dors);
				go="go";
			}
		}
		Vector<String[]> rs = new Vector<String[]>();	
		if("go".equalsIgnoreCase(go)){
			//���ݻ�������[on/off]�ж�
			if(sfblLstd.equalsIgnoreCase("on")){
				sql_querry_condition.append(" and cjrq is not null"); 
			}else if(sfblLstd.equalsIgnoreCase("off")){
				sql_querry_condition.append(" and cjrq is null");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)){ 
			//���Ϲ�ҵ��ѧ  ��ɫͨ��ֻ��������ѧ���ϣ�������ҳ������ʾ������ס�޷ѡ����ֶ�
				title_en = new String[]{"primaryKey","ksh","xh","xm","xymc","zymc","bjmc","cjrq","hyjxf"};
			}	
			rs.addAll(dao.rsToVator(
					sql_querry_condition.toString(), 
					new String[]{}, 
					title_en));
			String[] title_cn = dao.getColumnNameCN(title_en, "view_newstulstdinfo");
			Vector<HashMap<String, String>> tit = StandardOperation.getVector(title_en, title_cn);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", tit);
			request.setAttribute("rs", rs);
		}
		String writeAble = "";
		if("teacher".equalsIgnoreCase(userOnLine)){
			writeAble = (CheckPower.checkUsrPower(userName, "yxgl_lstd.do"))?"yes" : "no";
		}
		if(flag != 0 ){
			request.setAttribute("onload", "no");
			flag = 0;
		} else{
			request.setAttribute("onload", "yes");
		}		
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", subDao.getNewXyList());
		request.setAttribute("zyList", subDao.getNewZyList(xydm));
		request.setAttribute("bjList", subDao.getNewBjList(xydm, zydm));
		request.setAttribute("sfblLstdList", subDao.getSfblLstdList());
		if(new YxglDAO().isOkTime(StandardOperation.getXxdm()).equalsIgnoreCase("no")){
			request.setAttribute("tag", "no");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * �趨ӭ��ʱ��Ŀ�ʼʱ��ͽ���ʱ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */

	private ActionForward yxgl_init_time(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		YxglActionForm yxform = (YxglActionForm) form;
		String fromDate = yxform.getFromDate();
		String toDate = yxform.getToDate();
		yxform.setFromDate(fromDate);
		yxform.setToDate(toDate);
		YxglDAO yxglDao = new YxglDAO();
		boolean message = false;
		if(!StringUtils.isNull(request.getParameter("doType"))){
			message = yxglDao.saveInitTime(fromDate, toDate); //save
			if(message){
				request.setAttribute("message", "ok");
			}else{
				request.setAttribute("message", "no");
			}
			request.setAttribute("rs",yxform);
		}else{
			request.setAttribute("rs", yxglDao.getInitTime());
		}
		return mapping.findForward("success");
	}
	
	/**�����ʲ�ѯ  ��Ժ��Ϲ�ҵ��ѧ*/
	private ActionForward percentOfBdSearch(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		//�õ���������
//		String [] bdzl = request.getParameterValues("checkbox");
//		String[] bdzlColumns = new String[]{"xy","xyy","st","ss"};
//		List<HashMap<String,String>> bdzlList = new ArrayList<HashMap<String,String>>();
// 		String splitStr = "";
//		for(int i=0;i<bdzl.length;i++){
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put(bdzlColumns[i],bdzl[i]);
//			bdzlList.add(map);
//			if(i == 0){
//				splitStr +=  bdzl[i];
//			}else{
//				splitStr +=  ("-" + bdzl[i]);
//			}
// 		}
		YxglActionForm yxform = (YxglActionForm) form;
		String xydm = yxform.getXydm();
		String zydm = yxform.getZydm();
		YxglDAO yxglDao = new YxglDAO();
		DAO dao = DAO.getInstance();
		String xxdm = request.getParameter("xxdm");
		request.setAttribute("xyList", yxglDao.getNewXyList());
		request.setAttribute("zyList", yxglDao.getNewZyList(xydm));
		request.setAttribute("bjList", yxglDao.getNewBjList(xydm, zydm));
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("sfList", yxglDao.getSfList());
		request.setAttribute("bgzlList", yxglDao.getBgzlList_HENAN());//���Ϲ�ҵ��ѧ�ı�������
		request.setAttribute("ldList", yxglDao.getLdmcList());        //¥������
		request.setAttribute("rs",yxglDao.getSchoolBdSearch());       //ѧУ��ͳ��
		request.setAttribute("rs2",yxglDao.getXyBdSearch());		  //����ѧԺ��ͳ��
		request.setAttribute("percentOfBdSearch", "true");
		request.setAttribute("xxdm", xxdm);
		
		//request.setAttribute("bdzlListStr", splitStr);
		return mapping.findForward("success");
	}
	
	/**����ѧԺ���뷵��ѧ��������  ��Ժ��Ϲ�ҵ��ѧ*/
	private ActionForward getStuDataByXy(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		DAO dao = new DAO();
		YxglDAO yxglDao = new YxglDAO();
		List stuList = null;
		String tag = request.getParameter("tag");
		String expType = request.getParameter("expType");
		String[] output = new String[]{"ksh","xm","xb","nj","xymc","zymc","bjmc","xybd","yybd","stbd","ssbd"};
		if(!StringUtils.isNull(request.getParameter("school"))){  //whole school			
			request.setAttribute("printName",yxglDao.getXxmc());
			if(expType.equalsIgnoreCase("exp")){  //����		
				stuList = yxglDao.getTotalDataVec(tag,output);
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				String[] colListCN = dao.getColumnNameCN(output,"VIEW_NEWSTUREPORTINFO");
				Excel2Oracle.exportDataFor((ArrayList<Object>)stuList, colListCN, response.getOutputStream());
				return mapping.findForward("exp");
			}else if(expType.equalsIgnoreCase("print")){  //��ӡ
				stuList = (ArrayList<HashMap<String,String>>)yxglDao.getTotalStuData(tag);
			}
			request.setAttribute("rs",stuList);  
			request.setAttribute("rsNum",stuList.size());
		}else{
			String xydm = request.getParameter("xydm");
			if(expType.equalsIgnoreCase("exp")){  //����		
				stuList = yxglDao.getXyDataVec(xydm, tag, output);
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				String[] colListCN = dao.getColumnNameCN(output,"VIEW_NEWSTUREPORTINFO");
				Excel2Oracle.exportDataFor((ArrayList<Object>)stuList, colListCN, response.getOutputStream());
				return mapping.findForward("exp");
			}else if(expType.equalsIgnoreCase("print")){  //��ӡ
				stuList = (ArrayList<HashMap<String,String>>)yxglDao.getStuDataByXydm(xydm,tag);
			}
			request.setAttribute("printName", yxglDao.getXymcByXydm(xydm));
			request.setAttribute("rs",stuList);
			request.setAttribute("rsNum",stuList.size());
		}
		request.setAttribute("topTr", yxglDao.getStuTopTr());
		return mapping.findForward("success");
	}
	
	/**
	 * ��Ժ��Ϲ�ҵ��ѧ  ��ӡ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward yxglHngyXsbdd(ActionMapping mapping,ActionForm form,
	HttpServletRequest request,HttpServletResponse response)throws Exception{
		String ksh = request.getParameter("ksh");
		YxglDAO yxglDao = new YxglDAO();
		HashMap<String,String> stuInfo = yxglDao.getNewStuDetails_Print(ksh);  //û��kshʱ		
		stuInfo = this.getPicture(stuInfo, ksh, request);		
		request.setAttribute("rs", stuInfo);
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @param xydm
	 * @param zydm
	 * @param bjdm
	 * @param xh
	 * @param xm
	 * @return  �������ɵ�sql��������
	 */
	private String makeCondition(String xydm,String zydm,String bjdm,String xh,String xm,String ksh){
		StringBuffer sb = new StringBuffer();
		if(xydm != null && !("".equalsIgnoreCase(xydm.trim()))){
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("' ");
			flag++;
		}
		if(zydm != null && !("".equalsIgnoreCase(zydm.trim()))){
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("' ");
			flag++;
		}
		if(bjdm != null && !("".equalsIgnoreCase(bjdm.trim()))){
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("' ");
			flag++;
		}
		if(xh != null && !("".equalsIgnoreCase(xh.trim())) && Check_Input_Value.check2(xh)){
			sb.append(" and xh like '%");
			sb.append(xh);
			sb.append("%' ");
			flag++;
		}
		if(xm != null && !("".equalsIgnoreCase(xm.trim())) && Check_Input_Value.check2(xm)){
			sb.append(" and xm like '%");
			sb.append(xm);
			sb.append("%' ");
			flag++;
		}
		if(ksh != null && !("".equalsIgnoreCase(ksh.trim())) && Check_Input_Value.check2(ksh)){
			sb.append(" and ksh like '%");
			sb.append(ksh);
			sb.append("%' ");
			flag++;
		}
		return sb.toString();
	}
	
	private String makeQueryCondition(String xydm,String zydm,String bjdm,String bgzldm, String xb,String sfdm,
			String lddm,String qsh){
		StringBuffer sb = new StringBuffer();
		if(xydm != null && !("".equalsIgnoreCase(xydm.trim()))){
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("' ");
			flag++;
		}
		if(zydm != null && !("".equalsIgnoreCase(zydm.trim()))){
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("' ");
			flag++;
		}
		if(bjdm != null && !("".equalsIgnoreCase(bjdm.trim()))){
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("' ");
			flag++;
		}
		if(xb != null && !("".equalsIgnoreCase(xb.trim()))){
			sb.append(" and xb='");
			sb.append(xb);
			sb.append("' ");
			flag++;
		}
		if(sfdm != null && !("".equalsIgnoreCase(sfdm.trim()))){
			sb.append(" and sfdm='");
			sb.append(sfdm);
			sb.append("' ");
			flag++;
		}
		if(!StringUtils.isNull(lddm)){
			sb.append(" and lddm='");
			sb.append(lddm);
			sb.append("' ");
			flag++;
		}
		if(!StringUtils.isNull(qsh)){
			sb.append(" and qsh='");
			sb.append(lddm);
			sb.append("' ");
			flag++;
		}
		if(bgzldm != null && bgzldm.equalsIgnoreCase("")){
			sb.append(" and (xybd='��' or yybd='��' or stbd='��' or ssbd='��' ) ");
			sb.append(bgzldm);
			flag++;
		} 
		if(bgzldm != null && !("".equalsIgnoreCase(bgzldm.trim()))){
			sb.append(" group by ");
			sb.append(bgzldm);
			flag++;
		}
		
		return sb.toString();
	}
	
	private HashMap<String,String> getPicture(HashMap<String,String> map,String ksh,HttpServletRequest request){
		String[] id = new String[]{ksh,map.get("xh"),map.get("sfzh")};
		String dir = request.getSession().getServletContext().getRealPath("");
		String filepath = PictureUtil.getPicture(id, dir,"yxgl");
		map.put("picture",filepath);
		return map;
	}
	
	/**��ɽʦ��ά��������Ϣ*/
	private ActionForward getBdzcInfo(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		DAO dao = DAO.getInstance();
		YxglDAO yxglDao = new YxglDAO();;
		YxglActionForm yxform = (YxglActionForm) form;
		List<String[]> rs = new ArrayList<String[]>();
		String tips = "��ǰ����λ�ã�ӭ�¹��� - ����ע��";
		String rsNum = "";
		String flag = "";
		String[] colList = new String[] {"pk","xh","xn","xqmc","xm","xymc","zymc","bjmc","sfbd","bdqk","sfzc","zcqk"};
		String[] colListCN = new String[] {"����","ѧ��","ѧ��","ѧ��","����",Base.YXPZXY_KEY+"����","רҵ����","�༶����","�Ƿ񱨵�","�������","�Ƿ�ע��","ע�����" };
		List topTr = dao.arrayToList(colList, colListCN); 
		if(!Base.isNull(yxform.getXh())){
			yxform.setXh(yxform.getXh().trim());
		}
		if ((yxform.getDoType() != null) && (yxform.getDoType().equals("save")||yxform.getDoType().equals("del"))) {
			flag = yxglDao.checkXh(yxform);
			if(flag.equals("yes")){
				request.setAttribute("result", yxglDao.xsBdzc(yxform));
			}else{
				request.setAttribute("check", flag);
			}
			rs = yxglDao.queryXsBdzcInfo(yxform,colList);		
		}
		if ((yxform.getDoType() != null) && yxform.getDoType().equals("query")) {
			rs = yxglDao.queryXsBdzcInfo(yxform,colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if((yxform.getDoType() != null) && yxform.getDoType().equals("xydm")){
			yxform.setZydm("");
			yxform.setDoType("");
		}
		yxform.setXh("");
		if(yxform.getNj()== null){
			yxform.setNj(Base.currNd);
		}
		if(yxform.getXn()== null){
			yxform.setXn(Base.currXn);
		}
		if(yxform.getXq()== null){
			yxform.setXq(Base.currXq);
		}
		request.setAttribute("tableName", "view_yxgl_xsbdzcxx");
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", tips);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("xnList", yxglDao.getNoNullXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("bdList", yxglDao.getselectInfo("bdqkb"));
		request.setAttribute("zcList", yxglDao.getselectInfo("zcqkb"));
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(yxform.getXydm()));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(yxform.getXydm(), yxform.getZydm(), yxform.getNj()));// ���Ͱ༶�б�
		return mapping.findForward("success");
	}
	
	/**��ɽʦ������ѧ������ע����Ϣ*/
	private ActionForward getOneBdzcInfo(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		YxglDAO yxglDao = new YxglDAO();
		YxglActionForm yxform = (YxglActionForm) form;
		String tips = "��ǰ����λ�ã�ӭ�¹��� - ����ע�� - ��ϸ��Ϣ";
		String flag = "";
		if(!Base.isNull(yxform.getXh())){
			yxform.setXh(yxform.getXh().trim());
		}
		if ((yxform.getDoType() != null) && yxform.getDoType().equals("save")) {
			request.setAttribute("result", yxglDao.saveOneBdzcInfo(yxform));
		}else{
			flag = yxglDao.checkXh(yxform);
			if(!flag.equals("yes")){
				request.setAttribute("check", flag);
			}
		}
		HashMap<String,String> map = yxglDao.getOneBdzcInfo(yxform); 
		request.setAttribute("rs", map); 
		request.setAttribute("tips", tips);
		request.setAttribute("bdList", yxglDao.getselectInfo("bdqkb"));
		request.setAttribute("zcList", yxglDao.getselectInfo("zcqkb"));
		return mapping.findForward("success");
	}
	
	private ActionForward yxglExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YxglDAO yxglDao = new YxglDAO();
		YxglActionForm yxform = (YxglActionForm) form;
		String tableName = request.getParameter("tableName");
		String[] colCN = null;
		String sql = "";
		if("view_yxgl_xsbdzcxx".equals(tableName)){
			colCN = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","�Ƿ񱨵�","�������","�Ƿ�ע��","ע�����","��ע"};
			sql = yxglDao.getSql(yxform);
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(sql, colCN, response.getOutputStream(),"����ע��");
		return mapping.findForward("success");
	}
}

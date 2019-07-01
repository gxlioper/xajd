package xgxt.pjpy.ghxy.qsryf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.xsgygl.ghxy.qyfdywh.GhxyQyfdywhDao;
import xgxt.xsgygl.ghxy.qyfdywh.GhxyQyfdywhService;

import com.zfsoft.basic.BasicService;

public class GhxyQsryfService {
	
	public void getQsList(HttpServletRequest request,GhxyQsryfForm form,
			String pc,String xn,String xq,String userName) throws Exception{
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		GhxyQsryfService ghxyBjryfService=new GhxyQsryfService();
		//������Ϣ����
		List<String[]>map=ghxyQsryfDao.getQsList(form,userName);
		
		//���ؽ������¼��
		request.setAttribute("rsNum", map.size());
		//����ָ��List
		List<HashMap<String,String>> list = ghxyQsryfDao.getQszb();
		
		String[] colList = new String[4];
		String[] colListCN = new String[4];
		if (list != null && list.size() > 0) {
			colList = new String[list.size()+4];
			colListCN = new String[list.size()+4];
			colList[0]="ldmc";
			colListCN[0]="¥������";
			colList[1]="cs";
			colListCN[1]="����";
			colList[2]="qsh";
			colListCN[2]="���Һ�";
			colList[3]="qsh";
			colListCN[3]="���Һ�";
			int m=0;
			for (int i = 3; i < list.size()+3; i++) {
				colList[i]=list.get(i-3).get("zbdm");
				colListCN[i]=list.get(i-3).get("zbnr");
				m=i;
			}
			colList[m+1]="td";
			colListCN[m+1]="��ʾ��Ϣ";
		}
		
		DAO dao = DAO.getInstance();
		//���ؽ�����ı�ͷ
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		List<String[]>rs= ghxyBjryfService.getZblb(map,list,pc,xn,xq);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs",rs);
		if(rs.size()>0){
		 request.setAttribute("length", rs.get(0).length-1);
		}
	}
	
	
	public void ryfPcList(HttpServletRequest request){
		GhxyQsryfDao dao=new GhxyQsryfDao();
		request.setAttribute("ryfPcList", dao.getRyfpc());
	}
	/**
	 * �༶��������������
	 * @throws Exception 
	 *
	 */
	public void plSave(HttpServletRequest request,GhxyQsryfForm form,String dgpc) throws Exception{
		GhxyQsryfModel ghxyQsryfModel=new GhxyQsryfModel();
		GhxyQsryfService ghxyQsryfService=new GhxyQsryfService();
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//�������ݲ��� �ı���
		String realTable ="ghxy_qsryfb";
		
		//������ѡ�����Ϣ������
		String[] checkVal = form.getCheckVal();
		
		String[]xmfz=form.getXmfz();
		
		String[]zbdms=ghxyQsryfService.getZbdm();
		
		int length=zbdms.length;
		//�жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		//�������Ӽ�ȡ��
		
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xn || xq || pc  || plssbh  ";
			String[] arrzd = new String[] {"xn","xq","pc","plssbh","xmdm","xmfz"};
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
				
			
				ghxyQsryfModel.setPlssbh(xhArr);
				ghxyQsryfModel.setXn(xn);
				ghxyQsryfModel.setXq(xq);
				ghxyQsryfModel.setPc(pc);
				ghxyQsryfModel.setXmdm(zbdm);
				ghxyQsryfModel.setXmfz(xmfzArr);
				
				reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyQsryfModel);
				request.setAttribute("result", reslut);
			
			reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyQsryfModel);
			request.setAttribute("result", reslut);
			
		}
	}
	
	/**
	 * �༶��������˱���������
	 * @throws Exception 
	 * 
	 */
	public void shbSave(HttpServletRequest request,GhxyQsryfForm form,String dgpc) throws Exception{

		GhxyQsryfModel ghxyQsryfModel=new GhxyQsryfModel();
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//�������ݲ��� �ı���
		String realTable ="ghxy_qsryfshb";
		
		String []pkValue=form.getPrimarykey_cbv();
		
		String []xn=form.getXnArr();
		String []xq=form.getXqArr();
		//�����ѯ����������Ϣ��XH����
		String[]xnin=new String[xn.length];
		String[]xqin=new String[xq.length];
		
		String[] plbjdm = form.getPlssbh();
		//������ѡ�����Ϣ������
		String[] checkVal = form.getCheckVal();

		//�жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		//�������Ӽ�ȡ��
		
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xn || xq ||plssbh ||pc   ";
			String[] arrzd = new String[] {"xn","xq","pc","plssbh"};
		
			String[] sxh = new String[checkVal.length];
			int m = 0;
			for (int i = 0; i < checkVal.length; i++) {
				if (plbjdm != null && plbjdm.length > 0) {
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
				String[] xqArr = new String[m];
				String[] xnArr = new String[m];
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
				
			
				ghxyQsryfModel.setPlssbh(xhArr);
				ghxyQsryfModel.setXn(xnArr);
				ghxyQsryfModel.setXq(xqArr);
				ghxyQsryfModel.setPc(pc);
				reslut = ghxyNjzrwhService.saveTyxx(saveForm, ghxyQsryfModel);
				request.setAttribute("result", reslut);
			
		
			
		}
	}
	
	
	/**
	 * ��ȡָ��List
	 */
	public List<HashMap<String,String>>zbList(){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		return ghxyQsryfDao.getQszb();
	}
	
	/**
	 * ��ȡָ�����
	 * getZbdm
	 */
	public String[] getZbdm(){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		List<HashMap<String,String>>list=ghxyQsryfDao.getQszb();
		String[] zbdm=new String[list.size()];
		for(int i=0;i<list.size();i++){
			zbdm[i]=list.get(i).get("zbdm");
		}
		return zbdm;
	}
	
	/**
	 * list �����
	 * listMap ��ͷ��Ϣ
	 * @param list
	 * @param listMap
	 */
	public List getZblb(List<String[]>list,List<HashMap<String,String>>listMap,String pc,
			String xn,String xq){
		
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		StringBuilder sql =new StringBuilder();
		List textArray=new ArrayList();
		
		//ƴ�Ӳ�ѯghxy_qsryfb���ݵ���䣨���ݰ༶��ѯ��
		sql.append("select ldmc,cs,qsh,plssbh,xmdm,xmfz,pc,xn,xq from view_ghxy_qsryf where 1=1");
		for(int i=0;i<list.size();i++){
			String[]bjStr=list.get(i);
			sql.append(" or plssbh = '");
			sql.append(bjStr[0]);
			sql.append("'");
		}
		
		//��ȡ��������������б�
		List<HashMap<String,String>> zblb=ghxyQsryfDao.getZblb(sql.toString());
		//lengthΪJSP�ϵ���������Ҫ��ŵ�TEXT����
		String[]str=new String[list.size()*listMap.size()];
		boolean blog=false;
		int f=0;
		for(int i=1;i<=list.size();i++){
			String[]strs=list.get(i-1);
			for(int j=1;j<=listMap.size();j++){
				HashMap hashMap=listMap.get(j-1);
				for(int m=0;m<zblb.size();m++){
					if(strs[0].equalsIgnoreCase(zblb.get(m).get("plssbh"))
							&& hashMap.get("zbdm").equals(zblb.get(m).get("xmdm"))
							&& pc.equals(zblb.get(m).get("pc"))
							&& xn.equals(zblb.get(m).get("xn"))
							&& xq.equals(zblb.get(m).get("xq"))){
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
		//���ؽ����
		int s=0;
		int m=0;
		for(int i=0;i<list.size();i++){
			String []array=new String[listMap.size()+4];
			array[0]=list.get(i)[0];
			array[1]=list.get(i)[1];
			array[2]=list.get(i)[2];
			array[3]=list.get(i)[3];	
			for(int j=0; j<listMap.size();j++){
					m=0;
						array[j+4]=str[s];
						s++;
						m=j+4;
			}
			array[m]=list.get(i)[4];	
			textArray.add(array);
		}
		return textArray;
	}
	
	
	/**
	 * ��ȡ�༶�ļӷ���Ϣ
	 */
	public void getBjjfxx(HttpServletRequest request,String bjdm){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		BasicService basicService=new BasicService();
		List<String[]> bjjfxx=ghxyQsryfDao.getBjjfxx(bjdm);
		request.setAttribute("map", bjjfxx);
		if(bjjfxx.size()>0){
			request.setAttribute("rsNum", bjjfxx.size());
		}
		request.setAttribute("topTr", basicService.getTopTr("view_rcsw_kqgl_xskq",new String[]{"ѧ��","ѧ��","��������","¥������","��������","ָ������","ָ���ֵ"}));
	}
	
	/**
	 * ���������Ϣ
	 * 
	 */
	public HashMap<String,String> getShxx(String userType,String userName,String shjg,String djdm){
		HashMap<String,String> hashMap=new HashMap<String,String>();
		GhxyQyfdywhService ghxyQyfdywhService=new GhxyQyfdywhService();
		String times=GetTime.getNowTime2();
		//�Ƿ����꼶����Ա
		if(ghxyQyfdywhService.isQyfdy(userName)){
			hashMap.put("qyfdysh", shjg);
			hashMap.put("qyfdyshsj",times);
			hashMap.put("djdm",djdm);
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			hashMap.put("xxsh", shjg);
			hashMap.put("xxshsj",times);
		}
		
		return hashMap;
	}
	
	/**
	 * ������Ϣ
	 * 
	 */
	public void getReader(HttpServletRequest request,
			String userType,String userName){
		GhxyQyfdywhService ghxyQyfdywhService=new GhxyQyfdywhService();
		if(ghxyQyfdywhService.isQyfdy(userName)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
			request.setAttribute("annexTerm", " and qyfdysh='ͨ��' ");
		}else{
			request.setAttribute("clientColumns", "('disabled')disabled,");
		}
		
	}
	/**
	 * 
	 * @param request
	 * @param userType
	 * @param userName
	 */
	public String getReader(String userName,String userType){
		GhxyQyfdywhService qyfdyService =new GhxyQyfdywhService();
		String disabled="";
		if(qyfdyService.isQyfdy(userName)){
			disabled="(select (case xxsh when 'ͨ��' then 'daisabled' else ''end) disabled,b.* from ghxy_qsryfshb b where b.plssbh=a.plssbh and b.xn=a.xn and b.xq=a.xq and b.pc=a.pc)disabled,";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			disabled="''disabled";
		}else{
			disabled="(select (case qyfdysh when 'ͨ��' then 'disabled' else '' end)disabled from ghxy_qsryfshb b where b.plssbh=a.plssbh and b.xn=a.xn and b.xq=a.xq and b.pc=a.pc)disabled,";
		}
		return disabled;
	}
	
	/**
	 * �༶�����ֵȼ�List
	 */
	public void ryfdjList(HttpServletRequest request){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		request.setAttribute("ryfdjList",ghxyQsryfDao.ryfdjList());
	}
	
	/**
	 * �����༶
	 * @param request
	 */
	public void getBlszxx(HttpServletRequest request){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		BasicService basicService=new BasicService();
		List<String[]> bjjfxx=ghxyQsryfDao.getBlszxx();
		request.setAttribute("map", bjjfxx);
		request.setAttribute("rsNum", bjjfxx.size());
		request.setAttribute("topTr", basicService.getTopTr("view_ghxy_qsryfbl",new String[]{"�ȼ�����","�ȼ�����","�ӷ�","�༶����"}));
	}
	
	/**
	 * ������꼶���ε�½�жϸ��ȼ��༶��������(�û������ȼ����룬����)
	 * 
	 */
	public boolean checkQss(String userName,String djdm,int sl,
			String nj,String xqqdm,String pc){
		GhxyQyfdywhService ghxyQyfdywhService=new GhxyQyfdywhService();
		GhxyQsryfService ghxyQsryfService=new GhxyQsryfService();
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		if(ghxyQyfdywhService.isQyfdy(userName)){
			//�꼶�ܰ༶��
			int zqss=ghxyQsryfService.getZQsByNj(nj,xqqdm);
			int rydj=ghxyQsryfService.getQsRyfSl(nj,djdm,pc)+sl;
			float djbl=(float)rydj/(float)zqss*100;
			return ghxyQsryfDao.checkDjSl(djdm,djbl);
		}else{
			return true;
		}
	}	
	
	/**
	 * ��ȡ�꼶�ܰ༶��
	 */
	public int getZQsByNj(String nj,String xqqdm){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		List <HashMap<String,String>>list=ghxyQsryfDao.getZQsByNj(nj,xqqdm);
		HashMap<String,String> hashMap=list.get(0);
		String zqss=hashMap.get("zqss");
		return Integer.parseInt(zqss);
	}
	
	/**
	 * ���������ȼ���ȡ�༶�����ȼ�����
	 */
	public int getQsRyfSl(String nj,String djdm,String pc){
		GhxyQsryfDao ghxyQsryfDao=new GhxyQsryfDao();
		List <HashMap<String,String>>list=ghxyQsryfDao.getQsRyfSl(nj, djdm,pc);
		HashMap<String,String> hashMap=list.get(0);
		String rydj=hashMap.get("rydj");
		return Integer.parseInt(rydj);
	}
	
	/**
	 * ��ȡѧ��List
	 */
	public void yqqList(HttpServletRequest request){
		GhxyQsryfDao ghxysryfDao=new GhxyQsryfDao();
		request.setAttribute("yqqdmList",ghxysryfDao.yqqList());
		
	}
	
	/**
	 * ���ѧԺ���������ֲ�ѯ
	 * @return 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * 
	 */
	public void selectQsryf(HttpServletRequest request,GhxyQsryfForm qsryfForm,String userType,
			String userName,String isFdy,String userDep) throws Exception{

		QueryModel queryModel=new QueryModel();
		BasicService basicService =new BasicService();
		String[]yhqxfp=qsryfForm.getYhqxfp();
		boolean blog=false;
		StringBuilder sql=new StringBuilder();
		sql.append("select rownum r,a.* from(");
		if(!"admin".equalsIgnoreCase(userType)
				&& !"xx".equalsIgnoreCase(userType)
				&& !"stu".equalsIgnoreCase(userType)){
			for(int i=0;i<yhqxfp.length;i++){
				if("gyfdy".equalsIgnoreCase(yhqxfp[i])){//���Ҹ���Ա
					if(blog){
						sql.append(" union ");
					}
					sql.append("select xn||xq||pc||plssbh||xmdm pkValue,a.* from view_ghxy_qsryf a  where exists");
					sql.append("(select 1 from view_gyfdyxx b,view_ssxx c where b.yhm='"+userName+"' and a.lddm=b.lddm and b.lddm=c.lddm and a.qsh=c.qsh)");
					blog=true;
				}else if("xy".equalsIgnoreCase(yhqxfp[i])){//ѧԺ
					if(blog){
						sql.append(" union ");
					}
					sql.append("select xn||xq||pc||plssbh||xmdm pkValue,a.* from view_ghxy_qsryf a where exists");
					sql.append("(select 1 from view_xszsxx b where xydm='"+userDep+"' and a.lddm=b.lddm and a.xqqdm=b.xqdm and a.qsh=b.qsh)");
					blog=true;
				}else if("fdy".equalsIgnoreCase(yhqxfp[i])){//����Ա
					if(blog){
						sql.append(" union ");
					}
					sql.append("select xn||xq||pc||plssbh||xmdm pkValue,a.* from view_ghxy_qsryf a where exists");
					sql.append("(select 1 from(select * from fdybjb b,view_xszsxx c  where b.zgh='"+userName+"' and  b.bjdm=c.bjdm)b where a.lddm=b.lddm and a.xqqdm=b.xqdm and a.qsh=b.qsh)");
					blog=true;
				}else if("qyfdy".equalsIgnoreCase(yhqxfp[i])){
					if(blog){
						sql.append(" union ");
					}
					sql.append("select xn||xq||pc||plssbh||xmdm pkValue,a.* from view_ghxy_qsryf a where exists");
					sql.append("(select 1 from ghxy_qyfdyb b where a.yqqdm=b.yqqdm and zgh='"+userName+"')");
					blog=true;
				}
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			sql.append("select xn||xq||pc||plssbh||xmdm pkValue,a.* from view_ghxy_qsryf a");
		}else if("stu".equalsIgnoreCase(userType)){
			sql.append("select xn||xq||pc||plssbh||xmdm pkValue,a.* from view_ghxy_qsryf a ");
		}
		sql.append(")a");
		BeanUtils.copyProperties(queryModel,qsryfForm);
		MakeQuery makeQuery=new MakeQuery();
		String []colList={"xn","xq","nj","yqqdm","pc","lddm","qsh","cs"};
		makeQuery.makeQuery(colList, new String[]{}, queryModel);
		ArrayList<String[]>list= CommonQueryDAO.commonQuery(sql.toString(),makeQuery.getQueryString(),makeQuery.getInputList(),
				new String[]{"pkValue", "plssbh", "xn",
			"xqmc", "plssbh", "yqqmc", "ldmc", "nj", "qsh", "cs", "pcmc",
			"xmmc", "xmfz" },queryModel);
		request.setAttribute("rs", list);
		if(list.size()>0){
			request.setAttribute("rsNum", list.size());
		}
		request.setAttribute("topTr", basicService.getTopTr("view_ghxy_qsryf",new String[]{"���","","������","ѧ��","ѧ��","������","¥��","�꼶","���Һ�",
				"¥��","����","��Ŀ����","��Ŀ��ֵ"}));
	}
	
	/**
	 *�ж����ҵ��������Ƿ����
	 */
	public boolean checkQsryf(String userName,String userType,
			String ssbh,String pc,String xn,String xq){
		
		boolean blog=true;
		GhxyQyfdywhService qyfdyService=new GhxyQyfdywhService();
		GhxyQsryfDao ghxysryfDao=new GhxyQsryfDao();
		HashMap<String,String>hashMap= ghxysryfDao.checkQsryf(ssbh,pc,xn,xq);
		String qyfdysh="";
		String xxsh="";
		if(hashMap!=null){
			 qyfdysh=hashMap.get("qyfdysh");
			 xxsh=hashMap.get("xxsh");
		}
		//���Ҹ���Ա
		if(qyfdyService.isQyfdy(userName)){
			if("ͨ��".equalsIgnoreCase(xxsh)){
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
	
	
	public void getSsxx(HttpServletRequest request,String userType,String userName,
			String isFdy,String userDep){
		GhxyQsryfDao qsryfDao=new GhxyQsryfDao();
		GhxyQyfdywhService qyfdyService=new GhxyQyfdywhService();
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>ldlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>cslist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>qslist=new ArrayList<HashMap<String,String>>();
		if(qsryfDao.isGygly(userName)){//���Ҹ���Ա
			String ldsql="select distinct(lddm),ldmc from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?)";
			String cssql="select distinct(cs),lddm from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?)";
			String qssql="select distinct(qsh),lddm,cs from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?)";
			ldlist=dao.getList(ldsql, new String[]{userName}, new String[]{"lddm","ldmc"});
			cslist=dao.getList(cssql, new String[]{userName}, new String[]{"cs","cs"});
			qslist=dao.getList(qssql, new String[]{userName}, new String[]{"qsh","qsh"});
		}else if("xy".equalsIgnoreCase(userType)){//ѧԺ
			String ldsql="select distinct(lddm),ldmc from view_xszsxx where xydm=?";
			String cssql="select distinct(cs),lddm  from view_xszsxx where xydm=? ";
			String qssql="select distinct(qsh),lddm,cs from view_xszsxx where xydm=? order by qsh asc";
			ldlist=dao.getList(ldsql, new String[]{userDep}, new String[]{"lddm","ldmc"});
			cslist=dao.getList(cssql, new String[]{userDep}, new String[]{"cs","cs"});
			qslist=dao.getList(qssql, new String[]{userDep}, new String[]{"qsh","qsh"});
		}else if("true".equalsIgnoreCase(isFdy)){//����Ա
			String ldsql="select distinct(lddm),ldmc from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh=? )";
			String cssql="select distinct(cs),lddm from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh=? ) ";
			String qssql="select distinct(qsh),lddm,cs from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh=? )";
			ldlist=dao.getList(ldsql, new String[]{userName}, new String[]{"lddm","ldmc"});
			cslist=dao.getList(cssql, new String[]{userName}, new String[]{"cs","cs"});
			qslist=dao.getList(qssql, new String[]{userName}, new String[]{"qsh","qsh"});
		}else if(qyfdyService.isQyfdy(userName)){
			String ldsql="select distinct(lddm),ldmc from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh=? and a.yqdm=b.yqqdm )";
			String cssql="select distinct(cs),lddm from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh=? and a.yqdm=b.yqqdm )";
			String qssql="select distinct(qsh),lddm,cs from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh=? and a.yqdm=b.yqqdm )";
			ldlist=dao.getList(ldsql, new String[]{userName}, new String[]{"lddm","ldmc"});
			cslist=dao.getList(cssql, new String[]{userName}, new String[]{"cs","cs"});
			qslist=dao.getList(qssql, new String[]{userName}, new String[]{"qsh","qsh"});
		}
		request.setAttribute("ldList", ldlist);
		request.setAttribute("lcList", cslist);
		request.setAttribute("qshList", qslist);
	}
	
	
	public void getSsxxByWh(HttpServletRequest request,String userType,String userName,
			String isFdy,String userDep){
		GhxyQsryfDao qsryfDao=new GhxyQsryfDao();
		GhxyQyfdywhService qyfdyService=new GhxyQyfdywhService();
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>ldlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>cslist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>qslist=new ArrayList<HashMap<String,String>>();
		if(qsryfDao.isGygly(userName)){//���Ҹ���Ա
			String ldsql="select distinct(lddm),ldmc from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?)";
//			String cssql="select distinct(cs),lddm from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?)";
//			String qssql="select distinct(qsh),lddm,cs from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?)";
			ldlist=dao.getList(ldsql, new String[]{userName}, new String[]{"lddm","ldmc"});
			HashMap<String,String>lcMap=new HashMap<String,String>();
			HashMap<String,String>qsMap=new HashMap<String,String>();
			lcMap.put("mc", "--��ѡ��--");
			lcMap.put("dm", "");
			qsMap.put("mc", "--��ѡ��--");
			qsMap.put("dm", "");
			cslist.add(lcMap);
			qslist.add(qsMap);
		}
		request.setAttribute("ldList", ldlist);
		request.setAttribute("lcList", cslist);
		request.setAttribute("qshList", qslist);
	}
	
	/**
	 * 
	 * ����Ȩ�޲�ѯ��Ϣ
	 * @param request
	 * @param userType
	 * @param userName
	 * @param isFdy
	 * @param userDep
	 */
	public List<HashMap<String,String>> getSsByQx(String list,String userDep,String userName,String[] checks){
		
		
		boolean blog=false;
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>ldlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>cslist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>qslist=new ArrayList<HashMap<String,String>>();
		StringBuilder ldsb=new StringBuilder();
		StringBuilder cssb=new StringBuilder();
		StringBuilder qssb=new StringBuilder();
		ldsb.append("select lddm lddm,ldmc from (");
		cssb.append("select cs dm,cs mc from (");
		qssb.append("select qsh dm,qsh mc from (");
		for(int i=0;i<checks.length;i++){
			if("xy".equalsIgnoreCase(checks[i])){//ѧԺ
				if(blog){
					ldsb.append(" union ");
					cssb.append(" union ");
					qssb.append(" union ");
				}
				blog=true;
				String ldsql="(select distinct(lddm)lddm,ldmc from view_xszsxx where xydm='"+userDep+"')";
				String cssql="(select cs,lddm  from view_xszsxx where xydm='"+userDep+"')";
				String qssql="(select qsh,lddm,cs from view_xszsxx where xydm='"+userDep+"')";
				ldsb.append(ldsql);
				cssb.append(cssql);
				qssb.append(qssql);
			}else if("fdy".equalsIgnoreCase(checks[i])){//����Ա
				if(blog){
					ldsb.append(" union ");
					cssb.append(" union ");
					qssb.append(" union ");
				}
				blog=true;
				String ldsql="(select distinct(lddm)lddm,ldmc from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"'))";
				String cssql="(select cs,lddm from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"' ))";
				String qssql="(select qsh,lddm,cs from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"' ))";
				ldsb.append(ldsql);
				cssb.append(cssql);
				qssb.append(qssql);
			}else if("gyfdy".equalsIgnoreCase(checks[i])){//���Ҹ���Ա
				if(blog){
					ldsb.append(" union ");
					cssb.append(" union ");
					qssb.append(" union ");
				}
				String ldsql="(select distinct(lddm)lddm,ldmc from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm='"+userName+"')) ";
				String cssql="(select cs,lddm from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm='"+userName+"')) ";
				String qssql="(select qsh,lddm,cs from view_xszsxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm='"+userName+"')) ";
				blog=true;
				ldsb.append(ldsql);
				cssb.append(cssql);
				qssb.append(qssql);
			}else if("qyfdy".equalsIgnoreCase(checks[i])){
				if(blog){
					ldsb.append(" union ");
					cssb.append(" union ");
					qssb.append(" union ");
				}
				blog=true;
				String ldsql="(select distinct(lddm)lddm,ldmc from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh='"+userName+"' and a.yqdm=b.yqqdm ))";
				String cssql="(select cs,lddm from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh='"+userName+"' and a.yqdm=b.yqqdm ))";
				String qssql="(select qsh,lddm,cs from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh='"+userName+"' and a.yqdm=b.yqqdm ))";
				ldsb.append(ldsql);
				cssb.append(cssql);
				qssb.append(qssql);
			}
		}
		ldsb.append(") order by lddm asc");
		cssb.append(") order by cs asc");
		qssb.append(") order by qsh asc");
		HashMap<String,String>ldhash=new HashMap<String,String>();
		ldhash.put("lddm", "");
		ldhash.put("ldmc", "");
		HashMap<String,String>cshash=new HashMap<String,String>();
		cshash.put("dm", "");
		cshash.put("mc", "");
		HashMap<String,String>qshash=new HashMap<String,String>();
		qshash.put("dm", "");
		qshash.put("mc", "");
		ldlist.add(ldhash);
		cslist.add(cshash);
		qslist.add(qshash);
		ldlist.addAll(dao.getList(ldsb.toString(), new String[]{}, new String[]{"lddm","ldmc"}));
		
		
		if("ld".equalsIgnoreCase(list)){
			return ldlist;
		}else if("cs".equalsIgnoreCase(list)){
			return cslist;
		}else if("qs".equalsIgnoreCase(list)){
			return qslist;
		}else{
			return null;
		}
		
	}
	
	/**
	 * 
	 * ��ʼ������
	 */
	public List<HashMap<String,String>>initCs(String lddm,String userType,
			String userName,String isFdy,String userDep){
		GhxyQsryfDao qsryfDao=new GhxyQsryfDao();
		GhxyQyfdywhService qyfdyService=new GhxyQyfdywhService();
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>cslist=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>map=new HashMap<String,String>();
		String cssql="";
		map.put("dm", "");
		map.put("mc", "--��ѡ��--");
		cslist.add(map);
		if(qsryfDao.isGygly(userName)){//���Ҹ���Ա
			cssql="select distinct(cs)dm, cs mc ,lddm from view_ssxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?) and lddm=?";
			cslist.addAll(dao.getList(cssql, new String[]{userName,lddm}, new String[]{"dm","mc"}));
		}else if("true".equalsIgnoreCase(isFdy)){//����Ա
			cssql="select distinct(cs)dm, cs mc,lddm from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh=? ) and lddm=?";
			cslist.addAll(dao.getList(cssql, new String[]{userName,lddm}, new String[]{"dm","mc"}));
		}else if(qyfdyService.isQyfdy(userName)){
			cssql="select distinct(cs),lddm from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh=? and a.yqdm=b.yqqdm ) and lddm=?";
			cslist.addAll(dao.getList(cssql, new String[]{userName,lddm}, new String[]{"dm","mc"}));
		}else if("xy".equalsIgnoreCase(userType)){//ѧԺ
			cssql="select distinct(cs)dm, cs mc,lddm  from view_xszsxx where xydm=? and lddm=? ";
			cslist.addAll(dao.getList(cssql, new String[]{userDep,lddm}, new String[]{"dm","mc"}));
		}
		return cslist;
	}
	
	
	/**
	 * 
	 * ��ʼ������
	 */
	public List<HashMap<String,String>>initCsByQx(String lddm,String userType,
			String userName,String isFdy,String userDep,String[] checks){
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>cslist=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>map=new HashMap<String,String>();
		map.put("dm", "");
		map.put("mc", "--��ѡ��--");
		boolean blog=false;
		cslist.add(map);
		
		StringBuilder cssql=new StringBuilder();
		cssql.append("select distinct(dm),mc  from (");
		for(int i=0;i<checks.length;i++){
			if("gyfdy".equalsIgnoreCase(checks[i])){
				if(blog){
					cssql.append(" union ");
				}
				blog=true;
				cssql.append("(select cs dm, cs mc  from view_ssxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm='"+userName+"') and lddm='"+lddm+"')");	
			}
			if("xy".equalsIgnoreCase(checks[i])){//ѧԺ
				if(blog){
					cssql.append(" union ");
				}
				blog=true;
				cssql.append("(select cs dm, cs mc  from view_xszsxx where xydm='"+userDep+"' and lddm='"+lddm+"' )");
				
			}
			if("fdy".equalsIgnoreCase(checks[i])){//����Ա
				if(blog){
					cssql.append(" union ");
				}
				blog=true;
				cssql.append("(select cs dm, cs mc from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"'  and lddm='"+lddm+"')");
				
			}
			if("qyfdy".equalsIgnoreCase(checks[i])){//���Ҹ���Ա
				if(blog){
					cssql.append(" union ");
				}
				blog=true;
				cssql.append("(select cs dm,cs mc from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh='"+userName+"' and a.yqdm=b.yqqdm ) and lddm='"+lddm+"')");
				
			}
			
		}
		cssql.append(") order by dm asc");
		cslist.addAll(dao.getList(cssql.toString(), new String[]{}, new String[]{"dm","mc"}));
		return cslist;
	}
	
	/**
	 * ��ʼ�����Һ�
	 * 
	 */
	public List<HashMap<String,String>>initQsh(String lddm,String cs,
			String userType,String userName,String isFdy,String userDep){
		GhxyQsryfDao qsryfDao=new GhxyQsryfDao();
		GhxyQyfdywhService qyfdyService=new GhxyQyfdywhService();
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>qslist=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>map=new HashMap<String,String>();
		String qssql="";
		map.put("dm","" );
		map.put("mc","--��ѡ��--" );
		qslist.add(map);
		if(qsryfDao.isGygly(userName)){//���Ҹ���Ա
			qssql="select distinct(qsh)dm,qsh mc,lddm,cs from view_ssxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm=?) and lddm=? and cs=?";
			qslist.addAll(dao.getList(qssql, new String[]{userName,lddm,cs}, new String[]{"dm","mc"}));
		}else if("true".equalsIgnoreCase(isFdy)){//����Ա
			qssql="select  distinct(qsh)dm,qsh mc),lddm,cs from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh=? )";
			qslist.addAll(dao.getList(qssql, new String[]{userName,lddm,cs}, new String[]{"dm","mc"}));
		}else if(qyfdyService.isQyfdy(userName)){//����Ա
			qssql="select distinct(qsh),lddm,cs from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh=? and a.yqdm=b.yqqdm ) and lddm=? and cs=?";
			qslist.addAll(dao.getList(qssql, new String[]{userName,lddm,cs}, new String[]{"dm","mc"}));
		}else if("xy".equalsIgnoreCase(userType)){//ѧԺ
			qssql="select  distinct(qsh)dm,qsh mc,lddm,cs from view_xszsxx where xydm=? and lddm=? and cs=? order by qsh asc";
			qslist.addAll(dao.getList(qssql, new String[]{userDep,lddm,cs}, new String[]{"dm","mc"}));
		}
		return qslist;
	}
	
	/**
	 * ��ʼ�����Һ�
	 * byȨ��
	 */
	public List<HashMap<String,String>>initQshByQx(String lddm,String cs,
			String userType,String userName,String isFdy,String userDep,String[] checks){
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>qslist=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>map=new HashMap<String,String>();
		map.put("dm","" );
		map.put("mc","--��ѡ��--" );
		qslist.add(map);
		StringBuilder qssb=new StringBuilder();
		boolean blog=false;
		qssb.append("select distinct(dm)dm,mc mc from (");
		for(int i=0;i<checks.length;i++){
			if("gyfdy".equalsIgnoreCase(checks[i])){
				if(blog){
					qssb.append(" union ");
				}
				blog=true;
				qssb.append("(select qsh dm,qsh mc from view_ssxx a where exists (select * from view_gyfdyxx b where a.lddm=b.lddm and b.yhm='"+userName+"') and lddm='"+lddm+"' and cs='"+cs+"')");
			}else if("xy".equalsIgnoreCase(checks[i])){//ѧԺ
				if(blog){
					qssb.append(" union ");
				}
				blog=true;
				qssb.append("(select  qsh dm,qsh mc from view_xszsxx where xydm='"+userDep+"' and lddm='"+lddm+"' and cs='"+cs+"' )");
				
			}else if("fdy".equalsIgnoreCase(checks[i])){//����Ա
				if(blog){
					qssb.append(" union ");
				}
				blog=true;
				qssb.append("(select  qsh dm,qsh mc from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"' and lddm='"+lddm+"' and cs='"+cs+"'))");
				
			}else  if("qyfdy".equalsIgnoreCase(checks[i])){//���Ҹ���Ա
				if(blog){
					qssb.append(" union ");
				}
				blog=true;
				qssb.append("(select qsh dm,qsh mc from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh='"+userName+"' and a.yqdm=b.yqqdm ) and lddm='"+lddm+"' and cs='"+cs+"')");
				
			}
		}
		qssb.append(")order by dm asc");
		System.out.println(qssb.toString());
		qslist.addAll(dao.getList(qssb.toString(), new String[]{}, new String[]{"dm","mc"}));
		return qslist;
	}
	
	
	/**
	 * 
	 * ��ȡ���Ҹ���Ա
	 */
	public String getQyfdy(String userName){
		GhxyQyfdywhDao dao=new GhxyQyfdywhDao();
		List<HashMap<String,String>>list=dao.getQyfdy(userName);
		String yqqdm="";
		if(list.size()>0){
			HashMap<String,String> hashMap=list.get(0);
			yqqdm=hashMap.get("yqqdm");
		}
		return yqqdm;
	}
	
	/**
	 * ����Ȩ�޹���������Ϣ
	 * 
	 */
	public void getQsXx(HttpServletRequest request,String userType,String userName,
			String isFdy,String userDep){
		GhxyQsryfDao qsryfDao=new GhxyQsryfDao();
		GhxyQyfdywhService qyfdyService=new GhxyQyfdywhService();
		 if(qyfdyService.isQyfdy(userName)){//����Ա
			request.setAttribute("annexTerm", "and exists(select 1 from(select * from view_ssxx a where exists(select yqqdm from ghxy_qyfdyb b where zgh='"+userName+"' and a.yqdm=b.yqqdm ))b where a.lddm=b.lddm and a.qsh=b.qsh)");
		}else if(qsryfDao.isGygly(userName)){//���Ҹ���Ա
			request.setAttribute("annexTerm", " and exists(select 1 from view_gyfdyxx b where yhm='"+userName+"' and b.lddm=a.lddm and b.xqdm=a.xqqdm) ");
		}else if("xy".equalsIgnoreCase(userType)){//ѧԺ
			request.setAttribute("annexTerm", "and exists(select 1 from view_xszsxx b where xydm='"+userDep+"' and b.qsh=a.qsh and b.lddm=a.lddm and a.xqqdm=b.xqdm)");
		}else if("true".equalsIgnoreCase(isFdy)){//����Ա
			request.setAttribute("annexTerm", "and exists(select 1 from(select lddm,qsh from view_xszsxx a where exists(select * from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"'))b where a.lddm=b.lddm and a.qsh=b.qsh)");
		}
	}
	
	/**
	 * ����ѧ�Ż�ȡ
	 * ס����Ϣ
	 */
	public HashMap<String,String>getZsxx(String userName){
	
		DAO dao=DAO.getInstance();
		String sql="select lddm,cs,qsh from view_xszsxx where xh=?";
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{userName}, new String[]{"lddm","qsh","cs"});
		HashMap<String,String>hashMap=new HashMap<String,String>();
		if(list.size()>0){
			hashMap=list.get(0);
		}else{
			hashMap.put("lddm", "");
			hashMap.put("cs", "");
			hashMap.put("qsh", "");
		}
		return hashMap;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * 
	 */
	public void deleteQsxx() throws Exception{
		StringBuilder sb=new StringBuilder();
		sb.append("delete from ghxy_qsryfshb a where not exists");
		sb.append("(select * from ghxy_qsryfb b where (a.xn||a.xq||a.pc||a.plssbh) = (b.xn||b.xq||b.pc||b.plssbh) )");
		DAO dao=DAO.getInstance();
		dao.runUpdate(sb.toString(),new String[]{});
	}
}

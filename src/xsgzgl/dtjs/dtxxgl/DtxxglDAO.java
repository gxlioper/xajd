package xsgzgl.dtjs.dtxxgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import jxl.Sheet;
import jxl.Workbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;

public class DtxxglDAO {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	DAO dao = DAO.getInstance();
	
	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public List<String[]> dtxxQuery(User user,DtxxglForm form,String type,HttpServletRequest request) throws Exception{
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		String[] output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj"};
		
		sql.append("select rownum r,a.* from (select a.xh||a.jddm pk,a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc,"+
				"case when a.dqjdbj ='0' then '' else 'disabled' end dis,case when a.dqjdbj ='0' then '��' else '��' end sfdqjd "+
				"from xg_dtjs_xsdtxxjlb a left join view_xsbfxx b on a.xh=b.xh left join xg_dtjs_jbszb c on a.jddm=c.jddm order by b.nj,b.xydm,b.zydm,b.zydm,b.xh) a where 1=1");
		sql.append(qxSql);
		
		if("manage".equalsIgnoreCase(type)){
			sql.append(" and a.dqjdbj = '1' ");
		}else{
			output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj", "sfdqjd"};			
		}				
		
				
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), searchTj , inputV, output, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String[]> dtxxQuery2(User user,DtxxglForm form,String type,HttpServletRequest request) throws Exception{
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		String[] output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj", "sfdqjd"};
		
		sql.append("select rownum r,a.* from (select a.xh||a.jddm pk,a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc,"+
				"case when a.dqjdbj ='0' then '' else 'disabled' end dis,case when a.dqjdbj ='0' then '��' else '��' end sfdqjd "+
				"from (select xh,max(jddm) jddm from xg_dtjs_xsdtxxjlb group by xh) d left join xg_dtjs_xsdtxxjlb a on a.xh=d.xh and a.jddm=d.jddm left join view_xsbfxx b on d.xh=b.xh left join xg_dtjs_jbszb c on d.jddm=c.jddm) a where 1=1");
		sql.append(qxSql);
		
		
				
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), searchTj , inputV, output, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param user
	 * @param myForm
	 * @param type
	 * @param request
	 * @return
	 */
	public List<String[]> xytjQuery(User user, DtxxglForm myForm, String type,
			HttpServletRequest request)throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		List<String[]> list=new ArrayList<String[]>();
		String[] output = new String[]{"xymc","zrs","ty","per_ty","tys","per_tys","yb","per_yb","dy","per_dy"};
		String sql="select b.xymc,b.zrs,a.ty,CONCAT(TO_CHAR(a.ty/b.zrs*100,'990.99'),'%') per_ty,a.tys,CONCAT(TO_CHAR(a.tys/b.zrs*100,'990.99'),'%') per_tys,a.yb,CONCAT(TO_CHAR(a.yb/b.zrs*100,'990.99'),'%') per_yb,a.dy,CONCAT(TO_CHAR(a.dy/b.zrs*100,'990.99'),'%') per_dy from"+
		"(select a.xymc, (case when sum(a.jdmc02) is null then 0 else sum(a.jdmc02) end) ty,(case when sum(a.jdmc04) is null then 0 else sum(a.jdmc04) end) tys,(case when sum(a.jdmc07) is null then 0 else sum(a.jdmc07) end) yb,(case when sum(a.jdmc08) is null then 0 else sum(a.jdmc08) end) dy "+
		"from(select b.xymc,a.xh,a.jdmc02,a.jdmc04,a.jdmc07,a.jdmc08 from(select a.xh,b.xymc,a.jdmc02,a.jdmc04,a.jdmc07,a.jdmc08 "+
		"from(select a.xh, case when jddm='02' then 1 else 0 end jdmc02,case when jddm='04' then 1 else 0 end jdmc04,case when jddm='07' then 1 else 0 end jdmc07,case when jddm='08' then 1 else 0 end jdmc08 "+
		"from(select xh,max(jddm) jddm from xg_dtjs_xsdtxxjlb group by xh)a)a left join view_xsbfxx b on a.xh=b.xh)a right join (select distinct xymc from view_njxyzybj)b on a.xymc=b.xymc)a group by a.xymc)a left join (select xymc, count(xh) zrs from view_xsbfxx group by xymc)b on a.xymc=b.xymc";
		String tmpsql=sql;
		sql=sql+" union all select '�ܼ�' xymc,sum(zrs),sum(ty),CONCAT(TO_CHAR(sum(ty)/sum(zrs)*100,'990.99'),'%') per_ty,sum(tys),CONCAT(TO_CHAR(sum(tys)/sum(zrs)*100,'990.99'),'%') per_tys,sum(yb),CONCAT(TO_CHAR(sum(yb)/sum(zrs)*100,'990.99'),'%') per_yb,sum(dy),CONCAT(TO_CHAR(sum(dy)/sum(zrs)*100,'990.99'),'%') per_dy from ("+tmpsql+")";
		list = CommonQueryDAO.xytjQuery(sql.toString(), searchTj , inputV, output, myForm);
		return list;
	}
	
	/**
	 * ����ɾ��ѧ��������Ϣ(����ѧ��)
	 * */
	public boolean dtxxDel(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_dtjs_xsdtxxjlb where xh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * ����ɾ��ѧ��������Ϣ(������Ϣ)
	 * */
	public boolean delDtxx(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_dtjs_xsdtxxjlb where xh||jddm=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * ��ȡ���������еĵ��Ž���׶�list
	 * */
	public List<HashMap<String,String>> getJdList(){
		return dao.getArrayList("select * from xg_dtjs_jbszb order by to_number(jdsx)", 
				new String[]{}, new String[]{"jddm","jdmc","jdsx","sfszjssj"});
	}
	/**
	 * ��ȡ�׶δ�������
	 */
	public List<HashMap<String, String>> getJdList1(){
		
		return null;
	}
	
	/**
	 * ��ȡѧ���ĵ��Ž���׶�list
	 * */
	public List<HashMap<String,String>> getXsjdList(String xh){
		return dao.getArrayList("select a.*,b.kssj,b.jssj,b.dqjdbj,b.zd1,b.zd2,b.zd3,b.zd4,b.zd5 from xg_dtjs_jbszb a " +
				"left join xg_dtjs_xsdtxxjlb b on a.jddm = b.jddm and b.xh =? order by to_number(a.jdsx)", 
				new String[]{xh}, new String[]{"jddm","jdmc","jdsx","sfszjssj","dqjdbj","kssj","jssj","zd1","zd2","zd3","zd4","zd5"});
	}
	/**
	 * ��ȡѧ���ĵ��Ž���׶�������Ϣ
	 * */
	public HashMap<String,String> getXsjdOther(String xh){
		return dao.getMap(" select distinct b.xh,b.zd1,b.zd2,b.zd3,b.zd4,b.zd5,b.zd6,b.zd7 from xg_dtjs_xsdtxxjlb  b where b.xh =?  " ,
				new String[]{xh}, new String[]{"zd1","zd2","zd3","zd4","zd5","zd6","zd7"});
	}
	
	/**
	 * ��ȡѧ����ǰ�׶εĽ׶���Ϣ
	 * */
	public HashMap<String,String> getDqjd(String xh){
		return dao.getMap("select a.*,b.kssj,b.jssj,b.dqjdbj from xg_dtjs_jbszb a " +
				"left join xg_dtjs_xsdtxxjlb b on a.jddm = b.jddm and b.xh =? where dqjdbj = '1'", 
				new String[]{xh}, new String[]{"jddm","jdmc","jdsx"});
	}
	
	/**
	 * ���浳����Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveDtxx(DtxxglForm model) throws Exception{
		boolean b=false;
		String xh=model.getXh();
		String curr_jddm=model.getCurr_jddm();
		String[] jddm=model.getJddm();
		String[] kssj=model.getKssj();
		String[] jssj=model.getJssj();
		model.setZd7(model.getZd7()==null?"":model.getZd7());
		model.setZd6(model.getZd6()==null?"":model.getZd6());
		if (Base.isNull(curr_jddm) || jddm == null || jddm.length == 0
				|| kssj == null || kssj.length == 0 || jssj == null
				|| jssj.length == 0 || jddm.length != kssj.length
				|| jddm.length != jssj.length) {
			return b;
		}
		List<String> sql=new ArrayList<String>();
		for(int i=0;i<jddm.length;i++){
			if(curr_jddm.equals(jddm[i])){//���һ��
				sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
						+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','1','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				break;
			}else{
				if(!(Base.isNull(kssj[i])&&Base.isNull(jssj[i]))){
					sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
							+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','0','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				}
			}
		}
//		CommDAO dao=new CommDAO();
		b=dao.saveArrDate(sql.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * �޸ĵ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean updateDtxx(DtxxglForm model) throws Exception{
		boolean b=false;
		String xh=model.getXh();
		String curr_jddm=model.getCurr_jddm();
		String[] jddm=model.getJddm();
		String[] kssj=model.getKssj();
		String[] jssj=model.getJssj();
		model.setZd7(model.getZd7()==null?"":model.getZd7());
		model.setZd6(model.getZd6()==null?"":model.getZd6());
		if (Base.isNull(curr_jddm) || jddm == null || jddm.length == 0
				|| kssj == null || kssj.length == 0 || jssj == null
				|| jssj.length == 0 || jddm.length != kssj.length
				|| jddm.length != jssj.length) {
			return b;
		}
		List<String> sql=new ArrayList<String>();
		for(int i=0;i<jddm.length;i++){
			sql.add("delete from xg_dtjs_xsdtxxjlb where xh='"+xh+"' and jddm='"+jddm[i]+"'");
			if(curr_jddm.equals(jddm[i])){//���һ��
				sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
						+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','1','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				break;
			}else{
				if(!(Base.isNull(kssj[i])&&Base.isNull(jssj[i]))){
					sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
							+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','0','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				}
			}
		}
//		CommDAO dao=new CommDAO();
		b=dao.saveArrDate(sql.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * ��������
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		//���ȴ���׶δ��� start
		List<HashMap<String,String>> jddmList=getJdList();//��ȡ�׶��б�
		String[] jddms=new String[jddmList.size()];//�׶δ�������
		String[] sfszjssj=new String[jddmList.size()];//�Ƿ����ý���ʱ��
		HashMap<String,Integer> jddmMap=new HashMap<String, Integer>();//�׶δ���Map,���ڻ�ȡָ���׶δ��������
		for(int i=0;i<jddmList.size();i++){
			jddms[i]=jddmList.get(i).get("jddm");
			jddmMap.put(jddms[i], i);
			sfszjssj[i]=jddmList.get(i).get("sfszjssj");
		}
		//�׶δ���end
		DAO dao = DAO.getInstance();
		int excelXsCount=0;//excel�ļ�ѧ���ļ�¼��
		
		boolean b=false;
		//������ʱ��
		try {
			//���Ƚ���ʱ���е��������
			b=dao.runUpdate("delete from xg_dtjs_impxsdtxxjlb", new String[]{});
			if(!b){
				return "��ʱ������ɾ��ʧ�ܣ������µ��룡";
			}
			
			String path=request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			int dqColIndex=0;
			String[] row;
			String dqjddm=null;
			ArrayList<String> excelData_sql = new ArrayList<String>();//���ڱ����excel��õ�����
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				//���Ҫ�����¼   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,2+jddms.length*2);
				dqjddm=Base.chgNull(row[1], "", 0);
				if("".equals(dqjddm)){//��ǰ�׶δ���Ϊ��
					excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,mark,bz) " +
							"values(trim('"+row[0]+"'),'0','��ǰ�׶δ���Ϊ��')");
				}else if(jddmMap.get(dqjddm)==null){//�׶δ��벻��ȷ
					excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,mark,bz) " +
							"values(trim('"+row[0]+"'),'"+dqjddm+"','0','��ǰ�׶δ��벻��ȷ')");
				}else{
					dqColIndex=jddmMap.get(dqjddm)*2+2;
					if(Base.isNull(row[dqColIndex])){//�жϵ�ǰ�׶ο�ʼʱ���Ƿ�Ϊ��
						excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,mark,bz) " +
								"values(trim('"+row[0]+"'),'"+dqjddm+"','0','��ǰ�׶ο�ʼʱ��Ϊ��')");
					}else{
						for(int i=0;i<jddms.length;i++){
							if(jddms[i].equals(dqjddm)){//�жϽ׶��Ƿ�β��ǰ�׶δ���
								excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,kssj,jssj,dqjdbj,mark) " +
										"values(trim('"+row[0]+"'),'"+jddms[i]+"',trim('"+row[i*2+2]+"'),trim('"+row[i*2+3]+"'),'1','1')");
								break;
							}else if(!Base.isNull(row[i*2+2])||("��".equals(sfszjssj[i])&&!Base.isNull(row[i*2+3]))){
								excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,kssj,jssj,dqjdbj,mark) " +
										"values(trim('"+row[0]+"'),'"+jddms[i]+"',trim('"+row[i*2+2]+"'),trim('"+row[i*2+3]+"'),'0','1')");
							}
						}
					}
				}
			    //���Ҫ�����¼   end
			}
			CommDAO commdao=new CommDAO();
			excelXsCount=excelData_sql.size();
			if(excelXsCount>0){
				b=commdao.saveArrDate(excelData_sql.toArray(new String[]{}));
				if(!b){
					return "�����ֶι������޷��������ݿ⣡";
				}
			}else{
				return "�ļ���û�����ݿɵ��룡";
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return "���ݵ�����ʱ��ʱ�����쳣�������������ֶι������£�";

		}
		
		try {
			//��һ������ǵ��������У�ѧ��Ϊ�յ�����
			String sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='����ѧ���ֶ�Ϊ��' " +
			"where a.mark<>'0' and a.xh is null";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "����ѧ���ֶ�Ϊ�ձ��ʧ�ܣ�";
			}
			//�ڶ�������ǵ��������У�ѧ�Ų�������ϵͳ�е�����
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='��ѧ��ѧ��������' "+
				"where mark<>'0' and not exists (select 1 from view_xsjbxx b where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "ѧ��ѧ�������ڱ��ʧ�ܣ�";
			}
			//��������ѧ���������Դ����ڵ�����Ϣ��¼��
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='��ѧ��ѧ����Ϣ�Ѵ���' "+
			"where mark<>'0' and exists (select 1 from xg_dtjs_xsdtxxjlb b where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "ѧ��ѧ����Ϣ���ڱ��ʧ�ܣ�";
			}
			//���Ĳ���ѧ�Ž׶��ظ�
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='ѧ�Ž׶��ظ�' "+
			"where mark<>'0' and "+
			"exists (select 1 from " +
			"(select xh,jddm from xg_dtjs_impxsdtxxjlb where mark<>'0' group by xh,jddm having count(1)>1) b " +
			"where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "ѧ�Ž׶��ظ����ʧ�ܣ�";
			}
			//���岽����֤ʱ���ʽ
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='ʱ���ʽ����ȷ' " +
			"where a.mark<>'0' and exists (select 1 from (" +
			"select distinct xh from xg_dtjs_impxsdtxxjlb where mark<>'0' and (" +
			"(kssj is not null and (length(kssj)<>8 and length(kssj)<>10)) or " +
			"(jssj is not null and (length(jssj)<>8 and length(jssj)<>10))" +
			")) b where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "ʱ���ʽ����ȷ���ʧ�ܣ�";
			}
			//�����ݲ��뵽��ʽ����
			sql="insert into xg_dtjs_xsdtxxjlb(xh,jddm,kssj,jssj,dqjdbj) " +
				"select xh,jddm,kssj,jssj,dqjdbj from xg_dtjs_impxsdtxxjlb where mark='1'";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "����ת����ʽ��ʧ�ܣ�";
			}
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
			return "���ݸ��½׶η����쳣��";
		}
		
		List<String[]> xsList=new ArrayList<String[]>();
		try {
			String sql="select distinct xh,bz from xg_dtjs_impxsdtxxjlb where mark='0' order by bz";
			String[] outputValue=new String[]{"xh","bz"};
			String[] colListCN=new String[]{"ѧ��","����ʧ��ԭ��"};
			xsList=dao.rsToVator(sql, new String[]{}, outputValue);

			if(xsList!=null&&xsList.size()>0){
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(xsList,outputValue,colListCN, response.getOutputStream());
				request.setAttribute("sfdcExcel", "yes");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			response.reset();
			response.setContentType("text/html");
			return "���ݵ���ɹ����������Ϲ淶��"+((xsList==null||xsList.size()==0)?"":(xsList.size()+"��"))+"�����ڷ���ʱ�������쳣��";
		}
		
		return "����ɹ���";
	}
	
	/**
	 * ��������
	 * @param form
	 * @param type
	 * @param user 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> exportData(DtxxglForm form,String type,HttpServletResponse response, User user) throws Exception{
		
        Base.YXPZXY_KEY = message.getMessage("lable.xb");
		//���ȴ�����ת�� start
		List<String> output=new ArrayList<String>();//�����
		output.add("xh");
		output.add("xm");
		output.add("xb");
		output.add("zzmmmc");
		output.add("xymc");
		output.add("zymc");
		output.add("bjmc");
		output.add("jdmc");
		List<String> colListCN=new ArrayList<String>();//����б�ͷ
		colListCN.add("ѧ��");
		colListCN.add("����");
		colListCN.add("�Ա�");
		colListCN.add("������ò");
		colListCN.add(Base.YXPZXY_KEY);
		colListCN.add("רҵ");
		colListCN.add("�༶");
		colListCN.add("��ǰ�׶�");
		
		StringBuffer hzl_sql=new StringBuffer();//��ת��sql
		StringBuffer hzl_sql_case=new StringBuffer();//��ת������ת��sql
		StringBuffer hzl_sql_max=new StringBuffer();//��ת�кϲ�sql
		List<HashMap<String,String>> jddmList=getJdList();//�׶δ����б�
		String jddm,jdmc;
		for(int i=0;i<jddmList.size();i++){
			jddm=jddmList.get(i).get("jddm");
			jdmc=jddmList.get(i).get("jdmc");
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then kssj else '' end) kssj"+jddm);
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then jssj else '' end) jssj"+jddm);
			hzl_sql_max.append(",max(kssj"+jddm+") kssj"+jddm);
			hzl_sql_max.append(",max(jssj"+jddm+") jssj"+jddm);
			output.add("kssj"+jddm);
			output.add("jssj"+jddm);
			colListCN.add(jdmc+"��ʼʱ��");
			colListCN.add(jdmc+"����ʱ��");
			
		}
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_max);
		hzl_sql.append(" from ( ");//1#
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_case);
		hzl_sql.append(" from xg_dtjs_xsdtxxjlb ");
		hzl_sql.append(" ) ");//1#
		hzl_sql.append(" group by xh ");
		
		//���ȴ�����ת�� end
		//�滻��� start
		String xg_dtjs_xsdtxxjlb="(select a.*,b.jddm,b.dqjdbj from ("+hzl_sql+") a left join xg_dtjs_xsdtxxjlb b on a.xh=b.xh and b.dqjdbj='1')";
		//�滻��� end
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		
		sql.append("select a.* from (select a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc " +
				" from "+xg_dtjs_xsdtxxjlb+" a " +
				"left join view_xsbfxx b on a.xh = b.xh left join xg_dtjs_jbszb c on a.jddm = c.jddm " +
				"order by xydm,zydm,bjdm,a.xh,jdsx desc) a where 1=1 "+searchTj+qxSql);
		
		try {
			list = CommonQueryDAO.commonQueryNotFy(sql.toString(), "" , inputV, output.toArray(new String[]{}), form);
			Excel2Oracle.exportData(list,output.toArray(new String[]{}),colListCN.toArray(new String[]{}), response.getOutputStream());
		} catch (Exception e) {
		}
		
		return null;
	}
	
	public List<String[]> exportData2(DtxxglForm form,String type,HttpServletResponse response) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		//���ȴ�����ת�� start
		List<String> output=new ArrayList<String>();//�����
		output.add("xh");
		output.add("xm");
		output.add("xb");
		output.add("zzmmmc");
		output.add("xymc");
		output.add("zymc");
		output.add("bjmc");
		output.add("jdmc");
		List<String> colListCN=new ArrayList<String>();//����б�ͷ
		colListCN.add("ѧ��");
		colListCN.add("����");
		colListCN.add("�Ա�");
		colListCN.add("������ò");
		colListCN.add(Base.YXPZXY_KEY);
		colListCN.add("רҵ");
		colListCN.add("�༶");
		colListCN.add("��ǰ�׶�");
		
//		StringBuffer hzl_sql=new StringBuffer();//��ת��sql
//		StringBuffer hzl_sql_case=new StringBuffer();//��ת������ת��sql
//		StringBuffer hzl_sql_max=new StringBuffer();//��ת�кϲ�sql
//		List<HashMap<String,String>> jddmList=getJdList();//�׶δ����б�
//		String jddm,jdmc;
//		for(int i=0;i<jddmList.size();i++){
//			jddm=jddmList.get(i).get("jddm");
//			jdmc=jddmList.get(i).get("jdmc");
//			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then kssj else '' end) kssj"+jddm);
//			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then jssj else '' end) jssj"+jddm);
//			hzl_sql_max.append(",max(kssj"+jddm+") kssj"+jddm);
//			hzl_sql_max.append(",max(jssj"+jddm+") jssj"+jddm);
//			output.add("kssj"+jddm);
//			output.add("jssj"+jddm);
//			colListCN.add(jdmc+"��ʼʱ��");
//			colListCN.add(jdmc+"����ʱ��");
//			
//		}
//		hzl_sql.append("select xh ");
//		hzl_sql.append(hzl_sql_max);
//		hzl_sql.append(" from ( ");//1#
//		hzl_sql.append("select xh ");
//		hzl_sql.append(hzl_sql_case);
//		hzl_sql.append(" from xg_dtjs_xsdtxxjlb ");
//		hzl_sql.append(" ) ");//1#
//		hzl_sql.append(" group by xh ");
//		
//		//���ȴ�����ת�� end
//		//�滻��� start
//		String xg_dtjs_xsdtxxjlb="(select a.*,b.jddm,b.dqjdbj from ("+hzl_sql+") a left join xg_dtjs_xsdtxxjlb b on a.xh=b.xh and b.dqjdbj='1')";
//		//�滻��� end
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		
		sql.append("select b.xh,b.xm,b.XB,b.XYMC,b.ZZMMMC,b.ZYMC,b.BJMC,c.jdmc from (select xh,max(jddm) jddm from xg_dtjs_xsdtxxjlb group by xh) a left join view_xsbfxx b on a.xh=b.xh left join xg_dtjs_jbszb c on a.jddm=c.jddm where 1=1 "+searchTj);
		
		try {
			list = CommonQueryDAO.commonQueryNotFy(sql.toString(), "" , inputV, output.toArray(new String[]{}), form);
			Excel2Oracle.exportData(list,output.toArray(new String[]{}),colListCN.toArray(new String[]{}), response.getOutputStream());
		} catch (Exception e) {
		}
		
		return null;
	}
	
	/**
	 * ͬ������������ò
	 * @return
	 * @throws Exception 
	 */
	public boolean tbgxzzmm() throws Exception{
		//�ж�bks_xsjbxx�Ƿ��Ǳ�,ֻ���Ǳ������ǲŽ��иñ�ĸ���
		String bks_xsjbxx_isTable_sql="select count(1) num from user_tables where table_name='BKS_XSJBXX'";
		String num=new DAO().getOneRs(bks_xsjbxx_isTable_sql, new String[]{}, "num");
		
		String zdm = new DAO().getOneRs("select dyzd from xg_dtjs_jbszb where dyzd is not null and rownum=1", new String[]{}, "dyzd");
		
		boolean bks_xsjbxx_isTable="0".equals(num)?false:true;
		
		boolean b=false;
		List<String> sqls=new ArrayList<String>();
		sqls.add("update xsxxb x set "+zdm+" = (select dyz from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )where exists(" +
				"select xh from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )");
		if(bks_xsjbxx_isTable){
			sqls.add("update BKS_XSJBXX x set "+zdm+" = (select dyz from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )where exists(" +
				"select xh from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )");
		}
		CommDAO commdao=new CommDAO();
		b=commdao.saveArrDate(sqls.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * ѧ��������Ϣ�����ֶ��嵼��
	 * @param form
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>>  xsxxglExportData(DtxxglForm form) throws Exception{
		//���ȴ�����ת�� start
		Base.YXPZXY_KEY = message.getMessage("lable.xb"); 
		List<String> output=new ArrayList<String>();//�����
		output.add("xh");
		output.add("xm");
		output.add("xb");
		output.add("zzmmmc");
		output.add("xymc");
		output.add("zymc");
		output.add("bjmc");
		output.add("jdmc");
		List<String> colListCN=new ArrayList<String>();//����б�ͷ
		colListCN.add("ѧ��");
		colListCN.add("����");
		colListCN.add("�Ա�");
		colListCN.add("������ò");
		colListCN.add(Base.YXPZXY_KEY);
		colListCN.add("רҵ");
		colListCN.add("�༶");
		colListCN.add("��ǰ�׶�");
		
		StringBuffer hzl_sql=new StringBuffer();//��ת��sql
		StringBuffer hzl_sql_case=new StringBuffer();//��ת������ת��sql
		StringBuffer hzl_sql_max=new StringBuffer();//��ת�кϲ�sql
		List<HashMap<String,String>> jddmList=getJdList();//�׶δ����б�
		String jddm,jdmc;
		for(int i=0;i<jddmList.size();i++){
			jddm=jddmList.get(i).get("jddm");
			jdmc=jddmList.get(i).get("jdmc");
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then kssj else '' end) kssj"+jddm);
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then jssj else '' end) jssj"+jddm);
			hzl_sql_max.append(",max(kssj"+jddm+") kssj"+jddm);
			hzl_sql_max.append(",max(jssj"+jddm+") jssj"+jddm);
			output.add("kssj"+jddm);
			output.add("jssj"+jddm);
			colListCN.add(jdmc+"��ʼʱ��");
			colListCN.add(jdmc+"����ʱ��");
			
		}
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_max);
		hzl_sql.append(" from ( ");//1#
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_case);
		hzl_sql.append(" from xg_dtjs_xsdtxxjlb ");
		hzl_sql.append(" ) ");//1#
		hzl_sql.append(" group by xh ");
		
		//���ȴ�����ת�� end
		//�滻��� start
		String xg_dtjs_xsdtxxjlb="(select a.*,b.jddm,b.dqjdbj from ("+hzl_sql+") a left join xg_dtjs_xsdtxxjlb b on a.xh=b.xh and b.dqjdbj='1')";
		//�滻��� end
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		
		sql.append("select  rownum r, a.* from (select a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc " +
				" from "+xg_dtjs_xsdtxxjlb+" a " +
				"left join view_xsbfxx b on a.xh = b.xh left join xg_dtjs_jbszb c on a.jddm = c.jddm " +
				"order by xydm,zydm,bjdm,a.xh,jdsx desc) a where 1=1 "+searchTj);

		return  CommonQueryDAO.commonQueryforExportList(sql.toString(), "" , inputV, output.toArray(new String[]{}), form);
	}
	
	/**
	 * ѧ��������Ϣ��ѯ�Զ��嵼��
	 * @param form
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> xsdtxxcxExportData(DtxxglForm form,User user) throws Exception{
	
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();

		String[] output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj", "sfdqjd"};
		
		sql.append("select rownum r,a.* from (select a.xh||a.jddm pk,a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc,"+
				"case when a.dqjdbj ='0' then '' else 'disabled' end dis,case when a.dqjdbj ='0' then '��' else '��' end sfdqjd "+
				"from xg_dtjs_xsdtxxjlb a left join view_xsbfxx b on a.xh=b.xh left join xg_dtjs_jbszb c on a.jddm=c.jddm order by b.nj,b.xydm,b.zydm,b.zydm,b.xh) a where 1=1");
		sql.append(qxSql);
		
		
				
		
		return  CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj , inputV, output, form);
		
	}

}

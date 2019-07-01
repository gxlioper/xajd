package xsgzgl.gygl.qsgl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import common.Globals;

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
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;

public class QsglDAO extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	
	/**
	 * ��������
	 * @param model
	 * @return
	 */
	public boolean saveQswh(QsglModel model){
		boolean flag = false;
		
		String sql = "insert into xg_gygl_new_qsxxb(lddm,qsh,ch,qsxb,cws,sfbz,qsdh,xydm,nj,bz) values(?,?,?,?,?,?,?,?,?,?)";
		
		String[] input = new String[]{model.getLddm(), model.getQsh(), model.getCh(), 
									  model.getQsxb(), model.getCws(), model.getSfbz(),
									  model.getQsdh(), model.getXydm(), model.getNj(), model.getBz()};
		
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �Զ����ɴ�λ��
	 * @param params
	 * @return
	 */
	public boolean saveCws(List<String[]> params){
		boolean flag = false;
		
		String sql = "insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values(?,?,?)";
		
		try {
			int[] rs = dao.runBatch(sql, params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �޸�����
	 * @param model
	 * @return
	 */
	public boolean updateQswh(String pk,QsglModel model){
		boolean flag = false;
		HashMap<String,String> map=getQsfprzInfo(pk);
		if(null==model.getYwkt()){
			model.setYwkt("");
		}
		StringBuffer sql = new StringBuffer("update xg_gygl_new_qsxxb set sfbz='"+model.getSfbz()+"',qsdh='"+
				model.getQsdh()+"',bz='"+model.getBz()+"',ywkt='"+model.getYwkt()+"'");
		ArrayList<String> sqls=new ArrayList<String>();
		/**
		 * ���ݴ�ѧ���Ի�1036
		 */
		if(StringUtils.equals(Base.xxdm, Globals.XXDM_WZDX)){
			sql.append(" ,qsh='" + model.getQsh()+"'");
		}
		
		if("true".equals(map.get("xbsfkxg"))){//�޸������Ա�
			sql.append(" ,qsxb='"+model.getQsxb()+"' ");
		}
		if("0".equals(map.get("cwfprzgs"))&&!map.get("cws").equals(model.getCws())){//�޸Ĵ�λ��
			int cws=Integer.parseInt(model.getCws());
			if(cws>0){
				sql.append(" ,cws='"+model.getCws()+"' ");
				sqls.add("delete from xg_gygl_new_cwxxb where lddm||qsh='"+pk+"'");
				if ("10698".equals(Base.xxdm)) {
					String[] ch2 = {"A","B","C","D","E","F","G","H","I","J","H","I","J","K","L","M"};
					for(int i=0;i<cws;i++){
						sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
								+"','"+model.getQsh()+"','"+ch2[i]+"')");
					}
				}else {
					
					for(int i=0;i<cws;i++){
						sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
								+"','"+model.getQsh()+"','"+(i+1)+"')");
					}
				}
			
			}
		}
		sql.append(" where lddm||qsh='"+pk+"' ");
		sqls.add(sql.toString());
		try {
//			flag = dao.runUpdate(sql.toString(), input.toArray(new String[]{}));
			CommDAO dao=new CommDAO();
			flag= dao.saveArrDate(sqls.toArray(new String[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ѯ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> queryQs(QsglModel model) throws Exception{
		
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();				
		sql.append("select rownum r,a.* from (select rownum rr,a.*,a.lddm||a.qsh pkValue from view_xg_gygl_new_qsxx a ")
			.append(") a where 1=1 ");
							
		String[] output = new String[]{"pkValue","ldmc","qsh","qsxb", "nj", "xymc","qscws","blcws","wzcws","rzqk"};
				
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, output, model);
		return list;
	}
	
	/**
	 * ����ɾ��������Ϣ
	 * @param params
	 * @return
	 */
	public boolean delQs(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_gygl_new_qsxxb where lddm||qsh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * ɾ����λ��
	 * @param params
	 * @return
	 */
	public boolean delCws(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_gygl_new_cwxxb where lddm||qsh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * ͨ��������õ���������Ϣ
	 * @param pk
	 * @return
	 */
	public Map<String, String> getQsForPk(String pk){
		StringBuilder sql = new StringBuilder();
		 
		sql.append("select b.lddm||b.qsh pkValue,a.ldmc,a.ldxb,a.ldcs,a.qsch,a.sfhlc,b.* ")
		.append("from xg_gygl_new_ldxxb a,(select a.*,b.bmmc xymc,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc from xg_gygl_new_qsxxb a ")
		.append("left join zxbz_xxbmdm b on a.xydm=b.bmdm)b where a.lddm=b.lddm and b.lddm||b.qsh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
	
	/**
	 * ���ָ��¥��ָ���������������Һź����Ĵ�λ��
	 * @param lddm
	 * @param ch
	 * @return
	 */
	public Map<String, String> getMaxQsInfo(String lddm, String ch){
		String sql = "select max(qsh) maxQsh,max(cws) maxCws from xg_gygl_new_qsxxb where lddm=? and ch=?";
		return dao.getMapNotOut(sql, new String[]{lddm, ch});
	}
	
	/**
	 * ��ȡָ�������ҺŵĴ�λ��Ϣ
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<String[]> getCwForQs(String[] inputValue, String[] outputValue){
		StringBuilder sql = new StringBuilder();
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append("select a.*,b.xm,b.nj xsnj, b.xymc xsxymc, b.zymc xszymc, b.bjmc xsbjmc, ");
		sql.append("(select nvl2(t.xh,'���ҳ�','') from xg_gygl_new_gyglryb t ");
		sql.append("where rzzt = '����' and a.lddm=t.lddm and a.qsh=t.qsh and a.xh=t.xh) qsz ");
		sql.append("from (select * from view_xg_gygl_new_cwxx a where lddm||qsh=?)a ")
			.append("left join view_xsbfxx b on a.xh=b.xh order by "+sb+" ");
		
		return dao.rsToVator(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * ��������
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		int excelXsCount=0;//excel�ļ�ѧ���ļ�¼��
		boolean b=false;
		//������ʱ��
		try {
			//���Ƚ���ʱ���е��������
			b=dao.runUpdate("delete from xg_gygl_new_impqsxxb", new String[]{});
			if(!b){
				return "��ʱ������ɾ��ʧ�ܣ������µ��룡";
			}
			
			String path=request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//���Դexcel������
			String[] row;
			ArrayList<String> excelData_sql = new ArrayList<String>();//���ڱ����excel��õ�����
			
			//ģ����֤
			row = ExcelMethods.getOneRow(sourceSheet,0,3);
			if(!(row[0].equals("¥������")&&row[1].equals("���Һ�")&&row[2].equals("��������ֶ�"))){
				return "�������ʧ�ܣ��밴��ģ���ʽ���µ��룡";
			}
			
			for(int rownum=1;rownum<sourceRowCount;rownum++){//ÿ����¼
				//���Ҫ�����¼   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,5);
				excelData_sql.add("insert into xg_gygl_new_impqsxxb(lddm,qsh,drzd) " +
						"values( trim('"+row[0]+"'),trim('"+row[1]+"'),trim('"+row[2]+"') )");
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
			
			//��ǵ��������У�¥�����룬���Һţ���λ�ţ�ѧ��Ϊ�յ�����
			String sql="update xg_gygl_new_impqsxxb a set mark='0' " +
			"where not exists (select 1 from xg_gygl_new_qsxxb b where a.lddm=b.lddm and a.qsh=b.qsh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "��������ϵͳ�޶�Ӧ��Ϣ���ʧ�ܣ�";
			}
			

			//���ȸ���ѧ�ź���סʱ��
//			HttpSession session = request.getSession();
//			String rzczr=session.getAttribute("userName").toString();//���ò�����
			String drzd=request.getParameter("drzd");
			if(!("qsdh".equals(drzd)||"sfbz".equals(drzd)||"ywkt".equals(drzd))){
				return "��������ֶ���Ч��";
			}
			sql="update xg_gygl_new_qsxxb a set "+drzd+" = (select drzd from xg_gygl_new_impqsxxb b where a.lddm=b.lddm and a.qsh=b.qsh) " +
				" where exists (select 1 from xg_gygl_new_impqsxxb c where c.mark<>'0' and a.lddm=c.lddm and a.qsh=c.qsh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "ѧ�Ÿ���ʧ�ܣ�";
			}
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
			return "���ݸ��½׶η����쳣��";
		}
		
		List<String[]> xsList=new ArrayList<String[]>();
		try {
			String sql="select lddm,qsh,drzd,'ϵͳ�޶�Ӧ��¼' bz from xg_gygl_new_impqsxxb where mark='0'";
			String[] outputValue=new String[]{"lddm","qsh","drzd","bz"};
			String[] colListCN=new String[]{"¥������","���Һ�","�����ֶ�","����ʧ��ԭ��"};
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
	 * ��ȡ���ҷ�����ס��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getQsfprzInfo(String pkValue){
		String sql="select a.cws,a.xydm,a.nj,b.ldxb from xg_gygl_new_qsxxb a,xg_gygl_new_ldxxb b where a.lddm=b.lddm and b.lddm||a.qsh=?";
		HashMap<String,String> map=dao.getMapNotOut(sql, new String[]{pkValue});
		
		sql="select count(1) num from xg_gygl_new_cwxxb where lddm||qsh=? and (xydm is not null or nj is not null or xh is not null)";
		String num=dao.getOneRs(sql, new String[]{pkValue}, "num");
		map.put("cwfprzgs", num);//��λ�������ס����
		
		if ("11799".equals(Base.xxdm)) {
			map.put("xbsfkxg", "true");
		}else {
			if("��ס".equals(map.get("ldxb"))&&Base.isNull(map.get("xydm"))&&Base.isNull(map.get("nj"))&&"0".equals(num)){
				map.put("xbsfkxg", "true");//�Ա��Ƿ���޸�
			}else{
				map.put("xbsfkxg", "false");
			}
		}
		return map;
	}
	/**
	 * ��ȡ���Һ�
	 * @param tj
	 * @return
	 * @throws SQLException 
	 */
	public String[] getQshs(String tj) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select lddm||qsh pk from view_xg_gygl_new_qsxx a where sffp ='��' and rzqk ='ȫ��'");
		return dao.getArray(sql+tj, new String[]{}, "pk");
	}
	
	/**
	 * ��ʼ����������
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public String initQsss(QsglForm myForm,User user) throws Exception{
		String[] pk_cw = myForm.getPrimarykey_checkVal();
		List<String[]> list = new ArrayList<String[]>();
		
		if(pk_cw==null||pk_cw.length==0){
			return "û�пɳ�ʼ��������";
		}
		for(int i=0;i<pk_cw.length;i++){
			list.add(new String[]{pk_cw[i]});
		}
		String sql = "update xg_gygl_new_qsxxb a set xydm='',nj='' where lddm||qsh = ? and " +
		"exists (select 1 from xg_gygl_new_cwxxb x where x.lddm=a.lddm and x.qsh=a.qsh " +
		" and x.xh is null)";		
		boolean res = saveArrDate(sql,list, "xg_gygl_new_qsxxb", user);
		
		if(res&&"��".equals(myForm.getSfqccwss())){//�����λ����
			sql ="update xg_gygl_new_cwxxb a set xydm='',nj='',bjdm='' where lddm||qsh = ? and a.xh is null ";		
			res = saveArrDate(sql,list, "xg_gygl_new_cwxxb", user);
			if(res){
				return "��ʼ���ɹ�";
			}else{
				return "���ҳ�ʼ���ɹ�����λ��ʼ��ʧ��";
			}
		}
		
		if(res){
			return "��ʼ���ɹ�";
		}else{
			return "��ʼ��ʧ��";
		}
	}
	
	/**
	 * ��ȡ�ɳ�ʼ��������
	 * @param model
	 * @return
	 */
	public String getKcshqss(QsglModel model) throws Exception{
		String[] sfhkcw=model.getSearchModel().getSearch_tj_sf();//��ʱ�Ƴ����Ƿ񺬿մ�λ
		model.getSearchModel().setSearch_tj_sf(null);
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		model.getSearchModel().setSearch_tj_sf(sfhkcw);
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from ")
		.append("view_xg_gygl_new_qsxx a where sffp ='��' and rzqk ='ȫ��'  ");
							
		String num=dao.getOneRs(sql.toString()+searchTj, inputV, "num");
		return num;
	}
	
	/**
	 * ������Ϣ���� �Զ��� ���� 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> qsxxglExportdata(QsglModel model) throws Exception{
		
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();				
		sql.append("select rownum r,a.* from (select rownum rr,a.*,a.lddm||a.qsh pkValue from view_xg_gygl_new_qsxx a ")
			.append(") a where 1=1 ");
		
		String[] output = new String[]{"pkValue","ldmc","qsh","qsxb", "nj", "xymc","qscws","blcws","wzcws","rzqk"};
				
		list = CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj, inputV, output, model);
		return list;
	}

	/** 
	 * @����:(��ȡ¥���Ա�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-20 ����03:34:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getLcXb(String lddm, String ch) {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qsxb from xg_gygl_new_qsxxb where lddm=? and ch=?");
		return dao.getMapNotOut(sql.toString(), new String[]{lddm, ch});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ҹ������ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����10:06:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveplwhTypeOfQs(QsglForm para,String[] params) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update xg_gygl_new_qsxxb set cws = ?,sfbz=?,ywkt = ?" );
		if ("11799".equals(Base.xxdm)) {
			sql.append(",qsxb=?");
		}
		sql.append(" where lddm || qsh in(");
		paraList.add(para.getCws());
		paraList.add(para.getSfbz());
		paraList.add(para.getYwkt());
		if ("11799".equals(Base.xxdm)) {
			paraList.add(para.getQsxb());
		}
		for (int i = 0; i < params.length; i++) {
			sql.append("?");
			paraList.add(params[i]);
			if(i != params.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ��ȡ������ϢList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����05:35:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQsxxList(String[] params){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_gygl_new_qsxxb where lddm || qsh in(");
		for (int i = 0; i < params.length; i++) {
			sql.append("?");
			if(i != params.length-1){
				sql.append(",");
			}
		} 
		sql.append(" )");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), params);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����05:49:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCwxx(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_cwxxb(lddm,qsh,cwh");
		sql.append(")values(?,?,?");
		sql.append(")");
		return dao.runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����05:53:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delQsxx(String[] params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_new_cwxxb where lddm || qsh in(");
		for (int i = 0; i < params.length; i++) {
			sql.append("?");
			if(i != params.length-1){
				sql.append(",");
			}
		} 
		sql.append(" )");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.runUpdate(sql.toString(), params);
	}
}

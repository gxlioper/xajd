package xsgzgl.gygl.gyjlxxgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewService;

public class GyjlxxglDao extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	
	/**
	 * ��ü�����Ϣlist
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJlxxList(GyjlxxglForm myForm, HttpServletRequest request) throws Exception{
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		
		String searchTjstr = "";
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+inputV[i];
			}
			searchTjstr+=tj[inputV.length];
		}
		request.setAttribute("searchTjstr", searchTjstr);
		
		String sql = "select rownum r,a.* from (select a.*,case when to_number(wjcs)>0 then '��' else '��' end sfwj from (" +
				"select a.*,(select count(*) from xg_gygl_new_gyjlb where a.xh = xh) wjcs" +
				" from xg_view_gygl_new_xszsgl a where sfzs='��') a) a where 1=1 ";		
		String[] colList = new String[]{"xh","xm","xb","nj","xymc","bjmc","ldmc", "qsh", "cwh"};		
		
		return  commonQuery(sql,searchTj+searchTjQx,inputV,colList,myForm);
	}
	/**
	 * ���������Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String saveJlxx(GyjlxxglForm myForm,User user) throws Exception{
		String[] pk_xh = myForm.getPk_xh();
		String wjsj = myForm.getWjsj();
		String jldl = myForm.getJldldm();
		String jllb = myForm.getJllbdm();
		String bz = myForm.getBz();	
		String czr = user.getUserName();
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < pk_xh.length; i++){
			String[] arr = new String[]{jldl,jllb,wjsj,bz,czr,pk_xh[i]};
			list.add(arr);
		}
		String sql = "insert into xg_gygl_new_gyjlb(xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,gyjllbdldm,gyjllbdm,wjsj,bz,czr) " +
				"select xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,?,?,?,?,? from xg_view_gygl_new_xszsgl where xh = ? ";
		int[] res = dao.runBatch(sql, list);
		for (int i = 0; i < res.length; i++) {
			if (res[i] >= 0)
				return "����ʧ��";
		}
		return "����ɹ�";
	}
	/**
	 * ��ü��ɴ���
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getJldlList(HttpServletRequest request){
		String sql = "select GYJLLBDLDM jldldm,GYJLLBDLMC jldlmc from xg_gygl_new_gyjllbdlb order by GYJLLBDLDM ";		
		return dao.getList(sql, new String[]{},new String[]{"jldldm","jldlmc"});
	}
	
	/**
	 * ��ü��ɴ���Bydm
	 * @param request
	 * @return
	 */
	public String getJldlListByDm(String gyjllbdldm){
		String sql = "select gyjllbdlmc from xg_gygl_new_gyjllbdlb where gyjllbdldm=? ";		
		return dao.getOneRs(sql, new String[]{gyjllbdldm}, "gyjllbdlmc");
	}
	
	/**
	 * ��ü������
	 * @param jldldm
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getJllbList(String jldldm, HttpServletRequest request){
		String sql = "select GYJLLBDM jllbdm,GYJLLBMC jllbmc from xg_gygl_new_gyjllbdmb where GYJLLBDLDM=? order by GYJLLBDM ";		
		return dao.getList(sql, new String[]{jldldm},new String[]{"jllbdm","jllbmc"});
	}
	
	/**
	 * ��Ԣ���ɴ�������
	 * @param jldldm
	 * @param request
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>> getCflbList(){
		String sql = "select gyjlcfdm,gyjlcfmc from xg_gygl_new_gyjlcflbb order by  gyjlcfdm ";		
		return dao.getList(sql, new String[]{},new String[]{"gyjlcfdm","gyjlcfmc"});
	}
	
	/**
	 * �����������ס��ѧ��ѧ��
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String[] getXhs(String tj) throws Exception{
		String sql = "select * from (select rownum r, a.* from xg_view_gygl_new_xszsgl a where sfzs='��')where 1=1 ";
		return dao.getRs(sql+tj, new String[]{}, "xh");
	}
	/**
	 * ��ȡѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsxx(String xh, HttpServletRequest request){
		String sql="select a.*,a.ldmc||'_'||a.qsh||'_'||a.cwh zsqs from xg_view_gygl_new_xszsgl a where sfzs='��' and xh = ? ";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	/**
	 * ���ѧ����Υ����Ϣ
	 * @param xh
	 * @param request
	 * @return
	 */
	public List<String[]> getOneWjxxList(String xh, String wjsj,HttpServletRequest request) {
		String sql=("select wjxn,(select xqmc from xqdzb b where a.wjxq=b.xqdm)xqmc, (select gyjllbdlmc from xg_gygl_new_gyjllbdlb b " +
				"where a.gyjllbdldm=b.gyjllbdldm)||'('||(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' " +
				"wjlb,wjsj,nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c " +
				"where a.cljg=c.gyjlcfdm),'δ����') cljg from ( select * from xg_gygl_new_gyjlb ) a  where xh = ? and shzt='tg'");
				
		return dao.rsToVator(sql, new String[]{xh}, new String[]{"wjxn","xqmc","wjlb","wjsj","cljg"});
	}
	
	/**
	 * �����ʷΥ����Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getWjxxList(GyjlxxglForm myForm,
			HttpServletRequest request,String type) throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�	
					
		String searchTjstr = "";
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+inputV[i];
			}
			searchTjstr+=tj[inputV.length];
		}
		request.setAttribute("searchTjstr", searchTjstr);
		 
		String sql = "select a.*,rownum r from (select a.*, (select xqmc from XQDZB b where b.xqdm=a.wjxq) xqmc," +
				"ldmc||','||qsh||','||cwh||'�Ŵ�' ldqscw from(select  a.*,substr(wjsj,0,10) wjrq ,case when shzt ='wsh' then 'δ���' when shzt='tg' then 'ͨ��' when shzt='btg' then '��ͨ��' end shztxx,b.xm,b.xb," +
				"(select gyjllbdlmc from xg_gygl_new_gyjllbdlb where gyjllbdldm = a.gyjllbdldm)||'('||(" +
				"select gyjllbmc from xg_gygl_new_gyjllbdmb where gyjllbdm = a.gyjllbdm )||')' wjlb, " +
				" case when sfcl='1' or sfcl is null then 'δ����' when sfcl='2' then '�Ѵ���' when sfcl= '3' then '������' end sfclxx, "+
				" case when sfcl='1' or sfcl is null then '' else 'disabled' end clDisabled, "+
				" case when shzt='wsh'  then '' else 'disabled' end shDisabled,(case when sfcl='3' then '������' else nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') end) cljgmc "+
				"from xg_gygl_new_gyjlb a left join "+Base.xsxxb+" b on a.xh = b.xh) a where 1=1 ";
		String[] colList = new String[]{"clDisabled","shDisabled","xh","xm","xb","bjmc","ldqscw","wjsj","wjlb","cljgmc"};
		if("wjcl".equalsIgnoreCase(type)){
			colList = new String[]{"clDisabled","shDisabled","xh","xm","xb","ldqscw","wjsj","wjlb","cljgmc","shztxx"};
		}
		return  commonPageByPjQuery(myForm.getPages(),sql+searchTj+searchTjByUser+" order by wjsj desc ) a",inputV,colList);
	}
	/**
	 * ɾ��Υ����Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public String delWjxx(GyjlxxglForm myForm, HttpServletRequest request) throws SQLException {
		String[] pks = myForm.getDiv_pkValue();
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < pks.length; i ++){
			String[] pk = pks[i].split("!!!splitOne!!!");
			if (pk != null && pk.length > 2) {
				pk = new String[]{pks[i].split("!!!splitOne!!!")[0],pks[i].split("!!!splitOne!!!")[1]};
			}
			list.add(pk);
		}
		String sql = "delete from xg_gygl_new_gyjlb where xh = ? and wjsj = ? ";
		int[] res = dao.runBatch(sql, list);
		for (int i = 0; i < res.length; i++) {
			if (res[i] >= 0)
				return "ɾ��ʧ��";
		}
		return "ɾ���ɹ�";
	}
	/**
	 * �����ʷѧ��Υ����Ϣ
	 * @param pkValue
	 * @param request
	 * @return
	 */
	public Map<String, String> getXswjxx(String[] pkValue,
			HttpServletRequest request) {
		String sql="select * from (select a.wjsj wjrq,a.*,(select xqmc from XQDZB b where a.wjxq=b.xqdm) xqmc,a.ldmc||','||a.qsh||','||a.cwh||'�Ŵ�' zsqs, b.xm," +
				"(select gyjllbdlmc from xg_gygl_new_gyjllbdlb where gyjllbdldm = a.gyjllbdldm) jldl, (select gyjllbmc from xg_gygl_new_gyjllbdmb where gyjllbdm = a.gyjllbdm) jldb," +
				"(select gyjllbdlmc from xg_gygl_new_gyjllbdlb where gyjllbdldm = a.gyjllbdldm)||'('||(" +
				"select gyjllbmc from xg_gygl_new_gyjllbdmb where  gyjllbdm = a.gyjllbdm )||')' wjlb,c.qsdh,nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljgmc," +
				"(case when shzt='tg' then 'ͨ��' when shzt='btg' then '��ͨ��' when shzt='th' then '�˻�' else 'δ���' end) shztmc from xg_gygl_new_gyjlb a " +
				"left join "+Base.xsxxb+" b on a.xh = b.xh left join xg_gygl_new_qsxxb c on a.lddm = c.lddm and a.qsh=c.qsh ) where xh = ? and wjsj = ? and gyjllbdm = ? ";
		return dao.getMapNotOut(sql, pkValue);
	}
	
	/**
	 * ���ͬ����ѧ����Ϣ
	 * @param xh
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getTqsxsxx(String xh,
			HttpServletRequest request) {
		String sql = "select a.lddm,a.qsh,a.cwh, a.xh, a.xm, a.nj, a.xymc, a.zymc, a.bjmc, nvl(d.gllx,'')gllx from xg_view_gygl_new_xszsgl a" +
				" left join xg_view_gygl_new_gyglryb d on a.xh = d.xh  where 1 = 1"+
				"and exists (select 1 from xg_view_gygl_new_xszsgl b where b.xh = ? " +
				"and a.qsh=b.qsh and a.lddm=b.lddm ) and a.xh != ? order by cwh";		
		return dao.getList(sql, new String[]{xh,xh},new String[]{"cwh","xh","xm","nj","xymc","zymc","bjmc","gllx"});
	}
	
	/**
	 * 
	 * @����:�㽭��ý���Ի�������Υ��ʱ���ȡѧ����Ӧ���ʱ��
	 * @���ߣ�cq[���ţ�785]
	 * @���ڣ�2017-6-13 ����05:31:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param wjsj
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjInfo(String xh, String wjsj){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_zjcm_xsqjb where xh = ? and to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')");
		sql.append("between to_char(to_date(qjkssj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') ");
		sql.append("and to_char(to_date(qjjssj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') ");
		
		return dao.getListNotOut(sql.toString(), new String[] { xh, wjsj});
	}
	
	/**
	 * ���湫Ԣ������Ϣ
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public boolean saveGyjlxx(List<String[]> param) throws Exception  {
		
		String sql = "insert into xg_gygl_new_gyjlb(xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,gyjllbdldm,gyjllbdm,wjsj,bz,czr,wjxn,wjxq) " +
		"select xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,?,?,?,?,?,?,? from xg_view_gygl_new_xszsgl where xh = ? ";
		if("70002".equals(Base.xxdm)){
			 sql = "insert into xg_gygl_new_gyjlb(xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,gyjllbdldm,gyjllbdm,wjsj,bz,czr,wjxn,wjxq,fileid) " +
				"select xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,?,?,?,?,?,?,?,? from xg_view_gygl_new_xszsgl where xh = ? ";
		}
		
		boolean flag=false;
		try {
			int[] res= dao.runBatch(sql, param);
			flag=dao.checkBatchResult(res);
		} catch (SQLException e) {
			throw new Exception("������ʧ��");
		}
		return flag;
	}
	
	/**
	 * �޸Ĺ�Ԣ������Ϣ
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public String updateGyjlxx(GyjlxxglForm myForm, HttpServletRequest request) throws Exception {
		String xh = myForm.getXh();
		String ywjsj = myForm.getWjsj();
		String gyjllbdldm = myForm.getJldldm();
		String gyjllbdm = myForm.getJllbdm();
		String wjsj = myForm.getWjsj();
		String bz = myForm.getBz();
		String czr = myForm.getCzr();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String fileid = myForm.getFileid();
		String yjllbdm = request.getParameter("yjllbdm");
		String sql = "update xg_gygl_new_gyjlb set  gyjllbdldm = ? ,gyjllbdm = ? ,wjsj = ? ,bz= ? ,czr= ? ,wjxn = ?,wjxq = ? where " +
				"xh = ? and wjsj = ?  and gyjllbdm = ?";
		String[] input = new String[]{gyjllbdldm,gyjllbdm,wjsj,bz,czr,xn,xq,xh,ywjsj,yjllbdm};
		if("70002".equals(Base.xxdm)){
			sql = "update xg_gygl_new_gyjlb set  gyjllbdldm = ? ,gyjllbdm = ? ,wjsj = ? ,bz= ? ,czr= ? ,wjxn = ?,wjxq = ?,fileid=? where " +
			"xh = ? and wjsj = ?  and gyjllbdm = ?";
			input = new String[]{gyjllbdldm,gyjllbdm,wjsj,bz,czr,xn,xq,fileid,xh,ywjsj,yjllbdm};
		}
		return dao.runUpdate(sql, input)?"����ɹ�":"����ʧ��";
	}
	
	/**
	 * ��ȡ�����Ϣ
	 * @param myForm
	 * @param user
	 * @return
	 */
	public List<String[]>getGyjlShList(GyjlxxglForm myForm,User user, HttpServletRequest request) throws Exception{
		
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�	
					
		String searchTjstr = "";
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+inputV[i];
			}
			searchTjstr+=tj[inputV.length];
		}
		request.setAttribute("searchTjstr", searchTjstr);
		StringBuilder sql=new StringBuilder();
		sql.append("select rownum r,a.* from (");
		sql.append(" select  substr(wjsj,1,10) wjrq,wjsj||'!!shen!!'||a.xh pkValue,a.xh,b.xm, b.xb,bjmc,zsqs,");
		sql.append(" wjsj,cljgmc,b.nj,b.xydm,b.zydm,b.bjdm,a.qsh,a.lddm,a.cwh,a.gyjllbdldm,(select gyjllbdlmc from xg_gygl_new_gyjllbdlb where gyjllbdldm = ");
		sql.append(" a.gyjllbdldm) || '(' || (select gyjllbmc from xg_gygl_new_gyjllbdmb where ");
		sql.append(" gyjllbdm = a.gyjllbdm) || ')' wjlb,gyjllbdm,shr,case when shzt ='wsh' then 'δ���' when shzt='tg' then 'ͨ��' when shzt='btg' then '��ͨ��' end shzt ");
		sql.append("  from (select a.*,a.ldmc||','||a.qsh||','||a.cwh||'�Ŵ�' ");
		sql.append("  zsqs,(case when sfcl='3' then '������' else nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') end) cljgmc from xg_gygl_new_gyjlb a where sfcl is not null and sfcl <>'1' and sfcl <> '3')a ");
		sql.append("  left join"+Base.xsxxb+"b on a.xh = b.xh  order by wjsj desc)a where 1=1 ");
		
		
		return CommonQueryDAO.commonPageByPjQuery(myForm.getPages(), sql.toString()+searchTj+searchTjByUser, inputV, new String[]{"pkValue","xh","xm",
			"xb","zsqs","wjsj","wjlb","cljgmc","shzt"});
		
	}
	
	/**
	 * �޸����״̬
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveShzt(GyjlxxglForm myForm,User user) throws Exception{
		
		CommDAO commDAO=new CommDAO();
		
		String[]pkValue=myForm.getDiv_pkValue();
		String[]sqlArr=new String[pkValue.length];
		
		String shr=user.getUserName();
		String shyj=myForm.getShyj();
		
		for(int i=0;i<sqlArr.length;i++){
			
			sqlArr[i]=" update xg_gygl_new_gyjlb set shzt='"+myForm.getAct()+"' , shr='"+shr+"' , shyj='"+shyj+"' , shsj='"+DateUtils.getCurrTime()+"' where  wjsj||'!!shen!!'||xh||'!!shen!!'||gyjllbdm='"+pkValue[i]+"'";
		}
		
		return commDAO.saveArrDate(sqlArr);
	}
	
	/**
	 * �������洦�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean plclGyjlxx(GyjlxxglForm myForm) throws Exception{
		
		CommDAO commDAO=new CommDAO();
		
		String[]pkValue=myForm.getPkStr().split("!!@@");
		String[]sqlArr=new String[pkValue.length];
		
		for(int i=0;i<sqlArr.length;i++){
			
			sqlArr[i]=" update xg_gygl_new_gyjlb set sfcl='"+myForm.getSfcl()+"', dcqk='"+myForm.getDcqk()+"' , cljg='"+myForm.getCljg()+"' where  xh||wjsj='"+pkValue[i]+"'";
		}
		
		return commDAO.saveArrDate(sqlArr);
	}
	
	/**
	 * �޸Ĵ�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCljg(GyjlxxglForm myForm,User user) throws Exception{
		
		String[]pkValue=myForm.getDiv_pkValue();
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		String dcqk=myForm.getDcqk();
		String cljg=myForm.getCljg();
		
		sql.append(" update xg_gygl_new_gyjlb set dcqk=? ,cljg=? ,shzt='wsh',ylzd1=?");
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			sql.append(",ylzd3=? ");
		}
		inputV.add(dcqk);
		inputV.add(cljg);
		inputV.add(myForm.getYlzd1());
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			inputV.add(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		}
		sql.append(" where  wjsj||'!!shen!!'||xh||'!!shen!!'||gyjllbdm=? ");
		inputV.add(pkValue[0]);
		return dao.runUpdate(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	
	/**
	 * �Ƿ����
	 * @param pkValue
	 * @return
	 */
	public boolean isExists(String[] pkValue) {
		String sql = "select count(1) num from xg_gygl_new_gyjlb where xh=? and wjsj=? and gyjllbdm=?";
		String num = dao.getOneRs(sql, pkValue, "num");
		return  "0".equalsIgnoreCase(num)?false:true;
	}
	
	/**
	 * 
	 * @����: �ǳ��ڹ�ԢΥ���ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-22 ����08:56:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param wjsj
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExists2(String xh,String wjsj) {
		
		String[] arr = wjsj.split("-");
		String wjsjs = arr[0]+""+arr[1]+""+arr[2];// Υ��ʱ��ȥ����-���������ʱ��Ƚ�
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from (select t1.* from ( ");
		sql.append(" select a.xh, a.kssj, a.jssj, b.qjlxmc, c.wjsj, d.gyjllbdlmc, ");
        sql.append(" substr(kssj, '0', '4') || substr(kssj, '6', '2') || substr(kssj, '9', '2') kssjD, ");
        sql.append(" substr(jssj, '0', '4') || substr(jssj, '6', '2') || substr(jssj, '9', '2') jssjD ");
        sql.append(" from xg_rcsw_qjgl_qjjg a ");
        sql.append(" left join xg_rcsw_qjgl_qjlx b ");
        sql.append(" on a.qjlxid = b.qjlxid ");
        sql.append(" left join xg_gygl_new_gyjlb c ");
        sql.append(" on c.xh = a.xh ");
        sql.append(" and c.wjxn = a.xn ");
        sql.append(" and c.wjxq = a.xq ");
        sql.append(" left join xg_gygl_new_gyjllbdlb d ");
        sql.append(" on c.gyjllbdldm = d.gyjllbdldm) t1 ");
        sql.append(" where xh=? and qjlxmc <> '����' and (kssjD <='").append(wjsjs).append("'");
        sql.append(" and jssjD >='").append(wjsjs).append("')) t2");
		
        return dao.getOneRs(sql.toString(), new String[]{xh}, "num");
	}
	
	/**
	 * 
	 * @����: ���ڹ�ԢΥ����ĩ�ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-22 ����08:56:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param wjsj
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExists3(String xh,String wjsj) {
		
		String[] arr = wjsj.split("-");
		String wjsjs = arr[0]+""+arr[1]+""+arr[2];// Υ��ʱ��ȥ����-���������ʱ��Ƚ�
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from (select t1.* from ( ");
		sql.append(" select a.xh, a.kssj, a.jssj, b.qjlxmc, c.wjsj, d.gyjllbdlmc, ");
        sql.append(" substr(kssj, '0', '4') || substr(kssj, '6', '2') || substr(kssj, '9', '2') kssjD, ");
        sql.append(" substr(jssj, '0', '4') || substr(jssj, '6', '2') || substr(jssj, '9', '2') jssjD ");
        sql.append(" from xg_rcsw_qjgl_qjjg a ");
        sql.append(" left join xg_rcsw_qjgl_qjlx b ");
        sql.append(" on a.qjlxid = b.qjlxid ");
        sql.append(" left join xg_gygl_new_gyjlb c ");
        sql.append(" on c.xh = a.xh ");
        sql.append(" and c.wjxn = a.xn ");
        sql.append(" and c.wjxq = a.xq ");
        sql.append(" left join xg_gygl_new_gyjllbdlb d ");
        sql.append(" on c.gyjllbdldm = d.gyjllbdldm) t1 ");
        sql.append(" where xh=? and qjlxmc = '����' and (kssjD <='").append(wjsjs).append("'");
        sql.append(" and jssjD >='").append(wjsjs).append("')) t2");
		
        return dao.getOneRs(sql.toString(), new String[]{xh}, "num");
	}
}

package xsgzgl.gygl.wmqs.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.ahjg.CommanDAO;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

public class GyglWmqsDao extends GyglNewDAO{
	
	/**
	 * ��ȡ�������Ҹ���ά����Ϣ�б�
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> getWmqsgswhInfoList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
//		DAO dao = DAO.getInstance();
		String wmqsbfb=GyglNewInit.gzdx_wmqsbfb.replace("%", "");
		String sql="select d.xydm,d.xymc,d.qsgs,round(qsgs*("+wmqsbfb+"/100)) wmqsbfbgs,nvl(e.wmqsgs,round(qsgs*("+wmqsbfb+"/100))) wmqsgs from "+
			 "( "+
			 "select c.xydm,c.xymc,count(1) qsgs "+
			 "from view_xg_gygl_new_qsxx a  "+
			 "left join xg_gygl_new_gyglryb b  "+
			 "on a.LDDM=b.lddm and a.QSH=b.qsh and b.rzzt='����' "+
			 "left join view_xsjbxx c on b.xh=c.xh where c.xydm is not null "+
			 "group by c.xydm,c.xymc "+
			 ") d left join xg_gygl_new_gzdx_wmqsgsb e on d.xydm=e.xydm";
		String[] colList=new String[]{"xydm","xymc","qsgs","wmqsbfbgs","wmqsgs"};
		return this.commonQueryNotFy(sql,"" ,new String[]{}, colList, model);
	}
	
	/**
	 * �����������Ҹ���ά��
	 * @param model
	 * @return
	 */
	public boolean saveWmqsgs(GyglWmqsForm model){
		CommDAO dao=new CommDAO();
		boolean b=false;
		ArrayList<String> sqls=new ArrayList<String>();
		String[] xydms=model.getXydms();
		String[] wmqsgs=model.getWmqsgss();
		if(xydms==null||wmqsgs==null||xydms.length==0||xydms.length!=wmqsgs.length){
			return false;
		}
		sqls.add("delete from xg_gygl_new_gzdx_wmqsgsb where nd='"+Base.currNd+"'");
		for(int i=0;i<xydms.length;i++){
			sqls.add("insert into xg_gygl_new_gzdx_wmqsgsb(nd,xydm,wmqsgs) values('"+Base.currNd+"','"+xydms[i]+"','"+wmqsgs[i]+"')");
		}
		try {
			b= dao.saveArrDate(sqls.toArray(new String[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * ������������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveWmqssqxx(GyglWmqsForm m) throws Exception{
		boolean b=false;
		DAO dao=new DAO();
		//����һ������ƽ����
		String sql="select round(avg(wsffs),2) wspjf from gygl_wsjc_wsfwhb where nd=? and jcld=? and jcqs=?";
		String wspjf=dao.getOneRs(sql, new String[]{Base.currNd,m.getLddm(),m.getQsh()}, "wspjf");
		//��ѯһ���Ƿ�������
		sql="select count(1) num from xg_gygl_new_gzdx_wmqssqb where nd=? and lddm=? and qsh=?";
		String num=dao.getOneRs(sql, new String[]{Base.currNd,m.getLddm(),m.getQsh()}, "num");
		
		if("0".equals(num)){//û�м�¼ֱ�Ӳ���
			sql="insert into xg_gygl_new_gzdx_wmqssqb(nd,lddm,qsh,wspjf,sqsm,sqr,sqsj) values(?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
			b=dao.runUpdate(sql, new String[]{Base.currNd,m.getLddm(),m.getQsh(),wspjf,m.getSqsm(),m.getSqr()});
		}else{//��¼�Ѵ��ڽ��и���
			sql="update xg_gygl_new_gzdx_wmqssqb set wspjf=?,sqsm=? where nd=? and lddm=? and qsh=?";
			b=dao.runUpdate(sql, new String[]{wspjf,m.getSqsm(),Base.currNd,m.getLddm(),m.getQsh()});
		}
		return b;
	}
	
	/**
	 * ��ȡ��������������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getWmqssqxx(GyglWmqsForm model,HttpServletRequest request){
		DAO dao=new DAO();
		String sql;
		if(!"teacher".equals(model.getUserType())){//�����ѧ�������ȹ�ȥ���ڵ�¥��������
			sql="select * from xg_gygl_new_gyglryb where qsh!='#' and rzzt='����' and xh=?";
			HashMap<String,String> r=dao.getMap(sql, new String[]{model.getSqr()}, new String[]{"lddm","qsh"});
			model.setPkValue(r.get("lddm")+r.get("qsh"));
		}
		sql="select round(avg(wsffs),2) wspjf from gygl_wsjc_wsfwhb where nd=? and jcld||jcqs=?";
		String wspjf=dao.getOneRs(sql, new String[]{Base.currNd,model.getPkValue()}, "wspjf");
		sql="select a.*,b.sqsm," +
		" (select count(1) from xg_gygl_new_cwxxb x where a.lddm=x.lddm and a.qsh=x.qsh and a.xh is not null) qsrs " +
		" from view_xg_gygl_new_cwxx a left join xg_gygl_new_gzdx_wmqssqb b " +
		"on a.lddm=b.lddm and a.qsh=b.qsh where a.lddm||a.qsh=?";
		HashMap<String,String> rs= dao.getMapNotOut(sql, new String[]{model.getPkValue()});
		if(rs!=null&&!rs.isEmpty()){
			rs.put("wspjf", wspjf);
		}
		
		//��ȡ���ö�Ӧ���ҵ�ѧ���б�
		sql="select a.* from view_xsjbxx a ,xg_gygl_new_cwxxb b where a.xh=b.xh and b.lddm||b.qsh=?";
		List<HashMap<String,String>> xsList=dao.getListNotOut(sql, new String[]{model.getPkValue()});
		request.setAttribute("xsList", xsList);
		return rs;
	}
	
	/**
	 * ��ȡ������������б�
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getWmqsshList(GyglWmqsForm model,HttpServletRequest request,User user) throws Exception{

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "","xydm", null); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
			request.setAttribute("shtype", "fdy");
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
			request.setAttribute("shtype", "xx");
		}
		
		String sql="select rownum r,a.lddm||'!!one!!'||a.qsh pk,a.* from view_xg_gygl_new_gzdx_wmqssq a where 1=1 ";
				
		String[] colList=new String[]{"pk","ldmc","qsh","qsrs","wspjf","sqr","fdyshzt","xxshzt"};
		return this.commonQuery(sql, searchTj+searchTjQx, inputV, colList, model);
	}
	
	/**
	 * �����������������Ϣ
	 * @param myForm
	 * @return
	 */
	public boolean saveWmqsshxx(GyglWmqsForm myForm,HttpServletRequest request,User user) throws Exception{
		boolean b=false;
		String[] pk=myForm.getPrimarykey_checkVal();
		if(pk==null||pk.length==0){
			return b;
		}
		//Ȩ�޿���
		String type="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "","xydm", null); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
				
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy.trim())){//�û�Ϊ��Ԣ����Ա
			type = "fdy";
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			type = "xx";
		}		
		
		if("xx".equals(type)&&"ͨ��".equals(myForm.getShzt())){
			DAO dao=new DAO();
			String wmqsbfb=GyglNewInit.gzdx_wmqsbfb.replace("%", "");//�������Ұٷֱ�
			String sql="select d.xydm,d.xymc,d.qsgs,round(qsgs*("+wmqsbfb+"/100)) wmqsbfbgs,nvl(e.wmqsgs,round(qsgs*("+wmqsbfb+"/100))) wmqsgs from "+
			"( "+
			"select c.xydm,c.xymc,count(1) qsgs "+
			"from view_xg_gygl_new_qsxx a  "+
			"left join xg_gygl_new_gyglryb b  "+
			"on a.LDDM=b.lddm and a.QSH=b.qsh and b.rzzt='����' "+
			"left join view_xsjbxx c on b.xh=c.xh where xydm is not null "+
			"group by c.xydm,c.xymc "+
			") d left join xg_gygl_new_gzdx_wmqsgsb e on d.xydm=e.xydm " +
			"where d.xydm in(select xydm from view_xg_gygl_new_gzdx_wmqssq where lddm||'!!one!!'||qsh=?)";
			String wmqsgs=dao.getOneRs(sql, new String[]{pk[0]}, "wmqsgs");//�������Ҹ���
			if(wmqsgs==null||"".equals(wmqsgs)){
				request.setAttribute("message", "������û�з������ҳ���");
				return false;
			}
//			sql="select count(1) wmqstgs from view_xg_gygl_new_gzdx_wmqssq a where a.xxshzt='ͨ��' and "+
//			"a.xydm in(select xydm from view_xg_gygl_new_gzdx_wmqssq where lddm||'!!one!!'||qsh=?)";
			sql="select count(1) wmqstgs from view_xg_gygl_new_gzdx_wmqssq a where a.xxshzt='ͨ��' and "+
				"exists (select 1 from view_xg_gygl_new_gzdx_wmqssq x where x.lddm||'!!one!!'||x.qsh=? and x.xydm=a.xydm)";
			String wmqstgs=dao.getOneRs(sql, new String[]{pk[0]}, "wmqstgs");
			if(Integer.parseInt(wmqsgs)-Integer.parseInt(wmqstgs)<=0){
				request.setAttribute("message", "����������ѧԺ�����������ͨ�����Ѵﵽ�趨ֵ��");
				return false;
			}
			
		}
//		String sql="update xg_gygl_new_gzdx_wmqssqb set "+type+"shzt=?,"+type+"shsj=?,"+type+"shbz=? where nd=? and lddm||qsh=?";
		ArrayList<String> sqls=new ArrayList<String>();
		for(int i=0;i<pk.length;i++){
			if("fdy".equals(type)){//����Ա
				sqls.add("update xg_gygl_new_gzdx_wmqssqb set fdyshzt='"+myForm.getShzt()+"',fdyshsj=to_char(sysdate,'yyyy-mm-dd'),"+
						"fdyshbz='"+myForm.getShbz()+"',fdyshr='"+user.getUserName()+"' where nd='"+Base.currNd+"' and lddm||'!!one!!'||qsh='"+pk[i]+"'");
			}else{//ѧУ
				sqls.add("update xg_gygl_new_gzdx_wmqssqb set xxshzt='"+myForm.getShzt()+"',xxshsj=to_char(sysdate,'yyyy-mm-dd'),"+
						"xxshbz='"+myForm.getShbz()+"',xxshr='"+user.getUserName()+"' where nd='"+Base.currNd+"' and lddm||'!!one!!'||qsh='"+pk[i]+"'");
			}
		}
		return this.saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * �����������������Ϣ
	 * @param myForm
	 * @return
	 */
	public boolean delWmqsshxx(GyglWmqsForm myForm) throws Exception{
		boolean b=false;
		String[] pk=myForm.getPrimarykey_checkVal();
		if(pk==null||pk.length==0){
			return b;
		}
		ArrayList<String> sqls=new ArrayList<String>();
		for(int i=0;i<pk.length;i++){
			sqls.add("delete from  xg_gygl_new_gzdx_wmqssqb where nd='"+Base.currNd+"' and lddm||qsh='"+pk[i]+"'");
		}
		return this.saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ�������ҹ����б�
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getWmqsglList(GyglWmqsForm model,HttpServletRequest request) throws Exception{
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		String sql="select rownum r,a.lddm||a.qsh pk,a.* from view_xg_gygl_new_gzdx_wmqssq a where 1=1 ";
		String[] colList=new String[]{"pk","ldmc","qsh","qsrs","wspjf","sqr","fdyshzt","xxshzt"};
		return this.commonQuery(sql, searchTj+searchTjByGyfdy, inputV, colList, model);
	}
	
	/**
	 * ��ȡ�������Ұٷֱ�
	 * @param csName
	 * @return
	 */
	public List<HashMap<String,String>> getWmqsbfb(String[] csName){
		if(csName==null||csName.length==0){
			return null;
		}
		String sql="select csdm,csmc,csz,bz from xg_gygl_new_jbszb where csdm=?";
		return new DAO().getList(sql, new String[]{"gzdx_wmqsbfb"}, new String[]{"csdm","csmc","csz","bz"});
	}
	
	/**
	 * �����������Ұٷֱ�
	 * @param csz
	 * @return
	 */
	public boolean saveWmqsbfb(String csz){
		boolean b=false;
		DAO dao=new DAO();
		String[] inputV;
		String sql="select count(1) num from xg_gygl_new_jbszb where csdm=?";
		String num=dao.getOneRs(sql, new String[]{"gzdx_wmqsbfb"}, "num");
		if("0".equals(num)){
			sql="insert into xg_gygl_new_jbszb values(?,?,?,?)";
			inputV=new String[]{"gzdx_wmqsbfb","���ݴ�ѧ�������Ұٷֱ�",csz,"����ֵΪXX%"};
		}else{
			sql="update xg_gygl_new_jbszb set csz=? where csdm=?";
			inputV=new String[]{csz,"gzdx_wmqsbfb"};
		}
		try {
			 b=dao.runUpdate(sql, inputV);
			 if(b){
				GyglNewInit.gzdx_wmqsbfb=csz; 
			 }
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * ��ȡ¥����Ϣ�б�
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getLdxxList(HttpServletRequest request){
		GyglNewService gyglNewService = new GyglNewService();
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgl.do");
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		String sql="select lddm,ldmc from xg_gygl_new_ldxxb where 1=1 "+searchTjByGyfdy;
		return new DAO().getList(sql, new String[]{}, new String[]{"lddm","ldmc"});
	}

}

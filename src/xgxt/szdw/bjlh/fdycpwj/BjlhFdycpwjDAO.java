package xgxt.szdw.bjlh.fdycpwj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zfsoft.utils.StringUtil;
import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhForm;
import xgxt.utils.CommonQueryDAO;

public class BjlhFdycpwjDAO extends CommDAO {
	
	/**
	 * ��ȡ�����ʾ��б�
	 * @param model
	 * @return
	 */
	public List<String[]> getCpwjList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		List<String[]> list = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.* from (select a.*,(select count(*) from xg_szdw_bjlh_fdycpwjdab where wjid = a.wjid) num from xg_szdw_bjlh_fdycpwjb a)a where 1=1 ");
		//Ȩ�޿���
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//ѧԺ�û�
		
		String[] outputs = new String[]{"wjid","xn", "wjid", "wjmc", "sfqy", "fbrq", "fbr","num"};
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, outputs, model);
		
		return list;
		
	}
	
	/**
	 * ��ȡ�����ʾ�����¼
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjOne(BjlhFdycpwjForm model){
		DAO dao=new DAO(); 
		String sql="select * from xg_szdw_bjlh_fdycpwjb where wjid=?";
		return dao.getMapNotOut(sql, new String[]{model.getWjid()});
	}
	
	/**
	 * ��������ʾ���Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCpwjInfo(BjlhFdycpwjForm model,String type) throws Exception{
		String sql;
		String[] inputV;
		DAO dao=new DAO();
		if("add".equals(type)){
			sql="insert into xg_szdw_bjlh_fdycpwjb(xn,wjid,wjmc,sfqy,fbrq,fbr) " +
					"values(?,seq_bjlh_fdycpwj.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
			inputV=new String[]{model.getXn(),model.getWjmc(),"��",model.getFbr()};
		}else{
			sql="update xg_szdw_bjlh_fdycpwjb set wjmc=? where wjid=?";
			inputV=new String[]{model.getWjmc(),model.getWjid()};
		}
		return dao.runUpdate(sql, inputV);
	}
	
	/**
	 * ɾ�������ʾ���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		DAO dao=new DAO();
		String sql="delete from xg_szdw_bjlh_fdycpwjb where wjid=?";
		boolean b=dao.runUpdate(sql,new String[]{model.getWjid()});
		if(b){
			sql ="delete from xg_szdw_bjlh_fdycpwjstb where wjid=?";
			b=dao.runUpdate(sql,new String[]{model.getWjid()});
		}
		if(b){
			sql ="delete from xg_szdw_bjlh_fdycpwjstxxb a where exists (select 1 from xg_szdw_bjlh_fdycpwjstb b where a.stid=b.stid and b.wjid=?)";
			b=dao.runUpdate(sql,new String[]{model.getWjid()});
		}
		return b?"�����ɹ���":"����ʧ��";
	}
	
	/**
	 * ��֤�����ʾ�Ȩ��
	 * @param model
	 * @return
	 */
	public String checkCpwjQx(BjlhFdycpwjForm model){
		String msg="";
		String sql="select count(1) num from xg_szdw_bjlh_fdycpwjdab where wjid=?";
		String num=new DAO().getOneRs(sql, new String[]{model.getWjid()},"num");
		if(!"0".equals(num)){
			msg="�ʾ�����������";
		}
		return msg;
	}
	
	/**
	 * ���Ʋ����ʾ���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean copyCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		String wjid=model.getWjid();
		String sql="select * from xg_szdw_bjlh_fdycpwjstb where wjid=?";
		List<HashMap<String,String>> stList=getCpwjStList(model);//�����б�
		ArrayList<String> sqls=new ArrayList<String>();
		//�����ʾ������Ϣ
		sqls.add("insert into xg_szdw_bjlh_fdycpwjb(wjid,wjmc,sfqy,fbrq,fbr,xn) " +
				"select seq_bjlh_fdycpwj.nextval,'"+model.getWjmc()+"','��',to_char(sysdate,'yyyy-mm-dd'),'"+
				model.getFbr()+"','"+model.getXn()+"' " +
				"from xg_szdw_bjlh_fdycpwjb a where a.wjid='"+wjid+"'");
		if(stList!=null&&stList.size()>0){
			String stid;//����id
			for(int i=0;i<stList.size();i++){
				stid=stList.get(i).get("stid");
				//��������
				sqls.add("insert into xg_szdw_bjlh_fdycpwjstb(stid,wjid,stmc,stlx,xssx,dhxxgs,xxgs) "+
                         "select seq_bjlh_fdycpwj.nextval,(select max(to_number(wjid)) from xg_szdw_bjlh_fdycpwjb)," +
                         "stmc,stlx,xssx,dhxxgs,xxgs from xg_szdw_bjlh_fdycpwjstb a where a.wjid='"+wjid+"' and a.stid='"+stid+"'");
				//��������ѡ��
				sqls.add("insert into xg_szdw_bjlh_fdycpwjstxxb(xxid,stid,xxnr,xssx) " +
						"select seq_bjlh_fdycpwj.nextval,(select max(to_number(stid)) from xg_szdw_bjlh_fdycpwjstb)," +
						"xxnr,xssx from xg_szdw_bjlh_fdycpwjstxxb a where stid='"+stid+"'");
			}
		}
		boolean b=new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * �Ƿ����ò����ʾ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String sfqyCpwj(BjlhFdycpwjForm model) throws Exception{
		DAO dao=new DAO();
		String msg="";
		String sql="";
		if("��".equals(model.getSfqy())){
			sql="select count(1) num from xg_szdw_bjlh_fdycpwjb a where a.sfqy='��' " +
				"and exists (select 1 from xg_szdw_bjlh_fdycpwjb x where x.xn=a.xn and x.wjid=?)";
			if(!"0".equals(dao.getOneRs(sql, new String[]{model.getWjid()}, "num"))){
				msg="��ѧ�������ʾ����ã�";
				return msg;
			}
		}
		sql="update xg_szdw_bjlh_fdycpwjb set sfqy=? where wjid=?";
		msg=dao.runUpdate(sql, new String[]{model.getSfqy(),model.getWjid()})?"�����ɹ���":"����ʧ�ܣ�";
		return msg;
	}
	
	/**
	 * ��ȡ�����ʾ������б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getCpwjStList(BjlhFdycpwjForm model){
		DAO dao=new DAO();
		String sql="select a.*,rownum r from (select a.*," +
				"(case when stlx='1' then '��ѡ��' when stlx='2' then '�����' else '' end) stlxmc " +
				"from xg_szdw_bjlh_fdycpwjstb a where wjid=? order by to_number(xssx))a";
		return dao.getListNotOut(sql, new String[]{model.getWjid()});
	}
	
	/**
	 * ��������ļ�������Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean saveCpwjStxx(BjlhFdycpwjForm model,String type) throws Exception{
		boolean b=false;
		DAO dao=new DAO();
		ArrayList<String> sqls=new ArrayList<String>();
		String sql;
		if("add".equals(type)){//����
			sql="select seq_bjlh_fdycpwj.nextval stid from dual";
			String stid=dao.getOneRs(sql, new String[]{}, "stid");
			model.setStid(stid);
			sqls.add("insert into xg_szdw_bjlh_fdycpwjstb(stid,wjid,stmc,stlx,xssx,dhxxgs,xxgs) " +
					"values('"+stid+"','"+model.getWjid()+"','"+model.getStmc()+"','"+model.getStlx()+"','"+
					model.getXssx()+"','"+model.getDhxxgs()+"','"+model.getXxgs()+"')");
			if("1".equals(model.getStlx())){//��ѡ�����ѡ��
				for(int i=0;i<model.getXxnrs().length;i++){
					sqls.add("insert into xg_szdw_bjlh_fdycpwjstxxb(xxid,stid,xxnr,xssx) " +
							"values(seq_bjlh_fdycpwj.nextval,'"+stid+"','"+model.getXxnrs()[i]+"','"+i+"')");
				}
			}
		}else if("update".equals(type)){//�޸�
			sqls.add("update xg_szdw_bjlh_fdycpwjstb set stmc='"+model.getStmc()+"',stlx='"+model.getStlx()+"',xssx='"+
					model.getXssx()+"',dhxxgs='"+model.getDhxxgs()+"',xxgs='"+model.getXxgs()+"' where wjid='"+
					model.getWjid()+"' and stid='"+model.getStid()+"'");
			sqls.add("delete from xg_szdw_bjlh_fdycpwjstxxb where stid='"+model.getStid()+"'");
			if("1".equals(model.getStlx())){//��ѡ�����ѡ��
				for(int i=0;i<model.getXxnrs().length;i++){
					sqls.add("insert into xg_szdw_bjlh_fdycpwjstxxb(xxid,stid,xxnr,xssx) " +
							"values(seq_bjlh_fdycpwj.nextval,'"+model.getStid()+"','"+model.getXxnrs()[i]+"','"+i+"')");
				}
			}
		}else if("delete".equals(type)){//ɾ��
			sqls.add("delete from xg_szdw_bjlh_fdycpwjstb where stid='"+model.getStid()+"'");
			sqls.add("delete from xg_szdw_bjlh_fdycpwjstxxb where stid='"+model.getStid()+"'");
		}
		return new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ�����ʾ�������Ϣ�б�
	 * @param model
	 * @return
	 */
//	public List<HashMap<String,String>> getCpwjStxxList(BjlhFdycpwjForm model){
//		String sql="select * from xg_szdw_bjlh_fdycpwjstb where wjid=? order by";
//		return new DAO().getListNotOut(sql, new String[]{model.getWjid()});
//	}
	
	/**
	 * ��ȡѡ���б�
	 * @param model
	 * @param type
	 * @return
	 */
	public List<HashMap<String,String>> getXxlist(BjlhFdycpwjForm model,String type){
		String sql;
		String[] input;
		if("st".equals(type)){//��ȡһ�������ѡ��
			sql="select a.*,chr(ascii('A')+xssx) zfbm from xg_szdw_bjlh_fdycpwjstxxb a where stid=? order by to_number(xssx)";
			input=new String[]{model.getStid()};
		}else{//��ȡ�ʾ����������ѡ��
			sql="select a.*,chr(ascii('A')+a.xssx) zfbm from xg_szdw_bjlh_fdycpwjstxxb a,xg_szdw_bjlh_fdycpwjstb b " +
				"where a.stid=b.stid and b.wjid=? order by to_number(b.xssx),to_number(a.xssx)";
			input=new String[]{model.getWjid()};
		}
		return new DAO().getListNotOut(sql, input);
	}
	
	/**
	 * ��ȡ�����ʾ����ⵥ����¼
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjStxxOne(BjlhFdycpwjForm model){
		DAO dao=new DAO(); 
		String sql="select * from xg_szdw_bjlh_fdycpwjstb where wjid=? and stid=?";
		return dao.getMapNotOut(sql, new String[]{model.getWjid(),model.getStid()});
	}
	
	/**
	 * ��ȡ�ʾ�����𰸼���
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getWjStDaList(BjlhFdycpwjForm model){
		String sql="select a.stid||'-'||nvl(a.xxid,'') stxx,a.wbda,b.stlx from xg_szdw_bjlh_fdycpwjdab a " +
				"left join xg_szdw_bjlh_fdycpwjstb b on a.stid=b.stid " +
				"where a.xh=? and a.wjid=? and a.fdyid=?";
		return new DAO().getList(sql, new String[]{model.getXh(),model.getWjid(),model.getFdyid()}, new String[]{"stxx","wbda","stlx"});
	}
	
	/**
	 * ��ȡѧ���Ƿ��������ʾ�
	 * @param model
	 * @return
	 */
	public boolean getXsWjstSfzd(BjlhFdycpwjForm model){
		String sql="select count(1) num from xg_szdw_bjlh_fdycpwjdab a where a.xh=? and a.wjid=?";
		String num= new DAO().getOneRs(sql, new String[]{model.getXh(),model.getWjid()},"num");
		return "0".equals(num)?false:true;
	}
	
	public List<HashMap<String,String>> getWjstTjInfoList(BjlhFdycpwjForm model){
		String sql="select a.*,chr(ascii('A')+a.xxxssx) zfbm,b.dtrs,nvl(c.xxrs,'0') xxrs,round(nvl(c.xxrs,'0')/b.dtrs,4)*100||'%' xxrsbfb from "+
			"(select a.xxid,a.stid,a.xxnr,a.xssx xxxssx,b.xssx stxssx,b.wjid from xg_szdw_bjlh_fdycpwjstxxb a,xg_szdw_bjlh_fdycpwjstb b "+ 
			"where b.wjid=? and a.stid=b.stid) a "+
			"left join  "+
			"(select wjid,stid,count(1) dtrs from xg_szdw_bjlh_fdycpwjdab a where wjid=? and fdyid=? and xxid is not null "+
			"group by wjid,stid) b on a.wjid=b.wjid and a.stid=b.stid "+
			"left join  "+
			"(select wjid,stid,xxid,count(1) xxrs from xg_szdw_bjlh_fdycpwjdab a where wjid=? and fdyid=? and xxid is not null "+
			"group by wjid,stid,xxid) c "+
			"on a.wjid=c.wjid and a.stid=c.stid and a.xxid=c.xxid "+
			"order by to_number(a.stxssx),to_number(a.xxxssx)";
		return new DAO().getListNotOut(sql, new String[]{model.getWjid(),model.getWjid(),model.getFdyid(),model.getWjid(),model.getFdyid()});
//		return new DAO().getListNotOut(sql,new String[]{});
	}
	
	/**
	 * ��ȡ����Ա��Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String xh){
//		String sql="select zgh,xm from fdybjb a left join yhb b on a.zgh=b.yhm "+
//				   "where b.qx='1' and exists (select 1 from view_xsjbxx x where x.bjdm=a.bjdm and x.xh=?)";
		
		String sql="select a.zgh,c.xm,b.xymc from (select * from fdybjb union select * from bzrbbb) a,view_xsjbxx b,yhb c where a.bjdm=b.bjdm and a.zgh=c.yhm and b.xh=?";
		HashMap<String,String> fdyxx= new DAO().getMapNotOut(sql, new String[]{xh});
		if(fdyxx==null){
			fdyxx=new HashMap<String, String>();
		}
		return fdyxx;
	}
	
	// ��ø���Ա������δ�е�ѧ��
	public List<HashMap<String, String>> getAddXnList() {
		List<HashMap<String, String>> allList = Base.getXnndList();
		List<HashMap<String, String>> list = new DAO().getList(
				"select xn from xg_szdw_bjlh_fdycpwjb", new String[] {},
				new String[] { "xn" });
		;
		return removeXn(allList, list);
	}

	private List<HashMap<String, String>> removeXn(
			List<HashMap<String, String>> allList,
			List<HashMap<String, String>> list) {
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < allList.size(); i++) {
			HashMap<String, String> allMap = allList.get(i);
			boolean res = true; 
			for (int j = 0; j < list.size(); j++) {
				if (allMap.get("xn").equalsIgnoreCase(list.get(j).get("xn"))) {
					res= false;
					break;
				}
			}
			if(res)
			{
				result.add(allMap);
			}
		}
		return result;

	}
	
	/**
	 * ��ȡ�����ʾ�ͳ�Ʋ�ѯ�б�
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getCpwjTjQueryList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String sql="select rownum r,a.xn||'!!one!!'||a.wjid||'!!one!!'||a.zgh pk,a.* from ("+
					"	select a.xn,a.bmdm,a.bmmc,a.zgh,a.xm,a.fdybjzrs,b.wjid,case when b.drrs >0 then b.drrs else 0 end drrs from ( "+
					"    select xx.xn,yy.* from (select distinct xn from xg_szdw_bjlh_fdycpwjb) xx,"+
					"    (select a.zgh,b.xm,b.szbm bmdm,b.bmmc,count(1) fdybjzrs from fdybjb a left join view_fdyxx b on a.zgh = b.zgh   "+
					"    left join view_xsjbxx c on a.bjdm=c.bjdm "+
					"    group by a.zgh,b.xm,b.szbm,b.bmmc) yy "+
					"	) a "+
					"left join "+ 
					"(select c.xn,a.wjid,fdyid,count(distinct a.xh) drrs from xg_szdw_bjlh_fdycpwjdab a," +
					"view_xsjbxx b,xg_szdw_bjlh_fdycpwjb c where a.xh=b.xh and a.wjid=c.wjid group by c.xn,a.wjid,fdyid) b "+
					"on a.zgh=b.fdyid and a.xn=b.xn "+ 
//					"left join xg_szdw_bjlh_fdycpwjb c on c.wjid=b.wjid " +
				") a where 1=1 ";
		//Ȩ�޿���
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//ѧԺ�û�
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//����Ա�û�
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		
		String[] outputs=new String[]{"pk","xn","zgh","xm","bmmc","fdybjzrs","drrs"};
		List<String[]> list = CommonQueryDAO.commonQuery(sql, searchTj+searchTjQx, inputV, outputs, model);
		return list;
	}
	
	/**
	 * ��ò������ñ��е�Ĭ������
	 */
	public HashMap<String, String> getMrsz() {
		DAO dao=new DAO(); 
		String sql = "select a.*,b.*,c.* from XG_SZDW_BJLH_CSSZB a," +
				"(select b.wjid,b.wjmc,b.sfqy,b.fbr,b.fbrq from xg_szdw_bjlh_fdycpwjb b where b.xn=(select xn from XG_SZDW_BJLH_CSSZB) and b.sfqy = '��') b," +
				"(select b.khbid from xg_szdw_bjlh_fdykhb b where b.xn = (select xn from XG_SZDW_BJLH_CSSZB) and b.sfqy = '��' and b.pfdx='xs') c";
		return dao.getMapNotOut(sql, new String[]{});
	}

	/** 
	 * @����:��ȡѧ����Ҫ���˵ĸ���Աlist
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-13 ����10:08:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCpFdyList(BjlhFdycpwjForm myForm,
			String lx) {
		DAO dao = DAO.getInstance();
		String query="";
		if(!StringUtil.isNull(lx)){
			query=" b.lx='"+lx+"'";
		}
		StringBuilder sql=new StringBuilder();
		sql.append("select c.zgh zgh,b.lx, c.xm, c.szbm bmdm, c.bmmc,(case when d.fdyid is null ");
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			sql.append(" or e.fdyid is null");
		}
		sql.append(" then '��' else '��' end)sfycp from view_xsjbxx a");
		sql.append("  left join (select a.*, '����Ա' lx");
		sql.append("               from fdybjb a");
		sql.append("             union");
		sql.append("             select b.*, '������' lx from bzrbbb b) b");
		sql.append("    on a.bjdm = b.bjdm");
		sql.append("  left join view_fdyxx c");
		sql.append("    on b.zgh = c.zgh");
		sql.append("  left join (select distinct fdyid from xg_szdw_bjlh_fdycpwjdab where wjid=? and xh=?)d");
		sql.append("    on c.zgh=d.fdyid");
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			sql.append(" left join (select distinct fdyid from xg_szdw_bjlh_fdykhpfb where khbid=? and yhm=?) e ");
			sql.append("    on c.zgh=e.fdyid");
		}
		sql.append(" where a.xh = ?");
		sql.append(query);
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			return dao.getListNotOut(sql.toString(), new String[]{myForm.getWjid(),myForm.getXh(),myForm.getKhbid(),myForm.getXh(),myForm.getXh()});
		}else{
			return dao.getListNotOut(sql.toString(), new String[]{myForm.getWjid(),myForm.getXh(),myForm.getXh()});
		}
	}
	
	/**
	 * ��ȡ�����ʾ��Ƿ��������ʾ�
	 * @param model
	 * @return
	 */
	public boolean getCpwjSfzd(BjlhFdycpwjForm myForm){
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from xg_szdw_bjlh_fdycpwjdab a where wjid = ? and xh = ? and fdyid = ?";
		String num= dao.getOneRs(sql, new String[]{myForm.getWjid(),myForm.getXh(),myForm.getFdyid()},"num");
		return "0".equals(num)?false:true;
	}

}

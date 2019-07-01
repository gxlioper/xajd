package xsgzgl.gygl.cwgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.tsgl.TsglForm;

public class CwglDAO extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	
	/**
	 * ���洲λ
	 * @param model
	 * @return
	 */
	public boolean saveCwwh(CwglModel model){
		boolean flag = false;
		
		String sql = "insert into xg_gygl_new_cwxxb(lddm,qsh,cwh,sfbl,bz) values(?,?,?,?,?)";
		
		String[] input = new String[]{model.getLddm(), model.getQsh(), model.getCwh(), 
									  model.getSfbl(), model.getBz()};
		
		try {
			flag = dao.runUpdate(sql, input);
			if(flag){
				sql="update xg_gygl_new_qsxxb a set " +
					"cws=(select count(1) from xg_gygl_new_cwxxb x where x.lddm=a.lddm and x.qsh=a.qsh) " +
					"where a.lddm=? and a.qsh=?";
				dao.runUpdate(sql, new String[]{model.getLddm(), model.getQsh()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean updateCwqsh(String qsh1 , String sqh2 , String lddm) throws Exception{
		String sql = "update xg_gygl_new_cwxxb set qsh = ? where qsh = ? and lddm = ?";
		return dao.runUpdate(sql, new String[]{qsh1 , sqh2 , lddm});
	}
	
	/**
	 * �޸Ĵ�λ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean updateCwwh(String pk,CwglModel model){
		boolean flag = false;
		
		String xh = model.getXh();
		String xydm = model.getXydm();
		String nj = model.getNj();
		String rzsj=model.getRzsj();
		String rzczr=model.getCzr();
		String bz=model.getBz();
		String rzyy=model.getRzyy();
		String bjdm=model.getBjdm();
		String zydm=model.getZydm();
		String sql = "update xg_gygl_new_cwxxb set xh=?,xydm=?,nj=?,rzsj=?,rzczr=?,bz=?,rzyydm=?,zydm=?,bjdm=?"+
		             " where lddm||qsh||cwh=?";
		
		String[] input = new String[]{xh,xydm,nj,rzsj,rzczr,bz,rzyy,zydm,bjdm, pk};
		
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * @��������ס
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��2��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @param model
	 * @return
	 * boolean ��������
	 * @throws Exception 
	 */
	public boolean ruzhu(String pk,CwglModel model) throws Exception{
		String sql="select a.xh,a.lddm,a.ldmc,a.qsh,a.cwh,a.rzsj,a.rzczr,b.nj,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.xm,b.xb,a.rzyydm " +
				"from view_xg_gygl_new_cwxx a left join view_xsxxb b on a.xh=b.xh where lddm||qsh||cwh=?";
		Map<String,String> xscw=dao.getMapNotOut(sql, new String[]{pk});
		String czsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
		
		String ruzhu_sql="insert into XG_GYGL_NEW_SSYD_SSYDJG ( XH, CZSJ, XN, XQ, SSYDLX, TSTZYY, TSTZSJ, BZ, YDQLDDM, "
				+ "YDQLDMC, YDQQSH, YDQCWH, YDHLDDM, YDHLDMC, YDHQSH, YDHCWH, SFCWCSH, SJLY, SSYDSQID, "
				+ "YDQQSRZSJ, YDHNJ, YDHXYDM, YDHZYDM, YDHBJDM, TJSQRXM, FJXX, JFLX, ZSFJE) values "
				+ "( ?, ?, ?, ?, ?, ?, ?, null, null, null, null, null, ?, ?, ?, ?, null, ?, null, null, null, null, null, null, ?, null, null, null) ";
		String[] input = new String[]{xscw.get("xh"),czsj,Base.currXn,Base.currXq,"03",xscw.get("rzyydm"),xscw.get("rzsj"),
				xscw.get("lddm"),xscw.get("ldmc"),xscw.get("qsh"),xscw.get("cwh"),"0",xscw.get("rzczr"),};
		
		return dao.runUpdate(ruzhu_sql, input);
	}
	
	/**
	 * ��ѯ��λ��Ϣ
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw(CwglModel model,HttpServletRequest request) throws Exception{
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		//��������ְҵ����ѧԺ���Ի��жϣ���λ�ſ���Ϊ����
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1";
		}else if ("10698".equals(Base.xxdm)) {
			sb = "order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1";
		}else{
			sb = "order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a where 1=1";
		}
//		sql.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm," +
//				"(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
//		"from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1 ");
		sql.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,b.xz,b.pycc,b.sjhm," +
				"(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis "+
				"from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh "+sb+" ");
							
		String[] output = null;
		if("xydm".equals(GyglNewInit.CWFPDX)){//��λ�������ΪѧԺʱ������ʾ�����༶����
			output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "xh", "xm","sfbl"};
		}else if("zydm".equals(GyglNewInit.CWFPDX)){//��λ�������Ϊרҵʱ����ʾ����רҵ����
			output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc","zymc", "xh", "xm","sfbl"};
		}else {
			output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc","bjmc", "xh", "xm","sfbl"};
		}
		
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), searchTj , inputV, output, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ����ɾ����λ��Ϣ
	 * @param params
	 * @return
	 */
	public boolean delCw(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_gygl_new_cwxxb where lddm||qsh||cwh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
			if(flag){
				String sqlstr="update xg_gygl_new_qsxxb a set " +
							  "cws=(select count(1) from xg_gygl_new_cwxxb x where x.lddm=a.lddm and x.qsh=a.qsh) ";
				dao.runUpdate(sqlstr, new String[]{});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * ͨ��������õ�����λ��Ϣ
	 * @param pk
	 * @return
	 */
	public Map<String, String> getCwForPk(String pk){
		StringBuilder sql = new StringBuilder();
		 
		sql.append("select b.lddm||b.qsh||b.cwh pkValue,b.* ")
		.append("from (select a.*,b.sjhm lxfs,b.xm,b.xymc xsxymc,b.nj xsnj,b.zymc xszymc,b.bjmc xsbjmc,b.xb xsxb,(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis ")
		.append("from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh = b.xh ) b ")
		.append("where b.lddm||b.qsh||b.cwh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
	
	/**
	 * ��ȡ���λ��
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public Map<String, String> getCwhForQs(String lddm, String qsh){
		String sql = "";
		if ("10698".equals(Base.xxdm)) {
			 sql = "select cwh maxcwh from ( select * from xg_gygl_new_cwxxb where lddm=? and qsh=? order by cwh desc) where rownum=1";
		}else {
			 sql = "select max(to_number(cwh)) maxcwh from xg_gygl_new_cwxxb where lddm=? and qsh=?";
		}
		return dao.getMapNotOut(sql, new String[]{lddm, qsh});
	}

	/**
	 * �����޸ı���
	 * @author zhanghui
	 */
	public String updateCwbl(CwglForm myForm,User user) throws Exception{
		String[] pk = myForm.getPrimarykey_checkVal();
		String sfbl = myForm.getSfbl();
		String blbz = myForm.getBlbz();
		String yllb = myForm.getYllb();
		if(sfbl != null && !"".equals(sfbl) && "��".equals(sfbl)){
			yllb = "";
		}
		List<String[]> list = new ArrayList<String[]>();			
		for(int i=0;i<pk.length;i++){
			list.add(new String[]{pk[i]});
		}
		boolean res = saveArrDate("update xg_gygl_new_cwxxb set sfbl='"+sfbl+"' , blbz='"+blbz+"' , yllb='"+yllb+"' where lddm||qsh||cwh = ? ", 
				list, "xg_gygl_new_cwxxb", user);
		
		if(res){
			return "����ɹ�";
		}else{
			return "����ʧ��";
		}
	}
	/**
	 * Ԥ������б�
	 */
	public List<HashMap<String, String>> getYllbList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from gygl_yllb order by lxdm asc");
		DAO dao=DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ��λ�Ե�
	 * @param pk
	 * @return
	 */
	public boolean cwdd(String[] pk,CwglModel model){
		boolean flag = false;
		TsglForm tsglForm=new TsglForm();
		tsglForm.setSsydlx("01");		  // �����춯���ͣ�01[�������]
		tsglForm.setTsyy(model.getRzyy());//����ԭ��
		tsglForm.setTssj(model.getRzsj());//����ʱ��
		tsglForm.setXn(model.getXn()); // ѧ��
		tsglForm.setXq(model.getXq()); // ѧ��
		tsglForm.setBz(model.getBz()); // ��ע
		tsglForm.setTsczr(model.getCzr());
		
		if(pk!=null&&pk.length==2){
			//������֤������λ���Ա��Ƿ�һ��
			String sql="select xh,qsxb from view_xg_gygl_new_cwxx where lddm||qsh||cwh=?";
			Map<String, String> cwxx1=dao.getMap(sql, new String[]{pk[0]}, new String[]{"xh","qsxb"});
			Map<String, String> cwxx2=dao.getMap(sql, new String[]{pk[1]}, new String[]{"xh","qsxb"});
			
			if(Base.isNull(cwxx1.get("qsxb"))||!cwxx1.get("qsxb").equals(cwxx2.get("qsxb"))){
				return flag;
			}
			String xh1=cwxx1.get("xh");
			String xh2=cwxx2.get("xh");
			
			sql="select nj,xydm,zydm,bjdm from view_xsbfxx where xh=?";
			List<String> sqlArr=new ArrayList<String>();
			String[] njxybj;
			//�����һ����λ��ѧ����sql���ڵڶ�����λ��
			if(!Base.isNull(xh1)){
				njxybj=dao.getOneRs(sql, new String[]{xh1},new String[]{"nj","xydm","bjdm","zydm"});
				sqlArr.add("update xg_gygl_new_cwxxb set nj='"+njxybj[0]+"',xydm='"+njxybj[1]+"',xh='"+xh1+"'," +
						"bjdm='"+njxybj[2]+"',"+"zydm='"+njxybj[3]+"',"+
						"rzsj='"+model.getRzsj()+"',rzyydm='"+model.getRzyy()+"',rzczr='"+model.getCzr()+"' where lddm||qsh||cwh='"+pk[1]+"'");
				tsglForm.setLddm(pk[0]);
				tsglForm.setYdhldqscw(pk[1]);
				sqlArr.add(getTsxxNewSql(tsglForm));
			}else{
				sqlArr.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm||qsh||cwh='"+pk[1]+"'");
			}
			//����ڶ�����λ��ѧ����sql���ڵ�һ����λ��
			if(!Base.isNull(xh2)){
				njxybj=dao.getOneRs(sql, new String[]{xh2},new String[]{"nj","xydm","bjdm","zydm"});
				sqlArr.add("update xg_gygl_new_cwxxb set nj='"+njxybj[0]+"',xydm='"+njxybj[1]+"',xh='"+xh2+"'," +
						"bjdm='"+njxybj[2]+"',"+"zydm='"+njxybj[3]+"',"+
						"rzsj='"+model.getRzsj()+"',rzyydm='"+model.getRzyy()+"',rzczr='"+model.getCzr()+"' where lddm||qsh||cwh='"+pk[0]+"'");
				tsglForm.setLddm(pk[1]);
				tsglForm.setYdhldqscw(pk[0]);
				sqlArr.add(getTsxxNewSql(tsglForm));
			}else{
				sqlArr.add("update xg_gygl_new_cwxxb set xh='',rzsj='',rzczr='' where lddm||qsh||cwh='"+pk[0]+"'");
			}
			
			CommDAO dao=new CommDAO();
			try {
				flag=dao.saveArrDate(sqlArr.toArray(new String[]{}));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag; 
	}
	
	/**
	 * ��ȡ��ǰ��ѯ���ݼ�����ס�Ĵ�λ����
	 * @return
	 */
	public String getYrzrs(CwglModel model,User user) throws Exception{
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
				
		String sql = "select count(*) num from (select a.*,b.xm,b.xz from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ";
		
		if("xy".equalsIgnoreCase(user.getUserType()) && "11799".equals(Base.xxdm)){
			sql += " and a.xydm = '" + user.getUserDep() + "' " ;
		}
		String rs = dao.getOneRs(sql+searchTj+" and sfrz ='��'", inputV, "num");
		return rs;
	}
	
	/**
	 * ��ȡ��ǰ��ѯ���ݼ�δ��ס�Ĵ�λ����
	 * @return
	 */
	public String getWrzrs(CwglModel model) throws Exception{
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
				
		String sql = "select count(*) num from (select a.*,b.xm,b.xz from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ";
		
		String rs = dao.getOneRs(sql+searchTj+" and sfrz ='��' and (sffpxy = '��' or sffpbj='��') ", inputV, "num");
		return rs;
	}
	
	/**
	 * ���ݸ߼���ѯ������ѯ����ס��ѧ��
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String[] getXhs(String tj,User user) throws Exception{
		String sql = "select * from (select rownum r,a.* from (select a.xh pk,a.*,b.xm,b.xb,b.bjdm xsbjdm " +
		"from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " +
		"where sfrz ='��' ) a )a where 1=1 ";
		
		if("xy".equalsIgnoreCase(user.getUserType()) && "11799".equals(Base.xxdm)){
			sql += " and a.xydm = '" + user.getUserDep() + "' " ;
		}
		return dao.getRs(sql+tj, new String[]{}, "xh");
	}
	
	/**
	 * 
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String[] getCwhs(String tj) throws Exception{
		String sql = "select * from (select rownum r,a.* from (select lddm||qsh||cwh pk,a.*,b.xm,b.xb " +
		"from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " +
		"where sfrz ='��' and (sffpxy = '��' or sffpbj='��') ) a )where 1=1 ";
		return dao.getRs(sql+tj, new String[]{}, "pk");
	}
	
	/**
	 * ����������Ϣ
	 * @author zhanghui
	 */
	public String saveTsxx(CwglForm myForm,User user) throws Exception{
		String[] pk_xh = myForm.getPk_xh();
		String tsyy =myForm.getTsyy();
		String bz = myForm.getBz();	
		String tsczr = user.getUserName();
		String tssj = myForm.getTssj();
		
		
		List<String> list = new ArrayList<String>();	
		List<String[]> xhlist = new ArrayList<String[]>();
		TsglForm f = new TsglForm();
		// �����춯���ͣ�00[����]
		f.setSsydlx("00");
		f.setBz(bz);
		f.setTsyy(tsyy);
		f.setTssj(tssj);
		f.setTsczr(tsczr);
		// ׷��ѧ��ѧ��
		f.setXn(myForm.getXn());
		f.setXq(myForm.getXq());
		f.setSfcwcsh(myForm.getSfqccwss());
		
		for(int i=0;i<pk_xh.length;i++){
			f.setXh(pk_xh[i]);
			list.add(getTsxxNewSql(f));
			xhlist.add(new String[]{pk_xh[i]});
		}
		boolean res = saveArrDate(list.toArray(new String[]{}));
		if(res){
			if("��".equals(myForm.getSfqccwss())){//�����λ����
				res = saveArrDate("update xg_gygl_new_cwxxb set xydm='',nj='',bjdm='',zydm='',xh='',rzsj='',bz='',rzyydm='' where xh = ? ", 
						xhlist, "xg_gygl_new_cwxxb", user);
			}else{//ֻ����
				if("xydm".equalsIgnoreCase(GyglNewInit.CWFPDX)){
					res = saveArrDate("update xg_gygl_new_cwxxb set xh='',bjdm='',rzsj='',bz='',rzyydm='' where xh = ? ", 
							xhlist, "xg_gygl_new_cwxxb", user);
				}else{
					res = saveArrDate("update xg_gygl_new_cwxxb set xh='',rzsj='',bz='',rzyydm='' where xh = ? ", 
							xhlist, "xg_gygl_new_cwxxb", user);
				}
			}
		}
		if(res){
			return "����ɹ�";
		}else{
			return "����ʧ��";
		}		
	}
	
	/**
	 * ��ʼ����λ����
	 * @author zhanghui
	 */
	public String initCwss(CwglForm myForm,User user) throws Exception{
		String[] pk_cw = myForm.getPrimarykey_checkVal();
		String cshlx = myForm.getCshlx();
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0;i<pk_cw.length;i++){
			list.add(new String[]{pk_cw[i]});
		}
		String sql = "update xg_gygl_new_cwxxb set xydm='',nj='',zydm='',bjdm='' where lddm||qsh||cwh = ? and xh is null";		
		if("0".equalsIgnoreCase(cshlx)){
			sql = "update xg_gygl_new_cwxxb set bjdm='' where lddm||qsh||cwh = ? and xh is null";	
		}
		boolean res = saveArrDate(sql,list, "xg_gygl_new_cwxxb", user);
		if(res&&"��".equals(myForm.getSfqcqsss())){//�����������
			String cwStr=myForm.getBz();
			//2012-09-04 bug�޸�
//			if(cwStr !=null && !"".equalsIgnoreCase(cwStr)){
//				StringBuffer ldqscwpk=new StringBuffer();
//				String[] pk = cwStr.split("!!splitOne!!");
//				for(int i=0;i<pk.length;i++){
//					ldqscwpk.append("'");
//					ldqscwpk.append(pk[i]);
//					ldqscwpk.append("'");
//					ldqscwpk.append(" like b.lddm||b.qsh||'%' and ");
//				}
//				sql="update xg_gygl_new_qsxxb a set xydm='',nj='' where a.cws=" +
//				"(select count(1) cws from xg_gygl_new_cwxxb b where "+ldqscwpk.toString()+" " +
//				" b.xydm is null and b.nj is null and b.bjdm is null and xh is null and a.lddm=b.lddm and a.qsh=b.qsh)";
//			}else{
//				String tj=myForm.getSearchTjstr();
//				sql ="update xg_gygl_new_qsxxb b set xydm='',nj='' where b.cws="+ 
//				"(select count(1) cws from (select a.* from (select lddm||qsh||cwh pk,a.*,b.xm,b.xb " +
//				"from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " +
//				"where a.xydm is null and a.nj is null and a.bjdm is null and a.xh is null ) a )a " +
//				"where a.lddm=b.lddm and a.qsh=b.qsh "+tj+")";
//			}
			if(cwStr !=null && !"".equalsIgnoreCase(cwStr)){
				StringBuffer ldqscwpk=new StringBuffer("(");
				String[] pk = cwStr.split("!!splitOne!!");
				for(int i=0;i<pk.length;i++){
					ldqscwpk.append("'");
					ldqscwpk.append(pk[i]);
					ldqscwpk.append("'");
					ldqscwpk.append(" = b.lddm||b.qsh||b.cwh ");
					if(i==pk.length-1){
						ldqscwpk.append(") ");
					}else{
						ldqscwpk.append(" or ");
					}
				}
				sql="update xg_gygl_new_qsxxb a set xydm='',nj='' where exists(" +
					"select 1 from ( " +
					"select aa.lddm,aa.qsh,count(1) cws from xg_gygl_new_cwxxb aa, " +
					"( " +
					" select distinct lddm,qsh from xg_gygl_new_cwxxb b " +
					"                 where "+ldqscwpk.toString()+" " +
					") bb where aa.lddm=bb.lddm and aa.qsh=bb.qsh  " +
					"                   and aa.xydm is null " +
					"                   and aa.nj is null " +
					"                   and aa.bjdm is null " +
					"                   and aa.xh is null " +
					" group by aa.lddm,aa.qsh " +
					") c where c.lddm=a.lddm and c.qsh=a.qsh and c.cws=a.cws) " +
					"and nj is not null and xydm is not null";
			}else{
				String tj=myForm.getSearchTjstr();
				
				sql ="update xg_gygl_new_qsxxb b set xydm='',nj='' where exists("+ 
					"select 1 from  " +
				    "(select lddm,qsh, count(1) cws from (" +
					"select a.* from (select lddm||qsh||cwh pk,a.*,b.xm,b.xb " +
					"from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " +
					"where a.xydm is null and a.nj is null and a.bjdm is null and a.xh is null ) a " +
					"where 1=1 "+tj+
					")a group by lddm,qsh) aa " +
					"where aa.lddm=b.lddm and aa.qsh=b.qsh and aa.cws=b.cws) " +
					"and nj is not null and xydm is not null ";
				
//				sql ="update xg_gygl_new_qsxxb b set xydm='',nj='' where b.cws="+ 
//					"(select count(1) cws from (select a.* from (select lddm||qsh||cwh pk,a.*,b.xm,b.xb " +
//				    "from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " +
//				    "where a.xydm is null and a.nj is null and a.bjdm is null and a.xh is null ) a )a " +
//				    "where a.lddm=b.lddm and a.qsh=b.qsh "+tj+")";
			}
			res=dao.runUpdate(sql, new String[]{});
			if(res){
				return "��ʼ���ɹ�";
			}else{
				return "��λ��ʼ���ɹ������ҳ�ʼ��ʧ��";
			}
		}
		if(res){
			return "��ʼ���ɹ�";
		}else{
			return "��ʼ��ʧ��";
		}		
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsxx(String xh, HttpServletRequest request){
		DAO dao = DAO.getInstance();

		// Ȩ�޿���
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "b","xydm", "bjdm");
		String sql="select a.xh,a.ldmc,a.qsh,a.cwh,b.xm,b.xb,b.xydm,b.xymc,b.nj " +
		"from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh where a.xh=? ";
		
		return dao.getMapNotOut(sql+searchTjByUser, new String[]{xh});
	}
	
	/**
	 * ��ѯѧ��
	 * @param model
	 * @return
	 */
	public List<String[]> queryXs(CwglForm model, HttpServletRequest request){
		List<String[]> list = new ArrayList<String[]>();
		
		String[] colLikeList = new String[]{"xh", "xm"};
		String[] colList = new String[]{"nj", "xydm", "zydm", "bjdm", "xb"};
		
		MakeQuery makeQuery = new MakeQuery();
		StringBuilder sql = new StringBuilder();
		
		// Ȩ�޿���
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		
		sql.append("select rownum r,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc from (select a.* from view_xsjbxx a ");
		sql.append("where not exists (select 1 from xg_gygl_new_cwxxb b where b.xh=a.xh) ");
		if("13871".equals(Base.xxdm)){
			sql.append(" and a.xh not in(select xh from XG_GYGL_XYZSGL where to_date(sqsjstart,'yyyyMMdd')<sysdate and to_date(sqsjend,'yyyyMMdd')+1>sysdate) ");
		}
		//���칤�̴�ѧ���Ի�����ת�߶���ѧ��
		if("11799".equals(Base.xxdm)){
			sql.append(" and not exists (select 1 from xg_gygl_new_bzxbzb c where a.xh = c.xh)");
		}
		sql.append( " ) a ");
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
			list = CommonQueryDAO.commonQuery(sql.toString(), query+searchTjByUser, inputs, new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"}, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<HashMap<String, String>> getFdyBzrList(String pkValue) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c.zgh,c.xm,c.lxdh,'������' zw from (select b.bjdm from xg_gygl_new_cwxxb a left join view_xsjbxx b on a.xh=b.xh where a.lddm||a.qsh||a.cwh=? and a.xh is not null) a left join bzrbbb b on a.bjdm=b.bjdm left join fdyxxb c on b.zgh=c.zgh where c.zgh is not null");
		sql.append(" union all");
		sql.append(" select c.zgh,c.xm,c.lxdh,'����Ա' zw from (select b.bjdm from xg_gygl_new_cwxxb a left join view_xsjbxx b on a.xh=b.xh where a.lddm||a.qsh||a.cwh=? and a.xh is not null) a left join fdybjb b on a.bjdm=b.bjdm left join fdyxxb c on b.zgh=c.zgh where c.zgh is not null");
		DAO dao=DAO.getInstance();
		return dao.getList(sql.toString(), new String[]{pkValue,pkValue}, new String[]{"zgh","xm","lxdh","zw"});
	}

	public void deleteBzxbz(String pkValue) throws Exception {
		String sql="delete xg_gygl_new_bzxbzb where xh=?";
		dao.runUpdate(sql, new String[]{pkValue});
		
	}
	
	/**
	 * ͨ��ѧ�Ż��ѧ��ס����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getCwForXh(String xh){
		StringBuilder sql = new StringBuilder();
		 
		sql.append("select * from view_xg_gygl_new_cwxx where xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ȡ���Ҵ�λ��ס��Ϣ
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTqCw(String xh){
		HashMap<String, String> cwForXh = getCwForXh(xh);
		String lddm = cwForXh.get("lddm");
		String qsh = cwForXh.get("qsh");
		String sql = "select * from xg_view_gygl_new_xszsgl where lddm = ? and qsh = ?";
		
		if(StringUtils.isNotBlank(qsh) && StringUtils.isNotBlank(lddm)){
			return dao.getListNotOut(sql, new String[]{lddm,qsh});
		}
		return null;
	}
		
	/**
	 * ��λ��Ϣ�����Զ��嵼��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(CwglModel model,HttpServletRequest request) throws Exception{
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//��������ְҵ����ѧԺ���Ի��жϣ���λ�ſ���Ϊ����
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1";
		}else if ("10698".equals(Base.xxdm)) {
			sb = "order by a.lddm,to_number(a.ch),a.qsh,a.cwh) a where 1=1";
		}else{
			sb = "order by a.lddm,to_number(a.ch),a.qsh,to_number(a.cwh)) a where 1=1";
		}
		sql.append("select rownum r,a.* from (select lddm||qsh||cwh pkValue,a.*,b.xm,");
		if("12389".equals(Base.xxdm)){
			sql.append(" c.sfzh,");
		}
		sql.append("(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis ");
		sql.append("from view_xg_gygl_new_cwxx a left join "+Base.xsxxb+" b on a.xh=b.xh ");
		if("12389".equals(Base.xxdm)){
			sql.append(" left join view_xsjbxx c on a.xh=c.xh ");
		}
		sql.append(sb+" ");
		
		//sql.append("select rownum r , k.* from VIEW_NEW_DC_GYGL_CWGL k where 1=1 ");
		
		String[] output = null;
		if("12389".equals(Base.xxdm)){
			if("xydm".equals(GyglNewInit.CWFPDX)){//��λ�������ΪѧԺʱ������ʾ�����༶����
				output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "xh", "xm","sfbl","sfzh","sfbz"};
			}else {
				output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc","bjmc", "xh", "xm","sfbl","sfzh","sfbz"};
			}
		}else{
			if("xydm".equals(GyglNewInit.CWFPDX)){//��λ�������ΪѧԺʱ������ʾ�����༶����
				output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc", "xh", "xm","sfbl"};
			}else {
				output=new String[]{"pkValue", "dis", "ldmc", "qsh", "cwh","qsxb", "nj", "xymc","bjmc", "xh", "xm","sfbl"};
			}
		}

		try {
			list = CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj , inputV, output, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @��������ҵ����-���ޣ���ñ�ҵ������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��22�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @return
	 * @throws Exception
	 * int ��������
	 */
	public int getCounts(CwglForm myForm,SearchModel searchModel, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(xh) counts from VIEW_XSBFXX ");
		sql.append(" where 1=1 ");
		//sql.append("  and (sfyby is null or sfyby !='��' )  ");
		sql.append("  and ( sfyby !='��' )  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		List<HashMap<String,String>> resultList = null;
		resultList = dao.getListNotOut(sql.toString(), inputV);
		if(resultList!=null){
			return Integer.parseInt(resultList.get(0).get("counts"));
		}else{
			return 0;
		}
	}
}

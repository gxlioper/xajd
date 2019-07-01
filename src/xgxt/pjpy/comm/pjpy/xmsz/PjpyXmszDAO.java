package xgxt.pjpy.comm.pjpy.xmsz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

public class PjpyXmszDAO {
	/**
	 * ��ȡ��Ŀ�����б�
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXmxzList(){
		DAO dao=DAO.getInstance();
		String sql=" select xzdm dm,xzmc mc from xg_pjpy_xmxzb ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ��Ŀ�����б�
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXmfwList(){
		DAO dao=DAO.getInstance();
		String sql=" select fwdm dm,fwmc mc from xg_pjpy_xmfwb ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * �޸Ŀ���״̬
	 * 
	 * @throws Exception
	 */
	public Boolean updateSfqy(PjpyXmszForm myForm) throws Exception {
		
		DAO dao=DAO.getInstance();
		
		boolean flag = false;
		// ��Ŀ����
		String[] xmdm = myForm.getXmdms();
		// ��Ŀ����
		String[] sfqy = myForm.getSfqy();

		StringBuffer sql = new StringBuffer();

		if (xmdm != null && xmdm.length > 0) {

			String[] arr_sql = new String[xmdm.length];

			for (int i = 0; i < xmdm.length; i++) {
				sql = new StringBuffer();

				sql.append("update xg_pjpy_pjxmwhb set ");
				sql.append("sfqy = '" + sfqy[i] + "' ");
				sql.append("where xmdm||pjxn||pjxq||pjnd = '" + xmdm[i] + "' ");

				arr_sql[i] = sql.toString();
			}

			flag = dao.saveArrDate(arr_sql);
		}

		return flag;
	}
	
	
	public boolean fzlnxm(PjpyXmszForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into xg_pjpy_pjxmwhb(XMDM,XMMC,YWMC,XSSX,XMXZ,XMFW,XMLX,XMSM,PJXN,PJXQ,PJND,SQFS,SJJE,XMJE, ");
		sql.append(" RSSZ,KZJB,KZFW,RSSX,SQZQ,ZJTG,QZTJ,SJKZ,SFQY,SFSH,LCID,SFTBXM)");
		sql.append(" select * from (select XMDM,XMMC,YWMC,XSSX,XMXZ,XMFW,XMLX,XMSM,b.PJXN,b.PJXQ,b.PJND,SQFS,SJJE,XMJE, ");
		sql.append(" RSSZ,KZJB,KZFW,RSSX,SQZQ,ZJTG,QZTJ,SJKZ,SFQY,SFSH,LCID,'��' ");
		sql.append(" from xg_pjpy_pjxmwhb a,xg_pjpy_xtszb b where a.pjxn=? and a.pjxq=? and a.pjnd=?  ");
		//sql.append(" and a.pjxn<>b.pjxn and (a.pjxn <> b.pjxn  or a.pjxq <> b.pjxq or a.pjnd <> b.pjnd))b ");
		sql.append(" and (a.pjxn <> b.pjxn  or a.pjxq <> b.pjxq or a.pjnd <> b.pjnd))b ");
		
		//�ڵ�ǰ����ѧ�ꡢѧ�ڡ����
		sql.append(" where  not exists (select 1 from xg_pjpy_pjxmwhb d where b.xmdm=d.xmdm and b.pjxn=d.pjxn and b.pjxq=d.pjxq and b.pjnd=d.pjnd )");
		return dao.runUpdate(sql.toString(), new String[]{myForm.getSelect_xn(),myForm.getSelect_xq(),myForm.getSelect_nd()});
	}
	
	/**
	 * ��ȡ��������б�
	 * @return
	 */
	public List<HashMap<String,String>> getShlcList(){
		DAO dao=DAO.getInstance();
		String sql=" select lcid,lcmc,max(gzgw)gzgw   from ( "+
					" select lcid,lcmc,to_char(wm_concat (gzgw)over( " +
					" partition by  lcid,lcmc order by lcid, xh)) as gzgw from ( "+
					" select a.id lcid,a.mc lcmc,b.mc gzgw,c.xh from " +
					" xg_xtwh_splc a,xg_xtwh_spgw b,xg_xtwh_spbz c "+
					" 	where a.id=c.splc and b.id=c.spgw and LOWER(a.djlx)='pjpy'	) a )group by lcid,lcmc ";
		List<HashMap<String,String>> list=dao.getList(sql, new String[]{}, new String[]{"lcid","lcmc","gzgw"});
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).put("gzgw", list.get(i).get("gzgw").replace(",", "-->"));
			}
		}
		return list;
	}
	
	/**
	 * �����Ƿ�������
	 * @param xmdm
	 * @return
	 */
	public boolean checkSfysq(String xmdm){
		DAO dao=DAO.getInstance();
		String sql="select count(1) num from xg_pjpy_pjxmsqb where xmdm=?";
		String num=dao.getOneRs(sql, new String[]{xmdm},"num");
		if("0".equals(num)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * �ж�����ϵͳ���ñ������Ƿ�Ϊ��
	 * @return HashMap<String,String
	 */
	public HashMap<String,String>getPjXtszb(){

		DAO dao=DAO.getInstance();
		
		String sql=" select count(1)num from xg_pjpy_xtszb ";
		
		return dao.getMap(sql, new String[]{},  new String[]{"num"});
		
	}
	
	public String getXqmc(String xq){
		
        DAO dao=DAO.getInstance();
		
		String sql=" select xqmc from xqdzb where xqdm=? ";
		
		return dao.getOneRs(sql, new String[]{xq}, "xqmc");
	}
	
    public boolean xmdmExist(PjpyXmszForm model){
		
        DAO dao=DAO.getInstance();
		
		String sql=" select xmdm from xg_pjpy_pjxmwhb where pjxn=? and pjxq = ? and pjnd = ? and xmdm=? ";
		
		String xmdm = dao.getOneRs(sql, new String[]{model.getPjxn(),model.getPjxq(),model.getPjnd(),model.getXmdm()}, "xmdm");
		if(!StringUtil.isNull(xmdm)){
			return true;
		}
		return false;
	}
}

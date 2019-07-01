package xgxt.studentInfo.xszc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;



public class XszcService {
	
	XszcDAO dao = new XszcDAO();
	
	/**
	 * ƴװ��ȡѧ������ѧ��ѧ�ڵ�ע�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public List<String[]> getRs(String xh){
		//��ȡѧ����Ϣ
		HashMap<String, String> stuinfo = CommonQueryDAO.getStuInfo(xh);
		String nj = StringUtils.isNull(stuinfo.get("nj")) ? "" : stuinfo.get("nj");
		int cont = (Integer.parseInt(Base.currNd) - Integer.parseInt(nj));
		StringBuilder zbsql = new StringBuilder();
		int nianji = Integer.parseInt(nj);
		//��ȡ����ǰ����������ѧ��ѧ��
		for(int i = 0;i < cont; i++){
			zbsql.append(" select ")
				.append(nianji)
				.append("||'-'||")
				.append(nianji+1)
				.append(" xn,'��ѧ��' xq from dual ")
				.append(" union all ");
			zbsql.append(" select ")
				.append(nianji)
				.append("||'-'||")
				.append(nianji+1)
				.append(" xn,'��ѧ��' xq from dual ")
				.append(" union all ");
			nianji++;
		}
		//��ȡ��ǰ������ѧ��ѧ�ڣ���Ҫ��ѧ��������
		if(Base.currNd.equals(Base.currXn.substring(0,4))){
			zbsql.append(" select ")
				.append(Base.currNd)
				.append("||'-'||")
				.append(Integer.parseInt(Base.currNd)+1)
				.append(" xn,'��ѧ��' xq from dual ");
		}else{
			zbsql.append(" select ")
				.append(Base.currNd)
				.append("||'-'||")
				.append(Integer.parseInt(Base.currNd)+1)
				.append(" xn,'��ѧ��' xq from dual ")
				.append(" union all ");
			zbsql.append(" select ")
				.append(Base.currNd)
				.append("||'-'||")
				.append(Integer.parseInt(Base.currNd)+1)
				.append(" xn,'��ѧ��' xq from dual ");
		}
		String sql = "select a.xn||a.xq||'" + xh + "' pk,a.xn,a.xq," + xh + " xh,nvl(b.zczt,'δע��') zczt,nvl(b.fdysh,'δ���') fdysh,case when b.fdysh is not null then '#99CCFF' else '#FFFFFF' end bgcolor,case when b.yy is not null then '����д' else 'δ��д' end yytxzt from (" + zbsql.toString() + ") a left join xg_xsxx_zcqkb b on a.xn=b.xn and a.xq=b.xq and b.xh='" + xh + "' where a.xn <= '"+Base.currXn+"' order by a.xn,a.xq "; 
		return dao.getRs(sql, new String[]{}, new String[]{"pk","bgcolor","xn","xq","zczt","fdysh"});
	}
	
	/**
	 * ��ȡ��ͷ��Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(){
		return dao.getTopTr();
	}
	
	/**
	 * ע��
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public boolean zc(XszcActionForm form) throws SQLException{
		StringBuilder sql = new StringBuilder();
		String[] pkArr = form.getPrimarykey_cbv();
		String[] xnArr = form.getXn().split(",");
		String[] xqArr = form.getXq().split(",");
		for(int i=0;i<pkArr.length;i++){
			sql.append("delete from xg_xsxx_zcqkb where xn||xq||xh = '").append(pkArr[i]).append("'").append("!@");
			sql.append("insert into xg_xsxx_zcqkb(xn,xq,xh,zczt) values('").append(xnArr[i]).append("','").append(xqArr[i]).append("','").append(form.getXh()).append("','��ע��')").append("!@");
		}
		return dao.zc(sql.toString().substring(0,sql.toString().length()-2).split("!@"));
	}
	
	/**
	 * ��ȡѧ��ע����Ϣ
	 * @param form
	 * @return
	 */
	public HashMap<String, String> getXszcInfo(XszcActionForm form){
		HashMap<String, String> map = new HashMap<String, String>();
		if(!"0".equals(dao.getCont(form.getPk()))){
			map = dao.getXszcInfo(form.getPk());
		}else{
			map.put("xn", form.getXn());
			map.put("xq", form.getXq());
			map.put("xh", form.getXh());
		}
		map.put("type", form.getType()); //��������ע��״̬
		return map;
	}
	
	/**
	 * ɾ��ѧ��ע����Ϣ
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 * @throws Exception
	 */
	public boolean del(String pk) throws Exception{
		return dao.del(pk);
	}
	
	/**
	 * �����б�
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		if ("sh".equalsIgnoreCase(flg)) {
			List shztList = dao.getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		} else if ("zc".equals(flg)){
			List zcztList = dao.getSelectList("zczt");
			request.setAttribute("zcztList", zcztList);
		}
	}
	
}

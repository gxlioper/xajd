package xsgzgl.pjpy.bjlhdx.zhcp;

import xgxt.DAO.DAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_ͨ��_DAO��
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 *
 * @version 1.0
 */

public class PjpyZhcpDAO extends xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO {

	/**
	 * ���ٷ��Ƶ��۲��ת���������
	 * @return
	 * @throws Exception
	 */
	public boolean updateZcf() throws Exception{
		
		DAO dao=DAO.getInstance();
		// -------------------��ȡ�������� begin ------------------------
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		// -------------------��ȡ�������� emd ------------------------
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb set zd1=round(to_number(nvl(zd1,0))/20,2) where xn=? and xq=? ");
		
		return dao.runUpdate(sql.toString(), new String[]{pjxn,pjxq});
		
	}
}

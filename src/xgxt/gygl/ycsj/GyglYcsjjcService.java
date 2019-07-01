package xgxt.gygl.ycsj;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.gygl.GyglCommForm;
import xgxt.utils.String.StringUtils;

public class GyglYcsjjcService {

	GyglYcsjjcDAO dao = new GyglYcsjjcDAO();
	
	/**
	 * �쳣������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,Object> getYcsjByType(GyglCommForm model){
		
		HashMap<String,Object> ycsj = null;
		
		switch(YcsjlxEnum.valueOf(model.getYclx().toUpperCase())){
			case DRZTC://����סͬһ�Ŵ����쳣����
				ycsj = dao.getZsxxByDrtcw(model);
				ycsj.put("prompt", "����˱�������ͬһ����λ");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case RZRSCGCWS://����ס���˳�����
				ycsj = dao.getZsxxByZgcws(model);
				ycsj.put("prompt", "����ѧ����ס����������������ܴ�λ��");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case BLQSBFP://�������ұ�����
				ycsj = dao.getFpxxByFpblqs(model);
				ycsj.put("prompt", "���±������ұ�������");
				ycsj.put("tableName", "xg_gygl_qsfpb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
			case BLQSBZR://��������ס��
				ycsj = dao.getZsxxByBlqszr(model);
				ycsj.put("prompt", "����ѧ���������ڱ���������");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case XLCWBZR://���λס��
				ycsj = dao.getZsxxByXlcwfp(model);
				ycsj.put("prompt", "����ѧ�������������λ��");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case YXBFCSS://���Ա𲻷��ִ�����
				ycsj = dao.getZsxxByXbyw(model);
				ycsj.put("prompt", "����ѧ���������ڴ����Ա������");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case BFHLDXB://���᲻����¥��Ҫ���Ա�
				ycsj = dao.getSsxxByLdxbbf(model);
				ycsj.put("prompt", "����������¥�������Ա����Ʋ���");
				ycsj.put("tableName", "ssxxb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
			case SSCGZGC://��������¥����߲���������
				ycsj = dao.getSsxxByCgzgcs(model);
				ycsj.put("prompt", "����������Ϣ��������¥�����¥��");
				ycsj.put("tableName", "ssxxb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
			case CWCGZGC://��������¥����߲����Ĵ�λ
				ycsj = dao.getCwxxByCgzgcs(model);
				ycsj.put("prompt", "���´�λ��Ϣ��������¥�����¥��");
				ycsj.put("tableName", "xg_gygl_cwxxb");
				ycsj.put("pkKey", "lddm||cs||qsh||cwh");
				break;
			case QTCWCGZGC://��������¥����߲�����������λ
				ycsj = dao.getQtcwxxByCgzgcs(model);
				ycsj.put("prompt", "����������λ��Ϣ��������¥�����¥��");
				ycsj.put("tableName", "xg_gygl_qtcwxxb");
				ycsj.put("pkKey", "lddm||cs||qsh||cwh");
				break;
			case FCBM://���Ų��ɻ�ס�����������
				ycsj = dao.getSsxxBybmbkhz(model);
				ycsj.put("prompt", "����ѧ���������ڲ��ɻ�ס�ҷǱ����ŵ�������");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case FPXXBFJBSZ://������Ϣ�����ϻ��������з��䷽ʽ
				ycsj = dao.getFbxxByJbszbf(model);
				ycsj.put("prompt", "�������������Ϣ�����ϻ��������еķ��䷽ʽ");
				ycsj.put("tableName", "xg_gygl_qsfpb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
		}
		return ycsj;
	}
	
	
	
	/**
	 * �����쳣���Ͳ�ѯ�쳣���ݼ�¼��
	 * @param ycsjlx
	 * @return
	 * @throws SQLException
	 */
	public int getCountByType(String ycsjlx) throws SQLException{
			
			int count = 0;
		
			switch(YcsjlxEnum.valueOf(ycsjlx.toUpperCase())){
				case DRZTC://����סͬһ�Ŵ����쳣����
					count = dao.getCountByDrtcw();
					break;
				case RZRSCGCWS://����ס���˳�����
					count = dao.getCountByZgcws();
					break;
				case BLQSBFP://�������ұ�����
					count = dao.getCountByFpblqs();
					break;
				case BLQSBZR://��������ס��
					count = dao.getCountByBlqszr();
					break;
				case XLCWBZR://���λס��
					count = dao.getCountByXlcwfp();
					break;
				case YXBFCSS://���Ա𲻷��ִ�����
					count = dao.getCountByXbyw();
					break;
				case BFHLDXB://���᲻����¥��Ҫ���Ա�
					count = dao.getCountByLdxbbf();
					break;
				case SSCGZGC://��������¥����߲���������
					count = dao.getCountByCgzgcs();
					break;
				case CWCGZGC://��������¥����߲����Ĵ�λ
					count = dao.getCountByCgzgcscw();
					break;
				case QTCWCGZGC://��������¥����߲�����������λ
					count = dao.getCountByCgzgcsqtcw();
					break;
				case FCBM://���Ų��ɻ�ס�����������
					count = dao.getCountBybmbkhz();
					break;
				case FPXXBFJBSZ://������Ϣ�����ϻ��������з��䷽ʽ
					count = dao.getCountByJbszbf();
					break;
			}
			return count;
		}
	
	
	/**
	 * ɾ���쳣����
	 * @param tableName
	 * @param pkKey
	 * @param pkValues
	 * @return
	 */
	public boolean delYcsj(String tableName, String pkKey, String[] pkValues) {

		if (StringUtils.isNotNull(tableName) && StringUtils.isNotNull(pkKey)
				&& null != pkValues && pkValues.length > 0) {
			try {
				return dao.delYcsj(tableName, pkKey, pkValues);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

	}
	
}

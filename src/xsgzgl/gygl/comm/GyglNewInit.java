package xsgzgl.gygl.comm;

import java.util.HashMap;

import com.zfsoft.utils.StringUtil;

public class GyglNewInit {
	
	/**
	 * ��λ�������
	 * Ĭ�ϣ�xydmѧԺ
	 * �༶����Ϊbjdm
	 * רҵ����Ϊzydm
	 */
	public static String CWFPDX="xydm";		//��λ�������Ĭ��xydmѧԺ ,�༶����Ϊbjdm,רҵ����Ϊzydm
	
	public static String YQBJ="0";		//԰����ǣ�Ĭ��0�ޣ���0����У����1������԰������
	
	public static String XQBJ="0";		//У����ǣ�Ĭ��0�ޣ���0����У����1������У������
	
	public static String LZFPNUM="1";   //��Ԣ������Ա��������  Ĭ��һ����ֻ���¥�� ����Ϊһ�������и����Ŀ���
	
	public static String CZFPNUM="1";   //��Ԣ������Ա��������  Ĭ��һ����ֻ��Բ㳤 ����Ϊһ�������и����Ŀ���
	
	public static String gzdx_wmqsbfb="0%";//���ݴ�ѧ�����������Ұٷֱ�-����ѧԺ����
	
	public static String JFFS = "0";//0����ֵ��1���ȼ�
	
	public static boolean WSJC_XJQS = false;//��������Ƿ������Ǽ�����
	
	private static final String XJQS_ENABLE = "1";
	public static String FSCX_XSFS = "0";
	
	
	static {
		gyglNewInit();
	}
	
	public static void gyglNewInit(){
		GyglNewDAO dao= new GyglNewDAO();
		//��ȡ��Ԣ�������ݣ�δ������ȡĬ��ֵ
		HashMap<String,String> map = new HashMap<String, String>();
		
		map = dao.getGyglCssz();
		
		//xydm�����䴲λ��ѧУ�����ѧԺ��ѧԺ������ס��bjdm�����䴲λ��ѧУ�����ѧԺ��ѧԺ������༶�������ΰ�����ס
		if(map.get("cwfpdx") !=null && !"".equalsIgnoreCase(map.get("cwfpdx"))){
			CWFPDX = map.get("cwfpdx");
		}
		if(map.get("yqbj") !=null && !"".equalsIgnoreCase(map.get("yqbj"))){
			YQBJ = map.get("yqbj");
		}
		if(map.get("xqbj") !=null && !"".equalsIgnoreCase(map.get("xqbj"))){
			XQBJ = map.get("xqbj");
		}
		if(map.get("lzfpnum") !=null && !"".equalsIgnoreCase(map.get("lzfpnum"))){
			LZFPNUM = map.get("lzfpnum");
		}
		if(map.get("czfpnum") !=null && !"".equalsIgnoreCase(map.get("czfpnum"))){
			CZFPNUM = map.get("czfpnum");
		}
		if(map.get("gzdx_wmqsbfb") !=null && !"".equalsIgnoreCase(map.get("gzdx_wmqsbfb"))){
			gzdx_wmqsbfb = map.get("gzdx_wmqsbfb");
		}
		if(map.get("jffs") !=null && !"".equalsIgnoreCase(map.get("jffs"))){
			JFFS = map.get("jffs");
		}
		
		if(!StringUtil.isNull(map.get("wsjc_xjqs"))){
			WSJC_XJQS = XJQS_ENABLE.equals(map.get("wsjc_xjqs"));
		}
		if(!StringUtil.isNull(map.get("wsjc_fscx_xsfs"))){
			FSCX_XSFS = map.get("wsjc_fscx_xsfs");
		}
	}
}
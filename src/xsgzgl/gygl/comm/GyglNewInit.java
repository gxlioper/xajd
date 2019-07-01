package xsgzgl.gygl.comm;

import java.util.HashMap;

import com.zfsoft.utils.StringUtil;

public class GyglNewInit {
	
	/**
	 * 床位分配对象
	 * 默认：xydm学院
	 * 班级对象为bjdm
	 * 专业对象为zydm
	 */
	public static String CWFPDX="xydm";		//床位分配对象：默认xydm学院 ,班级对象为bjdm,专业对象为zydm
	
	public static String YQBJ="0";		//园区标记：默认0无；【0：无校区；1：存在园区；】
	
	public static String XQBJ="0";		//校区标记：默认0无；【0：无校区；1：存在校区；】
	
	public static String LZFPNUM="1";   //公寓管理人员个数控制  默认一个，只针对楼长 若不为一个将进行个数的控制
	
	public static String CZFPNUM="1";   //公寓管理人员个数控制  默认一个，只针对层长 若不为一个将进行个数的控制
	
	public static String gzdx_wmqsbfb="0%";//贵州大学——文明寝室百分比-怎对学院设置
	
	public static String JFFS = "0";//0：数值；1：等级
	
	public static boolean WSJC_XJQS = false;//卫生检查是否启用星级寝室
	
	private static final String XJQS_ENABLE = "1";
	public static String FSCX_XSFS = "0";
	
	
	static {
		gyglNewInit();
	}
	
	public static void gyglNewInit(){
		GyglNewDAO dao= new GyglNewDAO();
		//获取公寓设置数据，未配置则取默认值
		HashMap<String,String> map = new HashMap<String, String>();
		
		map = dao.getGyglCssz();
		
		//xydm：分配床位由学校分配给学院，学院安排入住；bjdm：分配床位由学校分配给学院，学院分配给班级，班主任安排入住
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
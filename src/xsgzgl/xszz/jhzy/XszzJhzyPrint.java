package xsgzgl.xszz.jhzy;

import java.util.HashMap;

import xgxt.xszz.comm.XszzCommPrint;

public class XszzJhzyPrint extends XszzCommPrint{

	public HashMap<String,String> getSfzhArr(String sfzh){
		
		String[]sT=new String[18];
		
		HashMap<String,String>map=new HashMap<String,String>();
		
		if(null != sfzh){

			for (int i = 0; i < sfzh.length(); i++) {
				sT[i] = sfzh.substring(i, i + 1);
			}
			map.put("sfzh1", sT[0]);
			map.put("sfzh2", sT[1]);
			map.put("sfzh3", sT[2]);
			map.put("sfzh4", sT[3]);
			map.put("sfzh5", sT[4]);
			map.put("sfzh6", sT[5]);
			map.put("sfzh7", sT[6]);
			map.put("sfzh8", sT[7]);
			map.put("sfzh9", sT[8]);
			map.put("sfzh10", sT[9]);
			map.put("sfzh11", sT[10]);
			map.put("sfzh12", sT[11]);
			map.put("sfzh13", sT[12]);
			map.put("sfzh14", sT[13]);
			map.put("sfzh15", sT[14]);
			map.put("sfzh16", sT[15]);
			map.put("sfzh17", sT[16]);
			map.put("sfzh18", sT[17]);
		}
		
		return map;
	}
}

package xgxt.utils;

public class RowidToPk {
	//处理用rowid做主键时特殊字符"+"的传递问题

	public static String rowidToPK (String rowid)
	{
		if(rowid==null){
			rowid = "";
		}
		return rowid.replaceAll(" ", "+");
	}

}

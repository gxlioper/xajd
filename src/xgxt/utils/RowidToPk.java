package xgxt.utils;

public class RowidToPk {
	//������rowid������ʱ�����ַ�"+"�Ĵ�������

	public static String rowidToPK (String rowid)
	{
		if(rowid==null){
			rowid = "";
		}
		return rowid.replaceAll(" ", "+");
	}

}

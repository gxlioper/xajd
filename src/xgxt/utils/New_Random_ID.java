package xgxt.utils;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.sql.Timestamp;

public class New_Random_ID {
	private static String dateForm = "yyyyMMddhhmmssSSS";
	private static int id=1;
	@SuppressWarnings("unused")
	private static String getNowDate() throws Exception {
		return (new SimpleDateFormat(dateForm, Locale.US)).format(new Date());
	}
	//随机生成一个表的虚拟主键	
	public String new_xnid(String tableName)
	{
		try{
			Timestamp ff = new Timestamp(System.currentTimeMillis());
			id++;
			if(id>1000)
				id=0;
			return (tableName+ff.getTime()*1000+id);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static String getUUId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	public static void main(String...s){
		System.out.println(getUUId());
		System.out.println(getUUId().length());
	}
}
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<OBJECT ID="ReadBar2Comm1" WIDTH="1" HEIGHT="1" Visible=0 - False
	CLASSID="CLSID:5A3B4AFF-F93D-43DA-83BD-0BA50E403311"
	DATA="DATA:application/x-oleobject;BASE64,/0o7Wj352kODvQulDkAzEQADAAByAQAAcgEAAA==">
</OBJECT>
<script type="text/javascript">
	var strData=new Array(1024);
	var lDataLen; 
	try
	{
	    ReadBar2Comm1.SetDCB(9600, 8 , 0, 0);
	    var a,b;
	    ReadBar2Comm1.ClosePort();
	    a=ReadBar2Comm1.OpenPort(1);
	    if(a!=0)
		{    
		    ReadBar2Comm1.ClosePort();
		    b=ReadBar2Comm1.OpenPort(1);
		    if(b!=0)
			{
			    alert("打开串口失败！");
			}
		} 					  
	}
	catch (ex)
	{
	    alert("你未安装ActiveX控件!请设置IE安全属性！");
	} 
	
	function leave(){
	    b=ReadBar2Comm1.ClosePort();   
	}
	
	function sfzy() {
		leave();
	}
	</script>
<script LANGUAGE="javascript" FOR="ReadBar2Comm1"
	EVENT="OnComm(strData, lDataLen)">
	    ReadBar2Comm1.GetBinaryData(strData); 
	    if(strData!=null&&strData.length>18){
	    	strData = strData.substr(0,18);
	    }
	    
	    //这里就获取到解码后的二维码
	    $("planarCode").value=strData;
	    $("sfzh").value=strData;
	    getXh();
	</script>
<table>
  <tr>
    <td style="display:none">
	<input type="hidden" name="planarCode" id="planarCode">		
	</td>
  </tr>
</table>

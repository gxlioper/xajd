<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<style>
.include_tab{border-collapse:collapse;border:0px;}
.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}
.include_tab_r{}
</style>
<table width="100%" class="include_tab">
<tr>
	<td width="20%" align=center class="include_tab_r">	
		银行类型
	</td>
	<td width="30%" class="include_tab_r">
		<input type="text" name="yhmc" id="yhmc" value="" readonly="readonly" style="width: 100%"/>
	</td>
	<td width="16%" align=center class="include_tab_r">
		卡号
	</td>
	<td style="border-right:0;">
		<input type="text" name="yhkh" id="yhkh" value="" readonly="readonly" style="width: 100%"/>		
	</td>
</tr>
</table>
<script language="javascript">
	
	var xh = "";
	
	if($("xh")){
	
		dwr.engine.setAsync(false);
		
		xh = $("xh").value;
		
		var tableName = "view_xsxxb"; 
		var colList = ["yhmc","yhkh"];
		var pk = "xh";
		var pkValue = xh; 
		var query = "";
		
		getOtherData.getTableInfo(tableName,colList,pk,pkValue,query,function(data){
			if(data != null && data.length > 0){
				var yhmc = "";
				var yhkh = "";
				if(data[0] != null){
					yhmc = data[0];
				}
				if(data[1] != null){
					yhkh = data[1];
				}
				$("yhmc").value = yhmc;
				$("yhkh").value = yhkh;
			}
		});
		
		dwr.engine.setAsync(true);
	}
</script>
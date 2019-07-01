<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" defer="defer">
		function save(){
			var sf = jQuery("#sf").val();
			var url = "szdw_dwwh.do?method=szdwSzSave_10352";			
			var ids=jQuery("#ids").val();
			jQuery.post(url,{"ids":ids, "sf":sf},function(result){
			 	showAlert(result,{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
			});					
		}
		</script>
		</head>
		<body>
		<html:form action="/szdw_dwwh" method="post">
		<input type="hidden" id="ids" value="${ids}"/>
	<div class="open_win01" id="yhfz">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>思政队伍设置</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>是否属于思政队伍
				</th>
				<td>
					<select id="sf" style="width:200px">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr>			
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="save()">
							保 存
						</button>
						<button type="button"  onclick="iFClose();return false;">
							关 闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
		</html:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" defer="defer">
		function save(){
			var zzshen = jQuery("#zzshen").val();
			var url = "dekt_jswhgl.do?method=dektSave_10698";			
			var zgh=jQuery("#zgh").val();
			jQuery.post(url,{"zgh":zgh, "zzshen":zzshen},function(result){
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
		<html:form action="/dekt_jswhgl" method="post">
		<input type="hidden" id="zgh" value="${zgh}"/>
	<div class="open_win01" id="yhfz">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>��ʦԤԼ����</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>�Ƿ���ԤԼ
				</th>
				<td>
					<select id="zzshen" style="width:200px">
						<option value="1">��</option>
						<option value="0">��</option>
					</select>
				</td>
			</tr>			
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"Ϊ������
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="save()">
							�� ��
						</button>
						<button type="button"  onclick="iFClose();return false;">
							�� ��
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
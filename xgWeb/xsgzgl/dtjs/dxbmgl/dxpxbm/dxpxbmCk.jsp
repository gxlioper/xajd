<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">		
	function iFClose(){
		var api = frameElement.api; 
		api.close();
	}
	</script>
</head>
<body>
	<html:form action="dxbmgl_dxpxgl" method="post" styleId="form">
		<div class="tab">
		<table class="formlist" width="95%">			
			<tbody>
				<tr >
					<th width="16%"><font color="red">*</font>��ѵ�ڴ�</th>
					<td width="34%">
						${dxpxglModel.pxqc}
					</td>
					<th width="16%">������</th>
					<td width="34%">${dxpxglModel.fbrxm}</td>
				</tr>
				<tr>
					<th width="16%"><font color="red">*</font>������ʼʱ��</th>
					<td>
						${dxpxglModel.bmkssj}
					</td>
					<th width="16%"><font color="red">*</font>��������ʱ��</th>
					<td>
						${dxpxglModel.bmjssj}
					</td>
				</tr>
				<tr>
					<th width="16%"><font color="red">*</font>��ѵʱ��</th>
					<td colspan="3">
						${dxpxglModel.pxsj}
					</td>
				</tr>
				<tr>
					<th width="16%">��ѵ����</th>
					<td colspan="3">						
						${dxpxglModel.pxnr}
					</td>
				</tr>
			</tbody>						
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button type="button"  name="�ر�" id="buttonClose" onclick="iFClose();">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>

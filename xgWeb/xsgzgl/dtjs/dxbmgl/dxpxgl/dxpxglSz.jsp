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
		function save(){
			lock();
		 	jQuery("#form").ajaxSubmit({
				url:'dtjs_dxbmgl_dxpxglSz.do',
				type:"post",
				dataType:"json",
				data:{"type":"save"},
				success:function(data){
					if(data||data=='true'){
			 			alertInfo("����ɹ�!");
			 			var api = frameElement.api;
			 			api.opener.reload();
						api.close();
			    	}else{
			    		alertInfo("����ʧ��!");
				 		unlock();
			    	}
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});
		}
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
					<td width="34%">${dxpxglModel.pxqc}
					<input type="hidden" name="pxqc" id="pxqc" value="${dxpxglModel.pxqc}"></td>
				</tr>
				<tr>
					<th width="16%">���ڳɼ�</th>
					<td width="34%"><html:text maxlength="3" property="kqcjbfb" styleId="kqcjbfb" value="${dxpxglModel.kqcjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
				<tr>
					<th width="16%">ʵ���ɼ�</th>
					<td width="34%"><html:text maxlength="3" property="sjcjbfb" styleId="sjcjbfb" value="${dxpxglModel.sjcjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
				<tr>
					<th width="16%">�ʼǳɼ�</th>
					<td width="34%"><html:text maxlength="3" property="bjcjbfb" styleId="bjcjbfb" value="${dxpxglModel.bjcjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
				<tr>
					<th width="16%">���Գɼ�</th>
					<td width="34%"><html:text maxlength="3" property="kscjbfb" styleId="kscjbfb" value="${dxpxglModel.kscjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
			</tbody>						
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="save();">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="iFClose();">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>

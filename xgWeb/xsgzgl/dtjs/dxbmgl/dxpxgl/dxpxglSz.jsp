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
			 			alertInfo("保存成功!");
			 			var api = frameElement.api;
			 			api.opener.reload();
						api.close();
			    	}else{
			    		alertInfo("保存失败!");
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
					<th width="16%"><font color="red">*</font>培训期次</th>
					<td width="34%">${dxpxglModel.pxqc}
					<input type="hidden" name="pxqc" id="pxqc" value="${dxpxglModel.pxqc}"></td>
				</tr>
				<tr>
					<th width="16%">考勤成绩</th>
					<td width="34%"><html:text maxlength="3" property="kqcjbfb" styleId="kqcjbfb" value="${dxpxglModel.kqcjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
				<tr>
					<th width="16%">实践成绩</th>
					<td width="34%"><html:text maxlength="3" property="sjcjbfb" styleId="sjcjbfb" value="${dxpxglModel.sjcjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
				<tr>
					<th width="16%">笔记成绩</th>
					<td width="34%"><html:text maxlength="3" property="bjcjbfb" styleId="bjcjbfb" value="${dxpxglModel.bjcjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
				<tr>
					<th width="16%">考试成绩</th>
					<td width="34%"><html:text maxlength="3" property="kscjbfb" styleId="kscjbfb" value="${dxpxglModel.kscjbfb}" onblur="onlyNumInput(this)"/>%</td>
				</tr>
			</tbody>						
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button"  name="提交" id="buttonSave" onclick="save();">保存</button>
			            <button type="button"  name="关闭" id="buttonClose" onclick="iFClose();">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>

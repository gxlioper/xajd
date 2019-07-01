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
		if(jQuery('#pxqc').val()==""){
			alertInfo("请输入培训期次!！");
			return false;
		}
		if(jQuery("#bmkssj").val()==""){
			alertInfo("请选择开始时间!");
			return false;
		}
		if(jQuery("#bmjssj").val()==""){
			alertInfo("请选择结束时间!");
			return false;
		}
		//时间先后校验
		var kssj=document.getElementById("bmkssj");
		var jssj=document.getElementById("bmjssj");
		if(kssj.value>jssj.value){//判断开始时间结束时间
			alertInfo("开始时间不可以晚于的结束时间!");
			return false;
		}
		var pxsj=jQuery("#pxsj").val();
		if(jQuery("#pxsj").val()==""){
			alertInfo("请选择培训时间!");
			return false;
		}
		if(jssj.value>pxsj){//判断开始时间结束时间
			alertInfo("培训时间不可以早于的报名时间!");
			return false;
		}
		lock();
	 	jQuery("#form").ajaxSubmit({
			url:'dtjs_dxbmgl_dxpxglXg.do',
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
					<td width="34%">
						<input type="hidden" id="pxqc" name="pxqc" value="${dxpxglModel.pxqc}" />
						${dxpxglModel.pxqc}
					</td>
					<th width="16%">发布人</th>
					<td width="34%">${dxpxglModel.fbrxm}</td>
				</tr>
				<tr>
					<th width="16%"><font color="red">*</font>报名开始时间</th>
					<td>
						<input name="bmkssj" id="bmkssj" onclick="return showCalendar(this.id,'yyyy-MM-dd')" value="${dxpxglModel.bmkssj}"/>
					</td>
					<th width="16%"><font color="red">*</font>报名结束时间</th>
					<td>
						<input name="bmjssj" id="bmjssj" onclick="return showCalendar(this.id,'yyyy-MM-dd')" value="${dxpxglModel.bmjssj}"/>
					</td>
				</tr>
				<tr>
					<th width="16%"><font color="red">*</font>培训时间</th>
					<td colspan="3">
						<input name="pxsj" id="pxsj" onclick="return showCalendar(this.id,'yyyy-MM-dd')" value="${dxpxglModel.pxsj}"/>
					</td>
				</tr>
				<tr>
					<th width="16%">培训内容</th>
					<td colspan="3">						
						<html:textarea property="pxnr" styleId="pxnr" rows="5" cols="75" value="${dxpxglModel.pxnr}"/>
					</td>
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

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
			alertInfo("��������ѵ�ڴ�!��");
			return false;
		}
		if(jQuery("#bmkssj").val()==""){
			alertInfo("��ѡ��ʼʱ��!");
			return false;
		}
		if(jQuery("#bmjssj").val()==""){
			alertInfo("��ѡ�����ʱ��!");
			return false;
		}
		//ʱ���Ⱥ�У��
		var kssj=document.getElementById("bmkssj");
		var jssj=document.getElementById("bmjssj");
		if(kssj.value>jssj.value){//�жϿ�ʼʱ�����ʱ��
			alertInfo("��ʼʱ�䲻�������ڵĽ���ʱ��!");
			return false;
		}
		var pxsj=jQuery("#pxsj").val();
		if(jQuery("#pxsj").val()==""){
			alertInfo("��ѡ����ѵʱ��!");
			return false;
		}
		if(jssj.value>pxsj){//�жϿ�ʼʱ�����ʱ��
			alertInfo("��ѵʱ�䲻�������ڵı���ʱ��!");
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
					<td width="34%">
						<input type="hidden" id="pxqc" name="pxqc" value="${dxpxglModel.pxqc}" />
						${dxpxglModel.pxqc}
					</td>
					<th width="16%">������</th>
					<td width="34%">${dxpxglModel.fbrxm}</td>
				</tr>
				<tr>
					<th width="16%"><font color="red">*</font>������ʼʱ��</th>
					<td>
						<input name="bmkssj" id="bmkssj" onclick="return showCalendar(this.id,'yyyy-MM-dd')" value="${dxpxglModel.bmkssj}"/>
					</td>
					<th width="16%"><font color="red">*</font>��������ʱ��</th>
					<td>
						<input name="bmjssj" id="bmjssj" onclick="return showCalendar(this.id,'yyyy-MM-dd')" value="${dxpxglModel.bmjssj}"/>
					</td>
				</tr>
				<tr>
					<th width="16%"><font color="red">*</font>��ѵʱ��</th>
					<td colspan="3">
						<input name="pxsj" id="pxsj" onclick="return showCalendar(this.id,'yyyy-MM-dd')" value="${dxpxglModel.pxsj}"/>
					</td>
				</tr>
				<tr>
					<th width="16%">��ѵ����</th>
					<td colspan="3">						
						<html:textarea property="pxnr" styleId="pxnr" rows="5" cols="75" value="${dxpxglModel.pxnr}"/>
					</td>
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">	
	function saveSh(){
<%--		if (jQuery("#gs").val() == "" || jQuery("#gs").val() == null || jQuery("#gsshzt").val() == "" || jQuery("#gsshzt").val() == null || jQuery("#gsshyj").val().trim() == "" || jQuery("#gsshyj").val().trim() == null){--%>
<%--			showAlert("�뽫��������д������");--%>
<%--			return false;--%>
<%--		}--%>
		if (jQuery("#gsshzt").val() == "" || jQuery("#gsshzt").val() == null || jQuery("#gsshyj").val().trim() == "" || jQuery("#gsshyj").val().trim() == null){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "zyhdry.do?method=BcDgsh";
		ajaxSubFormWithFun("qnzyryForm",url,function(data){
			 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
		});
	}

<%--	function xz(obj){--%>
<%--		if(obj.value == '1'){--%>
<%--			jQuery("#jg").css("display","");--%>
<%--		}else{--%>
<%--			jQuery("#jg").css("display","none");--%>
<%--		}--%>
<%--	}	--%>
	</script>
</head>
<body>
	<html:form action="/zyhdry" method="post" styleId="qnzyryForm">
		<input type="hidden" name="id" value="${data.id}"/>
		<input type="hidden" name="jbfwgs" value="${data.jbfwgs}" />
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				 </table>
		
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>���Ϣ</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<th style="width:16%">
						������
					</th>
					<td style="width:34%">
						${data.fzrxm}
					</td>
					<th style="width:16%">
						��֯����
					</th>
					<td style="width:34%">
						${data.zzbm}
					</td>
				</tr>
				<tr>
					<th>
						���ʼʱ��
					</th>
					<td>
						${data.hdkssj}
					</td>
					<th>
						�����ʱ��
					</th>
					<td>
						${data.hdjssj}
					</td>
				</tr>
				<tr>
					<th>
						������ֹʱ��
					</th>
					<td>
						${data.bmjzsj}
					</td>
					<th>
						��ص�
					</th>
					<td colspan="3">
						${data.hddd}
					</td>
				</tr>
				<tr>
					<th>
						��������ʱ
					</th>
					<td colspan="3">
						${data.jbfwgs}
					</td>
				</tr>
				<tr>
					<th>
						����ѧԺ
					</th>
					<td colspan="3">
						${data.xymc}
					</td>
				</tr>
				<tr>
					<th>
						����
					</th>
					<td colspan="3">
						<logic:notEmpty name="data" property="fjpath"> 
							<img src="${data.fjpath}" width="170px" height="130px"/>
						</logic:notEmpty>
						<logic:empty name="data" property="fjpath">
							<img src="default_dekt.jpg" width="170px" height="130px"/>
						</logic:empty>
					</td>
				</tr>		
			</tbody>	
			<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th >
						<font color="red">*&nbsp;</font>��˽��
					</th>
					<td colspan="3">
						<select id="gsshzt" name="gsshzt">
<%--							<option value="">---��ѡ��---</option>--%>
							<option value="1">ͨ��</option>
<%--							<option value="2">�˻�</option>--%>
						</select>
					</td>
				</tr>
			</tbody>
			<tbody>							
					<tr>
						<th>
							<font color="red">*&nbsp;</font>������
						</th>
						<td colspan="3">
							<select name="fwjg" id="fwjg" style="width: 100px;">
								<option value="����_1">
									����
								</option>
								<option value="��������_0.75">
									��������
								</option>
								<option value="һ������_0.5">
									һ������
								</option>
								<option value="������_0">
									������
								</option>
							</select>
						</td>
					</tr>				
			</tbody>
			<tbody>
				<tr>
					<th>
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<textarea id="gsshyj" rows="5" name="gsshyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>

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
		if (jQuery("#shzt").val() == "" || jQuery("#shzt").val() == null || jQuery("#shyj").val().trim() == "" || jQuery("#shyj").val().trim() == null){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "zyhd.do?method=BcDgsh";
		ajaxSubFormWithFun("qnzyhdForm",url,function(data){
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
	</script>
</head>
<body>
	<html:form action="/zyhd" method="post" styleId="qnzyhdForm">
		<input type="hidden" name="hdid" value="${data.hdid}"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>־Ը�߻��Ϣ</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<th style="width:16%">
						�����
					</th>
					<td style="width:34%">
						${data.hdmc}
					</td>
					<th style="width:16%">
						��������
					</th>
					<td style="width:34%">
						${data.fwlxmc}
					</td>
				</tr>
				<tr>
					<th>
						��������ʱ
					</th>
					<td>
						${data.jbfwgs}
					</td>
					<th>
						��ص�
					</th>
					<td>
						${data.hddd}
					</td>
				</tr>
				<tr>
					<th>
						�������
					</th>
					<td>
						${data.fwdx}
					</td>
					<th>
						�޶�����
					</th>
					<td>
						${data.xdrs}
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
						��֯����
					</th>
					<td>
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
						����������
					</th>
					<td>
						${data.fzrxm}
					</td>
					<th>
						�������ֻ���
					</th>
					<td>
						${data.hdfzrlxfs}
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
				<tr>
					<th>
						�����
					</th>
					<td colspan="3">
						${data.hdxq}
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
						<select id="shzt" name="shzt">
							<option value="1">ͨ��</option>
							<option value="2">��ͨ��</option>
							<option value="3">�˻�</option>
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
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
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

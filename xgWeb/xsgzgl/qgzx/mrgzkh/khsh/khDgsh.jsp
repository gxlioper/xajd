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
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		/*if("0" == jQuery("#yxgs").val()) {
			showAlert("��Ч��ʱ����Ϊ�㣡");
			return false;
		}*/
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "mrgzkhKhsh.do?method=khDgsh&type=save";
		ajaxSubFormWithFun("GzkhKhshForm",url,function(data){
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
	<html:form action="/mrgzkhKhsh" method="post" styleId="GzkhKhshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="sqsj" styleId="sqsj"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<html:hidden name="model" property="cjbz" styleId="cjbz"/>
		<html:hidden name="model" property="gzrq" styleId="gzrq"/>
		<html:hidden name="model" property="xn" styleId="xn"/>
		<html:hidden name="model" property="gs" styleId="gs" value="${rs.yxgs}"/>
		<html:hidden name="model" property="gwdm" styleId="gwdm"/>
		<div style="overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
				<tr>
					<th colspan="4">
						<span>��д��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>���˵�λ</th>
				<td>
						${rs.yrdwmc}
				<th>ѧ��</th>
				<td>
						${rs.xn}
				</td>
			</tr>
			<tr>
				<th>��λ</th>
				<td>
						${rs.gwmc}
				</td>
				<th>��������</th>
				<td>
						${rs.gzrq}
				</td>
			</tr>
			<tr>
				<th>��ʱ</th>
				<td>
						${rs.gs}��Сʱ��
				</td>
				<th>����ʱ���</th>
				<td>
						${rs.gzkssj}��${rs.gzjssj}
				</td>
			</tr>
					</tbody>
			<thead>
					<tr>
						<th colspan="4">
							<span>������Ϣ</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
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
					<tr>
						<th >
							��˽��
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=mrgzkh&id=shyj" />
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

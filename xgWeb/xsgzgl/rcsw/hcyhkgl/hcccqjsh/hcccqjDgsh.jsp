<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	<script type="text/javascript">
	

	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.ccqjtxid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	function saveShzt(){
		
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("��ѡ�����״̬��");
			return false;
		}
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("����д��������");
			return false;
		}
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "ͨ��";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "��ͨ��";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "�˻�";
		}
		
		showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
			var url = "rcsw_hcyhk_hcccqjshgl.do?method=hcccqjDgsh&type=save";
			ajaxSubFormWithFun("hcccqjshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
		
	}
	
	</script>
</head>
<body>
	<html:form action="/rcsw_hcyhk_hcccqjshgl" method="post" styleId="hcccqjshForm">
			
		<html:hidden name="model" property="ccqjtxid" styleId="ccqjtxid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		
		<html:hidden name="model" property="txsj" styleId="txsj"/>		
		<html:hidden name="model" property="xn" styleId="xn"/>
		<html:hidden name="model" property="xq" styleId="xq"/>
		<html:hidden name="model" property="ccqdz" styleId="ccqdz"/>		
		<html:hidden name="model" property="cczdz" styleId="cczdz"/>
		<html:hidden name="model" property="bz" styleId="bz"/>
			
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
			</table>
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>�𳵳˳�������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%">
					${rs.xn}
				</td>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					�˳�����
				</th>
				<td align="left" width="30%" colspan="3">
					${rs.ccqdz}��${rs.cczdz}
				</td>
			</tr>	
			<logic:equal name="xxdm" value="10351">
		    	<th>����ԭ��</th>
				<td colspan="3">
			    	${rs.hcyhklxmc}
				</td>
		    </logic:equal>
			<tr>	
				<th>
					��ע
				</th>
				<td colspan="3" style="word-break:break-all;" width="650px">
					${rs.bz}
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>�����ʷ</span>
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
						<th>
							<font color="red">*</font>��˽��
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">��200��</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=hcyhk&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top:5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
			
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveShzt();return false;">
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

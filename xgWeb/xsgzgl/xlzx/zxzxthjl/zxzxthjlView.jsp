<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src='xsgzgl/xlzx/zxzxthjl/js/zxzxthjlList.js'></script>
		<script type="text/javascript">
			jQuery(function(){
				var cbpgdm = jQuery('#cbpgdm').val();
				if(cbpgdm=="1"){
					jQuery("#ybwtlb1").hide();
					jQuery("#zajb1").hide();
				}else if(cbpgdm=="2"){
					jQuery("#ybwtlb1").show();
					jQuery("#zajb1").hide();
				}else if(cbpgdm=="3"){
					jQuery("#ybwtlb1").hide();
					jQuery("#zajb1").show();
				}
				jQuery("#zajbsmzx1").hide();
				jQuery("#cbpgjg1").hide();
				jQuery("#sfzj1").hide();
				
			var ybwtlb = jQuery("#ybwtlb").val();
				if(ybwtlb.length>0){
					var ybwtlbmc = ybwtlb.split(",");
					for ( var i = 0; i < ybwtlbmc.length; i++) {
								if( ybwtlbmc[i] == "������ѯ"){
									jQuery("#zajbsmzx1").show();
								}
					}
				}
				
			var zajb = jQuery("#zajb").val();
			if(zajb.length>0){
				var zajbmc = zajb.split(",");
				for ( var i = 0; i < zajbmc.length; i++) {
							if( zajbmc[i] == "��������"){
								jQuery("#cbpgjg1").show();
							}		
							if( zajbmc[i] == "������ѯ"){
								jQuery("#zajbsmzx1").show();
							}
							if( zajbmc[i] == "��������"){
								jQuery("#sfzj1").show();
							}									
						}
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/xlzx_thjl_zxzxthjlgl.do" styleId="zxzxthjlForm" method="post" >
		<input type="hidden" name="ytr" id="ytr" value="${jsInfo.zgh}" />
		<input type="hidden" name="cbpgdm" id="cbpgdm" value="${thjlxx.cbpgdm}" />
		<input type="hidden" name="ybwtlb" id="ybwtlb" value="${thjlxx.ybwtlb}" />
		<input type="hidden" name="zajb" id="zajb" value="${thjlxx.zajb}" />
		<input type="hidden" name="zajbsmzx" id="zajbsmzx" value="${thjlxx.zajbsmzx}" />
		<input type="hidden" name="cbpgjg" id="cbpgjg" value="${thjlxx.cbpgjg}" />
		<input type="hidden" name="sfzj" id="sfzj" value="${thjlxx.sfzj}" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
		<html:hidden property="id"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr> 
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">ѧ��</th>
							<td style="width:35%">
								<html:hidden property="xh" styleId="xh"/>
								<bean:write name="jbxx" property="xh"/>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>�Ա�</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xb"/>
								</logic:present>
							</td>
							<th>�༶</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
					<tr>
						<th>�꼶</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
							</logic:present>
						</td>
						<th>ѧԺ</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
							</logic:present>
						</td>
						<th>��ϵ�绰</th>
						<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="sjhm"/>
							</logic:present>
						</td>
					</tr>
					</tbody>
				<thead>
						<tr >
							<th colspan="4">
								<span≯����ʦ��Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody id="thjlxx">
					<tr style="height:10px">
						<th≯����ʦ</th>
						<td  width="34%">
							${thjlxx.zgxm}
						</td>
						<th>ְ����</th>
						<td>
							${thjlxx.ytr}
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							${thjlxx.zgxb}
						</td>
						<th>����</th>
						<td >
							${thjlxx.zgbmmc}
						</td>
					</tr>
					</tbody>
				<thead>
					<tr> 
						<th colspan="4">
							<span≯������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th≯��ʱ��</th>
						<td width="34%" colspan="3">
							<bean:write name="thjlxx" property="thsj"/>
						</td>
					</tr>
					<tr>
						<th>�����������</th>
						<td colspan="3">
						<bean:write name="thjlxx" property="jbqkms"/>
						</td>
					</tr>
					<tr>
						<th width="20%">��������</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="cbpgdmmc"/>
						</td>
					</tr>
					<tr id="ybwtlb1">
						<th >һ�������������</th>
						<td colspan="3" >
							<bean:write name="thjlxx" property="ybwtlb"/>
						</td>
				    </tr>
					<tr id="zajb1">
						<th>�����ϰ���</br>���񼲲�</th>
						<td colspan="3" >
							<bean:write name="thjlxx" property="zajb"/>
						</td>
				    </tr>
				    <tr id="cbpgjg1">
						<th width="16%">�����������</th>
						<td width="34%" colspan="3">
							<bean:write name="thjlxx" property="cbpgjg"/>
						</td>
					</tr>
					<tr id="zajbsmzx1">
						<th>�Ƿ�ԤԼ��ѯʱ��</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="zajbsmzx"/>
						</td>
					</tr>
					<tr id="sfzj1">
						<th>�Ƿ�ת��</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="sfzj"/>
						</td>
					</tr>
					<tr>
						<th>��ע<br/>�Ƿ���Ҫ��һ�����</th>
						<td colspan="3">
							<bean:write name="thjlxx" property="bz"/>
						</td>
					</tr>
				</tbody>
				</table>
				</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
										�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>


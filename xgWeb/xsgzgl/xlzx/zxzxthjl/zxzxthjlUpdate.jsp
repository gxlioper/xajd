<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src='xsgzgl/xlzx/zxzxthjl/js/zxzxthjlList.js'></script>
		<script type="text/javascript">
			jQuery(function(){
				var cbpgdm = jQuery('#cbpgdm').val();
				if(cbpgdm=="1"){
					jQuery("#ybwtlb1").hide();
					jQuery("#zajb1").hide();
				}else if(cbpgdm=="2"){//ѡ��ڶ���ѡ��ʱ
					jQuery("#ybwtlb1").show();
					jQuery("#zajb1").hide();
				}else if(cbpgdm=="3"){//ѡ�������ѡ��ʱ
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
						jQuery("input[name=ybwtlb]").each(function(){
							if(jQuery(this).val() == ybwtlbmc[i]){
								jQuery(this).attr("checked","checked");
								if( ybwtlbmc[i] == "������ѯ"){
									jQuery("#zajbsmzx1").show();
								}		
							}
						});
					}
				}
				
				var zajb = jQuery("#zajb").val();
				if(zajb.length>0){
					var zajbmc = zajb.split(",");
					for ( var i = 0; i < zajbmc.length; i++) {
						jQuery("input[name=zajb]").each(function(){
							if(jQuery(this).val() == zajbmc[i]){
								jQuery(this).attr("checked","checked");
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
						});
					}
				}
				
			});
		</script>
	</head>
	<body>
		<html:form action="/xlzx_thjl_zxzxthjlgl.do" styleId="zxzxthjlForm" method="post" >
		<input type="hidden" name="ytr" id="ytr" value="${jsInfo.zgh}" />
		<input type="hidden" name="ybwtlb" id="ybwtlb" value="${zxzxthjlForm.ybwtlb}" />
		<input type="hidden" name="zajb" id="zajb" value="${zxzxthjlForm.zajb}" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<html:hidden property="id"/>
		<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
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
				<tbody id="jsInfo">
					<tr style="height:10px">
						<th  width="16%"≯����ʦ</th>
						<td  width="34%">${jsInfo.xm}</td>
						<th  width="16%">ְ����</th>
						<td  width="34%">${jsInfo.zgh}</td>
					</tr>
					<tr style="height:10px">
						<th  width="16%">�Ա�</th>
						<td  width="34%">${jsInfo.xb}</td>
						<th  width="16%">����</th>
						<td  width="34%">${jsInfo.bmmc}</td>
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
						<th width="16%">
							<font color="red">*</font≯��ʱ��
						</th>
						<td width="34%" colspan="3">
							<html:text property="thsj" styleId="thsj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
					</tr>
					<tr style="height:100px;">
						<th>
							<font color="red">*</font>�����������
							<br><font color="red">(��200��)</font><br/>
						</th>
						<td colspan="3">
							<html:textarea  property='jbqkms' styleId="jbqkms" onblur="chLengs(this,200);" style="word-break:break-all;width:99%"  rows='4' />
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*</font>��������
						</th>
						<td colspan="3">
							<html:select  property="cbpgdm" styleId="cbpgdm" style="width:155px" onchange="javascript:change_cbpgdm();return false;">
								<html:option value="1">û�����⣬������ѯ</html:option>
    							<html:option value="2">һ����������</html:option>
    							<html:option value="3">�����ϰ��;��񼲲�</html:option>
							</html:select>
						</td>
					</tr>
					<tr id="ybwtlb1">
							<th ><font color="red">*</font>һ�������������</th>
							<td colspan="3" >
							<logic:notEmpty name="ybxlwtlbList">
							<logic:iterate name="ybxlwtlbList" id="s"  indexId="i">
								<label><input type="checkbox" name="ybwtlb" value="${s.mc}" onclick="javascript:click_ybwtlb(this);"/>${s.mc}</label>
							</logic:iterate>
							</logic:notEmpty>
							</td>
				    </tr>
					<tr id="zajb1">
							<th><font color="red">*</font>�����ϰ���</br>���񼲲�</th>
							<td colspan="3" >
								<input type="checkbox" name="zajb" value="��������" onclick="javascript:click_zajbcbpg(this);">��������
								<input type="checkbox" name="zajb" value="������ѯ" onclick="javascript:click_zajbjyzx(this);">������ѯ
								<input type="checkbox" name="zajb" value="��������" onclick="javascript:click_zajbqtjy(this);">��������
							</td>
				    </tr>
				    <tr id="cbpgjg1">
						<th width="16%">
							<font color="red">*</font>�����������
							<br><font color="red">(��50��)</font><br/>
						</th>
						<td width="34%" colspan="3">
							<html:textarea property="cbpgjg" styleId="cbpgjg" onblur="chLengs(this,50);" style="word-break:break-all;width:99%" rows='2'/>
						</td>
					</tr>
					<tr id="zajbsmzx1">
						<th>
							<font color="red">*</font>�Ƿ�ԤԼ��ѯʱ��
						</th>
						<td colspan="3">
							<html:select  property="zajbsmzx" styleId="zajbsmzx" style="width:66px;">
								<html:option value="2">---��ѡ��---</html:option>
								<html:option value="1">��</html:option>
								<html:option value="0">��</html:option>
							</html:select>				
						</td>
					</tr>
					
					<tr id="sfzj1">
						<th>
							<font color="red">*</font>�Ƿ�ת��
						</th>
						<td colspan="3">
							<html:select  property="sfzj" styleId="sfzj" style="width:66px;">
								<html:option value="2">---��ѡ��---</html:option>
								<html:option value="1">��</html:option>
								<html:option value="0">��</html:option>
							</html:select>				
						</td>
					</tr>
					<tr style="height:100px;">
						<th>
							��ע<br/>�Ƿ���Ҫ��һ�����
							<br><font color="red">(��500��)</font><br/>
						</th>
						<td colspan="3">
							<html:textarea  property='bz' styleId="bz" onblur="chLengs(this,500);" style="word-break:break-all;width:99%"  rows='4' />
						</td>
					</tr>
				</tbody>		
			</table>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveUpdate();">
										�� ��
									</button>
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

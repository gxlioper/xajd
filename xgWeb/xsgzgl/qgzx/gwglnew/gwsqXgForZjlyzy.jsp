<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsqForZjlyzy.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
				<script type="text/javascript"> 
			jQuery(document).ready(function(){
				changeYxssz();
				var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
				//�������� ���õ�н������
				var gwzgcjsx=jQuery("#gwzgcjsx").val();
				var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
				//��λ���ó������
				var gwcjsx=jQuery("#gwcjsx").val();
				//����˸�λδ����
					if(gwcjsx==""){
						jQuery("#gwcjsx").val(gwzgcjsx);
						jQuery("#gwcjsxh").text(gwzgcjsx);
					}
					
				if("10704"!=jQuery("#xxdm").val()){
					if("no"==sfsdgwcjsx){
						jQuery("#gwcjsxTr").hide();
					}else{
						jQuery("#gwcjsxTr").show();
						if("no"==sfkgggwcjsx){
							jQuery("#gwcjsx").hide();
							jQuery("#gwcjsxh").show();
							jQuery("#sxcolor").hide();
						}else{
							jQuery("#gwcjsx").show();
							jQuery("#gwcjsxh").hide();
							jQuery("#sxcolor").show();
						}
					}
				}else{
					var gwxz = jQuery("#gwlx").find("option:selected").text();
					if(gwxz=="�̶���"){
						jQuery("#gdgcjbzTr").show();
						jQuery("#gwcjsxTr").hide();
					}else{
						if("no"==sfsdgwcjsx){
							jQuery("#gwcjsxTr").hide();
						}else{
							jQuery("#gwcjsxTr").show();
							if("no"==sfkgggwcjsx){
								jQuery("#gwcjsx").hide();
								jQuery("#gwcjsxh").show();
								jQuery("#sxcolor").hide();
							}else{
								jQuery("#gwcjsx").show();
								jQuery("#gwcjsxh").hide();
								jQuery("#sxcolor").show();
							}
						}
					}
				}
				var doType=jQuery("#doType").val();
				//�鿴����
				if(doType!="update"){
					if("10704"==jQuery("#xxdm").val()&&'${rs.gwxzmc }'=="�̶���"){
						jQuery("#gdgcjbzTr").show();
						jQuery("#gwcjsxTr").hide();
					}
					jQuery("#gwcjsx").hide();
					jQuery("#gwcjsxh").show();
					jQuery("#sxcolor").hide();
					jQuery(".bz").hide();
				}
				if(jQuery("#xxdm").val() == "10351"){
					jQuery("#qxCheck").bind("click",function(){
						xz(this);
					});
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.gwdm}&tt="+new Date().getTime());
			});

			function selTea(){
				showDialog("ѡ�����Ա", 770, 520, "stglStsq.do?method=getTea")	
			}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwglnew" method="post" styleId="form">
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="oldGwxh" id="oldGwxh" value="${rs.gwxh }" />
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden"  id="sqsj" value="${rs.sqsj}"/>
			<div style='tab��width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table border="0" class="formlist">
					<logic:equal name="doType" value="update">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx" name="tbody_jbxx">
						<tr>
							<th width="16%">
								����
							</th>

							<td width="34%">
								<input type="hidden" id="yrbm" name="yrbm" value="${rs.yrbm }" />
								${rs.yrdwmc }
							</td>
							<th width="16%">
								<font color="red">*</font>��ϵ��
							</th>
							<td width="34%">
								<input type="text" id="lxr" name="lxr" value="${rs.lxr }" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								�걨ʱ��
							</th>
							<td width="34%">
								<input type="hidden" id="sqsj" name="sqsj" value="${rs.sqsj }" />
								${rs.sqsj }
							</td>
							<th width="16%">
								<font color="red">*</font>��ϵ�绰
							</th>
							<td width="34%">
								<input type="text" id="lxPhone" name="lxPhone" onblur="checkPhone(this);" value="${rs.lxphone }" />
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="5">
									<span>��λ������Ϣ </span>
								</th>
							</tr>
						</thead>
						<tr>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>

							<td width="34%">
								<input type="text" id="gwmc" name="gwmc" value="${rs.gwmc }" />
							</td>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							<td width="34%">
								<input type="text" id="xqrs" name="xqrs" value="${rs.xqrs }" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							<td>
								<html:select property="gwlx" styleId="gwlx" onclick="changeXq();" value="${rs.gwxzmc }">
									<html:option value="�̶���">�̶���</html:option>
									<html:option value="ʵϰ��">ʵϰ��</html:option>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							<td width="34%">
								<input type="text" id="knsrs" name="knsrs" value="${rs.knsrs }" />
							</td>
						</tr>
						<tr id="zxsInfo">
							<th><span class="red">*</span>��λ�����</th>
							<td>
								<input type="text" id="gwshr" class="gwshr" name="gwshr" style="width:120px;" readonly="readonly" value="${rs.gwshr }"/>
								<button type="button" onclick="showDialog('��ʦѡ��',680,480,'qgzx_gwglnew.do?method=showFdys');return false;" class="btn_01" id="buttonFindStu">
										ѡ��
								</button>
							</td>
							<th>
								<span class="red">*</span>��λ���������
							</th>
							<td width="30%" class="xm">
								<input type="text" id="gwshrxm" class="gwshrxm" name="gwshrxm"
									style="width: 120px;" readonly="readonly" value="${rs.gwshrxm }"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��λҪ��
							</th>
							<td colspan="3">
								<html:textarea property='gwryyq' styleId="gwryyq"
									style="word-break:break-all;width:97%" 
									onblur="chLengs(this,1000);" rows='6' value="${rs.gwryyq }"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>��������
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="gwms" style="width:97%" rows="6" 
									onblur="chLengs(this,500);" styleId="gwms"  value="${rs.gwms }"/>
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<logic:notEqual name="doType" value="update">
						<thead>
						<tr>
							<th colspan="4">
								<span>��λ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx" name="tbody_jbxx">
						<tr>
							<th width="16%">
								����
							</th>

							<td width="34%">
								<input type="hidden" id="yrbm" name="yrbm" value="${rs.yrbm }" />
								${rs.yrdwmc }
							</td>
							<th width="16%">
								��ϵ��
							</th>
							<td width="34%">
								<input type="hidden" id="lxr" name="lxr" value="${rs.lxr }" />
								${rs.lxr }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�걨ʱ��
							</th>
							<td width="34%">
								<input type="hidden" id="sqsj" name="sqsj" value="${sqsj }" />
								${rs.sqsj }
							</td>
							<th width="16%">
								��ϵ�绰
							</th>
							<td width="34%">
								<input type="hidden" id="lxPhone" name="lxPhone" value="${rs.lxphone }" />
								${rs.lxphone }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>

							<td width="34%">
								<input type="hidden" id="gwmc" name="gwmc" value="${rs.gwmc }" />
								${rs.gwmc }
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<input type="hidden" id="xqrs" name="xqrs" value="${rs.xqrs }" />
								${rs.xqrs }
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								<input type="hidden" id="gwxzmc" name="gwxzmc" />
								${rs.gwxzmc }
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<input type="hidden" id="knsrs" name="knsrs" />
								${rs.knsrs }
							</td>
						</tr>
						<tr id="zxsInfo">
							<th width="16%">
								��λ�����
							</th>
							<td width="34%">
								<input type="hidden" id="gwshr" name="gwshr" />
								${rs.gwshr }
							</td>
							<th width="16%">
								��λ���������
							</th>
							<td width="34%">
								<input type="hidden" id="gwshrxm" name="gwshrxm" />
								${rs.gwshrxm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λҪ��
							</th>
							<td colspan="3">
								<input type="hidden" id="gwryyq" name="gwryyq" />
								${rs.gwryyq }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td colspan="3">
								<input type="hidden" id="gwms" name="gwms" />
								${rs.gwms }
							</td>
						</tr>
						<logic:notEmpty name="rs" property="xn" >
							<tr>
								<th width="16%">
									�ڸ����
								</th>
								<td colspan="3">
									<input type="hidden" id="xn" name="xn" />
									${rs.xn}${rs.xqmc}
								</td>
							</tr>
							<tr>
								<th width="16%">��Чʱ����</th>
								<td width="34%">
									${rs.yxsszmc}
								</td>
								<th width= 16%>�Ƿ��ܸ�λ����������</th>
								<td width="34%">
									${rs.sfsgwsqsxzmc}
								</td>
							</tr>
								<th width="16%">��λ��ʼ����</th>
								<td width="34%">
									${rs.gwkssj}
								</td>
								<th width= 16%>��λ��������</th>
								<td width="34%">
									${rs.gwjssj}
								</td>
							</tr>
						</logic:notEmpty>
					</tbody>
						<thead>
						<tr>
							<th colspan="8">
								<span>������Ϣ</span>
							</th>
						</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="8" id="shlccx">
								</td>
							</tr>
				
						</tbody>
					</logic:notEqual>
				</table>
			</div>
			<div style="height: 50px">
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:equal name="doType" value="update">
										<button type="button" onclick="bcXgGwsqForZjlyzy('save')">
											�� ��
										</button>
										<button type="button" onclick="bcXgGwsqForZjlyzy('submit')">
											�ύ����
										</button>
									</logic:equal>
										<button type="button" onclick="Close();return false;">
											�� ��
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


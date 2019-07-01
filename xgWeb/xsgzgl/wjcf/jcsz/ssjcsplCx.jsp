<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" defer="defer">
		function saveSpl(){
			var url="wjcfJcsz_ssjcspl.do?method=ssjcsplBc";
			var ssspl = jQuery('#ssspl').val();
			var jcspl = jQuery('#jcspl').val();
			if (ssspl =="" || jcspl =="" ||ssspl==null||jcspl==null) {
				alertError("��*������Ϊ���");
				return false;
			}
			refreshForm(url);
		}
		function onShow() {
			var ssjg = jQuery('#ssjg').val();
			var sqjg = jQuery('#sqjg').val();
			if (ssjg != "" && ssjg != "0" && sqjg !="" && sqjg != "0") {
				jQuery('#btn_bc').attr('disabled', 'disabled');
			}
			if (ssjg =="0" && sqjg=="0") {
				document.getElementById("pts").style.display="none";
			}
		}
		
		jQuery(function(){
			onShow();
		})		
		</script>
		
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<logic:empty name="shlcList">
			<div class="prompt" id="pts">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��δ�������������뵽ϵͳ���� - �������̹��������ñ�ģ����������̣�
			</p>
		</div>
		</logic:empty>
		<!-- ��ʾ��Ϣ end-->
<%--		<div class="prompt" id="pts">--%>
<%--			<h3>--%>
<%--				<span>��ʾ��</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				����ѧ�����ߣ���������������δ���������ܽ����޸ģ�--%>
<%--			</p>--%>
<%--		</div>--%>
		
		
		
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/wjcfJcsz_ssjcspl" method="post">
			<input type="hidden" name="ssjg" id="ssjg" value="${rs.ssjg }"/>
			<input type="hidden" name="sqjg" id="sqjg" value="${rs.sqjg}"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<!-- ά����Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>��������������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							��������
						</th>
						<td width="">
							<logic:present name="rs">
							<logic:equal name="rs" property="ssjg" value="0">
							
									<html:select name="rs" property="ssspl" styleId="ssspl" style="width:240px" value="${rs.ssspl}">
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
									
								</logic:equal>
								<logic:notEqual value="0" name="rs" property="ssjg">
								<html:select name="rs" property="spl" styleId="spl" style="width:240px" value="${rs.ssspl}" disabled="true">
										<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
								<input type="hidden" name="ssspl" id="ssspl" value="${rs.ssspl }"/>
								</logic:notEqual>
								</logic:present>
								
								<logic:notPresent name="rs">
									<html:select name="rs" property="ssspl" styleId="ssspl" style="width:240px" value="${rs.ssspl}">
										<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
								</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>����<bean:message key="wjcf.text" />����������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							��������
						</th>
						<td width="">
							
							<logic:present name="rs">
							
<%--								��������Ѿ���¼��������У����ﲻ������  20140512 785--%>
<%--								<logic:equal name="rs" property="sqjg" value="0">--%>
									<html:select name="rs" property="jcspl" styleId="jcspl" style="width:240px" value="${rs.jcspl}">
											<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
<%--								</logic:equal>--%>
<%--								<logic:notEqual value="0" name="rs" property="sqjg">--%>
<%--									<html:select name="rs" property="cflbdm" styleId="cflbdm" style="width:240px" value="${rs.jcspl}" disabled="true">--%>
<%--											<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>--%>
<%--									</html:select>--%>
<%--									<input type="hidden" name="jcspl" id="jcspl" value="${rs.jcspl }"/>--%>
<%--								</logic:notEqual> --%>
								
							</logic:present>
								
							<logic:notPresent name="rs">
								<html:select name="rs" property="jcspl" styleId="jcspl" style="width:240px" value="${rs.jcspl}">
									<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
								</html:select>
							</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<logic:equal value="yes" name="writeAble">
									<!-- ���� -->
									<button type="button"  onclick="saveSpl();return false;" id="btn_bc"  <logic:empty name="shlcList">disabled='disabled'</logic:empty>>
										����
									</button>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ά����Ϣ end-->
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
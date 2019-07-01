<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function saveSpl(){
			var url="wjcfJcsz_ssjcspl.do?method=ssjcsplBc";
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��������ʹ��ʱ,�������ò��ɸ���
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/wjcfJcsz_ssjcspl" method="post">

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
							<logic:present name="shlcList">
								<logic:iterate id="shlc" name="shlcList" indexId="index">
									<logic:present name="rs">
										<input type="radio" name="ssspl" id="ssspl"
										<logic:greaterEqual name="rs" property="ssjg" value="1">
											disabled="none"
										</logic:greaterEqual>
										<logic:equal name="rs" property="ssspl"  value="${shlc.splc}"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
										<!-- ���ñ�ǩ��,����Ӧ����  begin -->
										<logic:greaterEqual name="rs" property="ssjg" value="1">
											<logic:equal name="rs" property="ssspl"  value="${shlc.splc}">
											<input type="hidden" name="ssspl" value="<bean:write name="shlc" property="splc"/>"/>
											</logic:equal>
										</logic:greaterEqual>
										<!-- ���ñ�ǩ��,����Ӧ����  end -->
									</logic:present>
									<logic:notPresent name="rs">
										<input type="radio" name="ssspl" id="ssspl"
										<logic:equal name="index" value="0"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
									</logic:notPresent>
									<bean:write name="shlc" property="lcxx" />
									<br/>
								</logic:iterate>
							</logic:present>
							<logic:notPresent name="shlcList">
								��ǰδ���ô�����������
							</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>���ֽ������������</span>
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
							<logic:present name="shlcList">
								<logic:iterate id="shlc" name="shlcList" indexId="index">
									<logic:present name="rs">
										<input type="radio" name="jcspl" id="jcspl"
										<logic:greaterEqual name="rs" property="sqjg" value="1">
											disabled="none"
										</logic:greaterEqual>
										<logic:equal name="rs" property="jcspl"  value="${shlc.splc}"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
										<!-- ���ñ�ǩ��,����Ӧ����  begin -->
										<logic:greaterEqual name="rs" property="sqjg" value="1">
											<logic:equal name="rs" property="jcspl"  value="${shlc.splc}">
											<input type="hidden" name="jcspl" value="<bean:write name="shlc" property="splc"/>"/>
											</logic:equal>
										</logic:greaterEqual>
										<!-- ���ñ�ǩ��,����Ӧ����  end-->
									</logic:present>
									<logic:notPresent name="rs">
										<input type="radio" name="jcspl" id="jcspl"
										<logic:equal name="index" value="0"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
									</logic:notPresent>
									<bean:write name="shlc" property="lcxx" />
									<br />
								</logic:iterate>
							</logic:present>
							<logic:notPresent name="shlcList">
								��ǰδ���ô�����������
							</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- ���� -->
								<button type="button" onclick="saveSpl();return false;">
									����
								</button>
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
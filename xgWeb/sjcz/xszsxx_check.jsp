<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="xyDisabled('xy');bjLimit('bj')">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em>
			</p>
		</div>
		<!-- ���� end-->
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/data_search" method="post">
			<div class="toolbox">
			<div class="comp_title"><ul><li><a href="#" onclick="refreshForm('xszsxx_check.do');" id="dm_ydlb"><span>�Ա�ʹ�λ����֤</span> </a></li>
			<li><a href="#" onclick="refreshForm('xszsxx_check.do?act=sshf');" id="dm_ydlb"><span>���Ữ����֤</span> </a></li></ul></div>
<%--				<button type="button"  onclick="refreshForm('xszsxx_check.do')"></button>--%>
<%--				<button type="button"  onclick="refreshForm('xszsxx_check.do?act=sshf')"></button>--%>
				<!-- ��ѯ���-->
				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</span>
						</h3>
						<logic:equal value="sshf" name="act">
							<table summary="" class="dateline" align="" width="100%">
								<!-- ��ͷ -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>�꼶</td>
										<td><bean:message key="lable.xb" /></td>
										<td>¥������</td>
										<td>���Һ�</td>
										<td>����ס����</td>
										<td>���ִ�λ��</td>
										<td>�쳣����</td>
									</tr>
								</thead>
								<!-- ��ͷ end-->
								<!--���� -->
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
											<td><bean:write name="s" property="nj"/></td>
											<td><bean:write name="s" property="xy"/></td>
											<td><bean:write name="s" property="ldmc"/></td>
											<td><bean:write name="s" property="qsh"/></td>
											<td><bean:write name="s" property="rzrs"/></td>
											<td><bean:write name="s" property="cws"/></td>
											<td><bean:write name="s" property="exc_type"/></td>
										</tr>
									</logic:iterate>
									<!--���� -->
								</tbody>
							</table>
						</logic:equal>
						<logic:notEqual value="sshf" name="act">
							<table summary="" class="dateline" align="" width="100%">
								<!-- ��ͷ -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>�꼶</td>
										<td>ѧ��</td>
										<td>����</td>
										<td>�Ա�</td>
										<td><bean:message key="lable.xb" /></td>
										<td>רҵ</td>
										<td>¥������</td>
										<td>���Һ�</td>
										<td> ��λ��</td>
										<td>��ס����</td>
										<td>�շѱ�׼</td>
										<td>�쳣����</td>
									</tr>
								</thead>
								<!-- ��ͷ end-->
								<!--���� -->
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
											<td><bean:write name="s" property="nj"/></td>
											<td><bean:write name="s" property="xh"/></td>
											<td><bean:write name="s" property="xm"/></td>
											<td><bean:write name="s" property="xb"/></td>
											<td><bean:write name="s" property="xy"/></td>
											<td><bean:write name="s" property="zymc"/></td>
											<td><bean:write name="s" property="ldmc"/></td>
											<td><bean:write name="s" property="qsh"/></td>
											<td><bean:write name="s" property="cwh"/></td>
											<td><bean:write name="s" property="rzrq"/></td>
											<td><bean:write name="s" property="sfbz"/></td>
											<td><bean:write name="s" property="exc_type"/></td>
										</tr>
									</logic:iterate>
									<!--���� -->
								</tbody>
							</table>
						</logic:notEqual>
<%--						<!--��ҳ-->--%>
<%--						<jsp:include flush="true"--%>
<%--							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>--%>
<%--						<!--��ҳend-->--%>
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="msg" value="${msg }" />

			<script type="text/javascript">
				alert($('msg').value);
			</script>

		</logic:present>
	</body>
</html>

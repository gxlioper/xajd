<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>

	<body>
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/zznl_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ۺ�����-ѧ���ۺ��������ɿ�-IVT��̳�鿴
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
<%--				<thead>--%>
<%--					<tr style="height: 23px">--%>
<%--						<td colspan="4" align="center">--%>
<%--							˼��������������ʷ�����--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--				</thead>--%>
				<tr style="height: 23px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xh"/>
					</td>
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
							<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr style="height: 23px">
				<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
							<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>
			</table>
			<fieldset>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<tr>
						<td>
					<div class="mid_box">
							<table align="center" style="width: 100%" id="rsT" class="tbstyle">
								<!-- ��ӡʱ��һ�в���ʾ- -->
								<thead style="height: 23px">
									<tr>
										<td nowrap="nowrap">
											���
										</td>
										<td nowrap="nowrap">
											������Ŀ
										</td>
										<td nowrap="nowrap">
											����
										</td>
										<td nowrap="nowrap">
											�����ȼ�
										</td>
										<td nowrap="nowrap">
											�����ȼ�
										</td>
										<td nowrap="nowrap">
											�Ӽ���
										</td>
										<td nowrap="nowrap">
											����
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="flag">
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s">
											<tr>
												<td>
													<bean:write name="s" property="rnum" />
												</td>
												<td>
													<bean:write name="s" property="jztm"/>
												</td>
												<td>
													<bean:write name="s" property="xthdrq"/>
												</td>
												<td>
													<bean:write name="s" property="jcdj"/>
												</td>
												<td>
													<bean:write name="s" property="ccdj"/>
												</td>
												<td>
													<bean:write name="s" property="jjf"/>
												</td>
												<td>
													<bean:write name="s" property="pf"/>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</tbody>				
							</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<logic:notEqual value="isview" name="isview">
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonClose" onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" style="width: 80px" id="buttonClose">
						�� ��
					</button> </span>
			</div>
			</logic:notEqual>
			<logic:present name="done">
				<logic:equal value="yes" name="done">
					<script>
	alert('�����ɹ���');
</script>
				</logic:equal>
				<logic:equal value="no" name="done">
					<script>
	alert('����ʧ�ܣ�');
</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
		<script language="JavaScript">
		<!--
<%--		self.moveTo(100,100)--%>
<%--		self.resizeTo("800","600")--%>
		//-->
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ס�޽�� - ��������</a>
			</p>
		</div>

		<!-- ���� end-->
		<html:form action="/gyglZsjg">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<html:hidden property="lddm" styleId="lddm"/>

			<!-- ��ѯ���-->
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>¥����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="czxq" value="��">
							<tr>
								<th style="width:16%">
									����
									<bean:message key="lable.xiaoqu" />
								</th>
								<td colspan="3" style="width:84%">
									${ldMap.xqmc }
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="czyq" value="��">
							<tr>
								<th style="width:16%">
									����
									<bean:message key="lable.yuanqu" />
								</th>
								<td colspan="3" style="width:84%">
									${ldMap.yqmc }
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.ld" />
							</th>
							<td style="width:34%">
								${ldMap.ldmc }
							</td>
							<th style="width:16%">
								<bean:message key="lable.cs" />
							</th>
							<td style="width:34%">
								${ldMap.cs }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								¥���Ա�����
							</th>
							<td style="width:34%">
								${ldMap.xbxd}
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">

							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="formbox">
									<h4 class="datetitle_01">
										<span> ��ѯ��� <logic:empty name="rs">
											&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font>
											</logic:empty> <logic:notEmpty name="rs">
											&nbsp;&nbsp;<font color="blue"></font>
											</logic:notEmpty>
										 </span>
									</h3>
									<div style='width:99%;height:300px;overflow:auto;overflow-x:hidden'>
										<table summary="" class="dateline" align="" width="98%">
											<!-- ��ͷ -->
											<thead>
												<tr align="center" style="cursor:hand">
													<logic:iterate id="tit" name="topTr" offset="0">
														<td id="<bean:write name="tit" property="en"/>" nowrap>
															<bean:write name="tit" property="cn" />
														</td>
													</logic:iterate>
												</tr>
											</thead>
											<!-- ��ͷ end-->
											<!--���� -->
											<logic:notEmpty name="rs">
												<logic:iterate name="rs" id="s" indexId="index">
													<tr onclick="rowOnClick(this);" style="cursor:hand">
														<!-- ��ʾ��Ϣ -->
														<logic:iterate id="v" name="s" offset="0">
															<td align="left" nowrap="nowrap">
																${v }
															</td>
														</logic:iterate>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
											<!--���� end-->

										</table>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button class="button2" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>


			<!-- ��ѯ��� end-->

		</html:form>
	</body>
</html>

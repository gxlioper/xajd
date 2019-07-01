<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function showQsxx(){
			var pkValue=$("pkValue").value;
			var doType=$("doType").value;
			var url = '/xgxt/gyglGywh.do?method=qsxxwh&doType='+doType+'&pkValue='+pkValue;
			refreshForm(url);
		}
		</script>
	</head>
	<body>

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ԣ����-��Ԣά��-ѧ��ס�޼�¼</a>
				</p>
			</div>
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${xsxx.xh}
							</td>
							<th>
								����
							</th>
							<td>
								${xsxx.xm}
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${xsxx.xb}
							</td>
							<th>
								�꼶
							</th>
							<td>
								${xsxx.nj}
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${xsxx.xymc}
							</td>
							<th>
								רҵ
							</th>
							<td>
								${xsxx.zymc}
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								${xsxx.bjmc}
							</td>
							<th>
								<bean:message key="lable.ld" />
							</th>
							<td>
								${xsxx.ldmc}
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.cs" />
							</th>
							<td>
								${xsxx.cs}
							</td>
							<th>
								���Һ�
							</th>
							<td>
								${xsxx.qsh}
							</td>
						</tr>
						<tr>
							<th>
								��λ��
							</th>
							<td>
								${xsxx.cwh}
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="formbox" style='width:98%;height:250px;overflow:auto;overflow-x:hidden'>
									<h5 class="datetitle_01">
										<span> ѧ����ʷס�޼�¼&nbsp;&nbsp; <logic:empty name="rs">
												<font color="red">δ�ҵ��κμ�¼��</font>
											</logic:empty> <logic:notEmpty name="rs">
												<font color="blue"></font>
											</logic:notEmpty> </span>
									</h5>


									<div class="con_overlfow">
										<table summary="" class="dateline" align="" width="100%">
											<thead>
												<tr align="center" style="cursor:hand">
													<logic:iterate id="tit" name="topTr">
														<td id="<bean:write name="tit" property="en"/>" nowrap>
															<bean:write name="tit" property="cn" />
														</td>
													</logic:iterate>
												</tr>
											</thead>
											<logic:notEmpty name="rs">
												<tbody>
													<logic:iterate name="rs" id="s" indexId="index">
														<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">

															<logic:iterate id="v" name="s" offset="0">
																<td nowrap>
																	<bean:write name="v" />
																</td>
															</logic:iterate>

														</tr>
													</logic:iterate>
											</logic:notEmpty>
											<!-- ������ -->
											<%@ include file="/comm/noRows.jsp"%>
											<!-- ������ end-->
											</tbody>

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
									<button class="button2" onclick="showQsxx()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

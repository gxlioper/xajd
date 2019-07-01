<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bdzc/10511/js/bdzc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	<body>
		<html:form action="/xsxx_xqbdzcgl" method="post" styleId="xqbdzcForm">
			<html:hidden property="xn" />
			<html:hidden property="xq" />
		
			<div class='tab' style="tab;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('${xqbdxx.zczt}','<bean:message key="lable.bdzc"/>');">
										<bean:message key="lable.bdzc"/>
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
							${jbxx.xh }
							<div style="display: none;">
								<input type="text" name="xh" value="${jbxx.xh }" readonly="true" id="xh" style="width:120px;"/>
								<logic:notEqual name="userType" value="stu">
									<button class="btn_01" type="button" 
											onclick="showDialog('��ѡ��һ��ѧ��',680,480,'xsxx_xsgl.do?method=showStudents&goto=${path}');return false;">ѡ��</button>
								</logic:notEqual>
							</div>
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${jbxx.xb }
							</td>
							<th>
								���֤��
							</th>
							<td>
								${jbxx.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${jbxx.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${jbxx.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								ѧԺ
							</th>
							<td>
								${jbxx.xymc }
							</td>
							<th>
								�꼶
							</th>
							<td>
								${jbxx.nj }
							</td>
						</tr>
						<tr>
							<th>
								������ò
							</th>
							<td>
								${jbxx.zzmmmc }
							</td>
							<th>
								����
							</th>
							<td>
								${jbxx.mzmc }
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td>
								${jbxx.jtdz }
							</td>
							<th>
								�ֻ�����
							</th>
							<td>
								${jbxx.sjhm }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.bdzc"/>��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��/ѧ��</th>
							<td>${xqbdxx.xn } / ${xqbdxx.xqmc }</td>
							<th><bean:message key="lable.bdzc"/>״̬</th>
							<td>${xqbdxx.zcztmc }</td>
						</tr>
						<tr>
							<th><span class="red">*</span><bean:message key="lable.bdzc"/>ʱ��</th>
							<td colspan="3">
								<input type="text" name="zcsj" value="${xqbdxx.zcsj}" id="zcsj"  style="250px" 
								onclick="return showCalendar('zcsj','yyyy-MM-dd',true);" 
								readonly="true"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>���񽻷���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="95%">
										<tbody id="cwsjxx">
											<tr>
												<th>ѧ��/ѧ��</td>
												<th>��������</td>
												<th>Ӧ�ɽ��</td>
												<th>ʵ�ɽ��</td>
												<th>Ƿ�ѽ��</td>
											</tr>
											<logic:present name="cwsjList">
													<logic:notEmpty name="cwsjList">
														<logic:iterate id="k" name="cwsjList">
															<tr>
																<td>${k.xqmc} / ${k.xn}</td>
																<td>${k.zd4 }</td>
																<td>${k.zd2 }</td>
																<td>${k.zd1 }</td>
																<td>${k.zd3 }</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
													<logic:empty name="cwsjList">
														<tr>
															<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
														</tr>
													</logic:empty>
												</logic:present>
										</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>


<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bdzc/10511/js/bdzc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/xsxx_xqbdzcgl" method="post" styleId="xqbdzcForm" style="width:99.9%">
			<html:hidden property="xn" />
			<html:hidden property="xq" />
		
			<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="�ر�" onclick="Close()" type="button"
										id="buttonClose">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>	
			<div class='tab'>
				<table style="margin-bottom: 5px" width="100%" border="0" class="formlist">				
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
								<span class="red"></span>ѧ��
							</th>
							<td>
								<a href="javascript:void(0);" class="name" 
									   onclick="showDialog('ѧ����ϸ��Ϣ',700,500,'stu_info_details.do?xh=<bean:write name="jbxx" property="xh"/>')"
									   style="margin-left: 1px;"
									 ><bean:write name="jbxx" property="xh"/>
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
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
						<logic:equal name="zczt" value="0">
						<tr>
							<th>δע�����</th>
							<td>${xqbdxx.wbdlbmc }</td>
							<th>Ԥ�Ʊ���ʱ��</th>
							<td>${xqbdxx.yjbdsj }</td>
						</tr>
						<tr>
							<th>δ<bean:message key="lable.bdzc"/>ԭ��</th>
							<td colspan="3">${xqbdxx.wbdyy }</td>
						</tr>
						</logic:equal>				
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
																<td>${k.zd3 }</td>
																<td>${k.zd1 }</td>
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


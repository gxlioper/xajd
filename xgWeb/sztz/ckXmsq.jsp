<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<input type="hidden" name="id" value="${rs.id }" />
			<input type="hidden" name="xmid" value="${rs.id }" />
			<input type="hidden" name="shlcid" value="${rs.shlcid }" />
			<input type="hidden" name="shzt" value="${rs.shzt}" />
			<input type="hidden" id="xh"  name="xh" value="${xh}" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr >
													<td colspan="2">
														��Ŀ���ƣ� ${rs.kmmc }
													</td>
													<td>
														�����������ƣ� ${rs.hxnlmc }
													</td>
													<td>
														��Ŀ���ͣ� ${rs.xmlxmc }
													</td>
												</tr>
												<tr >
													<td colspan="2">
														�����ɫ��
														<logic:equal value="��֯" name="rs" property="cyjs">
															<input type="radio" name="cyjs" value="����"  disabled="disabled"/>
															����
															<input type="radio" name="cyjs" value="��֯" disabled="disabled"
																checked="true" />
															��֯
														</logic:equal>
														<logic:notEqual value="��֯" name="rs" property="cyjs">
															<input type="radio" name="cyjs" value="����" disabled="disabled"
																checked="true" />
															����
															<input type="radio" name="cyjs" value="��֯"  disabled="disabled"/>
															��֯
														</logic:notEqual>
													</td>
													<td>
														�Ƿ����ޣ�

														<logic:equal value="��" name="rs" property="sfcx">
															<input type="radio" name="sfcx" value="��" disabled="disabled"
																checked="true" />
															��
															<input type="radio" name="sfcx}" value="��" disabled="disabled"/>
															��
														</logic:equal>

														<logic:notEqual value="��" name="rs" property="sfcx">
															<input type="radio" name="sfcx" value="��" disabled="disabled"/>
															��
															<input type="radio" name="sfcx" value="��" disabled="disabled"
																checked="true" />
															��
														</logic:notEqual>
													</td>
													<td>
														�������ޣ�${rs.rssx }(��)
													</td>
													
												</tr>
												<tr >
													<td align="right" width="10%">
														�ɹ�����
													</td>
													<td align="left" colspan="3" style="word-break:break-all;width:100%">
														${rs.cgms }
													</td>
												</tr>
												<tr >
													<td align="right">
														��ע
													</td>
													<td align="left" colspan="3" style="word-break:break-all;width:100%">
														${rs.bz }
													</td>
												</tr>
					</tbody>
				</table>
			</div>
			
		</html:form>
	</body>
</html>

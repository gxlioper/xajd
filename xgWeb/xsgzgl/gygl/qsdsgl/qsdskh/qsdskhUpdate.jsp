<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/qsdsgl/qsdskh/js/qsdskh.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_qsdskh" method="post" styleId="sqdswhForm">
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ҵ�ʦ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">
								ְ����
							</th>
							<td width="32%">
								${qsdskh.zgh }
								<input type="hidden" name="zgh" value="${qsdskh.zgh }" />
							</td>
							<th width="18%">
								��ʦ����
							</th>
							<td width="32%" id="dsxm">
								${qsdskh.dsxm }
							</td>
						</tr>
						
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td id="dslxdh">
								${qsdskh.lxdh }
							</td>
							<th>
								��λ
							</th>
							<td id="dsbmmc">
								${qsdskh.dw }
							</td>
						</tr>
						<tr>
							<th>
								���
							</th>
							<td>
								${qsdskh.nd }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${qsdskh.xn }
								<input type="hidden" name="xn" value="${qsdskh.xn }" />
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td>
								${qsdskh.xqmc }
								<input type="hidden" name="xq" value="${qsdskh.xq }" />
							</td>
							<th><span class="red">*</span>���˳ɼ�</th>
							<td>
								<html:text property="cj" styleId="cj" style="width:122px;" maxlength="10"  styleClass="text_nor" value="${qsdskh.cj }"></html:text>
							</td>
					    </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="save_button" type="button"
										onclick="updateQsdsxx();">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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


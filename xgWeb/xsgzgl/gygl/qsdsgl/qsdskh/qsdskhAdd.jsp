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
								<span class="red">*</span>ְ����
							</th>
							<td width="32%">
								<html:text property="zgh" styleId="zgh" styleClass="text_nor" readonly="true" style="width:110px;" value=""/>
								<button onclick="showDialog('��ʦѡ��',680,480,'szdw_fdyjtff.do?method=showFdysQsdsNotF5');return false;" class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<th width="18%">
								��ʦ����
							</th>
							<td width="32%" id="dsxm">
							</td>
						</tr>
						
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td id="dslxdh">
							</td>
							<th>
								��λ
							</th>
							<td id="dsbmmc">
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>���
							</th>
							<td>
								<html:select property="nd" styleId="nd" style="width:130px">
									<html:options collection="xnList" labelProperty="nd"
										property="nd" />
								</html:select>
							</td>
							<th>
								<span class="red">*</span>ѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn"
										property="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
									<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>���˳ɼ�</th>
							<td>
								<html:text property="cj" styleId="cj" style="width:122px;" maxlength="10"  styleClass="text_nor"></html:text>
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
										onclick="addQsdsxx();">
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


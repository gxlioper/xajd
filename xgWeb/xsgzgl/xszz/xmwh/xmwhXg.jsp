<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhXg.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
	    <input type="hidden" name="xmmcxgq" id="xmmcxgq" value='${xmmcxgq}'/>
		<html:form action="/xszz_xmwh" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm" />
			<div class="prompt">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						����Ƿ�ɵ���Ϊ���ǡ�ʱ��ѧ������д��������ʦ��˿��޸ģ��磺��������ʱ������ѧ�Ѽ�����Ŀ<br />
							Ϊ����ʱ��ѧ��������д����ʦ��˲����޸ģ��磺���ҽ�ѧ����Ŀ
					</p>
					<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style='tab;width:100%;height:229px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								��������
							</th>
							<td>
								${sqzq }
							</td>
						</tr>
						<tr>
							<th width="30%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td width="70%" >
								<html:text property="xmmc" styleId="xmmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="30%">
								��ĿӢ������
							</th>
							<td width="70%" >
								<html:text property="ywmc" styleId="ywmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ���
							</th>
							<td>
								<html:select property="lbdm" styleId="lbdm" style="width:155px">
								<html:options collection="xmlbList" property="lbdm"
									labelProperty="lbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								����Ƿ�ɵ���
							</th>
							<td >
								<html:radio property="jesfxssq" value="1">��</html:radio>
								<html:radio property="jesfxssq" value="0">��</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font><font id="jemc">�̶����</font>
							</th>
							<td>
								<html:text property="je" styleId="je" maxlength="10" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="20%">��Ŀ˵��<br/>
								<font color="red">(����¼��500��)</font></th>
							<td width="80%">
								<html:textarea property="xmsm" styleId="xmsm"  style="width:98%"  rows="5"/>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<th>--%>
<%--								��ӡ����--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<html:select property="dybb" styleId="dybb" style="width:200px">--%>
<%--									<option></option>--%>
<%--									<html:options collection="bbList" labelProperty="bbmc" property="bbdm"/>--%>
<%--								</html:select>								--%>
<%--															<!-- 						--%>
<%--								<a href="javascript::">����Ԥ��</a>--%>
<%--								 -->	--%>
<%--							</td>--%>
<%--						</tr>--%>
					</tbody>
				</table>
				</div>
				<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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


<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript">
	function selectCj() {
		var text = document.getElementById('cj').style;
		if (text.display == ''){
			text.display = 'none';
		} else {
			text.display = '';
		}
	}
</script>
<body>
<input type="hidden" name="save_nd" value="��">
<input type="hidden" name="save_xq" value="��"/>

		<table class="formlist" width="100%">
		<thead>
				<tr><th colspan="4"><span>������Ϣ</span></th></tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="24%">
					Υ�����
				</th>
				<td align="left"  colspan="3">
						<logic:empty name="cfxx">
							��ѧ��û��Υ�ͼ�¼��
						</logic:empty>
					<logic:present name="cfxx">
						<logic:notEmpty name="cfxx">
						<table class="tbstyle" width="85%">
							<thead>
							<tr>
								<th>ѧ��</th>
								<th>ѧ��</th>
								<th>�������</th>
								<th>����ԭ��</th>
								<th>����ʱ��</th>
								<th>�����ĺ�</th>
							</tr>
							</thead>
							<logic:iterate id="s" name="cfxx">
								<tr>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:notEmpty>
					</logic:present>
				</td>
			</tr>	
			<tr>
				<td bgcolor="#CCCCCC" colspan="4" align="center" onclick="selectCj();">
					<div style="color:blue;cursor:hand"><b>���γɼ�</b></div>
				</td>
			</tr>
			<tr id="cj" style="display :none">
				<th align="right" width="24%">
					���γɼ�
				</th>
				<td colspan="3" align="left" >
					<logic:empty name="cjxx">
						û�м�¼!
					</logic:empty>
					<logic:present name="cjxx">
						<logic:notEmpty name="cjxx">
						<table class="tbstyle" width="85%"  >
							<thead>
							<tr>
								<th>ѧ��</th>
								<th>ѧ��</th>
								<th>�γ�����</th>
								<th>�γ�����</th>
								<th>�ɼ�</th>
								<th>�����ɼ�</th>
							</tr>
							</thead>
							<logic:iterate id="s" name="cjxx">
								<tr>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:notEmpty>
					</logic:present>
				</td>
			</tr>
			<tr>
				<th align="right" width="24%">
					��������
					<br/>
					<font color="red">(������500������)</font>
				</th>
				<td colspan="3" width="76%">
					<html:textarea property="save_sqly" rows="8" style="width:90%;word-break:break-all;" value="${rs.sqly }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
			<tr guizhdx_jxjsq.jsp>
				<th align="right"  width="24%">
					���п���
				</th>
				<td width="26%">
					<html:text property="save_yhkh" maxlength="21" value="${rs.yhkh}" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
				</td>
				<th align="right"  width="24%">
					�Ƿ�Ƿѧ��
				</th>
				<td width="26%">
					<html:select property="save_sfqf" value="${rs.sfqf }">
						<html:option value=""></html:option>
						<html:options collection="isNot" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			</tbody>
		</table>

</body>
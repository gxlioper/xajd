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
<div class="tab">
		<table class="formlist" width="100%">
			<thead>
					<tr>
						<th colspan="4">
						<span>������Ϣ
						</span>
						</th>
					</tr>
				</thead>
				<tbody>
			<tr >
				<th align="right"  style="width: 16%">��ͥ����</th>
				<td  style="width: 34%">
					<html:text property="save_jthk" maxlength="100" value="${rs.jthk }${rs.hkszd }"></html:text>
				</td>
				<th align="right"  style="width: 16%">��ͥ�˿�����</th>
				<td  style="width:34%">
					<html:text property="save_rkzs" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" value="${rs.rkzs }"></html:text>
				</td>
			</tr>
			<tr >
				<th align="right">��ͥ��������</th>
				<td>
					<html:text property="save_jtyzsr"  maxlength="10" value="${rs.jtyzsr }${rs.jtzsr }" onblur="checkMoney(this)"></html:text>
				</td>
				<th align="right">�˾�����</th>
				<td>
					<html:text property="save_rjsr"  maxlength="10" value="${rs.rjsr }${rs.jtrjsr }" onblur="checkMoney(this)"></html:text>
				</td>
			</tr>
			<tr >
				<th align="right">������Դ</th>
				<td>
					<html:text property="save_srly"  maxlength="10" value="${rs.srly }"></html:text>
				</td>
				<th align="right">��������</th>
				<td>
					<html:text property="save_yzbm" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="7" value="${rs.yzbm }${rs.yb }"></html:text>
				</td>
				
			</tr>
			<tr>
				<th align="right">��ͥ��ϸסַ</th>
				<td colspan="3">
					<html:text property="save_jtxxdz"  maxlength="100" value="${rs.jtxxdz }${rs.jtszd }" style="width:90%"></html:text>
				</td>
			</tr>
			
			<tr>
				<th bgcolor="#CCCCCC" colspan="4" align="center" onclick="selectCj();">
					<div style="color:blue;cursor:hand" align="center"><b>���γɼ�</b></div>
				</th>
			</tr>
			<tr id="cj" style="display :none">
				<th align="right">
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
			<tr >
				<th align="right">
					������ֽ���<br/><font color="red"><��500��></font>
				</th>
				<td colspan="3">
					<html:textarea property="save_hjqk" rows="5" style="width:85%" value="${rs.hjqk }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
			<tr >
				<th >
					��������<br/><font color="red"><��500��></font>
				</th>
				<td colspan="3">
					<html:textarea property="save_sqly" rows="8" style="width:85%" value="${rs.sqly }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
			</tbody>
		</table>
</div>
</body>
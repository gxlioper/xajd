<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
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
	<fieldset>
		<legend>
			����
		</legend>

		<table class="tbstyle" width="100%" id="szynltz" style="display:none">
			<tr height="40px">
				<td align="right" width="16%">
					�������
				</td>
				<td colspan="3">
					<logic:present property="hjdj" name="rs">
						<input type="checkbox" value="1" onclick="display()"
							checked="checked" id="lb1">����&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="hjdj" name="rs">
						<input type="checkbox" value="1" onclick="display()" id="lb1">����&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>

					<logic:present property="kwmckh" name="rs">
						<input type="checkbox" value="2" onclick="display()"
							checked="checked" id="lb2")>����/����&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="kwmckh" name="rs">
						<input type="checkbox" value="2" onclick="display()" id="lb2">����/����&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>

					<logic:present property="cg" name="rs">
						<input type="checkbox" value="3" onclick="display()"
							checked="checked" id="lb3">ר��������&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="cg" name="rs">
						<input type="checkbox" value="3" onclick="display()" id="lb3">ר��������&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>

					<logic:present property="zysj" name="rs">
						<input type="checkbox" value="4" onclick="display()"
							checked="checked" id="lb4">����&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="zysj" name="rs">
						<input type="checkbox" value="4" onclick="display()" id="lb4">����&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>



				</td>
			</tr>
			<tr height="40px" id="1" style="display:none">
				<td align="right">
					�񽱵ȼ���
				</td>
				<td width="34%">
					<html:text property="save_hjdj" maxlength="20" value="${rs.hjdj }" styleId="hjdj"></html:text>
				</td>
				<td width="16%"></td>
				<td></td>
			</tr>
			<tr height="40px" id="2" style="display:none">
				<td align="right">
					�������쵥λ��
				</td>
				<td>
					<html:text property="save_zbdw" maxlength="50" value="${rs.zbdw }" styleId="zbdw"></html:text>
				</td>
				<td align="right">
					֤��䷢��λ��
				</td>
				<td>
					<html:text property="save_bfdw" maxlength="50" value="${rs.bfdw }" styleId="bfdw"></html:text>
				</td>
			</tr>

			<tr height="40px" id="3" style="display:none">
				<td align="right">
					�������Ƽ����ţ�
				</td>
				<td>
					<html:text property="save_kwmckh" maxlength="50" styleId="kwmckh"
						value="${rs.kwmckh }"></html:text>
				</td>
				<td></td>
				<td></td>

			</tr>
			<tr height="40px" id="4" style="display:none">
				<td align="right">
					����������
				</td>
				<td>
					<html:text property="save_zzpm" maxlength="10" styleId="zzpm"
						onkeyup="value=value.replace(/[^\d]/g,'')" value="${rs.zzpm }"></html:text>
				</td>
				<td align="right">
					����ʱ�䣺
				</td>
				<td>
					<html:text property="save_fbsj" maxlength="10" styleId="fbsj"
						onclick="showCalendar(this.id,'y-mm-dd');"
						onblur='dateFormatChg(this)' readonly="true" value="${rs.fbsj }"></html:text>
				</td>
			</tr>

			<tr height="40px" id="5" style="display:none">
				<td align="right">
					�ɹ���
				</td>
				<td>
					<html:text property="save_cg" maxlength="50" value="${rs.cg }" styleId="cg"></html:text>
				</td>
				<td align="right">
					�ɹ�����
				</td>
				<td>
					<html:text property="save_cgjb" maxlength="20" value="${rs.cgjb }" styleId="cgjb"></html:text>
				</td>
			</tr>
			<tr height="40px" id="6">
				<td align="right">
					<font color="red"><��500��>
					</font>��Ҫ�¼���
				</td>
				<td colspan="3">
					<html:textarea property="save_zysj" style="width:90%" rows="5"
						styleId="zysj"
						value="${rs.zysj }"
						onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
		</table>

		<table class="tbstyle" width="100%" id="nbcsjxj">
			<tr height="40px">
				<td align="right" width="16%">
					�༶������
				</td>
				<td width="34%">
					<html:text property="save_bjrs"  
						readonly="true"
						value="${rs.bjrs }${bjrs }"></html:text>
				</td>
				<td align="right" width="16%">
					ѧ���ֳɼ���
				</td>
				<td>
					<html:text property="save_xjfcj"  
						readonly="true"
						value="${rs.xjfcj }${xjf.xjfcj }"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">
					ѧ�������Σ�
				</td>
				<td>
					<html:text property="save_xjfpm" 
						readonly="true"
						value="${rs.xjfpm }${xjf.xjfpm }"></html:text>
				</td>
				<td align="right">
					ѧ��Ʒ�����۵ȼ�:
				</td>
				<td>
					<html:text property="save_pxdj" 
							   value="${rs.pxdj }${pxdj }" 
							   readonly="true"
							   styleId="pxdj"
							   maxlength="10"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">
					��ѧ�����ʽ�����׼���ȼ�:
				</td>
				<td colspan="3">
					<html:text property="save_jkdj" value="${rs.jkdj }" maxlength="10"></html:text>
				</td>
			</tr>
		</table>
		
		
		<table class="tbstyle" width="100%" id="gjlzjxj">
			<tr height="40px">
				<td align="right" width="16%">��ͥ����:</td>
				<td width="34%">
					<logic:notPresent name="rs">
						<html:radio property="save_jthk" value="����">����</html:radio>
						<html:radio property="save_jthk" value="ũ��">ũ��</html:radio>
					</logic:notPresent>
					<logic:present name="rs">
						<html:radio property="save_jthk" value="����" name="rs">����</html:radio>
						<html:radio property="save_jthk" value="ũ��" name="rs">ũ��</html:radio>
					</logic:present>
					
				</td>
				<td align="right" width="16%">��ͥ�˿�����:</td>
				<td width="34%">
					<html:text property="save_rkzs" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" value="${rs.rkzs }"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">��ͥ��������:</td>
				<td>
					<html:text property="save_jtyzsr"  maxlength="10" value="${rs.jtyzsr }${rs.jtzsr }" onblur="checkMoney(this)"></html:text>
				</td>
				<td align="right">�˾�����:</td>
				<td>
					<html:text property="save_rjsr"  maxlength="10" value="${rs.rjsr }${rs.jtrjsr }" onblur="checkMoney(this)"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">������Դ:</td>
				<td>
					<html:text property="save_srly"  maxlength="10" value="${rs.srly }"></html:text>
				</td>
				<td align="right">��������:</td>
				<td>
					<html:text property="save_yzbm" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="7" value="${rs.yzbm }${rs.yb }"></html:text>
				</td>
				
			</tr>
			<tr height="40px">
				<td align="right">��ͥ��ϸסַ:</td>
				<td colspan="3">
					<html:text property="save_jtxxdz"  maxlength="100" value="${rs.jtxxdz }${rs.jtszd }" style="width:90%"></html:text>
				</td>
			</tr>
			
			<tr>
				<td bgcolor="#CCCCCC" colspan="4" align="center" onclick="selectCj();">
					<div style="color:blue;cursor:hand"><b>���γɼ�</b></div>
				</td>
			</tr>
			<tr id="cj" style="display :none">
				<td align="right">
					���γɼ���
				</td>
				<td colspan="3" align="left" >
					<logic:empty name="cjxx">
						û�м�¼!
					</logic:empty>
					<logic:present name="cjxx">
						<logic:notEmpty name="cjxx">
						<table class="tbstyle" width="85%"  >
							<thead>
							<tr>
								<td>ѧ��</td>
								<td>ѧ��</td>
								<td>�γ�����</td>
								<td>�γ�����</td>
								<td>�ɼ�</td>
								<td>�����ɼ�</td>
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
			<tr height="40px">
				<td align="right">
					<font color="red"><��500��></font>������ֽ���:
				</td>
				<td colspan="3">
					<html:textarea property="save_hjqk" rows="5" style="width:85%" value="${rs.hjqk }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
		</table>
		<table class="tbstyle" width="100%" >
			<tr >
				<td align="right" width="16%">
					<font color="red"><��500��></font>�������ɣ�
				</td>
				<td>
					<html:textarea property="save_sqly" rows="8" style="width:85%" value="${rs.sqly }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
		</table>
	</fieldset>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyscjzJs.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
<script type="text/javascript">
<!--
	function save() {
		
		if (document.getElementById('ys').value==document.getElementById('yesNo').value&&document.getElementById('cj').value==document.getElementById('cxcj').value&&document.getElementById('yj').value==document.getElementById('shyj').value) {alert('δ���κ��޸ģ�');return;} refreshForm('pjpy_scjz_jxjsh.do');
	}
//-->
</script>
<body>
	<html:form action="/pjpyscjzwh" method="post">
	<input type="hidden" name="act" value="save"/>
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��� - ��ѧ�����
			</div>
		</div>
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<table class="tbstyle" width="100%"> 
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="25%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="25%">
					${rs.xh }
				</td>
				<td align="right" width="25%">
					ѧ�꣺
				</td>
				<td align="left" width="25%">
					${xn }
					<input type="hidden" name="xn" id="xn" value="${xn }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					ѧ�ڣ�
				</td>
				<td align="left">
					${xqmc }
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					${rs.jxjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					��ѧ�����
				</td>
				<td align="left">
					${rs.jxjlb }
					<input type="hidden" name="jlje" value="${rs.jlje }"/>
					<input type="hidden" name="jxjdm" value="${rs.jxjdm }"/>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					ϵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					������
				</td>
				<td align="left">
					${rs.jlje }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					����ְ��
				</td>
				<td align="left">
					${rs.drzw }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					�ֻ����룺
				</td>
				<td align="left">
					${rs.sjhm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					����ˮƽ��
				</td>
				<td align="left">
					${rs.wysp }
				</td>
				<td align="right">
					�����ˮƽ��
				</td>
				<td align="left">
					${rs.jsjsp }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���п�������
				</td>
				<td align="left">
					${rs.khss }
				</td>
				<td align="right">
					���ţ�
				</td>
				<td align="left">
					${rs.kh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������ͳɼ���
				</td>
				<td align="left">
					${rss.zdcj }
					
				</td>
				<td align="right">
					ƽ���ɼ���
				</td>
				<td align="left">
					${rss.pjcj }
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ѧϰ�ɼ�����
				</td>
				<td align="left" colspan="3">
					�༶��&nbsp;${rss.bjpm }&nbsp;��,רҵ��&nbsp;${rss.zypm }&nbsp;��
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���޴��֣�
				</td>
				<td align="left" colspan="3">
					<logic:empty name="cfList">
								<p align="">
									��
								</p>
							</logic:empty>
							<logic:notEmpty name="cfList">
								<logic:iterate name="cfList" id="s">
									<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>��&nbsp;&nbsp;
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>&nbsp;&nbsp;
									(<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>)
									<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate><br/>
								</logic:iterate>
							</logic:notEmpty>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					У���������
				</td>
				<td align="left" colspan="">
				&nbsp;
				</td>
				<td align="right">
					����ͨ�����
				</td>
				<td align="left" colspan="">
				&nbsp;	
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�����ɼ���
				</td>
				<td align="left" colspan="">
					
					<logic:equal value="true" name="isFdy">
										<html:select property="cxcj" styleId="cxcj" >
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
						<html:option value="����">����</html:option>
						<html:option value="������">������</html:option>
					</html:select>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy">
					${rs.cxcj }
					<input type="hidden" id="cxcj" value="${rs.cxcj }"/>
					</logic:notEqual>
				</td>
				<td align="right">
					<logic:equal value="xy" name="userType"><logic:equal value="true" name="isFdy">����Ա</logic:equal><logic:notEqual value="true" name="isFdy">ϵ</logic:notEqual> </logic:equal><logic:notEqual value="xy" name="userType">Ժ��У��</logic:notEqual>��ˣ�
				</td>
				<td align="left" colspan="">
					<html:select property="yesNo" styleId="yesNo">
						<html:option value=""></html:option>
						<html:option value="ͨ��"></html:option>
						<html:option value="��ͨ��"></html:option>
						<html:option value="δ���"></html:option>
					</html:select>
				</td>
				<input type="hidden" name="ys" id="ys" value="${ys }"/>
				<input type="hidden" name="yj" id="yj" value="${yj }"/>
				<input type="hidden" name="cj" id="cj" value="${cj }"/>
			</tr>
			<tr>
				<td align="right">
					<logic:equal value="xy" name="userType"><logic:equal value="true" name="isFdy">����Ա</logic:equal><logic:notEqual value="true" name="isFdy">ϵ</logic:notEqual> </logic:equal><logic:notEqual value="xy" name="userType">Ժ��У��</logic:notEqual>��������
				</td>
				<td align="left" colspan="3"><html:textarea property="shyj" styleId="shyj" rows="3" style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="save();return false;"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert(''+document.getElementById('failinfo').value);
					
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>

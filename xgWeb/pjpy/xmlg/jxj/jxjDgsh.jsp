<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<!-- DWR js -->
<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
<script type='text/javascript' src='/xgxt/pjpy/xmlg/pjpyxmlg.js'></script>
<script type="text/javascript">
<!--
	function savedata() {
		var sh = document.getElementById('sh').value;
		var userType = document.getElementById('userType').value;
		if (checkTextAreaLength('yj','������',1000)) 
		{
			var xn = document.getElementById('xn').value;
			var pk = document.getElementById('pkValue').value;
			
			//<bean:message key="lable.xsgzyxpzxy" />���Ҫ���
			if (userType=='xy'&&sh=='ͨ��') {
				document.getElementById('btn_save').disabled = true;
				//�ȼ��һ���Ƿ�����
				getStuDtiaInfo.checkJxjshrs(xn,pk,'jxj',function (data) {
					if (data==true) {
						saveinfo('pjpy_xmlg_jxjDgsh.do?operType=save','');
					} else {
						alert("���ʧ�ܣ�\n��ǰ���ͨ�������ѳ���ѧ���������������õ�����!");
						document.getElementById('btn_save').disabled = false;
						return false;
					}
				});				
			} else {//ѧУ��˲��ü��
				saveinfo('pjpy_xmlg_jxjDgsh.do?operType=save','');
			}
			
		}
	}
//-->
</script>
<body>
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pkValue" id="pkValue"
			value="${pkValue }" />
			<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pt" id="pt"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��� - ��ѧ�����
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="200px">
					ѧ�ţ�
				</td>
				<td align="left" width="200px">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right" width="200px">
					ѧ�꣺
				</td>
				<td align="left" width="200px">
					${rs.xn }
					<input type="hidden" name="xn" id="xn" value="${rs.xn }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					${rs.nd }
					<input type="hidden" name="nd" id="nd" value="${rs.nd }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					��ѧ�����
				</td>
				<td align="left">
					${rs.lbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					��ѧ��
				</td>
				<td align="left">
					${rs.jxjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					������
				</td>
				<td align="left">
					${rs.je }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					����ˮƽ��
				</td>
				<td align="left">
					${rs.wysp }
				</td>

			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					${rs.bjmc }
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
					�����ˮƽ��
				</td>
				<td align="left">
					${rs.jsjsp }
				</td>
				<td align="right">
					��ˣ�
				</td>
				<td align="left">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>

			<!-- ����ط���ʾ�ɼ����۲⣬������Ϣ -->
			<!-- �ۺϲ����ɼ� -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }ѧ���ۺϲ���������Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2">
						<table width="100%" border="1" align="center" class="tbstyle">
							<thead>
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									�������ַ�
								</td>
								<td align="center">
									�������ַ�
								</td>
								<td align="center">
									������ַ�
								</td>
								<td align="center">
									ѧ������
								</td>
								<td align="center">
									ѧ������
								</td>
							</tr>
							</thead>
							<logic:notEmpty name="zhcpList">
								<logic:iterate id="zc" name="zhcpList">
									<tr style="cursor:hand;"	align="center" >
										<logic:iterate id="v" name="zc" >
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="zhcpList">
								<tr><td colspan="6" align="center">���޼�¼</td></tr>
							</logic:empty>						
						</table>

					</div>
				</td>
			</tr>
			<!-- ѧ���ɼ���Ϣ -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
									onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getStucjList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }ѧ��γ̳ɼ���Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child3" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									�γ�����
								</td>
								<td align="center">
									�γ�
								</td>
								<td align="center">
									�ɼ�
								</td>
							</tr>
							</thead>
							<!-- ������ͨ��DWR���е��õ� -->
							<tbody width="100%" class="tbstyle" id="cjxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			<!-- ������Ϣ -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }ѧ��Υ�ʹ�����Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									�������
								</td>
								<td align="center">
									����ԭ��
								</td>
								<td align="center">
									����ʱ��
								</td>
							</tr>
							</thead>
							<!-- ������ͨ��DWR���е��õ� -->
							<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>	
				
			<tr>
				<td align="right">
					���������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="jlqk" styleId="jlqk" rows="4"
						style="width:650px" disabled="true" name="rs"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					�������ɣ�
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly" rows="4"
						style="width:650px" disabled="true" name="rs"></html:textarea>
				</td>
			</tr>
			<logic:notEqual value="xy" name="userType">
				<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyshyj" styleId="xyshyj" rows="3"
						style="width:650px" disabled="true"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<td align="right">
					��������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" rows="3"
						style="width:650px" ></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:notEqual value="no" name="writable">
			<button type="button" class="button2" id="btn_save"
				onclick="savedata()"
				style="width:80px">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>
			<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
				id="buttonClose">
				�� ��
			</button>
		</div>
		<!-- �������ʾҳ�� -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<body>
	<html:form action="/pjpyshcbyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!--���ʧ����Ϣ��ʾ -->
	<input type="hidden" name="xxsh" id="xxsh" value="<bean:write name="rs"  property="xxsh"/>"/>
	<input type="hidden" name="xysh" id="xysh" value="<bean:write name="rs"  property="xysh"/>"/>
	<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>	
	<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyshcbys" key="pjpy_shcbys_jxjsq" />
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��ѧ�����
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
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
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
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
					ѧ�ڣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
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
					��ѧ�ڼ���:
				</td>
				<td align="left">
					<bean:write name="rs" property="jd"/>
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
					����רҵ������
				</td>
				<td align="left">
					<bean:write name="rs" property="mc1"/>
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
					ְ��
				</td>
				<td align="left">
					<bean:write name="rs" property="drzw"/>
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
					��ѧ��:
				</td>
				<td align="left">
					<bean:write name="rs" property="jxjmc"/>
				</td>
				<input type="hidden" id="jxjdm" name="jxjdm" value="${rs.jxjdm }"/>
			</tr>
			<tr>
			<td></td><td></td>
				<td align="right">
					
					<logic:equal value="xy" name="userType">
						<logic:equal value="true" name="isFdy">����Ա</logic:equal>
						<logic:notEqual value="true" name="isFdy"><bean:message key="lable.xsgzyxpzxy" /></logic:notEqual>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">ѧУ</logic:notEqual>���:
				</td>
				<td align="left">
					<html:select property="yesNo" styleId="yesNo">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="width:22px">
					<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>���Ƴɼ�</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2" style="display:none">
						<table width="90%" border="1" align="center" class="tbstyle">
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									�γ�����
								</td>
								<td align="center">
									�ɼ�
								</td>
							</tr>
							<logic:notEmpty name="rss">
								<logic:iterate name="rss" id="s">
									<tr style="cursor:hand;">
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="rss">
								<tr>
									<td align="center" colspan="4">
										δ�ҵ��κμ�¼��
									</td>
								</tr>
							</logic:empty>
						</table>
					</div>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������������:
				</td>
				<td align="left" colspan="3" >
					<html:textarea name="rs" property="sqly" readonly="true" rows="5" styleId="sqly" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
			<logic:equal value="xy" name="userType">
				<logic:equal value="true" name="isFdy">
					<tr style="height:23px">
						<td align="right">
							����Ա���:
						</td>
						<td align="left" colspan="3" >
							<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
						</td>
						</tr>
				</logic:equal>
				<logic:notEqual value="true" name="isFdy">
					<tr style="height:23px">
						<td align="right">
							<bean:message key="lable.xb" />���:
						</td>
						<td align="left" colspan="3" >
							<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
						</td>
					</tr>
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<tr style="height:23px">
				<td align="right">
					ѧ�������:
				</td>
				<td align="left" colspan="3" >
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="savejxjinfo()"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						�ر�
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert(''+document.getElementById('failInfo').value);
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			
		</logic:present>
		<script>
			function savejxjinfo() {
				var userType = document.getElementById('userType').value;
				var xxsh = document.getElementById('xxsh').value;
				var xysh = document.getElementById('xysh').value;
				var isFdy = document.getElementById('isFdy').value;
				if (userType == 'xy') {
					if (isFdy=='true') {
						if (xysh=='ͨ��' || xxsh=='ͨ��') {
							alert('����ز��������ͨ��,�����ٲ���!');
							return;
						} else {
							BatAlert.showTips('���ڲ�������ȴ�...');
							saveinfo('pjpy_shcbys_jxjshoneres.do','');
						}
					} else {
						if (xxsh=='ͨ��') {
							alert('����ز��������ͨ��,�����ٲ���!');
							return;
						} else {
							BatAlert.showTips('���ڲ�������ȴ�...');
							saveinfo('pjpy_shcbys_jxjshoneres.do','');
						}
					}
				} else {
					BatAlert.showTips('���ڲ�������ȴ�...');
					saveinfo('pjpy_shcbys_jxjshoneres.do','');
				}
			}
		</script>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript">
<!--
//-->
</script>
<body>
	<html:form action="/pjpyshcbyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_shcb_jxjsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyshcbys" key="pjpy_shcbys_jxjsq" />
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��д�����
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xh"/>
					<input name="xh" id="xh" value="<bean:write name="rs" property="xh"/>" type="hidden"/>
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
					�������£�
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
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
					���壺
				</td>
				<td align="left">
					<bean:write name="rs" property="mzmc" />
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
					��Դ�أ�
				</td>
				<td align="left">
					<bean:write name="rs" property="syd" />
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
					������ò��
				</td>
				<td align="left">
					<bean:write name="rs" property="zzmmmc" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ְ��
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw" name="rs"></html:text>
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ�ڼ��㣺
				</td>
				<td align="left">
					<html:text property="jd" styleId="jd" name="rs"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left" colspan="">
					<html:select property="jxjdm" styleId="jxjdm" style="width:200px" name="rs">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
					<input type="hidden" name="jxjdmO" value="<bean:write name="rs" property="jxjdm"/>"/>
					
				</td>
				<td align="right">
					����רҵ����:
				</td>
				<td align="left" colspan="">
					<html:text property="mc1" styleId="mc1" name="rs" readonly="true"></html:text>
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
					<font color="red">*</font>�������룺
				</td>
				<td align="left" colspan="3" >
					<html:textarea property="sqly" rows="7" styleId="sqly" 
					style="width:95%" name="rs"></html:textarea>
				</td>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_shcbys_jxjsqxg_save.do','xh-jxjdm-jd-sqly');document.getElementById('btn_save').disabled=true;"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_print" onclick="" style="width:80px"
						id="buttonClose">
						�����ӡ
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="true" name="inserted">
				<script>
					alert('�����ɹ���');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="inserted">
				<script>
					alert('����ʧ�ܣ�');
					Close();
				</script>
			</logic:equal>	
			<logic:equal value="jdbhg" name="inserted">
				<script>
					alert('����ʧ��,��ѧ�ڸ����ɼ����㲻���ϴ˽�ѧ����������,��˶�!');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<body>
	<html:form action="/pjpyynyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_ynys_jxjsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_jxjsq" />
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
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
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
					</logic:notPresent>
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
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" onchange="if (chgXh()) {refreshForm('pjpy_ynys_jxjsq.do');}" styleId="jxjdm">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
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
					<bean:write name="rs" property="jxjlb" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="jlje" />
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
					<html:text property="drzw" styleId="drzw"></html:text>
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
					��ѧ������ѧ��
				</td>
				<td align="left">
					<html:select property="dnshjxj" styleId="dnshjxj">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					����ˮƽ��
				</td>
				<td align="left">
					<html:text property="wysp" styleId="wysp"></html:text>
				</td>
				<td align="right">
					�ֻ����룺
				</td>
				<td align="left">
					<html:text property="sjhm" styleId="sjhm"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="center" colspan="4">
					<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>��Ҫѧҵ�ɼ�</strong>
											</div>
										</div>
									</td>
								</tr>
					</table>
					<div id="child2" style="display:block;">
			<fieldset>
				<table class="tbstyle" width="90%" id="tTb">
					<thead style="height:23px">
					<tr>
						<td align="center">
							�γ�����
						</td>
						<td align="center">
							�ɼ�
						</td>
						<td align="center">
							רҵ��/�Ļ���
						</td>
						<td align="center">
							�Ƿ�Ϊ���޿�
						</td>
						</tr>
					</thead>
					<logic:empty name="cjList">
					<tr>
						<td colspan="4" align="center">
							���κγɼ���
						</td>
					</tr>
					</logic:empty>
					<logic:notEmpty name="cjList">
					<logic:iterate name="cjList" id="s">
					<tr>
						<logic:iterate id="v" name="s" >
							<td align=center>
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
					</logic:iterate>
					</logic:notEmpty>
					
				</table>
				<table class="tbstyle" width="90%">
					<tr>
						<td colspan="" align="center">
							רҵ��ƽ���ɼ�	
						</td>
						<td align="center">
							${cjpm.zykpjf }
						</td>
						<td colspan="" align="center">
							�Ļ���ƽ���ɼ�
						</td>
						<td align="center">
							${cjpm.whkpjf }
						</td>
					</tr>
					<tr>
						<td colspan="" align="center">
							�ۺ����ʲ����ɼ�	
						</td>
						<td align="center">
							${zhfpm.zf }
						</td >
						<td colspan="" align="center">
							�����ɼ������꼶
						</td>
						<td align="center">
							��&nbsp;${zhfpm.pm }&nbsp;��
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="center" colspan="4">
					<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>�������ĺ���Ʒ���</strong>
											</div>
										</div>
									</td>
								</tr>
					</table>
					<div id="child3" style="display:none">
				<fieldset>
				<legend>�γ� &nbsp;&nbsp;&nbsp;
						<font color="blue"><button type="button" class="button2" onclick="add('tBb')" style="height:18px;width:20px">+</button> 
							&nbsp;<button type="button" class="button2" onclick="decrease('tBb')" style="height:18px;width:20px">-</button></font>
				</legend>
				<table class="tbstyle" width="90%" id="tBb">
					<thead style="height:23px">
					<tr>
						<td align="center">
							������
						</td>
						<td align="center">
							���Ļ���Ʒ����
						</td>
						<td align="center">
							�񽱼���
						</td>
						<td align="center">
							�񽱵ȼ�
						</td>
						</tr>
					</thead>
					<tr style="display: none;"  id="a1">
						<td align="center">
							<html:text property="fbkw" styleId="fbkw1"></html:text>
						</td>
						<td align="center">
							<html:text property="lwhzpmc" styleId="lwhzpmc1"></html:text>
						</td>
						<td align="center">
							<html:text property="hjjb" styleId="hjjb1" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:text property="hjdj" styleId="hjdj1" style="width:100px"></html:text>
						</td>
					</tr>
					<tr style="display: none;"  id="a2">
						<td align="center">
							<html:text property="fbkw" styleId="fbkw2"></html:text>
						</td>
						<td align="center">
							<html:text property="lwhzpmc" styleId="lwhzpmc2"></html:text>
						</td>
						<td align="center">
							<html:text property="hjjb" styleId="hjjb2" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:text property="hjdj" styleId="hjdj2" style="width:100px"></html:text>
						</td>
					</tr>
					<tr style="display: none;"  id="a3">
						<td align="center">
							<html:text property="fbkw" styleId="fbkw3"></html:text>
						</td>
						<td align="center">
							<html:text property="lwhzpmc" styleId="lwhzpmc3"></html:text>
						</td>
						<td align="center">
							<html:text property="hjjb" styleId="hjjb3" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:text property="hjdj" styleId="hjdj3" style="width:100px"></html:text>
						</td>
					</tr>
					<tr style="display: none;"  id="a4">
						<td align="center">
							<html:text property="fbkw" styleId="fbkw4"></html:text>
						</td>
						<td align="center">
							<html:text property="lwhzpmc" styleId="lwhzpmc4"></html:text>
						</td>
						<td align="center">
							<html:text property="hjjb" styleId="hjjb4" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:text property="hjdj" styleId="hjdj4" style="width:100px"></html:text>
						</td>
					</tr>
					<tr style="display: none;"  id="a5">
						<td align="center">
							<html:text property="fbkw" styleId="fbkw5"></html:text>
						</td>
						<td align="center">
							<html:text property="lwhzpmc" styleId="lwhzpmc5"></html:text>
						</td>
						<td align="center">
							<html:text property="hjjb" styleId="hjjb5" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:text property="hjdj" styleId="hjdj5" style="width:100px"></html:text>
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
				</td>
			</tr>
			<tr>
				<td align="right">
					��Ҫ�¼�:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="zysj" styleId="zysj" rows="3" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					����<bean:message key="lable.xsgzyxpzxy" />������:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="ejyxyj" styleId="ejyxyj" rows="3" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />������:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyyj" styleId="xyyj" rows="3" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>		
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('ynys_jxjsqsave.do','xh-jxjdm');"
						style="width:80px">
						�� ��
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ�ܣ�');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>

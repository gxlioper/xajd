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
		var xh = document.getElementById('xh').value;
		var xn = document.getElementById('xn').value;
		var jxjdm = document.getElementById('jxjdm').value;
		if (xh==''||xn==''||jxjdm==''||xh==null||xn==null||jxjdm==null) {
			alert('��*���ֶα�����д!');
			return false;
		}
		if (checkTextAreaLength('jlqk-sqly','�������-��������',1000)) 
		{
			getStuDtiaInfo.checkStusqExists(xh,xn,function (data) {
			if (data[0]=="false") {
				alert("��������������:\n����"+xn+"ѧ���������һ�ν�ѧ�𣬲���������.");
				return false;
			} else if (data[1]=="false") {
				alert("��������������:\n����"+xn+"ѧ����޿γɼ��в�����.");
				return false;
			} else if (data[2]=="false") {
				alert("��������������:\n����"+xn+"ѧ���ܹ�����.");
				return false;
			} else {
				if ($("pt")) {
						BatAlert.showTips('���ڱ������ݣ���ȴ�...');
				}
				saveinfo('pjpy_xmlg_jxjsqDefault.do?operType=save','');	
			}
		});
		}
	}
//-->
</script>
<body>
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_xmlg_jxjsqDefault.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��ѧ������ - ��д�����
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
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
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
					ѧ�꣺
				</td>
				<td align="left">
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
					<html:select property="lbdm" styleId="lbdm" onchange="refreshForm('pjpy_xmlg_jxjsqDefault.do')">
						<html:option value=""></html:option>
						<html:options collection="jxjlbList" property="dm"
							labelProperty="mc" />
					</html:select>
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
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="dm"
							labelProperty="mc" />
					</html:select>
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
					
				</td>
				<td align="left">
					
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
					<html:text property="wysp" styleId="wysp" maxlength="30"></html:text>
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
					<html:text property="drzw" styleId="drzw" maxlength="100"
						style="width:200px"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				
				<td align="right">
					�༶������
				</td>
				<td align="left">
					${rs.bjrs }
				</td>
				<td align="right">
					�����ˮƽ��
				</td>
				<td align="left">
					<html:text property="jsjsp" styleId="jsjsp" maxlength="40"></html:text>
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
					<br/>
					<font color="red">(�����������1000������)</font>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="jlqk" styleId="jlqk" rows="5"
						style="width:770px"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					�������ɣ�
					<br/>
					<font color="red">(�����������1000������)</font>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly" rows="5"
						style="width:770px"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<button type="button" class="button2" id="btn_save"
				onclick="savedata()"
				style="width:80px">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="window.open('pjpy_xmlg_jxjprint.do?pkValue=' + document.getElementById('pkValue').value)" style="width:80px"
				id="buttonClose">
				�� ӡ
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

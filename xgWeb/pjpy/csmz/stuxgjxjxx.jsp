<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="chgjxj('jxjdmlb','')">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpycsmzwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - ��ѧ������ - ��д�����
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/prise_apply.do?tab=qtjxj" />
				<input type="hidden" name="tab1" id="tab1" value="qtjxj"/>
				<input type="hidden" name="oldjxjdm" id="oldjxjdm" value="${oldjxjdm }"/>
						<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" >
							��ȣ�
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" >
							ѧ�꣺
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" >
							<font color="red">*</font>��ѧ�����
						</td>
						<td align="left" colspan="4">
							<html:select property="jxjdmlb" styleId="jxjdmlb" onchange="chgjxj('jxjdmlb','refresh')" style="width:170px">
								<html:option value=""></html:option>
								<html:options collection="jxjdmList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" >
							<font color="red">*</font>��ѧ��
						</td>
						<td align="left" colspan="4">
							<html:select property="xmdm" styleId="jxjdm" style="width:170px"
								onchange="refreshForm('/xgxt/prise_apply.do?jxjlb1='+document.getElementById('jxjdmlb').value)">
								<option value=""></option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" >
							������
						</td>
						<td align="left" colspan="4">
							<input type="text" id="jlje" name="jlje" value="<bean:write name='rs' property='jlje' />" readonly="readonly"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" >
							����ְ��
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="drzw" styleId="a" />
						</td>
					</tr>
					<logic:notPresent name="showhzy">
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="bjmc" />
						</td>
						<logic:notPresent name="shjxj">
							<td align="right" >
							��ѧ������ѧ��
						</td>
						<td align="left" colspan="4">
							<html:select property="zdm">
								<option value=""></option>
								<html:options collection="jxjListN" property="jxjmc"
									labelProperty="jxjmc" />
							</html:select>
						</td>
						</logic:notPresent>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ˮƽ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="wysp" styleId="a"  style="width:98%"/>
						</td>
						<td align="right" >
							�ֻ����룺
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="sjhm" styleId="a" style="width:98%" />
						</td>
					</tr>
					</logic:notPresent>
					<logic:present name="showhzy">
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" >
							�ֻ����룺
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="sjhm" styleId="a" style="width:98%" />
						</td>
					</tr>
					</logic:present>
					<logic:equal name="showhzy" value="showhzy">
					<tr style="height:22px">
						<td align="right">
							�ɼ����Σ�
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="cjmc" styleId="a"  style="width:98%"/>
						</td>
						<td align="right" >
							�ۺ��������Σ�
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="zhpfmc" styleId="a" style="width:98%" />
						</td>
					</tr>
					</logic:equal>
					<logic:notEqual name="showhzy" value="showhzy">
  <tr>
    <td rowspan="2" height="35"></td>
    <td rowspan="2" align="center">��ѧ��</td>
    <td rowspan="2" align="center">����<br>��<br>�Ÿ�</td>
    <td rowspan="2" align="center" >�µȼ�</td>
    <td rowspan="2" align="center">���޿�<br>ƽ��<br>�ɼ�</td>
    <td colspan="2" align="center">ѧϰ�ɼ�����</td>
    <td rowspan="2" align="center">����<br>�ϸ�<br>��׼</td>
  </tr>
  <tr>
    <td width="6%" align="center" >�༶</td>
    <td width="6%" align="center">רҵ</td>
  </tr>
  <tr>
    <td align="center" height="35">��һѧ��</td>
    <td><html:text name='rs' property="jxj1" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg1" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj1" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj1" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx1" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx1" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz1" style="width:98%" /></td>

  </tr>
  <tr>
    <td align="center" height="35">�ڶ�ѧ��</td>
    <td><html:text name='rs' property="jxj2" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg2" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj2" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj2" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx2" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx2" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz2" style="width:98%" /></td>
  </tr>
  <tr>
    <td align="center" height="35">����ѧ��</td>
    <td><html:text name='rs' property="jxj3" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg3" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj3" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj3" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx3" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx3" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz3" style="width:98%" /></td>
  </tr>
  <tr>
    <td align="center" height="35">����ѧ��</td>
    <td><html:text name='rs' property="jxj4" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg4" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj4" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj4" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx4" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx4" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz4" style="width:98%" /></td>
  </tr>
  </logic:notEqual>
  <logic:notPresent name="showhzy">
					<tr align="left" style="height:22px">
						<td align="right">
							<logic:notPresent name="shjxj">ѧϰ������</logic:notPresent>
							<logic:present name="shjxj">���������飺</logic:present>
							<br/>
							<span class="style1">(������400����)</span>
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' />
						</td>
					</tr>
	</logic:notPresent>
					<tr align="left" style="height:22px">
						<logic:present name="showhzy">
							<td align="right">
								���������
								<br/>
							<span class="style1">(������400����)</span>
							</td>
							<td colspan="7">
								<html:textarea name='rs' property='jfqk' style="width:99%"
									rows='5' />
							</td>
						</logic:present>
						<logic:notPresent name="shjxj">
							<logic:notPresent name="showhzy">
							<td align="right">
								������Ŀ��
								<br/>
							<span class="style1">(������400����)</span>
							</td>
							<td colspan="7">
							<html:textarea name='rs' property='kyxm' style="width:99%"
								rows='5' />
							</td>
						</logic:notPresent>
						</logic:notPresent>
					</tr>
					<tr align="left" style="height:22px">
						<logic:notPresent name="shjxj">
							<td align="right">
							�������ɣ�
							<br/>
							<span class="style1">(������400����)</span>
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' />
						</td>
						</logic:notPresent>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="showhzy">
					<button class="button2" onclick="window.open('/xgxt/jxjbbexp.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value)">
						�� ӡ �� ��
					</button>
					</logic:notPresent>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript">
			
			function chgjxj(eid,dotype) {
				var obj = document.getElementById(eid);
				if (obj.selectedIndex<=0) {
					document.getElementById('jxjdm').selectedIndex=0;
					document.getElementById('jlje').value='';
					document.getElementById('jxjdm').disabled = true;
				}else {
					if (document.getElementById('xh').value=='' 
					|| document.getElementById('xh').value==null || document.getElementById('xh').value==' ') {
						alert('��������ѧ�ţ�');
						document.getElementById(eid).selectedIndex=0;
						document.getElementById('jxjdm').selectedIndex=0;
						document.getElementById('jlje').value='';
						document.getElementById('jxjdm').disabled = true;
						return;
					} else {
						document.getElementById('jxjdm').disabled = false;
						if (dotype=='refresh') {
							refreshForm('/xgxt/prise_apply.do?tab=qtjxj&jxjlb1='+obj.value);
						}
					}
				}
			}
		</script>
	</body>
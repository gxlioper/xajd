<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function print1(){
		var url = "/xgxt/data_print.do";
		var xh = $('xh').value;
		var zysj = $('zysj').value;
		var zzmmmc = $('zzmmmc').value;
		var rych = selText('rychdm');
		url = url+"?xh="+xh+"&zysj="+zysj+"&zzmmmc="+zzmmmc+"&rych="+rych;
		showOpenWindow(url,'1024','768');
	}
</script>
</head>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �����ƺ����� - ��д�����</a>
				</p>
			</div>
			<span id="tipFollow" style="display:none"></span>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="url" name="url" value="/sjcz/xsrychb.jsp" />
				<input type="hidden" id="ylrychdm" name="ylrychdm" value="<bean:write name='rs' property="rychdm" />"/>
 				<input type="hidden" id="stab" name="stab" value="stab"/>
 				<input type="hidden" id="edit" name="edit" value="yes"/>
 				<script type="text/javascript" src="js/BatAlert.js"></script>
 				<input type="hidden" name="tableName" id="tableName" value="view_xsrychb"/>
 				<input type="hidden" id="oldpk" name="oldpk" value="${rs.xh}${rs.xn}${rs.xq}${rs.rychdm}"/>
 				<input type="hidden" id="tmp" name="tmp" value="${rs.xn}${rs.nd}${rs.rychdm}${rs.xh}"/>
 				<input type="hidden" id="zzmmmc" value="${rs.zzmmmc }"/>
				<div class="div">
					<table width="95%" class="formlist">
						<thead>
							<tr style="height:22px">
								<th colspan="4">
									<span>�����ƺ�����</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th width="20%">
									<font color="red">*</font>ѧ��
								</th>
								<td align="left" width="30%">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th width="20%">
									<font color="red">*</font>ѧ��
								</th>
								<td align="left" width="30%">
									<input type="hidden" value="${rs.xh }" id="xh"/>
									<bean:write name='rs' property="xh"/>
								</td>
							</logic:equal>
							<th width="20%">
								���
							</th>
							<td align="left" width="30%">
								<bean:write name='rs' property="nd" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name='rs' property="xn" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								<font color="red">*</font>�����ƺ�
							</th>
							<td align="left">
							 	<logic:equal value="10355" name="xxdm">
							 		<html:select property="xmdm" styleId="rychdm" onchange="refreshForm('modiData.do?realTable=xsrychb&daType=modi&pk=xn||nd||rychdm||xh&form=credit&pkValue='+document.getElementById('tmp').value)+'&dms='+document.getElementById('rychdm').value">									
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
							 	</logic:equal>
								<logic:notEqual value="10355" name="xxdm">
								<html:select property="xmdm" styleId="rychdm" onchange="">									
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<logic:equal value="yes" name="info">
							<th>����</th>
							<td align="left">${rs.mzmc }</td>
							</logic:equal>
							<logic:notEqual value="yes" name="info">
								<th>����ˮƽ</th>
							<td align="left"><html:text name='rs' property="wysp" styleId="a"/></td>
							</logic:notEqual>
						</tr>
						<tr style="height:22px">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<logic:equal value="yes" name="info">
							<th>������ò</th>
							<td align="left">${rs.zzmmmc }
							</td>
							</logic:equal>
							<logic:notEqual value="yes" name="info">
								<th>��ҵ��ѧ</th>
							<td align="left"><html:text name='rs' property="byzx" styleId="a" /></td>
							</logic:notEqual>
						</tr>
						<tr style="height:22px">
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th>
								����ְ��
							</th>
							<td align="left">
								<html:text name='rs' property="drzw" styleId="a" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<logic:equal value="yes" name="info">
							<th>��Դ��</th>
							<td align="left">${rs.syd }</td>
							</logic:equal>
							<logic:notEqual value="yes" name="info">
								<td align="right"></td>
							<td align="left"></td>
							</logic:notEqual>
						</tr>
						<logic:notEqual value="10355" name="xxdm">
						<tr>
							<th>
								��Դ��
							</th>
							<td align="left">
								<html:text name='rs' property="syd" readonly="true"
									styleId="a" />
							</td>
							<th>
								��ϵ�绰
							</th>
							<td align="left">
								<html:text name="rs" property="lxdh" readonly="true" styleId="a" />
							</td>
						</tr>
						<tr>
								<th>
								��ͥ��ַ
							</th>
							<td colspan="3" align="left">
								<html:text name='rs' readonly="true"  property="jtszd" style="width: 90%"
									styleId="a" />
							</td>
						</tr>
						</logic:notEqual>
						<logic:equal value="yes" name="info">
						<tr style="height:22px">
							<th>
								��ͥ��ַ
							</th>
							<td align="left" colspan="3">
								${rs.jtdz }
							</td>
							</tr>
							</logic:equal>
						<logic:equal value="10355" name="xxdm">
						<logic:equal value="yes" name="yybys">
						<tr style="height:22px">
							<th>
								�����������˵��
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' 
									style="width: 95%;word-break:break-all;" property="mzpyqksm" styleId="mzpyqksm" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�������
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs'
									style="width: 95%;word-break:break-all;" property="jcqk" styleId="jcqk" />
							</td>
						</tr>
						</logic:equal>
						
						<logic:equal value="yes" name="sybys">
						<tr style="height:22px">
							<th>
								���˼���
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width: 95%;word-break:break-all;"
									property="brjl" styleId="a" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��Ҫ�¼�
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width: 95%;word-break:break-all;"
									property="zysj" styleId="zysj" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��У�ڼ�����
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' 
									style="width: 95%;word-break:break-all;" property="hjqk" styleId="hjqk" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��ҵ��ҵȥ��
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' 
									style="width: 95%;word-break:break-all;" property="byjyqx" styleId="byjyqx" />
							</td>
						</tr>
						</logic:equal>
						<tr align="left" style="height:22px">
<%--						<td align="right">--%>
<%--							��ע<font color="red">(�ܴ���,����,<br/>�������,<br/>ѧϰ�ɼ����)</font>--%>
<%--							<br/>--%>
<%--						</td>--%>
<%--						<td colspan="7">--%>
<%--							<html:textarea name='rs' property='bz' style="width:99%"--%>
<%--								rows='5' readonly="true"/>--%>
<%--						</td>--%>
					</tr>
					</logic:equal>
					<logic:notEqual value="10355" name="xxdm">
					<tr style="height:22px">
							<th>
								���˼���
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width: 95%;word-break:break-all;"
									property="brjl" styleId="a" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��Ҫ�¼�
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width: 95%;word-break:break-all;"
									property="zysj" styleId="a" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��У�ڼ�����
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width: 95%;word-break:break-all;" property="hjqk" styleId="hjqk" />
							</td>
						</tr>
					</logic:notEqual>
					</tbody>
					
					<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					         	 <button type="button" onclick="dataCanModi(true)" id="buttonModi">�� ��</button>
					          	<button type="button" name="�ύ" onclick="dataDoSaveApply1('/xgxt/applySave.do','rychdm','rych')" id="buttonSave">����</button>
					          	<logic:equal value="yes" name="isPrint">
					          	 <button type="button" onclick="print1();">��ӡ</button>
					          	</logic:equal>
					            <button type="button" name="�ر�" onclick="window.close();return false;" id="buttonClose">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
					</div>
				
			</logic:notEmpty>
		</html:form>
	</body>
</html>

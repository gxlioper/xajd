<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ��� - �����ƺ���� - ���������ƺ����</a>
				</p>
			</div>
			
			<input type="hidden" name="userType" id="userType" value="${userType }"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<input type="hidden" name="xsh" id="xsh" value="${xxsh }"/>
			<input type="hidden" name="ysh" id="ysh" value="${xysh }"/>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
			<input type="hidden" name="pkVal"
 				value="<bean:write name="xn||nd||xh||rychdm"/>" />
				<input type="hidden" id="tg" name="tg" value="${tgres }" />
				<!-- �㽭���� -->
			<logic:present name="showzjjd">
				<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							���������ƺ����
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ��
					</td>
					<td align="left"  id="selxh">
						<bean:write name="XH" />
						<input type="hidden" name="xh" id="xh" value="<bean:write name="XH" />"/>
					</td>
					<td align="right">
						���
					</td>
					<td align="left">
						<bean:write name="ND" />
						<input type="hidden" name="nd" id="nd" value="<bean:write name="ND" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����
					</td>
					<td align="left">
						<bean:write name="XM" />
					</td>
					<td align="right">
						ѧ��
					</td>
					<td align="left">
						<bean:write name="XN" />
						<input type="hidden" name="xn" id="xn" value="<bean:write name="XN" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="XB" />
					</td>
					<td align="right">
						�����ƺ�
					</td>
					<td align="left">
						<bean:write name="RYCHMC" />
						<input type="hidden" name="rychmc" id="rychmc" value="<bean:write name="RYCHMC" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶
					</td>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<td align="right">
						ѧ���ڵ���ƽ���ɼ�
					</td>
					<td align="left">
						${dypjcj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<td align="right">
						ѧ��������ƽ���ɼ�
					</td>
					<td align="left">
						${zypjcj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ
					</td>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<td align="right">
						�ۺϲ����ɼ�
					</td>
					<td align="left">
						${zhszcpzf }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����ȼ�
					</td>
					<td align="left">
						<bean:write name="wydj"/>
					</td>
					<td align="right">
						�ۺϲ�������
					</td>
					<td align="left">
						${zhszpm }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������ȼ�
					</td>
					<td align="left">
						<bean:write name="jsjdj"/>
					</td>
					<td align="right">
						�༶�����ȼ�
					</td>
					<td align="left">
						<bean:write name="bjpddj"/>
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						�༶
					</td>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
					<td align="right">
						���
					</td>
					<td align="left">
						<html:select property="yesNo" styleId="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>

			</table>
			</logic:present>
			<!-- END �㽭���� -->
			<!-- ���㽭���� -->
			<logic:notPresent name="showzjjd">
				<logic:equal value="10690" name="xxdm">
				<div class="tab">
					<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>���������ƺ����</span>
						</th>
					</tr>
				</thead>
				<tr style="height:22px">
					<th align="right">
						ѧ��
					</th>
					<td align="left"  id="selxh">
						<bean:write name="XH" />
						<input type="hidden" name="xh" id="xh" value="<bean:write name="XH" />"/>
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name="XN" />
						<input type="hidden" name="xn" id="xn" value="<bean:write name="XN" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						����
					</th>
					<td align="left">
						<bean:write name="XM" />
					</td>
					<th align="right">
						�����ƺ�
					</th>
					<td align="left">
						<bean:write name="RYCHMC" />
						<input type="hidden" name="rychmc" id="rychmc" value="<bean:write name="RYCHMC" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<bean:write name="XB" />
					</td>
					<th align="right">
						˼��������ʷ�:
					</th>
					<td align="left">
						${SXZZDDSZF }
					</td>
				</tr>
						<tr style="height:22px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<th align="right">
						��ѧ�Ļ����ʷ�:
					</th>
					<td align="left">
						${KXWHSZF }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<th align="right">
						�����������ʷ�:
					</th>
					<td align="left">
						${SXLXSZF }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<th align="right">
						ʵ�������������ʷ�:
					</th>
					<td align="left">
						${SJLXCXF }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�༶
					</th>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
					<th align="right">
						�ۺϲ����ܷ�:
					</th>
					<td align="left">
						${YNYSZHSZCPZF }
					</td>
				</tr>
				<tr>
					<th align="right">
						���
					</th>
					<td align="left" colspan="3">
						<html:select property="yesNo" styleId="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal value="xy" name="userType" scope="session">
					<tr>
					<th align="right">
						������:
					</th>
					<td align="left" colspan="3">
						<html:textarea  property="xyyj" styleId="xyyj" style="width:95%"
						 rows="3"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType" scope="session">
				<tr>
					<th align="right">
						������:
					</th>
					<td align="left" colspan="3">
						<html:textarea  property="xxyj" styleId="xxyj" style="width:95%"
						 rows="3"></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
			</table>
			</div>
				</logic:equal>
				<logic:notEqual value="10690" name="xxdm">
					<div class="tab">
					<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							���������ƺ����
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right">
						ѧ��
					</th>
					<td align="left"  id="selxh">
						<bean:write name="XH" />
						<input type="hidden" name="xh" id="xh" value="<bean:write name="XH" />"/>
					</td>
					<th align="right">
						���
					</th>
					<td align="left">
						<bean:write name="ND" />
						<input type="hidden" name="nd" id="nd" value="<bean:write name="ND" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						����
					</th>
					<td align="left">
						<bean:write name="XM" />
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name="XN" />
						<input type="hidden" name="xn" id="xn" value="<bean:write name="XN" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<bean:write name="XB" />
					</td>
					<th align="right">
						�����ƺ�
					</th>
					<td align="left">
						<bean:write name="RYCHMC" />
						<input type="hidden" name="rychmc" id="rychmc" value="<bean:write name="RYCHMC" />"/>
					</td>
				</tr>
				<logic:notPresent name="isgdby">
					<logic:present name="showhzy">
						<tr style="height:22px">
					<td align="right">
						�꼶
					</td>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<td align="right">
						ƽ���ɼ�
					</td>
					<td align="left">
						<bean:write name="pjcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<td align="right">
						�ɼ�����
					</td>
					<td align="left">
						<logic:equal value="12872" name="xxdm">
							<html:text property="cjmc" styleId="cjmc" value="${cjmc}"></html:text>
						</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
							<bean:write name="cjmc"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ
					</td>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<th align="right">
						�ۺ����ʲ�������
					</th>
					<td align="left">
						<logic:equal value="12872" name="xxdm">
							<html:text property="zhpfmc" styleId="zhpfmc" value="${zhpfmc}"></html:text>
						</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
							<bean:write name="zhszcpcjpm"/>
						</logic:notEqual>
					</td>
				</tr>
					</logic:present>
					<logic:notPresent name="showhzy">
						<tr style="height:22px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<!-- ���й�����<bean:message key="lable.xsgzyxpzxy" /> -->
					<logic:notEqual value="10355" name="xxdm">
					<!-- ���㽭��ý<bean:message key="lable.xsgzyxpzxy" /> -->
					<logic:notEqual value="11647" name="xxdm">
						<th align="right">
						�³ɼ�
					</th>
					<td align="left">
						<bean:write name='DCJ' />
					</td>
					<!-- END ���㽭��ý -->
					</logic:notEqual>
					<!-- END ���й�����<bean:message key="lable.xsgzyxpzxy" /> -->
					</logic:notEqual>
					<!--�й�����<bean:message key="lable.xsgzyxpzxy" /> -->
					<logic:equal value="10355" name="xxdm">
					<th align="right">
						����
					</th>
					<td align="left">
						${mzmc }
					</td>
					</logic:equal>
					<!--END �й�����<bean:message key="lable.xsgzyxpzxy" /> -->
					<!-- �㽭��ý<bean:message key="lable.xsgzyxpzxy" /> -->
					<logic:equal value="11647" name="xxdm">
					<th align="right">�ۺ����ʷ�</th>
					<td>${cj }</td>
					</logic:equal>
					<!-- ���㽭��ý<bean:message key="lable.xsgzyxpzxy" /> -->
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<logic:notEqual value="10355" name="xxdm">
					<logic:notEqual value="11647" name="xxdm">
						<th align="right">
						�ǳɼ�
					</th>
					<td align="left">
						<bean:write name="ZCJ" />
					</td>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="10355" name="xxdm">
					<th align="right">������ò</th>
					<td align="left">${zzmmmc }</td>
					</logic:equal>
					<logic:equal value="11647" name="xxdm">
					<th align="right">�ۺ����ʷְ༶����</th>
					<td>${bjpm }</td>
					</logic:equal>
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					
					<logic:notEqual value="10355" name="xxdm">
					<logic:notEqual value="11647" name="xxdm">
						<th align="right">
						��ɼ�
					</th>
					<td align="left">
						<bean:write name="TCJ" />
					</td>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="10355" name="xxdm">
					<td align="right">����ְ��</td>
					<td align="left">${drzw }</td>
					</logic:equal>
					<logic:equal value="11647" name="xxdm">
					<td></td>
					<td></td>
					</logic:equal>
				</tr>
					</logic:notPresent>
				</logic:notPresent>
				<logic:equal value="yes" name="isgdby">
					<tr style="height:22px">
					<td align="right">
						�꼶
					</td>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<th align="right">
						����
					</th>
					<td align="left">
						${mzmc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<th align="right">
						������ò
					</th>
					<td align="left">
						${zzmmmc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<th align="right">
						���гɼ�
					</th>
					<td align="left">
						<bean:write name="CXCJ" />
					</td>
				</tr>
				</logic:equal>
						<tr style="height:22px">
							<th align="right">
								�༶
							</th>
							<td align="left">
								<bean:write name="BJMC" />
							</td>
<%--							<logic:equal value="yes" name="notgyk">--%>
<%--								<td></td>--%>
<%--								<td></td>--%>
<%--							</logic:equal>--%>
							<!-- BEGIN ��ְԺ -->
							<logic:equal value="12872" name="xxdm">
								<logic:equal value="true" name="isFdy">
									<td></td>
									<td>
										<input type="hidden" name="yesNo" value="${yesNo}" />
									</td>
								</logic:equal>
								<logic:notEqual value="true" name="isFdy">
									<th align="right">
										���
									</th>
									<td align="left">
										<html:select property="yesNo" styleId="yesNo">
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
							</logic:equal>
							<!-- END ��ְԺ -->
							<logic:notEqual value="12872" name="xxdm">
								<logic:notEqual value="yes" name="notgyk">
									<th align="right">
										���
									</th>
									<td align="left">
										<html:select property="yesNo" styleId="yesNo">
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
							</logic:notEqual>
						</tr>

						<logic:equal value="10355" name="xxdm">
					<logic:equal value="yes" name="yybys">
						<tr style="height:22px">
							<th align="right">
								�����������˵��
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								${mzpyqksm }
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�������
								<br/>
							<span class="style1">(������400����)</span>
							</th>
							<td colspan="3" align="left">
								${jcqk }
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="yes" name="sybys">
						<tr style="height:22px">
							<td align="right">
								���˼���
								<br/>
							<span class="style1">(������400����)</span>
							</td>
							<td colspan="3" align="left">
								${brjl }
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								��Ҫ�¼�
								<br/>
							<span class="style1">(������400����)</span>
							</td>
							<td colspan="3" align="left">
								${zysj }
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								��У�ڼ�����
								<br/>
							<span class="style1">(������400����)</span>
							</td>
							<td colspan="3" align="left">
								${hjqk }
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								��ҵ��ҵȥ��
								<br/>
							<span class="style1">(������400����)</span>
							</td>
							<td colspan="3" align="left">
								${byjyqx }
							</td>
						</tr>
						</logic:equal>
<%--					<tr style="height:22px">--%>
<%--						<td align="right">--%>
<%--								��ע<font color="red">(�ܴ���,����,<br/>�������,<br/>ѧϰ�ɼ����)</font>--%>
<%--								<br/>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<html:textarea property='bz' style="width:95%"--%>
<%--									rows='5' readonly="true" ></html:textarea>--%>
<%--							</td>--%>
<%--					</tr>--%>
				</logic:equal>
				<logic:equal value="yes" name="shownblg">
					<tr style="height:22px">
					<th align="right">
						�³ɼ��༶����
					</th>
					<td align="left">
						${dcjpm }
					</td>
					<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				</logic:equal>
				<logic:present name="showhzy">
					<tr>
						<th align="right">
							��Ҫ�¼�
						</th>
						<td align="left" colspan="3" >
							<textarea name="zysj" id="zysj" rows="3" style="width:95%" type="_moz">${zysj }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							���������	
						</th>
						<td align="left" colspan="3">
							<textarea name="fdyyj" id="fdyyj" rows="3" style="width:95%"><logic:equal value=" " name="FDYYJ">ͬ���Ƽ�</logic:equal><logic:notEqual value=" " name="FDYYJ"><bean:write name="FDYYJ"/></logic:notEqual></textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							(ϵ)Ժ���	
						</th>
						<td align="left" colspan="3">
							<textarea name="xyyj" id="xyyj" rows="3" style="width:95%"><logic:equal value=" " name="XYYJ">ͬ���Ƽ�</logic:equal><logic:notEqual value=" " name="XYYJ"><bean:write name="XYYJ"/></logic:notEqual></textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							������ǩ��	
						</th>
						<td align="left">
							<input name="fdyqm" id="fdyqm" value="${fdyqm }" />
						</td>
						<logic:equal value="true" name="isFdy">
							<td></td><td><input type="hidden" name="xyqm" value="${xyqm}"/></td>
						</logic:equal>
						<logic:notEqual value="true" name="isFdy">
						<th align="right"> 
							��ϵ��Ժǩ��
						</th>
						<td align="left">
							<input name="xyqm" id="xyqm" value="${xyqm }" />
						</td>
						</logic:notEqual>
						
					</tr>
					<tr style="height:22px">
						<th align="right">
							ʡ���������
						</th>
						<td align="left" colspan="3">
							<textarea name="sjfkyj" rows="4" style="width: 100%" type="_moz"><logic:equal value=" " name="FKYJ">ͬ��</logic:equal><logic:notEqual value=" " name="FKYJ"><bean:write name="FKYJ"/></logic:notEqual> </textarea>
						</td>
					</tr>
				</logic:present>
				<logic:notEqual value="12872" name="xxdm">
					<logic:equal value="xy" name="userType" scope="session">
					<tr>
					<th align="right">
							����ְ��	
						</th>
						<td align="left">
							${drzw }
						</td>
						<th align="right">
							����ˮƽ	
						</th>
						<td align="left">
							${wysp }
						</td>
				</tr>
					<tr>
						<th align="right">���˼���</th>
						<td align="left" colspan="3">${brjl }</td>
					</tr>
					<tr>
						<th align="right">��Ҫ�¼�</th>
						<td align="left" colspan="3">${zysjs }</td>
					</tr>
					<tr>
						<th align="right">��У�ڼ�����</th>
						<td align="left" colspan="3">${hjqk }</td>
					</tr>
					<tr>
					<td align="right">
						<logic:equal value="true" name="isFdy">����Ա</logic:equal>
						<logic:equal value="10355" name="xxdm">
						��У�ڼ���ּ�<bean:message key="lable.xsgzyxpzxy" />�Ƽ����:
						</logic:equal>
						<logic:notEqual value="10355" name="xxdm">
						<logic:equal value="false" name="isFdy"><bean:message key="lable.xsgzyxpzxy" /></logic:equal>������
						</logic:notEqual>
					</td>
					<td align="left" colspan="3">
						<html:textarea  property="xyyj" styleId="xyyj" style="width:95%"
						 rows="3"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType" scope="session">
				<logic:notEqual value="10355" name="xxdm">
				<tr>
					<th align="right">
							����ְ��	
						</th>
						<td align="left">
							${drzw }
						</td>
						<th align="right">
							����ˮƽ	
						</th>
						<td align="left">
							${wysp }
						</td>
				</tr>
					<tr>
						<th align="right">���˼���</th>
						<td align="left" colspan="3">${brjl }</td>
					</tr>
					<tr>
						<th align="right">��Ҫ�¼�</th>
						<td align="left" colspan="3">${zysjs }</td>
					</tr>
					<tr>
						<th align="right">��У�ڼ�����</th>
						<td align="left" colspan="3">${hjqk }</td>
					</tr>
					<logic:notEqual value="11049" name="xxdm">
					<tr>
					<logic:notEqual value="10628#" name="xxdm">
					<th align="right">
						����Ա������
					</th>
					<td align="left" colspan="3">
						<html:textarea property="fdyyj" styleId="fdyyj" style="width:95%"
						 rows="3" readonly="true"></html:textarea>
					</td>
					</logic:notEqual>
				</tr>
				</logic:notEqual>
				</logic:notEqual>
				<tr>
					<th align="right">
						<logic:equal value="10355" name="xxdm">
						��У�ڼ���ּ�<bean:message key="lable.xsgzyxpzxy" />�Ƽ����
						</logic:equal>
						<logic:notEqual value="10355" name="xxdm">
						<bean:message key="lable.xsgzyxpzxy" />������
						</logic:notEqual>
						
					</th>
					<td align="left" colspan="3">
						${xyyj }
					</td>
				</tr>
				<logic:equal value="10355" name="xxdm">
<%--					<logic:notEqual value="xy" name="userType">--%>
<%--						<tr style="height:22px">--%>
<%--						<td align="right">--%>
<%--							ʡ���������--%>
<%--						</td>--%>
<%--						<td align="left" colspan="3">--%>
<%--							<textarea name="sjfkyj" rows="4" style="width: 100%" type="_moz"><logic:equal value=" " name="FKYJ">ͬ��</logic:equal><logic:notEqual value=" " name="FKYJ"><bean:write name="FKYJ"/></logic:notEqual> </textarea>--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					</logic:notEqual>--%>
				</logic:equal>
				<tr>
					<th align="right">
						ѧУ�������:
					</th>
					<td align="left" colspan="3">
						<html:textarea property="xxyj" styleId="xxyj" style="width:95%"
						 rows="3"></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
				</logic:notEqual>
				</tbody>
				<tfoot>
						<tr>
							<td colspan="4">
								 <div class="btn">
								 <logic:equal value="yes" name="shownblg">
					<button type="button" class=""
					onclick="if (document.getElementById('yesNo').value==document.getElementById('tg').value) {alert('��δ���κ��޸ģ�');return;} else refreshForm('/xgxt/creditChkOne.do?act=save');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				</logic:equal>
				<logic:notPresent name="shownblg">
					<button type="button" class=""
					onclick="ckflag()"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				</logic:notPresent>
				<logic:notPresent name="showjsxx">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="" onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" 
					id="buttonQuery">
					�鿴ѧ����Ϣ
				</button>
				</logic:notPresent>
				<logic:present name="showjsxx">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="" onclick="showTopWin('/xgxt/stu_cj_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" 
					id="buttonQuery">
					�鿴ѧ���ɼ�
				</button>
				</logic:present>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
								 </div>
								 </td>
								 </tr>
								 </tfoot>
			</table>
			</div>
				</logic:notEqual>
			</logic:notPresent>
			 <div class="buttontool" align="center">
				
				<logic:present name="insert">
					<logic:equal value="yes" name="insert">
						<script>
							alert('�����ɹ�!');
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>
					</logic:equal>
					<logic:equal value="no" name="insert">
					<logic:equal value="yes" name="cb">
						<script>
							alert('�����ѻ����������ƺŲ�����ͨ��!');
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>
					</logic:equal>
					
						<script>
							alert('����ʧ��,ԭ������Ǹ������������������ƺż�ù涨\n�������ͨ����������רҵ�趨����,��ȷ�ϣ�');
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>
					</logic:equal>
					
				</logic:present>
				</div>
		</html:form>
		<script>
			function ckflag() {
				var xxdm;
				var uType = document.getElementById('userType').value;
				var isFdy = document.getElementById('isFdy').value;
				var xsh = document.getElementById('xsh').value;
				var ysh = document.getElementById('ysh').value;
				if ($('xxdm')) {
					xxdm = document.getElementById('xxdm').value;
				}
				if (xxdm=='10827') {
					if (uType=='xy') {//fdy
						if (isFdy=='true') {
							if (xsh=='ͨ��' || ysh=='ͨ��') {
								alert('����ز����������ͨ��,�������޸�!');
								return;
							} else {
								refreshForm('/xgxt/creditChkOne.do?act=save');
								return;
							}
						} else {//xy
							if (xsh=='ͨ��') {
								alert('����ز����������ͨ��,�������޸�!');
								return;
							} else {
								refreshForm('/xgxt/creditChkOne.do?act=save');
								return;
							}
						}
					} else {
						refreshForm('/xgxt/creditChkOne.do?act=save');
						return;
					}
				} else {
					refreshForm('/xgxt/creditChkOne.do?act=save');
					return;
				}
			}
		</script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/xljkFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXljkSjm.js'></script>
	</head>
	
	<body onload="checkDoType();">
		<html:form action="/xljk_whlgdx_xskhdxwh" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				onclick="getStuInfoByXh();"/>
			<input type="hidden" name="doType"
				value="<bean:write name="rs" property="doType"/>" id="doType" />
			<input type="hidden" name="action"
				value="<bean:write name="rs" property="action"/>" id="action" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">		
			<table class="formlist" style="width:100%;">
				<thead><tr><th colspan="4"><span>��Ϣά��</span></th></tr></thead>
				<tbody>
				<tr id="a1">
					<th>
						<logic:notEqual   value="view" name="rs" property="doType">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ ��
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" name="rs"
							onkeydown="if(event.keyCode==13)getStuInfoByXh();" />
						<logic:equal value="add" name="rs" property="doType">
							<button  
								onclick="showTopWin('/xgxt/xljk_whlgdx_xskhdxwh.do?doType=stu_info',800,600);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
							<%--	
							onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						--%>
						</logic:equal>
					</td>
					<th>
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr id="a2">
					<th>
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" readonly="true" />
					</td>
					<th>
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="nj" styleId="nj" readonly="true" />
					</td>
				</tr>
				<tr id="a2">
					<th>
						ѧ Ժ
					</th>
					<td align="left">
						<html:text name="rs" property="xymc" styleId="xymc"
							readonly="true" />
					</td>
					<th>
						ר ҵ
					</th>
					<td align="left">
						<html:text name="rs" property="zymc" styleId="zymc"
							readonly="true" />
					</td>
				</tr>
				<logic:equal value="10338" name="xxdm" scope="session">
					<tr id="a3">
					<th>
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc"
							readonly="true" />
					</td>
					<th>
						����
					</th>
					<td align="left">
						<html:text name="rs" property="nl" styleId="nl"/>
					</td>
				</tr>
				<tr id="a3">
					<th>
						����
					</th>
					<td align="left">
						<html:text name="rs" property="jg" styleId="jg"/>
					</td>
					<th nowrap="nowrap">
						��Ҫ��������
					</th>
					<td align="left">
						<html:text name="rs" property="zyxlwt" styleId="zyxlwt"/>
					</td>
				</tr>
				<tr id="a3">
					<th nowrap="nowrap">
						ҽԺ������
					</th>
					<td align="left">
						<html:text name="rs" property="yyzdqk" styleId="yyzdqk"/>
					</td>
					<th>
						������
					</th>
					<td align="left">
						<html:text name="rs" property="xgqk" styleId="xgqk"/>
					</td>
				</tr>
				<tr id="a3">
					<th>
						Ŀǰ״̬
					</th>
					<td align="left">
						<html:text name="rs" property="mqzt" styleId="mqzt"/>
					</td>
					<th>
						ѧ���춯
					</th>
					<td align="left">
						<html:text name="rs" property="xjyd" styleId="xjyd"/>
					</td>
				</tr>
				<tr id="a3">
					<th>
						��������
					</th>
					<td align="left">
						<html:text name="rs" property="fqxm" styleId="mqzt"/>
					</td>
					<th>
						ĸ������
					</th>
					<td align="left">
						<html:text name="rs" property="mqxm" styleId="xjyd"/>
					</td>
				</tr>
				<tr id="a3">
					<th>
						��ͥ��ϵ��ʽ
					</th>
					<td align="left">
						<html:text name="rs" property="fmlxfs" styleId="mqzt"/>
					</td>
					<th>
					</th>
					<td align="left">
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="10338" name="xxdm" scope="session">
				<tr id="a3">
					<th>
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc"
							readonly="true" />
					</td>
					<th>
						���֤��
					</th>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh"
							readonly="true" />
					</td>
				</tr>
				<tr id="a3">
					<th>
						������
					</th>
					<td align="left">
						<html:text name="rs" property="ssbh" styleId="ssbh"
							readonly="true" />
					</td>
					<th>
						���ҵ绰
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" styleId="qsdh"
							readonly="true" />
					</td>
				</tr>
				<tr id="a3">
					<th>
						�ֻ�����
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="sjhm"
							readonly="true" />
					</td>
					<th>
						��ϵ�绰
					</th>
					<td align="left">
						<html:text name="rs" property="lxdh" styleId="lxdh"
							readonly="true" />
					</td>
				</tr>
				</logic:notEqual>
				<tr>
					<th rowspan="10" align="right">
						�� ע
						<font color="red"><br/>(��2000�ַ�)</font>
					</th>
					<td colspan="3">
						<span id="tishi"></span>
						<br/>
						<textarea name="rs" cols="70" rows="10"
							onpropertychange="javascript:getInputLength('remark',2000);"
							id="remark"><bean:write name="rs" property="remark"/></textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          
			          <logic:notEqual   value="view" name="rs" property="doType">
			          			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          </logic:notEqual>
			          
			          <div class="btn">
						  <logic:equal value="add" name="rs" property="doType">
							<button onclick="checkStuExist()">
								����
							</button>
						  </logic:equal>
						  <logic:equal value="modi" name="rs" property="doType">
						  	<button onclick="checkStuExist()">
								�޸ı���
							</button>
						  </logic:equal>
						  <button name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
			
			<div id="tmpdiv"></div>
		</html:form>
	</body>
	<logic:equal value="yes" property="result" name="rs">
		<script>
				alert("�����ɹ�");
				dialogArgumentsQueryChick();
				Close();
			</script>
	</logic:equal>
	<logic:equal value="no" property="result" name="rs">
		<script>alert("����ʧ��")</script>
	</logic:equal>
</html>

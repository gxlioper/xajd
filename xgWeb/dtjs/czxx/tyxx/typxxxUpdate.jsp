<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript"></script>
	</head>
	
	<body onload="">
		<html:form action="/czxxDtjsTyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="tableName" name="tableName" value="view_czxx_tyxx"/>
		<input type="hidden" id="zd" name="zd" value="sfty"/>
		<input type="hidden" id="va" name="va" value="��"/>
		<input type="hidden" id="lx" name="lx" value="��Ա"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xydm-xymc-zymc-bjmc"/>
		<input type="hidden" id="url" name="url" value="/xgxt/czxxDtjsTyxx.do?method=typxxxUpdate&doType=add"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div class="tab">
			<table width="100%"  border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4" align="center">
							<span>��Ա��ѵ��Ϣ</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<logic:equal name="doType" value="add">
			        	<div class="bz">"<span class="red">*</span>"Ϊ������</div>
			        	</logic:equal>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="�ύ" 
			          		onclick="if(checkXnNd('xn','nd')){saveUpdate('/xgxt/czxxDtjsTyxx.do?method=typxxxUpdate&doType=save','xh-pxxmdm-pxsj-xn-nd-xq')}">�� ��</button>
			          </logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height: 23px">
					<th align="right" width="10%">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						ѧ��
					</th>
					<td align="left" width="40%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th align="right">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						���
					</th>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="nd" styleId="nd"/>
							<bean:write name="rs" property="nd"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="nd" styleId="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</logic:equal>
						
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th align="right">
					<logic:equal name="doType" value="add">
						<font color="red">*</font>
					</logic:equal>	
						ѧ��
					</th>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="xn" styleId="xn"/>
							<bean:write name="rs" property="xn"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="xn" styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
						</logic:equal>						
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th align="right">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						ѧ��
					</th>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="xq" styleId="xq"/>
							<bean:write name="rs" property="xqmc"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="xq" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</logic:equal>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<th align="right">
						<logic:equal name="doType" value="add">
							<font color="red">*</font>
						</logic:equal>
						��ѵ��Ŀ
					</th>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="pxxmdm" styleId="pxxmdm"/>
							<bean:write name="rs" property="pxxmmc"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select property="pxxmdm" styleId="pxxmdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="pxxmList" property="dm" labelProperty="mc"/>
							</html:select>
						</logic:equal>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th align="right">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						��ѵʱ��
					</th>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="pxsj" styleId="pxsj"/>
							<bean:write name="rs" property="pxsj"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:text name="rs" property="pxsj" styleId="pxsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('pxsj','y-mm-dd');"/>
						</logic:equal>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<th align="right">
						��ѵ�ɼ�
					</th>
					<td align="left">
						<html:text name="rs" property="pxcj" styleId="pxcj" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="8"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						��ע<font color="blue"><B>(������60��)</B></font>
					</th>
					<td align="left" colspan="3" style="word-break:break-all">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				</table>
			</div>
			
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
	</html>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	</head>
	
	<body onload="">
		<html:form action="/czxxDtjsDyxx" enctype="multipart/form-data">
		<input type="hidden" value="${result }"/>
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="url" name="url" value="/xgxt/czxxDtjsDyxx.do?method=sxhbUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj-mzmc"/>
		<input type="hidden" id="tableName" name="tableName" value="view_czxx_rdsq"/>
		<input type="hidden" id="lx" name="lx" value="�뵳������"/>
		<input type="hidden" id="forward" name="forward" value="/czxxDtjsDyxx.do?method=sxhbUpdate"/>
		<html:hidden name='rs' property="id" styleId="id"/>
				<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>˼��㱨</span>
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
			          		onclick="saveUpdate('/xgxt/czxxDtjsDyxx.do?method=sxhbUpdate&doType=save','xh-xn-xq-wjm-tjsj')">�� ��</button>
			          </logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height: 23px">
					<th align="right" width="15%">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left" width="35%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="userType" value="stu">
								<button type="button" onclick="sendXx();return false;"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th align="right" width="15%">
						����
					</th>
					<td align="left" width="35%">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="" styleId="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:notEqual>
					</td>
					<th align="right">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xq" styleId="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
					</td>
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
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
						רҵ
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
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
						<logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						�ύʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="tjsj" styleId="tjsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('tjsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						<logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						�ļ���
					</th>
					<td align="left" colspan="3">
						<html:text name='rs' property="wjm" styleId="wjm" maxlength="50" style="width:80%"/>
					</td>
				</tr>	
				<tr>
					<logic:empty name="rs" property="scdz">
						<th align="right">
									�����ϴ�
						</th>
						<td align=left colspan="3"> 
							<input type="file" name="uploadFile" style="width:60%" id="kk" />
							&nbsp;&nbsp;
							<font color="red">(�ļ���СС��&lt;10M&gt;)</font>
						</td>
					</logic:empty>
					<logic:notEmpty name="rs" property="scdz">
						<th align="right">
							���ظ�����
						</th>
						<td align=left colspan="3"> 
							<input type="hidden" name="scdz" value="${rs.scdz }"/>
							<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=<bean:write name="rs" property="scdz" />" target="_blank" >�������</a>
							<logic:notEqual name="doType" value="view">
							&nbsp;&nbsp;
							<a href="javascript:refreshForm('/xgxt/czxxDtjsDyxx.do?method=fileDel')" >���ɾ��</a>
							</logic:notEqual>
						</td>
					</logic:notEmpty>
				</tr>	
				<tr style="height: 23px">
					<th align="right">
						��ע��<br/><font color="red">(��250��)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 80%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
			<logic:equal name="result" value="true">
				<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
				</script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script type="text/javascript">
				alert("����ʧ�ܣ�");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
				</script>
			</logic:equal>
		</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

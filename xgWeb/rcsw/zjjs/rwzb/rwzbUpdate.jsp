<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">	
	</script>
	</head>
	
	<body onload="">
		<html:form action="/zjjsRcswRwzb">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="url" name="url" value="zjjsRcswRwzb.do?method=rwzbUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj"/>
		<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
		<input type="hidden" id="lx" name="lx" value="ѧ��"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��������</span>
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
			          		onclick="saveUpdate('zjjsRcswRwzb.do?method=rwzbUpdate&doType=save','xh')">�� ��</button>
			          </logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr >
					<th align="right" width="20%">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						ѧ��
					</th>
					<td align="left"  width="30%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" style="width:100px"/>
							<button type="button" onclick="sendXx();return false;"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th  width="20%">
						����
					</th>
					<td align="left"  width="30%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th align="right">
						�꼶
					</th>
					<td align="left">
						${rs.nj }
					</td>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th align="right">
						רҵ
					</th>
					<td align="left">
						${rs.zymc }
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th align="right">
						�Ƿ����ѧ��
					</th>
					<td align="left">
						<html:select name="rs" property="sfjmxf" style="width:60px" styleId="sfjmxf">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<th align="right">
						�������
					</th>
					<td align="left">
						<html:text name="rs" property="bzje" styleId="bzje"
							onkeyup="checkInputNum(this)" 
							onblur="checkInputNum(this)" maxlength="5" 
							style="width : 100px;ime-mode:disabled;"
						/>
					</td>
				</tr>
				<tr>
         		<th>��������</th>
         			<td>
         				<html:select name="rs" property="rwlx" style="width:60px" styleId="rwlx">
							<html:option value=""></html:option>
							<html:option value="����">����</html:option>
							<html:option value="����">����</html:option>
						</html:select>
					</td>
					<th></th>
					<td></td>
         		</tr>	  
				<tr >
					<th align="right">
						��ע<br/><font color="red">(��500��)</font>
					</th>
					<td align="left" colspan="3"><br />
						<html:textarea name='rs' property="bz" styleId="bz"
							rows="5" style="width: 100%;word-break:break-all;" onblur="chLeng(this,500)"/>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alertInfo('�����ɹ���',function(){
							dialogArgumentsQueryChick();
							window.close();
						});
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alertInfo('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

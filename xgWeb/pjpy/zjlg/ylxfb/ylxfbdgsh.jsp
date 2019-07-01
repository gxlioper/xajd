<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
<body style="font-size:14px"  onload="">
	<html:form action="/zjlgPjpyylxfb" method="post">
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="isFdy" name="isFdy"
			value="<bean:write name="fdyQx" scope="session"/>" />
		<input type="hidden" id="userName" name="userName"
			value="<bean:write name="userName" scope="session"/>" />
		<input type="hidden" name="njV" id="njV"/>
		<input type="hidden" name="xyV" id="xyV"/>
		<input type="hidden" name="zyV" id="zyV"/>
		<input type="hidden" name="bjV" id="bjV"/>
		<input type="hidden" name="save_xn" value="${rs.xn }" />
		<input type="hidden" name="save_bjdm" value="${rs.bjdm }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="message" id="message" value="${message }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - ��� - ����ѧ��༶���</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>�������</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="25%">
					�༶����
				</th>
				<td width="25%">
					${rs.bjdm }
				</td>
				<th width="25%">
					ѧ��
				</th>
				<td width="25%">
					${rs.xn }
				</td>
			</tr>
			<tr>
				<th>
					�꼶
				</th>
				<td align="left">
					${rs.nj}
				</td>
				<th>
					�༶����
				</th>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc}
				</td>
				<th>
					����Ա
				</th>
				<td align="left">
					${rs.fdyxm}
				</td>
			</tr>
			
			<tr>
				<th>
					��ȿ��Կ�������
				</th>
				<td align="left">
					${map.kskyxl }
				</td>
				<th>
					��ȿ��Կμ�����
				</th>
				<td align="left">
					${map.kskjgl }
				</td>
			</tr>
			<tr>
				<th>
					��ȿ����������
				</th>
				<td align="left">
					${map.kckyxl }
				</td>
				<th>
					��ȿ���μ�����
				</th>
				<td align="left">
					${map.kckjgl }
				</td>
			</tr>
			<tr>
				<th>
					һ�����꼶Ӣ��γ�ƽ����
				</th>
				<td align="left">
					 ${map.ynj }&nbsp;/&nbsp;${map.enj }
				</td>
				<th>
					�༶ѧ�����ִ���
				</th>
				<td align="left">
					 ${map.wjcs }
				</td>
			</tr>
			<tr>
				<th>
					�༶A�����ұ���
				</th>
				<td align="left">
					  ${map.ajl }
				</td>
				<th>
					�༶�������ұ���
				</th>
				<td align="left">
					 ${map.wml }
				</td>
			</tr>
			<tr>
				<th>
					�༶��ɫ���ұ���
				</th>
				<td align="left">
					  ${map.tsl }
				</td>
				<th>
					
				</th>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<th>
					�༶������
				</th>
				<td align="left">
					${rs.cql}(%)
				</td>
				<th>
					�����ġ����꼶&nbsp;&nbsp;<br/>Ӣ���ļ���425�ֱ���
				</th>
				<td align="left">
					${rs.sjtgl}(%)
				</td>
			</tr>
			<tr>
				<th>
					���������ͨ����
				</th>
				<td align="left">
					${rs.jsjtgl}(%)
				</td>
				<th>
					���б�����
				</th>
				<td align="left">
					${rs.bkl}(%)
				</td>
			</tr>
			<tr>
				<th>
					����������
				</th>
				<td align="left">
					${rs.sxl}(%)
				</td>
				<logic:equal value="xy" name="userType">
					<th>
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td align="left">
						<html:select property="save_xysh" styleId="xysh" value="${rs.xysh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</td>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<th>
						ѧУ���
					</th>
					<td align="left">
						<html:select property="save_xxsh" styleId="xxsh" value="${rs.xxsh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</td>
				</logic:notEqual>
			</tr>
			<logic:notEqual value="xy" name="userType">
			<tr>
				<th>
					�༶��������
				</th>
				<td align="left">
					<html:text property="save_pjpm" styleId="save_pjpm" maxlength="3" onkeyup="chkIsNum(this)" style="width:50px" value="${rs.pjpm}"></html:text>
				</td>
				<th>
					
				</th>
				<td align="left">
					
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<th>
					�༶ѧ�罨��&nbsp;<br/>������
					<br/>
					<font color="red">(������1000����)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bhhd" styleId="bhhd" rows="5" style="width:99%" value="${rs.bhhd}" disabled="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					�ον�ʦ��&nbsp;<br/>�༶ѧ������
					<br/>
					<font color="red">(������1000����)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="jsbjpj" styleId="jsbjpj" rows="5" style="width:99%" disabled="true" value="${rs.jsbjpj}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					��ע
					<br/>
					<font color="red">(������1000����)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" rows="5" style="width:99%" onkeyup="checkLen(this,1000)" disabled="true" value="${rs.bz}"></html:textarea>
				</td>
			</tr>
			<logic:equal value="xy" name="userType">
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />������
					<br/>
					<font color="red">(������1000����)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_xyyj" styleId="save_xyyj" rows="5" style="width:99%" onkeyup="checkLen(this,1000)" value="${rs.xyyj}"></html:textarea>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<tr>
				<th>
					ѧУ������
					<br/>
					<font color="red">(������1000����)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_xxyj" styleId="save_xxyj" rows="5" style="width:99%" onkeyup="checkLen(this,1000)" value="${rs.xxyj}"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		             <logic:notEqual value="no" name="writable">
					  <button type="button" id="btn_save" onclick="saveinfo('pjpy_zjlg_ylxfbdgsh.do?act=save','')">
						����
					  </button>
					 </logic:notEqual>
					  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						Close();
						window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:notEmpty>
</html>

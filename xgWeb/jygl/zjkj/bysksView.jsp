<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" /></title>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript">
		function checkLqdw(){
			var sfsx = $('sfsx').value;

			if('��' == sfsx){
				$('lqdw').disabled = '';
			}else{
				$('lqdw').value = '';
				$('lqdw').disabled = 'disabled';
			}
		}
	</script>
</head>
<body onload="checkLqdw();">
	<html:form action="jygl.do" method="post">
	<input type="hidden" name="save_mk" value="${mk }"/>
	<input type="hidden" name="mk" value="${mk }"/>
	<input type="hidden" id="url" value="/jygl.do?method=byskssb"/>
	<input type="hidden" name="pkValue" value="${param.pkValue }" />
	
		<div class="tab_cur">
			 <p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>
							<logic:equal value="kg" name="mk">ѧ����������Ա�����Ϣ</logic:equal>
							<logic:equal value="ky" name="mk">ѧ�������о��������Ϣ</logic:equal> 
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">
					<div>
						<font color="red">*</font>ѧ��
					</div>
				</th>
				<td width="30%">
					<html:text property="save_xh" styleId="xh" value="${rs.xh }" readonly="true"/>	
				</td>
				
				<th width="20%">����</th>
				<td width="30%">${rs.xm }</td>
			</tr>
			<tr>
				<th>��ҵ���</th>
				<td>${rs.bynf }</td>
				<th><bean:message key="lable.xb" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
			</tr>
			<logic:equal value="ky" name="mk">
				<tr>
					<th>����ѧУ</th>
					<td><input type="text" name="save_bkmb" id="bkmb" maxlength="30" value="${rs.bkmb}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
					<th>���Է���</th>
					<td><input type="text" name="save_fs" id="fs" maxlength="10" value="${rs.fs}" onkeyup="checkInputNum(this);" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
				</tr>
				<tr>
					<th>�Ƿ�����</th>
					<td>
					<logic:equal value="modi" name="doType">
						<html:select property="save_sfsx" styleId="sfsx" onchange="checkLqdw();" value="${rs.sfsx}">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="modi" name="doType">
						<input type="text" name="save_sfsx" id="sfsx" value="${rs.sfsx}" readonly="readonly"/>
					</logic:notEqual>
					</td>
					<th>�Ƿ������</th>
					<td>
					<logic:equal value="modi" name="doType">
						<html:select property="save_sfxtj" styleId="sfxtj" value="${rs.sfxtj}">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="modi" name="doType">${rs.sfxtj}</logic:notEqual></td>
				</tr>
				<tr>
					<th>¼ȡѧУ</th>
					<td><input type="text" maxlength="30" name="save_lqdw" id="lqdw" value="${rs.lqdw}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
					<th>ѧУ���</th>
					<td>
					<logic:equal value="shone" name="doType">
						<html:select property="save_xxsh" value="${rs.xxsh }">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="shone" name="doType">
						<input type="hidden" name="save_xxsh" value="δ���"/>
						${rs.xxsh }
					</logic:notEqual>	
					</td>
				</tr>
			</logic:equal>
			
			<logic:equal value="kg" name="mk">
				<tr>
					<th>��������</th>
					<td><input type="text" name="save_bklx" id="bklx" maxlength="10" value="${rs.bklx}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
					<th>������λ</th>
					<td><input type="text" name="save_bkmb" id="bkmb" maxlength="30" value="${rs.bkmb}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
				</tr>
				<tr>
					<th>���Է���</th>
					<td><input type="text" name="save_fs" id="fs" maxlength="10" onkeyup="checkInputNum(this);" value="${rs.fs}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
					<th>�Ƿ�����</th>
					<td>
						<logic:equal value="modi" name="doType">
							<html:select property="save_sfsx" styleId="sfsx" onchange="checkLqdw();" value="${rs.sfsx}">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<input type="text" name="save_sfsx" id="sfsx" value="${rs.sfsx}" readonly="readonly"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>¼�õ�λ</th>
					<td><input type="text" name="save_lqdw" id="lqdw" maxlength="30" value="${rs.lqdw}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
					<th>ѧУ���</th>
					<td>
					<logic:equal value="shone" name="doType">
						<html:select property="save_xxsh" value="${rs.xxsh }">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="shone" name="doType">
						<input type="hidden" name="save_xxsh" value="δ���"/>
						${rs.xxsh }
					</logic:notEqual>	
					</td>
				</tr>
			</logic:equal>	
			<tr>
				<th>��ע<br/>
					<font color="red">(������400����)</font>
				</th>
				<td colspan="3">
					<textarea name='save_bz' <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,400)" rows='8'>${rs.bz }</textarea>
				</td>
			</tr>
			</tbody>
			
			<logic:notEqual name="doType" value="view">
			 <tfoot>
			    <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=bysksmodi&doType=save','xh','���ڴ������ݣ����Ժ�');">
							����
						 </button>
						 <button name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</logic:notEqual>
			
			<logic:equal name="doType" value="view">
			 <tfoot>
			    <tr>
			        <td colspan="4">
			          <div class="btn">
						 <button name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</logic:equal>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
			  window.dialogArguments.document.getElementById('search_go').click(); 
			}
		</script>
	</logic:present>
</body>
</html>

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
						<span>��д�����</span>
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
					<button onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);" id="buttonFindStu">
						&gt;&gt;
					</button>
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
					<td><html:text property="save_bkmb" styleId="bkmb" maxlength="30"></html:text></td>
					<th>���Է���</th>
					<td><html:text property="save_fs" styleId="fs" maxlength="10" onkeyup="checkInputNum(this);"></html:text></td>
				</tr>
				<tr>
					<th>�Ƿ�����</th>
					<td><html:select property="save_sfsx" styleId="sfsx" onchange="checkLqdw();">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select></td>
					<th>�Ƿ������</th>
					<td><html:select property="save_sfxtj" styleId="sfxtj">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select></td>
				</tr>
				<tr>
					<th>¼ȡѧУ</th>
					<td><html:text maxlength="30" property="save_lqdw" styleId="lqdw" disabled=""></html:text></td>
					<th></th>
					<td></td>
				</tr>
			</logic:equal>
			
			<logic:equal value="kg" name="mk">
				<tr>
					<th>��������</th>
					<td><html:text property="save_bklx" styleId="bklx" maxlength="10"></html:text></td>
					<th>������λ</th>
					<td><html:text property="save_bkmb" styleId="bkmb" maxlength="30"></html:text></td>
				
				</tr>
				<tr>
					<th>���Է���</th>
					<td><html:text property="save_fs" styleId="fs" maxlength="10" onkeyup="checkInputNum(this);"></html:text></td>
					<th>�Ƿ�����</th>
					<td><html:select property="save_sfsx" styleId="sfsx" onchange="checkLqdw();">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select></td>
				</tr>
				<tr>
					<th>¼�õ�λ</th>
					<td><html:text property="save_lqdw" styleId="lqdw" disabled="" maxlength="30"></html:text></td>
					<th></th>
					<td></td>
				</tr>
			</logic:equal>	
			<tr>
				<th>��ע<br/>
					<font color="red">(������400����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='save_bz'
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,400)" rows='8'/>
				</td>
			</tr>
			</tbody>
			
			 <tfoot>
			    <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=byskssb&doType=save',
							'xh','���ڴ������ݣ����Ժ�');">
							����
						 </button>
						 <button onclick="reset()">����</button>
			          	<button class="button2" onclick="location='jygl_kykgsq.do'">
							����
						</button>
			          </div></td>
			      </tr>
			   </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
	<logic:present name="msg">
		<input type="hidden" id="msg" value="${msg }" />
		<script type="text/javascript">
			alert(document.getElementById('msg').value);
		</script>
	</logic:present>
</body>
</html>

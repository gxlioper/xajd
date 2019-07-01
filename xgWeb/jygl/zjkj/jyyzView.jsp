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
</head>
<body>
	<html:form action="jygl.do" method="post">	
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
						<span>��ҵԮ����Ϣ</span>
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
				<th>������ò</th>
				<td>${rs.zzmmmc }</td>
				<th><bean:message key="lable.xb" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th>�Ƽ����Ե�λ</th>
				<td><input type="text" name="save_tjdw" value="${rs.tjdw}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
				<th>Ԯ�����</th>
				<td>
				<logic:equal value="modi" name="doType">
					<html:select property="save_yzjg" value="${rs.yzjg}">
						<html:option value=""></html:option>
						<html:options collection="yzjgList" property="xmdm" labelProperty="xmmc"/>
					</html:select>
				</logic:equal>
				
				<logic:notEqual value="modi" name="doType">
					${rs.yzjgmc}
				</logic:notEqual>
				</td>
			</tr>
			<tr>
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
				
				<th></th><td></td>
			</tr>
			<tr>
				<th>����ѧ��<br/>
					<font color="red">(������800����)</font>
				</th>
				<td colspan="3">
					<textarea name='save_cyxs' <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,800)" rows='8'>${rs.cyxs}</textarea>
				</td>
			</tr>
			<tr>
				<th>��ע<br/>
					<font color="red">(������400����)</font>
				</th>
				<td colspan="3">
					<textarea name='save_bz' <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,400)" rows='8'>${rs.bz}</textarea>
				</td>
			</tr>
			</tbody>

			 <tfoot>
			    <tr>
			        <td colspan="4"><logic:notEqual value="view" name="doType"><div class="bz">"<span class="red">*</span>"Ϊ������</div></logic:notEqual>
			          <div class="btn">
				        <logic:notEqual value="view" name="doType">
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=jyyzmodi&doType=save',
							'xh','���ڴ������ݣ����Ժ�');">
							����
						 </button>
						</logic:notEqual>
			          	  <button name="�ر�" onclick="window.close();return false;">�ر�</button>
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
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
			  window.dialogArguments.document.getElementById('search_go').click(); 
			}
		</script>
	</logic:present>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ����ά�� - Υ�ʹ��ֽ���</a>
			</p>
		</div>
		
		<html:form action="wjcf_bjqn.do" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue}"/>
		<input type="hidden" name="xh" value="${rs.xh }"/>
		<input type="hidden" name="cfsj" value="${rs.cfsj }"/>
		<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4"><span>ѧ�����ֽ���</span></th>
					</tr>
				</thead>
			
				<tbody>
					<tr>
						<th>ѧ��</th>
						<td>${rs.xh }</td>
						<th>����</th>
						<td>${rs.xm }</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>${rs.xb }</td>
						<th>�꼶</th>
						<td>${rs.nj }</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xb" />����</th>
						<td>${rs.xymc }</td>
						<th>רҵ����</th>
						<td>${rs.zymc}</td>
					</tr>
					<tr>
						<th>�༶����</th>
						<td>${rs.bjmc }</td>
						<th>������ò</th>
						<td>${rs.zzmmmc }</td>
					</tr>
					<tr>
						<th>ԭ�����ĺ�</th>
						<td>${rs.cfwh }</td>
						<th><font color="red">*</font>�����󴦷��ĺ�</th>
						<td><html:text property="cfwh" styleId="cfwh" maxlength="100"></html:text></td>
					</tr>
					<tr>
						<th>����ʱ��</th>
						<td>${rs.cfsj }</td>
						<th><font color="red">*</font>����ʱ��</th>
						<td><html:text property="jjsj" styleId="jjsj" onclick="showCalendar('jjsj','y-mm-dd');" onblur="dateFormatChg(this);"></html:text></td>
					</tr>
					<tr>
						<th>ԭ�������</th>
						<td>${rs.cflbmc }</td>
						<th><font color="red">*</font>�����󴦷����</th>
						<td>
							<logic:notPresent name="isCf">
								<html:select property="cflb">
									<html:option value=""></html:option>
									<html:options collection="cflbList" labelProperty="cflbmc" property="cflbdm"/>
								</html:select>
							</logic:notPresent>
							<logic:present name="isCf">
								��ѧ��������ʹ��ּ��𣬲��ܽ�����
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>����ԭ��</th>
						<td colspan="3">${rs.cfyymc }</td>
					</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave"
			          	onclick="saveDataShowTips('wjcf_bjqn.do?method=wjcfjjSave','jjsj-cfwh-cflb','���ڴ������ݣ����Ժ�')">�� ��</button>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</html:form>
		<logic:present name="isCf">
			<script type="text/javascript">
				$('buttonSave').disabled = true;
				$('jjsj').disabled = true;
				$('cfwh').disabled = true;
			</script>
		</logic:present>
		
		<logic:present name="message">
			<input type="hidden" name="message" value="${message }"/>
			<script>
				alert($('message').value);
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:present>
	</body>
</html>
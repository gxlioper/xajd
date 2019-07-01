<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<title><bean:message key="lable.title" /></title>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
</head>

<body>
	
	<html:form action="/ntzy_pypx.do?method=tytgbsq" method="post">
	<!-- ��ѡ��Ŀ-->
	<input type="hidden" name="mk" value="${mk }"/>
	<input type="hidden" name="save_mk" value="${mk }"/>
	<input type="hidden" name="save_xh" value="${rs.xh }"/>
	<input type="hidden" name="pkValue" value="${param.pkValue }" />
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>
			<logic:equal value="yxty" name="mk">���㹲����Ա�����޸Ĳ鿴</logic:equal>
			<logic:equal value="yxtgb" name="mk">���㹲���Ÿɲ������޸Ĳ鿴</logic:equal>
			</a>
		</p>
	</div>
	
	<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>��д�����</b>
					</td>
				</tr>
			</thead>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="�ύ" 
			          	onclick="saveDataShowTips('ntzy_pypx.do?method=pypxview&doType=save','xh');">�� ��</button>
			          </logic:notEqual>
			           <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			  </tfoot>
			  
			<tbody>  
			<tr>
				<logic:notEqual name="userOnLine" value="student" scope="session">
					<th>
						ѧ�ţ�
					</th>
					<td>
						${rs.xh }
					</td>
				</logic:notEqual>
				
				<th>
					<div >
						ѧ�꣺
					</div>
				</th>
				<td width="30%" align="left">
					<input type="hidden" name="save_xn" value="${rs.xn }"/>
					${rs.xn }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						�Ա�
					</div>
				</th>
				<td>
					${rs.xb }
				</td>
				<th>
					<div >
						�������£�
					</div>
				</th>
				<td>
					<input type="hidden" name="save_xn" value="${xn }"/>
					${rs.csrq }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						ѧ����
					</div>
				</th>
				<td>
					${rs.whcd }
				</td>
				<th>				
					<div >
						����ʱ�䣺
					</div>
				</th>
				<td width="30%">
					${rs.rtrq }
				</td>
			</tr>
			
			<tr>
				<th>
					<div >
						Ժϵ��
					</div>
				</th>
				<td width="30%">
					${rs.xymc }
				</td>
				<th>
					<div >
						�༶��
					</div>
				</th>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			
			<tr>
			<logic:equal value="yxtgb" name="mk">
				<th>
					<div >
						ְ��
					</div>
				</th>
				<td>
					${zw }
				</td>
			</logic:equal>
			</tr>	
		
			
			<tr align="left" style="height:22px">
							<th>
								���˼�����
								<br />
							<font color="red">(������400����)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_grjl' style="width:99%"
									onblur="checkLen(this,800)" rows='8' value="${rs.grjl}"/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<th>
								�������
								<br />
								<font color="red">(������400����)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_hjqk' style="width:99%"
									rows='8' onblur="checkLen(this,800)" value="${rs.hjqk}"/>
							</td>
						</tr>
						
			<tr align="left" style="height:22px">
							<th align="right">
								�¼�˵����
								<br />
								<font color="red">(������1000����)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_sqsm' style="width:99%"
									rows='8' onblur="checkLen(this,800)" value="${rs.sqsm}"/>
							</td>
						</tr>
		
		</tbody></table></div>
		
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

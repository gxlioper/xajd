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
	
	<html:form action="ntzy_pypx.do" method="post">
	<input type="hidden" name="save_ssbm" value="${userDep }"/>
	<input type="hidden" name="save_sqr" value="${userName }"/>
	<logic:equal value="xx" name="userType">
		<input type="hidden" name="save_xysh" value="ͨ��" />
	</logic:equal>
	
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
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
			          <logic:equal name="writeAble" value="yes">
				          <button type="button" class="button2" onclick="saveDataShowTips('ntzy_pypx.do?method=yxtzbsq&doType=save',
							'tzbmc','���ڴ������ݣ����Ժ�');">
							�ύ����
						  </button>
					  </logic:equal>
			          	<button type="button" class="button2" onclick="location='pypxsq.do'">
							��	��
						</button>
			          </div></td>
			      </tr>
			   </tfoot>
			<tbody>
			<tr>
				<th>
					<div>
						<font color="red">*</font>��֧�����ƣ�
					</div>
				</th>
				<td>
					<html:text property="save_tzbmc" styleId="tzbmc"/>
				</td>
				
				<th>
					<div >
						ѧ�꣺
					</div>
				</th>
				<td>
					<input type="hidden" name="save_xn" value="${xn }"/>
					${xn }
				</td>

			</tr>
				<tr>
				<th>
					<div>
						�����ˣ�
					</div>
				</th>
				<td width="30%" align="left">
					${userName }
				</td>
				
				<th>
					<div>
						����Ժϵ����֧��
					</div>
				</th>
				<td colspan="3">
					<html:text property="save_szyxtzz"/>
				</td>
			</tr>	
			<tr>
							<th>
								��Ҫ������
								<br />
							<font color="red">(������400����)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_zygz' style="width:99%"
									onblur="checkLen(this,800)" rows='8'/>
							</td>
			</tr>
			</tbody>
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

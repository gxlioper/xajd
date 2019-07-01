<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	
	<html:form action="ntzy_xyhd.do?method=xyhdsq" method="post">
	
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>
				<logic:equal value="modi" name="operation">�����Ź��� - У԰������޸�</logic:equal>
				<logic:equal value="view" name="operation">�����Ź��� - У԰������鿴</logic:equal>
			</a>
		</p>
	</div>
	
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		
		<div class="div">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��д�����</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>���뵥λ
					</th>
					<td align="left">
						<html:hidden property="save_sqdw" styleId="sqdw" value="${rs.sqdw }" />
						${rs.sqdw }
					</td>
				<th width="20%">
					<div align="right">
						�ܸ�����
					</div>
				</th>
				<td width="30%">
					<html:text property="save_zfzr" styleId="zfzr" value="${rs.zfzr}"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						<font color="red">*</font>��⿪ʼʱ��
					</div>
				</th>
				<td>
					<html:hidden property="save_kssj" styleId="kssj" value="${rs.kssj }" />
					${rs.kssj }
				</td>
				
				<th>
					<div align="right">
						�ص�
					</div>
				</th>
				<td>
					<html:text property="save_dd" styleId="dd" value="${rs.dd }"></html:text>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<div align="right">
						�����
					</div>
				</th>
				<td width="34%">
					<html:text property="save_hdnr" styleId="hdnr" value="${rs.hdnr }"></html:text>
				</td>
				<th>
					<div align="right">
						��������
					</div>
				</th>
				<td>
					<html:text property="save_cyrs" styleId="cyrs" value="${rs.cyrs}" 
					onkeyup="checkInputData(this);" maxlength="5"></html:text>
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						�ֳ�������һ
					</div>
				</th>
				<td>
					<html:text property="save_xcfzr1" styleId="xcfzr1" value="${rs.xcfzr1 }"></html:text>
				</td>
				
				<th>
					<div align="right">
						��ϵ�绰
					</div>
				</th>
				<td>
					<html:text property="save_fzr1dh" styleId="fzr1dh" value="${rs.fzr1dh }" 
					onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			
			<tr>	
				<th>
					<div align="right">
						�ֳ������˶�
					</div>
				</th>
				<td>
					<html:text property="save_xcfzr2" styleId="xcfzr2" value="${rs.xcfzr2 }"></html:text>
				</td>
				
				<th>
					<div align="right">
						��ϵ�绰
					</div>
				</th>
				<td>
					<html:text property="save_fzr2dh" styleId="fzr2dh" value="${rs.fzr2dh }" 
					onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			
				<tr>	
				<th>
					<div align="right">
						<bean:message key="lable.xb" />���
					</div>
				</th>
				<td>
					${rs.xysh }
				</td>
				
				<th>
					<div align="right">
						 ѧУ���
					</div>
				</th>
				<td>
					${rs.xxsh }
				</td>
			</tr>	

			<tr align="left" style="height:22px">
							<th align="right">
								�������Ҫ����
								<br />
							<font color="red">(������1000����)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_hdfa' style="width:99%" value="${rs.hdfa }"
									onblur="checkLen(this,1000)" rows='8'/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<th align="right">
								���뵥λ���
								<br/>
								<font color="red">(������400����)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_sqdwyj' style="width:99%" value="${rs.sqdwyj }"
									rows='7' onblur="checkLen(this,400)"/>
							</td>
						</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <logic:equal name="operation" value="modi">
			          	<button type="button" name="�ύ" onclick="BatAlert.showTips('���ڴ������ݣ����Ժ�');saveData('ntzy_xyhd.do?method=xyhdViewAndModi&doType=save','sqdw-kssj');">����</button>
			         </logic:equal>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
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

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
	<input type="hidden" name="pkValue" value="${param.pkValue }"/>
	
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>��ǰ����λ�ã������Ź��� - У԰��������</a>
		</p>
	</div>
	<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>У԰�������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>���뵥λ
					</th>
					<td align="left">
						<input type="hidden" name="save_sqdw" value="${rs.sqdw }"/>
						${rs.sqdw }
					</td>
				<th width="20%">
					<div align="right">
						�ܸ�����
					</div>
				</th>
				<td width="30%">
						${rs.zfzr }
				</td>

			</tr>
			<tr>
				<th>
					<div align="right">
						<font color="red">*</font>��⿪ʼʱ��
					</div>
				</th>
				<td>
					<input type="hidden" name="save_kssj" value="${rs.kssj }"/>
					${rs.kssj }
				</td>
				
				<th>
					<div align="right">
						�ص�
					</div>
				</th>
				<td>
					${rs.dd }
				</td>
			</tr>
			<tr>
				<th width="16%">
					<div align="right">
						�����
					</div>
				</th>
				<td width="34%">
					${rs.hdnr }
				</td>
				<th>
					<div align="right">
						��������
					</div>
				</th>
				<td>
					${rs.cyrs }
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						�ֳ�������һ
					</div>
				</th>
				<td>
						${rs.xcfzr1 }
				</td>
				
				<th>
					<div align="right">
						��ϵ�绰
					</div>
				</th>
				<td>
						${rs.fzr1dh }
				</td>
			</tr>
			
			<tr>	
				<th>
					<div align="right">
						�ֳ������˶�
					</div>
				</th>
				<td>
					${rs.xcfzr2}
				</td>
				
				<th>
					<div align="right">
						��ϵ�绰
					</div>
				</th>
				<td>
					${rs.fzr2dh }
				</td>
			</tr>		
	
			<tr>	
				<th>
					<div align="right">
						<bean:message key="lable.xb" />���
					</div>
				</th>
				<td>
					<logic:equal value="xy" name="userType">
						<html:select property="save_xysh" value="${rs.xysh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						${rs.xysh }
					</logic:notEqual>
				</td>
				
				<th align="right">
					ѧУ���
				</th>
				<td>
					<logic:equal value="xx" name="userType">
						<html:select property="save_xxsh" value="${rs.xxsh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xx" name="userType">
						${rs.xxsh }
					</logic:notEqual>
				</td>
			</tr>	
			
			<tr align="left" style="height:22px">
							<th align="right">
								�������Ҫ����
								<br />
							<font color="red">(������1000����)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_hdfa' style="width:99%" readonly="true" value="${rs.hdfa}"
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
								<html:textarea property='save_sqdwyj' style="width:99%" readonly="true" value="${rs.sqdwyj}"
									rows='7' onblur="checkLen(this,400)"/>
							</td>
						</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" 
			          		onclick="BatAlert.showTips('���ڴ������ݣ����Ժ�!');saveData('ntzy_xyhd.do?method=xyhdshone&doType=save','sqdw-kssj');">�� ��</button>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
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

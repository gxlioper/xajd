<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("��ѡ��һ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			refreshForm(url+pkValue,w,h);
		}
		
		function del(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ�������õļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
	<body>		
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - У���ڹ���ѧ - ��λ�������</a>
				</p>
			</div>
				
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>

			<logic:notEmpty name="rs">
				<div class="tab">
					<table width="100%" id="rsTable" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����λ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<bean:write name="rs" property="xh"/>
								<html:hidden property="xh" name="rs"/>
							</td>
							<th>ѧ��</th>
							<td>
								<bean:write name="rs" property="xn"/>
								<html:hidden property="xn" name="rs"/>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<bean:write name="rs" property="xm"/>
							</td>
							<th>���</th>
							<td>
								<bean:write name="rs" property="nd"/>
								<html:hidden property="nd" name="rs"/>
							</td>
						</tr>	
						<tr>
							<th>�Ա�</th>
							<td>
								<bean:write name="rs" property="xb"/>
							</td>
							<th>ѧ��</th>
							<td>
								<bean:write name="rs" property="xq"/>
								<html:hidden property="xq" name="rs"/>
							</td>
						</tr>	
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />����</th>
							<td>
								<bean:write name="rs" property="xymc"/>
							</td>
							<th>��λ����</th>
							<td>
								<bean:write name="rs" property="gwmc"/>
								<html:hidden property="gwmc" name="rs"/>
								<html:hidden property="gwsqsj" name="rs"/>
							</td>
						</tr>	
						<tr>
							<th>רҵ����</th>
							<td>
								<bean:write name="rs" property="zymc"/>
							</td>
							<th>�ɲμ��ڹ���ѧʱ��</th>
							<td>
								<bean:write name="rs" property="kcjqgzxsj"/>
							</td>
						</tr>	
						<tr>
							<th>�༶����</th>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<th>��ϵ�绰</th>
							<td>
								<bean:write name="rs" property="lxdh"/>
							</td>
						</tr>	
						<tr>
							<th>��������</th>
							<td colspan="3">
								<bean:write name="rs" property="sqly"/>
							</td>							
						</tr>	
						<tr>
							<th>��ע</th>
							<td colspan="3">
								<bean:write name="rs" property="bz"/>
							</td>							
						</tr>	
						<tr>
							<th>��Ƹʱ��</th>
							<td>
								<html:text property="zpsj" name="rs" maxlength="10"/>
							</td>
							<th>��Ƹ��ַ</th>
							<td>
								<html:text property="zpdz" name="rs" maxlength="32"/>
							</td>
						</tr>	
						<tr>
							<th>���</th>
							<td colspan="3">
								<html:select property="xxsh" name="rs" styleId="yesNo">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn" />
								</html:select>
							</td>						
						</tr>	
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
					            <button type="button" class="button2" id="btn_add"
									onclick="refreshForm('qgzxZgdzdx.do?method=saveXwgwsqsh')"
									style="width:80px">
									�� ��
								</button>
								<button type="button" class="button2" id="btn_edit"
									onclick="Close();return false;"
									style="width:80px">
									�� ��
								</button>			
					          </div>
					        </td>
					      </tr>
					    </tfoot>			
					</table>
				</div>
				<div id="tmpdiv"></div>
			</logic:notEmpty>
			
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
							alert('�����ɹ�!');
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>	
					</logic:equal>
					<logic:equal value="false" name="result">
					<script>
						alert(document.getElementById('mes').value);				
					</script>
				</logic:equal>	
			</logic:present>			
		</html:form>		
	</body>
</html>

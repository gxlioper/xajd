<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		function save(){
			var url = "qgzxZgdzdx.do?method=saveDistribute";
			refreshForm(url);
		}
	</script>
</head>
	<body>
			<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm"/>"/>
			<input type="hidden" name="msg" id="msg" value="${msg }"/>
								
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ��λ��Ϣ���� - <bean:message key="lable.xsgzyxpzxy" />��������</a>
				</p>
			</div>
			 <div class="tab">
				<table width="90%" align="center" class="formlist">
					<thead>							
						<tr>
							<th colspan="2">
								<span>�ڹ���ѧ������������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>ѧ��</th>
						<td>
							<input type="text" name="xn" id="xn" value="${rs.xn}" readonly="readonly"/>
						</td>
					</tr>						
					<tr>
						<th>���</th>
						<td>
							<input type="text" name="nd" id="nd" value="${rs.nd}" readonly="readonly"/>
						</td>
					</tr>						
					<tr>
						<th>ѧ��</th>
						<td>
							<input type="text" name="xqmc" id="xqmc" value="${rs.xqmc}" readonly="readonly"/>
							<input type="hidden" name="xq" id="xq" value="${rs.xq}"/>
						</td>
					</tr>			
					<tr>
						<th>��������</th>
						<td>
							<input type="text" name="xymc" id="xymc" value="${rs.xymc}" readonly="readonly"/>
							<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}"/>
						</td>
					</tr>
					<!--���ݴ�ѧ-->
					<logic:equal value="11078" name="xxdm">
					<tr>
						<th>��λ</th>
						<td>
							<input type="text" name="gwdm" id="gwdm" value="${rs.gwdm}" readonly="readonly"/>
							<input type="hidden" name="gwsbsj" id="gwsbsj" value="${rs.gwsbsj}"/>
						</td>
					</tr>
					</logic:equal>
					<!--end���ݴ�ѧ-->
					<!--������һְҵ����ѧԺ-->
					<logic:equal value="13742" name="xxdm">
					<tr>
						<th>��λ</th>
						<td>
							<input type="text" name="gwdm" id="gwdm" value="${rs.gwdm}" readonly="readonly"/>
							<input type="hidden" name="gwsbsj" id="gwsbsj" value="${rs.gwsbsj}"/>
						</td>
					</tr>
					</logic:equal>
					<!--end������һְҵ����ѧԺ-->
					<tr>
						<th>��������</th>
						<td>
							<input type="text" name="fprs" id="fprs" value="${rs.fprs}" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "/>��
						</td>
					</tr>	
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button"
								onclick="save();return false;">
								�� ��
							</button>
							<button type="button"
								onclick="Close();return false;">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>	
				</table>
			  </div>
				<logic:present name="msg">
					<script>
						alert($("msg").value);
					</script>
				</logic:present>
				<logic:notEmpty name="result">
					<logic:equal name="result" value="true">
						<script>
						alert("����ɹ�!");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
						</script>
						
					</logic:equal>
					<logic:equal name="result" value="false">
						<script>alert("����ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
	</body>
</html>

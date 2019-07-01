<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body onload="checkWinType();">
	<html:form action="/data_search" method="post">		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - �鵵���� - �鵵�����ύ</a>
			</p>
		</div>	

		<div class="tab">
		  <table width="100%" border="0" class="formlist" id="rsT">
			<thead>
				<tr>
					<th colspan="2">						
						<span>ѧ���鵵�����ύ��ϸ��Ϣ</span>						
					</td>
				</tr>
			</thead>
			<tbody>					
			<tr>
				<th>ѧ��</th>
				<td>
					${rs.xh}
					<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
				</td>
				<tr>
					<th>����</th>
					<td>
						${rs.xm}
					</td>
				</tr>
				<tr>
				<th>�Ա�</th>
				<td>
					${rs.xb}
				</td>
			 </tr>		
			<tr>
				<th>�꼶</th>
				<td>
					${rs.nj}							
				</td>
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>
					${rs.xz}
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc}
				</td>
			</tr>			
			<tr>					
				<th>רҵ����</th>
				<td>
					${rs.zymc}
				</td>
			</tr>
			<tr>
				<th>�༶����</th>
				<td>
					${rs.bjmc}
				</td>
			</tr>	
			<logic:equal value="11407" name="xxdm">
			<tr>
				<td colspan="2">
				<table  style="width:100%" class="formlist">
					<thead>
						<tr><th colspan="4"><span>��ѧ����</span></th></tr>
					</thead>
					<tbody>
					<tr>
						<th>��Դ��</th>
						<td>
							${rs.syd}
						</td>
						<th>����</th>
						<td>
							${rs.mzmc}
						</td>
					</tr>
					<tr>
						<th>�������</th>
						<td colspan="3">
							${rs.daqk}
						</td>
					</tr> 
					<tr>
						<th>��ע</th>
						<td colspan="3">
							${rs.bz}
						</td>
					</tr>
				</tbody>
			</table>
			</td>
			</tr>
			<tr>
			<td colspan="2">
				<table  style="width:100%" class="formlist">
					<thead>
						<tr><th colspan="2"><span>��У��������</span></th></tr>
					</thead>
					<tbody>
					<logic:notEmpty name="zlList">
					<tr>
						<th bgcolor="#CCCCCC">��������</th>
						<th bgcolor="#CCCCCC">��������</th>
					</tr>
					<logic:iterate id="zl" name="zlList">
						<tr>
							<logic:iterate id="v" name="zl">
							<td>
								<center><bean:write name="v"/></center>
							</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					</tbody>
				</table>
			</td>
			</tr>

			<tr>
			<td colspan="2">
				<table  style="width:100%" class="formlist">
					<thead>
						<tr><th colspan="2"><span>��ҵ����</span></th></tr>
					</thead>
					<tbody>
					<tr>
						<th>��ǲ��λ</th>
						<td>
							${rs.pqdwmc}
						</td>
					</tr>
					<tr>
						<th>����ʱ��</th>
						<td>
							${rs.ffsj}
						</td>
					</tr>
					<tr>
						<th>��ǲ֤���</th>
						<td>
							${rs.pqzbh}
						</td>
					</tr> 
					<tr>
						<th>ȡ��������</th>
						<td>
							${rs.qdrxm}
						</td>
					</tr> 
					<tr>
						<th>��ע</th>
						<td>
							${rs.bz}
						</td>
					</tr>
				</tbody>
				</table>
			</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="11407" name="xxdm">
			<tr>
				<th>�Ѿ��ύ������</th>
				<td>
					<span class="red">${rs.ytjzlmc}</span> 
				</td>
			</tr>
			<tr>
				<th>δ�ύ������</th>
				<td>
					<span class="red">${rs.wtjzlmc}</span>	
				</td>
			</tr>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
		           <button onclick="Close();return false;"
						style="width:80px" 
						id="buttonSave">
						ȷ ��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>	
				alert("�����ɹ���");
				Close();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>	
					alert("����ʧ�ܣ�");
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
	</body>
</html>


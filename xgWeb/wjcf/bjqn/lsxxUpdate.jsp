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
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ����ά�� - ���ֽ�����ʷ��Ϣ�����鿴</a>
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
						<th colspan="4"><span>ѧ��������Ϣ</span></th>
					</tr>
				</thead>
			
				<tbody>
					<tr>
						<th width="15%">ѧ��</th>
						<td width="35%">${rs.xh }</td>
						<th width="15%">���</th>
						<td width="35%">${rs.nd }</td>
					</tr>
					<tr>
						<th>����</th>
						<td>${rs.xm }</td>
						<th>ѧ��</th>
						<td>${rs.xn }</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>${rs.xb }</td>
						<th>�꼶</th>
						<td>${rs.nj }</td>
					</tr>
					<tr>
						<th>������ò</th>
						<td>${rs.zzmmmc }</td>
						<th>ѧ��</th>
						<td>${rs.xqmc }</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>${rs.nj }</td>
						<th>�������</th>
						<td>${rs.cflbmc }</td>
					</tr>
					<tr>
						<th>ϵ</th>
						<td>${rs.xymc }</td>
						<th>��������</th>
						<td>${rs.cfyymc}</td>
					</tr>
					<tr>
						<th>רҵ����</th>
						<td>${rs.zymc}</td>
						<th>����ʱ��</th>
						<td>${rs.cfsj}</td>
					</tr>
					<tr>
						<th>�༶����</th>
						<td>${rs.bjmc }</td>
						<th>�����ĺ�</th>
						<td>${rs.cfwh }</td>
					</tr>
					<tr>
						<th>���ֽ���ʱ��</th>
						<td>${rs.jjsj }</td>
					</tr>
					
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</html:form>
		
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
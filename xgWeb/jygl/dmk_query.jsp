<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />
<jsp:directive.page import="xgxt.jygl.form.Dmk" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	        function yz(){
			var sjzd = document.from.sjzd.value;
			if(sjzd == ""){
				alert("����ѡ��Ҫ��ѯ�Ĵ����!");
			}else{
				document.from.submit();
			}
		}
	    </script>
	</head>
	<body>
		<form action="/xgxt/dmkquery.do" method="post" name="from">
		<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<td colspan="6">
						<span>������ѯ��</span>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th colspan="1">
						��ѡ��Ҫ��ѯ�Ĵ����
					</th>
					<td colspan="3">
						<select id="sjzd" name="sjzd">
							<option value="">
								-------��ѡ��-------
							</option>
							<option value="bzgzzy">
								��ר��ְרҵ����
							</option>
							<option value="yjszy">
								�о���רҵ����
							</option>
							<option value="xb">
								�Ա����
							</option>
							<option value="xl">
								ѧ������
							</option>
							<option value="sydq">
								��Դ��������
							</option>
							<option value="bzgzpyfs">
								��ר������ʽ����
							</option>
							<option value="yjspyfs">
								�о���������ʽ����
							</option>
							<option value="zzmm">
								������ò����
							</option>
							<option value="dwdq">
								��λ��������
							</option>
							<option value="byqx">
								��ҵȥ�����
							</option>
							<option value="jzzbz">
								��ס֤�������־λ
							</option>
							<option value="sydzgdw">
								��Դ�����ܲ��Ŵ���
							</option>
							<option value="dwxz">
								��λ���ʴ���
							</option>
							<option value="zgbm">
								���ܲ��Ŵ���
							</option>
							<option value="dwxz2">
								��λ���ʴ���2
							</option>
							<option value="dqlx">
								�����������
							</option>
							<option value="hyfl">
								��ҵ�������
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>

						����
						<input type="text" value="" name="mc" />
					</td>
					<td>
						����
						<input type="text" value="" name="dm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="radio" name="myselect" value="1"/>
						&nbsp;��ȷ��ѯ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="myselect" value="2" checked/>
						&nbsp;ģ����ѯ
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button  onclick=yz()
							 value="��  ѯ" >��  ѯ</button>
						<button value="��  ��"  type="reset"
							>��  ��</button>
					</td>
				</tr>
			</table>
		</form>
		<div class="formbox">
				<h3 class="datetitle_01">
					<font color="blue">ע������ѯ����Ϊ��ʱ����ѯ���Ϊ�ô�����������ݡ�</font>
				</h3>
				<table summary="" class="dateline" align="" id="rsTable" width="100%">
				<thead>
					<%
					if (request.getAttribute("dmkmc") != null) {
					%>
					<tr>
						<td colspan="2" align="center">
							<div align="center">
								<font color="blue"><b><%=request.getAttribute("dmkmc")%></b></font>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 ��¼����<%=request.getAttribute("rsNum")%>
							</div>
						</td>
					</tr>
					<%
					}
					%>
					<tr>
						<th align="center">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</th>
						<th align="center">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</th>
					</tr>
				</thead>
				<tbody>
			<%
			if (request.getAttribute("list") != null) {
			%>
			<%
			ArrayList arrayList = (ArrayList) request.getAttribute("list");
			%>
			<%
						for (Iterator it = arrayList.iterator(); it.hasNext();) {
						Dmk dmk = (Dmk) it.next();
			%>
			<tr>
				<td align="center">
					<%=dmk.getDmkdm()%>
				</td>
				<td align="center">
					<%=dmk.getDmkmc()%>
				</td>
			</tr>
			<%
			}
			%>
			<%
			}
			%>
			</tbody>
		</table>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
        function yz(){
       		var xxfkdm = document.getElementById("xxfkdm").value;
       		if((xxfkdm == null) || (xxfkdm=="")){
       			alert("����ѡ��Ҫ��������Ϣ����!");
       			return false;
       		}
			document.forms[0].action = "/xgxt/szdw_zjlg.do?method=xxFkQuery";
		 	document.forms[0].submit();
	}
    </script>
	</head>
	<body>
		<html:form action="/szdw_zjlg" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><span><a>˼������ - ��Ϣ���� - ��Ϣ��ѯ</a></span>
				</p>
			</div>
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr valign="middle" >
						<td align="center">
							<span>��ѡ��Ҫ��������Ϣ����</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							<html:select property="xxfkdm" styleId="xxfkdm">
									<html:option value="">---------��ѡ��--------</html:option>
									<html:option value="pxxxfk">��ѵ��Ϣ����</html:option>
									<html:option value="gzxxfk">������Ϣ����</html:option>
								</html:select>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						<button type="button" onclick="yz()">ȷ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>

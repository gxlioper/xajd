<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/js/function.js"></script>
		<script language="javascript">
	        function yz(){
	       		var temp = document.getElementById("dyym").value;
	       		var url = "stuArchives.do?method=distributeAppPage";
	       		if((temp == null) || (temp=="")){
	       			alert("请先选择要申请的项目!");
	       			return false;
	       		}
	       		url += "&dyym=";
	       		url += temp;
	       		refreshForm(url);
			}
	    </script>	
	</head>
	<body >
			<html:form action="/stuArchives.do" method="post">
				<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr valign="middle" >
						<th align="center">
							<b style="font-size:14">请选择要申请的项目</b>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td align="center">
						<html:select property="dyym" styleId="dyym">
								<html:option value="">--------请选择--------</html:option>
								<html:options collection="List" property="dyym"
									labelProperty="gnmc" />
							</html:select>
					</td>
				</tr>
				<tbody>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<button type="button"  onclick=yz()
							 value="确  定" >确  定</button>		           
			          </div>
			          </td>
			      </tr>
			    </tfoot>
			</table>
				
			</html:form>
	</body>
</html>

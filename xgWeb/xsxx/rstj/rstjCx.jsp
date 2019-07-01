<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function dc(){
		var dclx=document.getElementById("dclx").value;
		if(dclx==""){
			alert("请选择要导出的报表类型");
			return false;
		}
		var url="rstj.do?method=rstjDc&doType="+dclx;
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
    </script>
    </head>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>


	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<form action="/xsxx_rstjcx" method="post" name="from">
			<div class="tab">
				<table width="70%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>请选择您要统计的报表类型</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="导出" onclick="dc()">
										导 出
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="40%">
								报表类型
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<select id="dclx">
										<option value="">---------请选择--------</option>
										<option value="xqxynj">校区<bean:message key="lable.xb" />年级</option>
										<option value="ssccxbmc">省市层次性别民族</option>
										<option value="xyzynjxb"><bean:message key="lable.xb" />专业年级性别</option>
										<option value="xyzynj"><bean:message key="lable.xb" />专业年级</option>
									</select>
						         </div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</body>
</html>

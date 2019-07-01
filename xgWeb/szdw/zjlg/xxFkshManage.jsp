<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
        function yz(){
       		var xxfkdm = document.getElementById("xxfkdm").value;
       		if((xxfkdm ==null)||(xxfkdm=="")){
       			alert("请先选择要反馈的信息种类!");
       			return false;
       		}
			document.forms[0].action = "/xgxt/szdw_zjlg.do?method=xxFkSh";
		 	document.forms[0].submit();
	}
    </script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>思政队伍 - 信息反馈 - 信息审核</a>
			</p>
		</div>
		<html:form action="/szdw_zjlg" method="post">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th>
							<span>请选择要反馈的信息类型</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td align="center">
						<html:select property="xxfkdm" styleId="xxfkdm">
							<html:option value="">---------请选择--------</html:option>
							<html:option value="pxxxfk">培训信息反馈</html:option>
							<html:option value="gzxxfk">工作信息反馈</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						<button type="button" onclick="yz()">确定</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
	</body>
</html>

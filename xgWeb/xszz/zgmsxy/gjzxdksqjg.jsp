<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript">
     function result_query(){
      		var temp = document.getElementById("gnmkbz").value;
      		if(temp == null){
      			alert("请先选择要查询的项目!");
      		}else{
			document.forms[0].action = "zgmsxy_xszz.do?method=gjzxdksqjg&doType=query";
	 		document.forms[0].submit();
		}
	}

function chkAssisOne() {
	if (curr_row == null) {
		return false;
	} else {
		url = curr_row.getElementsByTagName("input")[0].value;
		if (url==""){
			return false;
		}
		url = url.replace("&amp;","&");
		showTopWin(url, 750, 550);
		return true;
	}
}
    </script>
</head>
	<body>
		
		<form action="#" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>助学贷款 - 申请 - 申请结果查询</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th>
							<span>请选择要查询的项目</span>
						</th>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:select name="rs1" property="gnmkbz" styleId="gnmkbz">
							<html:option value="">---------请选择--------</html:option>
							<html:option value="1">国家助学贷款</html:option>
							<html:option value="2">毕业生信息</html:option>
						</html:select>
					</td>
				</tr>
		
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          	<button type="button" onclick="result_query()" value="确定">确定</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</form>
	</body>
</html>


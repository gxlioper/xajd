<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
        function yz(){
       		var temp = document.getElementById("gnmk").value;
       		if((temp == null) || (temp=="")){
       			alert("����ѡ��Ҫ�������Ŀ!");
       			return false;
       		}
			document.forms[0].action = "/xgxt/xszz_xzcd_open.do?cdlb=sqcd&doType=query";
		 	document.forms[0].submit();
	}
    </script>
	</head>
	<body>
		<form action="" method="post" name="from">
			<table width="99%"  border="0" class="formlist">
			<thead>
   				<tr>
       				<th colspan="4"><span>��ѡ��Ҫ�������Ŀ</span></th>
       			</tr>
  			</thead>
			<tbody>
				<tr>
					<td align="center">
						<html:select name="rs" property="gnmk" styleId="gnmk">
								<html:option value="">---------��ѡ��--------</html:option>
								<html:options collection="list" property="gnmklj"
									labelProperty="gnmkmc" />
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<button class="button2" onclick="yz()"
							style="width:80px" >ȷ ��</button>
					</td>
				</tr>
			</tbody>
			</table>
		</form>
	</body>
</html>

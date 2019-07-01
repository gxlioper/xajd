<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	
	<script type="text/javascript"> 
		function sqXm(){
			var path = $('sqxm').value;
			if(path == ""){
				alert('请选择具体项目！');
			}else{
				location = path;
			}
		}
	
	</script>
</head>
<body>
	<html:form action="ntzy_pypx.do" method="post">
	<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
	</div>
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<td colspan="4" align="center">
						<b>选择项目</b>
					</td>
				</tr>
			</thead>
			
			<tr align="center">
				<td>
					<html:select property="sqxm" styleId="sqxm">
						<html:option value="">------请选择------</html:option>
						<html:options collection="xmsqList" property="en" 
						labelProperty="cn"/>						
					</html:select>
				</td>
			</tr>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"></div>
			          <div class="btn">
				       	<button type="button" class="button2" onclick="sqXm();">
							确	定
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>	
	</html:form>
</body>
</html>

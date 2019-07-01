<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
	function jzxjZsPrint() {
	    var date = $("zqsj").value;
	    if(date==""){
	      alert("颁证日期不能为空！");
	      return false;
	    }
        var url = "/xgxt/zgdzdx_xszz.do?method=zsPrint";
		url += "&pkValue=";
		url += $("pkValue").value;
		url += "&xmdm=";
		url +=  $("xmdm").value;
		url +="&bzrq="+$("zqsj").value;	
		window.open(url, 1000, 1000);
		Close();		
	}

</script>
</head>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>		
		<script language="javascript">

		</script>
		<html:form action="/zgdzdx_xszz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>颁证日期设置</a>
				</p>
			</div>
			<input type="hidden" name="pkValue" value="${pkValue}"/>
			<input type="hidden" name="xmdm" value="${xmdm}"/>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          		<button type="button" name="提交" onclick="jzxjZsPrint() ">提 交</button>
			          </div></td>
			      </tr>
			    </tfoot>
				<tbody>
					<tr>
						<th align="right">
							颁证日期：
						</th>
						<td align="left">
							<html:text property="zqsj" styleId="zqsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('zqsj','y-mm-dd','aa');"
								readonly="true" />
						</td>
					</tr>
					</tbody>
				</table>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>

</html>

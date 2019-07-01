<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body onload="document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function dataCl(){
			var jjrq = document.getElementById('jjrq').value;
			re = /-/gi;
			document.getElementById('jjrq').value = jjrq.replace(re,"");
		}
		function toPrintOut(){//输出相应的打印页面
			var jjrq = document.getElementById('jjrq').value;
			var pkVal = document.getElementById('pkVal').value;
			var qs = document.getElementById('qs').value;
			
			if (null == jjrq || jjrq == ""){
				alert("请提填写需要打印借据的日期！");
				return false;
			}
			var url = "/xgxt/zgdzdx_xszz.do?method=gjzxdkjjdy";
			url += "&pkVal="+pkVal;
			url += "&qs="+qs;
			url += "&jjrq="+jjrq;
			window.open(url, 1000, 1000);
			return true;
		}
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkjjsz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>助学贷款 - 审核 - 国家助学贷款借据打印</a>
				</p>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="2" align="center">
						</td>
					</tr>
				</thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="提交"onClick="toPrintOut();">打 印</button>
			          </logic:notEqual>
			            <button type="button" name="关闭"  onclick="Close();return false;"  id="buttonClose">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr>
					<th align="center" width="50%">
						期数：
					</th>
					<td width="50%">
						<html:select name="rs" property="qs" style="width:100%;heigh:100%">
							<html:option value="1">第一期</html:option>
							<html:option value="2">第二期</html:option>
							<html:option value="3">第三期</html:option>
							<html:option value="4">第四期</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
							<span class="red">*</span>借据日期：
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%" onblur="dataCl();"
								onclick="return showCalendar('jjrq','y-mm-dd');"
								value="<bean:write name='rs' property="jjrq" />" name="jjrq"
								id="jjrq" />
					</td>
				</tr>
				</tbody>
			</table>
		</html:form>
	</body>
</html>

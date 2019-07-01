<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function cpwjDaSave(){
			saveData('bjlh_fdycpwj.do?method=cpwjglView&doType=save','');
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<html:hidden property="wjid" name="rs" styleId="wjid"/>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>测评问卷统计</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td>${rs.wjmc}</td>
			</tr>

<%--			<tr>--%>
<%--			<td>学院，辅导员姓名</td>--%>
<%--			</tr>--%>
			<logic:present name="stList">
			<logic:iterate id="st" name="stList">
				<tr>
					<td colspan="2">
						${st.r}、${st.stmc}(答题人数：${st.dtrs }人)
					<br/><br/>
						${st.xxHtml }
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}",function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
			});
		</script>
	</logic:present>
</body>
</html>

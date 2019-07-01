<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((kssj == null) || (kssj == "")){
				alert("申请开始时间不能为空!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("申请结束时间不能为空!");
				return false;
			}
			if (kssj > jssj){
				alert("申请开始时间不能大于申请结束时间！");
				return false;
			}
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=zzsjPlsz&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>当前所在位置：学生资助 - 基础数据维护 - 时间批量设置</a></p>
		</div>
	<html:form action="/nblg_xszz.do?method=zzsjPlsz" method="post">

		<input type="hidden" id="pkDel" name="pkDel"
			value="<bean:write name='rs' property='pkDel'/>" />
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("设置完成！");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
			<table width="99%"  border="0" class="formlist">
			 <thead>
   				<tr>
       				<th colspan="4"><span>时间维护</span></th>
       			</tr>
  			</thead>
			<tbody>
			<tr>
				<td width="50%">
					<span class="red">*</span>申请开始时间
				</td>
				<td width="50%">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<td>
					<span class="red">*</span>申请结束时间
				</td>
				<td>
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	<button type="button" id="buttonSave" onclick="yz();">
						保&nbsp;存
					</button>
					<button type="button" id="buttonClose"
						onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关&nbsp;闭
					</button>					           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
</body>
</html>

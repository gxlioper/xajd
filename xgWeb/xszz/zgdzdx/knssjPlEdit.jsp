<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
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
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knssjPlsz&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>困难生 - 基础数据维护 - 困难生时间批量设置</a>
				</p>
			</div>
	<html:form action="/zgdzdx_xszz.do?method=knssjPlsz" method="post">

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
			<table width="100%"  border="0" class="formlist">
			<thead>
				<tr>
    				<th colspan="6"><span>困难生时间维护</span></th>
    			</tr>
			 </thead>
			 <tbody>
			<tr>
				<th width="50%">
						 开始时间
				</td>
				<td align="center" width="50%">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<th>
						结束时间
				</th>
				<td align="center">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
			</tbody>
			<tfoot>
				 <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<button type="button" name="保存" onClick="yz();" id="buttonSave">
							保 存
						</button>
						<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>           
			          </div>
			          </td>
			      </tr>
			</tfoot>
		</table>
	</html:form>
</body>
</html>

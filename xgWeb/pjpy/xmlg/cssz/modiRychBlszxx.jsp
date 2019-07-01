<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript">
<!--
		//控制对于页面只有一个文本框,回车提交事件 重写onkeydown事件,注意会覆盖所有的回车事件
		function document.onkeydown() {     
			  var e=event.srcElement;     
			  if(event.keyCode==13) {
				  	return false;     
			  }     
		}
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置:评奖评优 - 参数设置 - 荣誉称号比例设置
			</div>
		</div>
		<table style="width:100%" class="tbstyle">
			<thead>
				<tr>
					<td colspan="2" align="center">
						修改设置比例
					</td>
				</tr>
			</thead>
			<tr style="width:22px">
				<td align="right" width="50%">
					学年：
				</td>
				<td align="left">
					${rs.xn }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					荣誉称号：
				</td>
				<td align="left">
					${rs.mc }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					部门：
				</td>
				<td align="left">
					${rs.bmmc }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					${rs.nj }
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					部门人数：
				</td>
				<td align="left">
					${rs.bmrs }
				</td>
			</tr>

			<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>获奖比例：
				</td>
				<td align="left">
					<html:text property="bl" styleId="bl" style="width:90px" onkeyup="ckinpdata(this)" maxlength="4"></html:text>
					<font color="red">% (键入1-100之间的数字)</font>
				</td>
			</tr>
						<tr style="width:22px">
				<td align="right">
					调整人数：
				</td>
				<td align="left">
					${rs.tzrs } (人)
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('pjpy_xmlg_modiRychBlszxx.do?operType=save','bl')"
					style="width:80px">
					保 存
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

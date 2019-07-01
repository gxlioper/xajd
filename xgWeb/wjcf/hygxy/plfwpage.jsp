<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
<base target="_self">
<body >
	<html:form action="/wjcfhygxywh.do" method="post">
			<input type="hidden" name="pkVal" id="pkVal" value="${pkVal }"/> 
		<div class="title">
			<div class="title_img" id="title_m">
				${tips }
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						批量发文
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>处分文号：
				</td>
				<td align="left">
					<html:text property="cfwh" styleId="cfwh"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>处分时间：
				</td>
				<td align="left">
					<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" ></html:text>
				</td>
			</tr>

		</table>
		<div class="buttontool" align="center">
						<button type="button" class="button2" id="btn_save" 
							onclick="saveinfo('wjcf_hhgxy_plfw.do?act=save','cfsj-cfwh');"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
		</div>
		<!-- 保存提示信息 -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
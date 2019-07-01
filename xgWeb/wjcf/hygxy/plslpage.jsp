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
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/> 
		<div class="title">
			<div class="title_img" id="title_m">
				${tips }
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="2" align="center">
						批量受理
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="25%">
					委员会受理：
				</td>
				<td align="left">
					<html:select  property="wyhsl" 
									styleId="wyhsl">
									<html:option value="受理">受理</html:option>
									<html:option value="不受理">不受理</html:option>
								</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					受理内容：
				</td>
				<td align="left">
					<html:textarea property="wyhsllr" styleId="wyhsllr"  rows="7" style="width:95%"></html:textarea>
				</td>
			</tr>

		</table>
		<div class="buttontool" align="center">
						<button type="button" class="button2" id="btn_save" 
							onclick="refreshForm('wjcf_hygxy_plsl.do?act=save');BatAlert.showTips('正在操作，请等待...');"
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
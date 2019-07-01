<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<body>
	<html:form action="/wjcfxmlgwh" method="post">
		<input type="hidden" name="pkString" id="pkString" value="${pkString }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 当前位置：违纪处分 - 解除留校察看 - 审核
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
			
				<tr style="height:23px">
					<td colspan="2" align="center">
						批量审核
					</td>
				</tr>
			</thead>
			<tr>
			<td align="right">
					解除时间：
				</td>
				<td align="left" colspan="">
					<html:text property="jcsj" styleId="jcsj" readonly="true" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');"></html:text>
				</td>
			</tr>
			<tr>
			<td align="right">
					解除文号：
				</td>
				<td align="left">
					<html:text property="jcwh" styleId="jcwh"></html:text>
				</td>
			</tr>
			<tr>
			<td align="right">
					处理结果：
				</td>
				<td align="left">
					<html:select property="jcjg" styleId="jcjg">
						<html:option value=""></html:option>
						<html:options collection="jcList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right" style="width:30%">
					审核：
				</td>
				<td align="left" width="70%">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					审核意见：
				</td>
				<td align="left">
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_xmlg_xbplshLxckxx.do?operType=save','');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						关 闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

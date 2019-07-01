<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前位置:评奖评优 - 数据维护 - 综合素质测评 
       		</div>
    	</div>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="2" align="center">
							设置综合素质测评排名方式
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>综合素质测<br/>评排名方式
					</td>
					<td align="left">
						<html:select property="pmfs" styleId="pmfs" style="width:90px">
							<html:option value=""></html:option>
							<html:option value="zydm" >专业</html:option>
							<html:option value="bjdm" >班级</html:option>
						</html:select>
				</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual value="no" name="writable">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_xmlg_szZhszcppm.do?operType=save','pmfs');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
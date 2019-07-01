<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="js/xszzFunction.js"></script>
<script type="text/javascript">
	function getXscj(obj){
		var pk = obj.getElementsByTagName("input")[0].value;
		var dm = obj.getElementsByTagName("input")[1].value;
		var lb = obj.getElementsByTagName("input")[2].value;
		showTopWin('/xgxt/pjpy_ycsf_viewxskccj.do?pk='+pk+'&dm='+dm+'&lb='+lb,700,600);
	}
	</script>
<body>
	<html:form action="pjpyycsfwh.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 信息维护 - 审核结果查询
			</div>
		</div>
		<fieldset >
			<div align="center">
			<table width="100%"  class="tbstyle" >
				<thead>
					<tr height="40">
						<td colspan="5">
							学号：<font color="red">${userName}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名：<font color="red">${userNameReal}</font>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<font color="red">奖学金情况</font>
						</td>
					</tr>
					<tr align="center">
						<td>学年</td><td>学期</td><td>奖学金名称</td><td>奖励金额</td><td>学校审核</td>
					</tr>
				</thead>	
				<logic:iterate id="s" name="jxjrs">
					<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center" ondblclick="getXscj(this)">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="3">
								<input type="hidden" value="<bean:write name="v"/>"/>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="3" length="1">
								<bean:write name="v"/>
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="4" length="4">
							<td align="center">
								<bean:write name="v"/>
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>			
			</table>
			<br>
			<table width="100%"  class="tbstyle" >
				<thead>
					<tr>
						<td colspan="3" align="center">
							<font color="red">荣誉称号情况</font>
						</td>
					</tr>
					<tr align="center">
						<td>学年</td><td>荣誉称号名称</td><td>学校审核</td>
					</tr>
				</thead>	
				<logic:iterate id="s" name="rychrs">
					<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center" ondblclick="getXscj(this)">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="3">
								<input type="hidden" value="<bean:write name="v"/>"/>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="3" length="1">
								<bean:write name="v"/>
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="4" length="2">
							<td align="center">
								<bean:write name="v"/>
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>			
			</table>
			</div>
		</fieldset>
		</html:form>
	</body>

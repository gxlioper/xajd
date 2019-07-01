<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="/czxxPjpyCssz" method="post">

		<script>
		</script>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 参数设置 - 评奖评优开关设置
			</div>
		</div>
		<fieldset >
			<br><br>
			<div align="center">
			<table width="95%"  class="tbstyle" >
				<thead>
					<tr height="35">
						<td align="right" width="50%">
							奖学金申请开关：
						</td>
						<td width="50%">
							<html:radio property="jxjsq" value="1" >开</html:radio>
							<html:radio property="jxjsq" value="0" >关</html:radio>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							奖学金审核开关：
						</td>
						<td>
							<html:radio property="jxjsh" value="1" >开</html:radio>
							<html:radio property="jxjsh" value="0" >关</html:radio>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							荣誉称号申请开关：
						</td>
						<td>
							<html:radio property="rychsq" value="1" >开</html:radio>
							<html:radio property="rychsq" value="0" >关</html:radio>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							荣誉称号审核开关：
						</td>
						<td>
							<html:radio property="rychsh" value="1" >开</html:radio>
							<html:radio property="rychsh" value="0" >关</html:radio>
						</td>
					</tr>
				</thead>
				<logic:equal value="yes" name="writeAble">
				<tr height="35">
					<td align="center" colspan="2">
						<button type="button" class="button2" onclick="refreshForm('pjpy_czxx_kgsz.do?act=save')"
							style="width: 60px">
							保 存
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="refreshForm('pjpy_czxx_kgsz.do')"
							style="width: 60px">
							重 置 
						</button>
					</td>
				</tr>
				</logic:equal>
			</table>
			</div>
		</fieldset>
		<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert('保存成功!');
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert('保存失败!');
			</script>
		</logic:equal>
		</logic:present>
		</html:form>
	</body>

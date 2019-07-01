<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<body onload="checkWinType();">
	<html:form action="/pjpyzgkdwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 信息维护 - 素质测评排名方式设置
       		</div>
    	</div>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="2" align="center">
							排名方式设置
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right" width="50%">
						素质分排名方式：
					</td>
					<td align="left" width="50%">
						专业<html:radio property="pmfs" value="zy"></html:radio>&nbsp;
						班级<html:radio property="pmfs" value="bj"></html:radio>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="refreshForm('pjpy_zgkd_pmfssz.do?act=save');document.getElementById('btn_save').disabled=true"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("操作成功!");
					Close();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("操作失败,未选择任何数据!");
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
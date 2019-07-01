<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>

<script language="javascript">
function cjxzSave(url){
		if($("zdcxcj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("成绩格式必需为数字,请检查!");
			return false;
		}
		if($("zdcxcj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("成绩格式必需为数字,请检查!");
			return false;
		}
		if($("zdzcj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("成绩格式必需为数字,请检查!");
			return false;
		}
	document.forms[0].action = url;
	document.forms[0].submit();
}
</script>
<body>
	<html:form action="/pjpyxcxywh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 参数设置 - 奖学金条件设置
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="search_go"
			onclick="refreshForm('pjpy_nblg_rychtjsz.do');" />
		<input type="hidden" id="tname" name="tname" value="${tname }" />
		<fieldset>
			<legend>
				奖学金选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							&nbsp;&nbsp; 奖学金名称:
							<html:select property="jxjdm" styleId="jxjdm"
								onchange="refreshForm('pjpyxcxywh.do?method=tjsz');"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<div id="result">
			<div class="searchcontent">
				<logic:notEmpty name="rs">
					<fieldset>
						<table width="99%" id="rsTable" class="tbstyle">
							
						<tr style="height:22px"> 
							<td align="right">
								最低平均成绩
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="zdpjcj" styleId="zdpjcj" />
							</td>
						</tr>
						<tr style="height:22px"> 
							<td align="right">
								最低操行成绩
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="zdcxcj" styleId="zdcxcj" />
							</td>
						</tr>
						<tr style="height:22px"> 
							<td align="right">
								最低总成绩
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="zdzcj" styleId="zdzcj" />
							</td>
						</tr>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
			<div class="buttontool" align="center" id="btn"
				style="position:absolute;width:95%;top:100px">
				<button class="button2" id="btn_tg" style="width:80px"
					onclick="cjxzSave('pjpyxcxywh.do?method=tjszSave');">
					修改
				</button>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="update">
		<logic:equal value="yes" name="update">
			<script>
	 			alert('操作成功！');
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="update">
			<script>
	 			alert('操作失败!');
	 		</script>
		</logic:equal>
	</logic:present>
</body>

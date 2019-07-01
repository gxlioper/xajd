<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
<script type="text/javascript">
<!--
	function countFs() {
		var xycpf = document.getElementById('xycpf').value;
		var xwbxf = document.getElementById('xwbxf').value;
		var tcbxf = document.getElementById('tcbxf').value;
		gzdxpjpy.countBxfZf(xycpf,xwbxf,tcbxf,function(data) {
			if (data != null) {
				document.getElementById("zhbxf").value=data[0];
				document.getElementById("zf").value=data[1];
			}
		});
	}	
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/gzdxPjpy" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前位置:评奖评优 - 综测信息维护 - 综合素质测评 
       		</div>
    	</div>
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/pjpy_gzdx_addZhszcpxx.do" />
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个增加
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td align="left">

					<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>

				</td>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
						学业测评分：
					</td>
					<td align="left">
						<html:text property="xycpf" styleId="xycpf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
					
				</tr>
				<tr style="width:22px">
					<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<td align="right">
						行为测评分：
					</td>
					<td align="left">
						<html:text property="xwbxf" styleId="xwbxf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<td align="right">
						突出测评分：
					</td>
					<td align="left">
						<html:text property="tcbxf" styleId="tcbxf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<td align="right">
						综合表现分：
					</td>
					<td align="left">
						<html:text property="zhbxf" styleId="zhbxf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<td align="right">
						综合素质测评总分：
					</td>
					<td align="left">
						<html:text property="zf" styleId="zf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_gzdx_addZhszcpxx.do?operType=save','xh-xn');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
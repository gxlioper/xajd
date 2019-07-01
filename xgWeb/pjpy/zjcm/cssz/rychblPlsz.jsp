<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js">

</script>
<body onload="dispconf('cpfw')">
	<html:form action="/pjpyZjcmCssz" method="post">
		
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 参数设置 - 参数设置
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						荣誉称号比例批量设置
					</td>
				</tr>
			</thead>
			<tr>
						<td align="right" width="35%">
							学年：
						</td>
						<td align="left">
							<html:text name="rs" property="xn" styleId="xn"
								style="color:#666666" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<html:text name="rs" property="xq" styleId="xq"
								style="color:#666666" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:text name="rs" property="nd" styleId="nd"
								style="color:#666666" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>设置比例范围：
						</td>
						<td align="left">
							<html:select property="cpfw" styleId="cpfw" style="width:120px" onchange="dispconf('cpfw')">
								<html:options collection="cpfwList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td>
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()"
							 styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>荣誉称号：
						</td>
						<td align="left">
							<html:select property="jxjdm" styleId="jxjdm">
								<html:options collection="dmList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							建议人数比例：
						</td>
						<td align="left">
							<input type="text" style="width:60px" name="jxjbl" id="jxjbl"
							onkeypress="return numInputValue(this,5,event)" maxlength="4" />
							<font color="red">% （请输入 0 ～ 100 之间的数字）</font>
						</td>
					</tr>
		</table>
		<div class="buttontool" align="center">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_zjcm_rychblPlsz.do?act=save','cpfw-jxjdm')"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
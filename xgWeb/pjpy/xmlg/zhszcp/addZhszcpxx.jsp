<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前位置:评奖评优 - 数据维护 - 综合素质测评 
       		</div>
    	</div>
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/pjpy_xmlg_addZhszcpxx.do" />
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
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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
						<font color="red">*</font>学期：
					</td>
					<td align="left">
						<html:select property="xq" style="width:90px" styleClass="select"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
						</html:select>
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
						德育表现分：
					</td>
					<td align="left">
						<html:text property="dcj" styleId="dcj" onblur="ckinpdata(this)" style="width:90px"></html:text>
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
						智育表现分：
					</td>
					<td align="left">
						<html:text property="zcj" styleId="zcj" onblur="ckinpdata(this)" style="width:90px"></html:text>
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
						文体表现分：
					</td>
					<td align="left">
						<html:text property="tcj" styleId="tcj" onblur="ckinpdata(this)" style="width:90px"></html:text>
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
						
					</td>
					<td align="left">
						
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
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_xmlg_addZhszcpxx.do?operType=save','xh-xn-xq');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
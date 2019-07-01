<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/zgkd/zgkd.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyzgkdwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 信息维护 - 素质测评
       		</div>
    	</div>
    	<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/pjpy_zgkd_szcpadd.do" />
			<input type="hidden" id="act" value="save"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个增加
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right" width="15%">
						<font color="red">*</font>学号：
					</td>
					<td align="left" width="35%">
						<html:text name='rs' property="xh"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="">
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
						品德测评得分：
					</td>
					<td align="left">
						<html:text property="pdcpdf" styleId="pdcpdf" onblur="ckinpdata(this);"></html:text>
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
						课程学习成绩测评得分：
					</td>
					<td align="left">
						<html:text property="kcxxcjdf" styleId="kcxxcjdf" onblur="ckinpdata(this);"></html:text>
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
						身心测评得分：
					</td>
					<td align="left">
						<html:text property="sxcpdf" styleId="sxcpdf" onblur="ckinpdata(this);"></html:text>
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
						基本素质测评得分：
					</td>
					<td align="left">
						<html:text property="jbszcpdf" styleId="jbszcpdf" onblur="ckinpdatanum(this);" maxlength="5"></html:text>
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
						基本素质测评等级：
					</td>
					<td align="left">
						<html:select property="jbszcpdj" styleId="jbszcpdj" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="szdjList" property="szdjdm" labelProperty="szdjmc"/>
						</html:select>
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
							发展素质测评得分：
						</td>
						<td align="left">
							<html:text property="fzszcpdf" styleId="fzszcpdf" onblur="ckinpdatanum(this);" maxlength="5"></html:text>
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_zgkd_szcpadd.do?act=save','xh-xn');"
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
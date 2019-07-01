<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>
<body>
	<html:form action="/pjpynblgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_nblg_jxjsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 评奖评优 - 奖学金申请 - 填写申请表
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
		<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
			<tr style="height:23px">
				<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="">
								<bean:write name='rs' property="xh" />
								<input type="hidden" id="xh" value="${rs.xh }"/>
							</td>
						</logic:equal>
				<td align="right">
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					智育成绩&nbsp;&nbsp;&nbsp;<br/>专业排名：
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					综合测评&nbsp;&nbsp;&nbsp;<br/>班级排名：
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					出生年月：
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
				</td>
				<td align="right">
					学年有无&nbsp;&nbsp;&nbsp;<br/>违纪处分：
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					体育&nbsp;&nbsp;&nbsp;<br/>是否达标：
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					外语：
				</td>
				<td align="left">
					等级<br/>
					成绩
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					学年其他&nbsp;&nbsp;&nbsp;<br/>获取情况：
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					申请奖学金类别：
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<td align="right">
					个&nbsp;&nbsp;&nbsp;<br/>
					人&nbsp;&nbsp;&nbsp;<br/>
					学&nbsp;&nbsp;&nbsp;<br/>
					年&nbsp;&nbsp;&nbsp;<br/>
					小&nbsp;&nbsp;&nbsp;<br/>
					结&nbsp;&nbsp;&nbsp;<br/>
				</td>
				<td align="left" colspan="3">
				
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nblg_jxjsq.do?act=save','xh-jxjdm');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_print" onclick="window.open('pjpy_nblg_jxjprint.do?xh='+document.getElementById('xh').value)" 
						style="" id="buttonPrint">
						打印报表
					</button>
				</div>
				<!-- 保存提示信息 -->
			
	</html:form>
</body>

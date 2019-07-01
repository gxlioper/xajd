<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<!-- DWR js -->
<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
<script type='text/javascript' src='/xgxt/pjpy/xmlg/pjpyxmlg.js'></script>
<script type="text/javascript">
<!--
	function savedata() {
		var xh = document.getElementById('xh').value;
		var xn = document.getElementById('xn').value;
		var rychdm = document.getElementById('rychdm').value;
		var rychmc = document.forms[0].rychdm.options[document.forms[0].rychdm.selectedIndex].text;
		if (xh==''||xn==''||rychdm==''||xh==null||xn==null||rychdm==null) {
			alert('带*号字段必须填写!');
			return false;
		}
		if (checkTextAreaLength('jlqk-sqly','奖励情况-申请理由',1000)) 
		{
			getStuDtiaInfo.checkStuRychsq(xh,xn,rychdm,function (data) {
			  if (data[0]=="false") {
				alert("你已申请"+xn+"学年 \'"+rychmc+"\' 荣誉称号，\n并已通过上级部门审核,不能再次申请！");
				return false;
			  }else if(data[1]=="false"){
			    alert(xn+"学年必修课成绩有不及格;\n或该生受过处分.暂不能进行申请！");
			    return false;
			  }else{
				if ($("pt")) {
					BatAlert.showTips('正在保存数据，请等待...');
				}
				saveinfo('xmlgpjpy.do?method=rychsqDefault&operType=save','');	
			  }
		   });
		}
	}
//-->
</script>
<body>
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_xmlg_rychsqDefault.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 荣誉称号申请 - 填写申请表
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
			<script>
   	 					alert("您输入的学号无效!");
    				</script>
		</logic:equal>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						填写申请表
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="30%">
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notPresent>
				</td>
				<td align="right" width="15%">
					学年：
				</td>
				<td align="left">
					${rs.xn }
					<input type="hidden" name="xn" id="xn" value="${rs.xn }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					${rs.nd }
					<input type="hidden" name="nd" id="nd" value="${rs.nd }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
				<font color="red">*</font>荣誉称号：	
				</td>
				<td align="left">
				<html:select property="rychdm" styleId="rychdm">
						<html:option value=""></html:option>
						<html:options collection="rychList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					专业：
				</td>
				<td align="left">
					${rs.zymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					班级：
				</td>
				<td align="left">
					${rs.bjmc }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					
				</td>
				<td align="left">
					
				</td>
				<td align="right">
					班级人数：
				</td>
				<td align="left">
				${rs.bjrs }
				</td>
			</tr>
			
			<!-- 这个地方显示成绩，综测，处分信息 -->
			<!-- 综合测评成绩 -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }学年综合测评排名信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2">
						<table width="100%" border="1" align="center" class="tbstyle">
							<thead>
							<tr>
								<td align="center">
									学期
								</td>
								<td align="center">
									德育表现分
								</td>
								<td align="center">
									智育表现分
								</td>
								<td align="center">
									文体表现分
								</td>
								<td align="center">
									学期排名
								</td>
								<td align="center">
									学年排名
								</td>
							</tr>
							</thead>
							<logic:notEmpty name="zhcpList">
								<logic:iterate id="zc" name="zhcpList">
									<tr style="cursor:hand;"	align="center" >
										<logic:iterate id="v" name="zc" >
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="zhcpList">
								<tr><td colspan="6" align="center">暂无记录</td></tr>
							</logic:empty>						
						</table>

					</div>
				</td>
			</tr>
			<!-- 学生成绩信息 -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
									onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getStucjList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }学年课程成绩信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child3" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									学期
								</td>
								<td align="center">
									课程性质
								</td>
								<td align="center">
									课程
								</td>
								<td align="center">
									成绩
								</td>
							</tr>
							</thead>
							<!-- 这里是通过DWR进行调用的 -->
							<tbody width="100%" class="tbstyle" id="cjxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			<!-- 处分信息 -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }学年违纪处分信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									学期
								</td>
								<td align="center">
									处分类别
								</td>
								<td align="center">
									处分原因
								</td>
								<td align="center">
									处分时间
								</td>
							</tr>
							</thead>
							<!-- 这里是通过DWR进行调用的 -->
							<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>	
				
			<tr>
				<td align="right">
					奖励情况：
					<br/>
					<font color="red">(限1000字)</font>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="jlqk" styleId="jlqk" rows="5"
						style="width:770px"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					申请理由：
					<br/>
					<font color="red">(限1000字)</font>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly" rows="5"
						style="width:770px"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<button type="button" class="button2" id="btn_save"
				onclick="savedata()"
				style="width:80px">
				保 存
			</button>
<%--			&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--			<button type="button" class="button2" id="btn_close" onclick="" style="width:80px"--%>
<%--				id="buttonClose">--%>
<%--				打 印--%>
<%--			</button>--%>
		</div>
		<!-- 保存后提示页面 -->
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败！');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>

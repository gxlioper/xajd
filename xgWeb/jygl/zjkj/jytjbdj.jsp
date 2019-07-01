<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 头文件 -->
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		<!--
			function saveForm(text){
				
				var flg1 = checkSearchTj('shgzkssj1','shgzjssj1');
				var flg2 = checkSearchTj('shgzkssj2','shgzjssj2');
				var flg3 = checkSearchTj('shgzkssj3','shgzjssj3');
				var flg4 = checkSearchTj('shsjkssj1','shsjjssj1');
				var flg5 = checkSearchTj('shsjkssj2','shsjjssj2');
				var flg6 = checkSearchTj('shsjkssj3','shsjjssj3');
				var flg = false;
				if (flg1 && flg2 && flg3 && flg4 && flg5 && flg6){
					flg = true;
				}
				
				if (flg && 'save'==text) {
					saveUpdate('/xgxt/jygl.do?method=jytjbdj&doType=save','save_xh');
				} else if (flg && 'update'==text) {
					saveUpdate('/xgxt/jygl.do?method=jytjbdj&doType=modify','save_xh');
				} else {
					return false;
				}
			}
		//-->
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="url" name="url"
				value="/jygl.do?method=jytjbdj" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="getStuInfo" value="xh" />

			<div class="con_overlfow" style="width:100%">
				<div class="tab">
					<table width="100%" border="0" class="formlist tablenowrap">
						<thead>
							<tr>
								<th colspan="8">
									<span>${rs.xxmc }毕业生就业推荐表</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<logic:notPresent name="doType">
											<button id="buttonSave" onclick="saveForm('save')">
												保存
											</button>
										</logic:notPresent>

										<logic:equal value="view" name="doType">
											<button id="buttonSave" onclick="window.close();return false;">
												关闭
											</button>
										</logic:equal>

										<logic:present name="doType">
											<logic:notEqual value="view" name="doType">
												<button id="buttonSave" onclick="saveForm('update')">
													保存
												</button>
											</logic:notEqual>
										</logic:present>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>学号
								</th>
								<td colspan="7">
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="save_xh" maxlength="20" value="${rs.xh }"
											readonly="true" />
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<logic:present name="doType">
											<html:text property="save_xh" maxlength="20"
												value="${rs.xh }" readonly="true" />
										</logic:present>
										<logic:notPresent name="doType">
											<html:text property="save_xh" maxlength="20"
												value="${rs.xh }"
												onkeypress="autoFillStuInfo(event.keyCode,this)"
												onblur="chkIsStu(this,'view_jy_bysxxb','xh')"></html:text>
											<button
												onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);"
												class="btn_01" id="buttonFindStu">
												选择
											</button>
										</logic:notPresent>
									</logic:notEqual>
								</td>
							</tr>
						</tbody>
						<tbody>
							<tr>
								<td align="center" width="12%"></td>
								<td align="center" width="14%"></td>
								<td align="center" width="12%"></td>
								<td align="center" width="12%"></td>
								<td align="center" width="12%"></td>
								<td align="center" width="12%"></td>
								<td align="center" width="16%"></td>
								<td align="center" width="12%"></td>
							</tr>
							<tr>
								<th rowspan="3">
									基本情况
								</th>
								<th>
									姓名
								</th>
								<td align="center">
									<html:text property="save_xm" readonly="true" value="${rs.xm }"></html:text>
								</td>
								<th>
									性别
								</th>
								<td align="center">
									<html:text property="save_xb" readonly="true" value="${rs.xb }"></html:text>
								</td>
								<th>
									民族
								</th>
								<td align="center">
									<html:text property="save_mzmc" readonly="true"
										value="${rs.mzmc }"></html:text>
								</td>
								<td align="center" rowspan="5">
									<img
										src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
										border="0" align="absmiddle" style="width:140px;height:160px" />
								</td>
							</tr>
							<tr>
								<th>
									出生年月
								</th>
								<td align="center">
									<html:text property="save_csrq" readonly="true"
										value="${rs.csrq }"></html:text>
								</td>
								<th>
									生源地区
								</th>
								<td align="center">
									<html:text property="save_sydq" readonly="true"
										value="${rs.sydq }${rs.syds }${rs.sydx }"></html:text>
								</td>
								<th>
									政治面貌
								</th>
								<td align="center">
									<html:text property="save_zzmmmc" readonly="true"
										value="${rs.zzmmmc }"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									家庭地址
								</th>
								<td align="center" colspan="3">
									<html:text property="save_jtdz" maxlength="50"
										style="width:90%" value="${rs.jtdz }"></html:text>
								</td>
								<th>
									健康状况
								</th>
								<td align="center">
									<html:text property="save_jkzk" maxlength="20"
										value="${rs.jkzk }"></html:text>
								</td>
							</tr>
							<tr>
								<th rowspan="2">
									联系方式
								</th>
								<th>
									联系地址
								</th>
								<td align="center" colspan="3">
									<html:text property="save_lxdz" value="${rs.lxdz }"
										maxlength="50" style="width:90%"></html:text>
								</td>
								<th>
									邮政编码
								</th>
								<td align="center">
									<html:text property="save_yzbh" value="${rs.yzbh }"
										maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									电子信箱
								</th>
								<td align="center" colspan="3">
									<html:text property="save_dzyx" value="${rs.dzyx }"
										maxlength="20" style="width:90%"></html:text>
								</td>
								<th>
									联系电话
								</th>
								<td align="center">
									<html:text property="save_lxdh" value="${rs.lxdh }"
										maxlength="20"></html:text>
								</td>
							</tr>
							<tr>
								<th rowspan="4">
									教育背景
								</th>
								<th>
									所在<bean:message key="lable.xb" />
								</th>
								<td align="center" colspan="3">
									<html:text property="save_xymc" value="${rs.xymc }"
										readonly="true" style="width:90%"></html:text>
								</td>
								<th>
									入学年月
								</th>
								<td align="center" colspan="2">
									<html:text property="save_rxnd" value="${rs.rxnd }${rs.rxnf }"
										style="width:90%"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									主修专业
								</th>
								<td align="center" colspan="3">
									<html:text property="save_zymc" readonly="true"
										value="${rs.zymc }" style="width:90%"></html:text>
								</td>
								<th>
									学 制
								</th>
								<td align="center" colspan="2">
									<html:text property="save_xz" value="${rs.xz }" readonly="true"
										style="width:90%"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									辅修专业
								</th>
								<td align="center" colspan="3">
									<html:text property="save_fxzy" style="width:90%"
										maxlength="50" value="${rs.fxzy }"></html:text>
								</td>
								<th>
									学历
								</th>
								<td align="center" colspan="2">
									<html:text property="save_xl" value="${rs.xl }" readonly="true"
										style="width:90%"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									计算机水平
								</th>
								<td align="center" colspan="3">
									<html:text property="save_jsjsp" maxlength="20"
										style="width:90%" value="${rs.jsjsp }"></html:text>
								</td>
								<th>
									外语水平
								</th>
								<td align="center" colspan="2">
									<html:text property="save_wysp" maxlength="20"
										style="width:90%" value="${rs.wysp }"></html:text>
								</td>
							</tr>
							<tr>
								<th rowspan="4">
									社会工作
								</th>
								<td align="center">
									起止时间
								</td>
								<td align="center" colspan="4">
									工作单位（或社团组织）
								</td>
								<td align="center" colspan="2">
									所任职务
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_shgzkssj1" styleId="shgzkssj1"
										style="width:70px" value="${rs.shgzkssj1 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shgzjssj1" styleId="shgzjssj1"
										style="width:70px" value="${rs.shgzjssj1 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_gzdw1" maxlength="50"
										style="width:90%" value="${rs.gzdw1 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_srzw1" maxlength="20"
										style="width:90%" value="${rs.srzw1 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_shgzkssj2" styleId="shgzkssj2"
										style="width:70px" value="${rs.shgzkssj2 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shgzjssj2" styleId="shgzjssj2"
										style="width:70px" value="${rs.shgzjssj2 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_gzdw2" maxlength="50"
										style="width:90%" value="${rs.gzdw2 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_srzw2" maxlength="20"
										style="width:90%" value="${rs.srzw2 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_shgzkssj3" styleId="shgzkssj3"
										style="width:70px" value="${rs.shgzkssj3 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shgzjssj3" styleId="shgzjssj3"
										style="width:70px" value="${rs.shgzjssj3 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_gzdw3" maxlength="50"
										style="width:90%" value="${rs.gzdw3 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_srzw3" maxlength="20"
										style="width:90%" value="${rs.srzw3 }"></html:text>
								</td>
							</tr>
							<tr>
								<th rowspan="4">
									获奖情况
								</th>
								<td align="center">
									时间
								</td>
								<td align="center" colspan="4">
									荣誉称号或证书名称
								</td>
								<td align="center" colspan="2">
									奖励部门（颁证机构）
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_hjsj1" styleId="hjsj1"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')" value="${rs.hjsj1 }"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_rych1" maxlength="50"
										style="width:90%" value="${rs.rych1 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_bzjg1" maxlength="50"
										style="width:90%" value="${rs.bzjg1 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_hjsj2" styleId="hjsj2"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')" value="${rs.hjsj2 }"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_rych2" maxlength="50"
										style="width:90%" value="${rs.rych2 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_bzjg2" maxlength="50"
										style="width:90%" value="${rs.bzjg2 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_hjsj3" styleId="hjsj3"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')" value="${rs.hjsj3 }"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_rych3" maxlength="50"
										style="width:90%" value="${rs.rych3 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_bzjg3" maxlength="50"
										style="width:90%" value="${rs.bzjg3 }"></html:text>
								</td>
							</tr>
							<tr>
								<th rowspan="4">
									技能证书
								</th>
								<td align="center">
									时间
								</td>
								<td align="center" colspan="4">
									荣誉称号或证书名称
								</td>
								<td align="center" colspan="2">
									奖励部门（颁证机构）
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_jnzssj1" value="${rs.jnzssj1 }"
										styleId="save_jnzssj1" onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_jnzsmc1" maxlength="50"
										style="width:90%" value="${rs.jnzsmc1 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_jnzsbzjg1" maxlength="50"
										style="width:90%" value="${rs.jnzsbzjg1 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_jnzssj2" value="${rs.jnzssj2 }"
										onblur='dateFormatChg(this)' styleId="save_jnzssj2"
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_jnzsmc2" maxlength="50"
										style="width:90%" value="${rs.jnzsmc2 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_jnzsbzjg2" maxlength="50"
										style="width:90%" value="${rs.jnzsbzjg2 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_jnzssj3" value="${rs.jnzssj3 }"
										onblur='dateFormatChg(this)' styleId="save_jnzssj3"
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_jnzsmc3" maxlength="50"
										style="width:90%" value="${rs.jnzsmc3 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_jnzsbzjg3" maxlength="50"
										style="width:90%" value="${rs.jnzsbzjg3 }"></html:text>
								</td>
							</tr>
							<tr>
								<th rowspan="4">
									社会实践
								</th>
								<td align="center">
									起止时间
								</td>
								<td align="center" colspan="4">
									工作单位及实践内容
								</td>
								<td align="center" colspan="2">
									实践活动成效
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_shsjkssj1" styleId="shsjkssj1"
										style="width:70px" value="${rs.shsjkssj1 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shsjjssj1" styleId="shsjjssj1"
										style="width:70px" value="${rs.shsjjssj1 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_shsjdw1" maxlength="50"
										style="width:90%" value="${rs.shsjdw1 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_shsjcx1" maxlength="50"
										style="width:90%" value="${rs.shsjcx1 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_shsjkssj2" styleId="shsjkssj2"
										style="width:70px" value="${rs.shsjkssj2 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shsjjssj2" styleId="shsjjssj2"
										style="width:70px" value="${rs.shsjjssj2 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_shsjdw2" maxlength="50"
										style="width:90%" value="${rs.shsjdw2 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_shsjcx2" maxlength="50"
										style="width:90%" value="${rs.shsjcx2 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="save_shsjkssj3" styleId="shsjkssj3"
										style="width:70px" value="${rs.shsjkssj3 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shsjjssj3" styleId="shsjjssj3"
										style="width:70px" value="${rs.shsjjssj3 }"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</td>
								<td align="center" colspan="4">
									<html:text property="save_shsjdw3" maxlength="50"
										style="width:90%" value="${rs.shsjdw3 }"></html:text>
								</td>
								<td align="center" colspan="2">
									<html:text property="save_shsjcx3" maxlength="50"
										style="width:90%" value="${rs.shsjcx3 }"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									毕业生自我推荐
								</th>
								<td colspan="7">
									<html:textarea property="save_zwtj" value="${rs.zwtj }"
										style="width:90%" rows="8" onblur="checkLen(this,2000)"></html:textarea>
								</td>
							</tr>
							<logic:equal value="sh" name="doType">
								<logic:equal value="xy" name="userType" scope="session">
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											审核
										</th>
										<td colspan="7">
											<html:select property="save_xysh" value="${rs.xysh }">
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											审核意见
											<br />
											<font color="red"><限500字>
											</font>
										</th>
										<td colspan="7">
											<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
												rows="5" onblur="checkLen(this,500)" style="width:90%"></html:textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="session">
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											审核
										</th>
										<td colspan="7">
											<html:select property="save_xysh" value="${rs.xysh }"
												disabled="true">
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											审核意见
											<br />
											<font color="red"><限500字>
											</font>
										</th>
										<td colspan="7">
											<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
												rows="5" onblur="checkLen(this,500)" disabled="true"
												style="width:90%"></html:textarea>
										</td>
									</tr>
									<tr>
										<th>
											学校审核
										</th>
										<td colspan="7">
											<html:select property="save_xxsh" value="${rs.xxsh }">
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th>
											学校审核意见
											<br />
											<font color="red"><限500字>
											</font>
										</th>
										<td colspan="7">
											<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
												rows="5" onblur="checkLen(this,500)" style="width:90%"></html:textarea>
										</td>
									</tr>
								</logic:notEqual>
							</logic:equal>
							<logic:equal value="view" name="doType">
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										审核
									</th>
									<td colspan="7">
										<html:select property="save_xysh" value="${rs.xysh }"
											disabled="true">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										审核意见
										<br />
										<font color="red"><限500字>
										</font>
									</th>
									<td colspan="7">
										<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
											rows="5" onblur="checkLen(this,500)" disabled="true"
											style="width:90%"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										学校审核
									</th>
									<td colspan="7">
										<html:select property="save_xxsh" value="${rs.xxsh }"
											disabled="true">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										学校审核意见
										<br />
										<font color="red"><限500字>
										</font>
									</th>
									<td colspan="7">
										<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
											rows="5" disabled="true" onblur="checkLen(this,500)"
											style="width:90%"></html:textarea>
									</td>
								</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
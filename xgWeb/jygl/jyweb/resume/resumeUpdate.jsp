<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<div id="tmpdiv1"></div>
			<div class="type_right">
				<div class="rightcon">
					<h3>
						<span>个人简历</span>
					</h3>
					<div class="tabcon">
						<div id="div1">
							<p class="choose">
								<html:hidden property="save_zdyrdw" value="${rs.zdyrdw }" />

								<input type="checkbox" name="zdyrdw"
									onclick="if(this.checked){$('save_zdyrdw').value='是'}else{$('save_zdyrdw').value='否'}"
									checked="true" />


								只对用人单位公开
							</p>
							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="5">
											<span>个人信息</span>
											<a href="#"
												onclick="if(this.className=='sq'){$('tbody1').className = 'hide';this.className='zk'}else{$('tbody1').className = '';this.className='sq'}"
												class="sq"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody1">
									<tr>
										<th width="65px">
											<font color="red">*</font>身份证号
										</th>
										<td>
											<html:hidden property="save_xh" value="${rs.xh }" />
											<html:text property="save_sfzh" value="${rs.sfzh }"></html:text>
											<span class="bm"> <html:hidden property="save_sfzhbm"
													value="${rs.sfzhbm }" /> <input type="checkbox"
													name="sfzhbm"
													onclick="if(this.checked){$('save_sfzhbm').value='是'}else{$('save_sfzhbm').value='否'}"
													/> 保密 </span>
										</td>
										<th width="65px">
											<font color="red">*</font>姓名
										</th>
										<td>
											<html:text property="save_xm" value="${rs.xm }"
												maxlength="20"></html:text>
										</td>
										<td rowspan="4">
											<div class="photo">
												<img
													src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
													style="width:95px;height:130px" />
									</tr>
									<tr>
										<th>
											<font color="red">*</font>性别
										</th>
										<td>
											<html:select property="save_xb" value="${rs.xb }">
												<html:option value=""></html:option>
												<html:option value="男">男</html:option>
												<html:option value="女">女</html:option>
											</html:select>
										</td>
										<th>
											<font color="red">*</font>民族
										</th>
										<td>
											<html:select property="save_mzmc" value="${rs.mzmc }">
												<html:options collection="mzList" property="mc"
													labelProperty="mc" />
											</html:select>

										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>出生年月
										</th>
										<td>
											<html:text property="save_csrq" value="${rs.csrq }"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="save_csrq"
													onblur="dateFormatChg(this)"
											></html:text>
										</td>
										<th>
											<font color="red">*</font>生源地区
										</th>
										<td>
											<html:text property="save_sydq"
												value="${rs.sydq }${rs.syds }${rs.sydx }"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>政治面貌
										</th>
										<td>
											<html:select property="save_zzmmmc" value="${rs.zzmmmc }" styleId="save_zzmmmc">
												<html:options collection="zzmmList" property="dm"
													labelProperty="mc" />
											</html:select>

										</td>
										<th>
											<font color="red">*</font>健康状况
										</th>
										<td>
											<html:text property="save_jkzk" maxlength="20"
												value="${rs.jkzk }"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>家庭地址
										</th>
										<td colspan="3">
											<html:text property="save_jtdz" maxlength="50"
												style="width:100%" value="${rs.jtdz }"></html:text>
										</td>
									</tr>
								</tbody>
							</table>

							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="5">
											<span>教育背景</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody2').className = 'hide';this.className='zk'}else{$('tbody2').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody2">
									<tr>
										<th width="65px">
											毕业<bean:message key="lable.xb" />
										</th>
										<td>
											<html:text property="save_xymc" value="${rs.xymc }"
												readonly="true"></html:text>
										</td>
										<th width="65px">
											入学年月
										</th>
										<td>
											<html:text property="save_rxnd" value="${rs.rxnd }${rs.rxrq }"
												readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											主修专业
										</th>
										<td>
											<html:text property="save_zymc" readonly="true"
												value="${rs.zymc }"></html:text>
										</td>
										<th>
											学制
										</th>
										<td>
											<html:text property="save_xz" value="${rs.xz }"
												readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											辅修专业
										</th>
										<td>
											<html:text property="save_fxzy" maxlength="50"
												value="${rs.fxzy }"></html:text>
										</td>
										<th>
											学历
										</th>
										<td>
											<html:text property="save_xl" value="${rs.xl }"
												maxlength="20"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											计算机水平
										</th>
										<td>
											<html:text property="save_jsjsp" maxlength="20"
												value="${rs.jsjsp }"></html:text>
										</td>
										<th>
											外语水平
										</th>
										<td>
											<html:text property="save_wysp" maxlength="20"
												value="${rs.wysp }"></html:text>
										</td>
									</tr>
								</tbody>
							</table>

							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="5">
											<span>联系方式</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody3').className = 'hide';this.className='zk'}else{$('tbody3').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody3">
									<tr>
										<th width="65px">
											联系地址
										</th>
										<td>
											<html:text property="save_lxdz" value="${rs.lxdz }"
												maxlength="50"></html:text>
										</td>
										<th width="65px">
											邮政编码
										</th>
										<td>
											<html:text property="save_yzbh" value="${rs.yzbh }"
												maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>电子信箱
										</th>
										<td>
											<html:text property="save_dzyx" value="${rs.dzyx }"
												maxlength="25"></html:text>
										</td>
										<th>
											<font color="red">*</font>联系电话
										</th>
										<td>
											<html:text property="save_lxdh" value="${rs.lxdh }"
												maxlength="20"></html:text>
										</td>
									</tr>
								</tbody>
							</table>

							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="2">
											<span>求职意向</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody4').className = 'hide';this.className='zk'}else{$('tbody4').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody4">
									<tr>
										<th width="70px">
											<font color="red">*</font>工作性质
										</th>
										<td>
											<html:text property="save_gzxz" value="${rs.gzxz }"
												maxlength="20"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>目标岗位
										</th>
										<td>
											<html:text property="save_mbgw" value="${rs.mbgw }"
												maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>目标地点
										</th>
										<td>
											<html:text property="save_gzdd" value="${rs.gzdd }"
												maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>期望薪水
										</th>
										<td>
											<html:text property="save_qwxs" maxlength="20"
												onblur="checkMoney(this)" value="${rs.qwxs }"></html:text>
										</td>
									</tr>
									<tr>
										<th valign="top">
											目标职能
										</th>
										<td>
											<html:textarea property="save_mbzn" rows="8"
												style="float:left;width:90%" value="${rs.mbzn }" />
										</td>
									</tr>
								</tbody>
							</table>
							<div class="btn">
								<button
									onclick="$('div1').className='hide';$('div2').className='';">
									下一页
								</button>
							</div>
						</div>
						<div id="div2" class="hide">

							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="4">
											<span>社会工作情况</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody5').className = 'hide';this.className='zk'}else{$('tbody5').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody5">
									<tr>
										<td width="20%" align="center">
											起止时间
										</td>
										<td width="30%" align="center">
											工作单位（或社团组织）
										</td>
										<td width="30%" align="center">
											所任职务
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_shgzkssj1" styleId="shgzkssj1"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shgzkssj1 }"
												onblur='dateFormatChg(this)'></html:text>
											<html:text property="save_shgzjssj1" styleId="shgzjssj1"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shgzjssj1 }"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_gzdw1" maxlength="50"
												style="width:100%" value="${rs.gzdw1 }"></html:text>
										</td>
										<td>
											<html:text property="save_srzw1" maxlength="20"
												style="width:100%" value="${rs.srzw1 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_shgzkssj2" styleId="shgzkssj2"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shgzkssj2 }"
												onblur='dateFormatChg(this)' />
											<html:text property="save_shgzjssj2" styleId="shgzjssj2"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shgzjssj2 }"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_gzdw2" maxlength="50"
												style="width:100%" value="${rs.gzdw2 }"></html:text>
										</td>
										<td>
											<html:text property="save_srzw2" maxlength="20"
												style="width:100%" value="${rs.srzw2 }"></html:text>
										</td>
									</tr>
									<tr>
										<td align="center">
											<html:text property="save_shgzkssj3" styleId="shgzkssj3"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shgzkssj3 }"
												onblur='dateFormatChg(this)'></html:text>
											<html:text property="save_shgzjssj3" styleId="shgzjssj3"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shgzjssj3 }"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_gzdw3" maxlength="50"
												style="width:100%" value="${rs.gzdw3 }"></html:text>
										</td>
										<td>
											<html:text property="save_srzw3" maxlength="20"
												style="width:100%" value="${rs.srzw3 }"></html:text>
										</td>
									</tr>
								</tbody>
							</table>


							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="4">
											<span>社会实践情况</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody8').className = 'hide';this.className='zk'}else{$('tbody8').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody8">
									<tr>
										<td width="20%">
											起止时间
										</td>
										<td width="30%">
											工作单位及实践内容
										</td>
										<td width="30%">
											实践活动成效
										</td>
									</tr>
									<tr>
										<td align="center">
											<html:text property="save_shsjkssj1" styleId="shsjkssj1"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shsjkssj1 }"
												onblur='dateFormatChg(this)'></html:text>
											<html:text property="save_shsjjssj1" styleId="shsjjssj1"
												style="width:70px" value="${rs.shsjjssj1 }"
												onclick="showCalendar(this.id,'y-mm-dd')"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_shsjdw1" maxlength="50"
												style="width:100%" value="${rs.shsjdw1 }"></html:text>
										</td>
										<td>
											<html:text property="save_shsjcx1" maxlength="50"
												style="width:100%" value="${rs.shsjcx1 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_shsjkssj2" styleId="shsjkssj2"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shsjkssj2 }"
												onblur='dateFormatChg(this)'></html:text>
											<html:text property="save_shsjjssj2" styleId="shsjjssj2"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shsjjssj2 }"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_shsjdw2" maxlength="50"
												style="width:100%" value="${rs.shsjdw2 }"></html:text>
										</td>
										<td>
											<html:text property="save_shsjcx2" maxlength="50"
												style="width:100%" value="${rs.shsjcx2 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_shsjkssj3" styleId="shsjkssj3"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shsjkssj3 }"
												onblur='dateFormatChg(this)'></html:text>
											<html:text property="save_shsjjssj3" styleId="shsjjssj3"
												onclick="showCalendar(this.id,'y-mm-dd')"
												style="width:70px" value="${rs.shsjjssj3 }"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_shsjdw3" maxlength="50"
												style="width:100%" value="${rs.shsjdw3 }"></html:text>
										</td>
										<td align="center" colspan="2">
											<html:text property="save_shsjcx3" maxlength="50"
												style="width:100%" value="${rs.shsjcx3 }"></html:text>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="4">
											<span>获奖情况</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody6').className = 'hide';this.className='zk'}else{$('tbody6').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody6">
									<tr>
										<td width="20%">
											时间
										</td>
										<td width="30%">
											荣誉称号
										</td>
										<td width="30%">
											奖励部门
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_hjsj1" styleId="hjsj1"
												onclick="showCalendar(this.id,'y-mm-dd')"
												onblur='dateFormatChg(this)' value="${rs.hjsj1 }"></html:text>
										</td>
										<td>
											<html:text property="save_rych1" maxlength="50"
												style="width:100%" value="${rs.rych1 }"></html:text>
										</td>
										<td>
											<html:text property="save_bzjg1" maxlength="50"
												style="width:100%" value="${rs.bzjg1 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_hjsj2" styleId="hjsj2"
												onclick="showCalendar(this.id,'y-mm-dd')"
												onblur='dateFormatChg(this)' value="${rs.hjsj2 }"></html:text>
										</td>
										<td>
											<html:text property="save_rych2" maxlength="50"
												style="width:100%" value="${rs.rych2 }"></html:text>
										</td>
										<td>
											<html:text property="save_bzjg2" maxlength="50"
												style="width:100%" value="${rs.bzjg2 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_hjsj3" styleId="hjsj3"
												onclick="showCalendar(this.id,'y-mm-dd')"
												onblur='dateFormatChg(this)' value="${rs.hjsj3 }"></html:text>
										</td>
										<td>
											<html:text property="save_rych3" maxlength="50"
												style="width:100%" value="${rs.rych3 }"></html:text>
										</td>
										<td>
											<html:text property="save_bzjg3" maxlength="50"
												style="width:100%" value="${rs.bzjg3 }"></html:text>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="4">
											<span>技能证书</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody7').className = 'hide';this.className='zk'}else{$('tbody7').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody7">
									<tr>
										<td width="20%">
											时间
										</td>
										<td width="30%">
											证书名称
										</td>
										<td width="30%">
											颁证机构
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_jnzssj1" value="${rs.jnzssj1 }"
												styleId="save_jnzssj1"
												onclick="showCalendar(this.id,'y-mm-dd')"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_jnzsmc1" maxlength="50"
												style="width:100%" value="${rs.jnzsmc1 }"></html:text>
										</td>
										<td>
											<html:text property="save_jnzsbzjg1" maxlength="50"
												style="width:100%" value="${rs.jnzsbzjg1 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_jnzssj2" value="${rs.jnzssj2 }"
												styleId="save_jnzssj2"
												onclick="showCalendar(this.id,'y-mm-dd')"
												onblur='dateFormatChg(this)'></html:text>
										</td>
										<td>
											<html:text property="save_jnzsmc2" maxlength="50"
												style="width:100%" value="${rs.jnzsmc2 }"></html:text>
										</td>
										<td>
											<html:text property="save_jnzsbzjg2" maxlength="50"
												style="width:100%" value="${rs.jnzsbzjg2 }"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:text property="save_jnzssj3" value="${rs.jnzssj3 }"
												styleId="save_jnzssj3"
												onblur='dateFormatChg(this)'
												onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
										</td>
										<td>
											<html:text property="save_jnzsmc3" maxlength="50"
												style="width:100%" value="${rs.jnzsmc3 }"></html:text>
										</td>
										<td>
											<html:text property="save_jnzsbzjg3" maxlength="50"
												style="width:100%" value="${rs.jnzsbzjg3 }"></html:text>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="99%" border="0" class="tab_01">
								<thead>
									<tr>
										<td colspan="4">
											<span>自我推荐</span>
											<a href="#" class="sq"
												onclick="if(this.className=='sq'){$('tbody9').className = 'hide';this.className='zk'}else{$('tbody9').className = '';this.className='sq'}"></a>
										</td>
									</tr>
								</thead>
								<tbody id="tbody9">
									<tr>
										<td style="word-break:break-all;">
											<html:textarea property="save_zwtj" value="${rs.zwtj }"
												style="width:100%" rows="8" onblur="checkLen(this,2000)"></html:textarea>
										
										</td>
										
									</tr>
								</tbody>
							</table>

							<div class="btn" style="height:38px">
								<button
									onclick="$('div1').className='';$('div2').className='hide';">
									上一页
								</button>
								<logic:notPresent name="doType">
									<button
										onclick="saveUpdate('jywebUseCheckSession.do?method=resumeUpdate&doType=save',
              		'save_sfzh-save_xm-save_xb-save_mzmc-save_csrq-save_sydq-save_zzmmmc-save_jkzk-save_jtdz-save_dzyx-save_lxdh-save_gzxz-save_gzdd-save_qwxs')">
										保存
									</button>

								</logic:notPresent>
								<logic:present name="doType">
									<button
										onclick="saveUpdate('jywebUseCheckSession.do?method=resumeUpdate&doType=modify',
              		'save_sfzh-save_xm-save_xb-save_mzmc-save_csrq-save_sydq-save_zzmmmc-save_jkzk-save_jtdz-save_dzyx-save_lxdh-save_gzxz-save_gzdd-save_qwxs')">
										保存
									</button>
								</logic:present>
								<button type="reset">
									重置
								</button>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
 			alert("${message}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 			
 			$('div1').className='hide';
 			$('div2').className='';
 			
 		</script>
		</logic:present>
	</body>
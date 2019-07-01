<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		<!--
			function setDwInfo(text) {
				if ('' != text) {
					jyglDAO.getDwInfo(text,function(data){
						$('dwdz').value = data.dwszd;
						$('dwszd').value = data.dwszd;
						$('dwlxdh').value=data.dwdh;
						$('dwxz').value=data.dwxzdm;
						$('dwlb').value=data.dwxzdm;
						$('dwyb').value=data.dwyb;
						$('dwhy').value=data.hyfldm;
						$('jyhy').value=data.hyfldm;
					});
				}
			}	
			
			function setKqxx(text) {
				if ('4'==text){
					$('kqxx').disabled=false;
				} else {
					$('kqxx').disabled=true;
				}
			}
		//-->
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业协议详细信息</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh" />
			<input type="hidden" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />


			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>第一部分</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td align="left" width="34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%" align="right">
								性别
							</th>
							<td width="34%">
								${rs.xb}
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								学校
							</th>
							<td>
								${rs.xxmc }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								学历
							</th>
							<td>
								${rs.xl }
							</td>
							<th>
								生源地
							</th>
							<td>
								${rs.sydq }${rs.syds }${rs.sydx }
							</td>
						</tr>

						<tr>
							<th>
								学制
							</th>
							<td>
								${rs.xz }
							</td>
							<th>
								培养方式
							</th>
							<td>
								${rs.pyfs }
							</td>
						</tr>

						<tr>
							<th>
								入学年份
							</th>
							<td>
								${rs.rxnf }
							</td>
							<th>
								毕业年份
							</th>
							<td>
								${rs.bynf }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>第二部分</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>就业类型
							</th>
							<td width="34%">
								<html:select property="save_jybz" styleId="jybz"
									style="width:85%" onchange="setKqxx(this.value);"
									value="${rs.jybz }">
									<html:options collection="jybzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								考取学校
							</th>
							<td>
								<html:text property="save_kqxx" maxlength="50" style="width:85%"
									styleId="kqxx" value="${rs.kqxx }"></html:text>
							</td>
							<th>
								主管部门
							</th>
							<td>
								<html:text property="save_zgdwdm" style="width:85%" maxlength="50" value="${rs.zgdwdm }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								实际就业单位
							</th>
							<td>
								<html:hidden property="save_dwdm" styleId="dwdm" value="${rs.dwdm }"/>
								
								<html:text property="save_dwmc"  styleId="dwmc" readonly="true" value="${rs.dwmc }"/>
								<button onclick="showTopWin('/xgxt/jygl.do?method=compayData',800,500);"
										id="buttonFindStu">
										选择
								</button>
							</td>
							<th>
								报到证单位名称
							</th>
							<td>
								<html:text property="save_bdzdw" style="width:85%" maxlength="50" value="${rs.bdzdw }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								推荐类型
							</th>
							<td>
								<html:select property="save_tjlx" style="width:85%"
									value="${rs.tjlx }">
									<html:options collection="tjlxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								解约情况
							</th>
							<td>
								<html:text property="save_jyqk" maxlength="50" style="width:85%"
									value="${rs.jyqk }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								未就业标志
							</th>
							<td>
								<html:text property="save_wjybz" maxlength="25"
									style="width:85%" value="${rs.wjybz }"></html:text>
							</td>
							<th>
								未就业原因
							</th>
							<td>
								<html:text property="save_wjyyy" maxlength="50"
									style="width:85%" value="${rs.wjyyy }"></html:text>
							</td>
						</tr>

						<tr>
							<th>
								是否解约
							</th>
							<td>
								<html:select property="save_sfjy" value="${rs.sfjy }"
									style="width:85%">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								是否违约
							</th>
							<td>
								<html:select property="save_sfwy" value="${rs.sfwy }"
									style="width:85%">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>报到证当<br/>天审核情况</th>
							<td>
								<html:text property="save_dtshqk" maxlength="50" value="${rs.dtshqk }" style="width:85%"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td>
								${rs.lxdz }
							</td>
							<th>
								邮编
							</th>
							<td>
								${rs.yzbh }
							</td>
						</tr>
						<tr>
							<th>
								手机号码
							</th>
							<td>
								${rs.sjhm }
							</td>
							<th>
								Email
							</th>
							<td>
								${rs.dzyx }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>第三部分</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								组织机构代码
							</th>
							<td width="34%">
								<html:text property="save_zzjgdm" value="${rs.zzjgdm }"
									maxlength="20" style="width:85%"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th width="16%">
								单位地址
							</th>
							<td width="34%" style="word-break:break-all;">
								<html:text property="save_yrdwszd" styleId="yrdwszd" maxlength="100" style="width:85%" value="${rs.dwszd }" />
							</td>
						</tr>
						<tr>
							<th>
								单位电话
							</th>
							<td>
								<div class="pos" style="z-index:2">
									<html:text property="save_dwlxdh" maxlength="20"
										style="width:85%" styleId="dwlxdh" readonly="true"
										value="${rs.dwdh }" onblur="checkPhoneV4(this)"></html:text>
										<div id="phoneErrow" class="hide" >
									       <p>电话格式不正确</p>
									    </div>
								</div>
							</td>
							<th>
								单位邮编
							</th>
							<td>
								<html:text property="dwyb" styleId="dwyb" style="width:85%"
									readonly="true" value="${rs.dwyb }" />
							</td>
						</tr>
						<tr>
							<th>
								单位隶属
							</th>
							<td>
								<html:select property="save_lsbm" style="width:85%"
									value="${rs.lsbm }">
									<html:options collection="lsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								单位类别
							</th>
							<td>
								<html:select property="save_dwxz" style="width:85%"
									styleId="dwxz"  value="${rs.dwxz }">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								单位行业
							</th>
							<td>
								<html:select property="save_jyhy" style="width:85%"
									styleId="hyfl" value="${rs.hyfldm }">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								实际工作所在地
							</th>
							<td>
								<html:select property="save_sjdwszd" style="width:85%"
									value="${rs.sjdwszd }">
									<html:options collection="dwdzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								档案接收地
							</th>
							<td style="word-break:break-all;">
								<html:text property="save_dajsd" maxlength="100" style="width:85%" value="${rs.dajsd }"></html:text>
							</td>
							<th>
								档案接收地地址
							</th>
							<td style="word-break:break-all;">
								<html:text property="save_dajsddz" maxlength="50"
									style="width:85%" value="${rs.dajsddz }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								档案接收地邮编
							</th>
							<td>
								<html:text property="save_dajsdyb" value="${rs.dajsdyb }"
									maxlength="6" style="width:85%"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								户口迁移地址
							</th>
							<td style="word-break:break-all;">
								<html:text property="save_hkqydz" value="${rs.hkqydz }"
									maxlength="100" style="width:85%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								试用期工资
							</th>
							<td>
								<html:text property="save_syqgz" value="${rs.syqgz }"
									maxlength="15" style="width:85%" onblur="checkMoney(this)"></html:text>
							</td>
							<th>
								转正后工资
							</th>
							<td>
								<html:text property="save_zzhgz" value="${rs.zzhgz }"
									maxlength="15" style="width:85%" onblur="checkMoney(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								专业对口
							</th>
							<td>
								<html:select property="save_sfdk" style="width:85%"
									value="${rs.sfdk }">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								登记日期
							</th>
							<td>
								<html:text property="save_djrq" maxlength="20" style="width:85%"
									value="${rs.djrq }" styleId="djrq" onblur='dateFormatChg(this)'
									onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									
									
									<logic:equal value="不通过" name="rs" property="xysh">
										<html:hidden property="save_xysh" value="未审核"/>
									</logic:equal>
									<logic:equal value="不通过" name="rs" property="xxsh">
										<html:hidden property="save_xxsh" value="未审核"/>
									</logic:equal>
									
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="saveButton"
										onclick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save','save_xh-save_jybz');">
										保存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>

		<logic:present name="result">
			<script>
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function setGp(text) {
				if ("是"==text) {
					$('gp1').style.display='';
				} else {
					$('gp1').style.display='none';
				}
			
			}
		</script>
	</head>
	<body onload="setGp($('isGp').value)">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业协议维护</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=zgddJyxywh" />
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

			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							您的就业协议当前审核状态为<bean:message key="lable.xb" />审核"${rs.xysh }"，学校审核"${rs.xxsh }"。
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:present name="flg">
						<logic:equal value="true" name="flg">
							<script defer="defer">
								$('buttonSave').disabled = true;
							</script>
						</logic:equal>
					</logic:present>
				</logic:notEmpty>

				<logic:present name="isBys">
					<logic:notEmpty name="isBys">
						<div class="prompt" id="div_help">
							<h3>
								<span>提示：</span>
							</h3>
							<p>
								${isBys}
							</p>
							<a class="close" title="隐藏"
								onclick="this.parentNode.style.display='none'"></a>
						</div>
						<script defer="defer">
						$('buttonSave').disabled = true;
					</script>
					</logic:notEmpty>
				</logic:present>
			</logic:equal>

			<!-- 学院审核状态为退回，修改后状态改为需重审 -->
			<logic:equal value="退回" name="rs" property="xysh">
				<html:hidden property="save_xysh" value="需重审" />
			</logic:equal>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<logic:notEqual value="view" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&doType=save','save_xh-save_jybz-save_byqx-save_dwxz-save_dwmc-save_lsbm-save_yrdwszd');">
												保存
											</button>
										</logic:notEqual>
										<logic:equal value="stu" name="userType">
											<button type="reset">
												重置
											</button>
										</logic:equal>
										<logic:notEqual value="stu" name="userType">
											<button onclick="window.close();return false;">
												关闭
											</button>
										</logic:notEqual>
									</div>
								</td>
							</tr>
						</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="30%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%">
								性别
							</th>
							<td width="30%">
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
								院系
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
						<tr>
							<th>
								手机号码
							</th>
							<td>
								${rs.sjhm }
							</td>
							<th>
								固定电话
							</th>
							<td>
								${rs.lxdh }
							</td>

						</tr>
						<tr>
							<th>
								民族
							</td>
							<td>
								${rs.mzmc }
							</td>
							<th>
								政治面貌
							</th>
							<td>
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>

							<th>
								QQ
							</th>
							<td>
								${rs.qq }
							</td>
							<th>
								Email
							</th>
							<td>
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td>
								${map.jtdz }
							</td>
							<th>
								家庭邮编
							</th>
							<td>
								${map.jtyb }
							</td>
						</tr>
						<tr>
							<th>
								通迅地址
							</th>
							<td colspan="3">
								${rs.lxdz }
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>就业状况
							</th>
							<td width="34%">
								<html:select property="save_jybz" styleId="jybz"
									style="width:90%" name="rs">
									<html:options collection="jyqkList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>毕业去向
							</th>
							<td width="34%">
								<html:select property="save_byqx" name="rs" styleId="byqx"
									style="width:90%" onchange="setGp(this)">
									<html:options collection="byqxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>用人单位性质
							</th>
							<td width="34%">
								<html:select property="save_dwxz" styleId="dwxz"
									style="width:90%" name="rs">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>用人单位
							</th>
							<td>
								<html:hidden property="save_dwdm" styleId="dwdm" name="rs" />
								<html:text property="save_dwmc" readonly="readonly"
									styleId="dwmc" style="width:65%" name="rs"></html:text>


								<button
									onclick="showTopWin('/xgxt/jygl.do?method=compayData&flg=dw',800,500);"
									id="buttonFindStu" class="btn_01">
									选择
								</button>


							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>单位隶属部门
							</th>
							<td>
								<html:select property="save_lsbm" style="width:90%" name="rs">
									<html:options collection="lsbmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>用人单位所在地
							</th>
							<td>
								<html:select property="save_yrdwszd" styleId="yrdwszddm" style="width:90%" name="rs">
									<html:options collection="dwszdList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								实际所在地
							</th>
							<td>
								<html:select property="save_sjdwszd" style="width:90%" name="rs">
									<html:options collection="dwszdList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								报到证号
							</th>
							<td>
								<html:text property="save_bdzh"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"
									style="width:90%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								派遣时间
							</th>
							<td>
								<html:text property="save_pqsj" style="width:90%" styleId="pqsj"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" maxlength="20" name="rs"></html:text>
							</td>
							<th>
								档案处理情况
							</th>
							<td>
								<html:text property="save_daclqk" maxlength="50"
									style="width:90%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								是否改派
							</th>
							<td>
								<html:select property="save_isgp" onchange="setGp(this.value)"
									styleId="isGp" style="width:90%" name="rs">
									<html:option value="">----请选择----</html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								专业是否对口
							</th>
							<td>
								<html:select property="save_sfdk" style="width:90%" name="rs">
									<html:option value="">----请选择----</html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>




						<tr style="display:none" id="gp1">
							<th>
								改派次数
							</th>
							<td>
								<html:text property="save_gpcs" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="3"
									style="width:90%"></html:text>
							</td>
							<th>
								改派原因
							</th>
							<td>
								<html:text property="save_gpyy" maxlength="100"
									style="width:90%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								原单位名称
							</th>
							<td>

								<html:hidden property="save_yyrdw" styleId="ydw" name="rs" />
								<input type="text" readonly="readonly" id="ydwmc"
									value="${rs.yrdwmc }" />

								<button
									onclick="showTopWin('/xgxt/jygl.do?method=compayData&flg=ydw',800,500);"
									id="buttonFindStu" class="btn_01">
									选择
								</button>

							</td>
							<th>
								原报到证号
							</th>
							<td>
								<html:text property="save_ybdzh" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								就业行业
							</th>
							<td>
								<html:select property="save_jyhy" style="width:90%" name="rs">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								户口去向
							</th>
							<td>
								<html:text property="save_hkqydz" maxlength="100" name="rs"
									style="width:80%" style="width:90%"></html:text>
							</td>
						</tr>

						<tr>
							<th>
								扩展项1
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx1" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								扩展项2
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx2" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								扩展项3
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx3" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								扩展项4
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx4" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								扩展项5
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx5" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								扩展项6
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx6" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								扩展项7
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx7" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								扩展项8
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx8" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								扩展项9
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx9" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								<font color="red"><限150字> </font>备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_jybz1" name="rs" rows="4"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				close();
				dialogArgumentsQueryChick();
			</script>
		</logic:present>
	</body>
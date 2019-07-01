<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			function delFj(){
				var pk = "xh";
				var filepath = $("filePath").value;
				var pkValue =  $("pkValue").value;
				
				if (confirm("确认要删除该附件?")) {
					var url="/xgxt/jygl.do?method=deleteFile";
					url+="&filepath="+filepath;
					url+="&url=fclr";
					url+="&tableName=jygl_bysfc"
					url+="&pk="+pk;
					url+="&pkValue="+pkValue;
					url+="&doType=update"
					refreshForm(url);
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/jygl" method="post" enctype="multipart/form-data">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="url" id="url" value="/jygl.do?method=fclr" />
			<input type="hidden" name="getStuInfo" value="xh" />
			<input type="hidden" name="filePath" id="filePath" value="${rs.filepath }" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="7">
								<span>${rs.xxmc }毕业生风采录</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="7">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notPresent name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=fclr&doType=save','save_xh-save_fclx');">
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
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=fclr&doType=modify','save_xh-save_fclx');">
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
							<td>
								<font color="red">*</font>学号
							</td>
							<td colspan="6">
								<logic:equal value="stu" name="userType" scope="session">
									<html:text property="save_xh" maxlength="20" value="${rs.xh }"
										readonly="true" />
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
									<logic:present name="doType">
										<html:text property="save_xh" maxlength="20" value="${rs.xh }"
											readonly="true" />
									</logic:present>
									<logic:notPresent name="doType">
										<html:text property="save_xh" maxlength="20" value="${rs.xh }"
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
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								姓名
							</td>
							<td align="center">
								${rs.xm }
							</td>
							<td align="center">
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</td>
							<td align="center">
								${rs.xymc }
							</td>
							<td align="center">
								<font color="red">*</font>风采类型
							</td>
							<td align="center">
								<html:select property="save_fclx" value="${rs.fclx }"
									style="width:100%">
									<html:options collection="fclxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								性别
							</td>
							<td align="center">
								${rs.xb }
							</td>
							<td align="center">
								专业名称
							</td>
							<td align="center">
								${rs.zymc }
							</td>
							<td align="center">
								学历
							</td>
							<td align="center">
								${rs.xl }
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								籍贯
							</td>
							<td align="center">
								${rs.sydq }${rs.syds }${rs.sydx }
							</td>
							<td align="center">
								班级
							</td>
							<td align="center">
								${rs.bjmc }
							</td>
							<td align="center">
								学号
							</td>
							<td align="center">
								${rs.xh }
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								邮箱
							</td>
							<td colspan="3" align="center">
								${rs.dzyx }
							</td>
							<td align="center">
								手机号码
							</td>
							<td align="center">
								${rs.sjhm }
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								家庭地址
							</td>
							<td colspan="3" align="center">
								${rs.lxdz }
							</td>
							<td align="center">
								邮政编码
							</td>
							<td align="center">
								${rs.yzbh }
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">附件</td>
							<td colspan="5" >
								<logic:notPresent name="doType">
									<html:file property="file" style="width:90%"></html:file>
								</logic:notPresent>
								<logic:present name="doType">
									<logic:present property="filepath" name="rs">
										<a href="jygl.do?method=downLoadFile&pkValue=${rs.xh }&filePath=${rs.filepath }&fileName=${rs.filename }"
										target="_self"/> <font color="red">下载附件</font></a>
										&nbsp;&nbsp;
										
										<logic:notEqual value="view" name="doType">
											<logic:notEqual value="sh" name="doType">
												<a href="#" onclick="delFj()" />点击删除</a>
											</logic:notEqual>
										</logic:notEqual>
										
										
									</logic:present>
									<logic:notPresent property="filepath" name="rs">
										<logic:notEqual value="sh" name="doType">
											<html:file property="file" style="width:90%"></html:file>
										</logic:notEqual>
									</logic:notPresent>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<td rowspan="4" align="center">
								社
								<br />
								会
								<br />
								工
								<br />
								作
							</td>
							<td align="center">
								1
							</td>
							<td colspan="5">
								<html:text property="save_shgz1" maxlength="250"
									style="width:90%" value="${rs.shgz1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								2
							</td>
							<td colspan="5">
								<html:text property="save_shgz2" maxlength="250"
									style="width:90%" value="${rs.shgz2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								3
							</td>
							<td colspan="5">
								<html:text property="save_shgz3" maxlength="250"
									style="width:90%" value="${rs.shgz3 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								4
							</td>
							<td colspan="5">
								<html:text property="save_shgz4" maxlength="250"
									style="width:90%" value="${rs.shgz4 }"></html:text>
							</td>
						</tr>

						<tr>
							<td rowspan="4" align="center">
								获
								<br />
								奖
								<br />
								情
								<br />
								况
							</td>
							<td align="center">
								1
							</td>
							<td colspan="5">
								<html:text property="save_hjqk1" maxlength="250"
									style="width:90%" value="${rs.hjqk1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								2
							</td>
							<td colspan="5">
								<html:text property="save_hjqk2" maxlength="250"
									style="width:90%" value="${rs.hjqk2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								3
							</td>
							<td colspan="5">
								<html:text property="save_hjqk3" maxlength="250"
									style="width:90%" value="${rs.hjqk3 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								4
							</td>
							<td colspan="5">
								<html:text property="save_hjqk4" maxlength="250"
									style="width:90%" value="${rs.hjqk4 }"></html:text>
							</td>
						</tr>

						<tr>
							<td rowspan="4" align="center">
								风
								<br />
								采
								<br />
								事
								<br />
								迹
							</td>
							<td align="center">
								1
							</td>
							<td colspan="5">
								<html:text property="save_fcsj1" maxlength="250"
									style="width:90%" value="${rs.fcsj1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								2
							</td>
							<td colspan="5">
								<html:text property="save_fcsj2" maxlength="250"
									style="width:90%" value="${rs.fcsj2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								3
							</td>
							<td colspan="5">
								<html:text property="save_fcsj3" maxlength="250"
									style="width:90%" value="${rs.fcsj3 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								4
							</td>
							<td colspan="5">
								<html:text property="save_fcsj4" maxlength="250"
									style="width:90%" value="${rs.fcsj4 }"></html:text>
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								风采感言
							</td>
							<td colspan="5" style="word-break:break-all;">
								<html:textarea property="save_fcgy" rows="5" style="width:90%"
									value="${rs.fcgy }" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								备注
							</td>
							<td colspan="5" style="word-break:break-all;">
								<html:textarea property="save_bz" rows="5" style="width:90%"
									value="${rs.bz }" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<td align="right" colspan="2">
									学校审核
								</td>
								<td colspan="5">
									${rs.xxsh }
								</td>
							</tr>
							<tr>
								<td align="right" colspan="2">
									学校审核意见
								</td>
								<td colspan="5">
									${rs.xxshyj }
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="sh" name="doType">
							<logic:equal value="xy" name="userType" scope="session">
								<tr>
									<td align="right" colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />
										审核
									</td>
									<td colspan="5">
										<html:select property="save_xysh" value="${rs.xysh }">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />
										审核意见
									</td>
									<td colspan="5" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" style="width:90%"
											value="${rs.xyshyj }" rows="5" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType" scope="session">
								<tr>
									<td align="right" colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />
										审核
									</td>
									<td colspan="5">
										<html:select property="save_xysh" value="${rs.xysh }"
											disabled="true">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />
										审核意见
									</td>
									<td colspan="5" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" style="width:90%"
											disabled="true" value="${rs.xyshyj }" rows="5"
											onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										学校审核
									</td>
									<td colspan="5">
										<html:select property="save_xxsh" value="${rs.xxsh }">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										学校审核意见
									</td>
									<td colspan="5" style="word-break:break-all;">
										<html:textarea property="save_xxshyj" style="width:90%"
											value="${rs.xxshyj }" rows="5" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:equal>
						</tbody>
					</table>
					</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}'+'${errMsg}');
				if (window.dialogArguments) {
					window.close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>
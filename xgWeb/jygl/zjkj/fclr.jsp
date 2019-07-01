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
				
				if (confirm("ȷ��Ҫɾ���ø���?")) {
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
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
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
								<span>${rs.xxmc }��ҵ�����¼</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="7">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notPresent name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=fclr&doType=save','save_xh-save_fclx');">
											����
										</button>
									</logic:notPresent>

									<logic:equal value="view" name="doType">
										<button id="buttonSave" onclick="window.close();return false;">
											�ر�
										</button>
									</logic:equal>

									<logic:present name="doType">
										<logic:notEqual value="view" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=fclr&doType=modify','save_xh-save_fclx');">
												����
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
								<font color="red">*</font>ѧ��
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
											ѡ��
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
								����
							</td>
							<td align="center">
								${rs.xm }
							</td>
							<td align="center">
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</td>
							<td align="center">
								${rs.xymc }
							</td>
							<td align="center">
								<font color="red">*</font>�������
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
								�Ա�
							</td>
							<td align="center">
								${rs.xb }
							</td>
							<td align="center">
								רҵ����
							</td>
							<td align="center">
								${rs.zymc }
							</td>
							<td align="center">
								ѧ��
							</td>
							<td align="center">
								${rs.xl }
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								����
							</td>
							<td align="center">
								${rs.sydq }${rs.syds }${rs.sydx }
							</td>
							<td align="center">
								�༶
							</td>
							<td align="center">
								${rs.bjmc }
							</td>
							<td align="center">
								ѧ��
							</td>
							<td align="center">
								${rs.xh }
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								����
							</td>
							<td colspan="3" align="center">
								${rs.dzyx }
							</td>
							<td align="center">
								�ֻ�����
							</td>
							<td align="center">
								${rs.sjhm }
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								��ͥ��ַ
							</td>
							<td colspan="3" align="center">
								${rs.lxdz }
							</td>
							<td align="center">
								��������
							</td>
							<td align="center">
								${rs.yzbh }
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">����</td>
							<td colspan="5" >
								<logic:notPresent name="doType">
									<html:file property="file" style="width:90%"></html:file>
								</logic:notPresent>
								<logic:present name="doType">
									<logic:present property="filepath" name="rs">
										<a href="jygl.do?method=downLoadFile&pkValue=${rs.xh }&filePath=${rs.filepath }&fileName=${rs.filename }"
										target="_self"/> <font color="red">���ظ���</font></a>
										&nbsp;&nbsp;
										
										<logic:notEqual value="view" name="doType">
											<logic:notEqual value="sh" name="doType">
												<a href="#" onclick="delFj()" />���ɾ��</a>
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
								��
								<br />
								��
								<br />
								��
								<br />
								��
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
								��
								<br />
								��
								<br />
								��
								<br />
								��
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
								��
								<br />
								��
								<br />
								��
								<br />
								��
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
								��ɸ���
							</td>
							<td colspan="5" style="word-break:break-all;">
								<html:textarea property="save_fcgy" rows="5" style="width:90%"
									value="${rs.fcgy }" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								��ע
							</td>
							<td colspan="5" style="word-break:break-all;">
								<html:textarea property="save_bz" rows="5" style="width:90%"
									value="${rs.bz }" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<td align="right" colspan="2">
									ѧУ���
								</td>
								<td colspan="5">
									${rs.xxsh }
								</td>
							</tr>
							<tr>
								<td align="right" colspan="2">
									ѧУ������
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
										���
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
										������
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
										���
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
										������
									</td>
									<td colspan="5" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" style="width:90%"
											disabled="true" value="${rs.xyshyj }" rows="5"
											onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										ѧУ���
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
										ѧУ������
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
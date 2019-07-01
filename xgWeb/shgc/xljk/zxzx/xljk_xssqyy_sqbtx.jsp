<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function check(obj){
			var checkVal = obj.value;
			if(checkVal == "yes"){	
				document.all["sfzxg_flag"].value="yes";
				document.getElementById('a1').style.display="block";
				document.getElementById('a2').style.display="block";
				document.getElementById('a3').style.display="block";
			}else if(checkVal == "no"){		
				document.all["sfzxg_flag"].value="no";
				document.getElementById('a1').style.display="none";
				document.getElementById('a2').style.display="none";
				document.getElementById('a3').style.display="none";
			}
		}
	</script>
	</head>
	<body onload="check(document.getElementById('sfzxg2'))">
		<html:form action="/xljk_xssqyy_zysq" method="post">
			<logic:present name="noSq" scope="request">
				<script type="text/javascript">
					alert("预约必须提前三小时");
					window.close();
				</script>
			</logic:present>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康－学生申请预约－申请表填写</a>
				</p>
			</div>
			<input type="hidden" id="sfzxg_flag" name="sfzxg_flag" value="no" />
			<input type="hidden" id="zxszy_xnid" name="zxszy_xnid"
				value="<bean:write name="zxszy_xnid" scope="request"/>" />
			
			<div class="tab" style="width:99%;height:500px;overflow-x:hidden;overflow-y:auto">
				<table width="99%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="2">
								<font color="red">*</font>学号
							</th>
							<td align="left">
									<html:text name='xs_rs' property="xh" styleId="xh"
									readonly="true" />
							</td>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='xs_rs' property="xm" styleId="xm"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th colspan="2" readonly="true">
								性别
							</th>
							<td align="left">
								<html:text name='xs_rs' property="xs_sex" styleId="xs_sex"
									readonly="true" />
							</td>
							<th>
								出生日期
							</th>
							<td align="left">
								<html:text name='xs_rs' property="csrq" styleId="csrq"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>籍贯
							</th>
							<td align="left">
								<html:text name='xs_rs' property="jg" styleId="jg" maxlength="10"/>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='xs_rs' property="xy" styleId="xy"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								专业
							</th>
							<td align="left">
								<html:text name='xs_rs' property="zy" styleId="zy"
									readonly="true" />
							</td>
							<th>
								联系电话
							</th>
							<td align="left">
<%--								<div class="pos" style="z-index:2">--%>
									<html:text name='xs_rs' property="qsdh" maxlength="20"/><!--  onblur="checkPhoneV4(this)"  -->
<%--									<div id="phoneErrow" class="hide">--%>
<%--										<p>--%>
<%--											电话格式不正确--%>
<%--										</p>--%>
<%--									</div>--%>
<%--								</div>--%>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>手机号码
							</th>
							<td align="left">
								<html:text name='xs_rs' property="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
							<th>
								家庭经济状况
							</th>
							<td align="left">
								<html:text name='xs_rs' property="jtjjzk" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								咨询师姓名
							</th>
							<td align="left">
								<html:text name='yq_rs' property="zxxxm" readonly="true" />
							</td>
							<th>
								咨询师性别
							</th>
							<td align="left">
								<html:text name='yq_rs' property="zxs_sex" readonly="true" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								咨询日期
							</th>
							<td align="left">
								<html:text name='yq_rs' property="rq" readonly="true" />
							</td>
							<th>
								咨询时间段
							</th>
							<td align="left">
								<html:text name='yq_rs' property="sjd" readonly="true" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								咨询地点
							</th>
							<td align="left" colspan="3">
								<html:text name='yq_rs' property="dd" readonly="true" style="width:90%"/>
							</td>

						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>所学专业满意程度
							</th>
							<td align="left" >
								<html:select property="zymycd" styleId="en" >
									<option value=""></option>
									<html:options collection="mycdList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<logic:present name="xxdm" scope="request">
								<logic:equal value="10355" name="xxdm" scope="request">
									<th>
										<font color="red">*</font>预约方式
									</th>
									<td align="left">
										<html:select property="yyfs" styleId="yyfs" name="yq_rs">
											<option value=""></option>
											<html:options collection="yyfsList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:equal>
							</logic:present>
							<!-- 信阳师范 -->
							<logic:equal value="10477" name="xxdm">
								<th>
									需求程度
								</th>
								<td align="left">
									<html:select property="jjcd" styleId="jjcd" name="yq_rs">
										<option value=""></option>
										<option value="紧急">紧急</option>
										<option value="一般">一般</option>
									</html:select>
								</td>
							</logic:equal>
							
						</tr>
						<tr>
							<th colspan="3">
								是否在其他机构有过咨询
							</th>
							<td colspan="2" align="center">
								<input type="radio" name="sfzxg" id="sfzxg1" value="yes"
									onclick="check(this)"/>
									&nbsp;&nbsp;是 
								<input type="radio" name="sfzxg" id="sfzxg2" value="no" checked
									onclick="check(this)"/>
									&nbsp;&nbsp;否 
							</td>
						</tr>
						<tr name="aa" id="a1">
							<th colspan="2">
								机构名称
							</th>
							<td align="left">
								<html:text name='zxls_rs' property="jgmc" maxlength="15"/>
							</td>
							<th>
								医生姓名
							</th>
							<td align="left">
								<html:text name='zxls_rs' property="ysxm" maxlength="10"/>
							</td>
						</tr>
						<tr name="aa" id="a2">
							<th colspan="2">
								起始时间
							</th>
							<td align="left">
								<html:text name='zxls_rs' property="qzsj" maxlength="15"/>
							</td>
							<th>
								是否或正在服用药物
							</th>
							<td align="left">
								<html:text name='zxls_rs' property="sffyyw" maxlength="25"/>
							</td>
						</tr>
						<tr name="aa" id="a3">
							<th colspan="2">
								有何诊断,分析或结论
							</th>
							<td colspan="4" align="left">
								<html:textarea rows="5" name='zxls_rs' style="width:98%"
									property="zdjg" styleId="a" onblur="checkLen(this,50)"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="6%">
								<font color="red">*</font>需要咨询的内容
								<br/>
								<font color="red"><限250字></font>
							</th>
							<td colspan="5" align="left">
								<html:textarea rows="5" style="width:98%" property="zxnr"
									styleId="a" onblur="checkLen(this,250)"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="6%">
								<font color="red">*</font>来咨询的
								<br/>
								期望和目标
								<br/>
								<font color="red"><限250字></font>
							</th>
							<td colspan="5" align="left">
								<html:textarea rows="5" style="width:98%" property="qwmb"
									styleId="a"  onblur="checkLen(this,250)"/>
							</td>
						</tr>

						<tr>
							<th width="6%" rowspan="6">
								家
								<br />
								庭
								<br />
								成
								<br />
								员
								<br />
								情
								<br />
								况
							</th>
							<th width="12%" align="center">
								<center>与本人关系</center>
							</th>
							<th width="9%" align="center">
								<center>出生年月</center>
							</th>
							<th width="25%" align="center">
								<center>学历</center>
							</th>
							<th colspan="2" align="center">
								<center>职业或身份</center>
							</th>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY1_gx" name="JTCY1_gx"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY1_gx"/>"/>
							</td>
							<td>
								<input type="text" id="JTCY1_csny" name="JTCY1_csny"
									onclick="showCalendar(this.id,'y-mm-dd')" 
									onblur='dateFormatChg(this)' 
									readonly="true"
									style="width:90%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_csny" />"/>
							</td>
							<td>
								<input type="text" id="JTCY1_xl" name="JTCY1_xl"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY1_xl" />"/>
							</td>
							<td colspan="2">
								<input type="text" id="JTCY1_zysf" name="JTCY1_zysf"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY1_zysf" />"/>
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY2_gx" name="JTCY2_gx"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY2_gx"/>"/>
							</td>
							<td>
								<input type="text" id="JTCY2_csny" name="JTCY2_csny"
									style="width:90%;heigh:100%"
									onclick="showCalendar(this.id,'y-mm-dd')" 
									onblur='dateFormatChg(this)' 
									readonly="true"
									value="<bean:write name="rs" property="JTCY2_csny" />"/>
							</td>
							<td>
								<input type="text" id="JTCY2_xl" name="JTCY2_xl"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY2_xl" />"/>
							</td>
							<td colspan="2">
								<input type="text" id="JTCY2_zysf" name="JTCY2_zysf"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY2_zysf" />"/>
							</td>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY3_gx" name="JTCY3_gx"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY3_gx"/>"/>
							</td>
							<td>
								<input type="text" id="JTCY3_csny" name="JTCY3_csny"
									style="width:90%;heigh:100%"
									onclick="showCalendar(this.id,'y-mm-dd')" 
									onblur='dateFormatChg(this)' 
									readonly="true"
									value="<bean:write name="rs" property="JTCY3_csny" />"/>
							</td>
							<td>
								<input type="text" id="JTCY3_xl" name="JTCY3_xl"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY3_xl" />"/>
							</td>
							<td colspan="2">
								<input type="text" id="JTCY3_zysf" name="JTCY3_zysf"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY3_zysf" />"/>
							</td>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY4_gx" name="JTCY4_gx"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY4_gx"/>"/>
							</td>
							<td>
								<input type="text" id="JTCY4_csny" name="JTCY4_csny"
									style="width:90%;heigh:100%"
									onclick="showCalendar(this.id,'y-mm-dd')" 
									onblur='dateFormatChg(this)' 
									readonly="true"
									value="<bean:write name="rs" property="JTCY4_csny" />"/>
							</td>
							<td>
								<input type="text" id="JTCY4_xl" name="JTCY4_xl"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY4_xl" />"/>
							</td>
							<td colspan="2">
								<input type="text" id="JTCY4_zysf" name="JTCY4_zysf"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY4_zysf" />"/>
							</td>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY5_gx" name="JTCY5_gx"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY5_gx"/>"/>
							</td>
							<td>
								<input type="text" id="JTCY5_csny" name="JTCY5_csny"
									style="width:90%;heigh:100%"
									onclick="showCalendar(this.id,'y-mm-dd')" 
									onblur='dateFormatChg(this)' 
									readonly="true"
									value="<bean:write name="rs" property="JTCY5_csny" />"/>
							</td>
							<td>
								<input type="text" id="JTCY5_xl" name="JTCY5_xl"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY5_xl" />"/>
							</td>
							<td colspan="2">
								<input type="text" id="JTCY5_zysf" name="JTCY5_zysf"
									style="width:90%;heigh:100%"
									maxlength="10"
									value="<bean:write name="rs" property="JTCY5_zysf" />"/>
							</td>
						</tr>
					</tbody>
				</table>
				
			</div>
			<table width="99%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="saveUpdate('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Table_Record',
									'jg-sjhm-zymycd-zxnr-qwmb-yyfs')" id="saveButton">
										保存
									</button>
									<button onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
					    alert("申请成功!");
					    dialogArgumentsQueryChick();
						window.close();
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
					    alert("申请失败!");
					    document.getElementById("tmpdiv").innerHTML = "";
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nocondi">
					<script>
					    alert("您不符合申请条件,申请失败！");
				    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 头文件 -->
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
		<script type="text/javascript">
function guizhdxPrint() {
		var xh = document.getElementById('xh').value;
		var xn = document.getElementById('xn').value;
		var sbsj = document.getElementById('sbsj').value;
		var pkValue = xh+xn+sbsj;
		window.open('wjcfnblgwh.do?method=gzdxCfPrint&pkValue='+pkValue );
	}
	
	function modicfxx(url,pks){
		var eles = pks.split("-");
		for (i = 0; i < eles.length; i++) {
			if($(eles[i])){
				if ($(eles[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
		}
		saveinfo(url,pks);
	}
</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 系院申报 - 申报信息维护</a>
			</p>
		</div>

		<html:form action="/wjcfxmlgwh" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${rs.sbsj }" id="sbsj" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单个修改</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
									<br />
									<font color="red">说明：须向学生处提交当事人思想与认识、事件陈述、检讨、<bean:message
											key="lable.xsgzyxpzxy" />调查情况及相关依据等书面材料.</font>
								</div>

								<div class="btn">
									<logic:notEqual value="view" name="xy_writable">
										<logic:equal name="xxdm" value="10388">
											<button type="button" id="btn_save"
												onclick="modicfxx('wjcf_xmlg_modiCfsbxx.do?operType=save','xn-nd-cflb-cfyy-jtwjsy-xyclyj');">
												保 存
											</button>
										</logic:equal>
										<logic:notEqual name="xxdm" value="10388">
										 	<button type="button" id="btn_save"
												onclick="saveinfo('wjcf_xmlg_modiCfsbxx.do?operType=save','xn-nd-cflb-cfyy-jtwjsy');">
												保 存
											</button>
										</logic:notEqual>
										</logic:notEqual>
										<logic:equal value="10657" name="xxdm">
											<button type="button" onclick="guizhdxPrint()">
												打 印
											</button>
										</logic:equal>
										<button type="button" id="btn_close" onclick="window.close();return false;">
											关 闭
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%">
								<html:select property="xn" styleId="xn" style="width:90px"
									styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
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
								<font color="red">*</font>年度
							</th>
							<td>
								<html:select property="nd" styleId="nd" style="width:90px">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								<font color="red">*</font>处分类别
							</th>
							<td>
								<html:select property="cflb" styleId="cflb" styleClass="select">
									<html:option value="">--请选择--</html:option>
									<html:options collection="cflbList" property="cflbdm"
										labelProperty="cflbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<font color="red">*</font>处分原因
							</th>
							<td>
								<html:select property="cfyy" styleId="cfyy" styleClass="select">
									<html:option value="">--请选择--</html:option>
									<html:options collection="cfyyList" property="cfyydm"
										labelProperty="cfyymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
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
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								违纪时间
							</th>
							<td>
								<html:text property="wjsj" styleId="wjsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('wjsj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
<%--							<th>--%>
<%--								违纪时间--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<html:text property="wjsj" styleId="wjsj"--%>
<%--									onblur="dateFormatChg(this)" style="cursor:hand;"--%>
<%--									onclick="return showCalendar('wjsj','y-mm-dd');"></html:text>--%>
<%--							</td>--%>
							<logic:equal name="xxdm" value="10388">
							<th>
								申报人
							</th>
							<td>
								<input type="text" value="${rs.sbr}" readonly="true"/>
							</td>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10388">
								<th>审核结果</th>
								<td>
									${rs.xxsh }
								</td>
							</logic:notEqual>
						</tr>
					<thead>
						<tr>
							<td colspan="4">
								<span>历史违纪处分信息&nbsp;&nbsp; <a
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfxx();">查看</a>
								</span>

							</td>
						</tr>
					</thead>

					<tr>
						<td colspan="4">
							<div id="child4" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center" width="80px">
												学年
											</td>
											<td align="center" width="110px">
												处分类别
											</td>
											<td align="center" width="110px">
												处分原因
											</td>
											<td align="center" width="80px">
												处分时间
											</td>
											<td align="center" width="110px">
												处分文号
											</td>
											<td align="center" width="80px">
												违纪时间
											</td>
											<td align="center">
												违纪事实
											</td>
										</tr>
									</thead>
									<!-- 这里是通过DWR进行调用的 -->
									<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<logic:notEqual value="11078" name="xxdm">
						<tr>
							<th>
								处理决定书
								<br />
								书面材料或附件
							</th>
							<td align="left" colspan="3">
								<input type="file" name="uploadFile" style="width:58%" />
								<logic:notEmpty name="rs" property="fjsclj">
									<a href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }"
										target="_blank">下载</a>
								</logic:notEmpty>
							</td>
						</tr>
					</logic:notEqual>
					<tr style="height:23px">
						<th>
							<font color="red">*</font>违纪事实
							<br />
							<font color="red">(字数在2000字以内)</font>
						</th>
						<td align="left" colspan="3">
							<font color="red">(请如实说明违纪时间,地点,事件经过和造成后果)</font>
							<br />
							<html:textarea rows="7" style="width:500px" property="jtwjsy"
								styleId="jtwjsy" onkeyup="checkLen(this,2000)" />
						</td>
					</tr>
					<logic:equal value="10338" name="xxdm">
						<tr>
							<th>
								历史违纪记录
								<br />
								<font color="red">(字数在800字以内)</font>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="6" style="width:500px" property="lswjjl"
									styleId="lswjjl" onkeyup="checkLen(this,800)" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							<logic:equal name="xxdm" value="10388">
							<font color="red">*</font>
							</logic:equal>
							<bean:message key="lable.xsgzyxpzxy" />
							、系处理依据&nbsp;&nbsp;
							<br />
							及处理意见
							<br />
							<font color="red">(字数在500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xyclyj" rows="5"
								style="width:500px" onkeyup="checkLen(this,500)">
							</html:textarea>
						</td>
					</tr>




					<logic:equal name="xxdm" value="11355">
						<logic:equal name="isFdy" value="true">
							<tr>
								<th>
									辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(字数在100字以内)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bzryj" styleId="bzryj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isFdy" value="true">
							<logic:equal name="userType" value="xy">
								<tr>
									<th>
										辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br />
										<font color="red">(字数在100字以内)</font>
									</th>
									<td align="left" colspan="3">
										<html:textarea name="rs" property="bzryj" styleId="bzryj"
											rows="6" style="width:500px" onblur="chLeng(this,100)">
										</html:textarea>
									</td>
								</tr>
							</logic:equal>
						</logic:notEqual>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(字数在100字以内)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bzryj" styleId="bzryj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									学校意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(字数在100字以内)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="xgcyj" styleId="xgcyj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="admin">
							<tr>
								<th>
									辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(字数在100字以内)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bzryj" styleId="bzryj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									学校意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(字数在100字以内)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="xgcyj" styleId="xgcyj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
								备注
								<br />
								<font color="red">(字数在100字以内)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea name="rs" property="bz" styleId="bz" rows="5"
									style="width:500px;inputtext" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
					</logic:equal>
				</table>
			</div>
			<!-- 保存后提示页面 -->
			<logic:present name="inserted">
				<logic:equal value="yes" name="inserted">
					<script>
				alert("操作成功！");
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
				</logic:equal>
				<logic:equal value="no" name="inserted">
					<script>
					alert("操作失败！");
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

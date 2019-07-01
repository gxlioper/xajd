<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/commWjcfDAO.js"></script>
		<script type="text/javascript">
			function getwh(tid) {
				var cfwh = document.getElementById(tid).value;
				var xxdm = document.getElementById('xxdm').value;
				if (xxdm=='10856') {
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='沪工程学[][]号';
					}
				} else if (xxdm=='10402') {
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='漳师[]号';
					}
				} else if (xxdm=='10628#') {
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='西昌学文号『』';
					}
				}
			}
			function dataDoSave11(mustFill) {
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alert("请将带\"*\"号的项目输入完整！");
						return false;
					}
				}
				
				var url = "/xgxt/modiData.do?realTable=";
				url += 'wjcfb';
			//	var tmpTable = window.dialogArguments.document.forms[0].realTable.value;
				url += "&doType=save";
				url += "&tableName=";
				url += 'view_wjcf';
				url += "&pk=";
				url += 'xh||cfwh||cfsj';
				url += "&pkValue=";
				url += document.forms[0].pkValue.value;
				url += "&from=";
				url += 'discipInfo';
				
				//检测该生同一处分时间与文号下面是否有处分
				var sj = $('cfsj').value;
				var wh = $('cfwh').value;
				var xh = $('xh').value;
				var hidd_cfsj = $('hidd_cfsj').value;
				var hidd_cfwh = $('hidd_cfwh').value;
				var type = $('doType').value;
			
				commWjcfDAO.checkStuWjcfIsExists(xh,sj,wh,function(data) {
				
					if (type=='add') {
						if (!data) {
							alert("该生在处分时间为:'" + sj + "',处分文号为:'" + wh+"'的文件中已受过一次处分，\n同一学生同一处分时间与文号下面不能有二次处分，请更换处分时间和文号。");
							return false;
						} else {
								document.forms[0].action = url;
								document.forms[0].submit();
								alert("保存成功！");
								window.close();
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
							}
					} else {

						if (!data && ((hidd_cfsj != sj) || (hidd_cfwh !=wh)) && (sj != null && wh != null)) {
							alert("该生在处分时间为:'" + sj + "',处分文号为:'" + wh+"'的文件中已受过一次处分，\n同一学生同一处分时间与文号下面不能有二次处分，请更换处分时间和文号。");
							return false;
						} else {
								document.forms[0].action = url;
								document.forms[0].submit();
								alert("保存成功！");
								window.close();
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
						}
					}	
				});
				
			}
		</script>
	</head>
	<body onload="">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 数据维护 - 违纪处分数据维护</a>
			</p>
		</div>


		<html:form action="/data_search" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
			<logic:notPresent name="showXbemy">
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						有错误发生！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
                       alert("您输入的学号无效!");
                    </script>
					</logic:equal>
					<input type="hidden" id="doType" name="doType"
						value="<bean:write name="doType" scope="request"/>" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="<bean:write name="pkValue" scope="request"/>" />
					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc-zzmmmc" />
					<input type="hidden" id="url" name="url" value="/sjcz/wjcfb.jsp" />
					<input type="hidden" id="clwh" name="clwh"
						value="<bean:write name="clwh"/>" />
					<input type="hidden" name="hidd_cfsj" id="hidd_cfsj" value="${rs.cfsj }"/>	
					<input type="hidden" name="hidd_cfwh" id="hidd_cfwh" value="${rs.cfwh }"/>	
							
					<logic:notEqual value="13022" name="xxdm">
						<logic:notEqual value="add" name="doType">

							<div class="formbox">
								<h3 class="datetitle_01">
									<span> 处分文件下载&nbsp;&nbsp; <logic:empty name="rs" property="fjsclj">
											<font color="red"> 暂无处分文件!</font>
										</logic:empty> </span>
								</h3>

								<logic:notEmpty name="rs" property="fjsclj">
									<table summary="" class="dateline" align="" width="100%">
										<tbody>
											<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand">
												<logic:empty name="rs" property="fjsclj">
													<td colspan="2">

													</td>
												</logic:empty>
												<logic:notEmpty name="rs" property="fjsclj">
													<td align="center" title="处分文件下载">
														<a href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }"
															target="_blank">下载</a>
													</td>
													<td align="center" title="处分文号">
														${rs.cfwh }
													</td>
													<td align="center" title="处分时间">
														${rs.cfsj }
													</td>
												</logic:notEmpty>
											</tr>
										</tbody>
									</table>
								</logic:notEmpty>
							</div>
							
						</logic:notEqual>
					</logic:notEqual>


					<div class="tab">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>违纪处分信息维护2</span>
									</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
											<logic:equal value="11417" name="xxdm">
												<button type="button" onclick="dataDoSave11('xn-xq-xh-cflb-cfyy');"
													id="buttonSave">
													保 存
												</button>
											</logic:equal>
											<logic:notEqual value="11417" name="xxdm">

													<button type="button"
														onclick="if(checkXnNd('xn','nd'))dataDoSave11('xn-xq-xh-bz-cfsj-cfwh');"
														id="buttonSave">
														保 存
													</button>
											</logic:notEqual>
											<button type="button" onclick="Close();return false;" id="buttonClose">
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
										<html:text name='rs' property="xh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<logic:equal name="doType" value="add">
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu" style="">
												选择
											</button>
										</logic:equal>
									</td>
									<logic:notEqual value="11417" name="xxdm">
										<th width="16%">
											<font color="red">*</font>年度
										</th>
										<td width="34%">
											<html:select name="rs" property="nd" style="width:90px"
												styleId="nd">
												<html:options collection="xnList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
									</logic:notEqual>
								</tr>
								<tr>
									<th>
										姓名
									</th>
									<td>
										${rs.xm }
									</td>
									<th>
										<font color="red">*</font>学年
									</th>
									<td>
										<html:select name="rs" property="xn" style="width:90px"
											styleId="xn">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
								</tr>

								<tr>
									<th>
										政治面貌
									</th>
									<td align="left">
											${rs.zzmmmc }
										<html:text name='rs' property="xb" styleId="xb"
											style="display:none" />
									</td>
									<th>
										<font color="red">*</font>学期
									</th>
									<td align="left">
										<html:select name="rs" property="xq" style="width:90px"
											styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										年级
									</th>
									<td align="left">
										${rs.nj }
									</td>
									<th>
										<font color="red">*</font>处分类别
									</th>
									<td align="left">
										<html:select name="rs" property="cflb" style="width:90px"
											styleId="cflb">
											<html:option value=""></html:option>
											<html:options collection="cflbList" property="cflbdm"
												labelProperty="cflbmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />

									</th>
									<td align="left">
										${rs.xymc }
									</td>
									<th>
										<font color="red">*</font>处分事由
									</th>
									<td align="left">
										<html:select name="rs" property="cfyy" style="width:90px"
											styleId="cfyy">
											<html:option value=""></html:option>
											<html:options collection="cfyyList" property="cfyydm"
												labelProperty="cfyymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										专业
									</th>
									<td align="left">
										${rs.zymc }
									</td>
									<th>
										<font color="red">*</font>处分时间
									</th>
									<td align="left">
										<html:text name='rs' property="cfsj" styleId="cfsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('cfsj','y-mm-dd');" />
									</td>
								</tr>
								<tr>
									<th>
										班级
									</th>
									<td align="left">
										${rs.bjmc }
									</td>
									<th>
										<logic:equal value="11078" name="xxdm">
									违纪处分决定书文号
								</logic:equal>
										<logic:notEqual value="11078" name="xxdm">
									<font color="red">*</font>处分文号
								</logic:notEqual>
									</th>
									<td align="left">
										<html:text name='rs' property="cfwh" styleId="cfwh"
											onclick="getwh('cfwh');" maxlength="30"/>
									</td>
								</tr>
								<tr>
									<th>
										申诉处理时间
									</th>
									<td align="left">
										<html:text name='rs' property="cxsj" styleId="cxsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('cxsj','y-mm-dd');" />
									</td>
									<th>
										申诉处理文号
									</th>
									<td align="left">
										<html:text name='rs' property="cxwh" styleId="cxwh"
											onclick="getwh('cxwh');" maxlength="30"/>
									</td>
								</tr>
								<tr>
									<th>
										申诉结果
									</th>
									<td align="left">
										<%--							<html:text name='rs' property="ssjg" styleId="ssjg" />--%>
										<html:select name="rs" property="ssjg" style="width:100px"
											styleId="ssjg">
											<html:option value=""></html:option>
											<html:option value="解除处分"></html:option>
											<html:option value="更改处分"></html:option>
											<html:option value="维持原处分"></html:option>
											<html:option value="未决定"></html:option>
										</html:select>
									</td>


									<logic:equal value="1049701" name="xxdm">
										<th>
											扣分
										</th>
										<td align="left">
											<html:text name='rs' property="kf" styleId="kf"
												style="width:90px" />
										</td>
									</logic:equal>
									<logic:equal value="yes" name="isCSMZ">
										<th>
											撤消结果
										</th>
										<td align="left">
											<html:text name='rs' property="cxjg" styleId="cxjg"
												readonly="true" />
										</td>
									</logic:equal>
									<logic:notPresent name="isCSMZ">
										<logic:equal value="13022" name="xxdm">
											<th>
												更改处分结果
											</th>
											<td>
												<html:select name="rs" property="ggcflbdm"
													style="width:90px" styleId="ggcflbdm">
													<html:option value=""></html:option>
													<html:options collection="cflbList" property="cflbdm"
														labelProperty="cflbmc" />
												</html:select>
												<br />
												<font color="red">(申诉结果为更改处分时修改该项)</font>
											</td>
										</logic:equal>
										<!-- 非宁波理工 begin-->
										<logic:notEqual value="13022" name="xxdm">
											<th>
												处分文件上传
											</th>
											<td align="left">
												<input type="file" name="uploadFile" style="width:240px"  value="" contenteditable="false"  />
											</td>
										</logic:notEqual>
										<!-- end -->
									</logic:notPresent>
								</tr>
								<logic:notEqual value="10827" name="xxdm">
									<logic:equal value="13022" name="xxdm">
										<tr>
											<th>
												解除时间
											</th>
											<td align="left">
												<input type="text" disabled="disabled" readonly="readonly"
													value="${rs.cxclsj }" />
											</td>
											<th>
												解除文号
											</th>
											<td align="left">
												<input type="text" disabled="disabled" readonly="readonly"
													value="${rs.cxclwh }" />
											</td>
										</tr>

										<tr>
											<th>
												解除结果
											</th>
											<td colspan="3">
												<input type="text" disabled="disabled" readonly="readonly"
													value="${rs.cxjg }" />
											</td>
										</tr>
									</logic:equal>
								</logic:notEqual>
								<!-- 浙江理工  begin-->
								<logic:equal value="10338" name="xxdm">
									<logic:equal value="yes" name="zjlgLxck">
										<tr>
											<th>
												留校察看解除时间
											</th>
											<td align="left">
												${rs.cxclsj }
											</td>
											<th>
												留校察看解除文号
											</th>
											<td align="left">
												${rs.cxclwh }
											</td>
										</tr>
										<tr>
											<th>
												留校察看解除结果
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<td align="right">

											</td>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- end -->
								<!-- 厦门理工  begin-->
								<logic:equal value="11062" name="xxdm">
									<logic:equal value="yes" name="xmlgLxck">
										<tr>
											<th>
												留校察看解除时间
											</th>
											<td align="left">
												${rs.cxclsj }
											</td>
											<th>
												留校察看解除文号
											</th>
											<td align="left">
												${rs.cxclwh }
											</td>
										</tr>
										<tr>
											<th>
												留校察看解除结果
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<td align="right">

											</td>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- end -->
								<!-- 浙江传媒  begin-->
								<logic:equal value="11647" name="xxdm">
									<logic:equal value="yes" name="lxck">
										<tr>
											<th>
												留校察看解除时间
											</th>
											<td align="left">
												${rs.cxclsj }
											</td>
											<th>
												留校察看解除文号
											</th>
											<td align="left">
												${rs.cxclwh }
											</td>
										</tr>
										<tr>
											<th>
												留校察看解除结果
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<td align="right">

											</td>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- end -->
								<!-- 宁波理工除外都显示 -->
								<logic:notEqual value="13022" name="xxdm">
									<logic:equal value="11078" name="xxdm">
										<tr>
											<th>
												原(申诉更改前)
												<br />
												处分类别
											</th>
											<td align="left">
												<html:select name="rs" property="ycflb" style="width:100px"
													styleId="ycflb">
													<html:option value=""></html:option>
													<html:options collection="cflbList" property="cflbdm"
														labelProperty="cflbmc" />
												</html:select>
											</td>
											<th>
												<font color="red">*</font>是否教务处数据
											</th>
											<td>
												<html:select property="sfjw" name="rs" style="width:90px">
													<html:option value="否">否</html:option>
													<html:option value="是">是</html:option>
												</html:select>
											</td>
										</tr>
									</logic:equal>
								</logic:notEqual>
								<tr align="left">
									<th>
										违纪时间
									</th>
									<td align="left">
										<html:text property="wjsj" name="rs" styleId="wjsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('wjsj','y-mm-dd');"></html:text>
									</td>

									<!-- 宁波城市单独 -->
									<logic:equal value="12645" name="xxdm">
										<th>
											处分期限
										</th>
										<td align="left">
											<html:text property="cfnx" name="rs" styleId="cfnx"
												maxlength="20"></html:text>
										</td>
									</logic:equal>
									<logic:notEqual value="12645" name="xxdm">
										<th>

										</th>
										<td align="left">

										</td>
									</logic:notEqual>
								</tr>

								<!-- 广州大学单独的 -->
								<%--						<logic:equal value="11078" name="xxdm">--%>
								<tr>
									<th>
										学生处分确认
									</th>
									<td align="left">
										${rs.xsqr }
									</td>
									<th>
										确认时间
									</th>
									<td align="left">
										${rs.qrsj }
									</td>
								</tr>
								<!-- 重庆工程职业技术学院单独的 -->
								<logic:equal value="12759" name="xxdm">
								<tr>
									<th>
										是否撤销
									</th>
									<td align="left">
										${rs.sfcx }
									</td>
									<th>
										撤销日期
									</th>
									<td align="left">
										${rs.cxrq }
									</td>
								</tr>
								</logic:equal>
							<thead>
								<tr>
									<td align="right" colspan="4">
										<span>历史违纪处分信息 &nbsp;&nbsp;<a  onclick="
											document.all.child4.style.display=(document.all.child4.style.display=='none')?'':'none';getStucfxx();"><font color="red">查看</font> </a>
										</span>
									</td>
								</tr>
							</thead>

							<tr>
								<td colspan="4">
									<div id="child4" style="display:none">
										<table width="100%" border="1" align="center" class="dateline">
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

							<tr align="left">
								<th>
									<logic:equal value="10856" name="xxdm">
										<font color="red">*</font>
										<bean:message key="lable.xsgzyxpzxy" />意见</logic:equal>
									<logic:notEqual value="10856" name="xxdm">
										<font color="red">*</font>违纪事实</logic:notEqual>
									<br />
									<font color="red">(限制在2000字内)</font>
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='bz' style="width:500px"
										onkeyup="checkLen(this,2000)" rows='5' />
								</td>
							</tr>
						</table>
				</logic:notEmpty>
			</logic:notPresent>
			<logic:present name="showXbemy">
				<logic:equal value="showXbemy" name="showXbemy">
					<div class="title">
						<div class="title_img" id="title_m">
							<span id="tipFollow"></span>
						</div>
					</div>
					<logic:empty name="rs">
						<br />
						<br />
						<p align="center">
							有错误发生！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<logic:equal name="rs" property="stuExists" value="no">
							<script>
							    alert("您输入的学号无效!");
							   </script>
						</logic:equal>
						<input type="hidden" id="doType" name="doType"
							value="<bean:write name="doType" scope="request"/>" />
						<input type="hidden" id="pkValue" name="pkValue"
							value="<bean:write name="pkValue" scope="request"/>" />
						<input type="hidden" id="disableEle" name="disableEle"
							value="xm-xb-xy-nj-zy-bj" />
						<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
						<input type="hidden" id="getStuInfo" name="getStuInfo"
							value="xm-xb-xymc-nj-zymc-bjmc" />
						<input type="hidden" id="url" name="url" value="/sjcz/wjcfb.jsp" />
						<input type="hidden" id="clwh" name="clwh"
							value="<bean:write name="clwh"/>" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="5" align="center">
										违纪处分信息维护
									</td>
								</tr>
							</thead>
							<tr>
								<td align="right">
									<font color="red">*</font>学号
								</td>
								<td align="left">
									<html:text name='rs' property="xh" readonly="true" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										选择
									</button>
								</td>
								<td align="right">
									<font color="red">*</font>年度
								</td>
								<td align="left">
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<td rowspan="5" width="15%">
									<img align="center" border="0"
										style="height:133px;width:100px;"
										src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg"
										id="pic"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名
								</td>
								<td align="left">
									<html:text name='rs' property="xm" styleId="xm" />
								</td>
								<td align="right">
									<font color="red">*</font>学年
								</td>
								<td align="left">
									<html:select name="rs" property="xn" style="width:90px"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									性别
								</td>
								<td align="left">
									<html:text name='rs' property="xb" styleId="xb" />
								</td>
								<td align="right">
									<font color="red">*</font>学期
								</td>
								<td align="left">
									<html:select name="rs" property="xq" style="width:90px"
										styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									年级
								</td>
								<td align="left">
									<html:text name='rs' property="nj" styleId="nj" />
								</td>
								<td align="right">
									处分类别
								</td>
								<td align="left">
									<html:select name="rs" property="cflb" style="width:90px"
										styleId="cflb">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />
									
								</td>
								<td align="left">
									<html:text name='rs' property="xymc" styleId="xy" />
								</td>
								<td align="right">
									处分事由
								</td>
								<td align="left">
									<html:select name="rs" property="cfyy" style="width:90px"
										styleId="cfyy">
										<html:option value=""></html:option>
										<html:options collection="cfyyList" property="cfyydm"
											labelProperty="cfyymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									专业
								</td>
								<td align="left">
									<html:text name='rs' property="zymc" styleId="zy" />
								</td>
								<td align="right">
									<font color="red">*</font>处分时间
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfsj" styleId="cfsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cfsj','y-mm-dd');" />
								</td>
							</tr>
							<tr>
								<td align="right">
									班级
								</td>
								<td align="left">
									<html:text name='rs' property="bjmc" styleId="bj" />
								</td>
								<td align="right">
									<font color="red">*</font>处分文号
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfwh" styleId="cfwh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									解除时间
								</td>
								<td align="left">
									<html:text name='rs' property="cxsj" styleId="cxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cxsj','y-mm-dd');" />
								</td>
								<td align="right">
									解除文号
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cxwh" styleId="cxwh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									申诉结果
								</td>
								<td align="left">
									<html:text name='rs' property="ssjg" styleId="ssjg" />
								</td>
								<td align="right">
									违纪时间
								</td>
								<td colspan="2" align="left">
									<input type="text" name="wjsj" id="wjsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('wjsj','y-mm-dd');"
										value="<logic:present name="rs"><bean:write name="rs" property="wjsj"/></logic:present>"/>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									违纪内容
								</td>
								<td colspan="4">
									<html:textarea name='rs' property='bz' style="width:99%"
										rows='5' />
								</td>
							</tr>
						</table>
						<div class="buttontool" align="center">
							<%--					<button type="button" class="button2" onclick="dataCanModi(true)"--%>
							<%--						style="width:80px" id="buttonModi">--%>
							<%--						修 改--%>
							<%--					</button>--%>
							
							<button type="button" class="button2"
								onclick="if(checkXnNd('xn','nd'))dataDoSave11('xn-xq-xh-cfsj-cfwh');"
								style="width:80px" id="buttonSave">
								保 存
							</button>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
								id="buttonClose">
								关 闭
							</button>
						</div>
					</logic:notEmpty>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/comm/swbl/swblSq.js"></script>
		<script type="text/javascript">
		function sqSave(){
			if($("xh") && ""==$("xh").value){
				alertError("学号不能为空!");
				return false;
			}
			saveRcsw("/xgxt/rcswSwbl.do?method=swblSq&doType=save");
		}
		
		function sqUpdate(){
			refreshForm("/xgxt/rcswSwbl.do?method=swblXg&doType=save");
		}
		
		function dgsh(shzt){
			refreshForm("/xgxt/rcswSwbl.do?method=swblDgsh&doType=save&shzt="+shzt);
		}
		</script>
	</head>
	<body onload="setTimeout('onShow()',100)">
		<html:form action="/rcswSwbl" method="post"
			enctype="multipart/form-data">
			<!-- 隐藏域 -->
			<logic:iterate name="fjzdList" id="fjzd">
				<input type="hidden" name="zxmzd" value="${fjzd.zd}" />
				<input type="hidden" name="zxmzdm" value="${fjzd.zdm}" />
				<input type="hidden" name="zxmzdlx" value="${fjzd.zdlx}" />
				<input type="hidden" name="zxmlrxz" value="${fjzd.lrxz}" />
				<input type="hidden" name="zxmlrcd" value="${fjzd.lrcd}" />
			</logic:iterate>
			<input type="hidden" name="primary_key" id="primary_key"
				value="${primary_key}" />
			<input type="hidden" name="doType" id="doType" value="${doType }" />
			<input type="hidden" name="shgw" id="shgw" value="${shgw }" />
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}" />
			<input type="hidden" name="xmmc" id="xmmc" value="${xmszMap.xmmc}" />
			<input type="hidden" name="xh" id="xh" value="${xh }" />
			<input type="hidden" name="message" id="message" value="${message}" />
			<input type="hidden" name="sqsj" id="sqsj" value="${sqsj }" />
			<html:hidden property="tableName" styleId="tableName"
				value="view_xsjbxx" />
			<html:hidden property="realTable" styleId="realTable"
				value="view_xsjbxx" />
			<input type="hidden" name="url" id="url"
				value="/xgxt/rcswSwbl.do?method=swblSq&xmdm=${xmdm}" />

			<%@ include file="initList.jsp"%>
			<!-- 隐藏域 -->

			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>各类证书信息维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- 学生信息基本字段显示 begin -->
						<logic:iterate name="xsxxZdList" id="xsxxzd">
							<tr>
								<logic:iterate name="xsxxzd" id="left">
								<logic:equal name="left" property="zd" value="xh">
									<th style="width:16%">
										<logic:equal name="doType" value="add">
											<font color="red"> * </font>
										</logic:equal>
										学号
									</th>
									<td style="width:34%">
										<logic:equal name="doType" value="add">
											<html:text name="stuInfo" property="xh" readonly="true" />
											<logic:equal name="userOnLine" value="teacher">
												<logic:equal name="doType" value="add">
													<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
														选 择
													</button>
												</logic:equal>
											</logic:equal>
										</logic:equal>
										<logic:notEqual name="doType" value="add">
									${stuInfo.xh }
								</logic:notEqual>
									</td>
								</logic:equal>
								</logic:iterate>
								<logic:iterate name="xsxxzd" id="left">
									<logic:notEqual name="left" property="zd" value="xh">
										<th style="width:16%">
											${xsxxzd.left.zdm}
										</th>
										<td style="width:34%">
											${stuInfo.xm }
										</td>
									</logic:notEqual>
								</logic:iterate>
								<logic:equal name="xsxxzd.right" property="zd" value="xh">
									<th style="width:16%">
										<logic:equal name="doType" value="add">
											<font color="red"> * </font>
										</logic:equal>
										学号
									</th>
									<td style="width:34%">
										<logic:equal name="doType" value="add">
											<html:text name="stuInfo" property="xh" readonly="true" />
											<logic:equal name="userOnLine" value="teacher">
												<logic:equal name="doType" value="add">
													<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
														选 择
													</button>
												</logic:equal>
											</logic:equal>
										</logic:equal>
										<logic:notEqual name="doType" value="add">
									${stuInfo.xh }
								</logic:notEqual>
									</td>
								</logic:equal>
								<logic:notEqual name="xsxxzd.right" property="zd" value="xh">
									<th style="width:16%">
										${xsxxzd.right.zdm}
									</th>
									<td style="width:34%">
										${stuInfo.xm }
									</td>
								</logic:notEqual>
							</tr>
						</logic:iterate>
						
						<!-- 学生信息基本字段显示 end -->

						<!-- 判断是否需要上传文件 begin  -->
						<logic:equal name="xmszMap" property="sfsc" value="是">
							<th style="width:16%">
								附件
							</th>

							<!-- 增加界面附件 begin -->
							<logic:equal name="doType" value="add">
								<td colspan="3" style="width:84%">
									<input type="file" name="uploadFile" style="width:400px"
										contenteditable="false" />
									&nbsp;&nbsp;
									<font color="blue">(文件大小小于&lt;20M&gt;)</font>
								</td>
							</logic:equal>
							<!-- 增加界面附件 end -->

							<!-- 修改界面附件 begin -->
							<logic:equal name="doType" value="update">
								<td colspan="3" style="width:84%">
									<input type="file" name="uploadFile" style="width:300px"
										contenteditable="false" />
									&nbsp;&nbsp;
									<font color="blue">(文件大小小于&lt;20M&gt;)</font>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:notEmpty name="stuInfo" property="scwj">
										<a
											href="czxxDtjsDyxx.do?method=downLoadFile&dir=${stuInfo.scwj}"
											target="_blank"><U><font color="blue">下载</font> </U> </a>
									</logic:notEmpty>
								</td>
							</logic:equal>
							<!-- 修改界面附件 end -->

							<!-- 查看界面附件 begin -->
							<logic:equal name="doType" value="view">
								<td colspan="3" style="width:84%">
									<logic:notEmpty name="stuInfo" property="scwj">
										<a
											href="czxxDtjsDyxx.do?method=downLoadFile&dir=${stuInfo.scwj}"
											target="_blank"><U><font color="blue">下载</font> </U> </a>
									</logic:notEmpty>
									<logic:empty name="stuInfo" property="scwj">
										无附件
									</logic:empty>
								</td>
							</logic:equal>
							<!-- 查看界面附件 end -->

							<!-- 审核附件 begin -->
							<logic:equal name="doType" value="dgsh">
								<td colspan="3" style="width:84%">
									<logic:notEmpty name="stuInfo" property="scwj">
										<a
											href="czxxDtjsDyxx.do?method=downLoadFile&dir=${stuInfo.scwj}"
											target="_blank"><U><font color="blue">下载</font> </U> </a>
									</logic:notEmpty>
									<logic:empty name="stuInfo" property="scwj">
										无附件
									</logic:empty>
								</td>
							</logic:equal>
							<!-- 审核附件 end -->
						</logic:equal>
						<!-- 判断是否需要上传文件 end -->
					</tbody>

				</table>
			</div>
			<h3 class="datetitle_01">
				<span> ${xmszMap.xmmc}信息 </span>
			</h3>

			<p>
				<!-- 查询界面不需修改子项目 begin -->
				<logic:equal value="view" name="doType">
					<input type="hidden" name="numAdd" id="numAdd1" style="width: 20px" />
					<input type="hidden" name="numDel" id="numDel1" style="width: 20px" />
				</logic:equal>
				<logic:equal value="dgsh" name="doType">
					<input type="hidden" name="numAdd" id="numAdd1" style="width: 20px" />
					<input type="hidden" name="numDel" id="numDel1" style="width: 20px" />
				</logic:equal>
				<!-- 查询界面不需修改子项目 end -->

				<!-- 增加、修改界面需修改 begin -->
				<logic:notEqual value="view" name="doType">
					<logic:notEqual value="dgsh" name="doType">
						<button type="button" value="+"
							onclick="trAdd('flag1','add','numAdd1','rzqk'); ">
							+
						</button>
						<button type="button" value="-" onclick="trDel('flag1','delRow1');">
							-
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
							onblur="trAdd('flag1','madd','numAdd1','rzqk')" />
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px"
							onblur="trDelAll('flag1','numDel1')" />
						&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
				</logic:notEqual>
				<!-- 增加、修改界面需修改 end -->
			</p>
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tbody>
						<tr>
							<td>
								<div class="formbox">
									<table summary="" class="datelist" align="" width="100%">
										<!-- 打印时第一行不显示- -->

										<thead>
											<tr>
												<td style="width:5%">
													<input type="checkbox" />
												</td>
												<logic:iterate name="fjzdList" id="fjzd">
													<td align="center" nowrap="nowrap">
														${fjzd.zdm }
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<tbody width="100%" align="center" class="tbstyle" id="flag1">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>

				</table>
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>

									<div class="btn">
										<logic:equal name="writeAble" value="yes">
											<logic:equal name="doType" value="add">
												<button type="button" id="buttonSave" onclick="sqSave();">
													保 存
												</button>
											</logic:equal>
										</logic:equal>
										<logic:equal name="writeAble" value="yes">
											<logic:equal name="doType" value="update">
												<button type="button" id="buttonSave" onclick="sqUpdate();">
													保 存
												</button>
											</logic:equal>
										</logic:equal>
										<logic:equal name="doType" value="dgsh">
											<button type="button" id="buttonSave" onclick="dgsh('通过')">
												通 过
											</button>
											<button type="button" id="buttonSave" onclick="dgsh('不通过')">
												不通过
											</button>
											<button type="button" id="buttonSave" onclick="dgsh('退回')">
												退 回
											</button>
										</logic:equal>
										<logic:notEqual name="doType" value="add">
											<logic:notEqual name="doType" value="save">
												<button type="button" onclick="Close();return false;">
													关 闭
												</button>
											</logic:notEqual>
										</logic:notEqual>
									</div>
								</td>
							</tr>
						</tfoot>
						<logic:equal name="doType" value="dgsh">
							<logic:iterate name="shxxInfo" id="shxx">
								<thead>
									<tr>
										<th colspan="4">
											<span>${shxx.gwmc}审核</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th style="width:16%">
											审核结果
										</th>
										<td style="width:34%">
											<!-- 未审核 begin -->
											<logic:equal name="shxx" property="shzt" value="未审核">
												<p>
													<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
														height="18" />
												</p>
											</logic:equal>
											<!-- 未审核 end -->
											<!-- 通过 begin -->
											<logic:equal name="shxx" property="shzt" value="通过">
												<p>
													<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
														height="18" />
												</p>
											</logic:equal>
											<!-- 通过 end -->
											<!-- 不通过 begin -->
											<logic:equal name="shxx" property="shzt" value="不通过">
												<p>
													<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
														height="18" />
												</p>
											</logic:equal>
											<!-- 不通过 end -->
											<!-- 退回 begin -->
											<logic:equal name="shxx" property="shzt" value="退回">
												<p>
													<img src="<%=stylePath%>images/ico_shth.gif" width="52"
														height="18" />
												</p>
											</logic:equal>
											<!-- 退回 end -->
											<!-- 需重审 begin -->
											<logic:equal name="shxx" property="shzt" value="需重审">
												<p>
													<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
														height="18" />
												</p>
											</logic:equal>
											<!-- 需重审 begin -->
										</td>
										<th style="width:16%">
											审核时间
										</th>
										<td style="width:34%">
											${shxx.shsj}
										</td>
									</tr>
									<tr>
										<th>
											审核意见
											<br />
											(限制录入500字)
										</th>
										<td colspan="3">
											<textarea name='shyj' id="shyj" rows='4'
												style="word-break:break-all;width:98%"
												onblur="chLeng(this,500);" type="_moz">${shxx.shyj }</textarea>
										</td>
									</tr>
								</tbody>
							</logic:iterate>
						</logic:equal>
					</table>
				</div>

				<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		
		function xsstDgsh(shzt){
			var url="/xgxt/cdtyXsst.do?method=xsstDgsh&doType=dgsh&shzt="+shzt;
			refreshForm(url);
		}
		
		
		function updateZxdkSq(){
			
			var url="/xgxt/zxdk_xnmz.do?method=zxdkModi&doType=save";
			refreshForm(url);
		}
		
		function displayTbody(id){
			if($(id).style.display=="none"){
				$(id).style.display="";
			}else{
				$(id).style.display="none"
			}
		}
		
		function setXsxxb(){
			$("tableName").value="view_xsjbxx";
			$("realTable").value="view_xsjbxx";
		}
		
		function sendStglb(){
			var url="/xgxt/cdtyXsst.do?method=xmxxInfo";
			showTopWin(url,800,600);
		}
		
		function saveXsstInfo(){
			var url="/xgxt/cdtyXsst.do?method=xsstSq&doType=save";
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/cdtyXsst" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<input type="hidden" name="rtsj" id="rtsj" value="${rtsj}"/>
			<input type="hidden" name="url" id="url" value="cdtyXsst.do?method=xsstSq"/>
			<!-- 隐藏域 -->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<div id="xxPrompt" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							已有申请信息，无法重复申请！
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
			</logic:equal>
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button class="button2" id="btn_bc" onclick="saveXsstInfo()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button class="button2" id="btn_bc" onclick="updateZxdkSq()">
											保 存
										</button>
										</logic:notEqual>
									</logic:equal>
									<logic:equal name="doType" value="dgsh">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
											<button class="button2" id="btn_bc" onclick="xsstDgsh('通过')">
												通  过
											</button>
											<button class="button2" id="btn_bc" onclick="xsstDgsh('不通过')">
												不通过
											</button>
										</logic:notEqual>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead onclick="displayTbody('tbody_jbxx')">
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody id="tbody_jbxx">
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<button class="btn_01" id="" onclick="setXsxxb();sendXx()">
										选 择
									</button>
								</logic:equal>
							</td>
							<th style="width:16%">
								<font color="red">*</font>社团
							</th>
							<td style="width:34%">
								<html:hidden property="stdm" styleId="stdm"
									value="${rs.stdm }" />
								<html:text property="stmc" styleId="stmc" readonly="true"
									value="${rs.stmc }" />
								<logic:equal name="doType" value="add">
									<button class="btn_01" id="" onclick="sendStglb()">
										选 择
									</button>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
							<th style="width:16%">
								社团性质
							</th>
							<td style="width:34%">
								${rs.stxz}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb }
							</td>
							<th style="width:16%">
								社团创始人
							</th>
							<td style="width:34%">
								${rs.stcsr }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj }
							</td>
							<th style="width:16%">
								社团创立时间
							</th>
							<td style="width:34%">
								${rs.stclsj }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td id="" style="width:34%">
								${rs.xymc }
							</td>
								
							<th>
								指导老师
							</th>
							<td id="" style="width:34%">
								${rs.zdls }
							</td>
						</tr>
							<tr>
							<th style="width:16%">
								专业
							</th>
							<td id="" style="width:34%">
								${rs.zymc }
							</td>
							<th>
								社团活动地点
							</th>
							<td id="" style="width:34%">
								${rs.sthddd }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc }
							</td>
							<th style="width:16%">
								
							</th>
							<td style="width:34%">
								
							</td>
						</tr>
						<logic:equal name="doType" value="dgsh">
						<tr>
							<th style="width:16%">
								审核状态
							</th>
							<td style="width:34%">
								${rs.shzt }
							</td>
							<th style="width:16%">
								审核人
							</th>
							<td style="width:34%">
								${rs.shr }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								审核意见
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='shyj' styleId="shyj" style="word-break:break-all;width:99%" onblur="chLeng(this,50);"
										rows='4' />
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<script type="text/javascript">
						$("btn_bc").disabled="true";
					</script>
				</logic:equal>
			</logic:equal>
		</html:form>
	</body>
</html>

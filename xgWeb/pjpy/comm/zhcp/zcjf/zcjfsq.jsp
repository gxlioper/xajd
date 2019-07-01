<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//点击加分项目
		function clickJf(xmdm,jfdm){
		
			var sqfs_id = "sqfs_"+xmdm+"_"+jfdm;
			var sqfs_real_id = "sqfs_real_"+xmdm+"_"+jfdm;
			
			if($(sqfs_id)){
				if($(sqfs_id).disabled == true){
					$(sqfs_id).disabled = false;
					$(sqfs_id).value = "";
					$(sqfs_id).focus();
				}else{
					$(sqfs_id).value = "请勾选";
					$(sqfs_real_id).value = "";
					$(sqfs_id).disabled = true;	
				}
			}
		}
		
		//验证加分
		function checkJf(xmdm,jfdm){
		
			var sqfs_id = "sqfs_"+xmdm+"_"+jfdm;
			var xxf_id = "xxf_"+xmdm+"_"+jfdm;
			var sxf_id = "sxf_"+xmdm+"_"+jfdm;
			
			var sqfs = $(sqfs_id).value;
			var xxf = $(xxf_id).value;
			var sxf = $(sxf_id).value;
			
			if(parseInt(sqfs)<parseInt(xxf)){
				$(sqfs_id).value = xxf;
			}else if(parseInt(sqfs)>parseInt(sxf)){
				$(sqfs_id).value = sxf;
			}
		}
		
		//保存加分申请
		function saveJfsq(){
			if(checkInputValue()){
				if (confirm("确定你所申请的加分？")) {
					saveUpdate("/xgxt/zhcpJfsq.do?method=zcjfsq&doType=save","");
				}
			}else{
				alert("请至少申请一项加分!");
			}
		}
		
		//检测录入值
		function checkInputValue(){
			var num =  document.getElementsByName("sqfs").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("sqfs")[i];
				if(obj.disabled == false && obj.value !=""){
					flag = true;
					break;
				}
			}
			
			return flag;
		}
		
		//设置申请分数
		function setSqfs(xmdm,jfdm){
		
			var sqfs_id = "sqfs_"+xmdm+"_"+jfdm;
			var sqfs_real_id = "sqfs_real_"+xmdm+"_"+jfdm;

			$(sqfs_real_id).value = $(sqfs_id).value;
		}
		
		//设置申请理由
		function setSqly(xmdm){
			var sqly_id = "sqly_"+xmdm;
			var sqly = $(sqly_id).value;
			
			var num =  document.getElementsByName("sqly").length;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("sqly")[i];
				var id = obj.id;
				var inputId = id.split('_')[0]+"_"+id.split('_')[1];
				if(inputId == sqly_id){
					obj.value=sqly;
				}
			}
		}
		
		function setDivHeight(){
			if($("div_jfxm")){
				if($("tb_xsxx").style.display == "none"){
					$("div_jfxm").style.height="400px";
				}else{
					$("div_jfxm").style.height="250px";
				}
			}			
		}
		
		function showZcjfsm(){
		
			tipsWindown("系统提示","id:div_zcjfsm","570","390","true","","true","id");
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt">
			<h3>
				<span>综测周期：</span>
			</h3>
			<p>
				评奖学年(${pjxn })&nbsp;&nbsp;
				<logic:equal name="zczq" value="xq">
				评奖学期(${pjxqmc })&nbsp;&nbsp;
				</logic:equal>
				
				综测周期
				<logic:equal name="zczq" value="xn">
					(学年)
				</logic:equal>
				<logic:equal name="zczq" value="xq">
					(学期)
				</logic:equal>		
			</p>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/zhcpJfsq">
			<!-- 隐藏域 -->
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="学生"/>
			<input type="hidden" id="url" name="url" value="/xgxt/zhcpJfsq.do?method=zcjfsq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zcjflx" value="${zcjflx }"/>
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- 评奖基本信息 -->
<%--					<thead>--%>
<%--						<tr onclick="showHiddenNr('tb_zcxx')" style="cursor: hand">--%>
<%--							<th colspan="4">--%>
<%--								<span>综测基本信息</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody id="tb_zcxx">		--%>
<%--						<tr>--%>
<%--							<th width="16%">--%>
<%--								评奖学年--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxn }--%>
<%--							</td>--%>
<%--							<th width="16%">--%>
<%--								评奖学期--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxqmc }--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								评奖年度--%>
<%--							</th>--%>
<%--							<td>							--%>
<%--								${pjnd }--%>
<%--							</td>--%>
<%--							<th>--%>
<%--								综测周期--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<logic:equal name="zczq" value="xn">--%>
<%--									学年--%>
<%--								</logic:equal>--%>
<%--								<logic:equal name="zczq" value="xq">--%>
<%--									学期--%>
<%--								</logic:equal>		--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
					<!-- 评奖基本信息 end-->
					
					<!-- 学生基本信息 -->
					<thead>
						<tr onclick="showHiddenNr('tb_xsxx');setDivHeight();" style="cursor: hand">
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_xsxx">		
						<tr>
							<th width="16%">
								学号	
							</th>
							<td width="34%">		
								<!-- 学生用户 -->
								<logic:equal name="userType" value="stu">
									${rs.xh }
								</logic:equal>					
								<!-- 学生用户 -->
								<logic:notEqual name="userType" value="stu">
									<input type="text" name="xh" id="xh" value="${rs.xh }" readonly="readonly"/>
									<button type="button" onclick="sendXx();return false;"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>	
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">							
								${rs.xm }
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
					</tbody>
					<!-- 学生基本信息 end-->
					
					<!-- 综测加分信息 -->
					<logic:notEmpty name="rs" property="zcxmList">
						<thead>
							<tr  style="cursor: hand"  onclick="showHiddenNr('tb_zcjf');">
								<th colspan="4">
									<span>申请加分项目</span>
								</th>
							</tr>
						</thead>
						<tbody id="tb_zcjf">		
							<tr>
								<td colspan="4">
									<!-- 提示信息 end-->
									<div class="prompt">
											提示：1、需要申请相关的加分请先对其进行勾选。2、大于（小于）上限分（下限分）的话，以上限分（下限分）为准。<br/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、如不注明加分理由，加分无效。
											<a href="#" onclick="showZcjfsm();return false;"><font color="blue"><U>综测加分说明</U></font></a>
										<a class="close" title="隐藏"
										   onclick="this.parentNode.style.display='none';"></a>
									</div>
									<!-- 提示信息 end-->									
									<div style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;" id="div_jfxm">		
										<logic:iterate name="rs" property="zcxmList" id="zcxm">
											<table width="100%">
												<thead>
													<tr onclick="showHiddenNr('tb_jfxm_${zcxm.xmdm }')" style="cursor: hand">
														<td colspan="2">
															${zcxm.xmmc }
														</td>
													</tr>
												</thead>
												<tbody id="tb_jfxm_${zcxm.xmdm }">
													<tr>
														<td width="16%">
															加分项目
														</td>
														<td>
															<table>
																<%int n=0; %>
																<tr>
																<logic:iterate name="zcxm" property="xmList" id="jfxm">
																	<%n++; %>
																	<logic:empty name="jfxm" property="sqfs">
																		<td>
																			<input type="checkbox" id="ch_${zcxm.xmdm }_${jfxm.jfdm }" onclick="clickJf('${zcxm.xmdm }','${jfxm.jfdm }')"/>
																			<span title="${jfxm.jfmc }">${jfxm.showmc }</span>
																			<input type="hidden" name="xmdm" value="${zcxm.xmdm }"/>
																			<input type="hidden" name="jfdm" value="${jfxm.jfdm }"/>
																			<input type="hidden" name="sqfs" id="sqfs_real_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqfs }"/>
																			<input type="hidden" name="sqly" id="sqly_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqly }"/>
																			<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																		</td>
																		<td>
																			<input type="text" style="width: 50px"
																				disabled="disabled" value="请勾选" 
																				onblur="checkJf('${zcxm.xmdm }','${jfxm.jfdm }');setSqfs('${zcxm.xmdm }','${jfxm.jfdm }')"
																				id="sqfs_${zcxm.xmdm }_${jfxm.jfdm }"
																				onkeydown="return onlyNum(this,2)"
																				onmousedown="return onlyNum(this,2)"
																				maxlength="2" 
																				style="width : 80px;ime-mode:disabled"/>
																		</td>
																		<td>
																			(申请范围：${jfxm.xxf }-${jfxm.sxf }分)
																			<input type="hidden" id="xxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.xxf }"/>
																			<input type="hidden" id="sxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sxf }"/>
																		</td>
																	</logic:empty>
																	<logic:notEmpty name="jfxm" property="sqfs">
																		<td>
																			<input type="checkbox" id="ch_${zcxm.xmdm }_${jfxm.jfdm }" checked="checked" onclick="clickJf('${zcxm.xmdm }','${jfxm.jfdm }')"/>
																			<span title="${jfxm.jfmc }">${jfxm.showmc }</span>
																			<input type="hidden" name="xmdm" value="${zcxm.xmdm }"/>
																			<input type="hidden" name="jfdm" value="${jfxm.jfdm }"/>
																			<input type="hidden" name="sqfs" id="sqfs_real_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqfs }"/>
																			<input type="hidden" name="sqly" id="sqly_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqly }"/>
																			<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																		</td>
																		<td>
																			<input type="text" style="width: 50px"
																				value="${jfxm.sqfs }" 
																				onblur="checkJf('${zcxm.xmdm }','${jfxm.jfdm }');setSqfs('${zcxm.xmdm }','${jfxm.jfdm }')"
																				id="sqfs_${zcxm.xmdm }_${jfxm.jfdm }"
																				onkeydown="return onlyNum(this,2)"
																				onmousedown="return onlyNum(this,2)"
																				maxlength="2" 
																				style="width : 80px;ime-mode:disabled"/>
																		</td>
																		<td>
																			(申请范围：${jfxm.xxf }-${jfxm.sxf }分)
																			<input type="hidden" id="xxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.xxf }"/>
																			<input type="hidden" id="sxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sxf }"/>
																		</td>
																	</logic:notEmpty>
																	<%if(n%2==0){%>
																	</tr>
																	<%}%>
																</logic:iterate>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															申请理由
															</br>
															<font color="red">(限制录入500字)</font>
														</td>
														<td>
															<textarea id="sqly_${zcxm.xmdm }" rows="5" 
																style="word-break:break-all;width:90%" 
																onblur="setSqly('${zcxm.xmdm }');chLeng(this,500)" type="_moz">${zcxm.sqly }</textarea>
														</td>
													</tr>
												</tbody>
											</table>
										</logic:iterate>
									</div>
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<!-- 综测加分信息 end-->
					
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEmpty name="rs" property="xh">
									<logic:notEmpty name="rs" property="shzt1">
										<logic:equal name="rs" property="shzt1" value="未审核">
											<div class="btn">
												<button type="button" onclick="saveJfsq()" id="buttonSave" style="width: 80px">
													保 存
												</button>									
											</div>
										</logic:equal>
										<logic:notEqual name="rs" property="shzt1" value="未审核">
											<div class="bz"><span class="red">注：申请已被审核</span></div>
										</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="shzt1">
										<div class="btn">
											<button type="button" onclick="saveJfsq()" id="buttonSave" style="width: 80px">
												保 存
											</button>									
										</div>
									</logic:empty>
								</logic:notEmpty>
								<logic:empty name="rs" property="xh">
									<div class="bz"><span class="red">注：请选择需要申请加分的学生</span></div>
								</logic:empty>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
					
					
					<!-- 部门总分计算选择DIV -->
			<div id="div_zcjfsm" style="display:none">
				<div class="open_win01" style="overflow-x:hidden;overflow-y:hidden;height: 380px ">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>综测加分说明</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="2">
								<div  style="overflow-x:hidden;overflow-y:auto;height:300px ">
								一、品德表现分加分方法：
								（一）注册青年志愿者，根据志愿服务时间，可加0～10分（校院团委负责）。<br/>
								（二）假期社会实践活动受到院及院以上表彰者加5分。（校团委负责）<br/>
								（三）凡担任学生干部的同学，任期满一年，根据工作成绩和工作态度，由所在部门按规定在九月<br/>
								中旬以书面方式通知班主任加分（身兼数职的学生干部只取最高一项加分）。<br/>
								1. 校、院学生会、社团联合会主席、副主席加0～15分（团委负责）。<br/>
								2. 校、院学生会部长、院团委委员、系（专业组或年级组）团总支副书记、校级学生社团负责人加<br/>
								0～12分（团委负责）。<br/>
								3. 系（专业组或年级组）团总支委员、各班班长、团支部书记加0～9分（班主任负责）。<br/>
								4. 各班班委、团支部委员、校、院学生会成员、院级学生社团负责人加0～6分（班主任负责）。<br/>
								5. 课代表加0－3分（班主任负责）。<br/>
								（四）无偿献血者，加10分。<br/>

								二、学业表现分加分方法：<br/>
								（一）学习附加分：<br/>
								1．通过北京市及以上统考统测按以下标准加分：<br/>
								（1）非外语专业学生：专科、本科二年级时通过CET－4加2分；通过CET－6加4分。<br/>
								（2）非计算机类专业学生：通过计算机Ⅱ级考试加2分；通过计算机Ⅲ级考试加4分。<br/>
								2．参加数学、英语等学科竞赛，获国家级一、二、三等奖，分别加5、4、3分；获市级一、二、三<br/>
								等奖，分别加3、2、1分；获校级一、二、三等奖，分别加2、1、0.5分。<br/>
								（二）科研附加分：<br/>
								参加课余科技活动，其研究成果获市级以上奖励者，经所在<bean:message key="lable.xb" />学生工作办公室批准，加0.5～3分；<br/>
								在校内外正式出版的学术刊物发表论文的，加0.5～3分。<br/>
								以上各项可累计加分。<br/>

								三、文体表现附加分的加分方法：<br/>
								文体表现的加分，包括以下三个小项，最高20分。<br/>
								（一）凡参加运动会的运动员、裁判员，每次加1分；获前六名的运动员每次每项依次另加7、6、<br/>
								5、4、3、2分；破记录者另加2分。<br/>
								（二）校院组织的大型文艺演出活动的参加者，每次加3分。获文艺竞赛活动一、二、三等奖的节<br/>
								目参加者分别加5、4、3分，参加者分别加2、1分。参加高校文艺演出获一、二、三等奖者（含集<br/>
								体奖的每位成员）分别加10、9、8分，参加者加6分。<br/>
								</td>
								</div>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="取 消" onclick="closeWindown();return false;">
											确 认
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
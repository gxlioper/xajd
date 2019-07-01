<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript">

		function tipsAndSave(url){
			BatAlert.showTips("正在保存，请稍后！");
			saveData(url,'');
		}
		
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		//保存综合测评信息
		function saveXssqInfo(){
			
			//保存前提示信息
			confirmInfo("该操作将会保存已修改信息，是否继续？",function(tag){
				//判断是否选择“确定”按钮
				if(tag=="ok"){
				
					//创建一个json对象
					var parameter={};
					
					//指定获取的控件类型，进行循环
					var hid_obj=jQuery("input,radio,select,textArea,checkbox").each(function(){
						
						//获取表单控件name
						var name=jQuery(this).attr("name");
						//构建json对象
						parameter[name]=jQuery(this).val();
					});
					
					//保存URL
					var url = "general_wdpj_xssq_ajax.do?method=saveXssqInfo";
					
					//------------AJAX保存 begin -------------
					jQuery.ajaxSetup({async:false});
						jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					
					jQuery.ajaxSetup({async:true});
					//------------AJAX保存 end -------------
					
					
				}else {
				
					return false;
				}
			});
		}
		
		
		//显示学生申请
		function showWdpjView(xmdm){
			var url = "general_pjpy.do?method=wdpjXmshDetail&xmdm="+xmdm;
			url+="&opera=view";
			url+="&xh="+${userName}
			showTopWin(url,"800","600");
		}
		
	</script>
	</head>
	<body>
		<html:form action="/pjpy_comm_xmsq" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 我的评奖 - 学生申请查询</a>
				</p>
			</div>
			<input type="hidden" name="tjr" value="${userName }" />
			<input type="hidden" name="xh" value="${wdpjXssqInfo.stuInfo.xh }" />
			<input type="hidden" name="xmdm" value="${wdpjXssqInfo.xmInfo.xmdm }" />
			<input type="hidden" name="xmmc" value="${wdpjXssqInfo.xmInfo.xmmc }" />
			<input type="hidden" name="xn" value="${wdpjXssqInfo.xmInfo.pjxn }" />
			<input type="hidden" name="xq" value="${wdpjXssqInfo.xmInfo.pjxq }" />
			<input type="hidden" name="nd" value="${wdpjXssqInfo.xmInfo.pjnd }" />
			<input type="hidden" name="pjxn" value="${wdpjXssqInfo.xmInfo.pjxn }" />
			<input type="hidden" name="pjxq" value="${wdpjXssqInfo.xmInfo.pjxq }" />
			<input type="hidden" name="pjnd" value="${wdpjXssqInfo.xmInfo.pjnd }" />
			<input type="hidden" name="opera" value="${opera}" />
			<input type="hidden" name="sqsj" value="${sqsj}" />
			<input type="hidden" id="url"
				name="/xgxt/pjpy_comm_xmsq.do?method=xssqUpdate" />
			<input type="hidden" id="refreshParent" value="yes" />

			<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);"
				style="display: none"></button>

			<div class="tab"
				style="width:100%;height:400px;overflow-x:hidden;overflow-y:hidden;" />
				<table class="formlist" width="93%">
					<thead onclick="hiddenMk('mk_xmxx')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="4">
								<span>本次评奖信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_xmxx">
						<tr>
							<td colspan="4">
								<table class="formlist" width="100%" 	height="120px" >
									
									<logic:empty name="xssqByZqInfo">
									<tr>
										<td colspan="6">
											<div align="center" style="vertical-align: middle;">
												不存在本次评奖申请信息
											</div>
										</td>
									</tr>
									</logic:empty>
									<logic:notEmpty name="xssqByZqInfo">
										<tr>
											<th>
												<div align="center">
													项目名称
												</div>
											</th>
											<th>
												<div align="center">
													项目类型
												</div>
											</th>
											<th>
												<div align="center">
													项目类别
												</div>
											</th>
											<th>
												<div align="center">
													结果
												</div>
											</th>
											<th>
												<div align="center">
													获得金额
												</div>
											</th>
<%--											<th>--%>
<%--												<div align="center">--%>
<%--													操作--%>
<%--												</div>--%>
<%--											</th>--%>
										</tr>
										<logic:iterate name="xssqByZqInfo" id="xssqByZq">
											<tr height="22px">
												<td>
													<div align="center">
														${xssqByZq.xmmc }
													</div>
												</td>
												<td>
													<div align="center">
														${xssqByZq.xmlxmc }
													</div>
												</td>
												<td>
													<div align="center">
														${xssqByZq.xmxzmc }
													</div>
												</td>
												<td>
													<div align="center">
														${xssqByZq.jg }
													</div>
												</td>
												<td>
													<div align="center">
														${xssqByZq.xmje }
													</div>
												</td>
<%--												<td>--%>
<%--													<div align="center">--%>
<%--														<a href="#" onclick="showWdpjView('${xssqByZq.xmdm}');return false"><font color="blue">查看详细</font> </a>--%>
<%--													</div>--%>
<%--												</td>--%>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</tbody>
					<thead onclick="hiddenMk('mk_lsxx')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="4">
								<span>历史评奖信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lsxx">
						<tr>
							<td colspan="4">
								<table class="formlist" width="100%">
									
									<logic:empty name="xssqInfo">
										<tr>
											<td	height="120px" colspan="6" >
												<div align="center" style="vertical-align: middle;">
													不存在历史评奖信息
												</div>
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="xssqInfo">
										<tr>
										<td>
											<div align="center">
												学年
											</div>
										</td>
										<td>
											<div align="center">
												学期
											</div>
										</td>
										<td>
											<div align="center">
												项目名称
											</div>
										</td>
										<td>
											<div align="center">
												项目类型
											</div>
										</td>
										<td>
											<div align="center">
												项目类别
											</div>
										</td>
										<td>
											<div align="center">
												获得金额
											</div>
										</td>
									</tr>
									<logic:iterate name="xssqInfo" id="xssq">
										<tr>
											<td>
												<div align="center">
													${xssq.xn}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xq}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmmc}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmlxmc}
												</div>
											</td>
											<td>	
												<div align="center">
													${xssq.xmxzmc}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmje}
												</div>
											</td>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table class="formlist" width="93%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<logic:equal value="add" name="opera">
									<button type="button" name="提交" id="buttonSave" onclick="saveXssqInfo()">
										保 存
									</button>
								</logic:equal>
								<logic:equal value="update" name="opera">
									<button type="button" name="提交" onclick="saveXssqInfo()">
										修 改
									</button>
								</logic:equal>
								<button type="button" name="关闭" onclick="window.close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<logic:present name="message">
			<script type="text/javascript">
			alertInfo('${message }');
		</script>
		</logic:present>
		<logic:notEqual value="" name="tjMessage">
			<script type="text/javascript">
			jQuery('#buttonSave').attr('disabled','disabled');
			jQuery('#buttonSave').attr('class','disabled');
			alertInfo('${tjMessage }');
		</script>
		</logic:notEqual>
	</body>
</html>

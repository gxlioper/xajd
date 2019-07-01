<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/messageFunctaion.js"></script>
		<script type="text/javascript">
			//查询结果集
			function searchRs(){
			
				allNotEmpThenGo('szdw_teaInfo.do?method=teaManage');
			}
		
		
			function showAddPage(){
				tipsWindown("系统提示","id:addJbxx","380","170","true","","true","id");
			}
			
			function checkZgh(){
				var isExists = true;
				var zgh = jQuery('#zgh').val();
				if (zgh == ''){
					alertInfo('职工号不能为空！');
					return false;
				}
				//验证是否存在
				jQuery.post('szdw_teaInfo.do?method=checkZgh',{zgh:zgh},function(data){
					if (data == 'true'){
						//jQuery('p',jQuery('#yzMessage')).text('职工号"'+zgh+'"已存在，不能使用！');
						//showMessage('yzMessage','msg_error');
						alertInfo('职工号"'+zgh+'"已存在，不能使用！');
						isExists = false;
						return false;
						
					} 
				});
				return isExists;
			}
			
			
			function addTea(){
				
				var zgh = jQuery('#zgh').val();
				
				jQuery.ajaxSetup({async:false});
				if (checkZgh()){
					var xm = jQuery('#xm').val();
					if (xm == ''){
						alertInfo('姓名不能为空！');
						//showMessage('xmMessage','msg_error');
						return false;
					}
					jQuery.post('szdw_teaInfo.do?method=addTea',{zgh:zgh,xm:encodeURI(xm)},function(data){
						if (data != ''){
							confirmInfo('保存成功，是否完善教师详细信息?',function(t){
								if (t == 'ok'){
									showTopWin('szdw_teaInfo.do?method=teaInfo&zgh='+zgh,700,550);
								}
								closeWindown();//关闭弹出层
								searchRs();
							})
						} else {
							alertInfo('保存失败！');
						}
					});
				}
				jQuery.ajaxSetup({async:true});
			}
			
			
			
			function tgZyhxxk(){
				var selected = jQuery('input[name=primarykey_cbv]:checked');
				
				if(selected.length == 0){
					alertInfo("请勾选需批量转入用户库的教师");
					return false;
				} else {
					var array = jQuery("[name=primarykey_cbv]:checked");
						for (var i=0;i<array.length;i++) {
							var ssbm = jQuery(array[i]).parent().parent().find("td").eq(4).html();
							if(ssbm==""){
								alertInfo("所属部门不能为空!");
								return false;
							}
						}
					tipsWindown("系统提示","id:viewYhz","450","190","true","","true","id");
				}
			}
			
			
			function tbTeaInfo() {
				var zdm = jQuery('#zdm').val();
				var dwdm = jQuery('#dwdm').val();
				
				if(zdm == ''){
					alertInfo("请选择所在用户组");
					return false;
				}
				if(dwdm == ''){
					alertInfo("请选择用户所在单位");
					return false;
				}
				
				confirmInfo('您确定要同步所选的教师到用户库吗?',function(t){
					if (t == 'ok'){
						refreshForm('szdw_teaInfo.do?method=tbTeaInfo&zdm='+zdm+'&dwdm='+dwdm);
					}
				})				
			}
		</script>
	</head>

	<body>
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.如果您觉得查询显示列不够完整，可以点击<font color="blue">列选</font>，来增加显示列。<br/>
				2.勾选您希望添加到用户库的记录，执行<font color="blue">添加至用户信息库</font>，可以将该教师添加到用户库，初始密码888888。<br/>
				3.如果您想删除某教师记录，但是无法成功，可能由于该教师还带着班级未被释放。<br/>
				4.双击记录，可以查看该教师的详细信息。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szdw_teaInfo" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
								<li>
									<a href="#" class="btn_zj"
										onclick="showAddPage();return false;"
										id="btn_qx">增加</a>
								</li>
								
								<li>
									<a href="#" class="btn_xg"
										onclick="showUpdateWindow('primarykey_cbv','szdw_teaInfo.do?method=teaInfo','720','550');return false;"
										id="btn_qx">修改</a>
								</li>
								
								<li>
									<a href="#" class="btn_sc"
										onclick="batchData('primarykey_cbv','szdw_teaInfo.do?method=delTeaInfo','del');return false;"
										id="btn_qx">删除</a>
								</li>
								
								<li>
									<a href="#" onclick="impAndChkData();return false;" class="btn_dr"> 导入 </a>
								</li>
								<li>
									<a href="#" onclick="tgZyhxxk();return false;" class="btn_sx"> 添加至用户信息库 </a>
								</li>
							</logic:equal>
							<li>
								<a href="#" class="btn_dc"
									onclick='expData("szdw_teaInfo.do?method=expTeaList");return false;'
									id="btn_qx">导出</a>
							</li>
							<%--<li>
								<a href="#" class="btn_sz"
									onclick='tipsWindown("系统提示","id:szcol","620","360","true","","true","id");return false;'
									id="btn_qx">列选</a>
							</li>
							
						--%></ul>
					</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<%--<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('szdw_teaInfo.do?method=teaManage')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
				</div>
			--%></div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
					<logic:notEmpty name="rs">
							<font color="blue"></font>
					</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" alt=""/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" 
										style="cursor:hand"
										ondblclick="showTopWin('szdw_teaInfo.do?method=teaInfo&doType=view&pkValue=${s.zgh }','720','550');return false;"
									>
										<td>
											<input type="checkbox" name="primarykey_cbv" value="${s.zgh }"/>
										</td>
										<logic:iterate id="c" name="colList">
											<td>
												<bean:write name="s" property="${c }"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=teaInfoForm"></jsp:include>
				
			</div>
			<!-- 增加老师：第一步 快速添加 -->
			<div id="addJbxx" style="display:none">
				<jsp:include flush="true" page="simpleInfo.jsp"></jsp:include>
			</div>
			
			<%--<!-- 教师管理 ：列选 -->
			<div id="szcol" style="display:none;" >
				<jsp:include flush="true" page="colList.jsp"></jsp:include>
			</div>
			
			
			--%><div id="viewYhz" style="display:none">	
				<jsp:include flush="true" page="tbxx.jsp"></jsp:include>
			</div>
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>

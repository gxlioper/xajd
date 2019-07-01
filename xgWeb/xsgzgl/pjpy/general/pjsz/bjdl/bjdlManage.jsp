<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			//
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_bjdl_ajax.do?method=searchPjszBjdl";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_sc").disabled=true;
					$("btn_ccg").disabled=true;
				}else{
					$("btn_sc").disabled=false;
					$("btn_ccg").disabled=false;
				}
			}
		}

		//检测显示班级大类Div
		function checkShowBjdlDiv(){

			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			// 判断是否勾选了记录
			if(num == 0){
				alertError("请您<font color='blue'>勾选</font>需要设置的班级！");
				return false;
			}
			jQuery("#bjdlmc").val("");
			tipsWindown("系统提示","id:div_bjdl","300","150","true","","true","id");
			
		}

		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.班级大类设置主要是为<font color="blue">评奖条件</font>服务，如果您的评奖条件对象都一致的话，可以<font color="blue">无需设置</font>。<br />
				2.如果您<font color="blue">勾选</font>了相关记录，点击<font color="blue">保存(取消)</font>按钮，系统将对勾选记录进行相应的操作。<br />
				3.如果您<font color="blue">未勾选</font>任一记录，点击<font color="blue">保存(取消)</font>按钮，系统将以<font color="blue">过滤条件</font>为准，对相应班级进行相应的操作。<br />
				4.点击<font color="blue">查看</font>按钮，可以查看已经维护好的班级大类信息，如有需要，可以前往代码维护处进行维护新的班级大类。<br />
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 页面来源 -->
						<logic:equal name="forward" value="jbsz">
						<li>
							<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
								返回设置
							</a>
						</li>
						</logic:equal>
						<!-- 页面来源end -->
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<!-- 可否执行 -->
							<li>
								<a href="#" onclick="if(checkItsDis(this)){checkShowBjdlDiv();};return false;" id="btn_ccg" class="btn_ccg">
									班级大类设置
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){checkDeleteBjdl();};return false;"  id="btn_sc" class="btn_sc">
									取消班级大类
								</a>
							</li>
							<!-- 可否执行 end-->
						</logic:equal>
						<!-- 读写权 end-->
						<li>
							<a href="#" onclick="showBjdlDetail();return false;" class="btn_ck">
								班级大类查看
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 班级大类设置弹出层 -->
			<div id="div_bjdl" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>班级大类设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
<%--							<tr>--%>
<%--								<th width="30%">--%>
<%--									班级大类设置--%>
<%--								</th>--%>
<%--								<td width="">--%>
<%--									<input type="radio" name="bjdlsz" id="bjdlsz_new" --%>
<%--										value="yes" --%>
<%--										onclick="setCheckedValue(this);$('tr_new').style.display='';$('tr_old').style.display='none'"--%>
<%--										checked="checked"--%>
<%--										/>新的班级大类--%>
<%--									<br />--%>
<%--									<input type="radio" name="bjdlsz" id="bjdlsz_old"--%>
<%--										value="no" --%>
<%--										onclick="setCheckedValue(this);$('tr_new').style.display='none';$('tr_old').style.display=''" --%>
<%--										/>已有的班级大类--%>
<%--									<input type="hidden" id="bjdlsz_check" value="new"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr id="tr_new">--%>
<%--								<th width="30%">--%>
<%--									班级大类名称--%>
<%--								</th>--%>
<%--								<td width="">--%>
<%--									<input type="text" id="bjdlmc" maxlength="20"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
							<tr id="tr_old" style="">
								<th width="30%">
									班级大类名称
								</th>
								<td width="">
									<select id="select_bjdlmc">
										<logic:notEmpty name="bjdlList">
											<logic:iterate name="bjdlList" id="bjdlRs">
												<option value="${bjdlRs.dm }">${bjdlRs.mc }</option>
											</logic:iterate>
										</logic:notEmpty>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveBjdl();return false;">
											确 定
										</button>
										
										<button type="button"  onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 班级大类设置弹出层 end-->
			
			<!-- 班级大类详细弹出层 begin -->
			<div id="div_bjdl_detail" style="display:none">
				<div class="open_win01" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>班级大类</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									班级大类代码
								</td>
								<td>
									班级大类名称
								</td>
							</tr>
							<logic:notEmpty name="bjdlList">
								<logic:iterate name="bjdlList" id="bjdlMap">
									<tr>
										<td>
											${bjdlMap.dm }
										</td>
										<td>
											${bjdlMap.mc }
										</td>
									</tr>
								</logic:iterate>
								<tr>
									<td colspan="2">				
										班级大类，请前往<font color="blue">代码维护</font>进行维护
									</td>
								</tr>
							</logic:notEmpty>
							<logic:empty name="bjdlList">
								<tr>
									<td colspan="2">
										班级大类，请前往<font color="blue">代码维护</font>进行维护
									</td>
								</tr>
							</logic:empty>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 班级大类详细弹出层 end -->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
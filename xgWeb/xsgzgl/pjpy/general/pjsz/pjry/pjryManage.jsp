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
			
			var url = "general_pjsz_pjry_ajax.do?method=searchPjszPjry";
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
					$("btn_xg").disabled=true;
					$("btn_ccg").disabled=true;
					$("btn_sc").disabled=true;
				}else{
					$("btn_xg").disabled=false;
					$("btn_ccg").disabled=false;
					$("btn_sc").disabled=false;
				}
			}
		}
		
		//撤销班级调整
		function disfrockBjtz(){
		
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			var flag=true;
			
			// 判断是否勾选了记录
			if(num == 0){
				alertError("请您<font color='blue'>勾选</font>需要取消调整班级的学生记录！");
				return false;
			}else{
			
				// 判断勾选记录中是否存在“未调换”的记录
				jQuery("input[name=primarykey_checkVal]:checked").each(function(i){
					var dhbj = jQuery(this).parents("tr").children("td").eq(5).html();
					if(dhbj=="未调换"){
					
						alertInfo("勾选记录中存在<font color='blue'>未调整</font>班级的学生，请确认！");
						flag=false;
					}
				})
			
				
				
				if(flag){
					// 撤销调换班级操作
					confirmInfo("该操作将会<font color='blue'>撤销已勾选学生班级调整信息</font>，是否确定要继续执行该操作？",function(tag){
						
						if(tag=="ok"){
							var xh=new Array();
					
							jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").each(function(i){
								xh[i]=jQuery(this).val();
							});
							
							var url = "general_pjsz_pjry_ajax.do?method=disfrockBjtz";
							var parameter = {
						 		"xh":xh.join("!!@@!!")
							};
							jQuery.post(url,
								parameter,
								function(result){
									$("divWaiting").style.display="none";
									$("divDisable").style.display="none";
									alertInfo(result);
					
									searchRs();
									closeWindown();
									
								}
							);
						}
					
					});
				}
			}
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
				<em>您的当前位置：</em><a>评奖评优 - 基本设置 - 评奖人员库设置</a>
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
				1.结果集中展示的是本次评奖周期的<font color="blue">人员数据</font>。<br/>
				2.如果有学生所在的班级<font color="blue">不是</font>评奖周期中实际存在的班级，请对其进行调整。<br/>
				3.如果您<font color="blue">勾选</font>了相关记录，点击<font color="blue">调整班级</font>按钮，选择希望调整的班级。<br />
				4.如果某学生没有资格参与本次评奖，请执行<font color="blue">设置是否参评</font><br />
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="bjdm_check"/>
			<input type="hidden" id="operation" name="operation" value="${operation }"/>
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
						
						<logic:equal name="userType" value="admin">
							<li>
								<a href="#" onclick="showBjtzDiv();return false;" disabled="true" id="btn_xg" class="btn_xg">
									调整班级
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){showSfcpDiv();}return false;" disabled="true" id="btn_ccg" class="btn_ccg">
									设置是否参评
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){disfrockBjtz();}return false;" disabled="true" id="btn_sc" class="btn_sc">
									取消调整班级
								</a>
							</li>
						</logic:equal>
						
						<logic:notEqual name="userType" value="admin">
							<li>
								<a href="#" onclick="showBjtzDiv();return false;" disabled="true" id="btn_xg" class="btn_xg">
									调整班级
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){showSfcpDiv();}return false;" disabled="true" id="btn_ccg" class="btn_ccg">
									设置是否参评
								</a>
							</li>
							<li style="display: none">
								<a href="#" onclick="if(checkItsDis(this)){showSfcpDiv();}return false;" disabled="true" id="btn_ccg" class="btn_ccg">
									设置是否参评
								</a>
							</li>
							<li style="">
								<a href="#" onclick="if(checkItsDis(this)){disfrockBjtz();}return false;" disabled="true" id="btn_sc" class="btn_sc">
									取消调整班级
								</a>
							</li>
						</logic:notEqual>
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
			
			<!-- 班级调整弹出层 -->
			<div id="div_bjtz" >
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>评奖班级</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									年级
								</th>
								<td width="">
									<html:select property="nj" style="width: 200px" onchange="initZyList();initBjList();" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="">
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td width="">
									<html:select property="xydm" style="width: 200px" styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="">
									专业
								</th>
								<td width="">
									<html:select property="zydm" style="width: 200px" styleId="zy" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="">
									<font color="red">*</font>班级
								</th>
								<td width="">
									<html:select property="bjdm" style="width:200px" styleId="bj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveBjtz()">
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
			<!-- 班级调整弹出层 end-->

			<!-- 参评设置弹出层 -->
			<div id="div_cpsz" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>可否参评设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									参评资格
								</th>
								<td width="">
									<input type="radio" name="sfcp" id="sfcp_yes" 
										value="yes" onclick="setCheckedValue(this)"/>有资格参评
									<input type="radio" name="sfcp" id="sfcp_no"
										value="no" onclick="setCheckedValue(this)" 
										checked="checked"/>无资格参评
									<input type="hidden" id="sfcp_check" value="no"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveCpsz()">
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
			<!-- 参评设置弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
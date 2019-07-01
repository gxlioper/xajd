<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" defer="defer">		
		//初始化列表
		function onShow(){
			//初始化部门
			defaultBmOption();
			//执行查询操作
			setTimeout("searchRs()",1000);
		}
		
		//查询结果集
		function searchRs(){
	
			var url = "jxglJxbz.do?method=getBjfpList";	
			var xn = $("xn").value;
			var sjdm = $("sjdm").value;
			var nj = " ";
			if(jQuery('#nj').combobox('getValue')!=""){
				nj = jQuery('#nj').combobox('getValue');
			}
			var xydm = " ";
			if(jQuery('#xydm').combobox('getValue')!=""){
				xydm = jQuery('#xydm').combobox('getValue');
			}
			var zydm = " "; 
			if(jQuery('#zydm').combobox('getValue')!=""){
				zydm = jQuery('#zydm').combobox('getValue');
			}
			var bjdm = " ";
			if(jQuery('#bjdm').combobox('getValue')!=""){
				bjdm = jQuery('#bjdm').combobox('getValue');
			}
	
			var otherValue = [xn,sjdm,nj,xydm,zydm,bjdm];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}	
		
		//保存军训编制
		function saveBjfp(tag){
			if(tag == "ok"){
				var pk = new Array();
				var num =  document.getElementsByName("primarykey_checkVal").length;
				var n=0;
				
				var flag = false;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("primarykey_checkVal")[i];
					if(obj.checked){
						flag = true;
						pk[n]=obj.value;
						n++;
					}
				}	
				
				if(flag){
					var url = "/xgxt/jxglJxbz.do?method=saveBjfp";
					var sjdm = $("sjdm").value;
					//参数
				 	var parameter = {
				 		"sjdm":sjdm,
				 		"pk":pk.join("!!@@!!")
					};

				 	jQuery.ajaxSetup({async:false});
				 	
				 	$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						doSuccess(result);
						searchRs();
					});

					jQuery.ajaxSetup({async:true});
				}else{
					alertError("请您至少勾选一个需要分配的班级");
				}
			}
		}
		
		jQuery(function(){
			onShow();
		})

		</script>
	</head>
	<body >	
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
				1.仅列出<font color="blue">未被分配</font>的班级。</br>
				<span id="div_help" style="display: none">
				2.勾选您希望分配的班级，请点击<font color="blue">确定编制</font>，完成操作。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/jxglJxbz">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czlx" id="czlx" value="${czlx }"/>
			<input type="hidden" id="xn" value="${rs.xn }" />
			<input type="hidden" id="sjdm" value="${rs.sjdm }" />
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_ccg"
								onclick="confirmInfo('将要分配您所勾选的班级，请确认',saveBjfp);return false;"
								class="btn_ccg">
								确定编制
							</a>
						</li>				
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<div class="searchtab">
					<table>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width: 150px" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									上级编制
								</th>
								<td>
									<html:select name="rs" property="sjdm" disabled="true" style="width:150px">
										<html:option value="">无上级编制</html:option>
										<html:options collection="jxbzList" property="bzdm" labelProperty="bzmc" />
									</html:select>
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width: 150px" title="可录入">
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" styleId="xydm" style="width: 150px" title="可录入">
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zydm" style="width: 150px" title="可录入">
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bjdm" style="width: 150px" title="可录入">
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
				              		<input type="hidden" name="go" value="a" />
				              		<button type="button" class="btn_cx" id="search_go" 
				              		onclick="searchRs();return false;">
				              			查 询
				              		</button>
				              		
				             		<button type="button" class="btn_cz" id="btn_cz" onclick="czSearchCond('nj-xydm-zydm-bjdm');return false;">
				              			重 置
				             		</button>
			            		</div>
			            		<!-- 过滤条件 -->
								<div style="display:none">
									<%@ include file="/comm/search/superSearchArea.jsp"%>
								</div>
								<!-- 过滤条件 end-->
			          		</td>
			       			</tr>
			     		</tfoot>
					</table>
				</div>	
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
				<table align="center" width="100%">
					<tr style="">
						<td width="100%" valign="top" style="position: relative;">
							<div id="div_rs">

							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxglJxbzForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->

		</html:form>
	</body>
</html>
			
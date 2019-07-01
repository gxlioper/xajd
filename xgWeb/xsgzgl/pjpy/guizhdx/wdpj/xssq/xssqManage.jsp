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
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_xssq_ajax.do?method=searchXssqXmxx";
			var ie = "ie";
			var xmlx = jQuery("#xmlx").val();//项目类型
			if(xmlx == ""){
				xmlx = " ";
			}
			var xmxz = jQuery("#xmxz").val();//项目性质
			if(xmxz == ""){
				xmxz = " ";
			}
			var xmmc = escape(jQuery("#xmmc").val());//项目名称
			if(xmmc == ""){
				xmmc = " ";
			}
			
			var otherValue = [ie,xmlx,xmxz,xmmc];
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//显示学生申请
		function showWdpjXssq(xmdm,opera){
			
			if(checkPjtj(xmdm)){
				
				var url = "general_pjpy.do?method=wdpjXssqDetail&xmdm="+xmdm;
				url+="&opera="+opera;
				showTopWin(url,"800","600");
			}
		
		}
		
		
		function checkPjtj(xmdm){
			//判断学生学号
			var xh=${userName};
			
			var parameter={}
			
			parameter["array_xh"]=xh;
			
			parameter["str_xmdm"]=xmdm;
			
			//保存URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_pjtj_ajax.do?method=checkPjtj";
			
			var flag=false;
			
			//------------条件判断 begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!=""){
						alertInfo(result);
						flag=false;
					}else{
						flag=true;
					}
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  flag;
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

		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1.本功能展示本评奖周期学生申请评奖信息。 </span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								返回我的评奖 </a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->

				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									项目类型
								</th>
								<td>
									<select id="xmlx" style="width:150px">
										<option value=""></option>
										<option value="01">
											奖学金
										</option>
										<option value="02">
											荣誉称号
										</option>
									</select>
								</td>
								<th>
									项目性质
								</th>
								<td>
									<select id="xmxz" style="width:150px">
										<option value=""></option>
										<logic:iterate name="xmxzList" id="xmxz">
											<option value="${xmxz.dm }">
												${xmxz.mc }
											</option>
										</logic:iterate>
									</select>
								</td>
								<th>
									项目名称
								</th>
								<td>
									<input type="text" id="xmmc" style="width:150px" />
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>

				<div style="display:none">
					<!-- 过滤条件 -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
					<!-- 过滤条件 end-->
				</div>
			</div>
			<h3 class="datetitle_01">
				<span> 查询结果&nbsp;&nbsp; </span>
			</h3>
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
				<div id="div_rs"
					style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>

				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

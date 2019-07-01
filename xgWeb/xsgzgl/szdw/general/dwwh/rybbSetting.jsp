<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
		
			jQuery.ajaxSetup({async: false});
		
			//创建年级Div
			createNjLvDiv();
			
			var index = jQuery("#hidden_nj").val();
				
			var id = "";
			
			if(index == ""){
				id = "a_nj_0";
			}else{
				id = "a_nj_"+index;
			}
					
			if($(id)){
				$(id).click();
			}
	
			jQuery.ajaxSetup({async: true});
		}
		
		jQuery(function(){
			onShow();
		})
		
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p><%--
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		--%></div>
		<!-- 标题 end-->
		
		<html:form action="/general_szdw" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_lx" value="${lx }"/>
			<input type="hidden" id="hidden_zgh" value="${zgh }"/>
			<input type="hidden" id="hidden_nj" value="${nj }"/>
	
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="goDwwh();return false;">
								返回
							</a>
						</li>
						<li>
							<a href="#" class="btn_ccg" onclick="saveFpbj();return false;">
								保存
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
			</div>
				
			<!-- 项目基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>教师信息</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th align="right" width="15%">
							职工号
						</th>
						<td align="left" width="35%">
							${rs.zgh }
						</td>
						<th align="right" width="15%">
							姓名
						</th>
						<td align="left" width="35%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th align="right" width="">
							所属部门
						</th>
						<td align="left" width="">
							${rs.bmmc }	
						</td>
						<th align="right" width="">
							${lxmc }带班数
						</th>
						<td align="left" width="">
							<span id="span_dbs">${rs.num }</span>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>${lxmc }所带班级选择（注：1、点击左下列表中年级进行切换 ；2、部门后显示【含】则表示此部门存在${rs.xm }老师的所带班级）</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<!-- 组织架构 DIV begin -->
							<div class="main_function">
							
								<!-- 年级 begin -->
								<div class="function_list01" id="div_nj">
									<input type="checkbox" onclick="" checked="checked"/>
				  				</div>
				  				<!-- 年级 end -->
				  				
				  				<!--  其他 begin -->
				  				<div class="function_list02" id="div_other" 
				  					style="overflow: scroll;overflow-x:hidden; height: 500px">
								  	
								</div>
				  				<!-- 其他 end  -->
				  				
				  				 <div class="function_list03"></div>
							</div>
							<!-- 组织架构 DIV end -->
						</td>
					</tr>
				</tbody>
			</table>
			
			<div id="div_fpbj_new" style="display:none"></div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
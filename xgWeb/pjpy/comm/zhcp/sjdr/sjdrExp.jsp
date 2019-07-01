<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" defer="defer">
		//下载模板
		function mbDownLoad(tag){
			if(tag=='ok'){
				//模糊查询
				var input_mhcx = opener.document.getElementById("input_mhcx").value;
				
				//点击查询
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				var n=0;
				var m=3;
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx[m]=jytj[i];
					m++;
				}
				
				var tj_num = opener.document.getElementById("searchTjDiv").getElementsByTagName('input').length;
					
				for(var j=0;j<tj_num;j++){
					var obj = opener.document.getElementById("searchTjDiv").getElementsByTagName('input')[j];
					searchTj[n]=obj.name;
					searchTjz[n]=escape(obj.value);
					n++;
				}
				
				//操作项目
				var czxm = $("czxm").value;
				//导出形式	
				var dcxs = jQuery('input[name=bmlx]:checked').val();
				
				//其他数据
			 	var parameter = {
			 		"dcxs":dcxs,
			 		"czxm":czxm,
					"input_mhcx":input_mhcx,
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!")
				};
				
				$("divWaiting").style.top="120px"
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				var url="zhcpSjdr.do?method=expToExcel";
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//显示导出成功信息
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			//$("divWaiting").style.top="0px"
			alertInfo(result,function(t){
				if (t == 'ok'){
					jQuery('#search_go').click();
				}
			});
		}
		
		//显示导出信息
		function showDcInfo(){
			
			confirmInfo('生成的新模版将覆盖原有的，您确定要这样做吗?',function(t){
			
				if (t == 'ok'){
					createStencil();
				}
			
			})
		}
		
		//生成模版
		function createStencil(){
			opener.document.getElementById("createTj").click();
			//模糊查询
			var input_mhcx = opener.document.getElementById("input_mhcx").value;
			//点击查询
			var searchLx = new Array();
			var searchTj = new Array();
			var searchTjz = new Array();
			
			var n=0;
			var m=3;
			
			searchLx[0]="xy";
			searchLx[1]="zy";
			searchLx[2]="bj";
			
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}
		
			var tj_num = opener.document.getElementById("searchTjDiv").getElementsByTagName('input').length;

			for(var j=0;j<tj_num;j++){
				var obj = opener.document.getElementById("searchTjDiv").getElementsByTagName('input')[j];
				searchTj[n]=obj.name;
				searchTjz[n]=escape(obj.value);
				n++;
			}
			
			//导出形式
			var dcxs = jQuery('input[name=bmlx]:checked').val();
			
			//其他数据
		 	var parameter = {
		 		"dcxs":dcxs,
				"input_mhcx":input_mhcx,
				"searchTj":searchTj.join("!!@@!!"),
				"searchTjz":searchTjz.join("!!@@!!"),
				"searchLx":searchLx.join("!!@@!!")
			};
			
			var url="zhcpSjdr.do?method=showDcInfo";
			jQuery.post(url,parameter,function(result){
				if(result == "false"){
					alertInfo("无法根据所选条件过滤出部门，请重新确认！");
				}else{
					confirmInfo(result,mbDownLoad);
				}
			});
		}
		
		function xzmb(){
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if (checkbox.length > 0){
				refreshForm('zhcpSjdr.do?method=downloadStencil');
				jQuery("#search_go").attr('disabled',false);
	   			jQuery("#search_go").attr('class','btn_cx');
			} else {
				alertInfo('请勾选您要下载的模版!',function(){});
			}
		}
		</script>
	</head>
	<body onload="">

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 综合素质测评 - 综测分维护</a>
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
				1.根据前页面所选择的<font color="blue">过滤条件</font>输出模板。</br>
				2.点击左上角<font color="blue">生成模板</font>，进行模板的创建，以便下载。</br>
				3.导出形式控制以何种数据范围生成<font color="blue">excel</font>，如果所选是“班级”的话，那么在所选路径中生成以班级</br>&nbsp;&nbsp;&nbsp;为单位的学生信息，"<bean:message key="lable.xb" />"同理。</br>
				4.如果在前页面的过滤条件中选择某年级，导出形式选择“班级”，那么系统将输出该年级下的所有</br>&nbsp;&nbsp;&nbsp;班级，其他过滤条件以此类推。</br>
				5.如果未选择条件的话将导出全校所有班级的信息，会严重影响导出<font color="blue">效率</font>，请注意。</br>
				6.导入数据必须使用本功能输出的模板，否则可能<font color="blue">产生异常</font>。</br>
				7.如果您希望下载已生成模板的话，请勾选相应记录，点击<font color="blue">下载模板</font>。</br>
				8.下载模板为RAR格式的压缩包，需要<font color="blue">解压</font>后进行操作。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/zhcpSjdr" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }" />
			<!-- 模板导出Div-->
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showDcInfo();return false;" class="btn_csh">
								生成模版
							</a>
						</li>
						<li>
							<a href="#" onclick="xzmb();return false;" class="btn_down">
								下载模版
							</a>
						</li>
					</ul>
				</div>
				
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>部门类型</th>
								<td>
									<html:radio property="bmlx" value="xy" ></html:radio>
									<bean:message key="lable.xsgzyxpzxy" />
									<html:radio property="bmlx" value="bj" ></html:radio>
									班级
								</td>
								<th>搜索模板名称</th>
								<td>
									<html:text property="wjmc"></html:text>
								</td>	
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('zhcpSjdr.do?method=sjdrExp&czxm=${czxm }')">
											查 询
										</button>
									</div>
								</td> 
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：翻页前请先下载您选择的模版</font>
						</logic:notEmpty> 
					</span>
				</h3>
				<table summary="" class="dateline" width="100%">
					<thead>
						<tr>
							<td>
								<input type="checkbox" disabled="disabled"/>
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
								<tr>
									<td>
										<input type="checkbox" name="primarykey_cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="0">
										<td>
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</tbody>
				</table>
			</div>
			<!--分页显示-->
			<jsp:include flush="true"
				page="/sjcz/turnpage.jsp?form=zhcpSjdrForm"></jsp:include>
			<!--分页显示-->
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


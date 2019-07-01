<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<%@ include file="/syscommon/pagehead_xg.ini"%>	
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/xsxxtjDWR.js'></script>			
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>		
		<script language="javascript" src="js/webPrint.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">

		//初始化
		function onShow(){ 
			searchRs();
			
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xsxx_lsxs_ajax.do?method=searchXsxxLsxs";
			var ie = "ie";
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			setTimeout("setDivHeight()","1500");
		}

		function showXsxxDetail(xh){
			var url = "general_xsxx.do?method=xsxxDetailed&doType=detail&xh="+xh;
			showTopWin(url,"800","600");
		}
		
		//显示部门弹出层
		function showBm(){
			tipsWindown("系统提示","id:div_bm","350","300","true","","true","id");
		}
		
		function jtxxDr(){
			$('realTable').value='xsfzxxb';
			impAndChkData();
			$('realTable').value='xsxxb';
			return false;
		}
		
		function setDivHeight(){
			
			if($("table_rs")){
				
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);
				
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
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.本功能展示的是历史学生信息，在该模块下可以查看历史学生详细信息。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_xsxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="xsxxb"/>
			<input type="hidden" id="tableName" name="tableName" value="xg_view_zjjt_xsxxdc"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfzxk" id="sfzxk" value="${sfzxk }"/>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:notEqual name="sfzxk" value="no">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">
								导出设置
							</a>
						</li>
						<li>
							<a href="#" onclick="configureExportData();return false;" class="btn_dc">
								导出
							</a>
						</li>
					</ul>
				</div>
				</logic:notEqual>
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
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
							<font color="blue">提示：双击记录可查看详细信息</font>
						</span>
				</h3>
				<!-- From内容 -->
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
					
			<!-- 部门选择DIV begin-->
			<div id="div_bm" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>毕业处理</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:200px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:200px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="确 定" onclick="saveBycl()">
											确 认
										</button>
										<button type="button" name="取 消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 部门总分计算选择DIV end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
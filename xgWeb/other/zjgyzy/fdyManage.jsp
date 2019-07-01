<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "zjgyzyXxhz_ajax.do?method=fdyManage";
			var ie = 'ie';
		
			var parameter = {
				"ie":ie
			};
			
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){
				
				parameter[jQuery(this).attr("name")]=escape(jQuery(this).val());
			});
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchGo(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			setTimeout("setDivHeight()","1000");
		}
		
		
		function printFdy(){
	   		
	  		var url = "zjgyzyXxhz.do?method=printFdyExcel";
		   	document.forms[0].action = url;
		   	document.forms[0].target = "_blank";
		   	document.forms[0].submit();
	  	 	document.forms[0].target = "_self";
	   }
		</script>
	</head>
	<body onload="onShow()">

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjgyzyXxhz" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmid" id="xmid" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->

				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="printFdy();return false;"
								class="btn_tj">辅导员信息一览</a>
						</li>
					</ul>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody id="tbody_search_query">
							<tr>
							<th>
								职工号
							</th>
							<td>
								<input type="text" name="zgh" id="zgh" />
							</td>
							<th>
								姓名
							</th>
							<td>
								<input type="text" name="xm" id="xm" />
							</td>
							<th >
								性别
							</th>
							<td>
								<select name="xb" id="xb" style="width:145px">
									<option value=""></option>
									<option value="男">男</option>
									<option value="女">女</option>
								</select>
							</td>
							<th >
								职务
							</th>
							<td>
								<input type="text" name="zwmc" id="zwmc" />
							</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 多功能操作区 end-->

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
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjgyzyXxhzForm"></jsp:include>
				<%--				<script type="text/javascript">--%>
				<%--						$('choose').className="hide";--%>
				<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->


			<div id="div_zjbb" style="display:none">

			</div>

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

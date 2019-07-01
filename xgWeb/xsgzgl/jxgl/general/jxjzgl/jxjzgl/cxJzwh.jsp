<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<!--<script type='text/javascript' src="js/comm/message.js"></script>-->
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzgl.js"></script>
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzTree.js"></script>
		<style>
		span{
			margin:0;
			border:0;
			list-style:none;
		}
		#gy-tree table{
			width:100%;
		}
		#gy-tree table tr td span{
			cursor:pointer;
		}
		#gy-tree table td {
			VERTICAL-ALIGN: top
		}
		#gy-tree table td.bj {
			
		}
		#gy-tree table td.bj {
			WIDTH: 15px;
			height: 15px;
		}
		#gy-tree table td.bj a{
			WIDTH: 15px;
			height: 15px;
			cursor: pointer;
		}
		#gy-tree table td.bj a div{
			WIDTH: 15px;
			height: 15px;
		}
		#gy-tree table td.kg {
			WIDTH: 15px
		}
		UNKNOWN {
			COLOR: #cc0080; TEXT-DECORATION: none
		}
		#gy-tree table A:hover {
			COLOR: #0ff080; TEXT-DECORATION: none
		}
		#gy-tree .hitTd{
			background-color: #deecf5;
		}
		
		</style>
		
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			/**
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxJzxsmdAjax";
			
			var ie = "ie";

			var otherValue = [ie];

			var jzid=getSjid();
			searchRsByAjax(url,jzid);
			
			setTimeout("setDivHeight()","2000");
			**/
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxJzxsmdAjax";
			
			var ie = "ie";

			var jzid=jQuery("#searchJzid").val();
			
			var otherValue = [ie,jzid];
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
		}


		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//初始化页面
		jQuery(function(){
			//初始化建制信息
			jQuery("#showJzmc").html(jQuery("#jxmc").val());
			//初始化树
			initTree();
			initJxjz();
			//alert(leafNode());
			onShow();
		});

		//查询军训建制名单
		function cxJzjzmdPage(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			//url=url+"&jzid="+jzid;
			url=url+"&resultPath=jxjzgl_cxJxjz.do?method=cxJzwh";
			window.location.href=url;
		}

		//查询军训建制名单 已编制
		function cxJzjzmdPageYbz(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&sjjz=1";
			url=url+"&resultPath=jxjzgl_cxJxjz.do?method=cxJzwh";
			window.location.href=url;
		}

		//查询军训建制名单  未编制
		function cxJzjzmdPageWbz(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&sjjz=0";
			url=url+"&resultPath=jxjzgl_cxJxjz.do?method=cxJzwh";
			window.location.href=url;
		}

</script>
	</head>
	<body onload="">

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
				<span> 1."增加下级建制"、"修改本级建制"、"删除本级建制"和"维护建制名单"操作都必须选择左边树形节点<br/>
				2.最低建制下不能再"增加下级建制"<br/>
				3.最高建制不能"修改本级建制"和"删除本级建制"<br/>
				4.选择对应学生再点击"取消编制",可以取消学生编制
				 </span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/jxjzgl_cxJxjz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation"
				value="${operation}" />
			<input type="hidden" name="jxid" id="jxid" value="${jxxxwhModel.jxid }"/>
			<input type="hidden" name="jxmc" id="jxmc" value="${jxxxwhModel.jxmc }"/>
			<input type="hidden" name="jddjdmZd" id="jddjdmZd" value="${jxdjzdModel.djdm }"/>
			<input type="hidden" name="searchJzid" id="searchJzid"  value="${jxxxwhModel.jxid }"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="window.location.href='jxjzgl_jxjzgl_cxJxjz.do';return false;"
								class="btn_fh"> 返回 </a>
						</li>
						<li>
							<a href="#" onclick="zjXjjz();return false;"
								class="btn_zj"> 增加下级建制 </a>
						</li>
						<li>
							<a href="#" onclick="xgJxjz();return false;"
								class="btn_xg"> 修改本级建制 </a>
						</li>
						<li>
							<a href="#" onclick="checkJzmdByJxcjAndJxbx();return false;"
								class="btn_sc"> 删除本级建制 </a>
						</li>
						<li>
							<a href="#" onclick="cxWhjzmd();return false;"
								class="btn_zj"> 维护建制名单 </a>
						</li>
						<li>
							<a href="#" onclick="qxbz();return false;"
								class="btn_sc"> 取消编制 </a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->

			</div>
			
			<!--标题start-->
			    <h3 class="datetitle_01">
			    <span id="jxjzrsTj">军训名称：${jxxxwhModel.jxmc} &nbsp;参训人数： <a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPage()">${rsTj.cxrs }</a>人&nbsp;已编制人数：<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageYbz()">${rsTj.ybzrs }</a>人&nbsp;尚未编制人数：<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageWbz()">${rsTj.wbzrs }</a>人</span>
			    </h3>
			<!--标题end-->
			
			<!-- 过滤条件 -->	
			<div style="display: none;">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 过滤条件 end-->
			
			<!-- 多功能操作区 -->
			<div class="leftframe04" style="width:182px;">
				<div class="menulist" style="width:100%;">
					<!--层start-->
					<div class="menutitle" style="width:100%;">
						<h3 style="width:100%;">
							<span class="title">编制列表</span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height: 500px; overflow: auto;"  style="width:97%;">
							<!-- 树形列表开始 -->
							<div id="gy-tree">
	
							</div>
							<!-- 树形列表结束 -->
						</div>
					</div>
					<!--层end-->
				</div>
			</div>
			<div class="rightframe04" style="width:610px;">
				<!--当左边栏目导航隐藏时调用rightframe04_hidden这个class名-->
				<!-- 搜索 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th >
									建制名称
								</th>
								<td colspan="3" id="showJzmc">
								
								</td>
							</tr>
							<tr>
								<th width="15%" >
									教官名称
								</th>
								<td width="35%" id="showJgmc">
									
								</td>
								<th width="15%">
									教官电话
								</th>
								<td id="showJgdh">
									
								</td>
							</tr>
							<tr>
								<th >
									教师名称
								</th>
								<td id="showJsmc">
								
								</td>
								<th >
									教师电话
								</th>
								<td id="showJsdh">
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<!--带有滚动条表单 内容块start-->
				<div class="con_overlfow" id="jzxsxx">
					<!--当左边栏目导航隐藏时调用con_overlfow01_hidden这个class名-->
					<div id="div_rs"
						style="width:100%;overflow-x:auto;overflow-y:hidden;">
					</div>
					</div>
				
				<!--分页显示-->
			<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
			</div>
			<script type="text/javascript">
									$('choose').className="hide";
							</script>
			<br />
			
			<!-- 增加下级建制  -->
			<div id="div_zjjz" style="display:none">
			</div>
			<!-- 增加下级建制 end-->
			
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
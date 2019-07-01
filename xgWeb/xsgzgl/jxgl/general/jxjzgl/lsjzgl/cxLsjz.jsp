<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzgl.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			//searchRs();
			loadJxjzTjb();
		}

		//加载军训建制统计表
		function loadJxjzTjb(){
			var url="lsjzgl_cxLsjz_ajax.do?method=cxLsjzTjbAjax";
			var sjid=jQuery("#sjid").val();
			var parameter={"sjid":sjid};
			jQuery("#div_rs").load(url,parameter,function(data){
				setTimeout("setDivHeight()","200");
			});
		}

		//查询历史建制名单
		function loadLsjzxx(){
			var url="lsjzgl_cxLsjz_ajax.do?method=cxLsjxxxAjax";
			var sjid=jQuery("#sjid").val();
			var parameter={"sjid":sjid};
			jQuery("#lsjxjzxx").load(url,parameter,function(data){
			});
		}
		
		//查询 军训建制推荐表 
		function searchJxjzTjb(){
			loadJxjzTjb();
			loadLsjzxx();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//导出建制推荐表
		function dcLsjzTjb(){
			var url = "lsjzgl_cxLsjz_ajax.do?method=dcJzTjb";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		//查询历史建制名单
		function cxLsjzmd(){
			var url = "lsjzgl_cxLsjz.do?method=cxLsjzmd";
			document.forms[0].action = url;
			document.forms[0].submit();
		}

		//查询军训建制名单
		function cxJzjzmdByJzid(jzid){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&jzid="+jzid+"&sjid="+sjid;
			window.location.href=url;
		}

		//查询军训建制名单
		function cxJzjzmdByJzidAndBjdm(jzid,bjdm){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&jzid="+jzid+"&sjid="+sjid+"&bjdm="+bjdm;
			window.location.href=url;
		}

		//查询军训建制名单
		function cxJzjzmdPage(){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&sjid="+sjid;
			//url=url+"&jzid="+jzid;
			window.location.href=url;
		}

		//查询军训建制名单 已编制
		function cxJzjzmdPageYbz(){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&sjid="+sjid;
			url=url+"&sjjz=1";
			window.location.href=url;
		}

		//查询军训建制名单  未编制
		function cxJzjzmdPageWbz(){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&sjid="+sjid;
			url=url+"&sjjz=0";
			window.location.href=url;
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
				1.点击统计表中的人数查看人数详细信息
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/jxjzgl_cxJxjz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="cxLsjzmd();return false;" class="btn_ck">
								名单查询
							</a>
						</li>
						<li>
							<a href="#" onclick="dcLsjzTjb();return false;" class="btn_dc">
								导出统配表
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
			</div>
			
			<!-- 过滤条件 -->	
			<div style="display: none;">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 过滤条件 end-->
			
			<!-- 查询条件start -->
			<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th width="10%">军训名称</th>
								<td colspan="3">
								<logic:present name="jxxxList">
									<select name="sjid" id="sjid">
											<logic:iterate name="jxxxList" id="jxxx" >
											<logic:equal name="jxxx" property="jxid" value="${jxxxwhModel.jxid}">
											<option selected="selected" value="${jxxx.jxid}">${jxxx.jxmc}</option>
											</logic:equal>
											<logic:notEqual name="jxxx" property="jxid" value="${jxxxwhModel.jxid}">
											<option value="${jxxx.jxid}">${jxxx.jxmc}</option>
											</logic:notEqual>
											</logic:iterate>
									</select>
								</logic:present>
									<button type="button" class="btn_cx" id="search_go"
											onclick="searchJxjzTjb();return false;">
										查 询
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<!-- 查询条件end -->
			
			<!--标题start-->
			    <h3 class="datetitle_01">
			    <span id="lsjxjzxx">军训名称：${jxxxwhModel.jxmc} &nbsp;参训人数： <a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPage()">${rsTj.cxrs }</a>人&nbsp;已编制人数：<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageYbz()">${rsTj.ybzrs }</a>人&nbsp;尚未编制人数：<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageWbz()">${rsTj.wbzrs }</a>人</span>
			    </h3>
			<!--标题end-->
			
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
				<DIV style="OVERFLOW-X: auto; OVERFLOW-Y: hidden; WIDTH: 100%; HEIGHT: 363px"
					id="div_rs">
					
				</DIV>


				<!--分页显示-->
				<div style="clear:both;" style="display: none; ">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
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
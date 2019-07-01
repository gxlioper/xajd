<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">

		</script>
	</head>
	<body onload="">
		
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 公寓工作流程</a>
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
					本页面展示的只是公寓工作流程图，请勿当连接点击，相关事项请点击左侧菜单栏
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- 提示信息 end-->
		<!-- 标题 end-->
		
		<html:form action="/gyglJbsz">
		<div class="procedure_xg">	
			<!-- 基本设置 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num01_xg"></span><span class="title">基本设置</span></h3>
				<ul>
					<!-- 操作时间设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('czsjsz_msg');" 
								onmouseout="hideMsgDiv('czsjsz_msg')">
							<a>
								<span>操作时间设置</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="czsjsz_msg" class="hide" style="left: 120px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							设置床位入住页面“取消入住”按钮功能是否启用
						</p>	
					</div>
				</div>
				
			</div>
			<!-- 基本设置 end-->
		
			<div class="arrow_02"></div>
		
			<!-- 房源管理-->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num02_xg"></span><span class="title">房源管理</span></h3>
				<ul>
					<!-- 楼栋管理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('ldgl_msg');" 
								onmouseout="hideMsgDiv('ldgl_msg')">
							<a>
								<span>楼栋信息管理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 寝室管理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsgl_msg');" 
								onmouseout="hideMsgDiv('qsgl_msg')">
							<a >
								<span>寝室信息管理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 床位管理 -->
					<li>
						<div style="position:relative;z-index:0;" 
								onmousemove="displayMsgDiv('cwgl_msg');" 
								onmouseout="hideMsgDiv('cwgl_msg')">
							<a  >
								<span>床位信息管理</span>				
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="ldgl_msg" class="hide" style="left: 120px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							维护楼栋信息，可同时生成寝室床位
						</p>	
					</div>
				</div>
				
				<div id="qsgl_msg" class="hide" style="left: 280px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							维护寝室信息
						</p>	
					</div>
				</div>
				
				<div id="cwgl_msg" class="hide" style="left: 440px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							维护床位信息，单个床位入住，批量退宿，床位对调，导入住宿信息
						</p>	
					</div>
				</div>
			</div>
			<!-- 房源管理 end-->
			
			<div class="arrow_02"></div>
			
			<!-- 住宿管理 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num03_xg"></span><span class="title">住宿管理</span></h3>
				<ul>
					<!-- 床位分配 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xssq_msg');" 
								onmouseout="hideMsgDiv('xssq_msg')">
							<a >
								<span>床位分配管理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 床位入住 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xmsh_msg');" 
								onmouseout="hideMsgDiv('xmsh_msg')">
							<a  >
								<span>床位入住管理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 住宿管理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('jgcx_msg');" 
								onmouseout="hideMsgDiv('jgcx_msg')">
							<a  >
								<span>住宿信息管理</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="xssq_msg" class="hide" style="left: 120px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							将寝室床位分配至年级<bean:message key="lable.xsgzyxpzxy" />或班级
						</p>
					</div>
				</div>

				<div id="xmsh_msg" class="hide" style="left: 280px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							<bean:message key="lable.xsgzyxpzxy" />安排将学生入住至本<bean:message key="lable.xsgzyxpzxy" />的床位
						</p>	
					</div>
				</div>
				
				<div id="jgcx_msg" class="hide" style="left: 440px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							公寓辅导员管理负责楼栋的住宿信息
						</p>	
					</div>
				</div>
			</div>
			<!-- 住宿管理 end-->
			
			<div class="arrow_02"></div>
			
			<!-- 统计查询 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num04_xg"></span><span class="title">统计查询</span></h3>
				<ul>
					<!-- 公寓住宿情况统计 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('gyzstj_msg');" 
								onmouseout="hideMsgDiv('gyzstj_msg')">
							<a >
								<span>公寓住宿情况统计</span>			
							</a>
						</div>
					</li>
					<!-- 学生住宿情况统计 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xszstj_msg');" 
								onmouseout="hideMsgDiv('xszstj_msg')">
							<a  >
								<span>学生住宿情况统计</span>			
							</a>
						</div>
					</li>
					<!-- 学生住宿信息查询 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xszsgl_msg');" 
								onmouseout="hideMsgDiv('xszsgl_msg')">
							<a  >
								<span>学生住宿信息查询</span>			
							</a>
						</div>
					</li>
					<!-- 学生退宿信息查询 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('tsgl_msg');" 
								onmouseout="hideMsgDiv('tsgl_msg')">
							<a  >
								<span>学生退宿信息查询</span>			
							</a>
						</div>
					</li>
				</ul>
				
			</div>
			<!-- 住宿管理 end-->
			<div class="arrow_02"></div>
			
			<div class="procedure_row_xg">
				<h3><span class="num_xg num05_xg"></span><span class="title">公寓报修</span></h3>
				<ul>
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('wdbxsq_msg');" 
								onmouseout="hideMsgDiv('wdbxsq_msg')">
							<a>
								<span>我的报修申请</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('bxsqcl_msg');" 
								onmouseout="hideMsgDiv('bxsqcl_msg')">
							<a>
								<span>报修申请管理</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="wdbxsq_msg" class="hide" style="left: 120px;top: 490px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							对公寓物品进行报修
						</p>	
					</div>
				</div>
				
				<div id="bxsqcl_msg" class="hide" style="left: 280px;top: 490px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							对报修记录进行处理
						</p>	
					</div>
				</div>
			</div>
		</div>
		</html:form>
	</body>
</html>
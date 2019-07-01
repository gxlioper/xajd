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
				<em>您的当前位置：</em><a>公寓管理 - 公寓流程展示</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/gyglJbsz">
		<div class="procedure_xg">	
			<!-- 公寓设置 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num01_xg"></span><span class="title">公寓设置</span></h3>
				<ul>
					<!-- 基本设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('gyjbsz_msg');" 
								onmouseout="hideMsgDiv('gyjbsz_msg')">
							<a>
								<span>基本设置</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="gyjbsz_msg" class="hide" style="left: 120px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							设置公寓流程开始前得相关参数
						</p>	
					</div>
				</div>
			</div>
			<!-- 公寓设置 end-->
		
			<div class="arrow_02"></div>
		
			<!-- 公寓维护 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num02_xg"></span><span class="title">公寓维护</span></h3>
				<ul>
					<!-- 基础数据处理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('jcsjcl_msg');" 
								onmouseout="hideMsgDiv('jcsjcl_msg')">
							<a>
								<span>基础数据处理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 业务数据处理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('ywsjcl_msg');" 
								onmouseout="hideMsgDiv('ywsjcl_msg')">
							<a >
								<span>业务数据处理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 楼栋信息维护 -->
					<li>
						<div style="position:relative;z-index:0;" 
								onmousemove="displayMsgDiv('ldxxwh_msg');" 
								onmouseout="hideMsgDiv('ldxxwh_msg')">
							<a  >
								<span>楼栋信息维护</span>				
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 自动生成寝室 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('zdscqs_msg');" 
								onmouseout="hideMsgDiv('zdscqs_msg')">
							<a >
								<span>自动生成寝室</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 寝室信息维护 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsxxwh_msg');" 
								onmouseout="hideMsgDiv('qsxxwh_msg')">
							<a  >
								<span>寝室信息维护</span>				
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="jcsjcl_msg" class="hide" style="left: 120px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							在公寓流程开始前，预先导入相关基础数据（建议直接走流程录入数据，不要采取导入的形式，否则，请前往异常数据检测处进行检测）
						</p>	
					</div>
				</div>
				
				<div id="ywsjcl_msg" class="hide" style="left: 280px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							在公寓流程开始前，预先导入相关业务数据（建议直接走流程录入数据，不要采取导入的形式，否则，请前往异常数据检测处进行检测）
						</p>	
					</div>
				</div>
				
				<div id="zdscqs_msg" class="hide" style="left: 580px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							根据楼栋信息(导入或者流程录入)，依照一定的规则，自动生成所需要的寝室信息以及床位信息。</br>完成后请前往寝室信息维护处查看生成结果
						</p>	
					</div>
				</div>
				
				<div id="ldxxwh_msg" class="hide" style="left: 440px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							对楼栋信息进行维护
							</br>
							注：楼栋是维护寝室的前提
						</p>	
					</div>
				</div>
				
				<div id="qsxxwh_msg" class="hide" style="left: 120px;top: 230px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							对寝室信息进行维护
							</br>
							注：寝室信息是寝室分配和床位分配的前提
						</p>	
					</div>
				</div>
			</div>
			<!-- 公寓维护 end-->
			
			<div class="arrow_02"></div>
			
			<!-- 寝室管理 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num03_xg"></span><span class="title">寝室管理</span></h3>
				<ul>
					<!-- 寝室自动分配 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qszdfp_msg');" 
								onmouseout="hideMsgDiv('qszdfp_msg')">
							<a >
								<span>寝室自动分配</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 寝室手动分配 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qssdfp_msg');" 
								onmouseout="hideMsgDiv('qssdfp_msg')">
							<a  >
								<span>寝室手动分配</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 分配结果查看 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsfpjg_msg');" 
								onmouseout="hideMsgDiv('qsfpjg_msg')">
							<a  >
								<span>分配结果查看</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="qszdfp_msg" class="hide" style="left: 120px;top: 340px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							根据基本设置模块所设置的分配对象，将未分配的寝室，自动分配给所选择的部门（以部门人数为准）
						</p>	
					</div>
				</div>

				<div id="qssdfp_msg" class="hide" style="left: 280px;top: 340px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							由用户将寝室分配给基本设置模块所设置的分配对象，另外，也可执行取消已分配寝室的操作
							</br>
							注：为减少用户操作，建议先进行“寝室自动分配”操作，然后再在此对结果进行微调
						</p>	
					</div>
				</div>
				
				<div id="qsfpjg_msg" class="hide" style="left: 440px;top: 340px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							查看已经完成寝室分配的分配记录，另外，也可执行取消已分配寝室的操作
						</p>	
					</div>
				</div>
			</div>
			<!-- 寝室管理 end-->
			
			<div class="arrow_02"></div>
			
			<!-- 床位管理 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num04_xg"></span><span class="title">床位管理</span></h3>
				<ul>
					<!-- 床位自动分配 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('cwzdfp_msg');" 
								onmouseout="hideMsgDiv('cwzdfp_msg')">
							<a  >
								<span>床位自动分配</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 床位手动分配 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('cwsdfp_msg');" 
								onmouseout="hideMsgDiv('cwsdfp_msg')">
							<a  >
								<span>床位手动分配</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 床位手动分配 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('cwfpjg_msg');" 
								onmouseout="hideMsgDiv('cwfpjg_msg')">
							<a  >
								<span>分配结果查看</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="cwzdfp_msg" class="hide" style="left: 120px;top: 440px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							根据寝室分配模块所分配给各个部门的寝室，自动分配将床位分配给所选部门的学生
						</p>	
					</div>
				</div>

				<div id="cwsdfp_msg" class="hide" style="left: 280px;top: 440px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							由用户将学生分入住到相关寝室，为其分配床位，另外，也可执行取消学生入住的操作
							</br>
							注：为减少用户操作，建议先进行“床位自动分配”操作，然后再在此对结果进行微调
						</p>	
					</div>
				</div>
				
				<div id="cwfpjg_msg" class="hide" style="left: 440px;top: 440px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							查看已经完成床位分配的分配记录，另外，也可执行取消已分配床位的操作
						</p>	
					</div>
				</div>
			</div>
			<!-- 床位管理 end-->
			
			<div class="arrow_02"></div>
			
			<!-- 住宿结果 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num05_xg"></span><span class="title">寝室管理</span></h3>
				<ul>
					<!-- 住宿结果管理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('zsggl_msg');" 
								onmouseout="hideMsgDiv('zsggl_msg')">
							<a >
								<span>住宿结果管理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 历史信息管理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('lsxxgl_msg');" 
								onmouseout="hideMsgDiv('lsxxgl_msg')">
							<a  >
								<span>历史信息管理</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 寝室异动管理 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsydgl_msg');" 
								onmouseout="hideMsgDiv('qsydgl_msg')">
							<a >
								<span>寝室异动管理</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="zsggl_msg" class="hide" style="left: 120px;top: 540px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							管理已经入住的学生信息，并可对其执行退宿操作
						</p>	
					</div>
				</div>

				<div id="lsxxgl_msg" class="hide" style="left: 280px;top: 540px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							管理已经退宿的历史学生信息，并可对其进行删除操作
						</p>	
					</div>
				</div>
				
				<div id="qsydgl_msg" class="hide" style="left: 440px;top: 540px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							对已经入住的学生进行宿舍调换操作
							</br>
							
						</p>	
					</div>
				</div>
			</div>
			<!-- 住宿结果 end-->
			
		</div>
		</html:form>
	</body>
</html>
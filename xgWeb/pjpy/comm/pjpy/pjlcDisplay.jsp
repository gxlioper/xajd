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
				<em>您的当前位置：</em><a>评奖评优 - 评奖流程展示</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/gyglJbsz">
		<div class="procedure_xg">	
			<!-- 基本设置 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num01_xg"></span><span class="title">基本设置</span></h3>
				<ul>
					<!-- 评奖基本设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjjbsz_msg');" 
								onmouseout="hideMsgDiv('pjjbsz_msg')">
							<a>
								<span>评奖基本设置</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 评奖人员确定 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjryqd_msg');" 
								onmouseout="hideMsgDiv('pjryqd_msg')">
							<a>
								<span>评奖人员确定</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 评奖项目设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjxmsz_msg');" 
								onmouseout="hideMsgDiv('pjxmsz_msg')">
							<a>
								<span>评奖项目设置</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="pjjbsz_msg" class="hide" style="left: 120px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							设置评奖的评奖学年，学期，年度等全局信息，影响整个评奖流程。
						</p>	
					</div>
				</div>
				
				<div id="pjryqd_msg" class="hide" style="left: 280px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							由于学生在评奖学年所在的班级可能与当前学年所在的班级不同，所以在评奖开始之前，
							需要先确定哪些学生有资格评奖，以及其所在的部门。
							</br>
							注：只有在评奖人员库中的学生，才有资格进行评奖，另外，
							每次评奖第一次操作该模块时，请先执行该模块的“同步部门”以及“同步学生”。
						</p>	
					</div>
				</div>
				
				
				<div id="pjxmsz_msg" class="hide" style="left: 440px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							用于维护本次评奖周期内有哪些项目，需要维护其代码，名称，是否需要人数设置，审核级别，
							金额等等相关属性，只有维护完成并且启用相关选项的项目，才能在“项目设置”模块可以对其进行人数
							设置，条件设置等等，全部完成后方可使该项目进入审核流程，以便学生申请，老师审核。
							</br>
							注：评奖项目需要每个评奖周期都进行设置，本模块提供“复制”上一评奖周期的功能，如果本次评奖与上一评奖项目
							无明显变化的话，可以直接执行此“复制”功能。
						</p>	
					</div>
				</div>
				
			</div>
			<!-- 基本设置 end-->
		
			<div class="arrow_02"></div>
		
			<!-- 项目设置 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num02_xg"></span><span class="title">项目设置</span></h3>
				<ul>
					<!-- 评奖人数设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjrssz_msg');" 
								onmouseout="hideMsgDiv('pjrssz_msg')">
							<a>
								<span>评奖人数设置</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 评奖条件设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjtjsz_msg');" 
								onmouseout="hideMsgDiv('pjtjsz_msg')">
							<a >
								<span>评奖条件设置</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 评奖兼得设置 -->
					<li>
						<div style="position:relative;z-index:0;" 
								onmousemove="displayMsgDiv('pjjdsz_msg');" 
								onmouseout="hideMsgDiv('pjjdsz_msg')">
							<a  >
								<span>评奖兼得设置</span>				
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- 项目调整范围设置 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xmtzsz_msg');" 
								onmouseout="hideMsgDiv('xmtzsz_msg')">
							<a >
								<span>项目调整范围设置</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="pjrssz_msg" class="hide" style="left: 120px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							在项目设置处启用的并且需要人数设置的项目，可以再此处进行人数上限设置。
							</br>
							人数设置针对每个项目，可以设置其年级人数，<bean:message key="lable.xb" />人数，专业加年级人数，班级人数，在审核流程的最后一级进行控制，
							通过人数超过设置人数，则不可被审核通过。
							</br>
							注：具体控制<bean:message key="lable.xb" />人数还是班级人数在项目设置处进行指定，若项目需要人数设置而没有此处进行设置的项目，
							审核无法被通过。
						</p>	
					</div>
				</div>
				
				<div id="pjtjsz_msg" class="hide" style="left: 280px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							为项目设置申请条件，不满足条件的学生，无法在“项目申请”模块对该项目进行申请。
						</p>	
					</div>
				</div>
				
				<div id="pjjdsz_msg" class="hide" style="left: 440px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							设置评奖项目的不可兼得项目，例如，获得“一等奖学金”的前提是不可以获得“二等奖学金”以及
							“三等奖学金”，即一二三等奖学金之间不可兼得，在审核流程的最后一级审核做控制。
						</p>	
					</div>
				</div>
				
				<div id="xmtzsz_msg" class="hide" style="left: 580px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							在审核流程的最后一级，由于人数</br>
							上限，兼得等情况导致的不能获得</br>
							该项目，可以由审核者决定是否需</br>
							要调整到另外一项申请项目，具体</br>
							可以调整到哪些项目在本模块进行</br>
							设置。</br>
							注：调整成功后，将会增加一条该</br>
							学生的调整项目的申请记录加入评</br>
							奖流程，该申请记录仍然需要经过</br>
							审核流程才能使该学生获得该项目。
						</p>	
					</div>
				</div>

			</div>
			<!-- 项目设置 end-->
			
			<div class="arrow_02"></div>
			
			<!-- 评奖流程 -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num03_xg"></span><span class="title">评奖流程</span></h3>
				<ul>
					<!-- 学生申请 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xssq_msg');" 
								onmouseout="hideMsgDiv('xssq_msg')">
							<a >
								<span>学 生 申 请</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 项目审核 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xmsh_msg');" 
								onmouseout="hideMsgDiv('xmsh_msg')">
							<a  >
								<span>项 目 审 核</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- 结果查询 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('jgcx_msg');" 
								onmouseout="hideMsgDiv('jgcx_msg')">
							<a  >
								<span>结 果 查 询</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- 提示信息 -->
				<div id="xssq_msg" class="hide" style="left: 120px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							由学生用户登陆并申请开放申请的所有项目，其中可申请哪些项目，受项目的条件控制所限制。
							</br>
							注：老师用户也可以帮学生申请，同样受条件控制。
						</p>
					</div>
				</div>

				<div id="xmsh_msg" class="hide" style="left: 280px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							登陆用户所在的岗位在审核流中，可以审核应用该审核流的项目，如果用户拥有2个及以上
							的岗位时，审核时需要确认是以哪一级别进行审核，当项目审核情况为最后一级通过的时候，
							需要控制人数上限以及兼得情况，并同时考虑项目调整情况。
							</br>
							注：审核者只能够审核前一级别审核通过的申请记录，如果审核者将某申请记录“退回”的话，
							则需要前一级别重新审核后，才可以继续审核。
						</p>	
					</div>
				</div>
				
				<div id="jgcx_msg" class="hide" style="left: 440px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							查询评奖项目的申请审核结果情况。
						</p>	
					</div>
				</div>
			</div>
			<!-- 评奖流程 end-->
			
		</div>
		</html:form>
	</body>
</html>
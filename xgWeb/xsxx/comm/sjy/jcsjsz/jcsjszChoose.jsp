<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript" defer="defer">

		</script>
	</head>
	<body onload="">
		
		<!-- 提示信息 end-->
		<div class="prompt">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				请选择需要设置的功能
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/sjyJcsjsz">
			<div class="procedure_xg">	
				<!-- 学生信息 -->
				<div class="procedure_row_xg">
					<h3><span class="num_xg num01_xg"></span><span class="title">学生信息</span></h3>
					<ul>
						<!-- 学生基本信息 -->
						<li>
							<div style="position:relative;z-index:0;">
								<a href="sjyJcsjsz.do?method=jcsjszManage">
									<span>学生基本信息</span>			
								</a>
							</div>
						</li>
						<em class="arrow_non"></em>
					</ul>
				</div>
				<!-- 学生信息 end-->
				
				<div class="arrow_02"></div>
				
				<!-- 其他信息 -->
				<div class="procedure_row_xg">
					<h3><span class="num_xg num02_xg"></span><span class="title">其他信息</span></h3>
					<ul>
						<!-- 其他信息 -->
						<li>
							<div style="position:relative;z-index:0;">
								<a>
									<span>其 他 信 息</span>			
								</a>
							</div>
						</li>
						<em class="arrow_non"></em>
					</ul>
				</div>
				<!-- 部门信息 end-->
			</div>
		</html:form>
	</body>
</html>
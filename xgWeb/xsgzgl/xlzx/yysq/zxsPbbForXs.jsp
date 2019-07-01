<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/dategrid.js"></script>
		<script language="javascript" src="js/comm/jquery.hoverdelay.js"></script>
		<script language="javascript" src="js/comm/dateUtils.js"></script>
		<style>
			.open{
				text-align: left;
			}
			.datetab_jy tbody th p {white-space:nowrap;}
		</style>
	</head>

	<body style="width: 99%">
	<input type="hidden"  id="xxdm" value="${xxdm}"/>
		<div class="jobchoose"> 
		  <!--选择周-->
			  <div class="choose_week"  >
			    <h3 onclick="goToday();" class="pointer" >本<br />月</h3>
			    <span id="curMonth"></span>
			    <a class="up" href="javascript:void(0);" onclick="goPre();" title="上月"></a>
			    <a class="next" href="javascript:void(0);" onclick="goNext();" title="下月"></a>
			  </div>
		  </div>
		<div class="rl-ts-bar">
			<h3>
			  	<em style="color:#00569d"><cite style="background:#c8dff1;"></cite>已排班</em>
				<em style="color:#c1e6c1"><cite style="background:#008d00;"></cite>未排班</em>
				<em style="color:#dd0202"><cite style="background:#ffd2d2;"></cite>已预约</em>
			</h3>
		</div>
		<div id="tt"></div>
	</body>
	<script language="javascript" src="xsgzgl/xlzx/yysq/js/zxsPbbForXs.js"></script>
		
</html>

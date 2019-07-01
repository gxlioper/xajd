<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/dategrid.js"></script>
		<script language="javascript" src="js/comm/jquery.hoverdelay.js"></script>
		<script language="javascript" src="js/comm/dateUtils.js"></script>
		<script type="text/javascript" defer="defer" src="xsgzgl/xlzx/zxswh/js/zxsPbDeal.js"></script>
		<style>
			.datetab_jy tbody th p {white-space:nowrap;}
			#tooltip{display:none;position:absolute;width:300px;text-align:left;border:1px solid #cccc00;background:#ffffe3;color:#000;padding:2px 4px;line-height:1.6;}
		</style>
	</head>

	<body >
	<html:form action="/xlzx_zxspb">
		<input type="hidden" id="XLZX_ZXSPB_N" value="${XLZX_ZXSPB_N }" />
		<div class="jobchoose"> 
			  <!--选择周-->
			  <div class="choose_week">
			    <h3 onclick="goToday();" class="pointer" >本<br />月</h3>
			    <span id="curMonth"></span> 
			    <a class="up" href="javascript:void(0);" onclick="goPre();" title="上月"></a>
			    <a class="next" href="javascript:void(0);" onclick="goNext();" title="下月"></a>
	     	 <!-- 湖南城市学院，咨询师排班个性化导出！ -->
		    <logic:equal name="xxdm" value="11527">
			  <div class="buttonbox" style="margin-top:20px;margin-left:220px">
			  	<li ><a href="#"  onclick="exportConfig();return false;" class="btn_dc" >导出</a></li>
			  </div>
		  	 </logic:equal>
			  </div>
			  
			  <!--查询-->
			  <div class="query hys_no1" >
			    <div onmouseover="javascript:document.getElementById('querydiv').style.display='block'" 
			    	 onmouseout="javascript:document.getElementById('querydiv').style.display='none'">
			    </div>
			  </div>
		</div>
			<div class="rl-ts-bar">
				<h3>
					<em style="color:#00569d"><cite style="background:#c8dff1;"></cite>已安排</em>
					<em style="color:#c1e6c1"><cite style="background:#008d00;"></cite>未安排</em>
				</h3>
			</div>
			<div id="tt"></div>
	</body>
</html:form>
</html>

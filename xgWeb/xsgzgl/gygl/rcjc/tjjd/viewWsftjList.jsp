<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/rcjc/tjjd/js/wsftj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				query();
				jQuery("#search_go").bind("click",query);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gyglnew_tjjdwsf">
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="exportData();" class="btn_dc">导出</a></li>	
				</ul>
			</div>
			</logic:equal>
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
				<li class="ha"><a href="javascript:void(0);" onclick="checkTab(this);return false;" link="ld"><span>按楼栋统计</span></a></li>
				<li ><a href="javascript:void(0);" onclick="checkTab(this);return false;" link="xy"><span>按学院统计</span></a></li>
		      </ul>
		    </div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th>
							检查日期
						</th>
						<td>
							<input type="text"  id="kssj" style="width:100px" readonly	onfocus="return showCalendar('kssj','yyyyMMdd',true,'jssj');"/>
							至
							<input type="text"  id="jssj" style="width:100px" readonly	onfocus="return showCalendar('jssj','yyyyMMdd',false,'kssj');"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 统计报表 </span>
			</h3>
			<table id="dataTable"></table>
		</div>
	</body>
</html>

<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/comm/dategrid.js"></script>
	</head>
	<body onload="changeView('month');">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>招聘日历</a>
				</p>
		</div>
		<html:form action="/jyweb" method="post">

			<div id='jobchoose' style="display: '';" class=jobchoose >
				<!--选择周-->
				<div class=choose_week>
					<span><input type="button"
							style="margin-top: 12pt; padding-top: -50pt; width: 80px; height: 25px"
							onclick="goToday()"  value="今天"></input>
					</span><span id='today'>2009-2010学年</span>
					<a class=up onclick="goPre()"></a><a class=next
						onclick="goNext()"></a>
				</div>
				<!--查询-->


				<div class=query style=" ">
					<div
						onmouseover="javascript:document.getElementById('querydiv').style.display='block'"
						onmouseout="javascript:document.getElementById('querydiv').style.display='none'">
						<p style="">
							<a title=使你的手机能同步到日程安排> <!--我的日历  --> </a>
						</p>
					</div>
					<table class=choose_time>
						<tbody>
							<tr>
								<td id="td_day" style="display:none">
									<a onclick="changeView('day')">日</a>
								</td>
								<td id="td_week" style="display:none">
									<a onclick="changeView('week')">周</a>
								</td>
								<td id="td_month" class=current>
									<a onclick="changeView('month');">月</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="tt"></div>
		</html:form>

		<script language=javascript>
	var dd = new DateGrid( {
		proxy : loadData,
		reader : {
			id : 'grrcid',
			title : 'grrcbt',
			date : 'grrcrq',
			time : 'kssj'
		},
		operator : {
			add : function(id) {
				add(id);
			},
			modify : true,
			del : true,
			view : true
		}
	});
	dd.render("tt");
	dd.viewable = true;
	var events = new Array();
	events[0] = {
		"id" : "1",
		"date" : "2010-08-23",
		"time" : "11:00",
		"title" : "我的新日程"
	};
	var startday = dd.startday;
	var endday = dd.endday;
	$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '年'
			+ (dd.virtualDay.getMonth() + 1) + '月' + dd.virtualDay.getDate()
			+ '日';

	
	function loadData() {
		var startday = dd.startday;
		var endday = dd.endday;
	}
	function dwrcall(data) {
		dd.load(data);
	}
	function toMonthView() {
		dd.toMonthView();
		loadData();

	}
	function toDayView() {
		dd.toDayView();
		loadData();

	}
	function toWeekView() {
		dd.toWeekView();
		loadData();

	}
	function goToday() {
		dd.virtualDay = new Date(dd.today);
		dd.refresh();
		loadData();
		$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '年'
				+ (dd.virtualDay.getMonth() + 1) + '月'
				+ dd.virtualDay.getDate() + '日';

	}
	function goPre() {
		dd.goPre();
		loadData();
		$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '年'
				+ (dd.virtualDay.getMonth() + 1) + '月'
				+ dd.virtualDay.getDate() + '日';

	}
	function goNext() {
		dd.goNext();
		loadData();
		$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '年'
				+ (dd.virtualDay.getMonth() + 1) + '月'
				+ dd.virtualDay.getDate() + '日';

	}
	
	function showDateGrid() {

	}
	
	function add(id) {
		var dd_html="<div class='open_win01'>";
		dd_html+="<table width='80%' class='formlist'>";
		dd_html+="<tbody><tr><td>专场招聘列表</td></tr>";
		dd_html+="<tr><td>";

		dwr.engine.setAsync(false);
		jyweb.getZtzpListByTime(id,function(data){
			if (null != data && data.length>0){
				for (var i=0 ; i<data.length; i++){
					dd_html+="<input type='checkbox' name='ztzp' value='";
					dd_html+=data[i].id;
					dd_html+="'/>";
					dd_html+=data[i].zpzt;
					dd_html+="<br/>";
				}
			}else{
				dd_html+="<font color='red'>无可绑定的专场招聘</font>";
			}
		});
		dwr.engine.setAsync(true);
		
		dd_html+="</td></tr>";
		dd_html+="</tbody></table></div>";

		createTmpDiv("请选择您要绑定的专场招聘",dd_html,300,140,true);
	}
	
	function changeView(str) {
		$("td_week").className = '';
		$("td_day").className = '';
		$("td_month").className = '';
		$("td_" + str).className = 'current';
		if (str == 'month') {
			toMonthView();
		}
		if (str == 'day') {
			toDayView();
		}
		if (str == 'week') {
			toWeekView();
		}
	}
	function refurbish() {
		loadData();
	}
	function view(id) {
		showDivWindow("grrcb.do?theAction=view&yhm=${user.yhm}&parameter.id="
				+ id, 700, 400, '');
	}

	function edit(id) {
		showDivWindow("grrcb.do?theAction=edit&parameter.id=" + id, 700, 400,
				'');
	}
	function changeCurrentUser(it) {
		$("currentUserName").value = it.value;
		loadData();

	}
</script>
	</body>
</html>

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
					<em>���ĵ�ǰλ��:</em><a>��Ƹ����</a>
				</p>
		</div>
		<html:form action="/jyweb" method="post">

			<div id='jobchoose' style="display: '';" class=jobchoose >
				<!--ѡ����-->
				<div class=choose_week>
					<span><input type="button"
							style="margin-top: 12pt; padding-top: -50pt; width: 80px; height: 25px"
							onclick="goToday()"  value="����"></input>
					</span><span id='today'>2009-2010ѧ��</span>
					<a class=up onclick="goPre()"></a><a class=next
						onclick="goNext()"></a>
				</div>
				<!--��ѯ-->


				<div class=query style=" ">
					<div
						onmouseover="javascript:document.getElementById('querydiv').style.display='block'"
						onmouseout="javascript:document.getElementById('querydiv').style.display='none'">
						<p style="">
							<a title=ʹ����ֻ���ͬ�����ճ̰���> <!--�ҵ�����  --> </a>
						</p>
					</div>
					<table class=choose_time>
						<tbody>
							<tr>
								<td id="td_day" style="display:none">
									<a onclick="changeView('day')">��</a>
								</td>
								<td id="td_week" style="display:none">
									<a onclick="changeView('week')">��</a>
								</td>
								<td id="td_month" class=current>
									<a onclick="changeView('month');">��</a>
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
		"title" : "�ҵ����ճ�"
	};
	var startday = dd.startday;
	var endday = dd.endday;
	$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '��'
			+ (dd.virtualDay.getMonth() + 1) + '��' + dd.virtualDay.getDate()
			+ '��';

	
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
		$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '��'
				+ (dd.virtualDay.getMonth() + 1) + '��'
				+ dd.virtualDay.getDate() + '��';

	}
	function goPre() {
		dd.goPre();
		loadData();
		$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '��'
				+ (dd.virtualDay.getMonth() + 1) + '��'
				+ dd.virtualDay.getDate() + '��';

	}
	function goNext() {
		dd.goNext();
		loadData();
		$('today').innerHTML = '' + dd.virtualDay.getFullYear() + '��'
				+ (dd.virtualDay.getMonth() + 1) + '��'
				+ dd.virtualDay.getDate() + '��';

	}
	
	function showDateGrid() {

	}
	
	function add(id) {
		var dd_html="<div class='open_win01'>";
		dd_html+="<table width='80%' class='formlist'>";
		dd_html+="<tbody><tr><td>ר����Ƹ�б�</td></tr>";
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
				dd_html+="<font color='red'>�޿ɰ󶨵�ר����Ƹ</font>";
			}
		});
		dwr.engine.setAsync(true);
		
		dd_html+="</td></tr>";
		dd_html+="</tbody></table></div>";

		createTmpDiv("��ѡ����Ҫ�󶨵�ר����Ƹ",dd_html,300,140,true);
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

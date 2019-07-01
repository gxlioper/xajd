function checkxnjxjdmNotNull(){
				if (document.getElementById('xn').selectedIndex<=0 || document.getElementById('jxjdm').selectedIndex<=0){
					alert('学年和奖学金必选，请选择后再查询！');
				}else{
					allNotEmpThenGo('zyjxjsbsearch.do');
				}
			}
function xsdetailsset(url,w,h) {
				if (curr_row == null || curr_row == ''){
					alert('请选择要操作的行！');
				}else{
					url += curr_row.cells[0].getElementsByTagName("input")[0].value;
					url += '&xh=';
					url += curr_row.cells[0].getElementsByTagName("input")[1].value;
					showTopWin(url,w,h);
				}
			}
function zyjxjautosh(url) {
	if (document.forms[0].xy.value == "") {
		alert("请选择要自动审核的学院！");
		return;
	} else {
		var confirmTxt = "自动审核将以";
		if (document.getElementById('bj').selectedIndex>=1) {
			confirmTxt += "\"班级\"为单位进行审核，审核依据为您在\"参数设置\"中设置的各个\"班级\"的人数上限。";
		} else {
			if (document.getElementById('zy').selectedIndex>=1) {
				confirmTxt += "\"专业\"为单位进行审核，审核依据为您在\"参数设置\"中设置的各个\"专业\"的人数上限。";
			} else {
				confirmTxt += "\"学院\"为单位进行审核，审核依据为您在\"参数设置\"中设置的各个\"学院\"的人数上限。";
			}
		}
		confirmTxt += "\n若要改变审核方式，请点击\"取消\"，并修改\"显示方式\"。";
		confirmTxt += "\n\n自动审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动审核，请稍候......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			for (i = 1; i < document.getElementsByTagName("table").length; i++) {
				document.getElementsByTagName("table")[i].style.display = "none";
			}
			showDiv(dd_html, 300, 120);
			alert("点击\"确定\"后开始审核！");
			refreshForm(url);
			return;
		} else {
			return;
		}
	}
}
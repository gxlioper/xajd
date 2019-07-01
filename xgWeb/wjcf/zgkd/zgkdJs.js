
function sendstuwjinfo() {
	var pk = curr_row.getElementsByTagName("input")[0].value;
	var fm = "/wjcf/zgkd/gzjyaddpage.jsp";
	window.dialogArguments.document.forms[0].action = "/xgxt/wjcf_zgkd_gzjyadd.do?from=" + fm + "&pkValue=" + pk;
	window.dialogArguments.document.forms[0].submit();
	window.close();
	return;
}


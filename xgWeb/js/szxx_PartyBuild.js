function partyBuildDataDoSave(mustFill) {
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	var url = "/xgxt/party_member_save.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType=save";
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("保存成功！");
	window.dialogArguments.document.getElementById("search_go").click();
	Close();
}

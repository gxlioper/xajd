// 菜单常量
var sMenuHr = "<tr><td align=center valign=middle height=2><TABLE border=0 cellpadding=0 cellspacing=0 width=128 height=2><tr><td height=1 class=HrShadow></td></tr><tr><td height=1 class=HrHighLight></td></tr></TABLE></td></tr>";
var sMenu1 = "<TABLE border=0 cellpadding=0 cellspacing=0 class=Menu width=150><tr><td width=18 valign=bottom align=center style='background:url(sysimage/contextmenu.gif);background-position:bottom;'></td><td width=132 class=RightBg><TABLE border=0 cellpadding=0 cellspacing=0>";
var sMenu2 = "</TABLE></td></tr></TABLE>";

// 取菜单行
function getMenuRow(s_Disabled, s_Event, s_Image, s_Html) {
	var s_MenuRow = "";
	s_MenuRow = "<tr><td align=center valign=middle><TABLE border=0 cellpadding=0 cellspacing=0 width=132><tr " + s_Disabled + "><td valign=middle height=20 class=MouseOut onMouseOver=this.className='MouseOver'; onMouseOut=this.className='MouseOut';";
	if (s_Disabled == "") {
		s_MenuRow += " onclick=\"parent." + s_Event + ";parent.oPopupMenu.hide();\"";
	}
	s_MenuRow += ">";
	if (s_Image != "") {
		s_MenuRow += "&nbsp;<img border=0 src='batEditor/buttonimage/" + config.StyleDir + "/" + s_Image + "' width=20 height=20 align=absmiddle " + s_Disabled + ">&nbsp;";
	} else {
		s_MenuRow += "&nbsp;";
	}
	s_MenuRow += s_Html + "</td></tr></TABLE></td></tr>";
	return s_MenuRow;
}

// 取标准的format菜单行
function getFormatMenuRow(menu, html, image) {
	var s_Disabled = "";
	if (!eWebEditor.document.queryCommandEnabled(menu)) {
		s_Disabled = "disabled";
	}
	var s_Event = "format('" + menu + "')";
	var s_Image = menu + ".gif";
	if (image) {
		s_Image = image;
	}
	return getMenuRow(s_Disabled, s_Event, s_Image, html);
}

// 取表格菜单行
function getTableMenuRow(what) {
	var s_Menu = "";
	var s_Disabled = "disabled";
	switch (what) {
	  case "TableInsert":
		if (!isTableSelected()) {
			s_Disabled = "";
		}
		s_Menu += getMenuRow(s_Disabled, "TableInsert()", "tableinsert.gif", "\u63d2\u5165\u8868\u683c...");
		break;
	  case "TableProp":
		if (isTableSelected() || isCursorInTableCell()) {
			s_Disabled = "";
		}
		s_Menu += getMenuRow(s_Disabled, "TableProp()", "tableprop.gif", "\u8868\u683c\u5c5e\u6027...");
		break;
	  case "TableCell":
		if (isCursorInTableCell()) {
			s_Disabled = "";
		}
		s_Menu += getMenuRow(s_Disabled, "TableCellProp()", "tablecellprop.gif", "\u5355\u5143\u683c\u5c5e\u6027...");
		s_Menu += getMenuRow(s_Disabled, "TableCellSplit()", "tablecellsplit.gif", "\u62c6\u5206\u5355\u5143\u683c...");
		s_Menu += sMenuHr;
		s_Menu += getMenuRow(s_Disabled, "TableRowProp()", "tablerowprop.gif", "\u8868\u683c\u884c\u5c5e\u6027...");
		s_Menu += getMenuRow(s_Disabled, "TableRowInsertAbove()", "tablerowinsertabove.gif", "\u63d2\u5165\u884c\uff08\u5728\u4e0a\u65b9\uff09");
		s_Menu += getMenuRow(s_Disabled, "TableRowInsertBelow()", "tablerowinsertbelow.gif", "\u63d2\u5165\u884c\uff08\u5728\u4e0b\u65b9\uff09");
		s_Menu += getMenuRow(s_Disabled, "TableRowMerge()", "tablerowmerge.gif", "\u5408\u5e76\u884c\uff08\u5411\u4e0b\u65b9\uff09");
		s_Menu += getMenuRow(s_Disabled, "TableRowSplit(2)", "tablerowsplit.gif", "\u62c6\u5206\u884c");
		s_Menu += getMenuRow(s_Disabled, "TableRowDelete()", "tablerowdelete.gif", "\u5220\u9664\u884c");
		s_Menu += sMenuHr;
		s_Menu += getMenuRow(s_Disabled, "TableColInsertLeft()", "tablecolinsertleft.gif", "\u63d2\u5165\u5217\uff08\u5728\u5de6\u4fa7\uff09");
		s_Menu += getMenuRow(s_Disabled, "TableColInsertRight()", "tablecolinsertright.gif", "\u63d2\u5165\u5217\uff08\u5728\u53f3\u4fa7\uff09");
		s_Menu += getMenuRow(s_Disabled, "TableColMerge()", "tablecolmerge.gif", "\u5408\u5e76\u5217\uff08\u5411\u53f3\u4fa7\uff09");
		s_Menu += getMenuRow(s_Disabled, "TableColSplit(2)", "tablecolsplit.gif", "\u62c6\u5206\u5217");
		s_Menu += getMenuRow(s_Disabled, "TableColDelete()", "tablecoldelete.gif", "\u5220\u9664\u5217");
		break;
	}
	return s_Menu;
}

// 右键菜单
var oPopupMenu = window.createPopup();
function showContextMenu(event) {
	if (!bEditMode) {
		return false;
	}
	var width = 150;
	var height = 0;
	var lefter = event.clientX;
	var topper = event.clientY;
	var oPopDocument = oPopupMenu.document;
	var oPopBody = oPopupMenu.document.body;
	var sMenu = "";
	sMenu += getFormatMenuRow("cut", "\u526a\u5207");
	sMenu += getFormatMenuRow("copy", "\u590d\u5236");
	sMenu += getFormatMenuRow("paste", "\u5e38\u89c4\u7c98\u8d34");
	sMenu += getFormatMenuRow("delete", "\u5220\u9664");
	sMenu += getFormatMenuRow("selectall", "\u5168\u9009");
	sMenu += sMenuHr;
	height += 102;
	if (isCursorInTableCell()) {
		sMenu += getTableMenuRow("TableProp");
		sMenu += getTableMenuRow("TableCell");
		sMenu += sMenuHr;
		height += 286;
	}
	if (isControlSelected("TABLE")) {
		sMenu += getTableMenuRow("TableProp");
		sMenu += sMenuHr;
		height += 22;
	}
	if (isControlSelected("IMG")) {
		sMenu += getMenuRow("", "ShowDialog('dialog/img.htm', 350, 315, true)", "img.gif", "\u56fe\u7247\u5c5e\u6027...");
		sMenu += sMenuHr;
		sMenu += getMenuRow("", "zIndex('forward')", "forward.gif", "\u4e0a\u79fb\u4e00\u5c42");
		sMenu += getMenuRow("", "zIndex('backward')", "backward.gif", "\u4e0b\u79fb\u4e00\u5c42");
		sMenu += sMenuHr;
		height += 64;
	}
	sMenu += getMenuRow("", "findReplace()", "findreplace.gif", "\u67e5\u627e\u66ff\u6362...");
	height += 20;
	sMenu = sMenu1 + sMenu + sMenu2;
	oPopDocument.open();
	oPopDocument.write(config.StyleMenuHeader + sMenu);
	oPopDocument.close();
	height += 2;
	if (lefter + width > document.body.clientWidth) {
		lefter = lefter - width;
	}
	//if(topper+height > document.body.clientHeight) topper=topper-height;
	oPopupMenu.show(lefter, topper, width, height, eWebEditor.document.body);
	return false;
}

// 工具栏菜单
function showToolMenu(menu) {
	if (!bEditMode) {
		return false;
	}
	var sMenu = "";
	var width = 150;
	var height = 0;
	var lefter = event.clientX;
	var leftoff = event.offsetX;
	var topper = event.clientY;
	var topoff = event.offsetY;
	var oPopDocument = oPopupMenu.document;
	var oPopBody = oPopupMenu.document.body;
	switch (menu) {
	  case "paste":
		break;
	  case "table":		// 表格菜单
		sMenu += getTableMenuRow("TableInsert");
		sMenu += getTableMenuRow("TableProp");
		sMenu += sMenuHr;
		sMenu += getTableMenuRow("TableCell");
		height = 306;
		break;
	  case "form":		// 表单菜单
		sMenu += getFormatMenuRow("InsertInputText", "\u63d2\u5165\u8f93\u5165\u6846", "formtext.gif");
		sMenu += getFormatMenuRow("InsertTextArea", "\u63d2\u5165\u8f93\u5165\u533a", "formtextarea.gif");
		sMenu += getFormatMenuRow("InsertInputRadio", "\u63d2\u5165\u5355\u9009\u94ae", "formradio.gif");
		sMenu += getFormatMenuRow("InsertInputCheckbox", "\u63d2\u5165\u590d\u9009\u94ae", "formcheckbox.gif");
		sMenu += getFormatMenuRow("InsertSelectDropdown", "\u63d2\u5165\u4e0b\u62c9\u6846", "formdropdown.gif");
		sMenu += getFormatMenuRow("InsertButton", "\u63d2\u5165\u6309\u94ae", "formbutton.gif");
		height = 120;
		break;
	  case "zoom":		// 缩放菜单
		for (var i = 0; i < aZoomSize.length; i++) {
			if (aZoomSize[i] == nCurrZoomSize) {
				sMenu += getMenuRow("", "doZoom(" + aZoomSize[i] + ")", "checked.gif", aZoomSize[i] + "%");
			} else {
				sMenu += getMenuRow("", "doZoom(" + aZoomSize[i] + ")", "space.gif", aZoomSize[i] + "%");
			}
			height += 20;
		}
		break;
	}
	sMenu = sMenu1 + sMenu + sMenu2;
	oPopDocument.open();
	//alert( sMenu);
	oPopDocument.write(config.StyleMenuHeader + sMenu);
	oPopDocument.close();
	height += 2;
	if (lefter + width > document.body.clientWidth) {
		lefter = lefter - width;
	}
	//if(topper+height > document.body.clientHeight) topper=topper-height;
	oPopupMenu.show(lefter - leftoff - 2, topper - topoff + 22, width, height, document.body);
	return false;
}


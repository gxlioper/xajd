var gridSetting = {
	caption : "行为类别列表",
	pager : "pager",
	url : "rcsw_rcxwwh_rcxwdmwhgl.do?method=getXwlbList&type=query",
	colList : [ {
		label : '所属行为大类',
		name : 'rcxwlbdlmc',
		index : 'rcxwlbdlmc',
		width : '25%'
	}, {
		label : '行为类<br>别代码',
		name : 'rcxwlbdm',
		index : 'rcxwlbdm',
		key : true,
		width : '8%'
	}, {
		label : '行为类别名称',
		name : 'rcxwlbmc',
		index : 'rcxwlbmc',
		width : '18%'
	}, {
		label : '分值<br>类型',
		name : 'fzlxmc',
		index : 'fzlxmc',
		width : '7%'
	}, {
		label : '行为类别<br>分值区间',
		name : 'fzqj',
		index : 'fzqj',
		width : '12%'
	}, {
		label : '备注',
		name : 'rcxwlbbz',
		index : 'rcxwlbbz',
		width : '30%'
	}, {
		label : '最低分值',
		name : 'rcxwlbzdfz',
		index : 'rcxwlbzdfz',
		width : '',
		hidden:true
	}, {
		label : '最高分值',
		name : 'rcxwlbzgfz',
		index : 'rcxwlbzgfz',
		width : '',
		hidden:true
	}, {
		label : '所属行为大类',
		name : 'rcxwlbdldm',
		index : 'rcxwlbdldm',
		hidden : true
	} ],
	sortname : "rcxwlbdm",
	sortorder : "asc"
}

var gridSetting1 = {
    caption : "加分类别列表",
    pager : "pager",
    url : "rcsw_rcxwwh_rcxwdmwhgl.do?method=getXwlbList&type=query",
    colList : [ {
        label : '所属加分大类',
        name : 'rcxwlbdlmc',
        index : 'rcxwlbdlmc',
        width : '25%'
    }, {
        label : '加分类<br>别代码',
        name : 'rcxwlbdm',
        index : 'rcxwlbdm',
        key : true,
        width : '8%'
    }, {
        label : '行为类别名称',
        name : 'rcxwlbmc',
        index : 'rcxwlbmc',
        width : '18%'
    }, {
        label : '分值<br>类型',
        name : 'fzlxmc',
        index : 'fzlxmc',
        width : '7%'
    }, {
        label : '加分类别<br>分值区间',
        name : 'fzqj',
        index : 'fzqj',
        width : '12%'
    }, {
        label : '备注',
        name : 'rcxwlbbz',
        index : 'rcxwlbbz',
        width : '30%'
    }, {
        label : '最低分值',
        name : 'rcxwlbzdfz',
        index : 'rcxwlbzdfz',
        width : '',
        hidden:true
    }, {
        label : '最高分值',
        name : 'rcxwlbzgfz',
        index : 'rcxwlbzgfz',
        width : '',
        hidden:true
    }, {
        label : '所属加分大类',
        name : 'rcxwlbdldm',
        index : 'rcxwlbdldm',
        hidden : true
    } ],
    sortname : "rcxwlbdm",
    sortorder : "asc"
}

jQuery(function() {
	if("13431" == jQuery("#xxdm").val()){
        jQuery("#dataTable").initGrid(gridSetting1);
	}else{
        jQuery("#dataTable").initGrid(gridSetting);
	}

});

function query() {
	var map = {};
	var rcxwlbdlmc = jQuery("#rcxwlbdlmc").val();
	map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
	if (jQuery.trim(rcxwlbdlmc) != "") {
		map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
	}
	jQuery("#dataTable").reloadGrid(map);
}
function queryRcxwlbdldm() {
	var map = {};
	var rcxwlbmc = jQuery("#rcxwlbmc").val();
	map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();

	if (jQuery.trim(rcxwlbmc) != "") {
		map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
	}
	jQuery("#dataTable").reloadGrid(map);
}
function selectLb(){
	var api=frameElement.api;
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm = jQuery("#xxdm").val();
	if(rows.length==0){
		var msg = "请选择您要申请的行为类别！";
		if("13431" == xxdm) msg = "请选择您要申请的加分类别！";
		showAlertDivLayer(msg);
		return false;
	}
	var tbody = jQuery(api.get('parentDialog').document).find("#xmInfo");
	tbody.find("tr").remove();
	jQuery.each(rows,function(i,e){
		var html="<tr><td><input type='hidden' name='xwlbdmArr' value='"+rows[i]['rcxwlbdm']+"'/>";
		html+="<input type='hidden' name='xwdldmArr' value='"+rows[i]['rcxwlbdldm']+"'/>";
		html+=rows[i]['rcxwlbmc']+"</td>";
		html+="<td>"+rows[i]['rcxwlbdlmc']+"</td>";
		html+="<td>"+rows[i]['fzlxmc']+"</td>";
		html+="<td>"+rows[i]['fzqj']+"</td>";
		html+="<td><input type='text' style='width:50%' name='fzArray' onkeyup='checkInputNum(this)' onblur='checkValue(this)' maxlength='8'/>";
		html+="<input type='hidden' name='rcxwlbzdfz' value='"+rows[i]['rcxwlbzdfz']+"'/>";
		html+="<input type='hidden' name='rcxwlbzgfz' value='"+rows[i]['rcxwlbzgfz']+"'/>";
		html+="<font color='red'>*</font>";
		html+="</td></tr>";
		tbody.append(html);
	});
	iFClose();
}


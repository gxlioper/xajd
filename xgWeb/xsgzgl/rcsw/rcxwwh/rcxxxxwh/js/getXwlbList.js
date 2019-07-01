var gridSetting = {
	caption : "��Ϊ����б�",
	pager : "pager",
	url : "rcsw_rcxwwh_rcxwdmwhgl.do?method=getXwlbList&type=query",
	colList : [ {
		label : '������Ϊ����',
		name : 'rcxwlbdlmc',
		index : 'rcxwlbdlmc',
		width : '25%'
	}, {
		label : '��Ϊ��<br>�����',
		name : 'rcxwlbdm',
		index : 'rcxwlbdm',
		key : true,
		width : '8%'
	}, {
		label : '��Ϊ�������',
		name : 'rcxwlbmc',
		index : 'rcxwlbmc',
		width : '18%'
	}, {
		label : '��ֵ<br>����',
		name : 'fzlxmc',
		index : 'fzlxmc',
		width : '7%'
	}, {
		label : '��Ϊ���<br>��ֵ����',
		name : 'fzqj',
		index : 'fzqj',
		width : '12%'
	}, {
		label : '��ע',
		name : 'rcxwlbbz',
		index : 'rcxwlbbz',
		width : '30%'
	}, {
		label : '��ͷ�ֵ',
		name : 'rcxwlbzdfz',
		index : 'rcxwlbzdfz',
		width : '',
		hidden:true
	}, {
		label : '��߷�ֵ',
		name : 'rcxwlbzgfz',
		index : 'rcxwlbzgfz',
		width : '',
		hidden:true
	}, {
		label : '������Ϊ����',
		name : 'rcxwlbdldm',
		index : 'rcxwlbdldm',
		hidden : true
	} ],
	sortname : "rcxwlbdm",
	sortorder : "asc"
}

var gridSetting1 = {
    caption : "�ӷ�����б�",
    pager : "pager",
    url : "rcsw_rcxwwh_rcxwdmwhgl.do?method=getXwlbList&type=query",
    colList : [ {
        label : '�����ӷִ���',
        name : 'rcxwlbdlmc',
        index : 'rcxwlbdlmc',
        width : '25%'
    }, {
        label : '�ӷ���<br>�����',
        name : 'rcxwlbdm',
        index : 'rcxwlbdm',
        key : true,
        width : '8%'
    }, {
        label : '��Ϊ�������',
        name : 'rcxwlbmc',
        index : 'rcxwlbmc',
        width : '18%'
    }, {
        label : '��ֵ<br>����',
        name : 'fzlxmc',
        index : 'fzlxmc',
        width : '7%'
    }, {
        label : '�ӷ����<br>��ֵ����',
        name : 'fzqj',
        index : 'fzqj',
        width : '12%'
    }, {
        label : '��ע',
        name : 'rcxwlbbz',
        index : 'rcxwlbbz',
        width : '30%'
    }, {
        label : '��ͷ�ֵ',
        name : 'rcxwlbzdfz',
        index : 'rcxwlbzdfz',
        width : '',
        hidden:true
    }, {
        label : '��߷�ֵ',
        name : 'rcxwlbzgfz',
        index : 'rcxwlbzgfz',
        width : '',
        hidden:true
    }, {
        label : '�����ӷִ���',
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
		var msg = "��ѡ����Ҫ�������Ϊ���";
		if("13431" == xxdm) msg = "��ѡ����Ҫ����ļӷ����";
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


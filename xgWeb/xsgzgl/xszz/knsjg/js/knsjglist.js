var DCCLBH = "xszz_knsjg_cx.do";//dcclbh,�������ܱ��
var sfyc=true;
function initGridSetting(){
	var xbmc=jQuery("#xbmc").val();
	if("10335"==jQuery("#xxdm").val()){
		sfyc=false;
		}
var gridSetting = {};
if("xq"==jQuery("#sqzq").val()){
	if(jQuery("#xxdm").val() != '13871'){
		gridSetting  = {
				caption : "����������б�",
				pager : "pager",
				url : "xszz_knsjg.do?method=getKnsjgList&type=query",
				colList : [ {
					label : 'guid',
					name : 'guid',
					index : 'guid',
					width : '10%',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '15%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '15%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '10%'
				},{
					label : xbmc,
					name : 'xymc',
					index : 'xydm',
					width : '20%'
				},  {
                    label : '��Ժ',
                    name : 'symc',
                    index:'symc',
                    width : '10%'
                },{
                    label : '�����༶',
                    name : 'bjmc',
                    index : 'bjdm',
                    width : '20%'
                },{
                    label : 'רҵ�༶',
                    name : 'zybjmc',
                    index : 'zybjmc',
                    width : '20%'
                },
				{
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : 'ѧ��',
					name : 'xqmc',
					index : 'xqmc',
					width : '10%'
				}, {
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : '�϶�����',
					name : 'dcmc',
					index : 'dcmc',
					width : '10%'
				}
//				,{label:'�޳��������',name:'ylzd3', index: 'ylzd3',width:'11%',hidden:sfyc}
				

				],
				sortname : "sqsj",
				sortorder : "desc"
			}
	}else{
		gridSetting  = {
				caption : "����������б�",
				pager : "pager",
				url : "xszz_knsjg.do?method=getKnsjgList&type=query",
				colList : [ {
					label : 'guid',
					name : 'guid',
					index : 'guid',
					width : '10%',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '15%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '15%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '10%'
				},{
					label : xbmc,
					name : 'xymc',
					index : 'xydm',
					width : '20%'
				}, {
                    label : '��Ժ',
                    name : 'symc',
					index:'symc',
                    width : '10%'
				},{
					label : '�����༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '20%'
				},{
                    label : 'רҵ�༶',
                    name : 'zybjmc',
                    index : 'zybjmc',
                    width : '20%'
				},{
					label : '��������',
					name : 'knpx',
					index : 'knpx',
					width : '10%'
				},
				{
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : 'ѧ��',
					name : 'xqmc',
					index : 'xqmc',
					width : '10%'
				}, {
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : '�϶�����',
					name : 'dcmc',
					index : 'dcmc',
					width : '10%'
				}
//				,{label:'�޳��������',name:'ylzd3', index: 'ylzd3',width:'11%',hidden:sfyc}
				

				]
				
			}
	}
	
}
else{
	if(jQuery("#xxdm").val() != '13871'){
	gridSetting  = {
			caption : "����������б�",
			pager : "pager",
			url : "xszz_knsjg.do?method=getKnsjgList&type=query",
			colList : [ {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				width : '10%',
				key : true,
				hidden : true
			}, {
				label : 'ѧ��',
				name : 'xh',
				index : 'xh',
				width : '15%',
				formatter : xhLink
			}, {
				label : '����',
				name : 'xm',
				index : 'xm',
				width : '15%'
			}, {
				label : '�Ա�',
				name : 'xb',
				index : 'xb',
				width : '10%'
			}, {
				label : xbmc,
				name : 'xymc',
				index : 'xydm',
				width : '20%'
			},{
                label : '��Ժ',
                name : 'symc',
                index:'symc',
                width : '10%'
            },{
                label : '�����༶',
                name : 'bjmc',
                index : 'bjdm',
                width : '20%'
            },{
                label : 'רҵ�༶',
                name : 'zybjmc',
                index : 'zybjmc',
                width : '20%'
            },{
				label : 'ѧ��',
				name : 'xn',
				index : 'xn',
				width : '10%'
			}, {
				label : 'sjly',
				name : 'sjly',
				index : 'sjly',
				hidden : true
			},{
				label : '�϶�����',
				name : 'dcmc',
				index : 'dcmc',
				width : '10%'
			}
//			 ,{label:'�޳��������',name:'wczzje', index: 'wczzje',width:'11%',hidden:sfyc}

			],
			sortname : "sqsj",
			sortorder : "desc"
		}
	}else{
		gridSetting  = {
		caption : "����������б�",
		pager : "pager",
		url : "xszz_knsjg.do?method=getKnsjgList&type=query",
		colList : [ {
			label : 'guid',
			name : 'guid',
			index : 'guid',
			width : '10%',
			key : true,
			hidden : true
		}, {
			label : 'ѧ��',
			name : 'xh',
			index : 'xh',
			width : '15%',
			formatter : xhLink
		}, {
			label : '����',
			name : 'xm',
			index : 'xm',
			width : '15%'
		}, {
			label : '�Ա�',
			name : 'xb',
			index : 'xb',
			width : '10%'
		}, {
			label : xbmc,
			name : 'xymc',
			index : 'xydm',
			width : '20%'
		},{
            label : '��Ժ',
            name : 'symc',
            index:'symc',
            width : '10%'
        },{
            label : '�����༶',
            name : 'bjmc',
            index : 'bjdm',
            width : '20%'
        },{
            label : 'רҵ�༶',
            name : 'zybjmc',
            index : 'zybjmc',
            width : '20%'
        },{
			label : '��������',
			name : 'knpx',
			index : 'knpx',
			width : '10%'
		},{
			label : 'ѧ��',
			name : 'xn',
			index : 'xn',
			width : '10%'
		}, {
			label : 'sjly',
			name : 'sjly',
			index : 'sjly',
			hidden : true
		},{
			label : '�϶�����',
			name : 'dcmc',
			index : 'dcmc',
			width : '10%'
		}
//		 ,{label:'�޳��������',name:'wczzje', index: 'wczzje',width:'11%',hidden:sfyc}

		]
	}
	}
	
}
return gridSetting;
}

/**
 * �����������������--�鿴
 * 
 * @param xh
 * @return
 */
function knsView(guid, xh) {
	showDialog("��������ѯ", 700, 435, "xszz_knsjg.do?method=knsjgView&guid=" + guid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='knsView(\""
			+ rowObject["guid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function add() {
	var url = "xszz_knsjg.do?method=addKnsjg";
	var title = "�����������϶���Ϣ";
	showDialog(title, 720, 480, url);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("��ѡ��ļ�¼Ϊ�������ݣ������޸�!");
			return false;
		}
		
		var url = 'xszz_knsjg.do?method=updateKnsjg&guid=' + rows[0]["guid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "�޸��������϶���Ϣ";
		showDialog(title, 720, 480, url);
	}

}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']!='0'){
				showAlertDivLayer("��ѡ�ļ�¼�а����������ݣ�����ɾ����������ѡ��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_knsjg.do?method=delKnsjg", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
// ��ӡ����
function printKnssq(url) {

	/*var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ��ӡ�ļ�¼��");
	} else {

		var url = url + "&guid=" + rows[0]["guid"] + "&xh=" + rows[0]["xh"]
		window.open(url);
	}*/
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else if (rows.length ==1){
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		//var url = url + "&guid=" +guid;
		//window.open(url);
		post(url, {guid:guid});
	}
}

function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)         
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
} 

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_knsjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function copy(){
	var iscanCopy=jQuery("#iscanCopy").val();
	if(iscanCopy=="0"){
		showAlertDivLayer("����������ѧ��ѧ�ڵ������������ܸ��ơ�",{},{"clkFun":function(){
		}});
		return false;
	}

	var url ="xszz_knsjg.do?method=knsjgcopy";
	showDialog("�������϶��������", 400, 200, url);
}
function savecopy(){
	var lyxnxq=jQuery("#lyxnxq").val();
	var lyxn=lyxnxq.split(",")[0];
	var lyxq=lyxnxq.split(",")[1];
	
	var xnxq=jQuery("#xnxq").val();
	var xn=xnxq.split(",")[0];
	var xq=xnxq.split(",")[1];
	
	jQuery("#form").ajaxSubmit({
		url:"xszz_knsjg.do?method=savecopy",
		type:"post",
		dataType:"json",
		data:{
			lyxn:lyxn,
			lyxq:lyxq,
			mbxn:xn,
			mbxq:xq},
		success:function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});	
		}
	});
	
//	jQuery.post("xszz_knsjg.do?method=savecopy", {
//		lyxn:lyxn,
//		lyxq:lyxq,
//		xn:xn,
//		xq:xq
//	}, function(data) {
//		showAlert(data["message"],{},{"clkFun":function(){
//			if (parent.window){
//				refershParent();
//			}
//		}});
//	}, 'json');
}
//���ݴ�ѧ������ͳ�Ƶ���
function knsrdTjExport(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_knsjg.do?method=knsrdTjExport"
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function knsExport(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_knsjg.do?method=knsExport"
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
/**
 * �����մɸ��Ի�
 * @return
 */
function rdhzbExport(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_knsjg.do?method=rdhzbExport"
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function exportKnsjg(type){
	var xbmc=jQuery("#xbmc").val();
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	var xy_num =  jQuery("a[name=a_name_xy]").length;
	var url = "xszz_knsjg.do?method=ExportKnsjg"
	if(xn_num!="1"){
		showAlertDivLayer("��ѡ��һ��ѧ�꣡");
		return false;
	}
	if(type=="dashk"){
		url=url+"&expType=dashk";
	}
	if(type=="rdpxb"){
		url=url+"&expType=rdpxb";
		/*if(xn_xy!="0"){
			showAlertDivLayer("�õ�������ѡ��1��"+xbmc);
			return false;
		}*/
	}
	setSearchTj();//���ø߼���ѯ����
	
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}



/* 	�����Ի� ��
	��ʾ������������ѡ��ر�����ѧ���ĸ�ѧԺ�ֲ�����ͱ��������ť
	2016��10��17��     ������Ա������
*/
function knstksPercent(){
	showDialog("��Ժϵ���ѡ�����ѧ������",750,400,"xszz_knsjg.do?method=knstksPercent");
}

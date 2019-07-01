

jQuery(function(){
	var gridSetting = {
			caption:"֤���������б�",
	pager:"pager",
	url:"rcsw_xszbb_bbjggl.do?method=xszbbjgManage&type=query",
	colList:[
	   {label:'key',name:'bbjgid', index: 'bbjgid',key:true ,hidden:true},
		{label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
		{label:'����',name:'xm', index: 'xm',width:'8%'},
		{label:'�꼶',name:'nj', index: 'nj',width:'5%'},
		{label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
		{label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
		{label:'�༶',name:'bjmc', index: 'bjdm',width:'8%'},
		{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
		{label:'����֤��',name:'xszbblxmc', index: 'xszbblxmc',width:'8%'},
		{label:'��ȡ',name:'sflq', index: 'sflq',width:'8%'},
		{label:'��������',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "sqsj",
 	sortorder: "desc"
}
	var gridSetting1 = {
			caption:"֤���������б�",
			pager:"pager",
			url:"rcsw_xszbb_bbjggl.do?method=xszbbjgManage&type=query",
			colList:[
			        {label:'key',name:'bbjgid', index: 'bbjgid',key:true ,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'�꼶',name:'nj', index: 'nj',width:'5%'},
					{label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
					{label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
					{label:'�༶',name:'bjmc', index: 'bjdm',width:'8%'},
					{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
					{label:'����֤��',name:'xszbblxmc', index: 'xszbblxmc',width:'8%'},
					{label:'��ȡ',name:'sflq', index: 'sflq',width:'8%'},
					{label:'������ʱ��',name:'shwcsj', index: 'shwcsj',width:'8%'},
					{label:'��������',name:'sjly', index: 'sjly',hidden:true}
					],
			sortname: "sqsj",
			sortorder: "desc"
	}
	if(jQuery("#xxdm").val() == '13011'){//�ൺ�Ƶ���Ի�
		jQuery("#dataTable").initGrid(gridSetting1);
	}else{		
		jQuery("#dataTable").initGrid(gridSetting);			
	}
	
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_xszbb_bbjggl.do?method=addXszbbsqjg";
	var title = "����֤����������Ϣ";
	showDialog(title,790,525,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("�������ݲ����޸ģ�");
	}else {
		var url = 'rcsw_xszbb_bbjggl.do?method=updateXszbbjg&bbjgid='+ rows[0]["bbjgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸�֤����������Ϣ";
		showDialog(title, 720, 525, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_xszbb_bbjggl.do?method=delXszbbjg", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������ݲ����޸ģ�";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function xszbbjgView(bbjgid, xh) {
	showDialog("֤����������ѯ", 720, 460, "rcsw_xszbb_bbjggl.do?method=viewOneXszbbjg&bbjgid=" + bbjgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xszbbjgView(\""
			+ rowObject["bbjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_xszbb_bbjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xszbbjgExportData);
}

// ��������
function xszbbjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_xszbb_bbjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ȡ
function lingq(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length ==0){
		showAlertDivLayer("��ѡ����Ҫ��ȡ�ļ�¼��");
	}else if (rows.length ==1){

		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("֤����ȡ",400,250,"rcsw_xszbb_bbjggl.do?method=lingqXszbbjg&ids="+ids);
	}else {
		showDialog("֤����ȡ",400,250,"rcsw_xszbb_bbjggl.do?method=lingqXszbbjg");
	}
}

//�����໪ѧ��֤��ӡ
function dyXszXaph(csdm) {
	 var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
	 } else {
		 var  flag = true;
		 var  ids = "";
		 jQuery(rows).each(function(i,row){
			 if(row["xszbblxmc"] != "ѧ��֤"){
				 flag = false;
				 return false;
			 }
			 ids += row["xh"];
			 if(i != rows.length-1){
				 ids += ",";
			 }
		 })
		 if(!flag){
			 showAlertDivLayer("��ѡȡѧ��֤���д�ӡ��");
			 return false;
		 }
		 jQuery.post("xsxx_xsxxgl.do?method=dyXszXaph",{csdm:csdm, value:ids},function(data){
				 var csz = data["csz"];
				 if(csz == null){
					 showAlertDivLayer("ѧ����Ϣδ��д����������д���ٴ�ӡ��");
					 return false;
				 }else{
					// ��ʼ�����
					var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
					LODOP.PRINT_INIT("��ӡ");
					// ����ģ��Ԫ��
					eval(csz);
					// ��ӡԤ��
					LODOP.PREVIEW();
				 }
			},'json');
     }
}


function getWord(){
	 var rows = jQuery("#dataTable").getSeletRow();
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����¼��");
	 } else if (rows.length > 1){
		var url="rcsw_xszbb_bbjggl.do?method=getZxzmZip";
		var ids = jQuery("#dataTable").getSeletIds();
		var url= url+"&value="+ids;
		window.open(url);
	 } else {
		var url="rcsw_xszbb_bbjggl.do?method=getZxzmWord";
		var url= url+"&xh="+rows[0]["xh"];
		window.open(url);
    }
}

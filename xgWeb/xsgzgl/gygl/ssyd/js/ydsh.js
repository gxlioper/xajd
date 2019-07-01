function searchRs(){
	var map = getSuperSearch();
	var shlx = jQuery("#shlx").val();
	if (shlx != ""){
		map["shlx"] = shlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
function selectTab(obj,shlx){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	if (shlx=="dsh"){
		var dclGrid = getDclGird();
		var map = getSuperSearch();
			map["shlx"] = "dsh"; 
		dclGrid["params"] = map;
		jQuery("#dataTable").initGrid(dclGrid);
		
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
		jQuery("#shlx").val("dsh");
	} else {
		var yclGrid = getYclGrid();
		var map = getSuperSearch();
			map["shlx"] = "ysh"; 
		yclGrid["params"] = map;
		jQuery("#dataTable").initGrid(yclGrid);
		
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
		jQuery("#shlx").val("ysh");
	}
}

//�����춯���
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		alertInfo("�Ѵ���ļ�¼�����ٴ���ˣ�");
	}else if (rows.length == 0){
		alertInfo("��ѡ��һ����Ҫ��˵ļ�¼��");
		return false;
	} else if (rows.length ==1){
		showDialog("�����춯���",760,490,'ydsh.do?method=ydsh&ssydsqid='+rows[0]["ssydsqid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]);
	} else {
		for(var i=0;i<rows.length;i++){
			var sqshHideCwxx = rows[i]['sqshHideCwxx'];
			if(rows[i]['ssydlx']=='01' && (sqshHideCwxx=='' || sqshHideCwxx=="null_null_null")){
				showAlertDivLayer("���������������û��ѡ��λ������������ˣ�");
				return false;
			}
			if(i!=0 && rows[i]["ssydlx"]!=rows[i-1]["ssydlx"]){
				showAlertDivLayer("��ѡ����ͬ�춯���͵����ݣ�");
				return false;
			}
		}
		showDialog("�����춯�������",500,300,"ydsh.do?method=ydPlsh&ssydlx="+rows[0]["ssydlx"]);
	}
}

function savePlsh(shzt,shyj,sfcwcsh,jflx,zsfje){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	var ssydlx = new Array();
	var sqshHideCwxx = new Array();
	var thgw = "";
	if(shzt == '3'){
		thgw = "-1";
	}
	jQuery.each(rows,function(i,row){
		guid.push(row["ssydsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splcid"]);
		ssydlx.push(row["ssydlx"]);
		sqshHideCwxx.push(row["sqshHideCwxx"]);
	});

	jQuery.post(
		"ydsh.do?method=ydPlsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 splcs:splc,
		 ssydlxs:ssydlx,
		 sqshHideCwxxs:sqshHideCwxx,
		 sfcwcsh:sfcwcsh,
		 jflx:jflx,
		 zsfje:zsfje,
		 thgw:thgw
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}


//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ssydsqid']+"&splc="+rows[0]['splcid']);
	}
}



/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	
	var zsfje = jQuery.trim(jQuery("#zsfje").val());
	var jflx = jQuery("#jflx").val();
	var shjg = jQuery("#shjg").val();
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	if(jQuery("#xxdm").val() != "13871"){
		if (shjg == "1" && ((jflx == "" && zsfje != "") || (jflx != "" && zsfje == ""))) {
			showAlertDivLayer("�뽫ס�޷���Ϣ��д������");
			return false;
		}
	}
	
	
	var ssydlx = jQuery("#ssydlx").val();
	if(shjg == "1" && ssydlx == "01" && jQuery("#xxdm").val() != "10351"&& jQuery("#xxdm").val() != "12872"  && jQuery("#xxdm").val() != "10344"  && (jQuery("#cwxx").val() == "" || jQuery("#cwxx").val() == "null_null_null")){
		showAlertDivLayer("��ѡ��λ��");
		return false;
	}
	if(ssydlx == "01" && jQuery("#xxdm").val() == "12861"){
		if (jQuery("#sstz_checkbox") && !jQuery("#sstz_checkbox").attr('checked')){
			showAlertDivLayer("��ȷ���������˫�������Ƿ���ͬ�⣡����ȷ�ϣ��빴ѡ��˫��������ͬ�⡿��");
			return false;
		}
	}
	//�ύ���
	showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		jQuery("#btqd").attr("disabled","disabled");
		var url = "ydsh.do?method=ydsh&type=save";
		jQuery("#demoForm").ajaxSubmit({
			url:url,
			type:"post",
			dataType:"json",
			success:function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					refershParent();
				}});
			},
			contentType:"application/x-www-form-urlencoded;charset=utf-8"
		});	
		
	}});
}


function exportConfig() {
	customExport("ydshbase.do", exportData);
}
// ��������
function exportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	
	var url = "ydshbase.do?method=exportData&dcclbh='ydshbase.do'"+ "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//ѡ��λ������ �����Ϣ��
function selectCw(){
	var xh = jQuery("#xh").val();
	showDialog('��ѡ��һ����λ',800,500,'ydsq.do?method=selectCwxx&goto=path&xh='+xh);
}
//ѡ��λ����ס �����Ϣ��
function selectRzcw(){
	var xh = jQuery("#xh").val();
	showDialog('��ѡ��һ����λ',800,500,'ydsq.do?method=selectRzcwxx&goto=path&xh='+xh);
}
//��ʾѡ�д�λ��Ϣ������ �����Ϣ��
function showCwxx(cwxx){
	var cwxxSetting = {
			caption:"��ѡ��λ��Ϣ",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
			colList:[
			   {label:'��λ��Ϣid',name:'cwxx', index: 'cwxx',key:true,hidden:true},
			   {label:'¥������',name:'ldmc', index: 'ldmc'},
			   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
			   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
			   {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
			   {label:'�����꼶',name:'nj', index: 'nj'},
			   {label:'����'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'ѧ��',name:'xh', index: 'xh'},
			   {label:'����',name:'xm', index: 'xm'}
			],
			sortname: "sfrz",
		 	sortorder: "desc"
		}
 	jQuery("#cwxxTable").initGrid(cwxxSetting);
	jQuery("#yxzcwxx").show();
	jQuery("#cwxx").val(cwxx);
}
//��ʾѡ�д�λ��Ϣ����ס �����Ϣ��
function showRzcwxx(rzcwxx){
	var rzcwxxSetting = {
			caption:"��ѡ��λ��Ϣ",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectRzcwxx&type=query&rzcwxx="+rzcwxx,
			colList:[
			         {label:'��λ��Ϣid',name:'rzcwxx', index: 'rzcwxx',key:true,hidden:true},
			         {label:'¥������',name:'ldmc', index: 'ldmc'},
			         {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
			         {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
			         {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
			         {label:'�����꼶',name:'nj', index: 'nj'},
			         {label:'����'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'}
			         //{label:'ѧ��',name:'xh', index: 'xh'},
			         //{label:'����',name:'xm', index: 'xm'}
			         ],
			         sortname: "sfrz",
			         sortorder: "desc"
	}
	jQuery("#rzcwxxTable").initGrid(rzcwxxSetting);
	jQuery("#yxzrzcwxx").show();
	jQuery("#rzcwxx").val(rzcwxx);
}


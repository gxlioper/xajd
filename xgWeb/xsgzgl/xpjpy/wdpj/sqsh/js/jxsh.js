//�������

var shzt='0';


/**
 * ��ʾ���ҳ��
 */
function viewJxsh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm = jQuery("#xxdm").val();
	if (rows.length ==0&&"10335"!=xxdm){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	}else if (rows.length == 1){
		var sqid = rows[0]["sqid"];
		var shid = rows[0]["shid"];
		var gwid = rows[0]["gwid"];
		
		showDialog("�������",700,500,"xpj_sqsh.do?method=viewJxsh&sqid="+sqid+"&shid="+shid+"&gwid="+gwid);
	}else {
		showDialog("�������",500,300,"xpj_sqsh.do?method=toPlsh");
	}
}


		

/**
 * ȡ�����
 */
function cancelAuding(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
//		jQuery.post("xpj_sqsh.do?method=runCancel",{sqid:rows[0]["sqid"],shid:rows[0]["shid"]},function(data){
//			showAlert(data["message"],{},{"clkFun":function(){
//				var yclGrid = getYclGrid();
//				jQuery("#dataTable").initGrid(yclGrid);
//			}});
//		},"json");
		
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var xmdm = rows[0]["xmdm"];
		var xh = rows[0]["xh"];
		
		
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
//				jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				jQuery.post("xpj_sqsh.do?method=cxshnew",{splc:splc,shid:shid,xmdm:xmdm,xh:xh},function(data){
					
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post("xpj_sqsh.do?method=cancel",{splcid:splc,shid:shid,xmdm:xmdm},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
			}
		});
	}
	
}



/**
 * �л��Ѵ���δ����
 * @param obj
 * @param shzt
 */
function chageShzt(obj,shzt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	if (shzt=="0"){
		var dclGrid = getDclGird();
		var map = getSuperSearch();
			map["shzt"] = "0"; 
		dclGrid["params"] = map;
		jQuery("#dataTable").initGrid(dclGrid);
		
		jQuery("#li_qx").hide();
		jQuery("#li_sh").show();
		jQuery("#shzt").val("0");
	} else {
		var yclGrid = getYclGrid();
		var map = getSuperSearch();
			map["shzt"] = "1"; 
		yclGrid["params"] = map;
		jQuery("#dataTable").initGrid(yclGrid);
		
		jQuery("#li_qx").show();
		jQuery("#li_sh").hide();
		jQuery("#shzt").val("1");
	}
}


/**
 * ������������
 * @returns {___anonymous3504_3637}
 */
function getShqkGrid(){
	
	var tjdw = jQuery("#tjdw").val();
	var gridSetting = {
		caption:"�б� ",
		pager:"pager",
		url:"xpj_sqsh.do?method=viewShqkList&type=query",
		sortname:"dsrs",
		sortorder:"desc",
		radioselect:true
	};
	
	var colList;
	
	if ("bj" == tjdw){
		colList=[
		   {label:'bjdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
		   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xydm',width:'20%'},
		   {label:'�༶',name:'bjmc', index: 'bmdm',width:'20%'},
		   {label:'������',name:'bjrs', index: 'bjrs',width:'10%'},
		   {label:'����/�ϱ�����',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'��������',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'ͨ������',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'��ͨ������',name:'btgrs', index: 'btgrs',width:'15%'}
		];
		
		
	} else if ("njzy" == tjdw){
		colList=[
		   {label:'bmdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
		   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
		   {label:'������',name:'zrs', index: 'zrs',width:'10%'},
		   {label:'����/�ϱ�����',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'��������',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'ͨ������',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'��ͨ������',name:'btgrs', index: 'btgrs',width:'15%'}
		];
	} else {
		colList=[
		   {label:'bmdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'������',name:'cpzmc', index: 'bmdm',width:'20%'},
		   {label:'������',name:'cpzrs', index: 'cpzrs',width:'10%'},
		   {label:'����/�ϱ�����',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'��������',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'ͨ������',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'��ͨ������',name:'btgrs', index: 'btgrs',width:'15%'}
		];
	}
	
	gridSetting["colList"] = colList;
	return gridSetting;
}



/**
 * ѡ��ͳ�Ʒ�ʽ
 */
function showSeletTjdw(){
	showDialog("ѡ��ͳ�Ʒ�ʽ",300,150,"xpj_sqsh.do?method=selectTjdw",{max:false,min:false});
}


/**
 * �����ϸҳ��
 */
function showAudingList(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else {
		var tjdw = jQuery("#tjdw").val();
		var shid = jQuery("#shid").val();
		var id = rows[0]["bmdm"];
		
		showDialog("�������",750,500,"xpj_sqsh.do?method=viewJxshList&tjdw="+tjdw+"&shid="+shid+"&bmdm="+id);
	}
}


/**
 * ���̸���
 */
function viewLcgz(){
	//var rows = jQuery("#dataTable").getSeletRow();

	//if (rows.length != 1){
	//	showAlertDivLayer("��ѡ��һ����¼��");
	//} else {
	//	var url = "xpj_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
	//	showDialog("���̸���",550,450,url,{max:false,min:false});
	//}
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
	
	
}


/**
 * ��ѯ
 */
function searchRs(){
	
	//�����Ƿ�ɲ�ѯ
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	var xn_xq =  jQuery("a[name=a_name_xq]").length;
	
	if(xn_num != "1"){
		alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
		return false;
	}
	
	
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
	
}


/**
 * ������ͳ�� 
 */
function viewShqk(){
	showDialog("�����������ͳ��",500,400,"xpj_sqsh.do?method=pjxmShtj");
}


//����
function exportConfig(){
	var DCCLBH='pj_jxsh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='pj_jxsh.do';
	
	setSearchTj();//���ø߼���ѯ����
	
	var url = "xpj_sqsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwids = new Array();
	var xhs = new Array();
	var splcs =new Array();
	var thgw = "";
	if(shzt == "3"){
		thgw = "-1";
	}
	jQuery.each(rows,function(i,row){
		guid.push(row["sqid"]);
		gwids.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcs.push(row["splc"]);
	});
	
	var map = getSuperSearch();
	map["shzt"]=shzt;
	map["id"]=guid;
	map["gwids"]=gwids;
	map["xhs"]=xhs;
	map["splcs"]=splcs;
	map["shyj"]=shyj;
	map["thgw"]=thgw;
	jQuery.post(
		"xpj_sqsh.do?method=savePlsh",
		map,function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);

}



function saveZdPlsh(shzt,shyj,xyXmdm,jsonStr,id){

	var map = JSON.parse(jsonStr);
	map["shzt"]=shzt;
	map["shyj"]=shyj;
	map["xyXmdm"]=xyXmdm;
	map["sqid"]=id;

	jQuery.post(
		"xpj_sqsh.do?method=saveZdPlsh",
		map,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}


/**
 * ���ͳ��
 */
function viewZdtj(){
	showDialog("�����������ͳ��",900,400,"xpj_sqsh.do?method=viewZdtj");
}

//�Ϻ�Ϸ��ѧԺ������������
function fjdc(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery.ajaxSetup({async:false});
	jQuery.post("xpj_sqsh.do?method=checkFjExist", map, function(data) {
		if(data == true){								
			setSearchTj();//���ø߼���ѯ����
			var url = "xpj_sqsh.do?method=fjdc&shzt="+jQuery("#shzt").val();
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}else{
			showAlertDivLayer(data["message"]);
		}	      		
		}, 'json');
	jQuery.ajaxSetup({async:true});
}

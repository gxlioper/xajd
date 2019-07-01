/*������� */
var shzt = '0';
/**
 * ��ȡ�����������
 * @return
 */
function getDclGird(){
	var colList = [
	   {label:'key',name:'id', index: 'id',hidden:true,key:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:function(cell,rowObject){
		   return "<a href='javascript:void(0);' onclick=\"showDialog('�鿴�����',800,500,'xpjpy_xmsq.do?method=xmsqView&id="+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
	   }},
	   {label:'����',name:'xm', index: 'xm',width:'8%'},
	   {label:'ѧԺ',name:'xymc', index: 'xydm',width:'11%'},
	   {label:'�༶',name:'bjmc', index: 'bjdm',width:'11%'},
	   {label:'��Ŀ����',name:'xmmc', index: 'xmdm',width:'10%'},
	   {label:'���',name:'xmje', index: 'xmje',width:'3%'},
	   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'11%'}
	];
	colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
	colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
	colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
	colList.push({label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'});
	return {
		caption:"����ѧ���б� ",
		pager:"pager",
		url:"xpjpy_xmsh.do?method=getXmshListDate&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}

/**
 * ��ȡ�Ѵ��������
 * @return
 */
function getYclGrid(){
	var colList = [
	   {label:'key',name:'id', index: 'id',hidden:true,key:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:function(cell,rowObject){
		   return "<a href='javascript:void(0);' onclick=\"showDialog('�鿴�����',800,500,'xpjpy_xmsq.do?method=xmsqView&id="+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
	   }},
	   {label:'����',name:'xm', index: 'xm',width:'8%'},
	   {label:'ѧԺ',name:'xymc', index: 'xydm',width:'11%'},
	   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
	   {label:'��Ŀ����',name:'xmmc', index: 'xmdm',width:'10%'},
	   {label:'���',name:'xmje', index: 'xmje',width:'3%'}
	];
	colList.push({label:'���ʱ��',name:'shsj', index: 'shsj',width:'11%'});
 	colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
 	colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
 	colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
 	colList.push({label:'xmdm',name:'xmdm', index: 'xmdm',hidden:true});
 	colList.push({label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'});
	return {
		caption:"����ѧ���б� ",
		pager:"pager",
		url:"xpjpy_xmsh.do?method=getXmshListDate&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}

jQuery(function(){
	jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
		var index = jQuery(this).attr("index");
		var liName = jQuery("#tabUl li.ha").attr("clzt");
		if ("dcl" == liName){
			var gridSetting = getDclGird();
			jQuery("#dataTable").initGrid(gridSetting);
		} else {
			var gridSetting = getYclGrid();
			jQuery("#dataTable").initGrid(gridSetting);
		}
	});
	loadCookie();
	var dclGrid = getDclGird();
	var map = getSuperSearch();
		map["shzt"] = "0";
		dclGrid["params"] = map;
	jQuery("#dataTable").initGrid(dclGrid);
});

/**
 * ��ѯ
 * @return
 */
function searchRs(){
	//�����Ƿ�ɲ�ѯ
	var xn_num = jQuery("a[name=a_name_xn]").length;
	
	if(xn_num != "1"){
		alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
		return false;
	}
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
	
}



/**
 * װ��cookie
 * @return
 */
function loadCookie(){
	var indexStr = jQuery.cookie("jxshGrid");
	if(indexStr != null && indexStr != undefined){
		var indexArray = indexStr.split("-");
		jQuery.each(indexArray,function(i,n){
			if (n != ""){
				jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
			}
		});
	}
}

/**
 * ����cookie
 * @return
 */
function setJxshCookie(){
	var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
	var indexStr = "";
	
	jQuery.each(chekedZcxm,function(i,n){
		var index = jQuery(n).attr("index");
		indexStr += index+"-";
	});
	jQuery.cookie("jxshGrid",indexStr, { expires: 7});
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
 * �������
 * @return
 */
function xmshPlsh(){
	var ids = jQuery("#dataTable").getSeletIds();
	showDialog("�������",750,570,"xpjpy_xmsh.do?method=xmshPlsh&id="+ids.toString());
}

/**
 * ������˱���
 * @param shzt
 * @param shyj
 * @param xyXmdm
 * @param jsonStr
 * @param id
 * @return
 */
function savePlsh(shzt,shyj,xyXmdm,jsonStr,id){
	var map = jsonStr;
	map["shzt"]=shzt;
	map["shyj"]=shyj;
	map["xyXmdm"]=xyXmdm;
	/*����id��������id*/
	map["id"]=id;
	jQuery.post("xpjpy_xmsh.do?method=savePlsh",map,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			jQuery("#dataTable").reloadGrid();
		}});
	},'json');
}

/**
 * ���̸���
 */
function xmshTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

/**
 * ����
 * @return
 */
function xmshRevoke(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var xmdm = rows[0]["xmdm"];
		var xh = rows[0]["xh"];
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("xpjpy_xmsh.do?method=cxshnew",{splc:splc,shid:shid,xmdm:xmdm,xh:xh},function(data){
						/*�ж��Ƿ����һ������(1:���һ�������ɹ���*/
						if("1" == data["cancelFlg"]){
							jQuery.post("xpjpy_xmsh.do?method=cancel",{splcid:splc,shid:shid,xmdm:xmdm},function(result){
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
 * ����
 */
function xmshExport(){
	var DCCLBH='xpjpy_wdpj_pjsh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='xpjpy_wdpj_pjsh.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_xmsh.do?method=xmshExport&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ���صǼǱ�
 */
function getWord() {
    /*ѡ��ļ�¼*/
    var rows = jQuery("#dataTable").getSeletRow();
    /*��ѡ��¼*/
    var ids = jQuery("#dataTable").getSeletIds();
    var len = ids.length;
    /*�жϴ�ӡ*/
    if(0 == len){
        showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
        return false;
    }else if(1 == len){
        var url = "xpjpy_xmsq.do?method=getWordForid&id="+rows[0]["id"];
        window.open(url);
    }else{
        var url = "xpjpy_xmsq.do?method=getDjbZip&value="+ids;
        window.open(url);
    }
}

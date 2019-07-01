var checkId="xmmc-kssj-jssj";
var gridSetting = {
	caption : "������Ŀ�б�",
	pager : "pager",
	url : "khglKhxmgl.do?method=getKhxmglList&type=query",
	colList : [ 
	   {label : 'xmid',name : 'xmid',index : 'xmid',key : true,hidden:true},
	   {label : '���˶���',name : 'khdxid',index : 'khdxid',hidden:true},
	   {label : 'sfypf',name : 'sfypf',index : 'sfypf',hidden:true},
	   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '30%', formatter:xmmcFormatter},
	   {label : '���˿�ʼʱ��',name : 'kssj',index : 'kssj',width : '20%'},
	   {label : '���˽���ʱ��',name : 'jssj',index : 'jssj',width : '20%'},
	   {label : '���˶���',name : 'khdxmc',index : 'khdxmc',width : '20%'},
	   {label : '���ֶ�������',name : 'pfdxsz',index : 'pfdxsz',width : '10%',formatter:setPfdx}
	   ]
}
function xmmcFormatter(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewKhxm(\""+rowObject["xmid"]+"\");'>"+cellValue+"</a>";
}
//���˶�������鿴
function viewKhxm(xmid) {
	showDialog("������Ŀ�鿴", 800, 650, "khglKhxmgl.do?method=viewKhxm&xmid="+xmid);
}

function setPfdx(cellValue, rowObject){
	var xmid = rowObject.xmid;
	var value = "δ����";
	var color;
	if (cellValue == '1') {
		value="������";
		color = "have";
	} else {
		color = "non";
	}
	 value = "<a  href='javascript:void(0);' onclick='return pfdxsz(\"" + xmid
		+ "\",\"" + rowObject.sfypf+ "\",\""+rowObject.khdxid+"\");' ><font class='" + color + "'>" + value + "</font></a>";
	 return value;
}
function pfdxsz(xmid,sfypf,khdxid) {
	if("1"==sfypf){
		showAlertDivLayer("����Ŀ�����֣��������������ֶ���");
		return false;
	}
	var url = 'khglKhxmgl.do?method=pfdxSz&xmid=' + xmid+"&khdxid="+khdxid;
	var title = "���ֶ�������";
	showDialog(title, 800, 570, url);
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

function query() {
	var map = {};
	map["xmmc"] = jQuery("#xmmc").val();
	jQuery("#dataTable").reloadGrid(map);
}
function add() {
	
	var url = "khglKhxmgl.do?method=addKhxm";
	var title = "���ӿ�����Ŀ";
	showDialog(title, 700, 320, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	} 
	var url = 'khglKhxmgl.do?method=updateKhxm&xmid=' + rows[0]["xmid"];
	var title = "�޸Ŀ�����Ŀ";
	showDialog(title, 700, 320, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhxmgl.do?method=delKhxm", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}

function saveForm(type){	  
	  if(!checkNotNull(checkId)){
		  showAlert("�뽫��������д������");
			return false;
	  }
	  if(jQuery("#xmms").val().length>500){
		  showAlert("��Ŀ�����������500�֣�");
			return false;
	  }
    var url = "khglKhxmgl.do?method=saveKhxm&type="+type;
     ajaxSubFormWithFun("KhxmglForm",url,function(data){
    	 if (data["message"] == "����ɹ���") {
 			showAlert(data["message"], {}, {
 				"clkFun" : function() {
 					if (parent.window) {
 						refershParent();
 					}
 				}
 			});
 		} else {
 			showAlert(data["message"]);
 		}
		});
 }

function pfdxSz() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�����ļ�¼��");
		return false;
	}
	if("1"==rows[0]["sfypf"]){
		showAlertDivLayer("����Ŀ�����֣��������������ֶ���");
		return false;
	}
	var url = 'khglKhxmgl.do?method=pfdxSz&xmid=' + rows[0]["xmid"]+"&khdxid="+rows[0]["khdxid"];
	var title = "���ֶ�������";
	showDialog(title, 800, 570, url);
}

/**
 * �б�չ��/����
 * @param obj
 * @return
 */
function showPfzmx(obj,index){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery(obj).parents(".dateline").children(".pfdxTbody").toggle();
}
function addRow(){
	var cloneTab=jQuery("#tab_orign").clone();
	cloneTab.show();
	jQuery("#pfdxDiv").append(cloneTab);	
}
//ɾ�����ֶ���
function delRow(obj){
	jQuery(obj).parents(".dateline").remove();
}

function changePffw(obj){
	var khlx=jQuery("#khlx").val();
	var url="khglKhxmgl.do?method=getPffw&pfzid="+obj.value+"&khlx="+khlx;
	jQuery.post(url, {}, function(data) {
		var pffw="<select name='sjfwdm'>";
		for(var i = 0; i < data.length; i++){
			pffw +="<option value='"+data[i].sjfwdm+"'>"+data[i].sjfw+"</option>";
		}
		pffw += "</select>"
		jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("td[name='pffwTd']").html(pffw);
		jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("th[name='pffwTh']").empty().append("�����鷶Χ");
		if(0==data.length){
			jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("select[name='sjfwdm']").hide();
			jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("th[name='pffwTh']").empty();
		}
		
	}, 'json');
}
function changeJsfs(obj){
	var jsfs=obj.value;
	//ȥͷβ��ƽ��(����)��ʾ���㷽ʽ�ٷֱȣ���������ʾ
	if("2"==jsfs){
		jQuery(obj).parents(".pfdxTbody").find("tr").last().css("display","");
	}else{
		jQuery(obj).parents(".pfdxTbody").find("tr").last().find("input[name='jsfsbfb']").val("");
		jQuery(obj).parents(".pfdxTbody").find("tr").last().css("display","none");
	}
	
}
//���ֶ������ñ���
function savePfdxSz() {
	var flg=true;
	var nullsFlag=true;
	var pfdxJson= [];
	var samePfz="";
	var pfzmc="";
	var sjfwmc="";
	var khbmc="";
	jQuery.each(jQuery(".pfdxTbody"),function(i,n){
		if(jQuery(n).parents(".dateline").css("display")=="none"){
			return true;
		}
		var obj = {};
			var pfzid = jQuery(n).find("select[name=pfzid] option:selected").val();
			 pfzmc = jQuery(n).find("select[name=pfzid] option:selected").text();
			var sjfwdm = jQuery(n).find("select[name=sjfwdm] option:selected").val();
			 sjfwmc = jQuery(n).find("select[name=sjfwdm] option:selected").text();
			var khbid = jQuery(n).find("select[name=khbid] option:selected").val();
			 khbmc = jQuery(n).find("select[name=khbid] option:selected").text();
			var szqz = jQuery(n).find("input[name=szqz]").val();
			var jsfs = jQuery(n).find("select[name=jsfs] option:selected").val();
			var jsfsbfb = jQuery(n).find("input[name=jsfsbfb]").val();
			
			var checkMsg=pfzid+sjfwdm+khbid;
			//��֤�������Ƿ��ظ����
			if(samePfz.indexOf(checkMsg) > -1){
				flg=false;
				return false;
			}else{
				samePfz+="&"+pfzid+sjfwdm+khbid;
			}
			//��֤������
			if(""==szqz||(""==jsfsbfb&&"2"==jsfs)||""==khbid||undefined==khbid){
				nullsFlag=false;
				return false;
			}
			obj.pfzid=pfzid;
			obj.sjfwdm=sjfwdm;
			obj.khbid=khbid;
			obj.szqz=szqz;
			obj.jsfs=jsfs;
			obj.jsfsbfb=jsfsbfb;
			pfdxJson.push(obj);
	});
	if(!flg){
		showAlertDivLayer("���ֶ���<font class='red'>"+pfzmc+"-"+sjfwmc+"-"+khbmc+"</font>���Ѵ��ڣ������ظ���ӣ�");
		return false;
	}
	if(!nullsFlag){
		showAlertDivLayer("�뽫<span class='red'>*</span>��������д������");
		return false;
	}
	var pfdxJson = JSON.stringify(pfdxJson);
	jQuery("#pfdxJson").val(pfdxJson);
	var url = "khglKhxmgl.do?method=savePfdxSz";
	ajaxSubFormWithFun("KhxmglForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	}


//����
function dr() {
		toImportDataNew("IMPORT_N930101_KhxmDR");
}
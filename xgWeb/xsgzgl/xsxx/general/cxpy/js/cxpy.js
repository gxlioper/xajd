var DCCLBH="xsxx_gygl_cxcxpy.do";


//���Ӳ�������
function addCxpy(){
	showDialog('����ѧ����������',800,530,'xsxx_gygl_cxcxpy.do?method=addCxpy');
}
//����ѧ��
function addTr(){
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	//showTopWin('xsxx_gygl_cxcxpy.do?method=getStu&xn='+xn+'&xq='+xq,800,700);
	showDialog('ѡ��ѧ��',800,600,'xsxx_gygl_cxcxpy.do?method=getStu&xn='+xn+'&xq='+xq);
	
}
//ɾ��ѧ��
function delTr(){
	var array = jQuery("[name=xsxx]:checked");
	for (var i=0;i<array.length;i++) {
		var xsxx = jQuery(array[i]).parent().parent();
		var xh=xsxx.find("td").eq(1).text();
		xh+=",";
		var xhs=jQuery("#xhs").val();
		while(xhs.indexOf(xh)!=-1){
			xhs=xhs.replace(xh,"");
		}
		jQuery("#xhs").val(xhs);
		xsxx.remove();
	}
}

//ѡ��ѧ��
function zjBcStu(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	var api = frameElement.api,W = api.get('parentDialog');
//	W.document.getElementById("xhs").value=str;
//	api.close();
//	W.document.getElementById("btn_getXsxx").onclick();
	if(rows.length>=1){
		ids+=",";
		var id= W.document.getElementById("xhs").value;
		id+=ids;
		W.document.getElementById("xhs").value=id;
		api.close();
		W.document.getElementById("btn_getXsxx").onclick();
	}else{
		alertInfo("�빴ѡ��Ҫ��ӵ����ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
	
}
//�����������
function bcCxpy(){
	document.forms[0].action = window.location.href;
	document.forms[0].submit();

}

//����
function save(){
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var cxdjdm=jQuery("#cxdjdm").val();
	var cxpy=jQuery("#cxpy").val();
	var array = jQuery("[name=xsxx]");
	var bzr=jQuery("#bzr").val();
	var xxdm = jQuery("#xxdm").val();
	if(xn==""){
		alertInfo("��ѡ��ѧ�꣡",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if("13943" == xxdm) {
		if(jQuery("#sqsj").val()=="" || jQuery("#sqsj").val()==null){
			alertInfo("����дʱ�䣡",function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
	}else {
		if(xq==""||xq==null){
			alertInfo("��ѡ��ѧ�ڣ�",function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
	}
	if(cxdjdm==""||cxdjdm==null){
		alertInfo("��ѡ����еȼ���",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(bzr==""||bzr==null){
		alertInfo("����д�����Σ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(cxpy==""){
		alertInfo("����д�������",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(array.length==0){
		alertInfo("��ѡ��ѧ����",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var url="xsxx_gygl_cxcxpy.do?method=save";
	ajaxSubFormWithFun("cxpyForm",url,function(data){
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

function removeXs(){
	var array = jQuery("#tbody_zgryxx>tr");
	array.remove();
	jQuery("#xhs").val("");
}
//ȫѡ
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if (rows[0]["sjly"] == "1"){
			showAlertDivLayer("�������ݲ����޸ģ�");
			return false;
		}
		showDialog('�޸�ѧ����������',800,380,'xsxx_gygl_cxcxpy.do?method=updateCxpy&pk='+rows[0]["pk"]);
	}
}
//�鿴
function viewCxpy(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		showDialog('�鿴ѧ����������',800,310,'xsxx_gygl_cxcxpy.do?method=viewCxpy&pk='+rows[0]["pk"]);
	}
}
function cxpyView(id){
	showDialog('�鿴ѧ����������',800,310,'xsxx_gygl_cxcxpy.do?method=viewCxpy&pk='+id);
}

function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_gygl_cxcxpy.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 */
function importConfig() {
	toImportData("IMPORT_N710502");
	return false;
}
/**
 * ��ͥ�������--�鿴
 * @param xh
 * @return
 */
function jtqkView(xh){
	showDialog("��ͥ�������",945,500,"xszz_jtqkdc.do?method=dcxxView&xh="+xh);
}

/**
 * ��ͥ��������ѯ�б�--ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='jtqkView(\""+cellValue+"\");'>"+cellValue+"</a>";
}

/**
 * ��ͥ�������--��ѯ�¼�
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ��ͥ�������--�޸İ�ť�¼�
 * @return
 */
function jtqkUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog("��ͥ�������",945,500,"xszz_jtqkdc.do?method=dcxxModify&type=update&xh="+rows[0]["xh"]);
	}
}

/**
 * ��ͥ�������--ɾ����ť�¼�
 * @return
 */
function jtqkDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xszz_jtqkdc.do?method=dcxxDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	var DCCLBH = "xszz_jtqkdc_cx.do";//dcclbh,�������ܱ��
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var DCCLBH = "xszz_jtqkdc_cx.do";
	var url = "xszz_jtqkdc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}




/**
 * ��ͥ�������--��������
 * @return
 */
function saveForm(){
	var xxdm = jQuery("#xxdm").val();
	//����֤ѧ��
	var xh = jQuery("#xh").val();
	
	if (jQuery.trim(xh) == ""){
		showAlertDivLayer("����ѡ��ѧ����");
		return false;
	}
	if (!checkMustNotNull()){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	//��֤��ͥ��Ա������
	var btx = jQuery("input[name=btx]");
	var jtcyFlg = true;
	var jtcyNum = true;

	jQuery.each(btx,function(i,e){
		var name = jQuery(e).val();
		var inputList = jQuery("input[labName="+name+"]");
		var selectList = jQuery("select[labName="+name+"]");

		var inputNum = 0;
		jQuery.each(inputList,function(i,e){
			var textVal = jQuery(e).val();
			if (textVal != null && textVal != ''){
				inputNum++;
			} else {
				//��֤������ֵΪ�գ�ͬһ��������Ԫ���Ƿ��зǿ���
				var input = jQuery(e).parents("tr").eq(0).find("input").not(jQuery(e));
				var select = jQuery(e).parents("tr").eq(0).find("select");
				
				var textFlg = false;
				jQuery.each(input,function(i,e){
					var textValT = jQuery(e).val();
					if (textValT != null && textValT != ''){
						textFlg = true;
						return;
					}
				});
				
				var selectFlg = false;
				jQuery.each(select,function(i,e){
					var selectValT = jQuery(e).val();
					if (selectValT != null && selectValT != ''){
						selectFlg = true;
						return;
					}
				});
				
				if (textFlg || selectFlg){
					jtcyFlg = false;
					return;
				}				
			}
		});
		
		var selectNum = 0;
		jQuery.each(selectList,function(i,e){
			var selectVal = jQuery(e).val();
			if (selectVal != null && selectVal != ''){
				selectNum++;
			}else{
				//��֤������ֵΪ�գ�ͬһ��������Ԫ���Ƿ��зǿ���
				var input = jQuery(e).parents("tr").eq(0).find("input");
				var select = jQuery(e).parents("tr").eq(0).find("select").not(jQuery(e));
				
				var textFlg = false;
				jQuery.each(input,function(i,e){
					var textValT = jQuery(e).val();
					if (textValT != null && textValT != ''){
						textFlg = true;
						return;
					}
				});
				
				var selectFlg = false;
				jQuery.each(select,function(i,e){
					var selectValT = jQuery(e).val();
					if (selectValT != null && selectValT != ''){
						selectFlg = true;
						return;
					}
				});
				
				if (textFlg || selectFlg){
					jtcyFlg = false;
					return ;
				}
			}
		});
		
		//��֤�Ƿ�ȫ�������Ϊ�գ�������һ�еı������Ϊ�ա���
			if (inputNum == 0 && selectNum == 0){
				jtcyNum = false;
				return ;
			}

	});
	
		if (!jtcyNum){
			showAlertDivLayer("��������дһ����ͥ��Ա��");
			return false;
		}

	if (!jtcyFlg){
		showAlertDivLayer("��ͥ��Ա�������Ϊ�գ�");
		return false;
	}

	var url = "xszz_jtqkdc.do?method=saveDcxx";
	ajaxSubFormWithFun("jtqkdcForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	});
	
}

/*����ͥ��������Ƿ�м��������Ի�����*/
function sfcj_cjzbh(){
	/*�м����*/
	var paraMap = {
			"xh":jQuery('#xh').val()
	}
	var cjbh = getCjbh_10335();
	if(jQuery("input:radio[name='sfgc']:checked").val() == '1'){
		jQuery("input:radio[name='sfgc']").parent().parent().append("<font id='font_sfgc' color = 'blue'>&nbsp;&nbsp;&nbsp;�м�֤���</font><input type='text' style = 'width:100px;' name='ylzd4' maxlength='20' style='' sfbt='yes' value = '"+cjbh+"' >");
	}else{
		//������������Ϊ�˷�ֹ�д������ݣ���֤�Ƿ�м��Ͳм�֤�������һ�£���ͬ
		jQuery("input:radio[name='sfgc']").parent().parent().append("<input type='hidden' id = 'ylzd4' name='ylzd4'  style='' sfbt='no' value=''/>");
	}
	jQuery("input:radio[name='sfgc']").change(function(){
		if(this.value == '1'){
			jQuery("#ylzd4").remove();
			jQuery("input:radio[name='sfgc']").parent().parent().append("<font id='font_sfgc' color = 'blue'>&nbsp;&nbsp;&nbsp;�м�֤���</font><input type='text' style = 'width:100px;' name='ylzd4' maxlength='20' style='' sfbt='yes' value = '"+cjbh+"' >");
		}else{
			jQuery("#ylzd4").remove();
			jQuery("input:text[name='ylzd4']").remove();
			jQuery("#font_sfgc").remove();
			jQuery("input:radio[name='sfgc']").parent().parent().append("<input type='hidden'  id = 'ylzd4' name='ylzd4'  style='' sfbt='no' value=''>");
		}
	});
}

/*�㽭��ѧ����������������ǣ�����ʾ������ǽ�����������������ڱ�ҳ���·��ϴ����֤�����ϡ�*/
function jdlkh_Hint(){
	if(jQuery("input:radio[name='ylzd7']:checked").val() == '1'){
		jQuery("input:radio[name='ylzd7']").parent().parent().append("<font id='font_ylzd7' color = 'red'>&nbsp;&nbsp;&nbsp;������ڱ�ҳ���·��ϴ��������������֤�����ϣ�</font>");
	}
	jQuery("input:radio[name='ylzd7']").change(function(){
		if(this.value == '1'){
			jQuery("#ylzd7").remove();
			jQuery("input:radio[name='ylzd7']").parent().parent().append("<font id='font_ylzd7' color = 'red'>&nbsp;&nbsp;&nbsp;������ڱ�ҳ���·��ϴ��������������֤�����ϣ�</font>");
		}else{
			jQuery("#ylzd7").remove();
			jQuery("#font_ylzd7").remove();
		}
	});
}


function getCjbh_10335(){
	var cjbh = "" ;
	var url = "xszz_jtqkdc.do?method=getCjbh_10335";
	var paraMap = {
			"xh":jQuery('#xh').val()
	}
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'text',
		data:paraMap,
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(result){
			if(result == null || result == 'null'){
				cjbh = '';
			}else{
			    cjbh = result;
			}
		}
	});
	return cjbh;
}

function checkMoney_10026(obj){
		var text = obj.value;
		
		if (null !== text && ''!= text) {
			
			if (!/^[0-9]\d*\.[0-9]{2}$|^[0-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text) || text>999999) {
				
				showAlertDivLayer("����������ֲ����Ϲ���,����������������6λ��С����������λ������!",{},{"clkFun":function(){
					obj.focus();
				}});
			}
		}
}

/**
 * ����ҽҩ�������ɶ�ѡ���غ͸�ֵ
 * @return
 */
function loadCheckbox(){
	var tds = jQuery("td[name = checkboxTd]");
	jQuery.each(tds,function(i,n){
		var checkboxNode = jQuery(n);
		var sjy = checkboxNode.attr("source");//����Դ��������
		var checkboxName = checkboxNode.attr("property");
		var val = jQuery(n).attr("tempValue");
		var hidHtml = "<input  type='hidden' name='"+checkboxName+"' value='"+val+"' />";
		jQuery(n).append(hidHtml);
		//��������Դ�������飬��̨����������List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var checkboxHtml = "<div style='float:left;width:145px'><input onclick=\"hidHtml('"+checkboxName+"');\" type='checkbox' name='ckb_"+checkboxName+"' value='"+e["dm"]+"' ";
					var arr = val.split(",");
					for(i=0;i<arr.length;i++){
						if (e["dm"] == arr[i]){
							checkboxHtml+="checked='checked'";
						}
					}
					checkboxHtml+="/>"+e["mc"]+"</div>";
					
					var label = jQuery("<label></label>");
					label.append(checkboxHtml);
					jQuery(n).append(label);
				});
			}
		},'json');
	});
}
function hidHtml(name){
	var values = "";
	jQuery("input[name='ckb_"+name+"']:checked").each(function(){
		values = values + jQuery(this).val() + ",";
	});
	values = values.substring(0, values.length-1);
	jQuery("input[name='"+name+"']").val(values);
}
//�Ͼ��ߵ�ְҵ����ѧԺ
function loadCheckbox_10874(){
	var tds = jQuery("td[name = checkboxTd]");
	jQuery.each(tds,function(i,n){
		var checkboxNode = jQuery(n);
		var sjy = checkboxNode.attr("source");//����Դ��������
		//var checkboxName = checkboxNode.attr("property");
		//��������Դ�������飬��̨����������List
		jQuery.post("xszz_bdpz.do?method=getSjyList",{sjy:sjy},function(data){
			if (data != null){
				jQuery.each(data,function(i,e){
					var checkboxHtml = "<div style='float:left;width:255px'><input  type='checkbox' name='jtknlx' value='"+e["dm"]+"' ";
					
					if (jQuery(n).attr("tempValue").indexOf(e["dm"]) != -1 ){
						checkboxHtml+="checked='checked'";
					}
					
					checkboxHtml+="/>"+e["mc"]+"</div>";
					
					var label = jQuery("<label></label>");
					label.append(checkboxHtml);
					jQuery(n).append(label);
				});
			}
		},'json');
	});
} 


/**
 * ��ѯʦְ��������
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zghLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('�鿴��ѯʦ��Ϣ' , 750 , 420 , 'xljk_zxsgl.do?method=viewZxsxx&zgh="
			+ rowObject['zgh'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}
/**
 * ��ѯʦ��������
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zxsxmLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('�鿴��ѯʦ��Ϣ' , 750 , 420 , 'xljk_zxsgl.do?method=viewZxsxx&zgh="
			+ rowObject['zxs'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + (cellValue==null?" ":cellValue)
			+ "</a>";

	return el;
}
/**
 * ��ѯʦ����
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zxsLink(zgh) {
	showDialog('�鿴��ѯʦ��Ϣ' , 750 , 420 , "xljk_zxsgl.do?method=viewZxsxx&zgh="+zgh);
}
/**
 * ѧ�����ӣ���ʾԤԼ��ѯ������Ϣ��
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	
	var onclickfn = "onclick=\""
		+ "showDialog('�鿴������ѯԤԼ��Ϣ' , 750 , 360 , 'xljk_xsyyzx.do?method=viewXsyysq";
	
	if(rowObject['sqid']!=null){
		onclickfn += "&sqid=" + rowObject['sqid']+ "')" + "\"";
	}else {
		onclickfn += "&zxid=" + rowObject['zxid']+ "')" + "\"";;
	}

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * ѧ�����ӣ���ʾԤԼ��ѯ������Ϣ��������ѯ��Ϣ����ѯʦ������Ϣ��
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLinkForZxgl(cellValue, rowObject) {
	var onclickfn = "";
	
	if(rowObject['zxid']==null){
		onclickfn = "onclick=\""
			+ "showDialog('�鿴������ѯ' , 750 , 400 , 'xljk_yyzxfk.do?method=viewXlzxYyfk&sqid="
			+ rowObject['sqid'] + "')" + "\"";
	}else{
		onclickfn = "onclick=\""
			+ "showDialog('�鿴������ѯ' , 750 , 400 , 'xljk_xlzxcl.do?method=viewXlzxcl&zxid="
			+ rowObject['zxid'] + "')" + "\"";
	}
	
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * ԤԼ״̬
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function yyztChange(cellValue, rowObject) {
	var returnText;
	switch (cellValue) {
		case "1":
			returnText="ԤԼ��";
			break;
		case "2":
			returnText="<font color='blue'>ԤԼ�ɹ�</font>";
			break;
		case "3":
			returnText="ԤԼ��<br/>(ѧ��ȡ��)";
			break;
		case "4":
			returnText="ԤԼ�ɹ�<br/>(ѧ��ȡ��)";
			break;
		case "5":
			returnText="ԤԼʧ��";
			break;
		default:
			returnText="";
			break;
	}
	return returnText;
}
/**
 * ����״̬
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function yyfkztChange(cellValue, rowObject) {
	var returnText;
	var yyzt = rowObject['yyzt'];
	if(yyzt=='2' || yyzt == '4' || yyzt == '5'){
		returnText = "�ѷ���";
	}else if(yyzt=='1'){
		returnText = "<font color='red'>δ����</font>";
	}else{
		returnText = "";
	}
	return returnText;
}
/**
 * ѧ����ѯ����״̬
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zxztChange(cellValue, rowObject) {
	var returnText;
	switch (cellValue) {
		case "0":
			returnText="����ѯ";
			break;
		case "1":
			returnText="����ѯ";
			break;
		case "2":
			returnText="δ��ѯ";
			break;
		default:
			returnText="";
			break;
	}
	return returnText;
}

/**
 * չ���ر���ʾtbody
 */
function zkandbxqwdview(node,showid){
	if(jQuery(node).attr("class")=="down"){
		jQuery(node).attr("class","up");
		jQuery(node).html("&nbsp;&nbsp;����");
	}else{
		jQuery(node).attr("class","down");
		jQuery(node).html("&nbsp;&nbsp;չ��");
	}
	jQuery("#"+showid).toggle();
}
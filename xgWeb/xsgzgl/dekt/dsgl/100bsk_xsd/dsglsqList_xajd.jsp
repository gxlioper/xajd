<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<%@ include file="/syscommon/v4_url.ini"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript">
        var stylePath = "<%=stylePath%>";
    </script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">


    <script type="text/javascript" src="js/function.js"></script>
    <%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="js/comm/ymPrompt.js"></script>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src="js/comm/watermark.js"></script>
    <script language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="js/json.js"></script>
    <link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css" media="all" />
    <script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>

    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <%--<script type="text/javascript" src="xsgzgl/hdgl/js/hdxx.js"></script>--%>
</head>

<body>
<html:form action="/dekt_dsglsq" method="post" styleId="dsglsqForm" onsubmit="return false;">
<div class="activity-list-page m-t20">
<div class="search-wrap">
    <div class="container" style="padding-right:-5px;padding-left:-5px;">
        <div class="row">
            <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 padding-lr0">
                <div class="col-lg-5 col-md-5 col-sm-10 col-xs-12 padding-lr0">
                    <input type="text" class="form-control" id="" name="textStr" maxleng="50"
                           onkeypress="if(pressEnter(event)){searchRs();return false;}"
                           placeholder="����/����"/>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 search-btn-wrap">
                    <button type="button" class="btn btn-primary blue-bg-btn search-btn" id="search_go" onclick="searchRs()">����</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="secondclass_details container" style="width:100%;">
    <ul class="nav-tabs nav panel-heading notice-tabs col-sm-12 p-0">
        <li class="active boder-right">
            <a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('dy');return false;" id="dy">��һ</a>
        </li>
        <li class="boder-right">
            <a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('de');return false;" id="de">���</a>
        </li>
        <li>
            <a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('ds')" id="ds">����</a>
        </li>
        <li>
            <a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('dss')" id="dss">����</a>
        </li>
        <li>
            <a href="javascript:void(0);" data-toggle="tab" onclick="searchRs('myStore')" id="myStore">�ҵ��ղ�</a>
        </li>
    </ul>
</div>
<div class="container" id="activity-list" style="width:100%;">
</div>
</div>
<input id="xh" type="hidden" value="${userName}">
</html:form>
<script type="text/javascript">
    jQuery(function(){
        searchRs("dy");
    });

    /**
     * ��д�ĵ�
     */
    function addXd(btn){
        //��ȡ�������ǲ�DIV(<div class="col-md-8">)
        var brotherDiv = jQuery(btn.parentElement.previousElementSibling);
        var spans = brotherDiv.find("span");
        var ssm = spans[0].innerText;//��ȡ������
        var url = "dekt_dsglsq.do?method=add&ssm="+ encodeURI(encodeURI(ssm));
        var title = "��Ӷ����ĵ�";
        showDialog(title,790,550,url);
    }
    /*
    *�鿴�ĵ�
     */
    function update(btn){

        //��ȡ�������ǲ�DIV(<div class="col-md-8">)
        var brotherDiv = jQuery(btn.parentElement.previousElementSibling);
        var inputs = brotherDiv.find("input");
        var sqid = jQuery(inputs[0]).val();//���ID
//        var shzt = jQuery(inputs[1]).val();//���״̬
        var xh= jQuery("#xh").val();
        var url = 'dekt_dsglsq.do?method=view&xh='+ xh +'&sqid=' + sqid;
        showDialog("�鿴�����ĵ�", 720, 520, url);
    }

    /**
     * �ղ���ȡ��
     */
    function sc(btn){
        //��ȡ�������ǲ�DIV(<div class="col-md-8">)
        var brotherDiv = jQuery(btn.parentElement.previousElementSibling);
        var spans = brotherDiv.find("span");
        var ssm = spans[0].innerText;//��ȡ������
        var operation;
        if(btn.innerText == "�ղ�"){
            operation = "add";
        } else {
            operation = "delete";
        }
        var url = "dekt_dsglsq.do?method=sc";
        var param = {"ssm":encodeURI(ssm),"operation":operation};
        jQuery.post(url,param,function(data){
            if(data["flag"] == "success"){
                if(data["operation"] == "add"){
                    btn.innerText = "���ղ�"
                    btn.className = "btn btn-success";
                } else {
                    btn.innerText = "�ղ�";
                    btn.className = "btn btn-primary";
                }
            } else {
                if(data["operation"] == "add"){
                    showAlertDivLayer("�ղ�ʧ�ܣ�����");
                } else {
                    showAlertDivLayer("ȡ��ʧ�ܣ�����");
                }
            }
        },'json');
    }
    function searchRs(nj){
        //������ť���£�û���꼶����ȡѡ�е��꼶tab
        if(typeof nj == 'undefined'){
            //�ҵ���ǰѡ�е��꼶����Ϊ��������
            jQuery.find('li').forEach(function(i){
                if(i.classList.contains('active')){
                    nj = i.children[0].id
                }
            })
        }
        var url = "xg_dekt_dsglsq.do?method=onehundredBookList&type=query&nj="+nj;
        ajaxSubFormWithFun("dsglsqForm", url, function(data) {
            if(nj == 'dy'){
                jQuery("#dy").parent().attr('class','boder-right active');
                jQuery("#de").parent().attr('class','boder-right');
                jQuery("#ds").parent().attr('class','boder-right');
                jQuery("#dss").parent().attr('class','boder-right');
                jQuery("#myStore").parent().attr('class','boder-right');
                appHtml(data);
            }else if(nj == 'de'){
                jQuery("#dy").parent().attr('class','boder-right');
                jQuery("#de").parent().attr('class','boder-right active');
                jQuery("#ds").parent().attr('class','boder-right');
                jQuery("#dss").parent().attr('class','boder-right');
                jQuery("#myStore").parent().attr('class','boder-right');
                appHtml(data);
            }else if(nj == 'ds') {
                jQuery("#dy").parent().attr('class','boder-right');
                jQuery("#de").parent().attr('class','boder-right');
                jQuery("#ds").parent().attr('class','boder-right active');
                jQuery("#dss").parent().attr('class','boder-right');
                jQuery("#myStore").parent().attr('class','boder-right');
                appHtml(data);
            } else if(nj == 'dss'){
                jQuery("#dy").parent().attr('class','boder-right');
                jQuery("#de").parent().attr('class','boder-right');
                jQuery("#ds").parent().attr('class','boder-right');
                jQuery("#dss").parent().attr('class','boder-right active');
                jQuery("#myStore").parent().attr('class','boder-right');
                appHtml(data);
            } else {
                jQuery("#dy").parent().attr('class','boder-right');
                jQuery("#de").parent().attr('class','boder-right');
                jQuery("#ds").parent().attr('class','boder-right');
                jQuery("#dss").parent().attr('class','boder-right');
                jQuery("#myStore").parent().attr('class','boder-right active');
                appHtml(data,true);
            }
        });
    }
    function appHtml(data,hideScBtn){

        if (data[0] === null) {
            jQuery("#activity-list").empty();
            jQuery("#activity-list").html("<div><p align='center'>δ�ҵ��κμ�¼��</p></div>");
        } else {

            jQuery("#activity-list").empty();
            var content = "";
            for (var i in data) {
                var o = data[i];
                content+= '<div class="active-item row">';
                content+='<div class="col-md-8">';
                content+='<div class="pic"><img src="common_upload.do?method=asyncDownload2&gid='+o['stp']+'" style="width:80px;height:70px;"/></div>';
                content+='<div class="content">';
                content+='<span class="title"><a href="//'+o['ebook']+'" style="color: #0f0f0f" target="_blank">' + o['sm'] + '</a></span>';
                //��ֵ��
                content+='<input type="hidden" value="'+ o['sqid'] +'"/>';
                if(o["sftj"] === "1"){
                    content+= '<span class="title red">���Ƽ���</span>';
                }
                if(o["lx"] === "�ض�"){
                    content+= '<span class="title red">���ض���</span>';
                }
                content+='<div class="detail">';
                content+='<div>'+o['author']+ "&nbsp;&nbsp;&nbsp;" + "��������"+ o['dyl'] +'</div>';
                content+='</div>';
                content+='</div>';
                content+='</div>';
                content+='<div class="col-md-4">';
                if(!hideScBtn){
                    if(o['sfsc'] === "on"){
                        content+='<button type="button" class="btn btn-success" onclick="sc(this);return false;" style="width: 70px;">���ղ�</button>'
                    } else {
                        content+='<button type="button" class="btn btn-primary" onclick="sc(this);return false;" style="width: 70px;">�ղ�</button>'
                    }
                }
                if(o["sqid"] != ""){
                    content+='<button type="button" class="btn btn-success" onclick="update(this);return false;">�鿴�ĵ�</button>'
                } else {
                    content+='<button type="button" class="btn btn-primary" onclick="addXd(this);return false;">��д�ĵ�</button>'
                }
                content+='</div>';
                content+='</div>';
            }

            jQuery("#activity-list").append(content);
        }
    }
</script>
</html>


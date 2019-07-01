<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
        <%@ include file="/syscommon/head.ini"%>
        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
        <script type="text/javascript" src="js/check.js"></script>
	</head>

	<body>
	<html:form action="/hdgl_hdxq" method="post" styleId="hdxxForm" onsubmit="return false;">
        <input type="hidden" id="hdid" name="hdid" value="${data.hdid}"/>
        <input type="hidden" id="bmlx" name="bmlx" value="${data.bmlx}"/>
		<input type="hidden" id="shtgrs" name="shtgrs" value="${data.shtgrs == null ? 0 : data.shtgrs}" />
		<input type="hidden" id="pprs" name="pprs" value="${data.pprs == null ? 0 : data.pprs}" />
        <%--������������check��--%>
        <input type="hidden" id="totalNum" value="${data.rs}">
        <div style='width:100%;overflow-x:hidden;overflow-y:auto;height: 500px'>
            <table width="100%" border="0" class="formlist">
                <thead>
                    <tr>
                        <th colspan="2">
                            <span id="hd">�ѱ���/ͨ���������/����Ʊ����/��Ʊ���ޣ�${data.rs == null ? 0 : data.rs}��/${data.shtgrs == null ? 0 : data.shtgrs}��/${data.pprs == null ? 0 : data.pprs}��/${data.bmrs}��</span>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th width="15%">
                            <span><font color='red'>*</font>��Ʊ��</span>
                        </th>
                        <td width="35%">
                            <input id="ppnum" name="rs" style="width: 50px;">
                            <span>��</span>
                        </td>
                    </tr>
                    <tr>
                        <th width="15%">
                            <span>��Ʊ����</span>
                        </th>
                        <td width="35%">
							<input type="hidden" id="ppfs" value="${data.ppfs}" />
                            <div>
                                <input type="radio" id="ppfs1" name="pptype" value="0" onclick="return false">
                                <span>�����Ʊ</span>
                            </div>
                            <div>
                                <input type="radio" id="ppfs2" name="pptype" value="1" onclick="return false">
                                <span>�ȵ��ȵ�</span>
                            </div>
                            <div>
                                <input type="radio" id="ppfs3" name="pptype" value="2" onclick="return false">
                                <span>ѡȡ���籨��</span>
                                <input style="width: 50px;" id="bmzzrs" name="bmzzrs" value="${data.zzbmrs}"/>
                                <span>�ˣ����������ȡ</span>
                            </div>
                        </td>
                    </tr>
                <tr>
                    <th colspan="2">
                        <div class="btn">
                            <button type="button" onclick="startPp();return false;">
                                ��ʼ��Ʊ
                            </button>
                        </div>
                    </th>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <thead>
                <tr>
                    <th colspan="2">
                        <span>��Ʊ��Ա�б�</span>
                    </th>
                </tr>
                </thead>
            </table>
            <table id="dataTable">
            </table>
            <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
                <tfoot>
                <tr>
                    <td colspan="4">
                        <div class="btn">
                            <button type="button" onclick="save();return false;">
                                ȷ��
                            </button>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
	</html:form>
	</body>
</html>
<script type="text/javascript">
	jQuery(function(){
		debugger;
		var ppfs = jQuery("#ppfs").val();
		if(ppfs !== null && ppfs != ""){
			if(ppfs == "2"){
				jQuery("#ppfs2").attr("checked","checked");
			}else if(ppfs == "3"){
				jQuery("#ppfs3").attr("checked","checked");
			}else{
				jQuery("#ppfs1").attr("checked","checked");
			}
			
		}
		var shtgrs = jQuery("#shtgrs").val();
		var pprs = jQuery("#pprs").val();
		jQuery("#ppnum").val(shtgrs-pprs);
	})
	
    /**
     * ��ʼ��Ʊ
     */
    function startPp(){

        var ppnum = jQuery("#ppnum").val();
		var pprs = jQuery("#pprs").val();
		var shtgrs = jQuery("#shtgrs").val();
        var total = jQuery("#totalNum").val();
        if(typeof ppnum == 'undefined' || Number(ppnum) == 0){
            showAlertDivLayer("�������Ʊ����������");
            return false;
        }
        //��֤���ж��ٵ�Ʊѧ��
        jQuery.post("hdgl_hdxq.do?method=checkPpRs",{"hdid":jQuery("#hdid").val(),"ppnum":ppnum},function(data){
            if(data["message"] == "true"){
                if(Number(ppnum) > Number(total)){
                    showAlertDivLayer("��Ʊ�������ܴ��ڱ���������������");
                    return false;
                }
                var ppType = jQuery("input[type='radio']:checked").val();
                var bmzzrs;
                if("2" == ppType){
                    bmzzrs = jQuery("#bmzzrs").val();
                    if(typeof bmzzrs == 'undefined' || bmzzrs == "" || "0" == bmzzrs){
                        showAlertDivLayer("������ѡȡ���籨������������");
                        return false;
                    }
                    if(Number(bmzzrs) > Number(ppnum)){
                        showAlertDivLayer("���籨���������ܴ�����Ʊ����������");
                        return false;
                    }
                }
                var gridSetting = {
                    caption:"test-title",
                    pager:"pager",
                    url:"hdgl_hdxq.do?method=getPpPersonList",
                    colList:[
                        {label:'����ID',name:'sqid', index: 'sqid',width:'10%',hidden:true},
                        {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
                        {label:'����',name:'xm', index: 'xm',width:'10%'},
                        {label:'����ʱ��',name:'bmsj', index: 'bmsj',width:'8%'},
                    ],
                    params:{"hdid":jQuery("#hdid").val(),
                        "bmlx":jQuery("#bmlx").val(),
                        "pptype":ppType,
                        "ppnum":ppnum,
                        "bmzzrs":bmzzrs},
                    radioselect:false,
                    multiselect:false,
                }

                jQuery("#dataTable").initGrid(gridSetting);
            } else {
                showAlertDivLayer(data["message"]);
            }
        },'json');
    }

    function save(){
        var t = jQuery("table tbody")[1];
        if(typeof t == 'undefined'){
            showAlertDivLayer("����Ʊ������");
            return false;
        }
        var rows = t.rows;
        var arrayObj = new Array()
        for(var i = 0; i<rows.length; i++ ){
            arrayObj[i] = rows[i].cells[0].innerHTML;
        }
        var url = "hdgl_hdxq.do?method=setPpPersonList";
        var param = {"param":arrayObj,"bmlx":jQuery("#bmlx").val()};
        jQuery.post(url,param,function(data){
            if (data["message"] == "�ύ�ɹ���") {
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
        },'json');
    }
</script>

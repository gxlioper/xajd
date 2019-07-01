function add(hblx){
    var isopen_nzhb = jQuery("#isopen_nzhb").val();
    var isopen_nzzj = jQuery("#isopen_nzzj").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�㱨�ļ�¼��");
        return false;
    }
    if("nzhb" == hblx){
        if(isopen_nzhb==null||isopen_nzhb==''){
            showAlertDivLayer('���ڻ㱨��������δ��ʼ��������ϵ����Ա��');
            return false;
        }
        if ("false" == isopen_nzhb){
            showAlertDivLayer("��ǰ���ڻ㱨δ�������룬����ϵ����Ա��");
            return false;
        }
    }
    if("nzzj" == hblx){
        if(isopen_nzzj==null||isopen_nzzj==''){
            showAlertDivLayer('�����ܽ��������δ��ʼ��������ϵ����Ա��');
            return false;
        }
        if ("false" == isopen_nzzj){
            showAlertDivLayer("��ǰ�����ܽ�δ�������룬����ϵ����Ա��");
            return false;
        }
    }
    var url = "sxzzjy_bjxfjshb.do?method=bjxfjshbAdd&hblx="+hblx+
        "&jgid="+rows[0]["jgid"];
    var title = "����";
    showDialog(title,700,550,url);
}

function update(hblx) {
    var isopen_nzhb = jQuery("#isopen_nzhb").val();
    var isopen_nzzj = jQuery("#isopen_nzzj").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var sqid="";
    var shzt = "";
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�㱨�ļ�¼��");
        return false;
    }
    if("nzhb" == hblx){
        if(isopen_nzhb==null||isopen_nzhb==''){
            showAlertDivLayer('���ڻ㱨��������δ��ʼ��������ϵ����Ա��');
            return false;
        }
        if ("false" == isopen_nzhb){
            showAlertDivLayer("��ǰ���ڻ㱨δ�������룬����ϵ����Ա��");
            return false;
        }
        sqid = rows[0]["nzhbid"];
        shzt = rows[0]["nzhbshzt"];
    }
    if("nzzj" == hblx){
        if(isopen_nzzj==null||isopen_nzzj==''){
            showAlertDivLayer('�����ܽ��������δ��ʼ��������ϵ����Ա��');
            return false;
        }
        if ("false" == isopen_nzzj){
            showAlertDivLayer("��ǰ�����ܽ�δ�������룬����ϵ����Ա��");
            return false;
        }
        sqid = rows[0]["nzzjid"];
        shzt = rows[0]["nzzjshzt"];
    }

    if ("0" != shzt&&"3" != shzt) {
        showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
        return false;
    }
    var url = 'sxzzjy_bjxfjshb.do?method=bjxfjshbUpdate&sqid=' + sqid;
    var title = "�޸�";
    showDialog(title,700,550,url);

}

function selectHblx(type) {
    var ids = jQuery("#dataTable").getSeletIds();
    if("del" == type) {
        if (ids.length == 0) {
            showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼!");
            return  false;
        }
    }else{
        if (ids.length != 1) {
            showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
            return  false;
        }
    }
    var url = 'sxzzjy_bjxfjshb.do?method=selectHblx&type='+type+'&values='+ids.toString() ;
    var title = "";
    showDialog(title,300,150,url);
}

//ɾ��
function del(hblx) {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
        var hblxs = hblx.split(",");
        for(var a=0;a<hblxs.length;a++){
            if("nzhb" == hblxs[a]){
                for (var i = 0; i < ids.length; i++) {
                    if (rows[i]["nzhbshzt"] != "0" && rows[i]["nzhbshzt"] != "3") {
                        showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
                        return false;
                    }
                }
            }
            if("nzzj" == hblxs[a]){
                for (var i = 0; i < ids.length; i++) {
                    if (rows[i]["nzzjshzt"] != "0" && rows[i]["nzzjshzt"] != "3") {
                        showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
                        return false;
                    }
                }
            }
        }

        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("sxzzjy_bjxfjshb.do?method=bjxfjshbDel", { values : ids.toString(),hblxs:hblx },
                    function(data) {
                        var mes = "�ɹ�ɾ����<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>������";
                        showAlertDivLayer(mes);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
}

function view(jgid) {
    showDialog("�鿴", 700,550, "sxzzjy_bjxfjshb.do?method=bjxfjshbView&jgid=" + jgid);
}

function bjLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["jgid"] + "\");'>" + cellValue
        + "</a>";
}

function submit(hblx){
    var isopen_nzhb = jQuery("#isopen_nzhb").val();
    var isopen_nzzj = jQuery("#isopen_nzzj").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var values = new Array();
    var shzt = "";
    var hblxs = hblx.split(",");
    for(var a=0;a<hblxs.length;a++){
        var hblx = hblxs[a];
        if("nzhb" == hblx){
            if(isopen_nzhb==null||isopen_nzhb==''){
                showAlertDivLayer('���ڻ㱨��������δ��ʼ��������ϵ����Ա��');
                return false;
            }
            if ("false" == isopen_nzhb){
                showAlertDivLayer("��ǰ���ڻ㱨δ�������룬����ϵ����Ա��");
                return false;
            }
            values.push( "nzhb_"+rows[0]["nzhbid"]);
            shzt = rows[0]["nzhbshzt"];
        }
        if("nzzj" == hblx){
            if(isopen_nzzj==null||isopen_nzzj==''){
                showAlertDivLayer('�����ܽ��������δ��ʼ��������ϵ����Ա��');
                return false;
            }
            if ("false" == isopen_nzzj){
                showAlertDivLayer("��ǰ�����ܽ�δ�������룬����ϵ����Ա��");
                return false;
            }
            values.push( "nzzj_"+rows[0]["nzzjid"]);
            shzt = rows[0]["nzzjshzt"];
        }

        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
            return false;
        }
    }

    var url = "sxzzjy_bjxfjshb.do?method=bjxfjshbSubmit";
    showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
        jQuery.post(url,
            {values:values.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
    }});
}

function cancel(hblx){
    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = "";
    var splcs = new Array();
    var sqids = new Array();
    var hblxs = hblx.split(",");
    for(var a=0;a<hblxs.length;a++){
        var hblx = hblxs[a];
        if("nzhb" == hblx){
            sqids.push( rows[0]["nzhbid"]);
            splcs.push( rows[0]["nzhbsplc"]);
            shzt = rows[0]["nzhbshzt"];
        }
        if("nzzj" == hblx){
            sqids.push(rows[0]["nzzjid"]);
            splcs.push( rows[0]["nzzjsplc"]);
            shzt = rows[0]["nzzjshzt"];
        }

        if (shzt != '5') {
            showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
            return false;
        }

    }
    showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
        jQuery.post("sxzzjy_bjxfjshb.do?method=bjxfjshbCancel",
            {
                sqids:sqids.toString(),
                splcs : splcs.toString()
            },function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
    }});
}


function bjxfjshbLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        var sqids = new Array();
        var splcs = new Array();
        var hblxs = new Array();
        var nzzjid = rows[0]["nzzjid"];
        var nzhbid = rows[0]["nzhbid"];
        var nzhbshztmc = rows[0]["nzhbshztmc"];
        var nzzjshztmc = rows[0]["nzzjshztmc"];
        if((nzhbshztmc == "δ�ύ" || nzhbshztmc == "δ�㱨")
            && (nzzjshztmc == "δ�ύ" || nzzjshztmc == "δ�㱨")){
            showAlertDivLayer("��ѡ��һ�����ύ�ļ�¼��");
            return false;
        }
        if(nzhbid != "" && nzhbid !=null ){
            sqids.push(nzhbid);
            splcs.push(rows[0]["nzhbsplc"]);
            hblxs.push("nzhb");
        }
        if(nzzjid != "" && nzzjid !=null ){
            sqids.push(nzzjid);
            splcs.push(rows[0]["nzzjsplc"]);
            hblxs.push("nzzj");
        }
        showDialog("ѧ��֤�����������̸���",580,480,'comm_spl.do?method=lcgz_hb&sqids='
            +sqids.toString()+"&splcs="+splcs.toString()+"&hblxs="+hblxs.toString());
    }
}

function printWord(hblx){
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var len = rows.length;
    var url = "";
    if (len == 1) {
        if(rows[0][hblx+"id"] == "" || rows[0][hblx+"id"]==null){
            showAlertDivLayer("��ѡ������ɵļ�¼��");
            return false;
        }
        url = "sxzzjy_bjxfjshb.do?method=getPrint&hblx="+hblx;
        url += "&jgid=" + rows[0]["jgid"];
        window.open(url);
    } else if (len == 0) {
        showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
        return false;
    } else {
        if(hblx != "sq"){
            for(var i=0;i<rows.length;i++){
                if(rows[i][hblx+"id"] == "" || rows[i][hblx+"id"]==null){
                    showAlertDivLayer("��ѡ������ɵļ�¼��");
                    return false;
                }
            }
        }
        url = "sxzzjy_bjxfjshb.do?method=getPrintZip&hblx="+hblx;
        url += "&value=" + ids;
        window.open(url);
    }
}


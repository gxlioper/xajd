/**
 *
 * @param tarid У������
 * @param id ¥������
 */
function xqChange(tarid,id){
    var v = jQuery("#"+tarid).val();
    jQuery.post("gygl_zsgl_cwdhgl.do?method=getLddxxList",{xqdm:v},function(data){
        jQuery("#"+id).empty();
        var option = "<option value=''>---��ѡ��---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["lddm"]+"'>"+data[i]["ldmc"]+"</option>";
        }
        jQuery("#"+id).append(option);
        if(tarid == 'xqdm1'){
            ldChange(id,'ch1');
        } else {
            ldChange(id,'ch2');
        }
    },'json');
}

/**
 *
 * @param tarid ¥������
 * @param id ¥��
 */
function ldChange(tarid,id){
    var v = jQuery("#"+tarid).val();

    jQuery.post("gygl_zsgl_cwdhgl.do?method=getLddcs",{lddm:v},function(data){
        jQuery("#"+id).empty();
        var option = "<option value=''>---��ѡ��---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]+"'>"+data[i]+"</option>";
        }
        jQuery("#"+id).append(option);
        if(tarid == 'lddm1'){
            lcChange(tarid,id,'qsh1');
        } else {
            lcChange(tarid,id,'qsh2');
        }
    },'json');
}

/**
 *
 * @param tar1id ¥������
 * @param tar2id ¥���
 * @param id ���Һ�
 */
function lcChange(tar1id,tar2id,id){
    var v = jQuery("#"+tar1id).val();
    var ch = jQuery("#"+tar2id).val();
    jQuery.post("gygl_zsgl_cwdhgl.do?method=getQsxxList",{lddm:v,ch:ch},function(data){
        jQuery("#"+id).empty();
        var option = "<option value=''>---��ѡ��---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["qsh"]+"'>"+data[i]["qsh"]+"</option>";
        }
        jQuery("#"+id).append(option);

    },'json');
}

/**
 *
 * @param xqid У��id
 * @param ldid ¥��id
 * @param lcid ¥��id
 * @param qsid ����id
 */
function search(xqid,ldid,lcid,qsid,spanid,tableid,xhid){
    var v1 = jQuery("#"+xqid).val();
    var v2 = jQuery("#"+ldid).val();
    var v3 = jQuery("#"+lcid).val();
    var v4 = jQuery("#"+qsid).val();
    var v5 = jQuery("#"+xhid).val();
    if(v4==''){
        showAlert("��ѡ�����ң�");
        return;
    }

    var txt1 = jQuery("#"+xqid+" :selected").text();
    var txt2 = jQuery("#"+ldid+" :selected").text();
    var txt3 = jQuery("#"+lcid+" :selected").text();
    var txt4 = jQuery("#"+qsid+" :selected").text();
    var str = txt1+"У����"+txt2+"¥��"+txt3+"�㣬"+txt4+"����";
    jQuery("#"+spanid).html(str);
    if("xh1" == xhid){
        jQuery("#outSelectDiv").find("dl").remove();
    } else {
        jQuery("#inSelectDiv").find("dl").remove();
    }
    var url = "gygl_zsgl_cwdhgl.do?method=getCwxxList";
    jQuery.post(url,{lddm:v2,qsh:v4,xh:v5},function(data){
        jQuery("#"+tableid+" tbody").empty();
        var tr = "";
        for(var i=0;i<data.length;i++){
            tr += "<tr>";
            tr += "<td>"+data[i]["cwh"]+"</td>";
            tr += "<td>"+data[i]["qsxbmc"]+"</td>";
            tr += "<td>"+data[i]["sfblmc"]+"</td>";
            tr += "<td>"+nullToBlank(data[i]["xh"])+"</td>";
            tr += "<td>"+nullToBlank(data[i]["xm"])+"</td>";
            if("xh1" == xhid){
                tr += "<td><button type='button' class='btn btn-basics' onclick='select(this,\"outSelectDiv\");'>ѡ��</button></td>";
            } else {
                tr += "<td><button type='button' class='btn btn-basics' onclick='select(this,\"inSelectDiv\");'>ѡ��</button></td>";
            }
            tr += "<td style='display: none;'>"+data[i]["lddm"]+"@!!!"+data[i]["qsh"]+"@!!!"+data[i]["cwh"]+"</td>";
            tr += "</tr>";
        }
        jQuery("#"+tableid+" tbody").append(tr);
    },'json');
}
function nullToBlank(val){
    return val == null ? "":val;
}
function select(tar,appendid){
    var btn = jQuery(tar);
    var tds = btn.parent().siblings();
    //ת���������ѡ������ס�Ĵ�λ
    if(appendid=="outSelectDiv"){
        if(tds[3].innerText == ""){
            showAlert("��ѡ������ס�ĵĴ�λ��");
            return;
        }
    }

    var dlStr = "<dl>";
    if(appendid=="outSelectDiv"){
        //�ύ����̨�����ֵ
        dlStr += "<input style='display: none' name='outKey' value='"+tds[5].innerText+"'>";
    } else {
        dlStr += "<input style='display: none' name='inKey' value='"+tds[5].innerText+"'>";
    }
    dlStr += "<dd>��λ��"+tds[0].innerText+"</dd>";
    dlStr += "<dd>ѧ�ţ�"+tds[3].innerText+"</dd>";
    dlStr += "<dd>������"+tds[4].innerText+"</dd>";
    dlStr += "<dd><a href='javascript:void(0)' onclick='delDl(this)'>ɾ��</a></dd>";
    dlStr += "</dl>";
    jQuery("#"+appendid).append(dlStr);
    //������key���ѡ���е�btn
    btn.attr("mark",tds[5].innerText);
    btn.attr("disabled","disabled");
}
function delDl(tar){
    var dl = jQuery(tar).parent().parent();
    var key = dl.find("input").val();
    //�Ƴ���Ӧbtn��disabled����
    jQuery("button[mark='"+key+"']").removeAttr("disabled");
    //�Ƴ�ѡ��Ԫ��
    dl.remove();
}

function save(){
    var url = "gygl_zsgl_cwdhgl.do?method=cwdh&type=save";
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"]);
        }else{
            showAlert(data["message"]);
        }
    });
}
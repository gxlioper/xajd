/**
 *
 * @param tarid 校区代码
 * @param id 楼栋代码
 */
function xqChange(tarid,id){
    var v = jQuery("#"+tarid).val();
    jQuery.post("gygl_zsgl_cwdhgl.do?method=getLddxxList",{xqdm:v},function(data){
        jQuery("#"+id).empty();
        var option = "<option value=''>---请选择---</option>";
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
 * @param tarid 楼栋代码
 * @param id 楼层
 */
function ldChange(tarid,id){
    var v = jQuery("#"+tarid).val();

    jQuery.post("gygl_zsgl_cwdhgl.do?method=getLddcs",{lddm:v},function(data){
        jQuery("#"+id).empty();
        var option = "<option value=''>---请选择---</option>";
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
 * @param tar1id 楼栋代码
 * @param tar2id 楼层好
 * @param id 寝室号
 */
function lcChange(tar1id,tar2id,id){
    var v = jQuery("#"+tar1id).val();
    var ch = jQuery("#"+tar2id).val();
    jQuery.post("gygl_zsgl_cwdhgl.do?method=getQsxxList",{lddm:v,ch:ch},function(data){
        jQuery("#"+id).empty();
        var option = "<option value=''>---请选择---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["qsh"]+"'>"+data[i]["qsh"]+"</option>";
        }
        jQuery("#"+id).append(option);

    },'json');
}

/**
 *
 * @param xqid 校区id
 * @param ldid 楼栋id
 * @param lcid 楼层id
 * @param qsid 寝室id
 */
function search(xqid,ldid,lcid,qsid,spanid,tableid,xhid){
    var v1 = jQuery("#"+xqid).val();
    var v2 = jQuery("#"+ldid).val();
    var v3 = jQuery("#"+lcid).val();
    var v4 = jQuery("#"+qsid).val();
    var v5 = jQuery("#"+xhid).val();
    if(v4==''){
        showAlert("请选择寝室！");
        return;
    }

    var txt1 = jQuery("#"+xqid+" :selected").text();
    var txt2 = jQuery("#"+ldid+" :selected").text();
    var txt3 = jQuery("#"+lcid+" :selected").text();
    var txt4 = jQuery("#"+qsid+" :selected").text();
    var str = txt1+"校区，"+txt2+"楼，"+txt3+"层，"+txt4+"寝室";
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
                tr += "<td><button type='button' class='btn btn-basics' onclick='select(this,\"outSelectDiv\");'>选择</button></td>";
            } else {
                tr += "<td><button type='button' class='btn btn-basics' onclick='select(this,\"inSelectDiv\");'>选择</button></td>";
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
    //转出宿舍必须选择已入住的床位
    if(appendid=="outSelectDiv"){
        if(tds[3].innerText == ""){
            showAlert("请选择已入住的的床位！");
            return;
        }
    }

    var dlStr = "<dl>";
    if(appendid=="outSelectDiv"){
        //提交给后台处理的值
        dlStr += "<input style='display: none' name='outKey' value='"+tds[5].innerText+"'>";
    } else {
        dlStr += "<input style='display: none' name='inKey' value='"+tds[5].innerText+"'>";
    }
    dlStr += "<dd>床位："+tds[0].innerText+"</dd>";
    dlStr += "<dd>学号："+tds[3].innerText+"</dd>";
    dlStr += "<dd>姓名："+tds[4].innerText+"</dd>";
    dlStr += "<dd><a href='javascript:void(0)' onclick='delDl(this)'>删除</a></dd>";
    dlStr += "</dl>";
    jQuery("#"+appendid).append(dlStr);
    //用主键key标记选择行的btn
    btn.attr("mark",tds[5].innerText);
    btn.attr("disabled","disabled");
}
function delDl(tar){
    var dl = jQuery(tar).parent().parent();
    var key = dl.find("input").val();
    //移除对应btn的disabled属性
    jQuery("button[mark='"+key+"']").removeAttr("disabled");
    //移除选择元素
    dl.remove();
}

function save(){
    var url = "gygl_zsgl_cwdhgl.do?method=cwdh&type=save";
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlert(data["message"]);
        }else{
            showAlert(data["message"]);
        }
    });
}
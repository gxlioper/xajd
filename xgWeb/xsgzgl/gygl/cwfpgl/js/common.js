//search方法只加载楼层信息，寝室床位信息在点击楼层扩展图标后在加载
function search(){
    var lddm = jQuery("#lddm").val();
    var xb = jQuery("#xb").val();
    if(lddm == ""){
        showAlert("请选择楼栋！");
        return false;
    }
    var url = "gygl_zsgl_cwgl.do?method=search";
    jQuery.post(url,{lddm:lddm,qsxb:xb},function(data){
        jQuery("#lcdiv").empty();
        if(data != null && data.length > 0){
            createLcDiv(data);
        }
    },'json');
}
//构建楼层div
function createLcDiv(data){
    var str = "<div class=\"page-header\" style=\"position: relative;\">";
    str += "<div class=\"icon\">";
    str += "<input type='hidden' name='lddmkey' value='"+data[0]["lddm"]+"'>";
    str += "<span class=\"basics-color basics-weight\">"+data[0]["ldmc"]+"</span>";
    str += "</div>";
    str += "<img src=\"./images/page-header@2x.png\" alt=\"\">";
    str += "</div>";
    for(var i=0;i<data.length;i++){
        str+="<div class=\"check-top\">";
        str+="<div class=\"check-top-iteam\">";
        str+="<input type=\"checkbox\" id='check-default-"+i+"' class='check-default' name='lcKey' value='"+data[i]["ch"]+"' onchange='lcCheckBoxChange(this)'>";
        str+="<label for='check-default-"+i+"'></label>";
        str+="<div class=\"iteam-right\">";
        str+="<li>"+data[i]["ch"]+"层</li>";
        str+="<li>寝室数/床位数："+data[i]["qss"]+"/"+data[i]["cws"]+"</li>";
        str+="</div></div>";
        str+="<div class=\"check-top-iteam\">";
        str+="<div class=\"progress\">";
        str+="<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+Number(data[i]["yfpcws"])/Number(data[i]["cws"])*100+"%;\"></div>";
        str+="</div>";
        str+="<div class=\"progress-left\">";
        str+="<span class=\"block\"></span>";
        str+="<span>当前班级已分配床位数："+data[i]["yfpcws"]+"</span>";
        str+="</div>";
        str+="<div class=\"progress-right\">";
        str+="<span class=\"block\"></span>";
        str+="<span>空床位数："+data[i]["kcws"]+"</span>";
        str+="</div>";
        str+="</div>";
        str+="<input type='hidden' value='"+data[i]["lddm"]+"@!!!"+data[i]["ch"]+"'>";
        // str+="<i class=\"fa fa-chevron-right\"></i>";
        str+="<img class=\"down-img\" src=\"./images/down.png\" alt='' onclick='changeImg(this)'>";
        str+="</div>";
        str+="</div>";
    }
    jQuery("#lcdiv").append(str);
}

function createOption(data){
    var str = "";
    for(var i=0;i<data.length;i++){
        if(typeof data[i]["mc"] != "undefined" && data[i]["mc"].trim() != ""){
            str += "<option value='"+data[i]["dm"]+"'>"+data[i]["mc"]+"</option>";
        }
    }
    return str;
}

function changeImg(tar){
    var img = jQuery(tar);
    var imgP = img.parent();
    var imgPNext = imgP.next();
    if(img.attr("src") == "./images/down.png"){
        img.attr("src","./images/select-top@2x.png");
        var classStyle = imgPNext.attr("class");
        if(classStyle == "check-top" || typeof classStyle == "undefined"){
            var key = img.prev().val();
            //加载寝室床位信息
            getQscwxx(key,imgP);
        } else {
            imgPNext.toggleClass("cwClass");
        }
    } else {
        img.attr("src","./images/down.png");
        imgPNext.toggleClass("cwClass");
    }
}

function getQscwxx(key,imgP){
    var url = "gygl_zsgl_cwgl.do?method=getQsCw";
    jQuery.post(url,{key:key},function(data){
        if(data != null){
            createQscwDiv(data,imgP);
        }
    },'json');
}

function lcCheckBoxChange(tar){
    var thisBox = jQuery(tar);
    var v = thisBox.val();
    var c = jQuery("input[marklabel^='"+v+"']");
    if(typeof c != "undefined" && c.length > 0){
        if(thisBox.is(':checked')){
            c.attr("checked","checked");
        } else {
            c.removeAttr("checked");
        }
    }
}
function qsCheckBoxChange(tar){
    var thisBox = jQuery(tar);
    var v = thisBox.val();
    var c = jQuery("input[marklabel='"+v+"']");
    if(typeof c != "undefined"){
        if(thisBox.is(':checked')){
            c.attr("checked","checked");
        } else {
            c.removeAttr("checked");
        }
    }
}

function nullToBlank(val){
    return val == null ? "":val;
}

function qkFp(){
    var ld = jQuery("#lddm").val();
    if(ld=="" || typeof ld == "undefined"){
        showAlert("请选择楼栋！");
        return false;
    }
    showConfirmDivLayer("您确定要清空此楼栋的全部分配吗？", {
        "okFun" : function() {
            var url = "gygl_zsgl_cwgl.do?method=qkFp";
            jQuery.post(url,{lddm:ld},function(data){
                showAlertDivLayer(data["message"]);
                search();
            },'json');
        }
    })
}
function fh(){
    window.location.href = "gygl_zsgl_cwfp.do";
}
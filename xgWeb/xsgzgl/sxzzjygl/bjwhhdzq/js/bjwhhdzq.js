jQuery(function(){
    //获取类型列表
    jQuery.post("bjwhhdzq_bjwh.do?method=getAlbForView",function(data){
        var	title="";
        var maxLen = (data.length > 5) ? 5 : data.length;
        for (var i = 0 ; i < maxLen ; i++){
            var typemc=data[i]["typemc"];
            var typedm=data[i]["typedm"];
            if(i == 0)
                jQuery("#firstType").val(data[i]["typedm"]);
            //处理lable分类
            if(i==0){
                //第一个选中
                title+="<li class='active' role='presentation' id=\"tag"+i+"\">";
            }else{
                title+="<li id=\"tag"+i+"\" role='presentation' class=''>";
            }
            title+="<input type=\"hidden\" value=\""+typedm+"\">";
            title+="<a onclick=\"typeLable(this)\" data-toggle='tab'>"+typemc+"</a>";
            title+="</li>";
        }
        jQuery("#tagslist01").append(title);
        var object=jQuery("#tagslist01").find("li").first().find("a");
        typeLable(object);
        //点击more跳转是默认选中第一个类型
        var newstype = jQuery("#firstType").val();
        jQuery("#more").attr("href","bjwhhdzq_bjwh.do?method=getNewsmore&newstype="+newstype);
    },"json");


});

function typeLable(object){
    var typedm=jQuery(object).prev().val();

    //加载具体内容列表
    jQuery.post("bjwhhdzq_bjwh.do?method=getNewsList",{"size":15,"typedm":typedm},function(data){
        var liHtml="";
        for (var i = 0 ; i < data.length ; i++){
            var newstitle=data[i]["hdmcbjmc"];
            if(newstitle.length > 24){
                newstitle = newstitle.substr(0,24)+"...";
            }
            //处理内容
            liHtml+="<a onclick='View(\""+data[i]["jgid"]+"\",\""+typedm+"\")' href='javascript:void(0);'  class='list-group-item'>";
            liHtml+="<span class='badge'>"+data[i]["hdrq"]+"</span>"+newstitle;
            liHtml+="</a>";
        }
        jQuery("#content0").html(liHtml);
        jQuery("#more").attr("href","bjwhhdzq_bjwh.do?method=getNewsmore&hdlx="+typedm);
    },"json");
}

function View(jgid,typedm) {
    if(typedm == "001")
    {
        showDialog("活动信息", 900, 450, "ztbhgl_ztbhjg.do?method=getHdInfo&jgid=" + jgid );
    }
    else{
        showDialog("活动信息", 900, 450, "bjhdgl_bjhdjg.do?method=getHdInfo&jgid=" + jgid );
    }

}
jQuery(function(){
    //��ȡ�����б�
    jQuery.post("sxzzjy_xymxbzbgl.do?method=getAlbForView",function(data){
        var	title="";
        var maxLen = (data.length > 5) ? 5 : data.length;
        for (var i = 0 ; i < maxLen ; i++){
            var typemc=data[i]["typemc"];
            var typedm=data[i]["typedm"];
            if(i == 0)
                jQuery("#firstType").val(data[i]["typedm"]);
            //����lable����
            if(i==0){
                //��һ��ѡ��
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
        //���more��ת��Ĭ��ѡ�е�һ������
        var newstype = jQuery("#firstType").val();
        jQuery("#more").attr("href","sxzzjy_xymxbzbgl.do?method=getNewsmore&newstype="+newstype);
    },"json");


});

function typeLable(object){
    var typedm=jQuery(object).prev().val();

    //���ؾ��������б�
    jQuery.post("sxzzjy_xymxbzbgl.do?method=getNewsList",{"size":15,"typedm":typedm},function(data){
        var liHtml="";
        for (var i = 0 ; i < data.length ; i++){
            var newstitle=data[i]["newstitle"];
            if(newstitle.length > 24){
                newstitle = newstitle.substr(0,24)+"...";
            }
            //��������
            liHtml+="<a onclick='zjydmc(\""+data[i]["newsid"]+"\");view(\""+data[i]["newsid"]+"\")' href='javascript:void(0);'  class='list-group-item'>";
            liHtml+="<span class='badge'>"+data[i]["fbsj"]+"</span>"+newstitle;
            if (data[i]["sfzd"] == "��"){
                liHtml += "<font color=\"red\" style=\"float:left;\">���ö���</font>";
            }
            liHtml+="</a>";
        }
        jQuery("#content0").html(liHtml);
        jQuery("#more").attr("href","sxzzjy_xymxbzbgl.do?method=getNewsmore&newstype="+typedm);
    },"json");
}

function zjydmc(newsid){
    jQuery.post("sxzzjy_xymxbzbgl.do?method=addYdr",{"newsid":newsid});
}
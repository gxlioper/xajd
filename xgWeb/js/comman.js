document.write("<script type=\"text/javascript\" src=\"js/page.js\"></script>");
document.write("<script type=\"text/javascript\" src=\"js/BatAjax.js\"></script>");
document.write("<script language=\"javascript\" src=\"dwr/util.js\"></script>");
document.write("<script language=\"javascript\" src=\"dwr/engine.js\"></script>");

//上一个页面
function UpPage(page,xmlfile)
{
    thePage="前一页";
    if(page+1>1) thePage="<A HREF='#' onclick='Javascript:return UpPageGo(tempxml)'>前一页</A>";
    return thePage;
}
function NextPage(page,xmlfile)
{
    thePage="后一页";
    if(page<pagesNumber) thePage="<A HREF='#' onclick='Javascript:return NextPageGo(tempxml)'>后一页</A>";
    return thePage;
}

function UpPageGo(xmlfile){ 

if(page>0) page--; 
    getContent(xmlfile); 
    BodyText=""; 

} 
//当前的页数
function currentPage()
{
    var cp;
    cp="当前是第 "+(page+1)+" 页";
    return cp;
}
//总共的页数
function allPage()
{
    var ap;
    ap='总共 '+(pagesNumber+1)+' 页';
    return ap
}
function NextPageGo(xmlfile)
{ 
if (page<pagesNumber) page++;

    getContent(xmlfile); 
    BodyText="";
} 

//显示分页状态栏
function pageBar(page,xmlfile)
{
    var pb;
    pb=UpPage(page,xmlfile)+"  "+NextPage(page,xmlfile)+"  "+currentPage()+"  "+allPage()+selectPage(xmlfile);
    return pb;
}
function changePage(tpage,xmlfile)
{    

    page=tpage
    if(page>=0) page--; 
    if (page<pagesNumber) page++;
    getContent(xmlfile); 
    BodyText="";
}

function selectPage()
{
    var sp;
    sp="<select name='hehe' onChange='javascript:changePage(this.options[this.selectedIndex].value,tempxml)'>";
    sp=sp+"<option value=''></option>";
    for (t=0;t<=pagesNumber;t++)
    {
        sp=sp+"<option value='"+t+"'>"+(t+1)+"</option>";
    }
    sp=sp+"</select>"
    return sp;
}


/**定义公用方法:替换字符串*/
function replaceChar(conversionString, inChar, outChar) {
	var convertedString = conversionString.split(inChar);
	convertedString = convertedString.join(outChar);
	return convertedString;
}



function refurbish(){	
	var queryStr = document.getElementById("query").value;
	var typeStr = document.getElementById("lmdm").value;
	portalAjax.getIndexNum(queryStr,typeStr,setRsCount);
}
function callback(pages) {
	var n = document.getElementById("pagesize").innerText;
	var queryStr = document.getElementById("query").value;
	var typeStr = document.getElementById("lmdm").value;
	var titleLength = document.getElementById("titleLength").value;
	var contentLength = document.getElementById("contentLength").value;
 	portalAjax.getIndexList(queryStr,typeStr,pages,n,titleLength,contentLength,dispIndexList);
	document.getElementById("pages").innerText=pages;
    DWRUtil.setValue("ddl_ys",pages);
    blank_page();
}


function dispIndexList(data) {
	if(data == null) {
		return false;
	} else {
		var i = 0;
		var l = data.length;
		var str = "<h3>查询结果（共"+document.getElementById("RsCount").value+"条记录）：</h3>\r";
		str += "<ul>\r";
		for(i = 0;i < l;i++) {
			str += "<li><a target=\"_blank\" href=\""+data[i].url+"\">" + data[i].title+ "</a>"
			+"<span class=type>"+data[i].pubuser+"</span><span class=time>"+data[i].pubtime+" </span>" + "<p>" +data[i].content+ "</p></li>";
		}
		str += "</ul>\r";
		document.getElementById("showhtml").innerHTML=str;
	}
	/**
	if(parseInt(document.getElementById("RsCount").value) > 5) {
		document.getElementById("showpage").style.display="";
	} else {
		document.getElementById("showpage").style.display="none";
	}
	*/
}



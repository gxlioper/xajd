<%@ page language="java" pageEncoding="GBK"%>
<jsp:directive.page import="xgxt.form.CommanForm"/>
<jsp:directive.page import="xgxt.jygl.comman.JyglForm"/>
<jsp:directive.page import="xgxt.utils.Pages"/>
<%@taglib prefix="template" uri="/WEB-INF/struts-template.tld"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%--<%@ include file="/syscommon/v4_url.ini"%>--%>
<%--<script type="text/javascript">--%>
<%--	var stylePath = "<%=stylePath%>";--%>
<%--</script>--%>
<%--<link rel="stylesheet" href="<%=stylePath %>css/public.css" type="text/css" media="all" />--%>
<html:hidden styleId="page_Size" property="pages.pageSize" value="10" />
<html:hidden styleId="currentPage"  property="pages.currentPage" />
<html:hidden styleId="maxPage"  property="pages.maxPage" />




<style>
<%--.pagination { width:100%; background:#F2F2F2; padding:2px 0; overflow:hidden; text-align: right; margin-top:15px; }--%>
<%--.pagination a { text-decoration: underline }--%>
<%--.pagination .pageleft .pagenum { display:inline; padding:0 3px; }--%>
<%--.pagination .pageleft .pagenum input { width:28px; height:15px; margin:0 3px; border:1px solid #9A9A9A;}--%>
<%--/*=======分页样式=========*/--%>
<%--.pagination .pageright { float: right; padding-right: 1.2em; }--%>
<%--.pagination .pageright .paging { padding:3px 0; }--%>
<%--.pagination .pageright .paging a {border:1px solid #9AC9EA; color:#373737; display:inline-block; padding:2px 8px 0px 8px; text-decoration:none; background:url(../images/btn_page.gif) repeat-x left center; }--%>
<%--.pagination .pageright .paging a:hover { background:url(../images/btn_page_hover.gif) repeat-x left center; }--%>
a:active{
color:red;
}
</style>

<%
String form = request.getParameter("form");

%>
<bean:define name="<%=form%>" property="pages" id="pages"></bean:define>

<input type="hidden" name="isPage" id="isPage" value=""/>
<input type="hidden" id="totlePage" value="${pages.maxPage }" />
<div class="pagination" style="width:100%">
	<div style="float:left">
		<p class="pagenum">
			<span style="display:none">第
			<span class="red">			
			<input type="text" class="text_nor" id="pageno" value="${pages.currentPage}"
				onkeypress="if(event.keyCode==13) {submitNoPage();return false;}" size='2'
				name='pageno'  style="width:20px"/></span> 页/ 共
			<span class="red" id="pageCount"><bean:write name="pages" property="maxPage" /></span>页，</span> 每页显示
			<input type="text" value="10" onkeypress="if(event.keyCode==13) {submitNoPage();return false;}" size="2" style="width:20px" class="text_nor" id="pagesize" name="pagesize"/>
			条/共
			<span class="red" id="total"><bean:write name="pages" property="maxRecord" /></span>条记录
		</p>
	</div>
	<div style="float:right;margin-left: -50px" >
		<!-- 分页位置 -->
		<div id="pagediv" class="paging" style="font-size: 14px">
			<span id="pagelist" class="pagelist"></span>
			<a id="first" href="javascript:submitFirstPage()" class="first"
				title="首页">首 页</a>
			<a id="pre" href="javascript:submitPrePage()" class="prev"
				title="上一页">上一页</a>
			<a id="next" href="javascript:submitNextPage()" class="next"
				title="下一页">下一页</a>
			<a id="last" href="javascript:submitLastPage()" class="last"
				title="末页">末 页</a>
		</div>
	</div>
</div>
<%--<script defer="defer">--%>
<%--	var currPage = $('pageno').value;--%>
<%--	var totlePage = $('totlePage').value;--%>
<%----%>
<%--	if(currPage == '1'){--%>
<%--		$('first').style.disabled = 'disabled';--%>
<%--		$('pre').style.disabled = 'disabled';--%>
<%--	}--%>
<%----%>
<%--	if(eval(currPage) == eval(totlePage)){--%>
<%--		$('last').style.disabled = 'disabled';--%>
<%--		$('next').style.disabled = 'disabled';--%>
<%--	}	--%>
<%--</script>--%>

<script language="JavaScript">
function submitFirstPage() {
	var page = $("currentPage").value;
	if (page == 1 || page == 0) {
		return;
	}
	$("currentPage").value = "1";
	$("isPage").value = "yes";
    //document.getElementById("search_go").click();
    queryData('0');  
}

function submitPrePage() {
	var page = $("currentPage").value;
	
	if (page == 1 || page==0) {
		return;
	} 
	else {
		$("currentPage").value = page - 1;
	}
	$("isPage").value = "yes";
	//document.getElementById("search_go").click();
	queryData('0');
}

function submitNextPage() {
	debugger;
	var page = parseInt($("currentPage").value);
	var mpage = parseInt($("maxPage").value);
	
	if (page == mpage) {
		return;
	} 
	else {
		$("currentPage").value = page - (-1);
	}
	$("isPage").value = "yes";
	//document.getElementById("search_go").click();
	queryData('0');
}


function submitLastPage() {
	jQuery("#maxPage").val(jQuery("#pageCount").text());
	var page = parseInt($("currentPage").value);
	var mpage = parseInt($("maxPage").value);
	if (page == mpage) {
		return;
	} 
	else {
		$("currentPage").value =mpage;
	}
	$("isPage").value = "yes";
	//document.getElementById("search_go").click();
	queryData('0');
}

function submitNoPage() 
{
	var page = parseInt($("pageno").value);
	var mpage = parseInt($("maxPage").value);	
	var currentPage=parseInt($("currentPage").value);
	var pagesize = $("pagesize").value;
	if (page > mpage) {
		return;
	} 
	//else if(page==currentPage){
		//return;
	//}
	else{
		$("currentPage").value =page;
	}
	if(pagesize != null && pagesize !=''){
		$("page_Size").value =pagesize;
	}
	
	$("isPage").value = "yes";
	
	if($("editPageSize")){
		$("editPageSize").value = "yes";
	}
	//document.getElementById("search_go").click();
	queryData('0');
}

function submitPage(page) 
{  
	var mpage = parseInt($("maxPage").value);
	var currentPage=parseInt($("currentPage").value);
	if (page > mpage) {
		return;
	} else if(page==currentPage){
		return;
	} else{
		
		$("currentPage").value =page;
	}
	$("isPage").value = "yes";
	//document.getElementById("search_go").click();
	queryData('0');
}

function reachTop(){
	top.scrollTo(0, 0);
}

function queryData(cxzt){ //cxzt 0:分页查询    1:点击查询
	 // if(jQuery("#rySearch").length > 0){
		 var ymmc = jQuery("#ymmc").val();
		 if(ymmc == 'hdcyList' || ymmc == 'hdcyList'){//报名审核页面
			 searchRy(cxzt);
		 }else if(ymmc == 'hdxqList' || ymmc == 'hdxqList'){//活动详情管理页面
			 searchHd(cxzt);
		 }else if(ymmc == 'zxsx' || ymmc == 'zxsx'){//最新上线活动页面
			 searchRs(cxzt,'zxsx');
		 }else if(ymmc == 'zjjb' || ymmc == 'zjjb'){//最近举办活动页面
			 searchRs(cxzt,'zjjb');
		 }else if(ymmc == 'wcj' || ymmc == 'wcj'){//我参加的活动页面
			 searchRs(cxzt,'wcj');
		 }
	 // }else{
		// searchRs(cxzt);
	 // }
	 reachTop();
}
</script>

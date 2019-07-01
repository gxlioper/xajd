<%@ page language="java" pageEncoding="GBK"%>
<jsp:directive.page import="xgxt.form.CommanForm"/>
<jsp:directive.page import="xgxt.jygl.comman.JyglForm"/>
<jsp:directive.page import="xgxt.utils.Pages"/>
<%@taglib prefix="template" uri="/WEB-INF/struts-template.tld"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<html:hidden styleId="page_Size" property="pages.pageSize" />
<html:hidden styleId="currentPage"  property="pages.currentPage" />
<html:hidden styleId="maxPage"  property="pages.maxPage" />


<style>
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
<div class="pagination">
	<div class="pageleft">
		<!-- 全选反选位置 -->
		<div class="choose" id="choose">
			<input name="cb" type="checkbox" value="全选" onclick="selectAllCheckbox(this);" />
			全选
			<input name="反选" type="checkbox" value="反选" onclick="turnSelect(this);" />
			反选
		</div>
		<p class="pagenum">
			<span style="display:none">第
			<span class="red">			
			<input type="text" class="text_nor" id="pageno" value="${pages.currentPage}"
				onkeypress="if(event.keyCode==13) {submitNoPage();return false;}" size='2'
				name='pageno'  style="width:20px"/></span> 页/ 共
			<span class="red"><bean:write name="pages" property="maxPage" /></span>页，</span> 每页显示
			<input type="text" value="${pages.pageSize}" onkeypress="if(event.keyCode==13) {submitNoPage();return false;}" size="2" style="width:20px" class="text_nor" id="pagesize" />
			条/共
			<span class="red"><bean:write name="pages" property="maxRecord" /></span>条记录
		</p>
	</div>
	<div class="pageright">
		<!-- 分页位置 -->
		<div id="pagediv" class="paging">
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
<script defer="defer">

	
	var table = null == jQuery('table.dateline') ? jQuery('table.dateline tablenowrap') : jQuery('table.dateline');
	var checkbox = jQuery('input[type=checkbox]',table).eq(0);
	
	
	if (jQuery(checkbox) && !jQuery(checkbox).attr('disabled')){
		jQuery(checkbox).attr('style','display:none');
	}

	var currPage = $('pageno').value;
	var totlePage = $('totlePage').value;

	if(currPage == '1'){
		$('first').style.disabled = 'disabled';
		$('pre').style.disabled = 'disabled';
	}

	if(eval(currPage) == eval(totlePage)){
		$('last').style.disabled = 'disabled';
		$('next').style.disabled = 'disabled';
	}
	
</script>
<script language="JavaScript">
function submitFirstPage() {
	var page = $("currentPage").value;
	if (page == 1 || page == 0) {
		return;
	}
	$("currentPage").value = "1";
	$("isPage").value = "yes";
    document.getElementById("search_go").click();
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
	document.getElementById("search_go").click();
}

function submitNextPage() {
	var page = parseInt($("currentPage").value);
	var mpage = parseInt($("maxPage").value);
	
	if (page == mpage) {
		return;
	} 
	else {
		$("currentPage").value = page - (-1);
	}
	$("isPage").value = "yes";
	document.getElementById("search_go").click();
}


function submitLastPage() {
	var page = parseInt($("currentPage").value);
	var mpage = parseInt($("maxPage").value);
	if (page == mpage) {
		return;
	} 
	else {
		$("currentPage").value =mpage;
	}
	$("isPage").value = "yes";
	document.getElementById("search_go").click();
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
	document.getElementById("search_go").click();
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
	document.getElementById("search_go").click();
}


//反选
function turnSelect(obj){
	var table = null == jQuery('table.dateline') ? jQuery('table.dateline tablenowrap') : jQuery('table.dateline');
	var checkBoxArr = new Array();
	
	if (table){
		checkBoxArr = jQuery('input[type=checkbox]',table);
	}
	for(var i=0;i<checkBoxArr.length;i++){
		if (!jQuery(checkBoxArr[i]).attr('disabled')){
			
			var checked = jQuery(checkBoxArr[i]).attr('checked')
		
			jQuery(checkBoxArr[i]).attr('checked',!checked);
		}
	}
}

function selectAllCheckbox(obj){
	var table = null == jQuery('table.dateline') ? jQuery('table.dateline tablenowrap') : jQuery('table.dateline');
	var checkBoxArr = new Array();
	
	if (table){
		checkBoxArr = jQuery('input[type=checkbox]',table);
	}
	
	for(var i=0;i<checkBoxArr.length;i++){
		if (!jQuery(checkBoxArr[i]).attr('disabled')){
			jQuery(checkBoxArr[i]).attr('checked',obj.checked);
		}
	}
	
}

</script>

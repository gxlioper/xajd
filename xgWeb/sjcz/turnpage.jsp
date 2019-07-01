<%@ page language="java" pageEncoding="GBK"%>
<jsp:directive.page import="xgxt.form.CommanForm"/>
<jsp:directive.page import="xgxt.jygl.comman.JyglForm"/>
<jsp:directive.page import="xgxt.utils.Pages"/>
<%@taglib prefix="template" uri="/WEB-INF/struts-template.tld"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<html:hidden styleId="page_Size" property="pages.pageSize" />
<html:hidden styleId="currentPage" property="pages.currentPage" />
<html:hidden styleId="maxPage" property="pages.maxPage" />
<input type="hidden" name="fanye" id="fanye" value="">

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
			<input name="cb" id="allCheck" type="checkbox" value="全选" onclick="canCheckOne(this);selectAllCheckbox(this);" />
			全选
			<input name="反选" id="turnCheck" type="checkbox" value="反选" onclick="canCheckOne(this);turnSelect(this);" />
			反选
		</div>
		<p class="pagenum">
			<span style="display:none">第
			<span class="red">			
			<input type="text" class="text_nor" id="pageno" value="${pages.currentPage}"
				onkeypress="if(event.keyCode==13) {submitNoPage();return false;}" size='2'
				name='pageno'  style="width:20px"/></span> 页/ 共
			<span class="red" id="max_page"><bean:write name="pages" property="maxPage" /></span>页，</span> 每页显示
			<input type="text" value="${pages.pageSize}" onkeypress="if(event.keyCode==13) {submitNoPage();return false;}" size="2" style="width:20px" class="text_nor" id="pagesize" />
			条/共
			<span id="max_record" class="red"><bean:write name="pages" property="maxRecord" /></span>条记录
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

	var currPage = jQuery('#pageno').val();
	var totlePage = jQuery('#totlePage').val();

	if(currPage == '1'){
		jQuery('#first').attr("disabled",'disabled');
		jQuery('#pre').attr("disabled",'disabled');
	}

	if(eval(currPage) == eval(totlePage)){
		jQuery('#last').attr("disabled",'disabled');
		jQuery('#next').attr("disabled",'disabled');
	}
	
</script>
<script language="JavaScript">
function submitFirstPage() {
	jQuery("#fanye").val("first");
	if(checkHadEdit()){
		var page = $("currentPage").value;
		if (page == 1 || page == 0) {
			return;
		}
		jQuery("#currentPage").val("1");
		jQuery("#isPage").val("yes");
		
		if($("allCheck")){
			$("allCheck").checked= false;
		}
		
		if($("turnCheck")){
			$("turnCheck").checked= false;
		}
		
	    document.getElementById("search_go").click();
    }
}

function submitPrePage() {
	jQuery("#fanye").val("pre");
	if(checkHadEdit()){
		var page = parseInt(jQuery("#currentPage").val());
		
		if (page == 1 || page==0) {
			return;
		} 
		else {
			jQuery("#currentPage").val(page - 1);
		}
		jQuery("#isPage").val("yes");
		
		if($("allCheck")){
			$("allCheck").checked= false;
		}
		
		if($("turnCheck")){
			$("turnCheck").checked= false;
		}
		
		document.getElementById("search_go").click();
	}
}

function submitNextPage() {
	jQuery("#fanye").val("next");
	if(checkHadEdit()){
		var page = parseInt(jQuery("#currentPage").val());
		var mpage = parseInt(jQuery("#maxPage").val());
		
		if (page == mpage) {
			return;
		} 
		else {
			jQuery("#currentPage").val(page - (-1));
		}
		jQuery("#isPage").val("yes");
		
		if($("allCheck")){
			$("allCheck").checked= false;
		}
		
		if($("turnCheck")){
			$("turnCheck").checked= false;
		}
		
		document.getElementById("search_go").click();
	}
}


function submitLastPage() {
	jQuery("#fanye").val("last");
	if(checkHadEdit()){
		var page = parseInt(jQuery("#currentPage").val());
		var mpage = parseInt(jQuery("#maxPage").val());
		if (page == mpage) {
			return;
		} 
		else {
			jQuery("#currentPage").val(mpage);
		}
		jQuery("#isPage").val("yes");
		
		if($("allCheck")){
			$("allCheck").checked= false;
		}
		
		if($("turnCheck")){
			$("turnCheck").checked= false;
		}
		
		document.getElementById("search_go").click();
	}
}
function submitNoPage() {
	if(checkHadEdit()){
		var page = parseInt(jQuery("#pageno").val());
		var mpage = parseInt(jQuery("#maxPage").val());	
		var currentPage=parseInt(jQuery("#currentPage").val());
		var pagesize = jQuery("#pagesize").val();
		if (page > mpage) {
			return;
		} 
		//else if(page==currentPage){
			//return;
		//}
		else{
			jQuery("#currentPage").val(page);
		}
		if(pagesize != null && pagesize !=''){
			jQuery("#page_Size").val(pagesize);
		}
		
		jQuery("#isPage").val("yes");
		
		if($("editPageSize")){
			jQuery("#editPageSize").val("yes");
		}
	
		document.getElementById("search_go").click();
	}
}

function submitPage(page) {  
	if(checkHadEdit()){
		var mpage = parseInt(jQuery("#maxPage").val());
		var currentPage=parseInt(jQuery("#currentPage").val());
		if (page > mpage) {
			return;
		} else if(page==currentPage){
			return;
		} else{
			
			jQuery("#currentPage").val(page);
		}
		jQuery("#isPage").val("yes");
		document.getElementById("search_go").click();
	}
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

function canCheckOne(obj){
	if(obj.id=="allCheck" && obj.checked){
		$("turnCheck").checked=!obj.checked
	}else if(obj.id=="turnCheck" && obj.checked){
		$("allCheck").checked=!obj.checked
	}
}

</script>

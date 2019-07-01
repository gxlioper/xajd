<%@ page language="java" pageEncoding="GB2312"%>
<%@taglib prefix="template" uri="/WEB-INF/struts-template.tld"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>




<style>
a:active{
color:red;
}
</style>
<SCRIPT LANGUAGE="JavaScript">
function submitFirstPage() {
	var page = $("jxglpages.currentPage").value;
	if (page == 1 || page == 0) {
		return;
	}
	$("jxglpages.currentPage").value = "1";
    document.getElementById("search_go").click();
}

function submitPrePage() {
	var page = parseInt(document.all["jxglpages.currentPage"].value);
	
	if (page == 1 || page==0) {
		return;
	} 
	else {
		document.all["jxglpages.currentPage"].value = page - 1;
	}
	document.getElementById("search_go").click();
}

function submitNextPage() {
	var page = parseInt(document.all["jxglpages.currentPage"].value);
	var mpage = parseInt(document.all["jxglpages.maxPage"].value);
	
	if (page == mpage) {
		return;
	} 
	else {
		document.all["jxglpages.currentPage"].value = page - (-1);
	}
	document.getElementById("search_go").click();
}


function submitLastPage() {
	var page = parseInt(document.all["jxglpages.currentPage"].value);
	var mpage = parseInt(document.all["jxglpages.maxPage"].value);
	if (page == mpage) {
		return;
	} 
	else {
		document.all["jxglpages.currentPage"].value =mpage;
	}
	document.getElementById("search_go").click();
}
function submitNoPage() 
{
	var page = parseInt($("pageno").value);
	var mpage = parseInt($("jxglpages.maxPage").value);	
	var currentPage=parseInt($("jxglpages.currentPage").value);
	if (page > mpage) {
		return;
	} 
	else if(page==currentPage){
		return;
	}else{
		$("jxglpages.currentPage").value =page;
	}
	document.getElementById("search_go").click();
}

function submitPage(page) 
{  
	var mpage = parseInt($("jxglpages.maxPage").value);
	var currentPage=parseInt($("jxglpages.currentPage").value);
	if (page > mpage) {
		return;
	} 
	else if(page==currentPage){
		return;
	}else{
		$("jxglpages.currentPage").value =page;
	}
	document.getElementById("search_go").click();
}
</SCRIPT>
<%
String form = request.getParameter("form");
%>
<bean:define name="<%=form%>" property="jxglpages" id="jxglpages"></bean:define>
<input type="hidden" name="jxglpages.pageSize" value="${ jxglpages.pageSize } " />
<input type="hidden" name="jxglpages.currentPage" value="${ jxglpages.currentPage }" />
<input type="hidden" name="jxglpages.maxPage" value="${ jxglpages.maxPage }" />

<TABLE class=tableborder cellSpacing=1 cellPadding=2 align="right">
	<TBODY>
		<TR bgColor=#f8f8f8>
			<TD title="总记录" bgcolor="#D0E0EE">
				<bean:write name="jxglpages" property="maxRecord" />
			</TD>
			<TD bgcolor="#D0E0EE">
				<span title="当前页"><bean:write name="jxglpages"
						property="currentPage" /> </span>/
				<span title="总页数"><bean:write name="jxglpages" property="maxPage" />
				</span>
			</TD>
			<TD>
				<A href="javascript:submitFirstPage()"><img
						src="images/forum/1.gif" border="0" alt="首页"> </A>
			</TD>
			<TD>
				<A href="javascript:submitPrePage()"><img
						src="images/forum/3.gif" border="0" alt="上一页"> </A>
			</TD>

			<logic:iterate id="page1" name="jxglpages" property="pagelist">
				<TD>
					<A href="javascript:submitPage('<bean:write name="page1"/>')">&nbsp;<bean:write
							name="page1" />&nbsp;</A>
				</TD>
			</logic:iterate>

			<TD>
				<A href="javascript:submitNextPage()"><img
						src="images/forum/4.gif" border="0" alt="下一页"> </A>
			</TD>
			<TD>
				<A href="javascript:submitLastPage()"><img
						src="images/forum/2.gif" border="0" alt="尾页"> </A>
			</TD>
			<TD
				style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px">
				<INPUT onkeydown="if(event.keyCode==13) {submitNoPage()}"
					style="BORDER-RIGHT: #698cc3 1px solid; BORDER-TOP: #698cc3 1px solid; BORDER-LEFT: #698cc3 1px solid; BORDER-BOTTOM: #698cc3 1px solid;height:22px"
					size=2 name=pageno><button type="button" onclick="submitNoPage();">GO</button>
			</TD>
		</TR>
	</TBODY>
</TABLE>

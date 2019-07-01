<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>


<script language="javascript" src="js/function.js"></script>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<div class="topframe">
	<div class="toplink">
		<ul>
			<li>
				<a
					onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('#');"
					href="#">设为首页</a>
			</li>
			<li>
				<a href=javascript:window.external.AddFavorite('#','')>加入收藏</a>
			</li>
			<li>
				<a href="#" target="_blank">联系我们</a>
			</li>
		</ul>
	</div>
	<div class="toplogo">

	</div>
</div>
<div class="adpic"></div>
<div class="menuframe">
	<ul>
		<li>
			<a href="index.do">首页</a>
		</li>
		<li>
			<a href="#">最新动态</a>
		</li>
		<li>
			<a href="#">招聘信息</a>
		</li>
		<li>
			<a href="#">求职信息</a>
		</li>
		<li>
			<a href="#">图片信息</a>
		</li>
		<li>
			<a href="#">政策法规</a>
		</li>
		<li>
			<a href="#">公告栏</a>
		</li>
		<li>
			<a href="#">校园专场招聘会</a>
		</li>
		<li>
			<a href="#">生源介绍</a>
		</li>
		<li>
			<a href="#">就业指导</a>
		</li>
		<li>
			<a href="#">下载中心</a>
		</li>
		<li>
			<a href="#">留言板</a>
		</li>
	</ul>
</div>


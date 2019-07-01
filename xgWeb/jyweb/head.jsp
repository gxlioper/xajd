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
			String url = request.getRequestURL().toString();
			url = url.substring(0,url.indexOf("xgxt"))+"xgxt/index.do";
		%>
		<script language="javascript">
			function addAddress(){
    			var url_sito = '<%=url %>';
    			if ((navigator.appName == "Microsoft Internet Explorer") && (parseInt
        			(navigator.appVersion) >= 4)){
       				 window.external.AddFavorite(url_sito, '<bean:write name="xxmc"/>'+'就业网');
    			}else if (navigator.appName == "Netscape"){
       				 window.sidebar.addPanel(nome_sito, url_sito, '');
    			}else
       				 alert("对不起，不能填加到收藏夹！");
			}
			
		</script>
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
							onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('<%=url %>');"
							href="#">设为首页</a>
					</li>
					<li>
						<a onclick="addAddress()" href="#">加入收藏</a>
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
					<a href="viewallnewsinfo.do?method=allnewsinfo&jytype=jyweb">最新动态</a>
				</li>
				<li>
					<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb">招聘信息</a>
				</li>
				<li>
					<a href="viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb">求职信息</a>
				</li>
				<li>
					<a href="viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb">图片信息</a>
				</li>
				<li>
					<a href="viewallzcfginfo.do?method=allzcfginfo&jytype=jyweb">政策法规</a>
				</li>
				<li>
					<a href="viewallgglinfo.do?method=allgglinfo&jytype=jyweb">公告栏</a>
				</li>
				<li>
					<a href="viewallzczpinfo.do?method=allzczpinfo&jytype=jyweb">校园专场招聘会</a>
				</li>
				<li>
					<a href="viewallsyjsinfo.do?method=allsyjsinfo&jytype=jyweb">生源介绍</a>
				</li>
<%--				<li>--%>
<%--					<a href="viewalljyzdinfo.do?method=alljyzdinfo&jytype=jyweb">就业指导</a>--%>
<%--				</li>--%>
				<li>
					<a href="viewallwjxzinfo.do?method=allwjxzsinfo&jytype=jyweb">下载中心</a>
				</li>
				<li>
					<a href="lybinfo.do?method=lybinfo&jytype=jyweb">留言板</a>
				</li>
			</ul>
		</div>
		
	

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
       				 window.external.AddFavorite(url_sito, '<bean:write name="xxmc"/>'+'��ҵ��');
    			}else if (navigator.appName == "Netscape"){
       				 window.sidebar.addPanel(nome_sito, url_sito, '');
    			}else
       				 alert("�Բ��𣬲�����ӵ��ղؼУ�");
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
							href="#">��Ϊ��ҳ</a>
					</li>
					<li>
						<a onclick="addAddress()" href="#">�����ղ�</a>
					</li>
					<li>
						<a href="#" target="_blank">��ϵ����</a>
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
					<a href="index.do">��ҳ</a>
				</li>
				<li>
					<a href="viewallnewsinfo.do?method=allnewsinfo&jytype=jyweb">���¶�̬</a>
				</li>
				<li>
					<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb">��Ƹ��Ϣ</a>
				</li>
				<li>
					<a href="viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb">��ְ��Ϣ</a>
				</li>
				<li>
					<a href="viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb">ͼƬ��Ϣ</a>
				</li>
				<li>
					<a href="viewallzcfginfo.do?method=allzcfginfo&jytype=jyweb">���߷���</a>
				</li>
				<li>
					<a href="viewallgglinfo.do?method=allgglinfo&jytype=jyweb">������</a>
				</li>
				<li>
					<a href="viewallzczpinfo.do?method=allzczpinfo&jytype=jyweb">У԰ר����Ƹ��</a>
				</li>
				<li>
					<a href="viewallsyjsinfo.do?method=allsyjsinfo&jytype=jyweb">��Դ����</a>
				</li>
<%--				<li>--%>
<%--					<a href="viewalljyzdinfo.do?method=alljyzdinfo&jytype=jyweb">��ҵָ��</a>--%>
<%--				</li>--%>
				<li>
					<a href="viewallwjxzinfo.do?method=allwjxzsinfo&jytype=jyweb">��������</a>
				</li>
				<li>
					<a href="lybinfo.do?method=lybinfo&jytype=jyweb">���԰�</a>
				</li>
			</ul>
		</div>
		
	

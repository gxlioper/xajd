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
					href="#">��Ϊ��ҳ</a>
			</li>
			<li>
				<a href=javascript:window.external.AddFavorite('#','')>�����ղ�</a>
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
			<a href="#">���¶�̬</a>
		</li>
		<li>
			<a href="#">��Ƹ��Ϣ</a>
		</li>
		<li>
			<a href="#">��ְ��Ϣ</a>
		</li>
		<li>
			<a href="#">ͼƬ��Ϣ</a>
		</li>
		<li>
			<a href="#">���߷���</a>
		</li>
		<li>
			<a href="#">������</a>
		</li>
		<li>
			<a href="#">У԰ר����Ƹ��</a>
		</li>
		<li>
			<a href="#">��Դ����</a>
		</li>
		<li>
			<a href="#">��ҵָ��</a>
		</li>
		<li>
			<a href="#">��������</a>
		</li>
		<li>
			<a href="#">���԰�</a>
		</li>
	</ul>
</div>


<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsh/js/xmsh.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
	</head>
	<body onunload="setJxshCookie();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/xpjpy_xmsh" method="post" styleId="xmshForm">
		<input type="hidden" id="shzt" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal value="true" name="cssz" property="kgzt">
								<li id="li_sh">
									<a href="javascript:void(0);" onclick="xmshPlsh();return false;" class="btn_sh">�������</a>
								</li>
								<li id="li_qx" style="display: none;">
									<a href="javascript:void(0);" onclick="xmshRevoke();return false;" class="btn_qxsh">����</a>
								</li>	
							</logic:equal>
								<li>
									<a href="javascript:void(0);" onclick="xmshTrack();return false;" class="btn_cs">���̸���</a>
								</li>
<%--								<li>--%>
<%--									<a href="javascript:void(0);" onclick="xmshStatistics();return false;" class="btn_tj">�������ͳ��</a>--%>
<%--								</li>--%>
						</logic:equal>
						
							<li>
								<a href="#" class="btn_dc" onclick="xmshExport();return false;">����</a>
							</li>
						<li>
							<a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">���صǼǱ�</a>
						</li>
					</ul>
				</div>
			</div>
		
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%" id="tabUl">
			<li class="ha" clzt="dcl"><a href="javascript:void(0);"  onclick="chageShzt(this,'0');"><span>������</span></a></li>
			<li clzt="ycl"><a href="javascript:void(0);"  onclick="chageShzt(this,'1');"><span>�Ѵ���</span></a></li>
	      </ul>
	    </div>
	    
	    <!-- �������� -->	
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- �������� end--> 
		
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span color="blue">�����������б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
			<table id="dataTable"></table>
			<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
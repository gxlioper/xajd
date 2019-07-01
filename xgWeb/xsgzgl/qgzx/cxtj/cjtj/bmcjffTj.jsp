<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qgzx/jcdmwh/jcdmwh.js"></script>
		<script>
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		function searchRs(){
			if($("nd") && $("nd").value.trim()==""){
				alertInfo("��Ȳ���Ϊ�գ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			var url = "qgzx_cxtj_ajax.do?method=bmcjffTj";
			url+="&nd="+jQuery("#nd").val();
			url+="&yf="+jQuery("#yf").val();
			url+="&bmdm="+jQuery("#bmdm").val();
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//����
		function Dc(){
			var url = "qgzx_cxtj_ajax.do?method=bmcjffDc";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
	</head>
	<body onload="searchRs();">
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_cxtj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
						<li><a href="#" onclick="Dc();return false;" class="btn_dc">����</a></li>
						
					</ul>
				</div>
				<div style="display: none;">
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
			<div class="comp_title">
				<ul>
		       	 	<li class="ha"><a href="#"><span>���ų�𷢷�ͳ��</span></a></li>
		      	 	<li><a href="javascript:refreshForm('qgzx_cxtj.do?method=gwcjffTj');"><span>��λ��𷢷�ͳ��</span></a></li>
		      	 	<li><a href="javascript:refreshForm('qgzx_cxtj.do?method=grcjffTj');"><span>���˳�𷢷�ͳ��</span></a></li>
		     	</ul>
	     	</div>
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>���</th>
							<td>
								<html:select name="rs" property="nd" style="width:180px" styleId="nd">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>�·�</th>
							<td>
								<html:select name="rs" property="yf" style="width:180px" styleId="yf">
									<html:option value="">ȫ��</html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
							</td>
							<th>���˲���</th>
							<td>								
								<logic:equal name="rs" property="dis" value="true">
									<input type="hidden" id="bmdm" name="bmdm" value="${rs.bmdm}"/>
									<html:select name="rs" property="bmdm" style="width:180px" styleId="bmdm" disabled="${rs.dis}">
										<html:option value="">ȫ��</html:option>
										<html:option value="xj">����У��</html:option>
										<html:option value="yj">����Ժ��</html:option>
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
									</html:select>
								</logic:equal>
								<logic:notEqual name="rs" property="dis" value="true">
									<html:select name="rs" property="bmdm" style="width:180px" styleId="bmdm" disabled="${rs.dis}">
										<html:option value="">ȫ��</html:option>
										<html:option value="xj">����У��</html:option>
										<html:option value="yj">����Ժ��</html:option>
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
										��ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="reset()">
										����
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ͳ�ƽ��&nbsp;&nbsp;</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

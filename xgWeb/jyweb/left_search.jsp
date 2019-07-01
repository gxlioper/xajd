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
		<script type="text/javascript">
		 function findarticle(){
		    document.forms[0].action = "findarticle.do?method=findarticle&doType=find&jytype=jyweb";
		    document.forms[0].submit();
		}
        </script>
	
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
		<div class="wzss">
						<h3>文章搜索</h3>
						<table width="95%" align="center">
						   <tr>
						     <td>关键字：</td>
						     <td><input type="text" name="gjz" style="width:100%;" class="text_normal" onkeypress="if(window.event.keyCode==13){ findarticle(); } " /></td>
                           </tr>
						   <tr>
						     <td>&nbsp;</td>
						     <td><select name="find" >
							    <option value="zxdt">最新动态 
							    <option value="tpxx">图片信息 
							    <option value="zcfg">政策法规 
							    <option value="ggl">公告栏
							    <option value="zph">招聘会 
							    <option value="syjs">生源介绍 
							    <option value="xzzx">下载中心 
							  </select></td>
							</tr>
							<tr>
						     <td>&nbsp;</td>
						     <td><button onClick="findarticle();" class="btn_search">
									
								</button></td>
						   </tr>
						</table>
						 <logic:present name="name">
        					<script type="text/javascript">
		 						document.getElementsByName('find')[0].value = '${name}';
        					</script>
       					 </logic:present>
					</div>
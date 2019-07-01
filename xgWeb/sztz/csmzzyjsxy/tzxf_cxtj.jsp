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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<base target="_self">
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="cxInfo(1);">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>		
    <html:form action="/csmz_sztz" method="post">
    <input type="hidden" name="zyV" id="zyV" />
	<input type="hidden" name="bjV" id="bjV" />
	<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm" scope="request"/>"/>
	<input type="hidden" name="cz" id="cz" value="<bean:write name="cxcz" scope="request"/>"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${tips }</a>
			</p>
		</div>
		<div class="toolbox">
		 <!-- 按钮 -->
		 <div class="buttonbox">
		    <ul>
		    	 <input type="hidden" name="print" value="a" />
				<li> <a href="#" onclick="document.forms[0].print.value='do';resultTo();" class="btn_tj"> 报表 </a> </li>
		    </ul>
		 </div>
		<div class="searchtab">
		<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="8">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
		              <button class="btn_cx" id="search_go" 
		              	onclick="resultTo()">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>					
								<tr>
									 <td clospan="8">	
									  统计类型								
										&nbsp;&nbsp;
										<html:radio property="cxcz"  styleId="cxcz" value="fsd" onclick="cxInfo(0)" >按分数段查询</html:radio>
										&nbsp;&nbsp;
										<html:radio property="cxcz" styleId="cxcz" value="yxpj" onclick="cxInfo(0)">(院)系平均分统计</html:radio>
										&nbsp;&nbsp;
										<html:radio property="cxcz" styleId="cxcz" value="bjpj" onclick="cxInfo(0)">班级平均分统计</html:radio>
										&nbsp;&nbsp;
										<html:radio property="cxcz" styleId="cxcz" value="bjcjtj" onclick="cxInfo(0)">班级学分统计</html:radio>	
										<logic:present name="showcsmz"><!-- 长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
										&nbsp;&nbsp;&nbsp;&nbsp;										
										<html:radio property="cxcz" styleId="cxcz" value="bytj" onclick="cxInfo(0)">毕业条件</html:radio>									    
									    </logic:present>

									</td>							
								</tr>
								<tr id="xntj">			
									<td  clospan="8">					    
									<span id="yxpj" style="display:none">按(院)系平均分</span>
								    <span id="bjpj" style="display:none">按班级平均分</span>
									学年
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										<logic:notPresent name="showcsmz"><!-- 长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
										&nbsp;&nbsp;
										学期
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										</logic:notPresent>		
									</td>					
								</tr>								
								<tr id="bjcjtj" >	
									<td clospan="8"> 
									   年级
									 	<html:select property="nj" 
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									   <bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm"  styleId="xy" style="width:180px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:select property="zydm"  styleId="zy" style="width:180px"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>									
										班级
										<html:select property="bjdm"  styleId="bj" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>										
									</td>																										
								</tr>															
								<tr id="fsd" style="display:none">
									<td colspan="8">
									分数段查询类型
									&nbsp;&nbsp;
									<html:radio property="fsdcx" value="dy">&nbsp;大于								
									<html:text property="dyfvalue" onkeypress="return NumInputV(this,5,0)" onblur="ckinpdata(this)" style="width:50px;cursor:hand;color:#999999" />分
									</html:radio>
									&nbsp;&nbsp;
									<html:radio property="fsdcx" value="xy" >&nbsp;小于							
									<html:text property="xyvalue" onkeypress="return NumInputV(this,5,0)"  onblur="ckinpdata(this)" style="width:50px;cursor:hand;color:#999999" />分
									</html:radio>
									&nbsp;&nbsp;
									<html:radio property="fsdcx" value="fsdvalue">&nbsp;从								
									<html:text property="fsdvalues" onkeypress="return NumInputV(this,5,0)" onblur="ckinpdata(this)" style="width:50px;cursor:hand;color:#999999" />&nbsp;分到<html:text property="fsdvaluee" onkeypress="return NumInputV(this,5,0)" onblur="ckinpdata(this)" style="width:50px;cursor:hand;color:#999999" />	分
									</html:radio>											
									&nbsp;&nbsp;
									<html:radio property="fsdcx" value="zgs">&nbsp;最高分前							
									<html:text  property="zgsvalue" onkeypress="return NumInputV(this,5,1)" onblur="ckinpdata(this)" style="width:50px;cursor:hand;color:#999999" />名
									</html:radio>	
									</td>
								</tr>
							<tr id="bytj" style="display:none">
								<td colspan="8" >
									年段
									<html:select property="nd" style="width:100px">
										<html:option value="dyn">第一年内</html:option>
										<html:option value="den">第二年内</html:option>
										<html:option value="ln">两年内</html:option>
									</html:select>
									&nbsp;&nbsp; 是否达到条件:
									<html:select property="yesNo" style="width:100px">
										<html:option value="yes">是</html:option>
										<html:option value="no">否</html:option>
									</html:select>																	
								</td>
							</tr>
						</tbody>
					</table>
				   </div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">					
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>		
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;">
											<td align="center" >												
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>					
						
				</logic:notEmpty>	
				</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
<script type="text/javascript">
function cxInfo(type){
var xxdm = $("xxdm").value;
var cxcz  = $("cz").value;
if(type==1){ 
    if(cxcz==""){
         $('yxpj').style.display="none";
         $('bjpj').style.display="none";
         $('bjcjtj').style.display="none";
         $('bytj').style.display="none";
         document.forms[0].cxcz[0].checked=true;
         $('fsd').style.display=""; 
		<%--  $('search_go').style.height="40px";--%>
   }else{ 
     if(cxcz=="fsd"){
        document.forms[0].cxcz[0].checked;
         $('yxpj').style.display="none";
         $('bjpj').style.display="none";
         $('bjcjtj').style.display="none";
         $('bytj').style.display="none";
         $('xntj').style.display="";      
         $('fsd').style.display="";
     }else if(cxcz=="yxpj"){
        document.forms[0].cxcz[1].checked;
         $('fsd').style.display="none";
         $('bjpj').style.display="none";
         $('bjcjtj').style.display="none";
         $('bytj').style.display="none";
         $('xntj').style.display="";        
         $('yxpj').style.display="";
     }else if(cxcz=="bjpj"){
        document.forms[0].cxcz[2].checked;
         $('yxpj').style.display="none";
         $('fsd').style.display="none";
         $('bjcjtj').style.display="";
         $('bytj').style.display="none"; 
         $('xntj').style.display="";       
        $('bjpj').style.display="";
     }else if(cxcz=="bjcjtj"){
        document.forms[0].cxcz[3].checked
         $('yxpj').style.display="none";
         $('bjpj').style.display="none";
         $('fsd').style.display="none";
         $('bytj').style.display="none";
         $('xntj').style.display="";       
        $('bjcjtj').style.display="";
   <%--     $('search_go').style.height="40px";--%>
     }else if(cxcz=="bytj"){
         $('yxpj').style.display="none";
         $('bjpj').style.display="none";
         $('bjcjtj').style.display="none";
         $('fsd').style.display="none";
         $('xntj').style.display="none";
         $('bjcjtj').style.display="";     
        document.forms[0].cxcz[4].checked
        $('bytj').style.display="";
   <%--      $('search_go').style.height="40px"; --%>
     }
  }
}else{
   if(document.forms[0].cxcz[0].checked){
     $('yxpj').style.display="none";
     $('bjpj').style.display="none";
     $('bjcjtj').style.display="none";
     $('bytj').style.display="none";
     $('xntj').style.display="";   
     if($('fsd').style.display=="none"){
       $('fsd').style.display="";
     }
   }
   if(document.forms[0].cxcz[1].checked){
     $('fsd').style.display="none";
     $('bjpj').style.display="none";
     $('bjcjtj').style.display="none";
     $('bytj').style.display="none";
     $('xntj').style.display="";  
     if($('yxpj').style.display=="none"){
        $('yxpj').style.display="";
     }
   }
   if(document.forms[0].cxcz[2].checked){
    $('fsd').style.display="none";
    $('yxpj').style.display="none";
    $('bjcjtj').style.display="none";
    $('bytj').style.display="none";
    $('xntj').style.display="";  
    if($('bjpj').style.display=="none"){
        $('bjpj').style.display="";
    }
   }
   if(document.forms[0].cxcz[3].checked){
    $('fsd').style.display="none";
    $('yxpj').style.display="none";
    $('bjpj').style.display="none";
    $('bytj').style.display="none";
    $('xntj').style.display="";  
  <%--  $('search_go').style.height="75px";--%>
    if($('bjcjtj').style.display=="none"){
        $('bjcjtj').style.display="";
     <%--    $('search_go').style.height="40px";--%>
    }
   }else{
 <%--    $('search_go').style.height="40px";--%>
   }
     if(xxdm=="10827"){//长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" />
       if(document.forms[0].cxcz[4].checked){
          $('fsd').style.display="none";
          $('yxpj').style.display="none";
          $('bjpj').style.display="none";         
          $('xntj').style.display="none";          
       if($('bytj').style.display=="none"){
          $('bytj').style.display="";
         $('bjcjtj').style.display="";
  <%--       $('search_go').style.height="90px";--%>
       }   
  <%--        $('search_go').style.height="75px";--%>
     }
   }
 }
}

function resultTo(){

<%--     if(document.forms[0].xn.value==""){--%>
<%--       alert("学年不能为空！");--%>
<%--       return false;--%>
<%--     }--%>

<%--  if(document.forms[0].xq.value==""){--%>
<%--     alert("学期不能为空！");--%>
<%--     return false;--%>
<%--  } --%>
<%--  if(!document.forms[0].cxcz[0].checked--%>
<%--     &&!document.forms[0].cxcz[1].checked--%>
<%--     &&!document.forms[0].cxcz[2].checked--%>
<%--     &&!document.forms[0].cxcz[3].checked){--%>
<%--       alert("请选择统计类型！");--%>
<%--       return false;--%>
<%--  }--%>

  if(document.forms[0].cxcz[0].checked){   
        if(!document.forms[0].fsdcx[0].checked
           &&!document.forms[0].fsdcx[1].checked
           &&!document.forms[0].fsdcx[2].checked
           &&!document.forms[0].fsdcx[3].checked){
           alert("请选择分数段查询类型！");
           return false;      
     }
  }
  if(document.forms[0].cxcz[3].checked){
       if($('bjdm').value==""){
       alert("请选择班级！");
       return false;
       }
  }
  document.forms[0].go.value="go";
  refreshForm('/xgxt/csmz_sztz.do?method=tzxf_cxtj');
}

function NumInputV(obj, maxLen,type) {
	var str = '.';
	var numLen = obj.value.length;
	var num = 0;
	if(type==0){
	   for(i = 0;i <numLen;i++){	   
	     if(obj.value.indexOf(str)!=-1){
	  	 num+=num+1;
	     }
	   }
	   if (((event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 46) && obj.value.length < maxLen ) {				
		 if((num > 0 || numLen == 0) && event.keyCode == 46 ){
		   return false;	
	 	 }else{
		   return true;
		 }			
	   } else {
		return false;
	  }
   }else{
         if ((event.keyCode >= 48 && event.keyCode <= 57) && obj.value.length < maxLen ) {	
            return true;
         }else{
            return false;
         }  
   }
}
</script>

</html>	
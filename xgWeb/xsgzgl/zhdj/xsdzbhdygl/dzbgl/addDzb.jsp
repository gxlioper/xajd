<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<%@page import="sun.org.mozilla.javascript.internal.Undefined"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/dzbgl.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">   
    
	   function hiddenXX(){
	       var objS = document.getElementById("dzblx"); //通过id获得这个元素
	       var value = objS.options[objS.selectedIndex].value; //获得option中的值

	       
	       var jgdzb = document.getElementsByName("jgdzb");
	       var xsdzb = document.getElementsByName("xsdzb");

	       if(value == "教工党支部"){
		       for(var i= 0;i<jgdzb.length;i++){
		    	   jgdzb[i].style.display = "";
		       };
		       for(var i= 0;i<xsdzb.length;i++){
		    	   xsdzb[i].style.display = "none";
		       };
		   };
	       if(value == "学生党支部"){
		       for(var i= 0;i<xsdzb.length;i++){
		    	   xsdzb[i].style.display = "";
		       };
		       for(var i= 0;i<jgdzb.length;i++){
		    	   jgdzb[i].style.display = "none";
		       };
	       };
	             
	   };


        
    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_dzbgl" method="post" styleId="DzbglForm">
	<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>增加党支部</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            	<th><font color="red">*</font>党支部代码</th>
            	<td colspan="3">
                    <html:text property="dzbid" styleId="dzbid" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>名称</th>
                <td colspan="3">
                    <html:text property="dzbmc" styleId="dzbmc" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>所属基层党委</th>
                <td colspan="3">
                        <%--<html:select property="jcdwdm" styleId="jcdwdm" style="width:90%">--%>
                        <%--<html:options collection="jcdwList" property="dm" labelProperty="mc"/>--%>
                        <%--</html:select>--%>
                    <select id="jcdwdm" name="jcdwdm" style="width:155px"></select>
                </td>
            </tr>
            <tr>
                <th>所属书院</th>
                <td colspan="3">
                    <span id="symc"></span>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>成立时间</th>
                <td colspan="3">
                    <html:text property="clsj" value="${minDate}" styleId="clsj"
                               onclick="return showCalendar('clsj','y-mm-dd');" maxlength="10"></html:text>
                </td>
            </tr>
            <tr>
                <th>换届时间</th>
                <td colspan="3">
                    <html:text property="hjsj" value="" styleId="hjsj"
                               onclick="return showCalendar('hjsj','y-mm-dd');" maxlength="10"></html:text>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>党支部类型</th>
                <td colspan="3">
					<html:select property="dzblx" style="width:152px" styleId="dzblx" onchange="hiddenXX()">
					  <option value="">---请选择---</option>
	                  <option value="教工党支部">教工党支部</option>
	                  <option value="学生党支部">学生党支部</option>
                    </html:select>
				</td>
            </tr>
             
	        <logic:iterate id="item" name="jgdzbList" indexId="number">
				<tr class="list"  id ="jgdzb" name = "jgdzb" style="display: none">
					<th><font color="red">*</font><bean:write name="item" property="jgzwmc" />
					<input type="hidden" value="${item.jgzwmc}" name="jgzwmcs" id="jgzwmc${number}"/>
					<input type="hidden" value="${item.jgzwdm}" name="jgzwdms" id="jgzwdm${number}"/>
					</th>
					<td><input type="text"  name="jzgmcs" id="jzgmc${number}" value=""
						style="width:120px;" readonly="readonly" class="text_nor">           
	                    <button class="btn_01" type="button"
	                            onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1&aaa=${number}');">选择
	                    </button>
	                    <input  name="lxrzghs" value="" id="lxrzgh${number}"  type="hidden" >
					<th width="20%"><font color="red">*</font>联系方式</th>
	                <td>
	                	<input name="jzgdhs" value="" id="jzgdh${number}" >
	                </td>
                </tr>
		    </logic:iterate>
		   

		    <logic:iterate id="item" name="xsdzbList" indexId="index">
				<tr class="list"  id ="xsdzb" name ="xsdzb" style="display: none">
					<th><font color="red">*</font><bean:write name="item" property="zwmc" />
					<input type="hidden" value="${item.zwmc}" name="zwmcs" id="zwmc${index}"/>
					<input type="hidden" value="${item.zwdm}" name="zwdms" id="zwdm${index}"/>
					</th>
					<td><input type="text"  name="xsmcs" id="xsmc${index}" value=""
						style="width:120px;" readonly="readonly" class="text_nor"> 
	                    <button class="btn_01" type="button"
	                            onclick="showDialog('请选择一个学生',800,500,'dzdy_dzbgl.do?method=getXx&bbb=${index}');">选择
	                    </button>
	                    <input  name="xhs" value="" id="xh${index}"  type="hidden" >
					<th width="20%"><font color="red">*</font>联系方式</th>
	                <td>
	                    <input  name="xsdhs" value="" id="xsdh${index}" >
	                </td>
	  		    </tr>
		    </logic:iterate>

            <%--
			<tr>
                <th><font color="red">*</font>党支部书记</th>
                <td>
                    <input type="text" name="sjxm" value="" id="sjxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="dzbsj" styleId="dzbsj" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1');">选择
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>联系方式</th>
                <td><html:text property="sjlxdh" styleId="sjlxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/>
                </td>
            </tr>
            <tr>
                <th width="20%"><font color="red">*</font>组织委员</th>
                <td><input type="text" name="zzwyxm" value="" id="zzwyxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="zzwy" styleId="zzwy" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=2');">选择
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>联系方式</th>
                <td><html:text property="zzwylxdh" styleId="zzwylxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/></td>
            </tr>

            <tr>
                <th width="20%"><font color="red">*</font>宣传委员</th>
                <td><input type="text" name="xcwyxm" value="" id="xcwyxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="xcwy" styleId="xcwy" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=3');">选择
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>联系方式</th>
                <td><html:text property="xcwylxdh" styleId="xcwylxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/></td>
            </tr>
            <tr>
                <th width="20%"><font color="red">*</font>纪检委员</th>
                <td><input type="text" name="jjwyxm" value="" id="jjwyxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="jjwy" styleId="jjwy" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('请选择一个职工',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=4');">选择
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>联系方式</th>
                <td><html:text property="jjwylxdh" styleId="jjwylxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/></td>
            </tr>
            --%>
            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <%--;height:520px --%>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <input type="hidden" value ="${jgcount}" name = "jgcount" id = "jgcount"/>
        <input type="hidden" value ="${xscount}" name = "xscount" id = "xscount"/>
        
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveDzb(dzblx,jgcount,xscount);">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>

</html>
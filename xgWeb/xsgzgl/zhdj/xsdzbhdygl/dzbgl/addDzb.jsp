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
	       var objS = document.getElementById("dzblx"); //ͨ��id������Ԫ��
	       var value = objS.options[objS.selectedIndex].value; //���option�е�ֵ

	       
	       var jgdzb = document.getElementsByName("jgdzb");
	       var xsdzb = document.getElementsByName("xsdzb");

	       if(value == "�̹���֧��"){
		       for(var i= 0;i<jgdzb.length;i++){
		    	   jgdzb[i].style.display = "";
		       };
		       for(var i= 0;i<xsdzb.length;i++){
		    	   xsdzb[i].style.display = "none";
		       };
		   };
	       if(value == "ѧ����֧��"){
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
                    <span>���ӵ�֧��</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            	<th><font color="red">*</font>��֧������</th>
            	<td colspan="3">
                    <html:text property="dzbid" styleId="dzbid" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>����</th>
                <td colspan="3">
                    <html:text property="dzbmc" styleId="dzbmc" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>�������㵳ί</th>
                <td colspan="3">
                        <%--<html:select property="jcdwdm" styleId="jcdwdm" style="width:90%">--%>
                        <%--<html:options collection="jcdwList" property="dm" labelProperty="mc"/>--%>
                        <%--</html:select>--%>
                    <select id="jcdwdm" name="jcdwdm" style="width:155px"></select>
                </td>
            </tr>
            <tr>
                <th>������Ժ</th>
                <td colspan="3">
                    <span id="symc"></span>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>����ʱ��</th>
                <td colspan="3">
                    <html:text property="clsj" value="${minDate}" styleId="clsj"
                               onclick="return showCalendar('clsj','y-mm-dd');" maxlength="10"></html:text>
                </td>
            </tr>
            <tr>
                <th>����ʱ��</th>
                <td colspan="3">
                    <html:text property="hjsj" value="" styleId="hjsj"
                               onclick="return showCalendar('hjsj','y-mm-dd');" maxlength="10"></html:text>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��֧������</th>
                <td colspan="3">
					<html:select property="dzblx" style="width:152px" styleId="dzblx" onchange="hiddenXX()">
					  <option value="">---��ѡ��---</option>
	                  <option value="�̹���֧��">�̹���֧��</option>
	                  <option value="ѧ����֧��">ѧ����֧��</option>
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
	                            onclick="showDialog('��ѡ��һ��ְ��',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1&aaa=${number}');">ѡ��
	                    </button>
	                    <input  name="lxrzghs" value="" id="lxrzgh${number}"  type="hidden" >
					<th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
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
	                            onclick="showDialog('��ѡ��һ��ѧ��',800,500,'dzdy_dzbgl.do?method=getXx&bbb=${index}');">ѡ��
	                    </button>
	                    <input  name="xhs" value="" id="xh${index}"  type="hidden" >
					<th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
	                <td>
	                    <input  name="xsdhs" value="" id="xsdh${index}" >
	                </td>
	  		    </tr>
		    </logic:iterate>

            <%--
			<tr>
                <th><font color="red">*</font>��֧�����</th>
                <td>
                    <input type="text" name="sjxm" value="" id="sjxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="dzbsj" styleId="dzbsj" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('��ѡ��һ��ְ��',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1');">ѡ��
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
                <td><html:text property="sjlxdh" styleId="sjlxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/>
                </td>
            </tr>
            <tr>
                <th width="20%"><font color="red">*</font>��֯ίԱ</th>
                <td><input type="text" name="zzwyxm" value="" id="zzwyxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="zzwy" styleId="zzwy" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('��ѡ��һ��ְ��',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=2');">ѡ��
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
                <td><html:text property="zzwylxdh" styleId="zzwylxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/></td>
            </tr>

            <tr>
                <th width="20%"><font color="red">*</font>����ίԱ</th>
                <td><input type="text" name="xcwyxm" value="" id="xcwyxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="xcwy" styleId="xcwy" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('��ѡ��һ��ְ��',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=3');">ѡ��
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
                <td><html:text property="xcwylxdh" styleId="xcwylxdh" onkeyup="checkInput(this)" maxlength="11"
                               style="width:90%"/></td>
            </tr>
            <tr>
                <th width="20%"><font color="red">*</font>�ͼ�ίԱ</th>
                <td><input type="text" name="jjwyxm" value="" id="jjwyxm" style="width:120px;" readonly="readonly"
                           class="text_nor">
                        <input  name="xh" value="" id="xh"  type="hidden" >
                    <html:hidden property="jjwy" styleId="jjwy" value=""/>
                    <button class="btn_01" type="button"
                            onclick="showDialog('��ѡ��һ��ְ��',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=4');">ѡ��
                    </button>
                </td>
                <th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
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
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveDzb(dzblx,jgcount,xscount);">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();">
                            �ر�
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
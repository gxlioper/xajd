<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
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
		       };	       };
	       if(value == "ѧ����֧��"){
		       for(var i= 0;i<xsdzb.length;i++){
		    	   xsdzb[i].style.display = "";
		       };
		       for(var i= 0;i<jgdzb.length;i++){
		    	   jgdzb[i].style.display = "none";
		       };
	       };
	             
	   };

	   jQuery(function ($) {
        var dzblx = $('#seldzblx').val();
        //��֧������
        jQuery("#dzblx option").each(function () {
            if (dzblx == ($(this).val())) {
                $(this).attr('selected', true);
            }
        });
        var jgdzb = document.getElementsByName("jgdzb");
		   var xsdzb = document.getElementsByName("xsdzb");
	       
	       if(dzblx == "�̹���֧��"){
		       for(var i= 0;i<jgdzb.length;i++){
		    	   jgdzb[i].style.display = "";
		       };
		       for(var i= 0;i<xsdzb.length;i++){
		    	   xsdzb[i].style.display = "none";
		       };	       };
	       if(dzblx == "ѧ����֧��"){
		       for(var i= 0;i<xsdzb.length;i++){
		    	   xsdzb[i].style.display = "";
		       };
		       for(var i= 0;i<jgdzb.length;i++){
		    	   jgdzb[i].style.display = "none";
		       };
	       };
    });

    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_dzbgl" method="post" styleId="DzbglForm">
    <input type="hidden" value="${dzbid}" name="dzbid" id="dzbid"/>
    <input type="hidden" value="${dzbhjid}" name="dzbhjid" id="dzbhjid"/>
    <input type="hidden" value="${dzbmc}" name="dzbmc" id="dzbmc"/>
    <input type="hidden" value="${jcdwdm}" name="jcdwdm" id="jcdwdm"/>
    <input type="hidden" value="${clsj}" name="clsj" id="clsj"/>
    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�޸ĵ�֧����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>��֧������</th>
                <td colspan="3">
                        ${dzbid}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>����</th>
                <td colspan="3">
                        ${dzbmc}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>�������㵳ί</th>
                <td colspan="3">
                        <%--<html:select property="jcdwdm" styleId="jcdwdm" style="width:90%">--%>
                        <%--<html:options collection="jcdwList" property="dm" labelProperty="mc"/>--%>
                        <%--</html:select>--%>
                        ${dzbdm}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>������Ժ</th>
                <td colspan="3">
                        ${symc}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>����ʱ��</th>
                <td colspan="3">
                        ${clsj}
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>����ʱ��</th>
                <td colspan="3">
                    <input type="hidden" value="${hjsj}" name="hjsjOld" id="hjsjOld"/>
                    <html:text property="hjsj" styleId="hjsj"
                               onclick="return showCalendar('hjsj','y-mm-dd',false,'hjsj');" maxlength="10"></html:text>
                </td>
            </tr>
           <tr>
                <th>��֧������</th>
                <td colspan="3">
                                	${dzblx}
					<input type="hidden" id="seldzblx" value="${dzblx}" name = "dzblx"/>       	              
				</td>
            </tr>
             
           <logic:iterate id="item" name="jgdzbList" indexId="number">
				<tr class="list"  id ="jgdzb" name = "jgdzb" style="display: none">
					<th><font color="red">*</font><bean:write name="item" property="jgzwmc" />
					<input type="hidden" value="${item.jgzwmc}" name="jgzwmcs" id="jgzwmc${number}"/>
					<input type="hidden" value="${item.jgzwdm}" name="jgzwdms" id="jgzwdm${number}"/>
					</th>
					<td><input type="text"  name="jzgmcs" id="jzgmc${number}" value="${item.lxrmc}"
						style="width:120px;" readonly="readonly" class="text_nor">           
	                    <button class="btn_01" type="button"
	                            onclick="showDialog('��ѡ��һ��ְ��',800,500,'dzdy_dzbgl.do?method=getZg&zwlx=1&aaa=${number}');">ѡ��
	                    </button>
	                   <input  name="lxrzghs" value="${item.lxrid}" id="lxrzgh${number}"  type="hidden" >
					<th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
	                <td>
	                    <input  name="jzgdhs" value ="${item.lxrdh}" id="jzgdh${number}" >
	                </td>
                </tr>
		    </logic:iterate>

		    <logic:iterate id="item" name="xsdzbList" indexId="index">
				<tr class="list"  id ="xsdzb" name ="xsdzb" style="display: none">
					<th><font color="red">*</font><bean:write name="item" property="zwmc" />

					<input type="hidden" value="${item.zwmc}" name="zwmcs" id="zwmc${index}"/>
					<input type="hidden" value="${item.zwdm}" name="zwdms" id="zwdm${index}"/>
					</th>
					<td><input type="text"  name="xsmcs" id="xsmc${index}" value="${item.lxrmc}"
						style="width:120px;" readonly="readonly" class="text_nor"> 
	                    <button class="btn_01" type="button"
	                            onclick="showDialog('��ѡ��һ��ѧ��',800,500,'dzdy_dzbgl.do?method=getXx&bbb=${index}');">ѡ��
	                    </button>
	                     <input  name="xhs" value="${item.lxrid}" id="xh${index}"  type="hidden" >
					<th width="20%"><font color="red">*</font>��ϵ��ʽ</th>
	                <td>
	                    <input  name="xsdhs" value="${item.lxrdh}" id="xsdh${index}">
	                </td>
	  		    </tr>
		    </logic:iterate>


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
                        <button type="button" onclick="hjAddDzb(dzblx,jgcount,xscount);">
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
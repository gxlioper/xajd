<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
		jQuery(function(){
			jQuery("#xh").attr("style","width:120px;border: none;");
			jQuery("#xh").attr("readonly",true);
			jQuery(".btn_01").hide();
		})
   //      function saveZwsq(){
   //          var checkId = 'xh-hdid';
   //          if(!checkNotNull(checkId)){
   //              showAlertDivLayer("�뽫��������д������");
   //              return false;
   //          }
			// var dwid = jQuery("#dwid").val();
			// if(dwid==""||dwid==null){
			// 	confirmInfo("δѡ����飬ȷ������������?",function(ok){
			// 			if(ok=="ok"){		
			// 				save();
			// 			}
			// 	});
			// }else{
			// 	save();
			// }
   //      }
		function updateQd(){
			var url = "hdgl_hdgl_hdqd_wh.do?method=saveQdxx";
			ajaxSubFormWithFun("demoForm",url,function(data){
			    showAlertDivLayer(data["message"],{},{"clkFun":function(){
			        if (parent.window){
			            refershParent();
			        }
			        iFClose();
			    }});
			});
		}

		// function selectHd(){
		// 	var xh = jQuery("#xh").val();
		// 	var goto = encodeURIComponent('${path}'+'&xh='+xh);
		//     showDialog("ѡ��",800,500,"hdgl_hdgl_hdqd_wh.do?method=getHdxxList&goto="+goto);
		// }
		// function selectDw(){
		// 	var xh = jQuery("#xh").val();
		// 	var hdid = jQuery("#hdid").val();
		// 	var goto = encodeURIComponent('${path}'+'&xh='+xh+'&hdid='+hdid);
		// 	showDialog("ѡ�����",600,500,"hdgl_hdgl_hdqd_wh.do?method=getDwList&xh="+xh+"&hdid="+hdid+"&goto="+goto);
		// }
		// function chearDw(){
		// 	jQuery("#dwid").val("");
		// 	jQuery("#dzxm").val("");
		// }
    </script>
</head>
<body style="width:100%">
<html:form action="/hdgl_hdgl_hdqd_wh" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ����Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/hdgl/hdqd/selectStudentforqd.jsp" %>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>���Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>�����</th>
                <td>
                    <html:hidden property="hdid" styleId="hdid" />
                    <html:hidden property="bmlx" styleId="bmlx" />
                    <html:text property="hdmc" styleId="hdmc" readonly="true" style="border: none;"/>
                    <!-- <button class="btn_01" type="button" onclick="selectHd();">ѡ��</button> -->
                </td>
                <th>��ص�</th>
                <td>
    				<html:text property="hddd" styleId="hddd" readonly="true" style="border: none;"/>
                </td>
            </tr>
    		<tr>
    		    <th>���ʼʱ��</th>
    		    <td>
    				<html:text property="hdkssj" styleId="kssj" readonly="true" style="border: none;"/>
    		    </td>
    		    <th>�����ʱ��</th>
    		    <td>
    				<html:text property="hdjssj" styleId="jssj" readonly="true" style="border: none;"/>
    		    </td>
    		</tr>
            </tbody>
    		<logic:equal name="hdxx" property="bmlx" value="0">
    			<thead>
    			    <tr>
    			        <th colspan="4">
    			            <span>�ûΪ�Ŷӻ����ѡ����飨������������ѡ��</span>
    			        </th>
    			    </tr>
    			</thead>
    			<tbody>
    			<tr>
    			    <th>������</th>
    			    <td>
    			    	<html:text  property="dwid" styleId="dwid" readonly="true" style="border: none;width: 120px;"></html:text>
    			    	<!-- <button class="btn_01" type="button" onclick="selectDw();" >ѡ��</button> -->
						<!-- <button class="btn_01" type="button" onclick="chearDw();" >���</button> -->
    			    </td>
    			    <th>�ӳ�����</th>
    			    <td>
    			    	<html:text  property="dzxm" styleId="dzxm" readonly="true" style="border: none;"></html:text>
    			    </td>
    			</tr>
    			</tbody>
    		</logic:equal>
    		<thead>
    		    <tr>
    		        <th colspan="4">
    		            <span>ǩ��ǩ��ʱ��</span>
    		        </th>
    		    </tr>
    		</thead>
    		<tbody>
    		<tr>
    		    <th>ǩ��ʱ��</th>
    		    <td>
    				<html:text  property="qdsj" styleId="qdsj"
    				            onclick="showCalendar('qdsj','yyyy-MM-dd HH:mm:ss');" ></html:text>
    		    </td>
    		    <th>ǩ��ʱ��</th>
    		    <td>
    				<html:text  property="qtsj" styleId="qtsj"
    				            onclick="showCalendar('qtsj','yyyy-MM-dd HH:mm:ss');" ></html:text>
    		    </td>
    		</tr>
    		</tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="updateQd();return false;" >
                            ����
                        </button>
                        <button type="button" name="�� ��" onclick="iFClose();">
                            �� ��
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


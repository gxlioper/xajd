<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/comm/MSClass.js"></script>

</head>
<body onload="">
<div class="notice">
		 <h3>
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 </h3>
		<marquee behavior="scroll" scrollAmount="3" 
					 width="500" step="5"
					 timer="20" dealayTime="10"
					 height="25" direction="left" onmouseover="stop()"
					 onmouseout="start()" style="padding: 0px; white-space: nowrap;">
					<b>${tsyj }</b>
			</marquee>
  		 <!--    <a class="close" title="����"></a>  С������X��ť-->     
      	</div>
        
        	<!-- ���ؽ������ѽ����ſ�ȵ���� -->
    		<div class="newsnotice" style="width:99.5%">
    			<jsp:include flush="true"
								page="newsMain.jsp"></jsp:include>
      			
    		</div>
    		<!-- ���ؽ������� -->
<%--    		<div class="remindtoday">--%>
<%--      			<h3><span>��������</span><a href="#"></a></h3>--%>
<%--      			<ul>--%>
<%--      				<li><a href="#">������</a></li>--%>
<%--      			</ul>--%>
<%--                --%>
<%--    		</div>--%>
            <div class="index_box_01">
      			<h3><span>���¹���</span><a href="#" onclick="showOpenWindow('gwjsMore.do');"></a></h3>
      			<ul class="index_list_01">
      				<jsp:include flush="true" page="/gwjs.jsp"></jsp:include>
      			</ul>
    		</div>
            <div class="index_box_01">
      			<h3><span>��ѧ��ѧ��ϵͳʹ�����</span><a href="#"></a></h3>
                <table width="100%" border="0" class="index_tab_01">
                  <thead>
                  <tr>
                    <td>ģ������</td>
                    <td>����������</td>
                    <td>����Աͨ������</td>
                    <td><bean:message key="lable.xb" />�������</td>
                    <td>ѧУͨ������</td>
                    <td>ѧУδͨ������</td>
                    <td>�跢���ܽ����</td>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td>ѧ������</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                  </tr>
                  <tr class="color">
                    <td>�ڹ���ѧ</td>
                       <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                  </tr>
                  <tr>
                    <td>��������</td>
                       <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                  </tr>
                  </tbody>
                </table>

            </div>
</body>
</html>

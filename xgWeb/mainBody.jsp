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
  		 <!--    <a class="close" title="隐藏"></a>  小喇叭旁X按钮-->     
      	</div>
        
        	<!-- 隐藏今日提醒将新闻宽度调到最到 -->
    		<div class="newsnotice" style="width:99.5%">
    			<jsp:include flush="true"
								page="newsMain.jsp"></jsp:include>
      			
    		</div>
    		<!-- 隐藏今日提醒 -->
<%--    		<div class="remindtoday">--%>
<%--      			<h3><span>今日提醒</span><a href="#"></a></h3>--%>
<%--      			<ul>--%>
<%--      				<li><a href="#">建设中</a></li>--%>
<%--      			</ul>--%>
<%--                --%>
<%--    		</div>--%>
            <div class="index_box_01">
      			<h3><span>最新公文</span><a href="#" onclick="showOpenWindow('gwjsMore.do');"></a></h3>
      			<ul class="index_list_01">
      				<jsp:include flush="true" page="/gwjs.jsp"></jsp:include>
      			</ul>
    		</div>
            <div class="index_box_01">
      			<h3><span>本学年学工系统使用情况</span><a href="#"></a></h3>
                <table width="100%" border="0" class="index_tab_01">
                  <thead>
                  <tr>
                    <td>模块名称</td>
                    <td>已申请人数</td>
                    <td>辅导员通过人数</td>
                    <td><bean:message key="lable.xb" />审核人数</td>
                    <td>学校通过人数</td>
                    <td>学校未通过人数</td>
                    <td>需发放总金额数</td>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td>学生资助</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                  </tr>
                  <tr class="color">
                    <td>勤工助学</td>
                       <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                  </tr>
                  <tr>
                    <td>评奖评优</td>
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

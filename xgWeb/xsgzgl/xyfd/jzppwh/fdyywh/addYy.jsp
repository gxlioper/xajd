
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

        function saveFdyy(type){
            var checkId = 'xh-fdsj-fdkc';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var fdsj = jQuery("#fdsj").val();
            var xq = ""; //改辅导时间所属星期
            var day = new Date(Date.parse(fdsj));
            var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
            var week = today[day.getDay()];
            var time = day.getHours()+':'+day.getMinutes();
            var result = false;
            if(week=='星期日'){
                var sun = '${fdkcxx.sun}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }else if(week=='星期一'){
                var sun = '${fdkcxx.mon}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }else if(week=='星期二'){
                var sun = '${fdkcxx.tues}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }else if(week=='星期三'){
                var sun = '${fdkcxx.wed}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }else if(week=='星期四'){
                var sun = '${fdkcxx.thur}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }else if(week=='星期五'){
                var sun = '${fdkcxx.fri}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }else if(week=='星期六'){
                var sun = '${fdkcxx.sat}';
                if(sun!=null&&sun!=""){
                    var sund = sun.split(",");
                    for(var i=0;i<sund.length;i++){
                        if(time>=sund[i].split("-")[0]&&time<=sund[i].split("-")[1]){
                            result = true;
                        }
                    }
                }
            }
            if(!result){
                showAlertDivLayer("请将所选辅导时间不在排班时间内！");
                return false;
            }
            save(type);
        }
        function save(type){
            var url = "xyfd_fqyy.do?method=saveAdd&type="+type;
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
		function selectFdkc() {
            var height = jQuery(window).height();
            var xh = jQuery("#xh").val();
            var goto = encodeURIComponent('${gotopath}'+'&xh='+xh);
            showDialog("选择辅导课程",800,height,"xyfd_fqyy.do?method=selectFdkc&goto="+goto);
        }


	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fqyy" method="post" styleId="demoForm">
	<input type="hidden" id="yysf" name="yysf" value="${yysf}"/>
	<input type="hidden" id="isJsOrPb" name="isJsOrPb" value="${isJsOrPb}"/>
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>预约学生信息</span>
				</th>
			</tr>
			</thead>
			<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/selectStudentforFdyy.jsp" %>
			<thead>
			<tr>
				<th colspan="4">
					<span>预约课程</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">课程名称</th>
				<td width="30%">
					<input type="hidden" id="fdkc" name="fdkc" value="${fdkcxx.jgid}"/>
					<input type="text" id="kcmc" name="kcmc" value="${fdkcxx.kcmc}" readonly="readonly"/>
					<%--<button class="btn_01" type="button" onclick="selectFdkc();">选择</button>--%>
				</td>
				<th width="20%">辅导地点</th>
				<td width="30%">
					${fdkcxx.fdsdd}
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>辅导时间</th>
				<td colspan="3">
					<html:text property="fdsj" styleId="fdsj" name="model"
							   onclick="showCalendar('fdsj','yyyy-MM-dd HH:mm');" readonly="readonly"></html:text>
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4">
					<span>辅导教师【${fdkcxx.xm}】的排班</span>
				</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<th colspan="4">
						<table width="100%">
							<tbody>
							<tr>
								<th width="14%">周一</th>
								<th width="14%">周二</th>
								<th width="14%">周三</th>
								<th width="14%">周四</th>
								<th width="14%">周五</th>
								<th width="14%">周六</th>
								<th width="14%">周日</th>
							</tr>
							<tr>
								<td>${fdkcxx.mon}</td>
								<td>${fdkcxx.tues}</td>
								<td>${fdkcxx.wed}</td>
								<td>${fdkcxx.thur}</td>
								<td>${fdkcxx.fri}</td>
								<td>${fdkcxx.sat}</td>
								<td>${fdkcxx.sun}</td>
							</tr>
							</tbody>
						</table>
					</th>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>预约记录</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>预约号</th>
					<th>辅导时间</th>
					<th>预约人</th>
					<th>预约状态</th>
				</tr>
				<logic:iterate id="yyxx" name="lsyyList" indexId="i">
				<tr>
					<td>${yyxx.yyh}</td>
					<td>${yyxx.fdsj}</td>
					<td>${yyxx.yyr}</td>
					<td>${yyxx.shztmc}</td>
				</tr>
				</logic:iterate>
			</tbody>
		</table>
	</div>
	<div style="position:fixed;bottom:0;width: 100%">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button type="button" type="button" onclick="saveFdyy('save');return false;" >
							保存草稿
						</button>
						<button type="button" type="button" onclick="saveFdyy('submit');return false;" >
							提交预约
						</button>
						<button type="button" name="关 闭" onclick="iFClose();">
							关 闭
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

     
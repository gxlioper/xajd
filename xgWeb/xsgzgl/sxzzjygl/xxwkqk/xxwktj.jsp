<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="application/javascript">
		function search() {
            var kssj = jQuery("#kssj").val();
            var jssj = jQuery("#jssj").val();
            if(kssj=="" && jssj=="")
			{
                showAlertDivLayer("请输入查询的起始时间！");
                return false;
			}
            var aDate = new Date(kssj); // 通过日期字符串，初始化第一个日期对象
            var bDate = new Date(jssj); // 同上，初始化第二个日期对象
            var aDay = 24 * 60 * 60 * 1000; // 完整一天有24小时，将小时换算成毫秒时间戳
            var diffDay = (bDate - aDate) / aDay; // 计算间隔天数（注意日期隐式转换）
            if(diffDay+1>7)
			{
                showAlertDivLayer("查询天数不能大于7天！");
                return false;
			}
            //表头
            jQuery.post("xxwkqk_xxwktj.do?method=getBtList",{"kssj":kssj,"jssj":jssj},function(data){
                var liHtml="";
                liHtml+="<tr class=\"nowrap\">";
                for (var i = 0 ; i < data.length ; i++){
                    //处理内容
					if(i==0)
					{
                        liHtml+="<th>节次</th>";
					}
                    liHtml+="<th>"+data[i]["xq"]+"</br>("+data[i]["cxrq"]+")</th>";
                }
                liHtml+="</tr>";
                jQuery("#bt").html(liHtml);
            },"json");
            //内容
            jQuery.post("xxwkqk_xxwktj.do?method=getList",{"kssj":kssj,"jssj":jssj},function(data){
                var liHtml="";
                for (var a = 1 ;a < 13 ; a++)
				{
				    liHtml+="<tr>";
                    for (var i = 0 ; i < data.length ; i++){
                        //处理内容
						if(i==0)
						{

                            liHtml+="<td>"+a+"</td>";
						}
						if(data[i]["jtjc"]==a)
						{
                            liHtml+="<td><a href=\"xxwkqk_xxwktj.do?method=getrqjcInfo&jc="+data[i]["jtjc"]+"&rq="+data[i]["cxrq"]+"\" class='list-group-item'>总无课人数:"+data[i]["num"]+"&nbsp;&nbsp;占比："+data[i]["zb"]+"</td>";

						}
                    }
                    liHtml+="</tr>";
				}

                jQuery("#nr").html(liHtml);
            },"json");


        }

	</script>
	</head>

	<body>
	<html:form action="/xxwkqk_xxwktj">
		<%@ include file="/comm/hiddenValue.jsp"%>
	</html:form>
	<!-- 过滤条件 -->
<html:form action="/xxwkqk_xxwktj" method="post" styleId="XxwktjForm">
	<div class="searchtab" style="padding-bottom: 20px;">
		<table width="100%" border="0">
			<tr>
				<th width="15%">开始日期</th>
				<td>
					<html:text property="kssj" styleId="kssj"
							   onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" />
				</td>

				<th width="15%">结束日期</th>
				<td>
					<html:text property="jssj" styleId="jssj"
							   onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" />
				</td>
				<td>
					<div class="btn">
						<button type="button" class="btn_cx" id="search_go" onclick="search();">
							查 询
						</button>
						<button type="button" class="btn_cz" id="btn_cz"
								onclick="czSearchCond();">
							重 置
						</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
</html:form>

	<div class="main_box">

		<div class="con_overlfow">
			<table id="dataTable" border="1"  class="dateline" width="95%">
				<thead id="bt"></thead>
				<tbody id="nr"></tbody>
			</table>
			<div id="pager"></div>
		</div>
	</div>
	</body>
</html>

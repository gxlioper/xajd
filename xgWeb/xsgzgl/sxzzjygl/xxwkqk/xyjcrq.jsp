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


        jQuery(function(){

            search();
        });
		function search() {
            //表头
            var xydm = jQuery("#xymc").val();
            jQuery.post("xxwkqk_xxwktj.do?method=getjcrqBtList",{cxxy:xydm},function(data){
                var liHtml="";
                liHtml+="<tr class=\"nowrap\">";
                for (var i = 0 ; i < data.length ; i++){
                    //处理内容
                    if(i==0)
                    {
                        liHtml+="<th >年级</th>";
                    }
                    liHtml+="<th style='min-width: 80px;'>"+data[i]["xymc"]+"</th>";
                }
                liHtml+="</tr>";
                jQuery("#bt").html(liHtml);
            },"json");
//            //内容
            var list = jQuery("#nj").val();
            var arr = list.split(",");
            var jc = jQuery("#jc").val();
            var rq = jQuery("#rq").val();
            jQuery.post("xxwkqk_xxwktj.do?method=getjcrqInfoList",{"jc":jc,"rq":rq,cxxy:xydm},function(data){
                var liHtml="";
                for (var a = 0 ;a < arr.length ; a++)
				{
				    liHtml+="<tr>";
                    for (var i = 0 ; i < data.length ; i++){
                        //处理内容
						if(i==0)
						{

                            liHtml+="<td>"+arr[a]+"</td>";
						}
						if(data[i]["nj"]==arr[a])
						{
                            liHtml+="<td style=\"word-break:break-all;\" ><a  href=\"xxwkqk_xxwktj.do?method=getxyrqInfo&jc="+jc+"&rq="+rq+"&xydm="+data[i]["xydm"]+"\" class='list-group-item'>无课人数:"+data[i]["num"]+"&nbsp;&nbsp;占比："+data[i]["zb"]+"</td>";

						}
                    }
                    liHtml+="</tr>";
				}

                jQuery("#nr").html(liHtml);
            },"json");


        }

        function searchBytj() {
            var jc = jQuery("#jc").val();
            var rq = jQuery("#rq").val();
            var cxnj = jQuery("#cxnj").val();
            var cxxy = jQuery("#cxxy").val();
            document.location.href  = "xxwkqk_xxwktj.do?method=getrqjcInfo&jc="+jc+"&rq="+rq+"&cxnj="+cxnj+"&cxxy="+cxxy;
        }
        function czSearchCond() {
            jQuery("#cxxy").val("");
            jQuery("#cxnj").val("");
        }

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
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
				<th><span style="font-size: 20px">${rq}第${jc}节课无课情况</span></th>
				<th width="15%">年级</th>
				<td>
					<html:select property="cxnj" styleId="cxnj" style="width:100px;">
						<html:options collection="njList" property="nj" labelProperty="nj" />
					</html:select>
				</td>

				<th width="15%">学院</th>
				<td>
					<html:select property="cxxy" styleId="cxxy" style="width:100px;">
						<html:options collection="xyList" property="xydm" labelProperty="xymc" />
					</html:select>

				</td>
				<td>
					<div class="btn">
						<button type="button" class="btn_cx" id="search_go" onclick="searchBytj();">
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
		<input type="hidden" id="jc" value="${jc}"/>
		<input type="hidden" id="rq" value="${rq}"/>
		<input type="hidden" id="nj" value="${njString}"/>
		<input type="hidden" id="xymc" value="${xymc}"/>
		<div class="con_overlfow" style="overflow:scroll;">
			<table id="dataTable" border="1"  class="dateline"  width="95%" >
				<thead id="bt"></thead>
				<tbody id="nr"></tbody>
			</table>
			<div id="pager"></div>
		</div>
	</div>
	</body>
</html>

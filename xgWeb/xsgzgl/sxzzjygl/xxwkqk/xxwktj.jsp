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
                showAlertDivLayer("�������ѯ����ʼʱ�䣡");
                return false;
			}
            var aDate = new Date(kssj); // ͨ�������ַ�������ʼ����һ�����ڶ���
            var bDate = new Date(jssj); // ͬ�ϣ���ʼ���ڶ������ڶ���
            var aDay = 24 * 60 * 60 * 1000; // ����һ����24Сʱ����Сʱ����ɺ���ʱ���
            var diffDay = (bDate - aDate) / aDay; // ������������ע��������ʽת����
            if(diffDay+1>7)
			{
                showAlertDivLayer("��ѯ�������ܴ���7�죡");
                return false;
			}
            //��ͷ
            jQuery.post("xxwkqk_xxwktj.do?method=getBtList",{"kssj":kssj,"jssj":jssj},function(data){
                var liHtml="";
                liHtml+="<tr class=\"nowrap\">";
                for (var i = 0 ; i < data.length ; i++){
                    //��������
					if(i==0)
					{
                        liHtml+="<th>�ڴ�</th>";
					}
                    liHtml+="<th>"+data[i]["xq"]+"</br>("+data[i]["cxrq"]+")</th>";
                }
                liHtml+="</tr>";
                jQuery("#bt").html(liHtml);
            },"json");
            //����
            jQuery.post("xxwkqk_xxwktj.do?method=getList",{"kssj":kssj,"jssj":jssj},function(data){
                var liHtml="";
                for (var a = 1 ;a < 13 ; a++)
				{
				    liHtml+="<tr>";
                    for (var i = 0 ; i < data.length ; i++){
                        //��������
						if(i==0)
						{

                            liHtml+="<td>"+a+"</td>";
						}
						if(data[i]["jtjc"]==a)
						{
                            liHtml+="<td><a href=\"xxwkqk_xxwktj.do?method=getrqjcInfo&jc="+data[i]["jtjc"]+"&rq="+data[i]["cxrq"]+"\" class='list-group-item'>���޿�����:"+data[i]["num"]+"&nbsp;&nbsp;ռ�ȣ�"+data[i]["zb"]+"</td>";

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
	<!-- �������� -->
<html:form action="/xxwkqk_xxwktj" method="post" styleId="XxwktjForm">
	<div class="searchtab" style="padding-bottom: 20px;">
		<table width="100%" border="0">
			<tr>
				<th width="15%">��ʼ����</th>
				<td>
					<html:text property="kssj" styleId="kssj"
							   onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" />
				</td>

				<th width="15%">��������</th>
				<td>
					<html:text property="jssj" styleId="jssj"
							   onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" />
				</td>
				<td>
					<div class="btn">
						<button type="button" class="btn_cx" id="search_go" onclick="search();">
							�� ѯ
						</button>
						<button type="button" class="btn_cz" id="btn_cz"
								onclick="czSearchCond();">
							�� ��
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

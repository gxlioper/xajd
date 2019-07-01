<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
            jQuery(function(){
                var gridSetting = {
                    caption:"�ҵĵ���",
                    pager:"pager",
                    url:"dycjgl_sjcx.do?method=sjcxQuery",
                    multiselect:false
                };
                var zcxm = jQuery("font[name=xm]");
                var colList=[
                    {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
                    {label:'ѧ��',name:'xq', index: 'xq',width:'10%'},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
                    {label:'����',name:'xm', index: 'xm',width:'8%'},
                    {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
                    {label:'�༶����',name:'bjdm', index: 'bjdm',hidden:true},
                ];

                jQuery.each(zcxm,function(i,n){
                    var json = {label:jQuery(n).attr("xmmc"), name:"fs"+i, index:"fs"+i,width:'5%'};
                    colList.push(json);
                });
                colList.push({label:'�����ܳɼ�',name:'dyzcj', index: 'dyzcj',width:'7%',formatter:setDyzcj});
                colList.push({label:'�Ƿ�ϸ�',name:'sfhg', index: 'sfhg',width:'7%' ,formatter:setTjsz});
                colList.push({label:'����ѧ��',name:'dyxf', index: 'dyxf',width:'7%'});
                colList.push({label:'�������',name:'bkqk', index: 'bkqk',width:'15%'});
                colList.push({label:'�����Ƿ�ϸ�',name:'bksfhg', index: 'bksfhg',width:'7%'});
                gridSetting["colList"] = colList;
                //���������������ɼ�¼��
                jQuery("#dataTable").initGrid(gridSetting);
            });
            function setTjsz(cellValue,rowObject){
                var value;
                if(cellValue == '���ϸ�') {
                    value = "���ϸ�";
                }
                else if(cellValue == '�ϸ�'){
                    value = "�ϸ�";
                }
                else{
                    value="";
				}
                value = setColor(value,cellValue);
                return value;
            }
            function setColor(value,status){
                var color;
                if(status == '�ϸ�'){
                    color = "#004400";
                }else{
                    color = "red";
                }
                return value = "<font color='"+color+"'>" + value + "</font>";
            }


            function setDyzcj(cellValue,rowObject){
                var value;
                if(Number(cellValue) < 60 && cellValue != null) {
                    value = cellValue;
                }
                else if(Number(cellValue) >= 60 && cellValue != null){
                    value = cellValue;
                }
                else if(cellValue == null){
                    value = "";
                }
                else{
                    value="";
                }
                value = setColorDycj(value,cellValue);
                return value;
            }
            function setColorDycj(value,status){
                var color;
                if(Number(status) < 60){
                    color = "red";
                }else{
                    color = "#004400";
                }
                return value = "<font color='"+color+"'>" + value + "</font>";
            }

            /* ���� */
            function exportXX() {
                customExport('dycjgl_sjcx_sjcx.do', exportData);
            }
            function exportData(){
                var url = "dycjgl_sjcx.do?method=exportData&dcclbh=" + 'dycjgl_sjcx_sjcx.do';// dcclbh,�������ܱ��,���ݱ��ֶ�
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
            }

            function searchRs() {
                var map = getSuperSearch();
                jQuery("#dataTable").reloadGrid(map);
            }

		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<logic:iterate id="z" name="xmList">
			<font style="display:none;" xmdm="${z.xmdm }"
				  xmmc="${z.xmmc }" cjhgfsx="${z.cjhgfsx }" xmsm="${z.xmsm }" name="xm"></font>
		</logic:iterate>
		<html:form action="/dycjgl_sjcx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="bjdm" value="${bjdm}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="exportXX();return false" class="btn_dc" >����</a>
					</li>

				</ul>
			</div>

			<!-- �������� -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		</div>
		</html:form>
		<div class="formbox" >
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

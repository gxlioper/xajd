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
                    caption:"我的德育",
                    pager:"pager",
                    url:"dycjgl_wddy.do?method=wddyQuery",
                    multiselect:false
                };
                var zcxm = jQuery("font[name=xm]");
                var colList=[
                    {label:'学年',name:'xn', index: 'xn',width:'10%'},
                    {label:'学期',name:'xq', index: 'xq',width:'10%'},
                    {label:'学号',name:'xh', index: 'xh',width:'12%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                    {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
                    {label:'班级代码',name:'bjdm', index: 'bjdm',hidden:true},
                ];

                jQuery.each(zcxm,function(i,n){
                    var json = {label:jQuery(n).attr("xmmc"), name:"fs"+i, index:"fs"+i,width:'5%'};
                    colList.push(json);
                });
                colList.push({label:'德育总成绩',name:'dyzcj', index: 'dyzcj',width:'7%',formatter:setDyzcj});
                colList.push({label:'是否合格',name:'sfhg', index: 'sfhg',width:'7%',formatter:setTjsz});
                colList.push({label:'德育学分',name:'dyxf', index: 'dyxf',width:'7%'});
                colList.push({label:'补考情况',name:'bkqk', index: 'bkqk',width:'15%'});
                colList.push({label:'补考是否合格',name:'bksfhg', index: 'bksfhg',width:'7%'});
                gridSetting["colList"] = colList;
                //补考考生及补考成绩录入
                jQuery("#dataTable").initGrid(gridSetting);
            });


            function setTjsz(cellValue,rowObject){
                var value;
                if(cellValue == '不合格') {
                    value = "不合格";
                }
                else if(cellValue == '合格'){
                    value = "合格";
                }
                else{
                    value="";
				}
                value = setColor(value,cellValue);
                return value;
            }
            function setColor(value,status){
                var color;
                if(status == '合格'){
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

            /* 导出 */
            function exportXX() {
                customExport('dycjgl_wddy_wddy.do', exportData);
            }
            function exportData(){
                var bjdm = jQuery("#bjdm").val();
                var url = "dycjgl_wddy.do?method=exportData&dcclbh=" + 'dycjgl_wddy_wddy.do';// dcclbh,导出功能编号,数据表字段
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
            }



		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<logic:iterate id="z" name="xmList">
			<font style="display:none;" xmdm="${z.xmdm }"
				  xmmc="${z.xmmc }" cjhgfsx="${z.cjhgfsx }" xmsm="${z.xmsm }" name="xm"></font>
		</logic:iterate>
		<html:form action="/dycjgl_wddy">
			<input type="hidden" id="bjdm" value="${bjdm}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="exportXX();return false" class="btn_dc" >导出</a>
					</li>

				</ul>
			</div>

			<!-- 过滤条件 -->
			<div class="searchtab">
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

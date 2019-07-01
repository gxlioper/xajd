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
                var bjdm = jQuery("#bjdm").val();
                var gridSetting = {
                    caption:"班级人员列表",
                    pager:"pager",
                    url:"dycjwh_dycjgl.do?method=viewBjmdQuery&bjdm="+bjdm,
                    multiselect:false
                };
                var zcxm = jQuery("font[name=xm]");
                var colList=[
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
            /**
             * 查看--查询
             */
            function bjQuery(){
                var jsonStr = jQuery("#jsonStr").val();
                var map = {};
                if(jsonStr){
                    map = JSON.parse(jsonStr);
                }
                map["xh"] = jQuery("#xh").val();
                jQuery("#dataTable").reloadGrid(map);
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
		<html:form action="/dycjwh_dycjgl">
			<input type="hidden" id="bjdm" value="${bjdm}"/>
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">学号/姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20"  />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="bjQuery()">
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
		<div class="formbox">
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

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
            function selectTab(obj, sffp) {
                jQuery("#sffp").val(sffp);
                var bjdm = jQuery("#bjdm").val();
                if (sffp == "not") {
                    jQuery("#zcdr").show();
                    jQuery("#zcdc").show();
                    jQuery("#bhgdr").hide();
                    jQuery("#bhgdc").hide();
                    var gridSetting = {
                        caption:"参评学生列表 ",
                        pager:"pager",
                        url:"dycjwh_dycjgl.do?method=viewstuList&bjdm="+bjdm,
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
                        var json = {label:jQuery(n).attr("xmmc"),
                            name:"fs"+i,
                            index:"fs"+i
                        };
                        json["formatter"] = function(cell,rowObject){
                            var html="";
                            html+= "<input onblur=\"checkInputNum(this);savedycj(this,'";
                            html+=jQuery(n).attr("xmdm");
                            html+="','";
                            html+=rowObject["xh"];
                            html+="')\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
                            html+=" style='width:80px;' maxlength='10' value='";
                            html+=cell==null ? "" : cell;
                            html+="' name='";
                            html+=jQuery(n).attr("xmmc")+"' />";

                            return html;
                        };
                        colList.push(json);
                    });
                    colList.push({label:'德育总成绩',name:'dyzcj', index: 'dyzcj',width:'7%',formatter:setDyzcj});
                    colList.push({label:'是否合格',name:'sfhg', index: 'sfhg',width:'7%',formatter:setTjsz});
                    colList.push({label:'德育学分',name:'dyxf', index: 'dyxf',width:'7%'});

                    gridSetting["colList"] = colList;

                    jQuery("#dataTable").initGrid(gridSetting);

                } else {
                    jQuery("#zcdr").hide();
                    jQuery("#zcdc").hide();
                    jQuery("#bhgdr").show();
                    jQuery("#bhgdc").show();
                    var bjdm = jQuery("#bjdm").val();
                    var sfhgList = ${sfhgList};
                    var gridSetting2 = {
                        caption:"",
                        pager:"pager",
                        url:"dycjwh_dycjgl.do?method=viewBkList&bjdm="+bjdm,
                        radioselect:true
                    }

                    var colList2=[
                        {label:'学号',name:'xh', index: 'xh',width:'12%'},
                        {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                        {label:'班级',name:'bjmc', index: 'bjmc',width:'17%'},
                        {label:'班级代码',name:'bjdm', index: 'bjdm',hidden:true},
                    ];
                    var bkjson = {label:'不合格学生补考情况',
                        name:"bkqk",
                        index:"bkqk",
                        width:'23%'
                    };
                    bkjson["formatter"] = function(cell,rowObject){
                        var html="";
                        html+= "<textarea onblur=\"saveBkqk(this";
                        html+=",'";
                        html+=rowObject["xh"];
                        html+="')\"  onkeyup='toNext(this,event);'";
                        html+=" style='width:289px;height:66px;'";
                        html+="' name=''>"
                        html+=cell==null ? "" : cell;
                        html+="</textarea>";

                        return html;
                    };
                    var bkjson2 = {label:'补考是否合格',
                        name:"bksfhg",
                        index:"bksfhg"
                    };
                    bkjson2["formatter"] = function(cell,rowObject){
                        var html="";
                        html+="<select onchange=\"saveHg(this,'"+rowObject["xh"]+"')\">";
                        html+="<option value=''>请选择补考是否合格</option>";
                        jQuery.each(sfhgList,function(i,sfhg){
                                var option = "<option value='" + sfhg['xxmc'] + "'";
                                if(cell == sfhg['xxmc']){
                                    option+=" selected ";
                                }
                                option+= ">" +  sfhg['xxmc'] + "</option>";
                                html+=option;

                        });
                        html+="</select>";


                        return html;
                    };
                    colList2.push(bkjson);
                    colList2.push(bkjson2);
                    colList2.push({label:'德育学分',name:'dyxf', index: 'dyxf',width:'10%'});
                    gridSetting2["colList"] = colList2;
                    jQuery("#dataTable").initGrid(gridSetting2);
                }
                jQuery(".ha").removeClass("ha");
                jQuery(obj).parent().addClass("ha");
            }

            jQuery(function(){
                jQuery("#zcdr").show();
                jQuery("#zcdc").show();
                jQuery("#bhgdr").hide();
                jQuery("#bhgdc").hide();
                var bjdm = jQuery("#bjdm").val();
                var gridSetting = {
                    caption:"参评学生列表 ",
                    pager:"pager",
                    url:"dycjwh_dycjgl.do?method=viewstuList&bjdm="+bjdm,
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
                    var json = {label:jQuery(n).attr("xmmc"),
                        name:"fs"+i,
                        index:"fs"+i
                    };
                    json["formatter"] = function(cell,rowObject){
                        var html="";
                        html+= "<input onblur=\"checkInputNum(this);savedycj(this,'";
                        html+=jQuery(n).attr("xmdm");
                        html+="','";
                        html+=rowObject["xh"];
                        html+="')\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
                        html+=" style='width:80px;' maxlength='10' value='";
                        html+=cell==null ? "" : cell;
                        html+="' name='";
                        html+=jQuery(n).attr("xmmc")+"' />";

                        return html;
                    };
                    colList.push(json);
                });
                colList.push({label:'德育总成绩',name:'dyzcj', index: 'dyzcj',width:'7%',formatter:setDyzcj});
                colList.push({label:'是否合格',name:'sfhg', index: 'sfhg',width:'7%',formatter:setTjsz});
                colList.push({label:'德育学分',name:'dyxf', index: 'dyxf',width:'7%'});

                gridSetting["colList"] = colList;

                //补考考生及补考成绩录入
                jQuery("#dataTable").initGrid(gridSetting);
            });




            function savedycj(obj,xmdm,xh){
                var fs = obj.value;
                var zero = 0;
                if(fs==null || fs=='')
				{
                    return false;
				}
				else{
                if ( Number(fs) < Number(zero)){
                    showAlertDivLayer("录入分数不能小于0",{},{"clkFun":function(){
                    }});
                    return false;
                }

                jQuery.post("dycjwh_dycjgl.do?method=savedycj",{xmdm:xmdm,xh:xh,fs:fs},function(data){
                    jQuery("#dataTable").reloadGrid();
                    if (data){
                        showAlert(data["message"]);
                    }
                },"json");
                }
            }

            function saveBkqk(obj,xh){
                var bkqk = obj.value;
                jQuery.post("dycjwh_dycjgl.do?method=saveBkqk",{bkqk:bkqk,xh:xh},function(data){
                    if (data){
                        showAlert(data["message"]);
                    }
                },"json");

            }
            function saveHg(obj,xh){
                var bksfhg = obj.value;
                jQuery.post("dycjwh_dycjgl.do?method=saveHg",{bksfhg:bksfhg,xh:xh},function(data){

                    if (data){
                        showAlert(data["message"]);
                    }
                },"json");
                jQuery("#dataTable").reloadGrid();
            }


            /**
             * 综测分输入框 上、下、左、右键事件
             * @param obj
             */
            function toNext(obj,event){
                var event = event || window.event;
                //左
                if (event.keyCode==37){
                    var inputs = jQuery("#dataTable input:not(:checkbox)");
                    var index = inputs.index(jQuery(obj));
                    inputs.eq(index-1).select();
                }

                //上
                if (event.keyCode==38){
                    var tr = jQuery(obj).parents("tr");
                    var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
                    jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
                }

                //右   或者 回车
                if (event.keyCode==39 || event.keyCode==13){
                    var inputs = jQuery("#dataTable input:not(:checkbox)");
                    var index = inputs.index(jQuery(obj));
                    inputs.eq(index+1).select();
                }

                //下
                if (event.keyCode==40){
                    var tr = jQuery(obj).parents("tr");
                    var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
                    jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
                }

            }


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
            function doQuery(){
                var jsonStr = jQuery("#jsonStr").val();
                var map = {};
                if(jsonStr){
                    map = JSON.parse(jsonStr);
                }
                map["xh"] = jQuery("#xh").val();
                jQuery("#dataTable").reloadGrid(map);
            }

            /* 导出 */
            function exportXX() {
                customExport('dycjgl_dycjwh_dycjgl.do', exportData);
            }
            function exportData(){
                var bjdm = jQuery("#bjdm").val();
                var url = "dycjwh_dycjgl.do?method=exportData&dcclbh=" + 'dycjgl_dycjwh_dycjgl.do&bjdm='+bjdm;// dcclbh,导出功能编号,数据表字段
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
            }

            /* 导出 */
            function exportBHG() {
                customExport('dycjgl_dycjwh_bhg.do', exportDataBhg);
            }
            function exportDataBhg(){
                var bjdm = jQuery("#bjdm").val();
                var url = "dycjwh_dycjgl.do?method=exportDataBhg&dcclbh=" + 'dycjgl_dycjwh_bhg.do&bjdm='+bjdm;// dcclbh,导出功能编号,数据表字段
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
            }
            function fhjcsd(){
                document.location.href = "dycjgl_dycjwh_dycjgl.do";
            }

            /* 导入 */
            function importXX(){
                var bjdm = jQuery("#bjdm").val();
                var url = "dycjwh_dycjgl.do?method=toImport&bjdm="+bjdm;
                var title = "导入";
                showDialog(title, 550, 350, url,{close:function(){
                    if (jQuery("#search_go")){
                        jQuery("#search_go").click();
                    }
                }});
            }

            function importBHG(){
                var bjdm = jQuery("#bjdm").val();
                var url = "dycjwh_dycjgl.do?method=importBHG&bjdm="+bjdm;
                var title = "导入";
                showDialog(title, 550, 350, url,{close:function(){
                    if (jQuery("#search_go")){
                        jQuery("#search_go").click();
                    }
                }});
            }
            function del() {
                jQuery("input[ type='text']").val("");

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
			<input type="hidden" id="sffp" value="not"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li id="zcdr">
						<a href="javascript:void(0);" onclick="importXX();return false" class="btn_dr" >导入</a>
					</li>
					<li id="zcdc">
						<a href="javascript:void(0);" onclick="exportXX();return false" class="btn_dc" >导出</a>
					</li>

					<li id="bhgdr">
						<a href="javascript:void(0);" onclick="importBHG();return false" class="btn_dr" >导入</a>
					</li>
					<li id="bhgdc">
						<a href="javascript:void(0);" onclick="exportBHG();return false" class="btn_dc" >导出</a>
					</li>
					<li>
						<a href="javascript:void(0);" class="btn_fh" onclick="fhjcsd();return false;">返回</a>
					</li>
					<%--<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>--%>
				</ul>
			</div>

			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">学号/姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20"  />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="doQuery()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
			<div class="comp_title" id="comp_title">
				<ul style="width:90%">
					<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'not');"><span>考试成绩录入</span></a></li>
					<li><a href="javascript:void(0);" onclick="selectTab(this,'');"><span>补考考生及补考成绩录入</span></a></li>
				</ul>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

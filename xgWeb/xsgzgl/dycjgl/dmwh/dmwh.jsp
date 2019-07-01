<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dycjgl/dmwh/js/dmwh.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
            jQuery(function(){

                jQuery("#dataTable").initGrid(gridSetting);
                jQuery("#cxxmmc").bind("keypress",function(event){
                    if(event.keyCode==13||"13"==event.keyCode){
                        query();
                        return false;
                    }
                });
            })

            function query(){
                var map = {};
                map["cxxmmc"] = jQuery("#cxxmmc").val();
                jQuery("#dataTable").reloadGrid(map);
            }


            //增加
            function add(){
                var url = "dycjgl_dmwh.do?method=addDmwh";
                var title = "增加德育成绩项目";
                showDialog(title,500,350,url);
            }

            //修改
            function update(){
                var rows = jQuery("#dataTable").getSeletRow();

                if (rows.length != 1){
                    showAlertDivLayer("请选择一条您要修改的记录！");
                } else {
                    var url = 'dycjgl_dmwh.do?method=updateDmwh&xmdm='+rows[0]["xmdm"];
                    var title = "修改";
                    showDialog(title,500,350,url);
                }
            }


            //删除
            function del(){
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                var dms = "";
                for(var i=0;i<rows.length;i++){
                    if(i==rows.length-1)
					{
                        dms+=rows[i]["xmdm"];
					}
					else{
                        dms+=rows[i]["xmdm"]+",";
					}
                }

                if (dms.length == ""){
                    showAlertDivLayer("请选择您要删除的记录！");
                } else {
                    showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
                        jQuery.post("dycjgl_dmwh.do?method=delDmwh",{values:dms.toString()},function(data){
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                    }});

                }
            }


		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dycjgl_dmwh">
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">德育成绩项目名称</th>
						<td>
							<input type="text" id="cxxmmc" name="cxxmmc" maxleng="20" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
            var gridSetting;
            var gridSetting2;
			jQuery(function(){
                gridSetting = {
                    caption:"学生岗位申请列表",
                    pager:"pager",
                    url:"qgzx_xsgwsh.do?method=xslzshCx&type=query",
                    colList:[
                        {label:'',name:'sqbh', index: 'sqbh',key:true,hidden:true},
                        {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
                        {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                        {label:'类别',name:'pycc', index: 'pycc',width:'7%',formatter:function(value,cell){
                            if(value == "1"){
                                return "博士生";
                            } else if(value == "2"){return "硕士生";} else if(value == "3"){return "本科生"}
                            else if(value == "4"){
                                return "专科生"
                            } else if(value == "9"){
                                return "其他";
                            } else {
                                return value;
                            }
                        }},
                        {label:'行政班级',name:'bjmc', index: 'bjmc',width:'12%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'12%'},
                        {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'13%'},
                        {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                        {label:'申请时间',name:'sqsj', index: 'sqsj',width:'15%'},
                        {label:'审批状态',name:'shzt', index: 'shzt',width:'18%'},
                        {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
                        {label:'shid',name:'shid', index: 'shid',hidden:true},
                        {label:'审批流程',name:'splc', index: 'splc',hidden:true}
                    ],
                    params:{shlx:"dsh"},
                    sortname: "sqsj",
                    sortorder: "desc"
                };
                gridSetting2 = {
                    caption:"学生岗位申请列表",
                    pager:"pager",
                    url:"qgzx_xsgwsh.do?method=xslzshCx&type=query",
                    colList:[
                        {label:'',name:'sqbh', index: 'sqbh',key:true,hidden:true},
                        {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
                        {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                        {label:'类别',name:'pycc', index: 'pycc',width:'7%',formatter:function(value,cell){
                            if(value == "1"){
                                return "博士生";
                            } else if(value == "2"){return "硕士生";} else if(value == "3"){return "本科生"}
                            else if(value == "4"){
                                return "专科生"
                            } else if(value == "9"){
                                return "其他";
                            } else {
                                return value;
                            }
                        }},
                        {label:'行政班级',name:'bjmc', index: 'bjmc',width:'12%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'12%'},
                        {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'13%'},
                        {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                        {label:'申请时间',name:'sqsj', index: 'sqsj',width:'15%'},
                        {label:'审批状态',name:'shzt', index: 'shzt',width:'18%'},
                        {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
                        {label:'shid',name:'shid', index: 'shid',hidden:true},
                        {label:'审批流程',name:'splc', index: 'splc',hidden:true}

                    ],
                    params:{shlx:"ysh"},
                    sortname: "shsj",
                    sortorder: "desc"
                };
				jQuery("#dataTable").initGrid(gridSetting);
			});
            function go_sh(){
                var rows = jQuery("#dataTable").getSeletRow();
                var shlx = jQuery("#shlx").val();
                if(rows.length == 0){
                    showAlertDivLayer("请选择一条记录！");
                    return false;
                }else if (rows.length != 1){
                    plsh();
                } else {
                    showDialog("学生岗位审核",765,530,"qgzx_xsgwsh.do?method=xslzSh&sqbh="+rows[0]["sqbh"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"],{
                        close:function(){
                        }
                    });
                }
            }
            function xhLink(cellValue,row){
                return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
            }

            //审核信息查看
            function seeInfo(sqbh,xh){
                if(sqbh==""){
                    alertInfo("请您选择一条要查看的岗位申请！");
                } else {
                    showDialog("学生岗位审核",765,500,"qgzx_xsgwsh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh,{
                        close:function(){
                        }
                    });
                }
            }
            function query(obj,shlx){
                jQuery("#comp_title li").removeClass();
                jQuery(obj).parent().attr("class","ha");
                jQuery("#shlx").val(shlx);
                if(shlx=='ysh'){
                    jQuery("#btn_sh").hide();
                    jQuery("#btn_qxsh").show();
                    jQuery("#dataTable").initGrid(gridSetting2);
                }else{
                    jQuery("#btn_sh").show();
                    jQuery("#btn_qxsh").hide();
                    jQuery("#dataTable").initGrid(gridSetting);
                }
                searchRs();
            }
            function searchRs(){
                var map = getSuperSearch();
                map["shlx"] = jQuery.trim(jQuery("#shlx").val());
                jQuery("#dataTable").reloadGrid(map);
            }
            function plsh(shzt){
                var ids = jQuery("#dataTable").getSeletIds();

                if (ids.length == 0) {
                    showAlertDivLayer("请选择您要审核的记录！");
                } else {
                    showDialog("批量审核",600,300,"qgzx_xsgwsh.do?method=lzplsh");
                }
            }
            function savePlsh(shzt,shyj){
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                var shgws = [];
                for(var i=0;i<ids.length;i++){
                    shgws[i]=rows[i]["gwid"];
                }
                jQuery.post("qgzx_xsgwsh.do?method=lzplsh",{ids:ids.toString(),shgws:shgws.join(","),shyj:shyj,shzt:shzt,type:'save'},function(data){

                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        jQuery("#dataTable").reloadGrid();
                    }});
                },'json');
            }
            function cxshnew(){
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    alertInfo("请选择一条您要撤销审核的记录！");
                } else {
                    var splc = rows[0]["splc"];
                    var shid = rows[0]["shid"];

                    //最后一级撤销审核后调用的路径
                    confirmInfo("您确定要撤销操作吗?",function(ty){
                        if(ty=="ok"){
                            jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){

//                                jQuery.post("qgzx_xsgwsh.do?method=cxRollBack",{shid:data["shid"]},function(){
                                    // 判断是否最后一级撤销(1:最后一级撤销成功）
                                    if("1" == data["cancelFlg"]){
                                        jQuery.post("qgzx_xsgwsh.do?method=lzcancel",{splc:splc,shid:shid},function(result){
                                            showAlertDivLayer(result["message"],{},{"clkFun":function(){
                                                jQuery("#dataTable").reloadGrid();
                                            }});
                                        },'json');
                                    }else{
                                        showAlertDivLayer(data["message"],{},{"clkFun":function(){
                                            jQuery("#dataTable").reloadGrid();
                                        }});
                                    }
//                                });
                            },'json');
                        }
                    });
                }
            }
            //审批流查看
            function lcgz(){
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                if (ids.length != 1){
                    alertInfo("请选择一条记录！");
                } else {
                    showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
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
		<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
			<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				
					<div class="buttonbox">
						<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
							<a href="javascript:void(0);" id="btn_sh" onclick="go_sh();return false;" class="btn_sh">审核</a>
							<a href="javascript:void(0);" id="btn_qxsh" onclick="cxshnew();return false;" class="btn_sr" style="display: none;">撤销</a>
							</li>
							</logic:equal>
							<li><a href="javascript:void(0);" id="btn_cs" onclick="lcgz();return false;" class="btn_cs">流程跟踪</a></li>
						</ul>
					</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生岗位申请列表</span>
			</h3>
			<div class="con_overlfow">
			<table id="dataTable" ></table>
			<div id="pager"></div>
			</div>

		</div>
	</body>
</html>

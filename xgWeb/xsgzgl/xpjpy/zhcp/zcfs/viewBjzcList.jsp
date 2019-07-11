<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"综测班级列表",
				pager:"pager",
				url:"xpj_zcfs.do?method=viewBjzcList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'年级',name:'nj', index: 'nj',width:'8%'},
				   <%--{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'24%'},--%>
                    {label:'书院',name:'symc', index: 'sydm',width:'24%'},
//				   {label:'专业',name:'zymc', index: 'zydm',width:'23%'},
				   {label:'行政班级',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'17%'},
				   {label:'班级人数',name:'bjrs', index: 'bjrs',width:'8%',
					formatter:function(cellValue,rowObject){
								var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
								html.bind("click",function(){
									showDialog("查看综测分",700,500,"xpj_zcfs.do?method=viewZcfs&id="+rowObject["id"]);
								});
								return html;
							 }
				   }
				   /*xg_pjpy_new_cspzb szyf = 1 屏蔽提交人，提交状态*/
				   <logic:notEqual name="szyf" value="1">
				   ,{label:'提交人',name:'tjrxm', index: 'tjr',width:'10%'},
				   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true},
				   {label:'提交状态',name:'tjztmc', index: 'tjzt',width:'10%'}
				   </logic:notEqual>
				  
				],
//				sortname: "tjzt,nj,xydm,zydm",
                sortname: "tjzt,nj,sydm",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			//综测提交统计
			function zctjtj(){
				var url = "xpj_zcfs.do?method=getZcfAyTjTjcxList";
				document.location.href = url;
			}
			function initCpzCpmd(){
				showConfirmDivLayer("是否确认初始化当前学年学期参评名单？（该过程不可逆）",{"okFun":function(){
					var tips = loading();	
					tips.show();
					jQuery.post("xpj_zcfs.do?method=initCpzCpmd",{},function(data){
						tips.close();
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},"json");
				}});
			}
            function tbxfcj() {
                showConfirmDivLayer("是否同步学分成绩", {
                    "okFun": function () {
                        var url = "xpj_zcfs.do?method=tbXfcj";
                        jQuery.post(url, {}, function (data) {
                            showAlertDivLayer(data["message"], {}, {
                                "okFun": function () {
                                }
                            });
                            jQuery("#dataTable").reloadGrid();
                        }, "json");

                    }
                });
            }
			
		</script>
	</head>

	<body>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfs="${z.zdfs }" zxfs="${z.zxfs }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<input type="hidden" value="${szyf}"  id="szyf"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">				
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<html:form action="/xpj_zcfs" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						按班级调整<font color="red">评奖人员名单</font>，维护参加评奖学生的<font color="red">综测成绩</font>
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="true" name="cssz" property="kgzt">
								<li><a href="javascript:void(0);" onclick="editZcfs();" class="btn_xg"><bean:message key="lable.zcwh" /></a></li>
								<!-- xg_pjpy_new_cspzb szyf = 1 屏蔽提交按钮	 -->
								<logic:notEqual name="szyf" value="1"> 
									<li><a href="javascript:void(0);" onclick="submitZcfs();" class="btn_up"><bean:message key="lable.zctj" /></a></li>
								</logic:notEqual>
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="viewZcfs();" class="btn_ck">查看</a></li>
							<li><a href="javascript:void(0);" onclick="tbxfcj();return false;" class="btn_xg"
							>同步学分成绩</a></li>
							<logic:equal name="szyf" value="1">
								<li><a href="javascript:void(0);" onclick="zctjtj();return false;" title="点击查看本学年本学期各综测月份统计。" class="btn_tj">提交统计</a></li>				
							</logic:equal>
							<logic:equal name="userName" value="zf01">
							<!--  武昌首义个性化 增加初始化参评组参评名单功能  -->
							<logic:equal name="xxdm" value="12309">
								<li><a href="javascript:void(0);" onclick="initCpzCpmd();" class="btn_ck">参评名单初始化</a></li>
							</logic:equal>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>综测班级列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

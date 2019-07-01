<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xsgzgl/szdw/thjl/js/thlx.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"̸�������б�",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"szdw_thlx.do?method=queryThlxAction",
			colList:[
			   {label:'���ʹ���',name:'lxdm', index: 'lxdm', key:true},
			   {label:'��������',name:'lxmc', index: 'lxmc'}
			],
			sortname: "lxdm",
		 	sortorder: "asc"
		};
        var gridSetting1={
            caption:"����������б�",
            pager:"pager",
            multiselect:true,
            rowNum:10,
            url:"szdw_thlx.do?method=queryKhwtAction",
            colList:[
                {label:'����̸������',name:'ssthlxmc', index: 'ssthlxmc'},
                {label:'������������',name:'lxdm', index: 'lxdm', key:true},
                {label:'�������������',name:'lxmc', index: 'lxmc'}
            ],
            sortname: "lxdm",
            sortorder: "asc"
        };
        var gridSetting2={
            caption:"���������б�",
            pager:"pager",
            multiselect:true,
            rowNum:10,
            url:"szdw_thlx.do?method=queryWtmsAction",
            colList:[
                {label:'����̸������',name:'ssthlxmc', index: 'ssthlxmc'},
                {label:'�������������',name:'sskhwtmc', index: 'ssthlxmc'},
                {label:'������������',name:'lxdm', index: 'lxdm', key:true},
                {label:'������������',name:'lxmc', index: 'lxmc'}
            ],
            sortname: "lxdm",
            sortorder: "asc"
        };
        var gridSetting3={
            caption:"�ṩ�����б�",
            pager:"pager",
            multiselect:true,
            rowNum:10,
            url:"szdw_thlx.do?method=queryTgbzAction",
            colList:[
                {label:'��������',name:'lxdm', index: 'lxdm', key:true},
                {label:'��������',name:'lxmc', index: 'lxmc'}
            ],
            sortname: "lxdm",
            sortorder: "asc"
        };
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function reloadThlxDataTable(){
			jQuery("#dataTable").reloadGrid();
		}
        /*function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }*/


        function selectTab(obj,type){
            jQuery("#type").val(type);
            if (type == "thlx"){
                jQuery("#dataTable").initGrid(gridSetting);
            } else if(type == "khwt") {
                jQuery("#dataTable").initGrid(gridSetting1);
            }else if(type == "wtms"){
                jQuery("#dataTable").initGrid(gridSetting2);
			}else{
                jQuery("#dataTable").initGrid(gridSetting3);
            }
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
            reloadThlxDataTable();
        }
	</script>
  </head>
  
  <body>
    <div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
		</p>
	</div>
	<input type="hidden" value="${xxdm}" id="xxdm"/>
	<html:form action="/szdw_thlx">
	<input type="hidden" value="thlx" id="type"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="addThlx();return false;" class="btn_zj" id="zjButton">����</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="updateThlx();return false;" class="btn_xg" id="xgButton">�޸�</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="deleteThlx();return false;" class="btn_sc" id="scButton">ɾ��</a>
					</li>
				</logic:equal>
			  </ul>
			</div>
			<logic:equal value="10351" name="xxdm">
				<div class="comp_title" id="comp_title">
					<ul style="width:90%">
						<li class="ha">
							<a href="javascript:void(0);" onclick="selectTab(this,'thlx');">
								<span≯������</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="selectTab(this,'khwt');">
								<span>���������</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="selectTab(this,'wtms');">
								<span>��������</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="selectTab(this,'tgbz');">
								<span>�ṩ����</span>
							</a>
						</li>
					</ul>
				</div>
			</logic:equal>
		<div>
			<h3 class="datetitle_01">
				<span> 
					��ѯ���
					<font color="red">(����:&nbsp;�Դ������ͳһ���룬ͬʱ��������ɾ�����¼�¼��)</font>
				</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:350px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</html:form>
  </body>
</html>

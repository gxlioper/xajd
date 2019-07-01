<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxx.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"xsxx_xsgl.do?method=selectDy&type=query&hdid="+'${hdid}',
				colList:[
                    {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
                    {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'12%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'12%'},
                    {label:'רҵ',name:'zymc', index: 'zydm',width:'12%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'12%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'12%'}
				],
				params:{sffp:"0"},
				sortname: "xh",
			 	sortorder: "asc",
			 	radioselect:false
		}
		var gridSetting2 = {
				caption:"",
				pager:"pager",
				url:"xsxx_xsgl.do?method=hasSelectDy",
				colList:[
                    {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
                    {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'12%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'12%'},
                    {label:'רҵ',name:'zymc', index: 'zydm',width:'12%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'12%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'12%'}
				],
				params:{sffp:"1",xhs:jQuery("#xhs").val()},
				sortname: "xh",
			 	sortorder: "asc",
			 	radioselect:false
		}
        function searchRs() {
            var map = getSuperSearch();
            var sffp = jQuery("#sffp").val();
            if (null!=sffp&&sffp != "") {
                map["sffp"] = sffp;
            }else{
                map["sffp"] = "0";
            }
            map["xhs"]=jQuery("#xhs").val();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(){
		    var xhStr = jQuery("#xhs").val();
		    var xhs;
		    if(xhStr!=null&&xhStr!=""){
		        xhs = xhStr.split(",");
			}else {
		        xhs = new Array();
			}
            var rows = jQuery("#dataTable").getSeletRow();
		    if(rows.length<=0){
		        return false;
			}
            for(var i=0;i<rows.length;i++){
                var xh = xhs.indexOf(rows[i]["xh"]);
                if(rows[i]["xh"]=="${userName}"){
                    continue;//�����Լ�
                }else if(xhs.indexOf(rows[i]["xh"])>=0){
                    continue;//������ѡ
                }else {
                    xhs.push(rows[i]["xh"]);
                }
            }
            xhStr = xhs.join(",");
			jQuery("#xhs").val(xhStr);

        }
        function cancelSel() {
            var xhStr = jQuery("#xhs").val();
            var xhs;
            if(xhStr!=null&&xhStr!=""){
                xhs = xhStr.split(",");
            }else {
                xhs = new Array();
            }
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length<=0){
                showAlertDivLayer("��ѡ��Ҫ�߳��������Ա��");
                return false;
            }
            for(var i=0;i<rows.length;i++){
                var xh = xhs.indexOf(rows[i]["xh"]);
                xhs.splice(xh,1);
            }
            xhStr = xhs.join(",");
            jQuery("#xhs").val(xhStr);
            selectTab(this,'1');
        }
        function saveSel() {
            var url = "hdgl_hdxx.do?method=bm&hdid="+'${hdid}'+"&lx=createGroup";//��ӱ���
			var xhStr = jQuery("#xhs").val();
            var xhs;
            if(xhStr!=null&&xhStr!=""){
                xhs = xhStr.split(",");
            }else {
                xhs = new Array();
            }
            debugger;
            var dwrs = jQuery("#dwrs").val();
            if(xhs.length>dwrs-1){
                showAlertDivLayer("�����������������");
                return false;
            }
            for(var i=0;i<xhs.length;i++){
                url += "&xhs="+xhs[i];
            }
            showDialog("�������", 800, 550, url);
			iFClose();
        }
		jQuery(function(){
			var map = getSuperSearch();
			map["sffp"]="0";
			var checkVal = new Array();
			var checkValObj = jQuery("[name='checkVal']");
			for(var i=0;i<checkValObj.length;i++){
				checkVal.push(jQuery(checkValObj[i]).val());
			}
			map["ids"]=checkVal;
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<div class="prompt" id="div_help">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				������ѡ���ѷ�ҳȷ����ӣ�
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/hdgl_hdgl">
			<input type="hidden" name="xhs" id="xhs"/>
			<input type="hidden" name="dwrs" id="dwrs" value="${data.dwrs}"/>
			<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<input type="hidden" value="${hdid}" id="hdid"/>
			<input type="hidden" id="sffp" value="0"/>

			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_bc">
							<a href="javascript:void(0);" onclick="select();return false;"
							   title=""
							   class="btn_ccg">�������</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSel();return false;"
							   title=""
							   class="btn_qxsh">�߳�����</a>

							<a href="javascript:void(0);" onclick="saveSel();return false;"
							   title=""
							   class="btn_qxsh">ȷ�����</a>
						</li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>δѡ����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��ѡ����</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

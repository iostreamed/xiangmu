<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
      <!-- OA系统首页  -->
	    <li><a class="am-cf" href="javascript:void(0)"><span class="am-icon-home am-icon-fw"></span> 首页<span class="am-icon-angle-right am-fr am-margin-right"></span></a></li>
		<li>
			<a id="Address" name="address" class="am-cf" data-am-collapse="{target: '#collapse-address'}"><span class="am-icon-users am-icon-fw"></span> 个人通讯录<span id="address" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-address">
				<li><a href="${pageContext.request.contextPath}/address_addresslist.action"  class="am-cf menuBtn"> 通讯录管理</a></li>
			</ul>
		</li>
       <li>
       		<a id="Schedule" name="schedule" class="am-cf" data-am-collapse="{target: '#collapse-schedule'}"><span class="am-icon-calendar am-icon-fw"></span> 日程安排<span id="schedule" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-schedule">
				<li><a href="${pageContext.request.contextPath}/schedule_schedulelist.action"  class="am-cf menuBtn"> 日程管理</a></li>
			</ul>       		
       </li>
		<li>
       		<a id="Worklog" name="worklog" class="am-cf" data-am-collapse="{target: '#collapse-worklog'}"><span class="am-icon-tasks am-icon-fw"></span> 工作日志<span id="worklog" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-worklog">
				<li><a href="${pageContext.request.contextPath}/worklog_workloglist.action"  class="am-cf menuBtn"> 日志管理</a></li>
			</ul>       		
       </li>
       <li>
       		<a id="Message" name="message" class="am-cf" data-am-collapse="{target: '#collapse-message'}"><span class="am-icon-comment am-icon-fw"></span> 消息管理<span id="message" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-message">
				<li><a href="${pageContext.request.contextPath}/sms_addSms.action"  class="am-cf menuBtn">写消息</a></li>
				<li><a href="${pageContext.request.contextPath}/sms_smslist.action"  class="am-cf menuBtn">消息管理</a></li>
			</ul>       		
       </li>
       <li>
       		<a id="Meeting" name="meeting" class="am-cf" data-am-collapse="{target: '#collapse-meeting'}"><span class="am-icon-newspaper-o am-icon-fw"></span> 会议管理<span id="meeting" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-meeting">
				<li><a href="${pageContext.request.contextPath}/meeting_meetinglist.action"  class="am-cf menuBtn"> 会议管理</a></li>
			</ul>       		
       </li>
       <li>
       		<a id="Notice" name="notice" class="am-cf" data-am-collapse="{target: '#collapse-notice'}"><span class="am-icon-bullhorn am-icon-fw"></span> 公告管理<span id="notice" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-notice">
				<li><a href="${pageContext.request.contextPath}/notice_noticelist.action"  class="am-cf menuBtn"> 公告管理</a></li>
				<li><a href="${pageContext.request.contextPath}/notice_addNotice.action"  class="am-cf menuBtn"> 发布公告</a></li>
			</ul>       		
       </li>
       <li>
       		<a id="System" name="system" class="am-cf" data-am-collapse="{target: '#collapse-system'}"><span class="am-icon-gear am-icon-fw"></span> 系统管理<span id="system" class="am-icon-angle-right am-fr am-margin-right"></span></a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-system">
				<li><a href="${pageContext.request.contextPath}/user_userlist.action"  class="am-cf menuBtn"> 用户管理</a></li>
			</ul>      		
       </li>
       </ul>
    </div>
    <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 最新公告</p>
          <a href="${pageContext.request.contextPath}/notice_noticelist.action" class="notices">${notice1.title }</a>
        </div>
      </div>
     <script type="text/javascript">   
      	$(document).ready(function(){
      			$("#Address").click(function(){
      				var name = $(this).attr('name');
      				if($("#collapse-address").css("dispaly")=="none"){
      					$("#"+name).attr("class", "am-icon-angle-down am-fr am-margin-right");
      				}else{
      					$("#"+name).attr("class", "am-icon-angle-right am-fr am-margin-right");
      				}
      			});
      		}
      	);
      </script>
  </div>
<%@ page import="ncpstudy.pensionservicereserve.dao.ExtraCoverDao" %>
<%@ page import="ncpstudy.pensionservicereserve.domain.ExtraCover" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    int reservationId = Integer.parseInt(request.getParameter("reservationId"));
    int num = Integer.parseInt(request.getParameter("num"));
    ExtraCoverDao dao = new ExtraCoverDao();
    String message="";
    if(!dao.isApplied(reservationId)){
        ExtraCover extCover = new ExtraCover(reservationId, num);
        dao.insertExtCover(extCover);
    }else{
        message+="이미 신청하셨습니다.";
    }
    JSONObject obj = new JSONObject();
    obj.put("message",message);
%>
<%=obj%>




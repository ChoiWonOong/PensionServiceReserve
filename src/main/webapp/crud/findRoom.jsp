<%@ page import="ncpstudy.pensionservicereserve.dao.RoomDao" %>
<%@ page import="ncpstudy.pensionservicereserve.domain.Room" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    RoomDao dao = new RoomDao();
    Room room;
    room = dao.findRoom(id);
    JSONObject obj = new JSONObject();
    obj.put("roomId", room.getId());
    obj.put("roomName", room.getRoomName());
    obj.put("stdPeople", room.getStdNumPeople());
    obj.put("maxPeople", room.getMaxNumPeople());
    obj.put("link", room.getLink());
%>
<%=obj%>

<%@ page import="ncpstudy.pensionservicereserve.dao.RoomDao" %>
<%@ page import="ncpstudy.pensionservicereserve.domain.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    RoomDao dao = new RoomDao();
    List<Room> rooms = dao.listRoom();
    JSONArray jsonArray = new JSONArray();
    for(Room room : rooms){
        JSONObject obj = new JSONObject();
        obj.put("id", room.getId());
        obj.put("roomName", room.getRoomName());
        obj.put("link", room.getLink());
        obj.put("stdNum", room.getStdNumPeople());
        obj.put("maxNum", room.getMaxNumPeople());
        jsonArray.add(obj);
    }
%>
<%=jsonArray%>
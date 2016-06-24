package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import model.Command;
import model.FactoryCommand;


public class Controller extends HttpServlet {
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req, resp);
   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");
      resp.setCharacterEncoding("utf-8");
      String param = req.getParameter("param");
      String url ="";
      Command command = null;
      
      FactoryCommand factory = FactoryCommand.newInstance();
      command = factory.createCommand(param,null);
   
      try {
         url = (String)command.processCommand(req, resp);
      } catch (SerialException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      RequestDispatcher view = req.getRequestDispatcher(url);
      view.forward(req, resp);
      
   }
}
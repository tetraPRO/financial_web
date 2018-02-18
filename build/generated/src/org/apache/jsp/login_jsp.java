package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("            body{\n");
      out.write("                background-color: #999a9b;  \n");
      out.write("            }\n");
      out.write("            #contrastContainer{\n");
      out.write("                border: solid black 2px;\n");
      out.write("                border-radius: 35px;\n");
      out.write("                background-color: #333;\n");
      out.write("                color: #cccccc;\n");
      out.write("                margin: auto;\n");
      out.write("                padding: 55px 25px;\n");
      out.write("                width: 33%;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("         <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/register.css\"/>\n");
      out.write("        <title>tetraPRO( )</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <button id=\"register_open\" style=\"float: right; margin: 10px\">Register</button>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <br><br><br>\n");
      out.write("    <center><h1>tetraPRO( ) Financials</h1></center>\n");
      out.write("    <div id=\"contrastContainer\">\n");
      out.write("        <form name=\"userverify\" action=\"Auth_Servlet\" method=\"POST\" >\n");
      out.write("            <center>\n");
      out.write("                <table border=\"0\" align=\"center\">\n");
      out.write("                    <tbody>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Email: </label></td>\n");
      out.write("                            <td><input type=\"text\" name=\"email\"/></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Password: </label></td>\n");
      out.write("                            <td><input type=\"password\" name=\"password\"/></td>\n");
      out.write("                        <tr>\n");
      out.write("                    </tbody>\n");
      out.write("                </table><br>\n");
      out.write("                <input type=\"submit\" value=\"Let's Go!\"/>\n");
      out.write("            </center>\n");
      out.write("        </form>   \n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->\n");
      out.write("      <!--Starting Registration form-->\n");
      out.write("        \n");
      out.write("        <div id=\"register_wrapper\">\n");
      out.write("        <div id=\"register_window\">\n");
      out.write("\n");
      out.write("        <div style=\"text-align: right;\"><a id=\"register_close\" href=\"#\">close <b>X</b></a></div>\n");
      out.write("\n");
      out.write("        <center><p>Register with tetraPRO( ) Financials.</p></center>\n");
      out.write("\n");
      out.write("        <form id=\"register_feedback\" method=\"POST\" action=\"Register_Servlet\" accept-charset=\"UTF-8\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td style=\"float: right\"> <label>First Name: </label></td>\n");
      out.write("                    <td><input type=\"text\" autofocus required name=\"firstName\" value=\"\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td style=\"float: right\"> <label>Last Name: </label></td>\n");
      out.write("                    <td><input type=\"text\" required name=\"lastName\" value=\"\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td style=\"float: right\"> <label>Email: </label></td>\n");
      out.write("                    <td><input type=\"text\" required name=\"email\" value=\"\"></td>\n");
      out.write("                </tr>\n");
      out.write("                 <tr>\n");
      out.write("                    <td style=\"float: right\"> <label>Password: </label></td>\n");
      out.write("                    <td><input type=\"text\" required name=\"password\" value=\"\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td style=\"float: right\"> <label>Confirm: </label></td>\n");
      out.write("                    <td><input type=\"text\" required name=\"confirm\" value=\"\"></td>\n");
      out.write("                </tr>\n");
      out.write("            </table><br>\n");
      out.write("            <center><input type=\"submit\" name=\"registerForm\" value=\"Register Now\"></center>\n");
      out.write("        </form>\n");
      out.write("        </div> <!-- #register_window -->\n");
      out.write("        </div> <!-- #register_wrapper -->\n");
      out.write("        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->\n");
      out.write("        <script src=\"JS/register.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

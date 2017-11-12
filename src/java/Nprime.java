import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michał Śliwa
 */
@WebServlet(urlPatterns = {"/Nprime"})
public class Nprime extends HttpServlet
{
    @EJB(mappedName = "NprimeRemote")
    public NprimeRemote nPrimeBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        Enumeration<String> paramNames = request.getParameterNames();
        int n = -1;
        while (paramNames.hasMoreElements())
        {
            String param = request.getParameter(paramNames.nextElement());
            if (canParseInt(param))
            {
                n = Integer.parseInt(param);
                break;
            }
        }
        try (PrintWriter out = response.getWriter())
        {
            
            if (n != -1)
            {

                if(request.getMethod().equals("POST"))
                {
                    out.println(nPrimeBean.prime(n));
                }
                else if(request.getMethod().equals("GET"))
                {
                    int tempN = n;
                    int output = n;
                    while (output >= n)
                       output = nPrimeBean.prime(tempN-=4);
                    out.println(output);
                }
            }
            else
            {
                out.println("No n given");
            }
        }
    }
    
    private boolean canParseInt(String in)
    {
        try
        {
            Integer.parseInt(in);
            return true;
        }
        catch( NumberFormatException ex)
        {
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}

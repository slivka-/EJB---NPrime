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
 * @author Michał Śliwa
 */
@WebServlet(urlPatterns = {"/Nprime"})
public class Nprime extends HttpServlet
{
    private static final long serialVersionUID = 5674342757568856568L;
    
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
        //check url parameters for n value
        while (paramNames.hasMoreElements())
        {
            String param = request.getParameter(paramNames.nextElement());
            if (canParseInt(param))
            {
                //if parameter is parsable to int, save it and break
                n = Integer.parseInt(param);
                break;
            }
        }
        try (PrintWriter out = response.getWriter())
        {
            //if n is non negative number
            if (n > 0)
            {
                if (request.getMethod().equals("POST"))
                {
                    if(n>7)
                    {
                        //return smallest prime number greater than n
                        out.println(nPrimeBean.prime(n));
                    }
                    else
                    {
                       out.println(7);
                    }
                }
                else if (request.getMethod().equals("GET"))
                {
                    if(n>7)
                    {
                        //return biggest prime number lesser than n
                        int tempN = n;
                        int output = n;
                        while (output >= n)
                           output = nPrimeBean.prime(tempN-=4);
                        out.println(output);
                    }
                }
            }
            else
            {
                out.println("No n given");
            }
        }
    }
    
    /**
     * Checks if String can be parsed to int
     * @param in String to be parsed
     * @return true if it can be parsed, otherwise false
     */
    private boolean canParseInt(String in)
    {
        try
        {
            //parse int, resturn true
            Integer.parseInt(in);
            return true;
        }
        catch (NumberFormatException ex)
        {
            //on exception return false
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

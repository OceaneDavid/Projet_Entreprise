import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName="protectionFilter", urlPatterns = {"/*"})
public class ProtectionFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws IOException, ServletException {
	
	HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;
	HttpSession session = request.getSession();
	
	String contextPath = request.getServletContext().getContextPath();
	String newIp = request.getRemoteAddr();
	String newUserAgent = request.getHeader("user-agent");
	String oldIp = (String) session.getAttribute("ip");
	String oldUserAgent = (String) session.getAttribute("user-agent");
	
	try {
	    if (!newIp.equals(oldIp)) {
		session.setAttribute("ip", newIp);
	    }
	    
	    
	    if (!newUserAgent.equals(oldUserAgent)) {
		session.setAttribute("user-agent", newUserAgent);
	    }
	    chain.doFilter(request, response);
	} catch (IllegalStateException e) {
	    response.sendRedirect(contextPath + "/SetSessionServlet");
	}
    }

	@Override
	public void destroy() {

	}
}

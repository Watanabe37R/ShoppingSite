package jp.co.aforce.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/*")
public class LoginCheckFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//セッションがあれば取る、無ければ作らない
		HttpSession session = req.getSession(false);

		boolean loginStatus = (session != null && session.getAttribute("loginuser") != null);

		String path = req.getRequestURI();

		String sp = path.substring(req.getContextPath().length());

		//静的ファイルは通す
		if (sp.startsWith("/css") ||
				sp.startsWith("/js") ||
				sp.startsWith("/images")) {
			chain.doFilter(request, response);
			return;
		}

		// 通す条件（ホワイトリスト）
		boolean allow = sp.startsWith("/Login")
				|| sp.startsWith("/views/login")
				|| sp.startsWith("/Logout")
				|| sp.startsWith("/views/logout")
				|| sp.startsWith("/Registration")
				|| sp.startsWith("/views/registration")
				|| sp.endsWith("user-menu.jsp")
				|| sp.endsWith("userDelete-out.jsp");

		// ホワイトリストは通す
		if (allow) {
			chain.doFilter(request, response);
			return;
		}

		//それ以外で
		//ログインしてなければtopへ
		if (!loginStatus) {
			res.sendRedirect(req.getContextPath() + "/views/user-menu.jsp");
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

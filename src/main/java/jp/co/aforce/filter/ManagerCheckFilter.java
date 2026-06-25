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

import jp.co.aforce.bean.Users;

/**
 * Servlet Filter implementation class ManagerCheckFilter
 */
@WebFilter("/*")
public class ManagerCheckFilter extends HttpFilter implements Filter {

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

		boolean managerLogin = false;

		if (session != null) {
			Users user = new Users();
			user = (Users) session.getAttribute("loginuser");
			if (user != null && user.getManager() == 1) {
				managerLogin = true;
			}
		}

		String path = req.getRequestURI();

		String sp = path.substring(req.getContextPath().length());
		//静的ファイルは通す
		if (sp.startsWith("/css") ||
				sp.startsWith("/js") ||
				sp.startsWith("/img")) {
			chain.doFilter(request, response);
			return;
		}

		// ログイン画面は通す
		if (sp.equals("/views/login-in.jsp") || sp.startsWith("/Login")) {
			chain.doFilter(request, response);
			return;
		}

		// 通さない条件（ブラックリスト）

		boolean needLogin = sp.startsWith("/views/manager")
				|| sp.startsWith("/Manager");

		//それ以外で
		//ログインしてなければtopへ
		if (!managerLogin && needLogin) {
			res.sendRedirect(req.getContextPath() + "/views/login-in.jsp");
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

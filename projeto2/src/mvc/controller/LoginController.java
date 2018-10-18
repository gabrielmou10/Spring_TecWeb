package mvc.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import mvc.model.NotasDAO;
import mvc.model.Pessoas;

@Controller
public class LoginController {
	
	
	@RequestMapping("entrar")
	public String entrar(HttpSession session,
			@RequestParam(value = "login") String login,
			@RequestParam(value = "senha") String senha) throws SQLException {
		
		NotasDAO dao = new NotasDAO();
		Pessoas pessoa = new Pessoas();
		pessoa.setLogin(login);
		pessoa.setSenha(senha);
		
		
		int id = dao.autenticaUsuario(pessoa);
		pessoa.setId(id);
		if (id == -1) {
			return "redirect:login";
		} else {
			session.setAttribute("id_usuario", pessoa.getId());
			return "redirect:inicio";
		}
	}
	
	@RequestMapping("registrar")
	public String registrar(
			@RequestParam(value = "login") String login,
			@RequestParam(value = "senha") String senha) throws SQLException{
		
		NotasDAO dao = new NotasDAO();
		
		Pessoas pessoa = new Pessoas();
		pessoa.setLogin(login);
		pessoa.setSenha(senha);
		dao.adicionaPessoa(pessoa);
		
		dao.close();
		return "redirect:login";
	}
	
	
	@RequestMapping("PaginaRegistrar")
	public String adicionar_pessoa(){
		return "criarpessoas";
	}
	
	@RequestMapping("sair")  //desloga da sessao
	public String sair(HttpSession session) {
		session.invalidate();
		//return "redirect:login";
		return "login";
	}
	
	@RequestMapping("/") //pagina inicial de login
	public String incial() {
		return "login";
	}
	
	@RequestMapping("apagarPessoa")
	public String apagar(
			@RequestParam(value = "id_usuario") Integer id_usuario) throws SQLException{
		
		NotasDAO dao = new NotasDAO();
		dao.removePessoa(id_usuario);
		dao.close();
		return "login";
		
	}
	
	
	@RequestMapping("editarPessoa")
	public String editar(
			@RequestParam(value = "log") String login,
			@RequestParam(value = "sen") String senha,
			@RequestParam(value = "id_usuario") int id_usuario) throws SQLException{
		
		NotasDAO dao = new NotasDAO();
		//session.getAttribute("id_usuario");
		
		Pessoas pessoa = new Pessoas();
		pessoa.setLogin(login);
		pessoa.setSenha(senha);
		pessoa.setId(id_usuario);
		//pessoa.setId(session.getAttribute("id_usuario"));
		dao.alteraPessoa(pessoa);
		System.out.println("alterou");
		dao.close();
		
		return "login";
	}
	
	
	@RequestMapping("paginaEditarPessoa")
	public String editar_pessoa(HttpSession session, ModelMap model) throws SQLException{
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		model.addAttribute("pessoa_id", id_usuario);
		
		return "alteracredenciais";
	}


	@RequestMapping(value={"login"})
	public String inicio() {
		return "login";
	}

	
	
}

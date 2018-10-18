package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.NotasDAO;
import mvc.model.Notas;


@Controller
public class NotasController {
 
	//pagina inicial de notas
	@RequestMapping(value={"inicio"})
	public String inicio(HttpSession session, Model model) throws SQLException, IOException, ParseException {
		NotasDAO dao = new NotasDAO();
	 
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		ArrayList<Notas>  notas =  (ArrayList<Notas>) dao.getListaNotas(id_usuario);
		//List<Notas> listaNotas = dao.getListaNotas(id_usuario); 
	 
		model.addAttribute("notas", notas);
		return "index";
	}
 
 
 /*
	@RequestMapping("/adicionaNota")
	public String adiciona(Notas nota) {
		NotasDAO dao = new NotasDAO();
		dao.adicionaNota(nota);
		return "redirect:inicio";
	}
*/

	@RequestMapping("/adicionaNota")
	public String adicionar(HttpSession session, 
			//@RequestParam(value = "titulo") String titulo,          <input type="text" class="form-control" id="title_id" name="titulo" required >
			@RequestParam(value = "conteudo") String texto_nota) throws SQLException, IOException{ //
	
		NotasDAO dao = new NotasDAO();
		Notas nota = new Notas();
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");

		nota.setPessoa_id(id_usuario);
	 	nota.setConteudo(texto_nota);
	 	nota.setDateTime();
	 	System.out.println("data");
	 	System.out.println(nota.getDateTime());
	 	
	 	
	 	
		
		dao.adicionaNota(nota);
		dao.close();
		return "redirect:inicio";
	}
 
	@RequestMapping("/removeNota")
	public String remover(HttpSession session,
			@RequestParam(value = "id") Integer id_nota) throws SQLException {
		
		NotasDAO dao = new NotasDAO();
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		dao.removeNota(id_nota, id_usuario);
		dao.close();
		
		return "redirect:inicio";
	}
	
	@RequestMapping("/editaNota")
	public String editar(HttpSession session,
			@RequestParam(value = "id") Integer id_nota,
			@RequestParam(value = "conteudo") String texto_nota) throws SQLException, IOException {
		
		NotasDAO dao = new NotasDAO();
		Notas nota = new Notas();
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");

		
		nota.setId(id_nota);
	 	nota.setConteudo(texto_nota);
	 	nota.setPessoa_id(id_usuario);
	 	
		dao.alteraNota(nota);
		dao.close();
		
		return "redirect:inicio";
		
	}
	
	@RequestMapping("paginaAdicionarNota") //ir para a pagina de nova nota
	public String pagina_nova(HttpSession session){
		return "crianotas";
	}
	
	@RequestMapping("paginaEditaNota")
	public String pagina_edita(HttpSession session,
			@RequestParam(value = "id") Integer id_nota, ModelMap model) throws SQLException{
		
		NotasDAO dao = new NotasDAO();
		//Integer personId = (Integer) session.getAttribute("idLogado");
		
		String texto_nota = dao.getNota(id_nota);
		model.addAttribute("id", id_nota);
		//model.addAttribute("titulo", nota.getTitle());
		model.addAttribute("conteudo", texto_nota);
		
		return "atualizanotas";
	}
	
	
}
package mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import mvc.model.NotasDAO;
import mvc.model.Notas;


@Controller
public class NotasController {
 
	//pagina inicial de notas
	@RequestMapping(value={"inicio"})
	public String inicio(HttpSession session, Model model) throws SQLException, IOException, ParseException {
		NotasDAO dao = new NotasDAO();
	 
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		String gif_url = (String) session.getAttribute("palavra_gif");
		
		ArrayList<Notas>  notas =  (ArrayList<Notas>) dao.getListaNotas(id_usuario);
	 
		model.addAttribute("notas", notas);
		model.addAttribute("gif_url", gif_url);
		return "index";
	}
 

	@RequestMapping("adicionaNota")
	public String adicionar(HttpSession session, 

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
 
	@RequestMapping("removeNota")
	public String remover(HttpSession session,
			@RequestParam(value = "id") Integer id_nota) throws SQLException {
		
		NotasDAO dao = new NotasDAO();
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		dao.removeNota(id_nota, id_usuario);
		dao.close();
		
		return "redirect:inicio";
	}
	
	@RequestMapping("editaNota")
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
	public String pagina_nova(HttpSession session) throws Exception{
		
		return "crianotas";
	}
	
	@RequestMapping("paginaEditaNota")
	public String pagina_edita(HttpSession session, ModelMap model,
			@RequestParam(value = "id") Integer id_nota,
			@RequestParam(value = "tipo") Integer tipo) throws SQLException, IOException{
		
		NotasDAO dao = new NotasDAO();
		
		String texto_nota = dao.getNota(id_nota);
		model.addAttribute("id", id_nota);
		
		if(tipo == 1) {
			
			texto_nota = translate(texto_nota);
			model.addAttribute("titulo", "Traduzir Nota");
			
		} else {
			model.addAttribute("titulo", "Atualizar Nota");
			
		}
		
		model.addAttribute("conteudo", texto_nota);
		return "atualizanotas";
	}
	
	
	
	@RequestMapping("buscaGif") //buscar gif
	public String gif(HttpSession session,
			@RequestParam(value = "palavra_gif") String gif) throws Exception{
		api(gif, session);
		return "redirect:inicio";
	}
	
	
	
	
	public String api(String palavra, HttpSession session) throws IOException{
		
		String key = "SnHvNXR8QqeokFX97fU7VRdyqFhgJzpL";
		String  tag = palavra;
		
		String site = "https://api.giphy.com/v1/gifs/random?api_key="+key+"&tag="+tag+"&rating=R";
		
		site = site.replace(" ","%20");  //botar espacos no formato de url
		
		URL url = new URL(site);
		HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
		conexao.setRequestMethod("GET");
		conexao.connect();
		
		int resposta = conexao.getResponseCode(); 
		String inline = "";
		System.out.println("resposta");
		System.out.println(resposta);
		if(resposta != 200)
			throw new RuntimeException("HttpResponseCode: " +resposta);
			else
			{
	
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
				inline+=sc.nextLine();
				}

				sc.close();

				
				JsonElement root = new JsonParser().parse(inline);
				String gif = root.getAsJsonObject().get("data").getAsJsonObject().get("images").getAsJsonObject().get("fixed_height").getAsJsonObject().get("url").getAsString();
				System.out.println(gif);
				
				session.setAttribute("palavra_gif", gif);
				return gif;
			}
}
	
	
	
	
	
	public String translate(String conteudo) throws IOException{
		
		String key = "trnsl.1.1.20181020T202331Z.70073d6cfd12dec1.55ab310566aea1592eca3655c6de29a0fd1642ef";
		
		String lingua = "en";  //traduzir para ingles predefinido
		
		String  texto = conteudo;
		
		String site = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+key+"&text="+texto+"&lang="+lingua+"&format=plain&options=1";
		
		site = site.replace(" ","%20");  //botar espacos no formato de url
		
		URL url = new URL(site);
		
		HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
		conexao.setRequestMethod("GET");
		conexao.connect();
		
		int resposta = conexao.getResponseCode(); 
		String inline = "";
		
		if(resposta != 200)
			throw new RuntimeException("HttpResponseCode: " +resposta);
			else
			{

				Scanner sc = new Scanner(url.openStream(), "UTF-8");

				
				while(sc.hasNext())
				{
				inline+=sc.nextLine();
				}

				sc.close();

				
				
				JsonElement root = new JsonParser().parse(inline.toString());
				
				String traducao = root.getAsJsonObject().get("text").getAsString();

				return traducao;
				
			}
}
		}
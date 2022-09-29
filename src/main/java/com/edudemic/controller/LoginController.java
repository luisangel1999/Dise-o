package com.edudemic.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edudemic.entities.Rol;
import com.edudemic.entities.Usuario;
import com.edudemic.service.RolService;
import com.edudemic.service.UsuarioService;

@Controller
@RequestMapping
public class LoginController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;
	@GetMapping("/auth/registro")
	public String registroU(Model model) 
	{
		model.addAttribute("roles", rolService.listarRoles());
		model.addAttribute("usuario",new Usuario());
		return "registroA";
	}
	@PostMapping("/auth/registro")
	public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) 
	{

		if(result.hasErrors() || usuarioService.validar(usuario.getDni())) 
		{
			return "redirect:/auth/registro";
		}else 
		{
			List<Rol> nuevoList = rolService.listarStudent("ROLE_COLEGIO");
			usuario.setRol(nuevoList.get(0));
			model.addAttribute("usuario",usuarioService.registrarUsuario(usuario));
		}
		return "redirect:/auth/login";
	}
	@GetMapping("/auth/login")
	public String login(Model model) 
	{
		model.addAttribute("usuario", new Usuario());
		return "inicio";
	}
	@PostMapping("/auth/login-post")
	public String ingreso(Model model) 
	{
		model.addAttribute("usuario", new Usuario());
		return "index";
	}

}